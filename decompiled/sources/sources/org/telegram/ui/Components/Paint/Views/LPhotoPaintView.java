package org.telegram.ui.Components.Paint.Views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Pair;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatValueHolder;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;
import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.Bitmaps;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.VideoEditedInfo;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.AdjustPanLayoutHelper;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.BubbleActivity;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.BlurringShader;
import org.telegram.ui.Components.ChatActivityEnterViewAnimatedIconView;
import org.telegram.ui.Components.ChatAttachAlert;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EmojiView;
import org.telegram.ui.Components.IPhotoPaintView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Paint.Brush;
import org.telegram.ui.Components.Paint.ColorPickerBottomSheet;
import org.telegram.ui.Components.Paint.PaintTypeface;
import org.telegram.ui.Components.Paint.Painting;
import org.telegram.ui.Components.Paint.PersistColorPalette;
import org.telegram.ui.Components.Paint.PhotoFace;
import org.telegram.ui.Components.Paint.RenderView;
import org.telegram.ui.Components.Paint.Swatch;
import org.telegram.ui.Components.Paint.UndoStore;
import org.telegram.ui.Components.Paint.Views.EntitiesContainerView;
import org.telegram.ui.Components.Paint.Views.EntityView;
import org.telegram.ui.Components.Paint.Views.PaintTextOptionsView;
import org.telegram.ui.Components.Paint.Views.PaintToolsView;
import org.telegram.ui.Components.Paint.Views.PaintWeightChooserView;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.Size;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.SizeNotifierFrameLayoutPhoto;
import org.telegram.ui.Components.ThanosEffect;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.PhotoViewer;
import org.telegram.ui.Stories.recorder.EmojiBottomSheet;

/* loaded from: classes7.dex */
public abstract class LPhotoPaintView extends SizeNotifierFrameLayoutPhoto implements IPhotoPaintView, PaintToolsView.Delegate, EntityView.EntityViewDelegate, PaintTextOptionsView.Delegate, SizeNotifierFrameLayout.SizeNotifierFrameLayoutDelegate, NotificationCenter.NotificationCenterDelegate {
    private float baseScale;
    private Bitmap bitmapToEdit;
    private BlurredBackgroundDrawable blurredBackgroundDrawableForTools;
    public FrameLayout bottomLayout;
    private boolean bottomPanelIgnoreOnce;
    public PaintCancelView cancelButton;
    private TextView cancelTextButton;
    private final Paint clearPaint;
    private Paint colorPickerRainbowPaint;
    private Swatch colorSwatch;
    private Paint colorSwatchOutlinePaint;
    private Paint colorSwatchPaint;
    private PaintColorsListView colorsListView;
    private int currentAccount;
    private MediaController.CropState currentCropState;
    private EntityView currentEntityView;
    private boolean destroyed;
    public PaintDoneView doneButton;
    private TextView doneTextButton;
    private boolean drawShadow;
    private TextView drawTab;
    private boolean editingText;
    private int emojiPadding;
    private EmojiView emojiView;
    public boolean emojiViewVisible;
    public boolean emojiViewWasVisible;
    private int emojiWasPadding;
    public EntitiesContainerView entitiesView;
    private ArrayList<PhotoFace> faces;
    private Bitmap facesBitmap;
    private boolean fillShapes;
    private boolean ignoreLayout;
    private boolean ignoreToolChangeAnimationOnce;
    private float imageHeight;
    private float imageWidth;
    private boolean inBubbleMode;
    private float inputTransformX;
    private float inputTransformY;
    public boolean isAnimatePopupClosing;
    private boolean isColorListShown;
    private boolean isTypefaceMenuShown;
    private int keyboardHeight;
    private int keyboardHeightLand;
    public boolean keyboardVisible;
    private int lastSizeChangeValue1;
    private boolean lastSizeChangeValue2;
    private BigInteger lcm;
    private Matrix matrix;
    private float offsetTranslationY;
    private Runnable onDoneButtonClickedListener;
    private Runnable openKeyboardRunnable;
    private int originalBitmapRotation;
    public FrameLayout overlayLayout;
    private PaintToolsView paintToolsView;
    private Size paintingSize;
    private final PersistColorPalette palette;
    private float panTranslationProgress;
    private float panTranslationY;
    private float pany;
    private FrameLayout pipetteContainerLayout;
    private ActionBarPopupWindow.ActionBarPopupWindowLayout popupLayout;
    private Rect popupRect;
    private ActionBarPopupWindow popupWindow;
    private int[] pos2;
    private float[] position;
    private DispatchQueue queue;
    private View renderInputView;
    private RenderView renderView;
    private Theme.ResourcesProvider resourcesProvider;
    private float scale;
    private int selectedTextType;
    private FrameLayout selectionContainerView;
    private final AnimatedFloat shadowAlpha;
    private final Paint shadowPaint;
    private TextView stickerTab;
    public LinearLayout tabsLayout;
    private int tabsNewSelectedIndex;
    private int tabsSelectedIndex;
    private ValueAnimator tabsSelectionAnimator;
    private float tabsSelectionProgress;
    public PaintTextOptionsView textOptionsView;
    private TextView textTab;
    private ThanosEffect thanosEffect;
    private Paint toolsPaint;
    private SpringAnimation toolsTransformAnimation;
    private float toolsTransformProgress;
    private FrameLayout topLayout;
    private float transformX;
    private float transformY;
    private boolean translateBottomPanelAfterResize;
    private PaintTypefaceListView typefaceListView;
    private Paint typefaceMenuBackgroundPaint;
    private Paint typefaceMenuOutlinePaint;
    private SpringAnimation typefaceMenuTransformAnimation;
    private float typefaceMenuTransformProgress;
    private TextView undoAllButton;
    private ImageView undoButton;
    private UndoStore undoStore;
    private boolean waitingForKeyboardOpen;
    public PaintWeightChooserView weightChooserView;
    private PaintWeightChooserView.ValueOverride weightDefaultValueOverride;
    private LinearLayout zoomOutButton;
    private ImageView zoomOutImage;
    private TextView zoomOutText;
    private boolean zoomOutVisible;

    /* renamed from: org.telegram.ui.Components.Paint.Views.LPhotoPaintView$1, reason: invalid class name */
    public class AnonymousClass1 implements PaintWeightChooserView.ValueOverride {
        final /* synthetic */ LPhotoPaintView this$0;

