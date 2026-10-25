package org.telegram.ui.Stories.recorder;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Pair;
import android.util.Property;
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
import com.google.zxing.common.detector.MathUtils;
import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.Bitmaps;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.VideoEditedInfo;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.AdjustPanLayoutHelper;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.BubbleActivity;
import org.telegram.ui.Cells.ChatActionCell;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AnimatedFileDrawable;
import org.telegram.ui.Components.BlurringShader;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ButtonBounce;
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
import org.telegram.ui.Components.Paint.Views.EditTextOutline;
import org.telegram.ui.Components.Paint.Views.EntitiesContainerView;
import org.telegram.ui.Components.Paint.Views.EntityView;
import org.telegram.ui.Components.Paint.Views.LinkPreview;
import org.telegram.ui.Components.Paint.Views.LinkView;
import org.telegram.ui.Components.Paint.Views.LocationView;
import org.telegram.ui.Components.Paint.Views.MessageEntityView;
import org.telegram.ui.Components.Paint.Views.PaintCancelView;
import org.telegram.ui.Components.Paint.Views.PaintColorsListView;
import org.telegram.ui.Components.Paint.Views.PaintDoneView;
import org.telegram.ui.Components.Paint.Views.PaintTextOptionsView;
import org.telegram.ui.Components.Paint.Views.PaintToolsView;
import org.telegram.ui.Components.Paint.Views.PaintTypefaceListView;
import org.telegram.ui.Components.Paint.Views.PaintWeightChooserView;
import org.telegram.ui.Components.Paint.Views.PhotoView;
import org.telegram.ui.Components.Paint.Views.ReactionWidgetEntityView;
import org.telegram.ui.Components.Paint.Views.RoundView;
import org.telegram.ui.Components.Paint.Views.StickerView;
import org.telegram.ui.Components.Paint.Views.TextPaintView;
import org.telegram.ui.Components.Paint.Views.WeatherView;
import org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;
import org.telegram.ui.Components.Reactions.ReactionsUtils;
import org.telegram.ui.Components.ReactionsContainerLayout;
import org.telegram.ui.Components.Size;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.SizeNotifierFrameLayoutPhoto;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.PhotoViewer;
import org.telegram.ui.Stars.StarGiftUniqueActionLayout;
import org.telegram.ui.Stars.StarsController;
import org.telegram.ui.Stories.DarkThemeResourceProvider;
import org.telegram.ui.Stories.recorder.PreviewView;
import org.telegram.ui.Stories.recorder.StoryRecorder;
import org.telegram.ui.Stories.recorder.Weather;
import org.telegram.ui.WrappedResourceProvider;

/* loaded from: classes7.dex */
public abstract class PaintView extends SizeNotifierFrameLayoutPhoto implements IPhotoPaintView, PaintToolsView.Delegate, EntityView.EntityViewDelegate, PaintTextOptionsView.Delegate, SizeNotifierFrameLayout.SizeNotifierFrameLayoutDelegate, StoryRecorder.Touchable {
    private float baseScale;
    private Bitmap bitmapToEdit;
    private Bitmap blurBitmapToEdit;
    private BlurringShader.BlurManager blurManager;
    private FrameLayout bottomLayout;
    private PaintCancelView cancelButton;
    private TextView cancelTextButton;
    private ColorPickerBottomSheet colorPickerBottomSheet;
    private Paint colorPickerRainbowPaint;
    private Swatch colorSwatch;
    private Paint colorSwatchOutlinePaint;
    private Paint colorSwatchPaint;
    private PaintColorsListView colorsListView;
    private boolean creatingNewRound;
    private int currentAccount;
    private MediaController.CropState currentCropState;
    private EntityView currentEntityView;
    private boolean destroyed;
    private PaintDoneView doneButton;
    private TextView doneTextButton;
    public boolean drawForThemeToggle;
    private TextView drawTab;
    private boolean editingText;
    private int emojiPadding;
    private EmojiBottomSheet emojiPopup;
    public EmojiView emojiView;
    private boolean emojiViewVisible;
    private boolean emojiViewWasVisible;
    private int emojiWasPadding;
    public boolean enteredThroughText;
    public EntitiesContainerView entitiesView;
    private ArrayList<PhotoFace> faces;
    private Bitmap facesBitmap;
    private File file;
    private boolean fileFromGallery;
    private boolean fillShapes;
    private boolean forceChanges;
    private int h;
    private boolean hasAudio;
    private boolean ignoreLayout;
    private boolean ignoreToolChangeAnimationOnce;
    private boolean inBubbleMode;
    private ArrayList<VideoEditedInfo.MediaEntity> initialEntities;
    private StoryEntry initialEntry;
    private boolean invalidateReactionPosition;
    private boolean isAnimatePopupClosing;
    private boolean isBot;
    private boolean isColorListShown;
    public boolean isCoverPreview;
    private boolean isTypefaceMenuShown;
    private boolean isVideo;
    private AnimatorSet keyboardAnimator;
    private int keyboardHeight;
    private int keyboardHeightLand;
    public final KeyboardNotifier keyboardNotifier;
    private boolean keyboardVisible;
    private int lastSizeChangeValue1;
    private boolean lastSizeChangeValue2;
    private BigInteger lcm;
    private float offsetTranslationY;
    private Runnable onCancelButtonClickedListener;
    private Runnable onDoneButtonClickedListener;
    private Runnable openKeyboardRunnable;
    private int originalBitmapRotation;
    private FrameLayout overlayLayout;
    private PaintToolsView paintToolsView;
    private Size paintingSize;
    private StoryRecorder.WindowView parent;
    private FrameLayout pipetteContainerLayout;
    float[] points;
    private PopupWindowLayout popupLayout;
    private Rect popupRect;
    private ActionBarPopupWindow popupWindow;
    private int[] pos;
    private PreviewView previewView;
    private ObjectAnimator previewViewTranslationAnimator;
    private DispatchQueue queue;
    ReactionWidgetEntityView reactionForEntity;
    public ReactionsContainerLayout reactionLayout;
    private boolean reactionLayoutShowing;
    private float reactionShowProgress;
    private View renderInputView;
    private RenderView renderView;
    private Theme.ResourcesProvider resourcesProvider;
    private int selectedTextType;
    private FrameLayout selectionContainerView;
    private TextView stickerTab;
    private LinearLayout tabsLayout;
    private int tabsNewSelectedIndex;
    private int tabsSelectedIndex;
    private ValueAnimator tabsSelectionAnimator;
    private float tabsSelectionProgress;
    private View textDim;
    private PaintTextOptionsView textOptionsView;
    private TextView textTab;
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
    private PreviewView.TextureViewHolder videoTextureHolder;
    private int w;
    private boolean waitingForKeyboardOpen;
    private boolean wasKeyboardVisible;
    private PaintWeightChooserView weightChooserView;
    private final PaintWeightChooserView.ValueOverride weightDefaultValueOverride;
    private LinearLayout zoomOutButton;
    private ImageView zoomOutImage;
    private TextView zoomOutText;
    private boolean zoomOutVisible;

    /* renamed from: org.telegram.ui.Stories.recorder.PaintView$1, reason: invalid class name */
    public class AnonymousClass1 implements PaintWeightChooserView.ValueOverride {
        final /* synthetic */ PaintView this$0;

