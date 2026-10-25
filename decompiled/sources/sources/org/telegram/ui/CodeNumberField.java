package org.telegram.ui;

import android.R;
import android.animation.ValueAnimator;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.text.Editable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextWatcher;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import androidx.core.content.ContextCompat;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.SimpleFloatPropertyCompat;

/* loaded from: classes6.dex */
public abstract class CodeNumberField extends EditTextBoldCursor {
    private static final FloatPropertyCompat<CodeNumberField> ERROR_PROGRESS = null;
    private static final FloatPropertyCompat<CodeNumberField> FOCUSED_PROGRESS = null;
    private static final FloatPropertyCompat<CodeNumberField> SUCCESS_PROGRESS = null;
    private static final FloatPropertyCompat<CodeNumberField> SUCCESS_SCALE_PROGRESS = null;
    float enterAnimation;
    ValueAnimator enterAnimator;
    private float errorProgress;
    private SpringAnimation errorSpringAnimation;
    float exitAnimation;
    ValueAnimator exitAnimator;
    Bitmap exitBitmap;
    Canvas exitCanvas;
    private float focusedProgress;
    private SpringAnimation focusedSpringAnimation;
    boolean pressed;
    boolean replaceAnimation;
    private boolean showSoftInputOnFocusInternal;
    float startX;
    float startY;
    private float successProgress;
    private float successScaleProgress;
    private SpringAnimation successScaleSpringAnimation;
    private SpringAnimation successSpringAnimation;

    /* renamed from: org.telegram.ui.CodeNumberField$1, reason: invalid class name */
    public class AnonymousClass1 implements TextWatcher {
        final /* synthetic */ CodeNumberField this$0;

        public AnonymousClass1(CodeNumberField r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r1) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
            CodeNumberField r22 = this.this$0;
            if (r1.length() == 0) goto L5;
            boolean r12 = true;
        L6:
            r22.startEnterAnimation(r12);
            this.this$0.hideActionMode();
            return;
        L5:
            r12 = false;
            goto L6
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.CodeNumberField$2, reason: invalid class name */
    public class AnonymousClass2 implements ActionMode.Callback {
        final /* synthetic */ CodeNumberField this$0;

        public AnonymousClass2(CodeNumberField r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ActionMode.Callback
        public boolean onActionItemClicked(ActionMode r2, MenuItem r3) {
            if (r3.getItemId() == 16908322) goto L5;
            return true;
        L5:
            CodeNumberField.m8658$$Nest$mpasteFromClipboard(this.this$0);
            this.this$0.hideActionMode();
            return true;
        }

        @Override // android.view.ActionMode.Callback
        public boolean onCreateActionMode(ActionMode r2, Menu r3) {
            r3.add(0, R.id.paste, 0, R.string.paste);
            return true;
        }

        @Override // android.view.ActionMode.Callback
        public void onDestroyActionMode(ActionMode r1) {
        }

        @Override // android.view.ActionMode.Callback
        public boolean onPrepareActionMode(ActionMode r1, Menu r2) {
            return true;
        }
    }

    /* renamed from: $r8$lambda$-5g0GVwwllzQjIXFZT8itpR3Y64, reason: not valid java name */
    public static /* synthetic */ void m8654$r8$lambda$5g0GVwwllzQjIXFZT8itpR3Y64(CodeNumberField r0, float r1) {
        r0.errorProgress = r1;
        if (r0.getParent() == null) goto L6;
        ((View) r0.getParent()).invalidate();
        return;
    }

    public static /* synthetic */ float $r8$lambda$2FBIJfK6uDoB7WEDPSqsAb3WDm0(CodeNumberField r0) {
        return r0.focusedProgress;
    }

    public static /* synthetic */ float $r8$lambda$34p38S7bAxGRmJAX1cNIWT4pYyE(CodeNumberField r0) {
        return r0.errorProgress;
    }

    public static /* synthetic */ float $r8$lambda$7eoBv_fesWyyt52qZwqUMlUSWns(CodeNumberField r0) {
        return r0.successProgress;
    }

