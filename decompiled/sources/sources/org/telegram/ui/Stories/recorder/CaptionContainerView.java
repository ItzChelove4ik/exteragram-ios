package org.telegram.ui.Stories.recorder;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.RoundedCorner;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import com.exteragram.messenger.utils.text.LocaleUtils;
import com.google.zxing.common.detector.MathUtils;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.Opcodes;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BotWebViewVibrationEffect;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.AdjustPanLayoutHelper;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.BlurringShader;
import org.telegram.ui.Components.ButtonBounce;
import org.telegram.ui.Components.CaptionPhotoViewer;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EditTextCaption;
import org.telegram.ui.Components.EditTextEmoji;
import org.telegram.ui.Components.EditTextSuggestionsFix;
import org.telegram.ui.Components.EmojiView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.MentionsContainerView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.Text;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.StrokeDrawable;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundColorProviderThemed;
import org.telegram.ui.Components.blur3.drawable.color.impl.BlurredBackgroundProviderImpl;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.Stories.DarkThemeResourceProvider;

/* loaded from: classes7.dex */
public abstract class CaptionContainerView extends FrameLayout {
    public ImageView applyButton;
    private Drawable applyButtonCheck;
    private CombinedDrawable applyButtonDrawable;
    protected final BlurringShader.StoryBlurDrawer backgroundBlur;
    private BlurredBackgroundDrawable backgroundForCaptionField;
    protected final Paint backgroundPaint;
    int beforeScrollY;
    private Bitmap blurBitmap;
    private Matrix blurBitmapMatrix;
    private BitmapShader blurBitmapShader;
    private final BlurringShader.BlurManager blurManager;
    private Paint blurPaint;
    private final ButtonBounce bounce;
    public final RectF bounds;
    protected final BlurringShader.StoryBlurDrawer captionBlur;
    private final RectF clickBounds;
    private final Path clipPath;
    private int codePointCount;
    private RadialGradient collapseGradient;
    private Matrix collapseGradientMatrix;
    private RadialGradient collapseOutGradient;
    private Paint collapseOutPaint;
    private Paint collapsePaint;
    public boolean collapsed;
    public int collapsedFromX;
    public final AnimatedFloat collapsedT;
    private final FrameLayout containerView;
    protected int currentAccount;
    private long dialogId;
    public final EditTextEmoji editText;
    protected BlurredBackgroundDrawableViewFactory factoryForMentions;
    private final LinearGradient fadeGradient;
    private final Paint fadePaint;
    private Utilities.CallbackVoidReturn<Bitmap> getUiBlurBitmap;
    int goingToScrollY;
    private boolean hasReply;
    private final AnimatedFloat heightAnimated;
    private Bitmap hintTextBitmap;
    private final Paint hintTextBitmapPaint;
    private final TextPaint hintTextPaint;
    private boolean ignoreDraw;
    private boolean ignoreTextChange;
    public boolean ignoreTouches;
    private ValueAnimator keyboardAnimator;
    public final KeyboardNotifier keyboardNotifier;
    public boolean keyboardShown;
    public float keyboardT;
    private int lastHeight;
    private float lastHeightTranslation;
    public FrameLayout limitTextContainer;
    public AnimatedTextView limitTextView;
    private final Matrix matrix;
    private BlurringShader.StoryBlurDrawer mentionBackgroundBlur;
    public MentionsContainerView mentionContainer;
    private MentionsContainerView.Delegate mentionsDelegate;
    private Utilities.Callback<Integer> onHeightUpdate;
    private Utilities.Callback<Boolean> onKeyboardOpen;
    ObjectAnimator parentKeyboardAnimator;
    private final RectF rectF;
    protected final BlurringShader.StoryBlurDrawer replyBackgroundBlur;
    private Path replyClipPath;
    private Paint replyLinePaint;
    private Path replyLinePath;
    private float[] replyLinePathRadii;
    private Text replyText;
    protected final BlurringShader.StoryBlurDrawer replyTextBlur;
    private Text replyTitle;
    protected Theme.ResourcesProvider resourcesProvider;
    private final FrameLayout rootView;
    private ObjectAnimator scrollAnimator;
    private int shiftDp;
    private final SizeNotifierFrameLayout sizeNotifierFrameLayout;
    protected final StrokeDrawable strokeDrawable;
    protected final StrokeDrawable strokeDrawableEmoji;
    private final Runnable textChangeRunnable;
    public boolean toKeyboardShow;
    private Runnable updateShowKeyboard;
    boolean waitingForScrollYChange;

    /* renamed from: org.telegram.ui.Stories.recorder.CaptionContainerView$1, reason: invalid class name */
    public class AnonymousClass1 extends BlurredBackgroundColorProviderThemed {
        final /* synthetic */ CaptionContainerView this$0;