        public AnonymousClass1(PaintView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.Paint.Views.PaintWeightChooserView.ValueOverride
        public float get() {
            Brush r0 = PaintView.m20104$$Nest$fgetrenderView(this.this$0).getCurrentBrush();
            PaintView r2 = this.this$0;
            if (r0 != null) goto L7;
            return PersistColorPalette.getInstance(PaintView.m20090$$Nest$fgetcurrentAccount(r2)).getCurrentWeight();
        L7:
            return PersistColorPalette.getInstance(PaintView.m20090$$Nest$fgetcurrentAccount(r2)).getWeight(String.valueOf(Brush.BRUSHES_LIST.indexOf(r0)), r0.getDefaultWeight());
        }

        @Override // org.telegram.ui.Components.Paint.Views.PaintWeightChooserView.ValueOverride
        public void set(float r4) {
            PersistColorPalette.getInstance(PaintView.m20090$$Nest$fgetcurrentAccount(this.this$0)).setWeight(String.valueOf(Brush.BRUSHES_LIST.indexOf(PaintView.m20104$$Nest$fgetrenderView(this.this$0).getCurrentBrush())), r4);
            PaintView.m20086$$Nest$fgetcolorSwatch(this.this$0).brushWeight = r4;
            PaintView r3 = this.this$0;
            PaintView.m20134$$Nest$msetCurrentSwatch(r3, PaintView.m20086$$Nest$fgetcolorSwatch(r3), true);
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.PaintView$10, reason: invalid class name */
    public class AnonymousClass10 extends FrameLayout {
        final /* synthetic */ PaintView this$0;

        public AnonymousClass10(PaintView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            setWillNotDraw(false);
        }

        @Override // android.view.View
        public void onDraw(Canvas r8) {
            super.onDraw(r8);
            PaintView.m20116$$Nest$fgettypefaceMenuOutlinePaint(this.this$0).setAlpha((int) ((PaintView.m20111$$Nest$fgettextOptionsView(this.this$0).getAlpha() * 20.0f) * (1.0f - PaintView.m20117$$Nest$fgettypefaceMenuTransformProgress(this.this$0))));
            PaintTextOptionsView r0 = PaintView.m20111$$Nest$fgettextOptionsView(this.this$0);
            RectF r1 = AndroidUtilities.rectTmp;
            r0.getTypefaceCellBounds(r1);
            float r02 = ((PaintView.m20084$$Nest$fgetbottomLayout(this.this$0).getTop() + PaintView.m20111$$Nest$fgettextOptionsView(this.this$0).getTop()) + PaintView.m20084$$Nest$fgetbottomLayout(this.this$0).getTranslationY()) + PaintView.m20111$$Nest$fgettextOptionsView(this.this$0).getTranslationY();
            r1.set(AndroidUtilities.lerp(r1.left, PaintView.m20114$$Nest$fgettypefaceListView(this.this$0).getLeft(), PaintView.m20117$$Nest$fgettypefaceMenuTransformProgress(this.this$0)), AndroidUtilities.lerp(r1.top + r02, PaintView.m20114$$Nest$fgettypefaceListView(this.this$0).getTop() - PaintView.m20114$$Nest$fgettypefaceListView(this.this$0).getTranslationY(), PaintView.m20117$$Nest$fgettypefaceMenuTransformProgress(this.this$0)), AndroidUtilities.lerp(r1.right, PaintView.m20114$$Nest$fgettypefaceListView(this.this$0).getRight(), PaintView.m20117$$Nest$fgettypefaceMenuTransformProgress(this.this$0)), AndroidUtilities.lerp(r02 + r1.bottom, PaintView.m20114$$Nest$fgettypefaceListView(this.this$0).getBottom() - PaintView.m20114$$Nest$fgettypefaceListView(this.this$0).getTranslationY(), PaintView.m20117$$Nest$fgettypefaceMenuTransformProgress(this.this$0)));
            float r03 = AndroidUtilities.dp(AndroidUtilities.lerp(32, 16, PaintView.m20117$$Nest$fgettypefaceMenuTransformProgress(this.this$0)));
            int r2 = PaintView.m20115$$Nest$fgettypefaceMenuBackgroundPaint(this.this$0).getAlpha();
            PaintView.m20115$$Nest$fgettypefaceMenuBackgroundPaint(this.this$0).setAlpha((int) (r2 * PaintView.m20117$$Nest$fgettypefaceMenuTransformProgress(this.this$0)));
            r8.drawRoundRect(r1, r03, r03, PaintView.m20115$$Nest$fgettypefaceMenuBackgroundPaint(this.this$0));
            PaintView.m20115$$Nest$fgettypefaceMenuBackgroundPaint(this.this$0).setAlpha(r2);
            r8.drawRoundRect(r1, r03, r03, PaintView.m20116$$Nest$fgettypefaceMenuOutlinePaint(this.this$0));
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r2) {
            if (r2.getActionMasked() != 0) goto L9;
            if (PaintView.m20096$$Nest$fgetisTypefaceMenuShown(this.this$0) == false) goto L9;
            PaintView.m20138$$Nest$mshowTypefaceMenu(this.this$0, false);
            return true;
        L9:
            return super.onTouchEvent(r2);
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.PaintView$11, reason: invalid class name */
    public class AnonymousClass11 extends PaintColorsListView {
        private Path path;
        final /* synthetic */ PaintView this$0;

        public AnonymousClass11(PaintView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.path = new Path();
        }

        @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
        public void draw(Canvas r8) {
            ViewGroup r0 = PaintView.m20131$$Nest$mgetBarView(this.this$0);
            RectF r1 = AndroidUtilities.rectTmp;
            r1.set(AndroidUtilities.lerp(r0.getLeft() - getLeft(), 0, PaintView.m20113$$Nest$fgettoolsTransformProgress(this.this$0)), AndroidUtilities.lerp(r0.getTop() - getTop(), 0, PaintView.m20113$$Nest$fgettoolsTransformProgress(this.this$0)), AndroidUtilities.lerp(r0.getRight() - getLeft(), getWidth(), PaintView.m20113$$Nest$fgettoolsTransformProgress(this.this$0)), AndroidUtilities.lerp(r0.getBottom() - getTop(), getHeight(), PaintView.m20113$$Nest$fgettoolsTransformProgress(this.this$0)));
            this.path.rewind();
            this.path.addRoundRect(r1, AndroidUtilities.dp(32.0f), AndroidUtilities.dp(32.0f), Path.Direction.CW);
            r8.save();
            r8.clipPath(this.path);
            super.draw(r8);
            r8.restore();
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.PaintView$12, reason: invalid class name */
    public class AnonymousClass12 implements ColorPickerBottomSheet.PipetteDelegate {
        private boolean hasPipette;
        final /* synthetic */ PaintView this$0;
        final /* synthetic */ Bitmap val$originalBitmap;
        final /* synthetic */ PersistColorPalette val$palette;

        public AnonymousClass12(PaintView r1, Bitmap r2, PersistColorPalette r3) {
            this.this$0 = r1;
            this.val$originalBitmap = r2;
            this.val$palette = r3;
        }

        @Override // org.telegram.ui.Components.Paint.ColorPickerBottomSheet.PipetteDelegate
        public ViewGroup getContainerView() {
            return PaintView.m20101$$Nest$fgetpipetteContainerLayout(this.this$0);
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
            PaintView.m20137$$Nest$mshowColorList(this.this$0, false);
            this.val$palette.selectColor(r3);
            this.val$palette.saveColors();
            PaintView.m20135$$Nest$msetNewColor(this.this$0, r3);
            PaintView.m20089$$Nest$fgetcolorsListView(this.this$0).setSelectedColorIndex(this.val$palette.getCurrentColorPosition());
            PaintView.m20089$$Nest$fgetcolorsListView(this.this$0).getAdapter().notifyDataSetChanged();
        }

        @Override // org.telegram.ui.Components.Paint.ColorPickerBottomSheet.PipetteDelegate
        public void onDrawImageOverCanvas(Bitmap r3, Canvas r4) {
            Matrix r32 = PaintView.m20104$$Nest$fgetrenderView(this.this$0).getMatrix();
            r4.save();
            r4.translate(PaintView.m20104$$Nest$fgetrenderView(this.this$0).getX(), PaintView.m20104$$Nest$fgetrenderView(this.this$0).getY());
            r4.concat(r32);
            r4.scale(PaintView.m20104$$Nest$fgetrenderView(this.this$0).getWidth() / this.val$originalBitmap.getWidth(), PaintView.m20104$$Nest$fgetrenderView(this.this$0).getHeight() / this.val$originalBitmap.getHeight(), 0.0f, 0.0f);
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

    /* renamed from: org.telegram.ui.Stories.recorder.PaintView$13, reason: invalid class name */
    public class AnonymousClass13 extends KeyboardNotifier {
        final /* synthetic */ PaintView this$0;

        public AnonymousClass13(PaintView r1, View r2, Utilities.Callback r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Stories.recorder.KeyboardNotifier
        public void ignore(boolean r1) {
            super.ignore(r1);
            if (r1 == false) goto L6;
            PaintView.m20138$$Nest$mshowTypefaceMenu(this.this$0, false);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.PaintView$14, reason: invalid class name */
    public class AnonymousClass14 implements PaintWeightChooserView.ValueOverride {
        final /* synthetic */ PaintView this$0;
        final /* synthetic */ float val$base;
        final /* synthetic */ TextPaintView val$textPaintView;

        public AnonymousClass14(PaintView r1, TextPaintView r2, float r3) {
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

    /* renamed from: org.telegram.ui.Stories.recorder.PaintView$15, reason: invalid class name */
    public class AnonymousClass15 extends AnimatorListenerAdapter {
        final /* synthetic */ PaintView this$0;
        final /* synthetic */ ReactionsContainerLayout val$layout;

        public AnonymousClass15(PaintView r1, ReactionsContainerLayout r2) {
            this.this$0 = r1;
            this.val$layout = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            AndroidUtilities.removeFromParent(this.val$layout);
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.PaintView$16, reason: invalid class name */
    public class AnonymousClass16 extends WrappedResourceProvider {
        final /* synthetic */ PaintView this$0;

        public AnonymousClass16(PaintView r1, Theme.ResourcesProvider r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.WrappedResourceProvider
        public void appendColors() {
            this.sparseIntArray.put(Theme.key_chat_emojiPanelBackground, ColorUtils.setAlphaComponent(-1, 30));
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.PaintView$17, reason: invalid class name */
    public class AnonymousClass17 implements ReactionsContainerLayout.ReactionsContainerDelegate {
        private final Path clipPath;
        final /* synthetic */ PaintView this$0;
        final /* synthetic */ Paint val$backgroundPaint;
        final /* synthetic */ BlurringShader.StoryBlurDrawer val$reactionBackgroundBlur;
        BlurringShader.StoryBlurDrawer windowBackgroundBlur;

        public AnonymousClass17(PaintView r1, BlurringShader.StoryBlurDrawer r2, Paint r3) {
            this.this$0 = r1;
            this.val$reactionBackgroundBlur = r2;
            this.val$backgroundPaint = r3;
            this.clipPath = new Path();
        }

        @Override // org.telegram.ui.Components.ReactionsContainerLayout.ReactionsContainerDelegate
        public boolean drawBackground() {
            return true;
        }

        @Override // org.telegram.ui.Components.ReactionsContainerLayout.ReactionsContainerDelegate
        public void drawRoundRect(Canvas r5, RectF r6, float r7, float r8, float r9, int r10, boolean r11) {
            if (r11 == false) goto L5;
        L13:
            if (r11 == true) goto L15;
            float r82 = -r8;
            float r92 = -r9;
            this.val$reactionBackgroundBlur.setBounds(r82, r92, this.this$0.getMeasuredWidth() + r82, this.this$0.getMeasuredHeight() + r92);
            Paint r83 = this.val$reactionBackgroundBlur.paint;
        L19:
            r83.setAlpha(r10);
            this.val$backgroundPaint.setAlpha((int) (r10 * 0.4f));
            r5.drawRoundRect(r6, r7, r7, r83);
            r5.drawRoundRect(r6, r7, r7, this.val$backgroundPaint);
            return;
        L15:
            if (this.windowBackgroundBlur != null) goto L17;
            this.windowBackgroundBlur = new BlurringShader.StoryBlurDrawer(PaintView.m20083$$Nest$fgetblurManager(this.this$0), this.this$0.reactionLayout.getReactionsWindow().windowView, 0);
        L17:
            float r84 = -r8;
            float r93 = -r9;
            this.windowBackgroundBlur.setBounds(r84, r93, this.this$0.getMeasuredWidth() + r84, this.this$0.getMeasuredHeight() + r93);
            r83 = this.windowBackgroundBlur.paint;
            goto L19
        L5:
            if (PaintView.m20083$$Nest$fgetblurManager(this.this$0) == null) goto L13;
            if (PaintView.m20083$$Nest$fgetblurManager(this.this$0).hasRenderNode() == false) goto L13;
            if (r11 == false) goto L10;
            BlurringShader.StoryBlurDrawer r85 = this.windowBackgroundBlur;
        L11:
            this.clipPath.rewind();
            this.clipPath.addRoundRect(r6, r7, r7, Path.Direction.CW);
            r5.save();
            r5.clipPath(this.clipPath);
            r85.drawRect(r5);
            this.val$backgroundPaint.setAlpha((int) (r10 * 0.4f));
            r5.drawPaint(this.val$backgroundPaint);
            r5.restore();
            return;
        L10:
            r85 = this.val$reactionBackgroundBlur;
            goto L11
        }

        @Override // org.telegram.ui.Components.ReactionsContainerLayout.ReactionsContainerDelegate
        public void onReactionClicked(View r1, ReactionsLayoutInBubble.VisibleReaction r2, boolean r3, boolean r4) {
            ReactionWidgetEntityView r12 = this.this$0.reactionForEntity;
            if (r12 != null) goto L5;
            return;
        L5:
            r12.setCurrentReaction(r2, true);
            this.this$0.showReactionsLayout(false);
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.PaintView$18, reason: invalid class name */
    public class AnonymousClass18 extends AnimatorListenerAdapter {
        final /* synthetic */ PaintView this$0;
        final /* synthetic */ boolean val$show;

        public AnonymousClass18(PaintView r1, boolean r2) {
            this.this$0 = r1;
            this.val$show = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (this.val$show == true) goto L6;
            this.this$0.reactionLayout.setVisibility(8);
            this.this$0.reactionLayout.reset();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.PaintView$19, reason: invalid class name */
    public class AnonymousClass19 extends AnimatorListenerAdapter {
        final /* synthetic */ PaintView this$0;

        public AnonymousClass19(PaintView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            PaintView.m20125$$Nest$fputreactionShowProgress(this.this$0, 0.0f);
            this.this$0.reactionLayout.setAlpha(1.0f);
            this.this$0.reactionLayout.setVisibility(8);
            this.this$0.reactionLayout.reset();
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.PaintView$2, reason: invalid class name */
    public class AnonymousClass2 implements Theme.ResourcesProvider {
        private ColorFilter animatedEmojiColorFilter;
        final /* synthetic */ PaintView this$0;
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;

        public AnonymousClass2(PaintView r1, Theme.ResourcesProvider r2) {
            this.this$0 = r1;
            this.val$resourcesProvider = r2;
        }

        @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
        public ColorFilter getAnimatedEmojiColorFilter() {
            if (this.animatedEmojiColorFilter != null) goto L6;
            this.animatedEmojiColorFilter = new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN);
        L6:
            return this.animatedEmojiColorFilter;
        }

        @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
        public int getColor(int r4) {
            if (r4 != Theme.key_actionBarDefaultSubmenuBackground) goto L7;
            return -14145495;
        L7:
            if (r4 != Theme.key_actionBarDefaultSubmenuItem) goto L10;
            return -1;
        L10:
            if (r4 != Theme.key_dialogBackground) goto L14;
            return -14737633;
        L14:
            if (r4 != Theme.key_dialogTextBlack) goto L18;
            return -592138;
        L18:
            if (r4 != Theme.key_dialogTextGray3) goto L22;
            return -8553091;
        L22:
            if (r4 != Theme.key_chat_emojiPanelBackground) goto L26;
            return -16777216;
        L26:
            if (r4 != Theme.key_chat_emojiPanelShadowLine) goto L30;
            return -1610612736;
        L30:
            if (r4 != Theme.key_chat_emojiBottomPanelIcon) goto L33;
            return -9539985;
        L33:
            if (r4 != Theme.key_chat_emojiPanelBackspace) goto L36;
            return -9539985;
        L36:
            if (r4 != Theme.key_chat_emojiPanelIcon) goto L39;
            return -9539985;
        L39:
            if (r4 != Theme.key_windowBackgroundWhiteBlackText) goto L41;
            return -1;
        L41:
            int r0 = Theme.key_featuredStickers_addedIcon;
            if (r4 != r0) goto L46;
            return -11754001;
        L46:
            if (r4 != Theme.key_listSelector) goto L50;
            return 536870911;
        L50:
            if (r4 != Theme.key_profile_tabSelectedText) goto L53;
            return -1;
        L53:
            if (r4 != Theme.key_profile_tabText) goto L56;
            return -1;
        L56:
            if (r4 != Theme.key_profile_tabSelectedLine) goto L59;
            return -1;
        L59:
            if (r4 != Theme.key_profile_tabSelector) goto L63;
            return 352321535;
        L63:
            if (r4 == Theme.key_chat_emojiSearchIcon) goto L80;
            if (r4 != r0) goto L67;
            return -7895161;
        L67:
            if (r4 != Theme.key_chat_emojiSearchBackground) goto L71;
            return 780633991;
        L71:
            if (r4 != Theme.key_windowBackgroundGray) goto L74;
            return -15921907;
        L74:
            Theme.ResourcesProvider r3 = this.val$resourcesProvider;
            if (r3 == null) goto L79;
            return r3.getColor(r4);
        L79:
            return Theme.getColor(r4);
        L80:
            return -7895161;
        }

        @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
        public Paint getPaint(String r1) {
            return this.val$resourcesProvider.getPaint(r1);
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.PaintView$20, reason: invalid class name */
    public class AnonymousClass20 extends LinearLayout {
        Paint linePaint;
        final /* synthetic */ PaintView this$0;

        public AnonymousClass20(PaintView r1, Context r2) {
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
            TextView r0 = (TextView) getChildAt(PaintView.m20108$$Nest$fgettabsSelectedIndex(this.this$0));
            Layout r3 = null;
            if (PaintView.m20107$$Nest$fgettabsNewSelectedIndex(this.this$0) == (-1)) goto L5;
            TextView r1 = (TextView) getChildAt(PaintView.m20107$$Nest$fgettabsNewSelectedIndex(this.this$0));
        L6:
            this.linePaint.setColor(r0.getCurrentTextColor());
            float r7 = ((r0.getY() + r0.getHeight()) - r0.getPaddingBottom()) + AndroidUtilities.dp(3.0f);
            Layout r2 = r0.getLayout();
            if (r2 != null) goto L9;
            return;
        L9:
            if (r1 == null) goto L11;
            r3 = r1.getLayout();
        L11:
            float r4 = 0.0f;
            if (r3 != null) goto L14;
            float r5 = 0.0f;
        L15:
            float r02 = r0.getX() + r2.getPrimaryHorizontal(r2.getLineStart(0));
            if (r3 == null) goto L18;
            float r13 = r1.getX() + r3.getPrimaryHorizontal(r2.getLineStart(0));
        L19:
            float r03 = AndroidUtilities.lerp(r02, r13, r5);
            float r14 = r2.getPrimaryHorizontal(r2.getLineEnd(0)) - r2.getPrimaryHorizontal(r2.getLineStart(0));
            if (r3 == null) goto L22;
            r4 = r3.getPrimaryHorizontal(r3.getLineEnd(0)) - r3.getPrimaryHorizontal(r3.getLineStart(0));
        L22:
            r12.drawLine(r03, r7, r03 + AndroidUtilities.lerp(r14, r4, r5), r7, this.linePaint);
            return;
        L18:
            r13 = 0.0f;
            goto L19
        L14:
            r5 = CubicBezierInterpolator.DEFAULT.getInterpolation(PaintView.m20110$$Nest$fgettabsSelectionProgress(this.this$0));
            goto L15
        L5:
            r1 = null;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.PaintView$21, reason: invalid class name */
    public class AnonymousClass21 extends AnimatorListenerAdapter {
        final /* synthetic */ PaintView this$0;
        final /* synthetic */ int val$index;
        final /* synthetic */ View val$newView;
        final /* synthetic */ View val$view;

        public AnonymousClass21(PaintView r1, View r2, View r3, int r4) {
            this.this$0 = r1;
            this.val$view = r2;
            this.val$newView = r3;
            this.val$index = r4;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            PaintView r0 = this.this$0;
            PaintView.m20127$$Nest$fputtabsSelectedIndex(r0, PaintView.m20107$$Nest$fgettabsNewSelectedIndex(r0));
            PaintView.m20126$$Nest$fputtabsNewSelectedIndex(this.this$0, -1);
            PaintView.m20106$$Nest$fgettabsLayout(this.this$0).invalidate();
            View r02 = this.val$view;
            if (r02 == null) goto L8;
            if (this.val$newView == null) goto L8;
            r02.setVisibility(8);
        L8:
            if (r3 != PaintView.m20109$$Nest$fgettabsSelectionAnimator(this.this$0)) goto L11;
            PaintView.m20128$$Nest$fputtabsSelectionAnimator(this.this$0, null);
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
            PaintView r0 = this.this$0;
            if (r33 != 2) goto L11;
            PaintView.m20121$$Nest$fgetweightChooserView(r0).setMinMax(0.5f, 2.0f);
            return;
        L11:
            Brush r34 = PaintView.m20104$$Nest$fgetrenderView(r0).getCurrentBrush();
            if ((r34 instanceof Brush.Blurer) == false) goto L14;
        L18:
            PaintView.m20121$$Nest$fgetweightChooserView(this.this$0).setMinMax(0.4f, 1.75f);
            return;
        L14:
            if ((r34 instanceof Brush.Eraser) == true) goto L18;
            PaintView.m20121$$Nest$fgetweightChooserView(this.this$0).setMinMax(0.05f, 1.0f);
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.PaintView$22, reason: invalid class name */
    public class AnonymousClass22 extends EmojiBottomSheet {
        final /* synthetic */ PaintView this$0;
        final /* synthetic */ int val$wasSelectedIndex;

        public AnonymousClass22(PaintView r1, Context r2, boolean r3, Theme.ResourcesProvider r4, boolean r5, int r6) {
            this.this$0 = r1;
            this.val$wasSelectedIndex = r6;
            super(r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Stories.recorder.EmojiBottomSheet
        public boolean canClickWidget(Integer r7) {
            if (r7.intValue() != 3) goto L22;
            int r2 = 0;
            int r3 = 0;
        L6:
            if (r2 >= this.this$0.entitiesView.getChildCount()) goto L12;
            if ((this.this$0.entitiesView.getChildAt(r2) instanceof ReactionWidgetEntityView) == false) goto L10;
            r3 = r3 + 1;
        L10:
            r2 = r2 + 1;
            goto L6
        L12:
            if (r3 < MessagesController.getInstance(this.currentAccount).storiesSuggestedReactionsLimitDefault) goto L18;
            if (UserConfig.getInstance(this.currentAccount).isPremium() == true) goto L18;
            showPremiumBulletin(LocaleController.formatPluralString("StoryPremiumWidgets2", MessagesController.getInstance(this.currentAccount).storiesSuggestedReactionsLimitPremium, new Object[0]));
            return false;
        L18:
            if (r3 < MessagesController.getInstance(this.currentAccount).storiesSuggestedReactionsLimitPremium) goto L22;
            this.container.performHapticFeedback(3);     // Catch: Exception -> L23
        L20:
            BulletinFactory.of(this.container, this.resourcesProvider).createSimpleBulletin(R.raw.chats_infotip, LocaleController.getString("LimitReached", R.string.LimitReached), LocaleController.formatPluralString("StoryReactionsWidgetLimit2", MessagesController.getInstance(this.currentAccount).storiesSuggestedReactionsLimitPremium, new Object[0])).show(true);
            return false;
        L22:
            return true;
        }

        @Override // org.telegram.ui.Stories.recorder.EmojiBottomSheet
        public boolean canShowWidget(Integer r4) {
            boolean r1 = false;
            if (PaintView.m20095$$Nest$fgetisBot(this.this$0) == false) goto L9;
            if (r4.intValue() != 2) goto L7;
            return true;
        L7:
            return false;
        L9:
            if (r4.intValue() != 5) goto L19;
            int r42 = 0;
        L12:
            if (r42 >= this.this$0.entitiesView.getChildCount()) goto L18;
            if ((this.this$0.entitiesView.getChildAt(r42) instanceof WeatherView) == true) goto L15;
            r42 = r42 + 1;
            goto L12
        L15:
            r1 = true;
        L18:
            return !r1;
        L19:
            return true;
        }

        @Override // org.telegram.ui.Stories.recorder.EmojiBottomSheet
        public boolean checkAudioPermission(Runnable r1) {
            return this.this$0.checkAudioPermission(r1);
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet
        public void onDismissAnimationStart() {
            super.onDismissAnimationStart();
            PaintView.m20139$$Nest$mswitchTab(this.this$0, this.val$wasSelectedIndex);
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.PaintView$23, reason: invalid class name */
    public class AnonymousClass23 extends BaseFragment {
        final /* synthetic */ PaintView this$0;

        public AnonymousClass23(PaintView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public Context getContext() {
            return this.this$0.getContext();
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public int getCurrentAccount() {
            return this.currentAccount;
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public Activity getParentActivity() {
            return AndroidUtilities.findActivity(this.this$0.getContext());
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public Theme.ResourcesProvider getResourceProvider() {
            return PaintView.m20105$$Nest$fgetresourcesProvider(this.this$0);
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public boolean presentFragment(BaseFragment r4) {
            BaseFragment r3 = LaunchActivity.getLastFragment();
            if (r3 != null) goto L5;
            return false;
        L5:
            BaseFragment.BottomSheetParams r1 = new BaseFragment.BottomSheetParams();
            r1.transitionFromLeft = true;
            r1.allowNestedScroll = false;
            r3.showAsSheet(r4, r1);
            return true;
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.PaintView$24, reason: invalid class name */
    public class AnonymousClass24 extends ChatActivity {
        final /* synthetic */ PaintView this$0;
        final /* synthetic */ Utilities.Callback2 val$onLocationSelected;

        /* renamed from: $r8$lambda$TJAqt-HJ3gUH-TAs7IV4QJfxXHk, reason: not valid java name */
        public static /* synthetic */ void m20140$r8$lambda$TJAqtHJ3gUHTAs7IV4QJfxXHk(TLRPC.MessageMedia r4, TL_stories.TL_mediaAreaGeoPoint r5) {
            List<Address> r42 = new Geocoder(ApplicationLoader.applicationContext, LocaleController.getInstance().getCurrentLocale()).getFromLocationName(r4.title, 1);     // Catch: Exception -> L7
            if (r42.size() <= 0) goto L10;
            TLRPC.GeoPoint r0 = r5.geo;     // Catch: Exception -> L7
            r0.lat = r42.get(0).getLatitude();     // Catch: Exception -> L7
            TLRPC.GeoPoint r52 = r5.geo;     // Catch: Exception -> L7
            r52._long = r42.get(0).getLongitude();     // Catch: Exception -> L7
            return;
        L10:
            return;
        }

        public AnonymousClass24(PaintView r1, Bundle r2, Utilities.Callback2 r3) {
            this.this$0 = r1;
            this.val$onLocationSelected = r3;
            super(r2);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.telegram.ui.ChatActivity, org.telegram.ui.LocationActivity.LocationActivityDelegate
        public void didSelectLocation(TLRPC.MessageMedia r1, int r2, boolean r3, int r4, long r5) {
            if ((r1 instanceof TLRPC.TL_messageMediaGeo) == false) goto L6;
            TL_stories.TL_mediaAreaGeoPoint r22 = new TL_stories.TL_mediaAreaGeoPoint();
            r22.geo = r1.geo;
        L18:
            this.val$onLocationSelected.run(r1, r22);
            return;
        L6:
            if ((r1 instanceof TLRPC.TL_messageMediaVenue) == false) goto L20;
            TLRPC.TL_messageMediaVenue r23 = (TLRPC.TL_messageMediaVenue) r1;
            long r32 = r23.query_id;
            if (r32 != (-1)) goto L10;
        L14:
            TL_stories.TL_mediaAreaGeoPoint r33 = new TL_stories.TL_mediaAreaGeoPoint();
            r33.geo = r1.geo;
            TL_stories.TL_geoPointAddress r24 = r23.geoAddress;
            r33.address = r24;
            if (r24 == null) goto L17;
            r33.flags |= 1;
        L17:
            Utilities.globalQueue.postRunnable(new PaintView$24$$ExternalSyntheticLambda0(r1, r33));
            TL_stories.TL_mediaAreaGeoPoint r34 = r33;
        L13:
            r22 = r34;
            goto L18
        L10:
            if (r32 == (-2)) goto L14;
            TL_stories.TL_inputMediaAreaVenue r35 = new TL_stories.TL_inputMediaAreaVenue();
            r35.query_id = r23.query_id;
            r35.result_id = r23.result_id;
            r34 = r35;
            goto L13
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
            return PaintView.m20105$$Nest$fgetresourcesProvider(this.this$0);
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

    /* renamed from: org.telegram.ui.Stories.recorder.PaintView$25, reason: invalid class name */
    public class AnonymousClass25 implements ChatAttachAlert.ChatAttachViewDelegate {
        final /* synthetic */ PaintView this$0;

        public AnonymousClass25(PaintView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate
        public void didPressedButton(int r1, boolean r2, boolean r3, int r4, int r5, long r6, boolean r8, boolean r9, long r10) {
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.PaintView$26, reason: invalid class name */
    public class AnonymousClass26 extends MessageEntityView {
        final /* synthetic */ PaintView this$0;

        public AnonymousClass26(PaintView r1, Context r2, PointF r3, float r4, float r5, ArrayList r6, BlurringShader.BlurManager r7, boolean r8, PreviewView.TextureViewHolder r9) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7, r8, r9);
        }

        @Override // org.telegram.ui.Components.Paint.Views.MessageEntityView
        public boolean drawForBitmap() {
            return this.this$0.drawForThemeToggle;
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.PaintView$27, reason: invalid class name */
    public class AnonymousClass27 extends StickerView {
        final /* synthetic */ PaintView this$0;

        public AnonymousClass27(PaintView r1, Context r2, PointF r3, float r4, float r5, Size r6, TLRPC.Document r7, Object r8) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7, r8);
        }

        @Override // org.telegram.ui.Components.Paint.Views.StickerView
        public void didSetAnimatedSticker(RLottieDrawable r1) {
            this.this$0.didSetAnimatedSticker(r1);
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.PaintView$28, reason: invalid class name */
    public class AnonymousClass28 implements Runnable {
        final /* synthetic */ PaintView this$0;

        public AnonymousClass28(PaintView r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if ((PaintView.m20091$$Nest$fgetcurrentEntityView(this.this$0) instanceof TextPaintView) == false) goto L27;
            EditTextOutline r0 = ((TextPaintView) PaintView.m20091$$Nest$fgetcurrentEntityView(this.this$0)).getEditText();
            if (PaintView.m20092$$Nest$fgetdestroyed(this.this$0) == true) goto L20;
            if (r0 != null) goto L9;
            return;
        L9:
            if (PaintView.m20120$$Nest$fgetwaitingForKeyboardOpen(this.this$0) == true) goto L11;
            return;
        L11:
            if (PaintView.m20097$$Nest$fgetkeyboardVisible(this.this$0) == false) goto L13;
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
            AndroidUtilities.cancelRunOnUIThread(PaintView.m20098$$Nest$fgetopenKeyboardRunnable(this.this$0));
            AndroidUtilities.runOnUIThread(PaintView.m20098$$Nest$fgetopenKeyboardRunnable(this.this$0), 100);
            return;
        L26:
            return;
        L20:
            return;
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.PaintView$29, reason: invalid class name */
    public class AnonymousClass29 extends AnimatorListenerAdapter {
        final /* synthetic */ PaintView this$0;

        public AnonymousClass29(PaintView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            this.this$0.emojiView.setTranslationY(0.0f);
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.PaintView$3, reason: invalid class name */
    public class AnonymousClass3 extends RenderView {
        final /* synthetic */ PaintView this$0;
        final /* synthetic */ Bitmap val$originalBitmap;

        public AnonymousClass3(PaintView r1, Context r2, Painting r3, Bitmap r4, Bitmap r5, BlurringShader.BlurManager r6, Bitmap r7) {
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
            PaintView.m20100$$Nest$fgetpaintToolsView(this.this$0).select(r0);
            this.this$0.onBrushSelected(r4);
            return;
        L5:
            if (this.val$originalBitmap != null) goto L7;
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.PaintView$30, reason: invalid class name */
    public class AnonymousClass30 extends AnimatorListenerAdapter {
        final /* synthetic */ PaintView this$0;

        public AnonymousClass30(PaintView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            PaintView.m20124$$Nest$fputisAnimatePopupClosing(this.this$0, false);
            this.this$0.emojiView.setTranslationY(0.0f);
            PaintView.m20133$$Nest$mhideEmojiView(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.PaintView$31, reason: invalid class name */
    public class AnonymousClass31 implements EmojiView.EmojiViewDelegate {
        int innerTextChange;
        final /* synthetic */ PaintView this$0;

        public static /* synthetic */ void $r8$lambda$frB1WJVFMaeUYtBymQ6whJM93ow(AnonymousClass31 r0, AlertDialog r1, int r2) {
            r0.lambda$onClearEmojiRecent$0(r1, r2);
        }

        public AnonymousClass31(PaintView r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onClearEmojiRecent$0(AlertDialog r1, int r2) {
            this.this$0.emojiView.clearRecentEmoji();
        }

        @Override // org.telegram.ui.Components.EmojiView.EmojiViewDelegate
        public void onAnimatedEmojiUnlockClick() {
        }

        @Override // org.telegram.ui.Components.EmojiView.EmojiViewDelegate
        public boolean onBackspace() {
            EditTextOutline r3 = ((TextPaintView) PaintView.m20091$$Nest$fgetcurrentEntityView(this.this$0)).getEditText();
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
            AlertDialog.Builder r0 = new AlertDialog.Builder(this.this$0.getContext(), PaintView.m20105$$Nest$fgetresourcesProvider(this.this$0));
            r0.setTitle(LocaleController.getString(R.string.ClearRecentEmojiTitle));
            r0.setMessage(LocaleController.getString(R.string.ClearRecentEmojiText));
            r0.setPositiveButton(LocaleController.getString(R.string.ClearButton), new PaintView$31$$ExternalSyntheticLambda0(this));
            r0.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
            r0.show();
        }

        @Override // org.telegram.ui.Components.EmojiView.EmojiViewDelegate
        public void onCustomEmojiSelected(long r5, TLRPC.Document r7, String r8, boolean r9) {
            EditTextOutline r92 = ((TextPaintView) PaintView.m20091$$Nest$fgetcurrentEntityView(this.this$0)).getEditText();
            if (r92 != null) goto L5;
            return;
        L5:
            int r0 = r92.getSelectionEnd();
            if (r0 >= 0) goto L25;
            r0 = 0;
        L25:
            this.innerTextChange = 2;     // Catch: Throwable -> L12 Exception -> L14
            SpannableString r2 = new SpannableString(r8);     // Catch: Throwable -> L12 Exception -> L14
            if (r7 == null) goto L16;
            AnimatedEmojiSpan r52 = new AnimatedEmojiSpan(r7, 1.0f, r92.getPaint().getFontMetricsInt());     // Catch: Throwable -> L12 Exception -> L14
        L17:
            r2.setSpan(r52, 0, r2.length(), 33);     // Catch: Throwable -> L12 Exception -> L14
            r92.setText(r92.getText().insert(r0, r2));     // Catch: Throwable -> L12 Exception -> L14
            int r02 = r0 + r2.length();     // Catch: Throwable -> L12 Exception -> L14
            r92.setSelection(r02, r02);     // Catch: Throwable -> L12 Exception -> L14
            this.innerTextChange = 0;
            return;
        L16:
            r52 = new AnimatedEmojiSpan(r5, 1.0f, r92.getPaint().getFontMetricsInt());     // Catch: Throwable -> L12 Exception -> L14
        L12:
            th = move-exception;
            this.innerTextChange = 0;
            throw th;
        L14:
            e = move-exception;
            FileLog.e(e);     // Catch: Throwable -> L12
            this.innerTextChange = 0;
        }

        @Override // org.telegram.ui.Components.EmojiView.EmojiViewDelegate
        public void onEmojiSelected(String r8) {
            if ((PaintView.m20091$$Nest$fgetcurrentEntityView(this.this$0) instanceof TextPaintView) == false) goto L35;
            TextPaintView r0 = (TextPaintView) PaintView.m20091$$Nest$fgetcurrentEntityView(this.this$0);
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

    /* renamed from: org.telegram.ui.Stories.recorder.PaintView$4, reason: invalid class name */
    public class AnonymousClass4 implements RenderView.RenderViewDelegate {
        final /* synthetic */ PaintView this$0;
        final /* synthetic */ Runnable val$onInit;

        public AnonymousClass4(PaintView r1, Runnable r2) {
            this.this$0 = r1;
            this.val$onInit = r2;
        }

        @Override // org.telegram.ui.Components.Paint.RenderView.RenderViewDelegate
        public void invalidateInputView() {
            if (PaintView.m20103$$Nest$fgetrenderInputView(this.this$0) == null) goto L6;
            PaintView.m20103$$Nest$fgetrenderInputView(this.this$0).invalidate();
            return;
        }

        @Override // org.telegram.ui.Components.Paint.RenderView.RenderViewDelegate
        public void onBeganDrawing() {
            if (PaintView.m20091$$Nest$fgetcurrentEntityView(this.this$0) == null) goto L5;
            this.this$0.selectEntity(null);
        L5:
            PaintView.m20121$$Nest$fgetweightChooserView(this.this$0).setViewHidden(true);
        }

        @Override // org.telegram.ui.Components.Paint.RenderView.RenderViewDelegate
        public void onFinishedDrawing(boolean r1) {
            PaintView.m20118$$Nest$fgetundoStore(this.this$0).getDelegate().historyChanged();
            PaintView.m20121$$Nest$fgetweightChooserView(this.this$0).setViewHidden(false);
        }

        @Override // org.telegram.ui.Components.Paint.RenderView.RenderViewDelegate
        public void onFirstDraw() {
            Runnable r0 = this.val$onInit;
            if (r0 == null) goto L6;
            r0.run();
            return;
        }

        @Override // org.telegram.ui.Components.Paint.RenderView.RenderViewDelegate
        public void resetBrush() {
            boolean r0 = PaintView.m20094$$Nest$fgetignoreToolChangeAnimationOnce(this.this$0);
            PaintView r1 = this.this$0;
            if (r0 == false) goto L6;
            PaintView.m20123$$Nest$fputignoreToolChangeAnimationOnce(r1, false);
            return;
        L6:
            PaintView.m20100$$Nest$fgetpaintToolsView(r1).select(1);
            this.this$0.onBrushSelected(Brush.BRUSHES_LIST.get(0));
        }

        @Override // org.telegram.ui.Components.Paint.RenderView.RenderViewDelegate
        public boolean shouldDraw() {
            if (PaintView.m20091$$Nest$fgetcurrentEntityView(this.this$0) != null) goto L5;
            boolean r0 = true;
        L6:
            if (r0 == true) goto L8;
            this.this$0.selectEntity(null);
        L8:
            return r0;
        L5:
            r0 = false;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.PaintView$5, reason: invalid class name */
    public class AnonymousClass5 extends View {
        final /* synthetic */ PaintView this$0;

        public AnonymousClass5(PaintView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r2) {
            super.dispatchDraw(r2);
            if (PaintView.m20104$$Nest$fgetrenderView(this.this$0) == null) goto L6;
            PaintView.m20104$$Nest$fgetrenderView(this.this$0).onDrawForInput(r2);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.PaintView$6, reason: invalid class name */
    public class AnonymousClass6 implements EntitiesContainerView.EntitiesContainerViewDelegate {
        final /* synthetic */ PaintView this$0;

        public AnonymousClass6(PaintView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.Paint.Views.EntitiesContainerView.EntitiesContainerViewDelegate
        public void onEntityDeselect() {
            this.this$0.selectEntity(null);
            PaintView r0 = this.this$0;
            if (r0.enteredThroughText == false) goto L5;
            r0.dismiss();
            this.this$0.enteredThroughText = false;
        L5:
            this.this$0.showReactionsLayout(false);
        }

        @Override // org.telegram.ui.Components.Paint.Views.EntitiesContainerView.EntitiesContainerViewDelegate
        public EntityView onSelectedEntityRequest() {
            return PaintView.m20091$$Nest$fgetcurrentEntityView(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.PaintView$7, reason: invalid class name */
    public class AnonymousClass7 extends EntitiesContainerView {
        private int lastStickyX;
        private int lastStickyY;
        long lastUpdate;
        Paint linePaint;
        float stickyXAlpha;
        float stickyYAlpha;
        final /* synthetic */ PaintView this$0;

        public AnonymousClass7(PaintView r1, Context r2, EntitiesContainerView.EntitiesContainerViewDelegate r3) {
            this.this$0 = r1;
            super(r2, r3);
            this.linePaint = new Paint();
            setWillNotDraw(false);
            this.linePaint.setStrokeWidth(AndroidUtilities.dp(2.0f));
            this.linePaint.setStyle(Paint.Style.STROKE);
            this.linePaint.setColor(-1);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r2) {
            if (this.this$0.isCoverPreview == false) goto L7;
            return false;
        L7:
            return super.dispatchTouchEvent(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r21) {
            super.onDraw(r21);
            long r1 = Math.min(16, System.currentTimeMillis() - this.lastUpdate);
            this.lastUpdate = System.currentTimeMillis();
            if (PaintView.m20091$$Nest$fgetcurrentEntityView(this.this$0) != null) goto L5;
        L9:
            int r3 = 0;
            int r4 = 0;
        L10:
            if (r3 == 0) goto L12;
            this.lastStickyX = r3;
        L12:
            if (r4 == 0) goto L15;
            this.lastStickyY = r4;
        L15:
            if (r3 == 0) goto L19;
            float r8 = this.stickyXAlpha;
            if (r8 == 1.0f) goto L19;
            this.stickyXAlpha = Math.min(1.0f, r8 + (r1 / 150.0f));
            invalidate();
        L23:
            if (r4 == 0) goto L27;
            float r32 = this.stickyYAlpha;
            if (r32 == 1.0f) goto L27;
            this.stickyYAlpha = Math.min(1.0f, r32 + (r1 / 150.0f));
            invalidate();
        L31:
            float r12 = this.stickyYAlpha;
            if (r12 == 0.0f) goto L42;
            this.linePaint.setAlpha((int) (r12 * 255.0f));
            int r13 = this.lastStickyY;
            if (r13 != 1) goto L38;
            int r14 = AndroidUtilities.dp(64.0f);
        L36:
            float r15 = r14;
        L37:
            float r10 = r15;
            r21.drawLine(0.0f, r10, getMeasuredWidth(), r10, this.linePaint);
            goto L42
        L38:
            if (r13 != 2) goto L40;
            r15 = getMeasuredHeight() / 2.0f;
            goto L37
        L40:
            r14 = getMeasuredHeight() - AndroidUtilities.dp(64.0f);
        L42:
            float r16 = this.stickyXAlpha;
            if (r16 == 0.0f) goto L54;
            this.linePaint.setAlpha((int) (r16 * 255.0f));
            int r17 = this.lastStickyX;
            if (r17 != 1) goto L49;
            int r18 = AndroidUtilities.dp(8.0f);
        L47:
            float r19 = r18;
        L48:
            float r152 = r19;
            r21.drawLine(r152, 0.0f, r152, getMeasuredHeight(), this.linePaint);
            return;
        L49:
            if (r17 != 2) goto L51;
            r19 = getMeasuredWidth() / 2.0f;
            goto L48
        L51:
            r18 = getMeasuredWidth() - AndroidUtilities.dp(8.0f);
            goto L47
        L54:
            return;
        L27:
            if (r4 != 0) goto L31;
            float r33 = this.stickyYAlpha;
            if (r33 == 0.0f) goto L31;
            this.stickyYAlpha = Math.max(0.0f, r33 - (r1 / 150.0f));
            invalidate();
        L19:
            if (r3 != 0) goto L23;
            float r34 = this.stickyXAlpha;
            if (r34 == 0.0f) goto L23;
            this.stickyXAlpha = Math.max(0.0f, r34 - (r1 / 150.0f));
            invalidate();
            goto L23
        L5:
            if (PaintView.m20091$$Nest$fgetcurrentEntityView(this.this$0).hasTouchDown() == false) goto L9;
            if (PaintView.m20091$$Nest$fgetcurrentEntityView(this.this$0).hasPanned() == false) goto L9;
            r3 = PaintView.m20091$$Nest$fgetcurrentEntityView(this.this$0).getStickyX();
            r4 = PaintView.m20091$$Nest$fgetcurrentEntityView(this.this$0).getStickyY();
            goto L10
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, r2);
            if (PaintView.m20119$$Nest$fgetw(this.this$0) > 0) goto L6;
            PaintView r12 = this.this$0;
            PaintView.m20129$$Nest$fputw(r12, r12.entitiesView.getMeasuredWidth());
        L6:
            if (PaintView.m20093$$Nest$fgeth(this.this$0) > 0) goto L8;
            PaintView r13 = this.this$0;
            PaintView.m20122$$Nest$fputh(r13, r13.entitiesView.getMeasuredHeight());
        L8:
            PaintView.m20136$$Nest$msetupEntities(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.PaintView$8, reason: invalid class name */
    public class AnonymousClass8 extends FrameLayout {
        final /* synthetic */ PaintView this$0;

        public AnonymousClass8(PaintView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r2) {
            if (this.this$0.isCoverPreview == false) goto L7;
            return false;
        L7:
            return super.dispatchTouchEvent(r2);
        }

        @Override // android.view.View
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouchEvent(MotionEvent r1) {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.PaintView$9, reason: invalid class name */
    public class AnonymousClass9 extends FrameLayout {
        private float lastRainbowX;
        private float lastRainbowY;
        private Path path;
        final /* synthetic */ PaintView this$0;
        final /* synthetic */ PersistColorPalette val$palette;

        public AnonymousClass9(PaintView r1, Context r2, PersistColorPalette r3) {
            this.this$0 = r1;
            this.val$palette = r3;
            super(r2);
            this.path = new Path();
            setWillNotDraw(false);
            PaintView.m20085$$Nest$fgetcolorPickerRainbowPaint(r1).setStyle(Paint.Style.STROKE);
            PaintView.m20085$$Nest$fgetcolorPickerRainbowPaint(r1).setStrokeWidth(AndroidUtilities.dp(2.0f));
        }

        private void checkRainbow(float r4, float r5) {
            if (r4 == this.lastRainbowX) goto L5;
        L8:
            this.lastRainbowX = r4;
            this.lastRainbowY = r5;
            PaintView.m20085$$Nest$fgetcolorPickerRainbowPaint(this.this$0).setShader(new SweepGradient(r4, r5, new int[]{-1356981, -1146130, -10452764, -16711681, -7352832, -256, -23296, -1356981}, null));
            return;
        L5:
            if (r5 != this.lastRainbowY) goto L8;
        }

        @Override // android.view.View
        public void onDraw(Canvas r19) {
            super.onDraw(r19);
            ViewGroup r2 = PaintView.m20131$$Nest$mgetBarView(this.this$0);
            RectF r3 = AndroidUtilities.rectTmp;
            r3.set(AndroidUtilities.lerp(r2.getLeft(), PaintView.m20089$$Nest$fgetcolorsListView(this.this$0).getLeft(), PaintView.m20113$$Nest$fgettoolsTransformProgress(this.this$0)), AndroidUtilities.lerp(r2.getTop(), PaintView.m20089$$Nest$fgetcolorsListView(this.this$0).getTop(), PaintView.m20113$$Nest$fgettoolsTransformProgress(this.this$0)), AndroidUtilities.lerp(r2.getRight(), PaintView.m20089$$Nest$fgetcolorsListView(this.this$0).getRight(), PaintView.m20113$$Nest$fgettoolsTransformProgress(this.this$0)), AndroidUtilities.lerp(r2.getBottom(), PaintView.m20089$$Nest$fgetcolorsListView(this.this$0).getBottom(), PaintView.m20113$$Nest$fgettoolsTransformProgress(this.this$0)));
            float r4 = AndroidUtilities.lerp(AndroidUtilities.dp(32.0f), AndroidUtilities.dp(24.0f), PaintView.m20113$$Nest$fgettoolsTransformProgress(this.this$0));
            r19.drawRoundRect(r3, r4, r4, PaintView.m20112$$Nest$fgettoolsPaint(this.this$0));
            if (r2.getChildCount() >= 1) goto L5;
            return;
        L5:
            if (PaintView.m20113$$Nest$fgettoolsTransformProgress(this.this$0) == 1.0f) goto L45;
            r19.save();
            r19.translate(r2.getLeft(), r2.getTop());
            View r5 = r2.getChildAt(0);
            if ((r2 instanceof PaintTextOptionsView) == false) goto L9;
            r5 = ((PaintTextOptionsView) r2).getColorClickableView();
        L9:
            View r8 = r5;
            if (r8.getAlpha() == 0.0f) goto L42;
            r19.scale(r8.getScaleX(), r8.getScaleY(), r8.getPivotX(), r8.getPivotY());
            PaintView.m20085$$Nest$fgetcolorPickerRainbowPaint(this.this$0).setAlpha((int) (((1.0f - PaintView.m20113$$Nest$fgettoolsTransformProgress(this.this$0)) * r8.getAlpha()) * 255.0f));
            int r52 = (r8.getWidth() - r8.getPaddingLeft()) - r8.getPaddingRight();
            int r6 = (r8.getHeight() - r8.getPaddingTop()) - r8.getPaddingBottom();
            float r10 = (r8.getX() + r8.getPaddingLeft()) + (r52 / 2.0f);
            float r11 = (r8.getY() + r8.getPaddingTop()) + (r6 / 2.0f);
            int r13 = PaintView.m20086$$Nest$fgetcolorSwatch(this.this$0).color;
            if (PaintView.m20107$$Nest$fgettabsNewSelectedIndex(this.this$0) == (-1)) goto L21;
            PaintView r14 = this.this$0;
            ViewGroup r142 = (ViewGroup) PaintView.m20130$$Nest$mgetBarView(r14, PaintView.m20107$$Nest$fgettabsNewSelectedIndex(r14));
            if (r142 != null) goto L16;
            ViewGroup r15 = r2;
        L17:
            View r152 = r15.getChildAt(0);
            float r16 = 1.0f;
            if ((r142 instanceof PaintTextOptionsView) == false) goto L20;
            r152 = ((PaintTextOptionsView) r142).getColorClickableView();
        L20:
            r10 = AndroidUtilities.lerp(r10, (r152.getX() + r152.getPaddingLeft()) + (((r152.getWidth() - r152.getPaddingLeft()) - r152.getPaddingRight()) / 2.0f), PaintView.m20110$$Nest$fgettabsSelectionProgress(this.this$0));
            r11 = AndroidUtilities.lerp(r11, (r152.getY() + r152.getPaddingTop()) + (((r152.getHeight() - r152.getPaddingTop()) - r152.getPaddingBottom()) / 2.0f), PaintView.m20110$$Nest$fgettabsSelectionProgress(this.this$0));
        L23:
            if (PaintView.m20089$$Nest$fgetcolorsListView(this.this$0) != null) goto L25;
        L27:
            checkRainbow(r10, r11);
            float r22 = (Math.min(r52, r6) / 2.0f) - AndroidUtilities.dp(0.5f);
            if (PaintView.m20089$$Nest$fgetcolorsListView(this.this$0) != null) goto L30;
        L32:
            float r7 = r22;
            r3.set(r10 - r7, r11 - r7, r10 + r7, r11 + r7);
            r19.drawArc(r3, 0.0f, 360.0f, false, PaintView.m20085$$Nest$fgetcolorPickerRainbowPaint(this.this$0));
            PaintView.m20088$$Nest$fgetcolorSwatchPaint(this.this$0).setColor(r13);
            PaintView.m20088$$Nest$fgetcolorSwatchPaint(this.this$0).setAlpha((int) (PaintView.m20088$$Nest$fgetcolorSwatchPaint(this.this$0).getAlpha() * r8.getAlpha()));
            PaintView.m20087$$Nest$fgetcolorSwatchOutlinePaint(this.this$0).setColor(r13);
            PaintView.m20087$$Nest$fgetcolorSwatchOutlinePaint(this.this$0).setAlpha((int) (r8.getAlpha() * 255.0f));
            float r32 = r7 - AndroidUtilities.dp(3.0f);
            if (PaintView.m20089$$Nest$fgetcolorsListView(this.this$0) != null) goto L35;
        L37:
            PaintColorsListView.drawColorCircle(r19, r10, r11, r32, PaintView.m20088$$Nest$fgetcolorSwatchPaint(this.this$0).getColor());
            if (PaintView.m20089$$Nest$fgetcolorsListView(this.this$0) == null) goto L42;
            if (PaintView.m20089$$Nest$fgetcolorsListView(this.this$0).getSelectedColorIndex() != 0) goto L42;
            PaintView.m20087$$Nest$fgetcolorSwatchOutlinePaint(this.this$0).setAlpha((int) ((PaintView.m20087$$Nest$fgetcolorSwatchOutlinePaint(this.this$0).getAlpha() * PaintView.m20113$$Nest$fgettoolsTransformProgress(this.this$0)) * r8.getAlpha()));
            r19.drawCircle(r10, r11, r7 - ((AndroidUtilities.dp(3.0f) + PaintView.m20087$$Nest$fgetcolorSwatchOutlinePaint(this.this$0).getStrokeWidth()) * (r16 - PaintView.m20113$$Nest$fgettoolsTransformProgress(this.this$0))), PaintView.m20087$$Nest$fgetcolorSwatchOutlinePaint(this.this$0));
            goto L42
        L35:
            if (PaintView.m20089$$Nest$fgetcolorsListView(this.this$0).getSelectedColorIndex() == 0) goto L37;
            r32 = AndroidUtilities.lerp(r7 - AndroidUtilities.dp(3.0f), AndroidUtilities.dp(2.0f) + r7, PaintView.m20113$$Nest$fgettoolsTransformProgress(this.this$0));
            goto L37
        L30:
            if (PaintView.m20089$$Nest$fgetcolorsListView(this.this$0).getChildCount() <= 0) goto L32;
            View r42 = PaintView.m20089$$Nest$fgetcolorsListView(this.this$0).getChildAt(0);
            r22 = AndroidUtilities.lerp(r22, (Math.min((r42.getWidth() - r42.getPaddingLeft()) - r42.getPaddingRight(), (r42.getHeight() - r42.getPaddingTop()) - r42.getPaddingBottom()) / 2.0f) - AndroidUtilities.dp(2.0f), PaintView.m20113$$Nest$fgettoolsTransformProgress(this.this$0));
            goto L32
        L25:
            if (PaintView.m20089$$Nest$fgetcolorsListView(this.this$0).getChildCount() <= 0) goto L27;
            View r72 = PaintView.m20089$$Nest$fgetcolorsListView(this.this$0).getChildAt(0);
            r10 = AndroidUtilities.lerp(r10, ((PaintView.m20089$$Nest$fgetcolorsListView(this.this$0).getX() - r2.getLeft()) + r72.getX()) + (r72.getWidth() / 2.0f), PaintView.m20113$$Nest$fgettoolsTransformProgress(this.this$0));
            r11 = AndroidUtilities.lerp(r11, ((PaintView.m20089$$Nest$fgetcolorsListView(this.this$0).getY() - r2.getTop()) + r72.getY()) + (r72.getHeight() / 2.0f), PaintView.m20113$$Nest$fgettoolsTransformProgress(this.this$0));
            r13 = ColorUtils.blendARGB(PaintView.m20086$$Nest$fgetcolorSwatch(this.this$0).color, this.val$palette.getColor(0), PaintView.m20113$$Nest$fgettoolsTransformProgress(this.this$0));
            goto L27
        L16:
            r15 = r142;
            goto L17
        L21:
            r16 = 1.0f;
        L42:
            r19.restore();
            return;
        }

        @Override // android.view.View
        public void setTranslationY(float r1) {
            super.setTranslationY(r1);
            if (PaintView.m20099$$Nest$fgetoverlayLayout(this.this$0) == null) goto L6;
            PaintView.m20099$$Nest$fgetoverlayLayout(this.this$0).invalidate();
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
        final /* synthetic */ PaintView this$0;

        /* renamed from: org.telegram.ui.Stories.recorder.PaintView$PopupButton$1, reason: invalid class name */
        public class AnonymousClass1 extends FrameLayout {
            Path path;
            final /* synthetic */ PopupButton this$1;
            final /* synthetic */ PaintView val$this$0;

            public AnonymousClass1(PopupButton r1, Context r2, PaintView r3) {
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

        /* renamed from: org.telegram.ui.Stories.recorder.PaintView$PopupButton$2, reason: invalid class name */
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

        public static /* synthetic */ void $r8$lambda$TG1IYFyJ6iV5CqXtUkA_W7ErEPc(PopupButton r0, boolean r1, ValueAnimator r2) {
            r0.lambda$setIcon$0(r1, r2);
        }

        public PopupButton(PaintView r12, Context r13) {
            this.this$0 = r12;
            super(r13);
            setOrientation(0);
            setBackground(Theme.getSelectorDrawable(Theme.getColor(Theme.key_listSelector, PaintView.m20105$$Nest$fgetresourcesProvider(r12)), false));
            AnonymousClass1 r0 = new AnonymousClass1(this, r13, r12);
            this.imagesView = r0;
            addView(r0, LayoutHelper.createLinear(-2, -2, 19, 16, 0, 16, 0));
            ImageView r02 = new ImageView(r13);
            this.imageView = r02;
            ImageView.ScaleType r1 = ImageView.ScaleType.CENTER;
            r02.setScaleType(r1);
            ImageView r03 = this.imageView;
            int r2 = Theme.key_actionBarDefaultSubmenuItem;
            r03.setColorFilter(PaintView.m20132$$Nest$mgetThemedColor(r12, r2));
            this.imagesView.addView(this.imageView, LayoutHelper.createFrame(-2, -2, 17));
            ImageView r04 = new ImageView(r13);
            this.image2View = r04;
            r04.setScaleType(r1);
            this.image2View.setColorFilter(PaintView.m20132$$Nest$mgetThemedColor(r12, r2));
            this.image2View.setVisibility(8);
            this.imagesView.addView(this.image2View, LayoutHelper.createFrame(-2, -2, 17));
            TextView r05 = new TextView(r13);
            this.textView = r05;
            r05.setTextColor(PaintView.m20132$$Nest$mgetThemedColor(r12, r2));
            this.textView.setTextSize(1, 16.0f);
            addView(this.textView, LayoutHelper.createLinear(-2, -2, 19, 0, 0, 16, 0));
            ImageView r06 = new ImageView(r13);
            this.checkView = r06;
            r06.setImageResource(R.drawable.msg_text_check);
            this.checkView.setScaleType(r1);
            this.checkView.setColorFilter(new PorterDuffColorFilter(PaintView.m20132$$Nest$mgetThemedColor(r12, Theme.key_radioBackgroundChecked), PorterDuff.Mode.MULTIPLY));
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
            if (PaintView.m20102$$Nest$fgetpopupWindow(this.this$0) == null) goto L8;
            if (PaintView.m20102$$Nest$fgetpopupWindow(this.this$0).isShowing() == false) goto L8;
            PaintView.m20102$$Nest$fgetpopupWindow(this.this$0).dismiss(true);
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
            r22.addUpdateListener(new PaintView$PopupButton$$ExternalSyntheticLambda0(this, r3));
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

    public class PopupWindowLayout extends ActionBarPopupWindow.ActionBarPopupWindowLayout {
        private final ButtonBounce bounce;
        private float cx;
        private float cy;
        public boolean enableBounce;
        final /* synthetic */ PaintView this$0;

        public PopupWindowLayout(PaintView r3, Context r4) {
            this.this$0 = r3;
            super(r4, PaintView.m20105$$Nest$fgetresourcesProvider(r3));
            this.bounce = new ButtonBounce(this, 1.5f, 1.0f, 2.0f);
            this.enableBounce = true;
        }

        @Override // org.telegram.ui.ActionBar.ActionBarPopupWindow.ActionBarPopupWindowLayout, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r5) {
            float r0 = this.bounce.getScale(0.05f);
            if (r0 >= 1.0f) goto L5;
            r5.save();
            r5.scale(r0, r0, this.cx, this.cy);
        L5:
            super.dispatchDraw(r5);
            if (r0 >= 1.0f) goto L9;
            r5.restore();
            return;
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r3) {
            if (r3.getAction() != 0) goto L6;
            this.cx = r3.getX();
            this.cy = r3.getY();
            this.bounce.setPressed(this.enableBounce);
        L11:
            return super.dispatchTouchEvent(r3);
        L6:
            if (r3.getAction() != 1) goto L8;
        L9:
            this.bounce.setPressed(false);
            goto L11
        L8:
            if (r3.getAction() != 3) goto L11;
            goto L9
        }

        @Override // org.telegram.ui.ActionBar.ActionBarPopupWindow.ActionBarPopupWindowLayout, android.widget.FrameLayout, android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, r2);
        }
    }

    public static class StickerPosition {
        private float angle;
        private PointF position;
        private float scale;

        /* renamed from: -$$Nest$fgetangle, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m20141$$Nest$fgetangle(StickerPosition r0) {
            return r0.angle;
        }

        /* renamed from: -$$Nest$fgetposition, reason: not valid java name */
        public static /* bridge */ /* synthetic */ PointF m20142$$Nest$fgetposition(StickerPosition r0) {
            return r0.position;
        }

        /* renamed from: -$$Nest$fgetscale, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m20143$$Nest$fgetscale(StickerPosition r0) {
            return r0.scale;
        }

        public StickerPosition(PointF r1, float r2, float r3) {
            this.position = r1;
            this.scale = r2;
            this.angle = r3;
        }
    }

    /* renamed from: $r8$lambda$-InNAIiy4QAxYy8ZsFmXtkgRzBE, reason: not valid java name */
    public static /* synthetic */ void m20060$r8$lambda$InNAIiy4QAxYy8ZsFmXtkgRzBE(PaintView r0) {
        r0.lambda$new$0();
    }

    public static /* synthetic */ void $r8$lambda$0_SmkwT3n2JKj64BvybnBBKmD9o(PaintView r0, View r1) {
        r0.lambda$setupTabsLayout$17(r1);
    }

    public static /* synthetic */ void $r8$lambda$20js8IloXFClVPDoRJFvuWvcdD8(PaintView r0, Integer r1, int r2, ValueAnimator r3) {
        r0.lambda$setCurrentSwatch$40(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$2SvaOkO1mBABSJ_b3FG_MtjX4Wg(PaintView r0, LinkView r1, LinkPreview.WebPagePreview r2) {
        r0.lambda$showLinkAlert$28(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$3Y9Vss2yxLSZGnbBDgtb6c4uTX8(PaintView r0, boolean r1, DynamicAnimation r2, boolean r3, float r4, float r5) {
        r0.lambda$showColorList$39(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$4S-CbcWXl07qkA4BR2-knLafh2E, reason: not valid java name */
    public static /* synthetic */ void m20061$r8$lambda$4SCbcWXl07qkA4BR2knLafh2E(PaintView r0, EntityView r1, View r2) {
        r0.lambda$showMenuForEntity$46(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$4prAKtqLQMMA6mbtJWZquhRe1gw(PaintView r0, View r1) {
        r0.lambda$appearAnimation$59(r1);
    }

    /* renamed from: $r8$lambda$85y9C4aRdJ9Ba92-DDHYP2FGkl0, reason: not valid java name */
    public static /* synthetic */ void m20062$r8$lambda$85y9C4aRdJ9Ba92DDHYP2FGkl0(PaintView r0, DialogInterface r1) {
        r0.lambda$showLocationAlert$30(r1);
    }

    public static /* synthetic */ void $r8$lambda$86xmefFfL__OWZpdPVD36EWl75A(PaintView r0) {
        r0.lambda$openStickersView$21();
    }

    /* renamed from: $r8$lambda$8GsKFCzx-NCF41lBQcRtPWXAmmE, reason: not valid java name */
    public static /* synthetic */ void m20063$r8$lambda$8GsKFCzxNCF41lBQcRtPWXAmmE(PaintView r0) {
        r0.lambda$detectFaces$32();
    }

    public static /* synthetic */ void $r8$lambda$92CRCPYGu23VvEwyxVXqo_3sMlQ(PaintView r0, StoryRecorder.WindowView r1, Integer r2) {
        r0.lambda$new$13(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$99gGnOV4l55xPUUWQjnKOVlFTaQ() {
        Looper r0 = Looper.myLooper();
        if (r0 == null) goto L6;
        r0.quit();
        return;
    }

    public static /* synthetic */ void $r8$lambda$9q1TJzwExy8z1J7KH94nFfk87fg(PaintView r0) {
        r0.lambda$onAddButtonPressed$44();
    }

    public static /* synthetic */ void $r8$lambda$BWaM3Roso9KsiZXbON0IlTwlI8o(PaintView r0) {
        r0.lambda$createText$15();
    }

    public static /* synthetic */ void $r8$lambda$BbXTXSM44UuJEDk53LCCEb5rxP0(Runnable r0, View r1) {
        r0.run();
    }

    public static /* synthetic */ boolean $r8$lambda$CUBQSfRVW1YUo8u3JGxq6vlNp7w(PaintView r0, View r1, MotionEvent r2) {
        return r0.lambda$showPopup$56(r1, r2);
    }

    /* renamed from: $r8$lambda$D17484QFTfE--qCwSz54nVqEC5g, reason: not valid java name */
    public static /* synthetic */ void m20064$r8$lambda$D17484QFTfEqCwSz54nVqEC5g(PaintView r0, boolean r1, DynamicAnimation r2, boolean r3, float r4, float r5) {
        r0.lambda$showTypefaceMenu$37(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$DZzo_UxJVi16jv-cEtedtR2LonE, reason: not valid java name */
    public static /* synthetic */ void m20065$r8$lambda$DZzo_UxJVi16jvcEtedtR2LonE(PaintView r0, View r1) {
        r0.lambda$new$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$FHU_mMmK_3YnTNs1PPNtH6WzT5U(PaintView r0) {
        r0.lambda$showPopup$58();
    }

    /* renamed from: $r8$lambda$FVuPW-8_UDGrNKelICBLpHINndI, reason: not valid java name */
    public static /* synthetic */ void m20066$r8$lambda$FVuPW8_UDGrNKelICBLpHINndI(PaintView r0, View r1, View r2, ValueAnimator r3) {
        r0.lambda$switchTab$20(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$G5TL8bamMW4HAN33FWuyrZJK-mI, reason: not valid java name */
    public static /* synthetic */ void m20067$r8$lambda$G5TL8bamMW4HAN33FWuyrZJKmI(PaintView r0, int r1, int r2, ValueAnimator r3) {
        r0.lambda$setNewColor$14(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$KIVwaOW2tvNDez8b8WR3yqZOJOc(PaintView r0, View r1) {
        r0.lambda$new$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$Kx3Q6vfoeibO8FLzXgOFlo1B3yU(PaintView r0, View r1) {
        r0.lambda$deleteRound$60(r1);
    }

    /* renamed from: $r8$lambda$LINgqlGPo-mydP74Iz9aT2FeFLU, reason: not valid java name */
    public static /* synthetic */ void m20068$r8$lambda$LINgqlGPomydP74Iz9aT2FeFLU(PaintView r0, KeyEvent r1) {
        r0.lambda$showPopup$57(r1);
    }

    public static /* synthetic */ void $r8$lambda$MWFZVXzEWo135L2hI8E18JhZ6RQ(PaintView r0, View r1, int r2) {
        r0.lambda$new$7(r1, r2);
    }

    /* renamed from: $r8$lambda$MaJjG7-bHGR2GrpGhfgDH5tUBMw, reason: not valid java name */
    public static /* synthetic */ void m20069$r8$lambda$MaJjG7bHGR2GrpGhfgDH5tUBMw(PaintView r0, ValueAnimator r1) {
        r0.lambda$showReactionsLayout$16(r1);
    }

    public static /* synthetic */ void $r8$lambda$MnIakh0IaNmfEaJiLKF6P__DezU(PaintView r0, View r1) {
        r0.lambda$setupTabsLayout$18(r1);
    }

    public static /* synthetic */ void $r8$lambda$NixFUr2XCJLsAjkZstohe5th7C0(PaintView r0, View r1) {
        r0.lambda$new$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$Pp8J6MyJ5Py0MRFajamAxPe0QCE(PaintView r0, DialogInterface r1) {
        r0.lambda$showLinkAlert$29(r1);
    }

    public static /* synthetic */ void $r8$lambda$Q2G7Q4xWgOnKBaEUAajz1wO7Ij0(PaintView r0) {
        r0.lambda$openStickersView$26();
    }

    public static /* synthetic */ void $r8$lambda$QViiTPy_7copOyEQo3U4DQVjc68(PaintView r0, boolean[] r1, int r2, DialogInterface r3) {
        r0.lambda$openStickersView$22(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$QgZACO4zVBx_Bnl7TAfGDW2LDQg(PaintView r0, EmojiBottomSheet r1, Weather.State r2) {
        r0.lambda$openStickersView$25(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Qloqyr_WQYsxhJ669oiV1HJEQCs(PaintView r0, int r1) {
        r0.lambda$new$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$QpgfU59me3VeJC_YvpjOHr4I4zA(PaintView r0, EntityView r1, View r2) {
        r0.lambda$showMenuForEntity$49(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$U7aNMUZ6c0UTM3QXXXoin2kKMm8(PaintView r0, Brush.Shape r1, int r2) {
        r0.lambda$onAddButtonPressed$42(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Vzyd3szRADHuuOGwFiybftRbTRI(PaintView r0, View r1) {
        r0.lambda$new$9(r1);
    }

    /* renamed from: $r8$lambda$WhxUXjInUzxzSblDc7e-y9UGB1k, reason: not valid java name */
    public static /* synthetic */ void m20070$r8$lambda$WhxUXjInUzxzSblDc7ey9UGB1k(PaintView r0, EntityView r1, TLRPC.MessageMedia r2, TL_stories.MediaArea r3) {
        r0.lambda$showMenuForEntity$48(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$Wm2QbA8qBi5GHii-IDGk08jHrKQ, reason: not valid java name */
    public static /* synthetic */ void m20071$r8$lambda$Wm2QbA8qBi5GHiiIDGk08jHrKQ(PaintView r0, boolean r1) {
        r0.lambda$updateTextDim$65(r1);
    }

    public static /* synthetic */ void $r8$lambda$YrYZ1HpHMANOuTFs2npzeR_8knI(PaintView r0, EntityView r1) {
        r0.lambda$registerRemovalUndo$62(r1);
    }

    /* renamed from: $r8$lambda$_-IBt9F14J-GSqycHSU485PaCgA, reason: not valid java name */
    public static /* synthetic */ void m20072$r8$lambda$_IBt9F14JGSqycHSU485PaCgA(PaintView r0, EntityView r1) {
        r0.lambda$showMenuForEntity$55(r1);
    }

    /* renamed from: $r8$lambda$a-P1BV9foUQnxrMZc_KvTr08b_k, reason: not valid java name */
    public static /* synthetic */ void m20073$r8$lambda$aP1BV9foUQnxrMZc_KvTr08b_k(PaintView r0, int r1) {
        r0.lambda$new$12(r1);
    }

    public static /* synthetic */ void $r8$lambda$a0jwkn2M04puOMBbJlufv4GzMkE(PaintView r0, RoundView r1) {
        r0.lambda$createRound$61(r1);
    }

    /* renamed from: $r8$lambda$a2yI_Vg-LBVhpY_tX7Nz_WYbJRE, reason: not valid java name */
    public static /* synthetic */ Boolean m20074$r8$lambda$a2yI_VgLBVhpY_tX7Nz_WYbJRE(PaintView r0, Object r1, TLRPC.Document r2, Boolean r3) {
        return r0.lambda$openStickersView$23(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$c1Kp-uUB7Fs651nzLxLBLsiGka8, reason: not valid java name */
    public static /* synthetic */ void m20075$r8$lambda$c1KpuUB7Fs651nzLxLBLsiGka8(PaintView r0, Integer r1) {
        r0.lambda$new$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$cFKMJJZ4GYzUpxfRWtkIsj9tYGE(PaintView r0, EntityView r1, View r2) {
        r0.lambda$showMenuForEntity$54(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$caPAt82CfPEmfyPiFMPxy3yVX6c(PaintView r0, View r1, boolean r2, boolean[] r3, float r4, float r5, DynamicAnimation r6, float r7, float r8) {
        r0.lambda$showColorList$38(r1, r2, r3, r4, r5, r6, r7, r8);
    }

    public static /* synthetic */ void $r8$lambda$cpuMxz2fzp032nEjQVls62hncDA(PaintView r0) {
        r0.lambda$showAudioAlert$31();
    }

    /* renamed from: $r8$lambda$dqcOgllBRYJUZSJPnzDLqkNG8-g, reason: not valid java name */
    public static /* synthetic */ void m20076$r8$lambda$dqcOgllBRYJUZSJPnzDLqkNG8g(PaintView r0, Context r1, Bitmap r2, PersistColorPalette r3, View r4) {
        r0.lambda$new$11(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$f4W-uqH2v2YzVy1jhk3CNUApq8k, reason: not valid java name */
    public static /* synthetic */ void m20077$r8$lambda$f4WuqH2v2YzVy1jhk3CNUApq8k(PaintView r0, EntityView r1, View r2) {
        r0.lambda$showMenuForEntity$50(r1, r2);
    }

    public static /* synthetic */ Boolean $r8$lambda$mx2WSLFWLeiBefTXEbVuYfHbyIQ(PaintView r0, boolean[] r1, EmojiBottomSheet r2, Integer r3) {
        return r0.lambda$openStickersView$27(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$nAgqg2q5BwmqCJSmn4IxHPJy5vY(PaintView r0, DynamicAnimation r1, float r2, float r3) {
        r0.lambda$showTypefaceMenu$36(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$o6kmXnagtqO3YCjdFew-SG6MlQY, reason: not valid java name */
    public static /* synthetic */ void m20078$r8$lambda$o6kmXnagtqO3YCjdFewSG6MlQY(PaintView r0, TLRPC.MessageMedia r1, TL_stories.MediaArea r2) {
        r0.lambda$openStickersView$24(r1, r2);
    }

    /* renamed from: $r8$lambda$rXE6osGVkCU9W-JqdkAGnZS6K7g, reason: not valid java name */
    public static /* synthetic */ void m20079$r8$lambda$rXE6osGVkCU9WJqdkAGnZS6K7g(PaintView r0, EntityView r1, View r2) {
        r0.lambda$showMenuForEntity$51(r1, r2);
    }

    /* renamed from: $r8$lambda$sTS0wZZOGPza-tjzUM4SHubLH3A, reason: not valid java name */
    public static /* synthetic */ boolean m20080$r8$lambda$sTS0wZZOGPzatjzUM4SHubLH3A(PaintView r0, View r1) {
        return r0.lambda$onAddButtonPressed$43(r1);
    }

    /* renamed from: $r8$lambda$teq-GakxMcJfwiz89jxVWeXtCOE, reason: not valid java name */
    public static /* synthetic */ void m20081$r8$lambda$teqGakxMcJfwiz89jxVWeXtCOE(PaintView r0, EntityView r1, View r2) {
        r0.lambda$showMenuForEntity$45(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$tqTIvKStAZDx8JRQ9r2NfbhjGxY(PaintView r0, View r1) {
        r0.lambda$setupTabsLayout$19(r1);
    }

    public static /* synthetic */ void $r8$lambda$vnT4oI3GE30JS4ywKDlP_OTnDVo(PaintView r0, View r1) {
        r0.lambda$new$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$wAHt8zQaNNxBnnQtgXTxTQDY9N8(PaintView r0, EntityView r1, View r2) {
        r0.lambda$showMenuForEntity$47(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$welPp_ExmPDu8B66M1Z3c8sB03Y(View r0) {
        PhotoViewer.getInstance().zoomOut();
    }

    public static /* synthetic */ void $r8$lambda$wtOQAWpIEPTaiT1vE59ceJ9mDtI(PaintView r0, PersistColorPalette r1, Integer r2) {
        r0.lambda$new$10(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$wxHGi8i4OfgeU_Z7xHxiinmBY4M(PaintView r0, ValueAnimator r1) {
        r0.lambda$showEmojiPopup$63(r1);
    }

    public static /* synthetic */ void $r8$lambda$x3v1LJA49EZsrQ1pGPzpUetkgR0(PaintView r0, ValueAnimator r1) {
        r0.lambda$hideEmojiPopup$64(r1);
    }

    /* renamed from: $r8$lambda$zX6SS3yE-d9LP_8jXtdF88QtxgY, reason: not valid java name */
    public static /* synthetic */ void m20082$r8$lambda$zX6SS3yEd9LP_8jXtdF88QtxgY(PaintView r0, PhotoView r1, View r2) {
        r0.lambda$showMenuForEntity$52(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$zmMYZP9571LIJsaNlOh8QrK0Jkw(PaintView r0, EntityView r1, View r2) {
        r0.lambda$showMenuForEntity$53(r1, r2);
    }

    /* renamed from: -$$Nest$fgetblurManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlurringShader.BlurManager m20083$$Nest$fgetblurManager(PaintView r0) {
        return r0.blurManager;
    }

    /* renamed from: -$$Nest$fgetbottomLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m20084$$Nest$fgetbottomLayout(PaintView r0) {
        return r0.bottomLayout;
    }

    /* renamed from: -$$Nest$fgetcolorPickerRainbowPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m20085$$Nest$fgetcolorPickerRainbowPaint(PaintView r0) {
        return r0.colorPickerRainbowPaint;
    }

    /* renamed from: -$$Nest$fgetcolorSwatch, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Swatch m20086$$Nest$fgetcolorSwatch(PaintView r0) {
        return r0.colorSwatch;
    }

    /* renamed from: -$$Nest$fgetcolorSwatchOutlinePaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m20087$$Nest$fgetcolorSwatchOutlinePaint(PaintView r0) {
        return r0.colorSwatchOutlinePaint;
    }

    /* renamed from: -$$Nest$fgetcolorSwatchPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m20088$$Nest$fgetcolorSwatchPaint(PaintView r0) {
        return r0.colorSwatchPaint;
    }

    /* renamed from: -$$Nest$fgetcolorsListView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PaintColorsListView m20089$$Nest$fgetcolorsListView(PaintView r0) {
        return r0.colorsListView;
    }

    /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20090$$Nest$fgetcurrentAccount(PaintView r0) {
        return r0.currentAccount;
    }

    /* renamed from: -$$Nest$fgetcurrentEntityView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EntityView m20091$$Nest$fgetcurrentEntityView(PaintView r0) {
        return r0.currentEntityView;
    }

    /* renamed from: -$$Nest$fgetdestroyed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20092$$Nest$fgetdestroyed(PaintView r0) {
        return r0.destroyed;
    }

    /* renamed from: -$$Nest$fgeth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20093$$Nest$fgeth(PaintView r0) {
        return r0.h;
    }

    /* renamed from: -$$Nest$fgetignoreToolChangeAnimationOnce, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20094$$Nest$fgetignoreToolChangeAnimationOnce(PaintView r0) {
        return r0.ignoreToolChangeAnimationOnce;
    }

    /* renamed from: -$$Nest$fgetisBot, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20095$$Nest$fgetisBot(PaintView r0) {
        return r0.isBot;
    }

    /* renamed from: -$$Nest$fgetisTypefaceMenuShown, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20096$$Nest$fgetisTypefaceMenuShown(PaintView r0) {
        return r0.isTypefaceMenuShown;
    }

    /* renamed from: -$$Nest$fgetkeyboardVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20097$$Nest$fgetkeyboardVisible(PaintView r0) {
        return r0.keyboardVisible;
    }

    /* renamed from: -$$Nest$fgetopenKeyboardRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m20098$$Nest$fgetopenKeyboardRunnable(PaintView r0) {
        return r0.openKeyboardRunnable;
    }

    /* renamed from: -$$Nest$fgetoverlayLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m20099$$Nest$fgetoverlayLayout(PaintView r0) {
        return r0.overlayLayout;
    }

    /* renamed from: -$$Nest$fgetpaintToolsView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PaintToolsView m20100$$Nest$fgetpaintToolsView(PaintView r0) {
        return r0.paintToolsView;
    }

    /* renamed from: -$$Nest$fgetpipetteContainerLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m20101$$Nest$fgetpipetteContainerLayout(PaintView r0) {
        return r0.pipetteContainerLayout;
    }

    /* renamed from: -$$Nest$fgetpopupWindow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarPopupWindow m20102$$Nest$fgetpopupWindow(PaintView r0) {
        return r0.popupWindow;
    }

    /* renamed from: -$$Nest$fgetrenderInputView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m20103$$Nest$fgetrenderInputView(PaintView r0) {
        return r0.renderInputView;
    }

    /* renamed from: -$$Nest$fgetrenderView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RenderView m20104$$Nest$fgetrenderView(PaintView r0) {
        return r0.renderView;
    }

    /* renamed from: -$$Nest$fgetresourcesProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Theme.ResourcesProvider m20105$$Nest$fgetresourcesProvider(PaintView r0) {
        return r0.resourcesProvider;
    }

    /* renamed from: -$$Nest$fgettabsLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m20106$$Nest$fgettabsLayout(PaintView r0) {
        return r0.tabsLayout;
    }

    /* renamed from: -$$Nest$fgettabsNewSelectedIndex, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20107$$Nest$fgettabsNewSelectedIndex(PaintView r0) {
        return r0.tabsNewSelectedIndex;
    }

    /* renamed from: -$$Nest$fgettabsSelectedIndex, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20108$$Nest$fgettabsSelectedIndex(PaintView r0) {
        return r0.tabsSelectedIndex;
    }

    /* renamed from: -$$Nest$fgettabsSelectionAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m20109$$Nest$fgettabsSelectionAnimator(PaintView r0) {
        return r0.tabsSelectionAnimator;
    }

    /* renamed from: -$$Nest$fgettabsSelectionProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m20110$$Nest$fgettabsSelectionProgress(PaintView r0) {
        return r0.tabsSelectionProgress;
    }

    /* renamed from: -$$Nest$fgettextOptionsView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PaintTextOptionsView m20111$$Nest$fgettextOptionsView(PaintView r0) {
        return r0.textOptionsView;
    }

    /* renamed from: -$$Nest$fgettoolsPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m20112$$Nest$fgettoolsPaint(PaintView r0) {
        return r0.toolsPaint;
    }

    /* renamed from: -$$Nest$fgettoolsTransformProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m20113$$Nest$fgettoolsTransformProgress(PaintView r0) {
        return r0.toolsTransformProgress;
    }

    /* renamed from: -$$Nest$fgettypefaceListView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PaintTypefaceListView m20114$$Nest$fgettypefaceListView(PaintView r0) {
        return r0.typefaceListView;
    }

    /* renamed from: -$$Nest$fgettypefaceMenuBackgroundPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m20115$$Nest$fgettypefaceMenuBackgroundPaint(PaintView r0) {
        return r0.typefaceMenuBackgroundPaint;
    }

    /* renamed from: -$$Nest$fgettypefaceMenuOutlinePaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m20116$$Nest$fgettypefaceMenuOutlinePaint(PaintView r0) {
        return r0.typefaceMenuOutlinePaint;
    }

    /* renamed from: -$$Nest$fgettypefaceMenuTransformProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m20117$$Nest$fgettypefaceMenuTransformProgress(PaintView r0) {
        return r0.typefaceMenuTransformProgress;
    }

    /* renamed from: -$$Nest$fgetundoStore, reason: not valid java name */
    public static /* bridge */ /* synthetic */ UndoStore m20118$$Nest$fgetundoStore(PaintView r0) {
        return r0.undoStore;
    }

    /* renamed from: -$$Nest$fgetw, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20119$$Nest$fgetw(PaintView r0) {
        return r0.w;
    }

    /* renamed from: -$$Nest$fgetwaitingForKeyboardOpen, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20120$$Nest$fgetwaitingForKeyboardOpen(PaintView r0) {
        return r0.waitingForKeyboardOpen;
    }

    /* renamed from: -$$Nest$fgetweightChooserView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PaintWeightChooserView m20121$$Nest$fgetweightChooserView(PaintView r0) {
        return r0.weightChooserView;
    }

    /* renamed from: -$$Nest$fputh, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20122$$Nest$fputh(PaintView r0, int r1) {
        r0.h = r1;
    }

    /* renamed from: -$$Nest$fputignoreToolChangeAnimationOnce, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20123$$Nest$fputignoreToolChangeAnimationOnce(PaintView r0, boolean r1) {
        r0.ignoreToolChangeAnimationOnce = r1;
    }

    /* renamed from: -$$Nest$fputisAnimatePopupClosing, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20124$$Nest$fputisAnimatePopupClosing(PaintView r0, boolean r1) {
        r0.isAnimatePopupClosing = r1;
    }

    /* renamed from: -$$Nest$fputreactionShowProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20125$$Nest$fputreactionShowProgress(PaintView r0, float r1) {
        r0.reactionShowProgress = r1;
    }

    /* renamed from: -$$Nest$fputtabsNewSelectedIndex, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20126$$Nest$fputtabsNewSelectedIndex(PaintView r0, int r1) {
        r0.tabsNewSelectedIndex = r1;
    }

    /* renamed from: -$$Nest$fputtabsSelectedIndex, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20127$$Nest$fputtabsSelectedIndex(PaintView r0, int r1) {
        r0.tabsSelectedIndex = r1;
    }

    /* renamed from: -$$Nest$fputtabsSelectionAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20128$$Nest$fputtabsSelectionAnimator(PaintView r0, ValueAnimator r1) {
        r0.tabsSelectionAnimator = r1;
    }

    /* renamed from: -$$Nest$fputw, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20129$$Nest$fputw(PaintView r0, int r1) {
        r0.w = r1;
    }

    /* renamed from: -$$Nest$mgetBarView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m20130$$Nest$mgetBarView(PaintView r0, int r1) {
        return r0.getBarView(r1);
    }

    /* renamed from: -$$Nest$mgetBarView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ViewGroup m20131$$Nest$mgetBarView(PaintView r0) {
        return r0.getBarView();
    }

    /* renamed from: -$$Nest$mgetThemedColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20132$$Nest$mgetThemedColor(PaintView r0, int r1) {
        return r0.getThemedColor(r1);
    }

    /* renamed from: -$$Nest$mhideEmojiView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20133$$Nest$mhideEmojiView(PaintView r0) {
        r0.hideEmojiView();
    }

    /* renamed from: -$$Nest$msetCurrentSwatch, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20134$$Nest$msetCurrentSwatch(PaintView r0, Swatch r1, boolean r2) {
        r0.setCurrentSwatch(r1, r2);
    }

    /* renamed from: -$$Nest$msetNewColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20135$$Nest$msetNewColor(PaintView r0, int r1) {
        r0.setNewColor(r1);
    }

    /* renamed from: -$$Nest$msetupEntities, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20136$$Nest$msetupEntities(PaintView r0) {
        r0.setupEntities();
    }

    /* renamed from: -$$Nest$mshowColorList, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20137$$Nest$mshowColorList(PaintView r0, boolean r1) {
        r0.showColorList(r1);
    }

    /* renamed from: -$$Nest$mshowTypefaceMenu, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20138$$Nest$mshowTypefaceMenu(PaintView r0, boolean r1) {
        r0.showTypefaceMenu(r1);
    }

    /* renamed from: -$$Nest$mswitchTab, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20139$$Nest$mswitchTab(PaintView r0, int r1) {
        r0.switchTab(r1);
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public PaintView(Context r29, boolean r30, File r31, boolean r32, boolean r33, StoryRecorder.WindowView r34, Activity r35, int r36, Bitmap r37, Bitmap r38, Bitmap r39, int r40, ArrayList<VideoEditedInfo.MediaEntity> r41, StoryEntry r42, int r43, int r44, MediaController.CropState r45, Runnable r46, BlurringShader.BlurManager r47, Theme.ResourcesProvider r48, PreviewView.TextureViewHolder r49, PreviewView r50) {
        super(r29, r35, true);
        this.tabsSelectedIndex = 0;
        this.tabsNewSelectedIndex = -1;
        AnonymousClass1 r15 = new AnonymousClass1(this);
        this.weightDefaultValueOverride = r15;
        this.typefaceMenuOutlinePaint = new Paint(1);
        this.typefaceMenuBackgroundPaint = new Paint(1);
        this.colorPickerRainbowPaint = new Paint(1);
        this.colorSwatchPaint = new Paint(1);
        this.colorSwatchOutlinePaint = new Paint(1);
        this.colorSwatch = new Swatch(-1, 1.0f, 0.016773745f);
        this.fillShapes = false;
        this.toolsPaint = new Paint(1);
        this.zoomOutVisible = false;
        this.points = new float[2];
        this.isCoverPreview = false;
        this.pos = new int[2];
        this.openKeyboardRunnable = new AnonymousClass28(this);
        setDelegate(this);
        this.blurManager = r47;
        this.videoTextureHolder = r49;
        this.fileFromGallery = r30;
        this.file = r31;
        this.isVideo = r32;
        this.isBot = r33;
        this.parent = r34;
        this.w = r43;
        this.h = r44;
        this.previewView = r50;
        this.currentAccount = r36;
        this.resourcesProvider = new AnonymousClass2(this, r48);
        this.currentCropState = r45;
        this.inBubbleMode = r29 instanceof BubbleActivity;
        PersistColorPalette r3 = PersistColorPalette.getInstance(r36);
        r3.resetCurrentColor();
        this.colorSwatch.color = r3.getCurrentColor();
        this.colorSwatch.brushWeight = r3.getCurrentWeight();
        this.queue = new DispatchQueue("Paint");
        this.bitmapToEdit = r37;
        this.blurBitmapToEdit = r38;
        this.facesBitmap = r39;
        this.originalBitmapRotation = r40;
        UndoStore r4 = new UndoStore();
        this.undoStore = r4;
        r4.setDelegate(new PaintView$$ExternalSyntheticLambda17(this));
        View r410 = new View(r29);
        this.textDim = r410;
        r410.setVisibility(8);
        this.textDim.setBackgroundColor(1291845632);
        this.textDim.setAlpha(0.0f);
        Painting r310 = new Painting(getPaintingSize(), r39, r40, r47);
        Bitmap r411 = this.bitmapToEdit;
        Bitmap r5 = this.blurBitmapToEdit;
        if (r42 != null) goto L5;
    L8:
        BlurringShader.BlurManager r6 = r47;
    L9:
        AnonymousClass3 r412 = new AnonymousClass3(this, r29, r310, r411, r5, r6, r39);
        this.renderView = r412;
        r412.setDelegate(new AnonymousClass4(this, r46));
        this.renderView.setUndoStore(this.undoStore);
        this.renderView.setQueue(this.queue);
        this.renderView.setVisibility(4);
        AnonymousClass5 r0 = new AnonymousClass5(this, r29);
        this.renderInputView = r0;
        r0.setVisibility(4);
        this.entitiesView = new AnonymousClass7(this, r29, new AnonymousClass6(this));
        this.initialEntry = r42;
        this.initialEntities = r41;
        if (this.w > 0) goto L12;
    L14:
        this.entitiesView.setVisibility(4);
        this.selectionContainerView = new AnonymousClass8(this, r29);
        FrameLayout r02 = new FrameLayout(r29);
        this.topLayout = r02;
        r02.setPadding(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(12.0f));
        FrameLayout r03 = this.topLayout;
        GradientDrawable.Orientation r413 = GradientDrawable.Orientation.TOP_BOTTOM;
        r03.setBackground(new GradientDrawable(r413, new int[]{TLObject.FLAG_30, 0}));
        addView(this.topLayout, LayoutHelper.createFrame(-1, -2, 48));
        ImageView r04 = new ImageView(r29);
        this.undoButton = r04;
        r04.setImageResource(R.drawable.photo_undo2);
        this.undoButton.setPadding(AndroidUtilities.dp(3.0f), AndroidUtilities.dp(3.0f), AndroidUtilities.dp(3.0f), AndroidUtilities.dp(3.0f));
        this.undoButton.setBackground(Theme.createSelectorDrawable(1090519039));
        this.undoButton.setOnClickListener(new PaintView$$ExternalSyntheticLambda21(this));
        this.undoButton.setAlpha(0.6f);
        this.undoButton.setClickable(false);
        this.topLayout.addView(this.undoButton, LayoutHelper.createFrame(32, 32.0f, 51, 12.0f, 0.0f, 0.0f, 0.0f));
        LinearLayout r05 = new LinearLayout(r29);
        this.zoomOutButton = r05;
        r05.setOrientation(0);
        this.zoomOutButton.setBackground(Theme.createSelectorDrawable(822083583, 7));
        this.zoomOutButton.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), 0);
        TextView r06 = new TextView(r29);
        this.zoomOutText = r06;
        r06.setTextColor(-1);
        this.zoomOutText.setTypeface(AndroidUtilities.bold());
        this.zoomOutText.setTextSize(1, 16.0f);
        this.zoomOutText.setText(LocaleController.getString(R.string.PhotoEditorZoomOut));
        ImageView r07 = new ImageView(r29);
        this.zoomOutImage = r07;
        r07.setImageResource(R.drawable.photo_zoomout);
        this.zoomOutButton.addView(this.zoomOutImage, LayoutHelper.createLinear(24, 24, 16, 0, 0, 8, 0));
        this.zoomOutButton.addView(this.zoomOutText, LayoutHelper.createLinear(-2, -2, 16));
        this.zoomOutButton.setAlpha(0.0f);
        this.zoomOutButton.setOnClickListener(new PaintView$$ExternalSyntheticLambda22());
        this.topLayout.addView(this.zoomOutButton, LayoutHelper.createFrame(-2, 32, 17));
        TextView r08 = new TextView(r29);
        this.undoAllButton = r08;
        r08.setBackground(Theme.createSelectorDrawable(822083583, 7));
        this.undoAllButton.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), 0);
        this.undoAllButton.setText(LocaleController.getString(R.string.PhotoEditorClearAll));
        this.undoAllButton.setGravity(16);
        this.undoAllButton.setTextColor(-1);
        this.undoAllButton.setTypeface(AndroidUtilities.bold());
        this.undoAllButton.setTextSize(1, 16.0f);
        this.undoAllButton.setOnClickListener(new PaintView$$ExternalSyntheticLambda23(this));
        this.undoAllButton.setAlpha(0.6f);
        this.topLayout.addView(this.undoAllButton, LayoutHelper.createFrame(-2, 32.0f, 5, 0.0f, 0.0f, 4.0f, 0.0f));
        TextView r09 = new TextView(r29);
        this.cancelTextButton = r09;
        r09.setBackground(Theme.createSelectorDrawable(822083583, 7));
        this.cancelTextButton.setText(LocaleController.getString(R.string.Clear));
        this.cancelTextButton.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), 0);
        this.cancelTextButton.setGravity(16);
        this.cancelTextButton.setTextColor(-1);
        this.cancelTextButton.setTypeface(AndroidUtilities.bold());
        this.cancelTextButton.setTextSize(1, 16.0f);
        this.cancelTextButton.setOnClickListener(new PaintView$$ExternalSyntheticLambda24(this));
        this.cancelTextButton.setAlpha(0.0f);
        this.cancelTextButton.setVisibility(8);
        this.topLayout.addView(this.cancelTextButton, LayoutHelper.createFrame(-2, 32.0f, 51, 4.0f, 0.0f, 0.0f, 0.0f));
        TextView r010 = new TextView(r29);
        this.doneTextButton = r010;
        r010.setBackground(Theme.createSelectorDrawable(822083583, 7));
        this.doneTextButton.setText(LocaleController.getString(R.string.Done));
        this.doneTextButton.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), 0);
        this.doneTextButton.setGravity(16);
        this.doneTextButton.setTextColor(-1);
        this.doneTextButton.setTypeface(AndroidUtilities.bold());
        this.doneTextButton.setTextSize(1, 16.0f);
        this.doneTextButton.setOnClickListener(new PaintView$$ExternalSyntheticLambda25(this));
        this.doneTextButton.setAlpha(0.0f);
        this.doneTextButton.setVisibility(8);
        this.topLayout.addView(this.doneTextButton, LayoutHelper.createFrame(-2, 32.0f, 5, 0.0f, 0.0f, 4.0f, 0.0f));
        AnonymousClass9 r011 = new AnonymousClass9(this, r29, r3);
        this.bottomLayout = r011;
        r011.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), 0);
        this.bottomLayout.setBackground(new GradientDrawable(r413, new int[]{0, Integer.MIN_VALUE}));
        addView(this.bottomLayout, LayoutHelper.createFrame(-1, 104, 80));
        if (r42 != null) goto L17;
    L22:
        boolean r414 = false;
    L23:
        PaintToolsView r012 = new PaintToolsView(r29, r414);
        this.paintToolsView = r012;
        r012.setPadding(AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(16.0f), 0);
        this.paintToolsView.setDelegate(this);
        this.paintToolsView.setSelectedIndex(1);
        this.bottomLayout.addView(this.paintToolsView, LayoutHelper.createFrame(-1, 48.0f));
        PaintTextOptionsView r013 = new PaintTextOptionsView(r29);
        this.textOptionsView = r013;
        r013.setPadding(AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(8.0f), 0);
        this.textOptionsView.setVisibility(8);
        this.textOptionsView.setDelegate(this);
        post(new PaintView$$ExternalSyntheticLambda26(this, r36));
        this.textOptionsView.setAlignment(PersistColorPalette.getInstance(r36).getCurrentAlignment());
        this.bottomLayout.addView(this.textOptionsView, LayoutHelper.createFrame(-1, 48.0f));
        AnonymousClass10 r014 = new AnonymousClass10(this, r29);
        this.overlayLayout = r014;
        addView(r014, LayoutHelper.createFrame(-1, -1.0f));
        PaintTypefaceListView r015 = new PaintTypefaceListView(r29);
        this.typefaceListView = r015;
        r015.setVisibility(8);
        this.typefaceListView.setOnItemClickListener(new PaintView$$ExternalSyntheticLambda27(this));
        this.textOptionsView.setTypefaceListView(this.typefaceListView);
        this.overlayLayout.addView(this.typefaceListView, LayoutHelper.createFrame(-2, -2.0f, 85, 0.0f, 0.0f, 8.0f, 8.0f));
        this.typefaceMenuOutlinePaint.setStyle(Paint.Style.FILL);
        this.typefaceMenuOutlinePaint.setColor(352321535);
        this.typefaceMenuBackgroundPaint.setColor(getThemedColor(Theme.key_actionBarDefaultSubmenuBackground));
        AnonymousClass11 r016 = new AnonymousClass11(this, r29);
        this.colorsListView = r016;
        r016.setVisibility(8);
        this.colorsListView.setColorPalette(PersistColorPalette.getInstance(r36));
        this.colorsListView.setColorListener(new PaintView$$ExternalSyntheticLambda28(this));
        this.bottomLayout.addView(this.colorsListView, LayoutHelper.createFrame(-1, 84.0f, 48, 56.0f, 0.0f, 56.0f, 6.0f));
        setupTabsLayout(r29);
        PaintCancelView r017 = new PaintCancelView(r29);
        this.cancelButton = r017;
        r017.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f));
        this.cancelButton.setBackground(Theme.createSelectorDrawable(1090519039));
        this.bottomLayout.addView(this.cancelButton, LayoutHelper.createFrame(32, 32.0f, 83, 12.0f, 0.0f, 0.0f, 4.0f));
        this.cancelButton.setOnClickListener(new PaintView$$ExternalSyntheticLambda29(this));
        PaintDoneView r018 = new PaintDoneView(r29);
        this.doneButton = r018;
        r018.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f));
        this.doneButton.setBackground(Theme.createSelectorDrawable(1090519039));
        this.doneButton.setOnClickListener(new PaintView$$ExternalSyntheticLambda18(this, r29, r39, r3));
        this.bottomLayout.addView(this.doneButton, LayoutHelper.createFrame(32, 32.0f, 85, 0.0f, 0.0f, 12.0f, 4.0f));
        PaintWeightChooserView r019 = new PaintWeightChooserView(r29);
        this.weightChooserView = r019;
        r019.setColorSwatch(this.colorSwatch);
        this.weightChooserView.setRenderView(this.renderView);
        this.weightChooserView.setValueOverride(r15);
        this.colorSwatch.brushWeight = r15.get();
        this.weightChooserView.setOnUpdate(new PaintView$$ExternalSyntheticLambda19(this, r36));
        addView(this.weightChooserView, LayoutHelper.createFrame(-1, -1.0f));
        FrameLayout r020 = new FrameLayout(r29);
        this.pipetteContainerLayout = r020;
        addView(r020, LayoutHelper.createFrame(-1, -1.0f));
        this.colorSwatchOutlinePaint.setStyle(Paint.Style.STROKE);
        this.colorSwatchOutlinePaint.setStrokeWidth(AndroidUtilities.dp(2.0f));
        setCurrentSwatch(this.colorSwatch, true);
        onBrushSelected(Brush.BRUSHES_LIST.get(0));
        updateColors();
        if (Build.VERSION.SDK_INT < 29) goto L26;
        setSystemGestureExclusionRects(Arrays.asList(new Rect[]{new Rect(0, (int) (AndroidUtilities.displaySize.y * 0.35f), AndroidUtilities.dp(100.0f), (int) (AndroidUtilities.displaySize.y * 0.65d))}));
    L26:
        this.keyboardNotifier = new AnonymousClass13(this, r34, new PaintView$$ExternalSyntheticLambda20(this, r34));
        EmojiBottomSheet.savedPosition = 1;
        return;
    L17:
        if (r42.isCollage() == true) goto L22;
        if (r42.isRepostMessage == true) goto L22;
        if (r47 == null) goto L22;
        r414 = true;
        goto L23
    L12:
        if (this.h <= 0) goto L14;
        setupEntities();
        goto L14
    L5:
        if (r42.isRepostMessage == false) goto L8;
        r6 = null;
        goto L9
    }

    private Size basePhotoSize(String r6) {
        BitmapFactory.Options r1 = new BitmapFactory.Options();     // Catch: Exception -> L5
        r1.inJustDecodeBounds = true;     // Catch: Exception -> L5
        BitmapFactory.decodeFile(r6, r1);     // Catch: Exception -> L5
        float r62 = r1.outWidth / r1.outHeight;
    L8:
        if (r62 <= 1.0f) goto L11;
        float r5 = (float) Math.floor(Math.max(this.w, this.entitiesView.getMeasuredWidth()) * 0.5d);
        return new Size(r5, r5 / r62);
    L11:
        float r52 = (float) Math.floor(Math.max(this.h, this.entitiesView.getMeasuredHeight()) * 0.5d);
        return new Size(r62 * r52, r52);
    L5:
        e = move-exception;
        FileLog.e(e);
        r62 = 1.0f;
        goto L8
    }

    private Size basePhotoSize(TLObject r4) {
        if ((r4 instanceof TLRPC.Photo) == true) goto L5;
    L7:
        float r0 = 1.0f;
    L9:
        if (r0 <= 1.0f) goto L12;
        float r3 = (float) Math.floor(Math.max(this.w, this.entitiesView.getMeasuredWidth()) * 0.5d);
        return new Size(r3, r3 / r0);
    L12:
        float r32 = (float) Math.floor(Math.max(this.h, this.entitiesView.getMeasuredHeight()) * 0.5d);
        return new Size(r0 * r32, r32);
    L5:
        if (FileLoader.getClosestPhotoSizeWithSize(((TLRPC.Photo) r4).sizes, 1000) == null) goto L7;
        r0 = r4.w / r4.h;
        goto L9
    }

    private Size baseStickerSize() {
        float r4 = (float) Math.floor(getPaintingSize().width * 0.5d);
        return new Size(r4, r4);
    }

    private PopupButton buttonForPopup(String r3, int r4, boolean r5, Runnable r6) {
        PopupButton r0 = new PopupButton(this, getContext());
        r0.setIcon(r4);
        r0.setText(r3);
        r0.setSelected(r5);
        if (r6 == null) goto L5;
        r0.setOnClickListener(new PaintView$$ExternalSyntheticLambda65(r6));
    L5:
        return r0;
    }

    private StickerPosition calculateStickerPosition(TLRPC.Document r7) {
        int r0 = 0;
    L4:
        if (r0 >= r7.attributes.size()) goto L9;
        TLRPC.DocumentAttribute r1 = r7.attributes.get(r0);
        if ((r1 instanceof TLRPC.TL_documentAttributeSticker) == true) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        TLRPC.TL_maskCoords r02 = r1.mask_coords;
    L10:
        MediaController.CropState r12 = this.currentCropState;
        float r2 = 0.75f;
        if (r12 == null) goto L13;
        float r3 = -(r12.transformRotation + r12.cropRotate);
        r2 = 0.75f / r12.cropScale;
    L14:
        StickerPosition r13 = new StickerPosition(centerPositionForEntity(), r2, r3);
        if (r02 == null) goto L26;
        ArrayList<PhotoFace> r22 = this.faces;
        if (r22 == null) goto L26;
        if (r22.size() == 0) goto L26;
        int r23 = r02.n;
        PhotoFace r72 = getRandomFaceWithVacantAnchor(r23, r7.id, r02);
        if (r72 == null) goto L26;
        PointF r14 = r72.getPointForAnchor(r23);
        float r24 = r72.getWidthForAnchor(r23);
        float r73 = r72.getAngle();
        return new StickerPosition(new PointF(r14.x, r14.y), (float) ((r24 / baseStickerSize().width) * r02.zoom), r73);
    L26:
        return r13;
    L13:
        r3 = 0.0f;
        goto L14
    L9:
        r02 = null;
        goto L10
    }

    private PointF centerPositionForEntity() {
        int r0 = this.entitiesView.getMeasuredWidth();
        int r1 = this.entitiesView.getMeasuredHeight();
        if (r0 > 0) goto L5;
        r0 = this.w;
    L5:
        if (r1 > 0) goto L8;
        r1 = this.h;
    L8:
        return new PointF(r0 / 2.0f, r1 / 2.0f);
    }

    private void checkEntitiesIsVideo() {
        boolean r0 = wouldBeVideo();
        int r1 = 0;
    L4:
        if (r1 >= this.entitiesView.getChildCount()) goto L9;
        View r2 = this.entitiesView.getChildAt(r1);
        if ((r2 instanceof EntityView) == false) goto L8;
        ((EntityView) r2).setIsVideo(r0);
    L8:
        r1 = r1 + 1;
        goto L4
    }

    private TextView createActionLayoutButton(int r6, String r7) {
        TextView r0 = new TextView(getContext());
        r0.setTextColor(getThemedColor(Theme.key_actionBarDefaultSubmenuItem));
        r0.setGravity(16);
        r0.setLines(1);
        r0.setSingleLine();
        r0.setEllipsize(TextUtils.TruncateAt.END);
        r0.setTypeface(AndroidUtilities.bold());
        r0.setPadding(AndroidUtilities.dp(14.0f), 0, AndroidUtilities.dp(14.0f), 0);
        r0.setTextSize(1, 14.0f);
        r0.setTag(Integer.valueOf(r6));
        r0.setText(r7);
        return r0;
    }

    private LinkView createLinkSticker(LinkPreview.WebPagePreview r11, TL_stories.MediaArea r12, boolean r13) {
        onTextAdd();
        this.forceChanges = true;
        getPaintingSize();
        PointF r3 = startPositionRelativeToEntity(null);
        if (this.entitiesView.getMeasuredWidth() > 0) goto L6;
        int r0 = this.w;
    L5:
        float r02 = r0;
        int r8 = ((int) r02) - AndroidUtilities.dp(58.0f);
        LinkView r1 = new LinkView(getContext(), r3, this.currentAccount, r11, r12, r02 / 360.0f, r8, 3);
        if (r3.x != (this.entitiesView.getMeasuredWidth() / 2.0f)) goto L11;
        r1.setStickyX(2);
    L11:
        if (r3.y != (this.entitiesView.getMeasuredHeight() / 2.0f)) goto L13;
        r1.setStickyY(2);
    L13:
        Swatch r112 = this.colorSwatch;
        if (r112 == null) goto L18;
        int r113 = r112.color;
        if (r113 == (-47814)) goto L18;
        r1.setColor(r113);
    L18:
        r1.setDelegate(this);
        r1.setMaxWidth(r8);
        this.entitiesView.addView(r1, LayoutHelper.createFrame(-2, -2.0f));
        checkEntitiesIsVideo();
        MediaController.CropState r114 = this.currentCropState;
        if (r114 == null) goto L21;
        r1.scale(1.0f / r114.cropScale);
        r1.rotate(-(r11.transformRotation + this.currentCropState.cropRotate));
    L21:
        if (r13 == false) goto L23;
        registerRemovalUndo(r1);
        selectEntity(r1, false);
    L23:
        return r1;
    L6:
        r0 = this.entitiesView.getMeasuredWidth();
        goto L5
    }

    private LocationView createLocationSticker(TLRPC.MessageMedia r10, TL_stories.MediaArea r11, boolean r12) {
        onTextAdd();
        this.forceChanges = true;
        getPaintingSize();
        PointF r3 = startPositionRelativeToEntity(null);
        if (this.entitiesView.getMeasuredWidth() > 0) goto L6;
        int r0 = this.w;
    L5:
        float r02 = r0;
        int r8 = ((int) r02) - AndroidUtilities.dp(58.0f);
        LocationView r1 = new LocationView(getContext(), r3, this.currentAccount, r10, r11, r02 / 240.0f, r8);
        if (r3.x != (this.entitiesView.getMeasuredWidth() / 2.0f)) goto L11;
        r1.setStickyX(2);
    L11:
        if (r3.y != (this.entitiesView.getMeasuredHeight() / 2.0f)) goto L13;
        r1.setStickyY(2);
    L13:
        Swatch r102 = this.colorSwatch;
        if (r102 == null) goto L18;
        int r103 = r102.color;
        if (r103 == (-47814)) goto L18;
        r1.setColor(r103);
    L18:
        r1.setDelegate(this);
        r1.setMaxWidth(r8);
        this.entitiesView.addView(r1, LayoutHelper.createFrame(-2, -2.0f));
        checkEntitiesIsVideo();
        MediaController.CropState r104 = this.currentCropState;
        if (r104 == null) goto L21;
        r1.scale(1.0f / r104.cropScale);
        r1.rotate(-(r10.transformRotation + this.currentCropState.cropRotate));
    L21:
        if (r12 == false) goto L23;
        registerRemovalUndo(r1);
        selectEntity(r1, false);
    L23:
        return r1;
    L6:
        r0 = this.entitiesView.getMeasuredWidth();
        goto L5
    }

    private ReactionWidgetEntityView createReactionWidget(boolean r8) {
        Size r0 = new Size(AndroidUtilities.dp(106.0f), AndroidUtilities.dp(106.0f));
        PointF r1 = centerPositionForEntity();
        if (this.entitiesView.getMeasuredHeight() <= 0) goto L11;
    L4:
        int r2 = 0;
    L6:
        if (r2 >= this.entitiesView.getChildCount()) goto L11;
        View r3 = this.entitiesView.getChildAt(r2);
        if (MathUtils.distance(r1.x, r1.y, r3.getX() + (r3.getMeasuredWidth() / 2.0f), r3.getY() + (r3.getMeasuredHeight() / 2.0f)) < AndroidUtilities.dp(6.0f)) goto L9;
        r2 = r2 + 1;
        goto L6
    L9:
        r1.x += this.entitiesView.getMeasuredWidth() * 0.05f;
        r1.y += this.entitiesView.getMeasuredHeight() * 0.05f;
        r1.x = Utilities.clamp(r1.x, this.entitiesView.getMeasuredWidth(), 0.0f);
        r1.y = Utilities.clamp(r1.y, this.entitiesView.getMeasuredHeight(), 0.0f);
    L11:
        ReactionWidgetEntityView r22 = new ReactionWidgetEntityView(getContext(), r1, r0);
        r22.setDelegate(this);
        this.entitiesView.addView(r22);
        checkEntitiesIsVideo();
        if (r8 == false) goto L14;
        registerRemovalUndo(r22);
        selectEntity(r22);
    L14:
        return r22;
    }

    private StickerView createSticker(Object r11, TLRPC.Document r12, boolean r13) {
        StickerPosition r0 = calculateStickerPosition(r12);
        AnonymousClass27 r1 = new AnonymousClass27(this, getContext(), StickerPosition.m20142$$Nest$fgetposition(r0), StickerPosition.m20141$$Nest$fgetangle(r0), StickerPosition.m20143$$Nest$fgetscale(r0), baseStickerSize(), r12, r11);
        if (MessageObject.isTextColorEmoji(r12) == false) goto L5;
        r1.centerImage.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
    L5:
        r1.centerImage.setLayerNum(12);
        r1.setDelegate(this);
        this.entitiesView.addView(r1);
        checkEntitiesIsVideo();
        if (r13 == false) goto L8;
        registerRemovalUndo(r1);
        selectEntity(r1);
    L8:
        return r1;
    }

    private TextPaintView createText(boolean r11) {
        onTextAdd();
        Size r0 = getPaintingSize();
        PointF r4 = startPositionRelativeToEntity(null);
        TextPaintView r2 = new TextPaintView(getContext(), r4, (int) (r0.width / 9.0f), _UrlKt.FRAGMENT_ENCODE_SET, this.colorSwatch, this.selectedTextType);
        float r02 = r0.width;
        r2.setMinMaxFontSize((int) ((r02 / 9.0f) * 0.5f), (int) ((r02 / 9.0f) * 2.0f), new PaintView$$ExternalSyntheticLambda32(this));
        if (r4.x != (this.entitiesView.getMeasuredWidth() / 2.0f)) goto L6;
        r2.setStickyX(2);
    L6:
        if (r4.y != (this.entitiesView.getMeasuredHeight() / 2.0f)) goto L8;
        r2.setStickyY(2);
    L8:
        r2.setDelegate(this);
        r2.setMaxWidth(this.w - AndroidUtilities.dp(32.0f));
        r2.setTypeface(PersistColorPalette.getInstance(this.currentAccount).getCurrentTypeface());
        r2.setType(PersistColorPalette.getInstance(this.currentAccount).getCurrentTextType());
        this.entitiesView.addView(r2, LayoutHelper.createFrame(-2, -2.0f));
        checkEntitiesIsVideo();
        MediaController.CropState r03 = this.currentCropState;
        if (r03 == null) goto L11;
        r2.scale(1.0f / r03.cropScale);
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

    private WeatherView createWeatherView(Weather.State r9, boolean r10) {
        onTextAdd();
        this.forceChanges = true;
        getPaintingSize();
        PointF r3 = startPositionRelativeToEntity(null);
        if (this.entitiesView.getMeasuredWidth() > 0) goto L6;
        int r0 = this.w;
    L5:
        float r02 = r0;
        int r7 = ((int) r02) - AndroidUtilities.dp(58.0f);
        WeatherView r1 = new WeatherView(getContext(), r3, this.currentAccount, r9, r02 / 240.0f, r7);
        if (r3.x != (this.entitiesView.getMeasuredWidth() / 2.0f)) goto L11;
        r1.setStickyX(2);
    L11:
        if (r3.y != (this.entitiesView.getMeasuredHeight() / 2.0f)) goto L13;
        r1.setStickyY(2);
    L13:
        Swatch r92 = this.colorSwatch;
        if (r92 == null) goto L18;
        int r93 = r92.color;
        if (r93 == (-47814)) goto L18;
        r1.setColor(r93);
    L18:
        r1.setDelegate(this);
        r1.setMaxWidth(r7);
        this.entitiesView.addView(r1, LayoutHelper.createFrame(-2, -2.0f));
        checkEntitiesIsVideo();
        MediaController.CropState r94 = this.currentCropState;
        if (r94 == null) goto L21;
        r1.scale(1.0f / r94.cropScale);
        r1.rotate(-(r9.transformRotation + this.currentCropState.cropRotate));
    L21:
        if (r10 == false) goto L23;
        registerRemovalUndo(r1);
        selectEntity(r1, false);
    L23:
        return r1;
    L6:
        r0 = this.entitiesView.getMeasuredWidth();
        goto L5
    }

    private void detectFaces() {
        this.queue.postRunnable(new PaintView$$ExternalSyntheticLambda49(this), 200);
    }

    private void duplicateEntity(EntityView r4) {
        if (r4 == null) goto L13;
        PointF r0 = startPositionRelativeToEntity(r4);
        if ((r4 instanceof StickerView) == false) goto L8;
        StickerView r1 = new StickerView(getContext(), (StickerView) r4, r0);
        r1.setDelegate(this);
        this.entitiesView.addView(r1);
        checkEntitiesIsVideo();
        EntityView r12 = r1;
    L10:
        registerRemovalUndo(r12);
        selectEntity(null);
        appearAnimation(r12);
        return;
    L8:
        if ((r4 instanceof TextPaintView) == false) goto L12;
        TextPaintView r13 = new TextPaintView(getContext(), (TextPaintView) r4, r0);
        r13.setDelegate(this);
        r13.setMaxWidth(this.w - AndroidUtilities.dp(32.0f));
        this.entitiesView.addView(r13, LayoutHelper.createFrame(-2, -2.0f));
        checkEntitiesIsVideo();
        r12 = r13;
        goto L10
    L12:
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

    private int[] getCenterLocationInWindow(View r5) {
        r5.getLocationInWindow(this.pos);
        float r0 = (r5.getWidth() * r5.getScaleX()) * this.entitiesView.getScaleX();
        float r1 = (r5.getHeight() * r5.getScaleY()) * this.entitiesView.getScaleY();
        int[] r4 = this.pos;
        r4[0] = (int) (r4[0] + (r0 / 2.0f));
        r4[1] = (int) (r4[1] + (r1 / 2.0f));
        return r4;
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
        Size r02 = new Size(1080.0f, 1920.0f);
        this.paintingSize = r02;
        return r02;
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
        PaintView r5 = this;
        int r7 = r12;
        long r8 = r13;
        TLRPC.TL_maskCoords r10 = r15;
        if (r5.isFaceAnchorOccupied(r6, r7, r8, r10) == false) goto L13;
        r2 = (r2 + 1) % r1;
        r3 = r3 - 1;
        PaintView this = r5;
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
        r43.addUpdateListener(new PaintView$$ExternalSyntheticLambda0(this));
        this.isAnimatePopupClosing = true;
        r43.addListener(new AnonymousClass30(this));
        r43.setDuration(250);
        r43.setInterpolator(AdjustPanLayoutHelper.keyboardInterpolator);
        r43.start();
        return;
    }

    private void hideEmojiView() {
        if (this.emojiViewVisible == true) goto L9;
        EmojiView r0 = this.emojiView;
        if (r0 == null) goto L9;
        if (r0.getVisibility() == 8) goto L9;
        this.emojiView.setVisibility(8);
    L9:
        int r02 = this.emojiPadding;
        this.emojiPadding = 0;
        if (r02 == 0) goto L13;
        this.keyboardNotifier.fire();
        return;
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

    public static boolean isVideoStickerDocument(TLRPC.Document r4) {
        if (r4 == null) goto L23;
        int r1 = 0;
    L6:
        if (r1 >= r4.attributes.size()) goto L23;
        TLRPC.DocumentAttribute r2 = r4.attributes.get(r1);
        if ((r2 instanceof TLRPC.TL_documentAttributeSticker) == true) goto L16;
        if ((r2 instanceof TLRPC.TL_documentAttributeCustomEmoji) == true) goto L16;
        if ((r2 instanceof TLRPC.TL_documentAttributeVideo) == true) goto L16;
        r1 = r1 + 1;
    L16:
        if ("video/webm".equals(r4.mime_type) == false) goto L18;
        return true;
    L18:
        if ("video/mp4".equals(r4.mime_type) == true) goto L28;
        return false;
    L28:
        return true;
    L23:
        return false;
    }

    private /* synthetic */ void lambda$appearAnimation$59(View r2) {
        if ((r2 instanceof EntityView) == false) goto L6;
        EntityView r22 = (EntityView) r2;
        r22.updateSelectionView();
        selectEntity(r22);
        return;
    }

    private /* synthetic */ void lambda$createRound$61(RoundView r1) {
        selectEntity(r1);
    }

    private /* synthetic */ void lambda$createText$15() {
        PaintWeightChooserView r0 = this.weightChooserView;
        if (r0 == null) goto L6;
        r0.invalidate();
        return;
    }

    private /* synthetic */ void lambda$deleteRound$60(View r1) {
        removeEntity((RoundView) r1);
    }

    private /* synthetic */ void lambda$detectFaces$32() {
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

    private /* synthetic */ void lambda$hideEmojiPopup$64(ValueAnimator r1) {
        float r12 = ((Float) r1.getAnimatedValue()).floatValue();
        this.emojiView.setTranslationY(r12);
    }

    private /* synthetic */ void lambda$new$0() {
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

    private /* synthetic */ void lambda$new$1(View r2) {
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

    private /* synthetic */ void lambda$new$10(PersistColorPalette r2, Integer r3) {
        r2.selectColor(r3.intValue());
        r2.saveColors();
        setNewColor(r3.intValue());
        this.colorsListView.setSelectedColorIndex(r2.getCurrentColorPosition());
        this.colorPickerBottomSheet = null;
    }

    private /* synthetic */ void lambda$new$11(Context r2, Bitmap r3, PersistColorPalette r4, View r5) {
        if (this.isColorListShown == false) goto L6;
        ColorPickerBottomSheet r52 = new ColorPickerBottomSheet(r2, this.resourcesProvider);
        this.colorPickerBottomSheet = r52;
        r52.setColor(this.colorSwatch.color).setPipetteDelegate(new AnonymousClass12(this, r3, r4)).setColorListener(new PaintView$$ExternalSyntheticLambda50(this, r4)).show();
        return;
    L6:
        Runnable r1 = this.onDoneButtonClickedListener;
        if (r1 == null) goto L10;
        r1.run();
        return;
    }

    private /* synthetic */ void lambda$new$12(int r3) {
        setCurrentSwatch(this.colorSwatch, true);
        PersistColorPalette.getInstance(r3).setCurrentWeight(this.colorSwatch.brushWeight);
    }

    private /* synthetic */ void lambda$new$13(StoryRecorder.WindowView r10, Integer r11) {
        int r102 = Math.max(0, Math.max(r11.intValue() - r10.getBottomPadding2(), this.emojiPadding - r10.getPaddingUnderContainer()));
        notifyHeightChanged();
        if (r102 <= 0) goto L9;
        EntityView r1 = this.currentEntityView;
        if ((r1 instanceof TextPaintView) == false) goto L9;
        if (((TextPaintView) r1).getEditText().isFocused() == false) goto L9;
        boolean r12 = true;
    L10:
        AnimatorSet r2 = this.keyboardAnimator;
        if (r2 == null) goto L13;
        r2.cancel();
    L13:
        this.keyboardAnimator = new AnimatorSet();
        ArrayList r22 = new ArrayList();
        PaintWeightChooserView r3 = this.weightChooserView;
        float r4 = 0.0f;
        if (r102 <= 0) goto L16;
        float r5 = Math.min(0.0f, ((-r102) / 2.0f) - AndroidUtilities.dp(8.0f));
    L17:
        float[] r6 = {r5};
        Property r52 = View.TRANSLATION_Y;
        r22.add(ObjectAnimator.ofFloat(r3, r52, r6));
        FrameLayout r32 = this.bottomLayout;
        if (r102 <= 0) goto L20;
        int r103 = Math.min(0, (-r102) + AndroidUtilities.dp(40.0f));
    L21:
        r22.add(ObjectAnimator.ofFloat(r32, r52, new float[]{r103}));
        LinearLayout r104 = this.tabsLayout;
        if (r12 == false) goto L24;
        float r53 = 0.0f;
    L25:
        float[] r62 = {r53};
        Property r54 = View.ALPHA;
        r22.add(ObjectAnimator.ofFloat(r104, r54, r62));
        PaintDoneView r105 = this.doneButton;
        if (r12 == true) goto L28;
    L30:
        float r63 = 1.0f;
    L31:
        r22.add(ObjectAnimator.ofFloat(r105, r54, new float[]{r63}));
        PaintCancelView r106 = this.cancelButton;
        if (r12 == true) goto L34;
    L36:
        r4 = 1.0f;
    L37:
        r22.add(ObjectAnimator.ofFloat(r106, r54, new float[]{r4}));
        updatePreviewViewTranslationY();
        this.keyboardAnimator.playTogether(r22);
        AnimatorSet r107 = this.keyboardAnimator;
        if (r12 == false) goto L40;
        r107.setDuration(250);
        this.keyboardAnimator.setInterpolator(AdjustPanLayoutHelper.keyboardInterpolator);
    L41:
        this.keyboardAnimator.start();
        int r108 = 0;
    L43:
        if (r108 >= r22.size()) goto L53;
        Animator r0 = (Animator) r22.get(r108);
        if (r12 == false) goto L47;
        long r7 = 350;
    L48:
        r0.setDuration(r7);
        Animator r02 = (Animator) r22.get(r108);
        if (r12 == false) goto L51;
        TimeInterpolator r72 = CubicBezierInterpolator.EASE_OUT_QUINT;
    L52:
        r02.setInterpolator(r72);
        ((Animator) r22.get(r108)).start();
        r108 = r108 + 1;
        goto L43
    L51:
        r72 = AdjustPanLayoutHelper.keyboardInterpolator;
        goto L52
    L47:
        r7 = 250;
        goto L48
    L53:
        if (r12 == true) goto L56;
        showTypefaceMenu(false);
    L56:
        if (this.wasKeyboardVisible == r12) goto L58;
        checkEntitiesIsVideo();
    L58:
        this.wasKeyboardVisible = r12;
        return;
    L40:
        r107.setDuration(350);
        this.keyboardAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        goto L41
    L34:
        if (this.isColorListShown == true) goto L36;
    L28:
        if (this.isColorListShown == true) goto L30;
        r63 = 0.0f;
        goto L31
    L24:
        r53 = 1.0f;
        goto L25
    L20:
        r103 = 0;
        goto L21
    L16:
        r5 = 0.0f;
    L9:
        r12 = false;
        goto L10
    }

    private /* synthetic */ void lambda$new$3(View r1) {
        clearAll();
    }

    private /* synthetic */ void lambda$new$4(View r2) {
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

    private /* synthetic */ void lambda$new$5(View r1) {
        selectEntity(null);
    }

    private /* synthetic */ void lambda$new$6(int r1) {
        this.textOptionsView.setTypeface(PersistColorPalette.getInstance(r1).getCurrentTypeface());
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

    private /* synthetic */ void lambda$new$9(View r1) {
        if (this.isColorListShown == false) goto L7;
        showColorList(false);
        return;
    L7:
        if (this.emojiViewVisible == false) goto L11;
        hideEmojiPopup(true);
        return;
    L11:
        if (this.editingText == false) goto L14;
        selectEntity(null);
        return;
    L14:
        Runnable r0 = this.onCancelButtonClickedListener;
        if (r0 == null) goto L18;
        r0.run();
        return;
    }

    private /* synthetic */ void lambda$onAddButtonPressed$42(Brush.Shape r2, int r3) {
        if ((this.renderView.getCurrentBrush() instanceof Brush.Shape) == false) goto L5;
        this.ignoreToolChangeAnimationOnce = true;
    L5:
        onBrushSelected(r2);
        this.paintToolsView.animatePlusToIcon(r3);
    }

    private /* synthetic */ boolean lambda$onAddButtonPressed$43(View r5) {
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

    private /* synthetic */ void lambda$onAddButtonPressed$44() {
        boolean r0 = PersistColorPalette.getInstance(this.currentAccount).getFillShapes();
        int r2 = 0;
    L4:
        if (r2 >= Brush.Shape.SHAPES_LIST.size()) goto L10;
        Brush.Shape r3 = Brush.Shape.SHAPES_LIST.get(r2);
        if (r0 == false) goto L8;
        int r4 = r3.getFilledIconRes();
    L9:
        PopupButton r32 = buttonForPopup(r3.getShapeName(), r4, false, new PaintView$$ExternalSyntheticLambda63(this, r3, r4));
        r32.setOnLongClickListener(new PaintView$$ExternalSyntheticLambda64(this));
        this.popupLayout.addView(r32, LayoutHelper.createLinear(-1, 48));
        r2 = r2 + 1;
        goto L4
    L8:
        r4 = r3.getIconRes();
        goto L9
    }

    private /* synthetic */ void lambda$openStickersView$21() {
        if (this.facesBitmap == null) goto L6;
        detectFaces();
        return;
    }

    private /* synthetic */ void lambda$openStickersView$22(boolean[] r1, int r2, DialogInterface r3) {
        this.emojiPopup = null;
        if (r1[0] == false) goto L5;
        onOpenCloseStickersAlert(false);
    L5:
        switchTab(r2);
    }

    private /* synthetic */ Boolean lambda$openStickersView$23(Object r2, TLRPC.Document r3, Boolean r4) {
        this.forceChanges = true;
        StickerView r22 = createSticker(r2, r3, false);
        if (r4.booleanValue() == false) goto L5;
        r22.setScale(1.5f);
    L5:
        appearAnimation(r22);
        return Boolean.TRUE;
    }

    private /* synthetic */ void lambda$openStickersView$24(TLRPC.MessageMedia r2, TL_stories.MediaArea r3) {
        appearAnimation(createLocationSticker(r2, r3, false));
    }

    private /* synthetic */ void lambda$openStickersView$25(EmojiBottomSheet r1, Weather.State r2) {
        if (r2 == null) goto L5;
        r1.dismiss();
        onOpenCloseStickersAlert(false);
        appearAnimation(createWeatherView(r2, false));
        return;
    }

    private /* synthetic */ void lambda$openStickersView$26() {
        new PremiumFeatureBottomSheet(new AnonymousClass23(this), 14, true).show();
    }

    private /* synthetic */ Boolean lambda$openStickersView$27(boolean[] r7, EmojiBottomSheet r8, Integer r9) {
        if (r9.intValue() != 0) goto L7;
        r7[0] = false;
        showLocationAlert(null, new PaintView$$ExternalSyntheticLambda53(this));
        return Boolean.TRUE;
    L7:
        if (r9.intValue() != 5) goto L11;
        r7[0] = false;
        Weather.fetch(true, new PaintView$$ExternalSyntheticLambda54(this, r8));
        return Boolean.FALSE;
    L11:
        if (r9.intValue() != 2) goto L15;
        r8.dismiss();
        onGalleryClick();
        return Boolean.TRUE;
    L15:
        if (r9.intValue() != 1) goto L19;
        r7[0] = false;
        showAudioAlert(new PaintView$$ExternalSyntheticLambda55(this));
        return Boolean.TRUE;
    L19:
        if (r9.intValue() != 3) goto L23;
        this.forceChanges = true;
        appearAnimation(createReactionWidget(true));
        return Boolean.TRUE;
    L23:
        if (r9.intValue() != 4) goto L42;
        if (UserConfig.getInstance(this.currentAccount).isPremium() == false) goto L44;
        int r92 = 0;
        int r0 = 0;
    L31:
        if (r92 >= this.entitiesView.getChildCount()) goto L36;
        if ((this.entitiesView.getChildAt(r92) instanceof LinkView) == false) goto L35;
        r0 = r0 + 1;
    L35:
        r92 = r92 + 1;
        goto L31
    L36:
        if (r0 < 3) goto L39;
        BulletinFactory.of(r8.container, this.resourcesProvider).createSimpleBulletin(R.raw.linkbroken, LocaleController.getString(R.string.StoryLinkLimitTitle), LocaleController.formatPluralString("StoryLinkLimitMessage", 3, new Object[0])).show(true);
        return Boolean.FALSE;
    L39:
        r7[0] = false;
        showLinkAlert(null);
        r8.dismiss();
        return Boolean.TRUE;
    L44:
        r8.container.performHapticFeedback(3);     // Catch: Exception -> L43
    L27:
        BulletinFactory.of(r8.container, this.resourcesProvider).createSimpleBulletin(R.raw.star_premium_2, AndroidUtilities.premiumText(LocaleController.getString(R.string.StoryLinkPremium), new PaintView$$ExternalSyntheticLambda56(this))).show(true);
        return Boolean.FALSE;
    L42:
        return Boolean.FALSE;
    }

    private /* synthetic */ void lambda$registerRemovalUndo$62(EntityView r1) {
        removeEntity(r1);
    }

    private /* synthetic */ void lambda$setCurrentSwatch$40(Integer r2, int r3, ValueAnimator r4) {
        float r42 = ((Float) r4.getAnimatedValue()).floatValue();
        Swatch r0 = this.colorSwatch;
        r0.color = ColorUtils.blendARGB(r2.intValue(), r3, r42);
        FrameLayout r1 = this.bottomLayout;
        if (r1 == null) goto L6;
        r1.invalidate();
        return;
    }

    private /* synthetic */ void lambda$setNewColor$14(int r2, int r3, ValueAnimator r4) {
        float r42 = ((Float) r4.getAnimatedValue()).floatValue();
        Swatch r0 = this.colorSwatch;
        r0.color = ColorUtils.blendARGB(r2, r3, r42);
        this.bottomLayout.invalidate();
    }

    private /* synthetic */ void lambda$setupTabsLayout$17(View r1) {
        if (this.editingText == false) goto L6;
        selectEntity(null);
        return;
    L6:
        switchTab(0);
    }

    private /* synthetic */ void lambda$setupTabsLayout$18(View r1) {
        openStickersView();
    }

    private /* synthetic */ void lambda$setupTabsLayout$19(View r1) {
        switchTab(2);
        if ((this.currentEntityView instanceof TextPaintView) == true) goto L6;
        this.forceChanges = true;
        createText(true);
        return;
    }

    private /* synthetic */ void lambda$showAudioAlert$31() {
        onOpenCloseStickersAlert(false);
    }

    private /* synthetic */ void lambda$showColorList$38(View r2, boolean r3, boolean[] r4, float r5, float r6, DynamicAnimation r7, float r8, float r9) {
        float r82 = r8 / 1000.0f;
        this.toolsTransformProgress = r82;
        float r72 = 1.0f;
        float r83 = ((1.0f - r82) * 0.4f) + 0.6f;
        r2.setScaleX(r83);
        r2.setScaleY(r83);
        r2.setTranslationY((AndroidUtilities.dp(16.0f) * Math.min(this.toolsTransformProgress, 0.25f)) / 0.25f);
        r2.setAlpha(1.0f - (Math.min(this.toolsTransformProgress, 0.25f) / 0.25f));
        this.colorsListView.setProgress(this.toolsTransformProgress, r3);
        this.doneButton.setProgress(this.toolsTransformProgress);
        this.cancelButton.setProgress(this.toolsTransformProgress);
        this.tabsLayout.setTranslationY(AndroidUtilities.dp(32.0f) * this.toolsTransformProgress);
        AnimatorSet r84 = this.keyboardAnimator;
        if (r84 == null) goto L8;
        if (r84.isRunning() == false) goto L8;
        r4[0] = false;
    L8:
        if (r4[0] == false) goto L26;
        float r42 = this.toolsTransformProgress;
        if (r3 == true) goto L13;
        r42 = 1.0f - r42;
    L13:
        PaintDoneView r85 = this.doneButton;
        if (r3 == false) goto L16;
        float r0 = 1.0f;
    L17:
        r85.setAlpha(AndroidUtilities.lerp(r5, r0, r42));
        PaintCancelView r86 = this.cancelButton;
        if (r3 == true) goto L21;
        r72 = 0.0f;
    L21:
        r86.setAlpha(AndroidUtilities.lerp(r5, r72, r42));
        FrameLayout r52 = this.bottomLayout;
        float r73 = AndroidUtilities.dp(39.0f) * r42;
        if (r3 == false) goto L24;
        int r32 = 1;
    L25:
        r52.setTranslationY(r6 - (r73 * r32));
        goto L26
    L24:
        r32 = -1;
        goto L25
    L16:
        r0 = 0.0f;
    L26:
        this.bottomLayout.invalidate();
        if (r2 != this.textOptionsView) goto L30;
        this.overlayLayout.invalidate();
        return;
    }

    private /* synthetic */ void lambda$showColorList$39(boolean r1, DynamicAnimation r2, boolean r3, float r4, float r5) {
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

    private /* synthetic */ void lambda$showEmojiPopup$63(ValueAnimator r1) {
        float r12 = ((Float) r1.getAnimatedValue()).floatValue();
        this.emojiView.setTranslationY(r12);
    }

    private /* synthetic */ void lambda$showLinkAlert$28(LinkView r3, LinkPreview.WebPagePreview r4) {
        if (r3 == null) goto L6;
        r3.setLink(this.currentAccount, r4, null);
        appearAnimation(r3);
        return;
    L6:
        appearAnimation(createLinkSticker(r4, null, false));
    }

    private /* synthetic */ void lambda$showLinkAlert$29(DialogInterface r1) {
        onOpenCloseStickersAlert(false);
    }

    private /* synthetic */ void lambda$showLocationAlert$30(DialogInterface r1) {
        onOpenCloseStickersAlert(false);
    }

    private /* synthetic */ void lambda$showMenuForEntity$45(EntityView r1, View r2) {
        if ((r1 instanceof RoundView) == false) goto L5;
        onTryDeleteRound();
    L6:
        ActionBarPopupWindow r12 = this.popupWindow;
        if (r12 != null) goto L9;
        return;
    L9:
        if (r12.isShowing() == false) goto L13;
        this.popupWindow.dismiss(true);
        return;
    L13:
        return;
    L5:
        removeEntity(r1);
        goto L6
    }

    private /* synthetic */ void lambda$showMenuForEntity$46(EntityView r1, View r2) {
        ((TextPaintView) r1).getEditText().onTextContextMenuItem(android.R.id.pasteAsPlainText);     // Catch: Exception -> L4
    L6:
        ActionBarPopupWindow r12 = this.popupWindow;
        if (r12 != null) goto L9;
        return;
    L9:
        if (r12.isShowing() == false) goto L15;
        this.popupWindow.dismiss(true);
        return;
    L15:
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
        goto L6
    }

    private /* synthetic */ void lambda$showMenuForEntity$47(EntityView r1, View r2) {
        selectEntity(r1);
        editSelectedTextEntity();
        ActionBarPopupWindow r12 = this.popupWindow;
        if (r12 != null) goto L5;
        return;
    L5:
        if (r12.isShowing() == false) goto L9;
        this.popupWindow.dismiss(true);
        return;
    }

    private /* synthetic */ void lambda$showMenuForEntity$48(EntityView r3, TLRPC.MessageMedia r4, TL_stories.MediaArea r5) {
        ((LocationView) r3).setLocation(this.currentAccount, r4, r5);
        appearAnimation(r3);
    }

    private /* synthetic */ void lambda$showMenuForEntity$49(EntityView r2, View r3) {
        selectEntity(null);
        showLocationAlert((LocationView) r2, new PaintView$$ExternalSyntheticLambda59(this, r2));
        ActionBarPopupWindow r22 = this.popupWindow;
        if (r22 != null) goto L5;
        return;
    L5:
        if (r22.isShowing() == false) goto L9;
        this.popupWindow.dismiss(true);
        return;
    }

    private /* synthetic */ void lambda$showMenuForEntity$50(EntityView r1, View r2) {
        selectEntity(null);
        showLinkAlert((LinkView) r1);
        ActionBarPopupWindow r12 = this.popupWindow;
        if (r12 != null) goto L5;
        return;
    L5:
        if (r12.isShowing() == false) goto L9;
        this.popupWindow.dismiss(true);
        return;
    }

    private /* synthetic */ void lambda$showMenuForEntity$51(EntityView r2, View r3) {
        if ((r2 instanceof StickerView) == false) goto L6;
        ((StickerView) r2).mirror(true);
    L12:
        ActionBarPopupWindow r22 = this.popupWindow;
        if (r22 != null) goto L15;
        return;
    L15:
        if (r22.isShowing() == false) goto L19;
        this.popupWindow.dismiss(true);
        return;
    L19:
        return;
    L6:
        if ((r2 instanceof ReactionWidgetEntityView) == false) goto L9;
        ((ReactionWidgetEntityView) r2).mirror(true);
        goto L12
    L9:
        if ((r2 instanceof RoundView) == false) goto L11;
        ((RoundView) r2).mirror(true);
        goto L12
    L11:
        ((PhotoView) r2).mirror(true);
        goto L12
    }

    private /* synthetic */ void lambda$showMenuForEntity$52(PhotoView r2, View r3) {
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

    private /* synthetic */ void lambda$showMenuForEntity$53(EntityView r1, View r2) {
        r1.bringToFront();
        ActionBarPopupWindow r12 = this.popupWindow;
        if (r12 != null) goto L5;
        return;
    L5:
        if (r12.isShowing() == false) goto L9;
        this.popupWindow.dismiss(true);
        return;
    }

    private /* synthetic */ void lambda$showMenuForEntity$54(EntityView r1, View r2) {
        duplicateEntity(r1);
        ActionBarPopupWindow r12 = this.popupWindow;
        if (r12 != null) goto L5;
        return;
    L5:
        if (r12.isShowing() == false) goto L9;
        this.popupWindow.dismiss(true);
        return;
    }

    private /* synthetic */ void lambda$showMenuForEntity$55(EntityView r14) {
        LinearLayout r1 = new LinearLayout(getContext());
        r1.setOrientation(0);
        boolean r3 = r14 instanceof MessageEntityView;
        if (r3 == true) goto L6;
        TextView r9 = new TextView(getContext());
        r9.setTextColor(getThemedColor(Theme.key_actionBarDefaultSubmenuItem));
        r9.setGravity(16);
        r9.setLines(1);
        r9.setSingleLine();
        r9.setEllipsize(TextUtils.TruncateAt.END);
        r9.setTypeface(AndroidUtilities.bold());
        r9.setPadding(AndroidUtilities.dp(14.0f), 0, AndroidUtilities.dp(14.0f), 0);
        r9.setTextSize(1, 14.0f);
        r9.setTag(0);
        r9.setText(LocaleController.getString("PaintDelete", R.string.PaintDelete));
        r9.setOnClickListener(new PaintView$$ExternalSyntheticLambda39(this, r14));
        r1.addView(r9, LayoutHelper.createLinear(-2, 44));
    L6:
        if ((r14 instanceof TextPaintView) == false) goto L17;
        TextView r92 = new TextView(getContext());
        r92.setTextColor(getThemedColor(Theme.key_actionBarDefaultSubmenuItem));
        r92.setGravity(16);
        r92.setLines(1);
        r92.setSingleLine();
        r92.setEllipsize(TextUtils.TruncateAt.END);
        r92.setTypeface(AndroidUtilities.bold());
        r92.setPadding(AndroidUtilities.dp(14.0f), 0, AndroidUtilities.dp(14.0f), 0);
        r92.setTextSize(1, 14.0f);
        if (this.keyboardNotifier.keyboardVisible() == false) goto L12;
        if (this.keyboardNotifier.ignoring == true) goto L12;
    L13:
        r92.setTag(3);
        r92.setText(LocaleController.getString(R.string.Paste));
        r92.setOnClickListener(new PaintView$$ExternalSyntheticLambda40(this, r14));
    L15:
        r1.addView(r92, LayoutHelper.createLinear(-2, 44));
    L23:
        if ((r14 instanceof StickerView) == false) goto L25;
    L30:
        TextView r93 = createActionLayoutButton(4, LocaleController.getString(R.string.Flip));
        r93.setOnClickListener(new PaintView$$ExternalSyntheticLambda44(this, r14));
        r1.addView(r93, LayoutHelper.createLinear(-2, 44));
    L31:
        boolean r94 = r14 instanceof PhotoView;
        if (r94 == false) goto L41;
        PhotoView r10 = (PhotoView) r14;
        if (r10.hasSegmentedImage() == false) goto L41;
        if (r10.isSegmented() == false) goto L38;
        int r11 = R.string.SegmentationUndoCutOut;
    L39:
        TextView r112 = createActionLayoutButton(5, LocaleController.getString(r11));
        r112.setOnClickListener(new PaintView$$ExternalSyntheticLambda45(this, r10));
        r1.addView(r112, LayoutHelper.createLinear(-2, 44));
        r10.highlightSegmented();
        goto L41
    L38:
        r11 = R.string.SegmentationCutOut;
    L41:
        if (this.entitiesView.indexOfChild(r14) != (this.entitiesView.getChildCount() - 1)) goto L43;
    L45:
        if (r94 == true) goto L58;
        if (r3 == true) goto L58;
        if ((r14 instanceof RoundView) == true) goto L58;
        if ((r14 instanceof LocationView) == true) goto L58;
        if ((r14 instanceof WeatherView) == true) goto L58;
        if ((r14 instanceof LinkView) == true) goto L58;
        if ((r14 instanceof ReactionWidgetEntityView) == true) goto L58;
        TextView r32 = new TextView(getContext());
        r32.setTextColor(getThemedColor(Theme.key_actionBarDefaultSubmenuItem));
        r32.setLines(1);
        r32.setSingleLine();
        r32.setEllipsize(TextUtils.TruncateAt.END);
        r32.setGravity(16);
        r32.setTypeface(AndroidUtilities.bold());
        r32.setPadding(AndroidUtilities.dp(14.0f), 0, AndroidUtilities.dp(14.0f), 0);
        r32.setTextSize(1, 14.0f);
        r32.setTag(2);
        r32.setText(LocaleController.getString("PaintDuplicate", R.string.PaintDuplicate));
        r32.setOnClickListener(new PaintView$$ExternalSyntheticLambda47(this, r14));
        r1.addView(r32, LayoutHelper.createLinear(-2, 44));
    L58:
        int r142 = 0;
    L60:
        if (r142 >= r1.getChildCount()) goto L77;
        View r0 = r1.getChildAt(r142);
        int r33 = getThemedColor(Theme.key_listSelector);
        int r4 = 8;
        if (r142 != 0) goto L64;
        int r5 = 8;
    L66:
        if (r142 != (r1.getChildCount() - 1)) goto L68;
        int r7 = 8;
    L70:
        if (r142 != (r1.getChildCount() - 1)) goto L72;
        int r95 = 8;
    L73:
        if (r142 == 0) goto L76;
        r4 = 0;
    L76:
        r0.setBackground(Theme.createRadSelectorDrawable(r33, r5, r7, r95, r4));
        r142 = r142 + 1;
        goto L60
    L72:
        r95 = 0;
        goto L73
    L68:
        r7 = 0;
        goto L70
    L64:
        r5 = 0;
        goto L66
    L77:
        this.popupLayout.addView(r1);
        LinearLayout.LayoutParams r13 = (LinearLayout.LayoutParams) r1.getLayoutParams();
        r13.width = -2;
        r13.height = -2;
        r1.setLayoutParams(r13);
        return;
    L43:
        if ((r14 instanceof ReactionWidgetEntityView) == true) goto L45;
        TextView r34 = new TextView(getContext());
        r34.setTextColor(getThemedColor(Theme.key_actionBarDefaultSubmenuItem));
        r34.setLines(1);
        r34.setSingleLine();
        r34.setEllipsize(TextUtils.TruncateAt.END);
        r34.setGravity(16);
        r34.setTypeface(AndroidUtilities.bold());
        r34.setPadding(AndroidUtilities.dp(14.0f), 0, AndroidUtilities.dp(14.0f), 0);
        r34.setTextSize(1, 14.0f);
        r34.setTag(2);
        r34.setText(LocaleController.getString(R.string.PaintBringToFront));
        r34.setOnClickListener(new PaintView$$ExternalSyntheticLambda46(this, r14));
        r1.addView(r34, LayoutHelper.createLinear(-2, 44));
        goto L58
    L25:
        if ((r14 instanceof RoundView) == true) goto L30;
        if ((r14 instanceof PhotoView) == true) goto L30;
        if ((r14 instanceof ReactionWidgetEntityView) == false) goto L31;
    L12:
        if (this.emojiPadding > 0) goto L13;
        r92.setTag(1);
        r92.setText(LocaleController.getString(R.string.PaintEdit));
        r92.setOnClickListener(new PaintView$$ExternalSyntheticLambda41(this, r14));
        goto L15
    L17:
        if ((r14 instanceof LocationView) == false) goto L20;
        TextView r96 = createActionLayoutButton(1, LocaleController.getString(R.string.PaintEdit));
        r96.setOnClickListener(new PaintView$$ExternalSyntheticLambda42(this, r14));
        r1.addView(r96, LayoutHelper.createLinear(-2, 44));
        goto L23
    L20:
        if ((r14 instanceof LinkView) == false) goto L23;
        TextView r97 = createActionLayoutButton(1, LocaleController.getString(R.string.PaintEdit));
        r97.setOnClickListener(new PaintView$$ExternalSyntheticLambda43(this, r14));
        r1.addView(r97, LayoutHelper.createLinear(-2, 44));
        goto L23
    }

    private /* synthetic */ boolean lambda$showPopup$56(View r2, MotionEvent r3) {
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

    private /* synthetic */ void lambda$showPopup$57(KeyEvent r3) {
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

    private /* synthetic */ void lambda$showPopup$58() {
        this.popupLayout.removeInnerViews();
    }

    private /* synthetic */ void lambda$showReactionsLayout$16(ValueAnimator r1) {
        float r12 = ((Float) r1.getAnimatedValue()).floatValue();
        this.reactionShowProgress = r12;
        this.reactionLayout.setTransitionProgress(r12);
    }

    private /* synthetic */ void lambda$showTypefaceMenu$36(DynamicAnimation r1, float r2, float r3) {
        float r22 = r2 / 1000.0f;
        this.typefaceMenuTransformProgress = r22;
        this.typefaceListView.setAlpha(r22);
        this.typefaceListView.invalidate();
        this.overlayLayout.invalidate();
        this.textOptionsView.getTypefaceCell().setAlpha(1.0f - this.typefaceMenuTransformProgress);
    }

    private /* synthetic */ void lambda$showTypefaceMenu$37(boolean r1, DynamicAnimation r2, boolean r3, float r4, float r5) {
        if (r2 != this.typefaceMenuTransformAnimation) goto L9;
        this.typefaceMenuTransformAnimation = null;
        if (r1 == true) goto L7;
        this.typefaceListView.setVisibility(8);
    L7:
        this.typefaceListView.setMaskProvider(null);
        return;
    }

    private /* synthetic */ void lambda$switchTab$20(View r8, View r9, ValueAnimator r10) {
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

    private /* synthetic */ void lambda$updateTextDim$65(boolean r1) {
        if (r1 == true) goto L5;
        this.textDim.setVisibility(8);
        return;
    }

    private void openStickersView() {
        int r6 = this.tabsSelectedIndex;
        switchTab(1);
        postDelayed(new PaintView$$ExternalSyntheticLambda4(this), 350);
        AnonymousClass22 r0 = new AnonymousClass22(this, getContext(), false, this.resourcesProvider, false, r6);
        this.emojiPopup = r0;
        StoryRecorder.WindowView r8 = this.parent;
        Objects.requireNonNull(r8);
        r0.setBlurDelegate(new PaintView$$ExternalSyntheticLambda5(r8));
        boolean[] r82 = {true};
        r0.setOnDismissListener(new PaintView$$ExternalSyntheticLambda6(this, r82, r6));
        r0.whenDocumentSelected(new PaintView$$ExternalSyntheticLambda7(this));
        r0.whenWidgetSelected(new PaintView$$ExternalSyntheticLambda8(this, r82, r0));
        r0.show();
        onOpenCloseStickersAlert(true);
    }

    private void registerRemovalUndo(EntityView r4) {
        if (r4 != null) goto L4;
        return;
    L4:
        this.undoStore.registerUndo(r4.getUUID(), new PaintView$$ExternalSyntheticLambda31(this, r4));
    }

    private void removeEntity(EntityView r4) {
        EntityView r0 = this.currentEntityView;
        if (r4 != r0) goto L13;
        if (r0 == null) goto L13;
        r0.deselect();
        selectEntity(null);
        if ((r4 instanceof TextPaintView) == false) goto L13;
        ValueAnimator r02 = this.tabsSelectionAnimator;
        if (r02 != null) goto L10;
    L12:
        switchTab(0);
        goto L13
    L10:
        if (this.tabsNewSelectedIndex == 0) goto L12;
        r02.cancel();
    L13:
        this.entitiesView.removeView(r4);
        checkEntitiesIsVideo();
        if (r4 == null) goto L17;
        this.undoStore.unregisterUndo(r4.getUUID());
    L17:
        if ((r4 instanceof PhotoView) == false) goto L19;
        ((PhotoView) r4).deleteSegmentedFile();
    L19:
        this.weightChooserView.setValueOverride(this.weightDefaultValueOverride);
        this.weightChooserView.setShowPreview(true);
        Swatch r03 = this.colorSwatch;
        r03.brushWeight = this.weightDefaultValueOverride.get();
        setCurrentSwatch(this.colorSwatch, true);
        if (this.creatingNewRound == false) goto L22;
        return;
    L22:
        if ((r4 instanceof RoundView) == false) goto L26;
        onDeleteRound();
        return;
    }

    private boolean selectEntity(EntityView r7, boolean r8) {
        boolean r0 = r7 instanceof TextPaintView;
        int r1 = 2;
        int r2 = 0;
        if (r0 == false) goto L18;
        int r3 = this.tabsNewSelectedIndex;
        if (r3 == (-1)) goto L7;
    L8:
        if (r3 == (-1)) goto L18;
        if (r3 == 2) goto L18;
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
        goto L18
    L7:
        if (this.tabsSelectedIndex != 2) goto L10;
    L18:
        boolean r32 = true;
        if (r0 == false) goto L31;
        if (r8 == false) goto L31;
        TextPaintView r82 = (TextPaintView) r7;
        int r42 = r82.getEditText().getGravity();
        if (r42 != 17) goto L24;
        r1 = 1;
    L27:
        this.textOptionsView.setAlignment(r1);
        PaintTypeface r12 = r82.getTypeface();
        if (r12 == null) goto L30;
        this.textOptionsView.setTypeface(r12.getKey());
    L30:
        this.textOptionsView.setOutlineType(r82.getType(), true);
        this.overlayLayout.invalidate();
        goto L31
    L24:
        if (r42 == 21) goto L27;
        r1 = 0;
    L31:
        EntityView r83 = this.currentEntityView;
        if (r83 == null) goto L78;
        if (r83 == r7) goto L35;
        r83.deselect();
        EntityView r84 = this.currentEntityView;
        if ((r84 instanceof TextPaintView) == false) goto L75;
        ((TextPaintView) r84).endEditing();
        if (r0 == true) goto L77;
        this.editingText = false;
        AndroidUtilities.hideKeyboard(((TextPaintView) this.currentEntityView).getFocusedView());
        hideEmojiPopup(false);
    L77:
        boolean r85 = true;
    L79:
        EntityView r02 = this.currentEntityView;
        this.currentEntityView = r7;
        if ((r02 instanceof TextPaintView) == true) goto L82;
    L84:
        EntityView r72 = this.currentEntityView;
        if (r02 != r72) goto L87;
    L89:
        EntityView r73 = this.currentEntityView;
        if (r73 == null) goto L95;
        r73.select(this.selectionContainerView);
        EntityView r74 = this.currentEntityView;
        if ((r74 instanceof TextPaintView) == false) goto L94;
        TextPaintView r75 = (TextPaintView) r74;
        r75.getSwatch().brushWeight = this.colorSwatch.brushWeight;
        r75.disableAutoresize(false);
        setCurrentSwatch(r75.getSwatch(), true);
        this.weightChooserView.setValueOverride(new AnonymousClass14(this, r75, (int) (this.paintingSize.width / 9.0f)));
        this.weightChooserView.setShowPreview(false);
    L104:
        updateTextDim();
        return r32;
    L94:
        this.weightChooserView.setValueOverride(this.weightDefaultValueOverride);
        this.weightChooserView.setShowPreview(true);
        this.colorSwatch.brushWeight = this.weightDefaultValueOverride.get();
        setCurrentSwatch(this.colorSwatch, true);
        goto L104
    L95:
        ValueAnimator r76 = this.tabsSelectionAnimator;
        if (r76 == null) goto L101;
        if (this.tabsNewSelectedIndex == 0) goto L101;
        r76.cancel();
    L101:
        if (this.isColorListShown == false) goto L103;
        showColorList(false);
    L103:
        switchTab(0);
        this.weightChooserView.setValueOverride(this.weightDefaultValueOverride);
        this.weightChooserView.setShowPreview(true);
        this.colorSwatch.brushWeight = this.weightDefaultValueOverride.get();
        setCurrentSwatch(this.colorSwatch, true);
        r32 = r85;
        goto L104
    L87:
        if ((r72 instanceof RoundView) == false) goto L89;
        onSelectRound((RoundView) r72);
        goto L89
    L82:
        if (TextUtils.isEmpty(((TextPaintView) r02).getText()) == false) goto L84;
        removeEntity(r02);
        goto L84
    L75:
        if ((r84 instanceof RoundView) == false) goto L77;
        onDeselectRound((RoundView) r84);
        goto L77
    L35:
        if (r7.hadMultitouch() == false) goto L37;
    L68:
        return true;
    L37:
        if ((r7 instanceof LocationView) == false) goto L40;
        LocationView r77 = (LocationView) r7;
        r77.setType((r77.getType() + 1) % r77.getTypesCount());
        goto L68
    L40:
        if ((r7 instanceof WeatherView) == false) goto L43;
        WeatherView r78 = (WeatherView) r7;
        r78.setType((r78.getType() + 1) % r78.getTypesCount());
        goto L68
    L43:
        if ((r7 instanceof LinkView) == false) goto L52;
        LinkView r79 = (LinkView) r7;
        if (r79.marker.withPreview() == false) goto L50;
        LinkPreview r6 = r79.marker;
        if (r6.getPreviewType() != 0) goto L49;
        r2 = 1;
    L49:
        r6.setPreviewType(r2);
        goto L68
    L50:
        r79.setType(r79.getNextType());
        goto L68
    L52:
        if (this.editingText == false) goto L54;
        EntityView r710 = this.currentEntityView;
        if ((r710 instanceof TextPaintView) == false) goto L68;
        AndroidUtilities.showKeyboard(((TextPaintView) r710).getFocusedView());
        hideEmojiPopup(false);
        goto L68
    L54:
        if ((r7 instanceof TextPaintView) == false) goto L57;
        this.enteredThroughText = true;
        editSelectedTextEntity();
        goto L68
    L57:
        if ((r7 instanceof ReactionWidgetEntityView) == false) goto L64;
        ReactionWidgetEntityView r86 = (ReactionWidgetEntityView) r7;
        if (this.reactionLayoutShowing == true) goto L61;
    L63:
        showReactionsLayoutForView(r86);
        goto L68
    L61:
        if (this.reactionForEntity != r7) goto L63;
        r86.changeStyle(true);
        goto L68
    L64:
        showMenuForEntity(this.currentEntityView);
        goto L68
    L78:
        r85 = false;
        goto L79
    }

    private void setCoverPause(boolean r6) {
        int r0 = 0;
    L4:
        if (r0 >= this.entitiesView.getChildCount()) goto L18;
        View r1 = this.entitiesView.getChildAt(r0);
        if ((r1 instanceof StickerView) == false) goto L17;
        ImageReceiver r12 = ((StickerView) r1).centerImage;
        RLottieDrawable r2 = r12.getLottieAnimation();
        AnimatedFileDrawable r3 = r12.getAnimation();
        r12.setAllowStartLottieAnimation(!r6);
        r12.setAllowStartAnimation(!r6);
        if (r2 == null) goto L12;
        if (r6 == false) goto L11;
        r2.stop();
        goto L17
    L11:
        r2.start();
        goto L17
    L12:
        if (r3 == null) goto L17;
        r3.setAllowDecodeSingleFrame(r6);
        if (r6 == false) goto L16;
        r3.stop();
        goto L17
    L16:
        r3.start();
    L17:
        r0 = r0 + 1;
        goto L4
    }

    private void setCurrentSwatch(Swatch r3, boolean r4) {
        setCurrentSwatch(r3, r4, null, false);
    }

    private void setCurrentSwatch(Swatch r4, boolean r5, Integer r6, boolean r7) {
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
        r02.addUpdateListener(new PaintView$$ExternalSyntheticLambda12(this, r6, r52));
        r02.start();
    L15:
        EntityView r54 = this.currentEntityView;
        if ((r54 instanceof TextPaintView) == false) goto L20;
        ((TextPaintView) r54).setSwatch(new Swatch(r4.color, r4.colorLocation, r4.brushWeight));
        return;
    L20:
        if (r7 == true) goto L22;
    L25:
        if (r7 == true) goto L27;
    L30:
        if (r7 == true) goto L32;
        return;
    L32:
        if ((r54 instanceof LinkView) == false) goto L36;
        ((LinkView) r54).setColor(r4.color);
        ((LinkView) this.currentEntityView).setType(0);
        return;
    L36:
        return;
    L27:
        if ((r54 instanceof WeatherView) == false) goto L30;
        ((WeatherView) r54).setColor(r4.color);
        ((WeatherView) this.currentEntityView).setType(3);
        return;
    L22:
        if ((r54 instanceof LocationView) == false) goto L25;
        ((LocationView) r54).setColor(r4.color);
        ((LocationView) this.currentEntityView).setType(3);
    }

    private void setNewColor(int r5) {
        Swatch r0 = this.colorSwatch;
        int r1 = r0.color;
        r0.color = r5;
        setCurrentSwatch(r0, true, null, true);
        ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}).setDuration(150);
        r02.addUpdateListener(new PaintView$$ExternalSyntheticLambda1(this, r1, r5));
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
        this.textOptionsView.setOutlineType(r3, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v0 */
    /* JADX WARN: Type inference failed for: r12v1, types: [int] */
    /* JADX WARN: Type inference failed for: r12v3 */
    private void setupEntities() {
        ArrayList<VideoEditedInfo.MediaEntity> r1 = this.initialEntities;
        if (r1 == null) goto L97;
        StoryEntry r2 = this.initialEntry;
        this.initialEntry = null;
        this.initialEntities = null;
        int r3 = r1.size();
        boolean r4 = false;
        int r5 = 0;
    L5:
        if (r5 >= r3) goto L87;
        VideoEditedInfo.MediaEntity r6 = r1.get(r5);
        byte r7 = r6.type;
        if (r7 != 0) goto L13;
        StickerView r72 = createSticker(r6.parentObject, r6.document, r4);
        if ((2 & r6.subType) == 0) goto L11;
        r72.mirror();
    L11:
        ViewGroup.LayoutParams r9 = r72.getLayoutParams();
        r9.width = r6.viewWidth;
        r9.height = r6.viewHeight;
        int r16 = r5;
        RoundView r73 = r72;
    L85:
        r73.setX((r6.x * this.w) - ((r6.viewWidth * (1.0f - r6.scale)) / 2.0f));
        r73.setY((r6.y * this.h) - ((r6.viewHeight * (1.0f - r6.scale)) / 2.0f));
        r73.setPosition(new PointF(r73.getX() + (r6.viewWidth / 2.0f), r73.getY() + (r6.viewHeight / 2.0f)));
        r73.setScale(r6.scale);
        r73.setRotation((float) (((-r6.rotation) / 3.141592653589793d) * 180.0d));
    L86:
        r5 = r16 + 1;
        r4 = false;
        goto L5
    L13:
        if (r7 != 1) goto L26;
        TextPaintView r74 = createText(r4);
        r74.setType(r6.subType);
        r74.setTypeface(r6.textTypeface);
        r74.setBaseFontSize(r6.fontSize);
        SpannableString r92 = new SpannableString(r6.text);
        ArrayList<VideoEditedInfo.EmojiEntity> r10 = r6.entities;
        int r11 = r10.size();
        ?? r12 = r4;
    L15:
        if (r12 >= r11) goto L17;
        VideoEditedInfo.EmojiEntity r13 = r10.get(r12);
        AnimatedEmojiSpan r14 = new AnimatedEmojiSpan(r13.document_id, 1.0f, r74.getFontMetricsInt());
        int r42 = r13.offset;
        r92.setSpan(r14, r42, r13.length + r42, 33);
        r5 = r5;
        r12 = r12 + 1;
        goto L15
    L17:
        r16 = r5;
        CharSequence r43 = Emoji.replaceEmoji(r92, r74.getFontMetricsInt(), false);
        if ((r43 instanceof Spanned) == false) goto L25;
        Emoji.EmojiSpan[] r52 = (Emoji.EmojiSpan[]) ((Spanned) r43).getSpans(0, r43.length(), Emoji.EmojiSpan.class);
        if (r52 == null) goto L25;
        int r93 = 0;
    L23:
        if (r93 >= r52.length) goto L25;
        r52[r93].scale = 0.85f;
        r93 = r93 + 1;
    L25:
        r74.setText(r43);
        setTextAlignment(r74, r6.textAlign);
        Swatch r44 = r74.getSwatch();
        r44.color = r6.color;
        r74.setSwatch(r44);
        r73 = r74;
        goto L85
    L26:
        r16 = r5;
        if (r7 != 2) goto L36;
        PhotoView r75 = createPhoto(r6.text, false);
        r75.crop = r6.crop;
        r75.preloadSegmented(r6.segmentedPath);
        if ((r6.subType & 2) == 0) goto L32;
        r75.mirror();
    L32:
        if ((r6.subType & 16) == 0) goto L34;
        r75.toggleSegmented(false);
    L34:
        ViewGroup.LayoutParams r45 = r75.getLayoutParams();
        r45.width = r6.viewWidth;
        r45.height = r6.viewHeight;
        r73 = r75;
        goto L85
    L36:
        if (r7 != 6) goto L43;
        MessageEntityView r76 = createMessage(r2.messageObjects, false, r2.isVideo);
        r73 = r76;
        if (r6.viewWidth <= 0) goto L85;
        r73 = r76;
        if (r6.viewHeight <= 0) goto L85;
        ViewGroup.LayoutParams r46 = r76.getLayoutParams();
        r46.width = r6.viewWidth;
        r46.height = r6.viewHeight;
        r73 = r76;
        goto L85
    L43:
        if (r7 != 3) goto L49;
        LocationView r77 = createLocationSticker(r6.media, r6.mediaArea, false);
        int r47 = r6.color;
        if (r47 == 0) goto L47;
        r77.setColor(r47);
    L47:
        r77.setType(r6.subType);
        r73 = r77;
        goto L85
    L49:
        if (r7 != 8) goto L58;
        Weather.State r48 = r6.weather;
        if (r48 == null) goto L86;
        WeatherView r78 = createWeatherView(r48, false);
        int r49 = r6.color;
        if (r49 == 0) goto L56;
        r78.setColor(r49);
    L56:
        r78.setType(r6.subType);
        r73 = r78;
        goto L85
    L58:
        if (r7 != 7) goto L70;
        LinkView r79 = createLinkSticker(r6.linkSettings, r6.mediaArea, false);
        int r53 = r6.color;
        if (r53 == 0) goto L63;
        r79.setColor(r53);
    L63:
        if (r79.marker.withPreview() == false) goto L65;
        r79.marker.setPreviewType(r6.subType);
    L65:
        byte r54 = r6.subType;
        if (r54 != (-1)) goto L68;
        r79.setType(3);
        r79.marker.setupLayout();
        int r55 = r79.marker.padx + ((int) Math.ceil(r4.w));
        LinkPreview r410 = r79.marker;
        r6.viewWidth = r55 + r410.padx;
        r6.viewHeight = (r410.pady + ((int) Math.ceil(r410.h))) + r79.marker.pady;
        PointF r411 = r79.getPosition();
        r411.y += this.h * 0.3f;
        r79.setPosition(r411);
        goto L86
    L68:
        r79.setType(r54);
        r73 = r79;
        goto L85
    L70:
        if (r7 != 4) goto L78;
        ReactionWidgetEntityView r710 = createReactionWidget(false);
        r710.setCurrentReaction(ReactionsLayoutInBubble.VisibleReaction.fromTL(r6.mediaArea.reaction), false);
        if (r6.mediaArea.flipped == false) goto L74;
        r710.mirror(false);
    L74:
        r73 = r710;
        if (r6.mediaArea.dark == false) goto L85;
        r710.changeStyle(false);
        r73 = r710;
        goto L85
    L78:
        if (r7 != 5) goto L86;
        if (r2.round == null) goto L86;
        RoundView r711 = createRound(r2.roundThumb, false);
        onCreateRound(r711);
        r73 = r711;
        if ((r6.subType & 2) == 0) goto L85;
        r711.mirror(false);
        r73 = r711;
        goto L85
    L87:
        this.entitiesView.setVisibility(0);
        return;
    }

    private void setupTabsLayout(Context r14) {
        AnonymousClass20 r0 = new AnonymousClass20(this, r14);
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
        this.drawTab.setOnClickListener(new PaintView$$ExternalSyntheticLambda9(this));
        this.tabsLayout.addView(this.drawTab, LayoutHelper.createLinear(0, -2, 1.0f));
        TextView r04 = new TextView(r14);
        this.stickerTab = r04;
        r04.setText(LocaleController.getString(R.string.PhotoEditorSticker).toUpperCase());
        this.stickerTab.setBackground(Theme.createSelectorDrawable(getThemedColor(r2), 7));
        this.stickerTab.setPadding(0, AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f));
        this.stickerTab.setOnClickListener(new PaintView$$ExternalSyntheticLambda10(this));
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
        this.textTab.setOnClickListener(new PaintView$$ExternalSyntheticLambda11(this));
        this.tabsLayout.addView(this.textTab, LayoutHelper.createLinear(0, -2, 1.0f));
    }

    private void showAudioAlert(Utilities.Callback<MessageObject> r4) {
        SelectAudioAlert r0 = new SelectAudioAlert(getContext(), r4, new DarkThemeResourceProvider());
        r0.setOnDismissListener(new PaintView$$ExternalSyntheticLambda57(this));
        r0.show();
    }

    @SuppressLint({"NotifyDataSetChanged"})
    private void showColorList(boolean r11) {
        if (this.isColorListShown == r11) goto L26;
        this.isColorListShown = r11;
        SpringAnimation r0 = this.toolsTransformAnimation;
        if (r0 == null) goto L7;
        r0.cancel();
    L7:
        float r2 = 1000.0f;
        if (r11 == false) goto L10;
        float r4 = 0.0f;
    L11:
        SpringAnimation r02 = new SpringAnimation(new FloatValueHolder(r4));
        this.toolsTransformAnimation = r02;
        SpringForce r1 = new SpringForce();
        if (r11 == true) goto L15;
        r2 = 0.0f;
    L15:
        r02.setSpring(r1.setFinalPosition(r2).setStiffness(1250.0f).setDampingRatio(1.0f));
        if (this.keyboardNotifier.keyboardVisible() == false) goto L18;
    L21:
        boolean r03 = true;
    L22:
        float r9 = this.bottomLayout.getTranslationY();
        float r8 = this.doneButton.getAlpha();
        this.toolsTransformAnimation.addUpdateListener(new PaintView$$ExternalSyntheticLambda13(this, getBarView(), r11, new boolean[]{r03}, r8, r9));
        this.toolsTransformAnimation.addEndListener(new PaintView$$ExternalSyntheticLambda14(this, r11));
        this.toolsTransformAnimation.start();
        if (r11 == false) goto L27;
        this.colorsListView.setVisibility(0);
        this.colorsListView.setSelectedColorIndex(PersistColorPalette.getInstance(this.currentAccount).getCurrentColorPosition());
        return;
    L27:
        return;
    L18:
        if (this.emojiPadding > 0) goto L21;
        r03 = false;
        goto L22
    L10:
        r4 = 1000.0f;
        goto L11
    }

    private void showEmojiPopup(int r9) {
        if (r9 != 1) goto L41;
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
        int r33 = r32 + this.parent.getPaddingUnderContainer();
        FrameLayout.LayoutParams r4 = (FrameLayout.LayoutParams) r2.getLayoutParams();
        r4.height = r33;
        r2.setLayoutParams(r4);
        if (AndroidUtilities.isInMultiwindow == false) goto L29;
    L33:
        this.emojiWasPadding = r33;
        this.emojiPadding = r33;
        this.keyboardNotifier.fire();
        requestLayout();
        ChatActivityEnterViewAnimatedIconView r22 = this.textOptionsView.getEmojiButton();
        if (r22 == null) goto L36;
        r22.setState(ChatActivityEnterViewAnimatedIconView.State.KEYBOARD, true);
    L36:
        if (r93 == false) goto L38;
    L54:
        updatePlusEmojiKeyboardButton();
        return;
    L38:
        if (this.keyboardVisible == false) goto L40;
        this.translateBottomPanelAfterResize = true;
        goto L54
    L40:
        ValueAnimator r94 = ValueAnimator.ofFloat(new float[]{this.emojiPadding, 0.0f});
        r94.addUpdateListener(new PaintView$$ExternalSyntheticLambda2(this));
        r94.addListener(new AnonymousClass29(this));
        r94.setDuration(250);
        r94.setInterpolator(AdjustPanLayoutHelper.keyboardInterpolator);
        r94.start();
        goto L54
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
    L41:
        ChatActivityEnterViewAnimatedIconView r24 = this.textOptionsView.getEmojiButton();
        if (r24 == null) goto L44;
        r24.setState(ChatActivityEnterViewAnimatedIconView.State.SMILE, true);
    L44:
        EmojiView r1 = this.emojiView;
        if (r1 == null) goto L51;
        this.emojiViewWasVisible = this.emojiViewVisible;
        this.emojiViewVisible = false;
        if (AndroidUtilities.usingHardwareInput == false) goto L49;
    L50:
        r1.setVisibility(8);
        goto L51
    L49:
        if (AndroidUtilities.isInMultiwindow == true) goto L50;
    L51:
        if (r9 != 0) goto L53;
        this.emojiPadding = 0;
        this.keyboardNotifier.fire();
    L53:
        requestLayout();
        goto L54
    }

    private void showLinkAlert(LinkView r6) {
        StoryLinkSheet r0 = new StoryLinkSheet(getContext(), this.resourcesProvider, this.previewView, new PaintView$$ExternalSyntheticLambda61(this, r6));
        if (r6 == null) goto L5;
        r0.set(r6.link);
    L5:
        r0.setOnDismissListener(new PaintView$$ExternalSyntheticLambda62(this));
        r0.show();
        onOpenCloseStickersAlert(true);
    }

    private void showLocationAlert(LocationView r8, Utilities.Callback2<TLRPC.MessageMedia, TL_stories.MediaArea> r9) {
        ChatAttachAlert r0 = new ChatAttachAlert(getContext(), new AnonymousClass24(this, null, r9), false, true, false, this.resourcesProvider);
        r0.setDelegate(new AnonymousClass25(this));
        if (r8 == null) goto L10;
        TLRPC.MessageMedia r82 = r8.location;
        if (r82 == null) goto L10;
        TLRPC.GeoPoint r83 = r82.geo;
        if (r83 == null) goto L10;
        r0.setStoryLocationPicker(r83.lat, r83._long);
    L13:
        r0.setOnDismissListener(new PaintView$$ExternalSyntheticLambda58(this));
        r0.init();
        r0.show();
        return;
    L10:
        if (this.fileFromGallery == false) goto L12;
        r0.setStoryLocationPicker(this.isVideo, this.file);
        goto L13
    L12:
        r0.setStoryLocationPicker();
        goto L13
    }

    private void showMenuForEntity(EntityView r10) {
        if ((r10 instanceof MessageEntityView) == false) goto L10;
        ActionBarPopupWindow r102 = this.popupWindow;
        if (r102 != null) goto L7;
        return;
    L7:
        if (r102.isShowing() == false) goto L13;
        this.popupWindow.dismiss();
        return;
    L13:
        return;
    L10:
        int[] r0 = getCenterLocationInWindow(r10);
        int r6 = r0[0];
        int r7 = r0[1] - AndroidUtilities.dp(32.0f);
        showPopup(new PaintView$$ExternalSyntheticLambda34(this, r10), this, 51, r6, r7, true);
    }

    private void showPopup(Runnable r6, View r7, int r8, int r9, int r10, boolean r11) {
        ActionBarPopupWindow r0 = this.popupWindow;
        if (r0 == null) goto L9;
        if (r0.isShowing() == false) goto L9;
        this.popupWindow.dismiss();
        return;
    L9:
        if (this.popupLayout != null) goto L11;
        this.popupRect = new Rect();
        PopupWindowLayout r02 = new PopupWindowLayout(this, getContext());
        this.popupLayout = r02;
        r02.setAnimationEnabled(true);
        this.popupLayout.setOnTouchListener(new PaintView$$ExternalSyntheticLambda35(this));
        this.popupLayout.setDispatchKeyEventListener(new PaintView$$ExternalSyntheticLambda36(this));
        this.popupLayout.setShownFromBottom(true);
    L11:
        PopupWindowLayout r03 = this.popupLayout;
        r03.enableBounce = r11;
        r03.removeInnerViews();
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
        this.popupWindow.setOnDismissListener(new PaintView$$ExternalSyntheticLambda37(this));
    L14:
        this.popupLayout.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(10000.0f), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(10000.0f), Integer.MIN_VALUE));
        this.popupWindow.setFocusable(true);
        if ((r8 & 48) == 0) goto L17;
        r9 = r9 - (this.popupLayout.getMeasuredWidth() / 2);
        r10 = r10 - this.popupLayout.getMeasuredHeight();
    L17:
        this.popupWindow.showAtLocation(r7, r8, r9, r10);
        ActionBarPopupWindow.startAnimation(this.popupLayout);
        Drawable r63 = getContext().getResources().getDrawable(R.drawable.popup_fixed_alert3).mutate();
        PopupWindowLayout r72 = this.popupLayout;
        if (r11 == false) goto L21;
        r72.setBackgroundDrawable(new BlurringShader.StoryBlurDrawer(this.blurManager, r72, 5).makeDrawable(r9, r10, r63, AndroidUtilities.dpf2(8.3f)));
        return;
    L21:
        r72.setBackgroundDrawable(r63);
        this.popupLayout.setBackgroundColor(-14145495);
    }

    private void showReactionsLayoutForView(ReactionWidgetEntityView r13) {
        ReactionWidgetEntityView r0 = this.reactionForEntity;
        if (r0 == null) goto L9;
        if (r0 == r13) goto L9;
        ReactionsContainerLayout r02 = this.reactionLayout;
        if (r02 == null) goto L9;
        r02.animate().alpha(0.0f).setListener(new AnonymousClass15(this, r02));
        this.reactionLayout = null;
        this.reactionLayoutShowing = false;
        this.reactionShowProgress = 0.0f;
    L9:
        if (this.reactionLayout != null) goto L11;
        ReactionsContainerLayout r4 = new ReactionsContainerLayout(2, LaunchActivity.getLastFragment(), getContext(), this.currentAccount, new AnonymousClass16(this, new DarkThemeResourceProvider()));
        this.reactionLayout = r4;
        BlurringShader.StoryBlurDrawer r03 = new BlurringShader.StoryBlurDrawer(this.blurManager, r4, 0);
        this.reactionLayout.setPadding(0, AndroidUtilities.dp(22.0f), 0, AndroidUtilities.dp(22.0f));
        this.parent.addView(this.reactionLayout, LayoutHelper.createFrame(-2, 96.0f, 53, 0.0f, 0.0f, 12.0f, 64.0f));
        Paint r2 = new Paint(1);
        r2.setColor(ColorUtils.setAlphaComponent(-16777216, 120));
        this.reactionLayout.setDelegate(new AnonymousClass17(this, r03, r2));
        this.reactionLayout.setMessage(null, null, true);
    L11:
        this.reactionLayout.setFragment(LaunchActivity.getLastFragment());
        this.reactionForEntity = r13;
        showReactionsLayout(true);
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
        this.typefaceMenuTransformAnimation.addUpdateListener(new PaintView$$ExternalSyntheticLambda51(this));
        this.typefaceMenuTransformAnimation.addEndListener(new PaintView$$ExternalSyntheticLambda52(this, r6));
        this.typefaceMenuTransformAnimation.start();
        return;
    L10:
        r4 = 1000.0f;
        goto L11
    }

    private PointF startPositionRelativeToEntity(EntityView r17) {
        MediaController.CropState r1 = this.currentCropState;
        float r2 = 200.0f;
        if (r1 == null) goto L5;
        r2 = 200.0f / r1.cropScale;
    L5:
        float r3 = 0.2f;
        if (r17 == null) goto L9;
        PointF r0 = r17.getPosition();
        float r12 = Math.min(r17.getHeight(), r17.getWidth()) * 0.2f;
        return new PointF(r0.x + r12, r0.y + r12);
    L9:
        float r4 = 100.0f;
        if (r1 == null) goto L12;
        r4 = 100.0f / r1.cropScale;
    L12:
        PointF r13 = centerPositionForEntity();
        int r6 = 0;
    L14:
        if (r6 >= 10) goto L31;
        int r7 = 0;
        boolean r8 = false;
    L17:
        if (r7 >= this.entitiesView.getChildCount()) goto L27;
        View r9 = this.entitiesView.getChildAt(r7);
        if ((r9 instanceof EntityView) == true) goto L21;
    L22:
        float r15 = r3;
        float r172 = r4;
    L26:
        r7 = r7 + 1;
        r4 = r172;
        r3 = r15;
        goto L17
    L21:
        if ((r9 instanceof MessageEntityView) == true) goto L22;
        PointF r10 = ((EntityView) r9).getPosition();
        r15 = r3;
        r172 = r4;
        if (((float) Math.sqrt(Math.pow(r10.x - r13.x, 2.0d) + Math.pow(r10.y - r13.y, 2.0d))) >= r172) goto L26;
        r2 = Math.min(r9.getHeight(), r9.getWidth()) * r15;
        r8 = true;
        goto L26
    L27:
        float r152 = r3;
        float r173 = r4;
        if (r8 == false) goto L31;
        r6 = r6 + 1;
        r13 = new PointF(r13.x + r2, r13.y + r2);
        r3 = r152;
        r4 = r173;
    L31:
        return r13;
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
        this.tabsSelectionAnimator.addUpdateListener(new PaintView$$ExternalSyntheticLambda16(this, r02, r1));
        this.tabsSelectionAnimator.addListener(new AnonymousClass21(this, r02, r1, r7));
        this.tabsSelectionAnimator.start();
        return;
    L13:
        r5 = false;
        goto L14
    }

    private void updatePlusEmojiKeyboardButton() {
        if (this.textOptionsView == null) goto L12;
        if (this.keyboardNotifier.keyboardVisible() == false) goto L7;
        this.textOptionsView.animatePlusToIcon(R.drawable.input_smile);
        goto L12
    L7:
        boolean r0 = this.emojiViewVisible;
        PaintTextOptionsView r1 = this.textOptionsView;
        if (r0 == false) goto L10;
        r1.animatePlusToIcon(R.drawable.input_keyboard);
        goto L12
    L10:
        r1.animatePlusToIcon(R.drawable.msg_add);
    L12:
        if (this.keyboardNotifier.keyboardVisible() == false) goto L14;
    L18:
        boolean r02 = true;
    L17:
        boolean r2 = r02;
        AndroidUtilities.updateViewShow(this.undoAllButton, !r2, false, 1.0f, true, null);
        AndroidUtilities.updateViewShow(this.undoButton, !r2, false, 1.0f, true, null);
        AndroidUtilities.updateViewShow(this.doneTextButton, r2, false, 1.0f, true, null);
        AndroidUtilities.updateViewShow(this.cancelTextButton, r2, false, 1.0f, true, null);
        return;
    L14:
        if (this.emojiViewVisible == true) goto L18;
        r02 = false;
        goto L17
    }

    private void updatePreviewViewTranslationY() {
        ObjectAnimator r0 = this.previewViewTranslationAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        View r02 = (View) this.renderView.getParent();
        if (r02 != null) goto L9;
        return;
    L9:
        if (this.keyboardNotifier.keyboardVisible() == false) goto L13;
        if (this.keyboardNotifier.ignoring == true) goto L13;
    L14:
        EntityView r1 = this.currentEntityView;
        if (r1 == null) goto L18;
        float r12 = (-(r1.getPosition().y - (r02.getMeasuredHeight() * 0.3f))) * r02.getScaleY();
    L19:
        ObjectAnimator r03 = ObjectAnimator.ofFloat(r02, View.TRANSLATION_Y, new float[]{r12});
        this.previewViewTranslationAnimator = r03;
        r03.setDuration(350);
        this.previewViewTranslationAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.previewViewTranslationAnimator.start();
        return;
    L18:
        r12 = 0.0f;
    L13:
        if (this.emojiPadding <= 0) goto L18;
        goto L14
    }

    private void updateTextDim() {
        if ((this.currentEntityView instanceof TextPaintView) == true) goto L5;
    L11:
        boolean r0 = false;
    L12:
        this.textDim.animate().cancel();
        this.textDim.setVisibility(0);
        ViewPropertyAnimator r1 = this.textDim.animate();
        if (r0 == false) goto L15;
        float r2 = 1.0f;
    L16:
        r1.alpha(r2).withEndAction(new PaintView$$ExternalSyntheticLambda33(this, r0)).start();
        return;
    L15:
        r2 = 0.0f;
        goto L16
    L5:
        if (this.keyboardNotifier.keyboardVisible() == true) goto L9;
        if (this.emojiPadding <= 0) goto L11;
    L9:
        if (this.keyboardNotifier.ignoring == true) goto L11;
        r0 = true;
        goto L12
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
        r5.animate().scaleX(r0).scaleY(r1).alpha(1.0f).setInterpolator(new OvershootInterpolator(3.0f)).setDuration(240).withEndAction(new PaintView$$ExternalSyntheticLambda30(this, r5)).start();
    }

    public abstract boolean checkAudioPermission(Runnable r1);

    public void clearAll() {
        if (this.undoStore.canUndo() == true) goto L5;
        return;
    L5:
        RenderView r0 = this.renderView;
        if (r0 != null) goto L8;
    L10:
        RenderView r02 = this.renderView;
        if (r02 == null) goto L13;
        r02.clearAll();
    L13:
        this.undoStore.reset();
        this.entitiesView.removeAllViews();
        return;
    L8:
        if ((r0.getCurrentBrush() instanceof Brush.Shape) == false) goto L10;
        this.renderView.clearShape();
        this.paintToolsView.setSelectedIndex(1);
        onBrushSelected(Brush.BRUSHES_LIST.get(0));
        goto L10
    }

    public void clearSelection() {
        selectEntity(null);
    }

    public void createEmojiView() {
        EmojiView r0 = this.emojiView;
        if (r0 == null) goto L8;
        if (r0.currentAccount == UserConfig.selectedAccount) goto L8;
        this.parent.removeView(r0);
        this.emojiView = null;
    L8:
        if (this.emojiView == null) goto L10;
        return;
    L10:
        EmojiView r1 = new EmojiView(null, true, false, false, getContext(), false, null, null, true, this.resourcesProvider, false, false, true);
        this.emojiView = r1;
        r1.fixBottomTabContainerTranslation = false;
        r1.allowEmojisForNonPremium(true, false);
        this.emojiView.setVisibility(8);
        if (AndroidUtilities.isTablet() == false) goto L13;
        this.emojiView.setForseMultiwindowLayout(true);
    L13:
        this.emojiView.setDelegate(new AnonymousClass31(this));
        this.parent.addView(this.emojiView);
    }

    public MessageEntityView createMessage(ArrayList<MessageObject> r12, boolean r13, boolean r14) {
        this.forceChanges = true;
        AnonymousClass26 r1 = new AnonymousClass26(this, getContext(), centerPositionForEntity(), 0.0f, 1.0f, r12, this.blurManager, r14, this.videoTextureHolder);
        r1.setDelegate(this);
        this.entitiesView.addView(r1);
        checkEntitiesIsVideo();
        if (r13 == false) goto L5;
        registerRemovalUndo(r1);
        selectEntity(r1);
    L5:
        return r1;
    }

    public PhotoView createPhoto(String r11, boolean r12) {
        this.forceChanges = true;
        Size r6 = basePhotoSize(r11);
        Pair<Integer, Integer> r1 = AndroidUtilities.getImageOrientation(r11);
        if (((((Integer) r1.first).intValue() / 90) % 2) != 1) goto L5;
        float r0 = r6.width;
        r6.width = r6.height;
        r6.height = r0;
    L5:
        PhotoView r13 = new PhotoView(getContext(), centerPositionForEntity(), 0.0f, 1.0f, r6, r11, ((Integer) r1.first).intValue(), ((Integer) r1.second).intValue());
        r13.setDelegate(this);
        this.entitiesView.addView(r13);
        checkEntitiesIsVideo();
        if (r12 == false) goto L8;
        registerRemovalUndo(r13);
        selectEntity(r13);
    L8:
        return r13;
    }

    public PhotoView createPhoto(TLObject r9, boolean r10) {
        this.forceChanges = true;
        Size r6 = basePhotoSize(r9);
        PhotoView r1 = new PhotoView(getContext(), centerPositionForEntity(), 0.0f, 1.0f, r6, r9);
        r1.setDelegate(this);
        this.entitiesView.addView(r1);
        checkEntitiesIsVideo();
        if (r10 == false) goto L5;
        registerRemovalUndo(r1);
        selectEntity(r1);
    L5:
        return r1;
    }

    public RoundView createRound(String r10, boolean r11) {
        this.forceChanges = true;
        this.creatingNewRound = true;
        deleteRound();
        int r0 = this.entitiesView.getMeasuredWidth();
        this.entitiesView.getMeasuredHeight();
        if (r0 > 0) goto L5;
        r0 = this.w;
    L5:
        float r1 = (float) Math.floor(0.43f * r0);
        Size r7 = new Size(r1, r1);
        RoundView r2 = new RoundView(getContext(), new PointF((r0 - (r7.width / 2.0f)) - AndroidUtilities.dp(16.0f), AndroidUtilities.dp(72.0f) + (r7.height / 2.0f)), 0.0f, 1.0f, r7, r10);
        r2.setDelegate(this);
        this.entitiesView.addView(r2);
        checkEntitiesIsVideo();
        if (r11 == false) goto L8;
        registerRemovalUndo(r2);
        post(new PaintView$$ExternalSyntheticLambda38(this, r2));
    L8:
        this.creatingNewRound = false;
        return r2;
    }

    public void deleteRound() {
        int r0 = 0;
    L4:
        if (r0 >= this.entitiesView.getChildCount()) goto L12;
        View r1 = this.entitiesView.getChildAt(r0);
        if ((r1 instanceof RoundView) == false) goto L11;
        if (this.currentEntityView != r1) goto L10;
        selectEntity(null);
    L10:
        r1.animate().scaleX(0.0f).scaleY(0.0f).setDuration(280).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).withEndAction(new PaintView$$ExternalSyntheticLambda48(this, r1)).start();
    L11:
        r0 = r0 + 1;
        goto L4
    }

    public void didSetAnimatedSticker(RLottieDrawable r1) {
    }

    public abstract void dismiss();

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r2) {
        if (this.isCoverPreview == false) goto L7;
        return false;
    L7:
        return super.dispatchTouchEvent(r2);
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r10, View r11, long r12) {
        int r1 = 0;
        if (r11 == this.renderView) goto L11;
        if (r11 == this.renderInputView) goto L11;
        if (r11 == this.entitiesView) goto L11;
        if (r11 == this.selectionContainerView) goto L11;
    L21:
        boolean r9 = super.drawChild(r10, r11, r12);
        if (r1 == 0) goto L24;
        r10.restore();
    L24:
        return r9;
    L11:
        if (this.currentCropState == null) goto L21;
        r10.save();
        if (this.inBubbleMode == true) goto L15;
        r1 = AndroidUtilities.statusBarHeight;
    L15:
        int r0 = ActionBar.getCurrentActionBarHeight() + r1;
        int r2 = r11.getMeasuredWidth();
        int r3 = r11.getMeasuredHeight();
        MediaController.CropState r4 = this.currentCropState;
        int r5 = r4.transformRotation;
        if (r5 != 90) goto L18;
    L19:
        r3 = r2;
        r2 = r3;
    L20:
        float r22 = (r2 * r4.cropPw) * r11.getScaleX();
        MediaController.CropState r42 = this.currentCropState;
        int r23 = (int) (r22 / r42.cropScale);
        int r32 = (int) (((r3 * r42.cropPh) * r11.getScaleY()) / this.currentCropState.cropScale);
        float r43 = ((float) Math.ceil((getMeasuredWidth() - r23) / 2.0f)) + this.transformX;
        float r02 = (((((((getMeasuredHeight() - r0) - AndroidUtilities.dp(48.0f)) + getAdditionalBottom()) - r32) / 2.0f) + AndroidUtilities.dp(8.0f)) + r1) + this.transformY;
        r10.clipRect(Math.max(0.0f, r43), Math.max(0.0f, r02), Math.min(r43 + r23, getMeasuredWidth()), Math.min(getMeasuredHeight(), r02 + r32));
        r1 = 1;
        goto L21
    L18:
        if (r5 != 270) goto L20;
        goto L19
    }

    public void editSelectedTextEntity() {
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

    public MessageEntityView findMessageView() {
        int r0 = 0;
    L4:
        if (r0 >= this.entitiesView.getChildCount()) goto L10;
        View r1 = this.entitiesView.getChildAt(r0);
        if ((r1 instanceof MessageEntityView) == true) goto L8;
        r0 = r0 + 1;
        goto L4
    L8:
        return (MessageEntityView) r1;
    L10:
        return null;
    }

    public RoundView findRoundView() {
        int r0 = 0;
    L4:
        if (r0 >= this.entitiesView.getChildCount()) goto L10;
        View r1 = this.entitiesView.getChildAt(r0);
        if ((r1 instanceof RoundView) == true) goto L8;
        r0 = r0 + 1;
        goto L4
    L8:
        return (RoundView) r1;
    L10:
        return null;
    }

    public int getAdditionalBottom() {
        return AndroidUtilities.dp(24.0f);
    }

    public int getAdditionalTop() {
        return AndroidUtilities.dp(48.0f);
    }

    public Bitmap getBitmap(ArrayList<VideoEditedInfo.MediaEntity> r33, int r34, int r35, boolean r36, boolean r37, boolean r38, boolean r39, StoryEntry r40) {
        ArrayList<VideoEditedInfo.MediaEntity> r2 = r33;
        StoryEntry r3 = r40;
        int r5 = 0;
        byte r6 = 1;
        if (r36 == false) goto L6;
        Bitmap r0 = this.renderView.getResultBitmap(false, r39);
    L5:
        Bitmap r7 = r0;
    L14:
        this.lcm = BigInteger.ONE;
        if (this.entitiesView.entitiesCount() <= 0) goto L339;
        int r8 = this.entitiesView.getChildCount();
        int r9 = 0;
    L17:
        if (r9 >= r8) goto L339;
        View r10 = this.entitiesView.getChildAt(r9);
        if ((r10 instanceof EntityView) == true) goto L21;
        int r14 = r5;
        int r26 = r8;
        int r27 = r9;
        byte r92 = r6;
    L338:
        r2 = r33;
        r3 = r40;
        r6 = r92;
        r5 = r14;
        r8 = r26;
        r9 = r27 + 1;
        goto L17
    L21:
        EntityView r02 = (EntityView) r10;
        r02.getPosition();
        VideoEditedInfo.MediaEntity r11 = new VideoEditedInfo.MediaEntity();
        if (r2 == null) goto L300;
        float r342 = 1.0f;
        long r21 = 5000;
        if ((r02 instanceof TextPaintView) == false) goto L70;
        r11.type = r6;
        TextPaintView r142 = (TextPaintView) r02;
        CharSequence r12 = r142.getText();
        if ((r12 instanceof Spanned) == false) goto L68;
        Spanned r4 = (Spanned) r12;
        AnimatedEmojiSpan[] r13 = (AnimatedEmojiSpan[]) r4.getSpans(r5, r12.length(), AnimatedEmojiSpan.class);
        if (r13 == null) goto L67;
        int r15 = r5;
    L31:
        if (r15 >= r13.length) goto L67;
        AnimatedEmojiSpan r52 = r13[r15];
        TLRPC.Document r62 = r52.document;
        if (r62 != null) goto L35;
        int r262 = r8;
        int r272 = r9;
        r62 = AnimatedEmojiDrawable.findDocument(this.currentAccount, r52.getDocumentId());
    L36:
        if (r62 == null) goto L38;
        AnimatedEmojiDrawable.getDocumentFetcher(this.currentAccount).putDocument(r62);
    L38:
        VideoEditedInfo.EmojiEntity r82 = new VideoEditedInfo.EmojiEntity();
        CharSequence r93 = r12;
        AnimatedEmojiSpan[] r28 = r13;
        r82.document_id = r52.getDocumentId();
        r82.document = r62;
        r82.offset = r4.getSpanStart(r52);
        r82.length = r4.getSpanEnd(r52) - r82.offset;
        r82.documentAbsolutePath = FileLoader.getInstance(this.currentAccount).getPathToAttach(r62, true).getAbsolutePath();
        int r53 = 0;
    L39:
        if (r62 == null) goto L50;
        ArrayList<TLRPC.PhotoSize> r122 = r62.thumbs;
        if (r122 == null) goto L50;
        if (r122.isEmpty() == true) goto L50;
        if (new File(r82.documentAbsolutePath).exists() == true) goto L50;
        Spanned r29 = r4;
        boolean r42 = true;
        r82.documentAbsolutePath = FileLoader.getInstance(this.currentAccount).getPathToAttach(r62.thumbs.get(r53), true).getAbsolutePath();
        r53 = r53 + 1;
        if (r53 >= r62.thumbs.size()) goto L51;
        r4 = r29;
    L51:
        boolean r54 = MessageObject.isAnimatedStickerDocument(r82.document, r42);
        if (r54 == false) goto L54;
    L55:
        byte r43 = r82.subType;
        if (r54 == false) goto L58;
        byte r55 = 1;
    L59:
        r82.subType = (byte) (r43 | r55);
    L61:
        if (MessageObject.isTextColorEmoji(r82.document) == false) goto L63;
        r82.subType = (byte) (r82.subType | 8);
    L63:
        r11.entities.add(r82);
        if (r62 == null) goto L66;
        BigInteger r44 = BigInteger.valueOf(5000);
        this.lcm = this.lcm.multiply(r44).divide(this.lcm.gcd(r44));
    L66:
        r15 = r15 + 1;
        r12 = r93;
        r8 = r262;
        r9 = r272;
        r13 = r28;
        r4 = r29;
        goto L31
    L58:
        r55 = 4;
        goto L59
    L54:
        if (isVideoStickerDocument(r82.document) == false) goto L61;
    L50:
        r29 = r4;
        r42 = true;
        goto L51
    L35:
        r262 = r8;
        r272 = r9;
    L67:
        r26 = r8;
        r27 = r9;
        CharSequence r94 = r12;
    L69:
        r11.text = r94.toString();
        r11.subType = (byte) r142.getType();
        r11.color = r142.getSwatch().color;
        r11.fontSize = r142.getTextSize();
        r11.textTypeface = r142.getTypeface();
        r11.textAlign = r142.getAlign();
    L167:
        View r392 = r10;
        boolean r45 = false;
    L168:
        boolean r56 = true;
    L143:
        ImageReceiver r123 = null;
    L249:
        r2.add(r11);
        float r63 = r392.getScaleX();
        float r83 = r392.getScaleY();
        float r95 = r392.getX();
        float r102 = r392.getY();
        r11.viewWidth = r392.getWidth();
        r11.viewHeight = r392.getHeight();
        r11.width = (r392.getWidth() * r63) / this.entitiesView.getMeasuredWidth();
        r11.height = (r392.getHeight() * r83) / this.entitiesView.getMeasuredHeight();
        r11.x = (((r392.getWidth() * (1.0f - r63)) / 2.0f) + r95) / this.entitiesView.getMeasuredWidth();
        r11.y = (((r392.getHeight() * (1.0f - r83)) / 2.0f) + r102) / this.entitiesView.getMeasuredHeight();
        r11.rotation = (float) ((-r392.getRotation()) * 0.017453292519943295d);
        r11.textViewX = ((r392.getWidth() / 2.0f) + r95) / this.entitiesView.getMeasuredWidth();
        r11.textViewY = ((r392.getHeight() / 2.0f) + r102) / this.entitiesView.getMeasuredHeight();
        r11.textViewWidth = r11.viewWidth / this.entitiesView.getMeasuredWidth();
        r11.textViewHeight = r11.viewHeight / this.entitiesView.getMeasuredHeight();
        r11.scale = r63;
        if ((r02 instanceof MessageEntityView) == false) goto L253;
        MessageEntityView r03 = (MessageEntityView) r02;
        RectF r132 = AndroidUtilities.rectTmp;
        double r143 = r03.getBubbleBounds(r132);
        r132.offset(r03.container.getX(), r03.container.getY());
        r132.offset(r03.listView.getX(), r03.listView.getY());
        r11.mediaArea.coordinates.x = ((((r95 + (r392.getWidth() / 2.0f)) - ((r392.getWidth() / 2.0f) * r63)) + (r132.centerX() * r63)) / this.entitiesView.getMeasuredWidth()) * 100.0f;
        r11.mediaArea.coordinates.y = ((((r102 + (r392.getHeight() / 2.0f)) - ((r392.getHeight() / 2.0f) * r83)) + (r132.centerY() * r83)) / this.entitiesView.getMeasuredHeight()) * 100.0f;
        r11.mediaArea.coordinates.w = ((r132.width() * r63) / this.entitiesView.getMeasuredWidth()) * 100.0f;
        r11.mediaArea.coordinates.h = ((r132.height() * r83) / this.entitiesView.getMeasuredHeight()) * 100.0f;
        r11.mediaArea.coordinates.rotation = ((-r11.rotation) / 3.141592653589793d) * 180.0d;
    L293:
        TL_stories.MediaArea r04 = r11.mediaArea;
        if (r04 == null) goto L301;
        TL_stories.MediaAreaCoordinates r05 = r04.coordinates;
        if (r05 == null) goto L301;
        if (r143 <= 0.0d) goto L301;
        r05.flags |= 1;
        r05.radius = ((r63 * r143) / r392.getWidth()) * 100.0d;
    L301:
        if (r56 == true) goto L302;
    L159:
        r92 = 1;
        r14 = 0;
        goto L338
    L302:
        if (r37 == true) goto L306;
        if (r38 == false) goto L159;
        if (r11.type != 6) goto L159;
    L306:
        if (r7 == null) goto L159;
        Canvas r22 = new Canvas(r7);
        float r32 = r7.getWidth() / this.entitiesView.getMeasuredWidth();
        int r57 = 0;
    L309:
        if (r57 >= 2) goto L335;
        if (r57 != 0) goto L312;
        Canvas r84 = r22;
    L313:
        if (r84 == null) goto L316;
        if (r57 != 0) goto L317;
        if (r45 == true) goto L316;
    L317:
        r84.save();
        r84.scale(r32, r32);
        r84.translate(r11.x * this.entitiesView.getMeasuredWidth(), r11.y * this.entitiesView.getMeasuredHeight());
        r84.scale(r392.getScaleX(), r392.getScaleY());
        r84.rotate(r392.getRotation(), ((r11.width / 2.0f) / r392.getScaleX()) * this.entitiesView.getMeasuredWidth(), ((r11.height / 2.0f) / r392.getScaleY()) * this.entitiesView.getMeasuredHeight());
        View r96 = r392;
        if ((r96 instanceof TextPaintView) == false) goto L330;
        if (r96.getHeight() <= 0) goto L330;
        if (r96.getWidth() <= 0) goto L330;
        int r06 = (int) (r96.getWidth() * r96.getScaleX());
        int r103 = (int) (r96.getHeight() * r96.getScaleY());
        Bitmap r133 = Bitmaps.createBitmap(r06, r103, Bitmap.Config.ARGB_8888);
        Canvas r144 = new Canvas(r133);
        r144.scale(r96.getScaleX(), r96.getScaleY());
        r96.draw(r144);
        r84.scale(r342 / r96.getScaleX(), r342 / r96.getScaleY());
        r84.drawBitmap(r133, null, new Rect(0, 0, r06, r103), new Paint(3));
        r144.setBitmap(null);     // Catch: Exception -> L326
    L328:
        r133.recycle();
    L333:
        r84.restore();
    L334:
        r57 = r57 + 1;
        r392 = r96;
    L326:
        e = move-exception;
        FileLog.e(e);
    L330:
        if ((r96 instanceof MessageEntityView) == false) goto L332;
        MessageEntityView r07 = (MessageEntityView) r96;
        r07.prepareToDraw(true);
        r96.draw(r84);
        r07.prepareToDraw(false);
        goto L333
    L332:
        r96.draw(r84);
    L316:
        r96 = r392;
        goto L334
    L312:
        r84 = null;
        goto L313
    L335:
        r14 = 0;
        r92 = 1;
        if (r123 == null) goto L338;
        r123.setVisible(true, false);
        goto L338
    L253:
        if ((r02 instanceof StickerView) == false) goto L260;
        float r08 = ((StickerView) r02).centerImage.getImageAspectRatio();
        float r23 = r11.x + (r11.width / 2.0f);
        float r310 = r11.y + (r11.height / 2.0f);
        float r85 = this.entitiesView.getMeasuredWidth() / this.entitiesView.getMeasuredHeight();
        if (r08 <= 1.0f) goto L258;
        float r24 = (r11.width * r85) / r08;
        r11.height = r24;
        r11.viewHeight = (int) (r11.viewWidth / r08);
        r11.y = r310 - (r24 / 2.0f);
    L292:
        r143 = -1.0d;
        goto L293
    L258:
        if (r08 >= 1.0f) goto L292;
        float r311 = (r11.height / r85) * r08;
        r11.width = r311;
        r11.viewWidth = (int) (r11.viewHeight * r08);
        r11.x = r23 - (r311 / 2.0f);
        goto L292
    L260:
        TL_stories.MediaArea r25 = r11.mediaArea;
        if (r25 == null) goto L292;
        boolean r312 = r02 instanceof LocationView;
        if (r312 == false) goto L265;
    L270:
        TL_stories.MediaAreaCoordinates r210 = r25.coordinates;
        float r97 = r11.x;
        float r104 = r11.width;
        r210.x = (r97 + (r104 / 2.0f)) * 100.0f;
        r210.y = (r11.y + (r11.height / 2.0f)) * 100.0f;
        if (r312 == false) goto L274;
        LocationView r98 = (LocationView) r02;
        r210.w = (r104 - (((r98.marker.padx * 2) * r63) / this.entitiesView.getMeasuredWidth())) * 100.0f;
        r11.mediaArea.coordinates.h = (r11.height - (((r98.marker.pady * 2) * r83) / this.entitiesView.getMeasuredHeight())) * 100.0f;
    L282:
        r11.mediaArea.coordinates.rotation = ((-r11.rotation) / 3.141592653589793d) * 180.0d;
        if (r312 == false) goto L287;
        float r09 = ((LocationView) r02).marker.getRadius();
    L285:
        r143 = r09;
        goto L293
    L287:
        if ((r02 instanceof WeatherView) == false) goto L290;
        r09 = ((WeatherView) r02).marker.getRadius();
        goto L285
    L290:
        if ((r02 instanceof LinkView) == false) goto L292;
        r09 = ((LinkView) r02).marker.getRadius();
        goto L285
    L274:
        if ((r02 instanceof WeatherView) == false) goto L277;
        WeatherView r99 = (WeatherView) r02;
        r210.w = (r104 - (((r99.marker.padx * 2) * r63) / this.entitiesView.getMeasuredWidth())) * 100.0f;
        r11.mediaArea.coordinates.h = (r11.height - (((r99.marker.pady * 2) * r83) / this.entitiesView.getMeasuredHeight())) * 100.0f;
        goto L282
    L277:
        if ((r02 instanceof LinkView) == false) goto L280;
        LinkView r910 = (LinkView) r02;
        r210.w = (r104 - (((r910.marker.padx * 2) * r63) / this.entitiesView.getMeasuredWidth())) * 100.0f;
        r11.mediaArea.coordinates.h = (r11.height - (((r910.marker.pady * 2) * r83) / this.entitiesView.getMeasuredHeight())) * 100.0f;
        goto L282
    L280:
        if ((r02 instanceof ReactionWidgetEntityView) == false) goto L282;
        ReactionWidgetEntityView r211 = (ReactionWidgetEntityView) r02;
        float r86 = ((r211.getPadding() * 2) * r63) / this.entitiesView.getMeasuredWidth();
        float r212 = ((r211.getPadding() * 2) * r63) / this.entitiesView.getMeasuredHeight();
        TL_stories.MediaAreaCoordinates r911 = r11.mediaArea.coordinates;
        r911.w = (r11.width - r86) * 100.0f;
        r911.h = (r11.height - r212) * 100.0f;
        goto L282
    L265:
        if ((r02 instanceof WeatherView) == true) goto L270;
        if ((r02 instanceof LinkView) == true) goto L270;
        if ((r02 instanceof ReactionWidgetEntityView) == false) goto L292;
    L68:
        r26 = r8;
        r27 = r9;
        r94 = r12;
        goto L69
    L70:
        r26 = r8;
        r27 = r9;
        if ((r02 instanceof StickerView) == false) goto L95;
        r11.type = 0;
        StickerView r46 = (StickerView) r02;
        Size r87 = r46.getBaseSize();
        r11.width = r87.width;
        r11.height = r87.height;
        r11.document = r46.getSticker();
        r11.parentObject = r46.getParentObject();
        TLRPC.Document r88 = r46.getSticker();
        r11.text = FileLoader.getInstance(UserConfig.selectedAccount).getPathToAttach(r88, true).getAbsolutePath();
        if (MessageObject.isAnimatedStickerDocument(r88, true) == false) goto L75;
    L76:
        boolean r912 = MessageObject.isAnimatedStickerDocument(r88, true);
        byte r124 = r11.subType;
        if (r912 == false) goto L79;
        byte r134 = 1;
    L80:
        r11.subType = (byte) (r124 | r134);
        if (r912 == false) goto L83;
    L84:
        r21 = r46.getDuration();
    L86:
        if (r21 == 0) goto L89;
        BigInteger r58 = BigInteger.valueOf(r21);
        this.lcm = this.lcm.multiply(r58).divide(this.lcm.gcd(r58));
    L89:
        if (MessageObject.isTextColorEmoji(r88) == false) goto L92;
        r11.color = -1;
        r11.subType = (byte) (r11.subType | 8);
    L92:
        if (r46.isMirrored() == false) goto L167;
        r11.subType = (byte) (r11.subType | 2);
        goto L167
    L83:
        if (isVideoStickerDocument(r88) == false) goto L86;
    L79:
        r134 = 4;
        goto L80
    L75:
        if (isVideoStickerDocument(r88) == false) goto L89;
    L95:
        if ((r02 instanceof PhotoView) == false) goto L107;
        PhotoView r47 = (PhotoView) r02;
        r11.type = 2;
        Size r59 = r47.getBaseSize();
        r11.width = r59.width;
        r11.height = r59.height;
        r11.text = r47.getPath(this.currentAccount);
        r11.crop = r47.crop;
        if (r47.isMirrored() == false) goto L100;
        r11.subType = (byte) (r11.subType | 2);
    L100:
        if (r47.hasSegmentedImage() == false) goto L167;
        if (r47.isSegmented() == false) goto L167;
        File r48 = r47.saveSegmentedImage(this.currentAccount);
        if (r48 == null) goto L167;
        r11.subType = (byte) (r11.subType | 16);
        r11.segmentedPath = r48.getPath();
        goto L167
    L107:
        if ((r02 instanceof LocationView) == false) goto L125;
        LocationView r49 = (LocationView) r02;
        r11.type = 3;
        r11.subType = (byte) r49.getType();
        r11.width = r49.marker.getWidth();
        r11.height = r49.marker.getHeight();
        r11.text = r49.marker.getText();
        if (r49.hasColor() == false) goto L111;
        int r510 = r49.getColor();
    L112:
        r11.color = r510;
        r11.density = r49.marker.density;
        r11.media = r49.location;
        TL_stories.MediaArea r511 = r49.mediaArea;
        r11.mediaArea = r511;
        r511.coordinates = new TL_stories.TL_mediaAreaCoordinates();
        TLRPC.Document r410 = r49.marker.getCodeEmojiDocument();
        if (r410 == null) goto L167;
        VideoEditedInfo.EmojiEntity r512 = new VideoEditedInfo.EmojiEntity();
        r512.document_id = r410.id;
        r512.document = r410;
        r512.documentAbsolutePath = FileLoader.getInstance(this.currentAccount).getPathToAttach(r410, true).getAbsolutePath();
        boolean r411 = MessageObject.isAnimatedStickerDocument(r512.document, true);
        if (r411 == false) goto L117;
    L118:
        byte r64 = r512.subType;
        if (r411 == false) goto L121;
        byte r125 = 1;
    L122:
        r512.subType = (byte) (r64 | r125);
    L123:
        r11.entities.add(r512);
        goto L167
    L121:
        r125 = 4;
        goto L122
    L117:
        if (isVideoStickerDocument(r512.document) == false) goto L123;
    L111:
        r510 = 0;
        goto L112
    L125:
        if ((r02 instanceof WeatherView) == false) goto L145;
        WeatherView r412 = (WeatherView) r02;
        r11.type = 8;
        r11.subType = (byte) r412.getType();
        r11.width = r412.marker.getWidth();
        r11.height = r412.marker.getHeight();
        r11.text = r412.marker.getText();
        if (r412.hasColor() == false) goto L129;
        int r513 = r412.getColor();
    L130:
        r11.color = r513;
        r11.density = r412.marker.density;
        r11.weather = r412.weather;
        TL_stories.TL_mediaAreaWeather r514 = new TL_stories.TL_mediaAreaWeather();
        r514.emoji = r412.weather.emoji;
        r514.temperature_c = Math.round(r6.temperature);
        r514.color = r412.marker.outlinePaint.getColor();
        r11.mediaArea = r514;
        r514.coordinates = new TL_stories.TL_mediaAreaCoordinates();
        TLRPC.Document r413 = r412.marker.getCodeEmojiDocument();
        if (r413 == null) goto L142;
        VideoEditedInfo.EmojiEntity r515 = new VideoEditedInfo.EmojiEntity();
        r515.document_id = r413.id;
        r515.document = r413;
        r515.documentAbsolutePath = FileLoader.getInstance(this.currentAccount).getPathToAttach(r413, true).getAbsolutePath();
        boolean r414 = MessageObject.isAnimatedStickerDocument(r515.document, true);
        if (r414 == false) goto L135;
    L136:
        byte r65 = r515.subType;
        if (r414 == false) goto L139;
        byte r126 = 1;
    L140:
        r515.subType = (byte) (r65 | r126);
    L141:
        r11.entities.add(r515);
        goto L142
    L139:
        r126 = 4;
        goto L140
    L135:
        if (isVideoStickerDocument(r515.document) == false) goto L141;
    L142:
        r392 = r10;
        r45 = false;
        r56 = false;
        goto L143
    L129:
        r513 = 0;
        goto L130
    L145:
        if ((r02 instanceof LinkView) == false) goto L170;
        LinkView r415 = (LinkView) r02;
        r11.type = 7;
        if (r415.marker.withPreview() == false) goto L149;
        r11.subType = (byte) r415.marker.getPreviewType();
    L150:
        r11.width = r415.marker.getWidth();
        r11.height = r415.marker.getHeight();
        if (r415.hasColor() == false) goto L153;
        int r516 = r415.getColor();
    L154:
        r11.color = r516;
        LinkPreview r517 = r415.marker;
        r11.density = r517.density;
        r11.linkSettings = r415.link;
        if (r517.hasPhoto == false) goto L157;
        r517.pushPhotoToCache();
        LinkPreview.WebPagePreview r518 = r11.linkSettings;
        r518.flags |= 4;
        r518.photoSize = r415.marker.getPhotoSide();
    L157:
        TL_stories.TL_mediaAreaUrl r519 = new TL_stories.TL_mediaAreaUrl();
        r11.mediaArea = r519;
        LinkPreview.WebPagePreview r66 = r415.link;
        if (r66 == null) goto L159;
        TLRPC.WebPage r67 = r66.webpage;
        if (r67 != null) goto L163;
    L165:
        String r416 = r415.link.url;
    L166:
        r519.url = r416;
        r11.mediaArea.coordinates = new TL_stories.TL_mediaAreaCoordinates();
        goto L167
    L163:
        if (TextUtils.isEmpty(r67.url) == true) goto L165;
        r416 = r415.link.webpage.url;
        goto L166
    L153:
        r516 = 0;
        goto L154
    L149:
        r11.subType = (byte) r415.getType();
        goto L150
    L170:
        if ((r02 instanceof ReactionWidgetEntityView) == false) goto L174;
        ReactionWidgetEntityView r417 = (ReactionWidgetEntityView) r02;
        r11.type = 4;
        TL_stories.TL_mediaAreaSuggestedReaction r520 = new TL_stories.TL_mediaAreaSuggestedReaction();
        r11.mediaArea = r520;
        r520.reaction = ReactionsUtils.toTLReaction(r417.getCurrentReaction());
        r11.mediaArea.dark = r417.isDark();
        r11.mediaArea.flipped = r417.isMirrored();
        r11.mediaArea.coordinates = new TL_stories.TL_mediaAreaCoordinates();
    L172:
        r392 = r10;
        r45 = true;
        goto L168
    L174:
        if ((r02 instanceof RoundView) == false) goto L182;
        RoundView r418 = (RoundView) r02;
        Size r521 = r418.getBaseSize();
        r11.width = r521.width;
        r11.height = r521.height;
        r11.type = 5;
        if (r3 == null) goto L178;
        r11.text = r3.round.getAbsolutePath();
        r11.roundOffset = r3.roundOffset;
        long r522 = r3.roundDuration;
        r11.roundDuration = r522;
        r11.roundLeft = (long) (r3.roundLeft * r522);
        r11.roundRight = (long) (r3.roundRight * r522);
    L178:
        r11.subType = 4;
        if (r418.isMirrored() == false) goto L172;
        r11.subType = (byte) (r11.subType | 2);
        goto L172
    L182:
        if ((r02 instanceof MessageEntityView) == false) goto L159;
        MessageEntityView r419 = (MessageEntityView) r02;
        r11.type = 6;
        int r913 = r419.getWidth();
        r11.viewWidth = r913;
        r11.width = r913;
        int r914 = r419.getHeight();
        r11.viewHeight = r914;
        r11.height = r914;
        if (r419.messageObjects.size() <= 0) goto L186;
        MessageObject r915 = r419.messageObjects.get(0);
    L187:
        if (r915 == null) goto L227;
        TLRPC.Message r127 = r915.messageOwner;
        if (r127 == null) goto L227;
        TLRPC.MessageAction r128 = r127.action;
        if ((r128 instanceof TLRPC.TL_messageActionStarGiftUnique) == false) goto L227;
        TL_stars.StarGift r916 = ((TLRPC.TL_messageActionStarGiftUnique) r128).gift;
        TL_stories.TL_mediaAreaStarGift r129 = new TL_stories.TL_mediaAreaStarGift();
        r11.mediaArea = r129;
        r129.slug = r916.slug;
        r129.coordinates = new TL_stories.TL_mediaAreaCoordinates();
        int r1210 = 0;
    L194:
        if (r1210 >= r419.listView.getChildCount()) goto L199;
        View r135 = r419.listView.getChildAt(r1210);
        if ((r135 instanceof ChatActionCell) == true) goto L197;
        r1210 = r1210 + 1;
        goto L194
    L197:
        ChatActionCell r136 = (ChatActionCell) r135;
    L200:
        if (r136 == null) goto L226;
        StarGiftUniqueActionLayout r1211 = r136.starGiftLayout;
        if (r1211 == null) goto L226;
        r123 = r1211.imageReceiver;
        if (r123 == null) goto L226;
        r123.setVisible(false, false);
        TL_stars.starGiftAttributeModel r145 = (TL_stars.starGiftAttributeModel) StarsController.findAttribute(r916.attributes, TL_stars.starGiftAttributeModel.class);
        if (r145 == null) goto L225;
        float r152 = AndroidUtilities.dp(110.0f);
        float r523 = ((r419.listView.getX() + r136.getX()) + r136.starGiftLayoutX) + r123.getCenterX();
        float r68 = ((r419.listView.getY() + r136.getY()) + r136.starGiftLayoutY) + r123.getCenterY();
        VideoEditedInfo.MediaEntity r137 = new VideoEditedInfo.MediaEntity();
        r137.type = 0;
        r137.width = r152;
        r137.height = r152;
        TLRPC.Document r89 = r145.document;
        r137.document = r89;
        r137.parentObject = r916;
        r137.text = FileLoader.getInstance(UserConfig.selectedAccount).getPathToAttach(r89, true).getAbsolutePath();
        if (MessageObject.isAnimatedStickerDocument(r89, true) == false) goto L210;
    L211:
        boolean r917 = MessageObject.isAnimatedStickerDocument(r89, true);
        byte r146 = r137.subType;
        if (r917 == false) goto L214;
        byte r232 = 1;
    L215:
        r137.subType = (byte) (r146 | r232);
        RLottieDrawable r147 = r123.getLottieAnimation();
        if (r147 == null) goto L222;
        if (r917 == false) goto L219;
    L220:
        r21 = r147.getDuration();
        goto L222
    L219:
        if (isVideoStickerDocument(r89) == true) goto L220;
    L222:
        if (r21 == 0) goto L224;
        BigInteger r810 = BigInteger.valueOf(r21);
        this.lcm = this.lcm.multiply(r810).divide(this.lcm.gcd(r810));
    L224:
        float r811 = r10.getScaleX();
        float r918 = r10.getScaleY();
        float r148 = r10.getX();
        float r213 = r10.getY();
        int r524 = (int) r152;
        r137.viewWidth = r524;
        r137.viewHeight = r524;
        r137.width = (r152 * r811) / this.entitiesView.getMeasuredWidth();
        r137.height = (r152 * r918) / this.entitiesView.getMeasuredHeight();
        r137.x = r148 + (r10.getWidth() / 2.0f);
        r137.y = r213 + (r10.getHeight() / 2.0f);
        float r69 = (r68 * r918) - ((r10.getHeight() / 2.0f) * r918);
        r392 = r10;
        MessageEntityView r149 = r419;
        double r313 = (r523 * r811) - ((r10.getWidth() / 2.0f) * r811);
        double r919 = (float) ((r10.getRotation() / 180.0f) * 3.141592653589793d);
        double r525 = r69;
        r137.x = (float) (r137.x + ((Math.cos(r919) * r313) - (Math.sin(r919) * r525)));
        float r314 = (float) (r137.y + ((Math.sin(r919) * r313) + (r525 * Math.cos(r919))));
        float r526 = (-r152) / 2.0f;
        float r420 = r137.x + (r526 * r811);
        r137.x = r420;
        r137.y = r314 + (r526 * r918);
        r137.x = r420 / this.entitiesView.getMeasuredWidth();
        r137.y /= this.entitiesView.getMeasuredHeight();
        r137.rotation = (float) ((-r392.getRotation()) * 0.017453292519943295d);
        r137.scale = r811;
        r2.add(r137);
    L232:
        if (r38 == true) goto L236;
        r45 = true;
    L234:
        r56 = true;
        goto L249
    L236:
        if (r40 != null) goto L238;
    L248:
        r45 = false;
        goto L234
    L238:
        if (r40.isVideo == false) goto L248;
        r40.matrix.reset();
        if (r149.listView.getChildCount() != 1) goto L245;
        View r421 = r149.listView.getChildAt(0);
        if ((r421 instanceof ChatMessageCell) == false) goto L244;
        ImageReceiver r527 = ((ChatMessageCell) r421).getPhotoImage();
    L246:
        if (r527 == null) goto L248;
        float r610 = Math.max(r527.getImageWidth() / Math.max(1, r40.width), r527.getImageHeight() / Math.max(1, r40.height));
        r40.matrix.postScale(r610, r610);
        r40.matrix.postTranslate(r527.getCenterX() - ((r40.width * r610) / 2.0f), r527.getCenterY() - ((r40.height * r610) / 2.0f));
        r40.matrix.postTranslate(r149.container.getX(), r149.container.getY());
        r40.matrix.postTranslate(r149.listView.getX(), r149.listView.getY());
        r40.matrix.postTranslate(r421.getX(), r421.getY());
        r40.matrix.postScale(r149.getScaleX(), r149.getScaleY(), r149.getPivotX(), r149.getPivotY());
        r40.matrix.postRotate(r149.getRotation(), r149.getPivotX(), r149.getPivotY());
        r40.matrix.postTranslate(r149.getX(), r149.getY());
        r40.matrix.postScale(1.0f / this.entitiesView.getWidth(), 1.0f / this.entitiesView.getHeight());
        r40.matrix.postScale(r40.resultWidth, r40.resultHeight);
    L244:
        r527 = null;
        goto L246
    L245:
        r421 = null;
        goto L244
    L214:
        r232 = 4;
        goto L215
    L210:
        if (isVideoStickerDocument(r89) == false) goto L224;
    L225:
        r149 = r419;
        r392 = r10;
    L226:
        r149 = r419;
        r392 = r10;
    L231:
        r123 = null;
        goto L232
    L199:
        r136 = null;
    L227:
        r149 = r419;
        r392 = r10;
        if (r915 == null) goto L230;
        TL_stories.TL_inputMediaAreaChannelPost r315 = new TL_stories.TL_inputMediaAreaChannelPost();
        r11.mediaArea = r315;
        r315.coordinates = new TL_stories.TL_mediaAreaCoordinates();
        ((TL_stories.TL_inputMediaAreaChannelPost) r11.mediaArea).channel = MessagesController.getInstance(this.currentAccount).getInputChannel(-StoryEntry.getRepostDialogId(r915));
        ((TL_stories.TL_inputMediaAreaChannelPost) r11.mediaArea).msg_id = StoryEntry.getRepostMessageId(r915);
        goto L231
    L230:
        TL_stories.TL_inputMediaAreaChannelPost r316 = new TL_stories.TL_inputMediaAreaChannelPost();
        r11.mediaArea = r316;
        r316.coordinates = new TL_stories.TL_mediaAreaCoordinates();
        goto L231
    L186:
        r915 = null;
        goto L187
    L300:
        r26 = r8;
        r27 = r9;
        r392 = r10;
        r342 = 1.0f;
        r45 = false;
        r56 = true;
        r123 = null;
    L339:
        return r7;
    L6:
        if (r38 == false) goto L8;
        r0 = Bitmap.createBitmap(Math.max(1, this.entitiesView.getMeasuredWidth()), Math.max(1, this.entitiesView.getMeasuredHeight()), Bitmap.Config.ARGB_8888);
        goto L5
    L8:
        if (r37 == false) goto L13;
        Bitmap r010 = this.renderView.getResultBitmap(false, false);
        if (r010 == null) goto L12;
        r0 = Bitmap.createBitmap(r010.getWidth(), r010.getHeight(), Bitmap.Config.ARGB_8888);
        goto L5
    L12:
        r0 = null;
        goto L5
    L13:
        r7 = null;
        goto L14
    }

    public Bitmap getBlurBitmap() {
        return this.renderView.getResultBitmap(true, false);
    }

    public View getBottomLayout() {
        return this.bottomLayout;
    }

    public View getCancelView() {
        return this.cancelButton;
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView.EntityViewDelegate
    public int[] getCenterLocation(EntityView r4) {
        this.pos[0] = (int) r4.getPosition().x;
        this.pos[1] = (int) r4.getPosition().y;
        return this.pos;
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

    public View getEntitiesView() {
        return this.entitiesView;
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

    public List<View> getPreviewViews() {
        return Arrays.asList(new View[]{this.renderView, this.renderInputView, this.entitiesView, this.selectionContainerView});
    }

    public View getRenderInputView() {
        return this.renderInputView;
    }

    public RenderView getRenderView() {
        return this.renderView;
    }

    public EntityView getSelectedEntity() {
        return this.currentEntityView;
    }

    public View getSelectionEntitiesView() {
        return this.selectionContainerView;
    }

    public View getTextDimView() {
        return this.textDim;
    }

    public View getTopLayout() {
        return this.topLayout;
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView.EntityViewDelegate
    public void getTransformedTouch(float r3, float r4, float[] r5) {
        View r2 = (View) this.renderView.getParent();
        if (r2 != null) goto L6;
        return;
    L6:
        if (((View) r2.getParent()) != null) goto L8;
        return;
    L8:
        float r32 = (r3 - r2.getX()) - r0.getLeft();
        float r42 = (r4 - r2.getY()) - r0.getTop();
        float r0 = r2.getPivotX() + ((r32 - r2.getPivotX()) / r2.getScaleX());
        float r33 = r2.getPivotY() + ((r42 - r2.getPivotY()) / r2.getScaleY());
        r5[0] = r0;
        r5[1] = r33;
    }

    @Override // org.telegram.ui.Components.IPhotoPaintView
    public /* bridge */ /* synthetic */ View getView() {
        return super.getView();
    }

    public View getWeightChooserView() {
        return this.weightChooserView;
    }

    public boolean hasBlur() {
        return this.renderView.getPainting().hasBlur;
    }

    public boolean hasChanges() {
        if (this.undoStore.canUndo() == false) goto L5;
        return true;
    L5:
        if (this.forceChanges == true) goto L11;
        return false;
    L11:
        return true;
    }

    public void init() {
        this.entitiesView.setVisibility(0);
        this.renderView.setVisibility(0);
        this.renderInputView.setVisibility(0);
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView.EntityViewDelegate
    public boolean isEntityDeletable() {
        return isEntityDeletable(this.currentEntityView);
    }

    public boolean isEntityDeletable(EntityView r1) {
        return !(r1 instanceof MessageEntityView);
    }

    @Override // org.telegram.ui.Components.SizeNotifierFrameLayoutPhoto, org.telegram.ui.Components.SizeNotifierFrameLayout
    public int measureKeyboardHeight() {
        return this.keyboardNotifier.getKeyboardHeight() - this.parent.getBottomPadding2();
    }

    @Override // org.telegram.ui.Components.Paint.Views.PaintToolsView.Delegate
    public void onAddButtonPressed(View r8) {
        showPopup(new PaintView$$ExternalSyntheticLambda60(this), this, 53, 0, getHeight(), false);
    }

    public void onAnimationStateChanged(boolean r2) {
        PaintWeightChooserView r1 = this.weightChooserView;
        if (r2 == false) goto L5;
        int r22 = 2;
    L6:
        r1.setLayerType(r22, null);
        return;
    L5:
        r22 = 0;
        goto L6
    }

    @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        this.destroyed = false;
        super.onAttachedToWindow();
    }

    public abstract void onAudioSelect(MessageObject r1);

    public boolean onBackPressed() {
        if (this.reactionLayoutShowing == false) goto L12;
        if (this.reactionLayout.getReactionsWindow() != null) goto L7;
    L9:
        showReactionsLayout(false);
    L10:
        return true;
    L7:
        if (this.reactionLayout.getReactionsWindow().isShowing() == false) goto L9;
        this.reactionLayout.dismissWindow();
        goto L10
    L12:
        if (this.isColorListShown == false) goto L16;
        showColorList(false);
        return true;
    L16:
        if (this.emojiViewVisible == false) goto L20;
        hideEmojiPopup(true);
        return true;
    L20:
        if (this.editingText == true) goto L22;
        return false;
    L22:
        if (this.enteredThroughText == false) goto L25;
        this.enteredThroughText = false;
        this.keyboardNotifier.ignore(true);
        return false;
    L25:
        selectEntity(null);
        return true;
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
        setCurrentSwatch(this.colorSwatch, true, Integer.valueOf(r0), false);
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

    public abstract void onCreateRound(RoundView r1);

    public abstract void onDeleteRound();

    public abstract void onDeselectRound(RoundView r1);

    @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        this.destroyed = true;
        ReactionsContainerLayout r0 = this.reactionLayout;
        if (r0 == null) goto L5;
        AndroidUtilities.removeFromParent(r0);
        this.reactionLayout = null;
    L5:
        super.onDetachedFromWindow();
    }

    public void onEmojiButtonClick() {
        if (this.emojiViewVisible == true) goto L5;
    L7:
        showEmojiPopup(!this.emojiViewVisible ? 1 : 0);
        return;
    L5:
        if ((this.currentEntityView instanceof TextPaintView) == false) goto L7;
        this.keyboardNotifier.awaitKeyboard();
        AndroidUtilities.showKeyboard(((TextPaintView) this.currentEntityView).getEditText());
        goto L7
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView.EntityViewDelegate
    public void onEntityDragEnd(boolean r1) {
        updatePreviewViewTranslationY();
        this.forceChanges = true;
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView.EntityViewDelegate
    public boolean onEntityLongClicked(EntityView r2) {
        showReactionsLayout(false);
        showMenuForEntity(r2);
        return true;
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView.EntityViewDelegate
    public boolean onEntitySelected(EntityView r1) {
        return selectEntity(r1);
    }

    public abstract void onGalleryClick();

    @Override // org.telegram.ui.Components.Paint.Views.PaintToolsView.Delegate
    public PersistColorPalette onGetPalette() {
        return PersistColorPalette.getInstance(this.currentAccount);
    }

    @Override // org.telegram.ui.Components.SizeNotifierFrameLayoutPhoto, org.telegram.ui.Components.SizeNotifierFrameLayout, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r10, int r11) {
        this.ignoreLayout = true;
        int r1 = View.MeasureSpec.getSize(r10);
        int r2 = View.MeasureSpec.getSize(r11);
        setMeasuredDimension(r1, r2);
        int r3 = (((AndroidUtilities.displaySize.y - ActionBar.getCurrentActionBarHeight()) - getAdditionalTop()) - getAdditionalBottom()) - AndroidUtilities.dp(48.0f);
        Bitmap r5 = this.bitmapToEdit;
        if (r5 == null) goto L5;
        float r4 = r5.getWidth();
        float r52 = this.bitmapToEdit.getHeight();
    L6:
        float r12 = r1;
        if (((float) Math.floor((r12 * r52) / r4)) <= r3) goto L9;
        r12 = (float) Math.floor((r3 * r4) / r52);
    L9:
        this.baseScale = r12 / this.paintingSize.width;
        EntityView r13 = this.currentEntityView;
        if (r13 == null) goto L12;
        r13.updateSelectionView();
    L12:
        measureChild(this.bottomLayout, r10, r11);
        measureChild(this.weightChooserView, r10, r11);
        measureChild(this.pipetteContainerLayout, r10, r11);
        measureChild(this.overlayLayout, r10, View.MeasureSpec.makeMeasureSpec(r2 - Math.max(this.emojiPadding - this.parent.getPaddingUnderContainer(), measureKeyboardHeight()), TLObject.FLAG_30));
        FrameLayout r14 = this.topLayout;
        r14.setPadding(r14.getPaddingLeft(), AndroidUtilities.dp(12.0f), this.topLayout.getPaddingRight(), this.topLayout.getPaddingBottom());
        measureChild(this.topLayout, r10, r11);
        this.ignoreLayout = false;
        if (this.waitingForKeyboardOpen == true) goto L22;
        if (AndroidUtilities.dp(20.0f) < 0) goto L22;
        if (this.emojiViewVisible == true) goto L22;
        if (this.isAnimatePopupClosing == true) goto L22;
        this.ignoreLayout = true;
        hideEmojiView();
        this.ignoreLayout = false;
    L22:
        if (AndroidUtilities.dp(20.0f) < 0) goto L24;
        return;
    L24:
        hideEmojiView();
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
        this.forceChanges = true;
        createText(true);
    }

    public abstract void onOpenCloseStickersAlert(boolean r1);

    public void onParentPreDraw() {
        if (this.invalidateReactionPosition == false) goto L28;
        this.invalidateReactionPosition = false;
        if (this.reactionLayout != null) goto L7;
        return;
    L7:
        if (this.reactionForEntity == null) goto L30;
        this.points[0] = r1.getMeasuredWidth() / 2.0f;
        this.points[1] = this.reactionForEntity.getMeasuredHeight() / 2.0f;
        this.reactionForEntity.getMatrix().mapPoints(this.points);
        float r1 = this.points[1] - ((this.reactionForEntity.getMeasuredHeight() / 2.0f) * this.reactionForEntity.getScaleX());
        float r2 = this.points[1] + ((this.reactionForEntity.getMeasuredHeight() / 2.0f) * this.reactionForEntity.getScaleX());
        if (r1 < AndroidUtilities.dp(120.0f)) goto L11;
    L13:
        float r22 = AndroidUtilities.dp(120.0f);
        ReactionsContainerLayout r5 = this.reactionLayout;
        if (r1 >= r22) goto L16;
        r5.setTop(true);
        this.reactionLayout.setTranslationY(this.points[1] + ((this.reactionForEntity.getMeasuredHeight() / 2.0f) * this.reactionForEntity.getScaleX()));
    L18:
        if (this.points[0] >= (getMeasuredWidth() / 2.0f)) goto L22;
        this.reactionLayout.setMirrorX(true);
        float r12 = (this.reactionLayout.getX() + (this.reactionLayout.getMeasuredWidth() / 2.0f)) - (this.points[0] - (this.reactionForEntity.getMeasuredHeight() / 2.0f));
        if (r12 <= 0.0f) goto L26;
        this.reactionLayout.setBubbleOffset(((r0.getMeasuredWidth() / 2.0f) - r12) / 2.0f);
    L26:
        this.reactionLayout.setTranslationX((this.parent.getMeasuredWidth() - this.reactionLayout.getMeasuredWidth()) / 2.0f);
        return;
    L22:
        float r4 = (this.reactionLayout.getX() + (this.reactionLayout.getMeasuredWidth() / 2.0f)) - (this.points[0] + (this.reactionForEntity.getMeasuredHeight() / 2.0f));
        if (r4 >= 0.0f) goto L25;
        this.reactionLayout.setBubbleOffset((-((r1.getMeasuredWidth() / 2.0f) + r4)) / 2.0f);
    L25:
        this.reactionLayout.setMirrorX(false);
        goto L26
    L16:
        r5.setTop(false);
        this.reactionLayout.setTranslationY((r1 - r2.getMeasuredHeight()) + AndroidUtilities.dp(16.0f));
        goto L18
    L11:
        if (r2 <= (this.parent.getMeasuredHeight() - AndroidUtilities.dp(200.0f))) goto L13;
        this.reactionLayout.setTop(false);
        this.reactionLayout.setTranslationY((AndroidUtilities.dp(120.0f) - this.reactionLayout.getMeasuredHeight()) + AndroidUtilities.dp(16.0f));
        goto L18
    L30:
        return;
    }

    @Override // org.telegram.ui.Components.SizeNotifierFrameLayout
    public void onResume() {
        this.renderView.redraw();
    }

    public abstract void onSelectRound(RoundView r1);

    @Override // org.telegram.ui.Components.SizeNotifierFrameLayout.SizeNotifierFrameLayoutDelegate
    public void onSizeChanged(int r5, boolean r6) {
        if (r5 <= AndroidUtilities.dp(50.0f)) goto L14;
        if (this.keyboardVisible == false) goto L14;
        if (AndroidUtilities.isInMultiwindow == true) goto L14;
        if (AndroidUtilities.isTablet() == true) goto L14;
        if (r6 == false) goto L12;
        this.keyboardHeightLand = r5;
        MessagesController.getGlobalEmojiSettings().edit().putInt("kbd_height_land3", this.keyboardHeightLand).commit();
        goto L14
    L12:
        this.keyboardHeight = r5;
        MessagesController.getGlobalEmojiSettings().edit().putInt("kbd_height", this.keyboardHeight).commit();
    L14:
        if (this.emojiViewVisible == false) goto L24;
        if (r6 == false) goto L17;
        int r0 = this.keyboardHeightLand;
    L18:
        int r02 = r0 + this.parent.getPaddingUnderContainer();
        FrameLayout.LayoutParams r1 = (FrameLayout.LayoutParams) this.emojiView.getLayoutParams();
        int r2 = r1.width;
        int r3 = AndroidUtilities.displaySize.x;
        if (r2 == r3) goto L21;
    L22:
        r1.width = r3;
        r1.height = r02;
        this.emojiView.setLayoutParams(r1);
        int r03 = r1.height;
        this.emojiWasPadding = r03;
        this.emojiPadding = r03;
        this.keyboardNotifier.fire();
        requestLayout();
        goto L24
    L21:
        if (r1.height == r02) goto L24;
    L17:
        r0 = this.keyboardHeight;
    L24:
        if (this.lastSizeChangeValue1 == r5) goto L26;
    L28:
        this.lastSizeChangeValue1 = r5;
        this.lastSizeChangeValue2 = r6;
        boolean r52 = this.keyboardVisible;
        EntityView r62 = this.currentEntityView;
        if ((r62 instanceof TextPaintView) == true) goto L31;
        this.keyboardVisible = false;
    L39:
        if (this.keyboardVisible == false) goto L44;
        if (this.emojiViewVisible == false) goto L44;
        showEmojiPopup(0);
    L44:
        if (this.emojiPadding == 0) goto L51;
        boolean r63 = this.keyboardVisible;
        if (r63 == true) goto L51;
        if (r63 == r52) goto L51;
        if (this.emojiViewVisible == true) goto L51;
        this.emojiPadding = 0;
        this.keyboardNotifier.fire();
        requestLayout();
    L51:
        updateTextDim();
        if (r52 == false) goto L61;
        if (this.keyboardVisible == true) goto L61;
        if (this.emojiPadding <= 0) goto L61;
        if (this.translateBottomPanelAfterResize == false) goto L61;
        this.translateBottomPanelAfterResize = false;
    L61:
        if (this.keyboardVisible == true) goto L63;
    L65:
        updatePlusEmojiKeyboardButton();
        return;
    L63:
        if (this.waitingForKeyboardOpen == false) goto L65;
        this.waitingForKeyboardOpen = false;
        AndroidUtilities.cancelRunOnUIThread(this.openKeyboardRunnable);
        goto L65
    L31:
        if (((TextPaintView) r62).getEditText().isFocused() == true) goto L33;
    L35:
        boolean r64 = false;
    L36:
        this.keyboardVisible = r64;
        goto L39
    L33:
        if (this.keyboardNotifier.keyboardVisible() == false) goto L35;
        r64 = true;
        goto L36
    L26:
        if (this.lastSizeChangeValue2 != r6) goto L28;
    }

    public abstract void onSwitchSegmentedAnimation(PhotoView r1);

    public void onTextAdd() {
    }

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
        setTextType((this.selectedTextType + 1) % 4);
    }

    @Override // org.telegram.ui.Stories.recorder.StoryRecorder.Touchable
    public boolean onTouch(MotionEvent r3) {
        if (this.isCoverPreview == false) goto L7;
        return false;
    L7:
        if (this.currentEntityView == null) goto L9;
        selectEntity(null);
    L9:
        float r0 = r3.getX();
        float r1 = r3.getY();
        MotionEvent r32 = MotionEvent.obtain(r3);
        r32.setLocation(r0, r1);
        this.renderView.onTouch(r32);
        r32.recycle();
        return true;
    }

    public abstract void onTryDeleteRound();

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

    public void openPaint() {
        switchTab(0);
        clearSelection();
    }

    public void openStickers() {
        switchTab(1);
        openStickersView();
    }

    public void openText() {
        switchTab(2);
        this.forceChanges = true;
        createText(true);
    }

    public void removeCurrentEntity() {
        EntityView r0 = this.currentEntityView;
        if (r0 == null) goto L6;
        removeEntity(r0);
        return;
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.ignoreLayout == false) goto L5;
        return;
    L5:
        super.requestLayout();
    }

    public boolean selectEntity(EntityView r2) {
        return selectEntity(r2, true);
    }

    public void setBlurManager(BlurringShader.BlurManager r1) {
        this.blurManager = r1;
    }

    public void setCoverPreview(boolean r2) {
        if (this.isCoverPreview == r2) goto L9;
        this.isCoverPreview = r2;
        if (r2 == false) goto L7;
        selectEntity(null);
    L7:
        setCoverPause(r2);
        return;
    }

    public void setCoverTime(long r7) {
        int r1 = 0;
    L4:
        if (r1 >= this.entitiesView.getChildCount()) goto L11;
        View r2 = this.entitiesView.getChildAt(r1);
        if ((r2 instanceof StickerView) == false) goto L10;
        ImageReceiver r22 = ((StickerView) r2).centerImage;
        RLottieDrawable r3 = r22.getLottieAnimation();
        r22.getAnimation();
        if (r3 == null) goto L10;
        r3.setCurrentFrame(Math.round(((r7 % r3.getDuration()) / r3.getDuration()) * r3.getFramesCount()), true, false);
    L10:
        r1 = r1 + 1;
        goto L4
    }

    public void setHasAudio(boolean r2) {
        if (r2 == this.hasAudio) goto L6;
        this.hasAudio = r2;
        checkEntitiesIsVideo();
        return;
    }

    @Override // org.telegram.ui.Components.IPhotoPaintView
    public /* bridge */ /* synthetic */ void setOffsetTranslationX(float r1) {
        super.setOffsetTranslationX(r1);
    }

    public void setOnCancelButtonClickedListener(Runnable r1) {
        this.onCancelButtonClickedListener = r1;
    }

    public void setOnDoneButtonClickedListener(Runnable r1) {
        this.onDoneButtonClickedListener = r1;
    }

    public void showReactionsLayout(boolean r6) {
        if (this.reactionLayoutShowing == r6) goto L24;
        if (r6 == false) goto L6;
    L8:
        this.reactionLayoutShowing = r6;
        if (r6 == false) goto L11;
        this.reactionLayout.reset();
        this.reactionLayout.setVisibility(0);
        this.reactionLayout.setSelectedReaction(this.reactionForEntity.getCurrentReaction());
        this.reactionLayout.getParent().bringChildToFront(this.reactionLayout);
    L12:
        float r1 = 0.0f;
        if (r6 == false) goto L20;
        this.invalidateReactionPosition = true;
        this.parent.invalidate();
        float r3 = this.reactionShowProgress;
        if (r6 == false) goto L17;
        r1 = 1.0f;
    L17:
        ValueAnimator r0 = ValueAnimator.ofFloat(new float[]{r3, r1});
        this.reactionLayout.setTransitionProgress(this.reactionShowProgress);
        r0.addUpdateListener(new PaintView$$ExternalSyntheticLambda3(this));
        r0.addListener(new AnonymousClass18(this, r6));
        r0.setDuration(200);
        r0.setInterpolator(CubicBezierInterpolator.EASE_OUT);
        r0.start();
        return;
    L20:
        if (this.reactionLayout.getReactionsWindow() == null) goto L22;
        this.reactionLayout.getReactionsWindow().dismissWithAlpha();
    L22:
        this.reactionLayout.animate().alpha(0.0f).setDuration(150).setListener(new AnonymousClass19(this)).start();
        return;
    L11:
        this.reactionForEntity = null;
        goto L12
    L6:
        if (this.reactionLayout != null) goto L8;
        return;
    }

    public void shutdown() {
        this.renderView.shutdown();
        this.entitiesView.setVisibility(8);
        this.selectionContainerView.setVisibility(8);
        this.queue.postRunnable(new PaintView$$ExternalSyntheticLambda15());
        EmojiBottomSheet r0 = this.emojiPopup;
        if (r0 == null) goto L5;
        r0.dismiss();
    L5:
        ColorPickerBottomSheet r2 = this.colorPickerBottomSheet;
        if (r2 == null) goto L9;
        r2.dismiss();
        return;
    }

    @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, org.telegram.ui.ActionBar.Theme.Colorable
    public void updateColors() {
        this.toolsPaint.setColor(-15132391);
    }

    public boolean wouldBeVideo() {
        if (this.isVideo == false) goto L5;
    L36:
        return true;
    L5:
        if (this.hasAudio == true) goto L36;
        int r2 = 0;
    L9:
        if (r2 >= this.entitiesView.getChildCount()) goto L35;
        View r3 = this.entitiesView.getChildAt(r2);
        if ((r3 instanceof TextPaintView) == false) goto L27;
        CharSequence r32 = ((TextPaintView) r3).getText();
        if ((r32 instanceof Spanned) == false) goto L34;
        AnimatedEmojiSpan[] r33 = (AnimatedEmojiSpan[]) ((Spanned) r32).getSpans(0, r32.length(), AnimatedEmojiSpan.class);
        int r4 = 0;
    L16:
        if (r4 >= r33.length) goto L34;
        AnimatedEmojiSpan r5 = r33[r4];
        TLRPC.Document r6 = r5.document;
        if (r6 != null) goto L20;
        r6 = AnimatedEmojiDrawable.findDocument(this.currentAccount, r5.getDocumentId());
    L20:
        if (r6 == null) goto L23;
        AnimatedEmojiDrawable.getDocumentFetcher(this.currentAccount).putDocument(r6);
    L23:
        if (StoryEntry.isAnimated(r6, FileLoader.getInstance(this.currentAccount).getPathToAttach(r6, true).getAbsolutePath()) == true) goto L24;
        r4 = r4 + 1;
        goto L16
    L24:
        return true;
    L34:
        r2 = r2 + 1;
        goto L9
    L27:
        if ((r3 instanceof StickerView) == false) goto L32;
        TLRPC.Document r34 = ((StickerView) r3).getSticker();
        if (StoryEntry.isAnimated(r34, FileLoader.getInstance(this.currentAccount).getPathToAttach(r34, true).getAbsolutePath()) == false) goto L34;
        return true;
    L32:
        if ((r3 instanceof RoundView) == false) goto L34;
        return true;
    L35:
        return false;
    }
}