    public static /* synthetic */ float $r8$lambda$CqX2w7BmV_JB8akX88aagtdENvQ(CodeNumberField r0) {
        return r0.successScaleProgress;
    }

    public static /* synthetic */ void $r8$lambda$HpXuBv82yoAS76t8pi9KS1yL5o0(CodeNumberField r0, float r1) {
        r0.focusedProgress = r1;
        if (r0.getParent() == null) goto L6;
        ((View) r0.getParent()).invalidate();
        return;
    }

    /* renamed from: $r8$lambda$S9PiEpe-VA-_A4LBoj8bYWPuL_E, reason: not valid java name */
    public static /* synthetic */ void m8655$r8$lambda$S9PiEpeVA_A4LBoj8bYWPuL_E(CodeNumberField r0, ValueAnimator r1) {
        r0.lambda$startExitAnimation$8(r1);
    }

    /* renamed from: $r8$lambda$UomGlqr4jGp-JcdvkMj7Ervg9P4, reason: not valid java name */
    public static /* synthetic */ void m8656$r8$lambda$UomGlqr4jGpJcdvkMj7Ervg9P4(CodeNumberField r0, float r1) {
        r0.successProgress = r1;
        if (r0.getParent() == null) goto L6;
        ((View) r0.getParent()).invalidate();
        return;
    }

    /* renamed from: $r8$lambda$XQm0Un1gX-rJzRog9t989rgHlYA, reason: not valid java name */
    public static /* synthetic */ void m8657$r8$lambda$XQm0Un1gXrJzRog9t989rgHlYA(CodeNumberField r0, float r1) {
        r0.successScaleProgress = r1;
        if (r0.getParent() == null) goto L6;
        ((View) r0.getParent()).invalidate();
        return;
    }

    public static /* synthetic */ void $r8$lambda$yAojmcoirI2xCZuXpcbh6MB49Ow(CodeNumberField r0, ValueAnimator r1) {
        r0.lambda$startEnterAnimation$9(r1);
    }

    /* renamed from: -$$Nest$mpasteFromClipboard, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8658$$Nest$mpasteFromClipboard(CodeNumberField r0) {
        r0.pasteFromClipboard();
    }

    static {
        FOCUSED_PROGRESS = new SimpleFloatPropertyCompat("focusedProgress", new CodeNumberField$$ExternalSyntheticLambda2(), new CodeNumberField$$ExternalSyntheticLambda3()).setMultiplier(100.0f);
        ERROR_PROGRESS = new SimpleFloatPropertyCompat("errorProgress", new CodeNumberField$$ExternalSyntheticLambda4(), new CodeNumberField$$ExternalSyntheticLambda5()).setMultiplier(100.0f);
        SUCCESS_PROGRESS = new SimpleFloatPropertyCompat("successProgress", new CodeNumberField$$ExternalSyntheticLambda6(), new CodeNumberField$$ExternalSyntheticLambda7()).setMultiplier(100.0f);
        SUCCESS_SCALE_PROGRESS = new SimpleFloatPropertyCompat("successScaleProgress", new CodeNumberField$$ExternalSyntheticLambda8(), new CodeNumberField$$ExternalSyntheticLambda9()).setMultiplier(100.0f);
    }

    public CodeNumberField(Context r3) {
        super(r3);
        this.successScaleProgress = 1.0f;
        this.focusedSpringAnimation = new SpringAnimation(this, FOCUSED_PROGRESS);
        this.errorSpringAnimation = new SpringAnimation(this, ERROR_PROGRESS);
        this.successSpringAnimation = new SpringAnimation(this, SUCCESS_PROGRESS);
        this.successScaleSpringAnimation = new SpringAnimation(this, SUCCESS_SCALE_PROGRESS);
        this.showSoftInputOnFocusInternal = true;
        this.enterAnimation = 1.0f;
        this.exitAnimation = 1.0f;
        this.pressed = false;
        this.startX = 0.0f;
        this.startY = 0.0f;
        setBackground(null);
        setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        setMovementMethod(null);
        addTextChangedListener(new AnonymousClass1(this));
    }

    private void animateSpring(SpringAnimation r2, float r3) {
        if (r2.getSpring() != null) goto L5;
    L7:
        r2.cancel();
        r2.setSpring(new SpringForce(r3).setStiffness(400.0f).setDampingRatio(1.0f).setFinalPosition(r3)).start();
        return;
    L5:
        if (r3 != r2.getSpring().getFinalPosition()) goto L7;
    }

    private /* synthetic */ void lambda$startEnterAnimation$9(ValueAnimator r1) {
        this.enterAnimation = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
        if (getParent() == null) goto L6;
        ((ViewGroup) getParent()).invalidate();
        return;
    }

