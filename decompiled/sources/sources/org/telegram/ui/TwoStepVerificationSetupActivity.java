package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.Property;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.graphics.ColorUtils;
import com.google.android.gms.cast.CastStatusCodes;
import java.util.ArrayList;
import java.util.Iterator;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SRPHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.CustomPhoneKeyboardView;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.FragmentFloatingButton;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.OutlineTextContainerView;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Components.RadialProgressView;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.TextStyleSpan;
import org.telegram.ui.Components.TransformableLoginButtonView;
import org.telegram.ui.Components.VerticalPositionAutoAnimator;
import org.telegram.ui.Components.spoilers.SpoilersTextView;

/* loaded from: classes6.dex */
public class TwoStepVerificationSetupActivity extends BaseFragment {
    private AnimatorSet actionBarAnimator;
    private View actionBarBackground;
    private RLottieDrawable[] animationDrawables;
    private TextView bottomSkipButton;
    private AnimatorSet buttonAnimation;
    private TextView buttonTextView;
    private boolean closeAfterSet;
    private CodeFieldContainer codeFieldContainer;
    private TL_account.Password currentPassword;
    private byte[] currentPasswordHash;
    private byte[] currentSecret;
    private long currentSecretId;
    private int currentType;
    private TextView descriptionText;
    private TextView descriptionText2;
    private TextView descriptionText3;
    private boolean doneAfterPasswordLoad;
    private EditTextBoldCursor editTextFirstRow;
    private EditTextBoldCursor editTextSecondRow;
    private String email;
    private String emailCode;
    private int emailCodeLength;
    private boolean emailOnly;
    private Runnable errorColorTimeout;
    private Runnable finishCallback;
    private String firstPassword;
    private VerticalPositionAutoAnimator floatingAutoAnimator;
    private FragmentFloatingButton floatingButton;
    private TransformableLoginButtonView floatingButtonIcon;
    private ArrayList<BaseFragment> fragmentsToClose;
    private boolean fromRegistration;
    private String hint;
    private boolean ignoreTextChange;
    private RLottieImageView imageView;
    private boolean isPasswordVisible;
    private CustomPhoneKeyboardView keyboardView;
    private Runnable monkeyEndCallback;
    private boolean needPasswordButton;
    private Runnable openedSettings;
    private int otherwiseReloginDays;
    private OutlineTextContainerView outlineTextFirstRow;
    private OutlineTextContainerView outlineTextSecondRow;
    private boolean paused;
    private boolean postedErrorColorTimeout;
    private RadialProgressView radialProgressView;
    private ScrollView scrollView;
    private Runnable setAnimationRunnable;
    private ImageView showPasswordButton;
    private TextView titleTextView;
    private boolean waitingForEmail;

    /* renamed from: org.telegram.ui.TwoStepVerificationSetupActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ TwoStepVerificationSetupActivity this$0;

        public static /* synthetic */ void $r8$lambda$eC01uoX4arnGCtIidUX5Ft6uNOE(AnonymousClass1 r0, AlertDialog r1, int r2) {
            r0.lambda$onItemClick$0(r1, r2);
        }