        public AnonymousClass1(CaptionContainerView r1, Theme.ResourcesProvider r2, int r3, float r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundColorProviderThemed
        public boolean isDark() {
            return true;
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.CaptionContainerView$2, reason: invalid class name */
    public class AnonymousClass2 extends BlurredBackgroundColorProviderThemed {
        final /* synthetic */ CaptionContainerView this$0;

        public AnonymousClass2(CaptionContainerView r1, Theme.ResourcesProvider r2, int r3, float r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundColorProviderThemed
        public boolean isDark() {
            return true;
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.CaptionContainerView$3, reason: invalid class name */
    public class AnonymousClass3 extends EditTextEmoji {
        private BlurringShader.StoryBlurDrawer blurDrawer;
        private BlurredBackgroundDrawable blurredBackgroundDrawable;
        final /* synthetic */ CaptionContainerView this$0;
        final /* synthetic */ BlurringShader.BlurManager val$blurManager;
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;

        /* renamed from: org.telegram.ui.Stories.recorder.CaptionContainerView$3$1, reason: invalid class name */
        public class AnonymousClass1 extends ViewOutlineProvider {
            final /* synthetic */ AnonymousClass3 this$1;

            public AnonymousClass1(AnonymousClass3 r1) {
                this.this$1 = r1;
            }

            @Override // android.view.ViewOutlineProvider
            public void getOutline(View r7, Outline r8) {
                r8.setRoundRect(0, 0, r7.getWidth(), r7.getHeight() + AndroidUtilities.dp(29.0f), AndroidUtilities.dp(29.0f));
            }
        }

        /* renamed from: org.telegram.ui.Stories.recorder.CaptionContainerView$3$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass3 this$1;

            public AnonymousClass2(AnonymousClass3 r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                if (CaptionContainerView.m19887$$Nest$fgetscrollAnimator(this.this$1.this$0) == r2) goto L5;
                return;
            L5:
                CaptionContainerView.m19892$$Nest$fputscrollAnimator(this.this$1.this$0, null);
                this.this$1.this$0.editText.getEditText().setScrollY(this.this$1.this$0.goingToScrollY);
            }
        }

        public AnonymousClass3(CaptionContainerView r1, Context r2, SizeNotifierFrameLayout r3, BaseFragment r4, int r5, boolean r6, Theme.ResourcesProvider r7, Theme.ResourcesProvider r8, BlurringShader.BlurManager r9) {
            this.this$0 = r1;
            this.val$resourcesProvider = r8;
            this.val$blurManager = r9;
            super(r2, r3, r4, r5, r6, r7);
        }

        @Override // org.telegram.ui.Components.EditTextEmoji
        public boolean allowSearch() {
            return true;
        }

        @Override // org.telegram.ui.Components.EditTextEmoji
        public void createEmojiView() {
            super.createEmojiView();
            EmojiView r0 = getEmojiView();
            if (r0 != null) goto L5;
        L12:
            if (r0 == null) goto L15;
            r0.customOutline = true;
            r0.setClipToOutline(true);
            r0.setOutlineProvider(new AnonymousClass1(this));
            return;
        L15:
            return;
        L5:
            if (this.this$0.getEditTextStyle() != 2) goto L7;
        L8:
            r0.shouldLightenBackground = false;
            r0.fixBottomTabContainerTranslation = false;
            r0.setShouldDrawBackground(false);
            if ((this.this$0 instanceof CaptionPhotoViewer) == false) goto L11;
            r0.setPadding(0, 0, 0, AndroidUtilities.navigationBarHeight);
            r0.emojiCacheType = 3;
        L11:
            r0.updateColors();
            goto L12
        L7:
            if (this.this$0.getEditTextStyle() != 3) goto L12;
            goto L8
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r3) {
            CaptionContainerView r0 = this.this$0;
            if ((r0 instanceof CaptionStory) == false) goto L9;
            if (((CaptionStory) r0).isRecording() == false) goto L9;
            return false;
        L9:
            return super.dispatchTouchEvent(r3);
        }

        @Override // org.telegram.ui.Components.EditTextEmoji
        public void drawEmojiBackground(Canvas r19, View r20) {
            CaptionContainerView.m19886$$Nest$fgetrectF(this.this$0).set(0.0f, 0.0f, r20.getWidth(), r20.getHeight() + AndroidUtilities.dp(29.0f));
            CaptionContainerView r2 = this.this$0;
            if (r2.factoryForMentions == null) goto L22;
            if (this.blurredBackgroundDrawable != null) goto L19;
            int r4 = 0;
            if (Build.VERSION.SDK_INT < 31) goto L17;
            WindowInsets r22 = getRootWindowInsets();
            if (r22 == null) goto L17;
            RoundedCorner r3 = r22.getRoundedCorner(3);
            RoundedCorner r23 = r22.getRoundedCorner(2);
            if (r3 != null) goto L13;
            int r32 = 0;
        L14:
            if (r23 == null) goto L18;
            r4 = r23.getRadius();
        L18:
            BlurredBackgroundDrawable r12 = this.this$0.factoryForMentions.create(r20).setColorProvider(BlurredBackgroundProviderImpl.photoViewer(this.val$resourcesProvider));
            this.blurredBackgroundDrawable = r12;
            r12.setRadius(AndroidUtilities.dp(29.0f), AndroidUtilities.dp(29.0f), r4, r32, true);
            this.blurredBackgroundDrawable.enableInAppKeyboardOptimization();
            this.blurredBackgroundDrawable.setThickness(AndroidUtilities.dp(32.0f));
            this.blurredBackgroundDrawable.setIntensity(0.4f);
            goto L19
        L13:
            r32 = r3.getRadius();
        L17:
            r32 = 0;
        L19:
            RectF r24 = CaptionContainerView.m19886$$Nest$fgetrectF(this.this$0);
            Rect r33 = AndroidUtilities.rectTmp2;
            r24.round(r33);
            this.blurredBackgroundDrawable.setBounds(r33);
            this.blurredBackgroundDrawable.draw(r19);
            return;
        L22:
            if (r2.customBlur() == true) goto L24;
            CaptionContainerView r0 = this.this$0;
            CaptionContainerView.m19895$$Nest$mdrawBackground(r0, r19, CaptionContainerView.m19886$$Nest$fgetrectF(r0), 0.0f, 0.95f, r20);
            return;
        L24:
            if (this.blurDrawer != null) goto L26;
            this.blurDrawer = new BlurringShader.StoryBlurDrawer(this.val$blurManager, r20, 7);
        L26:
            CaptionContainerView r1 = this.this$0;
            r1.drawBlur(this.blurDrawer, r19, CaptionContainerView.m19886$$Nest$fgetrectF(r1), AndroidUtilities.dp(29.0f), false, 0.0f, -r20.getY(), false, 1.0f);
            this.this$0.strokeDrawableEmoji.radius = AndroidUtilities.dp(29.0f);
            CaptionContainerView r25 = this.this$0;
            r25.strokeDrawableEmoji.setBounds((int) CaptionContainerView.m19886$$Nest$fgetrectF(r25).left, (int) CaptionContainerView.m19886$$Nest$fgetrectF(this.this$0).top, (int) CaptionContainerView.m19886$$Nest$fgetrectF(this.this$0).right, ((int) CaptionContainerView.m19886$$Nest$fgetrectF(this.this$0).bottom) + AndroidUtilities.dp(29.0f));
            this.this$0.strokeDrawableEmoji.draw(r19);
        }

        @Override // org.telegram.ui.Components.EditTextEmoji
        public void onEmojiKeyboardUpdate() {
            this.this$0.keyboardNotifier.fire();
        }

        @Override // org.telegram.ui.Components.EditTextEmoji
        public void onLineCountChanged(int r1, int r2) {
            this.this$0.onLineCountChanged(r1, r2);
        }

        @Override // org.telegram.ui.Components.EditTextEmoji
        public boolean onScrollYChange(int r6) {
            if (CaptionContainerView.m19887$$Nest$fgetscrollAnimator(this.this$0) != null) goto L5;
        L9:
            this.this$0.invalidate();
            CaptionContainerView r0 = this.this$0;
            if (r0.waitingForScrollYChange == false) goto L24;
            r0.waitingForScrollYChange = false;
            if (r0.beforeScrollY != r6) goto L14;
            return true;
        L14:
            if (CaptionContainerView.m19887$$Nest$fgetscrollAnimator(r0) == null) goto L20;
            if (CaptionContainerView.m19887$$Nest$fgetscrollAnimator(this.this$0).isRunning() == false) goto L20;
            if (r6 != this.this$0.goingToScrollY) goto L20;
            return true;
        L20:
            if (CaptionContainerView.m19887$$Nest$fgetscrollAnimator(this.this$0) == null) goto L22;
            CaptionContainerView.m19887$$Nest$fgetscrollAnimator(this.this$0).cancel();
        L22:
            this.this$0.editText.getEditText().setScrollY(this.this$0.beforeScrollY);
            CaptionContainerView r02 = this.this$0;
            EditTextCaption r2 = r02.editText.getEditText();
            CaptionContainerView r3 = this.this$0;
            int r4 = r3.beforeScrollY;
            r3.goingToScrollY = r6;
            CaptionContainerView.m19892$$Nest$fputscrollAnimator(r02, ObjectAnimator.ofInt(r2, "scrollY", new int[]{r4, r6}));
            CaptionContainerView.m19887$$Nest$fgetscrollAnimator(this.this$0).setDuration(240);
            CaptionContainerView.m19887$$Nest$fgetscrollAnimator(this.this$0).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            CaptionContainerView.m19887$$Nest$fgetscrollAnimator(this.this$0).addListener(new AnonymousClass2(this));
            CaptionContainerView.m19887$$Nest$fgetscrollAnimator(this.this$0).start();
            return false;
        L24:
            return true;
        L5:
            if (CaptionContainerView.m19887$$Nest$fgetscrollAnimator(this.this$0).isRunning() == false) goto L9;
            if (r6 != this.this$0.goingToScrollY) goto L9;
            return false;
        }

        @Override // org.telegram.ui.Components.EditTextEmoji
        public void onWaitingForKeyboard() {
            this.this$0.keyboardNotifier.awaitKeyboard();
        }

        @Override // org.telegram.ui.Components.EditTextEmoji
        public void updatedEmojiExpanded() {
            this.this$0.keyboardNotifier.fire();
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.CaptionContainerView$4, reason: invalid class name */
    public class AnonymousClass4 implements TextWatcher {
        private int lastLength;
        private boolean lastOverLimit;
        final /* synthetic */ CaptionContainerView this$0;

        public static /* synthetic */ void $r8$lambda$v4EDOZQNHqrGDi_b25fTdxfugwg(AnonymousClass4 r0) {
            r0.lambda$afterTextChanged$0();
        }

        public AnonymousClass4(CaptionContainerView r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$afterTextChanged$0() {
            this.this$0.waitingForScrollYChange = false;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r5) {
            CaptionContainerView.m19890$$Nest$fputcodePointCount(this.this$0, Character.codePointCount(r5, 0, r5.length()));
            int r52 = this.this$0.getCaptionLimit();
            if ((CaptionContainerView.m19882$$Nest$fgetcodePointCount(this.this$0) + 25) <= r52) goto L5;
            String r0 = _UrlKt.FRAGMENT_ENCODE_SET + (r52 - CaptionContainerView.m19882$$Nest$fgetcodePointCount(this.this$0));
        L6:
            this.this$0.limitTextView.cancelAnimation();
            this.this$0.limitTextView.setText(r0);
            CaptionContainerView r02 = this.this$0;
            AnimatedTextView r1 = r02.limitTextView;
            if (CaptionContainerView.m19882$$Nest$fgetcodePointCount(r02) < r52) goto L9;
            int r03 = -1280137;
        L10:
            r1.setTextColor(r03);
            if (CaptionContainerView.m19882$$Nest$fgetcodePointCount(this.this$0) > r52) goto L13;
        L23:
            this.lastLength = CaptionContainerView.m19882$$Nest$fgetcodePointCount(this.this$0);
            if (CaptionContainerView.m19882$$Nest$fgetcodePointCount(this.this$0) <= r52) goto L26;
            boolean r53 = true;
        L28:
            if (r53 == this.lastOverLimit) goto L30;
            this.this$0.onCaptionLimitUpdate(r53);
        L30:
            this.lastOverLimit = r53;
            if (CaptionContainerView.m19884$$Nest$fgetignoreTextChange(this.this$0) == true) goto L33;
            AndroidUtilities.cancelRunOnUIThread(CaptionContainerView.m19889$$Nest$fgettextChangeRunnable(this.this$0));
            AndroidUtilities.runOnUIThread(CaptionContainerView.m19889$$Nest$fgettextChangeRunnable(this.this$0), 1500);
        L33:
            CaptionContainerView.m19891$$Nest$fputignoreTextChange(this.this$0, false);
            AndroidUtilities.runOnUIThread(new CaptionContainerView$4$$ExternalSyntheticLambda0(this));
            return;
        L26:
            r53 = false;
            goto L28
        L13:
            if (UserConfig.getInstance(this.this$0.currentAccount).isPremium() == true) goto L23;
            if (CaptionContainerView.m19882$$Nest$fgetcodePointCount(this.this$0) >= this.this$0.getCaptionPremiumLimit()) goto L23;
            if (CaptionContainerView.m19882$$Nest$fgetcodePointCount(this.this$0) <= this.lastLength) goto L23;
            if (this.this$0.captionLimitToast() == false) goto L21;
        L22:
            CaptionContainerView r04 = this.this$0;
            AnimatedTextView r12 = r04.limitTextView;
            int r3 = -CaptionContainerView.m19888$$Nest$fgetshiftDp(r04);
            CaptionContainerView.m19893$$Nest$fputshiftDp(r04, r3);
            AndroidUtilities.shakeViewSpring(r12, r3);
            BotWebViewVibrationEffect.APP_ERROR.vibrate();
            goto L23
        L21:
            if (MessagesController.getInstance(this.this$0.currentAccount).premiumFeaturesBlocked() == false) goto L23;
        L9:
            r03 = -1;
            goto L10
        L5:
            r0 = null;
            goto L6
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
            if (CaptionContainerView.m19887$$Nest$fgetscrollAnimator(this.this$0) != null) goto L5;
        L8:
            CaptionContainerView r12 = this.this$0;
            r12.beforeScrollY = r12.editText.getEditText().getScrollY();
            this.this$0.waitingForScrollYChange = true;
            return;
        L5:
            if (CaptionContainerView.m19887$$Nest$fgetscrollAnimator(this.this$0).isRunning() == false) goto L8;
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r7, int r8, int r9, int r10) {
            if (this.this$0.editText.getEditText().suppressOnTextChanged == true) goto L13;
            CaptionContainerView r82 = this.this$0;
            if (r82.mentionContainer != null) goto L9;
            CaptionContainerView.m19894$$Nest$mcreateMentionsContainer(r82);
        L9:
            if (this.this$0.mentionContainer.getAdapter() == null) goto L12;
            this.this$0.mentionContainer.getAdapter().setUserOrChat(MessagesController.getInstance(this.this$0.currentAccount).getUser(Long.valueOf(CaptionContainerView.m19883$$Nest$fgetdialogId(this.this$0))), MessagesController.getInstance(this.this$0.currentAccount).getChat(Long.valueOf(-CaptionContainerView.m19883$$Nest$fgetdialogId(this.this$0))));
            this.this$0.mentionContainer.getAdapter().searchUsernameOrHashtag(r7, this.this$0.editText.getEditText().getSelectionStart(), null, false, false);
            return;
        L12:
            return;
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.CaptionContainerView$5, reason: invalid class name */
    public class AnonymousClass5 extends MentionsContainerView {
        final /* synthetic */ CaptionContainerView this$0;

        public AnonymousClass5(CaptionContainerView r1, Context r2, long r3, long r5, BaseFragment r7, Theme.ResourcesProvider r8) {
            this.this$0 = r1;
            super(r2, r3, r5, r7, r8);
        }

        @Override // org.telegram.ui.Components.MentionsContainerView
        public void drawRoundRect(Canvas r11, Rect r12, float r13) {
            CaptionContainerView.m19886$$Nest$fgetrectF(this.this$0).set(r12);
            boolean r122 = this.this$0.customBlur();
            CaptionContainerView r0 = this.this$0;
            if (r122 == false) goto L6;
            r0.drawBlur(CaptionContainerView.m19885$$Nest$fgetmentionBackgroundBlur(r0), r11, CaptionContainerView.m19886$$Nest$fgetrectF(this.this$0), r13, false, -this.this$0.mentionContainer.getX(), -this.this$0.mentionContainer.getY(), false, 1.0f);
            return;
        L6:
            Paint r112 = CaptionContainerView.m19885$$Nest$fgetmentionBackgroundBlur(r0).getPaint(1.0f);
            CaptionContainerView r123 = this.this$0;
            if (r112 != null) goto L10;
            r123.backgroundPaint.setAlpha(128);
            r11.drawRoundRect(CaptionContainerView.m19886$$Nest$fgetrectF(this.this$0), r13, r13, this.this$0.backgroundPaint);
            return;
        L10:
            r11.drawRoundRect(CaptionContainerView.m19886$$Nest$fgetrectF(r123), r13, r13, r112);
            this.this$0.backgroundPaint.setAlpha(80);
            r11.drawRoundRect(CaptionContainerView.m19886$$Nest$fgetrectF(this.this$0), r13, r13, this.this$0.backgroundPaint);
        }

        @Override // org.telegram.ui.Components.MentionsContainerView
        public boolean isStories() {
            return true;
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.CaptionContainerView$6, reason: invalid class name */
    public class AnonymousClass6 implements MentionsContainerView.Delegate {
        final /* synthetic */ CaptionContainerView this$0;

        public AnonymousClass6(CaptionContainerView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.MentionsContainerView.Delegate
        public Paint.FontMetricsInt getFontMetrics() {
            return this.this$0.editText.getEditText().getPaint().getFontMetricsInt();
        }

        @Override // org.telegram.ui.Components.MentionsContainerView.Delegate
        public void replaceText(int r1, int r2, CharSequence r3, boolean r4) {
            CaptionContainerView.m19896$$Nest$mreplaceWithText(this.this$0, r1, r2, r3, r4);
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.CaptionContainerView$7, reason: invalid class name */
    public class AnonymousClass7 extends AnimatorListenerAdapter {
        final /* synthetic */ CaptionContainerView this$0;
        final /* synthetic */ boolean val$show;

        public AnonymousClass7(CaptionContainerView r1, boolean r2) {
            this.this$0 = r1;
            this.val$show = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (this.val$show == true) goto L8;
            this.this$0.applyButton.setVisibility(8);
            MentionsContainerView r22 = this.this$0.mentionContainer;
            if (r22 == null) goto L8;
            r22.setVisibility(8);
        L8:
            if (this.val$show == false) goto L10;
            this.this$0.editText.getEditText().setAllowDrawCursor(true);
        L10:
            this.this$0.afterUpdateShownKeyboard(this.val$show);
        }
    }

    public static class BounceableImageView extends ImageView {
        private final ButtonBounce bounce;
        private final float scale;

        public BounceableImageView(Context r2) {
            this(r2, 0.2f);
        }

        public BounceableImageView(Context r1, float r2) {
            super(r1);
            this.bounce = new ButtonBounce(this);
            this.scale = r2;
        }

        @Override // android.view.View
        public void draw(Canvas r5) {
            r5.save();
            float r0 = this.bounce.getScale(this.scale);
            r5.scale(r0, r0, getWidth() / 2.0f, getHeight() / 2.0f);
            super.draw(r5);
            r5.restore();
        }

        @Override // android.view.View
        public void setPressed(boolean r1) {
            super.setPressed(r1);
            this.bounce.setPressed(r1);
        }
    }

    public static class PeriodDrawable extends Drawable {
        private final Path activePath;
        public final AnimatedTextView.AnimatedTextDrawable activeTextDrawable;
        private boolean clear;
        private float cx;
        private float cy;
        private final int dashes;
        public float diameterDp;
        private final Paint fillPaint;
        private final AnimatedFloat fillT;
        private boolean filled;
        public int strokeColor;
        public final Paint strokePaint;
        public final AnimatedTextView.AnimatedTextDrawable textDrawable;
        public float textOffsetX;
        public float textOffsetY;

        /* renamed from: org.telegram.ui.Stories.recorder.CaptionContainerView$PeriodDrawable$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatedTextView.AnimatedTextDrawable {
            final /* synthetic */ PeriodDrawable this$0;

            public AnonymousClass1(PeriodDrawable r1, boolean r2, boolean r3, boolean r4) {
                this.this$0 = r1;
                super(r2, r3, r4);
            }

            @Override // android.graphics.drawable.Drawable
            public void invalidateSelf() {
                this.this$0.invalidateSelf();
            }
        }

        /* renamed from: org.telegram.ui.Stories.recorder.CaptionContainerView$PeriodDrawable$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatedTextView.AnimatedTextDrawable {
            final /* synthetic */ PeriodDrawable this$0;

            public AnonymousClass2(PeriodDrawable r1, boolean r2, boolean r3, boolean r4) {
                this.this$0 = r1;
                super(r2, r3, r4);
            }

            @Override // android.graphics.drawable.Drawable
            public void invalidateSelf() {
                this.this$0.invalidateSelf();
            }
        }

        public PeriodDrawable() {
            this(5);
        }

        public PeriodDrawable(int r20) {
            Paint r1 = new Paint(1);
            this.strokePaint = r1;
            this.fillPaint = new Paint(1);
            AnonymousClass1 r4 = new AnonymousClass1(this, true, false, false);
            this.textDrawable = r4;
            AnonymousClass2 r11 = new AnonymousClass2(this, true, false, false);
            this.activeTextDrawable = r11;
            this.filled = false;
            CaptionContainerView$PeriodDrawable$$ExternalSyntheticLambda0 r13 = new CaptionContainerView$PeriodDrawable$$ExternalSyntheticLambda0(this);
            CubicBezierInterpolator r18 = CubicBezierInterpolator.EASE_OUT_QUINT;
            this.fillT = new AnimatedFloat(r13, 0, 350, r18);
            this.activePath = new Path();
            this.diameterDp = 21.0f;
            this.dashes = r20;
            r1.setStyle(Paint.Style.STROKE);
            r1.setStrokeWidth(AndroidUtilities.dpf2(1.66f));
            r1.setStrokeCap(Paint.Cap.ROUND);
            r4.setAnimationProperties(0.3f, 0, 250, r18);
            r4.setTypeface(AndroidUtilities.getTypeface("fonts/num.otf"));
            r4.setTextSize(AndroidUtilities.dpf2(12.0f));
            r4.setGravity(17);
            r11.setAnimationProperties(0.3f, 0, 250, r18);
            r11.setTypeface(AndroidUtilities.getTypeface("fonts/num.otf"));
            r11.setTextSize(AndroidUtilities.dpf2(12.0f));
            r11.setGravity(17);
            updateColors(-1, -15033089, -1);
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r2) {
            draw(r2, 1.0f);
        }

        public void draw(Canvas r17, float r18) {
            float r2 = AndroidUtilities.dpf2(this.diameterDp) / 2.0f;
            float r7 = this.fillT.set(this.filled);
            if (r7 <= 0.0f) goto L5;
            this.fillPaint.setAlpha((int) ((r18 * 255.0f) * r7));
            r17.drawCircle(this.cx, this.cy, AndroidUtilities.dpf2(11.33f) * r7, this.fillPaint);
        L5:
            this.strokePaint.setAlpha((int) ((Color.alpha(this.strokeColor) * r18) * (1.0f - r7)));
            RectF r22 = AndroidUtilities.rectTmp;
            float r4 = this.cx;
            float r6 = this.cy;
            r22.set(r4 - r2, r6 - r2, r4 + r2, r6 + r2);
            r17.drawArc(r22, 90.0f, 180.0f, false, this.strokePaint);
            int r1 = this.dashes;
            float r23 = (r1 + 1) * 1.5f;
            float r3 = (1.0f / ((r1 * 1.0f) + r23)) * 180.0f;
            float r13 = (1.5f / ((r1 * 1.0f) + r23)) * 180.0f;
            int r14 = 0;
            float r15 = r13;
        L7:
            if (r14 >= this.dashes) goto L9;
            float r42 = r3;
            r17.drawArc(AndroidUtilities.rectTmp, 270.0f + r15, r42, false, this.strokePaint);
            r15 = r15 + (r42 + r13);
            r14 = r14 + 1;
            r3 = r42;
            goto L7
        L9:
            r17.save();
            r17.translate(this.textOffsetX + 0.0f, this.textOffsetY);
            Rect r24 = AndroidUtilities.rectTmp2;
            r24.set((int) (this.cx - AndroidUtilities.dp(20.0f)), (int) (this.cy - AndroidUtilities.dp(20.0f)), (int) (this.cx + AndroidUtilities.dp(20.0f)), (int) (this.cy + AndroidUtilities.dp(20.0f)));
            this.textDrawable.setBounds(r24);
            this.textDrawable.setAlpha((int) (Color.alpha(this.strokeColor) * r18));
            this.textDrawable.draw(r17);
            if (r7 <= 0.0f) goto L12;
            this.activePath.rewind();
            this.activePath.addCircle(this.cx, this.cy + AndroidUtilities.dp(1.0f), AndroidUtilities.dpf2(11.33f) * r7, Path.Direction.CW);
            r17.clipPath(this.activePath);
            this.activeTextDrawable.setBounds(r24);
            this.activeTextDrawable.setAlpha((int) (r18 * 255.0f));
            this.activeTextDrawable.draw(r17);
        L12:
            r17.restore();
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return AndroidUtilities.dp(24.0f);
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return AndroidUtilities.dp(24.0f);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
        }

        @Override // android.graphics.drawable.Drawable
        public void setBounds(int r1, int r2, int r3, int r4) {
            super.setBounds(r1, r2, r3, r4);
            this.cx = getBounds().centerX();
            this.cy = getBounds().centerY();
        }

        @Override // android.graphics.drawable.Drawable
        public void setBounds(Rect r1) {
            super.setBounds(r1);
            this.cx = getBounds().centerX();
            this.cy = getBounds().centerY();
        }

        public void setCenterXY(float r1, float r2) {
            this.cx = r1;
            this.cy = r2;
        }

        public void setClear(boolean r5) {
            if (this.clear == r5) goto L13;
            this.clear = r5;
            Paint r0 = this.strokePaint;
            PorterDuffXfermode r1 = null;
            if (r5 == false) goto L7;
            PorterDuffXfermode r2 = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
        L8:
            r0.setXfermode(r2);
            TextPaint r4 = this.textDrawable.getPaint();
            if (r5 == false) goto L11;
            r1 = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
        L11:
            r4.setXfermode(r1);
            return;
        L7:
            r2 = null;
            goto L8
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }

        public void setTextSize(float r3) {
            this.activeTextDrawable.setTextSize(AndroidUtilities.dpf2(r3));
            this.textDrawable.setTextSize(AndroidUtilities.dpf2(r3));
        }

        public void setValue(int r4, boolean r5, boolean r6) {
            this.textDrawable.setText(_UrlKt.FRAGMENT_ENCODE_SET + r4, r6);
            this.activeTextDrawable.setText(_UrlKt.FRAGMENT_ENCODE_SET + r4, r6);
            this.filled = r5;
            if (r6 == true) goto L5;
            this.fillT.set(r5, true);
        L5:
            invalidateSelf();
        }

        public void updateColors(int r2, int r3, int r4) {
            this.strokeColor = r2;
            this.strokePaint.setColor(r2);
            this.textDrawable.setTextColor(r2);
            this.activeTextDrawable.setTextColor(r4);
            this.fillPaint.setColor(r3);
        }
    }

    public static /* synthetic */ void $r8$lambda$5Ek0ZrXu82JhY7ZM7XljhTPgKVI(CaptionContainerView r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$HF99bf103y5v7zGHpKUFlPhr66E(CaptionContainerView r0, ValueAnimator r1) {
        r0.lambda$updateShowKeyboard$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$_eRla3s4njWd8iQoHhyYq38ZP1g(CaptionContainerView r0) {
        r0.lambda$new$2();
    }

    public static /* synthetic */ void $r8$lambda$a2otEEGyeLoKAWNTfRmj1OwrF2U(CaptionContainerView r0, Canvas r1, Runnable r2) {
        r0.drawHint(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$xfwbJ4DkpwO_eJIOqrWfa4fuALo(CaptionContainerView r0) {
        r0.lambda$new$1();
    }

    /* renamed from: -$$Nest$fgetcodePointCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m19882$$Nest$fgetcodePointCount(CaptionContainerView r0) {
        return r0.codePointCount;
    }

    /* renamed from: -$$Nest$fgetdialogId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m19883$$Nest$fgetdialogId(CaptionContainerView r2) {
        return r2.dialogId;
    }

    /* renamed from: -$$Nest$fgetignoreTextChange, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19884$$Nest$fgetignoreTextChange(CaptionContainerView r0) {
        return r0.ignoreTextChange;
    }

    /* renamed from: -$$Nest$fgetmentionBackgroundBlur, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlurringShader.StoryBlurDrawer m19885$$Nest$fgetmentionBackgroundBlur(CaptionContainerView r0) {
        return r0.mentionBackgroundBlur;
    }

    /* renamed from: -$$Nest$fgetrectF, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RectF m19886$$Nest$fgetrectF(CaptionContainerView r0) {
        return r0.rectF;
    }

    /* renamed from: -$$Nest$fgetscrollAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ObjectAnimator m19887$$Nest$fgetscrollAnimator(CaptionContainerView r0) {
        return r0.scrollAnimator;
    }

    /* renamed from: -$$Nest$fgetshiftDp, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m19888$$Nest$fgetshiftDp(CaptionContainerView r0) {
        return r0.shiftDp;
    }

    /* renamed from: -$$Nest$fgettextChangeRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m19889$$Nest$fgettextChangeRunnable(CaptionContainerView r0) {
        return r0.textChangeRunnable;
    }

    /* renamed from: -$$Nest$fputcodePointCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19890$$Nest$fputcodePointCount(CaptionContainerView r0, int r1) {
        r0.codePointCount = r1;
    }

    /* renamed from: -$$Nest$fputignoreTextChange, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19891$$Nest$fputignoreTextChange(CaptionContainerView r0, boolean r1) {
        r0.ignoreTextChange = r1;
    }

    /* renamed from: -$$Nest$fputscrollAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19892$$Nest$fputscrollAnimator(CaptionContainerView r0, ObjectAnimator r1) {
        r0.scrollAnimator = r1;
    }

    /* renamed from: -$$Nest$fputshiftDp, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19893$$Nest$fputshiftDp(CaptionContainerView r0, int r1) {
        r0.shiftDp = r1;
    }

    /* renamed from: -$$Nest$mcreateMentionsContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19894$$Nest$mcreateMentionsContainer(CaptionContainerView r0) {
        r0.createMentionsContainer();
    }

    /* renamed from: -$$Nest$mdrawBackground, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19895$$Nest$mdrawBackground(CaptionContainerView r0, Canvas r1, RectF r2, float r3, float r4, View r5) {
        r0.drawBackground(r1, r2, r3, r4, r5);
    }

    /* renamed from: -$$Nest$mreplaceWithText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19896$$Nest$mreplaceWithText(CaptionContainerView r0, int r1, int r2, CharSequence r3, boolean r4) {
        r0.replaceWithText(r1, r2, r3, r4);
    }

    public CaptionContainerView(Context r32, FrameLayout r33, SizeNotifierFrameLayout r34, FrameLayout r35, Theme.ResourcesProvider r36, BlurringShader.BlurManager r37) {
        super(r32);
        StrokeDrawable r10 = new StrokeDrawable();
        this.strokeDrawable = r10;
        StrokeDrawable r11 = new StrokeDrawable();
        this.strokeDrawableEmoji = r11;
        Paint r12 = new Paint(1);
        this.backgroundPaint = r12;
        Paint r14 = new Paint(1);
        this.fadePaint = r14;
        LinearGradient r15 = new LinearGradient(0.0f, 0.0f, 0.0f, AndroidUtilities.dp(10.0f), new int[]{Opcodes.V_PREVIEW, 0}, new float[]{0.05f, 1.0f}, Shader.TileMode.CLAMP);
        this.fadeGradient = r15;
        this.matrix = new Matrix();
        this.hintTextPaint = new TextPaint(3);
        Paint r0 = new Paint(3);
        this.hintTextBitmapPaint = r0;
        this.shiftDp = -4;
        this.currentAccount = UserConfig.selectedAccount;
        this.textChangeRunnable = new CaptionContainerView$$ExternalSyntheticLambda0(this);
        this.bounce = new ButtonBounce(this, 1.0f, 3.0f);
        this.updateShowKeyboard = new CaptionContainerView$$ExternalSyntheticLambda1(this);
        CubicBezierInterpolator r6 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.heightAnimated = new AnimatedFloat(this, 0, 300, r6);
        this.ignoreDraw = false;
        this.rectF = new RectF();
        this.bounds = new RectF();
        this.clickBounds = new RectF();
        this.clipPath = new Path();
        this.collapsedT = new AnimatedFloat(this, 500, r6);
        this.resourcesProvider = r36;
        this.rootView = r33;
        this.sizeNotifierFrameLayout = r34;
        this.containerView = r35;
        this.blurManager = r37;
        this.backgroundBlur = new BlurringShader.StoryBlurDrawer(r37, this, 0, !customBlur());
        this.replyBackgroundBlur = new BlurringShader.StoryBlurDrawer(r37, this, 8);
        this.replyTextBlur = new BlurringShader.StoryBlurDrawer(r37, this, 9);
        r10.nonRound = true;
        int r62 = Theme.key_windowBackgroundWhite;
        r10.setColorProvider(new AnonymousClass1(this, r36, r62, 0.0f));
        r10.setBackgroundColor(0);
        r11.nonRound = true;
        r11.setColorProvider(new AnonymousClass2(this, r36, r62, 0.0f));
        r11.setBackgroundColor(0);
        r12.setColor(Integer.MIN_VALUE);
        this.keyboardNotifier = new KeyboardNotifier(r33, new CaptionContainerView$$ExternalSyntheticLambda2(this));
        AnonymousClass3 r02 = new AnonymousClass3(this, r32, r34, null, getEditTextStyle(), true, new DarkThemeResourceProvider(), r36, r37);
        this.editText = r02;
        r02.glassDesignForEmojiView = true;
        r02.getEditText().addTextChangedListener(new EditTextSuggestionsFix());
        r02.setFocusable(true);
        if (LocaleUtils.canUseLocalPremiumEmojis(this.currentAccount) == true) goto L5;
    L7:
        boolean r4 = false;
    L8:
        r02.allowEmojisForNonPremium(r4);
        r02.setFocusableInTouchMode(true);
        r02.getEditText().hintLayoutYFix = true;
        r02.getEditText().drawHint = new CaptionContainerView$$ExternalSyntheticLambda3(this);
        r02.getEditText().setSupportRtlHint(true);
        EditTextCaption r42 = r02.getEditText();
        if (customBlur() == false) goto L11;
        int r5 = 1;
    L12:
        this.captionBlur = new BlurringShader.StoryBlurDrawer(r37, r42, r5);
        r02.getEditText().setHintColor(-1);
        r02.getEditText().setHintText(LocaleController.getString(R.string.AddCaption), false);
        r0.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        r02.getEditText().setTranslationX(AndroidUtilities.dp(-26.0f));
        int r52 = 48;
        if (isAtTop() == false) goto L15;
        r02.getEditText().setGravity(48);
    L15:
        r02.getEmojiButton().setAlpha(0.0f);
        View r3 = r02.getEmojiButton();
        float r7 = -1.0f;
        if (isAtTop() == false) goto L18;
        float r63 = 1.0f;
    L19:
        r3.setTranslationY(AndroidUtilities.dp(r63));
        if (isAtTop() == false) goto L22;
        r7 = 1.0f;
    L22:
        r02.setTranslationY(AndroidUtilities.dp(r7));
        r02.getEditText().addTextChangedListener(new AnonymousClass4(this));
        r02.getEditText().setLinkTextColor(-1);
        if (isAtTop() == false) goto L25;
        int r38 = 48;
    L26:
        addView(r02, LayoutHelper.createFrame(-1, -2.0f, r38 | 7, 12.0f, 8.0f, additionalRightMargin() + 12, 8.0f));
        BounceableImageView r03 = new BounceableImageView(r32);
        this.applyButton = r03;
        ScaleStateListAnimator.apply(r03, 0.05f, 1.25f);
        Drawable r04 = r32.getResources().getDrawable(R.drawable.input_done).mutate();
        this.applyButtonCheck = r04;
        r04.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_dialogFloatingIcon), PorterDuff.Mode.SRC_IN));
        CombinedDrawable r05 = new CombinedDrawable(Theme.createCircleDrawable(AndroidUtilities.dp(18.0f), Theme.getColor(Theme.key_chat_editMediaButton, r36)), this.applyButtonCheck, 0, AndroidUtilities.dp(1.0f));
        this.applyButtonDrawable = r05;
        r05.setCustomSize(AndroidUtilities.dp(36.0f), AndroidUtilities.dp(36.0f));
        this.applyButton.setImageDrawable(this.applyButtonDrawable);
        this.applyButton.setScaleType(ImageView.ScaleType.CENTER);
        this.applyButton.setContentDescription(LocaleController.getString(R.string.Done));
        this.applyButton.setAlpha(0.0f);
        this.applyButton.setVisibility(8);
        this.applyButton.setOnClickListener(new CaptionContainerView$$ExternalSyntheticLambda4(this));
        View r06 = this.applyButton;
        if (isAtTop() == false) goto L29;
        int r39 = 48;
    L30:
        addView(r06, LayoutHelper.createFrame(44, 44.0f, r39 | 5, 8.0f, 8.0f, 8.0f, 8.0f));
        AnimatedTextView r07 = new AnimatedTextView(r32, false, true, true);
        this.limitTextView = r07;
        r07.setGravity(17);
        this.limitTextView.setTextSize(AndroidUtilities.dp(15.0f));
        this.limitTextView.setTextColor(-1);
        this.limitTextView.setAnimationProperties(0.4f, 0, 320, r6);
        this.limitTextView.setTypeface(AndroidUtilities.bold());
        FrameLayout r08 = new FrameLayout(r32);
        this.limitTextContainer = r08;
        r08.setTranslationX(AndroidUtilities.dp(2.0f));
        FrameLayout r09 = this.limitTextContainer;
        AnimatedTextView r2 = this.limitTextView;
        if (isAtTop() == false) goto L33;
        int r310 = 48;
    L34:
        r09.addView(r2, LayoutHelper.createFrame(52, 16, r310 | 5));
        View r010 = this.limitTextContainer;
        if (isAtTop() == true) goto L38;
        r52 = 80;
    L38:
        int r8 = r52 | 5;
        int r311 = 50;
        if (isAtTop() == false) goto L41;
        int r22 = 50;
    L42:
        float r102 = r22;
        if (isAtTop() == false) goto L45;
        r311 = 0;
    L45:
        addView(r010, LayoutHelper.createFrame(52, 16.0f, r8, 0.0f, r102, 0.0f, r311));
        r14.setShader(r15);
        r14.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        return;
    L41:
        r22 = 0;
        goto L42
    L33:
        r310 = 80;
        goto L34
    L29:
        r39 = 80;
        goto L30
    L25:
        r38 = 80;
        goto L26
    L18:
        r63 = -1.0f;
        goto L19
    L11:
        r5 = 2;
        goto L12
    L5:
        if (getEditTextStyle() != 3) goto L7;
        r4 = true;
        goto L8
    }

    private void animateScrollTo(boolean r7) {
        EditTextCaption r0 = this.editText.getEditText();
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getLayout() == null) goto L20;
        ObjectAnimator r1 = this.scrollAnimator;
        if (r1 == null) goto L10;
        r1.cancel();
    L10:
        int r12 = r0.getScrollY();
        EditTextEmoji r2 = this.editText;
        int r3 = 0;
        if (r7 == false) goto L13;
        int r4 = r2.length();
    L14:
        r2.setSelection(r4);
        this.editText.getEditText().setForceCursorEnd(false);
        int r22 = r0.getLayout().getLineTop(r0.getLineCount());
        int r42 = (r0.getHeight() - r0.getPaddingTop()) - r0.getPaddingBottom();
        if (r7 == false) goto L17;
        r3 = r22 - r42;
    L17:
        ObjectAnimator r72 = ObjectAnimator.ofInt(r0, "scrollY", new int[]{r12, r3});
        this.scrollAnimator = r72;
        r72.setDuration(360);
        this.scrollAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.scrollAnimator.start();
        return;
    L13:
        r4 = 0;
        goto L14
    }

    private void createMentionsContainer() {
        AnonymousClass5 r0 = new AnonymousClass5(this, getContext(), this.dialogId, 0, LaunchActivity.getLastFragment(), new DarkThemeResourceProvider());
        this.mentionContainer = r0;
        this.mentionBackgroundBlur = new BlurringShader.StoryBlurDrawer(this.blurManager, r0, 0);
        setupMentionContainer();
        AnonymousClass6 r9 = new AnonymousClass6(this);
        this.mentionsDelegate = r9;
        this.mentionContainer.withDelegate(r9);
        BlurredBackgroundDrawableViewFactory r92 = this.factoryForMentions;
        if (r92 == null) goto L5;
        MentionsContainerView r02 = this.mentionContainer;
        r02.setBackgroundDrawable(r92.create(r02).setColorProvider(BlurredBackgroundProviderImpl.photoViewer(this.resourcesProvider)));
    L5:
        this.containerView.addView(this.mentionContainer, LayoutHelper.createFrame(-1, -1, 83));
        setupMentionContainer();
    }

    private void drawBackground(Canvas r6, RectF r7, float r8, float r9, View r10) {
        float r1 = 0.0f;
        if (this.keyboardT > 0.0f) goto L5;
    L22:
        Paint r102 = this.backgroundPaint;
        if (this.blurPaint != null) goto L25;
        float r92 = 128.0f;
    L26:
        r102.setAlpha((int) r92);
        r6.drawRoundRect(r7, r8, r8, this.backgroundPaint);
        return;
    L25:
        r92 = r9 * AndroidUtilities.lerp(128, 153, this.keyboardT);
        goto L26
    L5:
        if (this.blurPaint == null) goto L22;
        if (this.blurBitmapShader == null) goto L22;
        Bitmap r0 = this.blurBitmap;
        if (r0 == null) goto L22;
        if (r0.isRecycled() == true) goto L22;
        this.blurBitmapMatrix.reset();
        this.blurBitmapMatrix.postScale(this.rootView.getWidth() / this.blurBitmap.getWidth(), this.rootView.getHeight() / this.blurBitmap.getHeight());
        int r02 = 0;
        float r2 = 0.0f;
    L14:
        if (r02 >= 8) goto L21;
        if (r10 == null) goto L21;
        r1 = r1 + r10.getX();
        r2 = r2 + r10.getY();
        Object r103 = r10.getParent();
        if ((r103 instanceof View) == false) goto L19;
        r10 = (View) r103;
    L20:
        r02 = r02 + 1;
        goto L14
    L19:
        r10 = null;
    L21:
        this.blurBitmapMatrix.postTranslate(-r1, -r2);
        this.blurBitmapShader.setLocalMatrix(this.blurBitmapMatrix);
        this.blurPaint.setAlpha((int) ((this.keyboardT * 255.0f) * r9));
        r6.drawRoundRect(r7, r8, r8, this.blurPaint);
        goto L22
    }

    private void drawHint(Canvas r12, Runnable r13) {
        if (customBlur() == true) goto L5;
        Paint r7 = this.captionBlur.getPaint(1.0f);
        EditTextCaption r2 = this.editText.getEditText();
        if (r7 == null) goto L13;
        int r3 = -1;
    L14:
        r2.setHintColor(r3);
        if (r7 != null) goto L18;
        r13.run();
        return;
    L18:
        EditTextCaption r8 = this.editText.getEditText();
        r12.saveLayerAlpha(0.0f, 0.0f, r8.getWidth(), r8.getHeight(), 255, 31);
        r13.run();
        r12.drawRect(0.0f, 0.0f, r8.getWidth(), r8.getHeight(), r7);
        r12.restore();
        return;
    L13:
        r3 = -2130706433;
        goto L14
    L5:
        if (this.hintTextBitmap != null) goto L8;
        r13.run();
        return;
    L8:
        r12.translate(-this.editText.getEditText().hintLayoutX, 0.0f);
        r12.saveLayerAlpha(0.0f, 0.0f, this.hintTextBitmap.getWidth(), this.hintTextBitmap.getHeight(), 255, 31);
        this.rectF.set(0.0f, 1.0f, this.hintTextBitmap.getWidth(), this.hintTextBitmap.getHeight() - 1);
        drawBlur(this.captionBlur, r12, this.rectF, 0.0f, true, (-this.editText.getX()) - r9.getPaddingLeft(), ((-this.editText.getY()) - r9.getPaddingTop()) - r9.getExtendedPaddingTop(), true, 1.0f);
        r12.save();
        this.hintTextBitmapPaint.setAlpha(165);
        r12.drawBitmap(this.hintTextBitmap, 0.0f, 0.0f, this.hintTextBitmapPaint);
        r12.restore();
        r12.restore();
    }

    private void drawReply(Canvas r18) {
        Canvas r1 = r18;
        if (this.hasReply == true) goto L5;
        return;
    L5:
        if (this.replyBackgroundBlur != null) goto L7;
        return;
    L7:
        if (this.replyTextBlur == null) goto L48;
        float r3 = 1.0f;
        if (this.collapsed == false) goto L18;
        boolean r2 = this.keyboardShown;
        RectF r4 = this.bounds;
        if (r2 == false) goto L15;
        float r22 = r4.bottom;
        int r42 = Math.max(AndroidUtilities.dp(46.0f), this.editText.getHeight());
    L14:
        float r23 = r22 - r42;
        r3 = 1.0f - this.collapsedT.get();
        float r24 = r23 - AndroidUtilities.dp(50.0f);
    L17:
        float r8 = r24;
        float r9 = r3;
        Paint r25 = this.replyBackgroundBlur.getPaint(r9);
        Paint r10 = this.replyTextBlur.getPaint(r9);
        RectF r11 = AndroidUtilities.rectTmp;
        r11.set(this.bounds.left + AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f) + r8, this.bounds.right - AndroidUtilities.dp(10.0f), AndroidUtilities.dp(52.0f) + r8);
        if (r25 == null) goto L22;
        r1.drawRoundRect(r11, AndroidUtilities.dp(5.0f), AndroidUtilities.dp(5.0f), r25);
    L22:
        if (r10 == null) goto L24;
        RectF r26 = this.bounds;
        r1.saveLayerAlpha(r26.left, r26.top, r26.right, r26.bottom, 255, 31);
    L24:
        Path r27 = this.replyClipPath;
        if (r27 != null) goto L27;
        this.replyClipPath = new Path();
    L28:
        float r28 = AndroidUtilities.lerp(AndroidUtilities.dp(21.0f), 0, this.keyboardT);
        Path r32 = this.replyClipPath;
        RectF r43 = this.bounds;
        Path.Direction r13 = Path.Direction.CW;
        r32.addRoundRect(r43, r28, r28, r13);
        r1.clipPath(this.replyClipPath);
        Text r29 = this.replyTitle;
        if (r29 == null) goto L31;
        r29.ellipsize((int) (this.bounds.width() - AndroidUtilities.dp(40.0f))).draw(r1, this.bounds.left + AndroidUtilities.dp(20.0f), AndroidUtilities.dp(22.0f) + r8, -1, 1.0f);
        r1 = r1;
    L31:
        Path r210 = this.replyLinePath;
        if (r210 != null) goto L34;
        this.replyLinePath = new Path();
        float[] r211 = new float[8];
        this.replyLinePathRadii = r211;
        float r33 = AndroidUtilities.dp(5.0f);
        r211[1] = r33;
        r211[0] = r33;
        float[] r212 = this.replyLinePathRadii;
        r212[3] = 0.0f;
        r212[2] = 0.0f;
        r212[5] = 0.0f;
        r212[4] = 0.0f;
        float r34 = AndroidUtilities.dp(5.0f);
        r212[7] = r34;
        r212[6] = r34;
    L35:
        float r213 = r11.left;
        r11.set(r213, r11.top, AndroidUtilities.dp(3.0f) + r213, r11.bottom);
        this.replyLinePath.addRoundRect(r11, this.replyLinePathRadii, r13);
        if (this.replyLinePaint != null) goto L38;
        Paint r214 = new Paint();
        this.replyLinePaint = r214;
        r214.setColor(-1);
    L38:
        this.replyLinePaint.setAlpha((int) (r9 * 255.0f));
        r1.drawPath(this.replyLinePath, this.replyLinePaint);
        if (r10 == null) goto L41;
        r1.save();
        r1.drawRect(this.bounds, r10);
        r1.restore();
        r1.restore();
    L41:
        Text r215 = this.replyText;
        if (r215 == null) goto L47;
        r215.ellipsize((int) (this.bounds.width() - AndroidUtilities.dp(40.0f))).draw(r1, this.bounds.left + AndroidUtilities.dp(20.0f), AndroidUtilities.dp(40.0f) + r8, -1, 1.0f);
        return;
    L47:
        return;
    L34:
        r210.rewind();
        goto L35
    L27:
        r27.rewind();
        goto L28
    L15:
        r22 = r4.bottom;
        r42 = Math.min(AndroidUtilities.dp(82.0f), this.editText.getHeight());
        goto L14
    L18:
        r24 = this.bounds.top;
        goto L17
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        done();
    }

    private /* synthetic */ void lambda$new$1() {
        onTextChange();
    }

    private /* synthetic */ void lambda$new$2() {
        updateShowKeyboard(this.toKeyboardShow, true);
    }

    private /* synthetic */ void lambda$updateShowKeyboard$3(ValueAnimator r5) {
        this.keyboardT = ((Float) r5.getAnimatedValue()).floatValue();
        this.editText.getEditText().setTranslationX(AndroidUtilities.lerp(AndroidUtilities.dp(-26.0f) + getEditTextLeft(), AndroidUtilities.dp(2.0f), this.keyboardT));
        this.limitTextContainer.setTranslationX(AndroidUtilities.lerp(-AndroidUtilities.dp(8.0f), AndroidUtilities.dp(2.0f), this.keyboardT));
        this.limitTextContainer.setTranslationY(AndroidUtilities.lerp(-AndroidUtilities.dp(8.0f), 0, this.keyboardT));
        this.editText.getEmojiButton().setAlpha(this.keyboardT);
        this.applyButton.setAlpha((float) Math.pow(this.keyboardT, 16.0d));
        onUpdateShowKeyboard(this.keyboardT);
        MentionsContainerView r52 = this.mentionContainer;
        if (r52 == null) goto L5;
        r52.setAlpha((float) Math.pow(this.keyboardT, 4.0d));
    L5:
        this.editText.getEditText().invalidate();
        invalidate();
    }

    private void replaceWithText(int r3, int r4, CharSequence r5, boolean r6) {
        if (this.editText != null) goto L13;
        return;
    L13:
        SpannableStringBuilder r0 = new SpannableStringBuilder(this.editText.getText());     // Catch: Exception -> L10
        r0.replace(r3, r4 + r3, r5);     // Catch: Exception -> L10
        if (r6 == false) goto L8;
        Emoji.replaceEmoji(r0, this.editText.getEditText().getPaint().getFontMetricsInt(), false);     // Catch: Exception -> L10
    L8:
        this.editText.setText(r0);     // Catch: Exception -> L10
        this.editText.setSelection(r3 + r5.length());     // Catch: Exception -> L10
        return;
    L10:
        e = move-exception;
        FileLog.e(e);
    }

    private void updateShowKeyboard(boolean r10, boolean r11) {
        if (this.keyboardShown == r10) goto L70;
        this.keyboardShown = r10;
        ValueAnimator r0 = this.keyboardAnimator;
        if (r0 == null) goto L8;
        r0.cancel();
        this.keyboardAnimator = null;
    L8:
        Utilities.Callback<Boolean> r02 = this.onKeyboardOpen;
        if (r02 == null) goto L11;
        r02.run(Boolean.valueOf(r10));
    L11:
        beforeUpdateShownKeyboard(r10);
        float r2 = 0.0f;
        if (r11 == false) goto L30;
        if (r10 == false) goto L18;
        MentionsContainerView r112 = this.mentionContainer;
        if (r112 == null) goto L17;
        r112.setVisibility(0);
    L17:
        this.applyButton.setVisibility(0);
    L19:
        float r113 = this.keyboardT;
        if (r10 == false) goto L22;
        r2 = 1.0f;
    L22:
        ValueAnimator r114 = ValueAnimator.ofFloat(new float[]{r113, r2});
        this.keyboardAnimator = r114;
        r114.addUpdateListener(new CaptionContainerView$$ExternalSyntheticLambda5(this));
        if (r10 == true) goto L25;
        this.editText.getEditText().setAllowDrawCursor(false);
    L25:
        this.keyboardAnimator.addListener(new AnonymousClass7(this, r10));
        ValueAnimator r115 = this.keyboardAnimator;
        if (r10 == false) goto L28;
        r115.setInterpolator(AdjustPanLayoutHelper.keyboardInterpolator);
        this.keyboardAnimator.setDuration(250);
    L29:
        this.keyboardAnimator.start();
    L41:
        animateScrollTo(r10);
        this.editText.setSuggestionsEnabled(r10);
        if (r10 == true) goto L44;
        this.editText.getEditText().setSpoilersRevealed(false, true);
    L44:
        if (r10 == true) goto L46;
        return;
    L46:
        if (SharedConfig.getDevicePerformanceClass() >= 1) goto L48;
        return;
    L48:
        if (LiteMode.isPowerSaverApplied() == false) goto L50;
        return;
    L50:
        if (this.blurBitmap != null) goto L52;
        this.blurBitmap = Bitmap.createBitmap((int) (this.rootView.getWidth() / 12.0f), (int) (this.rootView.getHeight() / 12.0f), Bitmap.Config.ARGB_8888);
    L52:
        this.ignoreDraw = true;
        drawBlurBitmap(this.blurBitmap, 12.0f);
        this.ignoreDraw = false;
        Bitmap r102 = this.blurBitmap;
        if (r102 != null) goto L55;
    L65:
        this.blurBitmap = null;
        return;
    L55:
        if (r102.isRecycled() == true) goto L65;
        Bitmap r116 = this.blurBitmap;
        Shader.TileMode r03 = Shader.TileMode.CLAMP;
        this.blurBitmapShader = new BitmapShader(r116, r03, r03);
        Matrix r103 = this.blurBitmapMatrix;
        if (r103 != null) goto L59;
        this.blurBitmapMatrix = new Matrix();
    L60:
        this.blurBitmapShader.setLocalMatrix(this.blurBitmapMatrix);
        if (this.blurPaint != null) goto L63;
        Paint r104 = new Paint(3);
        this.blurPaint = r104;
        r104.setColor(-1);
    L63:
        this.blurPaint.setShader(this.blurBitmapShader);
        return;
    L59:
        r103.reset();
        goto L60
    L28:
        r115.setInterpolator(new FastOutSlowInInterpolator());
        this.keyboardAnimator.setDuration(420);
        goto L29
    L18:
        this.editText.getEditText().scrollBy(0, -this.editText.getEditText().getScrollY());
        goto L19
    L30:
        if (r10 == false) goto L32;
        float r117 = 1.0f;
    L33:
        this.keyboardT = r117;
        this.editText.getEditText().setTranslationX(AndroidUtilities.lerp(AndroidUtilities.dp(-26.0f) + getEditTextLeft(), AndroidUtilities.dp(2.0f), this.keyboardT));
        this.limitTextContainer.setTranslationX(AndroidUtilities.lerp(-AndroidUtilities.dp(8.0f), AndroidUtilities.dp(2.0f), this.keyboardT));
        this.limitTextContainer.setTranslationY(AndroidUtilities.lerp(-AndroidUtilities.dp(8.0f), 0, this.keyboardT));
        this.editText.getEmojiButton().setAlpha(this.keyboardT);
        ImageView r118 = this.applyButton;
        if (r10 == false) goto L36;
        int r5 = 0;
    L37:
        r118.setVisibility(r5);
        ImageView r119 = this.applyButton;
        if (r10 == false) goto L40;
        r2 = 1.0f;
    L40:
        r119.setAlpha(r2);
        onUpdateShowKeyboard(this.keyboardT);
        this.editText.getEditText().setAllowDrawCursor(r10);
        afterUpdateShownKeyboard(r10);
        invalidate();
        goto L41
    L36:
        r5 = 8;
        goto L37
    L32:
        r117 = 0.0f;
        goto L33
    }

    public int additionalKeyboardHeight() {
        return AndroidUtilities.navigationBarHeight;
    }

    public int additionalRightMargin() {
        return 0;
    }

    public abstract void afterUpdateShownKeyboard(boolean r1);

    public abstract void beforeUpdateShownKeyboard(boolean r1);

    public abstract boolean captionLimitToast();

    public void clear() {
        this.ignoreTextChange = true;
        this.editText.setText(_UrlKt.FRAGMENT_ENCODE_SET);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void clearFocus() {
        this.editText.clearFocus();
    }

    public boolean clipChild(View r1) {
        return true;
    }

    public void closeKeyboard() {
        this.editText.closeKeyboard();
        this.editText.hidePopup(true);
    }

    public abstract boolean customBlur();

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r30) {
        if (this.ignoreDraw == true) goto L92;
        int r1 = this.editText.getHeight();
        if (this.collapsed == false) goto L9;
        int r12 = AndroidUtilities.dp(44.0f);
    L13:
        if (this.collapsed == false) goto L15;
    L17:
        float r4 = r12;
        int r3 = (int) this.heightAnimated.set(r4);
        if (r3 == this.lastHeight) goto L23;
        onEditHeightChange(r3);
        Utilities.Callback<Integer> r5 = this.onHeightUpdate;
        if (r5 == null) goto L22;
        r5.run(Integer.valueOf(r3));
    L22:
        this.lastHeight = r12;
    L23:
        updateMentionsLayoutPosition();
        int r13 = AndroidUtilities.dp(7.0f);
        int r52 = AndroidUtilities.dp(8.0f);
        if (isAtTop() == true) goto L26;
        float r6 = (AndroidUtilities.lerp(AndroidUtilities.dpf2(1.0f), AndroidUtilities.dpf2(-1.0f), this.keyboardT) + r4) - r3;
        if (Math.abs(this.lastHeightTranslation - r6) >= 1.0f) goto L32;
    L34:
        this.bounds.set(r13, (getHeight() - r52) - r3, getWidth() - r13, getHeight() - r52);
        this.clickBounds.set(0.0f, (getHeight() - r3) - AndroidUtilities.dp(24.0f), getWidth(), getHeight());
    L35:
        r30.save();
        float r14 = this.bounce.getScale(0.018f);
        r30.scale(r14, r14, this.bounds.centerX(), this.bounds.centerY());
        float r42 = AndroidUtilities.lerp(AndroidUtilities.dp(21.0f), 0, this.keyboardT * (1.0f - forceRound()));
        BlurredBackgroundDrawableViewFactory r15 = this.factoryForMentions;
        if (r15 != null) goto L38;
        boolean r16 = customBlur();
        BlurringShader.StoryBlurDrawer r17 = this.backgroundBlur;
        if (r16 == false) goto L44;
        drawBlur(r17, r30, this.bounds, r42, false, 0.0f, 0.0f, true, 1.0f);
        this.backgroundPaint.setAlpha(AndroidUtilities.lerp(38, 64, this.keyboardT));
        r30.drawRoundRect(this.bounds, r42, r42, this.backgroundPaint);
    L57:
        float r18 = this.collapsedT.get();
        float r32 = this.collapsedT.set(this.collapsed);
        if (Math.abs(r18 - r32) <= 0.001f) goto L60;
    L68:
        invalidateDrawOver2();
    L70:
        if (r32 <= 0.0f) goto L72;
        r30.saveLayerAlpha(this.bounds, 255, 31);
    L72:
        drawReply(r30);
        super.dispatchDraw(r30);
        if (r32 <= 0.0f) goto L87;
        int r19 = this.collapsedFromX;
        if (r19 != Integer.MAX_VALUE) goto L78;
        float r110 = this.bounds.right - AndroidUtilities.dp(20.0f);
    L81:
        float r7 = this.bounds.bottom - AndroidUtilities.dp(20.0f);
        RectF r8 = this.bounds;
        float r82 = MathUtils.distance(r8.left, r8.top, r110, r7);
        RectF r9 = this.bounds;
        float r83 = Math.max(r82, MathUtils.distance(r9.left, r9.bottom, r110, r7));
        RectF r92 = this.bounds;
        float r93 = MathUtils.distance(r92.right, r92.top, r110, r7);
        RectF r10 = this.bounds;
        float r84 = Math.max(r83, Math.max(r93, MathUtils.distance(r10.right, r10.bottom, r110, r7))) * r32;
        if (this.collapsePaint != null) goto L84;
        Paint r94 = new Paint(1);
        this.collapsePaint = r94;
        PorterDuff.Mode r142 = PorterDuff.Mode.DST_OUT;
        r94.setXfermode(new PorterDuffXfermode(r142));
        Shader.TileMode r21 = Shader.TileMode.CLAMP;
        RadialGradient r152 = new RadialGradient(0.0f, 0.0f, 32.0f, new int[]{-1, -1, 0}, new float[]{0.0f, 0.6f, 1.0f}, r21);
        this.collapseGradient = r152;
        this.collapsePaint.setShader(r152);
        this.collapseGradientMatrix = new Matrix();
        Paint r53 = new Paint(1);
        this.collapseOutPaint = r53;
        r53.setXfermode(new PorterDuffXfermode(r142));
        RadialGradient r22 = new RadialGradient(0.0f, 0.0f, 32.0f, new int[]{0, 0, -1}, new float[]{0.0f, 0.5f, 1.0f}, r21);
        this.collapseOutGradient = r22;
        this.collapseOutPaint.setShader(r22);
    L84:
        this.collapseGradientMatrix.reset();
        this.collapseGradientMatrix.postTranslate(r110, r7);
        this.collapseGradientMatrix.preScale(Math.max(1.0f, r84) / 16.0f, Math.max(1.0f, r84) / 16.0f);
        this.collapseGradient.setLocalMatrix(this.collapseGradientMatrix);
        r30.save();
        r30.drawRoundRect(this.bounds, r42, r42, this.collapsePaint);
        r30.restore();
        r30.restore();
        r30.saveLayerAlpha(this.bounds, 255, 31);
        drawOver(r30, this.bounds);
        this.collapseGradientMatrix.reset();
        this.collapseGradientMatrix.postTranslate(r110, r7);
        this.collapseGradientMatrix.preScale(Math.max(1.0f, r84) / 16.0f, Math.max(1.0f, r84) / 16.0f);
        this.collapseOutGradient.setLocalMatrix(this.collapseGradientMatrix);
        r30.save();
        r30.drawRoundRect(this.bounds, r42, r42, this.collapseOutPaint);
        r30.restore();
        r30.restore();
        if (drawOver2FromParent() == true) goto L87;
        drawOver2(r30, this.bounds, r32);
        goto L87
    L78:
        if (r19 != Integer.MIN_VALUE) goto L80;
        r110 = this.bounds.left + AndroidUtilities.dp(20.0f);
        goto L81
    L80:
        r110 = r19;
    L87:
        r30.restore();
        if (this.factoryForMentions != null) goto L91;
        this.clipPath.rewind();
        this.clipPath.addRoundRect(this.bounds, r42, r42, Path.Direction.CW);
        r30.save();
        r30.clipPath(this.clipPath);
        StrokeDrawable r111 = this.strokeDrawable;
        r111.radius = r42;
        RectF r33 = this.bounds;
        r111.setBounds((int) r33.left, (int) r33.top, (int) r33.right, (int) r33.bottom);
        this.strokeDrawable.draw(r30);
        r30.restore();
        return;
    L91:
        return;
    L60:
        if (r18 > 0.0f) goto L62;
        boolean r112 = true;
    L64:
        if (r32 > 0.0f) goto L66;
        boolean r54 = true;
    L67:
        if (r112 == r54) goto L70;
    L66:
        r54 = false;
        goto L67
    L62:
        r112 = false;
        goto L64
    L44:
        Paint[] r113 = r17.getPaints(1.0f, 0.0f, 0.0f);
        if (r113 != null) goto L47;
    L56:
        this.backgroundPaint.setAlpha(128);
        r30.drawRoundRect(this.bounds, r42, r42, this.backgroundPaint);
        goto L57
    L47:
        if (r113[1] == null) goto L56;
        Paint r34 = r113[0];
        if (r34 == null) goto L52;
        r30.drawRoundRect(this.bounds, r42, r42, r34);
    L52:
        Paint r114 = r113[1];
        if (r114 == null) goto L55;
        r30.drawRoundRect(this.bounds, r42, r42, r114);
    L55:
        this.backgroundPaint.setAlpha(51);
        r30.drawRoundRect(this.bounds, r42, r42, this.backgroundPaint);
        goto L57
    L38:
        if (this.backgroundForCaptionField != null) goto L40;
        this.backgroundForCaptionField = r15.create(this).setColorProvider(BlurredBackgroundProviderImpl.photoViewer(this.resourcesProvider)).setPadding(AndroidUtilities.dp(5.0f)).setRadius(AndroidUtilities.dp(22.0f));
    L40:
        RectF r115 = this.bounds;
        Rect r35 = AndroidUtilities.rectTmp2;
        r115.round(r35);
        r35.inset(-AndroidUtilities.lerp(AndroidUtilities.dp(1.0f), AndroidUtilities.dp(5.0f), this.keyboardT), -AndroidUtilities.dp(5.0f));
        this.backgroundForCaptionField.setBounds(r35);
        this.backgroundForCaptionField.draw(r30);
        goto L57
    L32:
        if (this.collapsed == true) goto L34;
        EditTextCaption r43 = this.editText.getEditText();
        this.lastHeightTranslation = r6;
        r43.setTranslationY(r6);
        goto L34
    L26:
        if (this.collapsed == true) goto L28;
        float r44 = AndroidUtilities.lerp(AndroidUtilities.dpf2(-1.0f), AndroidUtilities.dpf2(1.0f), this.keyboardT);
        EditTextCaption r62 = this.editText.getEditText();
        this.lastHeightTranslation = r44;
        r62.setTranslationY(r44);
    L28:
        float r63 = r13;
        float r85 = r52;
        this.bounds.set(r63, r85, getWidth() - r13, r52 + r3);
        this.clickBounds.set(r63, r85, getWidth() - r13, r5 + AndroidUtilities.dp(24.0f));
        goto L35
    L15:
        if (this.hasReply == false) goto L17;
        r12 = r12 + AndroidUtilities.dp(50.0f);
        goto L17
    L9:
        if (this.keyboardShown == false) goto L11;
        r12 = Math.max(AndroidUtilities.dp(44.0f), r1);
        goto L13
    L11:
        r12 = Math.min(AndroidUtilities.dp(82.0f), r1);
        goto L13
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r10) {
        if (this.ignoreTouches == false) goto L5;
    L51:
        return false;
    L5:
        if (r10.getAction() != 0) goto L9;
        if (ignoreTouches(r10.getX(), r10.getY()) == true) goto L51;
    L9:
        if (this.clickBounds.contains(r10.getX(), r10.getY()) == true) goto L14;
        if (this.keyboardShown == false) goto L51;
    L14:
        if (r10.getAction() != 0) goto L45;
        if (this.keyboardShown == true) goto L45;
        if ((this instanceof CaptionStory) == true) goto L20;
    L23:
        int r0 = 0;
    L25:
        if (r0 >= getChildCount()) goto L42;
        View r3 = getChildAt(r0);
        if (r3 == null) goto L41;
        if (r3.isClickable() == false) goto L41;
        if (r3.getVisibility() != 0) goto L41;
        if (r3.getAlpha() < 0.5f) goto L41;
        if (this.editText == r3) goto L41;
        this.rectF.set(r3.getX(), r3.getY(), r3.getX() + r3.getWidth(), r3.getY() + r3.getHeight());
        if (this.rectF.contains(r10.getX(), r10.getY()) == false) goto L41;
        return super.dispatchTouchEvent(r10);
    L41:
        r0 = r0 + 1;
        goto L25
    L42:
        this.keyboardNotifier.ignore(false);
        this.editText.getEditText().setForceCursorEnd(true);
        this.editText.getEditText().requestFocus();
        this.editText.openKeyboard();
        this.editText.getEditText().setScrollY(0);
        this.bounce.setPressed(true);
        return true;
    L20:
        if (((CaptionStory) this).isRecording() == false) goto L23;
        return super.dispatchTouchEvent(r10);
    L45:
        if (r10.getAction() != 1) goto L47;
    L48:
        this.bounce.setPressed(false);
    L50:
        return super.dispatchTouchEvent(r10);
    L47:
        if (r10.getAction() != 3) goto L50;
        goto L48
    }

    public void done() {
        closeKeyboard();
        AndroidUtilities.cancelRunOnUIThread(this.textChangeRunnable);
        this.textChangeRunnable.run();
    }

    public abstract void drawBlur(BlurringShader.StoryBlurDrawer r1, Canvas r2, RectF r3, float r4, boolean r5, float r6, float r7, boolean r8, float r9);

    public void drawBlurBitmap(Bitmap r1, float r2) {
        Utilities.stackBlurBitmap(r1, (int) r2);
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r13, View r14, long r15) {
        if (r14 != this.editText) goto L11;
        if (isAtTop() == false) goto L7;
        float r11 = 0.0f;
    L8:
        r13.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), 255, 31);
        r13.save();
        r13.clipRect(this.bounds);
        r13.translate(0.0f, r11);
        boolean r7 = super.drawChild(r13, r14, r15);
        r13.restore();
        r13.save();
        this.matrix.reset();
        this.matrix.postTranslate(0.0f, this.bounds.top - 1.0f);
        this.fadeGradient.setLocalMatrix(this.matrix);
        RectF r2 = this.bounds;
        float r3 = r2.left;
        float r32 = r2.top;
        r13.drawRect(r3, r32, r2.right, AndroidUtilities.dp(10.0f) + r32, this.fadePaint);
        this.matrix.reset();
        this.matrix.postRotate(180.0f);
        this.matrix.postTranslate(0.0f, this.bounds.bottom);
        this.fadeGradient.setLocalMatrix(this.matrix);
        RectF r1 = this.bounds;
        float r22 = r1.left;
        float r12 = r1.bottom - AndroidUtilities.dp(10.0f);
        RectF r33 = this.bounds;
        r13.drawRect(r22, r12, r33.right, r33.bottom, this.fadePaint);
        r13.restore();
        r13.restore();
        return r7;
    L7:
        r11 = Math.max(0, (this.editText.getHeight() - AndroidUtilities.dp(82.0f)) - this.editText.getScrollY()) * (1.0f - this.keyboardT);
        goto L8
    L11:
        if (clipChild(r14) == false) goto L15;
        r13.save();
        r13.clipRect(this.bounds);
        boolean r0 = super.drawChild(r13, r14, r15);
        r13.restore();
        return r0;
    L15:
        return super.drawChild(r13, r14, r15);
    }

    public void drawOver(Canvas r1, RectF r2) {
    }

    public void drawOver2(Canvas r1, RectF r2, float r3) {
    }

    public boolean drawOver2FromParent() {
        return false;
    }

    public float forceRound() {
        return 1.0f;
    }

    public RectF getBounds() {
        return this.bounds;
    }

    public int getCaptionDefaultLimit() {
        return 0;
    }

    public int getCaptionLimit() {
        if (UserConfig.getInstance(this.currentAccount).isPremium() == false) goto L7;
        return getCaptionPremiumLimit();
    L7:
        return getCaptionDefaultLimit();
    }

    public int getCaptionPremiumLimit() {
        return 0;
    }

    public int getCodePointCount() {
        return this.codePointCount;
    }

    public int getEditTextHeight() {
        return (int) this.heightAnimated.get();
    }

    public int getEditTextHeightClosedKeyboard() {
        return Math.min(AndroidUtilities.dp(82.0f), this.editText.getHeight());
    }

    public int getEditTextLeft() {
        return 0;
    }

    public int getEditTextStyle() {
        return 2;
    }

    public float getOver2Alpha() {
        return this.collapsedT.get();
    }

    public int getSelectionLength() {
        EditTextEmoji r0 = this.editText;
        if (r0 != null) goto L5;
    L12:
        return 0;
    L5:
        if (r0.getEditText() == null) goto L12;
        return this.editText.getEditText().getSelectionEnd() - this.editText.getEditText().getSelectionStart();
    L10:
        e = move-exception;
        FileLog.e(e);
        goto L12
    }

    public CharSequence getText() {
        return this.editText.getText();
    }

    public abstract boolean ignoreTouches(float r1, float r2);

    public void invalidateBlur() {
        invalidate();
        this.editText.getEditText().invalidate();
        this.editText.getEmojiButton().invalidate();
        MentionsContainerView r0 = this.mentionContainer;
        if (r0 == null) goto L6;
        r0.invalidate();
    L6:
        if (this.editText.getEmojiView() != null) goto L8;
        return;
    L8:
        if (customBlur() == false) goto L12;
        this.editText.getEmojiView().invalidate();
        return;
    }

    public void invalidateDrawOver2() {
    }

    public boolean isAtTop() {
        return false;
    }

    public boolean isCaptionOverLimit() {
        if (getCodePointCount() <= getCaptionLimit()) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (customBlur() == false) goto L9;
        Bitmap r0 = this.hintTextBitmap;
        if (r0 == null) goto L7;
        r0.recycle();
        this.hintTextBitmap = null;
    L7:
        this.hintTextPaint.setColor(-16777216);
        this.hintTextPaint.setTextSize(AndroidUtilities.dp(16.0f));
        String r02 = LocaleController.getString(R.string.AddCaption);
        this.hintTextBitmap = Bitmap.createBitmap((int) Math.ceil(this.hintTextPaint.measureText(r02)), (int) Math.ceil(this.hintTextPaint.getFontMetrics().descent - this.hintTextPaint.getFontMetrics().ascent), Bitmap.Config.ARGB_8888);
        new Canvas(this.hintTextBitmap).drawText(r02, 0.0f, -((int) this.hintTextPaint.getFontMetrics().ascent), this.hintTextPaint);
        return;
    }

    public boolean onBackPressed() {
        EditTextEmoji r0 = this.editText;
        if (r0.emojiExpanded == true) goto L5;
    L11:
        boolean r02 = this.editText.isPopupShowing();
        EditTextEmoji r1 = this.editText;
        if (r02 == false) goto L16;
        r1.hidePopup(true);
        return true;
    L16:
        if (r1.isKeyboardVisible() == true) goto L20;
        if (this.keyboardNotifier.keyboardVisible() == true) goto L20;
        return false;
    L20:
        if (this.keyboardNotifier.ignoring == true) goto L25;
        closeKeyboard();
        return true;
    L25:
        return false;
    L5:
        if (r0.getEmojiView() == null) goto L11;
        boolean r03 = this.keyboardNotifier.keyboardVisible();
        EditTextEmoji r3 = this.editText;
        if (r03 == false) goto L9;
        r3.getEmojiView().hideSearchKeyboard();
    L10:
        return true;
    L9:
        r3.collapseEmojiView();
        goto L10
    }

    public void onCaptionLimitUpdate(boolean r1) {
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Bitmap r0 = this.blurBitmap;
        if (r0 == null) goto L5;
        r0.recycle();
    L5:
        this.blurBitmapShader = null;
        this.blurPaint = null;
        Bitmap r1 = this.hintTextBitmap;
        if (r1 == null) goto L9;
        r1.recycle();
        this.hintTextBitmap = null;
        return;
    }

    public void onEditHeightChange(int r1) {
    }

    public void onLineCountChanged(int r1, int r2) {
    }

    public void onPause() {
        this.editText.onPause();
    }

    public void onResume() {
        this.editText.onResume();
    }

    public void onTextChange() {
    }

    public abstract void onUpdateShowKeyboard(float r1);

    public void setAccount(int r1) {
        this.currentAccount = r1;
    }

    public void setBlurredBackgroundDrawableForMentions(BlurredBackgroundDrawableViewFactory r1) {
        this.factoryForMentions = r1;
    }

    public void setCollapsed(boolean r1, int r2) {
        this.collapsed = r1;
        this.collapsedFromX = r2;
        invalidate();
    }

    public void setDialogId(long r1) {
        this.dialogId = r1;
        MentionsContainerView r0 = this.mentionContainer;
        if (r0 == null) goto L6;
        r0.setDialogId(r1);
        return;
    }

    public void setOnHeightUpdate(Utilities.Callback<Integer> r1) {
        this.onHeightUpdate = r1;
    }

    public void setOnKeyboardOpen(Utilities.Callback<Boolean> r1) {
        this.onKeyboardOpen = r1;
    }

    @Override // android.view.View
    public void setPressed(boolean r2) {
        super.setPressed(r2);
        ButtonBounce r0 = this.bounce;
        if (r2 == true) goto L5;
    L7:
        boolean r1 = false;
    L8:
        r0.setPressed(r1);
        return;
    L5:
        if (this.keyboardShown == true) goto L7;
        r1 = true;
        goto L8
    }

    public void setReply(CharSequence r5, CharSequence r6) {
        if (r5 != null) goto L6;
        if (r6 != null) goto L6;
        this.hasReply = false;
        invalidate();
        return;
    L6:
        this.hasReply = true;
        if (r5 != null) goto L9;
        r5 = _UrlKt.FRAGMENT_ENCODE_SET;
    L9:
        this.replyTitle = new Text(r5, 14.0f, AndroidUtilities.bold());
        if (r6 != null) goto L12;
        r6 = _UrlKt.FRAGMENT_ENCODE_SET;
    L12:
        this.replyText = new Text(r6, 14.0f);
    }

    public void setText(CharSequence r2) {
        this.ignoreTextChange = true;
        this.editText.setText(r2);
    }

    public void setUiBlurBitmap(Utilities.CallbackVoidReturn<Bitmap> r1) {
        this.getUiBlurBitmap = r1;
    }

    public void setupMentionContainer() {
        this.mentionContainer.getAdapter().setAllowStickers(false);
        this.mentionContainer.getAdapter().setAllowBots(false);
        this.mentionContainer.getAdapter().setAllowChats(false);
        this.mentionContainer.getAdapter().setSearchInDialogs(this instanceof CaptionStory);
    }

    public void updateColors(Theme.ResourcesProvider r5) {
        this.resourcesProvider = r5;
        this.applyButtonCheck.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_dialogFloatingIcon), PorterDuff.Mode.SRC_IN));
        this.applyButtonDrawable.setBackgroundDrawable(Theme.createCircleDrawable(AndroidUtilities.dp(16.0f), Theme.getColor(Theme.key_chat_editMediaButton, r5)));
    }

    public void updateEditTextLeft() {
        this.editText.getEditText().setTranslationX(AndroidUtilities.lerp(AndroidUtilities.dp(-26.0f) + getEditTextLeft(), AndroidUtilities.dp(2.0f), this.keyboardT));
    }

    public void updateKeyboard(int r8) {
        SizeNotifierFrameLayout r0 = this.sizeNotifierFrameLayout;
        if (r0 == null) goto L5;
        r0.notifyHeightChanged();
    L5:
        boolean r1 = false;
        if (this.editText.isPopupShowing() == false) goto L9;
        r8 = Math.max(0, additionalKeyboardHeight() + this.editText.getEmojiPadding());
    L11:
        SizeNotifierFrameLayout r02 = this.sizeNotifierFrameLayout;
        if (r02 != null) goto L14;
        int r03 = 0;
    L15:
        int r82 = Math.max(0, r8 - r03);
        View r04 = (View) getParent();
        r04.clearAnimation();
        if (isAtTop() == true) goto L26;
        ObjectAnimator r2 = this.parentKeyboardAnimator;
        if (r2 == null) goto L20;
        r2.removeAllListeners();
        this.parentKeyboardAnimator.cancel();
        this.parentKeyboardAnimator = null;
    L20:
        this.parentKeyboardAnimator = ObjectAnimator.ofFloat(r04, FrameLayout.TRANSLATION_Y, new float[]{r04.getTranslationY(), -r82});
        int r05 = AndroidUtilities.dp(20.0f);
        ObjectAnimator r22 = this.parentKeyboardAnimator;
        if (r82 <= r05) goto L23;
        r22.setInterpolator(AdjustPanLayoutHelper.keyboardInterpolator);
        this.parentKeyboardAnimator.setDuration(250);
    L24:
        this.parentKeyboardAnimator.start();
        goto L26
    L23:
        r22.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.parentKeyboardAnimator.setDuration(640);
    L26:
        if (r82 <= AndroidUtilities.dp(20.0f)) goto L28;
        r1 = true;
    L28:
        this.toKeyboardShow = r1;
        AndroidUtilities.cancelRunOnUIThread(this.updateShowKeyboard);
        AndroidUtilities.runOnUIThread(this.updateShowKeyboard);
        if (r82 >= AndroidUtilities.dp(20.0f)) goto L32;
        this.editText.getEditText().clearFocus();
        this.editText.hidePopup(true);
        return;
    L32:
        return;
    L14:
        r03 = r02.getBottomPadding();
        goto L15
    L9:
        if (this.editText.isWaitingForKeyboardOpen() == false) goto L11;
        r8 = Math.max(0, additionalKeyboardHeight() + this.editText.getKeyboardHeight());
        goto L11
    }

    public void updateMentionsLayoutPosition() {
        if (this.mentionContainer == null) goto L8;
        float r0 = ((View) getParent()).getTranslationY() - this.heightAnimated.get();
        if (this.mentionContainer.getY() == r0) goto L9;
        this.mentionContainer.setTranslationY(r0);
        this.mentionContainer.invalidate();
        return;
    L9:
        return;
    }
}