    private /* synthetic */ void lambda$startExitAnimation$8(ValueAnimator r1) {
        this.exitAnimation = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
        if (getParent() == null) goto L6;
        ((ViewGroup) getParent()).invalidate();
        return;
    }

    private void pasteFromClipboard() {
        if ((getParent() instanceof CodeFieldContainer) == false) goto L5;
        CodeFieldContainer r0 = (CodeFieldContainer) getParent();
    L6:
        if (r0 == null) goto L22;
        ClipboardManager r2 = (ClipboardManager) ContextCompat.getSystemService(getContext(), ClipboardManager.class);
        if (r2 == null) goto L24;
        ClipData r22 = r2.getPrimaryClip();
        if (r22 == null) goto L25;
        String r23 = r22.getItemAt(0).getText().toString();
        int r1 = Integer.parseInt(r23);     // Catch: Exception -> L16
    L17:
        if (r1 <= 0) goto L23;
        r0.setText(r23, true);
        return;
    L23:
        return;
    L16:
        r1 = -1;
        goto L17
    L25:
        return;
    L24:
        return;
    L22:
        return;
    L5:
        r0 = null;
        goto L6
    }

    public void animateErrorProgress(float r3) {
        animateSpring(this.errorSpringAnimation, r3 * 100.0f);
    }

    public void animateFocusedProgress(float r3) {
        animateSpring(this.focusedSpringAnimation, r3 * 100.0f);
    }

    public void animateSuccessProgress(float r4) {
        animateSpring(this.successSpringAnimation, r4 * 100.0f);
        this.successScaleSpringAnimation.cancel();
        if (r4 == 0.0f) goto L6;
        this.successScaleSpringAnimation.setSpring(new SpringForce(1.0f).setStiffness(500.0f).setDampingRatio(0.75f).setFinalPosition(100.0f)).setStartValue(100.0f).setStartVelocity(4000.0f).start();
        return;
    L6:
        this.successScaleProgress = 1.0f;
    }

    public float getErrorProgress() {
        return this.errorProgress;
    }

    public float getFocusedProgress() {
        return this.focusedProgress;
    }

    public float getSuccessProgress() {
        return this.successProgress;
    }

    public float getSuccessScaleProgress() {
        return this.successScaleProgress;
    }

    @Override // org.telegram.ui.Components.EditTextBoldCursor, org.telegram.ui.Components.EditTextEffects, com.exteragram.messenger.components.ReceiveContentEditText, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.focusedSpringAnimation.cancel();
        this.errorSpringAnimation.cancel();
    }

    @Override // org.telegram.ui.Components.EditTextBoldCursor, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
    public void onFocusChanged(boolean r1, int r2, Rect r3) {
        super.onFocusChanged(r1, r2, r3);
        if (isFocused() == true) goto L6;
        hideActionMode();
        return;
    }