        public AnonymousClass1(LPhotoPaintView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.Paint.Views.PaintWeightChooserView.ValueOverride
        public float get() {
            Brush r0 = LPhotoPaintView.m11487$$Nest$fgetrenderView(this.this$0).getCurrentBrush();
            LPhotoPaintView r2 = this.this$0;
            if (r0 != null) goto L7;
            return PersistColorPalette.getInstance(LPhotoPaintView.m11475$$Nest$fgetcurrentAccount(r2)).getCurrentWeight();
        L7:
            return PersistColorPalette.getInstance(LPhotoPaintView.m11475$$Nest$fgetcurrentAccount(r2)).getWeight(String.valueOf(Brush.BRUSHES_LIST.indexOf(r0)), r0.getDefaultWeight());
        }

        @Override // org.telegram.ui.Components.Paint.Views.PaintWeightChooserView.ValueOverride
        public void set(float r4) {
            PersistColorPalette.getInstance(LPhotoPaintView.m11475$$Nest$fgetcurrentAccount(this.this$0)).setWeight(String.valueOf(Brush.BRUSHES_LIST.indexOf(LPhotoPaintView.m11487$$Nest$fgetrenderView(this.this$0).getCurrentBrush())), r4);
            LPhotoPaintView.m11471$$Nest$fgetcolorSwatch(this.this$0).brushWeight = r4;
            LPhotoPaintView r3 = this.this$0;
            LPhotoPaintView.m11510$$Nest$msetCurrentSwatch(r3, LPhotoPaintView.m11471$$Nest$fgetcolorSwatch(r3), true);
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.Views.LPhotoPaintView$10, reason: invalid class name */
    public class AnonymousClass10 implements ColorPickerBottomSheet.PipetteDelegate {
        private boolean hasPipette;
        final /* synthetic */ LPhotoPaintView this$0;
        final /* synthetic */ Bitmap val$originalBitmap;

        public AnonymousClass10(LPhotoPaintView r1, Bitmap r2) {
            this.this$0 = r1;
            this.val$originalBitmap = r2;
        }

        @Override // org.telegram.ui.Components.Paint.ColorPickerBottomSheet.PipetteDelegate
        public ViewGroup getContainerView() {
            return LPhotoPaintView.m11484$$Nest$fgetpipetteContainerLayout(this.this$0);
        }

        @Override // org.telegram.ui.Components.Paint.ColorPickerBottomSheet.PipetteDelegate
        public View getSnapshotDrawingView() {
            return this.this$0;
        }

        @Override // org.telegram.ui.Components.Paint.ColorPickerBottomSheet.PipetteDelegate
        public boolean isPipetteAvailable() {
            if (this.val$originalBitmap == null) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // org.telegram.ui.Components.Paint.ColorPickerBottomSheet.PipetteDelegate
        public boolean isPipetteVisible() {
            return this.hasPipette;
        }

        @Override // org.telegram.ui.Components.Paint.ColorPickerBottomSheet.PipetteDelegate
        public void onColorSelected(int r3) {
            LPhotoPaintView.m11512$$Nest$mshowColorList(this.this$0, false);
            LPhotoPaintView.m11483$$Nest$fgetpalette(this.this$0).selectColor(r3);
            LPhotoPaintView.m11483$$Nest$fgetpalette(this.this$0).saveColors();
            LPhotoPaintView.m11511$$Nest$msetNewColor(this.this$0, r3);
            LPhotoPaintView.m11474$$Nest$fgetcolorsListView(this.this$0).setSelectedColorIndex(LPhotoPaintView.m11483$$Nest$fgetpalette(this.this$0).getCurrentColorPosition());
            LPhotoPaintView.m11474$$Nest$fgetcolorsListView(this.this$0).getAdapter().notifyDataSetChanged();
        }

        @Override // org.telegram.ui.Components.Paint.ColorPickerBottomSheet.PipetteDelegate
        public void onDrawImageOverCanvas(Bitmap r3, Canvas r4) {
            Matrix r32 = LPhotoPaintView.m11487$$Nest$fgetrenderView(this.this$0).getMatrix();
            r4.save();
            r4.translate(LPhotoPaintView.m11487$$Nest$fgetrenderView(this.this$0).getX(), LPhotoPaintView.m11487$$Nest$fgetrenderView(this.this$0).getY());
            r4.concat(r32);
            r4.scale(LPhotoPaintView.m11487$$Nest$fgetrenderView(this.this$0).getWidth() / this.val$originalBitmap.getWidth(), LPhotoPaintView.m11487$$Nest$fgetrenderView(this.this$0).getHeight() / this.val$originalBitmap.getHeight(), 0.0f, 0.0f);
            r4.drawBitmap(this.val$originalBitmap, 0.0f, 0.0f, null);
            r4.restore();
        }

        @Override // org.telegram.ui.Components.Paint.ColorPickerBottomSheet.PipetteDelegate
        public void onStartColorPipette() {
            this.hasPipette = true;
        }

        @Override // org.telegram.ui.Components.Paint.ColorPickerBottomSheet.PipetteDelegate
        public void onStopColorPipette() {
            this.hasPipette = false;
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.Views.LPhotoPaintView$11, reason: invalid class name */
    public class AnonymousClass11 implements PaintWeightChooserView.ValueOverride {
        final /* synthetic */ LPhotoPaintView this$0;
        final /* synthetic */ float val$base;
        final /* synthetic */ TextPaintView val$textPaintView;

        public AnonymousClass11(LPhotoPaintView r1, TextPaintView r2, float r3) {
            this.this$0 = r1;
            this.val$textPaintView = r2;
            this.val$base = r3;
        }

        @Override // org.telegram.ui.Components.Paint.Views.PaintWeightChooserView.ValueOverride
        public float get() {
            return this.val$textPaintView.getBaseFontSize() / this.val$base;
        }

        @Override // org.telegram.ui.Components.Paint.Views.PaintWeightChooserView.ValueOverride
        public void set(float r3) {
            this.val$textPaintView.disableAutoresize(true);
            this.val$textPaintView.setBaseFontSize((int) (this.val$base * r3));
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.Views.LPhotoPaintView$12, reason: invalid class name */
    public class AnonymousClass12 extends LinearLayout {
        Paint linePaint;
        final /* synthetic */ LPhotoPaintView this$0;

        public AnonymousClass12(LPhotoPaintView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            Paint r12 = new Paint(1);
            this.linePaint = r12;
            r12.setStrokeWidth(AndroidUtilities.dp(2.0f));
            this.linePaint.setStyle(Paint.Style.STROKE);
            this.linePaint.setStrokeCap(Paint.Cap.ROUND);
            setWillNotDraw(false);
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onDraw(Canvas r12) {
            super.onDraw(r12);
            TextView r0 = (TextView) getChildAt(LPhotoPaintView.m11490$$Nest$fgettabsSelectedIndex(this.this$0));
            Layout r3 = null;
            if (LPhotoPaintView.m11489$$Nest$fgettabsNewSelectedIndex(this.this$0) == (-1)) goto L5;
            TextView r1 = (TextView) getChildAt(LPhotoPaintView.m11489$$Nest$fgettabsNewSelectedIndex(this.this$0));
        L6:
            this.linePaint.setColor(r0.getCurrentTextColor());
            float r7 = ((r0.getY() + r0.getHeight()) - r0.getPaddingBottom()) + AndroidUtilities.dp(3.0f);
            Layout r2 = r0.getLayout();
            if (r1 == null) goto L9;
            r3 = r1.getLayout();
        L9:
            float r4 = 0.0f;
            if (r3 != null) goto L12;
            float r5 = 0.0f;
        L13:
            float r02 = r0.getX() + r2.getPrimaryHorizontal(r2.getLineStart(0));
            if (r1 == null) goto L16;
            float r13 = r1.getX() + r3.getPrimaryHorizontal(r2.getLineStart(0));
        L17:
            float r03 = AndroidUtilities.lerp(r02, r13, r5);
            float r14 = r2.getPrimaryHorizontal(r2.getLineEnd(0)) - r2.getPrimaryHorizontal(r2.getLineStart(0));
            if (r3 == null) goto L20;
            r4 = r3.getPrimaryHorizontal(r3.getLineEnd(0)) - r3.getPrimaryHorizontal(r3.getLineStart(0));
        L20:
            r12.drawLine(r03, r7, r03 + AndroidUtilities.lerp(r14, r4, r5), r7, this.linePaint);
            return;
        L16:
            r13 = 0.0f;
            goto L17
        L12:
            r5 = CubicBezierInterpolator.DEFAULT.getInterpolation(LPhotoPaintView.m11492$$Nest$fgettabsSelectionProgress(this.this$0));
            goto L13
        L5:
            r1 = null;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.Views.LPhotoPaintView$13, reason: invalid class name */
    public class AnonymousClass13 extends AnimatorListenerAdapter {
        final /* synthetic */ LPhotoPaintView this$0;
        final /* synthetic */ int val$index;
        final /* synthetic */ View val$newView;
        final /* synthetic */ View val$view;

        public AnonymousClass13(LPhotoPaintView r1, View r2, View r3, int r4) {
            this.this$0 = r1;
            this.val$view = r2;
            this.val$newView = r3;
            this.val$index = r4;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            LPhotoPaintView r0 = this.this$0;
            LPhotoPaintView.m11503$$Nest$fputtabsSelectedIndex(r0, LPhotoPaintView.m11489$$Nest$fgettabsNewSelectedIndex(r0));
            LPhotoPaintView.m11502$$Nest$fputtabsNewSelectedIndex(this.this$0, -1);
            this.this$0.tabsLayout.invalidate();
            View r02 = this.val$view;
            if (r02 == null) goto L8;
            if (this.val$newView == null) goto L8;
            r02.setVisibility(8);
        L8:
            if (r3 != LPhotoPaintView.m11491$$Nest$fgettabsSelectionAnimator(this.this$0)) goto L11;
            LPhotoPaintView.m11504$$Nest$fputtabsSelectionAnimator(this.this$0, null);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r3) {
            if (this.val$view == null) goto L7;
            View r32 = this.val$newView;
            if (r32 == null) goto L7;
            r32.setVisibility(0);
        L7:
            int r33 = this.val$index;
            LPhotoPaintView r0 = this.this$0;
            if (r33 != 2) goto L11;
            r0.weightChooserView.setMinMax(0.5f, 2.0f);
            return;
        L11:
            Brush r34 = LPhotoPaintView.m11487$$Nest$fgetrenderView(r0).getCurrentBrush();
            if ((r34 instanceof Brush.Blurer) == false) goto L14;
        L18:
            this.this$0.weightChooserView.setMinMax(0.4f, 1.75f);
            return;
        L14:
            if ((r34 instanceof Brush.Eraser) == true) goto L18;
            this.this$0.weightChooserView.setMinMax(0.05f, 1.0f);
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.Views.LPhotoPaintView$14, reason: invalid class name */
    public class AnonymousClass14 extends EmojiBottomSheet {
        final /* synthetic */ LPhotoPaintView this$0;

        public AnonymousClass14(LPhotoPaintView r1, Context r2, boolean r3, Theme.ResourcesProvider r4, boolean r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Stories.recorder.EmojiBottomSheet
        public boolean canShowWidget(Integer r1) {
            if (r1.intValue() != 2) goto L6;
            return true;
        L6:
            return false;
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.Views.LPhotoPaintView$15, reason: invalid class name */
    public class AnonymousClass15 extends ChatActivity {
        final /* synthetic */ LPhotoPaintView this$0;

        public AnonymousClass15(LPhotoPaintView r1, Bundle r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.ChatActivity, org.telegram.ui.Components.ChatActivityInterface
        public TLRPC.User getCurrentUser() {
            return UserConfig.getInstance(this.currentAccount).getCurrentUser();
        }

        @Override // org.telegram.ui.ChatActivity, org.telegram.ui.Components.ChatActivityInterface, org.telegram.ui.Components.InstantCameraView.Delegate
        public long getDialogId() {
            return 0;
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public Activity getParentActivity() {
            return AndroidUtilities.findActivity(this.this$0.getContext());
        }

        @Override // org.telegram.ui.ChatActivity, org.telegram.ui.ActionBar.BaseFragment
        public Theme.ResourcesProvider getResourceProvider() {
            return LPhotoPaintView.m11488$$Nest$fgetresourcesProvider(this.this$0);
        }

        @Override // org.telegram.ui.ChatActivity
        public boolean isKeyboardVisible() {
            return false;
        }

        @Override // org.telegram.ui.ChatActivity, org.telegram.ui.ActionBar.BaseFragment
        public boolean isLightStatusBar() {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.Views.LPhotoPaintView$16, reason: invalid class name */
    public class AnonymousClass16 implements ChatAttachAlert.ChatAttachViewDelegate {
        long start;
        final /* synthetic */ LPhotoPaintView this$0;
        final /* synthetic */ ChatAttachAlert val$chatAttachAlert;

        public AnonymousClass16(LPhotoPaintView r1, ChatAttachAlert r2) {
            this.this$0 = r1;
            this.val$chatAttachAlert = r2;
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate
        public void didPressedButton(int r1, boolean r2, boolean r3, int r4, int r5, long r6, boolean r8, boolean r9, long r10) {
            HashMap<Object, Object> r12 = this.val$chatAttachAlert.getPhotoLayout().getSelectedPhotos();     // Catch: Throwable -> L10
            if (r12.isEmpty() == true) goto L15;
            MediaController.PhotoEntry r13 = (MediaController.PhotoEntry) r12.values().iterator().next();     // Catch: Throwable -> L10
            String r22 = r13.imagePath;     // Catch: Throwable -> L10
            if (r22 != null) goto L8;
            r22 = r13.path;     // Catch: Throwable -> L10
        L8:
            LPhotoPaintView r14 = this.this$0;     // Catch: Throwable -> L10
            r14.appearAnimation(r14.createPhoto(r22, true));     // Catch: Throwable -> L10
            this.val$chatAttachAlert.dismiss();     // Catch: Throwable -> L10
            return;
        L15:
            return;
        L10:
            th = move-exception;
            FileLog.e(th);
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate
        public boolean selectItemOnClicking() {
            this.start = System.currentTimeMillis();
            return true;
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.Views.LPhotoPaintView$17, reason: invalid class name */
    public class AnonymousClass17 extends StickerView {
        final /* synthetic */ LPhotoPaintView this$0;

        public AnonymousClass17(LPhotoPaintView r1, Context r2, PointF r3, float r4, float r5, Size r6, TLRPC.Document r7, Object r8) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7, r8);
        }

        @Override // org.telegram.ui.Components.Paint.Views.StickerView
        public void didSetAnimatedSticker(RLottieDrawable r1) {
            this.this$0.didSetAnimatedSticker(r1);
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.Views.LPhotoPaintView$18, reason: invalid class name */
    public class AnonymousClass18 implements Runnable {
        final /* synthetic */ LPhotoPaintView this$0;

        public AnonymousClass18(LPhotoPaintView r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if ((LPhotoPaintView.m11476$$Nest$fgetcurrentEntityView(this.this$0) instanceof TextPaintView) == false) goto L27;
            EditTextOutline r0 = ((TextPaintView) LPhotoPaintView.m11476$$Nest$fgetcurrentEntityView(this.this$0)).getEditText();
            if (LPhotoPaintView.m11477$$Nest$fgetdestroyed(this.this$0) == true) goto L20;
            if (r0 != null) goto L9;
            return;
        L9:
            if (LPhotoPaintView.m11500$$Nest$fgetwaitingForKeyboardOpen(this.this$0) == true) goto L11;
            return;
        L11:
            if (this.this$0.keyboardVisible == false) goto L13;
            return;
        L13:
            if (AndroidUtilities.usingHardwareInput == false) goto L15;
            return;
        L15:
            if (AndroidUtilities.isInMultiwindow == false) goto L17;
            return;
        L17:
            if (AndroidUtilities.isTablet() == false) goto L26;
            r0.requestFocus();
            AndroidUtilities.showKeyboard(r0);
            AndroidUtilities.cancelRunOnUIThread(LPhotoPaintView.m11481$$Nest$fgetopenKeyboardRunnable(this.this$0));
            AndroidUtilities.runOnUIThread(LPhotoPaintView.m11481$$Nest$fgetopenKeyboardRunnable(this.this$0), 100);
            return;
        L26:
            return;
        L20:
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.Views.LPhotoPaintView$19, reason: invalid class name */
    public class AnonymousClass19 extends AnimatorListenerAdapter {
        final /* synthetic */ LPhotoPaintView this$0;

        public AnonymousClass19(LPhotoPaintView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            LPhotoPaintView.m11478$$Nest$fgetemojiView(this.this$0).setTranslationY(0.0f);
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.Views.LPhotoPaintView$2, reason: invalid class name */
    public class AnonymousClass2 extends RenderView {
        final /* synthetic */ LPhotoPaintView this$0;
        final /* synthetic */ Bitmap val$originalBitmap;

        public AnonymousClass2(LPhotoPaintView r1, Context r2, Painting r3, Bitmap r4, Bitmap r5, BlurringShader.BlurManager r6, Bitmap r7) {
            this.this$0 = r1;
            this.val$originalBitmap = r7;
            super(r2, r3, r4, r5, r6);
        }

        @Override // org.telegram.ui.Components.Paint.RenderView
        public void selectBrush(Brush r4) {
            int r0 = Brush.BRUSHES_LIST.indexOf(r4);
            int r1 = r0 + 1;
            if (r1 > 1) goto L5;
        L7:
            r0 = r1;
        L8:
            LPhotoPaintView.m11482$$Nest$fgetpaintToolsView(this.this$0).select(r0);
            this.this$0.onBrushSelected(r4);
            return;
        L5:
            if (this.val$originalBitmap != null) goto L7;
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.Views.LPhotoPaintView$20, reason: invalid class name */
    public class AnonymousClass20 extends AnimatorListenerAdapter {
        final /* synthetic */ LPhotoPaintView this$0;

        public AnonymousClass20(LPhotoPaintView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            LPhotoPaintView r22 = this.this$0;
            r22.isAnimatePopupClosing = false;
            LPhotoPaintView.m11478$$Nest$fgetemojiView(r22).setTranslationY(0.0f);
            LPhotoPaintView.m11508$$Nest$mhideEmojiView(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.Views.LPhotoPaintView$21, reason: invalid class name */
    public class AnonymousClass21 implements EmojiView.EmojiViewDelegate {
        int innerTextChange;
        final /* synthetic */ LPhotoPaintView this$0;

        public static /* synthetic */ void $r8$lambda$3wTbxBNoUnJGp8GvJYgrTGpBQVk(AnonymousClass21 r0, AlertDialog r1, int r2) {
            r0.lambda$onClearEmojiRecent$0(r1, r2);
        }

        public AnonymousClass21(LPhotoPaintView r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onClearEmojiRecent$0(AlertDialog r1, int r2) {
            LPhotoPaintView.m11478$$Nest$fgetemojiView(this.this$0).clearRecentEmoji();
        }

        @Override // org.telegram.ui.Components.EmojiView.EmojiViewDelegate
        public void onAnimatedEmojiUnlockClick() {
        }

        @Override // org.telegram.ui.Components.EmojiView.EmojiViewDelegate
        public boolean onBackspace() {
            EditTextOutline r3 = ((TextPaintView) LPhotoPaintView.m11476$$Nest$fgetcurrentEntityView(this.this$0)).getEditText();
            if (r3 != null) goto L5;
        L9:
            return false;
        L5:
            if (r3.length() == 0) goto L9;
            r3.dispatchKeyEvent(new KeyEvent(0, 67));
            return true;
        }

        @Override // org.telegram.ui.Components.EmojiView.EmojiViewDelegate
        public void onClearEmojiRecent() {
            AlertDialog.Builder r0 = new AlertDialog.Builder(this.this$0.getContext(), LPhotoPaintView.m11488$$Nest$fgetresourcesProvider(this.this$0));
            r0.setTitle(LocaleController.getString(R.string.ClearRecentEmojiTitle));
            r0.setMessage(LocaleController.getString(R.string.ClearRecentEmojiText));
            r0.setPositiveButton(LocaleController.getString(R.string.ClearButton), new LPhotoPaintView$21$$ExternalSyntheticLambda0(this));
            r0.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
            r0.show();
        }

        @Override // org.telegram.ui.Components.EmojiView.EmojiViewDelegate
        public void onCustomEmojiSelected(long r4, TLRPC.Document r6, String r7, boolean r8) {
            EditTextOutline r82 = ((TextPaintView) LPhotoPaintView.m11476$$Nest$fgetcurrentEntityView(this.this$0)).getEditText();
            if (r82 != null) goto L5;
            return;
        L5:
            int r0 = r82.getSelectionEnd();
            if (r0 >= 0) goto L25;
            r0 = 0;
        L25:
            this.innerTextChange = 2;     // Catch: Throwable -> L12 Exception -> L14
            SpannableString r2 = new SpannableString(r7);     // Catch: Throwable -> L12 Exception -> L14
            if (r6 == null) goto L16;
            AnimatedEmojiSpan r42 = new AnimatedEmojiSpan(r6, r82.getPaint().getFontMetricsInt());     // Catch: Throwable -> L12 Exception -> L14
        L17:
            r2.setSpan(r42, 0, r2.length(), 33);     // Catch: Throwable -> L12 Exception -> L14
            r82.setText(r82.getText().insert(r0, r2));     // Catch: Throwable -> L12 Exception -> L14
            int r02 = r0 + r2.length();     // Catch: Throwable -> L12 Exception -> L14
            r82.setSelection(r02, r02);     // Catch: Throwable -> L12 Exception -> L14
            this.innerTextChange = 0;
            return;
        L16:
            r42 = new AnimatedEmojiSpan(r4, r82.getPaint().getFontMetricsInt());     // Catch: Throwable -> L12 Exception -> L14
        L14:
            e = move-exception;
            FileLog.e(e);     // Catch: Throwable -> L12
            this.innerTextChange = 0;
            return;
        L12:
            th = move-exception;
            this.innerTextChange = 0;
            throw th;
        }

        @Override // org.telegram.ui.Components.EmojiView.EmojiViewDelegate
        public void onEmojiSelected(String r8) {
            if ((LPhotoPaintView.m11476$$Nest$fgetcurrentEntityView(this.this$0) instanceof TextPaintView) == false) goto L35;
            TextPaintView r0 = (TextPaintView) LPhotoPaintView.m11476$$Nest$fgetcurrentEntityView(this.this$0);
            EditTextOutline r1 = r0.getEditText();
            if (r1 != null) goto L8;
            return;
        L8:
            int r2 = r1.getSelectionEnd();
            if (r2 >= 0) goto L32;
            r2 = 0;
        L32:
            this.innerTextChange = 2;     // Catch: Throwable -> L20 Exception -> L22
            CharSequence r82 = Emoji.replaceEmoji(r8, r0.getFontMetricsInt(), false);     // Catch: Throwable -> L20 Exception -> L22
            if ((r82 instanceof Spanned) == false) goto L24;
            Emoji.EmojiSpan[] r02 = (Emoji.EmojiSpan[]) ((Spanned) r82).getSpans(0, r82.length(), Emoji.EmojiSpan.class);     // Catch: Throwable -> L20 Exception -> L22
            if (r02 == null) goto L24;
            int r4 = 0;
        L18:
            if (r4 >= r02.length) goto L24;
            r02[r4].scale = 0.85f;     // Catch: Throwable -> L20 Exception -> L22
            r4 = r4 + 1;     // Catch: Throwable -> L20 Exception -> L22
        L24:
            r1.setText(r1.getText().insert(r2, r82));     // Catch: Throwable -> L20 Exception -> L22
            int r22 = r2 + r82.length();     // Catch: Throwable -> L20 Exception -> L22
            r1.setSelection(r22, r22);     // Catch: Throwable -> L20 Exception -> L22
            this.innerTextChange = 0;
            return;
        L20:
            th = move-exception;
            this.innerTextChange = 0;
            throw th;
        L22:
            e = move-exception;
            FileLog.e(e);     // Catch: Throwable -> L20
            this.innerTextChange = 0;
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.Views.LPhotoPaintView$3, reason: invalid class name */
    public class AnonymousClass3 implements RenderView.RenderViewDelegate {
        final /* synthetic */ LPhotoPaintView this$0;
        final /* synthetic */ Runnable val$onInit;

        public AnonymousClass3(LPhotoPaintView r1, Runnable r2) {
            this.this$0 = r1;
            this.val$onInit = r2;
        }

        @Override // org.telegram.ui.Components.Paint.RenderView.RenderViewDelegate
        public void invalidateInputView() {
            if (LPhotoPaintView.m11486$$Nest$fgetrenderInputView(this.this$0) == null) goto L6;
            LPhotoPaintView.m11486$$Nest$fgetrenderInputView(this.this$0).invalidate();
            return;
        }

        @Override // org.telegram.ui.Components.Paint.RenderView.RenderViewDelegate
        public void onBeganDrawing() {
            if (LPhotoPaintView.m11476$$Nest$fgetcurrentEntityView(this.this$0) == null) goto L5;
            LPhotoPaintView.m11509$$Nest$mselectEntity(this.this$0, null);
        L5:
            this.this$0.weightChooserView.setViewHidden(true);
        }

        @Override // org.telegram.ui.Components.Paint.RenderView.RenderViewDelegate
        public void onFinishedDrawing(boolean r1) {
            LPhotoPaintView.m11499$$Nest$fgetundoStore(this.this$0).getDelegate().historyChanged();
            this.this$0.weightChooserView.setViewHidden(false);
        }

        @Override // org.telegram.ui.Components.Paint.RenderView.RenderViewDelegate
        public void onFirstDraw() {
            this.val$onInit.run();
        }

        @Override // org.telegram.ui.Components.Paint.RenderView.RenderViewDelegate
        public void resetBrush() {
            boolean r0 = LPhotoPaintView.m11479$$Nest$fgetignoreToolChangeAnimationOnce(this.this$0);
            LPhotoPaintView r1 = this.this$0;
            if (r0 == false) goto L6;
            LPhotoPaintView.m11501$$Nest$fputignoreToolChangeAnimationOnce(r1, false);
            return;
        L6:
            LPhotoPaintView.m11482$$Nest$fgetpaintToolsView(r1).select(1);
            this.this$0.onBrushSelected(Brush.BRUSHES_LIST.get(0));
        }

        @Override // org.telegram.ui.Components.Paint.RenderView.RenderViewDelegate
        public boolean shouldDraw() {
            if (LPhotoPaintView.m11476$$Nest$fgetcurrentEntityView(this.this$0) != null) goto L5;
            boolean r0 = true;
        L6:
            if (r0 == true) goto L8;
            LPhotoPaintView.m11509$$Nest$mselectEntity(this.this$0, null);
        L8:
            return r0;
        L5:
            r0 = false;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.Views.LPhotoPaintView$4, reason: invalid class name */
    public class AnonymousClass4 extends View {
        final /* synthetic */ LPhotoPaintView this$0;

        public AnonymousClass4(LPhotoPaintView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r2) {
            super.dispatchDraw(r2);
            if (LPhotoPaintView.m11487$$Nest$fgetrenderView(this.this$0) == null) goto L6;
            LPhotoPaintView.m11487$$Nest$fgetrenderView(this.this$0).onDrawForInput(r2);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.Views.LPhotoPaintView$5, reason: invalid class name */
    public class AnonymousClass5 implements EntitiesContainerView.EntitiesContainerViewDelegate {
        final /* synthetic */ LPhotoPaintView this$0;

        public AnonymousClass5(LPhotoPaintView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.Paint.Views.EntitiesContainerView.EntitiesContainerViewDelegate
        public void onEntityDeselect() {
            LPhotoPaintView.m11509$$Nest$mselectEntity(this.this$0, null);
        }

        @Override // org.telegram.ui.Components.Paint.Views.EntitiesContainerView.EntitiesContainerViewDelegate
        public EntityView onSelectedEntityRequest() {
            return LPhotoPaintView.m11476$$Nest$fgetcurrentEntityView(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.Views.LPhotoPaintView$6, reason: invalid class name */
    public class AnonymousClass6 extends EntitiesContainerView {
        long lastUpdate;
        Paint linePaint;
        float stickyXAlpha;
        float stickyYAlpha;
        final /* synthetic */ LPhotoPaintView this$0;

        public AnonymousClass6(LPhotoPaintView r1, Context r2, EntitiesContainerView.EntitiesContainerViewDelegate r3) {
            this.this$0 = r1;
            super(r2, r3);
            this.linePaint = new Paint();
            setWillNotDraw(false);
            this.linePaint.setStrokeWidth(AndroidUtilities.dp(2.0f));
            this.linePaint.setStyle(Paint.Style.STROKE);
            this.linePaint.setColor(-1);
        }

        @Override // android.view.View
        public void onDraw(Canvas r21) {
            super.onDraw(r21);
            long r1 = Math.min(16, System.currentTimeMillis() - this.lastUpdate);
            this.lastUpdate = System.currentTimeMillis();
            if (LPhotoPaintView.m11476$$Nest$fgetcurrentEntityView(this.this$0) != null) goto L5;
        L9:
            int r3 = 0;
            int r4 = 0;
        L11:
            if (r3 == 0) goto L15;
            float r8 = this.stickyXAlpha;
            if (r8 == 1.0f) goto L15;
            this.stickyXAlpha = Math.min(1.0f, r8 + (r1 / 150.0f));
            invalidate();
        L19:
            if (r4 == 0) goto L23;
            float r32 = this.stickyYAlpha;
            if (r32 == 1.0f) goto L23;
            this.stickyYAlpha = Math.min(1.0f, r32 + (r1 / 150.0f));
            invalidate();
        L27:
            float r12 = this.stickyYAlpha;
            if (r12 == 0.0f) goto L30;
            this.linePaint.setAlpha((int) (r12 * 255.0f));
            float r10 = getMeasuredHeight() / 2.0f;
            r21.drawLine(0.0f, r10, getMeasuredWidth(), r10, this.linePaint);
        L30:
            float r13 = this.stickyXAlpha;
            if (r13 == 0.0f) goto L34;
            this.linePaint.setAlpha((int) (r13 * 255.0f));
            float r15 = getMeasuredWidth() / 2.0f;
            r21.drawLine(r15, 0.0f, r15, getMeasuredHeight(), this.linePaint);
            return;
        L34:
            return;
        L23:
            if (r4 != 0) goto L27;
            float r33 = this.stickyYAlpha;
            if (r33 == 0.0f) goto L27;
            this.stickyYAlpha = Math.max(0.0f, r33 - (r1 / 150.0f));
            invalidate();
        L15:
            if (r3 != 0) goto L19;
            float r34 = this.stickyXAlpha;
            if (r34 == 0.0f) goto L19;
            this.stickyXAlpha = Math.max(0.0f, r34 - (r1 / 150.0f));
            invalidate();
            goto L19
        L5:
            if (LPhotoPaintView.m11476$$Nest$fgetcurrentEntityView(this.this$0).hasTouchDown() == false) goto L9;
            if (LPhotoPaintView.m11476$$Nest$fgetcurrentEntityView(this.this$0).hasPanned() == false) goto L9;
            r3 = LPhotoPaintView.m11476$$Nest$fgetcurrentEntityView(this.this$0).getStickyX();
            r4 = LPhotoPaintView.m11476$$Nest$fgetcurrentEntityView(this.this$0).getStickyY();
            goto L11
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.Views.LPhotoPaintView$7, reason: invalid class name */
    public class AnonymousClass7 extends FrameLayout {
        final /* synthetic */ LPhotoPaintView this$0;

        public AnonymousClass7(LPhotoPaintView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouchEvent(MotionEvent r1) {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.Views.LPhotoPaintView$8, reason: invalid class name */
    public class AnonymousClass8 extends FrameLayout {
        final /* synthetic */ LPhotoPaintView this$0;

        public AnonymousClass8(LPhotoPaintView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            setWillNotDraw(false);
        }

        @Override // android.view.View
        public void onDraw(Canvas r8) {
            super.onDraw(r8);
            LPhotoPaintView.m11497$$Nest$fgettypefaceMenuOutlinePaint(this.this$0).setAlpha((int) ((this.this$0.textOptionsView.getAlpha() * 102.0f) * (1.0f - LPhotoPaintView.m11498$$Nest$fgettypefaceMenuTransformProgress(this.this$0))));
            PaintTextOptionsView r0 = this.this$0.textOptionsView;
            RectF r1 = AndroidUtilities.rectTmp;
            r0.getTypefaceCellBounds(r1);
            float r02 = ((this.this$0.bottomLayout.getTop() + this.this$0.textOptionsView.getTop()) + this.this$0.bottomLayout.getTranslationY()) + this.this$0.textOptionsView.getTranslationY();
            r1.set(AndroidUtilities.lerp(r1.left, LPhotoPaintView.m11495$$Nest$fgettypefaceListView(this.this$0).getLeft(), LPhotoPaintView.m11498$$Nest$fgettypefaceMenuTransformProgress(this.this$0)), AndroidUtilities.lerp(r1.top + r02, LPhotoPaintView.m11495$$Nest$fgettypefaceListView(this.this$0).getTop() - LPhotoPaintView.m11495$$Nest$fgettypefaceListView(this.this$0).getTranslationY(), LPhotoPaintView.m11498$$Nest$fgettypefaceMenuTransformProgress(this.this$0)), AndroidUtilities.lerp(r1.right, LPhotoPaintView.m11495$$Nest$fgettypefaceListView(this.this$0).getRight(), LPhotoPaintView.m11498$$Nest$fgettypefaceMenuTransformProgress(this.this$0)), AndroidUtilities.lerp(r02 + r1.bottom, LPhotoPaintView.m11495$$Nest$fgettypefaceListView(this.this$0).getBottom() - LPhotoPaintView.m11495$$Nest$fgettypefaceListView(this.this$0).getTranslationY(), LPhotoPaintView.m11498$$Nest$fgettypefaceMenuTransformProgress(this.this$0)));
            float r03 = AndroidUtilities.dp(AndroidUtilities.lerp(32, 16, LPhotoPaintView.m11498$$Nest$fgettypefaceMenuTransformProgress(this.this$0)));
            int r2 = LPhotoPaintView.m11496$$Nest$fgettypefaceMenuBackgroundPaint(this.this$0).getAlpha();
            LPhotoPaintView.m11496$$Nest$fgettypefaceMenuBackgroundPaint(this.this$0).setAlpha((int) (r2 * LPhotoPaintView.m11498$$Nest$fgettypefaceMenuTransformProgress(this.this$0)));
            r8.drawRoundRect(r1, r03, r03, LPhotoPaintView.m11496$$Nest$fgettypefaceMenuBackgroundPaint(this.this$0));
            LPhotoPaintView.m11496$$Nest$fgettypefaceMenuBackgroundPaint(this.this$0).setAlpha(r2);
            r8.drawRoundRect(r1, r03, r03, LPhotoPaintView.m11497$$Nest$fgettypefaceMenuOutlinePaint(this.this$0));
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r2) {
            if (r2.getActionMasked() != 0) goto L9;
            if (LPhotoPaintView.m11480$$Nest$fgetisTypefaceMenuShown(this.this$0) == false) goto L9;
            LPhotoPaintView.m11513$$Nest$mshowTypefaceMenu(this.this$0, false);
            return true;
        L9:
            return super.onTouchEvent(r2);
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.Views.LPhotoPaintView$9, reason: invalid class name */
    public class AnonymousClass9 extends PaintColorsListView {
        private Path path;
        final /* synthetic */ LPhotoPaintView this$0;

        public AnonymousClass9(LPhotoPaintView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.path = new Path();
        }

        @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
        public void draw(Canvas r8) {
            ViewGroup r0 = LPhotoPaintView.m11506$$Nest$mgetBarView(this.this$0);
            RectF r1 = AndroidUtilities.rectTmp;
            r1.set(AndroidUtilities.lerp(r0.getLeft() - getLeft(), 0, LPhotoPaintView.m11494$$Nest$fgettoolsTransformProgress(this.this$0)), AndroidUtilities.lerp(r0.getTop() - getTop(), 0, LPhotoPaintView.m11494$$Nest$fgettoolsTransformProgress(this.this$0)), AndroidUtilities.lerp(r0.getRight() - getLeft(), getWidth(), LPhotoPaintView.m11494$$Nest$fgettoolsTransformProgress(this.this$0)), AndroidUtilities.lerp(r0.getBottom() - getTop(), getHeight(), LPhotoPaintView.m11494$$Nest$fgettoolsTransformProgress(this.this$0)));
            this.path.rewind();
            this.path.addRoundRect(r1, AndroidUtilities.dp(32.0f), AndroidUtilities.dp(32.0f), Path.Direction.CW);
            r8.save();
            r8.clipPath(this.path);
            super.draw(r8);
            r8.restore();
        }
    }

    public class BottomLayout extends FrameLayout {
        private float lastRainbowX;
        private float lastRainbowY;
        private Path path;
        final /* synthetic */ LPhotoPaintView this$0;

        public BottomLayout(LPhotoPaintView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.path = new Path();
            setWillNotDraw(false);
            LPhotoPaintView.m11470$$Nest$fgetcolorPickerRainbowPaint(r1).setStyle(Paint.Style.STROKE);
            LPhotoPaintView.m11470$$Nest$fgetcolorPickerRainbowPaint(r1).setStrokeWidth(AndroidUtilities.dp(2.0f));
        }

        private void checkRainbow(float r4, float r5) {
            if (r4 == this.lastRainbowX) goto L5;
        L8:
            this.lastRainbowX = r4;
            this.lastRainbowY = r5;
            LPhotoPaintView.m11470$$Nest$fgetcolorPickerRainbowPaint(this.this$0).setShader(new SweepGradient(r4, r5, new int[]{-1356981, -1146130, -10452764, -16711681, -7352832, -256, -23296, -1356981}, null));
            return;
        L5:
            if (r5 != this.lastRainbowY) goto L8;
        }

        @Override // android.view.View
        public void onDraw(Canvas r19) {
            super.onDraw(r19);
            ViewGroup r2 = LPhotoPaintView.m11506$$Nest$mgetBarView(this.this$0);
            Rect r3 = AndroidUtilities.rectTmp2;
            r3.set(AndroidUtilities.lerp(r2.getLeft(), LPhotoPaintView.m11474$$Nest$fgetcolorsListView(this.this$0).getLeft(), LPhotoPaintView.m11494$$Nest$fgettoolsTransformProgress(this.this$0)), AndroidUtilities.lerp(r2.getTop(), LPhotoPaintView.m11474$$Nest$fgetcolorsListView(this.this$0).getTop(), LPhotoPaintView.m11494$$Nest$fgettoolsTransformProgress(this.this$0)), AndroidUtilities.lerp(r2.getRight(), LPhotoPaintView.m11474$$Nest$fgetcolorsListView(this.this$0).getRight(), LPhotoPaintView.m11494$$Nest$fgettoolsTransformProgress(this.this$0)), AndroidUtilities.lerp(r2.getBottom(), LPhotoPaintView.m11474$$Nest$fgetcolorsListView(this.this$0).getBottom(), LPhotoPaintView.m11494$$Nest$fgettoolsTransformProgress(this.this$0)));
            RectF r4 = AndroidUtilities.rectTmp;
            r4.set(r3);
            float r5 = AndroidUtilities.lerp(AndroidUtilities.dp(32.0f), AndroidUtilities.dp(24.0f), LPhotoPaintView.m11494$$Nest$fgettoolsTransformProgress(this.this$0));
            if (LPhotoPaintView.m11469$$Nest$fgetblurredBackgroundDrawableForTools(this.this$0) == null) goto L5;
            r3.inset(-AndroidUtilities.dp(4.0f), -AndroidUtilities.dp(4.0f));
            LPhotoPaintView.m11469$$Nest$fgetblurredBackgroundDrawableForTools(this.this$0).setRadius(r5);
            LPhotoPaintView.m11469$$Nest$fgetblurredBackgroundDrawableForTools(this.this$0).setBounds(r3);
            LPhotoPaintView.m11469$$Nest$fgetblurredBackgroundDrawableForTools(this.this$0).draw(r19);
        L7:
            if (r2.getChildCount() >= 1) goto L9;
            return;
        L9:
            if (LPhotoPaintView.m11494$$Nest$fgettoolsTransformProgress(this.this$0) == 1.0f) goto L49;
            r19.save();
            r19.translate(r2.getLeft(), r2.getTop());
            View r52 = r2.getChildAt(0);
            if ((r2 instanceof PaintTextOptionsView) == false) goto L13;
            r52 = ((PaintTextOptionsView) r2).getColorClickableView();
        L13:
            View r8 = r52;
            if (r8.getAlpha() == 0.0f) goto L46;
            r19.scale(r8.getScaleX(), r8.getScaleY(), r8.getPivotX(), r8.getPivotY());
            LPhotoPaintView.m11470$$Nest$fgetcolorPickerRainbowPaint(this.this$0).setAlpha((int) (((1.0f - LPhotoPaintView.m11494$$Nest$fgettoolsTransformProgress(this.this$0)) * r8.getAlpha()) * 255.0f));
            int r53 = (r8.getWidth() - r8.getPaddingLeft()) - r8.getPaddingRight();
            int r6 = (r8.getHeight() - r8.getPaddingTop()) - r8.getPaddingBottom();
            float r10 = (r8.getX() + r8.getPaddingLeft()) + (r53 / 2.0f);
            float r11 = (r8.getY() + r8.getPaddingTop()) + (r6 / 2.0f);
            int r13 = LPhotoPaintView.m11471$$Nest$fgetcolorSwatch(this.this$0).color;
            if (LPhotoPaintView.m11489$$Nest$fgettabsNewSelectedIndex(this.this$0) == (-1)) goto L25;
            LPhotoPaintView r14 = this.this$0;
            ViewGroup r142 = (ViewGroup) LPhotoPaintView.m11505$$Nest$mgetBarView(r14, LPhotoPaintView.m11489$$Nest$fgettabsNewSelectedIndex(r14));
            if (r142 != null) goto L20;
            ViewGroup r15 = r2;
        L21:
            View r152 = r15.getChildAt(0);
            float r16 = 1.0f;
            if ((r142 instanceof PaintTextOptionsView) == false) goto L24;
            r152 = ((PaintTextOptionsView) r142).getColorClickableView();
        L24:
            r10 = AndroidUtilities.lerp(r10, (r152.getX() + r152.getPaddingLeft()) + (((r152.getWidth() - r152.getPaddingLeft()) - r152.getPaddingRight()) / 2.0f), LPhotoPaintView.m11492$$Nest$fgettabsSelectionProgress(this.this$0));
            r11 = AndroidUtilities.lerp(r11, (r152.getY() + r152.getPaddingTop()) + (((r152.getHeight() - r152.getPaddingTop()) - r152.getPaddingBottom()) / 2.0f), LPhotoPaintView.m11492$$Nest$fgettabsSelectionProgress(this.this$0));
        L27:
            if (LPhotoPaintView.m11474$$Nest$fgetcolorsListView(this.this$0) != null) goto L29;
        L31:
            checkRainbow(r10, r11);
            float r22 = (Math.min(r53, r6) / 2.0f) - AndroidUtilities.dp(0.5f);
            if (LPhotoPaintView.m11474$$Nest$fgetcolorsListView(this.this$0) != null) goto L34;
        L36:
            float r7 = r22;
            r4.set(r10 - r7, r11 - r7, r10 + r7, r11 + r7);
            r19.drawArc(r4, 0.0f, 360.0f, false, LPhotoPaintView.m11470$$Nest$fgetcolorPickerRainbowPaint(this.this$0));
            LPhotoPaintView.m11473$$Nest$fgetcolorSwatchPaint(this.this$0).setColor(r13);
            LPhotoPaintView.m11473$$Nest$fgetcolorSwatchPaint(this.this$0).setAlpha((int) (LPhotoPaintView.m11473$$Nest$fgetcolorSwatchPaint(this.this$0).getAlpha() * r8.getAlpha()));
            LPhotoPaintView.m11472$$Nest$fgetcolorSwatchOutlinePaint(this.this$0).setColor(r13);
            LPhotoPaintView.m11472$$Nest$fgetcolorSwatchOutlinePaint(this.this$0).setAlpha((int) (r8.getAlpha() * 255.0f));
            float r32 = r7 - AndroidUtilities.dp(3.0f);
            if (LPhotoPaintView.m11474$$Nest$fgetcolorsListView(this.this$0) != null) goto L39;
        L41:
            PaintColorsListView.drawColorCircle(r19, r10, r11, r32, LPhotoPaintView.m11473$$Nest$fgetcolorSwatchPaint(this.this$0).getColor());
            if (LPhotoPaintView.m11474$$Nest$fgetcolorsListView(this.this$0) == null) goto L46;
            if (LPhotoPaintView.m11474$$Nest$fgetcolorsListView(this.this$0).getSelectedColorIndex() != 0) goto L46;
            LPhotoPaintView.m11472$$Nest$fgetcolorSwatchOutlinePaint(this.this$0).setAlpha((int) ((LPhotoPaintView.m11472$$Nest$fgetcolorSwatchOutlinePaint(this.this$0).getAlpha() * LPhotoPaintView.m11494$$Nest$fgettoolsTransformProgress(this.this$0)) * r8.getAlpha()));
            r19.drawCircle(r10, r11, r7 - ((AndroidUtilities.dp(3.0f) + LPhotoPaintView.m11472$$Nest$fgetcolorSwatchOutlinePaint(this.this$0).getStrokeWidth()) * (r16 - LPhotoPaintView.m11494$$Nest$fgettoolsTransformProgress(this.this$0))), LPhotoPaintView.m11472$$Nest$fgetcolorSwatchOutlinePaint(this.this$0));
            goto L46
        L39:
            if (LPhotoPaintView.m11474$$Nest$fgetcolorsListView(this.this$0).getSelectedColorIndex() == 0) goto L41;
            r32 = AndroidUtilities.lerp(r7 - AndroidUtilities.dp(3.0f), AndroidUtilities.dp(2.0f) + r7, LPhotoPaintView.m11494$$Nest$fgettoolsTransformProgress(this.this$0));
            goto L41
        L34:
            if (LPhotoPaintView.m11474$$Nest$fgetcolorsListView(this.this$0).getChildCount() <= 0) goto L36;
            View r33 = LPhotoPaintView.m11474$$Nest$fgetcolorsListView(this.this$0).getChildAt(0);
            r22 = AndroidUtilities.lerp(r22, (Math.min((r33.getWidth() - r33.getPaddingLeft()) - r33.getPaddingRight(), (r33.getHeight() - r33.getPaddingTop()) - r33.getPaddingBottom()) / 2.0f) - AndroidUtilities.dp(2.0f), LPhotoPaintView.m11494$$Nest$fgettoolsTransformProgress(this.this$0));
            goto L36
        L29:
            if (LPhotoPaintView.m11474$$Nest$fgetcolorsListView(this.this$0).getChildCount() <= 0) goto L31;
            View r72 = LPhotoPaintView.m11474$$Nest$fgetcolorsListView(this.this$0).getChildAt(0);
            r10 = AndroidUtilities.lerp(r10, ((LPhotoPaintView.m11474$$Nest$fgetcolorsListView(this.this$0).getX() - r2.getLeft()) + r72.getX()) + (r72.getWidth() / 2.0f), LPhotoPaintView.m11494$$Nest$fgettoolsTransformProgress(this.this$0));
            r11 = AndroidUtilities.lerp(r11, ((LPhotoPaintView.m11474$$Nest$fgetcolorsListView(this.this$0).getY() - r2.getTop()) + r72.getY()) + (r72.getHeight() / 2.0f), LPhotoPaintView.m11494$$Nest$fgettoolsTransformProgress(this.this$0));
            r13 = ColorUtils.blendARGB(LPhotoPaintView.m11471$$Nest$fgetcolorSwatch(this.this$0).color, LPhotoPaintView.m11483$$Nest$fgetpalette(this.this$0).getColor(0), LPhotoPaintView.m11494$$Nest$fgettoolsTransformProgress(this.this$0));
            goto L31
        L20:
            r15 = r142;
            goto L21
        L25:
            r16 = 1.0f;
        L46:
            r19.restore();
            return;
        L49:
            return;
        L5:
            r19.drawRoundRect(r4, r5, r5, LPhotoPaintView.m11493$$Nest$fgettoolsPaint(this.this$0));
            goto L7
        }

        @Override // android.view.View
        public void setTranslationY(float r1) {
            super.setTranslationY(r1);
            FrameLayout r0 = this.this$0.overlayLayout;
            if (r0 == null) goto L6;
            r0.invalidate();
            return;
        }
    }

    public class PopupButton extends LinearLayout {
        ImageView checkView;
        ImageView image2View;
        ValueAnimator imageSwitchAnimator;
        boolean imageSwitchFill;
        float imageSwitchT;
        ImageView imageView;
        FrameLayout imagesView;
        public TextView textView;
        final /* synthetic */ LPhotoPaintView this$0;

        /* renamed from: org.telegram.ui.Components.Paint.Views.LPhotoPaintView$PopupButton$1, reason: invalid class name */
        public class AnonymousClass1 extends FrameLayout {
            Path path;
            final /* synthetic */ PopupButton this$1;
            final /* synthetic */ LPhotoPaintView val$this$0;

            public AnonymousClass1(PopupButton r1, Context r2, LPhotoPaintView r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
                this.path = new Path();
            }

            @Override // android.view.ViewGroup
            public boolean drawChild(Canvas r6, View r7, long r8) {
                PopupButton r0 = this.this$1;
                if (r0.imageSwitchAnimator == null) goto L17;
                boolean r1 = r0.imageSwitchFill;
                if (r1 == true) goto L7;
            L8:
                if (r1 == true) goto L17;
                if (r7 != r0.imageView) goto L17;
            L11:
                if (r1 == false) goto L13;
                float r02 = r0.imageSwitchT;
            L14:
                r6.save();
                this.path.rewind();
                this.path.addCircle(getMeasuredWidth() / 2.0f, getMeasuredHeight() / 2.0f, (r02 * getMeasuredWidth()) / 2.0f, Path.Direction.CW);
                r6.clipPath(this.path);
                boolean r5 = super.drawChild(r6, r7, r8);
                r6.restore();
                return r5;
            L13:
                r02 = 1.0f - r0.imageSwitchT;
                goto L14
            L7:
                if (r7 == r0.image2View) goto L11;
            L17:
                return super.drawChild(r6, r7, r8);
            }
        }

        /* renamed from: org.telegram.ui.Components.Paint.Views.LPhotoPaintView$PopupButton$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ PopupButton this$1;

            public AnonymousClass2(PopupButton r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r3) {
                super.onAnimationEnd(r3);
                PopupButton r32 = this.this$1;
                ImageView r0 = r32.imageView;
                r32.imageView = r32.image2View;
                r32.image2View = r0;
                r0.bringToFront();
                this.this$1.image2View.setVisibility(8);
                this.this$1.imageSwitchAnimator = null;
            }
        }

        public static /* synthetic */ void $r8$lambda$EvIMWc0tuO7ogdByhUmfFCGbYjw(PopupButton r0, boolean r1, ValueAnimator r2) {
            r0.lambda$setIcon$0(r1, r2);
        }

        public PopupButton(LPhotoPaintView r12, Context r13) {
            this.this$0 = r12;
            super(r13);
            setOrientation(0);
            setBackground(Theme.getSelectorDrawable(Theme.getColor(Theme.key_listSelector, LPhotoPaintView.m11488$$Nest$fgetresourcesProvider(r12)), false));
            AnonymousClass1 r0 = new AnonymousClass1(this, r13, r12);
            this.imagesView = r0;
            addView(r0, LayoutHelper.createLinear(-2, -2, 19, 16, 0, 16, 0));
            ImageView r02 = new ImageView(r13);
            this.imageView = r02;
            ImageView.ScaleType r1 = ImageView.ScaleType.CENTER;
            r02.setScaleType(r1);
            ImageView r03 = this.imageView;
            int r2 = Theme.key_actionBarDefaultSubmenuItem;
            r03.setColorFilter(LPhotoPaintView.m11507$$Nest$mgetThemedColor(r12, r2));
            this.imagesView.addView(this.imageView, LayoutHelper.createFrame(-2, -2, 17));
            ImageView r04 = new ImageView(r13);
            this.image2View = r04;
            r04.setScaleType(r1);
            this.image2View.setColorFilter(LPhotoPaintView.m11507$$Nest$mgetThemedColor(r12, r2));
            this.image2View.setVisibility(8);
            this.imagesView.addView(this.image2View, LayoutHelper.createFrame(-2, -2, 17));
            TextView r05 = new TextView(r13);
            this.textView = r05;
            r05.setTextColor(LPhotoPaintView.m11507$$Nest$mgetThemedColor(r12, r2));
            this.textView.setTextSize(1, 16.0f);
            addView(this.textView, LayoutHelper.createLinear(-2, -2, 19, 0, 0, 16, 0));
            ImageView r06 = new ImageView(r13);
            this.checkView = r06;
            r06.setImageResource(R.drawable.msg_text_check);
            this.checkView.setScaleType(r1);
            this.checkView.setColorFilter(new PorterDuffColorFilter(LPhotoPaintView.m11507$$Nest$mgetThemedColor(r12, Theme.key_radioBackgroundChecked), PorterDuff.Mode.MULTIPLY));
            this.checkView.setVisibility(8);
            addView(this.checkView, LayoutHelper.createLinear(50, -1));
        }

        private /* synthetic */ void lambda$setIcon$0(boolean r2, ValueAnimator r3) {
            float r32 = ((Float) r3.getAnimatedValue()).floatValue();
            this.imageSwitchT = r32;
            if (r2 == true) goto L5;
            this.imageView.setAlpha(1.0f - r32);
        L5:
            this.imagesView.invalidate();
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r1) {
            return true;
        }

        @Override // android.view.View
        public boolean performClick() {
            if (LPhotoPaintView.m11485$$Nest$fgetpopupWindow(this.this$0) == null) goto L8;
            if (LPhotoPaintView.m11485$$Nest$fgetpopupWindow(this.this$0).isShowing() == false) goto L8;
            LPhotoPaintView.m11485$$Nest$fgetpopupWindow(this.this$0).dismiss(true);
        L8:
            return super.performClick();
        }

        public void setIcon(int r3) {
            setIcon(r3, true, false);
        }

        public void setIcon(int r2, boolean r3, boolean r4) {
            if (r4 == false) goto L9;
            ValueAnimator r42 = this.imageSwitchAnimator;
            if (r42 == null) goto L7;
            r42.cancel();
            this.imageSwitchAnimator = null;
            setIcon(r2, false, false);
            return;
        L7:
            this.imageSwitchFill = r3;
            this.image2View.setImageResource(r2);
            this.image2View.setVisibility(0);
            this.image2View.setAlpha(1.0f);
            ValueAnimator r22 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            this.imageSwitchAnimator = r22;
            r22.addUpdateListener(new LPhotoPaintView$PopupButton$$ExternalSyntheticLambda0(this, r3));
            this.imageSwitchAnimator.addListener(new AnonymousClass2(this));
            this.imageSwitchAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            this.imageSwitchAnimator.setDuration(420);
            this.imageSwitchAnimator.start();
            return;
        L9:
            this.imageView.setImageResource(r2);
        }

        @Override // android.view.View
        public void setSelected(boolean r1) {
            ImageView r0 = this.checkView;
            if (r1 == false) goto L5;
            int r12 = 0;
        L6:
            r0.setVisibility(r12);
            return;
        L5:
            r12 = 8;
            goto L6
        }

        public void setText(CharSequence r1) {
            this.textView.setText(r1);
        }
    }

    public static class StickerPosition {
        private float angle;
        private PointF position;
        private float scale;

        /* renamed from: -$$Nest$fgetangle, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m11514$$Nest$fgetangle(StickerPosition r0) {
            return r0.angle;
        }

        /* renamed from: -$$Nest$fgetposition, reason: not valid java name */
        public static /* bridge */ /* synthetic */ PointF m11515$$Nest$fgetposition(StickerPosition r0) {
            return r0.position;
        }

        /* renamed from: -$$Nest$fgetscale, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m11516$$Nest$fgetscale(StickerPosition r0) {
            return r0.scale;
        }

        public StickerPosition(PointF r1, float r2, float r3) {
            this.position = r1;
            this.scale = r2;
            this.angle = r3;
        }
    }

    /* renamed from: $r8$lambda$-RgzIczfJIpdlSr5lEbrS9ocKsQ, reason: not valid java name */
    public static /* synthetic */ Boolean m11450$r8$lambda$RgzIczfJIpdlSr5lEbrS9ocKsQ(LPhotoPaintView r0, Object r1, TLRPC.Document r2, Boolean r3) {
        return r0.lambda$openStickersView$19(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$02-eQWslo7Q_jNoXyaqwnxcz9Q4, reason: not valid java name */
    public static /* synthetic */ void m11451$r8$lambda$02eQWslo7Q_jNoXyaqwnxcz9Q4(LPhotoPaintView r0, Brush.Shape r1, int r2) {
        r0.lambda$onAddButtonPressed$33(r1, r2);
    }

    /* renamed from: $r8$lambda$1DhlF-p62nh8aTTq29_zZi572ZU, reason: not valid java name */
    public static /* synthetic */ void m11452$r8$lambda$1DhlFp62nh8aTTq29_zZi572ZU(LPhotoPaintView r0) {
        r0.lambda$detectFaces$24();
    }

    public static /* synthetic */ void $r8$lambda$24zbz9ba3M0da56YT6knLrTLPLY(LPhotoPaintView r0, ValueAnimator r1) {
        r0.lambda$hideEmojiPopup$47(r1);
    }

    public static /* synthetic */ void $r8$lambda$4aiZhvQC2GURrN6CN5KcEZfpOL4() {
    }

    public static /* synthetic */ int $r8$lambda$6i0NVTYBdNPKqzxlYpb8bUqDG9g(Theme.ResourcesProvider r2, int r3) {
        if (r3 != Theme.key_actionBarDefaultSubmenuBackground) goto L7;
        return -14145495;
    L7:
        if (r3 != Theme.key_actionBarDefaultSubmenuItem) goto L10;
        return -1;
    L10:
        if (r3 != Theme.key_dialogBackground) goto L14;
        return -14737633;
    L14:
        if (r3 != Theme.key_dialogTextBlack) goto L18;
        return -592138;
    L18:
        if (r3 != Theme.key_dialogTextGray3) goto L22;
        return -8553091;
    L22:
        if (r3 != Theme.key_chat_emojiPanelBackground) goto L25;
        return -16777216;
    L25:
        if (r3 != Theme.key_chat_emojiPanelShadowLine) goto L29;
        return -1610612736;
    L29:
        if (r3 != Theme.key_chat_emojiBottomPanelIcon) goto L32;
        return -9539985;
    L32:
        if (r3 != Theme.key_chat_emojiPanelBackspace) goto L35;
        return -9539985;
    L35:
        if (r3 != Theme.key_chat_emojiPanelIcon) goto L39;
        return -9539985;
    L39:
        if (r3 == Theme.key_windowBackgroundWhiteBlackText) goto L104;
        int r0 = Theme.key_featuredStickers_addedIcon;
        if (r3 != r0) goto L46;
        return -11754001;
    L46:
        if (r3 != Theme.key_listSelector) goto L50;
        return 536870911;
    L50:
        if (r3 != Theme.key_profile_tabSelectedText) goto L53;
        return -1;
    L53:
        if (r3 != Theme.key_profile_tabText) goto L56;
        return -1;
    L56:
        if (r3 != Theme.key_profile_tabSelectedLine) goto L59;
        return -1;
    L59:
        if (r3 != Theme.key_profile_tabSelector) goto L63;
        return 352321535;
    L63:
        if (r3 == Theme.key_chat_emojiSearchIcon) goto L99;
        if (r3 != r0) goto L67;
        return -7895161;
    L67:
        if (r3 != Theme.key_chat_emojiSearchBackground) goto L71;
        return 780633991;
    L71:
        if (r3 != Theme.key_windowBackgroundGray) goto L75;
        return -15921907;
    L75:
        if (r3 != Theme.key_fastScrollInactive) goto L79;
        return -12500671;
    L79:
        if (r3 != Theme.key_fastScrollActive) goto L83;
        return -13133079;
    L83:
        if (r3 != Theme.key_fastScrollText) goto L87;
        return -1;
    L87:
        if (r3 != Theme.key_windowBackgroundWhite) goto L91;
        return -15198183;
    L91:
        if (r3 != Theme.key_divider) goto L94;
        return -16777216;
    L94:
        if (r2 == null) goto L98;
        return r2.getColor(r3);
    L98:
        return Theme.getColor(r3);
    L99:
        return -7895161;
    L104:
        return -1;
    }

    public static /* synthetic */ void $r8$lambda$AH1cnmOgu68i9GheivcSjzXpVXY(LPhotoPaintView r0) {
        r0.lambda$openStickersView$18();
    }

    public static /* synthetic */ void $r8$lambda$AlTx0RxqEx_7GT74cQ9T90MAVvc(LPhotoPaintView r0, EntityView r1) {
        r0.lambda$showMenuForEntity$41(r1);
    }

    /* renamed from: $r8$lambda$BI6gLq5PtgdqchK-wB8A-4FZ2AU, reason: not valid java name */
    public static /* synthetic */ void m11453$r8$lambda$BI6gLq5PtgdqchKwB8A4FZ2AU(LPhotoPaintView r0) {
        r0.lambda$getThanosEffect$48();
    }

    /* renamed from: $r8$lambda$BbZXr1Y_8l1-RfFfZSACntwGKQM, reason: not valid java name */
    public static /* synthetic */ void m11454$r8$lambda$BbZXr1Y_8l1RfFfZSACntwGKQM(LPhotoPaintView r0, View r1) {
        r0.lambda$new$5(r1);
    }

    /* renamed from: $r8$lambda$CU2kfX6bfLiroOYhd-JnI_XtLQg, reason: not valid java name */
    public static /* synthetic */ void m11455$r8$lambda$CU2kfX6bfLiroOYhdJnI_XtLQg(LPhotoPaintView r0, KeyEvent r1) {
        r0.lambda$showPopup$43(r1);
    }

    public static /* synthetic */ void $r8$lambda$C_RO2_zugGqgTbANDpN3s7ht1rk(LPhotoPaintView r0, View r1) {
        r0.lambda$new$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$Cqv8ZXq2AygH5xig6jDr4JBCJgE(LPhotoPaintView r0, ValueAnimator r1) {
        r0.lambda$showEmojiPopup$46(r1);
    }

    /* renamed from: $r8$lambda$ETF_AKVmQ-eu6HBpCaaXe5uXQkw, reason: not valid java name */
    public static /* synthetic */ void m11456$r8$lambda$ETF_AKVmQeu6HBpCaaXe5uXQkw(LPhotoPaintView r0, View r1, int r2) {
        r0.lambda$new$7(r1, r2);
    }

    /* renamed from: $r8$lambda$GrP-BuicSkJTVz4blt5FwkPaYrc, reason: not valid java name */
    public static /* synthetic */ void m11457$r8$lambda$GrPBuicSkJTVz4blt5FwkPaYrc(LPhotoPaintView r0, boolean r1, DynamicAnimation r2, boolean r3, float r4, float r5) {
        r0.lambda$showTypefaceMenu$28(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$HBwMgLV768-H64xLpzp_bqquEvk, reason: not valid java name */
    public static /* synthetic */ void m11458$r8$lambda$HBwMgLV768H64xLpzp_bqquEvk(LPhotoPaintView r0, View r1) {
        r0.lambda$showMenuForEntity$39(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$HxCCQOntmarh9DhgbEU02Q7hVGY(LPhotoPaintView r0, View r1, MotionEvent r2) {
        return r0.lambda$showPopup$42(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$J5WQ8TiNvWgf36gmp7ho9HtJcbM(LPhotoPaintView r0, PhotoView r1, View r2) {
        r0.lambda$showMenuForEntity$40(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$KxdBDoO2Zm3tTV73jojKahyzkgE() {
        Looper r0 = Looper.myLooper();
        if (r0 == null) goto L6;
        r0.quit();
        return;
    }

    /* renamed from: $r8$lambda$Lk4DhfVXKqH-jnYBWfFWvLv_808, reason: not valid java name */
    public static /* synthetic */ void m11459$r8$lambda$Lk4DhfVXKqHjnYBWfFWvLv_808(PhotoView r1) {
        r1.onSwitchSegmentedAnimationStarted(true);
    }

    public static /* synthetic */ void $r8$lambda$N9jInQYGDpKXrafn9vBo6WAQtnQ(LPhotoPaintView r0, EntityView r1, View r2) {
        r0.lambda$showMenuForEntity$38(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Ou8ZO8vCeHhlpAFL3a7AVYJrjaU(LPhotoPaintView r0) {
        r0.lambda$onAddButtonPressed$35();
    }

    public static /* synthetic */ void $r8$lambda$P8Wtr_uA0QAUbHaRI7ytLjvS2eU(LPhotoPaintView r0, Integer r1) {
        r0.lambda$new$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$RMLcIUsg9KpctlFSI0Ys1beK_fU(LPhotoPaintView r0, DynamicAnimation r1, float r2, float r3) {
        r0.lambda$showTypefaceMenu$27(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$Sl4-ZpXZjk6G2Bv9XYELowD6oUU, reason: not valid java name */
    public static /* synthetic */ void m11460$r8$lambda$Sl4ZpXZjk6G2Bv9XYELowD6oUU(View r0) {
        PhotoViewer.getInstance().zoomOut();
    }

    public static /* synthetic */ void $r8$lambda$Ul4oaXYMsxfewFPk3_lzYbmGJOM(LPhotoPaintView r0, EntityView r1, View r2) {
        r0.lambda$showMenuForEntity$36(r1, r2);
    }

    public static /* synthetic */ Boolean $r8$lambda$V9ZEbEk4nAX_oWzQn7etjPFVLuA(LPhotoPaintView r0, Integer r1) {
        return r0.lambda$openStickersView$20(r1);
    }

    /* renamed from: $r8$lambda$VsCpPeXsMDeGy9bAci-J5so8GzE, reason: not valid java name */
    public static /* synthetic */ void m11461$r8$lambda$VsCpPeXsMDeGy9bAciJ5so8GzE(LPhotoPaintView r0, int r1, DialogInterface r2) {
        r0.lambda$openStickersView$21(r1, r2);
    }

    /* renamed from: $r8$lambda$X9dZbQ2rRO6Mk-4KbqDkFxgJD88, reason: not valid java name */
    public static /* synthetic */ void m11462$r8$lambda$X9dZbQ2rRO6Mk4KbqDkFxgJD88(LPhotoPaintView r0, View r1, boolean r2, boolean[] r3, float r4, DynamicAnimation r5, float r6, float r7) {
        r0.lambda$showColorList$29(r1, r2, r3, r4, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$XvJh8MwwHPwG3RgZ4sXwZXF5d_U(LPhotoPaintView r0, View r1) {
        r0.lambda$appearAnimation$23(r1);
    }

    public static /* synthetic */ void $r8$lambda$YjVTjXrGSc7adJOetjIoiv_5iTM(LPhotoPaintView r0, EntityView r1) {
        r0.lambda$registerRemovalUndo$45(r1);
    }

    /* renamed from: $r8$lambda$ZJzY1jZhZSKhlQ2L4aKY-0M-JA4, reason: not valid java name */
    public static /* synthetic */ void m11463$r8$lambda$ZJzY1jZhZSKhlQ2L4aKY0MJA4(LPhotoPaintView r0, int r1) {
        r0.lambda$new$11(r1);
    }

    public static /* synthetic */ void $r8$lambda$_YfzXCdTqLPKzr5qzJlLGLY7IJ8(LPhotoPaintView r0, int r1, int r2, ValueAnimator r3) {
        r0.lambda$setNewColor$12(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$aV_Cttr_raxYqI5e7uO27OigAKo(LPhotoPaintView r0) {
        r0.lambda$createText$13();
    }

    public static /* synthetic */ void $r8$lambda$bBmWbhaz3eYjwZyYNEgFpRlI3eU(LPhotoPaintView r0, View r1) {
        r0.lambda$showMenuForEntity$37(r1);
    }

    public static /* synthetic */ void $r8$lambda$bUhW1_gbMvFhfGuuK_QkCD4KT0A(LPhotoPaintView r0, View r1) {
        r0.lambda$new$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$eSBkj0urj5iTaAXIrZvsXuqZ1bw(LPhotoPaintView r0) {
        r0.lambda$showPopup$44();
    }

    /* renamed from: $r8$lambda$f2m0wabO2zhD02Tttn-xhwKeen0, reason: not valid java name */
    public static /* synthetic */ void m11464$r8$lambda$f2m0wabO2zhD02TttnxhwKeen0(LPhotoPaintView r0, View r1) {
        r0.lambda$new$6(r1);
    }

    /* renamed from: $r8$lambda$f59on1SDoKPn-H_ZgW6pw6UqIxE, reason: not valid java name */
    public static /* synthetic */ void m11465$r8$lambda$f59on1SDoKPnH_ZgW6pw6UqIxE(LPhotoPaintView r0, View r1) {
        r0.lambda$setupTabsLayout$14(r1);
    }

    public static /* synthetic */ void $r8$lambda$gHUcfuwUv8fON1oK4XEF3xz02XE(DialogInterface r0) {
        MediaController.forceBroadcastNewPhotos = false;
    }

    public static /* synthetic */ void $r8$lambda$gos4QogPR_1G3K86af4oL6Nv55Q(LPhotoPaintView r0, View r1, View r2, ValueAnimator r3) {
        r0.lambda$switchTab$17(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$hA_YZ2XiFzXYv-vv-MzcyTIHUzY, reason: not valid java name */
    public static /* synthetic */ void m11466$r8$lambda$hA_YZ2XiFzXYvvvMzcyTIHUzY(LPhotoPaintView r0, Context r1, Bitmap r2, View r3) {
        r0.lambda$new$10(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$k4CaUNgVFnTek4-ZdkDYQejkme8, reason: not valid java name */
    public static /* synthetic */ void m11467$r8$lambda$k4CaUNgVFnTek4ZdkDYQejkme8(LPhotoPaintView r0, boolean r1, DynamicAnimation r2, boolean r3, float r4, float r5) {
        r0.lambda$showColorList$30(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$lRsuLN9oaF1s2NKMDTPTtF9n81s(LPhotoPaintView r0, View r1) {
        r0.lambda$setupTabsLayout$16(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$ngu1jco5rk8GXAJZ9bUfVvKsMqg(LPhotoPaintView r0, View r1) {
        return r0.lambda$onAddButtonPressed$34(r1);
    }

    public static /* synthetic */ void $r8$lambda$pizuMvrhWewqPv2DhARHe6i_4ss(Runnable r0, View r1) {
        r0.run();
    }

    public static /* synthetic */ void $r8$lambda$pqJ7roXKCAZVoef3woyMMt8h_44(LPhotoPaintView r0, View r1) {
        r0.lambda$setupTabsLayout$15(r1);
    }

    public static /* synthetic */ void $r8$lambda$rpTxlwQya79RfMAo9No437sS1Tk(LPhotoPaintView r0, Integer r1) {
        r0.lambda$new$9(r1);
    }

    /* renamed from: $r8$lambda$s4Ppzkmgo-Ic4957hEfqTePlTdY, reason: not valid java name */
    public static /* synthetic */ void m11468$r8$lambda$s4PpzkmgoIc4957hEfqTePlTdY(Runnable r0, AlertDialog r1, int r2) {
        r0.run();
    }

    public static /* synthetic */ void $r8$lambda$s8exhj6875mFALIR6Ox6IElhNLM(LPhotoPaintView r0) {
        r0.lambda$new$1();
    }

    public static /* synthetic */ void $r8$lambda$uGNjdnNeEcC2FpOozhRc0pacsGQ(LPhotoPaintView r0, Integer r1, int r2, ValueAnimator r3) {
        r0.lambda$setCurrentSwatch$31(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fgetblurredBackgroundDrawableForTools, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlurredBackgroundDrawable m11469$$Nest$fgetblurredBackgroundDrawableForTools(LPhotoPaintView r0) {
        return r0.blurredBackgroundDrawableForTools;
    }

    /* renamed from: -$$Nest$fgetcolorPickerRainbowPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m11470$$Nest$fgetcolorPickerRainbowPaint(LPhotoPaintView r0) {
        return r0.colorPickerRainbowPaint;
    }

    /* renamed from: -$$Nest$fgetcolorSwatch, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Swatch m11471$$Nest$fgetcolorSwatch(LPhotoPaintView r0) {
        return r0.colorSwatch;
    }

    /* renamed from: -$$Nest$fgetcolorSwatchOutlinePaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m11472$$Nest$fgetcolorSwatchOutlinePaint(LPhotoPaintView r0) {
        return r0.colorSwatchOutlinePaint;
    }

    /* renamed from: -$$Nest$fgetcolorSwatchPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m11473$$Nest$fgetcolorSwatchPaint(LPhotoPaintView r0) {
        return r0.colorSwatchPaint;
    }

    /* renamed from: -$$Nest$fgetcolorsListView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PaintColorsListView m11474$$Nest$fgetcolorsListView(LPhotoPaintView r0) {
        return r0.colorsListView;
    }

    /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11475$$Nest$fgetcurrentAccount(LPhotoPaintView r0) {
        return r0.currentAccount;
    }

    /* renamed from: -$$Nest$fgetcurrentEntityView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EntityView m11476$$Nest$fgetcurrentEntityView(LPhotoPaintView r0) {
        return r0.currentEntityView;
    }

    /* renamed from: -$$Nest$fgetdestroyed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11477$$Nest$fgetdestroyed(LPhotoPaintView r0) {
        return r0.destroyed;
    }

    /* renamed from: -$$Nest$fgetemojiView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EmojiView m11478$$Nest$fgetemojiView(LPhotoPaintView r0) {
        return r0.emojiView;
    }

    /* renamed from: -$$Nest$fgetignoreToolChangeAnimationOnce, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11479$$Nest$fgetignoreToolChangeAnimationOnce(LPhotoPaintView r0) {
        return r0.ignoreToolChangeAnimationOnce;
    }

    /* renamed from: -$$Nest$fgetisTypefaceMenuShown, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11480$$Nest$fgetisTypefaceMenuShown(LPhotoPaintView r0) {
        return r0.isTypefaceMenuShown;
    }

    /* renamed from: -$$Nest$fgetopenKeyboardRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m11481$$Nest$fgetopenKeyboardRunnable(LPhotoPaintView r0) {
        return r0.openKeyboardRunnable;
    }

    /* renamed from: -$$Nest$fgetpaintToolsView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PaintToolsView m11482$$Nest$fgetpaintToolsView(LPhotoPaintView r0) {
        return r0.paintToolsView;
    }

    /* renamed from: -$$Nest$fgetpalette, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PersistColorPalette m11483$$Nest$fgetpalette(LPhotoPaintView r0) {
        return r0.palette;
    }

    /* renamed from: -$$Nest$fgetpipetteContainerLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m11484$$Nest$fgetpipetteContainerLayout(LPhotoPaintView r0) {
        return r0.pipetteContainerLayout;
    }

    /* renamed from: -$$Nest$fgetpopupWindow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarPopupWindow m11485$$Nest$fgetpopupWindow(LPhotoPaintView r0) {
        return r0.popupWindow;
    }

    /* renamed from: -$$Nest$fgetrenderInputView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m11486$$Nest$fgetrenderInputView(LPhotoPaintView r0) {
        return r0.renderInputView;
    }

    /* renamed from: -$$Nest$fgetrenderView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RenderView m11487$$Nest$fgetrenderView(LPhotoPaintView r0) {
        return r0.renderView;
    }

    /* renamed from: -$$Nest$fgetresourcesProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Theme.ResourcesProvider m11488$$Nest$fgetresourcesProvider(LPhotoPaintView r0) {
        return r0.resourcesProvider;
    }

    /* renamed from: -$$Nest$fgettabsNewSelectedIndex, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11489$$Nest$fgettabsNewSelectedIndex(LPhotoPaintView r0) {
        return r0.tabsNewSelectedIndex;
    }

    /* renamed from: -$$Nest$fgettabsSelectedIndex, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11490$$Nest$fgettabsSelectedIndex(LPhotoPaintView r0) {
        return r0.tabsSelectedIndex;
    }

    /* renamed from: -$$Nest$fgettabsSelectionAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m11491$$Nest$fgettabsSelectionAnimator(LPhotoPaintView r0) {
        return r0.tabsSelectionAnimator;
    }

    /* renamed from: -$$Nest$fgettabsSelectionProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m11492$$Nest$fgettabsSelectionProgress(LPhotoPaintView r0) {
        return r0.tabsSelectionProgress;
    }

    /* renamed from: -$$Nest$fgettoolsPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m11493$$Nest$fgettoolsPaint(LPhotoPaintView r0) {
        return r0.toolsPaint;
    }

    /* renamed from: -$$Nest$fgettoolsTransformProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m11494$$Nest$fgettoolsTransformProgress(LPhotoPaintView r0) {
        return r0.toolsTransformProgress;
    }

    /* renamed from: -$$Nest$fgettypefaceListView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PaintTypefaceListView m11495$$Nest$fgettypefaceListView(LPhotoPaintView r0) {
        return r0.typefaceListView;
    }

    /* renamed from: -$$Nest$fgettypefaceMenuBackgroundPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m11496$$Nest$fgettypefaceMenuBackgroundPaint(LPhotoPaintView r0) {
        return r0.typefaceMenuBackgroundPaint;
    }

    /* renamed from: -$$Nest$fgettypefaceMenuOutlinePaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m11497$$Nest$fgettypefaceMenuOutlinePaint(LPhotoPaintView r0) {
        return r0.typefaceMenuOutlinePaint;
    }

    /* renamed from: -$$Nest$fgettypefaceMenuTransformProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m11498$$Nest$fgettypefaceMenuTransformProgress(LPhotoPaintView r0) {
        return r0.typefaceMenuTransformProgress;
    }

    /* renamed from: -$$Nest$fgetundoStore, reason: not valid java name */
    public static /* bridge */ /* synthetic */ UndoStore m11499$$Nest$fgetundoStore(LPhotoPaintView r0) {
        return r0.undoStore;
    }

    /* renamed from: -$$Nest$fgetwaitingForKeyboardOpen, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11500$$Nest$fgetwaitingForKeyboardOpen(LPhotoPaintView r0) {
        return r0.waitingForKeyboardOpen;
    }

    /* renamed from: -$$Nest$fputignoreToolChangeAnimationOnce, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11501$$Nest$fputignoreToolChangeAnimationOnce(LPhotoPaintView r0, boolean r1) {
        r0.ignoreToolChangeAnimationOnce = r1;
    }

    /* renamed from: -$$Nest$fputtabsNewSelectedIndex, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11502$$Nest$fputtabsNewSelectedIndex(LPhotoPaintView r0, int r1) {
        r0.tabsNewSelectedIndex = r1;
    }

    /* renamed from: -$$Nest$fputtabsSelectedIndex, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11503$$Nest$fputtabsSelectedIndex(LPhotoPaintView r0, int r1) {
        r0.tabsSelectedIndex = r1;
    }

    /* renamed from: -$$Nest$fputtabsSelectionAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11504$$Nest$fputtabsSelectionAnimator(LPhotoPaintView r0, ValueAnimator r1) {
        r0.tabsSelectionAnimator = r1;
    }

    /* renamed from: -$$Nest$mgetBarView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m11505$$Nest$mgetBarView(LPhotoPaintView r0, int r1) {
        return r0.getBarView(r1);
    }

    /* renamed from: -$$Nest$mgetBarView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ViewGroup m11506$$Nest$mgetBarView(LPhotoPaintView r0) {
        return r0.getBarView();
    }

    /* renamed from: -$$Nest$mgetThemedColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11507$$Nest$mgetThemedColor(LPhotoPaintView r0, int r1) {
        return r0.getThemedColor(r1);
    }

    /* renamed from: -$$Nest$mhideEmojiView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11508$$Nest$mhideEmojiView(LPhotoPaintView r0) {
        r0.hideEmojiView();
    }

    /* renamed from: -$$Nest$mselectEntity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11509$$Nest$mselectEntity(LPhotoPaintView r0, EntityView r1) {
        return r0.selectEntity(r1);
    }

    /* renamed from: -$$Nest$msetCurrentSwatch, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11510$$Nest$msetCurrentSwatch(LPhotoPaintView r0, Swatch r1, boolean r2) {
        r0.setCurrentSwatch(r1, r2);
    }

    /* renamed from: -$$Nest$msetNewColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11511$$Nest$msetNewColor(LPhotoPaintView r0, int r1) {
        r0.setNewColor(r1);
    }

    /* renamed from: -$$Nest$mshowColorList, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11512$$Nest$mshowColorList(LPhotoPaintView r0, boolean r1) {
        r0.showColorList(r1);
    }

    /* renamed from: -$$Nest$mshowTypefaceMenu, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11513$$Nest$mshowTypefaceMenu(LPhotoPaintView r0, boolean r1) {
        r0.showTypefaceMenu(r1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v118, types: [org.telegram.ui.Components.Paint.Views.TextPaintView] */
    /* JADX WARN: Type inference failed for: r0v121, types: [android.view.View, org.telegram.ui.Components.Paint.Views.StickerView] */
    /* JADX WARN: Type inference failed for: r12v4 */
    /* JADX WARN: Type inference failed for: r12v5, types: [int] */
    /* JADX WARN: Type inference failed for: r12v7 */
    /* JADX WARN: Type inference failed for: r25v0, types: [android.view.View, android.view.ViewGroup, android.widget.FrameLayout, org.telegram.ui.Components.Paint.Views.LPhotoPaintView, org.telegram.ui.Components.Paint.Views.PaintTextOptionsView$Delegate, org.telegram.ui.Components.Paint.Views.PaintToolsView$Delegate, org.telegram.ui.Components.SizeNotifierFrameLayout, org.telegram.ui.Components.SizeNotifierFrameLayout$SizeNotifierFrameLayoutDelegate] */
    @SuppressLint({"NotifyDataSetChanged"})
    public LPhotoPaintView(Context r26, Activity r27, int r28, Bitmap r29, Bitmap r30, int r31, ArrayList<VideoEditedInfo.MediaEntity> r32, MediaController.CropState r33, Runnable r34, Theme.ResourcesProvider r35) {
        byte r10 = 1;
        super(r26, r27, true);
        boolean r11 = false;
        this.tabsSelectedIndex = 0;
        this.tabsNewSelectedIndex = -1;
        this.weightDefaultValueOverride = new AnonymousClass1(this);
        this.typefaceMenuOutlinePaint = new Paint(1);
        this.typefaceMenuBackgroundPaint = new Paint(1);
        this.colorPickerRainbowPaint = new Paint(1);
        this.colorSwatchPaint = new Paint(1);
        this.colorSwatchOutlinePaint = new Paint(1);
        float r13 = 1.0f;
        this.colorSwatch = new Swatch(-1, 1.0f, 0.016773745f);
        this.fillShapes = false;
        this.toolsPaint = new Paint(1);
        this.zoomOutVisible = false;
        this.shadowAlpha = new AnimatedFloat(this, 350, CubicBezierInterpolator.EASE_OUT_QUINT);
        this.shadowPaint = new Paint(1);
        Paint r3 = new Paint(1);
        this.clearPaint = r3;
        r3.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        this.matrix = new Matrix();
        byte r14 = 2;
        this.position = new float[2];
        this.pos2 = new int[2];
        this.openKeyboardRunnable = new AnonymousClass18(this);
        setDelegate(this);
        this.currentAccount = r28;
        this.resourcesProvider = new LPhotoPaintView$$ExternalSyntheticLambda8(r35);
        this.currentCropState = r33;
        this.inBubbleMode = r26 instanceof BubbleActivity;
        PersistColorPalette r36 = PersistColorPalette.getInstance(r28);
        this.palette = r36;
        r36.resetCurrentColor();
        this.colorSwatch.color = r36.getCurrentColor();
        this.colorSwatch.brushWeight = r36.getCurrentWeight();
        this.queue = new DispatchQueue("Paint");
        this.bitmapToEdit = r29;
        this.facesBitmap = r30;
        this.originalBitmapRotation = r31;
        UndoStore r37 = new UndoStore();
        this.undoStore = r37;
        r37.setDelegate(new LPhotoPaintView$$ExternalSyntheticLambda10(this));
        AnonymousClass2 r38 = new AnonymousClass2(this, r26, new Painting(getPaintingSize(), r30, r31, null), this.bitmapToEdit, null, null, r30);
        this.renderView = r38;
        r38.setDelegate(new AnonymousClass3(this, r34));
        this.renderView.setUndoStore(this.undoStore);
        this.renderView.setQueue(this.queue);
        this.renderView.setVisibility(4);
        addView(this.renderView, LayoutHelper.createFrame(-1, -1, 51));
        AnonymousClass4 r0 = new AnonymousClass4(this, r26);
        this.renderInputView = r0;
        r0.setVisibility(4);
        addView(this.renderInputView, LayoutHelper.createFrame(-1, -1, 51));
        AnonymousClass6 r02 = new AnonymousClass6(this, r26, new AnonymousClass5(this));
        this.entitiesView = r02;
        addView(r02);
        if (r32 != null) goto L5;
    L39:
        float r16 = r13;
        this.entitiesView.setVisibility(4);
        AnonymousClass7 r03 = new AnonymousClass7(this, r26);
        this.selectionContainerView = r03;
        addView(r03);
        FrameLayout r04 = new FrameLayout(r26);
        this.topLayout = r04;
        r04.setPadding(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f));
        FrameLayout r05 = this.topLayout;
        GradientDrawable.Orientation r4 = GradientDrawable.Orientation.TOP_BOTTOM;
        r05.setBackground(new GradientDrawable(r4, new int[]{-16777216, 0}));
        addView(this.topLayout, LayoutHelper.createFrame(-1, -2, 48));
        ImageView r06 = new ImageView(r26);
        this.undoButton = r06;
        r06.setImageResource(R.drawable.photo_undo2);
        this.undoButton.setPadding(AndroidUtilities.dp(3.0f), AndroidUtilities.dp(3.0f), AndroidUtilities.dp(3.0f), AndroidUtilities.dp(3.0f));
        this.undoButton.setBackground(Theme.createSelectorDrawable(1090519039));
        this.undoButton.setOnClickListener(new LPhotoPaintView$$ExternalSyntheticLambda11(this));
        this.undoButton.setAlpha(0.6f);
        this.undoButton.setClickable(false);
        this.topLayout.addView(this.undoButton, LayoutHelper.createFrame(32, 32.0f, 51, 12.0f, 0.0f, 0.0f, 0.0f));
        LinearLayout r07 = new LinearLayout(r26);
        this.zoomOutButton = r07;
        r07.setOrientation(0);
        this.zoomOutButton.setBackground(Theme.createSelectorDrawable(822083583, 7));
        this.zoomOutButton.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), 0);
        TextView r08 = new TextView(r26);
        this.zoomOutText = r08;
        r08.setTextColor(-1);
        this.zoomOutText.setTypeface(AndroidUtilities.bold());
        this.zoomOutText.setTextSize(1, 16.0f);
        this.zoomOutText.setText(LocaleController.getString(R.string.PhotoEditorZoomOut));
        ImageView r09 = new ImageView(r26);
        this.zoomOutImage = r09;
        r09.setImageResource(R.drawable.photo_zoomout);
        this.zoomOutButton.addView(this.zoomOutImage, LayoutHelper.createLinear(24, 24, 16, 0, 0, 8, 0));
        this.zoomOutButton.addView(this.zoomOutText, LayoutHelper.createLinear(-2, -2, 16));
        this.zoomOutButton.setAlpha(0.0f);
        this.zoomOutButton.setOnClickListener(new LPhotoPaintView$$ExternalSyntheticLambda12());
        this.topLayout.addView(this.zoomOutButton, LayoutHelper.createFrame(-2, 32, 17));
        TextView r010 = new TextView(r26);
        this.undoAllButton = r010;
        r010.setBackground(Theme.createSelectorDrawable(822083583, 7));
        this.undoAllButton.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), 0);
        this.undoAllButton.setText(LocaleController.getString(R.string.PhotoEditorClearAll));
        this.undoAllButton.setGravity(16);
        this.undoAllButton.setTextColor(-1);
        this.undoAllButton.setTypeface(AndroidUtilities.bold());
        this.undoAllButton.setTextSize(1, 16.0f);
        this.undoAllButton.setOnClickListener(new LPhotoPaintView$$ExternalSyntheticLambda13(this));
        this.undoAllButton.setAlpha(0.6f);
        this.topLayout.addView(this.undoAllButton, LayoutHelper.createFrame(-2, 32.0f, 5, 0.0f, 0.0f, 4.0f, 0.0f));
        TextView r011 = new TextView(r26);
        this.cancelTextButton = r011;
        r011.setBackground(Theme.createSelectorDrawable(822083583, 7));
        this.cancelTextButton.setText(LocaleController.getString(R.string.Clear));
        this.cancelTextButton.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), 0);
        this.cancelTextButton.setGravity(16);
        this.cancelTextButton.setTextColor(-1);
        this.cancelTextButton.setTypeface(AndroidUtilities.bold());
        this.cancelTextButton.setTextSize(1, 16.0f);
        this.cancelTextButton.setOnClickListener(new LPhotoPaintView$$ExternalSyntheticLambda14(this));
        this.cancelTextButton.setAlpha(0.0f);
        this.cancelTextButton.setVisibility(8);
        this.topLayout.addView(this.cancelTextButton, LayoutHelper.createFrame(-2, 32.0f, 51, 4.0f, 0.0f, 0.0f, 0.0f));
        TextView r012 = new TextView(r26);
        this.doneTextButton = r012;
        r012.setBackground(Theme.createSelectorDrawable(822083583, 7));
        this.doneTextButton.setText(LocaleController.getString(R.string.Done));
        this.doneTextButton.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), 0);
        this.doneTextButton.setGravity(16);
        this.doneTextButton.setTextColor(-1);
        this.doneTextButton.setTypeface(AndroidUtilities.bold());
        this.doneTextButton.setTextSize(1, 16.0f);
        this.doneTextButton.setOnClickListener(new LPhotoPaintView$$ExternalSyntheticLambda15(this));
        this.doneTextButton.setAlpha(0.0f);
        this.doneTextButton.setVisibility(8);
        this.topLayout.addView(this.doneTextButton, LayoutHelper.createFrame(-2, 32.0f, 5, 0.0f, 0.0f, 4.0f, 0.0f));
        BottomLayout r013 = new BottomLayout(this, r26);
        this.bottomLayout = r013;
        r013.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), 0);
        this.bottomLayout.setBackground(new GradientDrawable(r4, new int[]{0, Integer.MIN_VALUE}));
        addView(this.bottomLayout, LayoutHelper.createFrame(-1, 104, 80));
        if (r30 == null) goto L42;
        boolean r42 = true;
    L43:
        PaintToolsView r014 = new PaintToolsView(r26, r42);
        this.paintToolsView = r014;
        r014.setPadding(AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(16.0f), 0);
        this.paintToolsView.setDelegate(this);
        this.paintToolsView.setSelectedIndex(1);
        this.bottomLayout.addView(this.paintToolsView, LayoutHelper.createFrame(-1, 48.0f));
        PaintTextOptionsView r015 = new PaintTextOptionsView(r26);
        this.textOptionsView = r015;
        r015.setPadding(AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(16.0f), 0);
        this.textOptionsView.setVisibility(8);
        this.textOptionsView.setDelegate(this);
        this.textOptionsView.setAlignment(PersistColorPalette.getInstance(r28).getCurrentAlignment());
        this.bottomLayout.addView(this.textOptionsView, LayoutHelper.createFrame(-1, 48.0f));
        AnonymousClass8 r016 = new AnonymousClass8(this, r26);
        this.overlayLayout = r016;
        addView(r016, LayoutHelper.createFrame(-1, -1.0f));
        PaintTypefaceListView r017 = new PaintTypefaceListView(r26);
        this.typefaceListView = r017;
        r017.setVisibility(8);
        this.typefaceListView.setOnItemClickListener(new LPhotoPaintView$$ExternalSyntheticLambda16(this));
        this.textOptionsView.setTypefaceListView(this.typefaceListView);
        this.overlayLayout.addView(this.typefaceListView, LayoutHelper.createFrame(-2, -2.0f, 85, 0.0f, 0.0f, 8.0f, 8.0f));
        Paint r018 = this.typefaceMenuOutlinePaint;
        Paint.Style r5 = Paint.Style.STROKE;
        r018.setStyle(r5);
        this.typefaceMenuOutlinePaint.setColor(1728053247);
        this.typefaceMenuOutlinePaint.setStrokeWidth(Math.max(2, AndroidUtilities.dp(r16)));
        this.typefaceMenuBackgroundPaint.setColor(getThemedColor(Theme.key_actionBarDefaultSubmenuBackground));
        AnonymousClass9 r019 = new AnonymousClass9(this, r26);
        this.colorsListView = r019;
        r019.setVisibility(8);
        this.colorsListView.setColorPalette(PersistColorPalette.getInstance(r28));
        this.colorsListView.setColorListener(new LPhotoPaintView$$ExternalSyntheticLambda17(this));
        this.bottomLayout.addView(this.colorsListView, LayoutHelper.createFrame(-1, 84.0f, 48, 56.0f, 0.0f, 56.0f, 6.0f));
        setupTabsLayout(r26);
        PaintCancelView r020 = new PaintCancelView(r26);
        this.cancelButton = r020;
        r020.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f));
        this.cancelButton.setBackground(Theme.createSelectorDrawable(1090519039));
        this.bottomLayout.addView(this.cancelButton, LayoutHelper.createFrame(32, 32.0f, 83, 12.0f, 0.0f, 0.0f, 4.0f));
        PaintDoneView r021 = new PaintDoneView(r26);
        this.doneButton = r021;
        r021.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f));
        this.doneButton.setBackground(Theme.createSelectorDrawable(1090519039));
        this.doneButton.setOnClickListener(new LPhotoPaintView$$ExternalSyntheticLambda18(this, r26, r30));
        this.bottomLayout.addView(this.doneButton, LayoutHelper.createFrame(32, 32.0f, 85, 0.0f, 0.0f, 12.0f, 4.0f));
        PaintWeightChooserView r022 = new PaintWeightChooserView(r26);
        this.weightChooserView = r022;
        r022.setColorSwatch(this.colorSwatch);
        this.weightChooserView.setRenderView(this.renderView);
        this.weightChooserView.setValueOverride(this.weightDefaultValueOverride);
        this.colorSwatch.brushWeight = this.weightDefaultValueOverride.get();
        this.weightChooserView.setOnUpdate(new LPhotoPaintView$$ExternalSyntheticLambda9(this, r28));
        addView(this.weightChooserView, LayoutHelper.createFrame(-1, -1.0f));
        FrameLayout r023 = new FrameLayout(r26);
        this.pipetteContainerLayout = r023;
        addView(r023, LayoutHelper.createFrame(-1, -1.0f));
        this.colorSwatchOutlinePaint.setStyle(r5);
        this.colorSwatchOutlinePaint.setStrokeWidth(AndroidUtilities.dp(2.0f));
        setCurrentSwatch(this.colorSwatch, true);
        onBrushSelected(Brush.BRUSHES_LIST.get(0));
        updateColors();
        if (Build.VERSION.SDK_INT < 29) goto L52;
        setSystemGestureExclusionRects(Arrays.asList(new Rect[]{new Rect(0, (int) (AndroidUtilities.displaySize.y * 0.35f), AndroidUtilities.dp(100.0f), (int) (AndroidUtilities.displaySize.y * 0.65d))}));
        return;
    L52:
        return;
    L42:
        r42 = false;
        goto L43
    L5:
        if (r32.isEmpty() == true) goto L39;
        int r52 = r32.size();
        int r6 = 0;
    L7:
        if (r6 >= r52) goto L39;
        VideoEditedInfo.MediaEntity r15 = r32.get(r6);
        byte r024 = r15.type;
        if (r024 != 0) goto L14;
        float r162 = r13;
        ?? r025 = createSticker(r15.parentObject, r15.document, r11);
        if ((r15.subType & r14) == 0) goto L13;
        r025.mirror();
    L13:
        ViewGroup.LayoutParams r132 = r025.getLayoutParams();
        r132.width = r15.viewWidth;
        r132.height = r15.viewHeight;
        int r342 = r52;
        PhotoView r026 = r025;
    L37:
        r026.setX((r15.x * this.paintingSize.width) - ((r15.viewWidth * (r162 - r15.scale)) / 2.0f));
        r026.setY((r15.y * this.paintingSize.height) - ((r15.viewHeight * (r162 - r15.scale)) / 2.0f));
        r026.setPosition(new PointF(r026.getX() + (r15.viewWidth / 2.0f), r026.getY() + (r15.viewHeight / 2.0f)));
        r026.setScale(r15.scale);
        r026.setRotation((float) (((-r15.rotation) / 3.141592653589793d) * 180.0d));
    L38:
        r6 = r6 + 1;
        r52 = r342;
        r13 = r162;
        r10 = 1;
        r11 = false;
        r14 = 2;
        goto L7
    L14:
        r162 = r13;
        if (r024 != r10) goto L28;
        ?? r027 = createText(r11);
        r027.setType(r15.subType);
        r027.setTypeface(r15.textTypeface);
        r027.setBaseFontSize(r15.fontSize);
        SpannableString r43 = new SpannableString(r15.text);
        ArrayList<VideoEditedInfo.EmojiEntity> r133 = r15.entities;
        int r102 = r133.size();
        ?? r12 = r11;
    L17:
        if (r12 >= r102) goto L19;
        VideoEditedInfo.EmojiEntity r39 = r133.get(r12);
        int r343 = r52;
        AnimatedEmojiSpan r142 = new AnimatedEmojiSpan(r39.document_id, r027.getFontMetricsInt());
        int r53 = r39.offset;
        r43.setSpan(r142, r53, r39.length + r53, 33);
        r12 = r12 + 1;
        r52 = r343;
        goto L17
    L19:
        r342 = r52;
        CharSequence r310 = Emoji.replaceEmoji(r43, r027.getFontMetricsInt(), false);
        if ((r310 instanceof Spanned) == false) goto L27;
        Emoji.EmojiSpan[] r44 = (Emoji.EmojiSpan[]) ((Spanned) r310).getSpans(0, r310.length(), Emoji.EmojiSpan.class);
        if (r44 == null) goto L27;
        int r54 = 0;
    L25:
        if (r54 >= r44.length) goto L27;
        r44[r54].scale = 0.85f;
        r54 = r54 + 1;
    L27:
        r027.setText(r310);
        setTextAlignment(r027, r15.textAlign);
        Swatch r311 = r027.getSwatch();
        r311.color = r15.color;
        r027.setSwatch(r311);
        r026 = r027;
        goto L37
    L28:
        r342 = r52;
        byte r312 = r14;
        if (r024 != r312) goto L38;
        PhotoView r028 = createPhoto(r15.text, false);
        r028.preloadSegmented(r15.segmentedPath);
        if ((r15.subType & r312) == 0) goto L34;
        r028.mirror();
    L34:
        if ((r15.subType & 16) == 0) goto L36;
        r028.toggleSegmented(false);
    L36:
        ViewGroup.LayoutParams r313 = r028.getLayoutParams();
        r313.width = r15.viewWidth;
        r313.height = r15.viewHeight;
        r026 = r028;
        goto L37
    }

    private Size basePhotoSize(String r6) {
        BitmapFactory.Options r1 = new BitmapFactory.Options();     // Catch: Exception -> L5
        r1.inJustDecodeBounds = true;     // Catch: Exception -> L5
        BitmapFactory.decodeFile(r6, r1);     // Catch: Exception -> L5
        float r62 = r1.outWidth / r1.outHeight;
    L7:
        EntitiesContainerView r5 = this.entitiesView;
        if (r62 <= 1.0f) goto L11;
        float r52 = (float) Math.floor(r5.getMeasuredWidth() * 0.5d);
        return new Size(r52, r52 / r62);
    L11:
        float r53 = (float) Math.floor(r5.getMeasuredHeight() * 0.5d);
        return new Size(r62 * r53, r53);
    L5:
        e = move-exception;
        FileLog.e(e);
        r62 = 1.0f;
        goto L7
    }

    private Size baseStickerSize() {
        float r4 = (float) Math.floor(getPaintingSize().width * 0.5d);
        return new Size(r4, r4);
    }

    private void bottomPanelTranslationY(float r1, float r2) {
    }

    private PopupButton buttonForPopup(String r3, int r4, boolean r5, Runnable r6) {
        PopupButton r0 = new PopupButton(this, getContext());
        r0.setIcon(r4);
        r0.setText(r3);
        r0.setSelected(r5);
        if (r6 == null) goto L5;
        r0.setOnClickListener(new LPhotoPaintView$$ExternalSyntheticLambda50(r6));
    L5:
        return r0;
    }

    private StickerPosition calculateStickerPosition(TLRPC.Document r16) {
        int r2 = 0;
    L4:
        if (r2 >= r16.attributes.size()) goto L9;
        TLRPC.DocumentAttribute r3 = r16.attributes.get(r2);
        if ((r3 instanceof TLRPC.TL_documentAttributeSticker) == true) goto L7;
        r2 = r2 + 1;
        goto L4
    L7:
        TLRPC.TL_maskCoords r22 = r3.mask_coords;
    L10:
        MediaController.CropState r32 = this.currentCropState;
        float r4 = 0.75f;
        if (r32 == null) goto L13;
        float r5 = -(r32.transformRotation + r32.cropRotate);
        r4 = 0.75f / r32.cropScale;
    L14:
        StickerPosition r33 = new StickerPosition(centerPositionForEntity(), r4, r5);
        if (r22 == null) goto L26;
        ArrayList<PhotoFace> r42 = this.faces;
        if (r42 == null) goto L26;
        if (r42.size() == 0) goto L26;
        int r43 = r22.n;
        PhotoFace r1 = getRandomFaceWithVacantAnchor(r43, r16.id, r22);
        if (r1 == null) goto L26;
        PointF r34 = r1.getPointForAnchor(r43);
        float r44 = r1.getWidthForAnchor(r43);
        float r12 = r1.getAngle();
        float r0 = (float) ((r44 / baseStickerSize().width) * r22.zoom);
        double r52 = (float) Math.toRadians(r12);
        double r9 = 1.5707963267948966d - r52;
        double r13 = r44;
        float r45 = (float) ((Math.sin(r9) * r13) * r22.x);
        float r7 = (float) ((Math.cos(r9) * r13) * r22.x);
        double r53 = r52 + 1.5707963267948966d;
        return new StickerPosition(new PointF((r34.x + r45) + ((float) ((Math.cos(r53) * r13) * r22.y)), (r34.y + r7) + ((float) ((Math.sin(r53) * r13) * r22.y))), r0, r12);
    L26:
        return r33;
    L13:
        r5 = 0.0f;
        goto L14
    L9:
        r22 = null;
        goto L10
    }

    private PointF centerPositionForEntity() {
        Size r0 = getPaintingSize();
        float r1 = r0.width / 2.0f;
        float r3 = r0.height / 2.0f;
        if (this.currentCropState == null) goto L6;
        double r6 = (float) Math.toRadians(-(r2.transformRotation + r2.cropRotate));
        float r2 = (float) ((this.currentCropState.cropPx * Math.cos(r6)) - (this.currentCropState.cropPy * Math.sin(r6)));
        float r12 = (float) ((this.currentCropState.cropPx * Math.sin(r6)) + (this.currentCropState.cropPy * Math.cos(r6)));
        r1 = r1 - (r2 * r0.width);
        r3 = r3 - (r12 * r0.height);
    L6:
        return new PointF(r1, r3);
    }

    private StickerView createSticker(Object r11, TLRPC.Document r12, boolean r13) {
        StickerPosition r0 = calculateStickerPosition(r12);
        AnonymousClass17 r1 = new AnonymousClass17(this, getContext(), StickerPosition.m11515$$Nest$fgetposition(r0), StickerPosition.m11514$$Nest$fgetangle(r0), StickerPosition.m11516$$Nest$fgetscale(r0), baseStickerSize(), r12, r11);
        if (MessageObject.isTextColorEmoji(r12) == false) goto L5;
        r1.centerImage.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
    L5:
        r1.centerImage.setLayerNum(12);
        if (StickerPosition.m11515$$Nest$fgetposition(r0).x != (this.entitiesView.getMeasuredWidth() / 2.0f)) goto L9;
        r1.setStickyX(2);
    L9:
        if (StickerPosition.m11515$$Nest$fgetposition(r0).y != (this.entitiesView.getMeasuredHeight() / 2.0f)) goto L11;
        r1.setStickyY(2);
    L11:
        r1.setDelegate(this);
        this.entitiesView.addView(r1);
        if (r13 == false) goto L14;
        registerRemovalUndo(r1);
        selectEntity(r1);
    L14:
        return r1;
    }

    private TextPaintView createText(boolean r11) {
        onTextAdd();
        Size r0 = getPaintingSize();
        PointF r4 = startPositionRelativeToEntity(null);
        TextPaintView r2 = new TextPaintView(getContext(), r4, (int) (r0.width / 9.0f), _UrlKt.FRAGMENT_ENCODE_SET, this.colorSwatch, this.selectedTextType);
        float r1 = r0.width;
        r2.setMinMaxFontSize((int) ((r1 / 9.0f) * 0.5f), (int) ((r1 / 9.0f) * 2.0f), new LPhotoPaintView$$ExternalSyntheticLambda40(this));
        if (r4.x != (this.entitiesView.getMeasuredWidth() / 2.0f)) goto L6;
        r2.setStickyX(2);
    L6:
        if (r4.y != (this.entitiesView.getMeasuredHeight() / 2.0f)) goto L8;
        r2.setStickyY(2);
    L8:
        r2.setDelegate(this);
        r2.setMaxWidth((int) (r0.width - 20.0f));
        r2.setTypeface(PersistColorPalette.getInstance(this.currentAccount).getCurrentTypeface());
        r2.setType(PersistColorPalette.getInstance(this.currentAccount).getCurrentTextType());
        this.entitiesView.addView(r2, LayoutHelper.createFrame(-2, -2.0f));
        MediaController.CropState r02 = this.currentCropState;
        if (r02 == null) goto L11;
        r2.scale(1.0f / r02.cropScale);
        r2.rotate(-(r0.transformRotation + this.currentCropState.cropRotate));
    L11:
        if (r11 == false) goto L13;
        registerRemovalUndo(r2);
        r2.beginEditing();
        selectEntity(r2, false);
        r2.getFocusedView().requestFocus();
        AndroidUtilities.showKeyboard(r2.getFocusedView());
        this.editingText = true;
        this.textOptionsView.setAlignment(PersistColorPalette.getInstance(this.currentAccount).getCurrentAlignment(), true);
        this.textOptionsView.setOutlineType(PersistColorPalette.getInstance(this.currentAccount).getCurrentTextType());
    L13:
        return r2;
    }

    private void detectFaces() {
        this.queue.postRunnable(new LPhotoPaintView$$ExternalSyntheticLambda43(this), 200);
    }

    private void duplicateSelectedEntity() {
        EntityView r0 = this.currentEntityView;
        if (r0 != null) goto L5;
        return;
    L5:
        PointF r02 = startPositionRelativeToEntity(r0);
        EntityView r1 = this.currentEntityView;
        if ((r1 instanceof StickerView) == false) goto L9;
        StickerView r12 = new StickerView(getContext(), (StickerView) this.currentEntityView, r02);
        r12.setDelegate(this);
        this.entitiesView.addView(r12);
        EntityView r13 = r12;
    L12:
        registerRemovalUndo(r13);
        selectEntity(r13);
        return;
    L9:
        if ((r1 instanceof TextPaintView) == false) goto L11;
        TextPaintView r14 = new TextPaintView(getContext(), (TextPaintView) this.currentEntityView, r02);
        r14.setDelegate(this);
        r14.setMaxWidth((int) (getPaintingSize().width - 20.0f));
        this.entitiesView.addView(r14, LayoutHelper.createFrame(-2, -2.0f));
        r13 = r14;
        goto L12
    L11:
        r13 = null;
        goto L12
    }

    private void editSelectedTextEntity() {
        EntityView r0 = this.currentEntityView;
        if ((r0 instanceof TextPaintView) == true) goto L5;
        return;
    L5:
        if (this.editingText == true) goto L10;
        TextPaintView r02 = (TextPaintView) r0;
        this.editingText = true;
        r02.beginEditing();
        View r2 = r02.getFocusedView();
        r2.requestFocus();
        AndroidUtilities.showKeyboard(r2);
        return;
    }

    private View getBarView(int r2) {
        if (r2 != 0) goto L6;
        return this.paintToolsView;
    L6:
        if (r2 == 2) goto L8;
        return null;
    L8:
        return this.textOptionsView;
    }

    private ViewGroup getBarView() {
        if (this.tabsSelectedIndex != 2) goto L7;
        return this.textOptionsView;
    L7:
        return this.paintToolsView;
    }

    private int[] getCenterLocationInWindow(View r14) {
        this.position[0] = r14.getWidth() / 2.0f;
        this.position[1] = r14.getHeight() / 2.0f;
        r14.getMatrix().mapPoints(this.position);
        float[] r0 = this.position;
        r0[0] = r0[0] + r14.getLeft();
        float[] r02 = this.position;
        r02[1] = r02[1] + r14.getTop();
        Object r142 = r14.getParent();
    L4:
        if ((r142 instanceof View) == false) goto L6;
        View r143 = (View) r142;
        float[] r03 = this.position;
        r03[0] = r03[0] - r143.getScrollX();
        float[] r04 = this.position;
        r04[1] = r04[1] - r143.getScrollY();
        r143.getMatrix().mapPoints(this.position);
        float[] r05 = this.position;
        r05[0] = r05[0] + r143.getLeft();
        float[] r06 = this.position;
        r06[1] = r06[1] + r143.getTop();
        r142 = r143.getParent();
        goto L4
    L6:
        this.pos2[0] = Math.round(this.position[0]);
        this.pos2[1] = Math.round(this.position[1]);
        float r07 = this.pos2[0];
        Point r1 = AndroidUtilities.displaySize;
        float r08 = r07 - (r1.x / 2.0f);
        float r144 = r14[1] - (r1.y / 2.0f);
        double r5 = r08;
        double r09 = (float) Math.toRadians(-this.entitiesView.getRotation());
        double r9 = r144;
        this.pos2[0] = ((int) ((Math.cos(r09) * r5) - (Math.sin(r09) * r9))) + (AndroidUtilities.displaySize.x / 2);
        this.pos2[1] = ((int) ((r5 * Math.sin(r09)) + (r9 * Math.cos(r09)))) + (AndroidUtilities.displaySize.y / 2);
        return this.pos2;
    }

    private int getFrameRotation() {
        int r1 = this.originalBitmapRotation;
        if (r1 != 90) goto L5;
        return 1;
    L5:
        if (r1 != 180) goto L7;
        return 2;
    L7:
        if (r1 == 270) goto L10;
        return 0;
    L10:
        return 3;
    }

    private Size getPaintingSize() {
        Size r0 = this.paintingSize;
        if (r0 == null) goto L5;
        return r0;
    L5:
        float r02 = this.bitmapToEdit.getWidth();
        float r1 = this.bitmapToEdit.getHeight();
        int r2 = SharedConfig.getDevicePerformanceClass();
        if (r2 != 0) goto L8;
        int r22 = 1280;
    L12:
        Size r3 = new Size(r02, r1);
        float r23 = r22;
        r3.width = r23;
        float r4 = (float) Math.floor((r23 * r1) / r02);
        r3.height = r4;
        if (r4 <= r23) goto L15;
        r3.height = r23;
        r3.width = (float) Math.floor((r23 * r02) / r1);
    L15:
        this.paintingSize = r3;
        return r3;
    L8:
        if (r2 == 2) goto L10;
        r22 = 2560;
        goto L12
    L10:
        r22 = 3840;
        goto L12
    }

    private PhotoFace getRandomFaceWithVacantAnchor(int r12, long r13, TLRPC.TL_maskCoords r15) {
        if (r12 >= 0) goto L5;
    L15:
        return null;
    L5:
        if (r12 > 3) goto L15;
        if (this.faces.isEmpty() == true) goto L15;
        int r1 = this.faces.size();
        int r2 = Utilities.random.nextInt(r1);
        int r3 = r1;
    L10:
        if (r3 <= 0) goto L15;
        PhotoFace r6 = this.faces.get(r2);
        LPhotoPaintView r5 = this;
        int r7 = r12;
        long r8 = r13;
        TLRPC.TL_maskCoords r10 = r15;
        if (r5.isFaceAnchorOccupied(r6, r7, r8, r10) == false) goto L13;
        r2 = (r2 + 1) % r1;
        r3 = r3 - 1;
        LPhotoPaintView this = r5;
        r12 = r7;
        r13 = r8;
        r15 = r10;
        goto L10
    L13:
        return r6;
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private void hideEmojiPopup(boolean r4) {
        if (this.emojiViewVisible == false) goto L5;
        showEmojiPopup(0);
    L5:
        if (r4 == false) goto L16;
        EmojiView r42 = this.emojiView;
        if (r42 != null) goto L9;
    L14:
        hideEmojiView();
        return;
    L9:
        if (r42.getVisibility() != 0) goto L14;
        if (this.waitingForKeyboardOpen == true) goto L14;
        ValueAnimator r43 = ValueAnimator.ofFloat(new float[]{0.0f, this.emojiView.getMeasuredHeight()});
        this.bottomPanelIgnoreOnce = false;
        r43.addUpdateListener(new LPhotoPaintView$$ExternalSyntheticLambda22(this));
        this.isAnimatePopupClosing = true;
        r43.addListener(new AnonymousClass20(this));
        r43.setDuration(250);
        r43.setInterpolator(AdjustPanLayoutHelper.keyboardInterpolator);
        r43.start();
        return;
    }

    private void hideEmojiView() {
        if (this.emojiPadding <= 0) goto L6;
        updateKeyboard();
    L6:
        if (this.emojiViewVisible == true) goto L12;
        EmojiView r0 = this.emojiView;
        if (r0 == null) goto L12;
        if (r0.getVisibility() == 8) goto L12;
        this.emojiView.setVisibility(8);
    L12:
        this.emojiPadding = 0;
    }

    private boolean isFaceAnchorOccupied(PhotoFace r10, int r11, long r12, TLRPC.TL_maskCoords r14) {
        if (r10.getPointForAnchor(r11) != null) goto L5;
        return true;
    L5:
        float r102 = r10.getWidthForAnchor(0) * 1.1f;
        int r2 = 0;
    L7:
        if (r2 >= this.entitiesView.getChildCount()) goto L22;
        View r3 = this.entitiesView.getChildAt(r2);
        if ((r3 instanceof StickerView) == false) goto L21;
        StickerView r32 = (StickerView) r3;
        if (r32.getAnchor() != r11) goto L21;
        PointF r4 = r32.getPosition();
        float r42 = (float) Math.hypot(r4.x - r14.x, r4.y - r14.y);
        if (r12 == r32.getSticker().id) goto L19;
        if (this.faces.size() <= 1) goto L21;
    L19:
        if (r42 >= r102) goto L21;
        return true;
    L21:
        r2 = r2 + 1;
        goto L7
    L22:
        return false;
    }

    private boolean isSidewardOrientation() {
        int r2 = this.originalBitmapRotation;
        if ((r2 % 360) != 90) goto L5;
        return true;
    L5:
        if ((r2 % 360) == 270) goto L11;
        return false;
    L11:
        return true;
    }

    private /* synthetic */ void lambda$appearAnimation$23(View r2) {
        if ((r2 instanceof EntityView) == false) goto L6;
        EntityView r22 = (EntityView) r2;
        r22.updateSelectionView();
        selectEntity(r22);
        return;
    }

    private /* synthetic */ void lambda$createText$13() {
        PaintWeightChooserView r0 = this.weightChooserView;
        if (r0 == null) goto L6;
        r0.invalidate();
        return;
    }

    private /* synthetic */ void lambda$detectFaces$24() {
        Detector r0 = null;
        FaceDetector.Builder r1 = new FaceDetector.Builder(ApplicationLoader.applicationContext).setMode(1).setLandmarkType(1);     // Catch: Throwable -> L9 Exception -> L11
        int r2 = 0;
        FaceDetector r02 = r1.setTrackingEnabled(false).build();     // Catch: Throwable -> L9 Exception -> L11
        if (r02.isOperational() == true) goto L38;
        if (BuildVars.LOGS_ENABLED == false) goto L13;
        FileLog.e("face detection is not operational");     // Catch: Throwable -> L9 Exception -> L11
    L13:
        r02.release();
        return;
    L38:
        SparseArray<Face> r12 = r02.detect(new Frame.Builder().setBitmap(this.facesBitmap).setRotation(getFrameRotation()).build());     // Catch: Throwable -> L27
        ArrayList<PhotoFace> r3 = new ArrayList();     // Catch: Throwable -> L9 Exception -> L11
        Size r4 = getPaintingSize();     // Catch: Throwable -> L9 Exception -> L11
    L19:
        if (r2 >= r12.size()) goto L24;
        PhotoFace r6 = new PhotoFace(r12.get(r12.keyAt(r2)), this.facesBitmap, r4, isSidewardOrientation());     // Catch: Throwable -> L9 Exception -> L11
        if (r6.isSufficient() == false) goto L23;
        r3.add(r6);     // Catch: Throwable -> L9 Exception -> L11
    L23:
        r2 = r2 + 1;     // Catch: Throwable -> L9 Exception -> L11
        goto L19
    L24:
        this.faces = r3;     // Catch: Throwable -> L9 Exception -> L11
        r02.release();
        return;
    L27:
        th = move-exception;
        FileLog.e(th);     // Catch: Throwable -> L9 Exception -> L11
        r02.release();
        return;
    L11:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L9
        if (0 == 0) goto L45;
        r0.release();
        return;
    L45:
        return;
    L9:
        th = move-exception;
        if (0 == 0) goto L37;
        r0.release();
    L37:
        throw th;
    }

    private /* synthetic */ void lambda$getThanosEffect$48() {
        ThanosEffect r0 = this.thanosEffect;
        if (r0 == null) goto L6;
        this.thanosEffect = null;
        removeView(r0);
        return;
    }

    private /* synthetic */ void lambda$hideEmojiPopup$47(ValueAnimator r1) {
        float r12 = ((Float) r1.getAnimatedValue()).floatValue();
        this.emojiView.setTranslationY(r12);
    }

    private /* synthetic */ void lambda$new$1() {
        boolean r0 = this.undoStore.canUndo();
        this.undoButton.animate().cancel();
        ViewPropertyAnimator r1 = this.undoButton.animate();
        float r2 = 0.6f;
        if (r0 == false) goto L5;
        float r4 = 1.0f;
    L6:
        r1.alpha(r4).translationY(0.0f).setDuration(150).start();
        this.undoButton.setClickable(r0);
        this.undoAllButton.animate().cancel();
        ViewPropertyAnimator r12 = this.undoAllButton.animate();
        if (r0 == false) goto L9;
        r2 = 1.0f;
    L9:
        r12.alpha(r2).translationY(0.0f).setDuration(150).start();
        this.undoAllButton.setClickable(r0);
        return;
    L5:
        r4 = 0.6f;
        goto L6
    }

    private /* synthetic */ void lambda$new$10(Context r2, Bitmap r3, View r4) {
        if (this.isColorListShown == false) goto L6;
        new ColorPickerBottomSheet(r2, this.resourcesProvider).setColor(this.colorSwatch.color).setPipetteDelegate(new AnonymousClass10(this, r3)).setColorListener(new LPhotoPaintView$$ExternalSyntheticLambda41(this)).show();
        return;
    L6:
        Runnable r1 = this.onDoneButtonClickedListener;
        if (r1 == null) goto L10;
        r1.run();
        return;
    }

    private /* synthetic */ void lambda$new$11(int r3) {
        setCurrentSwatch(this.colorSwatch, true);
        PersistColorPalette.getInstance(r3).setCurrentWeight(this.colorSwatch.brushWeight);
    }

    private /* synthetic */ void lambda$new$2(View r2) {
        RenderView r22 = this.renderView;
        if (r22 != null) goto L5;
    L8:
        this.undoStore.undo();
        return;
    L5:
        if ((r22.getCurrentBrush() instanceof Brush.Shape) == false) goto L8;
        this.renderView.clearShape();
        this.paintToolsView.setSelectedIndex(1);
        onBrushSelected(Brush.BRUSHES_LIST.get(0));
    }

    private /* synthetic */ void lambda$new$4(View r2) {
        if (this.undoStore.canUndo() == true) goto L5;
        return;
    L5:
        RenderView r22 = this.renderView;
        if (r22 != null) goto L8;
    L10:
        this.renderView.clearAll();
        this.undoStore.reset();
        this.entitiesView.removeAllViews();
        return;
    L8:
        if ((r22.getCurrentBrush() instanceof Brush.Shape) == false) goto L10;
        this.renderView.clearShape();
        this.paintToolsView.setSelectedIndex(1);
        onBrushSelected(Brush.BRUSHES_LIST.get(0));
        goto L10
    }

    private /* synthetic */ void lambda$new$5(View r2) {
        EntityView r22 = this.currentEntityView;
        if ((r22 instanceof TextPaintView) == false) goto L6;
        AndroidUtilities.hideKeyboard(((TextPaintView) r22).getFocusedView());
    L6:
        if (this.emojiViewVisible == false) goto L8;
        hideEmojiPopup(false);
    L8:
        removeEntity(this.currentEntityView);
        selectEntity(null);
    }

    private /* synthetic */ void lambda$new$6(View r1) {
        selectEntity(null);
    }

    private /* synthetic */ void lambda$new$7(View r2, int r3) {
        PaintTypeface r22 = PaintTypeface.get().get(r3);
        this.textOptionsView.setTypeface(r22.getKey());
        onTypefaceSelected(r22);
        showTypefaceMenu(false);
    }

    private /* synthetic */ void lambda$new$8(Integer r1) {
        setNewColor(r1.intValue());
        showColorList(false);
    }

    private /* synthetic */ void lambda$new$9(Integer r3) {
        this.palette.selectColor(r3.intValue());
        this.palette.saveColors();
        setNewColor(r3.intValue());
        this.colorsListView.setSelectedColorIndex(this.palette.getCurrentColorPosition());
        this.colorsListView.getAdapter().notifyDataSetChanged();
    }

    private /* synthetic */ void lambda$onAddButtonPressed$33(Brush.Shape r2, int r3) {
        if ((this.renderView.getCurrentBrush() instanceof Brush.Shape) == false) goto L5;
        this.ignoreToolChangeAnimationOnce = true;
    L5:
        onBrushSelected(r2);
        this.paintToolsView.animatePlusToIcon(r3);
    }

    private /* synthetic */ boolean lambda$onAddButtonPressed$34(View r5) {
        if (this.popupLayout == null) goto L15;
        PersistColorPalette.getInstance(this.currentAccount).toggleFillShapes();
        boolean r52 = PersistColorPalette.getInstance(this.currentAccount).getFillShapes();
        int r1 = 0;
    L6:
        if (r1 >= this.popupLayout.getItemsCount()) goto L15;
        View r2 = this.popupLayout.getItemAt(r1);
        if ((r2 instanceof PopupButton) == false) goto L14;
        Brush.Shape r3 = Brush.Shape.SHAPES_LIST.get(r1);
        if (r52 == false) goto L12;
        int r32 = r3.getFilledIconRes();
    L13:
        ((PopupButton) r2).setIcon(r32, r52, true);
        goto L14
    L12:
        r32 = r3.getIconRes();
    L14:
        r1 = r1 + 1;
    L15:
        return true;
    }

    private /* synthetic */ void lambda$onAddButtonPressed$35() {
        boolean r0 = PersistColorPalette.getInstance(this.currentAccount).getFillShapes();
        int r2 = 0;
    L4:
        if (r2 >= Brush.Shape.SHAPES_LIST.size()) goto L10;
        Brush.Shape r3 = Brush.Shape.SHAPES_LIST.get(r2);
        if (r0 == false) goto L8;
        int r4 = r3.getFilledIconRes();
    L9:
        PopupButton r32 = buttonForPopup(r3.getShapeName(), r4, false, new LPhotoPaintView$$ExternalSyntheticLambda47(this, r3, r4));
        r32.setOnLongClickListener(new LPhotoPaintView$$ExternalSyntheticLambda48(this));
        this.popupLayout.addView(r32, LayoutHelper.createLinear(-1, 48));
        r2 = r2 + 1;
        goto L4
    L8:
        r4 = r3.getIconRes();
        goto L9
    }

    private /* synthetic */ void lambda$openStickersView$18() {
        if (this.facesBitmap == null) goto L6;
        detectFaces();
        return;
    }

    private /* synthetic */ Boolean lambda$openStickersView$19(Object r2, TLRPC.Document r3, Boolean r4) {
        StickerView r1 = createSticker(r2, r3, true);
        if (r4.booleanValue() == false) goto L6;
        r1.setScale(1.5f);
    L6:
        return Boolean.TRUE;
    }

    private /* synthetic */ Boolean lambda$openStickersView$20(Integer r2) {
        if (r2.intValue() != 2) goto L6;
        showPhotoAlert();
    L6:
        return Boolean.TRUE;
    }

    private /* synthetic */ void lambda$openStickersView$21(int r1, DialogInterface r2) {
        onOpenCloseStickersAlert(false);
        switchTab(r1);
    }

    private /* synthetic */ void lambda$registerRemovalUndo$45(EntityView r1) {
        removeEntity(r1);
    }

    private /* synthetic */ void lambda$setCurrentSwatch$31(Integer r2, int r3, ValueAnimator r4) {
        float r42 = ((Float) r4.getAnimatedValue()).floatValue();
        Swatch r0 = this.colorSwatch;
        r0.color = ColorUtils.blendARGB(r2.intValue(), r3, r42);
        FrameLayout r1 = this.bottomLayout;
        if (r1 == null) goto L6;
        r1.invalidate();
        return;
    }

    private /* synthetic */ void lambda$setNewColor$12(int r2, int r3, ValueAnimator r4) {
        float r42 = ((Float) r4.getAnimatedValue()).floatValue();
        Swatch r0 = this.colorSwatch;
        r0.color = ColorUtils.blendARGB(r2, r3, r42);
        this.bottomLayout.invalidate();
    }

    private /* synthetic */ void lambda$setupTabsLayout$14(View r1) {
        if (this.editingText == false) goto L6;
        selectEntity(null);
        return;
    L6:
        switchTab(0);
    }

    private /* synthetic */ void lambda$setupTabsLayout$15(View r1) {
        openStickersView();
    }

    private /* synthetic */ void lambda$setupTabsLayout$16(View r1) {
        switchTab(2);
        if ((this.currentEntityView instanceof TextPaintView) == true) goto L6;
        createText(true);
        return;
    }

    private /* synthetic */ void lambda$showColorList$29(View r2, boolean r3, boolean[] r4, float r5, DynamicAnimation r6, float r7, float r8) {
        float r72 = r7 / 1000.0f;
        this.toolsTransformProgress = r72;
        float r73 = ((1.0f - r72) * 0.4f) + 0.6f;
        r2.setScaleX(r73);
        r2.setScaleY(r73);
        r2.setTranslationY((AndroidUtilities.dp(16.0f) * Math.min(this.toolsTransformProgress, 0.25f)) / 0.25f);
        r2.setAlpha(1.0f - (Math.min(this.toolsTransformProgress, 0.25f) / 0.25f));
        this.colorsListView.setProgress(this.toolsTransformProgress, r3);
        this.doneButton.setProgress(this.toolsTransformProgress);
        this.cancelButton.setProgress(this.toolsTransformProgress);
        this.tabsLayout.setTranslationY(AndroidUtilities.dp(32.0f) * this.toolsTransformProgress);
        if (r4[0] == false) goto L13;
        float r42 = this.toolsTransformProgress;
        if (r3 == true) goto L8;
        r42 = 1.0f - r42;
    L8:
        FrameLayout r62 = this.bottomLayout;
        float r74 = AndroidUtilities.dp(40.0f) * r42;
        if (r3 == false) goto L11;
        int r32 = 1;
    L12:
        r62.setTranslationY(r5 - (r74 * r32));
        goto L13
    L11:
        r32 = -1;
    L13:
        this.bottomLayout.invalidate();
        if (r2 != this.textOptionsView) goto L17;
        this.overlayLayout.invalidate();
        return;
    }

    private /* synthetic */ void lambda$showColorList$30(boolean r1, DynamicAnimation r2, boolean r3, float r4, float r5) {
        if (r2 != this.toolsTransformAnimation) goto L8;
        this.toolsTransformAnimation = null;
        if (r1 == true) goto L9;
        this.colorsListView.setVisibility(8);
        PersistColorPalette.getInstance(this.currentAccount).saveColors();
        this.colorsListView.getAdapter().notifyDataSetChanged();
        return;
    L9:
        return;
    }

    private /* synthetic */ void lambda$showEmojiPopup$46(ValueAnimator r1) {
        float r12 = ((Float) r1.getAnimatedValue()).floatValue();
        this.emojiView.setTranslationY(r12);
    }

    private /* synthetic */ void lambda$showMenuForEntity$36(EntityView r1, View r2) {
        removeEntity(r1);
        ActionBarPopupWindow r12 = this.popupWindow;
        if (r12 != null) goto L5;
        return;
    L5:
        if (r12.isShowing() == false) goto L9;
        this.popupWindow.dismiss(true);
        return;
    }

    private /* synthetic */ void lambda$showMenuForEntity$37(View r1) {
        editSelectedTextEntity();
        ActionBarPopupWindow r12 = this.popupWindow;
        if (r12 != null) goto L5;
        return;
    L5:
        if (r12.isShowing() == false) goto L9;
        this.popupWindow.dismiss(true);
        return;
    }

    private /* synthetic */ void lambda$showMenuForEntity$38(EntityView r1, View r2) {
        ((StickerView) r1).mirror(true);
        ActionBarPopupWindow r12 = this.popupWindow;
        if (r12 != null) goto L5;
        return;
    L5:
        if (r12.isShowing() == false) goto L9;
        this.popupWindow.dismiss(true);
        return;
    }

    private /* synthetic */ void lambda$showMenuForEntity$39(View r1) {
        duplicateSelectedEntity();
        ActionBarPopupWindow r12 = this.popupWindow;
        if (r12 != null) goto L5;
        return;
    L5:
        if (r12.isShowing() == false) goto L9;
        this.popupWindow.dismiss(true);
        return;
    }

    private /* synthetic */ void lambda$showMenuForEntity$40(PhotoView r2, View r3) {
        r2.toggleSegmented(true);
        if (r2.isSegmented() == false) goto L5;
        onSwitchSegmentedAnimation(r2);
    L5:
        ActionBarPopupWindow r22 = this.popupWindow;
        if (r22 != null) goto L8;
        return;
    L8:
        if (r22.isShowing() == false) goto L12;
        this.popupWindow.dismiss(true);
        return;
    }

    private /* synthetic */ void lambda$showMenuForEntity$41(EntityView r18) {
        LinearLayout r3 = new LinearLayout(getContext());
        r3.setOrientation(0);
        TextView r5 = new TextView(getContext());
        int r6 = Theme.key_actionBarDefaultSubmenuItem;
        r5.setTextColor(getThemedColor(r6));
        r5.setBackground(Theme.getSelectorDrawable(false));
        r5.setGravity(16);
        r5.setPadding(AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(14.0f), 0);
        r5.setTextSize(1, 14.0f);
        r5.setTag(0);
        r5.setText(LocaleController.getString(R.string.PaintDelete));
        TextUtils.TruncateAt r11 = TextUtils.TruncateAt.END;
        r5.setEllipsize(r11);
        r5.setOnClickListener(new LPhotoPaintView$$ExternalSyntheticLambda25(this, r18));
        r3.addView(r5, LayoutHelper.createLinear(-2, 48));
        if ((r18 instanceof TextPaintView) == false) goto L6;
        TextView r52 = new TextView(getContext());
        r52.setTextColor(getThemedColor(r6));
        r52.setBackground(Theme.getSelectorDrawable(false));
        r52.setGravity(16);
        r52.setPadding(AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(16.0f), 0);
        r52.setTextSize(1, 14.0f);
        r52.setEllipsize(r11);
        r52.setTag(1);
        r52.setText(LocaleController.getString(R.string.PaintEdit));
        r52.setOnClickListener(new LPhotoPaintView$$ExternalSyntheticLambda26(this));
        r3.addView(r52, LayoutHelper.createLinear(-2, 48));
    L6:
        if ((r18 instanceof StickerView) == false) goto L8;
        TextView r53 = new TextView(getContext());
        r53.setTextColor(getThemedColor(r6));
        r53.setBackgroundDrawable(Theme.getSelectorDrawable(false));
        r53.setGravity(16);
        r53.setEllipsize(r11);
        r53.setPadding(AndroidUtilities.dp(14.0f), 0, AndroidUtilities.dp(16.0f), 0);
        r53.setTextSize(1, 14.0f);
        r53.setTag(2);
        r53.setText(LocaleController.getString(R.string.Flip));
        r53.setOnClickListener(new LPhotoPaintView$$ExternalSyntheticLambda27(this, r18));
        r3.addView(r53, LayoutHelper.createLinear(-2, 48));
    L8:
        boolean r54 = r18 instanceof PhotoView;
        if (r54 == true) goto L11;
        TextView r14 = new TextView(getContext());
        r14.setTextColor(getThemedColor(r6));
        r14.setBackgroundDrawable(Theme.getSelectorDrawable(false));
        r14.setGravity(16);
        r14.setEllipsize(r11);
        float r16 = 16.0f;
        r14.setPadding(AndroidUtilities.dp(14.0f), 0, AndroidUtilities.dp(16.0f), 0);
        r14.setTextSize(1, 14.0f);
        r14.setTag(2);
        r14.setText(LocaleController.getString(R.string.PaintDuplicate));
        r14.setOnClickListener(new LPhotoPaintView$$ExternalSyntheticLambda28(this));
        r3.addView(r14, LayoutHelper.createLinear(-2, 48));
    L12:
        if (r54 == false) goto L20;
        PhotoView r1 = (PhotoView) r18;
        if (r1.hasSegmentedImage() == false) goto L20;
        TextView r2 = new TextView(getContext());
        r2.setTextColor(getThemedColor(r6));
        r2.setBackgroundDrawable(Theme.getSelectorDrawable(false));
        r2.setGravity(16);
        r2.setEllipsize(r11);
        r2.setPadding(AndroidUtilities.dp(14.0f), 0, AndroidUtilities.dp(r16), 0);
        r2.setTextSize(1, 14.0f);
        r2.setTag(5);
        if (r1.isSegmented() == false) goto L18;
        int r4 = R.string.SegmentationUndoCutOut;
    L19:
        r2.setText(LocaleController.getString(r4));
        r2.setOnClickListener(new LPhotoPaintView$$ExternalSyntheticLambda29(this, r1));
        r3.addView(r2, LayoutHelper.createLinear(-2, 44));
        r1.highlightSegmented();
        goto L20
    L18:
        r4 = R.string.SegmentationCutOut;
    L20:
        this.popupLayout.addView(r3);
        LinearLayout.LayoutParams r0 = (LinearLayout.LayoutParams) r3.getLayoutParams();
        r0.width = -2;
        r0.height = -2;
        r3.setLayoutParams(r0);
        return;
    L11:
        r16 = 16.0f;
        goto L12
    }

    private /* synthetic */ boolean lambda$showPopup$42(View r2, MotionEvent r3) {
        if (r3.getActionMasked() != 0) goto L13;
        ActionBarPopupWindow r0 = this.popupWindow;
        if (r0 != null) goto L7;
        return false;
    L7:
        if (r0.isShowing() == false) goto L15;
        r2.getHitRect(this.popupRect);
        if (this.popupRect.contains((int) r3.getX(), (int) r3.getY()) == true) goto L16;
        this.popupWindow.dismiss();
        return false;
    L16:
        return false;
    L15:
        return false;
    L13:
        return false;
    }

    private /* synthetic */ void lambda$showPopup$43(KeyEvent r3) {
        if (r3.getKeyCode() == 4) goto L5;
        return;
    L5:
        if (r3.getRepeatCount() != 0) goto L13;
        ActionBarPopupWindow r32 = this.popupWindow;
        if (r32 != null) goto L9;
        return;
    L9:
        if (r32.isShowing() == false) goto L15;
        this.popupWindow.dismiss();
        return;
    L15:
        return;
    }

    private /* synthetic */ void lambda$showPopup$44() {
        this.popupLayout.removeInnerViews();
    }

    private /* synthetic */ void lambda$showTypefaceMenu$27(DynamicAnimation r1, float r2, float r3) {
        float r22 = r2 / 1000.0f;
        this.typefaceMenuTransformProgress = r22;
        this.typefaceListView.setAlpha(r22);
        this.typefaceListView.invalidate();
        this.overlayLayout.invalidate();
        this.textOptionsView.getTypefaceCell().setAlpha(1.0f - this.typefaceMenuTransformProgress);
    }

    private /* synthetic */ void lambda$showTypefaceMenu$28(boolean r1, DynamicAnimation r2, boolean r3, float r4, float r5) {
        if (r2 != this.typefaceMenuTransformAnimation) goto L9;
        this.typefaceMenuTransformAnimation = null;
        if (r1 == true) goto L7;
        this.typefaceListView.setVisibility(8);
    L7:
        this.typefaceListView.setMaskProvider(null);
        return;
    }

    private /* synthetic */ void lambda$switchTab$17(View r8, View r9, ValueAnimator r10) {
        this.tabsSelectionProgress = ((Float) r10.getAnimatedValue()).floatValue();
        this.tabsLayout.invalidate();
        this.bottomLayout.invalidate();
        this.overlayLayout.invalidate();
        int r102 = 0;
    L4:
        if (r102 >= this.tabsLayout.getChildCount()) goto L13;
        View r0 = this.tabsLayout.getChildAt(r102);
        if (r102 != this.tabsNewSelectedIndex) goto L9;
        float r3 = this.tabsSelectionProgress;
    L12:
        r0.setAlpha((r3 * 0.4f) + 0.6f);
        r102 = r102 + 1;
        goto L4
    L9:
        if (r102 != this.tabsSelectedIndex) goto L11;
        r3 = 1.0f - this.tabsSelectionProgress;
        goto L12
    L11:
        r3 = 0.0f;
        goto L12
    L13:
        float r7 = CubicBezierInterpolator.DEFAULT.getInterpolation(this.tabsSelectionProgress);
        if (r8 == null) goto L22;
        if (r9 == null) goto L23;
        float r103 = 1.0f - r7;
        float r02 = (r103 * 0.4f) + 0.6f;
        r8.setScaleX(r02);
        r8.setScaleY(r02);
        r8.setTranslationY((AndroidUtilities.dp(16.0f) * Math.min(r7, 0.25f)) / 0.25f);
        r8.setAlpha(1.0f - (Math.min(r7, 0.25f) / 0.25f));
        float r72 = (r7 * 0.4f) + 0.6f;
        r9.setScaleX(r72);
        r9.setScaleY(r72);
        r9.setTranslationY(((-AndroidUtilities.dp(16.0f)) * Math.min(r103, 0.25f)) / 0.25f);
        r9.setAlpha(1.0f - (Math.min(r103, 0.25f) / 0.25f));
        return;
    L23:
        return;
    }

    private void onWindowSizeChanged() {
        getHeight();
    }

    private void openStickersView() {
        int r0 = this.tabsSelectedIndex;
        switchTab(1);
        postDelayed(new LPhotoPaintView$$ExternalSyntheticLambda35(this), 350);
        AnonymousClass14 r5 = new AnonymousClass14(this, getContext(), false, this.resourcesProvider, false);
        r5.whenDocumentSelected(new LPhotoPaintView$$ExternalSyntheticLambda36(this));
        r5.whenWidgetSelected(new LPhotoPaintView$$ExternalSyntheticLambda37(this));
        r5.setOnDismissListener(new LPhotoPaintView$$ExternalSyntheticLambda38(this, r0));
        r5.show();
        onOpenCloseStickersAlert(true);
    }

    private void registerRemovalUndo(EntityView r4) {
        if (r4 != null) goto L4;
        return;
    L4:
        this.undoStore.registerUndo(r4.getUUID(), new LPhotoPaintView$$ExternalSyntheticLambda39(this, r4));
    }

    private void removeEntity(EntityView r3) {
        EntityView r0 = this.currentEntityView;
        if (r3 != r0) goto L13;
        if (r0 == null) goto L13;
        r0.deselect();
        this.currentEntityView = null;
        if ((r3 instanceof TextPaintView) == false) goto L13;
        ValueAnimator r02 = this.tabsSelectionAnimator;
        if (r02 != null) goto L10;
    L12:
        switchTab(0);
        goto L13
    L10:
        if (this.tabsNewSelectedIndex == 0) goto L12;
        r02.cancel();
    L13:
        this.entitiesView.removeView(r3);
        if (r3 == null) goto L16;
        this.undoStore.unregisterUndo(r3.getUUID());
    L16:
        this.weightChooserView.setValueOverride(this.weightDefaultValueOverride);
        this.weightChooserView.setShowPreview(true);
        Swatch r32 = this.colorSwatch;
        r32.brushWeight = this.weightDefaultValueOverride.get();
        setCurrentSwatch(this.colorSwatch, true);
    }

    private boolean selectEntity(EntityView r2) {
        return selectEntity(r2, true);
    }

    private boolean selectEntity(EntityView r7, boolean r8) {
        boolean r0 = r7 instanceof TextPaintView;
        int r1 = 2;
        if (r0 == false) goto L19;
        int r3 = this.tabsNewSelectedIndex;
        if (r3 == (-1)) goto L7;
    L8:
        if (r3 == (-1)) goto L19;
        if (r3 == 2) goto L19;
    L10:
        ValueAnimator r4 = this.tabsSelectionAnimator;
        if (r4 == null) goto L15;
        if (r3 == 2) goto L15;
        r4.cancel();
    L15:
        if (this.isColorListShown == false) goto L17;
        showColorList(false);
    L17:
        switchTab(2);
        goto L19
    L7:
        if (this.tabsSelectedIndex != 2) goto L10;
    L19:
        if (r0 == false) goto L28;
        if (r8 == false) goto L28;
        TextPaintView r82 = (TextPaintView) r7;
        int r42 = r82.getEditText().getGravity();
        if (r42 != 17) goto L24;
        r1 = 1;
    L27:
        this.textOptionsView.setAlignment(r1);
        this.textOptionsView.setTypeface(r82.getTypeface().getKey());
        this.textOptionsView.setOutlineType(r82.getType(), true);
        this.overlayLayout.invalidate();
        goto L28
    L24:
        if (r42 == 21) goto L27;
        r1 = 0;
    L28:
        EntityView r83 = this.currentEntityView;
        if (r83 == null) goto L44;
        if (r83 == r7) goto L32;
        r83.deselect();
        EntityView r84 = this.currentEntityView;
        if ((r84 instanceof TextPaintView) == false) goto L43;
        ((TextPaintView) r84).endEditing();
        if (r0 == true) goto L43;
        this.editingText = false;
        AndroidUtilities.hideKeyboard(((TextPaintView) this.currentEntityView).getFocusedView());
        hideEmojiPopup(false);
    L43:
        boolean r85 = true;
    L45:
        EntityView r02 = this.currentEntityView;
        this.currentEntityView = r7;
        if ((r02 instanceof TextPaintView) == true) goto L48;
    L50:
        EntityView r72 = this.currentEntityView;
        if (r72 == null) goto L57;
        r72.select(this.selectionContainerView);
        this.entitiesView.bringChildToFront(this.currentEntityView);
        EntityView r73 = this.currentEntityView;
        if ((r73 instanceof TextPaintView) == false) goto L55;
        TextPaintView r74 = (TextPaintView) r73;
        r74.disableAutoresize(false);
        r74.getSwatch().brushWeight = this.colorSwatch.brushWeight;
        setCurrentSwatch(r74.getSwatch(), true);
        this.weightChooserView.setValueOverride(new AnonymousClass11(this, r74, (int) (this.paintingSize.width / 9.0f)));
        this.weightChooserView.setShowPreview(false);
    L56:
        return true;
    L55:
        this.weightChooserView.setValueOverride(this.weightDefaultValueOverride);
        this.weightChooserView.setShowPreview(true);
        this.colorSwatch.brushWeight = this.weightDefaultValueOverride.get();
        setCurrentSwatch(this.colorSwatch, true);
        goto L56
    L57:
        ValueAnimator r75 = this.tabsSelectionAnimator;
        if (r75 == null) goto L63;
        if (this.tabsNewSelectedIndex == 0) goto L63;
        r75.cancel();
    L63:
        if (this.isColorListShown == false) goto L65;
        showColorList(false);
    L65:
        switchTab(0);
        this.weightChooserView.setValueOverride(this.weightDefaultValueOverride);
        this.weightChooserView.setShowPreview(true);
        this.colorSwatch.brushWeight = this.weightDefaultValueOverride.get();
        setCurrentSwatch(this.colorSwatch, true);
        return r85;
    L48:
        if (TextUtils.isEmpty(((TextPaintView) r02).getText()) == false) goto L50;
        removeEntity(r02);
        goto L50
    L32:
        if (this.editingText == true) goto L35;
        showMenuForEntity(r83);
    L37:
        return true;
    L35:
        if ((r83 instanceof TextPaintView) == false) goto L37;
        AndroidUtilities.showKeyboard(((TextPaintView) r83).getFocusedView());
        hideEmojiPopup(false);
        goto L37
    L44:
        r85 = false;
        goto L45
    }

    private void setCurrentSwatch(Swatch r2, boolean r3) {
        setCurrentSwatch(r2, r3, null);
    }

    private void setCurrentSwatch(Swatch r4, boolean r5, Integer r6) {
        Swatch r0 = this.colorSwatch;
        if (r0 == r4) goto L5;
        r0.color = r4.color;
        r0.colorLocation = r4.colorLocation;
        r0.brushWeight = r4.brushWeight;
        PersistColorPalette.getInstance(this.currentAccount).selectColor(r4.color);
        PersistColorPalette.getInstance(this.currentAccount).setCurrentWeight(r4.brushWeight);
    L5:
        this.renderView.setColor(r4.color);
        this.renderView.setBrushSize(r4.brushWeight);
        if (r5 == false) goto L15;
        int r52 = this.colorSwatch.color;
        if (r6 != null) goto L10;
    L12:
        FrameLayout r53 = this.bottomLayout;
        if (r53 == null) goto L15;
        r53.invalidate();
        goto L15
    L10:
        if (r6.intValue() == r52) goto L12;
        ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}).setDuration(150);
        r02.addUpdateListener(new LPhotoPaintView$$ExternalSyntheticLambda23(this, r6, r52));
        r02.start();
    L15:
        EntityView r3 = this.currentEntityView;
        if ((r3 instanceof TextPaintView) == false) goto L19;
        ((TextPaintView) r3).setSwatch(new Swatch(r4.color, r4.colorLocation, r4.brushWeight));
        return;
    }

    private void setNewColor(int r5) {
        Swatch r0 = this.colorSwatch;
        int r1 = r0.color;
        r0.color = r5;
        setCurrentSwatch(r0, true);
        ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}).setDuration(150);
        r02.addUpdateListener(new LPhotoPaintView$$ExternalSyntheticLambda5(this, r1, r5));
        r02.start();
    }

    private void setTextAlignment(TextPaintView r4, int r5) {
        r4.setAlign(r5);
        int r3 = 2;
        if (r5 == 1) goto L7;
        if (r5 == 2) goto L6;
        int r1 = 19;
    L8:
        r4.getEditText().setGravity(r1);
        if (r5 != 1) goto L11;
        r3 = 4;
    L19:
        r4.getEditText().setTextAlignment(r3);
        return;
    L11:
        if (r5 == 2) goto L16;
        if (LocaleController.isRTL == false) goto L19;
    L14:
        r3 = 3;
        goto L19
    L16:
        if (LocaleController.isRTL == false) goto L14;
    L6:
        r1 = 21;
        goto L8
    L7:
        r1 = 17;
        goto L8
    }

    private void setTextType(int r3) {
        this.selectedTextType = r3;
        EntityView r0 = this.currentEntityView;
        if ((r0 instanceof TextPaintView) == false) goto L5;
        ((TextPaintView) r0).setType(r3);
    L5:
        PersistColorPalette.getInstance(this.currentAccount).setCurrentTextType(r3);
        this.textOptionsView.setOutlineType(r3);
    }

    private void setupTabsLayout(Context r14) {
        AnonymousClass12 r0 = new AnonymousClass12(this, r14);
        this.tabsLayout = r0;
        r0.setClipToPadding(false);
        this.tabsLayout.setOrientation(0);
        this.bottomLayout.addView(this.tabsLayout, LayoutHelper.createFrame(-1, 40.0f, 80, 52.0f, 0.0f, 52.0f, 0.0f));
        TextView r02 = new TextView(r14);
        this.drawTab = r02;
        r02.setText(LocaleController.getString(R.string.PhotoEditorDraw).toUpperCase());
        TextView r03 = this.drawTab;
        int r2 = Theme.key_listSelector;
        r03.setBackground(Theme.createSelectorDrawable(getThemedColor(r2), 7));
        this.drawTab.setPadding(0, AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f));
        this.drawTab.setTextColor(-1);
        this.drawTab.setTextSize(1, 14.0f);
        this.drawTab.setGravity(1);
        this.drawTab.setTypeface(AndroidUtilities.bold());
        this.drawTab.setSingleLine();
        this.drawTab.setOnClickListener(new LPhotoPaintView$$ExternalSyntheticLambda0(this));
        this.tabsLayout.addView(this.drawTab, LayoutHelper.createLinear(0, -2, 1.0f));
        TextView r04 = new TextView(r14);
        this.stickerTab = r04;
        r04.setText(LocaleController.getString(R.string.PhotoEditorSticker).toUpperCase());
        this.stickerTab.setBackground(Theme.createSelectorDrawable(getThemedColor(r2), 7));
        this.stickerTab.setPadding(0, AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f));
        this.stickerTab.setOnClickListener(new LPhotoPaintView$$ExternalSyntheticLambda1(this));
        this.stickerTab.setTextColor(-1);
        this.stickerTab.setTextSize(1, 14.0f);
        this.stickerTab.setGravity(1);
        this.stickerTab.setTypeface(AndroidUtilities.bold());
        this.stickerTab.setAlpha(0.6f);
        this.stickerTab.setSingleLine();
        this.tabsLayout.addView(this.stickerTab, LayoutHelper.createLinear(0, -2, 1.0f));
        TextView r05 = new TextView(r14);
        this.textTab = r05;
        r05.setText(LocaleController.getString(R.string.PhotoEditorText).toUpperCase());
        this.textTab.setBackground(Theme.createSelectorDrawable(getThemedColor(r2), 7));
        this.textTab.setPadding(0, AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f));
        this.textTab.setTextColor(-1);
        this.textTab.setTextSize(1, 14.0f);
        this.textTab.setGravity(1);
        this.textTab.setTypeface(AndroidUtilities.bold());
        this.textTab.setAlpha(0.6f);
        this.textTab.setSingleLine();
        this.textTab.setOnClickListener(new LPhotoPaintView$$ExternalSyntheticLambda2(this));
        this.tabsLayout.addView(this.textTab, LayoutHelper.createLinear(0, -2, 1.0f));
    }

    @SuppressLint({"NotifyDataSetChanged"})
    private void showColorList(boolean r10) {
        if (this.isColorListShown == r10) goto L26;
        this.isColorListShown = r10;
        SpringAnimation r0 = this.toolsTransformAnimation;
        if (r0 == null) goto L7;
        r0.cancel();
    L7:
        float r2 = 1000.0f;
        if (r10 == false) goto L10;
        float r4 = 0.0f;
    L11:
        SpringAnimation r02 = new SpringAnimation(new FloatValueHolder(r4));
        this.toolsTransformAnimation = r02;
        SpringForce r1 = new SpringForce();
        if (r10 == true) goto L15;
        r2 = 0.0f;
    L15:
        r02.setSpring(r1.setFinalPosition(r2).setStiffness(1250.0f).setDampingRatio(1.0f));
        if (this.keyboardVisible == false) goto L18;
    L21:
        boolean r03 = true;
    L22:
        float r8 = this.bottomLayout.getTranslationY();
        this.toolsTransformAnimation.addUpdateListener(new LPhotoPaintView$$ExternalSyntheticLambda3(this, getBarView(), r10, new boolean[]{r03}, r8));
        this.toolsTransformAnimation.addEndListener(new LPhotoPaintView$$ExternalSyntheticLambda4(this, r10));
        this.toolsTransformAnimation.start();
        if (r10 == false) goto L27;
        this.colorsListView.setVisibility(0);
        this.colorsListView.setSelectedColorIndex(PersistColorPalette.getInstance(this.currentAccount).getCurrentColorPosition());
        return;
    L27:
        return;
    L18:
        if (this.emojiViewVisible == true) goto L21;
        r03 = false;
        goto L22
    L10:
        r4 = 1000.0f;
        goto L11
    }

    private void showEmojiPopup(int r9) {
        this.bottomPanelIgnoreOnce = false;
        if (r9 != 1) goto L43;
        EmojiView r92 = this.emojiView;
        if (r92 != null) goto L7;
    L9:
        boolean r93 = false;
    L10:
        createEmojiView();
        this.emojiView.setVisibility(0);
        this.emojiViewWasVisible = this.emojiViewVisible;
        this.emojiViewVisible = true;
        EmojiView r2 = this.emojiView;
        if (this.keyboardHeight > 0) goto L17;
        if (AndroidUtilities.isTablet() == false) goto L15;
        this.keyboardHeight = AndroidUtilities.dp(150.0f);
        goto L17
    L15:
        this.keyboardHeight = MessagesController.getGlobalEmojiSettings().getInt("kbd_height", AndroidUtilities.dp(200.0f));
    L17:
        if (this.keyboardHeightLand <= 0) goto L19;
    L22:
        Point r3 = AndroidUtilities.displaySize;
        if (r3.x <= r3.y) goto L25;
        int r32 = this.keyboardHeightLand;
    L26:
        FrameLayout.LayoutParams r4 = (FrameLayout.LayoutParams) r2.getLayoutParams();
        r4.height = r32;
        r2.setLayoutParams(r4);
        if (AndroidUtilities.isInMultiwindow == false) goto L29;
    L33:
        this.emojiWasPadding = r32;
        this.emojiPadding = r32;
        requestLayout();
        updateKeyboard();
        ChatActivityEnterViewAnimatedIconView r22 = this.textOptionsView.getEmojiButton();
        if (r22 == null) goto L36;
        r22.setState(ChatActivityEnterViewAnimatedIconView.State.KEYBOARD, true);
    L36:
        onWindowSizeChanged();
        if (r93 == false) goto L39;
        return;
    L39:
        if (this.keyboardVisible == true) goto L58;
        ValueAnimator r94 = ValueAnimator.ofFloat(new float[]{this.emojiPadding, 0.0f});
        r94.addUpdateListener(new LPhotoPaintView$$ExternalSyntheticLambda7(this));
        r94.addListener(new AnonymousClass19(this));
        r94.start();
        return;
    L58:
        return;
    L29:
        if (AndroidUtilities.isTablet() == true) goto L33;
        EntityView r23 = this.currentEntityView;
        if ((r23 instanceof TextPaintView) == false) goto L33;
        AndroidUtilities.hideKeyboard(((TextPaintView) r23).getEditText());
        goto L33
    L25:
        r32 = this.keyboardHeight;
        goto L26
    L19:
        if (AndroidUtilities.isTablet() == false) goto L21;
        this.keyboardHeightLand = AndroidUtilities.dp(150.0f);
        goto L22
    L21:
        this.keyboardHeightLand = MessagesController.getGlobalEmojiSettings().getInt("kbd_height_land3", AndroidUtilities.dp(200.0f));
        goto L22
    L7:
        if (r92.getVisibility() != 0) goto L9;
        r93 = true;
        goto L10
    L43:
        ChatActivityEnterViewAnimatedIconView r24 = this.textOptionsView.getEmojiButton();
        if (r24 == null) goto L46;
        r24.setState(ChatActivityEnterViewAnimatedIconView.State.SMILE, true);
    L46:
        EmojiView r1 = this.emojiView;
        if (r1 == null) goto L53;
        this.emojiViewWasVisible = this.emojiViewVisible;
        this.emojiViewVisible = false;
        if (AndroidUtilities.usingHardwareInput == false) goto L51;
    L52:
        r1.setVisibility(8);
        goto L53
    L51:
        if (AndroidUtilities.isInMultiwindow == true) goto L52;
    L53:
        if (r9 != 0) goto L55;
        this.emojiPadding = 0;
    L55:
        updateKeyboard();
        requestLayout();
        onWindowSizeChanged();
    }

    private void showMenuForEntity(EntityView r9) {
        int[] r0 = getCenterLocationInWindow(r9);
        int r6 = r0[0];
        int r7 = r0[1] - AndroidUtilities.dp(32.0f);
        showPopup(new LPhotoPaintView$$ExternalSyntheticLambda6(this, r9), this, 51, r6, r7);
    }

    private void showPhotoAlert() {
        ChatAttachAlert r0 = new ChatAttachAlert(getContext(), new AnonymousClass15(this, null), false, false, false, this.resourcesProvider);
        r0.drawNavigationBar = true;
        r0.setupPhotoPicker(LocaleController.getString(R.string.AddImage));
        r0.setDelegate(new AnonymousClass16(this, r0));
        r0.setOnDismissListener(new LPhotoPaintView$$ExternalSyntheticLambda42());
        r0.setMaxSelectedPhotos(1, false);
        r0.init();
        MediaController.forceBroadcastNewPhotos = true;
        r0.getPhotoLayout().loadGalleryPhotos();
        r0.show();
    }

    private void showPopup(Runnable r6, View r7, int r8, int r9, int r10) {
        ActionBarPopupWindow r0 = this.popupWindow;
        if (r0 == null) goto L9;
        if (r0.isShowing() == false) goto L9;
        this.popupWindow.dismiss();
        return;
    L9:
        if (this.popupLayout != null) goto L11;
        this.popupRect = new Rect();
        ActionBarPopupWindow.ActionBarPopupWindowLayout r02 = new ActionBarPopupWindow.ActionBarPopupWindowLayout(getContext(), this.resourcesProvider);
        this.popupLayout = r02;
        r02.setAnimationEnabled(true);
        this.popupLayout.setBackgroundColor(-14145495);
        this.popupLayout.setOnTouchListener(new LPhotoPaintView$$ExternalSyntheticLambda30(this));
        this.popupLayout.setDispatchKeyEventListener(new LPhotoPaintView$$ExternalSyntheticLambda31(this));
        this.popupLayout.setShownFromBottom(true);
    L11:
        this.popupLayout.removeInnerViews();
        r6.run();
        if (this.popupWindow != null) goto L14;
        ActionBarPopupWindow r62 = new ActionBarPopupWindow(this.popupLayout, -2, -2);
        this.popupWindow = r62;
        r62.setAnimationEnabled(true);
        this.popupWindow.setAnimationStyle(R.style.PopupAnimation);
        this.popupWindow.setOutsideTouchable(true);
        this.popupWindow.setClippingEnabled(true);
        this.popupWindow.setInputMethodMode(2);
        this.popupWindow.setSoftInputMode(0);
        this.popupWindow.getContentView().setFocusableInTouchMode(true);
        this.popupWindow.setOnDismissListener(new LPhotoPaintView$$ExternalSyntheticLambda32(this));
    L14:
        this.popupLayout.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000.0f), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000.0f), Integer.MIN_VALUE));
        this.popupWindow.setFocusable(true);
        if ((r8 & 48) == 0) goto L17;
        r9 = r9 - (this.popupLayout.getMeasuredWidth() / 2);
        r10 = r10 - this.popupLayout.getMeasuredHeight();
    L17:
        this.popupWindow.showAtLocation(r7, r8, r9, r10);
        ActionBarPopupWindow.startAnimation(this.popupLayout);
    }

    private void showTypefaceMenu(boolean r6) {
        if (this.isTypefaceMenuShown == r6) goto L20;
        this.isTypefaceMenuShown = r6;
        SpringAnimation r0 = this.typefaceMenuTransformAnimation;
        if (r0 == null) goto L7;
        r0.cancel();
    L7:
        float r2 = 1000.0f;
        if (r6 == false) goto L10;
        float r4 = 0.0f;
    L11:
        SpringAnimation r02 = new SpringAnimation(new FloatValueHolder(r4));
        this.typefaceMenuTransformAnimation = r02;
        SpringForce r1 = new SpringForce();
        if (r6 == true) goto L15;
        r2 = 0.0f;
    L15:
        r02.setSpring(r1.setFinalPosition(r2).setStiffness(1250.0f).setDampingRatio(1.0f));
        if (r6 == false) goto L18;
        this.typefaceListView.setAlpha(0.0f);
        this.typefaceListView.setVisibility(0);
    L18:
        this.typefaceMenuTransformAnimation.addUpdateListener(new LPhotoPaintView$$ExternalSyntheticLambda33(this));
        this.typefaceMenuTransformAnimation.addEndListener(new LPhotoPaintView$$ExternalSyntheticLambda34(this, r6));
        this.typefaceMenuTransformAnimation.start();
        return;
    L10:
        r4 = 1000.0f;
        goto L11
    }

    private PointF startPositionRelativeToEntity(EntityView r12) {
        MediaController.CropState r0 = this.currentCropState;
        float r1 = 200.0f;
        if (r0 == null) goto L5;
        r1 = 200.0f / r0.cropScale;
    L5:
        if (r12 == null) goto L8;
        PointF r11 = r12.getPosition();
        return new PointF(r11.x + r1, r11.y + r1);
    L8:
        float r122 = 100.0f;
        if (r0 == null) goto L11;
        r122 = 100.0f / r0.cropScale;
    L11:
        PointF r02 = centerPositionForEntity();
    L12:
        int r2 = 0;
        boolean r3 = false;
    L14:
        if (r2 >= this.entitiesView.getChildCount()) goto L22;
        View r4 = this.entitiesView.getChildAt(r2);
        if ((r4 instanceof EntityView) == false) goto L21;
        PointF r42 = ((EntityView) r4).getPosition();
        if (((float) Math.sqrt(Math.pow(r42.x - r02.x, 2.0d) + Math.pow(r42.y - r02.y, 2.0d))) >= r122) goto L21;
        r3 = true;
    L21:
        r2 = r2 + 1;
        goto L14
    L22:
        if (r3 == false) goto L23;
        r02 = new PointF(r02.x + r1, r02.y + r1);
        goto L12
    L23:
        return r02;
    }

    private void switchTab(int r7) {
        if (this.tabsSelectedIndex != r7) goto L5;
        return;
    L5:
        if (this.tabsNewSelectedIndex == r7) goto L17;
        ValueAnimator r0 = this.tabsSelectionAnimator;
        if (r0 == null) goto L10;
        r0.cancel();
    L10:
        View r02 = getBarView(this.tabsSelectedIndex);
        this.tabsNewSelectedIndex = r7;
        View r1 = getBarView(r7);
        PersistColorPalette r2 = PersistColorPalette.getInstance(this.currentAccount);
        if (r7 != 2) goto L13;
        boolean r5 = true;
    L14:
        r2.setInTextMode(r5);
        this.colorSwatch.color = PersistColorPalette.getInstance(this.currentAccount).getCurrentColor();
        setCurrentSwatch(this.colorSwatch, true);
        ValueAnimator r22 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}).setDuration(300);
        this.tabsSelectionAnimator = r22;
        r22.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.tabsSelectionAnimator.addUpdateListener(new LPhotoPaintView$$ExternalSyntheticLambda21(this, r02, r1));
        this.tabsSelectionAnimator.addListener(new AnonymousClass13(this, r02, r1, r7));
        this.tabsSelectionAnimator.start();
        return;
    L13:
        r5 = false;
        goto L14
    }

    public float adjustPanLayoutHelperProgress() {
        return this.panTranslationProgress;
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView.EntityViewDelegate
    public boolean allowInteraction(EntityView r1) {
        return !this.editingText;
    }

    public void appearAnimation(View r5) {
        float r0 = r5.getScaleX();
        float r1 = r5.getScaleY();
        r5.setScaleX(r0 * 0.5f);
        r5.setScaleY(0.5f * r1);
        r5.setAlpha(0.0f);
        r5.animate().scaleX(r0).scaleY(r1).alpha(1.0f).setInterpolator(new OvershootInterpolator(3.0f)).setDuration(240).withEndAction(new LPhotoPaintView$$ExternalSyntheticLambda49(this, r5)).start();
    }

    public void createEmojiView() {
        EmojiView r0 = this.emojiView;
        if (r0 == null) goto L8;
        if (r0.currentAccount == UserConfig.selectedAccount) goto L8;
        removeView(r0);
        this.emojiView = null;
    L8:
        if (this.emojiView == null) goto L10;
        return;
    L10:
        EmojiView r1 = new EmojiView(null, true, false, false, getContext(), false, null, null, true, this.resourcesProvider, false, false, true);
        this.emojiView = r1;
        r1.allowEmojisForNonPremium(true, false);
        this.emojiView.setVisibility(8);
        if (AndroidUtilities.isTablet() == false) goto L13;
        this.emojiView.setForseMultiwindowLayout(true);
    L13:
        this.emojiView.setDelegate(new AnonymousClass21(this));
        addView(this.emojiView);
    }

    public PhotoView createPhoto(String r10, boolean r11) {
        Size r5 = basePhotoSize(r10);
        Pair<Integer, Integer> r0 = AndroidUtilities.getImageOrientation(r10);
        if (((((Integer) r0.first).intValue() / 90) % 2) != 1) goto L5;
        float r1 = r5.width;
        r5.width = r5.height;
        r5.height = r1;
    L5:
        PhotoView r02 = new PhotoView(getContext(), centerPositionForEntity(), 0.0f, 1.0f, r5, r10, ((Integer) r0.first).intValue(), ((Integer) r0.second).intValue());
        r02.setDelegate(this);
        this.entitiesView.addView(r02);
        if (r11 == false) goto L8;
        registerRemovalUndo(r02);
        selectEntity(r02);
    L8:
        return r02;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 == NotificationCenter.customTypefacesLoaded) goto L5;
        return;
    L5:
        if (this.entitiesView == null) goto L18;
        int r12 = 0;
    L9:
        if (r12 >= this.entitiesView.getChildCount()) goto L19;
        View r22 = this.entitiesView.getChildAt(r12);
        if ((r22 instanceof TextPaintView) == false) goto L13;
        ((TextPaintView) r22).updateTypeface();
    L13:
        r12 = r12 + 1;
        goto L9
    L19:
        return;
    }

    public abstract void didSetAnimatedSticker(RLottieDrawable r1);

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r1) {
        if (super.dispatchTouchEvent(r1) == true) goto L6;
        return false;
    L6:
        return true;
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r13, View r14, long r15) {
        int r10 = 0;
        if (r14 != this.entitiesView) goto L8;
        float r1 = this.shadowAlpha.set(this.drawShadow);
        if (r1 <= 0.0f) goto L8;
        this.shadowPaint.setShadowLayer(AndroidUtilities.dp(24.0f * r1), 0.0f, 0.0f, Theme.multAlpha(1090519039, r1));
        this.shadowPaint.setColor(0);
        r13.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), 255, 31);
        r13.translate(this.entitiesView.getX(), this.entitiesView.getY());
        r13.scale(this.entitiesView.getScaleX(), this.entitiesView.getScaleY(), this.entitiesView.getWidth() / 2.0f, this.entitiesView.getHeight() / 2.0f);
        r13.drawRect(0.0f, 0.0f, this.entitiesView.getWidth(), this.entitiesView.getHeight(), this.shadowPaint);
        r13.drawRect(0.0f, 0.0f, this.entitiesView.getWidth(), this.entitiesView.getHeight(), this.clearPaint);
        r13.restore();
    L8:
        if (r14 == this.renderView) goto L20;
        if (r14 == this.renderInputView) goto L20;
        EntitiesContainerView r2 = this.entitiesView;
        if (r14 == r2) goto L14;
    L15:
        FrameLayout r22 = this.selectionContainerView;
        if (r14 == r22) goto L18;
    L30:
        boolean r0 = super.drawChild(r13, r14, r15);
        if (r10 == 0) goto L33;
        r13.restore();
    L33:
        return r0;
    L18:
        if (r22.getClipChildren() == false) goto L30;
    L14:
        if (r2.getClipChildren() == false) goto L15;
    L20:
        if (this.currentCropState == null) goto L30;
        r13.save();
        if (this.inBubbleMode == true) goto L24;
        r10 = AndroidUtilities.statusBarHeight;
    L24:
        int r23 = ActionBar.getCurrentActionBarHeight() + r10;
        int r3 = r14.getMeasuredWidth();
        int r4 = r14.getMeasuredHeight();
        MediaController.CropState r5 = this.currentCropState;
        int r6 = r5.transformRotation;
        if (r6 != 90) goto L27;
    L28:
        r4 = r3;
        r3 = r4;
    L29:
        float r32 = (r3 * r5.cropPw) * r14.getScaleX();
        MediaController.CropState r52 = this.currentCropState;
        int r33 = (int) (r32 / r52.cropScale);
        int r42 = (int) (((r4 * r52.cropPh) * r14.getScaleY()) / this.currentCropState.cropScale);
        float r53 = ((float) Math.ceil((getMeasuredWidth() - r33) / 2.0f)) + this.transformX;
        float r24 = ((((((((getMeasuredHeight() - this.emojiPadding) - r23) - AndroidUtilities.dp(48.0f)) + getAdditionalBottom()) - r42) / 2.0f) + AndroidUtilities.dp(8.0f)) + r10) + this.transformY;
        r13.clipRect(Math.max(0.0f, r53), Math.max(0.0f, r24), Math.min(r53 + r33, getMeasuredWidth()), Math.min(getMeasuredHeight(), r24 + r42));
        r10 = 1;
        goto L30
    L27:
        if (r6 != 270) goto L29;
        goto L28
    }

    public int getAdditionalBottom() {
        return AndroidUtilities.dp(24.0f);
    }

    public int getAdditionalTop() {
        return AndroidUtilities.dp(48.0f);
    }

    public Bitmap getBitmap(ArrayList<VideoEditedInfo.MediaEntity> r27, Bitmap[] r28) {
        LPhotoPaintView r1 = this;
        ArrayList<VideoEditedInfo.MediaEntity> r2 = r27;
        int r3 = 0;
        Bitmap r4 = r1.renderView.getResultBitmap(false, false);
        r1.lcm = BigInteger.ONE;
        if (r4 != null) goto L5;
    L109:
        return r4;
    L5:
        if (r1.entitiesView.entitiesCount() <= 0) goto L109;
        int r5 = r1.entitiesView.getChildCount();
        int r7 = 0;
        Canvas r0 = null;
    L7:
        if (r7 >= r5) goto L109;
        View r8 = r1.entitiesView.getChildAt(r7);
        if ((r8 instanceof EntityView) == true) goto L11;
        Canvas r22 = r0;
    L80:
        r0 = r22;
        int r23 = 0;
    L108:
        r7 = r7 + 1;
        r1 = this;
        r3 = r23;
        r2 = r27;
        goto L7
    L11:
        EntityView r9 = (EntityView) r8;
        PointF r10 = r9.getPosition();
        if (r2 == null) goto L81;
        VideoEditedInfo.MediaEntity r13 = new VideoEditedInfo.MediaEntity();
        byte r16 = 4;
        float r17 = 2.0f;
        if ((r9 instanceof TextPaintView) == false) goto L44;
        r13.type = 1;
        TextPaintView r14 = (TextPaintView) r9;
        CharSequence r15 = r14.getText();
        if ((r15 instanceof Spanned) == false) goto L42;
        Spanned r6 = (Spanned) r15;
        AnimatedEmojiSpan[] r11 = (AnimatedEmojiSpan[]) r6.getSpans(r3, r15.length(), AnimatedEmojiSpan.class);
        if (r11 == null) goto L41;
        int r12 = r3;
    L21:
        if (r12 >= r11.length) goto L41;
        AnimatedEmojiSpan r32 = r11[r12];
        Canvas r222 = r0;
        TLRPC.Document r02 = r32.document;
        if (r02 != null) goto L25;
        AnimatedEmojiSpan[] r232 = r11;
        int r24 = r12;
        r02 = AnimatedEmojiDrawable.findDocument(r1.currentAccount, r32.getDocumentId());
    L26:
        if (r02 == null) goto L28;
        AnimatedEmojiDrawable.getDocumentFetcher(r1.currentAccount).putDocument(r02);
    L28:
        VideoEditedInfo.EmojiEntity r112 = new VideoEditedInfo.EmojiEntity();
        TextPaintView r122 = r14;
        CharSequence r25 = r15;
        r112.document_id = r32.getDocumentId();
        r112.document = r02;
        r112.offset = r6.getSpanStart(r32);
        r112.length = r6.getSpanEnd(r32) - r112.offset;
        r112.documentAbsolutePath = FileLoader.getInstance(r1.currentAccount).getPathToAttach(r02, true).getAbsolutePath();
        boolean r33 = MessageObject.isAnimatedStickerDocument(r112.document, true);
        if (r33 == false) goto L31;
    L32:
        byte r142 = r112.subType;
        if (r33 == false) goto L35;
        byte r34 = 1;
    L36:
        r112.subType = (byte) (r34 | r142);
    L37:
        r13.entities.add(r112);
        if (r02 == null) goto L40;
        BigInteger r03 = BigInteger.valueOf(5000);
        r1.lcm = r1.lcm.multiply(r03).divide(r1.lcm.gcd(r03));
    L40:
        r14 = r122;
        r11 = r232;
        r15 = r25;
        r12 = r24 + 1;
        r0 = r222;
        goto L21
    L35:
        r34 = 4;
        goto L36
    L31:
        if (MessageObject.isVideoStickerDocument(r112.document) == false) goto L37;
    L25:
        r232 = r11;
        r24 = r12;
    L41:
        r22 = r0;
        TextPaintView r123 = r14;
        CharSequence r252 = r15;
        r13.entities.isEmpty();
    L43:
        r13.text = r252.toString();
        r13.subType = (byte) r123.getType();
        r13.color = r123.getSwatch().color;
        r13.fontSize = r123.getTextSize();
        r13.textTypeface = r123.getTypeface();
        r13.textAlign = r123.getAlign();
        boolean r124 = true;
    L76:
        r2.add(r13);
        float r04 = r8.getScaleX();
        float r35 = r8.getScaleY();
        float r62 = r8.getX();
        float r113 = r8.getY();
        r13.viewWidth = r8.getWidth();
        r13.viewHeight = r8.getHeight();
        r13.width = (r8.getWidth() * r04) / r1.entitiesView.getMeasuredWidth();
        r13.height = (r8.getHeight() * r35) / r1.entitiesView.getMeasuredHeight();
        r13.x = (((r8.getWidth() * (1.0f - r04)) / 2.0f) + r62) / r1.entitiesView.getMeasuredWidth();
        r13.y = (((r8.getHeight() * (1.0f - r35)) / 2.0f) + r113) / r1.entitiesView.getMeasuredHeight();
        r13.rotation = (float) ((-r8.getRotation()) * 0.017453292519943295d);
        r13.textViewX = (r62 + (r8.getWidth() / 2.0f)) / r1.entitiesView.getMeasuredWidth();
        r13.textViewY = (r113 + (r8.getHeight() / 2.0f)) / r1.entitiesView.getMeasuredHeight();
        r13.textViewWidth = r13.viewWidth / r1.entitiesView.getMeasuredWidth();
        r13.textViewHeight = r13.viewHeight / r1.entitiesView.getMeasuredHeight();
        r13.scale = r04;
        if (r28[0] != null) goto L79;
        r28[0] = Bitmap.createBitmap(r4.getWidth(), r4.getHeight(), r4.getConfig());
        Canvas r05 = new Canvas(r28[0]);
        r05.drawBitmap(r4, 0.0f, 0.0f, null);
        r22 = r05;
    L79:
        boolean r36 = r124;
    L82:
        Canvas r63 = new Canvas(r4);
        int r114 = 0;
    L84:
        if (r114 >= 2) goto L107;
        if (r114 != 0) goto L87;
        Canvas r132 = r63;
    L88:
        if (r132 == null) goto L106;
        if (r114 != 0) goto L92;
        if (r36 == true) goto L106;
    L92:
        r132.save();
        r132.translate(r10.x, r10.y);
        r132.scale(r8.getScaleX(), r8.getScaleY());
        r132.rotate(r8.getRotation());
        r132.translate((-r9.getWidth()) / r17, (-r9.getHeight()) / r17);
        if ((r8 instanceof TextPaintView) == true) goto L95;
    L104:
        r8.draw(r132);
    L105:
        r132.restore();
        goto L106
    L95:
        if (r8.getHeight() <= 0) goto L104;
        if (r8.getWidth() <= 0) goto L104;
        Bitmap r143 = Bitmaps.createBitmap(r8.getWidth(), r8.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas r06 = new Canvas(r143);
        r8.draw(r06);
        r132.drawBitmap(r143, null, new Rect(0, 0, r143.getWidth(), r143.getHeight()), null);
        r06.setBitmap(null);     // Catch: Exception -> L101
    L103:
        r143.recycle();
    L101:
        e = move-exception;
        FileLog.e(e);
    L106:
        r114 = r114 + 1;
        goto L84
    L87:
        r132 = r22;
        goto L88
    L107:
        r23 = 0;
        r0 = r22;
        goto L108
    L42:
        r22 = r0;
        r123 = r14;
        r252 = r15;
        goto L43
    L44:
        r22 = r0;
        long r18 = 5000;
        if ((r9 instanceof StickerView) == false) goto L64;
        r13.type = 0;
        StickerView r07 = (StickerView) r9;
        Size r37 = r07.getBaseSize();
        r13.width = r37.width;
        r13.height = r37.height;
        r13.document = r07.getSticker();
        r13.parentObject = r07.getParentObject();
        TLRPC.Document r38 = r07.getSticker();
        r13.text = FileLoader.getInstance(UserConfig.selectedAccount).getPathToAttach(r38, true).getAbsolutePath();
        if (MessageObject.isAnimatedStickerDocument(r38, true) == false) goto L49;
    L50:
        boolean r39 = MessageObject.isAnimatedStickerDocument(r38, true);
        byte r64 = r13.subType;
        if (r39 == false) goto L53;
        r16 = 1;
    L53:
        r13.subType = (byte) (r64 | r16);
        if (r39 == false) goto L57;
        r18 = r07.getDuration();
    L57:
        if (r18 == 0) goto L60;
        BigInteger r310 = BigInteger.valueOf(r18);
        r1.lcm = r1.lcm.multiply(r310).divide(r1.lcm.gcd(r310));
    L60:
        if (r07.isMirrored() == false) goto L62;
        r13.subType = (byte) (r13.subType | 2);
    L62:
        r124 = true;
        goto L76
    L49:
        if (MessageObject.isVideoStickerDocument(r38) == false) goto L60;
    L64:
        if ((r9 instanceof PhotoView) == false) goto L80;
        PhotoView r08 = (PhotoView) r9;
        r13.type = 2;
        Size r311 = r08.getBaseSize();
        r13.width = r311.width;
        r13.height = r311.height;
        r13.text = r08.getPath(r1.currentAccount);
        if (r08.isMirrored() == false) goto L69;
        r13.subType = (byte) (r13.subType | 2);
    L69:
        if (r08.hasSegmentedImage() == true) goto L71;
    L75:
        r124 = false;
        goto L76
    L71:
        if (r08.isSegmented() == false) goto L75;
        File r09 = r08.saveSegmentedImage(r1.currentAccount);
        if (r09 == null) goto L75;
        r13.subType = (byte) (r13.subType | 16);
        r13.segmentedPath = r09.getPath();
        goto L75
    L81:
        r22 = r0;
        r17 = 2.0f;
        r36 = false;
        goto L82
    }

    public View getCancelView() {
        return this.cancelButton;
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView.EntityViewDelegate
    public int[] getCenterLocation(EntityView r1) {
        return getCenterLocationInWindow(r1);
    }

    @Override // org.telegram.ui.Components.SizeNotifierFrameLayoutPhoto, org.telegram.ui.Components.SizeNotifierFrameLayout, org.telegram.ui.ActionBar.Theme.Colorable
    public /* bridge */ /* synthetic */ int[] getColorKeys() {
        return super.getColorKeys();
    }

    public float getCropRotation() {
        MediaController.CropState r1 = this.currentCropState;
        if (r1 != null) goto L5;
        return 0.0f;
    L5:
        return r1.cropRotate + r1.transformRotation;
    }

    public View getDoneView() {
        return this.doneButton;
    }

    public int getEmojiPadding(boolean r3) {
        if (this.translateBottomPanelAfterResize == false) goto L7;
        if (r3 == false) goto L7;
        bottomPanelTranslationY(0.0f, 1.0f);
    L7:
        if (this.keyboardVisible == false) goto L14;
        if (this.translateBottomPanelAfterResize == false) goto L14;
        if (r3 == true) goto L14;
        return 0;
    L14:
        return this.emojiPadding;
    }

    public long getLcm() {
        return this.lcm.longValue();
    }

    public List<TLRPC.InputDocument> getMasks() {
        int r0 = this.entitiesView.getChildCount();
        ArrayList r1 = null;
        int r3 = 0;
    L3:
        if (r3 >= r0) goto L37;
        View r4 = this.entitiesView.getChildAt(r3);
        if ((r4 instanceof StickerView) == false) goto L14;
        TLRPC.Document r42 = ((StickerView) r4).getSticker();
        if (r1 != null) goto L9;
        r1 = new ArrayList();
    L9:
        TLRPC.TL_inputDocument r5 = new TLRPC.TL_inputDocument();
        r5.id = r42.id;
        r5.access_hash = r42.access_hash;
        byte[] r43 = r42.file_reference;
        r5.file_reference = r43;
        if (r43 != null) goto L12;
        r5.file_reference = new byte[0];
    L12:
        r1.add(r5);
    L36:
        r3 = r3 + 1;
        goto L3
    L14:
        if ((r4 instanceof TextPaintView) == false) goto L36;
        CharSequence r44 = ((TextPaintView) r4).getText();
        if ((r44 instanceof Spanned) == false) goto L36;
        AnimatedEmojiSpan[] r45 = (AnimatedEmojiSpan[]) ((Spanned) r44).getSpans(0, r44.length(), AnimatedEmojiSpan.class);
        if (r45 == null) goto L36;
        int r52 = 0;
    L21:
        if (r52 >= r45.length) goto L36;
        AnimatedEmojiSpan r6 = r45[r52];
        if (r6 == null) goto L35;
        TLRPC.Document r7 = r6.document;
        if (r7 != null) goto L28;
        r7 = AnimatedEmojiDrawable.findDocument(this.currentAccount, r6.getDocumentId());
    L28:
        if (r7 == null) goto L35;
        if (r1 != null) goto L31;
        r1 = new ArrayList();
    L31:
        TLRPC.TL_inputDocument r62 = new TLRPC.TL_inputDocument();
        r62.id = r7.id;
        r62.access_hash = r7.access_hash;
        byte[] r72 = r7.file_reference;
        r62.file_reference = r72;
        if (r72 != null) goto L34;
        r62.file_reference = new byte[0];
    L34:
        r1.add(r62);
    L35:
        r52 = r52 + 1;
        goto L21
    L37:
        return r1;
    }

    public float getOffsetTranslationY() {
        return this.offsetTranslationY;
    }

    public int getPKeyboardHeight() {
        return 0;
    }

    public RenderView getRenderView() {
        return this.renderView;
    }

    public float getSelectedEntityBottom() {
        EntityView r0 = this.currentEntityView;
        if (r0 == null) goto L5;
        r0.getLocationInWindow(new int[2]);
        return r1[1] + (this.currentEntityView.getHeight() * this.entitiesView.getScaleY());
    L5:
        return getY() + this.entitiesView.getMeasuredHeight();
    }

    public ThanosEffect getThanosEffect() {
        if (ThanosEffect.supports() == true) goto L7;
        return null;
    L7:
        if (this.thanosEffect != null) goto L10;
        ThanosEffect r0 = new ThanosEffect(getContext(), new LPhotoPaintView$$ExternalSyntheticLambda20(this));
        this.thanosEffect = r0;
        addView(r0);
    L10:
        return this.thanosEffect;
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView.EntityViewDelegate
    public void getTransformedTouch(float r10, float r11, float[] r12) {
        Point r0 = AndroidUtilities.displaySize;
        float r112 = r11 - (r0.y / 2.0f);
        double r02 = r10 - (r0.x / 2.0f);
        double r9 = (float) Math.toRadians(-this.entitiesView.getRotation());
        double r5 = r112;
        r12[0] = ((float) ((Math.cos(r9) * r02) - (Math.sin(r9) * r5))) + (AndroidUtilities.displaySize.x / 2.0f);
        r12[1] = ((float) ((r02 * Math.sin(r9)) + (r5 * Math.cos(r9)))) + (AndroidUtilities.displaySize.y / 2.0f);
    }

    @Override // org.telegram.ui.Components.IPhotoPaintView
    public /* bridge */ /* synthetic */ View getView() {
        return super.getView();
    }

    public boolean hasChanges() {
        return this.undoStore.canUndo();
    }

    public void init() {
        this.textOptionsView.setTypeface(PersistColorPalette.getInstance(this.currentAccount).getCurrentTypeface());
        this.entitiesView.setVisibility(0);
        this.renderView.setVisibility(0);
        this.renderInputView.setVisibility(0);
    }

    public boolean isCurrentText() {
        return this.currentEntityView instanceof TextPaintView;
    }

    public void maybeShowDismissalAlert(PhotoViewer r3, Activity r4, Runnable r5) {
        if (this.isColorListShown == false) goto L7;
        showColorList(false);
        return;
    L7:
        if (this.emojiViewVisible == false) goto L11;
        hideEmojiPopup(true);
        return;
    L11:
        if (this.editingText == false) goto L17;
        if (this.currentEntityView == null) goto L17;
        selectEntity(null);
        return;
    L17:
        if (hasChanges() == false) goto L22;
        if (r4 != null) goto L20;
        return;
    L20:
        AlertDialog.Builder r0 = new AlertDialog.Builder(r4, this.resourcesProvider);
        r0.setMessage(LocaleController.getString(R.string.PhotoEditorDiscardAlert));
        r0.setTitle(LocaleController.getString(R.string.DiscardChanges));
        r0.setPositiveButton(LocaleController.getString(R.string.PassportDiscard), new LPhotoPaintView$$ExternalSyntheticLambda19(r5));
        r0.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r3.showAlertDialog(r0);
        return;
    L22:
        r5.run();
    }

    @Override // org.telegram.ui.Components.Paint.Views.PaintToolsView.Delegate
    public void onAddButtonPressed(View r7) {
        showPopup(new LPhotoPaintView$$ExternalSyntheticLambda46(this), this, 53, 0, getHeight());
    }

    public void onAnimationStateChanged(boolean r6) {
        if (this.tabsSelectedIndex != 0) goto L17;
        PaintWeightChooserView r0 = this.weightChooserView;
        int r1 = 0;
        if (r6 == false) goto L7;
        int r3 = 2;
    L8:
        r0.setLayerType(r3, null);
        FrameLayout r02 = this.bottomLayout;
        if (r6 == false) goto L11;
        int r32 = 2;
    L12:
        r02.setLayerType(r32, null);
        FrameLayout r5 = this.topLayout;
        if (r6 == false) goto L15;
        r1 = 2;
    L15:
        r5.setLayerType(r1, null);
        return;
    L11:
        r32 = 0;
        goto L12
    L7:
        r3 = 0;
        goto L8
    }

    @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        this.destroyed = false;
        super.onAttachedToWindow();
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.customTypefacesLoaded);
    }

    public boolean onBackPressed() {
        if (this.isColorListShown == false) goto L7;
        showColorList(false);
        return true;
    L7:
        if (this.emojiViewVisible == false) goto L11;
        hideEmojiPopup(true);
        return true;
    L11:
        if (this.editingText == false) goto L14;
        selectEntity(null);
        return true;
    L14:
        return false;
    }

    @Override // org.telegram.ui.Components.Paint.Views.PaintToolsView.Delegate
    public void onBrushSelected(Brush r4) {
        if ((r4 instanceof Brush.Blurer) == false) goto L5;
    L8:
        this.weightChooserView.setMinMax(0.4f, 1.75f);
    L9:
        this.weightChooserView.setDrawCenter(!(r4 instanceof Brush.Shape));
        if ((this.renderView.getCurrentBrush() instanceof Brush.Shape) == false) goto L12;
        this.ignoreToolChangeAnimationOnce = true;
    L12:
        this.renderView.setBrush(r4);
        Swatch r42 = this.colorSwatch;
        int r0 = r42.color;
        r42.color = PersistColorPalette.getInstance(this.currentAccount).getCurrentColor();
        this.colorSwatch.brushWeight = this.weightDefaultValueOverride.get();
        setCurrentSwatch(this.colorSwatch, true, Integer.valueOf(r0));
        this.renderInputView.invalidate();
        return;
    L5:
        if ((r4 instanceof Brush.Eraser) == true) goto L8;
        this.weightChooserView.setMinMax(0.05f, 1.0f);
        goto L9
    }

    public void onCleanupEntities() {
        this.entitiesView.removeAllViews();
    }

    @Override // org.telegram.ui.Components.Paint.Views.PaintToolsView.Delegate, org.telegram.ui.Components.Paint.Views.PaintTextOptionsView.Delegate
    public void onColorPickerSelected() {
        showColorList(true);
    }

    @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        this.destroyed = true;
        super.onDetachedFromWindow();
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.customTypefacesLoaded);
    }

    public void onEmojiButtonClick() {
        boolean r0 = this.emojiViewVisible;
        if (r0 == true) goto L5;
    L7:
        if (r0 == false) goto L9;
        onEmojiViewCloseByClick();
    L9:
        showEmojiPopup(1 ^ (this.emojiViewVisible ? 1 : 0));
        if (r0 == false) goto L15;
        EntityView r3 = this.currentEntityView;
        if ((r3 instanceof TextPaintView) == false) goto L16;
        AndroidUtilities.showKeyboard(((TextPaintView) r3).getEditText());
        return;
    L16:
        return;
    L15:
        return;
    L5:
        if ((this.currentEntityView instanceof TextPaintView) == false) goto L7;
        this.bottomPanelIgnoreOnce = true;
        goto L7
    }

    public abstract void onEmojiViewCloseByClick();

    @Override // org.telegram.ui.Components.Paint.Views.EntityView.EntityViewDelegate
    public boolean onEntityLongClicked(EntityView r1) {
        showMenuForEntity(r1);
        return true;
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView.EntityViewDelegate
    public boolean onEntitySelected(EntityView r1) {
        return selectEntity(r1);
    }

    @Override // org.telegram.ui.Components.Paint.Views.PaintToolsView.Delegate
    public PersistColorPalette onGetPalette() {
        return PersistColorPalette.getInstance(this.currentAccount);
    }

    @Override // org.telegram.ui.Components.SizeNotifierFrameLayoutPhoto, org.telegram.ui.Components.SizeNotifierFrameLayout, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r3, int r4, int r5, int r6, int r7) {
        super.onLayout(r3, r4, r5, r6, r7);
        int r62 = r6 - r4;
        int r72 = r7 - r5;
        int r42 = 0;
        if (this.emojiView == null) goto L10;
        if (AndroidUtilities.isTablet() == false) goto L7;
        int r32 = r72 - this.emojiView.getMeasuredHeight();
    L8:
        EmojiView r52 = this.emojiView;
        r52.layout(0, r32, r52.getMeasuredWidth(), this.emojiView.getMeasuredHeight() + r32);
        goto L10
    L7:
        r32 = (measureKeyboardHeight() + r72) - this.emojiView.getMeasuredHeight();
    L10:
        if (this.inBubbleMode == true) goto L12;
        r42 = AndroidUtilities.statusBarHeight;
    L12:
        int r33 = ActionBar.getCurrentActionBarHeight() + r42;
        int r53 = (int) Math.ceil((r62 - this.renderView.getMeasuredWidth()) / 2.0f);
        int r73 = ((((((r72 - r33) - AndroidUtilities.dp(48.0f)) - this.renderView.getMeasuredHeight()) / 2) + AndroidUtilities.dp(8.0f)) + r42) + ((getAdditionalTop() - getAdditionalBottom()) / 2);
        RenderView r34 = this.renderView;
        r34.layout(r53, r73, r34.getMeasuredWidth() + r53, this.renderView.getMeasuredHeight() + r73);
        View r35 = this.renderInputView;
        r35.layout(r53, r73, r35.getMeasuredWidth() + r53, this.renderInputView.getMeasuredHeight() + r73);
        int r36 = ((this.renderView.getMeasuredWidth() - this.entitiesView.getMeasuredWidth()) / 2) + r53;
        int r43 = ((this.renderView.getMeasuredHeight() - this.entitiesView.getMeasuredHeight()) / 2) + r73;
        EntitiesContainerView r63 = this.entitiesView;
        r63.layout(r36, r43, r63.getMeasuredWidth() + r36, this.entitiesView.getMeasuredHeight() + r43);
        ThanosEffect r64 = this.thanosEffect;
        if (r64 == null) goto L15;
        r64.layout(r36, r43, this.entitiesView.getMeasuredWidth() + r36, this.entitiesView.getMeasuredHeight() + r43);
    L15:
        FrameLayout r37 = this.selectionContainerView;
        r37.layout(r53, r73, r37.getMeasuredWidth() + r53, this.selectionContainerView.getMeasuredHeight() + r73);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r11, int r12) {
        this.ignoreLayout = true;
        int r1 = View.MeasureSpec.getSize(r11);
        int r2 = View.MeasureSpec.getSize(r12);
        setMeasuredDimension(r1, r2);
        int r3 = (((AndroidUtilities.displaySize.y - ActionBar.getCurrentActionBarHeight()) - getAdditionalTop()) - getAdditionalBottom()) - AndroidUtilities.dp(48.0f);
        Bitmap r5 = this.bitmapToEdit;
        if (r5 == null) goto L5;
        float r4 = r5.getWidth();
        float r52 = this.bitmapToEdit.getHeight();
    L6:
        float r13 = r1;
        float r6 = (float) Math.floor((r13 * r52) / r4);
        float r32 = r3;
        if (r6 <= r32) goto L9;
        r13 = (float) Math.floor((r4 * r32) / r52);
        r6 = r32;
    L9:
        int r42 = (int) r13;
        int r62 = (int) r6;
        this.renderView.measure(View.MeasureSpec.makeMeasureSpec(r42, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r62, TLObject.FLAG_30));
        this.renderInputView.measure(View.MeasureSpec.makeMeasureSpec(r42, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r62, TLObject.FLAG_30));
        float r14 = r13 / this.paintingSize.width;
        this.baseScale = r14;
        this.entitiesView.setScaleX(r14);
        this.entitiesView.setScaleY(this.baseScale);
        this.entitiesView.measure(View.MeasureSpec.makeMeasureSpec((int) this.paintingSize.width, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec((int) this.paintingSize.height, TLObject.FLAG_30));
        ThanosEffect r15 = this.thanosEffect;
        if (r15 == null) goto L12;
        r15.measure(View.MeasureSpec.makeMeasureSpec((int) this.paintingSize.width, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec((int) this.paintingSize.height, TLObject.FLAG_30));
        this.thanosEffect.setScaleX(this.baseScale);
        this.thanosEffect.setScaleY(this.baseScale);
    L12:
        updateEntitiesSelections();
        this.selectionContainerView.measure(View.MeasureSpec.makeMeasureSpec(r42, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r62, TLObject.FLAG_30));
        measureChild(this.bottomLayout, r11, r12);
        measureChild(this.weightChooserView, r11, r12);
        measureChild(this.pipetteContainerLayout, r11, r12);
        measureChild(this.overlayLayout, r11, View.MeasureSpec.makeMeasureSpec(r2 - Math.max(getPKeyboardHeight(), this.emojiPadding), TLObject.FLAG_30));
        FrameLayout r16 = this.topLayout;
        r16.setPadding(r16.getPaddingLeft(), AndroidUtilities.dp(12.0f) + AndroidUtilities.statusBarHeight, this.topLayout.getPaddingRight(), this.topLayout.getPaddingBottom());
        measureChild(this.topLayout, r11, r12);
        this.ignoreLayout = false;
        if (this.waitingForKeyboardOpen == true) goto L22;
        if (AndroidUtilities.dp(20.0f) < 0) goto L22;
        if (this.emojiViewVisible == true) goto L22;
        if (this.isAnimatePopupClosing == true) goto L22;
        this.ignoreLayout = true;
        hideEmojiView();
        this.ignoreLayout = false;
    L22:
        if (AndroidUtilities.dp(20.0f) >= 0) goto L25;
        hideEmojiView();
    L25:
        EmojiView r0 = this.emojiView;
        if (r0 == null) goto L29;
        measureChild(r0, r11, r12);
        return;
    L29:
        return;
    L5:
        r52 = (r2 - ActionBar.getCurrentActionBarHeight()) - AndroidUtilities.dp(48.0f);
        r4 = r1;
        goto L6
    }

    @Override // org.telegram.ui.Components.Paint.Views.PaintTextOptionsView.Delegate
    public void onNewTextSelected() {
        if (this.keyboardVisible == false) goto L5;
    L9:
        onEmojiButtonClick();
        return;
    L5:
        if (this.emojiViewVisible == true) goto L9;
        createText(true);
    }

    public abstract void onOpenCloseStickersAlert(boolean r1);

    @Override // org.telegram.ui.Components.SizeNotifierFrameLayout
    public void onResume() {
        this.renderView.redraw();
    }

    @Override // org.telegram.ui.Components.SizeNotifierFrameLayout.SizeNotifierFrameLayoutDelegate
    public void onSizeChanged(int r5, boolean r6) {
        if (r5 <= AndroidUtilities.dp(50.0f)) goto L14;
        if (this.keyboardVisible == false) goto L14;
        if (AndroidUtilities.isInMultiwindow == true) goto L14;
        if (AndroidUtilities.isTablet() == true) goto L14;
        if (r6 == false) goto L12;
        this.keyboardHeightLand = r5;
        MessagesController.getGlobalEmojiSettings().edit().putInt("kbd_height_land3", this.keyboardHeightLand).apply();
        goto L14
    L12:
        this.keyboardHeight = r5;
        MessagesController.getGlobalEmojiSettings().edit().putInt("kbd_height", this.keyboardHeight).apply();
    L14:
        if (this.emojiViewVisible == false) goto L24;
        if (r6 == false) goto L17;
        int r0 = this.keyboardHeightLand;
    L18:
        FrameLayout.LayoutParams r1 = (FrameLayout.LayoutParams) this.emojiView.getLayoutParams();
        int r2 = r1.width;
        int r3 = AndroidUtilities.displaySize.x;
        if (r2 == r3) goto L21;
    L22:
        r1.width = r3;
        r1.height = r0;
        this.emojiView.setLayoutParams(r1);
        int r02 = r1.height;
        this.emojiWasPadding = r02;
        this.emojiPadding = r02;
        requestLayout();
        updateKeyboard();
        onWindowSizeChanged();
        goto L24
    L21:
        if (r1.height == r0) goto L24;
    L17:
        r0 = this.keyboardHeight;
    L24:
        if (this.lastSizeChangeValue1 == r5) goto L26;
    L29:
        this.lastSizeChangeValue1 = r5;
        this.lastSizeChangeValue2 = r6;
        boolean r62 = this.keyboardVisible;
        EntityView r03 = this.currentEntityView;
        if ((r03 instanceof TextPaintView) == true) goto L32;
        this.keyboardVisible = false;
    L39:
        if (this.keyboardVisible == false) goto L44;
        if (this.emojiViewVisible == false) goto L44;
        showEmojiPopup(0);
    L44:
        if (this.emojiPadding == 0) goto L51;
        boolean r52 = this.keyboardVisible;
        if (r52 == true) goto L51;
        if (r52 == r62) goto L51;
        if (this.emojiViewVisible == true) goto L51;
        this.emojiPadding = 0;
        requestLayout();
        updateKeyboard();
    L51:
        if (r62 == false) goto L60;
        if (this.keyboardVisible == true) goto L60;
        if (this.emojiPadding <= 0) goto L60;
        if (this.translateBottomPanelAfterResize == false) goto L60;
        this.translateBottomPanelAfterResize = false;
        bottomPanelTranslationY(0.0f, 1.0f);
        this.weightChooserView.stopPanTransition();
    L60:
        if (this.keyboardVisible == true) goto L62;
    L64:
        onWindowSizeChanged();
        return;
    L62:
        if (this.waitingForKeyboardOpen == false) goto L64;
        this.waitingForKeyboardOpen = false;
        AndroidUtilities.cancelRunOnUIThread(this.openKeyboardRunnable);
        goto L64
    L32:
        if (((TextPaintView) r03).getEditText().isFocused() == false) goto L35;
        if (r5 <= 0) goto L35;
        boolean r53 = true;
    L36:
        this.keyboardVisible = r53;
    L35:
        r53 = false;
        goto L36
    L26:
        if (this.lastSizeChangeValue2 != r6) goto L29;
        onWindowSizeChanged();
    }

    public void onSwitchSegmentedAnimation(PhotoView r13) {
        if (r13 != null) goto L4;
        return;
    L4:
        ThanosEffect r12 = getThanosEffect();
        if (r12 != null) goto L8;
        r13.onSwitchSegmentedAnimationStarted(false);
        return;
    L8:
        Bitmap r1 = r13.getSegmentedOutBitmap();
        if (r1 != null) goto L12;
        r13.onSwitchSegmentedAnimationStarted(false);
        return;
    L12:
        Matrix r0 = new Matrix();
        float r2 = r13.getWidth();
        float r3 = r13.getHeight();
        float r5 = 0.0f;
        if (r13.getRotation() == 0.0f) goto L15;
        float r4 = r1.getWidth();
        float r52 = r1.getHeight();
        float r7 = r4 / 2.0f;
        float r8 = r52 / 2.0f;
        float r72 = (float) Math.sqrt((r7 * r7) + (r8 * r8));
        float r82 = r72 * 2.0f;
        int r9 = (int) r82;
        Bitmap r92 = Bitmap.createBitmap(r9, r9, Bitmap.Config.ARGB_8888);
        Canvas r10 = new Canvas(r92);
        r10.save();
        r10.rotate(r13.getRotation(), r72, r72);
        r10.drawBitmap(r1, (r82 - r4) / 2.0f, (r82 - r52) / 2.0f, null);
        r1.recycle();
        float r14 = r2 / 2.0f;
        float r42 = r3 / 2.0f;
        float r15 = ((float) Math.sqrt((r14 * r14) + (r42 * r42))) * 2.0f;
        r5 = (-(r15 - r2)) / 2.0f;
        float r22 = (-(r15 - r3)) / 2.0f;
        r3 = r15;
        float r43 = r22;
        r2 = r3;
        r1 = r92;
    L16:
        r0.postScale(r2, r3);
        r0.postScale(r13.getScaleX(), r13.getScaleY(), r2 / 2.0f, r3 / 2.0f);
        r0.postTranslate(r13.getX() + r5, r13.getY() + r43);
        r12.animate(r0, r1, new LPhotoPaintView$$ExternalSyntheticLambda44(r13), new LPhotoPaintView$$ExternalSyntheticLambda45());
        return;
    L15:
        r43 = 0.0f;
        goto L16
    }

    public abstract void onTextAdd();

    @Override // org.telegram.ui.Components.Paint.Views.PaintTextOptionsView.Delegate
    public void onTextAlignmentSelected(int r3) {
        EntityView r0 = this.currentEntityView;
        if ((r0 instanceof TextPaintView) == false) goto L6;
        setTextAlignment((TextPaintView) r0, r3);
        PersistColorPalette.getInstance(this.currentAccount).setCurrentAlignment(r3);
        return;
    }

    @Override // org.telegram.ui.Components.Paint.Views.PaintTextOptionsView.Delegate
    public void onTextOutlineSelected(View r1) {
        setTextType((this.selectedTextType + 1) % 5);
    }

    public boolean onTouch(MotionEvent r13) {
        if (this.currentEntityView == null) goto L5;
        selectEntity(null);
    L5:
        float r0 = ((r13.getX() - this.renderView.getTranslationX()) - (getMeasuredWidth() / 2.0f)) / this.renderView.getScaleX();
        float r1 = ((((r13.getY() - this.renderView.getTranslationY()) - (getMeasuredHeight() / 2.0f)) + AndroidUtilities.dp(32.0f)) - ((getAdditionalTop() - getAdditionalBottom()) / 2.0f)) / this.renderView.getScaleY();
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

    @Override // org.telegram.ui.Components.Paint.Views.PaintTextOptionsView.Delegate
    public void onTypefaceButtonClicked() {
        showTypefaceMenu(true);
    }

    public void onTypefaceSelected(PaintTypeface r3) {
        PersistColorPalette.getInstance(this.currentAccount).setCurrentTypeface(r3.getKey());
        EntityView r2 = this.currentEntityView;
        if ((r2 instanceof TextPaintView) == false) goto L6;
        ((TextPaintView) r2).setTypeface(r3);
        return;
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.ignoreLayout == false) goto L5;
        return;
    L5:
        super.requestLayout();
    }

    public void setBlurredBackgroundDrawableForTools(BlurredBackgroundDrawable r2) {
        this.blurredBackgroundDrawableForTools = r2.setPadding(AndroidUtilities.dp(4.0f));
    }

    public void setDrawShadow(boolean r1) {
        this.drawShadow = r1;
        invalidate();
        setClipChildren(!r1);
    }

    @Override // org.telegram.ui.Components.IPhotoPaintView
    public void setOffsetTranslationX(float r2) {
        if (this.tabsSelectedIndex != 0) goto L6;
        this.weightChooserView.setTranslationX(r2);
        return;
    }

    public void setOffsetTranslationY(float r1, float r2, int r3, boolean r4) {
        this.offsetTranslationY = r1;
        if (r4 == true) goto L6;
        this.topLayout.setTranslationY(-r1);
        this.bottomLayout.setTranslationY(r1);
        return;
    L6:
        setTranslationY(0.0f);
    }

    public void setOnDoneButtonClickedListener(Runnable r1) {
        this.onDoneButtonClickedListener = r1;
    }

    public void setTransform(float r18, float r19, float r20, float r21, float r22) {
        this.scale = r18;
        this.imageWidth = r21;
        this.imageHeight = r22;
        this.inputTransformX = r19;
        this.inputTransformY = r20;
        this.transformX = r19;
        float r3 = r20 + this.panTranslationY;
        this.transformY = r3;
        int r6 = 0;
    L4:
        if (r6 >= 4) goto L36;
        if (r6 != 0) goto L8;
        View r7 = this.entitiesView;
    L14:
        MediaController.CropState r8 = this.currentCropState;
        if (r8 == null) goto L27;
        float r82 = r8.cropScale * 1.0f;
        int r10 = r7.getMeasuredWidth();
        int r11 = r7.getMeasuredHeight();
        if (r10 == 0) goto L26;
        if (r11 == 0) goto L43;
        MediaController.CropState r12 = this.currentCropState;
        int r13 = r12.transformRotation;
        if (r13 != 90) goto L23;
    L24:
        r11 = r10;
        r10 = r11;
    L25:
        float r102 = r10;
        float r122 = Math.max(r21 / ((int) (r12.cropPw * r102)), r22 / ((int) (r12.cropPh * r11)));
        float r83 = r82 * r122;
        MediaController.CropState r14 = this.currentCropState;
        float r15 = ((r14.cropPx * r102) * r18) * r122;
        float r103 = r14.cropScale;
        float r152 = (r15 * r103) + r19;
        float r202 = 1.0f;
        float r9 = ((((r14.cropPy * r11) * r18) * r122) * r103) + r3;
        float r104 = r14.cropRotate + r13;
    L32:
        float r92 = r9 + ((-this.emojiPadding) / 2.0f);
        float r84 = r83 * r18;
        if (Float.isNaN(r84) == false) goto L35;
        r84 = r202;
    L35:
        r7.setScaleX(r84);
        r7.setScaleY(r84);
        r7.setTranslationX(r152);
        r7.setTranslationY(r92);
        r7.setRotation(r104);
        r7.invalidate();
        r6 = r6 + 1;
        goto L4
    L23:
        if (r13 != 270) goto L25;
    L43:
        return;
    L26:
        return;
    L27:
        r202 = 1.0f;
        if (r6 != 0) goto L30;
        r83 = this.baseScale * 1.0f;
    L31:
        r104 = 0.0f;
        r152 = r19;
        r9 = r3;
        goto L32
    L30:
        r83 = 1.0f;
        goto L31
    L8:
        if (r6 != 1) goto L11;
        r7 = this.selectionContainerView;
        goto L14
    L11:
        if (r6 != 2) goto L13;
        r7 = this.renderView;
        goto L14
    L13:
        r7 = this.renderInputView;
        goto L14
    L36:
        updateEntitiesSelections();
        invalidate();
    }

    public void shutdown() {
        this.renderView.shutdown();
        this.entitiesView.setVisibility(8);
        this.selectionContainerView.setVisibility(8);
        this.queue.postRunnable(new LPhotoPaintView$$ExternalSyntheticLambda24());
    }

    public void translateY(float r8) {
        if (Math.abs(r8 - this.pany) <= 0.1f) goto L6;
        this.pany = r8;
        setTransform(this.scale, this.inputTransformX, this.inputTransformY, this.imageWidth, this.imageHeight);
        return;
    }

    @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, org.telegram.ui.ActionBar.Theme.Colorable
    public void updateColors() {
        this.toolsPaint.setColor(-15132391);
    }

    public void updateEntitiesSelections() {
        int r0 = 0;
    L4:
        if (r0 >= this.entitiesView.getChildCount()) goto L13;
        View r1 = this.entitiesView.getChildAt(r0);
        if (r1 != this.currentEntityView) goto L8;
    L11:
        ((EntityView) r1).updateSelectionView();
    L12:
        r0 = r0 + 1;
        goto L4
    L8:
        if ((r1 instanceof EntityView) == false) goto L12;
        if (((EntityView) r1).isSelectedProgress() == false) goto L12;
    }

    public abstract void updateKeyboard();

    public void updatePlusEmojiKeyboardButton() {
        PaintTextOptionsView r0 = this.textOptionsView;
        if (r0 != null) goto L5;
    L11:
        TextView r2 = this.undoAllButton;
        if (this.keyboardVisible == false) goto L14;
    L16:
        boolean r3 = false;
    L17:
        AndroidUtilities.updateViewShow(r2, r3, false, 1.0f, true, null);
        ImageView r9 = this.undoButton;
        if (this.keyboardVisible == false) goto L20;
    L22:
        boolean r10 = false;
    L23:
        AndroidUtilities.updateViewShow(r9, r10, false, 1.0f, true, null);
        TextView r22 = this.doneTextButton;
        if (this.keyboardVisible == false) goto L26;
    L29:
        boolean r32 = true;
    L30:
        AndroidUtilities.updateViewShow(r22, r32, false, 1.0f, true, null);
        TextView r92 = this.cancelTextButton;
        if (this.keyboardVisible == false) goto L33;
    L36:
        boolean r102 = true;
    L37:
        AndroidUtilities.updateViewShow(r92, r102, false, 1.0f, true, null);
        return;
    L33:
        if (this.emojiViewVisible == true) goto L36;
        r102 = false;
        goto L37
    L26:
        if (this.emojiViewVisible == true) goto L29;
        r32 = false;
        goto L30
    L20:
        if (this.emojiViewVisible == true) goto L22;
        r10 = true;
        goto L23
    L14:
        if (this.emojiViewVisible == true) goto L16;
        r3 = true;
        goto L17
    L5:
        if (this.keyboardVisible == false) goto L8;
        r0.animatePlusToIcon(R.drawable.input_smile);
        goto L11
    L8:
        if (this.emojiViewVisible == false) goto L10;
        r0.animatePlusToIcon(R.drawable.input_keyboard);
        goto L11
    L10:
        r0.animatePlusToIcon(R.drawable.msg_add);
        goto L11
    }

    public void updateZoom(boolean r3) {
        boolean r0 = !r3;
        if (this.zoomOutVisible == r0) goto L10;
        this.zoomOutVisible = r0;
        this.zoomOutButton.animate().cancel();
        ViewPropertyAnimator r2 = this.zoomOutButton.animate();
        if (r3 == false) goto L7;
        float r32 = 0.0f;
    L8:
        r2.alpha(r32).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).setDuration(240).start();
        return;
    L7:
        r32 = 1.0f;
        goto L8
    }
}