        public AnonymousClass1(TwoStepVerificationSetupActivity r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onItemClick$0(AlertDialog r1, int r2) {
            TwoStepVerificationSetupActivity.m21089$$Nest$msetNewPassword(this.this$0, true);
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r5) {
            if (r5 == (-1)) goto L5;
            if (r5 != 1) goto L23;
            AlertDialog.Builder r52 = new AlertDialog.Builder(this.this$0.getParentActivity());
            if (TwoStepVerificationSetupActivity.m21069$$Nest$fgetcurrentPassword(this.this$0) != null) goto L16;
        L18:
            String r0 = LocaleController.getString(R.string.CancelPasswordQuestion);
        L19:
            String r2 = LocaleController.getString(R.string.CancelEmailQuestionTitle);
            String r3 = LocaleController.getString(R.string.Abort);
            r52.setMessage(r0);
            r52.setTitle(r2);
            r52.setPositiveButton(r3, new TwoStepVerificationSetupActivity$1$$ExternalSyntheticLambda0(this));
            r52.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
            AlertDialog r53 = r52.create();
            this.this$0.showDialog(r53);
            TextView r4 = (TextView) r53.getButton(-1);
            if (r4 == null) goto L24;
            r4.setTextColor(Theme.getColor(Theme.key_text_RedBold));
            return;
        L24:
            return;
        L16:
            if (TwoStepVerificationSetupActivity.m21069$$Nest$fgetcurrentPassword(this.this$0).has_password == false) goto L18;
            r0 = LocaleController.getString(R.string.CancelEmailQuestion);
            goto L19
        L23:
            return;
        L5:
            if (TwoStepVerificationSetupActivity.m21079$$Nest$fgetotherwiseReloginDays(this.this$0) >= 0) goto L7;
        L10:
            this.this$0.finishFragment();
            return;
        L7:
            if (TwoStepVerificationSetupActivity.access$000(this.this$0).getFragmentStack().size() != 1) goto L10;
            TwoStepVerificationSetupActivity.m21091$$Nest$mshowSetForcePasswordAlert(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.TwoStepVerificationSetupActivity$10, reason: invalid class name */
    public class AnonymousClass10 extends CodeFieldContainer {
        final /* synthetic */ TwoStepVerificationSetupActivity this$0;

        public AnonymousClass10(TwoStepVerificationSetupActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.CodeFieldContainer
        public void processNextPressed() {
            TwoStepVerificationSetupActivity.m21088$$Nest$mprocessNext(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.TwoStepVerificationSetupActivity$11, reason: invalid class name */
    public class AnonymousClass11 implements TextWatcher {
        final /* synthetic */ TwoStepVerificationSetupActivity this$0;

        public AnonymousClass11(TwoStepVerificationSetupActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r1) {
            if (TwoStepVerificationSetupActivity.m21080$$Nest$fgetpostedErrorColorTimeout(this.this$0) == false) goto L6;
            AndroidUtilities.cancelRunOnUIThread(TwoStepVerificationSetupActivity.m21074$$Nest$fgeterrorColorTimeout(this.this$0));
            TwoStepVerificationSetupActivity.m21074$$Nest$fgeterrorColorTimeout(this.this$0).run();
            return;
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.TwoStepVerificationSetupActivity$12, reason: invalid class name */
    public class AnonymousClass12 extends View {
        private Paint paint;
        final /* synthetic */ TwoStepVerificationSetupActivity this$0;

        public AnonymousClass12(TwoStepVerificationSetupActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.paint = new Paint();
        }

        @Override // android.view.View
        public void onDraw(Canvas r9) {
            this.paint.setColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            int r0 = getMeasuredHeight() - AndroidUtilities.dp(3.0f);
            r9.drawRect(0.0f, 0.0f, getMeasuredWidth(), r0, this.paint);
            TwoStepVerificationSetupActivity.access$1100(this.this$0).drawHeaderShadow(r9, r0);
        }
    }

    /* renamed from: org.telegram.ui.TwoStepVerificationSetupActivity$13, reason: invalid class name */
    public class AnonymousClass13 implements TextWatcher {
        final /* synthetic */ TwoStepVerificationSetupActivity this$0;

        public AnonymousClass13(TwoStepVerificationSetupActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r9) {
            if (TwoStepVerificationSetupActivity.m21075$$Nest$fgetignoreTextChange(this.this$0) == true) goto L61;
            int r0 = TwoStepVerificationSetupActivity.m21070$$Nest$fgetcurrentType(this.this$0);
            TwoStepVerificationSetupActivity r1 = this.this$0;
            if (r0 != 0) goto L40;
            RLottieDrawable r92 = TwoStepVerificationSetupActivity.m21076$$Nest$fgetimageView(r1).getAnimatedDrawable();
            int r02 = TwoStepVerificationSetupActivity.m21073$$Nest$fgeteditTextFirstRow(this.this$0).length();
            TwoStepVerificationSetupActivity r12 = this.this$0;
            if (r02 <= 0) goto L28;
            TransformationMethod r03 = TwoStepVerificationSetupActivity.m21073$$Nest$fgeteditTextFirstRow(r12).getTransformationMethod();
            TwoStepVerificationSetupActivity r13 = this.this$0;
            if (r03 != null) goto L18;
            if (r92 != TwoStepVerificationSetupActivity.m21066$$Nest$fgetanimationDrawables(r13)[3]) goto L14;
            return;
        L14:
            if (r92 == TwoStepVerificationSetupActivity.m21066$$Nest$fgetanimationDrawables(this.this$0)[5]) goto L56;
            TwoStepVerificationSetupActivity.m21076$$Nest$fgetimageView(this.this$0).setAnimation(TwoStepVerificationSetupActivity.m21066$$Nest$fgetanimationDrawables(this.this$0)[5]);
            TwoStepVerificationSetupActivity.m21066$$Nest$fgetanimationDrawables(this.this$0)[5].setProgress(0.0f, false);
            TwoStepVerificationSetupActivity.m21076$$Nest$fgetimageView(this.this$0).playAnimation();
            return;
        L56:
            return;
        L18:
            if (r92 == TwoStepVerificationSetupActivity.m21066$$Nest$fgetanimationDrawables(r13)[3]) goto L57;
            RLottieDrawable r04 = TwoStepVerificationSetupActivity.m21066$$Nest$fgetanimationDrawables(this.this$0)[2];
            TwoStepVerificationSetupActivity r14 = this.this$0;
            if (r92 == r04) goto L24;
            TwoStepVerificationSetupActivity.m21076$$Nest$fgetimageView(r14).setAnimation(TwoStepVerificationSetupActivity.m21066$$Nest$fgetanimationDrawables(this.this$0)[2]);
            TwoStepVerificationSetupActivity.m21066$$Nest$fgetanimationDrawables(this.this$0)[2].setCustomEndFrame(49);
            TwoStepVerificationSetupActivity.m21066$$Nest$fgetanimationDrawables(this.this$0)[2].setProgress(0.0f, false);
            TwoStepVerificationSetupActivity.m21076$$Nest$fgetimageView(this.this$0).playAnimation();
            return;
        L24:
            if (TwoStepVerificationSetupActivity.m21066$$Nest$fgetanimationDrawables(r14)[2].getCurrentFrame() >= 49) goto L58;
            TwoStepVerificationSetupActivity.m21066$$Nest$fgetanimationDrawables(this.this$0)[2].setCustomEndFrame(49);
            return;
        L58:
            return;
        L57:
            return;
        L28:
            if (r92 != TwoStepVerificationSetupActivity.m21066$$Nest$fgetanimationDrawables(r12)[3]) goto L32;
            if (TwoStepVerificationSetupActivity.m21073$$Nest$fgeteditTextFirstRow(this.this$0).getTransformationMethod() != null) goto L32;
        L33:
            TwoStepVerificationSetupActivity.m21076$$Nest$fgetimageView(this.this$0).setAnimation(TwoStepVerificationSetupActivity.m21066$$Nest$fgetanimationDrawables(this.this$0)[4]);
            TwoStepVerificationSetupActivity.m21066$$Nest$fgetanimationDrawables(this.this$0)[4].setProgress(0.0f, false);
            TwoStepVerificationSetupActivity.m21076$$Nest$fgetimageView(this.this$0).playAnimation();
            return;
        L32:
            if (r92 == TwoStepVerificationSetupActivity.m21066$$Nest$fgetanimationDrawables(this.this$0)[5]) goto L33;
            TwoStepVerificationSetupActivity.m21066$$Nest$fgetanimationDrawables(this.this$0)[2].setCustomEndFrame(-1);
            if (r92 == TwoStepVerificationSetupActivity.m21066$$Nest$fgetanimationDrawables(this.this$0)[2]) goto L38;
            TwoStepVerificationSetupActivity.m21076$$Nest$fgetimageView(this.this$0).setAnimation(TwoStepVerificationSetupActivity.m21066$$Nest$fgetanimationDrawables(this.this$0)[2]);
            TwoStepVerificationSetupActivity.m21066$$Nest$fgetanimationDrawables(this.this$0)[2].setCurrentFrame(49, false);
        L38:
            TwoStepVerificationSetupActivity.m21076$$Nest$fgetimageView(this.this$0).playAnimation();
            return;
        L40:
            int r05 = TwoStepVerificationSetupActivity.m21070$$Nest$fgetcurrentType(r1);
            TwoStepVerificationSetupActivity r15 = this.this$0;
            if (r05 != 1) goto L48;
            TwoStepVerificationSetupActivity.m21066$$Nest$fgetanimationDrawables(this.this$0)[6].setCustomEndFrame((int) ((Math.min(1.0f, TwoStepVerificationSetupActivity.m21073$$Nest$fgeteditTextFirstRow(r15).getLayout().getLineWidth(0) / TwoStepVerificationSetupActivity.m21073$$Nest$fgeteditTextFirstRow(this.this$0).getWidth()) * 142.0f) + 18.0f));     // Catch: Exception -> L44
            TwoStepVerificationSetupActivity.m21076$$Nest$fgetimageView(this.this$0).playAnimation();     // Catch: Exception -> L44
            return;
        L44:
            e = move-exception;
            FileLog.e(e);
            return;
        L48:
            if (TwoStepVerificationSetupActivity.m21070$$Nest$fgetcurrentType(r15) == 8) goto L50;
            return;
        L50:
            if (r9.length() <= 0) goto L60;
            TwoStepVerificationSetupActivity.m21090$$Nest$mshowDoneButton(this.this$0, true);
            return;
        L60:
            return;
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.TwoStepVerificationSetupActivity$14, reason: invalid class name */
    public class AnonymousClass14 extends AnimatorListenerAdapter {
        final /* synthetic */ TwoStepVerificationSetupActivity this$0;
        final /* synthetic */ boolean val$show;

        public AnonymousClass14(TwoStepVerificationSetupActivity r1, boolean r2) {
            this.this$0 = r1;
            this.val$show = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r2) {
            if (TwoStepVerificationSetupActivity.m21067$$Nest$fgetbuttonAnimation(this.this$0) != null) goto L5;
            return;
        L5:
            if (TwoStepVerificationSetupActivity.m21067$$Nest$fgetbuttonAnimation(this.this$0).equals(r2) == false) goto L9;
            TwoStepVerificationSetupActivity.m21085$$Nest$fputbuttonAnimation(this.this$0, null);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (TwoStepVerificationSetupActivity.m21067$$Nest$fgetbuttonAnimation(this.this$0) != null) goto L5;
            return;
        L5:
            if (TwoStepVerificationSetupActivity.m21067$$Nest$fgetbuttonAnimation(this.this$0).equals(r2) == false) goto L13;
            boolean r22 = this.val$show;
            TwoStepVerificationSetupActivity r1 = this.this$0;
            if (r22 == false) goto L10;
            TwoStepVerificationSetupActivity.m21072$$Nest$fgetdescriptionText2(r1).setVisibility(4);
            return;
        L10:
            TwoStepVerificationSetupActivity.m21068$$Nest$fgetbuttonTextView(r1).setVisibility(4);
            return;
        }
    }

    /* renamed from: org.telegram.ui.TwoStepVerificationSetupActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends ViewGroup {
        final /* synthetic */ TwoStepVerificationSetupActivity this$0;

        public AnonymousClass2(TwoStepVerificationSetupActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onLayout(boolean r4, int r5, int r6, int r7, int r8) {
            TwoStepVerificationSetupActivity.access$300(this.this$0).layout(0, 0, r7, TwoStepVerificationSetupActivity.access$200(this.this$0).getMeasuredHeight());
            int r42 = r7 - r5;
            int r52 = r8 - r6;
            TwoStepVerificationSetupActivity r62 = this.this$0;
            if (r7 <= r8) goto L6;
            int r63 = (r52 - TwoStepVerificationSetupActivity.m21076$$Nest$fgetimageView(r62).getMeasuredHeight()) / 2;
            TwoStepVerificationSetupActivity.m21076$$Nest$fgetimageView(this.this$0).layout(0, r63, TwoStepVerificationSetupActivity.m21076$$Nest$fgetimageView(this.this$0).getMeasuredWidth(), TwoStepVerificationSetupActivity.m21076$$Nest$fgetimageView(this.this$0).getMeasuredHeight() + r63);
            float r43 = r42;
            float r64 = 0.4f * r43;
            int r72 = (int) r64;
            float r53 = r52;
            int r82 = (int) (0.22f * r53);
            TwoStepVerificationSetupActivity.m21083$$Nest$fgettitleTextView(this.this$0).layout(r72, r82, TwoStepVerificationSetupActivity.m21083$$Nest$fgettitleTextView(this.this$0).getMeasuredWidth() + r72, TwoStepVerificationSetupActivity.m21083$$Nest$fgettitleTextView(this.this$0).getMeasuredHeight() + r82);
            int r83 = (int) (0.39f * r53);
            TwoStepVerificationSetupActivity.m21071$$Nest$fgetdescriptionText(this.this$0).layout(r72, r83, TwoStepVerificationSetupActivity.m21071$$Nest$fgetdescriptionText(this.this$0).getMeasuredWidth() + r72, TwoStepVerificationSetupActivity.m21071$$Nest$fgetdescriptionText(this.this$0).getMeasuredHeight() + r83);
            int r44 = (int) (r64 + (((r43 * 0.6f) - TwoStepVerificationSetupActivity.m21068$$Nest$fgetbuttonTextView(this.this$0).getMeasuredWidth()) / 2.0f));
            int r54 = (int) (r53 * 0.64f);
            TwoStepVerificationSetupActivity.m21068$$Nest$fgetbuttonTextView(this.this$0).layout(r44, r54, TwoStepVerificationSetupActivity.m21068$$Nest$fgetbuttonTextView(this.this$0).getMeasuredWidth() + r44, TwoStepVerificationSetupActivity.m21068$$Nest$fgetbuttonTextView(this.this$0).getMeasuredHeight() + r54);
            return;
        L6:
            int r73 = (int) (r52 * 0.3f);
            int r65 = (r42 - TwoStepVerificationSetupActivity.m21076$$Nest$fgetimageView(r62).getMeasuredWidth()) / 2;
            TwoStepVerificationSetupActivity.m21076$$Nest$fgetimageView(this.this$0).layout(r65, r73, TwoStepVerificationSetupActivity.m21076$$Nest$fgetimageView(this.this$0).getMeasuredWidth() + r65, TwoStepVerificationSetupActivity.m21076$$Nest$fgetimageView(this.this$0).getMeasuredHeight() + r73);
            int r74 = r73 + (TwoStepVerificationSetupActivity.m21076$$Nest$fgetimageView(this.this$0).getMeasuredHeight() + AndroidUtilities.dp(16.0f));
            TwoStepVerificationSetupActivity.m21083$$Nest$fgettitleTextView(this.this$0).layout(0, r74, TwoStepVerificationSetupActivity.m21083$$Nest$fgettitleTextView(this.this$0).getMeasuredWidth(), TwoStepVerificationSetupActivity.m21083$$Nest$fgettitleTextView(this.this$0).getMeasuredHeight() + r74);
            int r75 = r74 + (TwoStepVerificationSetupActivity.m21083$$Nest$fgettitleTextView(this.this$0).getMeasuredHeight() + AndroidUtilities.dp(12.0f));
            TwoStepVerificationSetupActivity.m21071$$Nest$fgetdescriptionText(this.this$0).layout(0, r75, TwoStepVerificationSetupActivity.m21071$$Nest$fgetdescriptionText(this.this$0).getMeasuredWidth(), TwoStepVerificationSetupActivity.m21071$$Nest$fgetdescriptionText(this.this$0).getMeasuredHeight() + r75);
            int r45 = (r42 - TwoStepVerificationSetupActivity.m21068$$Nest$fgetbuttonTextView(this.this$0).getMeasuredWidth()) / 2;
            int r55 = (r52 - TwoStepVerificationSetupActivity.m21068$$Nest$fgetbuttonTextView(this.this$0).getMeasuredHeight()) - AndroidUtilities.dp(48.0f);
            TwoStepVerificationSetupActivity.m21068$$Nest$fgetbuttonTextView(this.this$0).layout(r45, r55, TwoStepVerificationSetupActivity.m21068$$Nest$fgetbuttonTextView(this.this$0).getMeasuredWidth() + r45, TwoStepVerificationSetupActivity.m21068$$Nest$fgetbuttonTextView(this.this$0).getMeasuredHeight() + r55);
        }

        @Override // android.view.View
        public void onMeasure(int r8, int r9) {
            int r82 = View.MeasureSpec.getSize(r8);
            int r0 = View.MeasureSpec.getSize(r9);
            TwoStepVerificationSetupActivity.access$100(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r82, TLObject.FLAG_30), r9);
            TwoStepVerificationSetupActivity r92 = this.this$0;
            if (r82 <= r0) goto L6;
            float r3 = r82;
            TwoStepVerificationSetupActivity.m21076$$Nest$fgetimageView(r92).measure(View.MeasureSpec.makeMeasureSpec((int) (0.45f * r3), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec((int) (r0 * 0.68f), TLObject.FLAG_30));
            int r32 = (int) (r3 * 0.6f);
            TwoStepVerificationSetupActivity.m21083$$Nest$fgettitleTextView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r32, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r0, 0));
            TwoStepVerificationSetupActivity.m21071$$Nest$fgetdescriptionText(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r32, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r0, 0));
            TwoStepVerificationSetupActivity.m21072$$Nest$fgetdescriptionText2(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r32, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r0, 0));
            TwoStepVerificationSetupActivity.m21068$$Nest$fgetbuttonTextView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r32, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(42.0f), TLObject.FLAG_30));
        L10:
            setMeasuredDimension(r82, r0);
            return;
        L6:
            if (TwoStepVerificationSetupActivity.m21070$$Nest$fgetcurrentType(r92) != 7) goto L8;
            int r93 = 160;
        L9:
            float r94 = r93;
            TwoStepVerificationSetupActivity.m21076$$Nest$fgetimageView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(r94), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(r94), TLObject.FLAG_30));
            TwoStepVerificationSetupActivity.m21083$$Nest$fgettitleTextView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r82, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r0, 0));
            TwoStepVerificationSetupActivity.m21071$$Nest$fgetdescriptionText(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r82, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r0, 0));
            TwoStepVerificationSetupActivity.m21072$$Nest$fgetdescriptionText2(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r82, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r0, 0));
            TwoStepVerificationSetupActivity.m21068$$Nest$fgetbuttonTextView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r82 - AndroidUtilities.dp(48.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(50.0f), TLObject.FLAG_30));
            goto L10
        L8:
            r93 = 140;
            goto L9
        }
    }

    /* renamed from: org.telegram.ui.TwoStepVerificationSetupActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends FrameLayout {
        final /* synthetic */ TwoStepVerificationSetupActivity this$0;

        public AnonymousClass3(TwoStepVerificationSetupActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, r2);
            ViewGroup.MarginLayoutParams r0 = (ViewGroup.MarginLayoutParams) TwoStepVerificationSetupActivity.m21081$$Nest$fgetradialProgressView(this.this$0).getLayoutParams();
            r0.topMargin = AndroidUtilities.statusBarHeight + AndroidUtilities.dp(16.0f);
        }
    }

    /* renamed from: org.telegram.ui.TwoStepVerificationSetupActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends SizeNotifierFrameLayout {
        final /* synthetic */ TwoStepVerificationSetupActivity this$0;
        final /* synthetic */ FrameLayout val$frameLayout;

        public AnonymousClass4(TwoStepVerificationSetupActivity r1, Context r2, FrameLayout r3) {
            this.this$0 = r1;
            this.val$frameLayout = r3;
            super(r2);
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r2, int r3, int r4, int r5, int r6) {
            if (TwoStepVerificationSetupActivity.m21077$$Nest$fgetkeyboardView(this.this$0).getVisibility() != 8) goto L5;
        L10:
            int r22 = TwoStepVerificationSetupActivity.m21077$$Nest$fgetkeyboardView(this.this$0).getVisibility();
            FrameLayout r62 = this.val$frameLayout;
            if (r22 == 8) goto L14;
            int r23 = getMeasuredWidth();
            int r52 = getMeasuredHeight() - AndroidUtilities.dp(230.0f);
            r62.layout(0, 0, r23, r52);
        L13:
            int r63 = r52;
        L15:
            TwoStepVerificationSetupActivity.m21077$$Nest$fgetkeyboardView(this.this$0).layout(0, r63, getMeasuredWidth(), AndroidUtilities.dp(230.0f) + r63);
            return;
        L14:
            int r24 = getMeasuredWidth();
            r52 = getMeasuredHeight();
            r62.layout(0, 0, r24, r52);
            goto L13
        L5:
            if (measureKeyboardHeight() < AndroidUtilities.dp(20.0f)) goto L10;
            boolean r25 = TwoStepVerificationSetupActivity.m21086$$Nest$misCustomKeyboardVisible(this.this$0);
            FrameLayout r53 = this.val$frameLayout;
            if (r25 == false) goto L9;
            int r26 = getMeasuredWidth();
            r63 = (getMeasuredHeight() - AndroidUtilities.dp(230.0f)) + measureKeyboardHeight();
            r53.layout(0, 0, r26, r63);
            goto L15
        L9:
            int r27 = getMeasuredWidth();
            r63 = getMeasuredHeight();
            r53.layout(0, 0, r27, r63);
            goto L15
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r5, int r6) {
            int r52 = View.MeasureSpec.getSize(r5);
            int r62 = View.MeasureSpec.getSize(r6);
            setMeasuredDimension(r52, r62);
            if (TwoStepVerificationSetupActivity.m21077$$Nest$fgetkeyboardView(this.this$0).getVisibility() != 8) goto L5;
        L7:
            this.val$frameLayout.measure(View.MeasureSpec.makeMeasureSpec(r52, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r62, TLObject.FLAG_30));
            TwoStepVerificationSetupActivity.m21077$$Nest$fgetkeyboardView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r52, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(230.0f), TLObject.FLAG_30));
            return;
        L5:
            if (measureKeyboardHeight() >= AndroidUtilities.dp(20.0f)) goto L7;
            r62 = r62 - AndroidUtilities.dp(230.0f);
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.TwoStepVerificationSetupActivity$5, reason: invalid class name */
    public class AnonymousClass5 extends ViewGroup {
        final /* synthetic */ TwoStepVerificationSetupActivity this$0;
        final /* synthetic */ SizeNotifierFrameLayout val$keyboardFrameLayout;

        public AnonymousClass5(TwoStepVerificationSetupActivity r1, Context r2, SizeNotifierFrameLayout r3) {
            this.this$0 = r1;
            this.val$keyboardFrameLayout = r3;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            TwoStepVerificationSetupActivity.access$800(this.this$0).layout(0, 0, TwoStepVerificationSetupActivity.access$600(this.this$0).getMeasuredWidth(), TwoStepVerificationSetupActivity.access$700(this.this$0).getMeasuredHeight());
            TwoStepVerificationSetupActivity.m21065$$Nest$fgetactionBarBackground(this.this$0).layout(0, 0, TwoStepVerificationSetupActivity.m21065$$Nest$fgetactionBarBackground(this.this$0).getMeasuredWidth(), TwoStepVerificationSetupActivity.m21065$$Nest$fgetactionBarBackground(this.this$0).getMeasuredHeight());
            SizeNotifierFrameLayout r12 = this.val$keyboardFrameLayout;
            r12.layout(0, 0, r12.getMeasuredWidth(), this.val$keyboardFrameLayout.getMeasuredHeight());
        }

        @Override // android.view.View
        public void onMeasure(int r7, int r8) {
            int r72 = View.MeasureSpec.getSize(r7);
            int r0 = View.MeasureSpec.getSize(r8);
            TwoStepVerificationSetupActivity.access$400(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r72, TLObject.FLAG_30), r8);
            TwoStepVerificationSetupActivity.m21065$$Nest$fgetactionBarBackground(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r72, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(TwoStepVerificationSetupActivity.access$500(this.this$0).getMeasuredHeight() + AndroidUtilities.dp(3.0f), TLObject.FLAG_30));
            this.val$keyboardFrameLayout.measure(View.MeasureSpec.makeMeasureSpec(r72, TLObject.FLAG_30), r8);
            setMeasuredDimension(r72, r0);
        }
    }

    /* renamed from: org.telegram.ui.TwoStepVerificationSetupActivity$6, reason: invalid class name */
    public class AnonymousClass6 extends ScrollView {
        private boolean isLayoutDirty;
        private int[] location;
        private int scrollingUp;
        private Rect tempRect;
        final /* synthetic */ TwoStepVerificationSetupActivity this$0;

        /* renamed from: org.telegram.ui.TwoStepVerificationSetupActivity$6$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass6 this$1;

            public AnonymousClass1(AnonymousClass6 r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                if (r2.equals(TwoStepVerificationSetupActivity.m21064$$Nest$fgetactionBarAnimator(this.this$1.this$0)) == false) goto L6;
                TwoStepVerificationSetupActivity.m21084$$Nest$fputactionBarAnimator(this.this$1.this$0, null);
                return;
            }
        }

        public AnonymousClass6(TwoStepVerificationSetupActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.location = new int[2];
            this.tempRect = new Rect();
            this.isLayoutDirty = true;
        }

        @Override // android.widget.ScrollView, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r2, int r3, int r4, int r5, int r6) {
            this.isLayoutDirty = false;
            super.onLayout(r2, r3, r4, r5, r6);
        }

        @Override // android.view.View
        public void onScrollChanged(int r6, int r7, int r8, int r9) {
            super.onScrollChanged(r6, r7, r8, r9);
            if (TwoStepVerificationSetupActivity.m21083$$Nest$fgettitleTextView(this.this$0) == null) goto L31;
            TwoStepVerificationSetupActivity.m21083$$Nest$fgettitleTextView(this.this$0).getLocationOnScreen(this.location);
            if ((this.location[1] + TwoStepVerificationSetupActivity.m21083$$Nest$fgettitleTextView(this.this$0).getMeasuredHeight()) >= TwoStepVerificationSetupActivity.access$900(this.this$0).getBottom()) goto L8;
            boolean r62 = true;
        L10:
            if (TwoStepVerificationSetupActivity.m21083$$Nest$fgettitleTextView(this.this$0).getTag() != null) goto L12;
            boolean r82 = true;
        L13:
            if (r62 == r82) goto L30;
            TextView r83 = TwoStepVerificationSetupActivity.m21083$$Nest$fgettitleTextView(this.this$0);
            if (r62 == false) goto L17;
            Integer r1 = null;
        L18:
            r83.setTag(r1);
            if (TwoStepVerificationSetupActivity.m21064$$Nest$fgetactionBarAnimator(this.this$0) == null) goto L21;
            TwoStepVerificationSetupActivity.m21064$$Nest$fgetactionBarAnimator(this.this$0).cancel();
            TwoStepVerificationSetupActivity.m21084$$Nest$fputactionBarAnimator(this.this$0, null);
        L21:
            TwoStepVerificationSetupActivity.m21084$$Nest$fputactionBarAnimator(this.this$0, new AnimatorSet());
            AnimatorSet r84 = TwoStepVerificationSetupActivity.m21064$$Nest$fgetactionBarAnimator(this.this$0);
            View r0 = TwoStepVerificationSetupActivity.m21065$$Nest$fgetactionBarBackground(this.this$0);
            float r12 = 0.0f;
            if (r62 == false) goto L24;
            float r3 = 1.0f;
        L25:
            float[] r4 = {r3};
            Property r32 = View.ALPHA;
            ObjectAnimator r02 = ObjectAnimator.ofFloat(r0, r32, r4);
            SimpleTextView r42 = TwoStepVerificationSetupActivity.access$1000(this.this$0).getTitleTextView();
            if (r62 == false) goto L28;
            r12 = 1.0f;
        L28:
            r84.playTogether(new Animator[]{r02, ObjectAnimator.ofFloat(r42, r32, new float[]{r12})});
            TwoStepVerificationSetupActivity.m21064$$Nest$fgetactionBarAnimator(this.this$0).setDuration(150);
            TwoStepVerificationSetupActivity.m21064$$Nest$fgetactionBarAnimator(this.this$0).addListener(new AnonymousClass1(this));
            TwoStepVerificationSetupActivity.m21064$$Nest$fgetactionBarAnimator(this.this$0).start();
            return;
        L24:
            r3 = 0.0f;
            goto L25
        L17:
            r1 = 1;
            goto L18
        L30:
            return;
        L12:
            r82 = false;
            goto L13
        L8:
            r62 = false;
            goto L10
        }

        @Override // android.widget.ScrollView, android.view.ViewGroup, android.view.ViewParent
        public void requestChildFocus(View r3, View r4) {
            if (Build.VERSION.SDK_INT >= 29) goto L8;
            if (r4 == null) goto L8;
            if (this.isLayoutDirty == true) goto L8;
            scrollToDescendant(r4);
        L8:
            super.requestChildFocus(r3, r4);
        }

        @Override // android.widget.ScrollView, android.view.ViewGroup, android.view.ViewParent
        public boolean requestChildRectangleOnScreen(View r1, Rect r2, boolean r3) {
            return super.requestChildRectangleOnScreen(r1, r2, r3);
        }

        @Override // android.widget.ScrollView, android.view.View, android.view.ViewParent
        public void requestLayout() {
            this.isLayoutDirty = true;
            super.requestLayout();
        }

        @Override // android.widget.ScrollView
        public void scrollToDescendant(View r4) {
            r4.getDrawingRect(this.tempRect);
            offsetDescendantRectToMyCoords(r4, this.tempRect);
            this.tempRect.bottom += AndroidUtilities.dp(120.0f);
            int r0 = computeScrollDeltaToGetChildRectOnScreen(this.tempRect);
            if (r0 >= 0) goto L5;
            int r2 = (getMeasuredHeight() - r4.getMeasuredHeight()) / 2;
            this.scrollingUp = r2;
            r0 = r0 - r2;
        L6:
            if (r0 == 0) goto L9;
            smoothScrollBy(0, r0);
            return;
        L9:
            return;
        L5:
            this.scrollingUp = 0;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.TwoStepVerificationSetupActivity$7, reason: invalid class name */
    public class AnonymousClass7 extends LinearLayout {
        final /* synthetic */ TwoStepVerificationSetupActivity this$0;

        public AnonymousClass7(TwoStepVerificationSetupActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r4, int r5) {
            super.onMeasure(r4, r5);
            ViewGroup.MarginLayoutParams r42 = (ViewGroup.MarginLayoutParams) TwoStepVerificationSetupActivity.m21083$$Nest$fgettitleTextView(this.this$0).getLayoutParams();
            int r1 = 0;
            if (TwoStepVerificationSetupActivity.m21076$$Nest$fgetimageView(this.this$0).getVisibility() != 8) goto L5;
            int r52 = AndroidUtilities.statusBarHeight;
        L6:
            int r53 = r52 + AndroidUtilities.dp(8.0f);
            if (TwoStepVerificationSetupActivity.m21070$$Nest$fgetcurrentType(this.this$0) == 2) goto L9;
        L13:
            r42.topMargin = r53 + r1;
            return;
        L9:
            if (AndroidUtilities.isSmallScreen() == false) goto L13;
            if (TwoStepVerificationSetupActivity.m21087$$Nest$misLandscape(this.this$0) == true) goto L13;
            r1 = AndroidUtilities.dp(32.0f);
            goto L13
        L5:
            r52 = 0;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.TwoStepVerificationSetupActivity$8, reason: invalid class name */
    public class AnonymousClass8 extends ImageView {
        final /* synthetic */ TwoStepVerificationSetupActivity this$0;

        public AnonymousClass8(TwoStepVerificationSetupActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r2) {
            super.onInitializeAccessibilityNodeInfo(r2);
            boolean r0 = true;
            r2.setCheckable(true);
            if (TwoStepVerificationSetupActivity.m21073$$Nest$fgeteditTextFirstRow(this.this$0).getTransformationMethod() == null) goto L6;
            r0 = false;
        L6:
            r2.setChecked(r0);
        }
    }

    /* renamed from: org.telegram.ui.TwoStepVerificationSetupActivity$9, reason: invalid class name */
    public class AnonymousClass9 implements TextWatcher {
        final /* synthetic */ TwoStepVerificationSetupActivity this$0;

        public AnonymousClass9(TwoStepVerificationSetupActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r5) {
            if (TwoStepVerificationSetupActivity.m21078$$Nest$fgetneedPasswordButton(this.this$0) == true) goto L5;
            return;
        L5:
            if (TwoStepVerificationSetupActivity.m21082$$Nest$fgetshowPasswordButton(this.this$0).getVisibility() == 0) goto L11;
            if (TextUtils.isEmpty(r5) == true) goto L11;
            AndroidUtilities.updateViewVisibilityAnimated(TwoStepVerificationSetupActivity.m21082$$Nest$fgetshowPasswordButton(this.this$0), true, 0.1f, true);
            return;
        L11:
            if (TwoStepVerificationSetupActivity.m21082$$Nest$fgetshowPasswordButton(this.this$0).getVisibility() != 8) goto L13;
            return;
        L13:
            if (TextUtils.isEmpty(r5) == false) goto L18;
            AndroidUtilities.updateViewVisibilityAnimated(TwoStepVerificationSetupActivity.m21082$$Nest$fgetshowPasswordButton(this.this$0), false, 0.1f, true);
            return;
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: $r8$lambda$0N14BsnqMsJCPCXA5gr2i-EtbjY, reason: not valid java name */
    public static /* synthetic */ void m21044$r8$lambda$0N14BsnqMsJCPCXA5gr2iEtbjY(TwoStepVerificationSetupActivity r0, boolean r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$setNewPassword$46(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$11Sv7BWLpwt0Xof1Fmg0LUF6tmw(TwoStepVerificationSetupActivity r0, View r1, boolean r2) {
        r0.lambda$createView$16(r1, r2);
    }

    /* renamed from: $r8$lambda$24LBL1NM48t4cg-aXSv818qmxD0, reason: not valid java name */
    public static /* synthetic */ void m21045$r8$lambda$24LBL1NM48t4cgaXSv818qmxD0(TwoStepVerificationSetupActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$processNext$25(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$2KN__XCezRcS2XilJDp1GZ_AjJE(TwoStepVerificationSetupActivity r0) {
        r0.lambda$onCodeFieldError$36();
    }

    /* renamed from: $r8$lambda$3FpguIHiY6mSWmL5XCZpd1bs-N8, reason: not valid java name */
    public static /* synthetic */ void m21046$r8$lambda$3FpguIHiY6mSWmL5XCZpd1bsN8(TwoStepVerificationSetupActivity r0) {
        r0.lambda$processNext$33();
    }

    /* renamed from: $r8$lambda$66ivlZ6IuMrD1a5BN9Q-iM4Je18, reason: not valid java name */
    public static /* synthetic */ void m21047$r8$lambda$66ivlZ6IuMrD1a5BN9QiM4Je18(TwoStepVerificationSetupActivity r0, View r1) {
        r0.lambda$createView$2(r1);
    }

    /* renamed from: $r8$lambda$6COUzTH1A2so8M-uLNyyo1ikubs, reason: not valid java name */
    public static /* synthetic */ void m21048$r8$lambda$6COUzTH1A2so8MuLNyyo1ikubs(TwoStepVerificationSetupActivity r0, AlertDialog r1, int r2) {
        r0.lambda$processNext$32(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$8EAcgVXOW3DxpeKWcNc21AC2Ecg(TwoStepVerificationSetupActivity r0, TLRPC.TL_error r1, boolean r2, TLObject r3, byte[] r4, String r5, TL_account.passwordInputSettings r6) {
        r0.lambda$setNewPassword$48(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$9UHBxNNMZgzawDjnzF4x_93F3fg(CodeNumberField r1) {
        r1.animateSuccessProgress(1.0f);
    }

    public static /* synthetic */ void $r8$lambda$AAKkWNvYyTWgKm0AAgk5ugBnCEk(TwoStepVerificationSetupActivity r0, String r1) {
        r0.lambda$processNext$29(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$CS7Bz7Y73ox6nVvDZMD6bUu6JiU(TwoStepVerificationSetupActivity r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$createView$14(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$Ce99X3P4ER6GpjtGIwW7nbCluZE(TwoStepVerificationSetupActivity r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$processNext$24(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$FFsnSFCYF71M6HG02KSszMQdLAk(TwoStepVerificationSetupActivity r0, boolean r1, byte[] r2, String r3, TL_account.passwordInputSettings r4, TLObject r5, TLRPC.TL_error r6) {
        r0.lambda$setNewPassword$49(r1, r2, r3, r4, r5, r6);
    }

    /* renamed from: $r8$lambda$HgvxPGcNCI-O_Iqq1ORTD9KjV8g, reason: not valid java name */
    public static /* synthetic */ void m21049$r8$lambda$HgvxPGcNCIO_Iqq1ORTD9KjV8g(TwoStepVerificationSetupActivity r0) {
        r0.lambda$new$0();
    }

    public static /* synthetic */ void $r8$lambda$IyVeWDMBjEj23jRzRBM4KXXCsm8(TwoStepVerificationSetupActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$setNewPassword$44(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$LN8cx4pg9aRCdoYWZ4H7IvHCaTs(TwoStepVerificationSetupActivity r0, byte[] r1) {
        r0.lambda$processNext$28(r1);
    }

    /* renamed from: $r8$lambda$MHu5h62b9ftQ5A-RBcD-cSZkhiw, reason: not valid java name */
    public static /* synthetic */ void m21050$r8$lambda$MHu5h62b9ftQ5ARBcDcSZkhiw(TwoStepVerificationSetupActivity r0) {
        r0.lambda$onCodeFieldError$37();
    }

    /* renamed from: $r8$lambda$NNl-sEkzbRl7nVNVrZNDwovhni4, reason: not valid java name */
    public static /* synthetic */ void m21051$r8$lambda$NNlsEkzbRl7nVNVrZNDwovhni4(TwoStepVerificationSetupActivity r0, View r1, boolean r2) {
        r0.lambda$createView$15(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$OFVCHF2tqogTFqK3emnCww0G3lI(TwoStepVerificationSetupActivity r0, View r1) {
        r0.lambda$createView$20(r1);
    }

    /* renamed from: $r8$lambda$PQfp-CsZTWsc96CYr2pFcoB7nQw, reason: not valid java name */
    public static /* synthetic */ void m21052$r8$lambda$PQfpCsZTWsc96CYr2pFcoB7nQw(TwoStepVerificationSetupActivity r0, TLRPC.TL_error r1) {
        r0.lambda$processNext$26(r1);
    }

    public static /* synthetic */ void $r8$lambda$RVjVkZOin5ENnt10bT9FDf7yz4o(TwoStepVerificationSetupActivity r0, TLObject r1, String r2, TLRPC.TL_error r3) {
        r0.lambda$processNext$30(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$RgeRneozPDQXuRZu7x7gTu83b_I(TwoStepVerificationSetupActivity r0, View r1) {
        r0.lambda$createView$18(r1);
    }

    public static /* synthetic */ void $r8$lambda$TsY5KXrIR7kGrDSFMmR_mtPVRp8(TwoStepVerificationSetupActivity r0, View r1) {
        r0.lambda$createView$13(r1);
    }

    /* renamed from: $r8$lambda$TvQIOeehbOk-p4H63lICQN5F6rQ, reason: not valid java name */
    public static /* synthetic */ void m21053$r8$lambda$TvQIOeehbOkp4H63lICQN5F6rQ(TwoStepVerificationSetupActivity r0, TLRPC.TL_error r1) {
        r0.lambda$processNext$34(r1);
    }

    public static /* synthetic */ void $r8$lambda$UuYwXEcMH8dQTD2R5jK2ikItK7Y(TwoStepVerificationSetupActivity r0, byte[] r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$processNext$27(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$Vkv_n6aAEGxgpQ3RaTagPfYTPrU(TwoStepVerificationSetupActivity r0) {
        r0.lambda$onTransitionAnimationEnd$39();
    }

    /* renamed from: $r8$lambda$VmF7h-QWGXQ6XCI_3ldFJk7ecVQ, reason: not valid java name */
    public static /* synthetic */ void m21054$r8$lambda$VmF7hQWGXQ6XCI_3ldFJk7ecVQ(TwoStepVerificationSetupActivity r0, Runnable r1) {
        r0.lambda$animateSuccess$22(r1);
    }

    public static /* synthetic */ void $r8$lambda$WqbdLWIZ4lZ4OVdrOZ1WERyQu5M(TwoStepVerificationSetupActivity r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$loadPasswordInfo$41(r1, r2);
    }

    /* renamed from: $r8$lambda$XCNElxWCskDQ5QpjzyVrRGiX-1g, reason: not valid java name */
    public static /* synthetic */ void m21055$r8$lambda$XCNElxWCskDQ5QpjzyVrRGiX1g(TwoStepVerificationSetupActivity r0, View r1) {
        r0.lambda$createView$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$XJg6VI8a6cx4JQMMnMCIoQkDtEA(TwoStepVerificationSetupActivity r0) {
        r0.lambda$onTransitionAnimationEnd$40();
    }

    public static /* synthetic */ void $r8$lambda$Y3bXs7MfXB_pkG3iIzVY5rtYaDA(TwoStepVerificationSetupActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadPasswordInfo$42(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$b2qcI7_grDnrNytZjvrZc0DSnSs(TwoStepVerificationSetupActivity r0, AlertDialog r1, int r2) {
        r0.lambda$createView$6(r1, r2);
    }

    /* renamed from: $r8$lambda$bB24bo1H_C4spWZueg-xEN8QcRE, reason: not valid java name */
    public static /* synthetic */ void m21056$r8$lambda$bB24bo1H_C4spWZuegxEN8QcRE(TwoStepVerificationSetupActivity r0) {
        r0.lambda$setRandomMonkeyIdleAnimation$38();
    }

    public static /* synthetic */ void $r8$lambda$bCEZx0VNsFTlSWkyOgyiVkXJeIw(TwoStepVerificationSetupActivity r0, TLRPC.TL_error r1, TLObject r2, boolean r3) {
        r0.lambda$setNewPassword$45(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$bg7t1iWwozuXwod02GRm-lami64, reason: not valid java name */
    public static /* synthetic */ void m21057$r8$lambda$bg7t1iWwozuXwod02GRmlami64(TwoStepVerificationSetupActivity r0, AlertDialog r1, int r2) {
        r0.lambda$createView$17(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$cCxuRkrzu1c3OBlyl_i6ge0TUX0(TwoStepVerificationSetupActivity r0, byte[] r1) {
        r0.lambda$processNext$23(r1);
    }

    /* renamed from: $r8$lambda$fV6dApAMiNR-yBGNN2Z4n_RLSy0, reason: not valid java name */
    public static /* synthetic */ void m21058$r8$lambda$fV6dApAMiNRyBGNN2Z4n_RLSy0(TwoStepVerificationSetupActivity r0) {
        r0.lambda$new$1();
    }

    public static /* synthetic */ void $r8$lambda$fVTCIoC4TaWbc9Fgx01quxfgviI(TwoStepVerificationSetupActivity r0, View r1) {
        r0.lambda$createView$7(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$fxnuijeVnmI5keukH7UodhEZbkQ(View r0, MotionEvent r1) {
        return true;
    }

    /* renamed from: $r8$lambda$fyUCb8AYHFVK4_-E1Rpfob4J9Ss, reason: not valid java name */
    public static /* synthetic */ void m21059$r8$lambda$fyUCb8AYHFVK4_E1Rpfob4J9Ss(TwoStepVerificationSetupActivity r0, AlertDialog r1, int r2) {
        r0.lambda$createView$3(r1, r2);
    }

    /* renamed from: $r8$lambda$g0uM74UwX1P-56KtbHUIzAUi80M, reason: not valid java name */
    public static /* synthetic */ boolean m21060$r8$lambda$g0uM74UwX1P56KtbHUIzAUi80M(TwoStepVerificationSetupActivity r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$createView$11(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$gVO__BqAlQ6Sem_3RBc5ovz9DUI(TwoStepVerificationSetupActivity r0, View r1, boolean r2) {
        r0.lambda$createView$12(r1, r2);
    }

    /* renamed from: $r8$lambda$gamc-yqC23n4cD5cS4ud1IS0KGQ, reason: not valid java name */
    public static /* synthetic */ void m21061$r8$lambda$gamcyqC23n4cD5cS4ud1IS0KGQ(TwoStepVerificationSetupActivity r0, String r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$processNext$31(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$iH2IRPliRRoGavqpL3ABPYy9sVQ(TwoStepVerificationSetupActivity r0, View r1) {
        r0.lambda$createView$9(r1);
    }

    /* renamed from: $r8$lambda$naA-x62nkPfgaXd8Nj5qDcZ2b_w, reason: not valid java name */
    public static /* synthetic */ void m21062$r8$lambda$naAx62nkPfgaXd8Nj5qDcZ2b_w(TLObject r0, TLRPC.TL_error r1) {
    }

    public static /* synthetic */ void $r8$lambda$njp8c4Y0lBIPoAgjlyur6k1SWZ0(TwoStepVerificationSetupActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$createView$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$r3ibXJiUrtP2XPSPX6Wv9mB7KcU(TwoStepVerificationSetupActivity r0, TLObject r1, boolean r2, String r3, TL_account.passwordInputSettings r4) {
        r0.lambda$setNewPassword$50(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$s8yXC0yX4wU_L8GipmI66Wt1_X4(TwoStepVerificationSetupActivity r0, TLRPC.TL_error r1) {
        r0.lambda$setNewPassword$43(r1);
    }

    public static /* synthetic */ void $r8$lambda$svcn_cN6WF9Pt7CicJtth6KfoFE(TwoStepVerificationSetupActivity r0, byte[] r1, AlertDialog r2, int r3) {
        r0.lambda$setNewPassword$47(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$t77bMAWGQgvLap-VwwfNBfrfqWU, reason: not valid java name */
    public static /* synthetic */ void m21063$r8$lambda$t77bMAWGQgvLapVwwfNBfrfqWU(TwoStepVerificationSetupActivity r0, TLRPC.TL_error r1) {
        r0.lambda$createView$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$uejxJt16GG3ZHSuY1fh_fbB8jcA(TwoStepVerificationSetupActivity r0, AlertDialog r1, int r2) {
        r0.lambda$showSetForcePasswordAlert$51(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$xH5HjkoY9yw8o7ugJXEhbMZwS54(TwoStepVerificationSetupActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$processNext$35(r1, r2);
    }

    /* renamed from: -$$Nest$fgetactionBarAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m21064$$Nest$fgetactionBarAnimator(TwoStepVerificationSetupActivity r0) {
        return r0.actionBarAnimator;
    }

    /* renamed from: -$$Nest$fgetactionBarBackground, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m21065$$Nest$fgetactionBarBackground(TwoStepVerificationSetupActivity r0) {
        return r0.actionBarBackground;
    }

    /* renamed from: -$$Nest$fgetanimationDrawables, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RLottieDrawable[] m21066$$Nest$fgetanimationDrawables(TwoStepVerificationSetupActivity r0) {
        return r0.animationDrawables;
    }

    /* renamed from: -$$Nest$fgetbuttonAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m21067$$Nest$fgetbuttonAnimation(TwoStepVerificationSetupActivity r0) {
        return r0.buttonAnimation;
    }

    /* renamed from: -$$Nest$fgetbuttonTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m21068$$Nest$fgetbuttonTextView(TwoStepVerificationSetupActivity r0) {
        return r0.buttonTextView;
    }

    /* renamed from: -$$Nest$fgetcurrentPassword, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TL_account.Password m21069$$Nest$fgetcurrentPassword(TwoStepVerificationSetupActivity r0) {
        return r0.currentPassword;
    }

    /* renamed from: -$$Nest$fgetcurrentType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21070$$Nest$fgetcurrentType(TwoStepVerificationSetupActivity r0) {
        return r0.currentType;
    }

    /* renamed from: -$$Nest$fgetdescriptionText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m21071$$Nest$fgetdescriptionText(TwoStepVerificationSetupActivity r0) {
        return r0.descriptionText;
    }

    /* renamed from: -$$Nest$fgetdescriptionText2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m21072$$Nest$fgetdescriptionText2(TwoStepVerificationSetupActivity r0) {
        return r0.descriptionText2;
    }

    /* renamed from: -$$Nest$fgeteditTextFirstRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditTextBoldCursor m21073$$Nest$fgeteditTextFirstRow(TwoStepVerificationSetupActivity r0) {
        return r0.editTextFirstRow;
    }

    /* renamed from: -$$Nest$fgeterrorColorTimeout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m21074$$Nest$fgeterrorColorTimeout(TwoStepVerificationSetupActivity r0) {
        return r0.errorColorTimeout;
    }

    /* renamed from: -$$Nest$fgetignoreTextChange, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21075$$Nest$fgetignoreTextChange(TwoStepVerificationSetupActivity r0) {
        return r0.ignoreTextChange;
    }

    /* renamed from: -$$Nest$fgetimageView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RLottieImageView m21076$$Nest$fgetimageView(TwoStepVerificationSetupActivity r0) {
        return r0.imageView;
    }

    /* renamed from: -$$Nest$fgetkeyboardView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CustomPhoneKeyboardView m21077$$Nest$fgetkeyboardView(TwoStepVerificationSetupActivity r0) {
        return r0.keyboardView;
    }

    /* renamed from: -$$Nest$fgetneedPasswordButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21078$$Nest$fgetneedPasswordButton(TwoStepVerificationSetupActivity r0) {
        return r0.needPasswordButton;
    }

    /* renamed from: -$$Nest$fgetotherwiseReloginDays, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21079$$Nest$fgetotherwiseReloginDays(TwoStepVerificationSetupActivity r0) {
        return r0.otherwiseReloginDays;
    }

    /* renamed from: -$$Nest$fgetpostedErrorColorTimeout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21080$$Nest$fgetpostedErrorColorTimeout(TwoStepVerificationSetupActivity r0) {
        return r0.postedErrorColorTimeout;
    }

    /* renamed from: -$$Nest$fgetradialProgressView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RadialProgressView m21081$$Nest$fgetradialProgressView(TwoStepVerificationSetupActivity r0) {
        return r0.radialProgressView;
    }

    /* renamed from: -$$Nest$fgetshowPasswordButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageView m21082$$Nest$fgetshowPasswordButton(TwoStepVerificationSetupActivity r0) {
        return r0.showPasswordButton;
    }

    /* renamed from: -$$Nest$fgettitleTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m21083$$Nest$fgettitleTextView(TwoStepVerificationSetupActivity r0) {
        return r0.titleTextView;
    }

    /* renamed from: -$$Nest$fputactionBarAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21084$$Nest$fputactionBarAnimator(TwoStepVerificationSetupActivity r0, AnimatorSet r1) {
        r0.actionBarAnimator = r1;
    }

    /* renamed from: -$$Nest$fputbuttonAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21085$$Nest$fputbuttonAnimation(TwoStepVerificationSetupActivity r0, AnimatorSet r1) {
        r0.buttonAnimation = r1;
    }

    /* renamed from: -$$Nest$misCustomKeyboardVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21086$$Nest$misCustomKeyboardVisible(TwoStepVerificationSetupActivity r0) {
        return r0.isCustomKeyboardVisible();
    }

    /* renamed from: -$$Nest$misLandscape, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21087$$Nest$misLandscape(TwoStepVerificationSetupActivity r0) {
        return r0.isLandscape();
    }

    /* renamed from: -$$Nest$mprocessNext, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21088$$Nest$mprocessNext(TwoStepVerificationSetupActivity r0) {
        r0.processNext();
    }

    /* renamed from: -$$Nest$msetNewPassword, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21089$$Nest$msetNewPassword(TwoStepVerificationSetupActivity r0, boolean r1) {
        r0.setNewPassword(r1);
    }

    /* renamed from: -$$Nest$mshowDoneButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21090$$Nest$mshowDoneButton(TwoStepVerificationSetupActivity r0, boolean r1) {
        r0.showDoneButton(r1);
    }

    /* renamed from: -$$Nest$mshowSetForcePasswordAlert, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21091$$Nest$mshowSetForcePasswordAlert(TwoStepVerificationSetupActivity r0) {
        r0.showSetForcePasswordAlert();
    }

    public TwoStepVerificationSetupActivity(int r3, int r4, TL_account.Password r5) {
        this.needPasswordButton = false;
        this.otherwiseReloginDays = -1;
        this.fragmentsToClose = new ArrayList();
        this.emailCodeLength = 6;
        this.currentPasswordHash = new byte[0];
        this.errorColorTimeout = new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda16(this);
        this.finishCallback = new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda17(this);
        this.currentAccount = r3;
        this.currentType = r4;
        this.currentPassword = r5;
        this.waitingForEmail = !TextUtils.isEmpty(r5.email_unconfirmed_pattern);
        if (this.currentPassword != null) goto L10;
        int r32 = this.currentType;
        if (r32 != 6) goto L7;
    L8:
        loadPasswordInfo();
        return;
    L7:
        if (r32 == 8) goto L8;
        return;
    }

    public TwoStepVerificationSetupActivity(int r3, TL_account.Password r4) {
        this.needPasswordButton = false;
        this.otherwiseReloginDays = -1;
        this.fragmentsToClose = new ArrayList();
        this.emailCodeLength = 6;
        this.currentPasswordHash = new byte[0];
        this.errorColorTimeout = new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda16(this);
        this.finishCallback = new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda17(this);
        this.currentType = r3;
        this.currentPassword = r4;
        if (r4 != null) goto L9;
        if (r3 != 6) goto L6;
    L7:
        loadPasswordInfo();
        return;
    L6:
        if (r3 == 8) goto L7;
    L9:
        this.waitingForEmail = !TextUtils.isEmpty(r4.email_unconfirmed_pattern);
    }

    public static /* synthetic */ INavigationLayout access$000(TwoStepVerificationSetupActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ ActionBar access$100(TwoStepVerificationSetupActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$1000(TwoStepVerificationSetupActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ INavigationLayout access$1100(TwoStepVerificationSetupActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ ActionBar access$200(TwoStepVerificationSetupActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$300(TwoStepVerificationSetupActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$400(TwoStepVerificationSetupActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$500(TwoStepVerificationSetupActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$600(TwoStepVerificationSetupActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$700(TwoStepVerificationSetupActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$800(TwoStepVerificationSetupActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$900(TwoStepVerificationSetupActivity r0) {
        return r0.actionBar;
    }

    private void animateSuccess(Runnable r9) {
        int r0 = 0;
    L3:
        CodeFieldContainer r1 = this.codeFieldContainer;
        CodeNumberField[] r2 = r1.codeField;
        if (r0 >= r2.length) goto L6;
        CodeNumberField r12 = r2[r0];
        r12.postDelayed(new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda44(r12), r0 * 75);
        r0 = r0 + 1;
        goto L3
    L6:
        r1.postDelayed(new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda45(this, r9), (this.codeFieldContainer.codeField.length * 75) + 350);
    }

    private boolean isCustomKeyboardVisible() {
        int r1 = this.currentType;
        if (r1 == 5) goto L7;
        if (r1 == 4) goto L7;
        return false;
    L7:
        if (AndroidUtilities.isTablet() == true) goto L16;
        Point r12 = AndroidUtilities.displaySize;
        if (r12.x < r12.y) goto L11;
        return false;
    L11:
        if (AndroidUtilities.isAccessibilityTouchExplorationEnabled() == true) goto L18;
        return true;
    L18:
        return false;
    L16:
        return false;
    }

    private boolean isIntro() {
        int r1 = this.currentType;
        if (r1 != 6) goto L5;
        return true;
    L5:
        if (r1 != 9) goto L7;
        return true;
    L7:
        if (r1 == 7) goto L14;
        return false;
    L14:
        return true;
    }

    private boolean isLandscape() {
        Point r1 = AndroidUtilities.displaySize;
        if (r1.x <= r1.y) goto L6;
        return true;
    L6:
        return false;
    }

    private boolean isValidEmail(String r3) {
        if (r3 != null) goto L5;
        return false;
    L5:
        if (r3.length() < 3) goto L15;
        int r0 = r3.lastIndexOf(46);
        int r32 = r3.lastIndexOf(64);
        if (r32 < 0) goto L13;
        if (r0 < r32) goto L14;
        return true;
    L14:
        return false;
    L13:
        return false;
    L15:
        return false;
    }

    private /* synthetic */ void lambda$animateSuccess$22(Runnable r5) {
        CodeNumberField[] r4 = this.codeFieldContainer.codeField;
        int r0 = r4.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r4[r1].animateSuccessProgress(0.0f);
        r1 = r1 + 1;
        goto L3
    L5:
        r5.run();
    }

    private /* synthetic */ boolean lambda$createView$11(TextView r1, int r2, KeyEvent r3) {
        if (r2 == 5) goto L10;
        if (r2 == 6) goto L10;
        return false;
    L10:
        if (this.outlineTextSecondRow.getVisibility() != 0) goto L13;
        this.editTextSecondRow.requestFocus();
        return true;
    L13:
        processNext();
        return true;
    }

    private /* synthetic */ void lambda$createView$12(View r1, boolean r2) {
        OutlineTextContainerView r0 = this.outlineTextFirstRow;
        if (r2 == false) goto L5;
        float r12 = 1.0f;
    L6:
        r0.animateSelection(r12);
        return;
    L5:
        r12 = 0.0f;
        goto L6
    }

    private /* synthetic */ void lambda$createView$13(View r7) {
        this.ignoreTextChange = true;
        if (this.editTextFirstRow.getTransformationMethod() != null) goto L16;
        this.isPasswordVisible = false;
        this.editTextFirstRow.setTransformationMethod(PasswordTransformationMethod.getInstance());
        this.showPasswordButton.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chat_messagePanelIcons), PorterDuff.Mode.MULTIPLY));
        if (this.currentType == 0) goto L7;
    L28:
        EditTextBoldCursor r72 = this.editTextFirstRow;
        r72.setSelection(r72.length());
        this.ignoreTextChange = false;
        return;
    L7:
        if (this.editTextFirstRow.length() <= 0) goto L28;
        if (this.editTextFirstRow.hasFocus() == false) goto L28;
        if (this.monkeyEndCallback != null) goto L28;
        this.animationDrawables[3].setCustomEndFrame(-1);
        RLottieDrawable r73 = this.imageView.getAnimatedDrawable();
        RLottieDrawable r0 = this.animationDrawables[3];
        if (r73 == r0) goto L15;
        this.imageView.setAnimation(r0);
        this.animationDrawables[3].setCurrentFrame(18, false);
    L15:
        this.imageView.playAnimation();
        goto L28
    L16:
        this.isPasswordVisible = true;
        this.editTextFirstRow.setTransformationMethod(null);
        this.showPasswordButton.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chat_messagePanelSend), PorterDuff.Mode.MULTIPLY));
        if (this.currentType != 0) goto L28;
        if (this.editTextFirstRow.length() <= 0) goto L28;
        if (this.editTextFirstRow.hasFocus() == false) goto L28;
        if (this.monkeyEndCallback != null) goto L28;
        this.animationDrawables[3].setCustomEndFrame(18);
        RLottieDrawable r74 = this.imageView.getAnimatedDrawable();
        RLottieDrawable r02 = this.animationDrawables[3];
        if (r74 == r02) goto L27;
        this.imageView.setAnimation(r02);
    L27:
        this.animationDrawables[3].setProgress(0.0f, false);
        this.imageView.playAnimation();
        goto L28
    }

    private /* synthetic */ boolean lambda$createView$14(TextView r1, int r2, KeyEvent r3) {
        if (r2 != 5) goto L5;
    L9:
        processNext();
        return true;
    L5:
        if (r2 == 6) goto L9;
        return false;
    }

    private /* synthetic */ void lambda$createView$15(View r1, boolean r2) {
        OutlineTextContainerView r0 = this.outlineTextSecondRow;
        if (r2 == false) goto L5;
        float r12 = 1.0f;
    L6:
        r0.animateSelection(r12);
        return;
    L5:
        r12 = 0.0f;
        goto L6
    }

    private /* synthetic */ void lambda$createView$16(View r1, boolean r2) {
        if (r2 == false) goto L5;
        this.keyboardView.setEditText((EditText) r1);
        this.keyboardView.setDispatchBackWhenEmpty(true);
        return;
    }

    private /* synthetic */ void lambda$createView$17(AlertDialog r1, int r2) {
        onReset();
        finishFragment();
    }

    private /* synthetic */ void lambda$createView$18(View r3) {
        AlertDialog.Builder r32 = new AlertDialog.Builder(getParentActivity());
        r32.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r32.setPositiveButton(LocaleController.getString(R.string.Reset), new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda19(this));
        r32.setTitle(LocaleController.getString(R.string.ResetPassword));
        r32.setMessage(LocaleController.getString(R.string.RestoreEmailTroubleText2));
        showDialog(r32.create());
    }

    private /* synthetic */ void lambda$createView$2(View r1) {
        processNext();
    }

    private /* synthetic */ void lambda$createView$20(View r3) {
        TL_account.resendPasswordEmail r32 = new TL_account.resendPasswordEmail();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r32, new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda18());
        showDialog(new AlertDialog.Builder(getParentActivity()).setMessage(LocaleController.getString(R.string.ResendCodeInfo)).setTitle(LocaleController.getString(R.string.TwoStepVerificationTitle)).setPositiveButton(LocaleController.getString(R.string.OK), null).create());
    }

    private /* synthetic */ void lambda$createView$3(AlertDialog r3, int r4) {
        int r32 = this.fragmentsToClose.size();
        int r0 = 0;
    L3:
        if (r0 >= r32) goto L5;
        this.fragmentsToClose.get(r0).removeSelfFromStack();
        r0 = r0 + 1;
        goto L3
    L5:
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.twoStepPasswordChanged, new Object[0]);
        finishFragment();
    }

    private /* synthetic */ void lambda$createView$4(TLRPC.TL_error r5) {
        needHideProgress();
        if (r5 != null) goto L9;
        getMessagesController().removeSuggestion(0, "VALIDATE_PASSWORD");
        AlertDialog.Builder r52 = new AlertDialog.Builder(getParentActivity());
        r52.setPositiveButton(LocaleController.getString(R.string.OK), new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda37(this));
        r52.setMessage(LocaleController.getString(R.string.PasswordReset));
        r52.setTitle(LocaleController.getString(R.string.TwoStepVerificationTitle));
        Dialog r4 = showDialog(r52.create());
        if (r4 == null) goto L18;
        r4.setCanceledOnTouchOutside(false);
        r4.setCancelable(false);
        return;
    L18:
        return;
    L9:
        if (r5.text.startsWith("FLOOD_WAIT") == false) goto L16;
        int r53 = Utilities.parseInt(r5.text);
        if (r53 >= 60) goto L13;
        String r54 = LocaleController.formatPluralString("Seconds", r53, new Object[0]);
    L14:
        showAlertWithText(LocaleController.getString(R.string.TwoStepVerificationTitle), LocaleController.formatString("FloodWaitTime", R.string.FloodWaitTime, new Object[]{r54}));
        return;
    L13:
        r54 = LocaleController.formatPluralString("Minutes", r53 / 60, new Object[0]);
        goto L14
    L16:
        showAlertWithText(LocaleController.getString(R.string.TwoStepVerificationTitle), r5.text);
    }

    private /* synthetic */ void lambda$createView$5(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda28(this, r2));
    }

    private /* synthetic */ void lambda$createView$6(AlertDialog r1, int r2) {
        this.email = _UrlKt.FRAGMENT_ENCODE_SET;
        setNewPassword(false);
    }

    private /* synthetic */ void lambda$createView$7(View r3) {
        if (this.bottomSkipButton.getAlpha() < 0.5f) goto L21;
        int r32 = this.currentType;
        if (r32 != 0) goto L10;
        needShowProgress();
        TLRPC.TL_auth_recoverPassword r33 = new TLRPC.TL_auth_recoverPassword();
        r33.code = this.emailCode;
        getConnectionsManager().sendRequest(r33, new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda21(this));
        return;
    L10:
        if (r32 != 3) goto L16;
        AlertDialog.Builder r34 = new AlertDialog.Builder(getParentActivity());
        r34.setMessage(LocaleController.getString(R.string.YourEmailSkipWarningText));
        r34.setTitle(LocaleController.getString(R.string.YourEmailSkipWarning));
        r34.setPositiveButton(LocaleController.getString(R.string.YourEmailSkip), new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda22(this));
        r34.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        AlertDialog r35 = r34.create();
        showDialog(r35);
        TextView r2 = (TextView) r35.getButton(-1);
        if (r2 == null) goto L19;
        r2.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    L19:
        return;
    L16:
        if (r32 != 2) goto L20;
        onHintDone();
        return;
    L20:
        return;
    }

    private /* synthetic */ void lambda$createView$8(View r2) {
        if (this.currentType != 8) goto L8;
        TwoStepVerificationActivity r22 = new TwoStepVerificationActivity();
        r22.setForgotPasswordOnShow();
        r22.setPassword(this.currentPassword);
        r22.setBlockingAlert(this.otherwiseReloginDays);
        presentFragment(r22, true);
        Runnable r23 = this.openedSettings;
        if (r23 == null) goto L9;
        AndroidUtilities.runOnUIThread(r23);
        this.openedSettings = null;
        return;
    L9:
        return;
    }

    private /* synthetic */ void lambda$createView$9(View r1) {
        processNext();
    }

    private /* synthetic */ void lambda$loadPasswordInfo$41(TLRPC.TL_error r9, TLObject r10) {
        if (r9 != null) goto L31;
        TL_account.Password r102 = (TL_account.Password) r10;
        this.currentPassword = r102;
        if (TwoStepVerificationActivity.canHandleCurrentPassword(r102, false) == true) goto L7;
        AlertsCreator.showUpdateAppAlert(getParentActivity(), LocaleController.getString(R.string.UpdateAppAlert), true);
        return;
    L7:
        this.waitingForEmail = !TextUtils.isEmpty(this.currentPassword.email_unconfirmed_pattern);
        TwoStepVerificationActivity.initPasswordNewAlgo(this.currentPassword);
        if (this.paused == true) goto L27;
        if (this.closeAfterSet == false) goto L27;
        TL_account.Password r92 = this.currentPassword;
        if (r92.has_password == false) goto L27;
        TLRPC.PasswordKdfAlgo r1 = r92.current_algo;
        TLRPC.SecurePasswordKdfAlgo r2 = r92.new_secure_algo;
        byte[] r3 = r92.secure_random;
        if (r92.has_recovery == false) goto L17;
        String r103 = "1";
    L16:
        String r4 = r103;
        String r93 = r92.hint;
        if (r93 == null) goto L21;
    L20:
        String r5 = r93;
        if (this.waitingForEmail == true) goto L27;
        if (r1 == null) goto L27;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.twoStepPasswordChanged, new Object[]{null, r1, r2, r3, r4, r5, null, null});
        finishFragment();
        goto L27
    L21:
        r93 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L20
    L17:
        r103 = null;
    L27:
        if (this.doneAfterPasswordLoad == false) goto L29;
        needHideProgress();
        processNext();
    L29:
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.didSetOrRemoveTwoStepPassword, new Object[]{this.currentPassword});
        return;
    }

    private /* synthetic */ void lambda$loadPasswordInfo$42(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda27(this, r3, r2));
    }

    private /* synthetic */ void lambda$new$0() {
        int r0 = 0;
        this.postedErrorColorTimeout = false;
    L3:
        CodeNumberField[] r1 = this.codeFieldContainer.codeField;
        if (r0 >= r1.length) goto L6;
        r1[r0].animateErrorProgress(0.0f);
        r0 = r0 + 1;
        goto L3
    }

    private /* synthetic */ void lambda$new$1() {
        EditTextBoldCursor r0 = this.editTextFirstRow;
        if (r0 != null) goto L6;
        return;
    L6:
        if (r0.length() == 0) goto L9;
        this.animationDrawables[2].setCustomEndFrame(49);
        this.animationDrawables[2].setProgress(0.0f, false);
        this.imageView.playAnimation();
        return;
    L9:
        setRandomMonkeyIdleAnimation(true);
    }

    private /* synthetic */ void lambda$onCodeFieldError$36() {
        CodeNumberField[] r4 = this.codeFieldContainer.codeField;
        int r0 = r4.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r4[r1].animateErrorProgress(0.0f);
        r1 = r1 + 1;
        goto L3
    }

    private /* synthetic */ void lambda$onCodeFieldError$37() {
        AndroidUtilities.runOnUIThread(new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda50(this), 150);
    }

    private /* synthetic */ void lambda$onTransitionAnimationEnd$39() {
        EditTextBoldCursor r0 = this.editTextFirstRow;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getVisibility() != 0) goto L9;
        this.editTextFirstRow.requestFocus();
        AndroidUtilities.showKeyboard(this.editTextFirstRow);
        return;
    }

    private /* synthetic */ void lambda$onTransitionAnimationEnd$40() {
        CodeFieldContainer r0 = this.codeFieldContainer;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getVisibility() != 0) goto L9;
        this.codeFieldContainer.codeField[0].requestFocus();
        return;
    }

    private /* synthetic */ void lambda$processNext$23(byte[] r4) {
        needHideProgress();
        this.currentPasswordHash = r4;
        getMessagesController().removeSuggestion(0, "VALIDATE_PASSWORD");
        TwoStepVerificationSetupActivity r42 = new TwoStepVerificationSetupActivity(9, this.currentPassword);
        r42.fromRegistration = this.fromRegistration;
        r42.setBlockingAlert(this.otherwiseReloginDays);
        presentFragment(r42, true);
    }

    private /* synthetic */ void lambda$processNext$24(TLRPC.TL_error r2, TLObject r3) {
        if (r2 != null) goto L5;
        TL_account.Password r32 = (TL_account.Password) r3;
        this.currentPassword = r32;
        TwoStepVerificationActivity.initPasswordNewAlgo(r32);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.didSetOrRemoveTwoStepPassword, new Object[]{this.currentPassword});
        processNext();
        return;
    }

    private /* synthetic */ void lambda$processNext$25(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda47(this, r3, r2));
    }

    private /* synthetic */ void lambda$processNext$26(TLRPC.TL_error r4) {
        if ("SRP_ID_INVALID".equals(r4.text) == false) goto L6;
        TL_account.getPassword r42 = new TL_account.getPassword();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r42, new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda41(this), 8);
        return;
    L6:
        needHideProgress();
        if ("PASSWORD_HASH_INVALID".equals(r4.text) == false) goto L11;
        this.descriptionText.setText(LocaleController.getString(R.string.CheckPasswordWrong));
        this.descriptionText.setTextColor(Theme.getColor(Theme.key_text_RedRegular));
        onFieldError(this.outlineTextFirstRow, this.editTextFirstRow, true);
        showDoneButton(false);
        return;
    L11:
        if (r4.text.startsWith("FLOOD_WAIT") == false) goto L18;
        int r43 = Utilities.parseInt(r4.text);
        if (r43 >= 60) goto L15;
        String r44 = LocaleController.formatPluralString("Seconds", r43, new Object[0]);
    L16:
        showAlertWithText(LocaleController.getString(R.string.AppName), LocaleController.formatString("FloodWaitTime", R.string.FloodWaitTime, new Object[]{r44}));
        return;
    L15:
        r44 = LocaleController.formatPluralString("Minutes", r43 / 60, new Object[0]);
        goto L16
    L18:
        showAlertWithText(LocaleController.getString(R.string.AppName), r4.text);
    }

    private /* synthetic */ void lambda$processNext$27(byte[] r1, TLObject r2, TLRPC.TL_error r3) {
        if (r3 != null) goto L5;
        AndroidUtilities.runOnUIThread(new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda35(this, r1));
        return;
    L5:
        AndroidUtilities.runOnUIThread(new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda36(this, r3));
    }

    private /* synthetic */ void lambda$processNext$28(byte[] r8) {
        TL_account.getPasswordSettings r0 = new TL_account.getPasswordSettings();
        TLRPC.PasswordKdfAlgo r1 = this.currentPassword.current_algo;
        if ((r1 instanceof TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow) == false) goto L5;
        byte[] r82 = SRPHelper.getX(r8, (TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow) r1);
    L6:
        TwoStepVerificationSetupActivity$$ExternalSyntheticLambda29 r12 = new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda29(this, r82);
        TL_account.Password r2 = this.currentPassword;
        TLRPC.PasswordKdfAlgo r4 = r2.current_algo;
        if ((r4 instanceof TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow) == false) goto L14;
        TLRPC.TL_inputCheckPasswordSRP r83 = SRPHelper.startCheck(r82, r2.srp_id, r2.srp_B, (TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow) r4);
        r0.password = r83;
        if (r83 != null) goto L12;
        TLRPC.TL_error r7 = new TLRPC.TL_error();
        r7.text = "ALGO_INVALID";
        r12.run(null, r7);
        return;
    L12:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, r12, 10);
        return;
    L14:
        TLRPC.TL_error r72 = new TLRPC.TL_error();
        r72.text = "PASSWORD_HASH_INVALID";
        r12.run(null, r72);
        return;
    L5:
        r82 = null;
        goto L6
    }

    private /* synthetic */ void lambda$processNext$29(String r5) {
        TwoStepVerificationSetupActivity r0 = new TwoStepVerificationSetupActivity(this.currentAccount, 0, this.currentPassword);
        r0.fromRegistration = this.fromRegistration;
        r0.fragmentsToClose.addAll(this.fragmentsToClose);
        r0.addFragmentToClose(this);
        r0.setCurrentEmailCode(r5);
        r0.setBlockingAlert(this.otherwiseReloginDays);
        presentFragment(r0, true);
    }

    private /* synthetic */ void lambda$processNext$30(TLObject r2, String r3, TLRPC.TL_error r4) {
        if ((r2 instanceof TLRPC.TL_boolTrue) == false) goto L6;
        animateSuccess(new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda38(this, r3));
        return;
    L6:
        if (r4 != null) goto L8;
    L20:
        onCodeFieldError(true);
        return;
    L8:
        if (r4.text.startsWith("CODE_INVALID") == true) goto L20;
        if (r4.text.startsWith("FLOOD_WAIT") == false) goto L18;
        int r22 = Utilities.parseInt(r4.text);
        if (r22 >= 60) goto L15;
        String r23 = LocaleController.formatPluralString("Seconds", r22, new Object[0]);
    L16:
        showAlertWithText(LocaleController.getString(R.string.TwoStepVerificationTitle), LocaleController.formatString("FloodWaitTime", R.string.FloodWaitTime, new Object[]{r23}));
        return;
    L15:
        r23 = LocaleController.formatPluralString("Minutes", r22 / 60, new Object[0]);
        goto L16
    L18:
        showAlertWithText(LocaleController.getString(R.string.TwoStepVerificationTitle), r4.text);
    }

    private /* synthetic */ void lambda$processNext$31(String r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda33(this, r3, r2, r4));
    }

    private /* synthetic */ void lambda$processNext$32(AlertDialog r9, int r10) {
        int r92 = this.fragmentsToClose.size();
        int r102 = 0;
    L3:
        if (r102 >= r92) goto L5;
        this.fragmentsToClose.get(r102).removeSelfFromStack();
        r102 = r102 + 1;
        goto L3
    L5:
        NotificationCenter r93 = NotificationCenter.getInstance(this.currentAccount);
        int r103 = NotificationCenter.twoStepPasswordChanged;
        byte[] r0 = this.currentPasswordHash;
        TL_account.Password r1 = this.currentPassword;
        r93.postNotificationName(r103, new Object[]{r0, r1.new_algo, r1.new_secure_algo, r1.secure_random, this.email, this.hint, null, this.firstPassword});
        TwoStepVerificationActivity r12 = new TwoStepVerificationActivity();
        TL_account.Password r2 = this.currentPassword;
        r2.has_password = true;
        r2.has_recovery = true;
        r2.email_unconfirmed_pattern = _UrlKt.FRAGMENT_ENCODE_SET;
        r12.setCurrentPasswordParams(r2, this.currentPasswordHash, this.currentSecretId, this.currentSecret);
        r12.setBlockingAlert(this.otherwiseReloginDays);
        presentFragment(r12, true);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.didSetOrRemoveTwoStepPassword, new Object[]{this.currentPassword});
        Runnable r94 = this.openedSettings;
        if (r94 == null) goto L10;
        AndroidUtilities.runOnUIThread(r94);
        this.openedSettings = null;
        return;
    }

    private /* synthetic */ void lambda$processNext$33() {
        int r1 = 0;
        if (this.currentPassword.has_password == false) goto L12;
        AlertDialog.Builder r0 = new AlertDialog.Builder(getParentActivity());
        r0.setPositiveButton(LocaleController.getString(R.string.OK), new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda46(this));
        if (this.currentPassword.has_recovery == false) goto L7;
        r0.setMessage(LocaleController.getString(R.string.YourEmailSuccessChangedText));
    L8:
        r0.setTitle(LocaleController.getString(R.string.YourPasswordSuccess));
        Dialog r10 = showDialog(r0.create());
        if (r10 == null) goto L18;
        r10.setCanceledOnTouchOutside(false);
        r10.setCancelable(false);
        return;
    L18:
        return;
    L7:
        r0.setMessage(LocaleController.getString(R.string.YourEmailSuccessText));
        goto L8
    L12:
        int r02 = this.fragmentsToClose.size();
    L13:
        if (r1 >= r02) goto L15;
        this.fragmentsToClose.get(r1).removeSelfFromStack();
        r1 = r1 + 1;
        goto L13
    L15:
        TL_account.Password r03 = this.currentPassword;
        r03.has_password = true;
        r03.has_recovery = true;
        r03.email_unconfirmed_pattern = _UrlKt.FRAGMENT_ENCODE_SET;
        TwoStepVerificationSetupActivity r3 = new TwoStepVerificationSetupActivity(7, r03);
        r3.fromRegistration = this.fromRegistration;
        r3.setCurrentPasswordParams(this.currentPasswordHash, this.currentSecretId, this.currentSecret, this.emailOnly);
        r3.fragmentsToClose.addAll(this.fragmentsToClose);
        r3.closeAfterSet = this.closeAfterSet;
        r3.setBlockingAlert(this.otherwiseReloginDays);
        presentFragment(r3, true);
        NotificationCenter r04 = NotificationCenter.getInstance(this.currentAccount);
        int r12 = NotificationCenter.twoStepPasswordChanged;
        byte[] r2 = this.currentPasswordHash;
        TL_account.Password r32 = this.currentPassword;
        r04.postNotificationName(r12, new Object[]{r2, r32.new_algo, r32.new_secure_algo, r32.secure_random, this.email, this.hint, null, this.firstPassword});
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.didSetOrRemoveTwoStepPassword, new Object[]{this.currentPassword});
    }

    private /* synthetic */ void lambda$processNext$34(TLRPC.TL_error r4) {
        needHideProgress();
        if (r4 != null) goto L10;
        if (getParentActivity() != null) goto L7;
        return;
    L7:
        animateSuccess(new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda39(this));
        return;
    L10:
        if (r4.text.startsWith("CODE_INVALID") == false) goto L14;
        onCodeFieldError(true);
        return;
    L14:
        if (r4.text.startsWith("FLOOD_WAIT") == false) goto L21;
        int r42 = Utilities.parseInt(r4.text);
        if (r42 >= 60) goto L18;
        String r43 = LocaleController.formatPluralString("Seconds", r42, new Object[0]);
    L19:
        showAlertWithText(LocaleController.getString(R.string.AppName), LocaleController.formatString("FloodWaitTime", R.string.FloodWaitTime, new Object[]{r43}));
        return;
    L18:
        r43 = LocaleController.formatPluralString("Minutes", r42 / 60, new Object[0]);
        goto L19
    L21:
        showAlertWithText(LocaleController.getString(R.string.AppName), r4.text);
    }

    private /* synthetic */ void lambda$processNext$35(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda32(this, r2));
    }

    private /* synthetic */ void lambda$setNewPassword$43(TLRPC.TL_error r7) {
        needHideProgress();
        if (r7 != null) goto L8;
        TwoStepVerificationActivity r0 = new TwoStepVerificationActivity();
        TL_account.Password r1 = this.currentPassword;
        r1.has_recovery = false;
        r1.email_unconfirmed_pattern = _UrlKt.FRAGMENT_ENCODE_SET;
        r0.setCurrentPasswordParams(r1, this.currentPasswordHash, this.currentSecretId, this.currentSecret);
        r0.setBlockingAlert(this.otherwiseReloginDays);
        presentFragment(r0, true);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.didRemoveTwoStepPassword, new Object[0]);
        Runnable r72 = this.openedSettings;
        if (r72 == null) goto L9;
        AndroidUtilities.runOnUIThread(r72);
        this.openedSettings = null;
        return;
    L9:
        return;
    }

    private /* synthetic */ void lambda$setNewPassword$44(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda34(this, r2));
    }

    private /* synthetic */ void lambda$setNewPassword$45(TLRPC.TL_error r1, TLObject r2, boolean r3) {
        if (r1 != null) goto L5;
        TL_account.Password r22 = (TL_account.Password) r2;
        this.currentPassword = r22;
        TwoStepVerificationActivity.initPasswordNewAlgo(r22);
        setNewPassword(r3);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.didSetOrRemoveTwoStepPassword, new Object[]{this.currentPassword});
        return;
    }

    private /* synthetic */ void lambda$setNewPassword$46(boolean r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda51(this, r4, r3, r2));
    }

    private /* synthetic */ void lambda$setNewPassword$47(byte[] r7, AlertDialog r8, int r9) {
        int r82 = this.fragmentsToClose.size();
        int r92 = 0;
    L3:
        if (r92 >= r82) goto L5;
        this.fragmentsToClose.get(r92).removeSelfFromStack();
        r92 = r92 + 1;
        goto L3
    L5:
        TwoStepVerificationActivity r0 = new TwoStepVerificationActivity();
        TL_account.Password r83 = this.currentPassword;
        r83.has_password = true;
        if (r83.has_recovery == true) goto L8;
        r83.has_recovery = !TextUtils.isEmpty(r83.email_unconfirmed_pattern);
    L8:
        TL_account.Password r1 = this.currentPassword;
        if (r7 != null) goto L12;
        r7 = this.currentPasswordHash;
    L12:
        r0.setCurrentPasswordParams(r1, r7, this.currentSecretId, this.currentSecret);
        r0.setBlockingAlert(this.otherwiseReloginDays);
        presentFragment(r0, true);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.didSetOrRemoveTwoStepPassword, new Object[]{this.currentPassword});
        Runnable r72 = this.openedSettings;
        if (r72 == null) goto L17;
        AndroidUtilities.runOnUIThread(r72);
        this.openedSettings = null;
        return;
    }

    private /* synthetic */ void lambda$setNewPassword$48(TLRPC.TL_error r10, boolean r11, TLObject r12, byte[] r13, String r14, TL_account.passwordInputSettings r15) {
        if (r10 != null) goto L4;
    L7:
        needHideProgress();
        int r1 = 0;
        if (r10 == null) goto L10;
    L52:
        if (r10 != null) goto L54;
        return;
    L54:
        if ("EMAIL_UNCONFIRMED".equals(r10.text) == false) goto L56;
    L72:
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.twoStepPasswordChanged, new Object[0]);
        int r102 = this.fragmentsToClose.size();
    L73:
        if (r1 >= r102) goto L75;
        this.fragmentsToClose.get(r1).removeSelfFromStack();
        r1 = r1 + 1;
        goto L73
    L75:
        NotificationCenter r103 = NotificationCenter.getInstance(this.currentAccount);
        int r112 = NotificationCenter.twoStepPasswordChanged;
        TLRPC.PasswordKdfAlgo r2 = r15.new_algo;
        TL_account.Password r122 = this.currentPassword;
        TLRPC.SecurePasswordKdfAlgo r3 = r122.new_secure_algo;
        byte[] r4 = r122.secure_random;
        String r5 = this.email;
        r103.postNotificationName(r112, new Object[]{r13, r2, r3, r4, r5, this.hint, r5, this.firstPassword});
        TL_account.Password r104 = this.currentPassword;
        r104.email_unconfirmed_pattern = this.email;
        TwoStepVerificationSetupActivity r16 = new TwoStepVerificationSetupActivity(5, r104);
        r16.fromRegistration = this.fromRegistration;
        if (r13 == null) goto L78;
        byte[] r22 = r13;
    L79:
        r16.setCurrentPasswordParams(r22, this.currentSecretId, this.currentSecret, this.emailOnly);
        r16.closeAfterSet = this.closeAfterSet;
        r16.setBlockingAlert(this.otherwiseReloginDays);
        presentFragment(r16, true);
        return;
    L78:
        r22 = this.currentPasswordHash;
        goto L79
    L56:
        if (r10.text.startsWith("EMAIL_UNCONFIRMED_") == true) goto L72;
        if ("EMAIL_INVALID".equals(r10.text) == false) goto L63;
        showAlertWithText(LocaleController.getString(R.string.AppName), LocaleController.getString(R.string.PasswordEmailInvalid));
        return;
    L63:
        if (r10.text.startsWith("FLOOD_WAIT") == false) goto L70;
        int r105 = Utilities.parseInt(r10.text);
        if (r105 >= 60) goto L67;
        String r106 = LocaleController.formatPluralString("Seconds", r105, new Object[0]);
    L68:
        showAlertWithText(LocaleController.getString(R.string.AppName), LocaleController.formatString("FloodWaitTime", R.string.FloodWaitTime, new Object[]{r106}));
        return;
    L67:
        r106 = LocaleController.formatPluralString("Minutes", r105 / 60, new Object[0]);
        goto L68
    L70:
        showAlertWithText(LocaleController.getString(R.string.AppName), r10.text);
        return;
    L10:
        if ((r12 instanceof TLRPC.TL_boolTrue) == false) goto L12;
    L13:
        getMessagesController().removeSuggestion(0, "VALIDATE_PASSWORD");
        if (r11 == false) goto L21;
        int r107 = this.fragmentsToClose.size();
        int r113 = 0;
    L16:
        if (r113 >= r107) goto L18;
        this.fragmentsToClose.get(r113).removeSelfFromStack();
        r113 = r113 + 1;
        goto L16
    L18:
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.didRemoveTwoStepPassword, new Object[0]);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.didSetOrRemoveTwoStepPassword, new Object[0]);
        finishFragment();
        return;
    L21:
        if (getParentActivity() != null) goto L24;
        return;
    L24:
        if (this.currentPassword.has_password == false) goto L37;
        AlertDialog.Builder r108 = new AlertDialog.Builder(getParentActivity());
        r108.setPositiveButton(LocaleController.getString(R.string.OK), new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda49(this, r13));
        if (r14 != null) goto L32;
        TL_account.Password r114 = this.currentPassword;
        if (r114 == null) goto L32;
        if (r114.has_password == false) goto L32;
        r108.setMessage(LocaleController.getString(R.string.YourEmailSuccessText));
    L33:
        r108.setTitle(LocaleController.getString(R.string.YourPasswordSuccess));
        Dialog r9 = showDialog(r108.create());
        if (r9 == null) goto L84;
        r9.setCanceledOnTouchOutside(false);
        r9.setCancelable(false);
        return;
    L84:
        return;
    L32:
        r108.setMessage(LocaleController.getString(R.string.YourPasswordChangedSuccessText));
        goto L33
    L37:
        int r109 = this.fragmentsToClose.size();
        int r115 = 0;
    L38:
        if (r115 >= r109) goto L40;
        this.fragmentsToClose.get(r115).removeSelfFromStack();
        r115 = r115 + 1;
        goto L38
    L40:
        TL_account.Password r1010 = this.currentPassword;
        r1010.has_password = true;
        if (r1010.has_recovery == true) goto L44;
        r1010.has_recovery = !TextUtils.isEmpty(r1010.email_unconfirmed_pattern);
    L44:
        if (this.closeAfterSet == false) goto L46;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.twoStepPasswordChanged, new Object[0]);
    L46:
        TwoStepVerificationSetupActivity r17 = new TwoStepVerificationSetupActivity(7, this.currentPassword);
        r17.fromRegistration = this.fromRegistration;
        if (r13 != null) goto L50;
        r13 = this.currentPasswordHash;
    L50:
        r17.setCurrentPasswordParams(r13, this.currentSecretId, this.currentSecret, this.emailOnly);
        r17.closeAfterSet = this.closeAfterSet;
        r17.setBlockingAlert(this.otherwiseReloginDays);
        presentFragment(r17, true);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.didSetOrRemoveTwoStepPassword, new Object[]{this.currentPassword});
        return;
    L12:
        if ((r12 instanceof TLRPC.auth_Authorization) == false) goto L52;
    L4:
        if ("SRP_ID_INVALID".equals(r10.text) == false) goto L7;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(new TL_account.getPassword(), new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda48(this, r11), 8);
    }

    private /* synthetic */ void lambda$setNewPassword$49(boolean r9, byte[] r10, String r11, TL_account.passwordInputSettings r12, TLObject r13, TLRPC.TL_error r14) {
        AndroidUtilities.runOnUIThread(new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda43(this, r14, r9, r13, r10, r11, r12));
    }

    private /* synthetic */ void lambda$setNewPassword$50(TLObject r18, boolean r19, String r20, TL_account.passwordInputSettings r21) {
        if ((r18 instanceof TL_account.updatePasswordSettings) == false) goto L8;
        TL_account.updatePasswordSettings r0 = (TL_account.updatePasswordSettings) r18;
        if (r0.password != null) goto L8;
        r0.password = getNewSrpPassword();
    L8:
        if (r19 == true) goto L14;
        if (r20 == null) goto L14;
        byte[] r02 = AndroidUtilities.getStringBytes(r20);
        TLRPC.PasswordKdfAlgo r2 = this.currentPassword.new_algo;
        if ((r2 instanceof TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow) == false) goto L13;
        byte[] r8 = r02;
        byte[] r3 = SRPHelper.getX(r02, (TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow) r2);
    L15:
        TwoStepVerificationSetupActivity$$ExternalSyntheticLambda40 r03 = new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda40(this, r19, r3, r20, r21);
        if (r19 == true) goto L35;
        if (r20 == null) goto L25;
        byte[] r32 = this.currentSecret;
        if (r32 == null) goto L25;
        if (r32.length != 32) goto L25;
        TLRPC.SecurePasswordKdfAlgo r33 = this.currentPassword.new_secure_algo;
        if ((r33 instanceof TLRPC.TL_securePasswordKdfAlgoPBKDF2HMACSHA512iter100000) == false) goto L25;
        TLRPC.TL_securePasswordKdfAlgoPBKDF2HMACSHA512iter100000 r34 = (TLRPC.TL_securePasswordKdfAlgoPBKDF2HMACSHA512iter100000) r33;
        byte[] r9 = Utilities.computePBKDF2(r8, r34.salt);
        byte[] r11 = new byte[32];
        System.arraycopy(r9, 0, r11, 0, 32);
        byte[] r13 = new byte[16];
        System.arraycopy(r9, 32, r13, 0, 16);
        byte[] r92 = new byte[32];
        System.arraycopy(this.currentSecret, 0, r92, 0, 32);
        Utilities.aesCbcEncryptionByteArraySafe(r92, r11, r13, 0, 32, 0, 1);
        TLRPC.TL_secureSecretSettings r4 = new TLRPC.TL_secureSecretSettings();
        r21.new_secure_settings = r4;
        r4.secure_algo = r34;
        r4.secure_secret = r92;
        r4.secure_secret_id = this.currentSecretId;
        r21.flags |= 4;
    L25:
        TLRPC.PasswordKdfAlgo r35 = this.currentPassword.new_algo;
        if ((r35 instanceof TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow) == false) goto L33;
        if (r20 == null) goto L31;
        byte[] r36 = SRPHelper.getVBytes(r8, (TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow) r35);
        r21.new_password_hash = r36;
        if (r36 != null) goto L31;
        TLRPC.TL_error r37 = new TLRPC.TL_error();
        r37.text = "ALGO_INVALID";
        r03.run(null, r37);
    L31:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r18, r03, 10);
        return;
    L33:
        TLRPC.TL_error r1 = new TLRPC.TL_error();
        r1.text = "PASSWORD_HASH_INVALID";
        r03.run(null, r1);
        return;
    L35:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r18, r03, 10);
        return;
    L13:
        r8 = r02;
        r3 = null;
    L14:
        r3 = null;
        r8 = null;
        goto L15
    }

    private /* synthetic */ void lambda$setRandomMonkeyIdleAnimation$38() {
        if (this.setAnimationRunnable != null) goto L5;
        return;
    L5:
        setRandomMonkeyIdleAnimation(false);
    }

    private /* synthetic */ void lambda$showSetForcePasswordAlert$51(AlertDialog r1, int r2) {
        finishFragment();
    }

    private void loadPasswordInfo() {
        TL_account.getPassword r0 = new TL_account.getPassword();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda20(this), 10);
    }

    private void needShowProgress() {
        if (getParentActivity() != null) goto L5;
        return;
    L5:
        if (getParentActivity().isFinishing() == true) goto L10;
        this.floatingButton.setProgressVisible(true, true);
        return;
    }

    private void onCodeFieldError(boolean r7) {
        CodeNumberField[] r0 = this.codeFieldContainer.codeField;
        int r1 = r0.length;
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L8;
        CodeNumberField r4 = r0[r3];
        if (r7 == false) goto L7;
        r4.setText(_UrlKt.FRAGMENT_ENCODE_SET);
    L7:
        r4.animateErrorProgress(1.0f);
        r3 = r3 + 1;
        goto L3
    L8:
        if (r7 == false) goto L10;
        this.codeFieldContainer.codeField[0].requestFocus();
    L10:
        AndroidUtilities.shakeViewSpring(this.codeFieldContainer, 8.0f, new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda42(this));
    }

    private void onFieldError(View r2, TextView r3, boolean r4) {
        if (getParentActivity() != null) goto L5;
        return;
    L5:
        r2.performHapticFeedback(3, 2);
        if (r4 == false) goto L8;
        r3.setText(_UrlKt.FRAGMENT_ENCODE_SET);
    L8:
        AndroidUtilities.shakeViewSpring(r2, 5.0f);
    }

    private void onHintDone() {
        TL_account.Password r0 = this.currentPassword;
        if (r0.has_recovery == true) goto L6;
        TwoStepVerificationSetupActivity r2 = new TwoStepVerificationSetupActivity(this.currentAccount, 3, r0);
        r2.fromRegistration = this.fromRegistration;
        r2.setCurrentPasswordParams(this.currentPasswordHash, this.currentSecretId, this.currentSecret, this.emailOnly);
        r2.firstPassword = this.firstPassword;
        r2.hint = this.hint;
        r2.fragmentsToClose.addAll(this.fragmentsToClose);
        r2.fragmentsToClose.add(this);
        r2.closeAfterSet = this.closeAfterSet;
        r2.setBlockingAlert(this.otherwiseReloginDays);
        presentFragment(r2);
        return;
    L6:
        this.email = _UrlKt.FRAGMENT_ENCODE_SET;
        setNewPassword(false);
    }

    private void processNext() {
        if (getParentActivity() == null) goto L92;
        int r3 = 1;
        switch(this.currentType) {
            case 0: goto L64;
            case 1: goto L64;
            case 2: goto L53;
            case 3: goto L43;
            case 4: goto L40;
            case 5: goto L38;
            case 6: goto L32;
            case 7: goto L21;
            case 8: goto L11;
            case 9: goto L8;
            default: goto L93;
        };
    L8:
        finishFragment();
        return;
    L32:
        TL_account.Password r0 = this.currentPassword;
        if (r0 != null) goto L36;
        needShowProgress();
        this.doneAfterPasswordLoad = true;
        return;
    L36:
        TwoStepVerificationSetupActivity r1 = new TwoStepVerificationSetupActivity(this.currentAccount, 0, r0);
        r1.fromRegistration = this.fromRegistration;
        r1.closeAfterSet = this.closeAfterSet;
        r1.setBlockingAlert(this.otherwiseReloginDays);
        presentFragment(r1, true);
        return;
    L38:
        TL_account.confirmPasswordEmail r02 = new TL_account.confirmPasswordEmail();
        r02.code = this.codeFieldContainer.getCode();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r02, new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda26(this), 10);
        needShowProgress();
        return;
    L40:
        String r03 = this.codeFieldContainer.getCode();
        TLRPC.TL_auth_checkRecoveryPassword r2 = new TLRPC.TL_auth_checkRecoveryPassword();
        r2.code = r03;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r2, new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda25(this, r03), 10);
        return;
    L53:
        String r04 = this.editTextFirstRow.getText().toString();
        this.hint = r04;
        if (r04.equalsIgnoreCase(this.firstPassword) == true) goto L89;
        onHintDone();
        return;
    L89:
        Toast.makeText(getParentActivity(), LocaleController.getString(R.string.PasswordAsHintError), 0).show();     // Catch: Exception -> L57
    L59:
        onFieldError(this.outlineTextFirstRow, this.editTextFirstRow, false);
        return;
    L57:
        e = move-exception;
        FileLog.e(e);
        goto L59
    L93:
        return;
    L11:
        if (this.currentPassword != null) goto L14;
        needShowProgress();
        this.doneAfterPasswordLoad = true;
        return;
    L14:
        String r05 = this.editTextFirstRow.getText().toString();
        if (r05.length() != 0) goto L18;
        onFieldError(this.outlineTextFirstRow, this.editTextFirstRow, false);
        return;
    L18:
        byte[] r06 = AndroidUtilities.getStringBytes(r05);
        needShowProgress();
        Utilities.globalQueue.postRunnable(new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda24(this, r06));
        return;
    L21:
        if (this.closeAfterSet == false) goto L25;
        finishFragment();
        return;
    L25:
        if (this.fromRegistration == false) goto L28;
        Bundle r07 = new Bundle();
        r07.putBoolean("afterSignup", true);
        MainTabsActivity r12 = new MainTabsActivity();
        r12.prepareDialogsActivity(r07);
        presentFragment(r12, true);
        return;
    L28:
        TwoStepVerificationActivity r4 = new TwoStepVerificationActivity();
        r4.setCurrentPasswordParams(this.currentPassword, this.currentPasswordHash, this.currentSecretId, this.currentSecret);
        r4.setBlockingAlert(this.otherwiseReloginDays);
        presentFragment(r4, true);
        Runnable r08 = this.openedSettings;
        if (r08 == null) goto L91;
        AndroidUtilities.runOnUIThread(r08);
        this.openedSettings = null;
        return;
    L91:
        return;
    L43:
        if (this.emailOnly == false) goto L45;
    L47:
        String r09 = this.editTextFirstRow.getText().toString();
        this.email = r09;
        if (isValidEmail(r09) == true) goto L51;
        onFieldError(this.outlineTextFirstRow, this.editTextFirstRow, false);
        return;
    L51:
        setNewPassword(false);
        return;
    L45:
        if (this.bottomSkipButton.getAlpha() >= 1.0f) goto L47;
        this.bottomSkipButton.animate().cancel();
        this.bottomSkipButton.animate().alpha(1.0f).start();
        goto L47
    L64:
        if (this.editTextFirstRow.length() != 0) goto L68;
        onFieldError(this.outlineTextFirstRow, this.editTextFirstRow, false);
        return;
    L68:
        if (this.editTextFirstRow.getText().toString().equals(this.firstPassword) == false) goto L70;
    L78:
        int r22 = this.currentAccount;
        if (this.currentType == 0) goto L82;
        r3 = 2;
    L82:
        TwoStepVerificationSetupActivity r010 = new TwoStepVerificationSetupActivity(r22, r3, this.currentPassword);
        r010.fromRegistration = this.fromRegistration;
        r010.firstPassword = this.editTextFirstRow.getText().toString();
        r010.setCurrentPasswordParams(this.currentPasswordHash, this.currentSecretId, this.currentSecret, this.emailOnly);
        r010.setCurrentEmailCode(this.emailCode);
        r010.fragmentsToClose.addAll(this.fragmentsToClose);
        r010.fragmentsToClose.add(this);
        r010.closeAfterSet = this.closeAfterSet;
        r010.setBlockingAlert(this.otherwiseReloginDays);
        presentFragment(r010);
        return;
    L70:
        if (this.currentType != 1) goto L78;
        AndroidUtilities.shakeViewSpring(this.outlineTextFirstRow, 5.0f);
        this.outlineTextFirstRow.performHapticFeedback(3, 2);     // Catch: Exception -> L84
    L85:
        Toast.makeText(getParentActivity(), LocaleController.getString(R.string.PasswordDoNotMatch), 0).show();     // Catch: Exception -> L75
        return;
    L75:
        e = move-exception;
        FileLog.e(e);
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void setNewPassword(boolean r9) {
        if (r9 == true) goto L4;
    L9:
        String r6 = this.firstPassword;
        TL_account.passwordInputSettings r7 = new TL_account.passwordInputSettings();
        if (r9 == false) goto L16;
        UserConfig.getInstance(this.currentAccount).resetSavedPassword();
        this.currentSecret = null;
        if (this.waitingForEmail == false) goto L14;
        r7.flags = 2;
        r7.email = _UrlKt.FRAGMENT_ENCODE_SET;
    L29:
        if (this.emailCode == null) goto L32;
        TLRPC.TL_auth_recoverPassword r0 = new TLRPC.TL_auth_recoverPassword();
        r0.code = this.emailCode;
        r0.new_settings = r7;
        r0.flags |= 1;
        TL_account.updatePasswordSettings r02 = r0;
    L31:
        TL_account.updatePasswordSettings r4 = r02;
        needShowProgress();
        Utilities.globalQueue.postRunnable(new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda31(this, r4, r9, r6, r7));
        return;
    L32:
        TL_account.updatePasswordSettings r03 = new TL_account.updatePasswordSettings();
        byte[] r1 = this.currentPasswordHash;
        if (r1 != null) goto L35;
    L39:
        r03.password = new TLRPC.TL_inputCheckPasswordEmpty();
    L40:
        r03.new_settings = r7;
        r02 = r03;
        goto L31
    L35:
        if (r1.length == 0) goto L39;
        if (r9 == false) goto L40;
        if (this.waitingForEmail == false) goto L40;
    L14:
        r7.flags = 3;
        r7.hint = _UrlKt.FRAGMENT_ENCODE_SET;
        r7.new_password_hash = new byte[0];
        r7.new_algo = new TLRPC.TL_passwordKdfAlgoUnknown();
        r7.email = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L29
    L16:
        if (this.hint != null) goto L21;
        TL_account.Password r2 = this.currentPassword;
        if (r2 == null) goto L21;
        this.hint = r2.hint;
    L21:
        if (this.hint != null) goto L23;
        this.hint = _UrlKt.FRAGMENT_ENCODE_SET;
    L23:
        if (r6 == null) goto L26;
        r7.flags |= 1;
        r7.hint = this.hint;
        r7.new_algo = this.currentPassword.new_algo;
    L26:
        if (this.email.length() <= 0) goto L29;
        r7.flags = 2 | r7.flags;
        r7.email = this.email.trim();
        goto L29
    L4:
        if (this.waitingForEmail == false) goto L9;
        if (this.currentPassword.has_password == false) goto L9;
        needShowProgress();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(new TL_account.cancelPasswordEmail(), new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda30(this));
    }

    private void setRandomMonkeyIdleAnimation(boolean r6) {
        if (this.currentType == 0) goto L5;
        return;
    L5:
        Runnable r0 = this.setAnimationRunnable;
        if (r0 == null) goto L8;
        AndroidUtilities.cancelRunOnUIThread(r0);
    L8:
        RLottieDrawable r02 = this.imageView.getAnimatedDrawable();
        if (r6 == true) goto L19;
        RLottieDrawable[] r3 = this.animationDrawables;
        if (r02 == r3[0]) goto L19;
        if (r02 == r3[1]) goto L19;
        if (this.editTextFirstRow.length() != 0) goto L25;
        if (r02 == null) goto L19;
        if (r02.isRunning() == false) goto L19;
    L25:
        TwoStepVerificationSetupActivity$$ExternalSyntheticLambda23 r62 = new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda23(this);
        this.setAnimationRunnable = r62;
        AndroidUtilities.runOnUIThread(r62, Utilities.random.nextInt(CastStatusCodes.AUTHENTICATION_FAILED) + 5000);
        return;
    L19:
        int r03 = Utilities.random.nextInt() % 2;
        RLottieImageView r32 = this.imageView;
        if (r03 != 0) goto L22;
        r32.setAnimation(this.animationDrawables[0]);
        this.animationDrawables[0].setProgress(0.0f);
    L23:
        if (r6 == true) goto L25;
        this.imageView.playAnimation();
        goto L25
    L22:
        r32.setAnimation(this.animationDrawables[1]);
        this.animationDrawables[1].setProgress(0.0f);
        goto L23
    }

    private void showAlertWithText(String r4, String r5) {
        if (getParentActivity() != null) goto L5;
        return;
    L5:
        AlertDialog.Builder r0 = new AlertDialog.Builder(getParentActivity());
        r0.setPositiveButton(LocaleController.getString(R.string.OK), null);
        r0.setTitle(r4);
        r0.setMessage(r5);
        showDialog(r0.create());
    }

    private void showDoneButton(boolean r20) {
        if (this.buttonTextView.getTag() == null) goto L5;
        boolean r2 = true;
    L6:
        if (r20 != r2) goto L8;
        return;
    L8:
        AnimatorSet r22 = this.buttonAnimation;
        if (r22 == null) goto L11;
        r22.cancel();
    L11:
        TextView r23 = this.buttonTextView;
        if (r20 == false) goto L14;
        Integer r5 = 1;
    L15:
        r23.setTag(r5);
        this.buttonAnimation = new AnimatorSet();
        if (r20 == false) goto L18;
        this.buttonTextView.setVisibility(0);
        AnimatorSet r12 = this.buttonAnimation;
        TextView r13 = this.descriptionText2;
        Property r14 = View.SCALE_X;
        ObjectAnimator r132 = ObjectAnimator.ofFloat(r13, r14, new float[]{0.9f});
        TextView r15 = this.descriptionText2;
        Property r24 = View.SCALE_Y;
        ObjectAnimator r52 = ObjectAnimator.ofFloat(r15, r24, new float[]{0.9f});
        TextView r10 = this.descriptionText2;
        Property r152 = View.ALPHA;
        r12.playTogether(new Animator[]{r132, r52, ObjectAnimator.ofFloat(r10, r152, new float[]{0.0f}), ObjectAnimator.ofFloat(this.buttonTextView, r14, new float[]{1.0f}), ObjectAnimator.ofFloat(this.buttonTextView, r24, new float[]{1.0f}), ObjectAnimator.ofFloat(this.buttonTextView, r152, new float[]{1.0f})});
    L19:
        this.buttonAnimation.addListener(new AnonymousClass14(this, r20));
        this.buttonAnimation.setDuration(150);
        this.buttonAnimation.start();
        return;
    L18:
        this.descriptionText2.setVisibility(0);
        AnimatorSet r25 = this.buttonAnimation;
        TextView r53 = this.buttonTextView;
        Property r6 = View.SCALE_X;
        ObjectAnimator r54 = ObjectAnimator.ofFloat(r53, r6, new float[]{0.9f});
        TextView r122 = this.buttonTextView;
        Property r133 = View.SCALE_Y;
        ObjectAnimator r102 = ObjectAnimator.ofFloat(r122, r133, new float[]{0.9f});
        TextView r123 = this.buttonTextView;
        Property r142 = View.ALPHA;
        r25.playTogether(new Animator[]{r54, r102, ObjectAnimator.ofFloat(r123, r142, new float[]{0.0f}), ObjectAnimator.ofFloat(this.descriptionText2, r6, new float[]{1.0f}), ObjectAnimator.ofFloat(this.descriptionText2, r133, new float[]{1.0f}), ObjectAnimator.ofFloat(this.descriptionText2, r142, new float[]{1.0f})});
        goto L19
    L14:
        r5 = null;
        goto L15
    L5:
        r2 = false;
        goto L6
    }

    private void showSetForcePasswordAlert() {
        AlertDialog.Builder r0 = new AlertDialog.Builder(getParentActivity());
        r0.setTitle(LocaleController.getString(R.string.Warning));
        r0.setMessage(LocaleController.formatPluralString("ForceSetPasswordAlertMessageShort", this.otherwiseReloginDays, new Object[0]));
        r0.setPositiveButton(LocaleController.getString(R.string.TwoStepVerificationSetPassword), null);
        r0.setNegativeButton(LocaleController.getString(R.string.ForceSetPasswordCancel), new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda13(this));
        ((TextView) r0.show().getButton(-2)).setTextColor(Theme.getColor(Theme.key_text_RedBold));
    }

    private void switchMonkeyAnimation(boolean r3) {
        if (r3 == false) goto L8;
        Runnable r32 = this.setAnimationRunnable;
        if (r32 == null) goto L6;
        AndroidUtilities.cancelRunOnUIThread(r32);
    L6:
        this.imageView.setAnimation(this.animationDrawables[6]);
        this.imageView.playAnimation();
        return;
    L8:
        this.editTextFirstRow.dispatchTextWatchersTextChanged();
        setRandomMonkeyIdleAnimation(true);
    }

    public void addFragmentToClose(BaseFragment r1) {
        this.fragmentsToClose.add(r1);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r37) {
        this.actionBar.setBackgroundDrawable(null);
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        boolean r4 = false;
        this.actionBar.setAllowOverlayTitle(false);
        ActionBar r2 = this.actionBar;
        int r5 = Theme.key_windowBackgroundWhiteBlackText;
        r2.setTitleColor(Theme.getColor(r5));
        this.actionBar.setItemsColor(Theme.getColor(r5), false);
        this.actionBar.setItemsBackgroundColor(Theme.getColor(Theme.key_actionBarWhiteSelector), false);
        this.actionBar.setCastShadows(false);
        this.actionBar.setAddToContainer(false);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        if (this.currentType != 5) goto L5;
        this.actionBar.createMenu().addItem(0, R.drawable.ic_ab_other).addSubItem(1, LocaleController.getString(R.string.AbortPasswordMenu));
    L5:
        FragmentFloatingButton r22 = new FragmentFloatingButton(r37, this.resourceProvider);
        this.floatingButton = r22;
        this.floatingAutoAnimator = VerticalPositionAutoAnimator.attach(r22);
        this.floatingButton.setOnClickListener(new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda0(this));
        TransformableLoginButtonView r23 = new TransformableLoginButtonView(r37);
        this.floatingButtonIcon = r23;
        r23.setTransformType(1);
        this.floatingButtonIcon.setProgress(0.0f);
        this.floatingButtonIcon.setColor(Theme.getColor(Theme.key_chats_actionIcon));
        this.floatingButtonIcon.setDrawBackground(false);
        this.floatingButton.setContentDescription(LocaleController.getString(R.string.Next));
        this.floatingButton.addView(this.floatingButtonIcon, LayoutHelper.createFrame(56, 56, 17));
        this.floatingButton.addAdditionalView(this.floatingButtonIcon);
        TextView r24 = new TextView(r37);
        this.bottomSkipButton = r24;
        r24.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText2));
        this.bottomSkipButton.setTextSize(1, 14.0f);
        this.bottomSkipButton.setGravity(19);
        this.bottomSkipButton.setVisibility(8);
        VerticalPositionAutoAnimator.attach(this.bottomSkipButton);
        this.bottomSkipButton.setPadding(AndroidUtilities.dp(32.0f), 0, AndroidUtilities.dp(32.0f), 0);
        this.bottomSkipButton.setOnClickListener(new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda4(this));
        RLottieImageView r25 = new RLottieImageView(r37);
        this.imageView = r25;
        ImageView.ScaleType r14 = ImageView.ScaleType.CENTER;
        r25.setScaleType(r14);
        if (this.currentType != 2) goto L12;
        if (AndroidUtilities.isSmallScreen() == false) goto L12;
        this.imageView.setVisibility(8);
    L10:
        char r16 = 5;
    L18:
        TextView r26 = new TextView(r37);
        this.titleTextView = r26;
        r26.setTextColor(Theme.getColor(r5));
        this.titleTextView.setGravity(1);
        this.titleTextView.setPadding(AndroidUtilities.dp(32.0f), 0, AndroidUtilities.dp(32.0f), 0);
        this.titleTextView.setTextSize(1, 24.0f);
        SpoilersTextView r27 = new SpoilersTextView(r37);
        this.descriptionText = r27;
        int r13 = Theme.key_windowBackgroundWhiteGrayText6;
        r27.setTextColor(Theme.getColor(r13));
        this.descriptionText.setGravity(1);
        this.descriptionText.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
        this.descriptionText.setTextSize(1, 15.0f);
        this.descriptionText.setVisibility(8);
        this.descriptionText.setPadding(AndroidUtilities.dp(32.0f), 0, AndroidUtilities.dp(32.0f), 0);
        TextView r28 = new TextView(r37);
        this.descriptionText2 = r28;
        r28.setTextColor(Theme.getColor(r13));
        this.descriptionText2.setGravity(1);
        this.descriptionText2.setTextSize(1, 14.0f);
        this.descriptionText2.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
        this.descriptionText2.setPadding(AndroidUtilities.dp(32.0f), 0, AndroidUtilities.dp(32.0f), 0);
        this.descriptionText2.setVisibility(8);
        this.descriptionText2.setOnClickListener(new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda5(this));
        TextView r29 = new TextView(r37);
        this.buttonTextView = r29;
        r29.setMinWidth(AndroidUtilities.dp(220.0f));
        this.buttonTextView.setPadding(AndroidUtilities.dp(34.0f), 0, AndroidUtilities.dp(34.0f), 0);
        this.buttonTextView.setGravity(17);
        this.buttonTextView.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText));
        this.buttonTextView.setTextSize(1, 15.0f);
        this.buttonTextView.setTypeface(AndroidUtilities.bold());
        this.buttonTextView.setBackground(Theme.AdaptiveRipple.filledRectByKey(Theme.key_featuredStickers_addButton, new float[]{6.0f}));
        this.buttonTextView.setOnClickListener(new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda6(this));
        int r210 = this.currentType;
        if (r210 == 6) goto L24;
        if (r210 == 7) goto L24;
        if (r210 == 9) goto L24;
        this.titleTextView.setTypeface(AndroidUtilities.bold());
        this.titleTextView.setTextSize(1, 18.0f);
    L26:
        switch(this.currentType) {
            case 0: goto L29;
            case 1: goto L29;
            case 2: goto L29;
            case 3: goto L29;
            case 4: goto L29;
            case 5: goto L29;
            case 6: goto L28;
            case 7: goto L28;
            case 8: goto L29;
            case 9: goto L28;
            default: goto L36;
        };
    L28:
        AnonymousClass2 r211 = new AnonymousClass2(this, r37);
        r211.setOnTouchListener(new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda7());
        r211.addView(this.actionBar);
        r211.addView(this.imageView);
        r211.addView(this.titleTextView);
        r211.addView(this.descriptionText);
        r211.addView(this.buttonTextView);
        this.fragmentView = r211;
        goto L36
    L29:
        AnonymousClass3 r212 = new AnonymousClass3(this, r37);
        AnonymousClass4 r9 = new AnonymousClass4(this, r37, r212);
        r9.addView(r212);
        AnonymousClass5 r7 = new AnonymousClass5(this, r37, r9);
        AnonymousClass6 r132 = new AnonymousClass6(this, r37);
        this.scrollView = r132;
        r132.setVerticalScrollBarEnabled(false);
        r212.addView(this.scrollView, LayoutHelper.createFrame(-1, -1.0f));
        r212.addView(this.bottomSkipButton, LayoutHelper.createFrame(-1, 56.0f, 80, 0.0f, 0.0f, 0.0f, 16.0f));
        r212.addView(this.floatingButton, FragmentFloatingButton.createDefaultLayoutParams());
        r7.addView(r9, LayoutHelper.createFrame(-1, -1.0f));
        AnonymousClass7 r3 = new AnonymousClass7(this, r37);
        r3.setOrientation(1);
        this.scrollView.addView(r3, LayoutHelper.createScroll(-1, -1, 51));
        r3.addView(this.imageView, LayoutHelper.createLinear(-2, -2, 49, 0, 69, 0, 0));
        r3.addView(this.titleTextView, LayoutHelper.createLinear(-2, -2, 49, 0, 8, 0, 0));
        r3.addView(this.descriptionText, LayoutHelper.createLinear(-2, -2, 49, 0, 9, 0, 0));
        OutlineTextContainerView r12 = new OutlineTextContainerView(r37);
        this.outlineTextFirstRow = r12;
        r12.animateSelection(1.0f, false);
        EditTextBoldCursor r122 = new EditTextBoldCursor(r37);
        this.editTextFirstRow = r122;
        r122.setTextSize(1, 18.0f);
        int r133 = AndroidUtilities.dp(16.0f);
        this.editTextFirstRow.setPadding(r133, r133, r133, r133);
        EditTextBoldCursor r123 = this.editTextFirstRow;
        int r134 = Theme.key_windowBackgroundWhiteInputFieldActivated;
        r123.setCursorColor(Theme.getColor(r134));
        this.editTextFirstRow.setTextColor(Theme.getColor(r5));
        this.editTextFirstRow.setBackground(null);
        this.editTextFirstRow.setMaxLines(1);
        this.editTextFirstRow.setLines(1);
        this.editTextFirstRow.setGravity(3);
        this.editTextFirstRow.setCursorSize(AndroidUtilities.dp(20.0f));
        this.editTextFirstRow.setSingleLine(true);
        this.editTextFirstRow.setCursorWidth(1.5f);
        this.editTextFirstRow.setOnEditorActionListener(new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda8(this));
        this.outlineTextFirstRow.attachEditText(this.editTextFirstRow);
        this.editTextFirstRow.setOnFocusChangeListener(new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda9(this));
        LinearLayout r8 = new LinearLayout(r37);
        r8.setOrientation(0);
        r8.addView(this.editTextFirstRow, LayoutHelper.createLinear(0, -2, 1.0f));
        AnonymousClass8 r6 = new AnonymousClass8(this, r37);
        this.showPasswordButton = r6;
        r6.setImageResource(R.drawable.msg_message);
        this.showPasswordButton.setScaleType(r14);
        this.showPasswordButton.setContentDescription(LocaleController.getString(R.string.TwoStepVerificationShowPassword));
        this.showPasswordButton.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_listSelector)));
        this.showPasswordButton.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chat_messagePanelIcons), PorterDuff.Mode.MULTIPLY));
        AndroidUtilities.updateViewVisibilityAnimated(this.showPasswordButton, false, 0.1f, false);
        this.showPasswordButton.setOnClickListener(new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda10(this));
        r8.addView(this.showPasswordButton, LayoutHelper.createLinear(24, 24, 16, 0, 0, 16, 0));
        this.editTextFirstRow.addTextChangedListener(new AnonymousClass9(this));
        this.outlineTextFirstRow.addView(r8, LayoutHelper.createFrame(-1, -2.0f));
        r3.addView(this.outlineTextFirstRow, LayoutHelper.createFrame(-1, -2.0f, 49, 24.0f, 32.0f, 24.0f, 32.0f));
        this.outlineTextSecondRow = new OutlineTextContainerView(r37);
        EditTextBoldCursor r62 = new EditTextBoldCursor(r37);
        this.editTextSecondRow = r62;
        r62.setTextSize(1, 18.0f);
        int r63 = AndroidUtilities.dp(16.0f);
        this.editTextSecondRow.setPadding(r63, r63, r63, r63);
        this.editTextSecondRow.setCursorColor(Theme.getColor(r134));
        this.editTextSecondRow.setTextColor(Theme.getColor(r5));
        this.editTextSecondRow.setBackground(null);
        this.editTextSecondRow.setMaxLines(1);
        this.editTextSecondRow.setLines(1);
        this.editTextSecondRow.setGravity(3);
        this.editTextSecondRow.setCursorSize(AndroidUtilities.dp(20.0f));
        this.editTextSecondRow.setSingleLine(true);
        this.editTextSecondRow.setCursorWidth(1.5f);
        this.editTextSecondRow.setOnEditorActionListener(new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda11(this));
        this.outlineTextSecondRow.attachEditText(this.editTextSecondRow);
        this.editTextSecondRow.setOnFocusChangeListener(new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda12(this));
        this.outlineTextSecondRow.addView(this.editTextSecondRow, LayoutHelper.createFrame(-1, -2.0f));
        r3.addView(this.outlineTextSecondRow, LayoutHelper.createFrame(-1, -2.0f, 49, 24.0f, 16.0f, 24.0f, 0.0f));
        this.outlineTextSecondRow.setVisibility(8);
        CustomPhoneKeyboardView r52 = new CustomPhoneKeyboardView(r37);
        this.keyboardView = r52;
        r52.setVisibility(8);
        r9.addView(this.keyboardView);
        AnonymousClass10 r53 = new AnonymousClass10(this, r37);
        this.codeFieldContainer = r53;
        boolean r124 = true;
        r53.setNumbersCount(6, 1);
        CodeNumberField[] r54 = this.codeFieldContainer.codeField;
        int r64 = r54.length;
        int r82 = 0;
    L30:
        if (r82 >= r64) goto L32;
        CodeNumberField r92 = r54[r82];
        r92.setShowSoftInputOnFocusCompat(isCustomKeyboardVisible() ^ r124);
        r92.addTextChangedListener(new AnonymousClass11(this));
        r92.setOnFocusChangeListener(new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda1(this));
        r82 = r82 + 1;
        r124 = true;
        goto L30
    L32:
        this.codeFieldContainer.setVisibility(8);
        r3.addView(this.codeFieldContainer, LayoutHelper.createLinear(-2, -2, 1, 0, 32, 0, 0));
        FrameLayout r55 = new FrameLayout(r37);
        r3.addView(r55, LayoutHelper.createLinear(-1, -2, 51, 0, 36, 0, 22));
        r55.addView(this.descriptionText2, LayoutHelper.createFrame(-2, -2, 49));
        if (this.currentType != 4) goto L35;
        TextView r56 = new TextView(r37);
        this.descriptionText3 = r56;
        r56.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteLinkText));
        this.descriptionText3.setGravity(1);
        this.descriptionText3.setTextSize(1, 14.0f);
        this.descriptionText3.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
        this.descriptionText3.setPadding(AndroidUtilities.dp(32.0f), 0, AndroidUtilities.dp(32.0f), 0);
        this.descriptionText3.setText(LocaleController.getString(R.string.RestoreEmailTroubleNoEmail));
        r3.addView(this.descriptionText3, LayoutHelper.createLinear(-2, -2, 49, 0, 0, 0, 25));
        this.descriptionText3.setOnClickListener(new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda2(this));
    L35:
        this.fragmentView = r7;
        AnonymousClass12 r32 = new AnonymousClass12(this, r37);
        this.actionBarBackground = r32;
        r32.setAlpha(0.0f);
        r7.addView(this.actionBarBackground);
        r7.addView(this.actionBar);
        RadialProgressView r33 = new RadialProgressView(r37);
        this.radialProgressView = r33;
        r33.setSize(AndroidUtilities.dp(20.0f));
        this.radialProgressView.setAlpha(0.0f);
        this.radialProgressView.setScaleX(0.1f);
        this.radialProgressView.setScaleY(0.1f);
        this.radialProgressView.setProgressColor(Theme.getColor(Theme.key_windowBackgroundWhiteInputFieldActivated));
        r212.addView(this.radialProgressView, LayoutHelper.createFrame(32, 32.0f, 53, 0.0f, 16.0f, 16.0f, 0.0f));
    L36:
        this.fragmentView.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        int r1 = this.currentType;
        String r213 = _UrlKt.FRAGMENT_ENCODE_SET;
        switch(r1) {
            case 0: goto L69;
            case 1: goto L69;
            case 2: goto L67;
            case 3: goto L63;
            case 4: goto L54;
            case 5: goto L50;
            case 6: goto L49;
            case 7: goto L41;
            case 8: goto L40;
            case 9: goto L39;
            default: goto L94;
        };
    L39:
        this.titleTextView.setText(LocaleController.getString(R.string.CheckPasswordPerfect));
        this.descriptionText.setText(LocaleController.getString(R.string.CheckPasswordPerfectInfo));
        this.buttonTextView.setText(LocaleController.getString(R.string.CheckPasswordBackToSettings));
        this.descriptionText.setVisibility(0);
        this.imageView.setAnimation(R.raw.wallet_perfect, 140, 140);
        this.imageView.playAnimation();
        goto L94
    L40:
        this.actionBar.setTitle(LocaleController.getString(R.string.PleaseEnterCurrentPassword));
        this.titleTextView.setText(LocaleController.getString(R.string.PleaseEnterCurrentPassword));
        this.descriptionText.setText(LocaleController.getString(R.string.CheckPasswordInfo));
        this.descriptionText.setVisibility(0);
        this.actionBar.getTitleTextView().setAlpha(0.0f);
        this.descriptionText2.setText(LocaleController.getString(R.string.ForgotPassword));
        this.descriptionText2.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText2));
        this.outlineTextFirstRow.setText(LocaleController.getString(R.string.LoginPassword));
        this.editTextFirstRow.setContentDescription(LocaleController.getString(R.string.LoginPassword));
        this.editTextFirstRow.setImeOptions(268435462);
        this.editTextFirstRow.setInputType(129);
        this.editTextFirstRow.setTransformationMethod(PasswordTransformationMethod.getInstance());
        this.editTextFirstRow.setTypeface(Typeface.DEFAULT);
        this.imageView.setAnimation(R.raw.wallet_science, 120, 120);
        this.imageView.playAnimation();
        goto L94
    L41:
        this.titleTextView.setText(LocaleController.getString(R.string.TwoStepVerificationPasswordSet));
        this.descriptionText.setText(LocaleController.getString(R.string.TwoStepVerificationPasswordSetInfo));
        if (this.closeAfterSet == false) goto L44;
        this.buttonTextView.setText(LocaleController.getString(R.string.TwoStepVerificationPasswordReturnPassport));
    L48:
        this.descriptionText.setVisibility(0);
        this.imageView.setAnimation(R.raw.wallet_allset, 160, 160);
        this.imageView.playAnimation();
        goto L94
    L44:
        boolean r15 = this.fromRegistration;
        TextView r214 = this.buttonTextView;
        if (r15 == false) goto L47;
        r214.setText(LocaleController.getString(R.string.Continue));
        goto L48
    L47:
        r214.setText(LocaleController.getString(R.string.TwoStepVerificationPasswordReturnSettings));
        goto L48
    L49:
        this.titleTextView.setText(LocaleController.getString(R.string.TwoStepVerificationTitle));
        this.descriptionText.setText(LocaleController.getString(R.string.SetAdditionalPasswordInfo));
        this.buttonTextView.setText(LocaleController.getString(R.string.TwoStepVerificationSetPassword));
        this.descriptionText.setVisibility(0);
        this.imageView.setAnimation(R.raw.tsv_setup_intro, 140, 140);
        this.imageView.playAnimation();
        goto L94
    L50:
        this.actionBar.setTitle(LocaleController.getString(R.string.VerificationCode));
        this.actionBar.getTitleTextView().setAlpha(0.0f);
        this.titleTextView.setText(LocaleController.getString(R.string.VerificationCode));
        this.outlineTextFirstRow.setVisibility(8);
        this.keyboardView.setVisibility(0);
        TextView r17 = this.descriptionText;
        int r34 = R.string.EmailPasswordConfirmText2;
        String r57 = this.currentPassword.email_unconfirmed_pattern;
        if (r57 == null) goto L53;
        r213 = r57;
    L53:
        r17.setText(LocaleController.formatString("EmailPasswordConfirmText2", r34, new Object[]{r213}));
        this.descriptionText.setVisibility(0);
        this.floatingButton.setButtonVisible(false, false);
        this.bottomSkipButton.setVisibility(0);
        this.bottomSkipButton.setGravity(17);
        ((ViewGroup.MarginLayoutParams) this.bottomSkipButton.getLayoutParams()).bottomMargin = 0;
        this.bottomSkipButton.setText(LocaleController.getString(R.string.ResendCode));
        this.bottomSkipButton.setOnClickListener(new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda3(this));
        this.codeFieldContainer.setVisibility(0);
        this.imageView.setAnimation(R.raw.tsv_setup_mail, 120, 120);
        this.imageView.playAnimation();
        goto L94
    L54:
        this.actionBar.setTitle(LocaleController.getString(R.string.PasswordRecovery));
        this.actionBar.getTitleTextView().setAlpha(0.0f);
        this.titleTextView.setText(LocaleController.getString(R.string.PasswordRecovery));
        this.keyboardView.setVisibility(0);
        this.outlineTextFirstRow.setVisibility(8);
        String r18 = this.currentPassword.email_unconfirmed_pattern;
        if (r18 == null) goto L57;
        r213 = r18;
    L57:
        SpannableStringBuilder r19 = SpannableStringBuilder.valueOf(r213);
        int r58 = r213.indexOf(42);
        int r215 = r213.lastIndexOf(42);
        if (r58 == r215) goto L62;
        if (r58 == (-1)) goto L62;
        if (r215 == (-1)) goto L62;
        TextStyleSpan.TextStyleRun r35 = new TextStyleSpan.TextStyleRun();
        r35.flags |= 256;
        r35.start = r58;
        int r216 = r215 + 1;
        r35.end = r216;
        r19.setSpan(new TextStyleSpan(r35), r58, r216, 0);
    L62:
        this.descriptionText.setText(AndroidUtilities.formatSpannable(LocaleController.getString(R.string.RestoreEmailSent), new CharSequence[]{r19}));
        this.descriptionText.setVisibility(0);
        this.floatingButton.setButtonVisible(false, false);
        this.codeFieldContainer.setVisibility(0);
        this.imageView.setAnimation(R.raw.tsv_setup_mail, 120, 120);
        this.imageView.playAnimation();
        goto L94
    L63:
        this.actionBar.setTitle(LocaleController.getString(R.string.RecoveryEmailTitle));
        this.actionBar.getTitleTextView().setAlpha(0.0f);
        if (this.emailOnly == true) goto L66;
        this.bottomSkipButton.setVisibility(0);
        this.bottomSkipButton.setAlpha(0.0f);
        this.bottomSkipButton.setText(LocaleController.getString(R.string.YourEmailSkip));
    L66:
        this.titleTextView.setText(LocaleController.getString(R.string.RecoveryEmailTitle));
        this.descriptionText.setText(LocaleController.getString(R.string.RecoveryEmailSubtitle));
        this.descriptionText.setVisibility(0);
        this.outlineTextFirstRow.setText(LocaleController.getString(R.string.PaymentShippingEmailPlaceholder));
        this.editTextFirstRow.setContentDescription(LocaleController.getString(R.string.PaymentShippingEmailPlaceholder));
        this.editTextFirstRow.setImeOptions(268435461);
        this.editTextFirstRow.setInputType(33);
        this.outlineTextSecondRow.setVisibility(8);
        this.imageView.setAnimation(R.raw.tsv_setup_email_sent, 120, 120);
        this.imageView.playAnimation();
        goto L94
    L67:
        this.actionBar.setTitle(LocaleController.getString(R.string.PasswordHint));
        this.actionBar.getTitleTextView().setAlpha(0.0f);
        this.bottomSkipButton.setVisibility(0);
        this.bottomSkipButton.setText(LocaleController.getString(R.string.YourEmailSkip));
        this.titleTextView.setText(LocaleController.getString(R.string.PasswordHint));
        this.descriptionText.setText(LocaleController.getString(R.string.PasswordHintDescription));
        this.descriptionText.setVisibility(0);
        this.outlineTextFirstRow.setText(LocaleController.getString(R.string.PasswordHintPlaceholder));
        this.editTextFirstRow.setContentDescription(LocaleController.getString(R.string.PasswordHintPlaceholder));
        this.editTextFirstRow.setImeOptions(268435461);
        this.outlineTextSecondRow.setVisibility(8);
        this.imageView.setAnimation(R.raw.tsv_setup_hint, 120, 120);
        this.imageView.playAnimation();
    L94:
        EditTextBoldCursor r110 = this.editTextFirstRow;
        if (r110 == null) goto L98;
        r110.addTextChangedListener(new AnonymousClass13(this));
    L98:
        return this.fragmentView;
    L69:
        if (this.currentPassword.has_password == false) goto L71;
        this.actionBar.setTitle(LocaleController.getString(R.string.PleaseEnterNewFirstPassword));
        this.titleTextView.setText(LocaleController.getString(R.string.PleaseEnterNewFirstPassword));
    L76:
        if (TextUtils.isEmpty(this.emailCode) == true) goto L78;
        this.bottomSkipButton.setVisibility(0);
        this.bottomSkipButton.setText(LocaleController.getString(R.string.YourEmailSkip));
    L78:
        this.actionBar.getTitleTextView().setAlpha(0.0f);
        OutlineTextContainerView r111 = this.outlineTextFirstRow;
        if (this.currentType != 0) goto L81;
        int r217 = R.string.EnterPassword;
    L82:
        r111.setText(LocaleController.getString(r217));
        EditTextBoldCursor r112 = this.editTextFirstRow;
        if (this.currentType != 0) goto L85;
        int r218 = R.string.EnterPassword;
    L86:
        r112.setContentDescription(LocaleController.getString(r218));
        this.editTextFirstRow.setImeOptions(268435461);
        this.editTextFirstRow.setInputType(129);
        this.editTextFirstRow.setTransformationMethod(PasswordTransformationMethod.getInstance());
        this.editTextFirstRow.setTypeface(Typeface.DEFAULT);
        if (this.currentType != 0) goto L89;
        boolean r113 = true;
    L90:
        this.needPasswordButton = r113;
        AndroidUtilities.updateViewVisibilityAnimated(this.showPasswordButton, false, 0.1f, false);
        RLottieDrawable[] r114 = new RLottieDrawable[7];
        this.animationDrawables = r114;
        r114[0] = new RLottieDrawable(R.raw.tsv_setup_monkey_idle1, AndroidUtilities.dp(120.0f), AndroidUtilities.dp(120.0f), true, null);
        this.animationDrawables[1] = new RLottieDrawable(R.raw.tsv_setup_monkey_idle2, AndroidUtilities.dp(120.0f), AndroidUtilities.dp(120.0f), true, null);
        this.animationDrawables[2] = new RLottieDrawable(R.raw.tsv_monkey_close, AndroidUtilities.dp(120.0f), AndroidUtilities.dp(120.0f), true, null);
        this.animationDrawables[3] = new RLottieDrawable(R.raw.tsv_setup_monkey_peek, AndroidUtilities.dp(120.0f), AndroidUtilities.dp(120.0f), true, null);
        this.animationDrawables[4] = new RLottieDrawable(R.raw.tsv_setup_monkey_close_and_peek_to_idle, AndroidUtilities.dp(120.0f), AndroidUtilities.dp(120.0f), true, null);
        this.animationDrawables[r16] = new RLottieDrawable(R.raw.tsv_setup_monkey_close_and_peek, AndroidUtilities.dp(120.0f), AndroidUtilities.dp(120.0f), true, null);
        this.animationDrawables[6] = new RLottieDrawable(R.raw.tsv_setup_monkey_tracking, AndroidUtilities.dp(120.0f), AndroidUtilities.dp(120.0f), true, null);
        this.animationDrawables[6].setPlayInDirectionOfCustomEndFrame(true);
        this.animationDrawables[6].setCustomEndFrame(19);
        this.animationDrawables[2].setOnFinishCallback(this.finishCallback, 97);
        setRandomMonkeyIdleAnimation(true);
        if (this.currentType != 1) goto L93;
        r4 = true;
    L93:
        switchMonkeyAnimation(r4);
        goto L94
    L89:
        r113 = false;
        goto L90
    L85:
        r218 = R.string.ReEnterPassword;
        goto L86
    L81:
        r217 = R.string.ReEnterPassword;
        goto L82
    L71:
        if (r1 != 0) goto L73;
        int r115 = R.string.CreatePassword;
    L74:
        String r116 = LocaleController.getString(r115);
        this.actionBar.setTitle(r116);
        this.titleTextView.setText(r116);
        goto L76
    L73:
        r115 = R.string.ReEnterPassword;
    L24:
        this.titleTextView.setTypeface(Typeface.DEFAULT);
        this.titleTextView.setTextSize(1, 24.0f);
    L12:
        if (isIntro() == true) goto L10;
        RLottieImageView r219 = this.imageView;
        if (isLandscape() == false) goto L16;
        r16 = 5;
        int r72 = 8;
    L17:
        r219.setVisibility(r72);
        goto L18
    L16:
        r16 = 5;
        r72 = 0;
        goto L17
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void finishFragment() {
        if (this.otherwiseReloginDays >= 0) goto L5;
    L8:
        super.finishFragment();
        return;
    L5:
        if (this.parentLayout.getFragmentStack().size() != 1) goto L8;
        Bundle r0 = new Bundle();
        r0.putBoolean("afterSignup", true);
        MainTabsActivity r2 = new MainTabsActivity();
        r2.prepareDialogsActivity(r0);
        presentFragment(r2, true);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean finishFragment(boolean r4) {
        Iterator<BaseFragment> r0 = getParentLayout().getFragmentStack().iterator();
    L4:
        if (r0.hasNext() == false) goto L11;
        BaseFragment r1 = r0.next();
        if (r1 == this) goto L4;
        if ((r1 instanceof TwoStepVerificationSetupActivity) == false) goto L4;
        ((TwoStepVerificationSetupActivity) r1).floatingAutoAnimator.ignoreNextLayout();
        goto L4
    L11:
        return super.finishFragment(r4);
    }

    public TLRPC.TL_inputCheckPasswordSRP getNewSrpPassword() {
        TL_account.Password r0 = this.currentPassword;
        TLRPC.PasswordKdfAlgo r1 = r0.current_algo;
        if ((r1 instanceof TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow) == false) goto L6;
        byte[] r4 = this.currentPasswordHash;
        long r2 = r0.srp_id;
        byte[] r02 = r0.srp_B;
        return SRPHelper.startCheck(r4, r2, r02, (TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow) r1);
    L6:
        return null;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND | ThemeDescription.FLAG_CHECKTAG, null, null, null, null, Theme.key_windowBackgroundWhite));
        View r4 = this.fragmentView;
        int r2 = ThemeDescription.FLAG_BACKGROUND;
        r1.add(new ThemeDescription(r4, ThemeDescription.FLAG_CHECKTAG | r2, null, null, null, null, Theme.key_windowBackgroundGray));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_actionBarDefault));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        r1.add(new ThemeDescription(this.titleTextView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_windowBackgroundWhiteGrayText6));
        r1.add(new ThemeDescription(this.editTextFirstRow, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_windowBackgroundWhiteBlackText));
        r1.add(new ThemeDescription(this.editTextFirstRow, ThemeDescription.FLAG_HINTTEXTCOLOR, null, null, null, null, Theme.key_windowBackgroundWhiteHintText));
        r1.add(new ThemeDescription(this.editTextFirstRow, ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, Theme.key_windowBackgroundWhiteInputField));
        r1.add(new ThemeDescription(this.editTextFirstRow, ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_DRAWABLESELECTEDSTATE, null, null, null, null, Theme.key_windowBackgroundWhiteInputFieldActivated));
        return r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean hasForceLightStatusBar() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean hideKeyboardOnShow() {
        int r1 = this.currentType;
        if (r1 != 7) goto L5;
        return true;
    L5:
        if (r1 == 9) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isLightStatusBar() {
        if (ColorUtils.calculateLuminance(Theme.getColor(Theme.key_windowBackgroundWhite, null, true)) <= 0.699999988079071d) goto L5;
        return true;
    L5:
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSwipeBackEnabled(MotionEvent r3) {
        if (this.otherwiseReloginDays < 0) goto L9;
        if (this.parentLayout.getFragmentStack().size() != 1) goto L9;
        return false;
    L9:
        return super.isSwipeBackEnabled(r3);
    }

    public void needHideProgress() {
        this.floatingButton.setProgressVisible(false, true);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r3) {
        if (this.otherwiseReloginDays >= 0) goto L5;
    L10:
        if (r3 == false) goto L12;
        finishFragment();
    L12:
        return true;
    L5:
        if (this.parentLayout.getFragmentStack().size() != 1) goto L10;
        if (r3 == false) goto L13;
        showSetForcePasswordAlert();
        return false;
    L13:
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onConfigurationChanged(Configuration r4) {
        super.onConfigurationChanged(r4);
        int r0 = 0;
        if (this.imageView != null) goto L5;
    L16:
        CustomPhoneKeyboardView r42 = this.keyboardView;
        if (r42 != null) goto L19;
        return;
    L19:
        if (isCustomKeyboardVisible() == true) goto L22;
        r0 = 8;
    L22:
        r42.setVisibility(r0);
        return;
    L5:
        if (this.currentType != 2) goto L10;
        if (AndroidUtilities.isSmallScreen() == false) goto L10;
        this.imageView.setVisibility(8);
    L10:
        if (isIntro() == true) goto L16;
        RLottieImageView r43 = this.imageView;
        if (isLandscape() == false) goto L14;
        int r2 = 8;
    L15:
        r43.setVisibility(r2);
        goto L16
    L14:
        r2 = 0;
        goto L15
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        this.doneAfterPasswordLoad = false;
        Runnable r1 = this.setAnimationRunnable;
        if (r1 == null) goto L6;
        AndroidUtilities.cancelRunOnUIThread(r1);
        this.setAnimationRunnable = null;
    L6:
        if (this.animationDrawables == null) goto L12;
        int r12 = 0;
    L8:
        RLottieDrawable[] r3 = this.animationDrawables;
        if (r12 >= r3.length) goto L11;
        r3[r12].recycle(false);
        r12 = r12 + 1;
        goto L8
    L11:
        this.animationDrawables = null;
    L12:
        AndroidUtilities.removeAdjustResize(getParentActivity(), this.classGuid);
        if (isCustomKeyboardVisible() == false) goto L17;
        AndroidUtilities.removeAltFocusable(getParentActivity(), this.classGuid);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onPause() {
        super.onPause();
        this.paused = true;
    }

    public void onReset() {
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        this.paused = false;
        AndroidUtilities.requestAdjustResize(getParentActivity(), this.classGuid);
        if (isCustomKeyboardVisible() == false) goto L6;
        AndroidUtilities.requestAltFocusable(getParentActivity(), this.classGuid);
        AndroidUtilities.hideKeyboard(this.fragmentView);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationEnd(boolean r3, boolean r4) {
        if (r3 == true) goto L4;
        return;
    L4:
        if (this.editTextFirstRow != null) goto L6;
    L8:
        CodeFieldContainer r32 = this.codeFieldContainer;
        if (r32 != null) goto L11;
        return;
    L11:
        if (r32.getVisibility() != 0) goto L16;
        AndroidUtilities.runOnUIThread(new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda15(this), 200);
        return;
    L16:
        return;
    L6:
        if (isCustomKeyboardVisible() == true) goto L8;
        AndroidUtilities.runOnUIThread(new TwoStepVerificationSetupActivity$$ExternalSyntheticLambda14(this), 200);
        goto L8
    }

    public void setBlockingAlert(int r1) {
        this.otherwiseReloginDays = r1;
    }

    public void setCloseAfterSet(boolean r1) {
        this.closeAfterSet = r1;
    }

    public void setCurrentEmailCode(String r1) {
        this.emailCode = r1;
    }

    public void setCurrentPasswordParams(byte[] r1, long r2, byte[] r4, boolean r5) {
        this.currentPasswordHash = r1;
        this.currentSecret = r4;
        this.currentSecretId = r2;
        this.emailOnly = r5;
    }

    public void setFromRegistration(boolean r1) {
        this.fromRegistration = r1;
    }

    public void setOnOpenedSettings(Runnable r1) {
        this.openedSettings = r1;
    }
}