    @Override // org.telegram.ui.Components.EditTextBoldCursor, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent r4) {
        if (r4.getAction() != 0) goto L6;
        this.pressed = true;
        this.startX = r4.getX();
        this.startY = r4.getY();
    L6:
        if (r4.getAction() == 1) goto L10;
        if (r4.getAction() == 3) goto L10;
    L47:
        return this.pressed;
    L10:
        if ((getParent() instanceof CodeFieldContainer) == false) goto L12;
        CodeFieldContainer r0 = (CodeFieldContainer) getParent();
    L14:
        if (r4.getAction() == 1) goto L16;
    L45:
        this.pressed = false;
        goto L47
    L16:
        if (this.pressed == false) goto L45;
        if (isFocused() == false) goto L41;
        if (r0 == null) goto L41;
        ClipboardManager r42 = (ClipboardManager) ContextCompat.getSystemService(getContext(), ClipboardManager.class);
        if (r42 != null) goto L23;
    L40:
        return false;
    L23:
        if (r42.getPrimaryClipDescription() == null) goto L40;
        ClipDescription r02 = r42.getPrimaryClipDescription();
        if (r02 != null) goto L28;
        return false;
    L28:
        r02.hasMimeType("text/plain");
        ClipData.Item r43 = r42.getPrimaryClip().getItemAt(0);
        if (r43 != null) goto L31;
    L34:
        String r44 = _UrlKt.FRAGMENT_ENCODE_SET;
    L48:
        int r45 = Integer.parseInt(r44);     // Catch: Exception -> L37
    L38:
        if (r45 <= 0) goto L42;
        startActionMode(new AnonymousClass2(this));
    L42:
        setSelection(0);
        if (this.showSoftInputOnFocusInternal == false) goto L45;
        AndroidUtilities.showKeyboard(this);
    L37:
        r45 = -1;
        goto L38
    L31:
        if (r43.getText() == null) goto L34;
        r44 = r43.getText().toString();
    L41:
        requestFocus();
        goto L42
    L12:
        r0 = null;
        goto L14
    }

    @Override // android.view.View
    public boolean requestFocus(int r2, Rect r3) {
        ((ViewGroup) getParent()).invalidate();
        return super.requestFocus(r2, r3);
    }

    public void setShowSoftInputOnFocusCompat(boolean r1) {
        this.showSoftInputOnFocusInternal = r1;
        setShowSoftInputOnFocus(r1);
    }

    public void startEnterAnimation(boolean r4) {
        this.replaceAnimation = r4;
        this.enterAnimation = 0.0f;
        ValueAnimator r42 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.enterAnimator = r42;
        r42.addUpdateListener(new CodeNumberField$$ExternalSyntheticLambda1(this));
        boolean r43 = this.replaceAnimation;
        ValueAnimator r0 = this.enterAnimator;
        if (r43 == true) goto L5;
        r0.setInterpolator(new OvershootInterpolator(1.5f));
        this.enterAnimator.setDuration(350);
    L6:
        this.enterAnimator.start();
        return;
    L5:
        r0.setDuration(220);
        goto L6
    }

    public void startExitAnimation() {
        if (getMeasuredHeight() != 0) goto L5;
        return;
    L5:
        if (getMeasuredWidth() != 0) goto L7;
        return;
    L7:
        if (getLayout() == null) goto L23;
        Bitmap r0 = this.exitBitmap;
        if (r0 != null) goto L12;
    L15:
        Bitmap r02 = this.exitBitmap;
        if (r02 == null) goto L18;
        r02.recycle();
    L18:
        this.exitBitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        this.exitCanvas = new Canvas(this.exitBitmap);
    L19:
        this.exitBitmap.eraseColor(0);
        StaticLayout r3 = new StaticLayout(getTransformationMethod().getTransformation(getText(), this), getLayout().getPaint(), (int) Math.ceil(getLayout().getPaint().measureText(r4, 0, r4.length())), Layout.Alignment.ALIGN_NORMAL, getLineSpacingMultiplier(), getLineSpacingExtra(), getIncludeFontPadding());
        this.exitCanvas.save();
        this.exitCanvas.translate((getMeasuredWidth() - r3.getWidth()) / 2.0f, (getMeasuredHeight() - r3.getHeight()) / 2.0f);
        r3.draw(this.exitCanvas);
        this.exitCanvas.restore();
        this.exitAnimation = 0.0f;
        ValueAnimator r03 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.exitAnimator = r03;
        r03.addUpdateListener(new CodeNumberField$$ExternalSyntheticLambda0(this));
        this.exitAnimator.setDuration(220);
        this.exitAnimator.start();
        return;
    L12:
        if (r0.getHeight() != getMeasuredHeight()) goto L15;
        if (this.exitBitmap.getWidth() == getMeasuredWidth()) goto L19;
    }
}
