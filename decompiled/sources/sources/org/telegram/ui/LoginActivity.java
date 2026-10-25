package org.telegram.ui;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.telephony.PhoneNumberUtils;
import android.telephony.SignalStrength;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.ReplacementSpan;
import android.util.Base64;
import android.util.Property;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.dynamicanimation.animation.DynamicAnimation;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ProductDetails;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.QueryProductDetailsParams;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.components.QrCodeLoginView;
import com.exteragram.messenger.utils.ui.FabUiHelper;
import com.exteragram.messenger.utils.ui.UIUtil;
import com.google.android.gms.auth.api.R$drawable;
import com.google.android.gms.auth.api.identity.GetPhoneNumberHintIntentRequest;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.timepicker.TimeModel;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicReference;
import okhttp3.internal.url._UrlKt;
import org.json.JSONObject;
import org.mvel2.MVEL;
import org.telegram.PhoneFormat.PhoneFormat;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.AuthTokensHelper;
import org.telegram.messenger.BillingController;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.CallReceiver;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.PasskeysController;
import org.telegram.messenger.PushListenerController;
import org.telegram.messenger.SRPHelper;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.utils.ViewOutlineProviderImpl;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.SerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.tgnet.tl.TL_update;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.CheckBoxCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.AnimatedPhoneNumberEditText;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.CustomPhoneKeyboardView;
import org.telegram.ui.Components.Easings;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.FragmentFloatingButton;
import org.telegram.ui.Components.ImageUpdater;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkPath;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.LoadingDrawable;
import org.telegram.ui.Components.LoginOrView;
import org.telegram.ui.Components.OutlineTextContainerView;
import org.telegram.ui.Components.Premium.GLIcon.GLIconRenderer;
import org.telegram.ui.Components.Premium.GLIcon.GLIconTextureView;
import org.telegram.ui.Components.Premium.StarParticlesView;
import org.telegram.ui.Components.ProxyDrawable;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Components.RadialProgressView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.SimpleThemeDescription;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.SlideView;
import org.telegram.ui.Components.TextStyleSpan;
import org.telegram.ui.Components.TextViewSwitcher;
import org.telegram.ui.Components.TransformableLoginButtonView;
import org.telegram.ui.Components.URLSpanNoUnderline;
import org.telegram.ui.Components.VerticalPositionAutoAnimator;
import org.telegram.ui.Components.chat.ViewPositionWatcher;
import org.telegram.ui.Components.spoilers.SpoilersTextView;
import org.telegram.ui.CountrySelectActivity;
import org.telegram.ui.PhotoViewer;
import org.telegram.ui.Stars.ExplainStarsSheet;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.bots.BotWebViewSheet;

@SuppressLint({"HardwareIds"})
/* loaded from: classes6.dex */
public class LoginActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private static final int SHOW_DELAY = 0;
    private int activityMode;
    private Runnable animationFinishCallback;
    private ImageView backButtonView;
    private View cachedFragmentView;
    private AlertDialog cancelDeleteProgressDialog;
    private TLRPC.TL_auth_sentCode cancelDeletionCode;
    private Bundle cancelDeletionParams;
    private String cancelDeletionPhone;
    private boolean checkPermissions;
    private int currentConnectionState;
    private int currentDoneType;
    private TLRPC.TL_help_termsOfService currentTermsOfService;
    private int currentViewNum;
    private boolean customKeyboardWasVisible;
    private boolean[] doneButtonVisible;
    private AnimatorSet doneItemAnimation;
    private boolean[] doneProgressVisible;
    private Runnable[] editDoneCallback;
    private Runnable emailChangeFinishCallback;
    private boolean emailChangeIsSuggestion;
    private boolean emailChangeNonSkippable;
    private TextView emailChangeSkipButton;
    private Runnable emailChangeSkipCallback;
    private VerticalPositionAutoAnimator floatingAutoAnimator;
    private FragmentFloatingButton floatingButton;
    private TransformableLoginButtonView floatingButtonIcon;
    private boolean forceDisableSafetyNet;
    private View introView;
    private boolean isAnimatingIntro;
    private boolean isRequestingFirebaseSms;
    private ValueAnimator keyboardAnimator;
    private Runnable keyboardHideCallback;
    private LinearLayout keyboardLinearLayout;
    private CustomPhoneKeyboardView keyboardView;
    private ItemOptions loginOptionsMenu;
    private ProxyDrawable loginOptionsProxyDrawable;
    private ActionBarMenuSubItem loginOptionsProxyItem;
    private boolean newAccount;
    private boolean paid;
    private boolean pendingSwitchingAccount;
    private Dialog permissionsDialog;
    private ArrayList<String> permissionsItems;
    private PhoneNumberConfirmView phoneNumberConfirmView;
    private boolean[] postedEditDoneCallback;
    private int progressRequestId;
    private ImageView proxyButtonView;
    private boolean proxyButtonVisible;
    private ProxyDrawable proxyDrawable;
    private RadialProgressView radialProgressView;
    private boolean restoringState;
    private AnimatorSet[] showDoneAnimation;
    private Runnable showProxyButtonDelayed;
    private SizeNotifierFrameLayout sizeNotifierFrameLayout;
    private FrameLayout slideViewsContainer;
    private TextView startMessagingButton;
    private boolean syncContacts;
    private boolean testBackend;
    private final SlideView[] views;

    /* renamed from: org.telegram.ui.LoginActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ LoginActivity this$0;

        public AnonymousClass1(LoginActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r3) {
            if (r3 != 1) goto L7;
            LoginActivity.m15833$$Nest$monDoneButtonPressed(this.this$0);
            return;
        L7:
            if (r3 == (-1)) goto L9;
            return;
        L9:
            if (this.this$0.onBackPressed(true) == false) goto L13;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.LoginActivity$10, reason: invalid class name */
    public class AnonymousClass10 extends AnimatorListenerAdapter {
        final /* synthetic */ LoginActivity this$0;
        final /* synthetic */ boolean val$needFloatingButton;
        final /* synthetic */ SlideView val$outView;

        public AnonymousClass10(LoginActivity r1, boolean r2, SlideView r3) {
            this.this$0 = r1;
            this.val$needFloatingButton = r2;
            this.val$outView = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (LoginActivity.m15780$$Nest$fgetcurrentDoneType(this.this$0) == 0) goto L5;
        L7:
            this.val$outView.setVisibility(8);
            this.val$outView.onHide();
            this.val$outView.setX(0.0f);
            return;
        L5:
            if (this.val$needFloatingButton == false) goto L7;
            LoginActivity.m15835$$Nest$mshowDoneButton(this.this$0, true, true);
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.LoginActivity$11, reason: invalid class name */
    public class AnonymousClass11 extends AnimatorListenerAdapter {
        final /* synthetic */ LoginActivity this$0;
        final /* synthetic */ Runnable val$callback;
        final /* synthetic */ TransformableLoginButtonView val$transformButton;

        public AnonymousClass11(LoginActivity r1, TransformableLoginButtonView r2, Runnable r3) {
            this.this$0 = r1;
            this.val$transformButton = r2;
            this.val$callback = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            LoginActivity.m15793$$Nest$fgetkeyboardLinearLayout(this.this$0).setAlpha(1.0f);
            LoginActivity.m15804$$Nest$fgetstartMessagingButton(this.this$0).setVisibility(0);
            this.this$0.fragmentView.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            LoginActivity.m15788$$Nest$fgetfloatingButton(this.this$0).setButtonVisible(true, false);
            ((FrameLayout) this.this$0.fragmentView).removeView(this.val$transformButton);
            if (LoginActivity.m15777$$Nest$fgetanimationFinishCallback(this.this$0) == null) goto L5;
            AndroidUtilities.runOnUIThread(LoginActivity.m15777$$Nest$fgetanimationFinishCallback(this.this$0));
            LoginActivity.m15808$$Nest$fputanimationFinishCallback(this.this$0, null);
        L5:
            LoginActivity.m15812$$Nest$fputisAnimatingIntro(this.this$0, false);
            this.val$callback.run();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r3) {
            LoginActivity.m15788$$Nest$fgetfloatingButton(this.this$0).setButtonVisible(false, false);
            LoginActivity.m15793$$Nest$fgetkeyboardLinearLayout(this.this$0).setAlpha(0.0f);
            this.this$0.fragmentView.setBackgroundColor(0);
            LoginActivity.m15804$$Nest$fgetstartMessagingButton(this.this$0).setVisibility(4);
            ((FrameLayout) this.this$0.fragmentView).addView(this.val$transformButton);
        }
    }

    /* renamed from: org.telegram.ui.LoginActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends SizeNotifierFrameLayout {
        final /* synthetic */ LoginActivity this$0;

        public AnonymousClass2(LoginActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r7, int r8) {
            ViewGroup.MarginLayoutParams r0 = (ViewGroup.MarginLayoutParams) LoginActivity.m15788$$Nest$fgetfloatingButton(this.this$0).getLayoutParams();
            int r2 = 0;
            if (LoginActivity.m15824$$Nest$misCustomKeyboardVisible(this.this$0) == false) goto L5;
            int r1 = AndroidUtilities.dp(226.0f);
        L7:
            if (LoginActivity.m15824$$Nest$misCustomKeyboardVisible(this.this$0) == false) goto L12;
            if (measureKeyboardHeight() <= AndroidUtilities.dp(20.0f)) goto L12;
            r1 = r1 - measureKeyboardHeight();
        L12:
            if (Bulletin.getVisibleBulletin() != null) goto L14;
        L16:
            r0.bottomMargin = AndroidUtilities.dp(FabUiHelper.getFabBottomMarginDp()) + r1;
        L18:
            if (AndroidUtilities.isTablet() == true) goto L21;
            r2 = AndroidUtilities.statusBarHeight;
        L21:
            ((ViewGroup.MarginLayoutParams) LoginActivity.m15778$$Nest$fgetbackButtonView(this.this$0).getLayoutParams()).topMargin = AndroidUtilities.dp(16.0f) + r2;
            ((ViewGroup.MarginLayoutParams) LoginActivity.m15798$$Nest$fgetproxyButtonView(this.this$0).getLayoutParams()).topMargin = AndroidUtilities.dp(16.0f) + r2;
            ((ViewGroup.MarginLayoutParams) LoginActivity.m15799$$Nest$fgetradialProgressView(this.this$0).getLayoutParams()).topMargin = AndroidUtilities.dp(16.0f) + r2;
            if (LoginActivity.m15787$$Nest$fgetemailChangeSkipButton(this.this$0) == null) goto L25;
            ((ViewGroup.MarginLayoutParams) LoginActivity.m15787$$Nest$fgetemailChangeSkipButton(this.this$0).getLayoutParams()).topMargin = AndroidUtilities.dp(16.0f) + r2;
        L25:
            if (measureKeyboardHeight() > AndroidUtilities.dp(20.0f)) goto L27;
        L36:
            super.onMeasure(r7, r8);
            return;
        L27:
            if (LoginActivity.m15794$$Nest$fgetkeyboardView(this.this$0).getVisibility() == 8) goto L36;
            if (LoginActivity.m15823$$Nest$misCustomKeyboardForceDisabled(this.this$0) == true) goto L36;
            if (LoginActivity.m15783$$Nest$fgetcustomKeyboardWasVisible(this.this$0) == true) goto L36;
            if (LoginActivity.m15792$$Nest$fgetkeyboardAnimator(this.this$0) == null) goto L35;
            LoginActivity.m15792$$Nest$fgetkeyboardAnimator(this.this$0).cancel();
        L35:
            LoginActivity.m15794$$Nest$fgetkeyboardView(this.this$0).setVisibility(8);
            goto L36
        L14:
            if (Bulletin.getVisibleBulletin().isShowing() == false) goto L16;
            super.onMeasure(r7, r8);
            r0.bottomMargin = ((AndroidUtilities.dp(FabUiHelper.getFabBottomMarginDp()) + Bulletin.getVisibleBulletin().getLayout().getMeasuredHeight()) - AndroidUtilities.dp(10.0f)) + r1;
            goto L18
        L5:
            r1 = 0;
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.LoginActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends ScrollView {
        final /* synthetic */ LoginActivity this$0;

        public AnonymousClass3(LoginActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.ScrollView, android.view.ViewGroup, android.view.ViewParent
        public boolean requestChildRectangleOnScreen(View r3, Rect r4, boolean r5) {
            if (LoginActivity.m15782$$Nest$fgetcurrentViewNum(this.this$0) != 1) goto L5;
        L8:
            r4.bottom += AndroidUtilities.dp(40.0f);
        L10:
            return super.requestChildRectangleOnScreen(r3, r4, r5);
        L5:
            if (LoginActivity.m15782$$Nest$fgetcurrentViewNum(this.this$0) == 2) goto L8;
            if (LoginActivity.m15782$$Nest$fgetcurrentViewNum(this.this$0) != 4) goto L10;
            goto L8
        }
    }

    /* renamed from: org.telegram.ui.LoginActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends FrameLayout {
        final /* synthetic */ LoginActivity this$0;

        public AnonymousClass4(LoginActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r5, int r6, int r7, int r8, int r9) {
            super.onLayout(r5, r6, r7, r8, r9);
            SlideView[] r52 = LoginActivity.m15807$$Nest$fgetviews(this.this$0);
            int r62 = r52.length;
            int r72 = 0;
        L3:
            if (r72 >= r62) goto L10;
            SlideView r82 = r52[r72];
            ViewGroup.MarginLayoutParams r92 = (ViewGroup.MarginLayoutParams) r82.getLayoutParams();
            int r0 = getHeight() + AndroidUtilities.dp(16.0f);
            if (r82.hasCustomKeyboard() == true) goto L9;
            if (LoginActivity.m15794$$Nest$fgetkeyboardView(this.this$0).getVisibility() != 0) goto L9;
            r0 = r0 + AndroidUtilities.dp(230.0f);
        L9:
            r82.layout(r92.leftMargin, r92.topMargin, getWidth() - r92.rightMargin, r0);
            r72 = r72 + 1;
            goto L3
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r8, int r9) {
            super.onMeasure(r8, r9);
            int r82 = getMeasuredWidth();
            int r92 = getMeasuredHeight();
            SlideView[] r0 = LoginActivity.m15807$$Nest$fgetviews(this.this$0);
            int r1 = r0.length;
            int r2 = 0;
        L3:
            if (r2 >= r1) goto L10;
            SlideView r3 = r0[r2];
            ViewGroup.MarginLayoutParams r4 = (ViewGroup.MarginLayoutParams) r3.getLayoutParams();
            int r5 = (r92 - r4.topMargin) + AndroidUtilities.dp(16.0f);
            if (r3.hasCustomKeyboard() == true) goto L9;
            if (LoginActivity.m15794$$Nest$fgetkeyboardView(this.this$0).getVisibility() != 0) goto L9;
            r5 = r5 + AndroidUtilities.dp(230.0f);
        L9:
            r3.measure(View.MeasureSpec.makeMeasureSpec((r82 - r4.rightMargin) - r4.leftMargin, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r5, TLObject.FLAG_30));
            r2 = r2 + 1;
            goto L3
        }
    }

    /* renamed from: org.telegram.ui.LoginActivity$5, reason: invalid class name */
    public class AnonymousClass5 extends AnimatorListenerAdapter {
        final /* synthetic */ LoginActivity this$0;

        public AnonymousClass5(LoginActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (LoginActivity.m15792$$Nest$fgetkeyboardAnimator(this.this$0) != r2) goto L6;
            LoginActivity.m15814$$Nest$fputkeyboardAnimator(this.this$0, null);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r1) {
            LoginActivity.m15794$$Nest$fgetkeyboardView(this.this$0).setVisibility(0);
        }
    }

    /* renamed from: org.telegram.ui.LoginActivity$6, reason: invalid class name */
    public class AnonymousClass6 extends AnimatorListenerAdapter {
        final /* synthetic */ LoginActivity this$0;

        public AnonymousClass6(LoginActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            LoginActivity.m15794$$Nest$fgetkeyboardView(this.this$0).setVisibility(8);
            if (LoginActivity.m15792$$Nest$fgetkeyboardAnimator(this.this$0) != r3) goto L6;
            LoginActivity.m15814$$Nest$fputkeyboardAnimator(this.this$0, null);
            return;
        }
    }

    /* renamed from: org.telegram.ui.LoginActivity$7, reason: invalid class name */
    public class AnonymousClass7 implements TextWatcher {
        final /* synthetic */ LoginActivity this$0;
        final /* synthetic */ EditText val$editText;
        final /* synthetic */ AtomicReference val$timeoutCallbackRef;

        public static /* synthetic */ void $r8$lambda$GcVof4okILFaXZf2e9OyQsopVQo(AnonymousClass7 r0, EditText r1, AtomicReference r2) {
            r0.lambda$beforeTextChanged$0(r1, r2);
        }

        public AnonymousClass7(LoginActivity r1, EditText r2, AtomicReference r3) {
            this.this$0 = r1;
            this.val$editText = r2;
            this.val$timeoutCallbackRef = r3;
        }

        private /* synthetic */ void lambda$beforeTextChanged$0(EditText r1, AtomicReference r2) {
            r1.removeTextChangedListener(this);
            r1.removeCallbacks((Runnable) r2.get());
            ((Runnable) r2.get()).run();
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r1) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
            EditText r12 = this.val$editText;
            r12.post(new LoginActivity$7$$ExternalSyntheticLambda0(this, r12, this.val$timeoutCallbackRef));
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.LoginActivity$8, reason: invalid class name */
    public class AnonymousClass8 extends AnimatorListenerAdapter {
        final /* synthetic */ LoginActivity this$0;
        final /* synthetic */ boolean val$floating;
        final /* synthetic */ boolean val$show;

        public AnonymousClass8(LoginActivity r1, boolean r2, boolean r3) {
            this.this$0 = r1;
            this.val$floating = r2;
            this.val$show = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r3) {
            if (LoginActivity.m15801$$Nest$fgetshowDoneAnimation(this.this$0)[!this.val$floating ? 1 : 0] != null) goto L5;
            return;
        L5:
            if (LoginActivity.m15801$$Nest$fgetshowDoneAnimation(this.this$0)[!this.val$floating ? 1 : 0].equals(r3) == false) goto L9;
            LoginActivity.m15801$$Nest$fgetshowDoneAnimation(this.this$0)[!this.val$floating ? 1 : 0] = null;
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            if (LoginActivity.m15801$$Nest$fgetshowDoneAnimation(this.this$0)[!this.val$floating ? 1 : 0] != null) goto L5;
            return;
        L5:
            if (LoginActivity.m15801$$Nest$fgetshowDoneAnimation(this.this$0)[!this.val$floating ? 1 : 0].equals(r3) == true) goto L7;
            return;
        L7:
            if (this.val$show == false) goto L9;
            return;
        L9:
            if (this.val$floating == false) goto L12;
            LoginActivity.m15788$$Nest$fgetfloatingButton(this.this$0).setVisibility(8);
        L12:
            if (this.val$floating == true) goto L14;
            return;
        L14:
            if (LoginActivity.m15789$$Nest$fgetfloatingButtonIcon(this.this$0).getAlpha() == 1.0f) goto L21;
            LoginActivity.m15789$$Nest$fgetfloatingButtonIcon(this.this$0).setAlpha(1.0f);
            LoginActivity.m15789$$Nest$fgetfloatingButtonIcon(this.this$0).setScaleX(1.0f);
            LoginActivity.m15789$$Nest$fgetfloatingButtonIcon(this.this$0).setScaleY(1.0f);
            LoginActivity.m15789$$Nest$fgetfloatingButtonIcon(this.this$0).setVisibility(0);
            return;
        }
    }

    /* renamed from: org.telegram.ui.LoginActivity$9, reason: invalid class name */
    public class AnonymousClass9 extends AnimatorListenerAdapter {
        final /* synthetic */ LoginActivity this$0;
        final /* synthetic */ boolean val$show;

        public AnonymousClass9(LoginActivity r1, boolean r2) {
            this.this$0 = r1;
            this.val$show = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            if (this.val$show == true) goto L6;
            LoginActivity.m15799$$Nest$fgetradialProgressView(this.this$0).setVisibility(4);
        L6:
            if (LoginActivity.m15784$$Nest$fgetdoneItemAnimation(this.this$0) != null) goto L8;
            return;
        L8:
            if (LoginActivity.m15784$$Nest$fgetdoneItemAnimation(this.this$0).equals(r3) == false) goto L12;
            LoginActivity.m15811$$Nest$fputdoneItemAnimation(this.this$0, null);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r1) {
            if (this.val$show == false) goto L6;
            LoginActivity.m15799$$Nest$fgetradialProgressView(this.this$0).setVisibility(0);
            return;
        }
    }

    public class LoadingTextView extends TextView {
        public final LoadingDrawable loadingDrawable;
        private final Drawable rippleDrawable;
        final /* synthetic */ LoginActivity this$0;

        public LoadingTextView(LoginActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            Drawable r12 = Theme.createSelectorDrawable(Theme.multAlpha(Theme.getColor(Theme.key_windowBackgroundWhiteValueText), 0.1f), 7);
            this.rippleDrawable = r12;
            LoadingDrawable r22 = new LoadingDrawable();
            this.loadingDrawable = r22;
            r12.setCallback(this);
            r22.setAppearByGradient(true);
            r22.setSpeed(0.8f);
        }

        private void updateLoadingLayout() {
            Layout r0 = getLayout();
            if (r0 == null) goto L10;
            CharSequence r1 = r0.getText();
            if (r1 != null) goto L8;
            return;
        L8:
            LinkPath r2 = new LinkPath(true);
            r2.setInset(AndroidUtilities.dp(3.0f), AndroidUtilities.dp(6.0f));
            int r12 = r1.length();
            r2.setCurrentLayout(r0, 0, 0.0f);
            r0.getSelectionPath(0, r12, r2);
            RectF r02 = AndroidUtilities.rectTmp;
            r2.getBounds(r02);
            this.rippleDrawable.setBounds((int) r02.left, (int) r02.top, (int) r02.right, (int) r02.bottom);
            this.loadingDrawable.usePath(r2);
            this.loadingDrawable.setRadiiDp(4.0f);
            int r03 = this.this$0.getThemedColor(Theme.key_chat_linkSelectBackground);
            this.loadingDrawable.setColors(Theme.multAlpha(r03, 0.85f), Theme.multAlpha(r03, 2.0f), Theme.multAlpha(r03, 3.5f), Theme.multAlpha(r03, 6.0f));
            this.loadingDrawable.updateBounds();
            return;
        }

        public boolean isResendingCode() {
            return false;
        }

        public boolean isRippleEnabled() {
            return true;
        }

        @Override // android.widget.TextView, android.view.View
        public void onDraw(Canvas r4) {
            r4.save();
            if ((getGravity() & 16) != 0) goto L5;
        L7:
            float r0 = getPaddingTop();
        L8:
            r4.translate(getPaddingLeft(), r0);
            this.rippleDrawable.draw(r4);
            r4.restore();
            super.onDraw(r4);
            if (isResendingCode() == false) goto L11;
        L14:
            r4.save();
            r4.translate(getPaddingLeft(), r0);
            this.loadingDrawable.draw(r4);
            r4.restore();
            invalidate();
            return;
        L11:
            if (this.loadingDrawable.isDisappearing() == true) goto L14;
            return;
        L5:
            if (getLayout() == null) goto L7;
            r0 = getPaddingTop() + ((((getHeight() - getPaddingTop()) - getPaddingBottom()) - getLayout().getHeight()) / 2.0f);
            goto L8
        }

        @Override // android.widget.TextView, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            updateLoadingLayout();
        }

        @Override // android.widget.TextView, android.view.View
        public boolean onTouchEvent(MotionEvent r4) {
            if (isRippleEnabled() == false) goto L8;
            if (r4.getAction() != 0) goto L8;
            this.rippleDrawable.setHotspot(r4.getX(), r4.getY());
            this.rippleDrawable.setState(new int[]{R.attr.state_enabled, R.attr.state_pressed});
        L13:
            return super.onTouchEvent(r4);
        L8:
            if (r4.getAction() != 1) goto L10;
        L11:
            this.rippleDrawable.setState(new int[0]);
            goto L13
        L10:
            if (r4.getAction() != 1) goto L13;
            goto L11
        }

        @Override // android.widget.TextView
        public void setText(CharSequence r1, TextView.BufferType r2) {
            super.setText(r1, r2);
            updateLoadingLayout();
        }

        @Override // android.widget.TextView, android.view.View
        public boolean verifyDrawable(Drawable r2) {
            if (r2 != this.rippleDrawable) goto L5;
            return true;
        L5:
            if (super.verifyDrawable(r2) == true) goto L11;
            return false;
        L11:
            return true;
        }
    }

    public class LoginActivityEmailCodeView extends SlideView {
        private FrameLayout cantAccessEmailFrameLayout;
        private TextView cantAccessEmailView;
        private CodeFieldContainer codeFieldContainer;
        private TextView confirmTextView;
        private Bundle currentParams;
        private String email;
        private String emailPhone;
        private TextView emailResetInView;
        private Runnable errorColorTimeout;
        private ViewSwitcher errorViewSwitcher;
        private GoogleSignInAccount googleAccount;
        private RLottieImageView inboxImageView;
        private boolean isFromSetup;
        private boolean isSetup;
        private int length;
        private LoginOrView loginOrView;
        private boolean nextPressed;
        private String phone;
        private String phoneHash;
        private boolean postedErrorColorTimeout;
        private String requestPhone;
        private boolean requestingEmailReset;
        private Runnable resendCodeTimeout;
        private TextView resendCodeView;
        private FrameLayout resendFrameLayout;
        private int resetAvailablePeriod;
        private int resetPendingDate;
        private boolean resetRequestPending;
        private TextView signInWithGoogleView;
        final /* synthetic */ LoginActivity this$0;
        private TextView titleView;
        private Runnable updateResetPendingDateCallback;
        private TextView wrongCodeView;

        /* renamed from: org.telegram.ui.LoginActivity$LoginActivityEmailCodeView$1, reason: invalid class name */
        public class AnonymousClass1 extends CodeFieldContainer {
            final /* synthetic */ LoginActivityEmailCodeView this$1;
            final /* synthetic */ LoginActivity val$this$0;

            public AnonymousClass1(LoginActivityEmailCodeView r1, Context r2, LoginActivity r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            @Override // org.telegram.ui.CodeFieldContainer
            public void processNextPressed() {
                this.this$1.onNextPressed(null);
            }
        }

        /* renamed from: org.telegram.ui.LoginActivity$LoginActivityEmailCodeView$2, reason: invalid class name */
        public class AnonymousClass2 extends ReplacementSpan {
            final /* synthetic */ LoginActivityEmailCodeView this$1;
            final /* synthetic */ LoginActivity val$this$0;

            public AnonymousClass2(LoginActivityEmailCodeView r1, LoginActivity r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // android.text.style.ReplacementSpan
            public void draw(Canvas r1, CharSequence r2, int r3, int r4, float r5, int r6, int r7, int r8, Paint r9) {
            }

            @Override // android.text.style.ReplacementSpan
            public int getSize(Paint r1, CharSequence r2, int r3, int r4, Paint.FontMetricsInt r5) {
                return AndroidUtilities.dp(12.0f);
            }
        }

        /* renamed from: org.telegram.ui.LoginActivity$LoginActivityEmailCodeView$3, reason: invalid class name */
        public class AnonymousClass3 implements NotificationCenter.NotificationCenterDelegate {
            final /* synthetic */ LoginActivityEmailCodeView this$1;

            public AnonymousClass3(LoginActivityEmailCodeView r1) {
                this.this$1 = r1;
            }

            @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
            public void didReceivedNotification(int r2, int r3, Object... r4) {
                int r22 = ((Integer) r4[0]).intValue();
                ((Integer) r4[1]).getClass();
                Intent r32 = (Intent) r4[2];
                NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.onActivityResultReceived);
                if (r22 != 200) goto L11;
                LoginActivityEmailCodeView.m15854$$Nest$fputgoogleAccount(this.this$1, GoogleSignIn.getSignedInAccountFromIntent(r32).getResult(ApiException.class));     // Catch: ApiException -> L6
                this.this$1.onNextPressed(null);     // Catch: ApiException -> L6
                return;
            L6:
                e = move-exception;
                FileLog.e(e);
                return;
            }
        }

        /* renamed from: org.telegram.ui.LoginActivity$LoginActivityEmailCodeView$4, reason: invalid class name */
        public class AnonymousClass4 extends TextView {
            final /* synthetic */ LoginActivityEmailCodeView this$1;
            final /* synthetic */ LoginActivity val$this$0;

            public AnonymousClass4(LoginActivityEmailCodeView r1, Context r2, LoginActivity r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            @Override // android.widget.TextView, android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(100.0f), Integer.MIN_VALUE));
            }
        }

        /* renamed from: org.telegram.ui.LoginActivity$LoginActivityEmailCodeView$5, reason: invalid class name */
        public class AnonymousClass5 extends TextView {
            final /* synthetic */ LoginActivityEmailCodeView this$1;
            final /* synthetic */ LoginActivity val$this$0;

            public AnonymousClass5(LoginActivityEmailCodeView r1, Context r2, LoginActivity r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            @Override // android.widget.TextView, android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(Math.max(View.MeasureSpec.getSize(r3), AndroidUtilities.dp(100.0f)), Integer.MIN_VALUE));
            }
        }

        /* renamed from: org.telegram.ui.LoginActivity$LoginActivityEmailCodeView$6, reason: invalid class name */
        public class AnonymousClass6 extends ViewSwitcher {
            final /* synthetic */ LoginActivityEmailCodeView this$1;
            final /* synthetic */ LoginActivity val$this$0;

            public AnonymousClass6(LoginActivityEmailCodeView r1, Context r2, LoginActivity r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            @Override // android.widget.FrameLayout, android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(100.0f), Integer.MIN_VALUE));
            }
        }

        /* renamed from: org.telegram.ui.LoginActivity$LoginActivityEmailCodeView$7, reason: invalid class name */
        public class AnonymousClass7 implements TextWatcher {
            final /* synthetic */ LoginActivityEmailCodeView this$1;

            public AnonymousClass7(LoginActivityEmailCodeView r1) {
                this.this$1 = r1;
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable r1) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
                if (LoginActivityEmailCodeView.m15853$$Nest$fgetpostedErrorColorTimeout(this.this$1) == false) goto L6;
                LoginActivityEmailCodeView r12 = this.this$1;
                r12.removeCallbacks(LoginActivityEmailCodeView.m15852$$Nest$fgeterrorColorTimeout(r12));
                LoginActivityEmailCodeView.m15852$$Nest$fgeterrorColorTimeout(this.this$1).run();
                return;
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }
        }

        /* renamed from: $r8$lambda$-IHZ2YrSDaihLYJqUVVQPBc9qQ8, reason: not valid java name */
        public static /* synthetic */ void m15839$r8$lambda$IHZ2YrSDaihLYJqUVVQPBc9qQ8(LoginActivityEmailCodeView r0, TLRPC.TL_error r1, String r2, TLObject r3) {
            r0.lambda$onNextPressed$22(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$-a8QOyScpj3qU7Nz_hMzrAm3BMk, reason: not valid java name */
        public static /* synthetic */ void m15840$r8$lambda$a8QOyScpj3qU7Nz_hMzrAm3BMk(LoginActivityEmailCodeView r0, Bundle r1) {
            r0.lambda$onNextPressed$17(r1);
        }

        /* renamed from: $r8$lambda$3vYOFCLUVn4GOrcJ-ZfRxhTZb7Y, reason: not valid java name */
        public static /* synthetic */ void m15841$r8$lambda$3vYOFCLUVn4GOrcJZfRxhTZb7Y(LoginActivityEmailCodeView r0, GoogleSignInClient r1, Task r2) {
            r0.lambda$new$2(r1, r2);
        }

        /* renamed from: $r8$lambda$7OKc5Tf6DbtdHReJm0GUJ-ct-hE, reason: not valid java name */
        public static /* synthetic */ void m15842$r8$lambda$7OKc5Tf6DbtdHReJm0GUJcthE(LoginActivityEmailCodeView r0, TLObject r1, Bundle r2, TLRPC.TL_error r3, TLRPC.TL_auth_resetLoginEmail r4) {
            r0.lambda$new$4(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$8VGq9ZR0reDAAO0Wj6NuaFM8Od0(LoginActivityEmailCodeView r0, AlertDialog r1, int r2) {
            r0.lambda$new$6(r1, r2);
        }

        /* renamed from: $r8$lambda$AMfs4hj-CS0PTs21H2j8BAMogEc, reason: not valid java name */
        public static /* synthetic */ void m15843$r8$lambda$AMfs4hjCS0PTs21H2j8BAMogEc(LoginActivityEmailCodeView r0, TLObject r1, Bundle r2, TLRPC.TL_error r3, TLRPC.TL_auth_resetLoginEmail r4) {
            r0.lambda$requestEmailReset$12(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$Clgvfm25HJk8WlWz7Lyr8ukdl7Q(LoginActivityEmailCodeView r0) {
            r0.lambda$onPasscodeError$15();
        }

        public static /* synthetic */ void $r8$lambda$HJJw0RoOx3R3h42J8RxzlL58Bes(LoginActivityEmailCodeView r0) {
            r0.updateResetPendingDate();
        }

        public static /* synthetic */ void $r8$lambda$KlqXUefPKQuEEULVtLMs5BpEUik(LoginActivityEmailCodeView r0, TLObject r1, Bundle r2, TLRPC.TL_error r3, TLRPC.TL_auth_resendCode r4) {
            r0.lambda$new$9(r1, r2, r3, r4);
        }

        /* renamed from: $r8$lambda$OGBQb-_JHH8h0R4_s14GTCUVHqA, reason: not valid java name */
        public static /* synthetic */ void m15844$r8$lambda$OGBQb_JHH8h0R4_s14GTCUVHqA(LoginActivityEmailCodeView r0, View r1) {
            r0.lambda$new$3(r1);
        }

        public static /* synthetic */ void $r8$lambda$PJT8tQE5A68nUKCPeg5fvzxNDGI(LoginActivityEmailCodeView r0, TLRPC.TL_error r1, TLObject r2, String r3) {
            r0.lambda$onNextPressed$20(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$QZirMf1MPT-kZ7_WsM6bTWKdqSY, reason: not valid java name */
        public static /* synthetic */ void m15845$r8$lambda$QZirMf1MPTkZ7_WsM6bTWKdqSY(LoginActivityEmailCodeView r0, Runnable r1) {
            r0.lambda$animateSuccess$25(r1);
        }

        public static /* synthetic */ void $r8$lambda$RxOfLzvaSTrsJc_6DiYkh49JjIg(LoginActivityEmailCodeView r0) {
            r0.lambda$onPasscodeError$16();
        }

        /* renamed from: $r8$lambda$TUvHv_SUu4Oiqgo7DiXK-8CrKu4, reason: not valid java name */
        public static /* synthetic */ void m15846$r8$lambda$TUvHv_SUu4Oiqgo7DiXK8CrKu4(LoginActivityEmailCodeView r0, int r1) {
            r0.lambda$animateSuccess$24(r1);
        }

        /* renamed from: $r8$lambda$U-GXGgO8bBbTrxldx70lTbfv3bY, reason: not valid java name */
        public static /* synthetic */ void m15847$r8$lambda$UGXGgO8bBbTrxldx70lTbfv3bY(LoginActivityEmailCodeView r0, Bundle r1, TLRPC.TL_auth_resetLoginEmail r2, TLObject r3, TLRPC.TL_error r4) {
            r0.lambda$requestEmailReset$13(r1, r2, r3, r4);
        }

        /* renamed from: $r8$lambda$UDLw-KI-WYbJwyUqpdLQkYoL3qM, reason: not valid java name */
        public static /* synthetic */ void m15848$r8$lambda$UDLwKIWYbJwyUqpdLQkYoL3qM(LoginActivityEmailCodeView r0, View r1, boolean r2) {
            r0.lambda$setParams$14(r1, r2);
        }

        /* renamed from: $r8$lambda$VgnWWi748UEx_E-mDjDQoF5SzzQ, reason: not valid java name */
        public static /* synthetic */ void m15849$r8$lambda$VgnWWi748UEx_EmDjDQoF5SzzQ(LoginActivityEmailCodeView r0) {
            r0.requestEmailReset();
        }

        public static /* synthetic */ void $r8$lambda$XLEMZkQUOQMISC0dI9UW9ZvktJw(LoginActivityEmailCodeView r0) {
            r0.lambda$shakeWrongCode$26();
        }

        /* renamed from: $r8$lambda$XV6dd-9L6haYXHDzvMWR4yMuh1k, reason: not valid java name */
        public static /* synthetic */ void m15850$r8$lambda$XV6dd9L6haYXHDzvMWR4yMuh1k(LoginActivityEmailCodeView r0, Bundle r1, TLRPC.TL_auth_resetLoginEmail r2, TLObject r3, TLRPC.TL_error r4) {
            r0.lambda$new$5(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$Y3nXXxvdhLic7ZfR88C_D1c3fSM(LoginActivityEmailCodeView r0, Bundle r1) {
            r0.lambda$onNextPressed$19(r1);
        }

        public static /* synthetic */ void $r8$lambda$Z_kdeqEqCVpwA_guWrk1zGogxPc(LoginActivityEmailCodeView r0) {
            r0.lambda$new$0();
        }

        public static /* synthetic */ void $r8$lambda$Zce9rStKL8IivmqaRm_ZQXHRdfM(LoginActivityEmailCodeView r0, String r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$onNextPressed$21(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$_PVf1uHcKvfxPfMaRJPHjGOnU2E(LoginActivityEmailCodeView r0, View r1) {
            r0.lambda$new$8(r1);
        }

        public static /* synthetic */ void $r8$lambda$esDAVsibfjlfa1_S10VzeWZ6k9k(LoginActivityEmailCodeView r0) {
            r0.lambda$shakeWrongCode$27();
        }

        public static /* synthetic */ void $r8$lambda$gGLlfywUmp83w7ZH8CwjPZvDiNs(LoginActivityEmailCodeView r0) {
            r0.lambda$onShow$28();
        }

        public static /* synthetic */ void $r8$lambda$h5pjEm1xQca9b7dtBG2yltOKoI0(LoginActivityEmailCodeView r0, TLObject r1, Bundle r2) {
            r0.lambda$onNextPressed$18(r1, r2);
        }

        /* renamed from: $r8$lambda$ifY8-k0D37YaqFNvekoaqOQM7hA, reason: not valid java name */
        public static /* synthetic */ void m15851$r8$lambda$ifY8k0D37YaqFNvekoaqOQM7hA(LoginActivityEmailCodeView r0) {
            r0.lambda$new$1();
        }

        public static /* synthetic */ void $r8$lambda$lZPZcj4JdRYueyQZcmU6N9lJtmQ(LoginActivityEmailCodeView r0, String r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$onNextPressed$23(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$rPYm89QzbiVv4q0OKhAINcEaHvg(LoginActivityEmailCodeView r0, View r1) {
            r0.lambda$new$11(r1);
        }

        public static /* synthetic */ void $r8$lambda$u7zgxDzBuy5naELmmc172Z2yFTE(LoginActivityEmailCodeView r0, Bundle r1, TLRPC.TL_auth_resendCode r2, TLObject r3, TLRPC.TL_error r4) {
            r0.lambda$new$10(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$vL0nB6Q7oxlDJkVaq4z61bNxfJA(LoginActivityEmailCodeView r0, Context r1, View r2) {
            r0.lambda$new$7(r1, r2);
        }

        /* renamed from: -$$Nest$fgeterrorColorTimeout, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Runnable m15852$$Nest$fgeterrorColorTimeout(LoginActivityEmailCodeView r0) {
            return r0.errorColorTimeout;
        }

        /* renamed from: -$$Nest$fgetpostedErrorColorTimeout, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m15853$$Nest$fgetpostedErrorColorTimeout(LoginActivityEmailCodeView r0) {
            return r0.postedErrorColorTimeout;
        }

        /* renamed from: -$$Nest$fputgoogleAccount, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15854$$Nest$fputgoogleAccount(LoginActivityEmailCodeView r0, GoogleSignInAccount r1) {
            r0.googleAccount = r1;
        }

        public LoginActivityEmailCodeView(LoginActivity r30, Context r31, boolean r32) {
            this.this$0 = r30;
            super(r31);
            this.errorColorTimeout = new LoginActivity$LoginActivityEmailCodeView$$ExternalSyntheticLambda2(this);
            this.resendCodeTimeout = new LoginActivity$LoginActivityEmailCodeView$$ExternalSyntheticLambda3(this);
            this.updateResetPendingDateCallback = new LoginActivity$LoginActivityEmailCodeView$$ExternalSyntheticLambda4(this);
            this.isSetup = r32;
            setOrientation(1);
            FrameLayout r5 = new FrameLayout(r31);
            this.inboxImageView = new RLottieImageView(r31);
            if (r32 == true) goto L5;
        L8:
            this.inboxImageView.setAnimation(org.telegram.messenger.R.raw.email_check_inbox, 120, 120);
        L9:
            this.inboxImageView.setAutoRepeat(false);
            r5.addView(this.inboxImageView, LayoutHelper.createFrame(120, 120, 1));
            if (AndroidUtilities.isSmallScreen() == true) goto L17;
            Point r7 = AndroidUtilities.displaySize;
            if (r7.x > r7.y) goto L14;
        L16:
            int r72 = 0;
        L18:
            r5.setVisibility(r72);
            addView(r5, LayoutHelper.createFrame(-1, -2, 1));
            TextView r52 = new TextView(r31);
            this.titleView = r52;
            r52.setTextSize(1, 18.0f);
            this.titleView.setTypeface(AndroidUtilities.bold());
            TextView r53 = this.titleView;
            if (LoginActivity.m15776$$Nest$fgetactivityMode(r30) != 3) goto L21;
            int r12 = org.telegram.messenger.R.string.CheckYourNewEmail;
        L24:
            r53.setText(LocaleController.getString(r12));
            this.titleView.setGravity(17);
            this.titleView.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            addView(this.titleView, LayoutHelper.createFrame(-1, -2.0f, 1, 32.0f, 16.0f, 32.0f, 0.0f));
            SpoilersTextView r54 = new SpoilersTextView(r31, false);
            this.confirmTextView = r54;
            r54.setTextSize(1, 14.0f);
            this.confirmTextView.setGravity(17);
            this.confirmTextView.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            addView(this.confirmTextView, LayoutHelper.createLinear(-2, -2, 1, 24, 8, 24, 0));
            AnonymousClass1 r55 = new AnonymousClass1(this, r31, r30);
            this.codeFieldContainer = r55;
            if (r32 == false) goto L27;
            int r22 = 48;
        L28:
            addView(r55, LayoutHelper.createLinear(-2, 42, 1, 0, r22, 0, 0));
            TextView r56 = new TextView(r31);
            this.signInWithGoogleView = r56;
            r56.setGravity(17);
            this.signInWithGoogleView.setTextSize(1, 14.0f);
            this.signInWithGoogleView.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            this.signInWithGoogleView.setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f));
            this.signInWithGoogleView.setMaxLines(2);
            SpannableStringBuilder r57 = new SpannableStringBuilder("d ");
            Drawable r10 = ContextCompat.getDrawable(r31, R$drawable.googleg_standard_color_18);
            r10.setBounds(0, AndroidUtilities.dp(9.0f), AndroidUtilities.dp(18.0f), AndroidUtilities.dp(27.0f));
            r57.setSpan(new ImageSpan(r10, 0), 0, 1, 33);
            r57.setSpan(new AnonymousClass2(this, r30), 1, 2, 33);
            r57.append(LocaleController.getString(org.telegram.messenger.R.string.SignInWithGoogle));
            this.signInWithGoogleView.setText(r57);
            this.signInWithGoogleView.setOnClickListener(new LoginActivity$LoginActivityEmailCodeView$$ExternalSyntheticLambda5(this));
            FrameLayout r58 = new FrameLayout(r31);
            this.cantAccessEmailFrameLayout = r58;
            if (LoginActivity.m15776$$Nest$fgetactivityMode(r30) != 3) goto L31;
        L33:
            boolean r8 = false;
        L34:
            AndroidUtilities.updateViewVisibilityAnimated(r58, r8, 1.0f, false);
            AnonymousClass4 r59 = new AnonymousClass4(this, r31, r30);
            this.cantAccessEmailView = r59;
            r59.setText(LocaleController.getString(org.telegram.messenger.R.string.LoginCantAccessThisEmail));
            this.cantAccessEmailView.setGravity(17);
            this.cantAccessEmailView.setTextSize(1, 14.0f);
            this.cantAccessEmailView.setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f));
            this.cantAccessEmailView.setMaxLines(2);
            this.cantAccessEmailView.setOnClickListener(new LoginActivity$LoginActivityEmailCodeView$$ExternalSyntheticLambda6(this, r31));
            this.cantAccessEmailFrameLayout.addView(this.cantAccessEmailView);
            AnonymousClass5 r510 = new AnonymousClass5(this, r31, r30);
            this.emailResetInView = r510;
            r510.setGravity(17);
            this.emailResetInView.setTextSize(1, 14.0f);
            this.emailResetInView.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            this.emailResetInView.setMaxLines(3);
            this.emailResetInView.setOnClickListener(new LoginActivity$LoginActivityEmailCodeView$$ExternalSyntheticLambda7(this));
            this.emailResetInView.setPadding(0, AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(16.0f));
            this.emailResetInView.setVisibility(8);
            this.cantAccessEmailFrameLayout.addView(this.emailResetInView);
            TextView r511 = new TextView(r31);
            this.resendCodeView = r511;
            r511.setGravity(17);
            this.resendCodeView.setTextSize(1, 14.0f);
            this.resendCodeView.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            this.resendCodeView.setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f));
            this.resendCodeView.setMaxLines(2);
            this.resendCodeView.setText(LocaleController.getString(org.telegram.messenger.R.string.ResendCode));
            this.resendCodeView.setOnClickListener(new LoginActivity$LoginActivityEmailCodeView$$ExternalSyntheticLambda8(this));
            AndroidUtilities.updateViewVisibilityAnimated(this.resendCodeView, false, 1.0f, false);
            LoginOrView r512 = new LoginOrView(r31);
            this.loginOrView = r512;
            VerticalPositionAutoAnimator.attach(r512);
            this.errorViewSwitcher = new AnonymousClass6(this, r31, r30);
            Animation r1 = AnimationUtils.loadAnimation(r31, org.telegram.messenger.R.anim.text_in);
            Interpolator r513 = Easings.easeInOutQuad;
            r1.setInterpolator(r513);
            this.errorViewSwitcher.setInAnimation(r1);
            Animation r13 = AnimationUtils.loadAnimation(r31, org.telegram.messenger.R.anim.text_out);
            r13.setInterpolator(r513);
            this.errorViewSwitcher.setOutAnimation(r13);
            FrameLayout r14 = new FrameLayout(r31);
            this.resendFrameLayout = r14;
            r14.addView(this.resendCodeView, LayoutHelper.createFrame(-2, -2, 17));
            this.errorViewSwitcher.addView(this.resendFrameLayout);
            TextView r15 = new TextView(r31);
            this.wrongCodeView = r15;
            r15.setText(LocaleController.getString("WrongCode", org.telegram.messenger.R.string.WrongCode));
            this.wrongCodeView.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            this.wrongCodeView.setTextSize(1, 15.0f);
            this.wrongCodeView.setGravity(49);
            this.wrongCodeView.setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f));
            this.errorViewSwitcher.addView(this.wrongCodeView);
            FrameLayout r16 = new FrameLayout(r31);
            ViewSwitcher r2 = this.errorViewSwitcher;
            if (r32 == false) goto L38;
            r16.addView(r2, LayoutHelper.createFrame(-1, -2.0f, 80, 0.0f, 0.0f, 0.0f, 32.0f));
        L39:
            addView(r16, LayoutHelper.createLinear(-1, 0, 1.0f));
            return;
        L38:
            r16.addView(r2, LayoutHelper.createFrame(-1, -2, 48));
            r16.addView(this.cantAccessEmailFrameLayout, LayoutHelper.createFrame(-1, -2, 48));
            r16.addView(this.loginOrView, LayoutHelper.createFrame(-1, 16.0f, 17, 0.0f, 0.0f, 0.0f, 16.0f));
            r16.addView(this.signInWithGoogleView, LayoutHelper.createFrame(-1, -2.0f, 80, 0.0f, 0.0f, 0.0f, 16.0f));
            goto L39
        L31:
            if (this.isSetup == true) goto L33;
            r8 = true;
            goto L34
        L27:
            r22 = 32;
            goto L28
        L21:
            if (r32 == false) goto L23;
            r12 = org.telegram.messenger.R.string.VerificationCode;
            goto L24
        L23:
            r12 = org.telegram.messenger.R.string.CheckYourEmail;
            goto L24
        L14:
            if (AndroidUtilities.isTablet() == true) goto L16;
        L17:
            r72 = 8;
            goto L18
        L5:
            if (LoginActivity.m15776$$Nest$fgetactivityMode(r30) == 3) goto L8;
            this.inboxImageView.setAnimation(org.telegram.messenger.R.raw.email_setup_heart, 120, 120);
            goto L9
        }

        private void animateSuccess(Runnable r8) {
            if (this.googleAccount == null) goto L6;
            r8.run();
            return;
        L6:
            int r0 = 0;
        L7:
            CodeFieldContainer r1 = this.codeFieldContainer;
            if (r0 >= r1.codeField.length) goto L10;
            r1.postDelayed(new LoginActivity$LoginActivityEmailCodeView$$ExternalSyntheticLambda26(this, r0), r0 * 75);
            r0 = r0 + 1;
            goto L7
        L10:
            r1.postDelayed(new LoginActivity$LoginActivityEmailCodeView$$ExternalSyntheticLambda27(this, r8), (this.codeFieldContainer.codeField.length * 75) + 400);
        }

        private String getTimePattern(int r6) {
            int r0 = r6 / 86400;
            int r62 = r6 % 86400;
            int r5 = r62 / 3600;
            int r63 = (r62 % 3600) / 60;
            if (r0 != 0) goto L7;
            if (r5 != 0) goto L7;
            r63 = Math.max(1, r63);
        L7:
            if (r0 == 0) goto L12;
            if (r5 == 0) goto L12;
            return LocaleController.formatString(org.telegram.messenger.R.string.LoginEmailResetInDoublePattern, new Object[]{LocaleController.formatPluralString("Days", r0, new Object[0]), LocaleController.formatPluralString("Hours", r5, new Object[0])});
        L12:
            if (r5 == 0) goto L16;
            if (r63 == 0) goto L16;
            return LocaleController.formatString(org.telegram.messenger.R.string.LoginEmailResetInDoublePattern, new Object[]{LocaleController.formatPluralString("Hours", r5, new Object[0]), LocaleController.formatPluralString("Minutes", r63, new Object[0])});
        L16:
            if (r0 != 0) goto L18;
            if (r5 == 0) goto L23;
            return LocaleController.formatString(org.telegram.messenger.R.string.LoginEmailResetInSinglePattern, new Object[]{LocaleController.formatPluralString("Hours", r0, new Object[0])});
        L23:
            return LocaleController.formatString(org.telegram.messenger.R.string.LoginEmailResetInSinglePattern, new Object[]{LocaleController.formatPluralString("Minutes", r63, new Object[0])});
        L18:
            return LocaleController.formatString(org.telegram.messenger.R.string.LoginEmailResetInSinglePattern, new Object[]{LocaleController.formatPluralString("Days", r0, new Object[0])});
        }

        private String getTimePatternForTimer(int r5) {
            int r0 = r5 / 86400;
            int r52 = r5 % 86400;
            int r4 = r52 / 3600;
            int r53 = r52 % 3600;
            int r1 = r53 / 60;
            int r54 = r53 % 60;
            if (r4 < 16) goto L5;
            r0 = r0 + 1;
        L5:
            if (r0 != 0) goto L7;
            StringBuilder r02 = new StringBuilder();
            if (r4 == 0) goto L11;
            String r42 = String.format(Locale.ROOT, "%02d:", new Object[]{Integer.valueOf(r4)});
        L12:
            r02.append(r42);
            Locale r43 = Locale.ROOT;
            r02.append(String.format(r43, "%02d:", new Object[]{Integer.valueOf(r1)}));
            r02.append(String.format(r43, TimeModel.ZERO_LEADING_NUMBER_FORMAT, new Object[]{Integer.valueOf(r54)}));
            String r44 = r02.toString();
            return LocaleController.formatString(org.telegram.messenger.R.string.LoginEmailResetInSinglePattern, new Object[]{r44});
        L11:
            r42 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L12
        L7:
            return LocaleController.formatString(org.telegram.messenger.R.string.LoginEmailResetInSinglePattern, new Object[]{LocaleController.formatPluralString("Days", r0, new Object[0])});
        }

        private /* synthetic */ void lambda$animateSuccess$24(int r1) {
            this.codeFieldContainer.codeField[r1].animateSuccessProgress(1.0f);
        }

        private /* synthetic */ void lambda$animateSuccess$25(Runnable r5) {
            int r1 = 0;
        L3:
            CodeNumberField[] r2 = this.codeFieldContainer.codeField;
            if (r1 >= r2.length) goto L6;
            r2[r1].animateSuccessProgress(0.0f);
            r1 = r1 + 1;
            goto L3
        L6:
            r5.run();
            this.codeFieldContainer.isFocusSuppressed = false;
        }

        private /* synthetic */ void lambda$new$0() {
            boolean r0 = false;
            this.postedErrorColorTimeout = false;
            int r1 = 0;
        L3:
            CodeNumberField[] r2 = this.codeFieldContainer.codeField;
            if (r1 >= r2.length) goto L7;
            r2[r1].animateErrorProgress(0.0f);
            r1 = r1 + 1;
            goto L3
        L7:
            if (this.errorViewSwitcher.getCurrentView() == this.resendFrameLayout) goto L18;
            this.errorViewSwitcher.showNext();
            FrameLayout r12 = this.cantAccessEmailFrameLayout;
            if (this.resendCodeView.getVisibility() != 0) goto L11;
        L15:
            AndroidUtilities.updateViewVisibilityAnimated(r12, r0, 1.0f, true);
            return;
        L11:
            if (LoginActivity.m15776$$Nest$fgetactivityMode(this.this$0) == 3) goto L15;
            if (this.isSetup == true) goto L15;
            r0 = true;
            goto L15
        }

        private /* synthetic */ void lambda$new$1() {
            showResendCodeView(true);
        }

        private /* synthetic */ void lambda$new$10(Bundle r7, TLRPC.TL_auth_resendCode r8, TLObject r9, TLRPC.TL_error r10) {
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivityEmailCodeView$$ExternalSyntheticLambda18(this, r9, r7, r10, r8));
        }

        private /* synthetic */ void lambda$new$11(View r4) {
            if (this.resendCodeView.getVisibility() == 0) goto L5;
            return;
        L5:
            if (this.resendCodeView.getAlpha() != 1.0f) goto L10;
            showResendCodeView(false);
            TLRPC.TL_auth_resendCode r42 = new TLRPC.TL_auth_resendCode();
            r42.phone_number = this.requestPhone;
            r42.phone_code_hash = this.phoneHash;
            Bundle r0 = new Bundle();
            r0.putString("phone", this.phone);
            r0.putString("ephone", this.emailPhone);
            r0.putString("phoneFormated", this.requestPhone);
            ConnectionsManager.getInstance(LoginActivity.access$3200(this.this$0)).sendRequest(r42, new LoginActivity$LoginActivityEmailCodeView$$ExternalSyntheticLambda10(this, r0, r42), 10);
            return;
        }

        private /* synthetic */ void lambda$new$2(GoogleSignInClient r1, Task r2) {
            if (this.this$0.getParentActivity() != null) goto L5;
            return;
        L5:
            this.this$0.getParentActivity().startActivityForResult(r1.getSignInIntent(), 200);
        }

        private /* synthetic */ void lambda$new$3(View r3) {
            NotificationCenter.getGlobalInstance().addObserver(new AnonymousClass3(this), NotificationCenter.onActivityResultReceived);
            GoogleSignInClient r32 = GoogleSignIn.getClient(getContext(), new GoogleSignInOptions.Builder().requestIdToken(BuildVars.GOOGLE_AUTH_CLIENT_ID).requestEmail().build());
            r32.signOut().addOnCompleteListener(new LoginActivity$LoginActivityEmailCodeView$$ExternalSyntheticLambda11(this, r32));
        }

        private /* synthetic */ void lambda$new$4(TLObject r3, Bundle r4, TLRPC.TL_error r5, TLRPC.TL_auth_resetLoginEmail r6) {
            if ((r3 instanceof TLRPC.TL_auth_sentCode) == false) goto L9;
            TLRPC.TL_auth_sentCode r32 = (TLRPC.TL_auth_sentCode) r3;
            TLRPC.auth_SentCodeType r52 = r32.type;
            if ((r52 instanceof TLRPC.TL_auth_sentCodeTypeEmailCode) == false) goto L7;
            r52.email_pattern = this.currentParams.getString("emailPattern");
            this.resetRequestPending = true;
        L7:
            LoginActivity.m15820$$Nest$mfillNextCodeParams(this.this$0, r4, r32);
            return;
        L9:
            if (r5 == null) goto L18;
            String r33 = r5.text;
            if (r33 != null) goto L13;
            return;
        L13:
            if (r33.contains("PHONE_CODE_EXPIRED") == false) goto L16;
            onBackPressed(true);
            this.this$0.setPage(0, true, null, true);
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString("CodeExpired", org.telegram.messenger.R.string.CodeExpired));
            return;
        L16:
            AlertsCreator.processError(LoginActivity.access$3400(this.this$0), r5, this.this$0, r6, new Object[0]);
            return;
        }

        private /* synthetic */ void lambda$new$5(Bundle r7, TLRPC.TL_auth_resetLoginEmail r8, TLObject r9, TLRPC.TL_error r10) {
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivityEmailCodeView$$ExternalSyntheticLambda28(this, r9, r7, r10, r8));
        }

        private /* synthetic */ void lambda$new$6(AlertDialog r3, int r4) {
            Bundle r32 = new Bundle();
            r32.putString("phone", this.phone);
            r32.putString("ephone", this.emailPhone);
            r32.putString("phoneFormated", this.requestPhone);
            TLRPC.TL_auth_resetLoginEmail r42 = new TLRPC.TL_auth_resetLoginEmail();
            r42.phone_number = this.requestPhone;
            r42.phone_code_hash = this.phoneHash;
            this.this$0.getConnectionsManager().sendRequest(r42, new LoginActivity$LoginActivityEmailCodeView$$ExternalSyntheticLambda19(this, r32, r42), 10);
        }

        private /* synthetic */ void lambda$new$7(Context r7, View r8) {
            String r82 = this.currentParams.getString("emailPattern");
            SpannableStringBuilder r0 = new SpannableStringBuilder(r82);
            int r2 = r82.indexOf(42);
            int r83 = r82.lastIndexOf(42);
            if (r2 != r83) goto L5;
        L8:
            new AlertDialog.Builder(r7).setTitle(LocaleController.getString(org.telegram.messenger.R.string.LoginEmailResetTitle)).setMessage(AndroidUtilities.formatSpannable(AndroidUtilities.replaceTags(LocaleController.getString(org.telegram.messenger.R.string.LoginEmailResetMessage)), new CharSequence[]{r0, getTimePattern(this.resetAvailablePeriod)})).setPositiveButton(LocaleController.getString(org.telegram.messenger.R.string.LoginEmailResetButton), new LoginActivity$LoginActivityEmailCodeView$$ExternalSyntheticLambda15(this)).setNegativeButton(LocaleController.getString(org.telegram.messenger.R.string.Cancel), null).show();
            return;
        L5:
            if (r2 == (-1)) goto L8;
            if (r83 == (-1)) goto L8;
            TextStyleSpan.TextStyleRun r4 = new TextStyleSpan.TextStyleRun();
            r4.flags |= 256;
            r4.start = r2;
            int r84 = r83 + 1;
            r4.end = r84;
            r0.setSpan(new TextStyleSpan(r4), r2, r84, 0);
            goto L8
        }

        private /* synthetic */ void lambda$new$8(View r1) {
            requestEmailReset();
        }

        private /* synthetic */ void lambda$new$9(TLObject r2, Bundle r3, TLRPC.TL_error r4, TLRPC.TL_auth_resendCode r5) {
            if ((r2 instanceof TLRPC.TL_auth_sentCode) == false) goto L6;
            LoginActivity r1 = this.this$0;
            LoginActivity.m15820$$Nest$mfillNextCodeParams(r1, r3, (TLRPC.TL_auth_sentCode) r2);
            return;
        L6:
            if (r4 != null) goto L8;
            return;
        L8:
            if (r4.text == null) goto L12;
            AlertsCreator.processError(LoginActivity.access$3300(this.this$0), r4, this.this$0, r5, new Object[0]);
            return;
        }

        private /* synthetic */ void lambda$onNextPressed$17(Bundle r4) {
            this.this$0.setPage(5, true, r4, false);
        }

        private /* synthetic */ void lambda$onNextPressed$18(TLObject r3, Bundle r4) {
            if ((r3 instanceof TL_account.TL_emailVerified) == false) goto L9;
            if (LoginActivity.m15776$$Nest$fgetactivityMode(this.this$0) != 3) goto L9;
            this.this$0.finishFragment();
            LoginActivity.m15785$$Nest$fgetemailChangeFinishCallback(this.this$0).run();
            return;
        L9:
            if ((r3 instanceof TL_account.TL_emailVerifiedLogin) == false) goto L13;
            LoginActivity.m15820$$Nest$mfillNextCodeParams(this.this$0, r4, ((TL_account.TL_emailVerifiedLogin) r3).sent_code);
            return;
        L13:
            if ((r3 instanceof TLRPC.TL_auth_authorization) == false) goto L16;
            LoginActivity.m15831$$Nest$monAuthSuccess(this.this$0, (TLRPC.TL_auth_authorization) r3);
            return;
        }

        private /* synthetic */ void lambda$onNextPressed$19(Bundle r4) {
            this.this$0.setPage(6, true, r4, false);
        }

        private /* synthetic */ void lambda$onNextPressed$20(TLRPC.TL_error r4, TLObject r5, String r6) {
            this.nextPressed = false;
            LoginActivity.m15835$$Nest$mshowDoneButton(this.this$0, false, true);
            if (r4 != null) goto L10;
            TL_account.Password r52 = (TL_account.Password) r5;
            if (TwoStepVerificationActivity.canHandleCurrentPassword(r52, true) == true) goto L8;
            AlertsCreator.showUpdateAppAlert(this.this$0.getParentActivity(), LocaleController.getString("UpdateAppAlert", org.telegram.messenger.R.string.UpdateAppAlert), true);
            return;
        L8:
            Bundle r42 = new Bundle();
            SerializedData r0 = new SerializedData(r52.getObjectSize());
            r52.serializeToStream(r0);
            r42.putString("password", Utilities.bytesToHex(r0.toByteArray()));
            r42.putString("phoneFormated", this.requestPhone);
            r42.putString("phoneHash", this.phoneHash);
            r42.putString("code", r6);
            animateSuccess(new LoginActivity$LoginActivityEmailCodeView$$ExternalSyntheticLambda29(this, r42));
            return;
        L10:
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), r4.text);
        }

        private /* synthetic */ void lambda$onNextPressed$21(String r2, TLObject r3, TLRPC.TL_error r4) {
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivityEmailCodeView$$ExternalSyntheticLambda24(this, r4, r3, r2));
        }

        private /* synthetic */ void lambda$onNextPressed$22(TLRPC.TL_error r6, String r7, TLObject r8) {
            LoginActivity.m15826$$Nest$mneedHideProgress(this.this$0, false);
            if (r6 != null) goto L12;
            this.nextPressed = false;
            LoginActivity.m15835$$Nest$mshowDoneButton(this.this$0, false, true);
            Bundle r62 = new Bundle();
            r62.putString("phone", this.phone);
            r62.putString("ephone", this.emailPhone);
            r62.putString("phoneFormated", this.requestPhone);
            r62.putString("phoneHash", this.phoneHash);
            r62.putString("code", r7);
            if ((r8 instanceof TLRPC.TL_auth_authorizationSignUpRequired) == false) goto L10;
            TLRPC.TL_help_termsOfService r72 = ((TLRPC.TL_auth_authorizationSignUpRequired) r8).terms_of_service;
            if (r72 == null) goto L9;
            LoginActivity.m15810$$Nest$fputcurrentTermsOfService(this.this$0, r72);
        L9:
            animateSuccess(new LoginActivity$LoginActivityEmailCodeView$$ExternalSyntheticLambda21(this, r62));
        L48:
            this.googleAccount = null;
            return;
        L10:
            animateSuccess(new LoginActivity$LoginActivityEmailCodeView$$ExternalSyntheticLambda22(this, r8, r62));
            goto L48
        L12:
            if (r6.text.contains("SESSION_PASSWORD_NEEDED") == false) goto L14;
            ConnectionsManager.getInstance(LoginActivity.access$3000(this.this$0)).sendRequest(new TL_account.getPassword(), new LoginActivity$LoginActivityEmailCodeView$$ExternalSyntheticLambda23(this, r7), 10);
            goto L48
        L14:
            this.nextPressed = false;
            LoginActivity.m15835$$Nest$mshowDoneButton(this.this$0, false, true);
            if (r6.text.contains("EMAIL_ADDRESS_INVALID") == false) goto L18;
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString(org.telegram.messenger.R.string.EmailAddressInvalid));
        L40:
            if (this.codeFieldContainer.codeField == null) goto L46;
            int r63 = 0;
        L42:
            CodeNumberField[] r73 = this.codeFieldContainer.codeField;
            if (r63 >= r73.length) goto L45;
            r73[r63].setText(_UrlKt.FRAGMENT_ENCODE_SET);
            r63 = r63 + 1;
            goto L42
        L45:
            r73[0].requestFocus();
        L46:
            this.codeFieldContainer.isFocusSuppressed = false;
            goto L48
        L18:
            if (r6.text.contains("PHONE_NUMBER_INVALID") == false) goto L21;
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString("InvalidPhoneNumber", org.telegram.messenger.R.string.InvalidPhoneNumber));
            goto L40
        L21:
            if (r6.text.contains("CODE_EMPTY") == false) goto L23;
        L47:
            shakeWrongCode();
            goto L48
        L23:
            if (r6.text.contains("CODE_INVALID") == true) goto L47;
            if (r6.text.contains("EMAIL_CODE_INVALID") == true) goto L47;
            if (r6.text.contains("PHONE_CODE_INVALID") == true) goto L47;
            if (r6.text.contains("EMAIL_TOKEN_INVALID") == false) goto L33;
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString(org.telegram.messenger.R.string.EmailTokenInvalid));
            goto L40
        L33:
            if (r6.text.contains("EMAIL_VERIFY_EXPIRED") == false) goto L35;
            onBackPressed(true);
            this.this$0.setPage(0, true, null, true);
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString("CodeExpired", org.telegram.messenger.R.string.CodeExpired));
            goto L40
        L35:
            boolean r74 = r6.text.startsWith("FLOOD_WAIT");
            LoginActivity r82 = this.this$0;
            if (r74 == false) goto L38;
            LoginActivity.m15828$$Nest$mneedShowAlert(r82, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString("FloodWait", org.telegram.messenger.R.string.FloodWait));
            goto L40
        L38:
            LoginActivity.m15828$$Nest$mneedShowAlert(r82, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString("ErrorOccurred", org.telegram.messenger.R.string.ErrorOccurred) + "\n" + r6.text);
            goto L40
        }

        private /* synthetic */ void lambda$onNextPressed$23(String r2, TLObject r3, TLRPC.TL_error r4) {
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivityEmailCodeView$$ExternalSyntheticLambda13(this, r4, r2, r3));
        }

        private /* synthetic */ void lambda$onPasscodeError$15() {
            CodeFieldContainer r0 = this.codeFieldContainer;
            int r1 = 0;
            r0.isFocusSuppressed = false;
            r0.codeField[0].requestFocus();
        L3:
            CodeNumberField[] r02 = this.codeFieldContainer.codeField;
            if (r1 >= r02.length) goto L6;
            r02[r1].animateErrorProgress(0.0f);
            r1 = r1 + 1;
            goto L3
        }

        private /* synthetic */ void lambda$onPasscodeError$16() {
            postDelayed(new LoginActivity$LoginActivityEmailCodeView$$ExternalSyntheticLambda17(this), 150);
            removeCallbacks(this.errorColorTimeout);
            postDelayed(this.errorColorTimeout, 3000);
            this.postedErrorColorTimeout = true;
        }

        private /* synthetic */ void lambda$onShow$28() {
            this.inboxImageView.getAnimatedDrawable().setCurrentFrame(0, false);
            this.inboxImageView.playAnimation();
            CodeFieldContainer r0 = this.codeFieldContainer;
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.codeField == null) goto L9;
            r0.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            this.codeFieldContainer.codeField[0].requestFocus();
            return;
        }

        private /* synthetic */ void lambda$requestEmailReset$12(TLObject r3, Bundle r4, TLRPC.TL_error r5, TLRPC.TL_auth_resetLoginEmail r6) {
            if (this.this$0.getParentActivity() == null) goto L24;
            this.requestingEmailReset = false;
            if ((r3 instanceof TLRPC.TL_auth_sentCode) == false) goto L9;
            LoginActivity.m15820$$Nest$mfillNextCodeParams(this.this$0, r4, (TLRPC.TL_auth_sentCode) r3);
            return;
        L9:
            if (r5 == null) goto L22;
            String r32 = r5.text;
            if (r32 != null) goto L13;
            return;
        L13:
            if (r32.contains("TASK_ALREADY_EXISTS") == false) goto L17;
            new AlertDialog.Builder(getContext()).setTitle(LocaleController.getString(org.telegram.messenger.R.string.LoginEmailResetPremiumRequiredTitle)).setMessage(AndroidUtilities.replaceTags(LocaleController.formatString(org.telegram.messenger.R.string.LoginEmailResetPremiumRequiredMessage, new Object[]{LocaleController.addNbsp(PhoneFormat.getInstance().format("+" + this.requestPhone))}))).setPositiveButton(LocaleController.getString(org.telegram.messenger.R.string.OK), null).show();
            return;
        L17:
            if (r5.text.contains("PHONE_CODE_EXPIRED") == false) goto L20;
            onBackPressed(true);
            this.this$0.setPage(0, true, null, true);
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString("CodeExpired", org.telegram.messenger.R.string.CodeExpired));
            return;
        L20:
            AlertsCreator.processError(LoginActivity.access$3100(this.this$0), r5, this.this$0, r6, new Object[0]);
            return;
        L22:
            return;
        }

        private /* synthetic */ void lambda$requestEmailReset$13(Bundle r7, TLRPC.TL_auth_resetLoginEmail r8, TLObject r9, TLRPC.TL_error r10) {
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivityEmailCodeView$$ExternalSyntheticLambda20(this, r9, r7, r10, r8));
        }

        private /* synthetic */ void lambda$setParams$14(View r1, boolean r2) {
            if (r2 == false) goto L5;
            LoginActivity.m15794$$Nest$fgetkeyboardView(this.this$0).setEditText((EditText) r1);
            LoginActivity.m15794$$Nest$fgetkeyboardView(this.this$0).setDispatchBackWhenEmpty(true);
            return;
        }

        private /* synthetic */ void lambda$shakeWrongCode$26() {
            CodeFieldContainer r0 = this.codeFieldContainer;
            int r1 = 0;
            r0.isFocusSuppressed = false;
            r0.codeField[0].requestFocus();
        L3:
            CodeNumberField[] r02 = this.codeFieldContainer.codeField;
            if (r1 >= r02.length) goto L6;
            r02[r1].animateErrorProgress(0.0f);
            r1 = r1 + 1;
            goto L3
        }

        private /* synthetic */ void lambda$shakeWrongCode$27() {
            postDelayed(new LoginActivity$LoginActivityEmailCodeView$$ExternalSyntheticLambda30(this), 150);
        }

        private void onPasscodeError(boolean r6) {
            if (this.this$0.getParentActivity() != null) goto L17;
            return;
        L17:
            this.codeFieldContainer.performHapticFeedback(3, 2);     // Catch: Exception -> L16
        L7:
            if (r6 == false) goto L11;
            CodeNumberField[] r62 = this.codeFieldContainer.codeField;
            int r1 = r62.length;
            int r2 = 0;
        L9:
            if (r2 >= r1) goto L11;
            r62[r2].setText(_UrlKt.FRAGMENT_ENCODE_SET);
            r2 = r2 + 1;
        L11:
            CodeNumberField[] r63 = this.codeFieldContainer.codeField;
            int r12 = r63.length;
            int r22 = 0;
        L12:
            if (r22 >= r12) goto L14;
            r63[r22].animateErrorProgress(1.0f);
            r22 = r22 + 1;
            goto L12
        L14:
            this.codeFieldContainer.codeField[0].requestFocus();
            AndroidUtilities.shakeViewSpring(this.codeFieldContainer, new LoginActivity$LoginActivityEmailCodeView$$ExternalSyntheticLambda16(this));
        }

        private void requestEmailReset() {
            if (this.requestingEmailReset == false) goto L5;
            return;
        L5:
            this.requestingEmailReset = true;
            Bundle r0 = new Bundle();
            r0.putString("phone", this.phone);
            r0.putString("ephone", this.emailPhone);
            r0.putString("phoneFormated", this.requestPhone);
            TLRPC.TL_auth_resetLoginEmail r1 = new TLRPC.TL_auth_resetLoginEmail();
            r1.phone_number = this.requestPhone;
            r1.phone_code_hash = this.phoneHash;
            this.this$0.getConnectionsManager().sendRequest(r1, new LoginActivity$LoginActivityEmailCodeView$$ExternalSyntheticLambda12(this, r0, r1), 10);
        }

        private void shakeWrongCode() {
            this.codeFieldContainer.performHapticFeedback(3, 2);     // Catch: Exception -> L12
        L3:
            int r1 = 0;
        L4:
            CodeNumberField[] r2 = this.codeFieldContainer.codeField;
            if (r1 >= r2.length) goto L8;
            r2[r1].setText(_UrlKt.FRAGMENT_ENCODE_SET);
            this.codeFieldContainer.codeField[r1].animateErrorProgress(1.0f);
            r1 = r1 + 1;
            goto L4
        L8:
            if (this.errorViewSwitcher.getCurrentView() != this.resendFrameLayout) goto L10;
            this.errorViewSwitcher.showNext();
            AndroidUtilities.updateViewVisibilityAnimated(this.cantAccessEmailFrameLayout, false, 1.0f, true);
        L10:
            this.codeFieldContainer.codeField[0].requestFocus();
            AndroidUtilities.shakeViewSpring(this.codeFieldContainer, 10.0f, new LoginActivity$LoginActivityEmailCodeView$$ExternalSyntheticLambda25(this));
            removeCallbacks(this.errorColorTimeout);
            postDelayed(this.errorColorTimeout, 5000);
            this.postedErrorColorTimeout = true;
        }

        private void showResendCodeView(boolean r9) {
            AndroidUtilities.updateViewVisibilityAnimated(this.resendCodeView, r9);
            FrameLayout r0 = this.cantAccessEmailFrameLayout;
            if (r9 == false) goto L5;
        L9:
            boolean r1 = false;
        L10:
            AndroidUtilities.updateViewVisibilityAnimated(r0, r1);
            if (this.loginOrView.getVisibility() == 8) goto L19;
            LoginOrView r02 = this.loginOrView;
            if (r9 == false) goto L16;
            float r92 = 8.0f;
        L17:
            r02.setLayoutParams(LayoutHelper.createFrame(-1, 16.0f, 17, 0.0f, 0.0f, 0.0f, r92));
            this.loginOrView.requestLayout();
            return;
        L16:
            r92 = 16.0f;
            goto L17
        L19:
            return;
        L5:
            if (LoginActivity.m15776$$Nest$fgetactivityMode(this.this$0) == 3) goto L9;
            if (this.isSetup == true) goto L9;
            r1 = true;
            goto L10
        }

        private void updateResetPendingDate() {
            int r0 = (int) (this.resetPendingDate - (System.currentTimeMillis() / 1000));
            if (this.resetPendingDate <= 0) goto L14;
            if (r0 <= 0) goto L14;
            String r02 = LocaleController.formatString(org.telegram.messenger.R.string.LoginEmailResetInTime, new Object[]{getTimePatternForTimer(r0)});
            SpannableStringBuilder r1 = SpannableStringBuilder.valueOf(r02);
            int r3 = r02.indexOf(42);
            int r03 = r02.lastIndexOf(42);
            if (r3 != r03) goto L9;
        L12:
            this.emailResetInView.setText(r1);
            AndroidUtilities.runOnUIThread(this.updateResetPendingDateCallback, 1000);
            return;
        L9:
            if (r3 == (-1)) goto L12;
            if (r03 == (-1)) goto L12;
            r1.replace(r03, r03 + 1, _UrlKt.FRAGMENT_ENCODE_SET);
            r1.replace(r3, r3 + 1, _UrlKt.FRAGMENT_ENCODE_SET);
            r1.setSpan(new ForegroundColorSpan(this.this$0.getThemedColor(Theme.key_windowBackgroundWhiteBlueText4)), r3, r03 - 1, 33);
        L14:
            this.emailResetInView.setVisibility(0);
            this.emailResetInView.setText(LocaleController.getString(org.telegram.messenger.R.string.LoginEmailResetPleaseWait));
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivityEmailCodeView$$ExternalSyntheticLambda14(this), 1000);
        }

        @Override // org.telegram.ui.Components.SlideView
        public String getHeaderName() {
            return LocaleController.getString(org.telegram.messenger.R.string.VerificationCode);
        }

        @Override // org.telegram.ui.Components.SlideView
        public boolean hasCustomKeyboard() {
            return true;
        }

        @Override // org.telegram.ui.Components.SlideView
        public boolean needBackButton() {
            return true;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            removeCallbacks(this.errorColorTimeout);
            removeCallbacks(this.resendCodeTimeout);
        }

        @Override // org.telegram.ui.Components.SlideView
        public void onHide() {
            super.onHide();
            if (this.resetPendingDate == 0) goto L6;
            AndroidUtilities.cancelRunOnUIThread(this.updateResetPendingDateCallback);
            return;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.telegram.ui.Components.SlideView
        public void onNextPressed(String r7) {
            if (this.nextPressed == false) goto L5;
            return;
        L5:
            AndroidUtilities.cancelRunOnUIThread(this.resendCodeTimeout);
            CodeFieldContainer r72 = this.codeFieldContainer;
            r72.isFocusSuppressed = true;
            CodeNumberField[] r73 = r72.codeField;
            int r2 = 0;
            if (r73 == null) goto L10;
            int r3 = r73.length;
            int r4 = 0;
        L8:
            if (r4 >= r3) goto L10;
            r73[r4].animateFocusedProgress(0.0f);
            r4 = r4 + 1;
        L10:
            String r74 = this.codeFieldContainer.getCode();
            if (r74.length() == 0) goto L13;
        L16:
            this.nextPressed = true;
            LoginActivity.m15829$$Nest$mneedShowProgress(this.this$0, 0);
            if (LoginActivity.m15776$$Nest$fgetactivityMode(this.this$0) != 3) goto L20;
            TL_account.verifyEmail r32 = new TL_account.verifyEmail();
            r32.purpose = new TLRPC.TL_emailVerifyPurposeLoginChange();
            TLRPC.TL_emailVerificationCode r42 = new TLRPC.TL_emailVerificationCode();
            r42.code = r74;
            r32.verification = r42;
            TLRPC.TL_auth_signIn r33 = r32;
        L27:
            CodeFieldContainer r43 = this.codeFieldContainer;
            r43.isFocusSuppressed = true;
            CodeNumberField[] r0 = r43.codeField;
            if (r0 == null) goto L32;
            int r44 = r0.length;
        L30:
            if (r2 >= r44) goto L32;
            r0[r2].animateFocusedProgress(0.0f);
            r2 = r2 + 1;
        L32:
            ConnectionsManager.getInstance(LoginActivity.access$2900(this.this$0)).sendRequest(r33, new LoginActivity$LoginActivityEmailCodeView$$ExternalSyntheticLambda0(this, r74), 10);
            return;
        L20:
            if (this.isFromSetup == false) goto L22;
            TL_account.verifyEmail r34 = new TL_account.verifyEmail();
            TLRPC.TL_emailVerifyPurposeLoginSetup r45 = new TLRPC.TL_emailVerifyPurposeLoginSetup();
            r45.phone_number = this.requestPhone;
            r45.phone_code_hash = this.phoneHash;
            r34.purpose = r45;
            TLRPC.TL_emailVerificationCode r46 = new TLRPC.TL_emailVerificationCode();
            r46.code = r74;
            r34.verification = r46;
            r33 = r34;
            goto L27
        L22:
            TLRPC.TL_auth_signIn r35 = new TLRPC.TL_auth_signIn();
            r35.phone_number = this.requestPhone;
            r35.phone_code_hash = this.phoneHash;
            if (this.googleAccount == null) goto L25;
            TLRPC.TL_emailVerificationGoogle r47 = new TLRPC.TL_emailVerificationGoogle();
            r47.token = this.googleAccount.getIdToken();
            r35.email_verification = r47;
        L26:
            r35.flags |= 2;
            r33 = r35;
            goto L27
        L25:
            TLRPC.TL_emailVerificationCode r48 = new TLRPC.TL_emailVerificationCode();
            r48.code = r74;
            r35.email_verification = r48;
            goto L26
        L13:
            if (this.googleAccount != null) goto L16;
            onPasscodeError(false);
        }

        @Override // org.telegram.ui.Components.SlideView
        public void onShow() {
            super.onShow();
            if (this.resetRequestPending == false) goto L6;
            this.resetRequestPending = false;
            return;
        L6:
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivityEmailCodeView$$ExternalSyntheticLambda9(this), LoginActivity.m15838$$Nest$sfgetSHOW_DELAY());
        }

        @Override // org.telegram.ui.Components.SlideView
        public void restoreStateParams(Bundle r3) {
            Bundle r0 = r3.getBundle("emailcode_params");
            this.currentParams = r0;
            if (r0 == null) goto L5;
            setParams(r0, true);
        L5:
            String r32 = r3.getString("emailcode_code");
            if (r32 == null) goto L9;
            this.codeFieldContainer.setText(r32);
            return;
        }

        @Override // org.telegram.ui.Components.SlideView
        public void saveStateParams(Bundle r3) {
            String r0 = this.codeFieldContainer.getCode();
            if (r0 != null) goto L5;
        L7:
            Bundle r2 = this.currentParams;
            if (r2 == null) goto L11;
            r3.putBundle("emailcode_params", r2);
            return;
        L11:
            return;
        L5:
            if (r0.length() == 0) goto L7;
            r3.putString("emailcode_code", r0);
            goto L7
        }

        @Override // org.telegram.ui.Components.SlideView
        public void setParams(Bundle r10, boolean r11) {
            if (r10 == null) goto L60;
            this.currentParams = r10;
            this.requestPhone = r10.getString("phoneFormated");
            this.phoneHash = this.currentParams.getString("phoneHash");
            this.phone = this.currentParams.getString("phone");
            this.emailPhone = this.currentParams.getString("ephone");
            this.isFromSetup = this.currentParams.getBoolean("setup");
            this.length = this.currentParams.getInt("length");
            this.email = this.currentParams.getString("email");
            this.resetAvailablePeriod = this.currentParams.getInt("resetAvailablePeriod");
            this.resetPendingDate = this.currentParams.getInt("resetPendingDate");
            int r2 = 8;
            if (LoginActivity.m15776$$Nest$fgetactivityMode(this.this$0) != 3) goto L8;
            this.confirmTextView.setText(LocaleController.formatString(org.telegram.messenger.R.string.CheckYourNewEmailSubtitle, new Object[]{this.email}));
            AndroidUtilities.updateViewVisibilityAnimated(this.cantAccessEmailFrameLayout, false, 1.0f, false);
        L21:
            this.codeFieldContainer.setNumbersCount(this.length, 1);
            CodeNumberField[] r0 = this.codeFieldContainer.codeField;
            int r3 = r0.length;
            int r6 = 0;
        L22:
            if (r6 >= r3) goto L31;
            CodeNumberField r7 = r0[r6];
            if (hasCustomKeyboard() == true) goto L26;
        L29:
            boolean r8 = true;
        L30:
            r7.setShowSoftInputOnFocusCompat(r8);
            r7.addTextChangedListener(new AnonymousClass7(this));
            r7.setOnFocusChangeListener(new LoginActivity$LoginActivityEmailCodeView$$ExternalSyntheticLambda1(this));
            r6 = r6 + 1;
            goto L22
        L26:
            if (LoginActivity.m15823$$Nest$misCustomKeyboardForceDisabled(this.this$0) == true) goto L29;
            r8 = false;
            goto L30
        L31:
            this.codeFieldContainer.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            if (this.isFromSetup == true) goto L43;
            if (LoginActivity.m15776$$Nest$fgetactivityMode(this.this$0) == 3) goto L43;
            String r02 = this.currentParams.getString("emailPattern");
            SpannableStringBuilder r1 = new SpannableStringBuilder(r02);
            int r62 = r02.indexOf(42);
            int r03 = r02.lastIndexOf(42);
            if (r62 != r03) goto L38;
        L41:
            this.confirmTextView.setText(AndroidUtilities.formatSpannable(LocaleController.getString(org.telegram.messenger.R.string.CheckYourEmailSubtitle), new CharSequence[]{r1}));
            goto L43
        L38:
            if (r62 == (-1)) goto L41;
            if (r03 == (-1)) goto L41;
            TextStyleSpan.TextStyleRun r32 = new TextStyleSpan.TextStyleRun();
            r32.flags |= 256;
            r32.start = r62;
            int r04 = r03 + 1;
            r32.end = r04;
            r1.setSpan(new TextStyleSpan(r32), r62, r04, 0);
        L43:
            if (r10.getBoolean("googleSignInAllowed") == true) goto L45;
        L47:
            this.loginOrView.setVisibility(r2);
            this.signInWithGoogleView.setVisibility(r2);
            LoginActivity.m15836$$Nest$mshowKeyboard(this.this$0, this.codeFieldContainer.codeField[0]);
            this.codeFieldContainer.requestFocus();
            if (r11 == true) goto L53;
            if (r10.containsKey("nextType") == false) goto L53;
            AndroidUtilities.runOnUIThread(this.resendCodeTimeout, r10.getInt("timeout"));
        L53:
            if (this.resetPendingDate == 0) goto L59;
            AndroidUtilities.runOnUIThread(this.updateResetPendingDateCallback, 1000);
            return;
        L59:
            return;
        L45:
            if (PushListenerController.GooglePushListenerServiceProvider.INSTANCE.hasServices() == false) goto L47;
            r2 = 0;
            goto L47
        L8:
            if (this.isSetup == false) goto L10;
            this.confirmTextView.setText(LocaleController.formatString(org.telegram.messenger.R.string.VerificationCodeSubtitle, new Object[]{this.email}));
            AndroidUtilities.updateViewVisibilityAnimated(this.cantAccessEmailFrameLayout, false, 1.0f, false);
            goto L21
        L10:
            AndroidUtilities.updateViewVisibilityAnimated(this.cantAccessEmailFrameLayout, true, 1.0f, false);
            TextView r05 = this.cantAccessEmailView;
            if (this.resetPendingDate != 0) goto L13;
            int r33 = 0;
        L14:
            r05.setVisibility(r33);
            TextView r06 = this.emailResetInView;
            if (this.resetPendingDate == 0) goto L17;
            int r34 = 0;
        L18:
            r06.setVisibility(r34);
            if (this.resetPendingDate == 0) goto L21;
            updateResetPendingDate();
            goto L21
        L17:
            r34 = 8;
            goto L18
        L13:
            r33 = 8;
            goto L14
        }

        @Override // org.telegram.ui.Components.SlideView
        public void updateColors() {
            this.titleView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
            TextView r0 = this.confirmTextView;
            int r1 = Theme.key_windowBackgroundWhiteGrayText6;
            r0.setTextColor(Theme.getColor(r1));
            TextView r02 = this.signInWithGoogleView;
            int r2 = Theme.key_windowBackgroundWhiteBlueText4;
            r02.setTextColor(Theme.getColor(r2));
            this.loginOrView.updateColors();
            this.resendCodeView.setTextColor(Theme.getColor(r2));
            this.cantAccessEmailView.setTextColor(Theme.getColor(r2));
            this.emailResetInView.setTextColor(Theme.getColor(r1));
            this.wrongCodeView.setTextColor(Theme.getColor(Theme.key_text_RedBold));
            this.codeFieldContainer.invalidate();
        }
    }

    public class LoginActivityNewPasswordView extends SlideView {
        private TextView cancelButton;
        private EditTextBoldCursor[] codeField;
        private TextView confirmTextView;
        private Bundle currentParams;
        private TL_account.Password currentPassword;
        private int currentStage;
        private String emailCode;
        private boolean isPasswordVisible;
        private String newPassword;
        private boolean nextPressed;
        private OutlineTextContainerView[] outlineFields;
        private ImageView passwordButton;
        private String passwordString;
        final /* synthetic */ LoginActivity this$0;
        private TextView titleTextView;

        /* renamed from: org.telegram.ui.LoginActivity$LoginActivityNewPasswordView$1, reason: invalid class name */
        public class AnonymousClass1 implements TextWatcher {
            final /* synthetic */ LoginActivityNewPasswordView this$1;
            final /* synthetic */ boolean val$showPasswordButton;
            final /* synthetic */ LoginActivity val$this$0;

            public AnonymousClass1(LoginActivityNewPasswordView r1, LoginActivity r2, boolean r3) {
                this.this$1 = r1;
                this.val$this$0 = r2;
                this.val$showPasswordButton = r3;
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable r5) {
                if (this.val$showPasswordButton == true) goto L5;
                return;
            L5:
                if (LoginActivityNewPasswordView.m15860$$Nest$fgetpasswordButton(this.this$1).getVisibility() == 0) goto L14;
                if (TextUtils.isEmpty(r5) == true) goto L14;
                if (LoginActivityNewPasswordView.m15859$$Nest$fgetisPasswordVisible(this.this$1) == false) goto L11;
                LoginActivityNewPasswordView.m15860$$Nest$fgetpasswordButton(this.this$1).callOnClick();
            L11:
                AndroidUtilities.updateViewVisibilityAnimated(LoginActivityNewPasswordView.m15860$$Nest$fgetpasswordButton(this.this$1), true, 0.1f, true);
                return;
            L14:
                if (LoginActivityNewPasswordView.m15860$$Nest$fgetpasswordButton(this.this$1).getVisibility() != 8) goto L16;
                return;
            L16:
                if (TextUtils.isEmpty(r5) == false) goto L21;
                AndroidUtilities.updateViewVisibilityAnimated(LoginActivityNewPasswordView.m15860$$Nest$fgetpasswordButton(this.this$1), false, 0.1f, true);
                return;
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }
        }

        /* renamed from: $r8$lambda$6Vf-XLw4byZaxdVlV0ocDkd70gM, reason: not valid java name */
        public static /* synthetic */ void m15855$r8$lambda$6VfXLw4byZaxdVlV0ocDkd70gM(LoginActivityNewPasswordView r0, String r1, String r2, TLRPC.TL_auth_recoverPassword r3) {
            r0.lambda$recoverPassword$9(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$9zbTJCwVT64dux2fm5JzrRwieUY(LoginActivityNewPasswordView r0) {
            r0.lambda$onShow$10();
        }

        /* renamed from: $r8$lambda$GH4dwnkapRg0O57c-CnOL7h6nrI, reason: not valid java name */
        public static /* synthetic */ void m15856$r8$lambda$GH4dwnkapRg0O57cCnOL7h6nrI(LoginActivityNewPasswordView r0, String r1, String r2, TLObject r3, TLRPC.TL_error r4) {
            r0.lambda$recoverPassword$5(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$M4JsQn9vZW0nT9a3RJIQsROKxbs(LoginActivityNewPasswordView r0, String r1, String r2, TLObject r3, TLRPC.TL_error r4) {
            r0.lambda$recoverPassword$8(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$RZuOGmksfkHwxtFT6lxReZzS0KA(LoginActivityNewPasswordView r0, TLRPC.TL_error r1, TLObject r2, String r3, String r4) {
            r0.lambda$recoverPassword$4(r1, r2, r3, r4);
        }

        public static /* synthetic */ boolean $r8$lambda$Vq3T2_QFphV29PyWmwyZk1qGays(LoginActivityNewPasswordView r0, int r1, TextView r2, int r3, KeyEvent r4) {
            return r0.lambda$new$2(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$e657M5Rv8EcazT9pQX9RQoI0dtk(LoginActivityNewPasswordView r0, TLObject r1, AlertDialog r2, int r3) {
            r0.lambda$recoverPassword$6(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$gXRinkpCUitaho7WDNwxB-zJrE4, reason: not valid java name */
        public static /* synthetic */ void m15857$r8$lambda$gXRinkpCUitaho7WDNwxBzJrE4(LoginActivityNewPasswordView r0, TLRPC.TL_error r1, String r2, String r3, TLObject r4) {
            r0.lambda$recoverPassword$7(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$sMavxPBHQCn098pFkKRICEmCPJ0(LoginActivityNewPasswordView r0, View r1) {
            r0.lambda$new$3(r1);
        }

        public static /* synthetic */ void $r8$lambda$sVNam_93CH21nDlVG8tTYe94Kdw(LoginActivityNewPasswordView r0, View r1) {
            r0.lambda$new$1(r1);
        }

        /* renamed from: $r8$lambda$zYhCu7_oFxh-NL-FzbK-Y5RtiCg, reason: not valid java name */
        public static /* synthetic */ void m15858$r8$lambda$zYhCu7_oFxhNLFzbKY5RtiCg(OutlineTextContainerView r0, View r1, boolean r2) {
            if (r2 == false) goto L4;
            float r12 = 1.0f;
        L5:
            r0.animateSelection(r12);
            return;
        L4:
            r12 = 0.0f;
            goto L5
        }

        /* renamed from: -$$Nest$fgetisPasswordVisible, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m15859$$Nest$fgetisPasswordVisible(LoginActivityNewPasswordView r0) {
            return r0.isPasswordVisible;
        }

        /* renamed from: -$$Nest$fgetpasswordButton, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ImageView m15860$$Nest$fgetpasswordButton(LoginActivityNewPasswordView r0) {
            return r0.passwordButton;
        }

        public LoginActivityNewPasswordView(LoginActivity r26, Context r27, int r28) {
            this.this$0 = r26;
            super(r27);
            this.currentStage = r28;
            setOrientation(1);
            if (r28 != 1) goto L5;
            int r5 = 1;
        L6:
            EditTextBoldCursor[] r52 = new EditTextBoldCursor[r5];
            this.codeField = r52;
            this.outlineFields = new OutlineTextContainerView[r52.length];
            TextView r53 = new TextView(r27);
            this.titleTextView = r53;
            float r6 = 18.0f;
            r53.setTextSize(1, 18.0f);
            this.titleTextView.setTypeface(AndroidUtilities.bold());
            float r7 = 2.0f;
            this.titleTextView.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            this.titleTextView.setGravity(49);
            this.titleTextView.setText(LocaleController.getString(org.telegram.messenger.R.string.SetNewPassword));
            View r54 = this.titleTextView;
            if (AndroidUtilities.isSmallScreen() == false) goto L9;
            int r15 = 16;
        L10:
            addView(r54, LayoutHelper.createLinear(-2, -2, 1, 8, r15, 8, 0));
            TextView r55 = new TextView(r27);
            this.confirmTextView = r55;
            r55.setTextSize(1, 16.0f);
            this.confirmTextView.setGravity(1);
            this.confirmTextView.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            addView(this.confirmTextView, LayoutHelper.createLinear(-2, -2, 1, 8, 6, 8, 16));
            int r11 = 0;
        L12:
            if (r11 >= this.codeField.length) goto L36;
            OutlineTextContainerView r12 = new OutlineTextContainerView(r27);
            this.outlineFields[r11] = r12;
            if (r28 != 0) goto L18;
            if (r11 != 0) goto L17;
            int r14 = org.telegram.messenger.R.string.PleaseEnterNewFirstPasswordHint;
        L19:
            r12.setText(LocaleController.getString(r14));
            this.codeField[r11] = new EditTextBoldCursor(r27);
            this.codeField[r11].setCursorSize(AndroidUtilities.dp(20.0f));
            this.codeField[r11].setCursorWidth(1.5f);
            this.codeField[r11].setImeOptions(268435461);
            this.codeField[r11].setTextSize(1, r6);
            this.codeField[r11].setMaxLines(1);
            this.codeField[r11].setBackground(null);
            int r142 = AndroidUtilities.dp(16.0f);
            this.codeField[r11].setPadding(r142, r142, r142, r142);
            if (r28 != 0) goto L22;
            this.codeField[r11].setInputType(129);
            this.codeField[r11].setTransformationMethod(PasswordTransformationMethod.getInstance());
        L22:
            this.codeField[r11].setTypeface(Typeface.DEFAULT);
            EditTextBoldCursor r143 = this.codeField[r11];
            if (LocaleController.isRTL == false) goto L25;
            int r152 = 5;
        L26:
            r143.setGravity(r152);
            EditTextBoldCursor r144 = this.codeField[r11];
            if (r11 != 0) goto L30;
            if (r28 != 0) goto L30;
            boolean r153 = true;
        L31:
            r144.addTextChangedListener(new AnonymousClass1(this, r26, r153));
            this.codeField[r11].setOnFocusChangeListener(new LoginActivity$LoginActivityNewPasswordView$$ExternalSyntheticLambda1(r12));
            if (r153 == false) goto L34;
            LinearLayout r145 = new LinearLayout(r27);
            r145.setOrientation(0);
            r145.setGravity(16);
            float r17 = r7;
            r145.addView(this.codeField[r11], LayoutHelper.createLinear(0, -2, 1.0f));
            ImageView r72 = new ImageView(r27);
            this.passwordButton = r72;
            r72.setImageResource(org.telegram.messenger.R.drawable.msg_message);
            AndroidUtilities.updateViewVisibilityAnimated(this.passwordButton, true, 0.1f, false);
            this.passwordButton.setOnClickListener(new LoginActivity$LoginActivityNewPasswordView$$ExternalSyntheticLambda2(this));
            r145.addView(this.passwordButton, LayoutHelper.createLinearRelatively(24.0f, 24.0f, 0, 0.0f, 0.0f, 14.0f, 0.0f));
            r12.addView(r145, LayoutHelper.createFrame(-1, -2.0f));
        L35:
            r12.attachEditText(this.codeField[r11]);
            addView(r12, LayoutHelper.createLinear(-1, -2, 1, 16, 16, 16, 0));
            this.codeField[r11].setOnEditorActionListener(new LoginActivity$LoginActivityNewPasswordView$$ExternalSyntheticLambda3(this, r11));
            r11 = r11 + 1;
            r7 = r17;
            r6 = 18.0f;
            goto L12
        L34:
            r17 = r7;
            r12.addView(this.codeField[r11], LayoutHelper.createFrame(-1, -2.0f));
        L30:
            r153 = false;
            goto L31
        L25:
            r152 = 3;
            goto L26
        L17:
            r14 = org.telegram.messenger.R.string.PleaseEnterNewSecondPasswordHint;
            goto L19
        L18:
            r14 = org.telegram.messenger.R.string.PasswordHintPlaceholder;
            goto L19
        L36:
            float r172 = r7;
            TextView r1 = this.confirmTextView;
            if (r28 != 0) goto L39;
            r1.setText(LocaleController.getString("PleaseEnterNewFirstPasswordLogin", org.telegram.messenger.R.string.PleaseEnterNewFirstPasswordLogin));
        L40:
            TextView r13 = new TextView(r27);
            this.cancelButton = r13;
            r13.setGravity(19);
            this.cancelButton.setTextSize(1, 15.0f);
            this.cancelButton.setLineSpacing(AndroidUtilities.dp(r172), 1.0f);
            this.cancelButton.setPadding(AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(16.0f), 0);
            this.cancelButton.setText(LocaleController.getString(org.telegram.messenger.R.string.YourEmailSkip));
            FrameLayout r16 = new FrameLayout(r27);
            r16.addView(this.cancelButton, LayoutHelper.createFrame(-1, 56.0f, 80, 0.0f, 0.0f, 0.0f, 32.0f));
            addView(r16, LayoutHelper.createLinear(-1, -1, 80));
            VerticalPositionAutoAnimator.attach(this.cancelButton);
            this.cancelButton.setOnClickListener(new LoginActivity$LoginActivityNewPasswordView$$ExternalSyntheticLambda4(this));
            return;
        L39:
            r1.setText(LocaleController.getString("PasswordHintTextLogin", org.telegram.messenger.R.string.PasswordHintTextLogin));
            goto L40
        L9:
            r15 = 72;
            goto L10
        L5:
            r5 = 2;
            goto L6
        }

        private /* synthetic */ void lambda$new$1(View r5) {
            this.isPasswordVisible = !this.isPasswordVisible;
            int r52 = 0;
        L3:
            EditTextBoldCursor[] r0 = this.codeField;
            if (r52 >= r0.length) goto L10;
            int r02 = r0[r52].getSelectionStart();
            int r1 = this.codeField[r52].getSelectionEnd();
            EditTextBoldCursor r2 = this.codeField[r52];
            if (this.isPasswordVisible == false) goto L8;
            int r3 = 144;
        L9:
            r2.setInputType(r3 | 1);
            this.codeField[r52].setSelection(r02, r1);
            r52 = r52 + 1;
            goto L3
        L8:
            r3 = 128;
            goto L9
        L10:
            this.passwordButton.setTag(Boolean.valueOf(this.isPasswordVisible));
            ImageView r53 = this.passwordButton;
            if (this.isPasswordVisible == false) goto L13;
            int r4 = Theme.key_windowBackgroundWhiteInputFieldActivated;
        L14:
            r53.setColorFilter(Theme.getColor(r4));
            return;
        L13:
            r4 = Theme.key_windowBackgroundWhiteHintText;
            goto L14
        }

        private /* synthetic */ boolean lambda$new$2(int r2, TextView r3, int r4, KeyEvent r5) {
            if (r2 != 0) goto L9;
            EditTextBoldCursor[] r22 = this.codeField;
            if (r22.length != 2) goto L9;
            r22[1].requestFocus();
            return true;
        L9:
            if (r4 != 5) goto L12;
            onNextPressed(null);
            return true;
        L12:
            return false;
        }

        private /* synthetic */ void lambda$new$3(View r2) {
            if (this.currentStage != 0) goto L6;
            recoverPassword(null, null);
            return;
        L6:
            recoverPassword(this.newPassword, null);
        }

        private /* synthetic */ void lambda$onShow$10() {
            EditTextBoldCursor[] r0 = this.codeField;
            if (r0 == null) goto L6;
            r0[0].requestFocus();
            EditTextBoldCursor r02 = this.codeField[0];
            r02.setSelection(r02.length());
            AndroidUtilities.showKeyboard(this.codeField[0]);
            return;
        }

        private /* synthetic */ void lambda$recoverPassword$4(TLRPC.TL_error r1, TLObject r2, String r3, String r4) {
            if (r1 != null) goto L5;
            TL_account.Password r22 = (TL_account.Password) r2;
            this.currentPassword = r22;
            TwoStepVerificationActivity.initPasswordNewAlgo(r22);
            recoverPassword(r3, r4);
            return;
        }

        private /* synthetic */ void lambda$recoverPassword$5(String r7, String r8, TLObject r9, TLRPC.TL_error r10) {
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivityNewPasswordView$$ExternalSyntheticLambda10(this, r10, r9, r7, r8));
        }

        private /* synthetic */ void lambda$recoverPassword$6(TLObject r1, AlertDialog r2, int r3) {
            LoginActivity.m15831$$Nest$monAuthSuccess(this.this$0, (TLRPC.TL_auth_authorization) r1);
        }

        private /* synthetic */ void lambda$recoverPassword$7(TLRPC.TL_error r3, String r4, String r5, TLObject r6) {
            if (r3 != null) goto L4;
        L9:
            LoginActivity.m15826$$Nest$mneedHideProgress(this.this$0, false);
            if ((r6 instanceof TLRPC.auth_Authorization) == false) goto L19;
            AlertDialog.Builder r32 = new AlertDialog.Builder(this.this$0.getParentActivity());
            r32.setPositiveButton(LocaleController.getString(org.telegram.messenger.R.string.Continue), new LoginActivity$LoginActivityNewPasswordView$$ExternalSyntheticLambda9(this, r6));
            if (TextUtils.isEmpty(r4) == false) goto L14;
            r32.setMessage(LocaleController.getString(org.telegram.messenger.R.string.YourPasswordReset));
        L15:
            r32.setTitle(LocaleController.getString(org.telegram.messenger.R.string.TwoStepVerificationTitle));
            Dialog r2 = this.this$0.showDialog(r32.create());
            if (r2 == null) goto L30;
            r2.setCanceledOnTouchOutside(false);
            r2.setCancelable(false);
            return;
        L30:
            return;
        L14:
            r32.setMessage(LocaleController.getString(org.telegram.messenger.R.string.YourPasswordChangedSuccessText));
            goto L15
        L19:
            if (r3 == null) goto L31;
            this.nextPressed = false;
            if (r3.text.startsWith("FLOOD_WAIT") == false) goto L28;
            int r33 = Utilities.parseInt(r3.text);
            if (r33 >= 60) goto L25;
            String r34 = LocaleController.formatPluralString("Seconds", r33, new Object[0]);
        L26:
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.formatString("FloodWaitTime", org.telegram.messenger.R.string.FloodWaitTime, new Object[]{r34}));
            return;
        L25:
            r34 = LocaleController.formatPluralString("Minutes", r33 / 60, new Object[0]);
            goto L26
        L28:
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), r3.text);
            return;
        L31:
            return;
        L4:
            if ("SRP_ID_INVALID".equals(r3.text) == false) goto L6;
        L7:
            TL_account.getPassword r35 = new TL_account.getPassword();
            ConnectionsManager.getInstance(LoginActivity.access$3700(this.this$0)).sendRequest(r35, new LoginActivity$LoginActivityNewPasswordView$$ExternalSyntheticLambda8(this, r4, r5), 8);
            return;
        L6:
            if ("NEW_SALT_INVALID".equals(r3.text) == false) goto L9;
            goto L7
        }

        private /* synthetic */ void lambda$recoverPassword$8(String r7, String r8, TLObject r9, TLRPC.TL_error r10) {
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivityNewPasswordView$$ExternalSyntheticLambda7(this, r10, r7, r8, r9));
        }

        private /* synthetic */ void lambda$recoverPassword$9(String r5, String r6, TLRPC.TL_auth_recoverPassword r7) {
            if (r5 == null) goto L5;
            byte[] r1 = AndroidUtilities.getStringBytes(r5);
        L6:
            LoginActivity$LoginActivityNewPasswordView$$ExternalSyntheticLambda6 r2 = new LoginActivity$LoginActivityNewPasswordView$$ExternalSyntheticLambda6(this, r5, r6);
            TLRPC.PasswordKdfAlgo r62 = this.currentPassword.new_algo;
            if ((r62 instanceof TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow) == false) goto L14;
            if (r5 == null) goto L12;
            r7.new_settings.new_password_hash = SRPHelper.getVBytes(r1, (TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow) r62);
            if (r7.new_settings.new_password_hash != null) goto L12;
            TLRPC.TL_error r52 = new TLRPC.TL_error();
            r52.text = "ALGO_INVALID";
            r2.run(null, r52);
        L12:
            ConnectionsManager.getInstance(LoginActivity.access$3600(this.this$0)).sendRequest(r7, r2, 10);
            return;
        L14:
            TLRPC.TL_error r4 = new TLRPC.TL_error();
            r4.text = "PASSWORD_HASH_INVALID";
            r2.run(null, r4);
            return;
        L5:
            r1 = null;
            goto L6
        }

        private void onPasscodeError(boolean r3, int r4) {
            if (this.this$0.getParentActivity() != null) goto L9;
            return;
        L9:
            this.codeField[r4].performHapticFeedback(3, 2);     // Catch: Exception -> L8
        L6:
            AndroidUtilities.shakeView(this.codeField[r4]);
        }

        private void recoverPassword(String r4, String r5) {
            TLRPC.TL_auth_recoverPassword r0 = new TLRPC.TL_auth_recoverPassword();
            r0.code = this.emailCode;
            if (TextUtils.isEmpty(r4) == true) goto L9;
            r0.flags |= 1;
            TL_account.passwordInputSettings r1 = new TL_account.passwordInputSettings();
            r0.new_settings = r1;
            r1.flags |= 1;
            if (r5 == null) goto L7;
            String r2 = r5;
        L8:
            r1.hint = r2;
            r1.new_algo = this.currentPassword.new_algo;
            goto L9
        L7:
            r2 = _UrlKt.FRAGMENT_ENCODE_SET;
        L9:
            Utilities.globalQueue.postRunnable(new LoginActivity$LoginActivityNewPasswordView$$ExternalSyntheticLambda5(this, r4, r5, r0));
        }

        @Override // org.telegram.ui.Components.SlideView
        public String getHeaderName() {
            return LocaleController.getString("NewPassword", org.telegram.messenger.R.string.NewPassword);
        }

        @Override // org.telegram.ui.Components.SlideView
        public boolean needBackButton() {
            return true;
        }

        @Override // org.telegram.ui.Components.SlideView
        public boolean onBackPressed(boolean r2) {
            LoginActivity.m15826$$Nest$mneedHideProgress(this.this$0, true);
            this.currentParams = null;
            this.nextPressed = false;
            return true;
        }

        @Override // org.telegram.ui.Components.SlideView
        public void onCancelPressed() {
            this.nextPressed = false;
        }

        @Override // org.telegram.ui.Components.SlideView
        public void onNextPressed(String r6) {
            if (this.nextPressed == false) goto L5;
            return;
        L5:
            String r62 = this.codeField[0].getText().toString();
            if (r62.length() != 0) goto L10;
            onPasscodeError(false, 0);
            return;
        L10:
            if (this.currentStage == 0) goto L12;
            this.nextPressed = true;
            LoginActivity.m15829$$Nest$mneedShowProgress(this.this$0, 0);
            recoverPassword(this.newPassword, r62);
            return;
        L12:
            if (r62.equals(this.codeField[1].getText().toString()) == true) goto L15;
            onPasscodeError(false, 1);
            return;
        L15:
            Bundle r1 = new Bundle();
            r1.putString("emailCode", this.emailCode);
            r1.putString("new_password", r62);
            r1.putString("password", this.passwordString);
            this.this$0.setPage(10, true, r1, false);
        }

        @Override // org.telegram.ui.Components.SlideView
        public void onShow() {
            super.onShow();
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivityNewPasswordView$$ExternalSyntheticLambda0(this), LoginActivity.m15838$$Nest$sfgetSHOW_DELAY());
        }

        @Override // org.telegram.ui.Components.SlideView
        public void restoreStateParams(Bundle r3) {
            Bundle r32 = r3.getBundle("recoveryview_params" + this.currentStage);
            this.currentParams = r32;
            if (r32 == null) goto L6;
            setParams(r32, true);
            return;
        }

        @Override // org.telegram.ui.Components.SlideView
        public void saveStateParams(Bundle r3) {
            if (this.currentParams == null) goto L6;
            r3.putBundle("recoveryview_params" + this.currentStage, this.currentParams);
            return;
        }

        @Override // org.telegram.ui.Components.SlideView
        public void setParams(Bundle r4, boolean r5) {
            if (r4 != null) goto L4;
            return;
        L4:
            int r0 = 0;
        L5:
            EditTextBoldCursor[] r1 = this.codeField;
            if (r0 >= r1.length) goto L8;
            r1[r0].setText(_UrlKt.FRAGMENT_ENCODE_SET);
            r0 = r0 + 1;
            goto L5
        L8:
            this.currentParams = r4;
            this.emailCode = r4.getString("emailCode");
            String r42 = this.currentParams.getString("password");
            this.passwordString = r42;
            if (r42 == null) goto L11;
            SerializedData r02 = new SerializedData(Utilities.hexToBytes(r42));
            TL_account.Password r43 = TL_account.Password.TLdeserialize(r02, r02.readInt32(false), false);
            this.currentPassword = r43;
            TwoStepVerificationActivity.initPasswordNewAlgo(r43);
        L11:
            this.newPassword = this.currentParams.getString("new_password");
            LoginActivity.m15836$$Nest$mshowKeyboard(this.this$0, this.codeField[0]);
            this.codeField[0].requestFocus();
        }

        @Override // org.telegram.ui.Components.SlideView
        public void updateColors() {
            this.titleTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
            this.confirmTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText6));
            EditTextBoldCursor[] r0 = this.codeField;
            int r1 = r0.length;
            int r2 = 0;
            int r3 = 0;
        L3:
            if (r3 >= r1) goto L5;
            EditTextBoldCursor r4 = r0[r3];
            r4.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
            r4.setCursorColor(Theme.getColor(Theme.key_windowBackgroundWhiteInputFieldActivated));
            r3 = r3 + 1;
            goto L3
        L5:
            OutlineTextContainerView[] r02 = this.outlineFields;
            int r12 = r02.length;
        L6:
            if (r2 >= r12) goto L8;
            r02[r2].updateColor();
            r2 = r2 + 1;
            goto L6
        L8:
            this.cancelButton.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText4));
            ImageView r03 = this.passwordButton;
            if (r03 != null) goto L11;
            return;
        L11:
            if (this.isPasswordVisible == false) goto L13;
            int r13 = Theme.key_windowBackgroundWhiteInputFieldActivated;
        L14:
            r03.setColorFilter(Theme.getColor(r13));
            this.passwordButton.setBackground(Theme.createSelectorDrawable(this.this$0.getThemedColor(Theme.key_listSelector), 1));
            return;
        L13:
            r13 = Theme.key_windowBackgroundWhiteHintText;
            goto L14
        }
    }

    public class LoginActivityPasswordView extends SlideView {
        private TextView cancelButton;
        private EditTextBoldCursor codeField;
        private TextView confirmTextView;
        private Bundle currentParams;
        private TL_account.Password currentPassword;
        private RLottieImageView lockImageView;
        private boolean nextPressed;
        private OutlineTextContainerView outlineCodeField;
        private String passwordString;
        private String phoneCode;
        private String phoneHash;
        private String requestPhone;
        final /* synthetic */ LoginActivity this$0;
        private TextView titleView;

        public static /* synthetic */ void $r8$lambda$3y5MvEhd94JnbAm7fxKDwdHcYIw(LoginActivityPasswordView r0, TLRPC.TL_auth_passwordRecovery r1, AlertDialog r2, int r3) {
            r0.lambda$new$2(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$6d26uQgFWHRqI1jFnoB4dIa7nZY(LoginActivityPasswordView r0, View r1, boolean r2) {
            r0.lambda$new$0(r1, r2);
        }

        public static /* synthetic */ boolean $r8$lambda$H5loevSq3KgD2wWo_h64JdV1NkM(LoginActivityPasswordView r0, TextView r1, int r2, KeyEvent r3) {
            return r0.lambda$new$1(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$KtyFC6O1zquEL8Bw8P8XYqeg1AE(LoginActivityPasswordView r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$new$4(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$LC7IrENorAJUH3ZdDrbJLpSluzc(LoginActivityPasswordView r0, TLRPC.TL_error r1, TLObject r2) {
            r0.lambda$onNextPressed$7(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$PCwnPB1wQK1wNIjNW3i1ke7t_bw(LoginActivityPasswordView r0, TLRPC.TL_error r1, TLObject r2) {
            r0.lambda$new$3(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$RRy4ZXVzqNM6y8cef_c8Pf0Qyfc(LoginActivityPasswordView r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$onNextPressed$11(r1, r2);
        }

        /* renamed from: $r8$lambda$TwX6yV9cRj3S6pO7UEjcS9-zYQQ, reason: not valid java name */
        public static /* synthetic */ void m15861$r8$lambda$TwX6yV9cRj3S6pO7UEjcS9zYQQ(LoginActivityPasswordView r0) {
            r0.lambda$onShow$13();
        }

        public static /* synthetic */ void $r8$lambda$Ys7BG20cEjYxymI5nmCCvbIVWxM(LoginActivityPasswordView r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$onNextPressed$8(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$ZNZtXI3vzQ3xVJQv0MN1XLcJM38(LoginActivityPasswordView r0, TLRPC.TL_error r1, TLObject r2) {
            r0.lambda$onNextPressed$10(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$_Vz1j7IeTnc7ULF9phcNyhxmBOw(LoginActivityPasswordView r0, AlertDialog r1, int r2) {
            r0.lambda$new$5(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$aY8Ct9WZdpDVfkeEY07Pb5OTLik(LoginActivityPasswordView r0, Context r1, View r2) {
            r0.lambda$new$6(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$pqqIEVBNyqHlg7Bw7747zSfUakk(LoginActivityPasswordView r0, TLObject r1) {
            r0.lambda$onNextPressed$9(r1);
        }

        /* renamed from: $r8$lambda$s1VFYlO_6-x_KoBOjP3tdtnQDPo, reason: not valid java name */
        public static /* synthetic */ void m15862$r8$lambda$s1VFYlO_6x_KoBOjP3tdtnQDPo(LoginActivityPasswordView r0, String r1) {
            r0.lambda$onNextPressed$12(r1);
        }

        /* renamed from: -$$Nest$fgetcurrentPassword, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TL_account.Password m15863$$Nest$fgetcurrentPassword(LoginActivityPasswordView r0) {
            return r0.currentPassword;
        }

        public LoginActivityPasswordView(LoginActivity r19, Context r20) {
            this.this$0 = r19;
            super(r20);
            setOrientation(1);
            FrameLayout r3 = new FrameLayout(r20);
            RLottieImageView r4 = new RLottieImageView(r20);
            this.lockImageView = r4;
            r4.setAnimation(org.telegram.messenger.R.raw.tsv_setup_intro, 120, 120);
            this.lockImageView.setAutoRepeat(false);
            r3.addView(this.lockImageView, LayoutHelper.createFrame(120, 120, 1));
            if (AndroidUtilities.isSmallScreen() == true) goto L10;
            Point r42 = AndroidUtilities.displaySize;
            if (r42.x > r42.y) goto L7;
        L9:
            int r43 = 0;
        L11:
            r3.setVisibility(r43);
            addView(r3, LayoutHelper.createFrame(-1, -2, 1));
            TextView r32 = new TextView(r20);
            this.titleView = r32;
            r32.setTextSize(1, 18.0f);
            this.titleView.setTypeface(AndroidUtilities.bold());
            this.titleView.setText(LocaleController.getString(org.telegram.messenger.R.string.YourPasswordHeader));
            this.titleView.setGravity(17);
            this.titleView.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            addView(this.titleView, LayoutHelper.createFrame(-1, -2.0f, 1, 32.0f, 16.0f, 32.0f, 0.0f));
            TextView r33 = new TextView(r20);
            this.confirmTextView = r33;
            r33.setTextSize(1, 14.0f);
            this.confirmTextView.setGravity(1);
            this.confirmTextView.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            this.confirmTextView.setText(LocaleController.getString(org.telegram.messenger.R.string.LoginPasswordTextShort));
            addView(this.confirmTextView, LayoutHelper.createLinear(-2, -2, 1, 12, 8, 12, 0));
            OutlineTextContainerView r34 = new OutlineTextContainerView(r20);
            this.outlineCodeField = r34;
            r34.setText(LocaleController.getString(org.telegram.messenger.R.string.EnterPassword));
            EditTextBoldCursor r35 = new EditTextBoldCursor(r20);
            this.codeField = r35;
            r35.setCursorSize(AndroidUtilities.dp(20.0f));
            this.codeField.setCursorWidth(1.5f);
            this.codeField.setBackground(null);
            this.codeField.setImeOptions(268435461);
            this.codeField.setTextSize(1, 18.0f);
            this.codeField.setMaxLines(1);
            int r7 = AndroidUtilities.dp(16.0f);
            this.codeField.setPadding(r7, r7, r7, r7);
            this.codeField.setInputType(129);
            this.codeField.setTransformationMethod(PasswordTransformationMethod.getInstance());
            this.codeField.setTypeface(Typeface.DEFAULT);
            EditTextBoldCursor r72 = this.codeField;
            if (LocaleController.isRTL == false) goto L14;
            int r9 = 5;
        L15:
            r72.setGravity(r9);
            this.codeField.setOnFocusChangeListener(new LoginActivity$LoginActivityPasswordView$$ExternalSyntheticLambda1(this));
            this.outlineCodeField.attachEditText(this.codeField);
            this.outlineCodeField.addView(this.codeField, LayoutHelper.createFrame(-1, -2, 48));
            this.codeField.setOnEditorActionListener(new LoginActivity$LoginActivityPasswordView$$ExternalSyntheticLambda2(this));
            addView(this.outlineCodeField, LayoutHelper.createLinear(-1, -2, 1, 16, 32, 16, 0));
            TextView r6 = new TextView(r20);
            this.cancelButton = r6;
            r6.setGravity(19);
            this.cancelButton.setText(LocaleController.getString(org.telegram.messenger.R.string.ForgotPassword));
            this.cancelButton.setTextSize(1, 15.0f);
            this.cancelButton.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            this.cancelButton.setPadding(AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(16.0f), 0);
            FrameLayout r1 = new FrameLayout(r20);
            r1.addView(this.cancelButton, LayoutHelper.createFrame(-1, 56.0f, 80, 0.0f, 0.0f, 0.0f, 32.0f));
            addView(r1, LayoutHelper.createLinear(-1, -1, 80));
            VerticalPositionAutoAnimator.attach(this.cancelButton);
            this.cancelButton.setOnClickListener(new LoginActivity$LoginActivityPasswordView$$ExternalSyntheticLambda3(this, r20));
            return;
        L14:
            r9 = 3;
            goto L15
        L7:
            if (AndroidUtilities.isTablet() == true) goto L9;
        L10:
            r43 = 8;
            goto L11
        }

        private /* synthetic */ void lambda$new$0(View r1, boolean r2) {
            OutlineTextContainerView r0 = this.outlineCodeField;
            if (r2 == false) goto L5;
            float r12 = 1.0f;
        L6:
            r0.animateSelection(r12);
            return;
        L5:
            r12 = 0.0f;
            goto L6
        }

        private /* synthetic */ boolean lambda$new$1(TextView r1, int r2, KeyEvent r3) {
            if (r2 != 5) goto L6;
            onNextPressed(null);
            return true;
        L6:
            return false;
        }

        private /* synthetic */ void lambda$new$2(TLRPC.TL_auth_passwordRecovery r2, AlertDialog r3, int r4) {
            Bundle r32 = new Bundle();
            r32.putString("email_unconfirmed_pattern", r2.email_pattern);
            r32.putString("password", this.passwordString);
            r32.putString("requestPhone", this.requestPhone);
            r32.putString("phoneHash", this.phoneHash);
            r32.putString("phoneCode", this.phoneCode);
            this.this$0.setPage(7, true, r32, false);
        }

        private /* synthetic */ void lambda$new$3(TLRPC.TL_error r8, TLObject r9) {
            LoginActivity.m15826$$Nest$mneedHideProgress(this.this$0, false);
            if (r8 != null) goto L18;
            TLRPC.TL_auth_passwordRecovery r92 = (TLRPC.TL_auth_passwordRecovery) r9;
            if (this.this$0.getParentActivity() == null) goto L28;
            AlertDialog.Builder r82 = new AlertDialog.Builder(this.this$0.getParentActivity());
            String r0 = r92.email_pattern;
            SpannableStringBuilder r2 = SpannableStringBuilder.valueOf(r0);
            int r4 = r0.indexOf(42);
            int r02 = r0.lastIndexOf(42);
            if (r4 != r02) goto L10;
        L13:
            r82.setMessage(AndroidUtilities.formatSpannable(LocaleController.getString(org.telegram.messenger.R.string.RestoreEmailSent), new CharSequence[]{r2}));
            r82.setTitle(LocaleController.getString("RestoreEmailSentTitle", org.telegram.messenger.R.string.RestoreEmailSentTitle));
            r82.setPositiveButton(LocaleController.getString(org.telegram.messenger.R.string.Continue), new LoginActivity$LoginActivityPasswordView$$ExternalSyntheticLambda10(this, r92));
            Dialog r7 = this.this$0.showDialog(r82.create());
            if (r7 == null) goto L27;
            r7.setCanceledOnTouchOutside(false);
            r7.setCancelable(false);
            return;
        L27:
            return;
        L10:
            if (r4 == (-1)) goto L13;
            if (r02 == (-1)) goto L13;
            TextStyleSpan.TextStyleRun r5 = new TextStyleSpan.TextStyleRun();
            r5.flags |= 256;
            r5.start = r4;
            int r03 = r02 + 1;
            r5.end = r03;
            r2.setSpan(new TextStyleSpan(r5), r4, r03, 0);
            goto L13
        L28:
            return;
        L18:
            if (r8.text.startsWith("FLOOD_WAIT") == false) goto L25;
            int r83 = Utilities.parseInt(r8.text);
            if (r83 >= 60) goto L22;
            String r84 = LocaleController.formatPluralString("Seconds", r83, new Object[0]);
        L23:
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.WrongCodeTitle), LocaleController.formatString("FloodWaitTime", org.telegram.messenger.R.string.FloodWaitTime, new Object[]{r84}));
            return;
        L22:
            r84 = LocaleController.formatPluralString("Minutes", r83 / 60, new Object[0]);
            goto L23
        L25:
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), r8.text);
        }

        private /* synthetic */ void lambda$new$4(TLObject r2, TLRPC.TL_error r3) {
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivityPasswordView$$ExternalSyntheticLambda8(this, r3, r2));
        }

        private /* synthetic */ void lambda$new$5(AlertDialog r2, int r3) {
            LoginActivity.m15837$$Nest$mtryResetAccount(this.this$0, this.requestPhone, this.phoneHash, this.phoneCode);
        }

        private /* synthetic */ void lambda$new$6(Context r2, View r3) {
            if (LoginActivity.m15799$$Nest$fgetradialProgressView(this.this$0).getTag() == null) goto L6;
            return;
        L6:
            if (this.currentPassword.has_recovery == false) goto L9;
            LoginActivity.m15829$$Nest$mneedShowProgress(this.this$0, 0);
            TLRPC.TL_auth_requestPasswordRecovery r22 = new TLRPC.TL_auth_requestPasswordRecovery();
            ConnectionsManager.getInstance(LoginActivity.access$2200(this.this$0)).sendRequest(r22, new LoginActivity$LoginActivityPasswordView$$ExternalSyntheticLambda5(this), 10);
            return;
        L9:
            AndroidUtilities.hideKeyboard(this.codeField);
            new AlertDialog.Builder(r2).setTitle(LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle)).setMessage(LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailText)).setPositiveButton(LocaleController.getString(org.telegram.messenger.R.string.Close), null).setNegativeButton(LocaleController.getString(org.telegram.messenger.R.string.ResetAccount), new LoginActivity$LoginActivityPasswordView$$ExternalSyntheticLambda6(this)).show();
        }

        private /* synthetic */ void lambda$onNextPressed$10(TLRPC.TL_error r5, TLObject r6) {
            this.nextPressed = false;
            if (r5 != null) goto L5;
        L8:
            boolean r1 = r6 instanceof TLRPC.TL_auth_authorization;
            LoginActivity r2 = this.this$0;
            if (r1 == false) goto L12;
            LoginActivity.m15835$$Nest$mshowDoneButton(r2, false, true);
            postDelayed(new LoginActivity$LoginActivityPasswordView$$ExternalSyntheticLambda12(this, r6), 150);
            return;
        L12:
            LoginActivity.m15826$$Nest$mneedHideProgress(r2, false);
            if (r5.text.equals("PASSWORD_HASH_INVALID") == false) goto L17;
            onPasscodeError(true);
            return;
        L17:
            if (r5.text.startsWith("FLOOD_WAIT") == false) goto L24;
            int r52 = Utilities.parseInt(r5.text);
            if (r52 >= 60) goto L21;
            String r53 = LocaleController.formatPluralString("Seconds", r52, new Object[0]);
        L22:
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.formatString("FloodWaitTime", org.telegram.messenger.R.string.FloodWaitTime, new Object[]{r53}));
            return;
        L21:
            r53 = LocaleController.formatPluralString("Minutes", r52 / 60, new Object[0]);
            goto L22
        L24:
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), r5.text);
            return;
        L5:
            if ("SRP_ID_INVALID".equals(r5.text) == false) goto L8;
            TL_account.getPassword r54 = new TL_account.getPassword();
            ConnectionsManager.getInstance(LoginActivity.access$2100(this.this$0)).sendRequest(r54, new LoginActivity$LoginActivityPasswordView$$ExternalSyntheticLambda11(this), 8);
        }

        private /* synthetic */ void lambda$onNextPressed$11(TLObject r2, TLRPC.TL_error r3) {
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivityPasswordView$$ExternalSyntheticLambda9(this, r3, r2));
        }

        private /* synthetic */ void lambda$onNextPressed$12(String r7) {
            TLRPC.PasswordKdfAlgo r0 = this.currentPassword.current_algo;
            boolean r1 = r0 instanceof TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow;
            if (r1 == false) goto L5;
            byte[] r72 = SRPHelper.getX(AndroidUtilities.getStringBytes(r7), (TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow) r0);
        L6:
            LoginActivity$LoginActivityPasswordView$$ExternalSyntheticLambda7 r3 = new LoginActivity$LoginActivityPasswordView$$ExternalSyntheticLambda7(this);
            if (r1 == false) goto L14;
            TL_account.Password r12 = this.currentPassword;
            TLRPC.TL_inputCheckPasswordSRP r73 = SRPHelper.startCheck(r72, r12.srp_id, r12.srp_B, (TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow) r0);
            if (r73 != null) goto L12;
            TLRPC.TL_error r6 = new TLRPC.TL_error();
            r6.text = "PASSWORD_HASH_INVALID";
            r3.run(null, r6);
            return;
        L12:
            TLRPC.TL_auth_checkPassword r02 = new TLRPC.TL_auth_checkPassword();
            r02.password = r73;
            ConnectionsManager.getInstance(LoginActivity.access$2000(this.this$0)).sendRequest(r02, r3, 10);
            return;
        L14:
            return;
        L5:
            r72 = null;
            goto L6
        }

        private /* synthetic */ void lambda$onNextPressed$7(TLRPC.TL_error r1, TLObject r2) {
            if (r1 != null) goto L5;
            this.currentPassword = (TL_account.Password) r2;
            onNextPressed(null);
            return;
        }

        private /* synthetic */ void lambda$onNextPressed$8(TLObject r2, TLRPC.TL_error r3) {
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivityPasswordView$$ExternalSyntheticLambda13(this, r3, r2));
        }

        private /* synthetic */ void lambda$onNextPressed$9(TLObject r3) {
            LoginActivity.m15827$$Nest$mneedHideProgress(this.this$0, false, false);
            AndroidUtilities.hideKeyboard(this.codeField);
            LoginActivity.m15831$$Nest$monAuthSuccess(this.this$0, (TLRPC.TL_auth_authorization) r3);
        }

        private /* synthetic */ void lambda$onShow$13() {
            EditTextBoldCursor r0 = this.codeField;
            if (r0 == null) goto L6;
            r0.requestFocus();
            EditTextBoldCursor r02 = this.codeField;
            r02.setSelection(r02.length());
            LoginActivity.m15836$$Nest$mshowKeyboard(this.this$0, this.codeField);
            this.lockImageView.getAnimatedDrawable().setCurrentFrame(0, false);
            this.lockImageView.playAnimation();
            return;
        }

        private void onPasscodeError(boolean r2) {
            if (this.this$0.getParentActivity() != null) goto L5;
            return;
        L5:
            if (r2 == false) goto L7;
            this.codeField.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        L7:
            LoginActivity.m15834$$Nest$monFieldError(this.this$0, this.outlineCodeField, true);
        }

        @Override // org.telegram.ui.Components.SlideView
        public String getHeaderName() {
            return LocaleController.getString("LoginPassword", org.telegram.messenger.R.string.LoginPassword);
        }

        @Override // org.telegram.ui.Components.SlideView
        public boolean needBackButton() {
            return true;
        }

        @Override // org.telegram.ui.Components.SlideView
        public boolean onBackPressed(boolean r2) {
            this.nextPressed = false;
            LoginActivity.m15826$$Nest$mneedHideProgress(this.this$0, true);
            this.currentParams = null;
            return true;
        }

        @Override // org.telegram.ui.Components.SlideView
        public void onCancelPressed() {
            this.nextPressed = false;
        }

        @Override // org.telegram.ui.Components.SlideView
        public void onNextPressed(String r3) {
            if (this.nextPressed == false) goto L6;
            return;
        L6:
            if (this.currentPassword != null) goto L8;
            return;
        L8:
            String r32 = this.codeField.getText().toString();
            if (r32.length() != 0) goto L12;
            onPasscodeError(false);
            return;
        L12:
            this.nextPressed = true;
            LoginActivity.m15829$$Nest$mneedShowProgress(this.this$0, 0);
            Utilities.globalQueue.postRunnable(new LoginActivity$LoginActivityPasswordView$$ExternalSyntheticLambda4(this, r32));
        }

        @Override // org.telegram.ui.Components.SlideView
        public void onShow() {
            super.onShow();
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivityPasswordView$$ExternalSyntheticLambda0(this), LoginActivity.m15838$$Nest$sfgetSHOW_DELAY());
        }

        @Override // org.telegram.ui.Components.SlideView
        public void restoreStateParams(Bundle r3) {
            Bundle r0 = r3.getBundle("passview_params");
            this.currentParams = r0;
            if (r0 == null) goto L5;
            setParams(r0, true);
        L5:
            String r32 = r3.getString("passview_code");
            if (r32 == null) goto L9;
            this.codeField.setText(r32);
            return;
        }

        @Override // org.telegram.ui.Components.SlideView
        public void saveStateParams(Bundle r3) {
            String r0 = this.codeField.getText().toString();
            if (r0.length() == 0) goto L5;
            r3.putString("passview_code", r0);
        L5:
            Bundle r2 = this.currentParams;
            if (r2 == null) goto L9;
            r3.putBundle("passview_params", r2);
            return;
        }

        @Override // org.telegram.ui.Components.SlideView
        public void setParams(Bundle r3, boolean r4) {
            if (r3 != null) goto L4;
            return;
        L4:
            boolean r42 = r3.isEmpty();
            EditTextBoldCursor r0 = this.codeField;
            if (r42 == false) goto L8;
            AndroidUtilities.hideKeyboard(r0);
            return;
        L8:
            r0.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            this.currentParams = r3;
            String r43 = r3.getString("password");
            this.passwordString = r43;
            if (r43 == null) goto L11;
            SerializedData r02 = new SerializedData(Utilities.hexToBytes(r43));
            this.currentPassword = TL_account.Password.TLdeserialize(r02, r02.readInt32(false), false);
        L11:
            this.requestPhone = r3.getString("phoneFormated");
            this.phoneHash = r3.getString("phoneHash");
            this.phoneCode = r3.getString("code");
            TL_account.Password r32 = this.currentPassword;
            if (r32 != null) goto L14;
        L17:
            this.codeField.setHint(null);
            return;
        L14:
            if (TextUtils.isEmpty(r32.hint) == true) goto L17;
            this.codeField.setHint(this.currentPassword.hint);
        }

        @Override // org.telegram.ui.Components.SlideView
        public void updateColors() {
            TextView r0 = this.titleView;
            int r1 = Theme.key_windowBackgroundWhiteBlackText;
            r0.setTextColor(Theme.getColor(r1));
            this.confirmTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText6));
            this.codeField.setTextColor(Theme.getColor(r1));
            this.codeField.setCursorColor(Theme.getColor(r1));
            this.codeField.setHintTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteHintText));
            this.cancelButton.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText4));
            this.outlineCodeField.updateColor();
        }
    }

    public class LoginActivityPhraseView extends SlideView {
        private String beginning;
        private final Runnable checkPasteRunnable;
        private final EditTextBoldCursor codeField;
        private int codeTime;
        private final TextView confirmTextView;
        private Bundle currentParams;
        private final int currentType;
        private final Runnable dismissField;
        private String emailPhone;
        private boolean errorShown;
        private final TextView errorTextView;
        private final LinearLayout fieldContainer;
        private final RLottieImageView imageView;
        private final FrameLayout infoContainer;
        private final TextView infoTextView;
        private boolean isResendingCode;
        private double lastCurrentTime;
        private String lastError;
        private TLRPC.TL_auth_sentCode nextCodeAuth;
        private Bundle nextCodeParams;
        private boolean nextPressed;
        private int nextType;
        private final OutlineTextContainerView outlineField;
        private boolean pasteShown;
        private final TextView pasteTextView;
        private boolean pasted;
        private boolean pasting;
        private String phone;
        private String phoneHash;
        private int prevType;
        private final TextView prevTypeTextView;
        private String requestPhone;
        private float shiftDp;
        final /* synthetic */ LoginActivity this$0;
        private int time;
        private final LoadingTextView timeText;
        private Timer timeTimer;
        private final Object timerSync;
        private final TextView titleTextView;
        private boolean waitingForEvent;

        /* renamed from: org.telegram.ui.LoginActivity$LoginActivityPhraseView$1, reason: invalid class name */
        public class AnonymousClass1 extends EditTextBoldCursor {
            final /* synthetic */ LoginActivityPhraseView this$1;
            final /* synthetic */ LoginActivity val$this$0;

            /* renamed from: $r8$lambda$yOjA_G2-6_nNaO-M0CaDUH0ks1s, reason: not valid java name */
            public static /* synthetic */ void m15887$r8$lambda$yOjA_G26_nNaOM0CaDUH0ks1s(AnonymousClass1 r0) {
                r0.lambda$onTextContextMenuItem$0();
            }

            public AnonymousClass1(LoginActivityPhraseView r1, Context r2, LoginActivity r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            private /* synthetic */ void lambda$onTextContextMenuItem$0() {
                LoginActivityPhraseView.m15879$$Nest$fputpasting(this.this$1, false);
            }

            @Override // org.telegram.ui.Components.EditTextBoldCursor, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.EditText, android.widget.TextView
            public boolean onTextContextMenuItem(int r4) {
                if (r4 != 16908322) goto L5;
            L7:
                LoginActivityPhraseView r0 = this.this$1;
                LoginActivityPhraseView.m15878$$Nest$fputpasted(r0, true);
                LoginActivityPhraseView.m15879$$Nest$fputpasting(r0, true);
                postDelayed(new LoginActivity$LoginActivityPhraseView$1$$ExternalSyntheticLambda0(this), 1000);
            L9:
                return super.onTextContextMenuItem(r4);
            L5:
                if (r4 == 16908337) goto L7;
                goto L7
            }
        }

        /* renamed from: org.telegram.ui.LoginActivity$LoginActivityPhraseView$2, reason: invalid class name */
        public class AnonymousClass2 implements TextWatcher {
            private boolean ignoreTextChange;
            final /* synthetic */ LoginActivityPhraseView this$1;
            private int trimmedLength;
            final /* synthetic */ LoginActivity val$this$0;

            public AnonymousClass2(LoginActivityPhraseView r1, LoginActivity r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable r6) {
                if (this.ignoreTextChange == true) goto L22;
                boolean r1 = true;
                LoginActivityPhraseView.m15883$$Nest$mcheckPaste(this.this$1, true);
                AndroidUtilities.cancelRunOnUIThread(LoginActivityPhraseView.m15869$$Nest$fgetdismissField(this.this$1));
                LoginActivityPhraseView.m15881$$Nest$manimateError(this.this$1, false);
                if (TextUtils.isEmpty(r6) == false) goto L9;
                LoginActivityPhraseView.m15878$$Nest$fputpasted(this.this$1, false);
            L9:
                if (LoginActivityPhraseView.m15882$$Nest$mbeginsOk(this.this$1, r6.toString()) == true) goto L21;
                LoginActivityPhraseView.m15885$$Nest$monInputError(this.this$1, true);
                this.ignoreTextChange = true;
                if (LoginActivityPhraseView.m15868$$Nest$fgetcodeField(this.this$1).getSelectionEnd() >= LoginActivityPhraseView.m15868$$Nest$fgetcodeField(this.this$1).getText().length()) goto L15;
                r1 = false;
            L15:
                if (LoginActivityPhraseView.m15873$$Nest$fgetpasted(this.this$1) == true) goto L19;
                LoginActivityPhraseView.m15868$$Nest$fgetcodeField(this.this$1).setText(LoginActivityPhraseView.m15867$$Nest$fgetbeginning(this.this$1).substring(0, Utilities.clamp(this.trimmedLength, LoginActivityPhraseView.m15867$$Nest$fgetbeginning(this.this$1).length(), 0)));
                if (r1 == false) goto L19;
                LoginActivityPhraseView.m15868$$Nest$fgetcodeField(this.this$1).setSelection(LoginActivityPhraseView.m15868$$Nest$fgetcodeField(this.this$1).getText().length());
            L19:
                this.ignoreTextChange = false;
                return;
            L21:
                return;
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
                if (this.ignoreTextChange == true) goto L12;
                if (r1 != null) goto L7;
                return;
            L7:
                if (LoginActivityPhraseView.m15867$$Nest$fgetbeginning(this.this$1) == null) goto L13;
                this.trimmedLength = LoginActivityPhraseView.m15886$$Nest$mtrimLeft(this.this$1, r1.toString()).length();
                return;
            L13:
                return;
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }
        }

        /* renamed from: org.telegram.ui.LoginActivity$LoginActivityPhraseView$3, reason: invalid class name */
        public class AnonymousClass3 extends LoadingTextView {
            final /* synthetic */ LoginActivityPhraseView this$1;
            final /* synthetic */ LoginActivity val$this$0;

            public AnonymousClass3(LoginActivityPhraseView r1, Context r2, LoginActivity r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r1.this$0, r2);
            }

            @Override // org.telegram.ui.LoginActivity.LoadingTextView
            public boolean isResendingCode() {
                return LoginActivityPhraseView.m15870$$Nest$fgetisResendingCode(this.this$1);
            }

            @Override // org.telegram.ui.LoginActivity.LoadingTextView
            public boolean isRippleEnabled() {
                if (getVisibility() == 0) goto L5;
                return false;
            L5:
                if (LoginActivityPhraseView.m15874$$Nest$fgettime(this.this$1) > 0) goto L7;
                return true;
            L7:
                if (LoginActivityPhraseView.m15876$$Nest$fgettimeTimer(this.this$1) != null) goto L13;
                return true;
            L13:
                return false;
            }
        }

        /* renamed from: org.telegram.ui.LoginActivity$LoginActivityPhraseView$4, reason: invalid class name */
        public class AnonymousClass4 extends TimerTask {
            final /* synthetic */ LoginActivityPhraseView this$1;

            public static /* synthetic */ void $r8$lambda$6Ws9hyaRB4VbAkPBlAgH45vD0As(AnonymousClass4 r0) {
                r0.lambda$run$0();
            }

            public AnonymousClass4(LoginActivityPhraseView r1) {
                this.this$1 = r1;
            }

            private /* synthetic */ void lambda$run$0() {
                double r0 = System.currentTimeMillis();
                double r2 = r0 - LoginActivityPhraseView.m15871$$Nest$fgetlastCurrentTime(this.this$1);
                LoginActivityPhraseView.m15877$$Nest$fputlastCurrentTime(this.this$1, r0);
                LoginActivityPhraseView.m15880$$Nest$fputtime(this.this$1, (int) (LoginActivityPhraseView.m15874$$Nest$fgettime(r0) - r2));
                int r02 = LoginActivityPhraseView.m15874$$Nest$fgettime(this.this$1);
                LoginActivityPhraseView r1 = this.this$1;
                if (r02 < 1000) goto L17;
                int r03 = (LoginActivityPhraseView.m15874$$Nest$fgettime(r1) / 1000) / 60;
                int r12 = (LoginActivityPhraseView.m15874$$Nest$fgettime(this.this$1) / 1000) - (r03 * 60);
                LoginActivityPhraseView.m15875$$Nest$fgettimeText(this.this$1).setTextSize(1, 13.0f);
                if (LoginActivityPhraseView.m15872$$Nest$fgetnextType(this.this$1) != 4) goto L7;
            L15:
                LoginActivityPhraseView.m15875$$Nest$fgettimeText(this.this$1).setText(LocaleController.formatString(org.telegram.messenger.R.string.CallAvailableIn2, new Object[]{Integer.valueOf(r03), Integer.valueOf(r12)}));
                return;
            L7:
                if (LoginActivityPhraseView.m15872$$Nest$fgetnextType(this.this$1) == 3) goto L15;
                if (LoginActivityPhraseView.m15872$$Nest$fgetnextType(this.this$1) == 11) goto L15;
                if (LoginActivityPhraseView.m15872$$Nest$fgetnextType(this.this$1) != 2) goto L26;
                LoginActivityPhraseView.m15875$$Nest$fgettimeText(this.this$1).setText(LocaleController.formatString(org.telegram.messenger.R.string.SmsAvailableIn2, new Object[]{Integer.valueOf(r03), Integer.valueOf(r12)}));
                return;
            L26:
                return;
            L17:
                LoginActivityPhraseView.m15884$$Nest$mdestroyTimer(r1);
                if (LoginActivityPhraseView.m15872$$Nest$fgetnextType(this.this$1) != 3) goto L20;
            L27:
                LoginActivityPhraseView.m15875$$Nest$fgettimeText(this.this$1).setTextSize(1, 15.0f);
                int r04 = LoginActivityPhraseView.m15872$$Nest$fgetnextType(this.this$1);
                LoginActivityPhraseView r13 = this.this$1;
                if (r04 != 4) goto L30;
                LoginActivityPhraseView.m15875$$Nest$fgettimeText(r13).setText(LocaleController.getString(org.telegram.messenger.R.string.RequestCallButton));
            L40:
                LoadingTextView r05 = LoginActivityPhraseView.m15875$$Nest$fgettimeText(this.this$1);
                int r14 = Theme.key_chats_actionBackground;
                r05.setTextColor(Theme.getColor(r14));
                LoginActivityPhraseView.m15875$$Nest$fgettimeText(this.this$1).setTag(org.telegram.messenger.R.id.color_key_tag, Integer.valueOf(r14));
                return;
            L30:
                int r06 = LoginActivityPhraseView.m15872$$Nest$fgetnextType(r13);
                LoginActivityPhraseView r15 = this.this$1;
                if (r06 != 15) goto L34;
                LoginActivityPhraseView.m15875$$Nest$fgettimeText(r15).setText(LocaleController.getString(org.telegram.messenger.R.string.DidNotGetTheCodeFragment));
                goto L40
            L34:
                if (LoginActivityPhraseView.m15872$$Nest$fgetnextType(r15) != 11) goto L36;
            L39:
                LoginActivityPhraseView.m15875$$Nest$fgettimeText(this.this$1).setText(LocaleController.getString(org.telegram.messenger.R.string.RequestMissedCall));
                goto L40
            L36:
                if (LoginActivityPhraseView.m15872$$Nest$fgetnextType(this.this$1) == 3) goto L39;
                LoginActivityPhraseView.m15875$$Nest$fgettimeText(this.this$1).setText(AndroidUtilities.replaceArrows(LocaleController.getString(org.telegram.messenger.R.string.RequestAnotherSMS), true, 0.0f, 0.0f));
                goto L40
            L20:
                if (LoginActivityPhraseView.m15872$$Nest$fgetnextType(this.this$1) == 4) goto L27;
                if (LoginActivityPhraseView.m15872$$Nest$fgetnextType(this.this$1) == 2) goto L27;
                if (LoginActivityPhraseView.m15872$$Nest$fgetnextType(this.this$1) == 11) goto L27;
            }

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (LoginActivityPhraseView.m15876$$Nest$fgettimeTimer(this.this$1) != null) goto L5;
                return;
            L5:
                AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivityPhraseView$4$$ExternalSyntheticLambda0(this));
            }
        }

        public static /* synthetic */ void $r8$lambda$59Qt4rSBDehKxzSbroLmMSXw5M4(LoginActivityPhraseView r0) {
            r0.lambda$new$8();
        }

        public static /* synthetic */ void $r8$lambda$78auo4enSe4f_MhbCAm5nyJTmdg(LoginActivityPhraseView r0, TLRPC.TL_error r1, TLObject r2, TLRPC.TL_auth_signIn r3) {
            r0.lambda$onNextPressed$9(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$AqbqRgKXuFfjXl8FinaXc_wylq4(LoginActivityPhraseView r0, View r1, boolean r2) {
            r0.lambda$new$0(r1, r2);
        }

        /* renamed from: $r8$lambda$B5pfn987_adrMYrFGCaqk1h6-3I, reason: not valid java name */
        public static /* synthetic */ void m15864$r8$lambda$B5pfn987_adrMYrFGCaqk1h63I(LoginActivityPhraseView r0, Bundle r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$resendCode$15(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$CbbnlKrlH9D9Y4YZrozbB8PYzLg(LoginActivityPhraseView r0, TLRPC.TL_error r1, Bundle r2, TLObject r3) {
            r0.lambda$resendCode$14(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$EAd2sKAa4tPccUXNmXzINYfzVYk(LoginActivityPhraseView r0) {
            r0.lambda$onNextPressed$11();
        }

        public static /* synthetic */ void $r8$lambda$G8OzuNBOcbjTbevXmhFQGIubAzk(LoginActivityPhraseView r0, TLRPC.TL_error r1, TLObject r2, TLRPC.TL_auth_signIn r3) {
            r0.lambda$onNextPressed$12(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$KvbWKghFqtuXrJl3w1tDE3Iye44(LoginActivityPhraseView r0, Bundle r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$new$5(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$NY9F9NzTQ8S35cpfN4WRg1pCrTo(LoginActivityPhraseView r0, TLRPC.TL_auth_signIn r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$onNextPressed$10(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$OtWPOsh7VYIImLXChvDi7nZjBVE(LoginActivityPhraseView r0, TLRPC.TL_auth_signIn r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$onNextPressed$13(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$XfIVhNDB6CggtlV8_jgPmTUA4AQ(LoginActivityPhraseView r0, View r1) {
            r0.lambda$new$1(r1);
        }

        public static /* synthetic */ boolean $r8$lambda$a3pMBcEZSvwUPNkcy0_ZR3hHkCI(LoginActivityPhraseView r0, TextView r1, int r2, KeyEvent r3) {
            return r0.lambda$new$2(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$aB-WSQTQ0FN8Gskjhou_orSv3Sc, reason: not valid java name */
        public static /* synthetic */ void m15865$r8$lambda$aBWSQTQ0FN8Gskjhou_orSv3Sc(LoginActivityPhraseView r0, TLObject r1, Bundle r2, TLRPC.TL_error r3) {
            r0.lambda$new$4(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$eM7zHBUPektFEyPuLbPePlGFIAs(LoginActivityPhraseView r0) {
            r0.lambda$new$7();
        }

        public static /* synthetic */ void $r8$lambda$ed604TFlg7KcFtKRcYtlKBav1Fw(LoginActivityPhraseView r0, View r1) {
            r0.lambda$new$6(r1);
        }

        /* renamed from: $r8$lambda$gL3-AKUEtqxHzqO1Qd1x8H_EDLA, reason: not valid java name */
        public static /* synthetic */ void m15866$r8$lambda$gL3AKUEtqxHzqO1Qd1x8H_EDLA(LoginActivityPhraseView r0, View r1) {
            r0.lambda$new$3(r1);
        }

        public static /* synthetic */ void $r8$lambda$sij0REe_q9RE34kC1GhYxXrejWI(LoginActivityPhraseView r0) {
            r0.lambda$onShow$16();
        }

        /* renamed from: -$$Nest$fgetbeginning, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m15867$$Nest$fgetbeginning(LoginActivityPhraseView r0) {
            return r0.beginning;
        }

        /* renamed from: -$$Nest$fgetcodeField, reason: not valid java name */
        public static /* bridge */ /* synthetic */ EditTextBoldCursor m15868$$Nest$fgetcodeField(LoginActivityPhraseView r0) {
            return r0.codeField;
        }

        /* renamed from: -$$Nest$fgetdismissField, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Runnable m15869$$Nest$fgetdismissField(LoginActivityPhraseView r0) {
            return r0.dismissField;
        }

        /* renamed from: -$$Nest$fgetisResendingCode, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m15870$$Nest$fgetisResendingCode(LoginActivityPhraseView r0) {
            return r0.isResendingCode;
        }

        /* renamed from: -$$Nest$fgetlastCurrentTime, reason: not valid java name */
        public static /* bridge */ /* synthetic */ double m15871$$Nest$fgetlastCurrentTime(LoginActivityPhraseView r2) {
            return r2.lastCurrentTime;
        }

        /* renamed from: -$$Nest$fgetnextType, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m15872$$Nest$fgetnextType(LoginActivityPhraseView r0) {
            return r0.nextType;
        }

        /* renamed from: -$$Nest$fgetpasted, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m15873$$Nest$fgetpasted(LoginActivityPhraseView r0) {
            return r0.pasted;
        }

        /* renamed from: -$$Nest$fgettime, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m15874$$Nest$fgettime(LoginActivityPhraseView r0) {
            return r0.time;
        }

        /* renamed from: -$$Nest$fgettimeText, reason: not valid java name */
        public static /* bridge */ /* synthetic */ LoadingTextView m15875$$Nest$fgettimeText(LoginActivityPhraseView r0) {
            return r0.timeText;
        }

        /* renamed from: -$$Nest$fgettimeTimer, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Timer m15876$$Nest$fgettimeTimer(LoginActivityPhraseView r0) {
            return r0.timeTimer;
        }

        /* renamed from: -$$Nest$fputlastCurrentTime, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15877$$Nest$fputlastCurrentTime(LoginActivityPhraseView r0, double r1) {
            r0.lastCurrentTime = r1;
        }

        /* renamed from: -$$Nest$fputpasted, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15878$$Nest$fputpasted(LoginActivityPhraseView r0, boolean r1) {
            r0.pasted = r1;
        }

        /* renamed from: -$$Nest$fputpasting, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15879$$Nest$fputpasting(LoginActivityPhraseView r0, boolean r1) {
            r0.pasting = r1;
        }

        /* renamed from: -$$Nest$fputtime, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15880$$Nest$fputtime(LoginActivityPhraseView r0, int r1) {
            r0.time = r1;
        }

        /* renamed from: -$$Nest$manimateError, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15881$$Nest$manimateError(LoginActivityPhraseView r0, boolean r1) {
            r0.animateError(r1);
        }

        /* renamed from: -$$Nest$mbeginsOk, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m15882$$Nest$mbeginsOk(LoginActivityPhraseView r0, String r1) {
            return r0.beginsOk(r1);
        }

        /* renamed from: -$$Nest$mcheckPaste, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15883$$Nest$mcheckPaste(LoginActivityPhraseView r0, boolean r1) {
            r0.checkPaste(r1);
        }

        /* renamed from: -$$Nest$mdestroyTimer, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15884$$Nest$mdestroyTimer(LoginActivityPhraseView r0) {
            r0.destroyTimer();
        }

        /* renamed from: -$$Nest$monInputError, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15885$$Nest$monInputError(LoginActivityPhraseView r0, boolean r1) {
            r0.onInputError(r1);
        }

        /* renamed from: -$$Nest$mtrimLeft, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m15886$$Nest$mtrimLeft(LoginActivityPhraseView r0, String r1) {
            return r0.trimLeft(r1);
        }

        public LoginActivityPhraseView(LoginActivity r26, Context r27, int r28) {
            this.this$0 = r26;
            super(r27);
            this.pasteShown = true;
            this.errorShown = false;
            this.pasting = false;
            this.pasted = false;
            this.timerSync = new Object();
            this.time = 60000;
            this.codeTime = 15000;
            this.lastError = _UrlKt.FRAGMENT_ENCODE_SET;
            this.checkPasteRunnable = new LoginActivity$LoginActivityPhraseView$$ExternalSyntheticLambda1(this);
            this.dismissField = new LoginActivity$LoginActivityPhraseView$$ExternalSyntheticLambda2(this);
            this.shiftDp = -3.0f;
            this.currentType = r28;
            if (r28 != 16) goto L5;
            boolean r3 = false;
        L6:
            setOrientation(1);
            RLottieImageView r6 = new RLottieImageView(r27);
            this.imageView = r6;
            r6.setScaleType(ImageView.ScaleType.CENTER);
            r6.setAnimation(org.telegram.messenger.R.raw.bubble, 95, 95);
            if (AndroidUtilities.isSmallScreen() == true) goto L14;
            Point r7 = AndroidUtilities.displaySize;
            if (r7.x > r7.y) goto L11;
        L13:
            boolean r72 = false;
        L16:
            if (r72 == false) goto L18;
            int r9 = 8;
        L19:
            r6.setVisibility(r9);
            addView(r6, LayoutHelper.createLinear(95, 95, 1, 0, 10, 0, 5));
            TextView r62 = new TextView(r27);
            this.titleTextView = r62;
            r62.setTextSize(1, 18.0f);
            r62.setTypeface(AndroidUtilities.bold());
            r62.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            r62.setGravity(49);
            if (r3 == true) goto L22;
            int r11 = org.telegram.messenger.R.string.SMSWordTitle;
        L23:
            r62.setText(LocaleController.getString(r11));
            if (r72 == false) goto L26;
            int r17 = 25;
        L27:
            addView(r62, LayoutHelper.createLinear(-2, -2, 1, 8, r17, 8, 0));
            TextView r63 = new TextView(r27);
            this.confirmTextView = r63;
            r63.setTextSize(1, 14.0f);
            r63.setGravity(1);
            r63.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            addView(r63, LayoutHelper.createLinear(-2, -2, 1, 8, 5, 8, 16));
            OutlineTextContainerView r64 = new OutlineTextContainerView(r27);
            this.outlineField = r64;
            if (r3 == true) goto L30;
            int r112 = org.telegram.messenger.R.string.SMSWord;
        L31:
            r64.setText(LocaleController.getString(r112));
            AnonymousClass1 r113 = new AnonymousClass1(this, r27, r26);
            this.codeField = r113;
            r113.setSingleLine();
            r113.setLines(1);
            r113.setCursorSize(AndroidUtilities.dp(20.0f));
            r113.setCursorWidth(1.5f);
            r113.setImeOptions(268435461);
            r113.setTextSize(1, 18.0f);
            r113.setMaxLines(1);
            r113.setBackground(null);
            if (r3 == true) goto L34;
            int r92 = org.telegram.messenger.R.string.SMSWordHint;
        L35:
            r113.setHint(LocaleController.getString(r92));
            r113.addTextChangedListener(new AnonymousClass2(this, r26));
            r113.setEllipsizeByGradient(true);
            r113.setInputType(1);
            r113.setTypeface(Typeface.DEFAULT);
            if (LocaleController.isRTL == false) goto L38;
            int r93 = 5;
        L39:
            r113.setGravity(r93);
            r113.setOnFocusChangeListener(new LoginActivity$LoginActivityPhraseView$$ExternalSyntheticLambda3(this));
            TextView r94 = new TextView(r27);
            this.pasteTextView = r94;
            r94.setTextSize(1, 12.0f);
            r94.setTypeface(AndroidUtilities.bold());
            r94.setText(LocaleController.getString(org.telegram.messenger.R.string.Paste));
            r94.setPadding(AndroidUtilities.dp(10.0f), 0, AndroidUtilities.dp(10.0f), 0);
            r94.setGravity(17);
            int r5 = Theme.getColor(Theme.key_windowBackgroundWhiteBlueText2, LoginActivity.access$4000(r26));
            r94.setTextColor(r5);
            r94.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(6.0f), Theme.multAlpha(r5, 0.12f), Theme.multAlpha(r5, 0.15f)));
            ScaleStateListAnimator.apply(r94, 0.1f, 1.5f);
            r113.setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(13.34f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(13.34f));
            r94.setOnClickListener(new LoginActivity$LoginActivityPhraseView$$ExternalSyntheticLambda4(this));
            r64.addView(r113, LayoutHelper.createFrame(-1, -2.0f, 119, 0.0f, 0.0f, 0.0f, 0.0f));
            r64.attachEditText(r113);
            r64.addView(r94, LayoutHelper.createFrame(-2, 26.0f, 21, 0.0f, 0.0f, 10.0f, 0.0f));
            LinearLayout r95 = new LinearLayout(r27);
            this.fieldContainer = r95;
            r95.setOrientation(1);
            r95.addView(r64, LayoutHelper.createLinear(-1, -2, 1));
            addView(r95, LayoutHelper.createLinear(-1, -2, 1, 16, 3, 16, 0));
            r113.setOnEditorActionListener(new LoginActivity$LoginActivityPhraseView$$ExternalSyntheticLambda5(this));
            FrameLayout r65 = new FrameLayout(r27);
            this.infoContainer = r65;
            r95.addView(r65, LayoutHelper.createLinear(-1, -2));
            LoadingTextView r96 = new LoadingTextView(r26, r27);
            this.prevTypeTextView = r96;
            int r114 = Theme.key_windowBackgroundWhiteValueText;
            r96.setLinkTextColor(Theme.getColor(r114));
            r96.setTextColor(r26.getThemedColor(r114));
            r96.setTextSize(1, 14.0f);
            r96.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            r96.setPadding(AndroidUtilities.dp(14.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(14.0f), AndroidUtilities.dp(16.0f));
            r96.setOnClickListener(new LoginActivity$LoginActivityPhraseView$$ExternalSyntheticLambda6(this));
            addView(r96, LayoutHelper.createLinear(-2, -2, 1, 0, 18, 0, 0));
            r96.setVisibility(8);
            TextView r52 = new TextView(r27);
            this.errorTextView = r52;
            r52.setPivotX(0.0f);
            r52.setPivotY(0.0f);
            if (r3 == true) goto L42;
            int r8 = org.telegram.messenger.R.string.SMSWordError;
        L43:
            r52.setText(LocaleController.getString(r8));
            r52.setTextColor(r26.getThemedColor(Theme.key_text_RedRegular));
            r52.setTextSize(1, 13.0f);
            r65.addView(r52, LayoutHelper.createFrame(-1, -2.0f, 119, 16.0f, 8.0f, 16.0f, 8.0f));
            r52.setAlpha(0.0f);
            r52.setScaleX(0.8f);
            r52.setScaleY(0.8f);
            r52.setTranslationY(-AndroidUtilities.dp(4.0f));
            TextView r53 = new TextView(r27);
            this.infoTextView = r53;
            r53.setPivotX(0.0f);
            r53.setPivotY(0.0f);
            if (r3 == true) goto L46;
            int r32 = org.telegram.messenger.R.string.SMSWordPasteHint;
        L47:
            r53.setText(LocaleController.getString(r32));
            r53.setTextColor(r26.getThemedColor(Theme.key_windowBackgroundWhiteGrayText));
            r53.setTextSize(1, 13.0f);
            r65.addView(r53, LayoutHelper.createFrame(-1, -2.0f, 119, 16.0f, 8.0f, 16.0f, 8.0f));
            AnonymousClass3 r33 = new AnonymousClass3(this, r27, r26);
            this.timeText = r33;
            r33.setLinkTextColor(Theme.getColor(r114));
            r33.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            r33.setPadding(AndroidUtilities.dp(14.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(14.0f), AndroidUtilities.dp(16.0f));
            r33.setTextSize(1, 15.0f);
            r33.setGravity(19);
            r33.setOnClickListener(new LoginActivity$LoginActivityPhraseView$$ExternalSyntheticLambda7(this));
            FrameLayout r1 = new FrameLayout(r27);
            r1.addView(r33, LayoutHelper.createFrame(-1, 56.0f, 80, 6.0f, 0.0f, 60.0f, 28.0f));
            addView(r1, LayoutHelper.createLinear(-1, -1, 80));
            VerticalPositionAutoAnimator.attach(r33);
            return;
        L46:
            r32 = org.telegram.messenger.R.string.SMSPhrasePasteHint;
            goto L47
        L42:
            r8 = org.telegram.messenger.R.string.SMSPhraseError;
            goto L43
        L38:
            r93 = 3;
            goto L39
        L34:
            r92 = org.telegram.messenger.R.string.SMSPhraseHint;
            goto L35
        L30:
            r112 = org.telegram.messenger.R.string.SMSPhrase;
            goto L31
        L26:
            r17 = 0;
            goto L27
        L22:
            r11 = org.telegram.messenger.R.string.SMSPhraseTitle;
            goto L23
        L18:
            r9 = 0;
            goto L19
        L11:
            if (AndroidUtilities.isTablet() == true) goto L13;
        L14:
            r72 = true;
            goto L16
        L5:
            r3 = true;
            goto L6
        }

        private void animateError(boolean r9) {
            this.errorShown = r9;
            float r0 = 0.0f;
            if (r9 == false) goto L5;
            float r92 = 1.0f;
        L6:
            this.outlineField.animateError(r92);
            float r4 = (r92 * 0.1f) + 0.9f;
            ViewPropertyAnimator r2 = this.errorTextView.animate().scaleX(r4).scaleY(r4).alpha(r92);
            float r42 = -5.0f;
            ViewPropertyAnimator r93 = r2.translationY((1.0f - r92) * AndroidUtilities.dp(-5.0f));
            CubicBezierInterpolator r22 = CubicBezierInterpolator.EASE_OUT_QUINT;
            r93.setInterpolator(r22).setDuration(290).start();
            if (this.pasteShown == true) goto L9;
        L11:
            float r3 = (0.1f * r0) + 0.9f;
            ViewPropertyAnimator r94 = this.infoTextView.animate().scaleX(r3).scaleY(r3).alpha(r0);
            float r1 = 1.0f - r0;
            if (this.errorShown == false) goto L14;
            r42 = 5.0f;
        L14:
            r94.translationY(r1 * AndroidUtilities.dp(r42)).setInterpolator(r22).setDuration(290).start();
            return;
        L9:
            if (this.errorShown == true) goto L11;
            r0 = 1.0f;
            goto L11
        L5:
            r92 = 0.0f;
            goto L6
        }

        private boolean beginsOk(String r4) {
            if (this.beginning != null) goto L5;
            return true;
        L5:
            String r42 = trimLeft(r4).toLowerCase();
            String r3 = this.beginning.toLowerCase();
            int r0 = Math.min(r42.length(), r3.length());
            if (r0 > 0) goto L9;
            return true;
        L9:
            return TextUtils.equals(r42.substring(0, r0), r3.substring(0, r0));
        }

        private void checkPaste(boolean r11) {
            AndroidUtilities.cancelRunOnUIThread(this.checkPasteRunnable);
            ClipboardManager r0 = (ClipboardManager) getContext().getSystemService("clipboard");
            if (TextUtils.isEmpty(this.codeField.getText()) == false) goto L8;
            if (r0 == null) goto L8;
            if (r0.hasPrimaryClip() == false) goto L8;
            boolean r02 = true;
        L10:
            if (this.pasteShown == r02) goto L88;
            this.pasteShown = r02;
            TextView r1 = this.pasteTextView;
            float r2 = -5.0f;
            float r4 = 0.9f;
            float r5 = 0.7f;
            float r6 = 0.0f;
            float r7 = 1.0f;
            if (r11 == false) goto L51;
            ViewPropertyAnimator r112 = r1.animate();
            if (r02 == false) goto L16;
            float r12 = 1.0f;
        L17:
            ViewPropertyAnimator r113 = r112.alpha(r12);
            if (r02 == false) goto L20;
            float r13 = 1.0f;
        L21:
            ViewPropertyAnimator r114 = r113.scaleX(r13);
            if (r02 == false) goto L24;
            r5 = 1.0f;
        L24:
            ViewPropertyAnimator r115 = r114.scaleY(r5);
            CubicBezierInterpolator r03 = CubicBezierInterpolator.EASE_OUT_QUINT;
            r115.setInterpolator(r03).setDuration(300).start();
            ViewPropertyAnimator r116 = this.infoTextView.animate();
            if (this.pasteShown == true) goto L27;
        L29:
            float r14 = 0.9f;
        L30:
            ViewPropertyAnimator r117 = r116.scaleX(r14);
            if (this.pasteShown == true) goto L33;
        L35:
            ViewPropertyAnimator r118 = r117.scaleY(r4);
            if (this.pasteShown == true) goto L38;
        L40:
            r7 = 0.0f;
        L41:
            ViewPropertyAnimator r119 = r118.alpha(r7);
            if (this.pasteShown == false) goto L47;
            if (this.errorShown == true) goto L47;
        L50:
            r119.translationY(r6).setInterpolator(r03).setDuration(300).start();
        L47:
            if (this.errorShown == false) goto L49;
            r2 = 5.0f;
        L49:
            r6 = AndroidUtilities.dp(r2);
            goto L50
        L38:
            if (this.errorShown == true) goto L40;
        L33:
            if (this.errorShown == true) goto L35;
            r4 = 1.0f;
            goto L35
        L27:
            if (this.errorShown == true) goto L29;
            r14 = 1.0f;
            goto L30
        L20:
            r13 = 0.7f;
            goto L21
        L16:
            r12 = 0.0f;
            goto L17
        L51:
            if (r02 == false) goto L53;
            float r1110 = 1.0f;
        L54:
            r1.setAlpha(r1110);
            TextView r1111 = this.pasteTextView;
            if (r02 == false) goto L57;
            float r15 = 1.0f;
        L58:
            r1111.setScaleX(r15);
            TextView r1112 = this.pasteTextView;
            if (r02 == false) goto L61;
            r5 = 1.0f;
        L61:
            r1112.setScaleY(r5);
            TextView r1113 = this.infoTextView;
            if (this.pasteShown == true) goto L64;
        L66:
            float r04 = 0.9f;
        L67:
            r1113.setScaleX(r04);
            TextView r1114 = this.infoTextView;
            if (this.pasteShown == true) goto L70;
        L72:
            r1114.setScaleY(r4);
            TextView r1115 = this.infoTextView;
            if (this.pasteShown == true) goto L75;
        L77:
            r7 = 0.0f;
        L78:
            r1115.setAlpha(r7);
            TextView r1116 = this.infoTextView;
            if (this.pasteShown == false) goto L84;
            if (this.errorShown == true) goto L84;
        L87:
            r1116.setTranslationY(r6);
        L84:
            if (this.errorShown == false) goto L86;
            r2 = 5.0f;
        L86:
            r6 = AndroidUtilities.dp(r2);
            goto L87
        L75:
            if (this.errorShown == true) goto L77;
        L70:
            if (this.errorShown == true) goto L72;
            r4 = 1.0f;
            goto L72
        L64:
            if (this.errorShown == true) goto L66;
            r04 = 1.0f;
            goto L67
        L57:
            r15 = 0.7f;
            goto L58
        L53:
            r1110 = 0.0f;
        L88:
            AndroidUtilities.runOnUIThread(this.checkPasteRunnable, 5000);
            return;
        L8:
            r02 = false;
            goto L10
        }

        private void createTimer() {
            if (this.timeTimer == null) goto L5;
            return;
        L5:
            LoadingTextView r0 = this.timeText;
            int r1 = Theme.key_windowBackgroundWhiteGrayText;
            r0.setTextColor(Theme.getColor(r1));
            this.timeText.setTag(org.telegram.messenger.R.id.color_key_tag, Integer.valueOf(r1));
            Timer r3 = new Timer();
            this.timeTimer = r3;
            r3.schedule(new AnonymousClass4(this), 0, 1000);
        }

        private void destroyTimer() {
            LoadingTextView r0 = this.timeText;
            int r1 = Theme.key_windowBackgroundWhiteGrayText;
            r0.setTextColor(Theme.getColor(r1));
            this.timeText.setTag(org.telegram.messenger.R.id.color_key_tag, Integer.valueOf(r1));
            Object r02 = this.timerSync;     // Catch: Exception -> L14
            monitor-enter(r02);     // Catch: Exception -> L14
            Timer r12 = this.timeTimer;     // Catch: Throwable -> L8
            if (r12 == null) goto L10;
            r12.cancel();     // Catch: Throwable -> L8
            this.timeTimer = null;     // Catch: Throwable -> L8
        L10:
            monitor-exit(r02);     // Catch: Throwable -> L8
            return;
        L8:
            th = move-exception;
            throw th;     // Catch: Exception -> L14
        L14:
            e = move-exception;
            FileLog.e(e);
        }

        private /* synthetic */ void lambda$new$0(View r1, boolean r2) {
            OutlineTextContainerView r0 = this.outlineField;
            if (r2 == false) goto L5;
            float r12 = 1.0f;
        L6:
            r0.animateSelection(r12);
            return;
        L5:
            r12 = 0.0f;
            goto L6
        }

        private /* synthetic */ void lambda$new$1(View r6) {
            CharSequence r62 = ((ClipboardManager) getContext().getSystemService("clipboard")).getPrimaryClip().getItemAt(0).coerceToText(getContext());     // Catch: Exception -> L5
        L8:
            if (r62 == null) goto L13;
            Editable r2 = this.codeField.getText();
            this.pasted = true;
            this.pasting = true;
            if (r2 == null) goto L12;
            int r3 = Math.max(0, this.codeField.getSelectionStart());
            r2.replace(r3, Math.max(r3, this.codeField.getSelectionEnd()), r62);
        L12:
            this.pasting = false;
        L13:
            checkPaste(true);
            return;
        L5:
            e = move-exception;
            FileLog.e(e);
            r62 = null;
            goto L8
        }

        private /* synthetic */ boolean lambda$new$2(TextView r1, int r2, KeyEvent r3) {
            if (r2 != 5) goto L6;
            onNextPressed(null);
            return true;
        L6:
            return false;
        }

        private /* synthetic */ void lambda$new$3(View r1) {
            onBackPressed(true);
        }

        private /* synthetic */ void lambda$new$4(TLObject r3, Bundle r4, TLRPC.TL_error r5) {
            this.isResendingCode = false;
            this.timeText.invalidate();
            if (r3 == null) goto L6;
            this.nextCodeParams = r4;
            TLRPC.TL_auth_sentCode r32 = (TLRPC.TL_auth_sentCode) r3;
            this.nextCodeAuth = r32;
            LoginActivity.m15820$$Nest$mfillNextCodeParams(this.this$0, r4, r32);
            return;
        L6:
            if (r5 == null) goto L29;
            String r33 = r5.text;
            if (r33 != null) goto L10;
            return;
        L10:
            if (r33.contains("PHONE_NUMBER_INVALID") == false) goto L13;
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString(org.telegram.messenger.R.string.InvalidPhoneNumber));
        L27:
            this.lastError = r5.text;
            return;
        L13:
            if (r5.text.contains("PHONE_CODE_EMPTY") == false) goto L15;
        L26:
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString(org.telegram.messenger.R.string.InvalidCode));
            goto L27
        L15:
            if (r5.text.contains("PHONE_CODE_INVALID") == true) goto L26;
            if (r5.text.contains("PHONE_CODE_EXPIRED") == false) goto L21;
            onBackPressed(true);
            this.this$0.setPage(0, true, null, true);
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString(org.telegram.messenger.R.string.CodeExpired));
            goto L27
        L21:
            if (r5.text.startsWith("FLOOD_WAIT") == false) goto L24;
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString(org.telegram.messenger.R.string.FloodWait));
            goto L27
        L24:
            if (r5.code == (-1000)) goto L27;
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString(org.telegram.messenger.R.string.ErrorOccurred) + "\n" + r5.text);
            goto L27
        }

        private /* synthetic */ void lambda$new$5(Bundle r2, TLObject r3, TLRPC.TL_error r4) {
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivityPhraseView$$ExternalSyntheticLambda13(this, r3, r2, r4));
        }

        private /* synthetic */ void lambda$new$6(View r5) {
            if (this.time > 0) goto L5;
        L7:
            Bundle r52 = this.nextCodeParams;
            if (r52 == null) goto L13;
            TLRPC.TL_auth_sentCode r0 = this.nextCodeAuth;
            if (r0 == null) goto L13;
            LoginActivity.m15820$$Nest$mfillNextCodeParams(this.this$0, r52, r0);
            return;
        L13:
            int r53 = this.nextType;
            if (r53 != 4) goto L16;
        L25:
            this.isResendingCode = true;
            this.timeText.invalidate();
            this.timeText.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteValueText));
            this.timeText.setTextSize(1, 15.0f);
            int r54 = this.nextType;
            if (r54 == 4) goto L30;
            if (r54 == 11) goto L30;
            this.timeText.setText(LocaleController.getString(org.telegram.messenger.R.string.SendingSms));
        L31:
            Bundle r55 = new Bundle();
            r55.putString("phone", this.phone);
            r55.putString("ephone", this.emailPhone);
            r55.putString("phoneFormated", this.requestPhone);
            r55.putInt("prevType", this.currentType);
            TLRPC.TL_auth_resendCode r02 = new TLRPC.TL_auth_resendCode();
            r02.phone_number = this.requestPhone;
            r02.phone_code_hash = this.phoneHash;
            ConnectionsManager.getInstance(LoginActivity.access$4300(this.this$0)).sendRequest(r02, new LoginActivity$LoginActivityPhraseView$$ExternalSyntheticLambda10(this, r55), 10);
            return;
        L30:
            this.timeText.setText(LocaleController.getString(org.telegram.messenger.R.string.Calling));
            goto L31
        L16:
            if (r53 == 2) goto L25;
            if (r53 == 11) goto L25;
            if (r53 == 15) goto L25;
            if (r53 != 3) goto L33;
            AndroidUtilities.setWaitingForSms(false);
            this.waitingForEvent = false;
            resendCode();
            return;
        L33:
            return;
        L5:
            if (this.timeTimer == null) goto L7;
        }

        private /* synthetic */ void lambda$new$7() {
            checkPaste(true);
        }

        private /* synthetic */ void lambda$new$8() {
            animateError(false);
        }

        private /* synthetic */ void lambda$onNextPressed$10(TLRPC.TL_auth_signIn r2, TLObject r3, TLRPC.TL_error r4) {
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivityPhraseView$$ExternalSyntheticLambda16(this, r4, r3, r2));
        }

        private /* synthetic */ void lambda$onNextPressed$11() {
            this.codeField.requestFocus();
            String r0 = this.beginning;
            if (r0 == null) goto L16;
            int r2 = 1;
            if (r0.length() <= 1) goto L16;
            String r02 = this.codeField.getText().toString();
            int r3 = trimLeftLen(r02) + this.beginning.length();
            if (r3 >= 0) goto L9;
        L13:
            r2 = 0;
        L14:
            this.codeField.setSelection(Utilities.clamp(r3 + r2, r02.length(), 0), this.codeField.getText().length());
            return;
        L9:
            if (r3 >= r02.length()) goto L13;
            if (r02.charAt(r3) != ' ') goto L13;
        L16:
            EditTextBoldCursor r6 = this.codeField;
            r6.setSelection(0, r6.getText().length());
        }

        private /* synthetic */ void lambda$onNextPressed$12(TLRPC.TL_error r5, TLObject r6, TLRPC.TL_auth_signIn r7) {
            LoginActivity.m15827$$Nest$mneedHideProgress(this.this$0, false, true);
            if (r5 != null) goto L11;
            this.nextPressed = false;
            LoginActivity.m15835$$Nest$mshowDoneButton(this.this$0, false, true);
            destroyTimer();
            if ((r6 instanceof TLRPC.TL_auth_authorizationSignUpRequired) == false) goto L10;
            TLRPC.TL_help_termsOfService r52 = ((TLRPC.TL_auth_authorizationSignUpRequired) r6).terms_of_service;
            if (r52 == null) goto L9;
            LoginActivity.m15810$$Nest$fputcurrentTermsOfService(this.this$0, r52);
        L9:
            Bundle r53 = new Bundle();
            r53.putString("phoneFormated", this.requestPhone);
            r53.putString("phoneHash", this.phoneHash);
            r53.putString("code", r7.phone_code);
            this.this$0.setPage(5, true, r53, false);
        L15:
            if (this.currentType != 3) goto L39;
            AndroidUtilities.endIncomingCall();
            AndroidUtilities.setWaitingForCall(false);
            return;
        L39:
            return;
        L10:
            LoginActivity.m15831$$Nest$monAuthSuccess(this.this$0, (TLRPC.TL_auth_authorization) r6);
            goto L15
        L11:
            String r62 = r5.text;
            this.lastError = r62;
            if (r62.contains("SESSION_PASSWORD_NEEDED") == false) goto L18;
            ConnectionsManager.getInstance(LoginActivity.access$4200(this.this$0)).sendRequest(new TL_account.getPassword(), new LoginActivity$LoginActivityPhraseView$$ExternalSyntheticLambda14(this, r7), 10);
            destroyTimer();
            goto L15
        L18:
            this.nextPressed = false;
            if (this.currentType != 3) goto L21;
            return;
        L21:
            if (r5.text.contains("PHONE_NUMBER_INVALID") == false) goto L24;
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString("InvalidPhoneNumber", org.telegram.messenger.R.string.InvalidPhoneNumber));
        L35:
            this.codeField.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            this.codeField.requestFocus();
            return;
        L24:
            if (r5.text.contains("PHONE_CODE_EMPTY") == false) goto L26;
        L37:
            onInputError(false);
            this.codeField.post(new LoginActivity$LoginActivityPhraseView$$ExternalSyntheticLambda15(this));
            return;
        L26:
            if (r5.text.contains("PHONE_CODE_INVALID") == true) goto L37;
            if (r5.text.contains("PHONE_CODE_EXPIRED") == false) goto L31;
            onBackPressed(true);
            this.this$0.setPage(0, true, null, true);
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString("CodeExpired", org.telegram.messenger.R.string.CodeExpired));
            goto L35
        L31:
            boolean r63 = r5.text.startsWith("FLOOD_WAIT");
            LoginActivity r72 = this.this$0;
            if (r63 == false) goto L34;
            LoginActivity.m15828$$Nest$mneedShowAlert(r72, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString("FloodWait", org.telegram.messenger.R.string.FloodWait));
            goto L35
        L34:
            LoginActivity.m15828$$Nest$mneedShowAlert(r72, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString("ErrorOccurred", org.telegram.messenger.R.string.ErrorOccurred) + "\n" + r5.text);
            goto L35
        }

        private /* synthetic */ void lambda$onNextPressed$13(TLRPC.TL_auth_signIn r2, TLObject r3, TLRPC.TL_error r4) {
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivityPhraseView$$ExternalSyntheticLambda11(this, r4, r3, r2));
        }

        private /* synthetic */ void lambda$onNextPressed$9(TLRPC.TL_error r5, TLObject r6, TLRPC.TL_auth_signIn r7) {
            this.nextPressed = false;
            LoginActivity.m15835$$Nest$mshowDoneButton(this.this$0, false, true);
            if (r5 != null) goto L10;
            TL_account.Password r62 = (TL_account.Password) r6;
            if (TwoStepVerificationActivity.canHandleCurrentPassword(r62, true) == true) goto L8;
            AlertsCreator.showUpdateAppAlert(this.this$0.getParentActivity(), LocaleController.getString("UpdateAppAlert", org.telegram.messenger.R.string.UpdateAppAlert), true);
            return;
        L8:
            Bundle r52 = new Bundle();
            SerializedData r1 = new SerializedData(r62.getObjectSize());
            r62.serializeToStream(r1);
            r52.putString("password", Utilities.bytesToHex(r1.toByteArray()));
            r52.putString("phoneFormated", this.requestPhone);
            r52.putString("phoneHash", this.phoneHash);
            r52.putString("code", r7.phone_code);
            this.this$0.setPage(6, true, r52, false);
            return;
        L10:
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), r5.text);
        }

        private /* synthetic */ void lambda$onShow$16() {
            EditTextBoldCursor r0 = this.codeField;
            if (r0 == null) goto L6;
            r0.requestFocus();
            EditTextBoldCursor r02 = this.codeField;
            r02.setSelection(r02.length());
            AndroidUtilities.showKeyboard(this.codeField);
            return;
        }

        private /* synthetic */ void lambda$resendCode$14(TLRPC.TL_error r4, Bundle r5, TLObject r6) {
            this.nextPressed = false;
            if (r4 != null) goto L5;
            LoginActivity.m15820$$Nest$mfillNextCodeParams(this.this$0, r5, (TLRPC.TL_auth_sentCode) r6);
        L25:
            LoginActivity.m15826$$Nest$mneedHideProgress(this.this$0, false);
            return;
        L5:
            String r52 = r4.text;
            if (r52 == null) goto L25;
            if (r52.contains("PHONE_NUMBER_INVALID") == false) goto L11;
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString(org.telegram.messenger.R.string.InvalidPhoneNumber));
            goto L25
        L11:
            if (r4.text.contains("PHONE_CODE_EMPTY") == false) goto L13;
        L24:
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString(org.telegram.messenger.R.string.InvalidCode));
            goto L25
        L13:
            if (r4.text.contains("PHONE_CODE_INVALID") == true) goto L24;
            if (r4.text.contains("PHONE_CODE_EXPIRED") == false) goto L19;
            onBackPressed(true);
            this.this$0.setPage(0, true, null, true);
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString(org.telegram.messenger.R.string.CodeExpired));
            goto L25
        L19:
            if (r4.text.startsWith("FLOOD_WAIT") == false) goto L22;
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString(org.telegram.messenger.R.string.FloodWait));
            goto L25
        L22:
            if (r4.code == (-1000)) goto L25;
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString(org.telegram.messenger.R.string.ErrorOccurred) + "\n" + r4.text);
            goto L25
        }

        private /* synthetic */ void lambda$resendCode$15(Bundle r2, TLObject r3, TLRPC.TL_error r4) {
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivityPhraseView$$ExternalSyntheticLambda17(this, r4, r2, r3));
        }

        private void onInputError(boolean r4) {
            if (this.this$0.getParentActivity() != null) goto L31;
            return;
        L31:
            this.codeField.performHapticFeedback(3, 2);     // Catch: Exception -> L30
        L7:
            if (this.currentType != 16) goto L9;
            boolean r0 = false;
        L10:
            if (r4 == false) goto L16;
            TextView r42 = this.errorTextView;
            if (r0 == true) goto L14;
            int r02 = org.telegram.messenger.R.string.SMSWordBeginningError;
        L15:
            r42.setText(LocaleController.getString(r02));
        L24:
            if (this.errorShown == false) goto L26;
        L28:
            AndroidUtilities.cancelRunOnUIThread(this.dismissField);
            animateError(true);
            AndroidUtilities.runOnUIThread(this.dismissField, NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS);
            this.shiftDp = -this.shiftDp;
            return;
        L26:
            if (this.pasted == true) goto L28;
            AndroidUtilities.shakeViewSpring(this.codeField, this.shiftDp);
            AndroidUtilities.shakeViewSpring(this.errorTextView, this.shiftDp);
            goto L28
        L14:
            r02 = org.telegram.messenger.R.string.SMSPhraseBeginningError;
            goto L15
        L16:
            boolean r43 = TextUtils.isEmpty(this.codeField.getText());
            TextView r1 = this.errorTextView;
            if (r43 == false) goto L19;
            r1.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            goto L24
        L19:
            if (r0 == true) goto L21;
            int r44 = org.telegram.messenger.R.string.SMSWordError;
        L22:
            r1.setText(LocaleController.getString(r44));
            goto L24
        L21:
            r44 = org.telegram.messenger.R.string.SMSPhraseError;
            goto L22
        L9:
            r0 = true;
            goto L10
        }

        private void resendCode() {
            if (this.nextPressed == false) goto L5;
            return;
        L5:
            if (this.isResendingCode == false) goto L7;
            return;
        L7:
            if (LoginActivity.m15791$$Nest$fgetisRequestingFirebaseSms(this.this$0) == true) goto L13;
            this.isResendingCode = true;
            this.timeText.invalidate();
            this.timeText.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteValueText));
            Bundle r1 = new Bundle();
            r1.putString("phone", this.phone);
            r1.putString("ephone", this.emailPhone);
            r1.putString("phoneFormated", this.requestPhone);
            this.nextPressed = true;
            TLRPC.TL_auth_resendCode r0 = new TLRPC.TL_auth_resendCode();
            r0.phone_number = this.requestPhone;
            r0.phone_code_hash = this.phoneHash;
            int r02 = ConnectionsManager.getInstance(LoginActivity.access$4100(this.this$0)).sendRequest(r0, new LoginActivity$LoginActivityPhraseView$$ExternalSyntheticLambda12(this, r1), 10);
            LoginActivity.m15829$$Nest$mneedShowProgress(this.this$0, r02);
            return;
        }

        private String trimLeft(String r4) {
            int r3 = r4.length();
            int r0 = 0;
        L3:
            if (r0 >= r3) goto L7;
            if (r4.charAt(r0) > ' ') goto L7;
            r0 = r0 + 1;
        L7:
            if (r0 > 0) goto L13;
            if (r3 < r4.length()) goto L13;
            return r4;
        L13:
            return r4.substring(r0, r3);
        }

        private int trimLeftLen(String r4) {
            int r3 = r4.length();
            int r0 = 0;
        L3:
            if (r0 >= r3) goto L7;
            if (r4.charAt(r0) > ' ') goto L7;
            r0 = r0 + 1;
        L7:
            return r0;
        }

        @Override // org.telegram.ui.Components.SlideView
        public String getHeaderName() {
            return LocaleController.getString("NewPassword", org.telegram.messenger.R.string.NewPassword);
        }

        @Override // org.telegram.ui.Components.SlideView
        public boolean needBackButton() {
            return true;
        }

        @Override // org.telegram.ui.Components.SlideView
        public boolean onBackPressed(boolean r4) {
            LoginActivity.m15826$$Nest$mneedHideProgress(this.this$0, true);
            int r42 = this.prevType;
            if (r42 == 0) goto L6;
            this.this$0.setPage(r42, true, null, true);
            return false;
        L6:
            this.currentParams = null;
            this.nextPressed = false;
            return true;
        }

        @Override // org.telegram.ui.Components.SlideView
        public void onCancelPressed() {
            this.nextPressed = false;
        }

        @Override // org.telegram.ui.Components.SlideView
        public void onHide() {
            super.onHide();
            AndroidUtilities.cancelRunOnUIThread(this.checkPasteRunnable);
        }

        @Override // org.telegram.ui.Components.SlideView
        public void onNextPressed(String r5) {
            if (this.nextPressed == false) goto L5;
            return;
        L5:
            String r52 = this.codeField.getText().toString();
            if (r52.length() != 0) goto L10;
            onInputError(false);
            return;
        L10:
            if (beginsOk(r52) == true) goto L13;
            onInputError(true);
            return;
        L13:
            this.nextPressed = true;
            TLRPC.TL_auth_signIn r0 = new TLRPC.TL_auth_signIn();
            r0.phone_number = this.requestPhone;
            r0.phone_code = r52;
            r0.phone_code_hash = this.phoneHash;
            r0.flags |= 1;
            LoginActivity.m15830$$Nest$mneedShowProgress(this.this$0, this.this$0.getConnectionsManager().sendRequest(r0, new LoginActivity$LoginActivityPhraseView$$ExternalSyntheticLambda0(this, r0), 10), true);
            LoginActivity.m15835$$Nest$mshowDoneButton(this.this$0, true, true);
        }

        @Override // org.telegram.ui.Components.SlideView
        public void onResume() {
            super.onResume();
            checkPaste(true);
        }

        @Override // org.telegram.ui.Components.SlideView
        public void onShow() {
            super.onShow();
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivityPhraseView$$ExternalSyntheticLambda9(this), LoginActivity.m15838$$Nest$sfgetSHOW_DELAY());
        }

        @Override // org.telegram.ui.Components.SlideView
        public void restoreStateParams(Bundle r3) {
            Bundle r32 = r3.getBundle("recoveryview_word" + this.currentType);
            this.currentParams = r32;
            if (r32 == null) goto L6;
            setParams(r32, true);
            return;
        }

        @Override // org.telegram.ui.Components.SlideView
        public void saveStateParams(Bundle r3) {
            if (this.currentParams == null) goto L6;
            r3.putBundle("recoveryview_word" + this.currentType, this.currentParams);
            return;
        }

        @Override // org.telegram.ui.Components.SlideView
        public void setParams(Bundle r11, boolean r12) {
            boolean r3 = true;
            if (r11 == null) goto L5;
            this.codeField.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            this.currentParams = r11;
            this.beginning = null;
            this.nextType = r11.getInt("nextType");
            this.prevType = r11.getInt("prevType", 0);
            this.emailPhone = r11.getString("ephone");
            if (this.currentParams.containsKey("beginning") == false) goto L21;
            this.beginning = this.currentParams.getString("beginning");
        L21:
            this.requestPhone = r11.getString("phoneFormated");
            this.phoneHash = r11.getString("phoneHash");
            this.phone = this.currentParams.getString("phone");
            this.time = r11.getInt("timeout");
            int r112 = this.prevType;
            if (r112 != 17) goto L24;
            this.prevTypeTextView.setVisibility(0);
            this.prevTypeTextView.setText(AndroidUtilities.replaceArrows(LocaleController.getString(org.telegram.messenger.R.string.BackEnteringPhrase), true, AndroidUtilities.dp(-1.0f), AndroidUtilities.dp(1.0f)));
        L35:
            this.nextCodeParams = null;
            this.nextCodeAuth = null;
            this.nextPressed = false;
            this.isResendingCode = false;
            LoginActivity.m15813$$Nest$fputisRequestingFirebaseSms(this.this$0, false);
            this.timeText.invalidate();
            if (this.currentType != 16) goto L38;
            r3 = false;
        L38:
            String r113 = "+" + PhoneFormat.getInstance().format(PhoneFormat.stripExceptNumbers(this.phone));
            String r122 = this.beginning;
            TextView r1 = this.confirmTextView;
            if (r122 != null) goto L44;
            if (r3 == true) goto L42;
            int r123 = org.telegram.messenger.R.string.SMSWordText;
        L43:
            r1.setText(AndroidUtilities.replaceTags(LocaleController.formatString(r123, new Object[]{r113})));
        L48:
            LoginActivity.m15836$$Nest$mshowKeyboard(this.this$0, this.codeField);
            this.codeField.requestFocus();
            if (this.imageView.getAnimatedDrawable() == null) goto L51;
            this.imageView.getAnimatedDrawable().setCurrentFrame(0, false);
        L51:
            RLottieImageView r114 = this.imageView;
            Objects.requireNonNull(r114);
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivityPhraseView$$ExternalSyntheticLambda8(r114), 500);
            checkPaste(false);
            animateError(false);
            this.lastCurrentTime = System.currentTimeMillis();
            this.timeText.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText));
            int r115 = this.nextType;
            if (r115 == 2) goto L58;
            if (r115 == 4) goto L58;
            if (r115 == 3) goto L58;
            this.timeText.setVisibility(8);
            return;
        L58:
            createTimer();
            return;
        L42:
            r123 = org.telegram.messenger.R.string.SMSPhraseText;
            goto L43
        L44:
            if (r3 == true) goto L46;
            int r2 = org.telegram.messenger.R.string.SMSWordBeginningText;
        L47:
            r1.setText(AndroidUtilities.replaceTags(LocaleController.formatString(r2, new Object[]{r113, r122})));
            goto L48
        L46:
            r2 = org.telegram.messenger.R.string.SMSPhraseBeginningText;
            goto L47
        L24:
            if (r112 != 16) goto L26;
            this.prevTypeTextView.setVisibility(0);
            this.prevTypeTextView.setText(AndroidUtilities.replaceArrows(LocaleController.getString(org.telegram.messenger.R.string.BackEnteringWord), true, AndroidUtilities.dp(-1.0f), AndroidUtilities.dp(1.0f)));
            goto L35
        L26:
            if (r112 == 1) goto L34;
            if (r112 == 2) goto L34;
            if (r112 == 4) goto L34;
            if (r112 == 3) goto L34;
            if (r112 == 15) goto L34;
            this.prevTypeTextView.setVisibility(8);
        L34:
            this.prevTypeTextView.setVisibility(0);
            this.prevTypeTextView.setText(AndroidUtilities.replaceArrows(LocaleController.getString(org.telegram.messenger.R.string.BackEnteringCode), true, AndroidUtilities.dp(-1.0f), AndroidUtilities.dp(1.0f)));
            goto L35
        L5:
            if (this.nextCodeParams != null) goto L7;
            return;
        L7:
            if (this.nextCodeAuth == null) goto L61;
            this.timeText.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteValueText));
            int r116 = this.nextType;
            if (r116 != 17) goto L11;
            int r117 = org.telegram.messenger.R.string.ReturnEnteringPhrase;
        L16:
            this.timeText.setText(AndroidUtilities.replaceArrows(LocaleController.getString(r117), true, AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f)));
            return;
        L11:
            if (r116 != 16) goto L13;
            r117 = org.telegram.messenger.R.string.ReturnEnteringWord;
            goto L16
        L13:
            if (r116 != 3) goto L15;
            r117 = org.telegram.messenger.R.string.ReturnPhoneCall;
            goto L16
        L15:
            r117 = org.telegram.messenger.R.string.ReturnEnteringSMS;
            goto L16
        }

        @Override // org.telegram.ui.Components.SlideView
        public void updateColors() {
            TextView r0 = this.titleTextView;
            LoginActivity r1 = this.this$0;
            int r2 = Theme.key_windowBackgroundWhiteBlackText;
            r0.setTextColor(r1.getThemedColor(r2));
            this.confirmTextView.setTextColor(this.this$0.getThemedColor(Theme.key_windowBackgroundWhiteGrayText6));
            this.codeField.setTextColor(this.this$0.getThemedColor(r2));
            this.codeField.setCursorColor(this.this$0.getThemedColor(Theme.key_windowBackgroundWhiteInputFieldActivated));
            this.codeField.setHintTextColor(this.this$0.getThemedColor(Theme.key_windowBackgroundWhiteHintText));
            this.outlineField.updateColor();
        }
    }

    public class LoginActivityQrView extends QrCodeLoginView implements NotificationCenter.NotificationCenterDelegate {
        private Runnable applyTokenRunnable;
        private int exportRequestId;
        private boolean exportRequestedAfterCurrent;
        private boolean firstQrToken;
        private int importRequestId;
        private int passwordRequestId;
        private long qrShowStartTimeMs;
        private Runnable refreshRunnable;
        final /* synthetic */ LoginActivity this$0;
        private boolean waitingForEvent;

        /* renamed from: $r8$lambda$-chrJrlmsX8RNgA9xknnX2YDLEg, reason: not valid java name */
        public static /* synthetic */ void m15888$r8$lambda$chrJrlmsX8RNgA9xknnX2YDLEg(LoginActivityQrView r0, TLRPC.TL_error r1, TLObject r2) {
            r0.lambda$handleError$8(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$DD8dYcIp7LdUlltqnCypeO6QXt8(LoginActivityQrView r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$exportLoginToken$5(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$NmICTkLORctAvgohrxNuJpZF6iY(LoginActivityQrView r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$exportLoginToken$7(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$UcPDkjabKSoXcHDusH7AiRzc4M4(LoginActivityQrView r0) {
            r0.lambda$exportLoginToken$1();
        }

        public static /* synthetic */ void $r8$lambda$VlZr05CLYHzPmbz_mcOByiOn5Po(LoginActivityQrView r0, TLObject r1) {
            r0.lambda$exportLoginToken$2(r1);
        }

        public static /* synthetic */ void $r8$lambda$Wgw8WCSeiSx3tRSKLGCAKtVf0iE(LoginActivityQrView r0, TLRPC.TL_error r1, TLObject r2) {
            r0.lambda$exportLoginToken$6(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$dWUracvDOGpWfTar08GCjjXvWOQ(LoginActivityQrView r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$handleError$9(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$eTp8z5K0AirIlRl7fSwbnchZBDI(LoginActivityQrView r0, TLObject r1) {
            r0.lambda$exportLoginToken$3(r1);
        }

        public static /* synthetic */ void $r8$lambda$gY1mk8dCrfJDEzU9hLtIjq1upCQ(LoginActivityQrView r0, TLRPC.TL_error r1, TLObject r2) {
            r0.lambda$exportLoginToken$4(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$tDkRW92ZJlU7thV7oh6dudPzENU(LoginActivityQrView r0, String r1) {
            r0.lambda$exportLoginToken$0(r1);
        }

        public LoginActivityQrView(LoginActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        private void cancelPendingRequests() {
            if (this.exportRequestId == 0) goto L6;
            this.this$0.getConnectionsManager().cancelRequest(this.exportRequestId, true);
            this.exportRequestId = 0;
        L6:
            if (this.importRequestId == 0) goto L9;
            this.this$0.getConnectionsManager().cancelRequest(this.importRequestId, true);
            this.importRequestId = 0;
        L9:
            if (this.passwordRequestId == 0) goto L12;
            this.this$0.getConnectionsManager().cancelRequest(this.passwordRequestId, true);
            this.passwordRequestId = 0;
            return;
        }

        private void exportLoginToken(boolean r6) {
            if (this.waitingForEvent == true) goto L5;
            return;
        L5:
            if (getContext() != null) goto L8;
            return;
        L8:
            if (this.exportRequestId == 0) goto L11;
            this.exportRequestedAfterCurrent = true;
            return;
        L11:
            int r0 = 0;
            this.exportRequestedAfterCurrent = false;
            if (r6 == false) goto L17;
            this.this$0.getNotificationCenter().addObserver(this, NotificationCenter.onUpdateLoginToken);
            if (this.this$0.getConnectionsManager().isTestBackend() == LoginActivity.m15806$$Nest$fgettestBackend(this.this$0)) goto L16;
            this.this$0.getConnectionsManager().switchBackend(false);
        L16:
            this.this$0.getConnectionsManager().cleanup(false);
        L17:
            TLRPC.TL_auth_exportLoginToken r62 = new TLRPC.TL_auth_exportLoginToken();
            r62.api_hash = BuildVars.getExteraAppHash();
            r62.api_id = BuildVars.getExteraAppId();
        L19:
            if (r0 >= 16) goto L24;
            UserConfig r1 = UserConfig.getInstance(r0);
            if (r1.isClientActivated() == false) goto L23;
            r62.except_ids.add(Long.valueOf(r1.getClientUserId()));
        L23:
            r0 = r0 + 1;
            goto L19
        L24:
            this.exportRequestId = this.this$0.getConnectionsManager().sendRequest(r62, new LoginActivity$LoginActivityQrView$$ExternalSyntheticLambda0(this), 27);
        }

        private void handleError(String r4) {
            if (r4.contains("SESSION_PASSWORD_NEEDED") == false) goto L6;
            TL_account.getPassword r42 = new TL_account.getPassword();
            this.passwordRequestId = this.this$0.getConnectionsManager().sendRequest(r42, new LoginActivity$LoginActivityQrView$$ExternalSyntheticLambda7(this), 10);
            return;
        L6:
            boolean r0 = r4.startsWith("FLOOD_WAIT");
            LoginActivity r3 = this.this$0;
            if (r0 == false) goto L10;
            LoginActivity.m15828$$Nest$mneedShowAlert(r3, LocaleController.getString(org.telegram.messenger.R.string.AppName), LocaleController.getString(org.telegram.messenger.R.string.FloodWait));
            return;
        L10:
            LoginActivity.m15828$$Nest$mneedShowAlert(r3, LocaleController.getString(org.telegram.messenger.R.string.AppName), r4);
        }

        private boolean isQrViewActive() {
            if (this.waitingForEvent == true) goto L5;
            return false;
        L5:
            if (LoginActivity.m15782$$Nest$fgetcurrentViewNum(this.this$0) != 18) goto L10;
            return true;
        L10:
            return false;
        }

        private /* synthetic */ void lambda$exportLoginToken$0(String r2) {
            this.applyTokenRunnable = null;
            if (isQrViewActive() == true) goto L5;
            return;
        L5:
            setData(r2);
            this.firstQrToken = false;
        }

        private /* synthetic */ void lambda$exportLoginToken$1() {
            exportLoginToken(false);
        }

        private /* synthetic */ void lambda$exportLoginToken$2(TLObject r1) {
            LoginActivity.m15831$$Nest$monAuthSuccess(this.this$0, (TLRPC.TL_auth_authorization) ((TLRPC.TL_auth_loginTokenSuccess) r1).authorization);
        }

        private /* synthetic */ void lambda$exportLoginToken$3(TLObject r4) {
            LoginActivity.m15835$$Nest$mshowDoneButton(this.this$0, false, true);
            LoginActivity.m15831$$Nest$monAuthSuccess(this.this$0, (TLRPC.TL_auth_authorization) ((TLRPC.TL_auth_loginTokenSuccess) r4).authorization);
        }

        private /* synthetic */ void lambda$exportLoginToken$4(TLRPC.TL_error r4, TLObject r5) {
            this.importRequestId = 0;
            if (LoginActivity.m15782$$Nest$fgetcurrentViewNum(this.this$0) != 18) goto L17;
            if (r4 == null) goto L7;
            LoginActivity.m15835$$Nest$mshowDoneButton(this.this$0, false, true);
            clear(true);
            removeObserver();
            if (TextUtils.isEmpty(r4.text) == true) goto L13;
            String r42 = r4.text;
        L14:
            handleError(r42);
            return;
        L13:
            r42 = LocaleController.getString(org.telegram.messenger.R.string.UnknownError);
            goto L14
        L7:
            if ((r5 instanceof TLRPC.TL_auth_loginTokenSuccess) == false) goto L16;
            postDelayed(new LoginActivity$LoginActivityQrView$$ExternalSyntheticLambda8(this, r5), 150);
            return;
        L16:
            return;
        }

        private /* synthetic */ void lambda$exportLoginToken$5(TLObject r2, TLRPC.TL_error r3) {
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivityQrView$$ExternalSyntheticLambda6(this, r3, r2));
        }

        private /* synthetic */ void lambda$exportLoginToken$6(TLRPC.TL_error r9, TLObject r10) {
            this.exportRequestId = 0;
            boolean r1 = this.exportRequestedAfterCurrent;
            this.exportRequestedAfterCurrent = false;
            if (isQrViewActive() == true) goto L6;
            return;
        L6:
            if (r9 == null) goto L8;
            clear(true);
            removeObserver();
            if (TextUtils.isEmpty(r9.text) == true) goto L32;
            String r92 = r9.text;
        L33:
            handleError(r92);
        L34:
            if (r1 == true) goto L36;
            return;
        L36:
            if (isQrViewActive() == true) goto L38;
            return;
        L38:
            if (this.exportRequestId != 0) goto L43;
            exportLoginToken(false);
            return;
        L43:
            return;
        L32:
            r92 = LocaleController.getString(org.telegram.messenger.R.string.UnknownError);
            goto L33
        L8:
            if ((r10 instanceof TLRPC.TL_auth_loginToken) == false) goto L24;
            TLRPC.TL_auth_loginToken r102 = (TLRPC.TL_auth_loginToken) r10;
            String r93 = "tg://login?token=" + Base64.encodeToString(r102.token, 11);
            if (this.firstQrToken == false) goto L15;
            clear(true);
            Runnable r2 = this.applyTokenRunnable;
            if (r2 == null) goto L14;
            AndroidUtilities.cancelRunOnUIThread(r2);
        L14:
            this.applyTokenRunnable = new LoginActivity$LoginActivityQrView$$ExternalSyntheticLambda2(this, r93);
            AndroidUtilities.runOnUIThread(this.applyTokenRunnable, Math.max(0, 380 - Math.max(0, System.currentTimeMillis() - this.qrShowStartTimeMs)));
        L16:
            long r94 = r102.expires - this.this$0.getConnectionsManager().getCurrentTime();
            if (r94 >= 0) goto L19;
            r94 = 20;
        L19:
            Runnable r22 = this.refreshRunnable;
            if (r22 == null) goto L22;
            AndroidUtilities.cancelRunOnUIThread(r22);
        L22:
            LoginActivity$LoginActivityQrView$$ExternalSyntheticLambda3 r23 = new LoginActivity$LoginActivityQrView$$ExternalSyntheticLambda3(this);
            this.refreshRunnable = r23;
            AndroidUtilities.runOnUIThread(r23, r94 * 1000);
            goto L34
        L15:
            setData(r93);
            goto L16
        L24:
            if ((r10 instanceof TLRPC.TL_auth_loginTokenSuccess) == false) goto L27;
            removeObserver();
            postDelayed(new LoginActivity$LoginActivityQrView$$ExternalSyntheticLambda4(this, r10), 150);
            goto L34
        L27:
            if ((r10 instanceof TLRPC.TL_auth_loginTokenMigrateTo) == false) goto L34;
            removeObserver();
            LoginActivity.m15835$$Nest$mshowDoneButton(this.this$0, true, true);
            TLRPC.TL_auth_loginTokenMigrateTo r103 = (TLRPC.TL_auth_loginTokenMigrateTo) r10;
            this.this$0.getConnectionsManager().setDefaultDatacenterId(r103.dc_id);
            TLRPC.TL_auth_importLoginToken r95 = new TLRPC.TL_auth_importLoginToken();
            r95.token = r103.token;
            this.importRequestId = this.this$0.getConnectionsManager().sendRequest(r95, new LoginActivity$LoginActivityQrView$$ExternalSyntheticLambda5(this), 27);
            goto L34
        }

        private /* synthetic */ void lambda$exportLoginToken$7(TLObject r2, TLRPC.TL_error r3) {
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivityQrView$$ExternalSyntheticLambda1(this, r3, r2));
        }

        private /* synthetic */ void lambda$handleError$8(TLRPC.TL_error r6, TLObject r7) {
            this.passwordRequestId = 0;
            if (LoginActivity.m15782$$Nest$fgetcurrentViewNum(this.this$0) != 18) goto L5;
        L7:
            LoginActivity.m15835$$Nest$mshowDoneButton(this.this$0, false, true);
            if (r6 != null) goto L15;
            TL_account.Password r72 = (TL_account.Password) r7;
            if (TwoStepVerificationActivity.canHandleCurrentPassword(r72, true) == true) goto L13;
            AlertsCreator.showUpdateAppAlert(this.this$0.getParentActivity(), LocaleController.getString(org.telegram.messenger.R.string.UpdateAppAlert), true);
            return;
        L13:
            Bundle r62 = new Bundle();
            SerializedData r1 = new SerializedData(r72.getObjectSize());
            r72.serializeToStream(r1);
            r62.putString("password", Utilities.bytesToHex(r1.toByteArray()));
            this.this$0.setPage(6, true, r62, false);
            return;
        L15:
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.AppName), r6.text);
            return;
        L5:
            if (LoginActivity.m15782$$Nest$fgetcurrentViewNum(this.this$0) == 6) goto L7;
        }

        private /* synthetic */ void lambda$handleError$9(TLObject r2, TLRPC.TL_error r3) {
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivityQrView$$ExternalSyntheticLambda9(this, r3, r2));
        }

        private void removeObserver() {
            this.waitingForEvent = false;
            this.exportRequestedAfterCurrent = false;
            this.this$0.getNotificationCenter().removeObserver(this, NotificationCenter.onUpdateLoginToken);
            Runnable r0 = this.refreshRunnable;
            if (r0 == null) goto L5;
            AndroidUtilities.cancelRunOnUIThread(r0);
            this.refreshRunnable = null;
        L5:
            Runnable r02 = this.applyTokenRunnable;
            if (r02 == null) goto L9;
            AndroidUtilities.cancelRunOnUIThread(r02);
            this.applyTokenRunnable = null;
            return;
        }

        @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
        public void didReceivedNotification(int r1, int r2, Object... r3) {
            if (r1 != NotificationCenter.onUpdateLoginToken) goto L6;
            exportLoginToken(false);
            return;
        }

        @Override // org.telegram.ui.Components.SlideView
        public boolean onBackPressed(boolean r3) {
            this.this$0.setPage(0, true, null, true);
            return false;
        }

        @Override // org.telegram.ui.Components.SlideView
        public void onDestroyActivity() {
            removeObserver();
            cancelPendingRequests();
            super.onDestroyActivity();
        }

        @Override // org.telegram.ui.Components.SlideView
        public void onHide() {
            removeObserver();
            cancelPendingRequests();
            super.onHide();
            this.firstQrToken = true;
            clear(false);
        }

        @Override // org.telegram.ui.Components.SlideView
        public void onShow() {
            super.onShow();
            this.waitingForEvent = true;
            this.firstQrToken = true;
            this.qrShowStartTimeMs = System.currentTimeMillis();
            clear();
            exportLoginToken(true);
        }
    }

    public class LoginActivityRecoverView extends SlideView {
        private CodeFieldContainer codeFieldContainer;
        private TextView confirmTextView;
        private Bundle currentParams;
        private Runnable errorColorTimeout;
        private RLottieImageView inboxImageView;
        private boolean nextPressed;
        private String passwordString;
        private String phoneCode;
        private String phoneHash;
        private boolean postedErrorColorTimeout;
        private String requestPhone;
        final /* synthetic */ LoginActivity this$0;
        private TextView titleView;
        private TextView troubleButton;

        /* renamed from: org.telegram.ui.LoginActivity$LoginActivityRecoverView$1, reason: invalid class name */
        public class AnonymousClass1 extends CodeFieldContainer {
            final /* synthetic */ LoginActivityRecoverView this$1;
            final /* synthetic */ LoginActivity val$this$0;

            public AnonymousClass1(LoginActivityRecoverView r1, Context r2, LoginActivity r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            @Override // org.telegram.ui.CodeFieldContainer
            public void processNextPressed() {
                this.this$1.onNextPressed(null);
            }
        }

        /* renamed from: org.telegram.ui.LoginActivity$LoginActivityRecoverView$2, reason: invalid class name */
        public class AnonymousClass2 implements TextWatcher {
            final /* synthetic */ LoginActivityRecoverView this$1;
            final /* synthetic */ LoginActivity val$this$0;

            public AnonymousClass2(LoginActivityRecoverView r1, LoginActivity r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable r1) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
                if (LoginActivityRecoverView.m15895$$Nest$fgetpostedErrorColorTimeout(this.this$1) == false) goto L6;
                LoginActivityRecoverView r12 = this.this$1;
                r12.removeCallbacks(LoginActivityRecoverView.m15893$$Nest$fgeterrorColorTimeout(r12));
                LoginActivityRecoverView.m15893$$Nest$fgeterrorColorTimeout(this.this$1).run();
                return;
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }
        }

        public static /* synthetic */ void $r8$lambda$Vg3GmTeU3W2g5yUQpHxz5Gs6U8E(LoginActivityRecoverView r0, View r1) {
            r0.lambda$new$4(r1);
        }

        public static /* synthetic */ void $r8$lambda$XK6LPMe35BQfrRpAb0fHlIkNVqE(LoginActivityRecoverView r0) {
            r0.lambda$new$0();
        }

        public static /* synthetic */ void $r8$lambda$YKOSV4Pk4H_Siq6HWa7V6AS9x6I(LoginActivityRecoverView r0, TLObject r1, String r2, TLRPC.TL_error r3) {
            r0.lambda$onNextPressed$7(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$ceLfJa8VC4eRRa0BGl_pMQM_ZZw(LoginActivityRecoverView r0, AlertDialog r1, int r2) {
            r0.lambda$new$2(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$czMhNwOhAMB7fD4QM5MSBYPfT4U(LoginActivityRecoverView r0) {
            r0.lambda$onPasscodeError$5();
        }

        /* renamed from: $r8$lambda$elVTWgZKvXKAEV3-Jh4TKfc7iyg, reason: not valid java name */
        public static /* synthetic */ void m15889$r8$lambda$elVTWgZKvXKAEV3Jh4TKfc7iyg(LoginActivityRecoverView r0, View r1, boolean r2) {
            r0.lambda$new$1(r1, r2);
        }

        /* renamed from: $r8$lambda$nYsFzpjEY-ou1aGt2L0XWDLl6Ro, reason: not valid java name */
        public static /* synthetic */ void m15890$r8$lambda$nYsFzpjEYou1aGt2L0XWDLl6Ro(LoginActivityRecoverView r0) {
            r0.lambda$onPasscodeError$6();
        }

        public static /* synthetic */ void $r8$lambda$tigHFBAlnqfE3DYpx8pKFvinuro(LoginActivityRecoverView r0) {
            r0.lambda$onShow$9();
        }

        /* renamed from: $r8$lambda$vhrFR_Zgin-kDgBHbtZUasmcy14, reason: not valid java name */
        public static /* synthetic */ void m15891$r8$lambda$vhrFR_ZginkDgBHbtZUasmcy14(LoginActivityRecoverView r0, String r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$onNextPressed$8(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$zva3UAmpNZP9eK-KPAI6NFN18Mw, reason: not valid java name */
        public static /* synthetic */ void m15892$r8$lambda$zva3UAmpNZP9eKKPAI6NFN18Mw(LoginActivityRecoverView r0, AlertDialog r1, int r2) {
            r0.lambda$new$3(r1, r2);
        }

        /* renamed from: -$$Nest$fgeterrorColorTimeout, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Runnable m15893$$Nest$fgeterrorColorTimeout(LoginActivityRecoverView r0) {
            return r0.errorColorTimeout;
        }

        /* renamed from: -$$Nest$fgetpasswordString, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m15894$$Nest$fgetpasswordString(LoginActivityRecoverView r0) {
            return r0.passwordString;
        }

        /* renamed from: -$$Nest$fgetpostedErrorColorTimeout, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m15895$$Nest$fgetpostedErrorColorTimeout(LoginActivityRecoverView r0) {
            return r0.postedErrorColorTimeout;
        }

        public LoginActivityRecoverView(LoginActivity r19, Context r20) {
            this.this$0 = r19;
            super(r20);
            this.errorColorTimeout = new LoginActivity$LoginActivityRecoverView$$ExternalSyntheticLambda2(this);
            setOrientation(1);
            FrameLayout r4 = new FrameLayout(r20);
            RLottieImageView r5 = new RLottieImageView(r20);
            this.inboxImageView = r5;
            r5.setAnimation(org.telegram.messenger.R.raw.tsv_setup_mail, 120, 120);
            this.inboxImageView.setAutoRepeat(false);
            r4.addView(this.inboxImageView, LayoutHelper.createFrame(120, 120, 1));
            if (AndroidUtilities.isSmallScreen() == true) goto L10;
            Point r52 = AndroidUtilities.displaySize;
            if (r52.x > r52.y) goto L7;
        L9:
            int r53 = 0;
        L11:
            r4.setVisibility(r53);
            addView(r4, LayoutHelper.createFrame(-1, -2, 1));
            TextView r42 = new TextView(r20);
            this.titleView = r42;
            r42.setTextSize(1, 18.0f);
            this.titleView.setTypeface(AndroidUtilities.bold());
            this.titleView.setText(LocaleController.getString(org.telegram.messenger.R.string.EnterCode));
            this.titleView.setGravity(17);
            this.titleView.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            addView(this.titleView, LayoutHelper.createFrame(-1, -2.0f, 1, 32.0f, 16.0f, 32.0f, 0.0f));
            TextView r43 = new TextView(r20);
            this.confirmTextView = r43;
            r43.setTextSize(1, 14.0f);
            this.confirmTextView.setGravity(17);
            this.confirmTextView.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            this.confirmTextView.setText(LocaleController.getString(org.telegram.messenger.R.string.RestoreEmailSentInfo));
            addView(this.confirmTextView, LayoutHelper.createLinear(-2, -2, 1, 12, 8, 12, 0));
            AnonymousClass1 r44 = new AnonymousClass1(this, r20, r19);
            this.codeFieldContainer = r44;
            r44.setNumbersCount(6, 1);
            CodeNumberField[] r45 = this.codeFieldContainer.codeField;
            int r11 = r45.length;
            int r12 = 0;
        L12:
            if (r12 >= r11) goto L21;
            CodeNumberField r13 = r45[r12];
            if (hasCustomKeyboard() == true) goto L16;
        L19:
            boolean r14 = true;
        L20:
            r13.setShowSoftInputOnFocusCompat(r14);
            r13.addTextChangedListener(new AnonymousClass2(this, r19));
            r13.setOnFocusChangeListener(new LoginActivity$LoginActivityRecoverView$$ExternalSyntheticLambda3(this));
            r12 = r12 + 1;
            goto L12
        L16:
            if (LoginActivity.m15823$$Nest$misCustomKeyboardForceDisabled(r19) == true) goto L19;
            r14 = false;
            goto L20
        L21:
            addView(this.codeFieldContainer, LayoutHelper.createLinear(-2, 42, 1, 0, 32, 0, 0));
            SpoilersTextView r1 = new SpoilersTextView(r20, false);
            this.troubleButton = r1;
            r1.setGravity(17);
            this.troubleButton.setTextSize(1, 14.0f);
            this.troubleButton.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            this.troubleButton.setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f));
            this.troubleButton.setMaxLines(2);
            this.troubleButton.setOnClickListener(new LoginActivity$LoginActivityRecoverView$$ExternalSyntheticLambda4(this));
            FrameLayout r15 = new FrameLayout(r20);
            r15.addView(this.troubleButton, LayoutHelper.createFrame(-1, -2.0f, 80, 0.0f, 0.0f, 0.0f, 32.0f));
            addView(r15, LayoutHelper.createLinear(-1, 0, 1.0f));
            VerticalPositionAutoAnimator.attach(this.troubleButton);
            return;
        L7:
            if (AndroidUtilities.isTablet() == true) goto L9;
        L10:
            r53 = 8;
            goto L11
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

        private /* synthetic */ void lambda$new$1(View r1, boolean r2) {
            if (r2 == false) goto L5;
            LoginActivity.m15794$$Nest$fgetkeyboardView(this.this$0).setEditText((EditText) r1);
            LoginActivity.m15794$$Nest$fgetkeyboardView(this.this$0).setDispatchBackWhenEmpty(true);
            return;
        }

        private /* synthetic */ void lambda$new$2(AlertDialog r2, int r3) {
            this.this$0.setPage(6, true, new Bundle(), true);
        }

        private /* synthetic */ void lambda$new$3(AlertDialog r2, int r3) {
            LoginActivity.m15837$$Nest$mtryResetAccount(this.this$0, this.requestPhone, this.phoneHash, this.phoneCode);
        }

        private /* synthetic */ void lambda$new$4(View r3) {
            AlertDialog.Builder r32 = new AlertDialog.Builder(this.this$0.getParentActivity()).setTitle(LocaleController.getString("RestorePasswordNoEmailTitle", org.telegram.messenger.R.string.RestorePasswordNoEmailTitle)).setMessage(LocaleController.getString("RestoreEmailTroubleText", org.telegram.messenger.R.string.RestoreEmailTroubleText)).setPositiveButton(LocaleController.getString(org.telegram.messenger.R.string.OK), new LoginActivity$LoginActivityRecoverView$$ExternalSyntheticLambda6(this)).setNegativeButton(LocaleController.getString(org.telegram.messenger.R.string.ResetAccount), new LoginActivity$LoginActivityRecoverView$$ExternalSyntheticLambda7(this));
            Dialog r2 = this.this$0.showDialog(r32.create());
            if (r2 == null) goto L6;
            r2.setCanceledOnTouchOutside(false);
            r2.setCancelable(false);
            return;
        }

        private /* synthetic */ void lambda$onNextPressed$7(TLObject r3, String r4, TLRPC.TL_error r5) {
            LoginActivity.m15826$$Nest$mneedHideProgress(this.this$0, false);
            this.nextPressed = false;
            if ((r3 instanceof TLRPC.TL_boolTrue) == false) goto L6;
            Bundle r32 = new Bundle();
            r32.putString("emailCode", r4);
            r32.putString("password", this.passwordString);
            this.this$0.setPage(9, true, r32, false);
            return;
        L6:
            if (r5 != null) goto L8;
        L20:
            onPasscodeError(true);
            return;
        L8:
            if (r5.text.startsWith("CODE_INVALID") == true) goto L20;
            if (r5.text.startsWith("FLOOD_WAIT") == false) goto L18;
            int r33 = Utilities.parseInt(r5.text);
            if (r33 >= 60) goto L15;
            String r34 = LocaleController.formatPluralString("Seconds", r33, new Object[0]);
        L16:
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.formatString("FloodWaitTime", org.telegram.messenger.R.string.FloodWaitTime, new Object[]{r34}));
            return;
        L15:
            r34 = LocaleController.formatPluralString("Minutes", r33 / 60, new Object[0]);
            goto L16
        L18:
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), r5.text);
        }

        private /* synthetic */ void lambda$onNextPressed$8(String r2, TLObject r3, TLRPC.TL_error r4) {
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivityRecoverView$$ExternalSyntheticLambda5(this, r3, r2, r4));
        }

        private /* synthetic */ void lambda$onPasscodeError$5() {
            CodeFieldContainer r0 = this.codeFieldContainer;
            int r1 = 0;
            r0.isFocusSuppressed = false;
            r0.codeField[0].requestFocus();
        L3:
            CodeNumberField[] r02 = this.codeFieldContainer.codeField;
            if (r1 >= r02.length) goto L6;
            r02[r1].animateErrorProgress(0.0f);
            r1 = r1 + 1;
            goto L3
        }

        private /* synthetic */ void lambda$onPasscodeError$6() {
            postDelayed(new LoginActivity$LoginActivityRecoverView$$ExternalSyntheticLambda9(this), 150);
            removeCallbacks(this.errorColorTimeout);
            postDelayed(this.errorColorTimeout, 3000);
            this.postedErrorColorTimeout = true;
        }

        private /* synthetic */ void lambda$onShow$9() {
            this.inboxImageView.getAnimatedDrawable().setCurrentFrame(0, false);
            this.inboxImageView.playAnimation();
            CodeFieldContainer r2 = this.codeFieldContainer;
            if (r2 == null) goto L6;
            r2.codeField[0].requestFocus();
            return;
        }

        private void onPasscodeError(boolean r6) {
            if (this.this$0.getParentActivity() != null) goto L19;
            return;
        L19:
            this.codeFieldContainer.performHapticFeedback(3, 2);     // Catch: Exception -> L7
        L10:
            if (r6 == false) goto L14;
            CodeNumberField[] r62 = this.codeFieldContainer.codeField;
            int r1 = r62.length;
            int r2 = 0;
        L12:
            if (r2 >= r1) goto L14;
            r62[r2].setText(_UrlKt.FRAGMENT_ENCODE_SET);
            r2 = r2 + 1;
        L14:
            CodeNumberField[] r63 = this.codeFieldContainer.codeField;
            int r12 = r63.length;
            int r22 = 0;
        L15:
            if (r22 >= r12) goto L17;
            r63[r22].animateErrorProgress(1.0f);
            r22 = r22 + 1;
            goto L15
        L17:
            this.codeFieldContainer.codeField[0].requestFocus();
            AndroidUtilities.shakeViewSpring(this.codeFieldContainer, new LoginActivity$LoginActivityRecoverView$$ExternalSyntheticLambda8(this));
            return;
        L7:
            e = move-exception;
            FileLog.e(e);
            goto L10
        }

        @Override // org.telegram.ui.Components.SlideView
        public String getHeaderName() {
            return LocaleController.getString("LoginPassword", org.telegram.messenger.R.string.LoginPassword);
        }

        @Override // org.telegram.ui.Components.SlideView
        public boolean hasCustomKeyboard() {
            return true;
        }

        @Override // org.telegram.ui.Components.SlideView
        public boolean needBackButton() {
            return true;
        }

        @Override // org.telegram.ui.Components.SlideView
        public boolean onBackPressed(boolean r2) {
            LoginActivity.m15826$$Nest$mneedHideProgress(this.this$0, true);
            this.currentParams = null;
            this.nextPressed = false;
            return true;
        }

        @Override // org.telegram.ui.Components.SlideView
        public void onCancelPressed() {
            this.nextPressed = false;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            removeCallbacks(this.errorColorTimeout);
        }

        @Override // org.telegram.ui.Components.SlideView
        public void onNextPressed(String r7) {
            if (this.nextPressed == false) goto L5;
            return;
        L5:
            CodeFieldContainer r72 = this.codeFieldContainer;
            r72.isFocusSuppressed = true;
            CodeNumberField[] r73 = r72.codeField;
            int r1 = r73.length;
            int r3 = 0;
        L6:
            if (r3 >= r1) goto L8;
            r73[r3].animateFocusedProgress(0.0f);
            r3 = r3 + 1;
            goto L6
        L8:
            String r74 = this.codeFieldContainer.getCode();
            if (r74.length() != 0) goto L12;
            onPasscodeError(false);
            return;
        L12:
            this.nextPressed = true;
            LoginActivity.m15829$$Nest$mneedShowProgress(this.this$0, 0);
            TLRPC.TL_auth_checkRecoveryPassword r0 = new TLRPC.TL_auth_checkRecoveryPassword();
            r0.code = r74;
            ConnectionsManager.getInstance(LoginActivity.access$3500(this.this$0)).sendRequest(r0, new LoginActivity$LoginActivityRecoverView$$ExternalSyntheticLambda1(this, r74), 10);
        }

        @Override // org.telegram.ui.Components.SlideView
        public void onShow() {
            super.onShow();
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivityRecoverView$$ExternalSyntheticLambda0(this), LoginActivity.m15838$$Nest$sfgetSHOW_DELAY());
        }

        @Override // org.telegram.ui.Components.SlideView
        public void restoreStateParams(Bundle r3) {
            Bundle r0 = r3.getBundle("recoveryview_params");
            this.currentParams = r0;
            if (r0 == null) goto L5;
            setParams(r0, true);
        L5:
            String r32 = r3.getString("recoveryview_code");
            if (r32 == null) goto L9;
            this.codeFieldContainer.setText(r32);
            return;
        }

        @Override // org.telegram.ui.Components.SlideView
        public void saveStateParams(Bundle r3) {
            String r0 = this.codeFieldContainer.getCode();
            if (r0 != null) goto L5;
        L7:
            Bundle r2 = this.currentParams;
            if (r2 == null) goto L11;
            r3.putBundle("recoveryview_params", r2);
            return;
        L11:
            return;
        L5:
            if (r0.length() == 0) goto L7;
            r3.putString("recoveryview_code", r0);
            goto L7
        }

        @Override // org.telegram.ui.Components.SlideView
        public void setParams(Bundle r6, boolean r7) {
            if (r6 != null) goto L4;
            return;
        L4:
            this.codeFieldContainer.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            this.currentParams = r6;
            this.passwordString = r6.getString("password");
            this.requestPhone = this.currentParams.getString("requestPhone");
            this.phoneHash = this.currentParams.getString("phoneHash");
            this.phoneCode = this.currentParams.getString("phoneCode");
            String r62 = this.currentParams.getString("email_unconfirmed_pattern");
            SpannableStringBuilder r72 = SpannableStringBuilder.valueOf(r62);
            int r1 = r62.indexOf(42);
            int r63 = r62.lastIndexOf(42);
            if (r1 != r63) goto L7;
        L10:
            this.troubleButton.setText(AndroidUtilities.formatSpannable(LocaleController.getString(org.telegram.messenger.R.string.RestoreEmailNoAccess), new CharSequence[]{r72}));
            LoginActivity.m15836$$Nest$mshowKeyboard(this.this$0, this.codeFieldContainer);
            this.codeFieldContainer.requestFocus();
            return;
        L7:
            if (r1 == (-1)) goto L10;
            if (r63 == (-1)) goto L10;
            TextStyleSpan.TextStyleRun r3 = new TextStyleSpan.TextStyleRun();
            r3.flags |= 256;
            r3.start = r1;
            int r64 = r63 + 1;
            r3.end = r64;
            r72.setSpan(new TextStyleSpan(r3), r1, r64, 0);
            goto L10
        }

        @Override // org.telegram.ui.Components.SlideView
        public void updateColors() {
            this.titleView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
            this.confirmTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText6));
            this.troubleButton.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText4));
            this.codeFieldContainer.invalidate();
        }
    }

    public class LoginActivityRegisterView extends SlideView implements ImageUpdater.ImageUpdaterDelegate {
        private TLRPC.FileLocation avatar;
        private AnimatorSet avatarAnimation;
        private TLRPC.FileLocation avatarBig;
        private AvatarDrawable avatarDrawable;
        private RLottieImageView avatarEditor;
        private BackupImageView avatarImage;
        private View avatarOverlay;
        private RadialProgressView avatarProgressView;
        private RLottieDrawable cameraDrawable;
        private RLottieDrawable cameraWaitDrawable;
        private Bundle currentParams;
        private TextView descriptionTextView;
        private FrameLayout editTextContainer;
        private EditTextBoldCursor firstNameField;
        private OutlineTextContainerView firstNameOutlineView;
        private ImageUpdater imageUpdater;
        private boolean isCameraWaitAnimationAllowed;
        private EditTextBoldCursor lastNameField;
        private OutlineTextContainerView lastNameOutlineView;
        private boolean nextPressed;
        private String phoneHash;
        private TextView privacyView;
        private String requestPhone;
        final /* synthetic */ LoginActivity this$0;
        private TextView titleTextView;
        private TextView wrongNumber;

        /* renamed from: org.telegram.ui.LoginActivity$LoginActivityRegisterView$1, reason: invalid class name */
        public class AnonymousClass1 extends BackupImageView {
            final /* synthetic */ LoginActivityRegisterView this$1;
            final /* synthetic */ LoginActivity val$this$0;

            public AnonymousClass1(LoginActivityRegisterView r1, Context r2, LoginActivity r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            @Override // android.view.View
            public void invalidate() {
                if (LoginActivityRegisterView.m15911$$Nest$fgetavatarOverlay(this.this$1) == null) goto L5;
                LoginActivityRegisterView.m15911$$Nest$fgetavatarOverlay(this.this$1).invalidate();
            L5:
                super.invalidate();
            }

            @Override // android.view.View
            public void invalidate(int r2, int r3, int r4, int r5) {
                if (LoginActivityRegisterView.m15911$$Nest$fgetavatarOverlay(this.this$1) == null) goto L5;
                LoginActivityRegisterView.m15911$$Nest$fgetavatarOverlay(this.this$1).invalidate();
            L5:
                super.invalidate(r2, r3, r4, r5);
            }
        }

        /* renamed from: org.telegram.ui.LoginActivity$LoginActivityRegisterView$2, reason: invalid class name */
        public class AnonymousClass2 extends View {
            final /* synthetic */ LoginActivityRegisterView this$1;
            final /* synthetic */ Paint val$paint;
            final /* synthetic */ LoginActivity val$this$0;

            public AnonymousClass2(LoginActivityRegisterView r1, Context r2, LoginActivity r3, Paint r4) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                this.val$paint = r4;
                super(r2);
            }

            @Override // android.view.View
            public void onDraw(Canvas r10) {
                if (LoginActivityRegisterView.m15910$$Nest$fgetavatarImage(this.this$1) != null) goto L5;
                return;
            L5:
                if (LoginActivityRegisterView.m15912$$Nest$fgetavatarProgressView(this.this$1).getVisibility() != 0) goto L9;
                this.val$paint.setAlpha((int) ((LoginActivityRegisterView.m15910$$Nest$fgetavatarImage(this.this$1).getImageReceiver().getCurrentAlpha() * 85.0f) * LoginActivityRegisterView.m15912$$Nest$fgetavatarProgressView(this.this$1).getAlpha()));
                r10.drawRoundRect(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), ExteraConfig.getAvatarCorners(getMeasuredWidth(), true), ExteraConfig.getAvatarCorners(getMeasuredWidth(), true), this.val$paint);
                return;
            }
        }

        /* renamed from: org.telegram.ui.LoginActivity$LoginActivityRegisterView$3, reason: invalid class name */
        public class AnonymousClass3 extends RLottieImageView {
            final /* synthetic */ LoginActivityRegisterView this$1;
            final /* synthetic */ LoginActivity val$this$0;

            public AnonymousClass3(LoginActivityRegisterView r1, Context r2, LoginActivity r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            @Override // android.view.View
            public void invalidate() {
                super.invalidate();
                LoginActivityRegisterView.m15911$$Nest$fgetavatarOverlay(this.this$1).invalidate();
            }

            @Override // android.view.View
            public void invalidate(int r1, int r2, int r3, int r4) {
                super.invalidate(r1, r2, r3, r4);
                LoginActivityRegisterView.m15911$$Nest$fgetavatarOverlay(this.this$1).invalidate();
            }
        }

        /* renamed from: org.telegram.ui.LoginActivity$LoginActivityRegisterView$4, reason: invalid class name */
        public class AnonymousClass4 implements View.OnAttachStateChangeListener {
            private Runnable cameraWaitCallback;
            private boolean isAttached;
            private long lastRun;
            final /* synthetic */ LoginActivityRegisterView this$1;
            final /* synthetic */ LoginActivity val$this$0;

            public static /* synthetic */ void $r8$lambda$euyvL3DUTnpnk1RF7okjWU4yE5c(AnonymousClass4 r0) {
                r0.lambda$$0();
            }

            /* renamed from: $r8$lambda$o_-DMfCMQZb8BQszpmMa2fUIPzU, reason: not valid java name */
            public static /* synthetic */ void m15920$r8$lambda$o_DMfCMQZb8BQszpmMa2fUIPzU(AnonymousClass4 r0) {
                r0.lambda$$1();
            }

            public static /* synthetic */ void $r8$lambda$xZvJ5i5VA_skC61wPiqYRoSQSTc(AnonymousClass4 r0) {
                r0.lambda$$2();
            }

            public AnonymousClass4(LoginActivityRegisterView r1, LoginActivity r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
                this.lastRun = System.currentTimeMillis();
                this.cameraWaitCallback = new LoginActivity$LoginActivityRegisterView$4$$ExternalSyntheticLambda0(this);
            }

            private /* synthetic */ void lambda$$0() {
                LoginActivityRegisterView.m15913$$Nest$fgetcameraDrawable(this.this$1).setCurrentFrame(0, false);
                LoginActivityRegisterView.m15909$$Nest$fgetavatarEditor(this.this$1).setAnimation(LoginActivityRegisterView.m15913$$Nest$fgetcameraDrawable(this.this$1));
            }

            private /* synthetic */ void lambda$$1() {
                AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivityRegisterView$4$$ExternalSyntheticLambda2(this));
            }

            private /* synthetic */ void lambda$$2() {
                if (this.isAttached == true) goto L5;
                return;
            L5:
                if (LoginActivityRegisterView.m15916$$Nest$fgetisCameraWaitAnimationAllowed(this.this$1) == true) goto L7;
            L9:
                LoginActivityRegisterView.m15909$$Nest$fgetavatarEditor(this.this$1).postDelayed(this.cameraWaitCallback, 1000);
                return;
            L7:
                if ((System.currentTimeMillis() - this.lastRun) < NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS) goto L9;
                LoginActivityRegisterView.m15909$$Nest$fgetavatarEditor(this.this$1).setAnimation(LoginActivityRegisterView.m15914$$Nest$fgetcameraWaitDrawable(this.this$1));
                LoginActivityRegisterView.m15914$$Nest$fgetcameraWaitDrawable(this.this$1).setCurrentFrame(0, false);
                LoginActivityRegisterView.m15914$$Nest$fgetcameraWaitDrawable(this.this$1).setOnAnimationEndListener(new LoginActivity$LoginActivityRegisterView$4$$ExternalSyntheticLambda1(this));
                LoginActivityRegisterView.m15909$$Nest$fgetavatarEditor(this.this$1).playAnimation();
                this.lastRun = System.currentTimeMillis();
                goto L9
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View r2) {
                this.isAttached = true;
                r2.post(this.cameraWaitCallback);
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View r2) {
                this.isAttached = false;
                r2.removeCallbacks(this.cameraWaitCallback);
            }
        }

        /* renamed from: org.telegram.ui.LoginActivity$LoginActivityRegisterView$5, reason: invalid class name */
        public class AnonymousClass5 extends RadialProgressView {
            final /* synthetic */ LoginActivityRegisterView this$1;
            final /* synthetic */ LoginActivity val$this$0;

            public AnonymousClass5(LoginActivityRegisterView r1, Context r2, LoginActivity r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            @Override // org.telegram.ui.Components.RadialProgressView, android.view.View
            public void setAlpha(float r1) {
                super.setAlpha(r1);
                LoginActivityRegisterView.m15911$$Nest$fgetavatarOverlay(this.this$1).invalidate();
            }
        }

        /* renamed from: org.telegram.ui.LoginActivity$LoginActivityRegisterView$6, reason: invalid class name */
        public class AnonymousClass6 extends AnimatorListenerAdapter {
            final /* synthetic */ LoginActivityRegisterView this$1;
            final /* synthetic */ boolean val$show;

            public AnonymousClass6(LoginActivityRegisterView r1, boolean r2) {
                this.this$1 = r1;
                this.val$show = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator r1) {
                LoginActivityRegisterView.m15918$$Nest$fputavatarAnimation(this.this$1, null);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r3) {
                if (LoginActivityRegisterView.m15908$$Nest$fgetavatarAnimation(this.this$1) != null) goto L5;
                return;
            L5:
                if (LoginActivityRegisterView.m15909$$Nest$fgetavatarEditor(this.this$1) == null) goto L14;
                boolean r32 = this.val$show;
                LoginActivityRegisterView r0 = this.this$1;
                if (r32 == false) goto L10;
                LoginActivityRegisterView.m15909$$Nest$fgetavatarEditor(r0).setVisibility(4);
            L11:
                LoginActivityRegisterView.m15918$$Nest$fputavatarAnimation(this.this$1, null);
                return;
            L10:
                LoginActivityRegisterView.m15912$$Nest$fgetavatarProgressView(r0).setVisibility(4);
                goto L11
            }
        }

        public class LinkSpan extends ClickableSpan {
            final /* synthetic */ LoginActivityRegisterView this$1;

            public LinkSpan(LoginActivityRegisterView r1) {
                this.this$1 = r1;
            }

            @Override // android.text.style.ClickableSpan
            public void onClick(View r1) {
                LoginActivityRegisterView.m15919$$Nest$mshowTermsOfService(this.this$1, false);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint r1) {
                super.updateDrawState(r1);
                r1.setUnderlineText(false);
            }
        }

        public static /* synthetic */ void $r8$lambda$1lrm5CJCAeKjTtaiHcaHt14gnvs(LoginActivityRegisterView r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$onNextPressed$19(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$2jzXpSD5CN48wUHRBDySAv5yjGo(LoginActivityRegisterView r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$onNextPressed$18(r1, r2);
        }

        /* renamed from: $r8$lambda$5PZ9mjR9I-BjoDoaTCcvb3RHaKE, reason: not valid java name */
        public static /* synthetic */ void m15896$r8$lambda$5PZ9mjR9IBjoDoaTCcvb3RHaKE(LoginActivityRegisterView r0, TLRPC.PhotoSize r1, TLRPC.PhotoSize r2) {
            r0.lambda$didUploadPhoto$13(r1, r2);
        }

        /* renamed from: $r8$lambda$6m9sk-pq_r-MQVjy1FyoJrOU_4Q, reason: not valid java name */
        public static /* synthetic */ boolean m15897$r8$lambda$6m9skpq_rMQVjy1FyoJrOU_4Q(LoginActivityRegisterView r0, TextView r1, int r2, KeyEvent r3) {
            return r0.lambda$new$11(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$9MfRgzTdOEHachsQjNlt-x7jNOQ, reason: not valid java name */
        public static /* synthetic */ void m15898$r8$lambda$9MfRgzTdOEHachsQjNltx7jNOQ(LoginActivityRegisterView r0) {
            r0.lambda$onShow$15();
        }

        public static /* synthetic */ void $r8$lambda$ArBS18tQ7c7zo5z8yPgB9kExfI8(LoginActivityRegisterView r0, View r1) {
            r0.lambda$new$7(r1);
        }

        /* renamed from: $r8$lambda$FC9n-TcHiSJti2aZ9CDd_MX7jaQ, reason: not valid java name */
        public static /* synthetic */ void m15899$r8$lambda$FC9nTcHiSJti2aZ9CDd_MX7jaQ(LoginActivityRegisterView r0) {
            r0.lambda$new$5();
        }

        /* renamed from: $r8$lambda$JhI_i-KZa-ISCAg1TT1CmyQ78_s, reason: not valid java name */
        public static /* synthetic */ void m15900$r8$lambda$JhI_iKZaISCAg1TT1CmyQ78_s(LoginActivityRegisterView r0, DialogInterface r1) {
            r0.lambda$new$6(r1);
        }

        public static /* synthetic */ void $r8$lambda$RPnqABYAzqkUzU7PMJpASu1WXeU(LoginActivityRegisterView r0, View r1) {
            r0.lambda$new$12(r1);
        }

        public static /* synthetic */ void $r8$lambda$ZnkTZc5swwyru1NFPaRw6ghfQLU(LoginActivityRegisterView r0, AlertDialog r1, int r2) {
            r0.lambda$showTermsOfService$3(r1, r2);
        }

        /* renamed from: $r8$lambda$dongp-hqaAk7n0UtJNwk_K_XN88, reason: not valid java name */
        public static /* synthetic */ void m15901$r8$lambda$dongphqaAk7n0UtJNwk_K_XN88(LoginActivityRegisterView r0, TLObject r1) {
            r0.lambda$onNextPressed$17(r1);
        }

        /* renamed from: $r8$lambda$g-SzZBZQ0G07IT9JJkLm6EoGPLA, reason: not valid java name */
        public static /* synthetic */ void m15902$r8$lambda$gSzZBZQ0G07IT9JJkLm6EoGPLA(LoginActivityRegisterView r0, AlertDialog r1, int r2) {
            r0.lambda$showTermsOfService$1(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$g4m7WL4W83_jhqTtAZ0LfzFcVWk(LoginActivityRegisterView r0, AlertDialog r1, int r2) {
            r0.lambda$showTermsOfService$0(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$gR_7t9oWIc24Zy_O_7gFQA74boA(LoginActivityRegisterView r0) {
            r0.lambda$new$4();
        }

        /* renamed from: $r8$lambda$kFnK26Voz71PXRylwLqK7-eoGOY, reason: not valid java name */
        public static /* synthetic */ void m15903$r8$lambda$kFnK26Voz71PXRylwLqK7eoGOY(LoginActivityRegisterView r0, View r1, boolean r2) {
            r0.lambda$new$10(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$kggEwlZe7v4x9PzNvFpd__ELBWo(LoginActivityRegisterView r0, TLRPC.FileLocation r1) {
            r0.lambda$onNextPressed$16(r1);
        }

        /* renamed from: $r8$lambda$l55GjNdgQiqGYB6XaL3Ao9nz-nc, reason: not valid java name */
        public static /* synthetic */ boolean m15904$r8$lambda$l55GjNdgQiqGYB6XaL3Ao9nznc(LoginActivityRegisterView r0, TextView r1, int r2, KeyEvent r3) {
            return r0.lambda$new$9(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$pRuSg5qJ6-KMiHBxARBDjzbEMYk, reason: not valid java name */
        public static /* synthetic */ void m15905$r8$lambda$pRuSg5qJ6KMiHBxARBDjzbEMYk(LoginActivityRegisterView r0, AlertDialog r1, int r2) {
            r0.lambda$showTermsOfService$2(r1, r2);
        }

        /* renamed from: $r8$lambda$r6RB8HDZHsvwie6WI0-OQwoky3w, reason: not valid java name */
        public static /* synthetic */ void m15906$r8$lambda$r6RB8HDZHsvwie6WI0OQwoky3w(LoginActivityRegisterView r0, AlertDialog r1, int r2) {
            r0.lambda$onBackPressed$14(r1, r2);
        }

        /* renamed from: $r8$lambda$w0PAKbJXCOfnHlBO-PWviNcvkdo, reason: not valid java name */
        public static /* synthetic */ void m15907$r8$lambda$w0PAKbJXCOfnHlBOPWviNcvkdo(LoginActivityRegisterView r0, View r1, boolean r2) {
            r0.lambda$new$8(r1, r2);
        }

        /* renamed from: -$$Nest$fgetavatarAnimation, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AnimatorSet m15908$$Nest$fgetavatarAnimation(LoginActivityRegisterView r0) {
            return r0.avatarAnimation;
        }

        /* renamed from: -$$Nest$fgetavatarEditor, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RLottieImageView m15909$$Nest$fgetavatarEditor(LoginActivityRegisterView r0) {
            return r0.avatarEditor;
        }

        /* renamed from: -$$Nest$fgetavatarImage, reason: not valid java name */
        public static /* bridge */ /* synthetic */ BackupImageView m15910$$Nest$fgetavatarImage(LoginActivityRegisterView r0) {
            return r0.avatarImage;
        }

        /* renamed from: -$$Nest$fgetavatarOverlay, reason: not valid java name */
        public static /* bridge */ /* synthetic */ View m15911$$Nest$fgetavatarOverlay(LoginActivityRegisterView r0) {
            return r0.avatarOverlay;
        }

        /* renamed from: -$$Nest$fgetavatarProgressView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RadialProgressView m15912$$Nest$fgetavatarProgressView(LoginActivityRegisterView r0) {
            return r0.avatarProgressView;
        }

        /* renamed from: -$$Nest$fgetcameraDrawable, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RLottieDrawable m15913$$Nest$fgetcameraDrawable(LoginActivityRegisterView r0) {
            return r0.cameraDrawable;
        }

        /* renamed from: -$$Nest$fgetcameraWaitDrawable, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RLottieDrawable m15914$$Nest$fgetcameraWaitDrawable(LoginActivityRegisterView r0) {
            return r0.cameraWaitDrawable;
        }

        /* renamed from: -$$Nest$fgetimageUpdater, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ImageUpdater m15915$$Nest$fgetimageUpdater(LoginActivityRegisterView r0) {
            return r0.imageUpdater;
        }

        /* renamed from: -$$Nest$fgetisCameraWaitAnimationAllowed, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m15916$$Nest$fgetisCameraWaitAnimationAllowed(LoginActivityRegisterView r0) {
            return r0.isCameraWaitAnimationAllowed;
        }

        /* renamed from: -$$Nest$fgetwrongNumber, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TextView m15917$$Nest$fgetwrongNumber(LoginActivityRegisterView r0) {
            return r0.wrongNumber;
        }

        /* renamed from: -$$Nest$fputavatarAnimation, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15918$$Nest$fputavatarAnimation(LoginActivityRegisterView r0, AnimatorSet r1) {
            r0.avatarAnimation = r1;
        }

        /* renamed from: -$$Nest$mshowTermsOfService, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15919$$Nest$mshowTermsOfService(LoginActivityRegisterView r0, boolean r1) {
            r0.showTermsOfService(r1);
        }

        public LoginActivityRegisterView(LoginActivity r23, Context r24) {
            this.this$0 = r23;
            super(r24);
            this.nextPressed = false;
            this.isCameraWaitAnimationAllowed = true;
            setOrientation(1);
            ImageUpdater r5 = new ImageUpdater(false, 0, false);
            this.imageUpdater = r5;
            r5.setOpenWithFrontfaceCamera(true);
            this.imageUpdater.setSearchAvailable(false);
            this.imageUpdater.setUploadAfterSelect(false);
            ImageUpdater r52 = this.imageUpdater;
            r52.parentFragment = r23;
            r52.setDelegate(this);
            FrameLayout r53 = new FrameLayout(r24);
            addView(r53, LayoutHelper.createLinear(78, 78, 1));
            this.avatarDrawable = new AvatarDrawable();
            AnonymousClass1 r6 = new AnonymousClass1(this, r24, r23);
            this.avatarImage = r6;
            r6.setRoundRadius(ExteraConfig.getAvatarCorners(78.0f));
            this.avatarDrawable.setAvatarType(13);
            this.avatarDrawable.setInfo(5, null, null);
            this.avatarImage.setImageDrawable(this.avatarDrawable);
            r53.addView(this.avatarImage, LayoutHelper.createFrame(-1, -1.0f));
            Paint r62 = new Paint(1);
            r62.setColor(1426063360);
            AnonymousClass2 r10 = new AnonymousClass2(this, r24, r23, r62);
            this.avatarOverlay = r10;
            r53.addView(r10, LayoutHelper.createFrame(-1, -1.0f));
            this.avatarOverlay.setOnClickListener(new LoginActivity$LoginActivityRegisterView$$ExternalSyntheticLambda2(this));
            this.cameraDrawable = new RLottieDrawable(org.telegram.messenger.R.raw.camera, AndroidUtilities.dp(70.0f), AndroidUtilities.dp(70.0f), false, null);
            this.cameraWaitDrawable = new RLottieDrawable(org.telegram.messenger.R.raw.camera_wait, AndroidUtilities.dp(70.0f), AndroidUtilities.dp(70.0f), false, null);
            AnonymousClass3 r63 = new AnonymousClass3(this, r24, r23);
            this.avatarEditor = r63;
            r63.setScaleType(ImageView.ScaleType.CENTER);
            this.avatarEditor.setAnimation(this.cameraDrawable);
            this.avatarEditor.setEnabled(false);
            this.avatarEditor.setClickable(false);
            r53.addView(this.avatarEditor, LayoutHelper.createFrame(-1, -1.0f));
            this.avatarEditor.addOnAttachStateChangeListener(new AnonymousClass4(this, r23));
            AnonymousClass5 r64 = new AnonymousClass5(this, r24, r23);
            this.avatarProgressView = r64;
            r64.setSize(AndroidUtilities.dp(30.0f));
            this.avatarProgressView.setProgressColor(-1);
            r53.addView(this.avatarProgressView, LayoutHelper.createFrame(-1, -1.0f));
            showAvatarProgress(false, false);
            TextView r1 = new TextView(r24);
            this.titleTextView = r1;
            r1.setText(LocaleController.getString(org.telegram.messenger.R.string.RegistrationProfileInfo));
            this.titleTextView.setTextSize(1, 18.0f);
            this.titleTextView.setTypeface(AndroidUtilities.bold());
            this.titleTextView.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            this.titleTextView.setGravity(1);
            addView(this.titleTextView, LayoutHelper.createLinear(-2, -2, 1, 8, 12, 8, 0));
            TextView r12 = new TextView(r24);
            this.descriptionTextView = r12;
            r12.setText(LocaleController.getString("RegisterText2", org.telegram.messenger.R.string.RegisterText2));
            this.descriptionTextView.setGravity(1);
            float r65 = 14.0f;
            this.descriptionTextView.setTextSize(1, 14.0f);
            this.descriptionTextView.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            addView(this.descriptionTextView, LayoutHelper.createLinear(-2, -2, 1, 8, 6, 8, 0));
            FrameLayout r13 = new FrameLayout(r24);
            this.editTextContainer = r13;
            addView(r13, LayoutHelper.createLinear(-1, -2, 8.0f, 21.0f, 8.0f, 0.0f));
            OutlineTextContainerView r14 = new OutlineTextContainerView(r24);
            this.firstNameOutlineView = r14;
            r14.setText(LocaleController.getString(org.telegram.messenger.R.string.FirstName));
            EditTextBoldCursor r15 = new EditTextBoldCursor(r24);
            this.firstNameField = r15;
            r15.setCursorSize(AndroidUtilities.dp(20.0f));
            this.firstNameField.setCursorWidth(1.5f);
            this.firstNameField.setImeOptions(268435461);
            this.firstNameField.setTextSize(1, 17.0f);
            this.firstNameField.setMaxLines(1);
            this.firstNameField.setInputType(8192);
            this.firstNameField.setOnFocusChangeListener(new LoginActivity$LoginActivityRegisterView$$ExternalSyntheticLambda3(this));
            this.firstNameField.setBackground(null);
            this.firstNameField.setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f));
            this.firstNameOutlineView.attachEditText(this.firstNameField);
            this.firstNameOutlineView.addView(this.firstNameField, LayoutHelper.createFrame(-1, -2, 48));
            this.firstNameField.setOnEditorActionListener(new LoginActivity$LoginActivityRegisterView$$ExternalSyntheticLambda4(this));
            OutlineTextContainerView r16 = new OutlineTextContainerView(r24);
            this.lastNameOutlineView = r16;
            r16.setText(LocaleController.getString(org.telegram.messenger.R.string.LastName));
            EditTextBoldCursor r17 = new EditTextBoldCursor(r24);
            this.lastNameField = r17;
            r17.setCursorSize(AndroidUtilities.dp(20.0f));
            this.lastNameField.setCursorWidth(1.5f);
            this.lastNameField.setImeOptions(268435462);
            this.lastNameField.setTextSize(1, 17.0f);
            this.lastNameField.setMaxLines(1);
            this.lastNameField.setInputType(8192);
            this.lastNameField.setOnFocusChangeListener(new LoginActivity$LoginActivityRegisterView$$ExternalSyntheticLambda5(this));
            this.lastNameField.setBackground(null);
            this.lastNameField.setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f));
            this.lastNameOutlineView.attachEditText(this.lastNameField);
            this.lastNameOutlineView.addView(this.lastNameField, LayoutHelper.createFrame(-1, -2, 48));
            this.lastNameField.setOnEditorActionListener(new LoginActivity$LoginActivityRegisterView$$ExternalSyntheticLambda6(this));
            buildEditTextLayout(AndroidUtilities.isSmallScreen());
            TextView r18 = new TextView(r24);
            this.wrongNumber = r18;
            r18.setText(LocaleController.getString("CancelRegistration", org.telegram.messenger.R.string.CancelRegistration));
            TextView r19 = this.wrongNumber;
            int r9 = 3;
            if (LocaleController.isRTL == false) goto L5;
            int r54 = 5;
        L6:
            r19.setGravity(r54 | 1);
            this.wrongNumber.setTextSize(1, 14.0f);
            this.wrongNumber.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            this.wrongNumber.setPadding(0, AndroidUtilities.dp(24.0f), 0, 0);
            this.wrongNumber.setVisibility(8);
            View r110 = this.wrongNumber;
            if (LocaleController.isRTL == false) goto L9;
            r9 = 5;
        L9:
            addView(r110, LayoutHelper.createLinear(-2, -2, r9 | 48, 0, 20, 0, 0));
            this.wrongNumber.setOnClickListener(new LoginActivity$LoginActivityRegisterView$$ExternalSyntheticLambda7(this));
            FrameLayout r111 = new FrameLayout(r24);
            addView(r111, LayoutHelper.createLinear(-1, -1, 83));
            TextView r3 = new TextView(r24);
            this.privacyView = r3;
            r3.setMovementMethod(new AndroidUtilities.LinkMovementMethodMy());
            TextView r2 = this.privacyView;
            if (AndroidUtilities.isSmallScreen() == false) goto L12;
            r65 = 13.0f;
        L12:
            r2.setTextSize(1, r65);
            this.privacyView.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            this.privacyView.setGravity(16);
            r111.addView(this.privacyView, LayoutHelper.createFrame(-2, 56.0f, 83, 14.0f, 0.0f, 70.0f, 32.0f));
            VerticalPositionAutoAnimator.attach(this.privacyView);
            String r112 = LocaleController.getString("TermsOfServiceLogin", org.telegram.messenger.R.string.TermsOfServiceLogin);
            SpannableStringBuilder r22 = new SpannableStringBuilder(r112);
            int r55 = r112.indexOf(42);
            int r113 = r112.lastIndexOf(42);
            if (r55 == (-1)) goto L17;
            if (r113 == (-1)) goto L17;
            if (r55 == r113) goto L17;
            r22.replace(r113, r113 + 1, _UrlKt.FRAGMENT_ENCODE_SET);
            r22.replace(r55, r55 + 1, _UrlKt.FRAGMENT_ENCODE_SET);
            r22.setSpan(new LinkSpan(this), r55, r113 - 1, 33);
        L17:
            this.privacyView.setText(r22);
            return;
        L5:
            r54 = 3;
            goto L6
        }

        private void buildEditTextLayout(boolean r11) {
            boolean r0 = this.firstNameField.hasFocus();
            boolean r1 = this.lastNameField.hasFocus();
            this.editTextContainer.removeAllViews();
            if (r11 == false) goto L11;
            LinearLayout r112 = new LinearLayout(this.this$0.getParentActivity());
            r112.setOrientation(0);
            this.firstNameOutlineView.setText(LocaleController.getString(org.telegram.messenger.R.string.FirstNameSmall));
            this.lastNameOutlineView.setText(LocaleController.getString(org.telegram.messenger.R.string.LastNameSmall));
            r112.addView(this.firstNameOutlineView, LayoutHelper.createLinear(0, -2, 1.0f, 0, 0, 8, 0));
            r112.addView(this.lastNameOutlineView, LayoutHelper.createLinear(0, -2, 1.0f, 8, 0, 0, 0));
            this.editTextContainer.addView(r112);
            if (r0 == false) goto L8;
            this.firstNameField.requestFocus();
            AndroidUtilities.showKeyboard(this.firstNameField);
            return;
        L8:
            if (r1 == false) goto L13;
            this.lastNameField.requestFocus();
            AndroidUtilities.showKeyboard(this.lastNameField);
            return;
        L13:
            return;
        L11:
            this.firstNameOutlineView.setText(LocaleController.getString(org.telegram.messenger.R.string.FirstName));
            this.lastNameOutlineView.setText(LocaleController.getString(org.telegram.messenger.R.string.LastName));
            this.editTextContainer.addView(this.firstNameOutlineView, LayoutHelper.createFrame(-1, -2.0f, 48, 8.0f, 0.0f, 8.0f, 0.0f));
            this.editTextContainer.addView(this.lastNameOutlineView, LayoutHelper.createFrame(-1, -2.0f, 48, 8.0f, 82.0f, 8.0f, 0.0f));
        }

        private void hidePrivacyView() {
            this.privacyView.animate().alpha(0.0f).setDuration(150).setStartDelay(0).setInterpolator(AndroidUtilities.accelerateInterpolator).start();
        }

        private /* synthetic */ void lambda$didUploadPhoto$13(TLRPC.PhotoSize r3, TLRPC.PhotoSize r4) {
            TLRPC.FileLocation r32 = r3.location;
            this.avatar = r32;
            this.avatarBig = r4.location;
            this.avatarImage.setImage(ImageLocation.getForLocal(r32), "50_50", this.avatarDrawable, null);
        }

        private /* synthetic */ void lambda$new$10(View r1, boolean r2) {
            OutlineTextContainerView r0 = this.lastNameOutlineView;
            if (r2 == false) goto L5;
            float r12 = 1.0f;
        L6:
            r0.animateSelection(r12);
            return;
        L5:
            r12 = 0.0f;
            goto L6
        }

        private /* synthetic */ boolean lambda$new$11(TextView r1, int r2, KeyEvent r3) {
            if (r2 != 6) goto L5;
        L9:
            onNextPressed(null);
            return true;
        L5:
            if (r2 == 5) goto L9;
            return false;
        }

        private /* synthetic */ void lambda$new$12(View r1) {
            if (LoginActivity.m15799$$Nest$fgetradialProgressView(this.this$0).getTag() == null) goto L5;
            return;
        L5:
            onBackPressed(false);
        }

        private /* synthetic */ void lambda$new$4() {
            this.avatar = null;
            this.avatarBig = null;
            showAvatarProgress(false, true);
            this.avatarImage.setImage(null, null, this.avatarDrawable, null);
            this.avatarEditor.setAnimation(this.cameraDrawable);
            this.cameraDrawable.setCurrentFrame(0);
            this.isCameraWaitAnimationAllowed = true;
        }

        private /* synthetic */ void lambda$new$5() {
            this.isCameraWaitAnimationAllowed = true;
        }

        private /* synthetic */ void lambda$new$6(DialogInterface r2) {
            boolean r22 = this.imageUpdater.isUploadingImage();
            RLottieImageView r0 = this.avatarEditor;
            if (r22 == true) goto L6;
            r0.setAnimation(this.cameraDrawable);
            this.cameraDrawable.setCustomEndFrame(86);
            this.avatarEditor.setOnAnimationEndListener(new LoginActivity$LoginActivityRegisterView$$ExternalSyntheticLambda18(this));
            this.avatarEditor.playAnimation();
            return;
        L6:
            r0.setAnimation(this.cameraDrawable);
            this.cameraDrawable.setCurrentFrame(0, false);
            this.isCameraWaitAnimationAllowed = true;
        }

        private /* synthetic */ void lambda$new$7(View r5) {
            ImageUpdater r52 = this.imageUpdater;
            if (this.avatar == null) goto L5;
            boolean r0 = true;
        L6:
            r52.openMenu(r0, new LoginActivity$LoginActivityRegisterView$$ExternalSyntheticLambda12(this), new LoginActivity$LoginActivityRegisterView$$ExternalSyntheticLambda13(this), 0);
            this.isCameraWaitAnimationAllowed = false;
            this.avatarEditor.setAnimation(this.cameraDrawable);
            this.cameraDrawable.setCurrentFrame(0);
            this.cameraDrawable.setCustomEndFrame(43);
            this.avatarEditor.playAnimation();
            return;
        L5:
            r0 = false;
            goto L6
        }

        private /* synthetic */ void lambda$new$8(View r1, boolean r2) {
            OutlineTextContainerView r0 = this.firstNameOutlineView;
            if (r2 == false) goto L5;
            float r12 = 1.0f;
        L6:
            r0.animateSelection(r12);
            return;
        L5:
            r12 = 0.0f;
            goto L6
        }

        private /* synthetic */ boolean lambda$new$9(TextView r1, int r2, KeyEvent r3) {
            if (r2 != 5) goto L6;
            this.lastNameField.requestFocus();
            return true;
        L6:
            return false;
        }

        private /* synthetic */ void lambda$onBackPressed$14(AlertDialog r3, int r4) {
            onBackPressed(true);
            this.this$0.setPage(0, true, null, true);
            hidePrivacyView();
        }

        private /* synthetic */ void lambda$onNextPressed$16(TLRPC.FileLocation r1) {
            MessagesController.getInstance(LoginActivity.access$3900(this.this$0)).uploadAndApplyUserAvatar(r1);
        }

        private /* synthetic */ void lambda$onNextPressed$17(TLObject r3) {
            LoginActivity.m15827$$Nest$mneedHideProgress(this.this$0, false, false);
            AndroidUtilities.hideKeyboard(this.this$0.fragmentView.findFocus());
            LoginActivity.m15832$$Nest$monAuthSuccess(this.this$0, (TLRPC.TL_auth_authorization) r3, true);
            TLRPC.FileLocation r32 = this.avatarBig;
            if (r32 == null) goto L6;
            Utilities.cacheClearQueue.postRunnable(new LoginActivity$LoginActivityRegisterView$$ExternalSyntheticLambda19(this, r32));
            return;
        }

        private /* synthetic */ void lambda$onNextPressed$18(TLObject r4, TLRPC.TL_error r5) {
            this.nextPressed = false;
            if ((r4 instanceof TLRPC.TL_auth_authorization) == false) goto L6;
            hidePrivacyView();
            LoginActivity.m15835$$Nest$mshowDoneButton(this.this$0, false, true);
            postDelayed(new LoginActivity$LoginActivityRegisterView$$ExternalSyntheticLambda17(this, r4), 150);
            return;
        L6:
            LoginActivity.m15826$$Nest$mneedHideProgress(this.this$0, false);
            if (r5.text.contains("PHONE_NUMBER_INVALID") == false) goto L11;
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString("InvalidPhoneNumber", org.telegram.messenger.R.string.InvalidPhoneNumber));
            return;
        L11:
            if (r5.text.contains("PHONE_CODE_EMPTY") == false) goto L13;
        L29:
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString("InvalidCode", org.telegram.messenger.R.string.InvalidCode));
            return;
        L13:
            if (r5.text.contains("PHONE_CODE_INVALID") == true) goto L29;
            if (r5.text.contains("PHONE_CODE_EXPIRED") == false) goto L20;
            onBackPressed(true);
            this.this$0.setPage(0, true, null, true);
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString("CodeExpired", org.telegram.messenger.R.string.CodeExpired));
            return;
        L20:
            if (r5.text.contains("FIRSTNAME_INVALID") == false) goto L23;
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString("InvalidFirstName", org.telegram.messenger.R.string.InvalidFirstName));
            return;
        L23:
            boolean r42 = r5.text.contains("LASTNAME_INVALID");
            LoginActivity r3 = this.this$0;
            if (r42 == false) goto L27;
            LoginActivity.m15828$$Nest$mneedShowAlert(r3, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString("InvalidLastName", org.telegram.messenger.R.string.InvalidLastName));
            return;
        L27:
            LoginActivity.m15828$$Nest$mneedShowAlert(r3, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), r5.text);
        }

        private /* synthetic */ void lambda$onNextPressed$19(TLObject r2, TLRPC.TL_error r3) {
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivityRegisterView$$ExternalSyntheticLambda14(this, r2, r3));
        }

        private /* synthetic */ void lambda$onShow$15() {
            EditTextBoldCursor r0 = this.firstNameField;
            if (r0 == null) goto L6;
            r0.requestFocus();
            EditTextBoldCursor r02 = this.firstNameField;
            r02.setSelection(r02.length());
            AndroidUtilities.showKeyboard(this.firstNameField);
            return;
        }

        private /* synthetic */ void lambda$showTermsOfService$0(AlertDialog r1, int r2) {
            LoginActivity.m15781$$Nest$fgetcurrentTermsOfService(this.this$0).popup = false;
            onNextPressed(null);
        }

        private /* synthetic */ void lambda$showTermsOfService$1(AlertDialog r1, int r2) {
            LoginActivity.m15781$$Nest$fgetcurrentTermsOfService(this.this$0).popup = false;
            onNextPressed(null);
        }

        private /* synthetic */ void lambda$showTermsOfService$2(AlertDialog r2, int r3) {
            onBackPressed(true);
            this.this$0.setPage(0, true, null, true);
        }

        private /* synthetic */ void lambda$showTermsOfService$3(AlertDialog r2, int r3) {
            AlertDialog.Builder r22 = new AlertDialog.Builder(this.this$0.getParentActivity());
            r22.setTitle(LocaleController.getString("TermsOfService", org.telegram.messenger.R.string.TermsOfService));
            r22.setMessage(LocaleController.getString("TosDecline", org.telegram.messenger.R.string.TosDecline));
            r22.setPositiveButton(LocaleController.getString("SignUp", org.telegram.messenger.R.string.SignUp), new LoginActivity$LoginActivityRegisterView$$ExternalSyntheticLambda15(this));
            r22.setNegativeButton(LocaleController.getString("Decline", org.telegram.messenger.R.string.Decline), new LoginActivity$LoginActivityRegisterView$$ExternalSyntheticLambda16(this));
            this.this$0.showDialog(r22.create());
        }

        private void showAvatarProgress(boolean r9, boolean r10) {
            if (this.avatarEditor != null) goto L5;
            return;
        L5:
            AnimatorSet r0 = this.avatarAnimation;
            if (r0 == null) goto L9;
            r0.cancel();
            this.avatarAnimation = null;
        L9:
            if (r10 == false) goto L16;
            this.avatarAnimation = new AnimatorSet();
            Property r4 = View.ALPHA;
            if (r9 == false) goto L13;
            this.avatarProgressView.setVisibility(0);
            this.avatarAnimation.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.avatarEditor, r4, new float[]{0.0f}), ObjectAnimator.ofFloat(this.avatarProgressView, r4, new float[]{1.0f})});
        L14:
            this.avatarAnimation.setDuration(180);
            this.avatarAnimation.addListener(new AnonymousClass6(this, r9));
            this.avatarAnimation.start();
            return;
        L13:
            this.avatarEditor.setVisibility(0);
            this.avatarAnimation.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.avatarEditor, r4, new float[]{1.0f}), ObjectAnimator.ofFloat(this.avatarProgressView, r4, new float[]{0.0f})});
            goto L14
        L16:
            RLottieImageView r102 = this.avatarEditor;
            if (r9 == false) goto L20;
            r102.setAlpha(1.0f);
            this.avatarEditor.setVisibility(4);
            this.avatarProgressView.setAlpha(1.0f);
            this.avatarProgressView.setVisibility(0);
            return;
        L20:
            r102.setAlpha(1.0f);
            this.avatarEditor.setVisibility(0);
            this.avatarProgressView.setAlpha(0.0f);
            this.avatarProgressView.setVisibility(4);
        }

        private void showTermsOfService(boolean r9) {
            if (LoginActivity.m15781$$Nest$fgetcurrentTermsOfService(this.this$0) != null) goto L5;
            return;
        L5:
            AlertDialog.Builder r0 = new AlertDialog.Builder(this.this$0.getParentActivity());
            r0.setTitle(LocaleController.getString("TermsOfService", org.telegram.messenger.R.string.TermsOfService));
            if (r9 == false) goto L8;
            r0.setPositiveButton(LocaleController.getString("Accept", org.telegram.messenger.R.string.Accept), new LoginActivity$LoginActivityRegisterView$$ExternalSyntheticLambda10(this));
            r0.setNegativeButton(LocaleController.getString("Decline", org.telegram.messenger.R.string.Decline), new LoginActivity$LoginActivityRegisterView$$ExternalSyntheticLambda11(this));
        L9:
            SpannableStringBuilder r2 = new SpannableStringBuilder(LoginActivity.m15781$$Nest$fgetcurrentTermsOfService(this.this$0).text);
            MessageObject.addEntitiesToText(r2, LoginActivity.m15781$$Nest$fgetcurrentTermsOfService(this.this$0).entities, false, false, false, false);
            r0.setMessage(r2);
            this.this$0.showDialog(r0.create());
            return;
        L8:
            r0.setPositiveButton(LocaleController.getString("OK", org.telegram.messenger.R.string.OK), null);
            goto L9
        }

        @Override // org.telegram.ui.Components.ImageUpdater.ImageUpdaterDelegate
        public void didUploadPhoto(TLRPC.InputFile r1, TLRPC.InputFile r2, double r3, String r5, TLRPC.PhotoSize r6, TLRPC.PhotoSize r7, boolean r8, TLRPC.VideoSize r9) {
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivityRegisterView$$ExternalSyntheticLambda9(this, r7, r6));
        }

        @Override // org.telegram.ui.Components.ImageUpdater.ImageUpdaterDelegate
        public /* bridge */ /* synthetic */ PhotoViewer.PlaceProviderObject getCloseIntoObject() {
            return super.getCloseIntoObject();
        }

        @Override // org.telegram.ui.Components.SlideView
        public String getHeaderName() {
            return LocaleController.getString("YourName", org.telegram.messenger.R.string.YourName);
        }

        @Override // org.telegram.ui.Components.ImageUpdater.ImageUpdaterDelegate
        public /* bridge */ /* synthetic */ String getInitialSearchString() {
            return super.getInitialSearchString();
        }

        @Override // org.telegram.ui.Components.SlideView
        public boolean needBackButton() {
            return true;
        }

        @Override // org.telegram.ui.Components.SlideView
        public boolean onBackPressed(boolean r5) {
            LoginActivity r0 = this.this$0;
            if (r5 == true) goto L6;
            AlertDialog.Builder r52 = new AlertDialog.Builder(r0.getParentActivity());
            r52.setTitle(LocaleController.getString(org.telegram.messenger.R.string.Warning));
            r52.setMessage(LocaleController.getString("AreYouSureRegistration", org.telegram.messenger.R.string.AreYouSureRegistration));
            r52.setNegativeButton(LocaleController.getString("Stop", org.telegram.messenger.R.string.Stop), new LoginActivity$LoginActivityRegisterView$$ExternalSyntheticLambda0(this));
            r52.setPositiveButton(LocaleController.getString("Continue", org.telegram.messenger.R.string.Continue), null);
            this.this$0.showDialog(r52.create());
            return false;
        L6:
            LoginActivity.m15826$$Nest$mneedHideProgress(r0, true);
            this.nextPressed = false;
            this.currentParams = null;
            return true;
        }

        @Override // org.telegram.ui.Components.SlideView
        public void onCancelPressed() {
            this.nextPressed = false;
        }

        @Override // org.telegram.ui.Components.SlideView
        public void onNextPressed(String r3) {
            if (this.nextPressed == false) goto L6;
            return;
        L6:
            if (LoginActivity.m15781$$Nest$fgetcurrentTermsOfService(this.this$0) == null) goto L12;
            if (LoginActivity.m15781$$Nest$fgetcurrentTermsOfService(this.this$0).popup == false) goto L12;
            showTermsOfService(true);
            return;
        L12:
            if (this.firstNameField.length() != 0) goto L15;
            LoginActivity.m15834$$Nest$monFieldError(this.this$0, this.firstNameOutlineView, true);
            return;
        L15:
            this.nextPressed = true;
            TLRPC.TL_auth_signUp r32 = new TLRPC.TL_auth_signUp();
            r32.phone_code_hash = this.phoneHash;
            r32.phone_number = this.requestPhone;
            r32.first_name = this.firstNameField.getText().toString();
            r32.last_name = this.lastNameField.getText().toString();
            LoginActivity.m15829$$Nest$mneedShowProgress(this.this$0, 0);
            ConnectionsManager.getInstance(LoginActivity.access$3800(this.this$0)).sendRequest(r32, new LoginActivity$LoginActivityRegisterView$$ExternalSyntheticLambda8(this), 10);
        }

        @Override // org.telegram.ui.Components.SlideView
        public void onShow() {
            super.onShow();
            if (this.privacyView == null) goto L8;
            boolean r0 = LoginActivity.m15800$$Nest$fgetrestoringState(this.this$0);
            TextView r1 = this.privacyView;
            if (r0 == false) goto L7;
            r1.setAlpha(1.0f);
            goto L8
        L7:
            r1.setAlpha(0.0f);
            this.privacyView.animate().alpha(1.0f).setDuration(200).setStartDelay(300).setInterpolator(AndroidUtilities.decelerateInterpolator).start();
        L8:
            EditTextBoldCursor r02 = this.firstNameField;
            if (r02 == null) goto L11;
            r02.requestFocus();
            EditTextBoldCursor r03 = this.firstNameField;
            r03.setSelection(r03.length());
            AndroidUtilities.showKeyboard(this.firstNameField);
        L11:
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivityRegisterView$$ExternalSyntheticLambda1(this), LoginActivity.m15838$$Nest$sfgetSHOW_DELAY());
        }

        @Override // org.telegram.ui.Components.SlideView
        public void restoreStateParams(Bundle r5) {
            Bundle r0 = r5.getBundle("registerview_params");
            this.currentParams = r0;
            if (r0 == null) goto L20;
            setParams(r0, true);
        L20:
            String r02 = r5.getString("terms");     // Catch: Exception -> L11
            if (r02 == null) goto L13;
            byte[] r03 = Base64.decode(r02, 0);     // Catch: Exception -> L11
            if (r03 == null) goto L13;
            SerializedData r2 = new SerializedData(r03);     // Catch: Exception -> L11
            LoginActivity.m15810$$Nest$fputcurrentTermsOfService(this.this$0, TLRPC.TL_help_termsOfService.TLdeserialize(r2, r2.readInt32(false), false));     // Catch: Exception -> L11
            r2.cleanup();     // Catch: Exception -> L11
        L13:
            String r04 = r5.getString("registerview_first");
            if (r04 == null) goto L16;
            this.firstNameField.setText(r04);
        L16:
            String r52 = r5.getString("registerview_last");
            if (r52 == null) goto L22;
            this.lastNameField.setText(r52);
            return;
        L22:
            return;
        L11:
            e = move-exception;
            FileLog.e(e);
            goto L13
        }

        @Override // org.telegram.ui.Components.SlideView
        public void saveStateParams(Bundle r4) {
            String r0 = this.firstNameField.getText().toString();
            if (r0.length() == 0) goto L5;
            r4.putString("registerview_first", r0);
        L5:
            String r02 = this.lastNameField.getText().toString();
            if (r02.length() == 0) goto L9;
            r4.putString("registerview_last", r02);
        L9:
            if (LoginActivity.m15781$$Nest$fgetcurrentTermsOfService(this.this$0) == null) goto L11;
            SerializedData r03 = new SerializedData(LoginActivity.m15781$$Nest$fgetcurrentTermsOfService(this.this$0).getObjectSize());
            LoginActivity.m15781$$Nest$fgetcurrentTermsOfService(this.this$0).serializeToStream(r03);
            r4.putString("terms", Base64.encodeToString(r03.toByteArray(), 0));
            r03.cleanup();
        L11:
            Bundle r3 = this.currentParams;
            if (r3 == null) goto L15;
            r4.putBundle("registerview_params", r3);
            return;
        }

        @Override // org.telegram.ui.Components.SlideView
        public void setParams(Bundle r2, boolean r3) {
            if (r2 != null) goto L4;
            return;
        L4:
            this.firstNameField.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            this.lastNameField.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            this.requestPhone = r2.getString("phoneFormated");
            this.phoneHash = r2.getString("phoneHash");
            this.currentParams = r2;
        }

        @Override // org.telegram.ui.Components.SlideView
        public void updateColors() {
            this.avatarDrawable.invalidateSelf();
            TextView r0 = this.titleTextView;
            int r1 = Theme.key_windowBackgroundWhiteBlackText;
            r0.setTextColor(Theme.getColor(r1));
            TextView r02 = this.descriptionTextView;
            int r2 = Theme.key_windowBackgroundWhiteGrayText6;
            r02.setTextColor(Theme.getColor(r2));
            this.firstNameField.setTextColor(Theme.getColor(r1));
            EditTextBoldCursor r03 = this.firstNameField;
            int r3 = Theme.key_windowBackgroundWhiteInputFieldActivated;
            r03.setCursorColor(Theme.getColor(r3));
            this.lastNameField.setTextColor(Theme.getColor(r1));
            this.lastNameField.setCursorColor(Theme.getColor(r3));
            this.wrongNumber.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText4));
            this.privacyView.setTextColor(Theme.getColor(r2));
            this.privacyView.setLinkTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteLinkText));
            this.firstNameOutlineView.updateColor();
            this.lastNameOutlineView.updateColor();
        }
    }

    public class LoginActivityResetWaitView extends SlideView {
        private TextView confirmTextView;
        private Bundle currentParams;
        private String phoneCode;
        private String phoneHash;
        private String requestPhone;
        private TextView resetAccountButton;
        private TextView resetAccountText;
        private TextView resetAccountTime;
        private int startTime;
        final /* synthetic */ LoginActivity this$0;
        private Runnable timeRunnable;
        private TextView titleView;
        private RLottieImageView waitImageView;
        private int waitTime;
        private Boolean wasResetButtonActive;

        /* renamed from: org.telegram.ui.LoginActivity$LoginActivityResetWaitView$1, reason: invalid class name */
        public class AnonymousClass1 implements Runnable {
            final /* synthetic */ LoginActivityResetWaitView this$1;

            public AnonymousClass1(LoginActivityResetWaitView r1) {
                this.this$1 = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (LoginActivityResetWaitView.m15922$$Nest$fgettimeRunnable(this.this$1) == this) goto L5;
                return;
            L5:
                LoginActivityResetWaitView.m15923$$Nest$mupdateTimeText(this.this$1);
                AndroidUtilities.runOnUIThread(LoginActivityResetWaitView.m15922$$Nest$fgettimeRunnable(this.this$1), 1000);
            }
        }

        public static /* synthetic */ void $r8$lambda$DZZVSjGbNxieFpdjFYR5WdxtycU(LoginActivityResetWaitView r0, AlertDialog r1, int r2) {
            r0.lambda$new$2(r1, r2);
        }

        /* renamed from: $r8$lambda$JVkUXcPKUTSfSEZMO3-S7KMnXro, reason: not valid java name */
        public static /* synthetic */ void m15921$r8$lambda$JVkUXcPKUTSfSEZMO3S7KMnXro(LoginActivityResetWaitView r0, TLRPC.TL_error r1) {
            r0.lambda$new$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$coQI3ziaMFxRH12_qnZp_QzCl14(LoginActivityResetWaitView r0, View r1) {
            r0.lambda$new$3(r1);
        }

        public static /* synthetic */ void $r8$lambda$nKpKjSAUUkK377eTCOfL7Uuu7ZU(LoginActivityResetWaitView r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$new$1(r1, r2);
        }

        /* renamed from: -$$Nest$fgettimeRunnable, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Runnable m15922$$Nest$fgettimeRunnable(LoginActivityResetWaitView r0) {
            return r0.timeRunnable;
        }

        /* renamed from: -$$Nest$mupdateTimeText, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15923$$Nest$mupdateTimeText(LoginActivityResetWaitView r0) {
            r0.updateTimeText();
        }

        public LoginActivityResetWaitView(LoginActivity r19, Context r20) {
            this.this$0 = r19;
            super(r20);
            setOrientation(1);
            LinearLayout r3 = new LinearLayout(r20);
            r3.setOrientation(1);
            r3.setGravity(17);
            FrameLayout r5 = new FrameLayout(r20);
            RLottieImageView r6 = new RLottieImageView(r20);
            this.waitImageView = r6;
            r6.setAutoRepeat(true);
            this.waitImageView.setAnimation(org.telegram.messenger.R.raw.sandclock, 120, 120);
            r5.addView(this.waitImageView, LayoutHelper.createFrame(120, 120, 1));
            Point r62 = AndroidUtilities.displaySize;
            if (r62.x > r62.y) goto L5;
        L7:
            int r63 = 0;
        L8:
            r5.setVisibility(r63);
            r3.addView(r5, LayoutHelper.createFrame(-1, -2, 1));
            TextView r52 = new TextView(r20);
            this.titleView = r52;
            r52.setTextSize(1, 18.0f);
            this.titleView.setTypeface(AndroidUtilities.bold());
            this.titleView.setText(LocaleController.getString(org.telegram.messenger.R.string.ResetAccount));
            this.titleView.setGravity(17);
            this.titleView.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            r3.addView(this.titleView, LayoutHelper.createFrame(-1, -2.0f, 1, 32.0f, 16.0f, 32.0f, 0.0f));
            TextView r53 = new TextView(r20);
            this.confirmTextView = r53;
            r53.setTextSize(1, 14.0f);
            this.confirmTextView.setGravity(1);
            this.confirmTextView.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            r3.addView(this.confirmTextView, LayoutHelper.createLinear(-2, -2, 1, 12, 8, 12, 0));
            addView(r3, LayoutHelper.createLinear(-1, 0, 1.0f));
            TextView r32 = new TextView(r20);
            this.resetAccountText = r32;
            r32.setGravity(1);
            this.resetAccountText.setText(LocaleController.getString("ResetAccountStatus", org.telegram.messenger.R.string.ResetAccountStatus));
            this.resetAccountText.setTextSize(1, 14.0f);
            this.resetAccountText.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            addView(this.resetAccountText, LayoutHelper.createLinear(-2, -2, 49, 0, 24, 0, 0));
            TextView r33 = new TextView(r20);
            this.resetAccountTime = r33;
            r33.setGravity(1);
            this.resetAccountTime.setTextSize(1, 20.0f);
            this.resetAccountTime.setTypeface(AndroidUtilities.bold());
            this.resetAccountTime.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            addView(this.resetAccountTime, LayoutHelper.createLinear(-2, -2, 1, 0, 8, 0, 0));
            TextView r34 = new TextView(r20);
            this.resetAccountButton = r34;
            r34.setGravity(17);
            this.resetAccountButton.setText(LocaleController.getString(org.telegram.messenger.R.string.ResetAccount));
            this.resetAccountButton.setTypeface(AndroidUtilities.bold());
            this.resetAccountButton.setTextSize(1, 15.0f);
            this.resetAccountButton.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            this.resetAccountButton.setPadding(AndroidUtilities.dp(34.0f), 0, AndroidUtilities.dp(34.0f), 0);
            this.resetAccountButton.setTextColor(-1);
            addView(this.resetAccountButton, LayoutHelper.createLinear(-1, 50, 1, 16, 32, 16, 48));
            this.resetAccountButton.setOnClickListener(new LoginActivity$LoginActivityResetWaitView$$ExternalSyntheticLambda0(this));
            return;
        L5:
            if (AndroidUtilities.isTablet() == true) goto L7;
            r63 = 8;
            goto L8
        }

        private /* synthetic */ void lambda$new$0(TLRPC.TL_error r5) {
            LoginActivity.m15826$$Nest$mneedHideProgress(this.this$0, false);
            if (r5 == null) goto L5;
            boolean r0 = r5.text.equals("2FA_RECENT_CONFIRM");
            LoginActivity r4 = this.this$0;
            if (r0 == false) goto L19;
            LoginActivity.m15828$$Nest$mneedShowAlert(r4, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString("ResetAccountCancelledAlert", org.telegram.messenger.R.string.ResetAccountCancelledAlert));
            return;
        L19:
            LoginActivity.m15828$$Nest$mneedShowAlert(r4, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), r5.text);
            return;
        L5:
            if (this.requestPhone != null) goto L7;
        L13:
            this.this$0.setPage(0, true, null, true);
            return;
        L7:
            if (this.phoneHash == null) goto L13;
            if (this.phoneCode == null) goto L13;
            Bundle r52 = new Bundle();
            r52.putString("phoneFormated", this.requestPhone);
            r52.putString("phoneHash", this.phoneHash);
            r52.putString("code", this.phoneCode);
            this.this$0.setPage(5, true, r52, false);
        }

        private /* synthetic */ void lambda$new$1(TLObject r1, TLRPC.TL_error r2) {
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivityResetWaitView$$ExternalSyntheticLambda3(this, r2));
        }

        private /* synthetic */ void lambda$new$2(AlertDialog r2, int r3) {
            LoginActivity.m15829$$Nest$mneedShowProgress(this.this$0, 0);
            TL_account.deleteAccount r22 = new TL_account.deleteAccount();
            r22.reason = "Forgot password";
            ConnectionsManager.getInstance(LoginActivity.access$2400(this.this$0)).sendRequest(r22, new LoginActivity$LoginActivityResetWaitView$$ExternalSyntheticLambda2(this), 10);
        }

        private /* synthetic */ void lambda$new$3(View r4) {
            if (LoginActivity.m15799$$Nest$fgetradialProgressView(this.this$0).getTag() == null) goto L5;
            return;
        L5:
            LoginActivity r42 = this.this$0;
            r42.showDialog(new AlertDialog.Builder(r42.getParentActivity()).setTitle(LocaleController.getString("ResetMyAccountWarning", org.telegram.messenger.R.string.ResetMyAccountWarning)).setMessage(LocaleController.getString("ResetMyAccountWarningText", org.telegram.messenger.R.string.ResetMyAccountWarningText)).setPositiveButton(LocaleController.getString("ResetMyAccountWarningReset", org.telegram.messenger.R.string.ResetMyAccountWarningReset), new LoginActivity$LoginActivityResetWaitView$$ExternalSyntheticLambda1(this)).setNegativeButton(LocaleController.getString("Cancel", org.telegram.messenger.R.string.Cancel), null).create());
        }

        private void updateTimeText() {
            int r1 = 0;
            int r0 = Math.max(0, this.waitTime - (ConnectionsManager.getInstance(LoginActivity.access$2300(this.this$0)).getCurrentTime() - this.startTime));
            int r2 = r0 / 86400;
            int r3 = Math.round(r0 / 86400.0f);
            int r4 = r0 / 3600;
            int r5 = (r0 / 60) % 60;
            int r6 = r0 % 60;
            TextView r7 = this.resetAccountTime;
            if (r2 < 2) goto L5;
            r7.setText(LocaleController.formatPluralString("Days", r3, new Object[0]));
        L7:
            if (r0 != 0) goto L9;
            boolean r02 = true;
        L10:
            Boolean r32 = this.wasResetButtonActive;
            if (r32 != null) goto L13;
        L16:
            RLottieImageView r33 = this.waitImageView;
            if (r02 == true) goto L21;
            r33.setAutoRepeat(true);
            if (this.waitImageView.isPlaying() == true) goto L22;
            this.waitImageView.playAnimation();
        L22:
            TextView r22 = this.resetAccountTime;
            if (r02 == false) goto L25;
            int r42 = 4;
        L26:
            r22.setVisibility(r42);
            TextView r23 = this.resetAccountText;
            if (r02 == false) goto L29;
            int r43 = 4;
        L30:
            r23.setVisibility(r43);
            TextView r24 = this.resetAccountButton;
            if (r02 == true) goto L34;
            r1 = 4;
        L34:
            r24.setVisibility(r1);
            this.wasResetButtonActive = Boolean.valueOf(r02);
            return;
        L29:
            r43 = 0;
            goto L30
        L25:
            r42 = 0;
            goto L26
        L21:
            r33.getAnimatedDrawable().setAutoRepeat(0);
            goto L22
        L13:
            if (r32.booleanValue() != r02) goto L16;
            return;
        L9:
            r02 = false;
            goto L10
        L5:
            r7.setText(String.format(Locale.getDefault(), "%02d:%02d:%02d", new Object[]{Integer.valueOf(r4), Integer.valueOf(r5), Integer.valueOf(r6)}));
            goto L7
        }

        @Override // org.telegram.ui.Components.SlideView
        public String getHeaderName() {
            return LocaleController.getString("ResetAccount", org.telegram.messenger.R.string.ResetAccount);
        }

        @Override // org.telegram.ui.Components.SlideView
        public boolean needBackButton() {
            return true;
        }

        @Override // org.telegram.ui.Components.SlideView
        public boolean onBackPressed(boolean r2) {
            LoginActivity.m15826$$Nest$mneedHideProgress(this.this$0, true);
            AndroidUtilities.cancelRunOnUIThread(this.timeRunnable);
            this.timeRunnable = null;
            this.currentParams = null;
            return true;
        }

        @Override // org.telegram.ui.Components.SlideView
        public void restoreStateParams(Bundle r2) {
            Bundle r22 = r2.getBundle("resetview_params");
            this.currentParams = r22;
            if (r22 == null) goto L6;
            setParams(r22, true);
            return;
        }

        @Override // org.telegram.ui.Components.SlideView
        public void saveStateParams(Bundle r2) {
            Bundle r1 = this.currentParams;
            if (r1 == null) goto L6;
            r2.putBundle("resetview_params", r1);
            return;
        }

        @Override // org.telegram.ui.Components.SlideView
        public void setParams(Bundle r4, boolean r5) {
            if (r4 != null) goto L4;
            return;
        L4:
            this.currentParams = r4;
            this.requestPhone = r4.getString("phoneFormated");
            this.phoneHash = r4.getString("phoneHash");
            this.phoneCode = r4.getString("code");
            this.startTime = r4.getInt("startTime");
            this.waitTime = r4.getInt("waitTime");
            this.confirmTextView.setText(AndroidUtilities.replaceTags(LocaleController.formatString("ResetAccountInfo", org.telegram.messenger.R.string.ResetAccountInfo, new Object[]{LocaleController.addNbsp(PhoneFormat.getInstance().format("+" + this.requestPhone))})));
            updateTimeText();
            AnonymousClass1 r42 = new AnonymousClass1(this);
            this.timeRunnable = r42;
            AndroidUtilities.runOnUIThread(r42, 1000);
        }

        @Override // org.telegram.ui.Components.SlideView
        public void updateColors() {
            TextView r0 = this.titleView;
            int r1 = Theme.key_windowBackgroundWhiteBlackText;
            r0.setTextColor(Theme.getColor(r1));
            this.confirmTextView.setTextColor(Theme.getColor(r1));
            this.resetAccountText.setTextColor(Theme.getColor(r1));
            this.resetAccountTime.setTextColor(Theme.getColor(r1));
            this.resetAccountButton.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(6.0f), Theme.getColor(Theme.key_changephoneinfo_image2), Theme.getColor(Theme.key_chats_actionPressedBackground)));
        }
    }

    public class LoginActivitySetupEmail extends SlideView {
        private Bundle currentParams;
        private EditTextBoldCursor emailField;
        private OutlineTextContainerView emailOutlineView;
        private String emailPhone;
        private GoogleSignInAccount googleAccount;
        private RLottieImageView inboxImageView;
        private LoginOrView loginOrView;
        private boolean nextPressed;
        private String phone;
        private String phoneHash;
        private String requestPhone;
        private TextView signInWithGoogleView;
        private TextView subtitleView;
        final /* synthetic */ LoginActivity this$0;
        private TextView titleView;

        /* renamed from: org.telegram.ui.LoginActivity$LoginActivitySetupEmail$1, reason: invalid class name */
        public class AnonymousClass1 extends ReplacementSpan {
            final /* synthetic */ LoginActivitySetupEmail this$1;
            final /* synthetic */ LoginActivity val$this$0;

            public AnonymousClass1(LoginActivitySetupEmail r1, LoginActivity r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // android.text.style.ReplacementSpan
            public void draw(Canvas r1, CharSequence r2, int r3, int r4, float r5, int r6, int r7, int r8, Paint r9) {
            }

            @Override // android.text.style.ReplacementSpan
            public int getSize(Paint r1, CharSequence r2, int r3, int r4, Paint.FontMetricsInt r5) {
                return AndroidUtilities.dp(12.0f);
            }
        }

        /* renamed from: org.telegram.ui.LoginActivity$LoginActivitySetupEmail$2, reason: invalid class name */
        public class AnonymousClass2 implements NotificationCenter.NotificationCenterDelegate {
            final /* synthetic */ LoginActivitySetupEmail this$1;

            public AnonymousClass2(LoginActivitySetupEmail r1) {
                this.this$1 = r1;
            }

            @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
            public void didReceivedNotification(int r2, int r3, Object... r4) {
                int r22 = ((Integer) r4[0]).intValue();
                ((Integer) r4[1]).getClass();
                Intent r32 = (Intent) r4[2];
                NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.onActivityResultReceived);
                if (r22 != 200) goto L11;
                LoginActivitySetupEmail.m15926$$Nest$fputgoogleAccount(this.this$1, GoogleSignIn.getSignedInAccountFromIntent(r32).getResult(ApiException.class));     // Catch: ApiException -> L6
                this.this$1.onNextPressed(null);     // Catch: ApiException -> L6
                return;
            L6:
                e = move-exception;
                FileLog.e(e);
                return;
            }
        }

        /* renamed from: $r8$lambda$2P2RcbSwuO3M-plRPAVbfZlPN9s, reason: not valid java name */
        public static /* synthetic */ void m15924$r8$lambda$2P2RcbSwuO3MplRPAVbfZlPN9s(LoginActivitySetupEmail r0, Bundle r1, TL_account.verifyEmail r2, TLObject r3, TLRPC.TL_error r4) {
            r0.lambda$onNextPressed$6(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$4Cgr6It3JpcGDWbpElTSeY2Jm70(LoginActivitySetupEmail r0, View r1, boolean r2) {
            r0.lambda$new$0(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$GyCv6lPm5inSgpwL30praYTxKqQ(LoginActivitySetupEmail r0) {
            r0.lambda$onShow$9();
        }

        public static /* synthetic */ void $r8$lambda$TIB10ea6oT7KWGOVRzRFO8GminY(LoginActivitySetupEmail r0, TLObject r1, Bundle r2, TLRPC.TL_error r3, TL_account.verifyEmail r4) {
            r0.lambda$onNextPressed$5(r1, r2, r3, r4);
        }

        /* renamed from: $r8$lambda$cCP4tleCyUlQTUIsw1P-3KRkuaw, reason: not valid java name */
        public static /* synthetic */ boolean m15925$r8$lambda$cCP4tleCyUlQTUIsw1P3KRkuaw(LoginActivitySetupEmail r0, TextView r1, int r2, KeyEvent r3) {
            return r0.lambda$new$1(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$g1A2EPJz9xoPIhI7BkdCyGNzZaU(LoginActivitySetupEmail r0, View r1) {
            r0.lambda$new$3(r1);
        }

        public static /* synthetic */ void $r8$lambda$jCpVLVsM9vUpwnuAEZxPfacKBkY(LoginActivitySetupEmail r0, Bundle r1, TL_account.sendVerifyEmailCode r2, TLObject r3, TLRPC.TL_error r4) {
            r0.lambda$onNextPressed$8(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$kUDFuMKR293PG3Kl1e8rdHfeaVU(LoginActivitySetupEmail r0, GoogleSignInClient r1, Task r2) {
            r0.lambda$new$2(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$oiEKE2v09Sd3ZPT5uw8FRmeo3jQ(LoginActivitySetupEmail r0, TLObject r1, Bundle r2, TLRPC.TL_error r3, TL_account.sendVerifyEmailCode r4) {
            r0.lambda$onNextPressed$7(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$pw1fVpf5UU6T0UTcwBz8nMicQHw(LoginActivitySetupEmail r0) {
            r0.lambda$onPasscodeError$4();
        }

        /* renamed from: -$$Nest$fputgoogleAccount, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15926$$Nest$fputgoogleAccount(LoginActivitySetupEmail r0, GoogleSignInAccount r1) {
            r0.googleAccount = r1;
        }

        public LoginActivitySetupEmail(LoginActivity r26, Context r27) {
            this.this$0 = r26;
            super(r27);
            setOrientation(1);
            FrameLayout r4 = new FrameLayout(r27);
            RLottieImageView r5 = new RLottieImageView(r27);
            this.inboxImageView = r5;
            r5.setAnimation(org.telegram.messenger.R.raw.tsv_setup_mail, 120, 120);
            this.inboxImageView.setAutoRepeat(false);
            r4.addView(this.inboxImageView, LayoutHelper.createFrame(120, 120, 1));
            if (AndroidUtilities.isSmallScreen() == true) goto L10;
            Point r52 = AndroidUtilities.displaySize;
            if (r52.x > r52.y) goto L7;
        L9:
            int r53 = 0;
        L11:
            r4.setVisibility(r53);
            addView(r4, LayoutHelper.createFrame(-1, -2, 1));
            TextView r42 = new TextView(r27);
            this.titleView = r42;
            r42.setTextSize(1, 18.0f);
            this.titleView.setTypeface(AndroidUtilities.bold());
            TextView r43 = this.titleView;
            if (LoginActivity.m15776$$Nest$fgetactivityMode(r26) != 3) goto L14;
            int r9 = org.telegram.messenger.R.string.EnterNewEmail;
        L15:
            r43.setText(LocaleController.getString(r9));
            this.titleView.setGravity(17);
            this.titleView.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            addView(this.titleView, LayoutHelper.createFrame(-1, -2.0f, 1, 32.0f, 16.0f, 32.0f, 0.0f));
            LinkSpanDrawable.LinksTextView r44 = new LinkSpanDrawable.LinksTextView(r27);
            this.subtitleView = r44;
            r44.setTextSize(1, 14.0f);
            this.subtitleView.setGravity(17);
            this.subtitleView.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            this.subtitleView.setText(LocaleController.getString(org.telegram.messenger.R.string.AddEmailSubtitle));
            addView(this.subtitleView, LayoutHelper.createLinear(-2, -2, 1, 32, 8, 32, 0));
            OutlineTextContainerView r45 = new OutlineTextContainerView(r27);
            this.emailOutlineView = r45;
            if (LoginActivity.m15776$$Nest$fgetactivityMode(r26) != 3) goto L18;
            int r92 = org.telegram.messenger.R.string.YourNewEmail;
        L19:
            r45.setText(LocaleController.getString(r92));
            EditTextBoldCursor r46 = new EditTextBoldCursor(r27);
            this.emailField = r46;
            r46.setCursorSize(AndroidUtilities.dp(20.0f));
            this.emailField.setCursorWidth(1.5f);
            this.emailField.setImeOptions(268435461);
            this.emailField.setTextSize(1, 17.0f);
            this.emailField.setMaxLines(1);
            this.emailField.setInputType(33);
            this.emailField.setOnFocusChangeListener(new LoginActivity$LoginActivitySetupEmail$$ExternalSyntheticLambda3(this));
            this.emailField.setBackground(null);
            this.emailField.setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f));
            this.emailOutlineView.attachEditText(this.emailField);
            this.emailOutlineView.addView(this.emailField, LayoutHelper.createFrame(-1, -2, 48));
            this.emailField.setOnEditorActionListener(new LoginActivity$LoginActivitySetupEmail$$ExternalSyntheticLambda4(this));
            addView(this.emailOutlineView, LayoutHelper.createLinear(-1, 58, 16.0f, 24.0f, 16.0f, 0.0f));
            TextView r47 = new TextView(r27);
            this.signInWithGoogleView = r47;
            r47.setGravity(3);
            this.signInWithGoogleView.setTextSize(1, 14.0f);
            this.signInWithGoogleView.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            this.signInWithGoogleView.setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f));
            this.signInWithGoogleView.setMaxLines(2);
            SpannableStringBuilder r48 = new SpannableStringBuilder("d ");
            Drawable r10 = ContextCompat.getDrawable(r27, R$drawable.googleg_standard_color_18);
            r10.setBounds(0, AndroidUtilities.dp(9.0f), AndroidUtilities.dp(18.0f), AndroidUtilities.dp(27.0f));
            r48.setSpan(new ImageSpan(r10, 0), 0, 1, 33);
            r48.setSpan(new AnonymousClass1(this, r26), 1, 2, 33);
            r48.append(LocaleController.getString(org.telegram.messenger.R.string.SignInWithGoogle));
            this.signInWithGoogleView.setText(r48);
            this.loginOrView = new LoginOrView(r27);
            addView(new Space(r27), LayoutHelper.createLinear(0, 0, 1.0f));
            FrameLayout r1 = new FrameLayout(r27);
            r1.addView(this.signInWithGoogleView, LayoutHelper.createFrame(-2, -2.0f, 83, 0.0f, 0.0f, 0.0f, 24.0f));
            r1.addView(this.loginOrView, LayoutHelper.createFrame(-2, 16.0f, 83, 0.0f, 0.0f, 0.0f, 70.0f));
            this.loginOrView.setMeasureAfter(this.signInWithGoogleView);
            addView(r1, LayoutHelper.createLinear(-1, -2));
            VerticalPositionAutoAnimator.attach(r1);
            r1.setOnClickListener(new LoginActivity$LoginActivitySetupEmail$$ExternalSyntheticLambda5(this));
            return;
        L18:
            r92 = org.telegram.messenger.R.string.YourEmail;
            goto L19
        L14:
            r9 = org.telegram.messenger.R.string.AddEmailTitle;
            goto L15
        L7:
            if (AndroidUtilities.isTablet() == true) goto L9;
        L10:
            r53 = 8;
            goto L11
        }

        private /* synthetic */ void lambda$new$0(View r1, boolean r2) {
            OutlineTextContainerView r0 = this.emailOutlineView;
            if (r2 == false) goto L5;
            float r12 = 1.0f;
        L6:
            r0.animateSelection(r12);
            return;
        L5:
            r12 = 0.0f;
            goto L6
        }

        private /* synthetic */ boolean lambda$new$1(TextView r1, int r2, KeyEvent r3) {
            if (r2 != 5) goto L6;
            onNextPressed(null);
            return true;
        L6:
            return false;
        }

        private /* synthetic */ void lambda$new$2(GoogleSignInClient r1, Task r2) {
            if (this.this$0.getParentActivity() != null) goto L5;
            return;
        L5:
            if (this.this$0.getParentActivity().isFinishing() == true) goto L10;
            this.this$0.getParentActivity().startActivityForResult(r1.getSignInIntent(), 200);
            return;
        }

        private /* synthetic */ void lambda$new$3(View r3) {
            NotificationCenter.getGlobalInstance().addObserver(new AnonymousClass2(this), NotificationCenter.onActivityResultReceived);
            GoogleSignInClient r32 = GoogleSignIn.getClient(getContext(), new GoogleSignInOptions.Builder().requestIdToken(BuildVars.GOOGLE_AUTH_CLIENT_ID).requestEmail().build());
            r32.signOut().addOnCompleteListener(new LoginActivity$LoginActivitySetupEmail$$ExternalSyntheticLambda9(this, r32));
        }

        private /* synthetic */ void lambda$onNextPressed$5(TLObject r3, Bundle r4, TLRPC.TL_error r5, TL_account.verifyEmail r6) {
            if ((r3 instanceof TL_account.TL_emailVerified) == false) goto L9;
            if (LoginActivity.m15776$$Nest$fgetactivityMode(this.this$0) != 3) goto L9;
            this.this$0.finishFragment();
            LoginActivity.m15785$$Nest$fgetemailChangeFinishCallback(this.this$0).run();
            return;
        L9:
            if ((r3 instanceof TL_account.TL_emailVerifiedLogin) == false) goto L12;
            TL_account.TL_emailVerifiedLogin r32 = (TL_account.TL_emailVerifiedLogin) r3;
            r4.putString("email", r32.email);
            LoginActivity.m15820$$Nest$mfillNextCodeParams(this.this$0, r4, r32.sent_code);
            return;
        L12:
            if (r5 != null) goto L14;
            return;
        L14:
            if (r5.text.contains("EMAIL_NOT_ALLOWED") == false) goto L18;
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString(org.telegram.messenger.R.string.EmailNotAllowed));
            return;
        L18:
            if (r5.text.contains("EMAIL_TOKEN_INVALID") == false) goto L22;
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString(org.telegram.messenger.R.string.EmailTokenInvalid));
            return;
        L22:
            if (r5.code == (-1000)) goto L26;
            AlertsCreator.processError(LoginActivity.access$2800(this.this$0), r5, this.this$0, r6, new Object[0]);
            return;
        }

        private /* synthetic */ void lambda$onNextPressed$6(Bundle r7, TL_account.verifyEmail r8, TLObject r9, TLRPC.TL_error r10) {
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivitySetupEmail$$ExternalSyntheticLambda8(this, r9, r7, r10, r8));
        }

        private /* synthetic */ void lambda$onNextPressed$7(TLObject r3, Bundle r4, TLRPC.TL_error r5, TL_account.sendVerifyEmailCode r6) {
            LoginActivity.m15826$$Nest$mneedHideProgress(this.this$0, false);
            this.nextPressed = false;
            if ((r3 instanceof TL_account.sentEmailCode) == false) goto L6;
            LoginActivity r2 = this.this$0;
            LoginActivity.m15821$$Nest$mfillNextCodeParams(r2, r4, (TL_account.sentEmailCode) r3);
            return;
        L6:
            String r32 = r5.text;
            if (r32 != null) goto L9;
            return;
        L9:
            if (r32.contains("EMAIL_INVALID") == false) goto L13;
            onPasscodeError(false);
            return;
        L13:
            if (r5.text.contains("EMAIL_NOT_ALLOWED") == false) goto L17;
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString(org.telegram.messenger.R.string.EmailNotAllowed));
            return;
        L17:
            if (r5.text.contains("PHONE_PASSWORD_FLOOD") == false) goto L21;
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString("FloodWait", org.telegram.messenger.R.string.FloodWait));
            return;
        L21:
            if (r5.text.contains("PHONE_NUMBER_FLOOD") == false) goto L25;
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString("PhoneNumberFlood", org.telegram.messenger.R.string.PhoneNumberFlood));
            return;
        L25:
            if (r5.text.contains("PHONE_CODE_EMPTY") == false) goto L27;
        L41:
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString("InvalidCode", org.telegram.messenger.R.string.InvalidCode));
            return;
        L27:
            if (r5.text.contains("PHONE_CODE_INVALID") == true) goto L41;
            if (r5.text.contains("PHONE_CODE_EXPIRED") == false) goto L34;
            onBackPressed(true);
            this.this$0.setPage(0, true, null, true);
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString("CodeExpired", org.telegram.messenger.R.string.CodeExpired));
            return;
        L34:
            if (r5.text.startsWith("FLOOD_WAIT") == false) goto L38;
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString("FloodWait", org.telegram.messenger.R.string.FloodWait));
            return;
        L38:
            if (r5.code == (-1000)) goto L44;
            AlertsCreator.processError(LoginActivity.access$2700(this.this$0), r5, this.this$0, r6, new Object[]{this.requestPhone});
            return;
        }

        private /* synthetic */ void lambda$onNextPressed$8(Bundle r7, TL_account.sendVerifyEmailCode r8, TLObject r9, TLRPC.TL_error r10) {
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivitySetupEmail$$ExternalSyntheticLambda7(this, r9, r7, r10, r8));
        }

        private /* synthetic */ void lambda$onPasscodeError$4() {
            this.emailField.requestFocus();
        }

        private /* synthetic */ void lambda$onShow$9() {
            this.inboxImageView.getAnimatedDrawable().setCurrentFrame(0, false);
            this.inboxImageView.playAnimation();
            this.emailField.requestFocus();
            AndroidUtilities.showKeyboard(this.emailField);
        }

        private void onPasscodeError(boolean r4) {
            if (this.this$0.getParentActivity() != null) goto L11;
            return;
        L11:
            this.emailOutlineView.performHapticFeedback(3, 2);     // Catch: Exception -> L10
        L6:
            if (r4 == false) goto L8;
            this.emailField.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        L8:
            this.emailField.requestFocus();
            LoginActivity.m15834$$Nest$monFieldError(this.this$0, this.emailOutlineView, true);
            postDelayed(new LoginActivity$LoginActivitySetupEmail$$ExternalSyntheticLambda6(this), 300);
        }

        @Override // org.telegram.ui.Components.SlideView
        public String getHeaderName() {
            return LocaleController.getString("AddEmailTitle", org.telegram.messenger.R.string.AddEmailTitle);
        }

        @Override // org.telegram.ui.Components.SlideView
        public boolean needBackButton() {
            return !LoginActivity.m15786$$Nest$fgetemailChangeIsSuggestion(this.this$0);
        }

        @Override // org.telegram.ui.Components.SlideView
        public void onNextPressed(String r7) {
            if (this.nextPressed == false) goto L5;
            return;
        L5:
            GoogleSignInAccount r72 = this.googleAccount;
            if (r72 == null) goto L8;
            String r73 = r72.getEmail();
        L9:
            Bundle r0 = new Bundle();
            r0.putString("phone", this.phone);
            r0.putString("ephone", this.emailPhone);
            r0.putString("phoneFormated", this.requestPhone);
            r0.putString("phoneHash", this.phoneHash);
            r0.putString("email", r73);
            r0.putBoolean("setup", true);
            if (this.googleAccount == null) goto L18;
            TL_account.verifyEmail r74 = new TL_account.verifyEmail();
            if (LoginActivity.m15776$$Nest$fgetactivityMode(this.this$0) != 3) goto L14;
            r74.purpose = new TLRPC.TL_emailVerifyPurposeLoginChange();
        L15:
            TLRPC.TL_emailVerificationGoogle r1 = new TLRPC.TL_emailVerificationGoogle();
            r1.token = this.googleAccount.getIdToken();
            r74.verification = r1;
            this.googleAccount = null;
            ConnectionsManager.getInstance(LoginActivity.access$2500(this.this$0)).sendRequest(r74, new LoginActivity$LoginActivitySetupEmail$$ExternalSyntheticLambda1(this, r0, r74), 10);
            return;
        L14:
            TLRPC.TL_emailVerifyPurposeLoginSetup r12 = new TLRPC.TL_emailVerifyPurposeLoginSetup();
            r12.phone_number = this.requestPhone;
            r12.phone_code_hash = this.phoneHash;
            r74.purpose = r12;
            goto L15
        L18:
            if (TextUtils.isEmpty(r73) == false) goto L21;
            onPasscodeError(false);
            return;
        L21:
            this.nextPressed = true;
            LoginActivity.m15829$$Nest$mneedShowProgress(this.this$0, 0);
            TL_account.sendVerifyEmailCode r13 = new TL_account.sendVerifyEmailCode();
            if (LoginActivity.m15776$$Nest$fgetactivityMode(this.this$0) != 3) goto L24;
            r13.purpose = new TLRPC.TL_emailVerifyPurposeLoginChange();
        L25:
            r13.email = r73;
            ConnectionsManager.getInstance(LoginActivity.access$2600(this.this$0)).sendRequest(r13, new LoginActivity$LoginActivitySetupEmail$$ExternalSyntheticLambda2(this, r0, r13), 10);
            return;
        L24:
            TLRPC.TL_emailVerifyPurposeLoginSetup r2 = new TLRPC.TL_emailVerifyPurposeLoginSetup();
            r2.phone_number = this.requestPhone;
            r2.phone_code_hash = this.phoneHash;
            r13.purpose = r2;
            goto L25
        L8:
            r73 = this.emailField.getText().toString();
            goto L9
        }

        @Override // org.telegram.ui.Components.SlideView
        public void onShow() {
            super.onShow();
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivitySetupEmail$$ExternalSyntheticLambda0(this), LoginActivity.m15838$$Nest$sfgetSHOW_DELAY());
        }

        @Override // org.telegram.ui.Components.SlideView
        public void restoreStateParams(Bundle r3) {
            Bundle r0 = r3.getBundle("emailsetup_params");
            this.currentParams = r0;
            if (r0 == null) goto L5;
            setParams(r0, true);
        L5:
            String r32 = r3.getString("emailsetup_email");
            if (r32 == null) goto L9;
            this.emailField.setText(r32);
            return;
        }

        @Override // org.telegram.ui.Components.SlideView
        public void saveStateParams(Bundle r3) {
            String r0 = this.emailField.getText().toString();
            if (r0 != null) goto L5;
        L7:
            Bundle r2 = this.currentParams;
            if (r2 == null) goto L11;
            r3.putBundle("emailsetup_params", r2);
            return;
        L11:
            return;
        L5:
            if (r0.length() == 0) goto L7;
            r3.putString("emailsetup_email", r0);
            goto L7
        }

        @Override // org.telegram.ui.Components.SlideView
        public void setParams(Bundle r2, boolean r3) {
            if (r2 != null) goto L4;
            return;
        L4:
            this.emailField.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            this.currentParams = r2;
            this.phone = r2.getString("phone");
            this.emailPhone = this.currentParams.getString("ephone");
            this.requestPhone = this.currentParams.getString("phoneFormated");
            this.phoneHash = this.currentParams.getString("phoneHash");
            if (r2.getBoolean("googleSignInAllowed") == true) goto L7;
        L9:
            int r22 = 8;
        L10:
            this.loginOrView.setVisibility(r22);
            this.signInWithGoogleView.setVisibility(r22);
            LoginActivity.m15836$$Nest$mshowKeyboard(this.this$0, this.emailField);
            this.emailField.requestFocus();
            return;
        L7:
            if (PushListenerController.GooglePushListenerServiceProvider.INSTANCE.hasServices() == false) goto L9;
            r22 = 0;
            goto L10
        }

        @Override // org.telegram.ui.Components.SlideView
        public void updateColors() {
            TextView r0 = this.titleView;
            int r1 = Theme.key_windowBackgroundWhiteBlackText;
            r0.setTextColor(Theme.getColor(r1));
            this.subtitleView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText6));
            this.subtitleView.setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkIn));
            this.emailField.setTextColor(Theme.getColor(r1));
            this.signInWithGoogleView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText4));
            this.loginOrView.updateColors();
            this.emailOutlineView.invalidate();
        }
    }

    public class LoginActivitySmsView extends SlideView implements NotificationCenter.NotificationCenterDelegate {
        private RLottieImageView blueImageView;
        private FrameLayout bottomContainer;
        private String catchedPhone;
        private CodeFieldContainer codeFieldContainer;
        private int codeTime;
        private Timer codeTimer;
        private TextView confirmTextView;
        private Bundle currentParams;
        private int currentType;
        private RLottieDrawable dotsDrawable;
        private RLottieDrawable dotsToStarsDrawable;
        private String emailPhone;
        private Runnable errorColorTimeout;
        private ViewSwitcher errorViewSwitcher;
        RLottieDrawable hintDrawable;
        private boolean isDotsAnimationVisible;
        private boolean isResendingCode;
        private double lastCodeTime;
        private double lastCurrentTime;
        private String lastError;
        private int length;
        private ImageView missedCallArrowIcon;
        private TextView missedCallDescriptionSubtitle;
        private TextView missedCallDescriptionSubtitle2;
        private ImageView missedCallPhoneIcon;
        private TLRPC.TL_auth_sentCode nextCodeAuth;
        private Bundle nextCodeParams;
        private boolean nextPressed;
        private int nextType;
        private LinearLayout openFragmentButton;
        private TextView openFragmentButtonText;
        private RLottieImageView openFragmentImageView;
        private int openTime;
        private String pattern;
        private String phone;
        private String phoneHash;
        private boolean postedErrorColorTimeout;
        private String prefix;
        private TextView prefixTextView;
        private int prevType;
        private TextView prevTypeTextView;
        private FrameLayout problemFrame;
        private LoadingTextView problemText;
        private String requestPhone;
        private RLottieDrawable starsToDotsDrawable;
        final /* synthetic */ LoginActivity this$0;
        private int time;
        private LoadingTextView timeText;
        private Timer timeTimer;
        private final Object timerSync;
        private TextView titleTextView;
        private String url;
        private boolean waitingForEvent;
        private TextView wrongCode;

        /* renamed from: org.telegram.ui.LoginActivity$LoginActivitySmsView$1, reason: invalid class name */
        public class AnonymousClass1 extends CodeFieldContainer {
            final /* synthetic */ LoginActivitySmsView this$1;
            final /* synthetic */ LoginActivity val$this$0;

            public AnonymousClass1(LoginActivitySmsView r1, Context r2, LoginActivity r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            @Override // org.telegram.ui.CodeFieldContainer
            public void processNextPressed() {
                this.this$1.onNextPressed(null);
            }
        }

        /* renamed from: org.telegram.ui.LoginActivity$LoginActivitySmsView$2, reason: invalid class name */
        public class AnonymousClass2 extends CodeFieldContainer {
            final /* synthetic */ LoginActivitySmsView this$1;
            final /* synthetic */ LoginActivity val$this$0;

            public AnonymousClass2(LoginActivitySmsView r1, Context r2, LoginActivity r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            @Override // org.telegram.ui.CodeFieldContainer
            public void processNextPressed() {
                this.this$1.onNextPressed(null);
            }
        }

        /* renamed from: org.telegram.ui.LoginActivity$LoginActivitySmsView$3, reason: invalid class name */
        public class AnonymousClass3 extends LoadingTextView {
            final /* synthetic */ LoginActivitySmsView this$1;
            final /* synthetic */ LoginActivity val$this$0;

            public AnonymousClass3(LoginActivitySmsView r1, Context r2, LoginActivity r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r1.this$0, r2);
            }

            @Override // org.telegram.ui.LoginActivity.LoadingTextView
            public boolean isResendingCode() {
                return LoginActivitySmsView.m15939$$Nest$fgetisResendingCode(this.this$1);
            }

            @Override // org.telegram.ui.LoginActivity.LoadingTextView
            public boolean isRippleEnabled() {
                if (getVisibility() == 0) goto L5;
                return false;
            L5:
                if (LoginActivitySmsView.m15948$$Nest$fgettime(this.this$1) > 0) goto L7;
                return true;
            L7:
                if (LoginActivitySmsView.m15950$$Nest$fgettimeTimer(this.this$1) != null) goto L13;
                return true;
            L13:
                return false;
            }
        }

        /* renamed from: org.telegram.ui.LoginActivity$LoginActivitySmsView$4, reason: invalid class name */
        public class AnonymousClass4 extends ViewSwitcher {
            final /* synthetic */ LoginActivitySmsView this$1;
            final /* synthetic */ LoginActivity val$this$0;

            public AnonymousClass4(LoginActivitySmsView r1, Context r2, LoginActivity r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            @Override // android.widget.FrameLayout, android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(100.0f), Integer.MIN_VALUE));
            }
        }

        /* renamed from: org.telegram.ui.LoginActivity$LoginActivitySmsView$5, reason: invalid class name */
        public class AnonymousClass5 extends LoadingTextView {
            final /* synthetic */ LoginActivitySmsView this$1;
            final /* synthetic */ LoginActivity val$this$0;

            public AnonymousClass5(LoginActivitySmsView r1, Context r2, LoginActivity r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r1.this$0, r2);
            }

            @Override // org.telegram.ui.LoginActivity.LoadingTextView
            public boolean isResendingCode() {
                return LoginActivitySmsView.m15939$$Nest$fgetisResendingCode(this.this$1);
            }

            @Override // org.telegram.ui.LoginActivity.LoadingTextView
            public boolean isRippleEnabled() {
                if (isClickable() == true) goto L5;
                return false;
            L5:
                if (getVisibility() == 0) goto L7;
                return false;
            L7:
                if (LoginActivitySmsView.m15942$$Nest$fgetnextPressed(this.this$1) == false) goto L9;
                return false;
            L9:
                if (LoginActivitySmsView.m15949$$Nest$fgettimeText(this.this$1) == null) goto L13;
                if (LoginActivitySmsView.m15949$$Nest$fgettimeText(this.this$1).getVisibility() == 8) goto L13;
                return false;
            L13:
                if (LoginActivitySmsView.m15939$$Nest$fgetisResendingCode(this.this$1) == true) goto L21;
                return true;
            L21:
                return false;
            }
        }

        /* renamed from: org.telegram.ui.LoginActivity$LoginActivitySmsView$6, reason: invalid class name */
        public class AnonymousClass6 implements TextWatcher {
            final /* synthetic */ LoginActivitySmsView this$1;

            public AnonymousClass6(LoginActivitySmsView r1) {
                this.this$1 = r1;
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable r1) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
                if (LoginActivitySmsView.m15945$$Nest$fgetpostedErrorColorTimeout(this.this$1) == false) goto L6;
                LoginActivitySmsView r12 = this.this$1;
                r12.removeCallbacks(LoginActivitySmsView.m15938$$Nest$fgeterrorColorTimeout(r12));
                LoginActivitySmsView.m15938$$Nest$fgeterrorColorTimeout(this.this$1).run();
                return;
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }
        }

        /* renamed from: org.telegram.ui.LoginActivity$LoginActivitySmsView$7, reason: invalid class name */
        public class AnonymousClass7 extends TimerTask {
            final /* synthetic */ LoginActivitySmsView this$1;

            /* renamed from: $r8$lambda$9rYhrQZtBo2b-M4mItwauZpLyLY, reason: not valid java name */
            public static /* synthetic */ void m15958$r8$lambda$9rYhrQZtBo2bM4mItwauZpLyLY(AnonymousClass7 r0) {
                r0.lambda$run$0();
            }

            public AnonymousClass7(LoginActivitySmsView r1) {
                this.this$1 = r1;
            }

            private /* synthetic */ void lambda$run$0() {
                double r0 = System.currentTimeMillis();
                double r2 = r0 - LoginActivitySmsView.m15940$$Nest$fgetlastCodeTime(this.this$1);
                LoginActivitySmsView.m15952$$Nest$fputlastCodeTime(this.this$1, r0);
                LoginActivitySmsView.m15951$$Nest$fputcodeTime(this.this$1, (int) (LoginActivitySmsView.m15936$$Nest$fgetcodeTime(r0) - r2));
                if (LoginActivitySmsView.m15936$$Nest$fgetcodeTime(this.this$1) > 1000) goto L9;
                LoginActivitySmsView.m15957$$Nest$msetProblemTextVisible(this.this$1, true);
                LoginActivitySmsView.m15949$$Nest$fgettimeText(this.this$1).setVisibility(8);
                if (LoginActivitySmsView.m15946$$Nest$fgetproblemText(this.this$1) == null) goto L7;
                LoginActivitySmsView.m15946$$Nest$fgetproblemText(this.this$1).setVisibility(0);
            L7:
                LoginActivitySmsView.m15955$$Nest$mdestroyCodeTimer(this.this$1);
                return;
            }

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivitySmsView$7$$ExternalSyntheticLambda0(this));
            }
        }

        /* renamed from: org.telegram.ui.LoginActivity$LoginActivitySmsView$8, reason: invalid class name */
        public class AnonymousClass8 extends TimerTask {
            final /* synthetic */ LoginActivitySmsView this$1;

            public static /* synthetic */ void $r8$lambda$ixhpaLLKr7cRwUDnzoMWWsHAb2k(AnonymousClass8 r0) {
                r0.lambda$run$0();
            }

            public AnonymousClass8(LoginActivitySmsView r1) {
                this.this$1 = r1;
            }

            private /* synthetic */ void lambda$run$0() {
                double r0 = System.currentTimeMillis();
                double r2 = r0 - LoginActivitySmsView.m15941$$Nest$fgetlastCurrentTime(this.this$1);
                LoginActivitySmsView.m15953$$Nest$fputlastCurrentTime(this.this$1, r0);
                LoginActivitySmsView.m15954$$Nest$fputtime(this.this$1, (int) (LoginActivitySmsView.m15948$$Nest$fgettime(r0) - r2));
                int r02 = LoginActivitySmsView.m15948$$Nest$fgettime(this.this$1);
                LoginActivitySmsView r1 = this.this$1;
                if (r02 < 1000) goto L30;
                int r03 = (LoginActivitySmsView.m15948$$Nest$fgettime(r1) / 1000) / 60;
                int r12 = (LoginActivitySmsView.m15948$$Nest$fgettime(this.this$1) / 1000) - (r03 * 60);
                if (LoginActivitySmsView.m15943$$Nest$fgetnextType(this.this$1) != 4) goto L7;
            L27:
                LoginActivitySmsView.m15949$$Nest$fgettimeText(this.this$1).setText(LocaleController.formatString("CallAvailableIn", org.telegram.messenger.R.string.CallAvailableIn, new Object[]{Integer.valueOf(r03), Integer.valueOf(r12)}));
            L28:
                LoginActivitySmsView.m15947$$Nest$fgetprogressView(this.this$1);
                return;
            L7:
                if (LoginActivitySmsView.m15943$$Nest$fgetnextType(this.this$1) == 3) goto L27;
                if (LoginActivitySmsView.m15943$$Nest$fgetnextType(this.this$1) == 11) goto L27;
                if (LoginActivitySmsView.m15937$$Nest$fgetcurrentType(this.this$1) != 2) goto L21;
                if (LoginActivitySmsView.m15943$$Nest$fgetnextType(this.this$1) != 2) goto L16;
            L19:
                LoginActivitySmsView.m15949$$Nest$fgettimeText(this.this$1).setText(LocaleController.formatString("ResendSmsAvailableIn", org.telegram.messenger.R.string.ResendSmsAvailableIn, new Object[]{Integer.valueOf(r03), Integer.valueOf(r12)}));
                goto L28
            L16:
                if (LoginActivitySmsView.m15943$$Nest$fgetnextType(this.this$1) == 17) goto L19;
                if (LoginActivitySmsView.m15943$$Nest$fgetnextType(this.this$1) == 16) goto L19;
            L21:
                if (LoginActivitySmsView.m15943$$Nest$fgetnextType(this.this$1) != 2) goto L23;
            L26:
                LoginActivitySmsView.m15949$$Nest$fgettimeText(this.this$1).setText(LocaleController.formatString("SmsAvailableIn", org.telegram.messenger.R.string.SmsAvailableIn, new Object[]{Integer.valueOf(r03), Integer.valueOf(r12)}));
                goto L28
            L23:
                if (LoginActivitySmsView.m15943$$Nest$fgetnextType(this.this$1) == 17) goto L26;
                if (LoginActivitySmsView.m15943$$Nest$fgetnextType(this.this$1) != 16) goto L28;
            L30:
                LoginActivitySmsView.m15956$$Nest$mdestroyTimer(r1);
                if (LoginActivitySmsView.m15943$$Nest$fgetnextType(this.this$1) != 3) goto L33;
            L44:
                int r04 = LoginActivitySmsView.m15943$$Nest$fgetnextType(this.this$1);
                LoginActivitySmsView r13 = this.this$1;
                if (r04 != 4) goto L48;
                LoginActivitySmsView.m15949$$Nest$fgettimeText(r13).setText(LocaleController.getString("RequestCallButton", org.telegram.messenger.R.string.RequestCallButton));
            L54:
                LoadingTextView r05 = LoginActivitySmsView.m15949$$Nest$fgettimeText(this.this$1);
                int r14 = Theme.key_chats_actionBackground;
                r05.setTextColor(Theme.getColor(r14));
                LoginActivitySmsView.m15949$$Nest$fgettimeText(this.this$1).setTag(org.telegram.messenger.R.id.color_key_tag, Integer.valueOf(r14));
                return;
            L48:
                if (LoginActivitySmsView.m15943$$Nest$fgetnextType(r13) != 11) goto L50;
            L53:
                LoginActivitySmsView.m15949$$Nest$fgettimeText(this.this$1).setText(LocaleController.getString(org.telegram.messenger.R.string.RequestMissedCall));
                goto L54
            L50:
                if (LoginActivitySmsView.m15943$$Nest$fgetnextType(this.this$1) == 3) goto L53;
                LoginActivitySmsView.m15949$$Nest$fgettimeText(this.this$1).setText(LocaleController.getString("RequestSmsButton", org.telegram.messenger.R.string.RequestSmsButton));
                goto L54
            L33:
                if (LoginActivitySmsView.m15943$$Nest$fgetnextType(this.this$1) == 4) goto L44;
                if (LoginActivitySmsView.m15943$$Nest$fgetnextType(this.this$1) == 2) goto L44;
                if (LoginActivitySmsView.m15943$$Nest$fgetnextType(this.this$1) == 17) goto L44;
                if (LoginActivitySmsView.m15943$$Nest$fgetnextType(this.this$1) == 16) goto L44;
                if (LoginActivitySmsView.m15943$$Nest$fgetnextType(this.this$1) == 11) goto L44;
            }

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (LoginActivitySmsView.m15950$$Nest$fgettimeTimer(this.this$1) != null) goto L5;
                return;
            L5:
                AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivitySmsView$8$$ExternalSyntheticLambda0(this));
            }
        }

        public static /* synthetic */ void $r8$lambda$3Xyp65ucV9jE2EeLRVpUo_yHALs(LoginActivitySmsView r0) {
            r0.lambda$tryShowProgress$15();
        }

        public static /* synthetic */ void $r8$lambda$4JCon9wxYAj4hWeGUv9AQRYHvLQ(LoginActivitySmsView r0, Context r1, View r2) {
            r0.lambda$new$9(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$4vWkQtf4H3Cfq9VJk4KdIbdaIwk(LoginActivitySmsView r0, Runnable r1) {
            r0.lambda$animateSuccess$39(r1);
        }

        public static /* synthetic */ void $r8$lambda$52DeFaYXxLV34a8iv3I32qkfJQM(LoginActivitySmsView r0, TLRPC.TL_auth_signIn r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$onNextPressed$37(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$5uCST0i3r35lEY2Qz_cCXianxNs(LoginActivitySmsView r0, View r1) {
            r0.lambda$new$6(r1);
        }

        public static /* synthetic */ void $r8$lambda$ATYof2zTHAED1vYiVyf1EZtZlQU(LoginActivitySmsView r0) {
            r0.lambda$tryShowProgress$14();
        }

        public static /* synthetic */ void $r8$lambda$D3eZrDEHGrxRvSunrJ5CdwvdzNE(LoginActivitySmsView r0, Context r1, AlertDialog r2, int r3) {
            r0.lambda$new$7(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$DNFgdGXgmqgdtzPFpZcYqYfiR1A(LoginActivitySmsView r0, TLRPC.TL_error r1, TLObject r2) {
            r0.lambda$onNextPressed$24(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$DRXgxQYvVyDdjZOV0FXr5kN9ZIM(LoginActivitySmsView r0) {
            r0.lambda$tryHideProgress$17();
        }

        public static /* synthetic */ void $r8$lambda$FlLvXEdcfr_4gzQ31Gg_t9Hj4A8(LoginActivitySmsView r0, Bundle r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$new$4(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$Ghr6jfb18qnAnvXrdT7JcXLVh7c(LoginActivitySmsView r0, TLRPC.TL_error r1, Bundle r2, TLObject r3) {
            r0.lambda$resendCode$10(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$GjNtscnZTaGjeeYr9ZBognF9o8g(LoginActivitySmsView r0, Bundle r1) {
            r0.lambda$onNextPressed$32(r1);
        }

        public static /* synthetic */ void $r8$lambda$GlHFhxXvFdAUIJ1e42ByhAMH1JM(LoginActivitySmsView r0) {
            r0.lambda$tryHideProgress$18();
        }

        public static /* synthetic */ void $r8$lambda$IyRGaPGIAeMJ0l8QMaf9WDZ0KI8(LoginActivitySmsView r0, int r1, boolean r2) {
            r0.lambda$tryShowProgress$12(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$J89Lv8OERjD_mRxcHzyxJUwCAHA(LoginActivitySmsView r0) {
            r0.lambda$shakeWrongCode$40();
        }

        /* renamed from: $r8$lambda$LXTmYlCi56hr25pqz4X-2uBDc9A, reason: not valid java name */
        public static /* synthetic */ void m15927$r8$lambda$LXTmYlCi56hr25pqz4X2uBDc9A(LoginActivitySmsView r0, Bundle r1, TLObject r2) {
            r0.lambda$new$2(r1, r2);
        }

        /* renamed from: $r8$lambda$MN-uWUxJVKVOcpHLEJIYlY8Cifg, reason: not valid java name */
        public static /* synthetic */ void m15928$r8$lambda$MNuWUxJVKVOcpHLEJIYlY8Cifg(LoginActivitySmsView r0, AlertDialog r1, int r2) {
            r0.lambda$new$8(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$Oph6KOQ39wvtd1ScisTul2yI4Pk(LoginActivitySmsView r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$onNextPressed$25(r1, r2);
        }

        /* renamed from: $r8$lambda$S1Ck0Tr0CAS2ZqFQkg-EqpVZ_Bw, reason: not valid java name */
        public static /* synthetic */ void m15929$r8$lambda$S1Ck0Tr0CAS2ZqFQkgEqpVZ_Bw() {
            CallReceiver.checkLastReceivedCall();
        }

        public static /* synthetic */ void $r8$lambda$SbVNKwJh3BRwkF1eWRTr22DQygA(LoginActivitySmsView r0, int r1, boolean r2) {
            r0.lambda$tryShowProgress$13(r1, r2);
        }

        /* renamed from: $r8$lambda$SromzoXQEhgmipL-ENpal12-3Zs, reason: not valid java name */
        public static /* synthetic */ void m15930$r8$lambda$SromzoXQEhgmipLENpal123Zs(LoginActivitySmsView r0, DialogInterface r1) {
            r0.lambda$onNextPressed$22(r1);
        }

        /* renamed from: $r8$lambda$T6YwBA3EV5-wBD9HI7k2EAaduzA, reason: not valid java name */
        public static /* synthetic */ void m15931$r8$lambda$T6YwBA3EV5wBD9HI7k2EAaduzA(LoginActivitySmsView r0) {
            r0.lambda$onNextPressed$23();
        }

        /* renamed from: $r8$lambda$U1B4i9hIC0g1Iz-kfSTMTOYqriw, reason: not valid java name */
        public static /* synthetic */ void m15932$r8$lambda$U1B4i9hIC0g1IzkfSTMTOYqriw(LoginActivitySmsView r0, View r1) {
            r0.lambda$new$5(r1);
        }

        public static /* synthetic */ void $r8$lambda$UTT_O8hA0LvvxtMBX9nBhSYMxlc(LoginActivitySmsView r0) {
            r0.lambda$tryHideProgress$16();
        }

        public static /* synthetic */ void $r8$lambda$UY9kxYEiJuAGRQbt45xrwEx3B_E(LoginActivitySmsView r0) {
            r0.lambda$shakeWrongCode$41();
        }

        public static /* synthetic */ void $r8$lambda$XP7D3DhREepyTXZUksWlKbwwKIo(LoginActivitySmsView r0, Bundle r1) {
            r0.lambda$onNextPressed$30(r1);
        }

        /* renamed from: $r8$lambda$ZjHaXiS8yHRSqqUnt08Rjv-MPFs, reason: not valid java name */
        public static /* synthetic */ void m15933$r8$lambda$ZjHaXiS8yHRSqqUnt08RjvMPFs(LoginActivitySmsView r0, TLRPC.TL_error r1, TL_account.confirmPhone r2) {
            r0.lambda$onNextPressed$28(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$ZwisHwNPNz3ktROxBpRORqczQZQ(LoginActivitySmsView r0, TLObject r1) {
            r0.lambda$onNextPressed$31(r1);
        }

        public static /* synthetic */ void $r8$lambda$aIaaslJtdVCVRnJ7sVf8arsJ5vU(LoginActivitySmsView r0, AlertDialog r1, int r2) {
            r0.lambda$onBackPressed$42(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$bg7_VGc10uhNSybSgqbzPlkCk0c(LoginActivitySmsView r0, int r1) {
            r0.lambda$animateSuccess$38(r1);
        }

        public static /* synthetic */ void $r8$lambda$cv7o9vQ3q44FR8bHaeyYPBm1Lrs(LoginActivitySmsView r0, TLRPC.TL_auth_signIn r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$onNextPressed$34(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$dG5jD8K8iO3B13fHMsej4tJzYOM(LoginActivitySmsView r0, DialogInterface r1) {
            r0.lambda$onNextPressed$26(r1);
        }

        public static /* synthetic */ void $r8$lambda$gKyVJVXs5FXqCJwgMxzY5MPqX6U(TLObject r0, TLRPC.TL_error r1) {
        }

        /* renamed from: $r8$lambda$gxf-xokGCci4-LMhiZX8ka9dK80, reason: not valid java name */
        public static /* synthetic */ void m15934$r8$lambda$gxfxokGCci4LMhiZX8ka9dK80(LoginActivitySmsView r0, Activity r1) {
            r0.lambda$onNextPressed$27(r1);
        }

        public static /* synthetic */ void $r8$lambda$jIMuEkxeFf5BTa8T63IZmQ7EJ8Y(LoginActivitySmsView r0, TL_account.confirmPhone r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$onNextPressed$29(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$lxfPWKZkitAmX6HCEmiamvO94YU(LoginActivitySmsView r0) {
            r0.lambda$onShow$44();
        }

        public static /* synthetic */ void $r8$lambda$oJyQlGZDOOJ9CdJfsxDVziLUxBI(LoginActivitySmsView r0, View r1) {
            r0.lambda$new$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$oeHQ3NtJHqvmxmdnFi5ZFxjl5dc(LoginActivitySmsView r0, View r1, boolean r2) {
            r0.lambda$setParams$21(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$soAlzppPzfFNHahRuAu5wYU04Lg(LoginActivitySmsView r0, Bundle r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$resendCode$11(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$sqkKk61DZpbe1_Pj2vHvZt3Qj8I(LoginActivitySmsView r0, TLRPC.TL_error r1, TLObject r2, TLRPC.TL_auth_signIn r3) {
            r0.lambda$onNextPressed$36(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$svUv2hNG_CXh0XAMTrUU00FEAh0(LoginActivitySmsView r0, TLRPC.TL_error r1) {
            r0.lambda$new$3(r1);
        }

        public static /* synthetic */ void $r8$lambda$tgas35wCGHO6JgGQEjmsSnueCQI(LoginActivitySmsView r0) {
            r0.lambda$new$0();
        }

        public static /* synthetic */ void $r8$lambda$vCMpKtLixg89cTHNXcK6ofSHhqc() {
            CallReceiver.checkLastReceivedCall();
        }

        /* renamed from: $r8$lambda$xUxaWIcbK50gpGE-FjSzrn1rcVE, reason: not valid java name */
        public static /* synthetic */ void m15935$r8$lambda$xUxaWIcbK50gpGEFjSzrn1rcVE(LoginActivitySmsView r0) {
            r0.lambda$tryHideProgress$19();
        }

        public static /* synthetic */ void $r8$lambda$zt7Mw2JofhVcsXPp4bp0E1T2Q_g(LoginActivitySmsView r0, TLRPC.TL_error r1, TLObject r2, TLRPC.TL_auth_signIn r3) {
            r0.lambda$onNextPressed$33(r1, r2, r3);
        }

        /* renamed from: -$$Nest$fgetcodeTime, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m15936$$Nest$fgetcodeTime(LoginActivitySmsView r0) {
            return r0.codeTime;
        }

        /* renamed from: -$$Nest$fgetcurrentType, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m15937$$Nest$fgetcurrentType(LoginActivitySmsView r0) {
            return r0.currentType;
        }

        /* renamed from: -$$Nest$fgeterrorColorTimeout, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Runnable m15938$$Nest$fgeterrorColorTimeout(LoginActivitySmsView r0) {
            return r0.errorColorTimeout;
        }

        /* renamed from: -$$Nest$fgetisResendingCode, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m15939$$Nest$fgetisResendingCode(LoginActivitySmsView r0) {
            return r0.isResendingCode;
        }

        /* renamed from: -$$Nest$fgetlastCodeTime, reason: not valid java name */
        public static /* bridge */ /* synthetic */ double m15940$$Nest$fgetlastCodeTime(LoginActivitySmsView r2) {
            return r2.lastCodeTime;
        }

        /* renamed from: -$$Nest$fgetlastCurrentTime, reason: not valid java name */
        public static /* bridge */ /* synthetic */ double m15941$$Nest$fgetlastCurrentTime(LoginActivitySmsView r2) {
            return r2.lastCurrentTime;
        }

        /* renamed from: -$$Nest$fgetnextPressed, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m15942$$Nest$fgetnextPressed(LoginActivitySmsView r0) {
            return r0.nextPressed;
        }

        /* renamed from: -$$Nest$fgetnextType, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m15943$$Nest$fgetnextType(LoginActivitySmsView r0) {
            return r0.nextType;
        }

        /* renamed from: -$$Nest$fgetopenTime, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m15944$$Nest$fgetopenTime(LoginActivitySmsView r0) {
            return r0.openTime;
        }

        /* renamed from: -$$Nest$fgetpostedErrorColorTimeout, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m15945$$Nest$fgetpostedErrorColorTimeout(LoginActivitySmsView r0) {
            return r0.postedErrorColorTimeout;
        }

        /* renamed from: -$$Nest$fgetproblemText, reason: not valid java name */
        public static /* bridge */ /* synthetic */ LoadingTextView m15946$$Nest$fgetproblemText(LoginActivitySmsView r0) {
            return r0.problemText;
        }

        /* renamed from: -$$Nest$fgetprogressView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ProgressView m15947$$Nest$fgetprogressView(LoginActivitySmsView r0) {
            r0.getClass();
            return null;
        }

        /* renamed from: -$$Nest$fgettime, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m15948$$Nest$fgettime(LoginActivitySmsView r0) {
            return r0.time;
        }

        /* renamed from: -$$Nest$fgettimeText, reason: not valid java name */
        public static /* bridge */ /* synthetic */ LoadingTextView m15949$$Nest$fgettimeText(LoginActivitySmsView r0) {
            return r0.timeText;
        }

        /* renamed from: -$$Nest$fgettimeTimer, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Timer m15950$$Nest$fgettimeTimer(LoginActivitySmsView r0) {
            return r0.timeTimer;
        }

        /* renamed from: -$$Nest$fputcodeTime, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15951$$Nest$fputcodeTime(LoginActivitySmsView r0, int r1) {
            r0.codeTime = r1;
        }

        /* renamed from: -$$Nest$fputlastCodeTime, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15952$$Nest$fputlastCodeTime(LoginActivitySmsView r0, double r1) {
            r0.lastCodeTime = r1;
        }

        /* renamed from: -$$Nest$fputlastCurrentTime, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15953$$Nest$fputlastCurrentTime(LoginActivitySmsView r0, double r1) {
            r0.lastCurrentTime = r1;
        }

        /* renamed from: -$$Nest$fputtime, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15954$$Nest$fputtime(LoginActivitySmsView r0, int r1) {
            r0.time = r1;
        }

        /* renamed from: -$$Nest$mdestroyCodeTimer, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15955$$Nest$mdestroyCodeTimer(LoginActivitySmsView r0) {
            r0.destroyCodeTimer();
        }

        /* renamed from: -$$Nest$mdestroyTimer, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15956$$Nest$mdestroyTimer(LoginActivitySmsView r0) {
            r0.destroyTimer();
        }

        /* renamed from: -$$Nest$msetProblemTextVisible, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15957$$Nest$msetProblemTextVisible(LoginActivitySmsView r0, boolean r1) {
            r0.setProblemTextVisible(r1);
        }

        public LoginActivitySmsView(LoginActivity r31, Context r32, int r33) {
            this.this$0 = r31;
            super(r32);
            this.timerSync = new Object();
            this.time = 60000;
            this.codeTime = 15000;
            this.lastError = _UrlKt.FRAGMENT_ENCODE_SET;
            this.isResendingCode = false;
            this.pattern = "*";
            this.prefix = _UrlKt.FRAGMENT_ENCODE_SET;
            this.errorColorTimeout = new LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda1(this);
            this.currentType = r33;
            setOrientation(1);
            TextView r5 = new TextView(r32);
            this.confirmTextView = r5;
            r5.setTextSize(1, 14.0f);
            this.confirmTextView.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            TextView r52 = new TextView(r32);
            this.titleTextView = r52;
            r52.setTextSize(1, 18.0f);
            this.titleTextView.setTypeface(AndroidUtilities.bold());
            TextView r53 = this.titleTextView;
            if (LocaleController.isRTL == false) goto L5;
            int r8 = 5;
        L6:
            r53.setGravity(r8);
            this.titleTextView.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            this.titleTextView.setGravity(49);
            if (LoginActivity.m15776$$Nest$fgetactivityMode(r31) == 1) goto L9;
            String r54 = null;
        L10:
            int r12 = this.currentType;
            if (r12 != 11) goto L17;
            TextView r122 = this.titleTextView;
            if (r54 != null) goto L16;
            r54 = LocaleController.getString(org.telegram.messenger.R.string.MissedCallDescriptionTitle);
        L16:
            r122.setText(r54);
            FrameLayout r55 = new FrameLayout(r32);
            this.missedCallArrowIcon = new ImageView(r32);
            this.missedCallPhoneIcon = new ImageView(r32);
            r55.addView(this.missedCallArrowIcon);
            r55.addView(this.missedCallPhoneIcon);
            this.missedCallArrowIcon.setImageResource(org.telegram.messenger.R.drawable.login_arrow1);
            this.missedCallPhoneIcon.setImageResource(org.telegram.messenger.R.drawable.login_phone1);
            addView(r55, LayoutHelper.createLinear(64, 64, 1, 0, 16, 0, 0));
            addView(this.titleTextView, LayoutHelper.createLinear(-2, -2, 49, 0, 8, 0, 0));
            TextView r56 = new TextView(r32);
            this.missedCallDescriptionSubtitle = r56;
            r56.setTextSize(1, 14.0f);
            this.missedCallDescriptionSubtitle.setGravity(1);
            this.missedCallDescriptionSubtitle.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            this.missedCallDescriptionSubtitle.setText(AndroidUtilities.replaceTags(LocaleController.getString(org.telegram.messenger.R.string.MissedCallDescriptionSubtitle)));
            addView(this.missedCallDescriptionSubtitle, LayoutHelper.createLinear(-1, -2, 49, 36, 16, 36, 0));
            this.codeFieldContainer = new AnonymousClass1(this, r32, r31);
            LinearLayout r57 = new LinearLayout(r32);
            r57.setOrientation(0);
            TextView r123 = new TextView(r32);
            this.prefixTextView = r123;
            r123.setTextSize(1, 20.0f);
            this.prefixTextView.setMaxLines(1);
            this.prefixTextView.setTypeface(AndroidUtilities.bold());
            this.prefixTextView.setPadding(0, 0, 0, 0);
            this.prefixTextView.setGravity(16);
            r57.addView(this.prefixTextView, LayoutHelper.createLinear(-2, -1, 16, 0, 0, 4, 0));
            r57.addView(this.codeFieldContainer, LayoutHelper.createLinear(-2, -1));
            addView(r57, LayoutHelper.createLinear(-2, 34, 1, 0, 28, 0, 0));
            TextView r58 = new TextView(r32);
            this.missedCallDescriptionSubtitle2 = r58;
            r58.setTextSize(1, 14.0f);
            this.missedCallDescriptionSubtitle2.setGravity(1);
            this.missedCallDescriptionSubtitle2.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            this.missedCallDescriptionSubtitle2.setText(AndroidUtilities.replaceTags(LocaleController.getString(org.telegram.messenger.R.string.MissedCallDescriptionSubtitle2)));
            addView(this.missedCallDescriptionSubtitle2, LayoutHelper.createLinear(-1, -2, 49, 36, 28, 36, 12));
        L59:
            FrameLayout r11 = null;
        L61:
            if (this.currentType == 11) goto L64;
            AnonymousClass2 r4 = new AnonymousClass2(this, r32, r31);
            this.codeFieldContainer = r4;
            addView(r4, LayoutHelper.createLinear(-2, 42, 1, 0, 32, 0, 0));
        L64:
            if (this.currentType != 3) goto L66;
            this.codeFieldContainer.setVisibility(8);
        L66:
            LoadingTextView r42 = new LoadingTextView(r31, r32);
            this.prevTypeTextView = r42;
            int r7 = Theme.key_windowBackgroundWhiteValueText;
            r42.setLinkTextColor(Theme.getColor(r7));
            this.prevTypeTextView.setTextColor(r31.getThemedColor(r7));
            this.prevTypeTextView.setTextSize(1, 14.0f);
            this.prevTypeTextView.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            this.prevTypeTextView.setPadding(AndroidUtilities.dp(14.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(14.0f), AndroidUtilities.dp(16.0f));
            this.prevTypeTextView.setOnClickListener(new LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda2(this));
            addView(this.prevTypeTextView, LayoutHelper.createLinear(-2, -2, 1, 0, 18, 0, 0));
            this.prevTypeTextView.setVisibility(8);
            this.problemFrame = new FrameLayout(r32);
            AnonymousClass3 r43 = new AnonymousClass3(this, r32, r31);
            this.timeText = r43;
            r43.setLinkTextColor(Theme.getColor(r7));
            this.timeText.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            this.timeText.setPadding(AndroidUtilities.dp(14.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(14.0f), AndroidUtilities.dp(16.0f));
            this.timeText.setTextSize(1, 15.0f);
            this.timeText.setGravity(51);
            this.timeText.setOnClickListener(new LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda3(this));
            this.problemFrame.addView(this.timeText, LayoutHelper.createFrame(-2, -2, 49));
            this.errorViewSwitcher = new AnonymousClass4(this, r32, r31);
            if (this.currentType == 15) goto L69;
            Animation r44 = AnimationUtils.loadAnimation(r32, org.telegram.messenger.R.anim.text_in);
            Interpolator r72 = Easings.easeInOutQuad;
            r44.setInterpolator(r72);
            this.errorViewSwitcher.setInAnimation(r44);
            Animation r45 = AnimationUtils.loadAnimation(r32, org.telegram.messenger.R.anim.text_out);
            r45.setInterpolator(r72);
            this.errorViewSwitcher.setOutAnimation(r45);
            AnonymousClass5 r46 = new AnonymousClass5(this, r32, r31);
            this.problemText = r46;
            r46.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            this.problemText.setTextSize(1, 15.0f);
            this.problemText.setGravity(49);
            this.problemText.setPadding(AndroidUtilities.dp(14.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(14.0f), AndroidUtilities.dp(16.0f));
            this.problemFrame.addView(this.problemText, LayoutHelper.createFrame(-1, -2, 17));
            this.errorViewSwitcher.addView(this.problemFrame, LayoutHelper.createFrame(-2, -2, 17));
        L70:
            TextView r1 = new TextView(r32);
            this.wrongCode = r1;
            r1.setText(LocaleController.getString(org.telegram.messenger.R.string.WrongCode));
            this.wrongCode.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            this.wrongCode.setTextSize(1, 15.0f);
            this.wrongCode.setGravity(49);
            this.wrongCode.setPadding(0, AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f));
            this.errorViewSwitcher.addView(this.wrongCode, LayoutHelper.createFrame(-2, -2, 17));
            if (r11 != null) goto L77;
            FrameLayout r13 = new FrameLayout(r32);
            this.bottomContainer = r13;
            ViewSwitcher r3 = this.errorViewSwitcher;
            if (this.currentType != 15) goto L75;
            int r112 = -1;
        L76:
            r13.addView(r3, LayoutHelper.createFrame(r112, -2.0f, 81, 0.0f, 0.0f, 0.0f, 32.0f));
            addView(this.bottomContainer, LayoutHelper.createLinear(-1, 0, 1.0f));
        L78:
            VerticalPositionAutoAnimator.attach(this.errorViewSwitcher);
            if (this.currentType == 15) goto L82;
            this.problemText.setOnClickListener(new LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda5(this, r32));
            return;
        L82:
            return;
        L75:
            r112 = -2;
            goto L76
        L77:
            r11.addView(this.errorViewSwitcher, LayoutHelper.createFrame(-2, -2.0f, 81, 0.0f, 0.0f, 0.0f, 32.0f));
            goto L78
        L69:
            Animation r14 = AnimationUtils.loadAnimation(r32, org.telegram.messenger.R.anim.scale_in);
            CubicBezierInterpolator r47 = CubicBezierInterpolator.DEFAULT;
            r14.setInterpolator(r47);
            this.errorViewSwitcher.setInAnimation(r14);
            Animation r15 = AnimationUtils.loadAnimation(r32, org.telegram.messenger.R.anim.scale_out);
            r15.setInterpolator(r47);
            this.errorViewSwitcher.setOutAnimation(r15);
            LinearLayout r16 = new LinearLayout(r32);
            this.openFragmentButton = r16;
            r16.setOrientation(0);
            this.openFragmentButton.setGravity(17);
            this.openFragmentButton.setPadding(AndroidUtilities.dp(34.0f), 0, AndroidUtilities.dp(34.0f), 0);
            this.openFragmentButton.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(6.0f), Theme.getColor(Theme.key_changephoneinfo_image2), Theme.getColor(Theme.key_chats_actionPressedBackground)));
            this.openFragmentButton.setOnClickListener(new LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda4(this));
            this.errorViewSwitcher.addView(this.openFragmentButton, LayoutHelper.createFrame(-1, 52.0f));
            RLottieImageView r17 = new RLottieImageView(r32);
            this.openFragmentImageView = r17;
            r17.setAnimation(org.telegram.messenger.R.raw.fragment, 36, 36);
            this.openFragmentButton.addView(this.openFragmentImageView, LayoutHelper.createLinear(36, 36, 16, 0, 0, 2, 0));
            TextView r18 = new TextView(r32);
            this.openFragmentButtonText = r18;
            r18.setText(LocaleController.getString(org.telegram.messenger.R.string.OpenFragment));
            this.openFragmentButtonText.setTextColor(-1);
            this.openFragmentButtonText.setTextSize(1, 15.0f);
            this.openFragmentButtonText.setGravity(17);
            this.openFragmentButtonText.setTypeface(AndroidUtilities.bold());
            this.openFragmentButton.addView(this.openFragmentButtonText);
            goto L70
        L17:
            TextView r142 = this.confirmTextView;
            int r132 = 64;
            if (r12 != 3) goto L28;
            r142.setGravity(1);
            FrameLayout r124 = new FrameLayout(r32);
            addView(r124, LayoutHelper.createLinear(-1, 0, 1.0f));
            LinearLayout r143 = new LinearLayout(r32);
            r143.setOrientation(1);
            r143.setGravity(1);
            r124.addView(r143, LayoutHelper.createFrame(-1, -2, 17));
            FrameLayout.LayoutParams r48 = (FrameLayout.LayoutParams) r143.getLayoutParams();
            if (AndroidUtilities.isTablet() == false) goto L22;
            int r73 = 0;
        L23:
            r48.bottomMargin = r73;
            FrameLayout r49 = new FrameLayout(r32);
            r143.addView(r49, LayoutHelper.createFrame(-2, -2, 1));
            this.blueImageView = new RLottieImageView(r32);
            RLottieDrawable r21 = new RLottieDrawable(org.telegram.messenger.R.raw.phone_flash_call, AndroidUtilities.dp(64.0f), AndroidUtilities.dp(64.0f), true, null);
            this.hintDrawable = r21;
            this.blueImageView.setAnimation(r21);
            r49.addView(this.blueImageView, LayoutHelper.createFrame(64, 64.0f));
            TextView r410 = this.titleTextView;
            if (r54 != null) goto L27;
            r54 = LocaleController.getString(org.telegram.messenger.R.string.YourCode);
        L27:
            r410.setText(r54);
            r143.addView(this.titleTextView, LayoutHelper.createLinear(-2, -2, 1, 0, 16, 0, 0));
            r143.addView(this.confirmTextView, LayoutHelper.createLinear(-2, -2, 1, 0, 8, 0, 0));
            r11 = r124;
            goto L61
        L22:
            r73 = AndroidUtilities.statusBarHeight;
            goto L23
        L28:
            r142.setGravity(49);
            FrameLayout r411 = new FrameLayout(r32);
            addView(r411, LayoutHelper.createLinear(-2, -2, 49, 0, 16, 0, 0));
            int r74 = this.currentType;
            if (r74 != 1) goto L31;
            r132 = 128;
        L31:
            if (r74 != 1) goto L33;
            float r75 = r132;
            this.hintDrawable = new RLottieDrawable(org.telegram.messenger.R.raw.code_laptop, AndroidUtilities.dp(r75), AndroidUtilities.dp(r75), true, null);
        L34:
            RLottieImageView r76 = new RLottieImageView(r32);
            this.blueImageView = r76;
            r76.setAnimation(this.hintDrawable);
            if (this.currentType == 1) goto L37;
        L39:
            RLottieImageView r77 = this.blueImageView;
            float r113 = r132;
            if (this.currentType == 1) goto L42;
        L45:
            float r125 = 0.0f;
        L46:
            r411.addView(r77, LayoutHelper.createFrame(r132, r113, 51, 0.0f, 0.0f, 0.0f, r125));
            TextView r412 = this.titleTextView;
            if (r54 == null) goto L50;
        L54:
            r412.setText(r54);
            addView(this.titleTextView, LayoutHelper.createLinear(-2, -2, 49, 0, 18, 0, 0));
            if (this.currentType != 15) goto L57;
            int r26 = 16;
        L58:
            addView(this.confirmTextView, LayoutHelper.createLinear(-2, -2, 49, r26, 17, r26, 0));
            goto L59
        L57:
            r26 = 0;
            goto L58
        L50:
            if (this.currentType != 1) goto L52;
            int r59 = org.telegram.messenger.R.string.SentAppCodeTitle;
        L53:
            r54 = LocaleController.getString(r59);
            goto L54
        L52:
            r59 = org.telegram.messenger.R.string.SentSmsCodeTitle;
            goto L53
        L42:
            if (AndroidUtilities.isSmallScreen() == true) goto L45;
            r125 = -AndroidUtilities.dp(16.0f);
            goto L46
        L37:
            if (AndroidUtilities.isSmallScreen() == true) goto L39;
            this.blueImageView.setTranslationY(-AndroidUtilities.dp(24.0f));
            goto L39
        L33:
            float r78 = r132;
            this.hintDrawable = new RLottieDrawable(org.telegram.messenger.R.raw.sms_incoming_info, AndroidUtilities.dp(r78), AndroidUtilities.dp(r78), true, null);
            this.starsToDotsDrawable = new RLottieDrawable(org.telegram.messenger.R.raw.phone_stars_to_dots, AndroidUtilities.dp(r78), AndroidUtilities.dp(r78), true, null);
            this.dotsDrawable = new RLottieDrawable(org.telegram.messenger.R.raw.phone_dots, AndroidUtilities.dp(r78), AndroidUtilities.dp(r78), true, null);
            this.dotsToStarsDrawable = new RLottieDrawable(org.telegram.messenger.R.raw.phone_dots_to_stars, AndroidUtilities.dp(r78), AndroidUtilities.dp(r78), true, null);
            goto L34
        L9:
            r54 = LocaleController.getString(org.telegram.messenger.R.string.CancelAccountReset);
            goto L10
        L5:
            r8 = 3;
            goto L6
        }

        private void animateSuccess(Runnable r8) {
            if (this.currentType != 3) goto L6;
            r8.run();
            return;
        L6:
            int r0 = 0;
        L7:
            CodeFieldContainer r1 = this.codeFieldContainer;
            if (r0 >= r1.codeField.length) goto L10;
            r1.postDelayed(new LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda38(this, r0), r0 * 75);
            r0 = r0 + 1;
            goto L7
        L10:
            r1.postDelayed(new LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda39(this, r8), (this.codeFieldContainer.codeField.length * 75) + 400);
        }

        private void applyLottieColors(RLottieDrawable r3) {
            if (r3 == null) goto L5;
            r3.setLayerColor("Bubble", Theme.getColor(Theme.key_chats_actionBackground));
            int r2 = Theme.key_windowBackgroundWhiteBlackText;
            r3.setLayerColor("Phone", Theme.getColor(r2));
            r3.setLayerColor("Note", Theme.getColor(r2));
            return;
        }

        private void createCodeTimer() {
            if (this.codeTimer == null) goto L5;
            return;
        L5:
            this.codeTime = 15000;
            int r1 = this.time;
            if (r1 <= 15000) goto L8;
            this.codeTime = r1;
        L8:
            this.codeTimer = new Timer();
            this.lastCodeTime = System.currentTimeMillis();
            this.codeTimer.schedule(new AnonymousClass7(this), 0, 1000);
        }

        private void createTimer() {
            if (this.timeTimer == null) goto L5;
            return;
        L5:
            LoadingTextView r0 = this.timeText;
            int r1 = Theme.key_windowBackgroundWhiteGrayText6;
            r0.setTextColor(Theme.getColor(r1));
            this.timeText.setTag(org.telegram.messenger.R.id.color_key_tag, Integer.valueOf(r1));
            Timer r3 = new Timer();
            this.timeTimer = r3;
            r3.schedule(new AnonymousClass8(this), 0, 1000);
        }

        private void destroyCodeTimer() {
            Object r0 = this.timerSync;     // Catch: Exception -> L13
            monitor-enter(r0);     // Catch: Exception -> L13
            Timer r1 = this.codeTimer;     // Catch: Throwable -> L7
            if (r1 == null) goto L9;
            r1.cancel();     // Catch: Throwable -> L7
            this.codeTimer = null;     // Catch: Throwable -> L7
        L9:
            monitor-exit(r0);     // Catch: Throwable -> L7
            return;
        L7:
            th = move-exception;
            throw th;     // Catch: Exception -> L13
        L13:
            e = move-exception;
            FileLog.e(e);
        }

        private void destroyTimer() {
            LoadingTextView r0 = this.timeText;
            int r1 = Theme.key_windowBackgroundWhiteGrayText6;
            r0.setTextColor(Theme.getColor(r1));
            this.timeText.setTag(org.telegram.messenger.R.id.color_key_tag, Integer.valueOf(r1));
            Object r02 = this.timerSync;     // Catch: Exception -> L14
            monitor-enter(r02);     // Catch: Exception -> L14
            Timer r12 = this.timeTimer;     // Catch: Throwable -> L8
            if (r12 == null) goto L10;
            r12.cancel();     // Catch: Throwable -> L8
            this.timeTimer = null;     // Catch: Throwable -> L8
        L10:
            monitor-exit(r02);     // Catch: Throwable -> L8
            return;
        L8:
            th = move-exception;
            throw th;     // Catch: Exception -> L14
        L14:
            e = move-exception;
            FileLog.e(e);
        }

        private /* synthetic */ void lambda$animateSuccess$38(int r1) {
            this.codeFieldContainer.codeField[r1].animateSuccessProgress(1.0f);
        }

        private /* synthetic */ void lambda$animateSuccess$39(Runnable r5) {
            int r1 = 0;
        L3:
            CodeNumberField[] r2 = this.codeFieldContainer.codeField;
            if (r1 >= r2.length) goto L6;
            r2[r1].animateSuccessProgress(0.0f);
            r1 = r1 + 1;
            goto L3
        L6:
            r5.run();
            this.codeFieldContainer.isFocusSuppressed = false;
        }

        private /* synthetic */ void lambda$new$0() {
            int r0 = 0;
            this.postedErrorColorTimeout = false;
        L3:
            CodeNumberField[] r1 = this.codeFieldContainer.codeField;
            if (r0 >= r1.length) goto L7;
            r1[r0].animateErrorProgress(0.0f);
            r0 = r0 + 1;
            goto L3
        L7:
            if (this.currentType != 15) goto L9;
            View r02 = this.openFragmentButton;
        L11:
            if (this.errorViewSwitcher.getCurrentView() == r02) goto L15;
            this.errorViewSwitcher.showNext();
            return;
        L15:
            return;
        L9:
            r02 = this.problemFrame;
            goto L11
        }

        private /* synthetic */ void lambda$new$1(View r1) {
            onBackPressed(true);
        }

        private /* synthetic */ void lambda$new$2(Bundle r3, TLObject r4) {
            this.nextCodeParams = r3;
            TLRPC.TL_auth_sentCode r42 = (TLRPC.TL_auth_sentCode) r4;
            this.nextCodeAuth = r42;
            TLRPC.auth_SentCodeType r0 = r42.type;
            if ((r0 instanceof TLRPC.TL_auth_sentCodeTypeSmsPhrase) == false) goto L6;
            this.nextType = 17;
        L8:
            LoginActivity.m15820$$Nest$mfillNextCodeParams(this.this$0, r3, r42);
            return;
        L6:
            if ((r0 instanceof TLRPC.TL_auth_sentCodeTypeSmsWord) == false) goto L8;
            this.nextType = 16;
            goto L8
        }

        private /* synthetic */ void lambda$new$3(TLRPC.TL_error r1) {
            this.lastError = r1.text;
        }

        private /* synthetic */ void lambda$new$4(Bundle r1, TLObject r2, TLRPC.TL_error r3) {
            if (r2 == null) goto L5;
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda25(this, r1, r2));
            return;
        L5:
            if (r3 != null) goto L7;
            return;
        L7:
            if (r3.text == null) goto L11;
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda26(this, r3));
            return;
        }

        private /* synthetic */ void lambda$new$5(View r4) {
            if (this.time > 0) goto L5;
        L7:
            this.isResendingCode = true;
            this.timeText.invalidate();
            this.timeText.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteValueText));
            int r42 = this.nextType;
            if (r42 != 4) goto L10;
        L23:
            if (r42 == 4) goto L27;
            if (r42 == 11) goto L27;
            this.timeText.setText(LocaleController.getString(org.telegram.messenger.R.string.SendingSms));
        L28:
            Bundle r43 = new Bundle();
            r43.putString("phone", this.phone);
            r43.putString("ephone", this.emailPhone);
            r43.putString("phoneFormated", this.requestPhone);
            r43.putInt("prevType", this.currentType);
            createCodeTimer();
            TLRPC.TL_auth_resendCode r0 = new TLRPC.TL_auth_resendCode();
            r0.phone_number = this.requestPhone;
            r0.phone_code_hash = this.phoneHash;
            ConnectionsManager.getInstance(LoginActivity.access$1900(this.this$0)).sendRequest(r0, new LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda19(this, r43), 10);
            return;
        L27:
            this.timeText.setText(LocaleController.getString(org.telegram.messenger.R.string.Calling));
            goto L28
        L10:
            if (r42 == 2) goto L23;
            if (r42 == 17) goto L23;
            if (r42 == 16) goto L23;
            if (r42 == 11) goto L23;
            if (r42 == 15) goto L23;
            if (r42 != 3) goto L30;
            AndroidUtilities.setWaitingForSms(false);
            NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.didReceiveSmsCode);
            this.waitingForEvent = false;
            destroyCodeTimer();
            this.isResendingCode = false;
            resendCode();
            return;
        L30:
            return;
        L5:
            if (this.timeTimer == null) goto L7;
        }

        private /* synthetic */ void lambda$new$6(View r3) {
            getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.url)));     // Catch: Exception -> L4
            return;
        L4:
            e = move-exception;
            FileLog.e(e);
        }

        private /* synthetic */ void lambda$new$7(Context r12, AlertDialog r13, int r14) {
            PackageInfo r0 = ApplicationLoader.applicationContext.getPackageManager().getPackageInfo(ApplicationLoader.applicationContext.getPackageName(), 0);     // Catch: Exception -> L66
            String r1 = String.format(Locale.US, "%s (%d)", new Object[]{r0.versionName, Integer.valueOf(r0.versionCode)});     // Catch: Exception -> L66
            Intent r3 = new Intent("android.intent.action.SENDTO");     // Catch: Exception -> L66
            r3.setData(Uri.parse("mailto:"));     // Catch: Exception -> L66
            r3.putExtra("android.intent.extra.EMAIL", new String[]{"sms@telegram.org"});     // Catch: Exception -> L66
            StringBuilder r5 = new StringBuilder();     // Catch: Exception -> L66
            r5.append(this.emailPhone);     // Catch: Exception -> L66
            r5.append(" Android Registration/Login Issue ");     // Catch: Exception -> L66
            r5.append(r1);     // Catch: Exception -> L66
            if (LoginActivity.m15796$$Nest$fgetpaid(this.this$0) == false) goto L6;
            String r6 = " #paidauth";
        L7:
            r5.append(r6);     // Catch: Exception -> L66
            r3.putExtra("android.intent.extra.SUBJECT", r5.toString());     // Catch: Exception -> L66
            StringBuilder r2 = new StringBuilder();     // Catch: Exception -> L66
            r2.append("Technical Details (PLEASE DO NOT EDIT OR REMOVE)\n");     // Catch: Exception -> L66
            r2.append("Device: ");     // Catch: Exception -> L66
            r2.append(Build.MANUFACTURER);     // Catch: Exception -> L66
            r2.append(" ");     // Catch: Exception -> L66
            r2.append(Build.MODEL);     // Catch: Exception -> L66
            r2.append("\n");     // Catch: Exception -> L66
            r2.append("OS version: SDK ");     // Catch: Exception -> L66
            int r52 = Build.VERSION.SDK_INT;     // Catch: Exception -> L66
            r2.append(r52);     // Catch: Exception -> L66
            r2.append("\n");     // Catch: Exception -> L66
            r2.append("Locale: ");     // Catch: Exception -> L66
            r2.append(Locale.getDefault());     // Catch: Exception -> L66
            r2.append("\n");     // Catch: Exception -> L66
            r2.append("\n");     // Catch: Exception -> L66
            r2.append("Target Phone: +");     // Catch: Exception -> L66
            r2.append(this.requestPhone);     // Catch: Exception -> L66
            r2.append("\n");     // Catch: Exception -> L66
            r2.append("\n");     // Catch: Exception -> L66
            SubscriptionManager r62 = SubscriptionManager.from(getContext());     // Catch: Exception -> L11
            if (r52 < 30) goto L13;
            List<SubscriptionInfo> r8 = r62.getCompleteActiveSubscriptionInfoList();     // Catch: Exception -> L11
        L14:
            if (r8 == null) goto L18;
            if (r8.isEmpty() == true) goto L18;
        L20:
            if (r8 != null) goto L22;
        L23:
            r8 = r62.getActiveSubscriptionInfoList();     // Catch: Exception -> L11
        L24:
            if (r8 == null) goto L39;
            Iterator<SubscriptionInfo> r53 = r8.iterator();     // Catch: Exception -> L11
        L26:
            if (r53.hasNext() == false) goto L39;
            SubscriptionInfo r63 = r53.next();     // Catch: Exception -> L11
            String r82 = r63.getNumber();     // Catch: Exception -> L11
            if (TextUtils.isEmpty(r82) == true) goto L26;
            String r9 = "SIM" + r63.getSimSlotIndex();     // Catch: Exception -> L11
            r2.append(r9);     // Catch: Exception -> L11
            r2.append(".Phone: ");     // Catch: Exception -> L11
            r2.append(r82);     // Catch: Exception -> L11
            r2.append("\n");     // Catch: Exception -> L11
            r2.append(r9);     // Catch: Exception -> L11
            r2.append(".MCC: ");     // Catch: Exception -> L11
            r2.append(r63.getMcc());     // Catch: Exception -> L11
            r2.append("\n");     // Catch: Exception -> L11
            r2.append(r9);     // Catch: Exception -> L11
            r2.append(".MNC: ");     // Catch: Exception -> L11
            r2.append(r63.getMnc());     // Catch: Exception -> L11
            r2.append("\n");     // Catch: Exception -> L11
            r2.append(r9);     // Catch: Exception -> L11
            r2.append(".Carrier: ");     // Catch: Exception -> L11
            if (TextUtils.isEmpty(r63.getCarrierName()) == false) goto L33;
            CharSequence r64 = "unknown";
        L34:
            r2.append(r64);     // Catch: Exception -> L11
            r2.append("\n\n");     // Catch: Exception -> L11
            goto L26
        L33:
            r64 = r63.getCarrierName();     // Catch: Exception -> L11
            goto L34
        L39:
            if (Build.VERSION.SDK_INT >= 29) goto L69;
            r2.append("Signal: unknown\n");     // Catch: Exception -> L66
        L49:
            r2.append("Wi-Fi: ");     // Catch: Exception -> L66
            r2.append(AndroidUtilities.isWifiEnabled(r12));     // Catch: Exception -> L66
            r2.append("\n");     // Catch: Exception -> L66
            r2.append("Airplane Mode: ");     // Catch: Exception -> L66
            r2.append(AndroidUtilities.isInAirplaneMode(r12));     // Catch: Exception -> L66
            r2.append("\n");     // Catch: Exception -> L66
            r2.append("\n");     // Catch: Exception -> L66
            r2.append("App: ");     // Catch: Exception -> L66
            r2.append(BuildVars.getExteraAppId());     // Catch: Exception -> L66
            r2.append("\n");     // Catch: Exception -> L66
            int r122 = r0.versionCode % 10;     // Catch: Exception -> L66
            if (r122 == 1) goto L56;
            if (r122 == 2) goto L56;
            if (BuildVars.isBetaApp() == false) goto L55;
            String r123 = "beta";
        L57:
            r2.append("App version: ");     // Catch: Exception -> L66
            r2.append(r1);     // Catch: Exception -> L66
            r2.append(" ");     // Catch: Exception -> L66
            r2.append(r123);     // Catch: Exception -> L66
            r2.append("\n");     // Catch: Exception -> L66
            r2.append("\n");     // Catch: Exception -> L66
            r2.append("Issue: ");     // Catch: Exception -> L66
            if (LoginActivity.m15796$$Nest$fgetpaid(this.this$0) == false) goto L60;
            String r124 = "no_otp";
        L61:
            r2.append(r124);     // Catch: Exception -> L66
            r2.append("\n");     // Catch: Exception -> L66
            if (TextUtils.isEmpty(this.lastError) == true) goto L64;
            r2.append("Error: ");     // Catch: Exception -> L66
            r2.append(this.lastError);     // Catch: Exception -> L66
            r2.append("\n");     // Catch: Exception -> L66
        L64:
            r2.append("\n\n================================================\n");     // Catch: Exception -> L66
            r2.append("WRITE YOUR COMMENT HERE:\n");     // Catch: Exception -> L66
            r2.append("\n");     // Catch: Exception -> L66
            r2.append("\n");     // Catch: Exception -> L66
            r3.putExtra("android.intent.extra.TEXT", r2.toString());     // Catch: Exception -> L66
            getContext().startActivity(Intent.createChooser(r3, "Send email..."));     // Catch: Exception -> L66
            return;
        L60:
            r124 = "no_otp_paid";
            goto L61
        L55:
            r123 = "universal";
        L56:
            r123 = "store";
            goto L57
        L69:
            TelephonyManager r54 = (TelephonyManager) r12.getSystemService(TelephonyManager.class);     // Catch: Exception -> L43
            ConnectivityManager r65 = (ConnectivityManager) r12.getSystemService(ConnectivityManager.class);     // Catch: Exception -> L43
            SignalStrength r55 = r54.getSignalStrength();     // Catch: Exception -> L43
            if (r55 == null) goto L45;
            r2.append("Signal: ");     // Catch: Exception -> L43
            r2.append(r55.getLevel());     // Catch: Exception -> L43
            r2.append("/4\n");     // Catch: Exception -> L43
        L68:
        L45:
            r2.append("Signal: unknown\n");     // Catch: Exception -> L43
        L43:
            e = move-exception;
            FileLog.e(e);     // Catch: Exception -> L66
            goto L49
        L22:
            if (r8.isEmpty() == false) goto L24;
        L18:
            if (r52 < 28) goto L20;
            r8 = r62.getAccessibleSubscriptionInfoList();     // Catch: Exception -> L11
            goto L20
        L13:
            r8 = null;
        L11:
            e = move-exception;
            FileLog.e(e);     // Catch: Exception -> L66
            goto L39
        L6:
            r6 = _UrlKt.FRAGMENT_ENCODE_SET;
        L66:
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.AppName), LocaleController.getString("NoMailInstalled", org.telegram.messenger.R.string.NoMailInstalled));
        }

        private /* synthetic */ void lambda$new$8(AlertDialog r2, int r3) {
            this.this$0.setPage(0, true, null, true);
        }

        private /* synthetic */ void lambda$new$9(Context r5, View r6) {
            Bundle r62 = this.nextCodeParams;
            if (r62 == null) goto L9;
            TLRPC.TL_auth_sentCode r0 = this.nextCodeAuth;
            if (r0 == null) goto L9;
            LoginActivity.m15820$$Nest$mfillNextCodeParams(this.this$0, r62, r0);
            return;
        L9:
            if (this.nextPressed == true) goto L35;
            LoadingTextView r63 = this.timeText;
            if (r63 == null) goto L15;
            if (r63.getVisibility() == 8) goto L15;
            return;
        L15:
            if (this.isResendingCode == false) goto L18;
            return;
        L18:
            if (this.nextType != 0) goto L29;
            TLRPC.TL_auth_reportMissingCode r64 = new TLRPC.TL_auth_reportMissingCode();
            r64.phone_number = this.requestPhone;
            r64.phone_code_hash = this.phoneHash;
            r64.mnc = _UrlKt.FRAGMENT_ENCODE_SET;
            String r1 = ((TelephonyManager) ApplicationLoader.applicationContext.getSystemService("phone")).getNetworkOperator();     // Catch: Exception -> L24
            if (TextUtils.isEmpty(r1) == true) goto L26;
            r1.substring(0, 3);     // Catch: Exception -> L24
            r64.mnc = r1.substring(3);     // Catch: Exception -> L24
        L26:
            this.this$0.getConnectionsManager().sendRequest(r64, null, 8);
            new AlertDialog.Builder(r5).setTitle(LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle)).setMessage(AndroidUtilities.replaceTags(LocaleController.formatString(org.telegram.messenger.R.string.DidNotGetTheCodeInfo, new Object[]{this.phone}))).setNeutralButton(LocaleController.getString(org.telegram.messenger.R.string.DidNotGetTheCodeHelpButton), new LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda17(this, r5)).setPositiveButton(LocaleController.getString(org.telegram.messenger.R.string.Close), null).setNegativeButton(LocaleController.getString(org.telegram.messenger.R.string.DidNotGetTheCodeEditNumberButton), new LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda18(this)).show();
            return;
        L24:
            e = move-exception;
            FileLog.e(e);
            goto L26
        L29:
            if (LoginActivity.m15799$$Nest$fgetradialProgressView(this.this$0).getTag() != null) goto L38;
            resendCode();
            return;
        L38:
            return;
        }

        private /* synthetic */ void lambda$onBackPressed$42(AlertDialog r2, int r3) {
            onBackPressed(true);
            this.this$0.setPage(0, true, null, true);
        }

        private /* synthetic */ void lambda$onNextPressed$22(DialogInterface r1) {
            this.this$0.finishFragment();
        }

        private /* synthetic */ void lambda$onNextPressed$23() {
            this.this$0.fragmentView.performHapticFeedback(3, 2);     // Catch: Exception -> L5
        L3:
            new AlertDialog.Builder(getContext()).setTitle(LocaleController.getString(org.telegram.messenger.R.string.YourPasswordSuccess)).setMessage(LocaleController.formatString(org.telegram.messenger.R.string.ChangePhoneNumberSuccessWithPhone, new Object[]{PhoneFormat.getInstance().format("+" + this.requestPhone)})).setPositiveButton(LocaleController.getString(org.telegram.messenger.R.string.OK), null).setOnDismissListener(new LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda40(this)).show();
        }

        private /* synthetic */ void lambda$onNextPressed$24(TLRPC.TL_error r10, TLObject r11) {
            tryHideProgress(false, true);
            this.nextPressed = false;
            if (r10 != null) goto L9;
            TLRPC.User r112 = (TLRPC.User) r11;
            destroyTimer();
            destroyCodeTimer();
            UserConfig.getInstance(LoginActivity.access$1400(this.this$0)).setCurrentUser(r112);
            UserConfig.getInstance(LoginActivity.access$1500(this.this$0)).saveConfig(true);
            ArrayList r102 = new ArrayList();
            r102.add(r112);
            MessagesStorage.getInstance(LoginActivity.access$1600(this.this$0)).putUsersAndChats(r102, null, true, true);
            MessagesController.getInstance(LoginActivity.access$1700(this.this$0)).putUser(r112, false);
            NotificationCenter.getInstance(LoginActivity.access$1800(this.this$0)).postNotificationName(NotificationCenter.mainUserInfoChanged, new Object[0]);
            this.this$0.getMessagesController().removeSuggestion(0, "VALIDATE_PHONE_NUMBER");
            if (this.currentType != 3) goto L7;
            AndroidUtilities.endIncomingCall();
        L7:
            animateSuccess(new LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda24(this));
            return;
        L9:
            this.lastError = r10.text;
            this.nextPressed = false;
            LoginActivity.m15835$$Nest$mshowDoneButton(this.this$0, false, true);
            int r113 = this.currentType;
            if (r113 != 3) goto L16;
            int r8 = this.nextType;
            if (r8 == 4) goto L25;
            if (r8 == 2) goto L25;
            if (r8 == 17) goto L25;
            if (r8 != 16) goto L16;
        L25:
            createTimer();
        L26:
            int r114 = this.currentType;
            if (r114 != 15) goto L29;
            NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.didReceiveSmsCode);
        L33:
            this.waitingForEvent = true;
            if (this.currentType != 3) goto L36;
            return;
        L36:
            if (r10.text.contains("PHONE_NUMBER_INVALID") == false) goto L39;
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString("InvalidPhoneNumber", org.telegram.messenger.R.string.InvalidPhoneNumber));
        L50:
            int r103 = 0;
        L51:
            CodeFieldContainer r115 = this.codeFieldContainer;
            CodeNumberField[] r1 = r115.codeField;
            if (r103 >= r1.length) goto L54;
            r1[r103].setText(_UrlKt.FRAGMENT_ENCODE_SET);
            r103 = r103 + 1;
            goto L51
        L54:
            r115.isFocusSuppressed = false;
            r1[0].requestFocus();
            return;
        L39:
            if (r10.text.contains("PHONE_CODE_EMPTY") == false) goto L41;
        L56:
            shakeWrongCode();
            return;
        L41:
            if (r10.text.contains("PHONE_CODE_INVALID") == true) goto L56;
            if (r10.text.contains("PHONE_CODE_EXPIRED") == false) goto L46;
            onBackPressed(true);
            this.this$0.setPage(0, true, null, true);
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString("CodeExpired", org.telegram.messenger.R.string.CodeExpired));
            goto L50
        L46:
            boolean r116 = r10.text.startsWith("FLOOD_WAIT");
            LoginActivity r12 = this.this$0;
            if (r116 == false) goto L49;
            LoginActivity.m15828$$Nest$mneedShowAlert(r12, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString("FloodWait", org.telegram.messenger.R.string.FloodWait));
            goto L50
        L49:
            LoginActivity.m15828$$Nest$mneedShowAlert(r12, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString("ErrorOccurred", org.telegram.messenger.R.string.ErrorOccurred) + "\n" + r10.text);
            goto L50
        L29:
            if (r114 != 2) goto L31;
            AndroidUtilities.setWaitingForSms(true);
            NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.didReceiveSmsCode);
            goto L33
        L31:
            if (r114 != 3) goto L33;
            AndroidUtilities.setWaitingForCall(true);
            NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.didReceiveCall);
        L16:
            if (r113 != 2) goto L20;
            int r82 = this.nextType;
            if (r82 == 4) goto L25;
            if (r82 == 3) goto L25;
        L20:
            if (r113 != 4) goto L26;
            int r117 = this.nextType;
            if (r117 == 2) goto L25;
            if (r117 == 17) goto L25;
            if (r117 != 16) goto L26;
            goto L25
        }

        private /* synthetic */ void lambda$onNextPressed$25(TLObject r2, TLRPC.TL_error r3) {
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda15(this, r3, r2));
        }

        private /* synthetic */ void lambda$onNextPressed$26(DialogInterface r1) {
            this.this$0.finishFragment();
        }

        private /* synthetic */ void lambda$onNextPressed$27(Activity r5) {
            new AlertDialog.Builder(r5).setTitle(LocaleController.getString(org.telegram.messenger.R.string.CancelLinkSuccessTitle)).setMessage(LocaleController.formatString("CancelLinkSuccess", org.telegram.messenger.R.string.CancelLinkSuccess, new Object[]{PhoneFormat.getInstance().format("+" + this.phone)})).setPositiveButton(LocaleController.getString(org.telegram.messenger.R.string.Close), null).setOnDismissListener(new LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda37(this)).show();
        }

        private /* synthetic */ void lambda$onNextPressed$28(TLRPC.TL_error r9, TL_account.confirmPhone r10) {
            tryHideProgress(false);
            this.nextPressed = false;
            if (r9 != null) goto L9;
            Activity r92 = this.this$0.getParentActivity();
            if (r92 == null) goto L48;
            animateSuccess(new LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda23(this, r92));
            return;
        L48:
            return;
        L9:
            this.lastError = r9.text;
            int r1 = this.currentType;
            if (r1 != 3) goto L16;
            int r7 = this.nextType;
            if (r7 == 4) goto L25;
            if (r7 == 2) goto L25;
            if (r7 == 17) goto L25;
            if (r7 != 16) goto L16;
        L25:
            createTimer();
        L26:
            int r12 = this.currentType;
            if (r12 != 15) goto L29;
            NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.didReceiveSmsCode);
        L33:
            this.waitingForEvent = true;
            if (this.currentType == 3) goto L37;
            AlertsCreator.processError(LoginActivity.access$1300(this.this$0), r9, this.this$0, r10, new Object[0]);
        L37:
            if (r9.text.contains("PHONE_CODE_EMPTY") == false) goto L39;
        L45:
            shakeWrongCode();
            return;
        L39:
            if (r9.text.contains("PHONE_CODE_INVALID") == true) goto L45;
            if (r9.text.contains("PHONE_CODE_EXPIRED") == false) goto L47;
            onBackPressed(true);
            this.this$0.setPage(0, true, null, true);
            return;
        L47:
            return;
        L29:
            if (r12 != 2) goto L31;
            AndroidUtilities.setWaitingForSms(true);
            NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.didReceiveSmsCode);
            goto L33
        L31:
            if (r12 != 3) goto L33;
            AndroidUtilities.setWaitingForCall(true);
            NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.didReceiveCall);
        L16:
            if (r1 != 2) goto L20;
            int r72 = this.nextType;
            if (r72 == 4) goto L25;
            if (r72 == 3) goto L25;
        L20:
            if (r1 != 4) goto L26;
            int r13 = this.nextType;
            if (r13 == 2) goto L25;
            if (r13 == 17) goto L25;
            if (r13 != 16) goto L26;
            goto L25
        }

        private /* synthetic */ void lambda$onNextPressed$29(TL_account.confirmPhone r1, TLObject r2, TLRPC.TL_error r3) {
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda13(this, r3, r1));
        }

        private /* synthetic */ void lambda$onNextPressed$30(Bundle r4) {
            this.this$0.setPage(5, true, r4, false);
        }

        private /* synthetic */ void lambda$onNextPressed$31(TLObject r1) {
            LoginActivity.m15831$$Nest$monAuthSuccess(this.this$0, (TLRPC.TL_auth_authorization) r1);
        }

        private /* synthetic */ void lambda$onNextPressed$32(Bundle r4) {
            this.this$0.setPage(6, true, r4, false);
        }

        private /* synthetic */ void lambda$onNextPressed$33(TLRPC.TL_error r4, TLObject r5, TLRPC.TL_auth_signIn r6) {
            this.nextPressed = false;
            LoginActivity.m15835$$Nest$mshowDoneButton(this.this$0, false, true);
            if (r4 != null) goto L10;
            TL_account.Password r52 = (TL_account.Password) r5;
            if (TwoStepVerificationActivity.canHandleCurrentPassword(r52, true) == true) goto L8;
            AlertsCreator.showUpdateAppAlert(this.this$0.getParentActivity(), LocaleController.getString("UpdateAppAlert", org.telegram.messenger.R.string.UpdateAppAlert), true);
            return;
        L8:
            Bundle r42 = new Bundle();
            SerializedData r0 = new SerializedData(r52.getObjectSize());
            r52.serializeToStream(r0);
            r42.putString("password", Utilities.bytesToHex(r0.toByteArray()));
            r42.putString("phoneFormated", this.requestPhone);
            r42.putString("phoneHash", this.phoneHash);
            r42.putString("code", r6.phone_code);
            animateSuccess(new LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda42(this, r42));
            return;
        L10:
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), r4.text);
        }

        private /* synthetic */ void lambda$onNextPressed$34(TLRPC.TL_auth_signIn r2, TLObject r3, TLRPC.TL_error r4) {
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda35(this, r4, r3, r2));
        }

        private /* synthetic */ void lambda$onNextPressed$36(TLRPC.TL_error r8, TLObject r9, TLRPC.TL_auth_signIn r10) {
            tryHideProgress(false, true);
            if (r8 != null) goto L11;
            this.nextPressed = false;
            LoginActivity.m15835$$Nest$mshowDoneButton(this.this$0, false, true);
            destroyTimer();
            destroyCodeTimer();
            if ((r9 instanceof TLRPC.TL_auth_authorizationSignUpRequired) == false) goto L10;
            TLRPC.TL_help_termsOfService r82 = ((TLRPC.TL_auth_authorizationSignUpRequired) r9).terms_of_service;
            if (r82 == null) goto L9;
            LoginActivity.m15810$$Nest$fputcurrentTermsOfService(this.this$0, r82);
        L9:
            Bundle r83 = new Bundle();
            r83.putString("phoneFormated", this.requestPhone);
            r83.putString("phoneHash", this.phoneHash);
            r83.putString("code", r10.phone_code);
            animateSuccess(new LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda27(this, r83));
        L15:
            if (this.currentType != 3) goto L68;
            AndroidUtilities.endIncomingCall();
            AndroidUtilities.setWaitingForCall(false);
            return;
        L68:
            return;
        L10:
            animateSuccess(new LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda28(this, r9));
            goto L15
        L11:
            String r92 = r8.text;
            this.lastError = r92;
            if (r92.contains("SESSION_PASSWORD_NEEDED") == false) goto L18;
            ConnectionsManager.getInstance(LoginActivity.access$1200(this.this$0)).sendRequest(new TL_account.getPassword(), new LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda29(this, r10), 10);
            destroyTimer();
            destroyCodeTimer();
            goto L15
        L18:
            this.nextPressed = false;
            LoginActivity.m15835$$Nest$mshowDoneButton(this.this$0, false, true);
            int r93 = this.currentType;
            if (r93 != 3) goto L25;
            int r6 = this.nextType;
            if (r6 == 4) goto L34;
            if (r6 == 2) goto L34;
            if (r6 == 17) goto L34;
            if (r6 != 16) goto L25;
        L34:
            createTimer();
        L35:
            int r94 = this.currentType;
            if (r94 != 15) goto L38;
            NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.didReceiveSmsCode);
        L42:
            this.waitingForEvent = true;
            if (this.currentType != 3) goto L45;
            return;
        L45:
            if (r8.text.contains("PHONE_NUMBER_INVALID") == false) goto L48;
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString("InvalidPhoneNumber", org.telegram.messenger.R.string.InvalidPhoneNumber));
        L59:
            int r84 = 0;
        L60:
            CodeFieldContainer r95 = this.codeFieldContainer;
            CodeNumberField[] r102 = r95.codeField;
            if (r84 >= r102.length) goto L63;
            r102[r84].setText(_UrlKt.FRAGMENT_ENCODE_SET);
            r84 = r84 + 1;
            goto L60
        L63:
            r95.isFocusSuppressed = false;
            r102[0].requestFocus();
            return;
        L48:
            if (r8.text.contains("PHONE_CODE_EMPTY") == false) goto L50;
        L65:
            shakeWrongCode();
            return;
        L50:
            if (r8.text.contains("PHONE_CODE_INVALID") == true) goto L65;
            if (r8.text.contains("PHONE_CODE_EXPIRED") == false) goto L55;
            onBackPressed(true);
            this.this$0.setPage(0, true, null, true);
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString("CodeExpired", org.telegram.messenger.R.string.CodeExpired));
            goto L59
        L55:
            boolean r96 = r8.text.startsWith("FLOOD_WAIT");
            LoginActivity r103 = this.this$0;
            if (r96 == false) goto L58;
            LoginActivity.m15828$$Nest$mneedShowAlert(r103, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString("FloodWait", org.telegram.messenger.R.string.FloodWait));
            goto L59
        L58:
            LoginActivity.m15828$$Nest$mneedShowAlert(r103, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString("ErrorOccurred", org.telegram.messenger.R.string.ErrorOccurred) + "\n" + r8.text);
            goto L59
        L38:
            if (r94 != 2) goto L40;
            AndroidUtilities.setWaitingForSms(true);
            NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.didReceiveSmsCode);
            goto L42
        L40:
            if (r94 != 3) goto L42;
            AndroidUtilities.setWaitingForCall(true);
            NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.didReceiveCall);
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda30());
        L25:
            if (r93 != 2) goto L29;
            int r62 = this.nextType;
            if (r62 == 4) goto L34;
            if (r62 == 3) goto L34;
        L29:
            if (r93 != 4) goto L35;
            int r97 = this.nextType;
            if (r97 == 2) goto L34;
            if (r97 == 17) goto L34;
            if (r97 != 16) goto L35;
            goto L34
        }

        private /* synthetic */ void lambda$onNextPressed$37(TLRPC.TL_auth_signIn r2, TLObject r3, TLRPC.TL_error r4) {
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda16(this, r4, r3, r2));
        }

        private /* synthetic */ void lambda$onShow$44() {
            if (this.currentType == 3) goto L14;
            CodeNumberField[] r0 = this.codeFieldContainer.codeField;
            if (r0 == null) goto L14;
            int r02 = r0.length - 1;
        L7:
            if (r02 < 0) goto L14;
            if (r02 == 0) goto L13;
            if (this.codeFieldContainer.codeField[r02].length() != 0) goto L13;
            r02 = r02 - 1;
        L13:
            this.codeFieldContainer.codeField[r02].requestFocus();
            CodeNumberField r1 = this.codeFieldContainer.codeField[r02];
            r1.setSelection(r1.length());
            LoginActivity.m15836$$Nest$mshowKeyboard(this.this$0, this.codeFieldContainer.codeField[r02]);
        L14:
            RLottieDrawable r03 = this.hintDrawable;
            if (r03 == null) goto L18;
            r03.start();
        L18:
            if (this.currentType != 15) goto L24;
            this.openFragmentImageView.getAnimatedDrawable().setCurrentFrame(0, false);
            this.openFragmentImageView.getAnimatedDrawable().start();
            return;
        }

        private /* synthetic */ void lambda$resendCode$10(TLRPC.TL_error r4, Bundle r5, TLObject r6) {
            this.nextPressed = false;
            if (r4 != null) goto L11;
            this.nextCodeParams = r5;
            TLRPC.TL_auth_sentCode r62 = (TLRPC.TL_auth_sentCode) r6;
            this.nextCodeAuth = r62;
            TLRPC.auth_SentCodeType r42 = r62.type;
            if ((r42 instanceof TLRPC.TL_auth_sentCodeTypeSmsPhrase) == false) goto L8;
            this.nextType = 17;
        L10:
            LoginActivity.m15820$$Nest$mfillNextCodeParams(this.this$0, r5, r62);
        L31:
            tryHideProgress(false);
            return;
        L8:
            if ((r42 instanceof TLRPC.TL_auth_sentCodeTypeSmsWord) == false) goto L10;
            this.nextType = 16;
            goto L10
        L11:
            String r52 = r4.text;
            if (r52 == null) goto L31;
            if (r52.contains("PHONE_NUMBER_INVALID") == false) goto L17;
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString(org.telegram.messenger.R.string.InvalidPhoneNumber));
            goto L31
        L17:
            if (r4.text.contains("PHONE_CODE_EMPTY") == false) goto L19;
        L30:
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString(org.telegram.messenger.R.string.InvalidCode));
            goto L31
        L19:
            if (r4.text.contains("PHONE_CODE_INVALID") == true) goto L30;
            if (r4.text.contains("PHONE_CODE_EXPIRED") == false) goto L25;
            onBackPressed(true);
            this.this$0.setPage(0, true, null, true);
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString(org.telegram.messenger.R.string.CodeExpired));
            goto L31
        L25:
            if (r4.text.startsWith("FLOOD_WAIT") == false) goto L28;
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString(org.telegram.messenger.R.string.FloodWait));
            goto L31
        L28:
            if (r4.code == (-1000)) goto L31;
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString(org.telegram.messenger.R.string.ErrorOccurred) + "\n" + r4.text);
            goto L31
        }

        private /* synthetic */ void lambda$resendCode$11(Bundle r2, TLObject r3, TLRPC.TL_error r4) {
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda34(this, r4, r2, r3));
        }

        private /* synthetic */ void lambda$setParams$21(View r1, boolean r2) {
            if (r2 == false) goto L5;
            LoginActivity.m15794$$Nest$fgetkeyboardView(this.this$0).setEditText((EditText) r1);
            LoginActivity.m15794$$Nest$fgetkeyboardView(this.this$0).setDispatchBackWhenEmpty(true);
            return;
        }

        private /* synthetic */ void lambda$shakeWrongCode$40() {
            CodeFieldContainer r0 = this.codeFieldContainer;
            int r1 = 0;
            r0.isFocusSuppressed = false;
            r0.codeField[0].requestFocus();
        L3:
            CodeNumberField[] r02 = this.codeFieldContainer.codeField;
            if (r1 >= r02.length) goto L6;
            r02[r1].animateErrorProgress(0.0f);
            r1 = r1 + 1;
            goto L3
        }

        private /* synthetic */ void lambda$shakeWrongCode$41() {
            postDelayed(new LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda43(this), 150);
        }

        private /* synthetic */ void lambda$tryHideProgress$16() {
            this.blueImageView.setAutoRepeat(false);
            this.blueImageView.setAnimation(this.hintDrawable);
        }

        private /* synthetic */ void lambda$tryHideProgress$17() {
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda44(this));
        }

        private /* synthetic */ void lambda$tryHideProgress$18() {
            this.dotsToStarsDrawable.setOnAnimationEndListener(new LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda41(this));
            this.blueImageView.setAutoRepeat(false);
            this.dotsToStarsDrawable.setCurrentFrame(0, false);
            this.blueImageView.setAnimation(this.dotsToStarsDrawable);
            this.blueImageView.playAnimation();
        }

        private /* synthetic */ void lambda$tryHideProgress$19() {
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda32(this));
        }

        private /* synthetic */ void lambda$tryShowProgress$12(int r1, boolean r2) {
            tryShowProgress(r1, r2);
        }

        private /* synthetic */ void lambda$tryShowProgress$13(int r2, boolean r3) {
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda33(this, r2, r3));
        }

        private /* synthetic */ void lambda$tryShowProgress$14() {
            this.blueImageView.setAutoRepeat(true);
            this.dotsDrawable.setCurrentFrame(0, false);
            this.dotsDrawable.setAutoRepeat(1);
            this.blueImageView.setAnimation(this.dotsDrawable);
            this.blueImageView.playAnimation();
        }

        private /* synthetic */ void lambda$tryShowProgress$15() {
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda22(this));
        }

        private void resendCode() {
            if (this.nextPressed == false) goto L5;
            return;
        L5:
            if (this.isResendingCode == false) goto L7;
            return;
        L7:
            if (LoginActivity.m15791$$Nest$fgetisRequestingFirebaseSms(this.this$0) == true) goto L13;
            this.isResendingCode = true;
            this.timeText.invalidate();
            this.problemText.invalidate();
            Bundle r1 = new Bundle();
            r1.putString("phone", this.phone);
            r1.putString("ephone", this.emailPhone);
            r1.putString("phoneFormated", this.requestPhone);
            r1.putInt("prevType", this.currentType);
            this.nextPressed = true;
            TLRPC.TL_auth_resendCode r0 = new TLRPC.TL_auth_resendCode();
            r0.phone_number = this.requestPhone;
            r0.phone_code_hash = this.phoneHash;
            tryShowProgress(ConnectionsManager.getInstance(LoginActivity.access$700(this.this$0)).sendRequest(r0, new LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda31(this, r1), 10));
            return;
        }

        private void setProblemTextVisible(boolean r3) {
            LoadingTextView r0 = this.problemText;
            if (r0 == null) goto L13;
            if (r3 == false) goto L7;
            float r32 = 1.0f;
        L9:
            if (r0.getAlpha() == r32) goto L12;
            this.problemText.animate().cancel();
            this.problemText.animate().alpha(r32).setDuration(150).start();
            return;
        L12:
            return;
        L7:
            r32 = 0.0f;
            goto L9
        }

        private void shakeWrongCode() {
            this.codeFieldContainer.performHapticFeedback(3, 2);     // Catch: Exception -> L16
        L3:
            int r1 = 0;
        L4:
            CodeNumberField[] r2 = this.codeFieldContainer.codeField;
            if (r1 >= r2.length) goto L8;
            r2[r1].setText(_UrlKt.FRAGMENT_ENCODE_SET);
            this.codeFieldContainer.codeField[r1].animateErrorProgress(1.0f);
            r1 = r1 + 1;
            goto L4
        L8:
            if (this.errorViewSwitcher.getCurrentView() == this.wrongCode) goto L10;
            this.errorViewSwitcher.showNext();
        L10:
            this.codeFieldContainer.codeField[0].requestFocus();
            CodeFieldContainer r0 = this.codeFieldContainer;
            if (this.currentType != 11) goto L13;
            float r12 = 3.5f;
        L14:
            AndroidUtilities.shakeViewSpring(r0, r12, new LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda36(this));
            removeCallbacks(this.errorColorTimeout);
            postDelayed(this.errorColorTimeout, 5000);
            this.postedErrorColorTimeout = true;
            return;
        L13:
            r12 = 10.0f;
            goto L14
        }

        private void tryHideProgress(boolean r2) {
            tryHideProgress(r2, true);
        }

        private void tryHideProgress(boolean r2, boolean r3) {
            if (this.starsToDotsDrawable != null) goto L5;
            LoginActivity.m15827$$Nest$mneedHideProgress(this.this$0, r2, r3);
            return;
        L5:
            if (this.isDotsAnimationVisible == true) goto L7;
            return;
        L7:
            this.isDotsAnimationVisible = false;
            this.blueImageView.setAutoRepeat(false);
            this.dotsDrawable.setAutoRepeat(0);
            this.dotsDrawable.setOnFinishCallback(new LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda14(this), this.dotsDrawable.getFramesCount() - 1);
        }

        private void tryShowProgress(int r2) {
            tryShowProgress(r2, true);
        }

        private void tryShowProgress(int r4, boolean r5) {
            if (this.starsToDotsDrawable != null) goto L5;
            LoginActivity.m15830$$Nest$mneedShowProgress(this.this$0, r4, r5);
            return;
        L5:
            if (this.isDotsAnimationVisible == false) goto L7;
            return;
        L7:
            this.isDotsAnimationVisible = true;
            if (this.hintDrawable.getCurrentFrame() == (this.hintDrawable.getFramesCount() - 1)) goto L11;
            this.hintDrawable.setOnAnimationEndListener(new LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda20(this, r4, r5));
            return;
        L11:
            this.starsToDotsDrawable.setOnAnimationEndListener(new LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda21(this));
            this.blueImageView.setAutoRepeat(false);
            this.starsToDotsDrawable.setCurrentFrame(0, false);
            this.blueImageView.setAnimation(this.starsToDotsDrawable);
            this.blueImageView.playAnimation();
        }

        @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
        public void didReceivedNotification(int r4, int r5, Object... r6) {
            if (this.waitingForEvent == false) goto L21;
            CodeFieldContainer r52 = this.codeFieldContainer;
            if (r52.codeField != null) goto L8;
            return;
        L8:
            if (r4 != NotificationCenter.didReceiveSmsCode) goto L12;
            r52.setText(_UrlKt.FRAGMENT_ENCODE_SET + r6[0]);
            onNextPressed(null);
            return;
        L12:
            if (r4 != NotificationCenter.didReceiveCall) goto L22;
            String r42 = _UrlKt.FRAGMENT_ENCODE_SET + r6[0];
            if (AndroidUtilities.checkPhonePattern(this.pattern, r42) == true) goto L17;
            return;
        L17:
            if (this.pattern.equals("*") == true) goto L19;
            this.catchedPhone = r42;
            AndroidUtilities.endIncomingCall();
        L19:
            onNextPressed(r42);
            CallReceiver.clearLastCall();
            return;
        L22:
            return;
        }

        @Override // org.telegram.ui.Components.SlideView
        public String getHeaderName() {
            int r0 = this.currentType;
            if (r0 == 3) goto L10;
            if (r0 == 11) goto L10;
            return LocaleController.getString("YourCode", org.telegram.messenger.R.string.YourCode);
        L10:
            return this.phone;
        }

        @Override // org.telegram.ui.Components.SlideView
        public boolean hasCustomKeyboard() {
            if (this.currentType == 3) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // org.telegram.ui.Components.SlideView
        public boolean needBackButton() {
            return true;
        }

        @Override // org.telegram.ui.Components.SlideView
        public boolean onBackPressed(boolean r7) {
            if (LoginActivity.m15776$$Nest$fgetactivityMode(this.this$0) == 0) goto L6;
            this.this$0.finishFragment();
            return false;
        L6:
            int r0 = this.prevType;
            if (r0 == 0) goto L10;
            this.this$0.setPage(r0, true, null, true);
            return false;
        L10:
            if (r7 == true) goto L13;
            LoginActivity r72 = this.this$0;
            r72.showDialog(new AlertDialog.Builder(r72.getParentActivity()).setTitle(LocaleController.getString(org.telegram.messenger.R.string.EditNumber)).setMessage(AndroidUtilities.replaceTags(LocaleController.formatString("EditNumberInfo", org.telegram.messenger.R.string.EditNumberInfo, new Object[]{this.phone}))).setPositiveButton(LocaleController.getString(org.telegram.messenger.R.string.Close), null).setNegativeButton(LocaleController.getString(org.telegram.messenger.R.string.Edit), new LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda11(this)).create());
            return false;
        L13:
            this.nextPressed = false;
            tryHideProgress(true);
            TLRPC.TL_auth_cancelCode r73 = new TLRPC.TL_auth_cancelCode();
            r73.phone_number = this.requestPhone;
            r73.phone_code_hash = this.phoneHash;
            ConnectionsManager.getInstance(LoginActivity.access$1100(this.this$0)).sendRequest(r73, new LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda12(), 10);
            destroyTimer();
            destroyCodeTimer();
            this.currentParams = null;
            int r74 = this.currentType;
            if (r74 != 15) goto L17;
            NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.didReceiveSmsCode);
        L22:
            this.waitingForEvent = false;
            return true;
        L17:
            if (r74 != 2) goto L20;
            AndroidUtilities.setWaitingForSms(false);
            NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.didReceiveSmsCode);
            goto L22
        L20:
            if (r74 != 3) goto L22;
            AndroidUtilities.setWaitingForCall(false);
            NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.didReceiveCall);
            goto L22
        }

        @Override // org.telegram.ui.Components.SlideView
        public void onCancelPressed() {
            this.nextPressed = false;
        }

        @Override // android.view.View
        public void onConfigurationChanged(Configuration r6) {
            super.onConfigurationChanged(r6);
            CodeFieldContainer r62 = this.codeFieldContainer;
            if (r62 == null) goto L16;
            CodeNumberField[] r63 = r62.codeField;
            if (r63 == null) goto L20;
            int r0 = r63.length;
            int r2 = 0;
        L7:
            if (r2 >= r0) goto L21;
            CodeNumberField r3 = r63[r2];
            if (hasCustomKeyboard() == true) goto L11;
        L14:
            boolean r4 = true;
        L15:
            r3.setShowSoftInputOnFocusCompat(r4);
            r2 = r2 + 1;
            goto L7
        L11:
            if (LoginActivity.m15823$$Nest$misCustomKeyboardForceDisabled(this.this$0) == true) goto L14;
            r4 = false;
            goto L15
        L21:
            return;
        L20:
            return;
        }

        @Override // org.telegram.ui.Components.SlideView
        public void onDestroyActivity() {
            super.onDestroyActivity();
            int r0 = this.currentType;
            if (r0 != 15) goto L6;
            NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.didReceiveSmsCode);
        L11:
            this.waitingForEvent = false;
            destroyTimer();
            destroyCodeTimer();
            return;
        L6:
            if (r0 != 2) goto L9;
            AndroidUtilities.setWaitingForSms(false);
            NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.didReceiveSmsCode);
            goto L11
        L9:
            if (r0 != 3) goto L11;
            AndroidUtilities.setWaitingForCall(false);
            NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.didReceiveCall);
            goto L11
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            removeCallbacks(this.errorColorTimeout);
        }

        @Override // org.telegram.ui.Components.SlideView
        public void onHide() {
            super.onHide();
            this.isResendingCode = false;
            this.nextPressed = false;
            if (this.prevType == 0) goto L8;
            Bundle r0 = this.currentParams;
            if (r0 == null) goto L9;
            r0.putInt("timeout", this.time);
            return;
        L9:
            return;
        }

        @Override // org.telegram.ui.Components.SlideView
        public void onNextPressed(String r8) {
            int r0 = LoginActivity.m15782$$Nest$fgetcurrentViewNum(this.this$0);
            boolean r1 = this.nextPressed;
            if (r0 != 11) goto L6;
            if (r1 == true) goto L57;
        L14:
            if (r8 != null) goto L16;
            r8 = this.codeFieldContainer.getCode();
        L16:
            boolean r02 = TextUtils.isEmpty(r8);
            LoginActivity r12 = this.this$0;
            int r5 = 0;
            if (r02 == false) goto L21;
            LoginActivity.m15834$$Nest$monFieldError(r12, this.codeFieldContainer, false);
            return;
        L21:
            if (LoginActivity.m15782$$Nest$fgetcurrentViewNum(r12) >= 1) goto L23;
        L27:
            this.nextPressed = true;
            int r03 = this.currentType;
            if (r03 != 15) goto L30;
            NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.didReceiveSmsCode);
        L35:
            this.waitingForEvent = false;
            int r04 = LoginActivity.m15776$$Nest$fgetactivityMode(this.this$0);
            if (r04 == 1) goto L48;
            if (r04 == 2) goto L43;
            TLRPC.TL_auth_signIn r05 = new TLRPC.TL_auth_signIn();
            r05.phone_number = this.requestPhone;
            r05.phone_code = r8;
            r05.phone_code_hash = this.phoneHash;
            r05.flags |= 1;
            destroyTimer();
            CodeFieldContainer r82 = this.codeFieldContainer;
            r82.isFocusSuppressed = true;
            CodeNumberField[] r83 = r82.codeField;
            int r13 = r83.length;
        L39:
            if (r5 >= r13) goto L41;
            r83[r5].animateFocusedProgress(0.0f);
            r5 = r5 + 1;
            goto L39
        L41:
            tryShowProgress(ConnectionsManager.getInstance(LoginActivity.access$1000(this.this$0)).sendRequest(r05, new LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda10(this, r05), 10), true);
            LoginActivity.m15835$$Nest$mshowDoneButton(this.this$0, true, true);
            return;
        L43:
            TL_account.changePhone r06 = new TL_account.changePhone();
            r06.phone_number = this.requestPhone;
            r06.phone_code = r8;
            r06.phone_code_hash = this.phoneHash;
            destroyTimer();
            CodeFieldContainer r84 = this.codeFieldContainer;
            r84.isFocusSuppressed = true;
            CodeNumberField[] r85 = r84.codeField;
            int r3 = r85.length;
        L44:
            if (r5 >= r3) goto L46;
            r85[r5].animateFocusedProgress(0.0f);
            r5 = r5 + 1;
            goto L44
        L46:
            tryShowProgress(ConnectionsManager.getInstance(LoginActivity.access$800(this.this$0)).sendRequest(r06, new LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda8(this), 2), true);
            LoginActivity.m15835$$Nest$mshowDoneButton(this.this$0, true, true);
            return;
        L48:
            this.requestPhone = LoginActivity.m15779$$Nest$fgetcancelDeletionPhone(this.this$0);
            TL_account.confirmPhone r07 = new TL_account.confirmPhone();
            r07.phone_code = r8;
            r07.phone_code_hash = this.phoneHash;
            destroyTimer();
            CodeFieldContainer r86 = this.codeFieldContainer;
            r86.isFocusSuppressed = true;
            CodeNumberField[] r87 = r86.codeField;
            int r32 = r87.length;
        L49:
            if (r5 >= r32) goto L51;
            r87[r5].animateFocusedProgress(0.0f);
            r5 = r5 + 1;
            goto L49
        L51:
            tryShowProgress(ConnectionsManager.getInstance(LoginActivity.access$900(this.this$0)).sendRequest(r07, new LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda9(this, r07), 2));
            return;
        L30:
            if (r03 != 2) goto L33;
            AndroidUtilities.setWaitingForSms(false);
            NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.didReceiveSmsCode);
            goto L35
        L33:
            if (r03 != 3) goto L35;
            NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.didReceiveCall);
            goto L35
        L23:
            if (LoginActivity.m15782$$Nest$fgetcurrentViewNum(this.this$0) > 4) goto L27;
            if (this.codeFieldContainer.isFocusSuppressed == false) goto L27;
            return;
        L57:
            return;
        L6:
            if (r1 == false) goto L8;
            return;
        L8:
            if (LoginActivity.m15782$$Nest$fgetcurrentViewNum(this.this$0) < 1) goto L12;
            if (LoginActivity.m15782$$Nest$fgetcurrentViewNum(this.this$0) <= 4) goto L14;
        L12:
            if (LoginActivity.m15782$$Nest$fgetcurrentViewNum(this.this$0) == 15) goto L14;
        }

        @Override // org.telegram.ui.Components.SlideView
        public void onShow() {
            super.onShow();
            RLottieDrawable r0 = this.hintDrawable;
            if (r0 == null) goto L5;
            r0.setCurrentFrame(0);
        L5:
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda0(this), LoginActivity.m15838$$Nest$sfgetSHOW_DELAY());
        }

        @Override // org.telegram.ui.Components.SlideView
        public void restoreStateParams(Bundle r4) {
            Bundle r0 = r4.getBundle("smsview_params_" + this.currentType);
            this.currentParams = r0;
            if (r0 == null) goto L5;
            setParams(r0, true);
        L5:
            String r02 = r4.getString("catchedPhone");
            if (r02 == null) goto L8;
            this.catchedPhone = r02;
        L8:
            String r03 = r4.getString("smsview_code_" + this.currentType);
            if (r03 == null) goto L13;
            CodeFieldContainer r1 = this.codeFieldContainer;
            if (r1.codeField == null) goto L13;
            r1.setText(r03);
        L13:
            int r04 = r4.getInt("time");
            if (r04 == 0) goto L16;
            this.time = r04;
        L16:
            int r42 = r4.getInt("open");
            if (r42 == 0) goto L20;
            this.openTime = r42;
            return;
        }

        @Override // org.telegram.ui.Components.SlideView
        public void saveStateParams(Bundle r4) {
            String r0 = this.codeFieldContainer.getCode();
            if (r0.length() == 0) goto L5;
            r4.putString("smsview_code_" + this.currentType, r0);
        L5:
            String r02 = this.catchedPhone;
            if (r02 == null) goto L9;
            r4.putString("catchedPhone", r02);
        L9:
            if (this.currentParams == null) goto L11;
            r4.putBundle("smsview_params_" + this.currentType, this.currentParams);
        L11:
            int r03 = this.time;
            if (r03 == 0) goto L14;
            r4.putInt("time", r03);
        L14:
            int r3 = this.openTime;
            if (r3 == 0) goto L18;
            r4.putInt("open", r3);
            return;
        }

        @Override // org.telegram.ui.Components.SlideView
        public void setParams(Bundle r20, boolean r21) {
            float r8 = 1.0f;
            if (r20 == null) goto L5;
            this.waitingForEvent = true;
            int r12 = this.currentType;
            if (r12 != 15) goto L21;
            NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.didReceiveSmsCode);
        L27:
            this.currentParams = r20;
            this.phone = r20.getString("phone");
            this.emailPhone = r20.getString("ephone");
            this.requestPhone = r20.getString("phoneFormated");
            this.phoneHash = r20.getString("phoneHash");
            this.time = r20.getInt("timeout");
            this.openTime = (int) (System.currentTimeMillis() / 1000);
            this.nextType = r20.getInt("nextType");
            this.pattern = r20.getString("pattern");
            this.prefix = r20.getString("prefix");
            this.length = r20.getInt("length");
            this.prevType = r20.getInt("prevType", 0);
            if (this.length != 0) goto L30;
            this.length = 5;
        L30:
            this.url = r20.getString("url");
            this.nextCodeParams = null;
            this.nextCodeAuth = null;
            this.codeFieldContainer.setNumbersCount(this.length, this.currentType);
            CodeNumberField[] r13 = this.codeFieldContainer.codeField;
            int r14 = r13.length;
            int r15 = 0;
        L31:
            if (r15 >= r14) goto L40;
            float r16 = r8;
            CodeNumberField r82 = r13[r15];
            if (hasCustomKeyboard() == true) goto L35;
        L38:
            boolean r122 = true;
        L39:
            r82.setShowSoftInputOnFocusCompat(r122);
            r82.addTextChangedListener(new AnonymousClass6(this));
            r82.setOnFocusChangeListener(new LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda7(this));
            r15 = r15 + 1;
            r8 = r16;
            goto L31
        L35:
            if (LoginActivity.m15823$$Nest$misCustomKeyboardForceDisabled(this.this$0) == true) goto L38;
            r122 = false;
            goto L39
        L40:
            float r162 = r8;
            int r83 = this.prevType;
            if (r83 != 17) goto L43;
            this.prevTypeTextView.setVisibility(0);
            this.prevTypeTextView.setText(AndroidUtilities.replaceArrows(LocaleController.getString(org.telegram.messenger.R.string.BackEnteringPhrase), true, AndroidUtilities.dp(-1.0f), AndroidUtilities.dp(r162)));
        L48:
            if (this.phone == null) goto L272;
            String r84 = PhoneFormat.getInstance().format(this.phone);
            if (LoginActivity.m15825$$Nest$misInCancelAccountDeletionMode(this.this$0) == false) goto L58;
            SpannableStringBuilder r123 = new SpannableStringBuilder(AndroidUtilities.replaceTags(LocaleController.formatString("CancelAccountResetInfo2", org.telegram.messenger.R.string.CancelAccountResetInfo2, new Object[]{PhoneFormat.getInstance().format("+" + r84)})));
            int r85 = TextUtils.indexOf(r123, '*');
            int r1 = TextUtils.lastIndexOf(r123, '*');
            if (r85 == (-1)) goto L57;
            if (r1 == (-1)) goto L57;
            if (r85 == r1) goto L57;
            this.confirmTextView.setMovementMethod(new AndroidUtilities.LinkMovementMethodMy());
            r123.replace(r1, r1 + 1, _UrlKt.FRAGMENT_ENCODE_SET);
            r123.replace(r85, r85 + 1, _UrlKt.FRAGMENT_ENCODE_SET);
            r123.setSpan(new URLSpanNoUnderline("tg://settings/change_number"), r85, r1 - 1, 33);
        L57:
            CharSequence r17 = r123;
        L72:
            this.confirmTextView.setText(r17);
            int r18 = this.currentType;
            if (r18 == 15) goto L89;
            if (r18 != 1) goto L87;
            int r19 = this.nextType;
            if (r19 == 3) goto L86;
            if (r19 == 4) goto L86;
            if (r19 == 11) goto L86;
            if (r19 != 15) goto L82;
            this.problemText.setText(LocaleController.getString(org.telegram.messenger.R.string.DidNotGetTheCodeFragment));
            goto L89
        L82:
            LoadingTextView r9 = this.problemText;
            if (r19 != 0) goto L85;
            r9.setText(LocaleController.getString(org.telegram.messenger.R.string.DidNotGetTheCode));
            goto L89
        L85:
            r9.setText(LocaleController.getString(org.telegram.messenger.R.string.DidNotGetTheCodeSms));
        L86:
            this.problemText.setText(LocaleController.getString(org.telegram.messenger.R.string.DidNotGetTheCodePhone));
            goto L89
        L87:
            this.problemText.setText(LocaleController.getString(org.telegram.messenger.R.string.DidNotGetTheCode));
        L89:
            if (this.currentType == 3) goto L91;
            LoginActivity.m15836$$Nest$mshowKeyboard(this.this$0, this.codeFieldContainer.codeField[0]);
            this.codeFieldContainer.codeField[0].requestFocus();
        L92:
            destroyTimer();
            destroyCodeTimer();
            this.lastCurrentTime = System.currentTimeMillis();
            int r110 = this.currentType;
            if (r110 != 1) goto L98;
            setProblemTextVisible(true);
            this.timeText.setVisibility(8);
            LoadingTextView r111 = this.problemText;
            if (r111 == null) goto L247;
            r111.setVisibility(0);
        L247:
            if (this.currentType != 11) goto L271;
            String r112 = this.prefix;
            int r2 = 0;
        L250:
            if (r2 >= this.length) goto L252;
            r112 = r112 + MVEL.VERSION_SUB;
            r2 = r2 + 1;
            goto L250
        L252:
            String r113 = PhoneFormat.getInstance().format("+" + r112);
            int r22 = 0;
        L254:
            if (r22 >= this.length) goto L259;
            int r3 = r113.lastIndexOf(MVEL.VERSION_SUB);
            if (r3 < 0) goto L258;
            r113 = r113.substring(0, r3);
        L258:
            r22 = r22 + 1;
            goto L254
        L259:
            this.prefixTextView.setText(r113.replaceAll("\\)", _UrlKt.FRAGMENT_ENCODE_SET).replaceAll("\\(", _UrlKt.FRAGMENT_ENCODE_SET));
            return;
        L271:
            return;
        L98:
            if (r110 != 3) goto L138;
            int r114 = this.nextType;
            if (r114 == 4) goto L109;
            if (r114 == 2) goto L109;
            if (r114 == 17) goto L109;
            if (r114 == 16) goto L109;
            if (r114 == 11) goto L109;
            this.timeText.setVisibility(8);
        L120:
            if (r21 == false) goto L122;
            String r115 = AndroidUtilities.obtainLoginPhoneCall(this.pattern);
        L123:
            if (r115 == null) goto L125;
            onNextPressed(r115);
            goto L247
        L125:
            String r116 = this.catchedPhone;
            if (r116 == null) goto L128;
            onNextPressed(r116);
            goto L247
        L128:
            int r117 = this.nextType;
            if (r117 == 4) goto L136;
            if (r117 == 2) goto L136;
            if (r117 == 17) goto L136;
            if (r117 == 16) goto L136;
            if (r117 != 11) goto L247;
        L136:
            createTimer();
            goto L247
        L122:
            r115 = null;
        L109:
            setProblemTextVisible(false);
            this.timeText.setVisibility(0);
            this.problemText.setVisibility(8);
            int r118 = this.nextType;
            if (r118 == 4) goto L119;
            if (r118 == 11) goto L119;
            if (r118 != 2) goto L115;
        L118:
            this.timeText.setText(LocaleController.formatString("SmsAvailableIn", org.telegram.messenger.R.string.SmsAvailableIn, new Object[]{1, 0}));
            goto L120
        L115:
            if (r118 == 17) goto L118;
            if (r118 != 16) goto L120;
        L119:
            this.timeText.setText(LocaleController.formatString("CallAvailableIn", org.telegram.messenger.R.string.CallAvailableIn, new Object[]{1, 0}));
            goto L120
        L138:
            if (r110 != 2) goto L184;
            int r4 = this.nextType;
            if (r4 == 2) goto L149;
            int r11 = 17;
            int r86 = 16;
            if (r4 == 17) goto L150;
            if (r4 == 16) goto L150;
            if (r4 == 4) goto L150;
            if (r4 == 3) goto L150;
            int r42 = 4;
        L185:
            if (r110 != r42) goto L220;
            int r43 = this.nextType;
            if (r43 == 2) goto L197;
            int r119 = 17;
            if (r43 != 17) goto L191;
        L196:
            int r87 = 16;
        L198:
            if (r43 == 2) goto L203;
            if (r43 == r119) goto L203;
            if (r43 == r87) goto L203;
            this.timeText.setText(LocaleController.formatString("CallAvailableIn", org.telegram.messenger.R.string.CallAvailableIn, new Object[]{2, 0}));
        L205:
            if (this.time >= 1000) goto L207;
            boolean r44 = true;
        L208:
            setProblemTextVisible(r44);
            LoadingTextView r120 = this.timeText;
            if (this.time >= 1000) goto L211;
            int r23 = 8;
        L212:
            r120.setVisibility(r23);
            LoadingTextView r121 = this.problemText;
            if (r121 != null) goto L215;
        L219:
            createTimer();
            goto L247
        L215:
            if (this.time >= 1000) goto L217;
            int r1110 = 0;
        L218:
            r121.setVisibility(r1110);
            goto L219
        L217:
            r1110 = 8;
            goto L218
        L211:
            r23 = 0;
            goto L212
        L207:
            r44 = false;
        L203:
            this.timeText.setText(LocaleController.formatString("SmsAvailableIn", org.telegram.messenger.R.string.SmsAvailableIn, new Object[]{1, 0}));
            goto L205
        L191:
            if (r43 == 11) goto L196;
            r87 = 16;
            if (r43 != 16) goto L220;
        L197:
            r87 = 16;
            r119 = 17;
        L220:
            if (r110 != 11) goto L242;
            int r124 = this.nextType;
            if (r124 == 4) goto L229;
            if (r124 == 2) goto L229;
            if (r124 == 17) goto L229;
            if (r124 == 16) goto L229;
            if (r124 != 11) goto L247;
        L229:
            setProblemTextVisible(false);
            this.timeText.setVisibility(0);
            this.problemText.setVisibility(8);
            int r125 = this.nextType;
            if (r125 != 4) goto L232;
        L240:
            this.timeText.setText(LocaleController.formatString("CallAvailableIn", org.telegram.messenger.R.string.CallAvailableIn, new Object[]{1, 0}));
        L241:
            createTimer();
            goto L247
        L232:
            if (r125 == 11) goto L240;
            if (r125 != 2) goto L236;
        L239:
            this.timeText.setText(LocaleController.formatString("SmsAvailableIn", org.telegram.messenger.R.string.SmsAvailableIn, new Object[]{1, 0}));
            goto L241
        L236:
            if (r125 == 17) goto L239;
            if (r125 != 16) goto L241;
        L242:
            this.timeText.setVisibility(8);
            LoadingTextView r126 = this.problemText;
            if (r126 == null) goto L245;
            r126.setVisibility(0);
        L245:
            setProblemTextVisible(false);
            createCodeTimer();
        L150:
            if (r4 == 2) goto L155;
            if (r4 == r11) goto L155;
            if (r4 == r86) goto L155;
            this.timeText.setText(LocaleController.formatString("CallAvailableIn", org.telegram.messenger.R.string.CallAvailableIn, new Object[]{2, 0}));
        L157:
            if (this.time >= 1000) goto L159;
            boolean r127 = true;
        L160:
            setProblemTextVisible(r127);
            LoadingTextView r128 = this.timeText;
            if (this.time >= 1000) goto L163;
            int r24 = 8;
        L164:
            r128.setVisibility(r24);
            LoadingTextView r129 = this.problemText;
            if (r129 != null) goto L167;
        L171:
            SharedPreferences r130 = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0);
            String r25 = r130.getString("sms_hash", null);
            if (TextUtils.isEmpty(r25) == true) goto L180;
            String r131 = r130.getString("sms_hash_code", null);
            if (r131 == null) goto L180;
            if (r131.contains(r25 + "|") == false) goto L180;
            if (LoginActivity.m15795$$Nest$fgetnewAccount(this.this$0) == true) goto L180;
            String r32 = r131.substring(r131.indexOf(124) + 1);
        L181:
            if (r32 == null) goto L183;
            this.codeFieldContainer.setCode(r32);
            onNextPressed(null);
            goto L247
        L183:
            createTimer();
        L180:
            r32 = null;
            goto L181
        L167:
            if (this.time >= 1000) goto L169;
            int r1111 = 0;
        L170:
            r129.setVisibility(r1111);
            goto L171
        L169:
            r1111 = 8;
            goto L170
        L163:
            r24 = 0;
            goto L164
        L159:
            r127 = false;
        L155:
            this.timeText.setText(LocaleController.formatString("SmsAvailableIn", org.telegram.messenger.R.string.SmsAvailableIn, new Object[]{1, 0}));
            goto L157
        L149:
            r86 = 16;
            r11 = 17;
            goto L150
        L184:
            r42 = 4;
            goto L185
        L91:
            AndroidUtilities.hideKeyboard(this.codeFieldContainer.codeField[0]);
            goto L92
        L58:
            int r132 = this.currentType;
            if (r132 != 1) goto L61;
            r123 = AndroidUtilities.replaceTags(LocaleController.formatString("SentAppCodeWithPhone", org.telegram.messenger.R.string.SentAppCodeWithPhone, new Object[]{LocaleController.addNbsp(r84)}));
            goto L57
        L61:
            if (r132 != 2) goto L64;
            r123 = AndroidUtilities.replaceTags(LocaleController.formatString("SentSmsCode", org.telegram.messenger.R.string.SentSmsCode, new Object[]{LocaleController.addNbsp(r84)}));
            goto L57
        L64:
            if (r132 != 3) goto L66;
            r123 = AndroidUtilities.replaceTags(LocaleController.formatString("SentCallCode", org.telegram.messenger.R.string.SentCallCode, new Object[]{LocaleController.addNbsp(r84)}));
            goto L57
        L66:
            if (r132 != 4) goto L69;
            r123 = AndroidUtilities.replaceTags(LocaleController.formatString("SentCallOnly", org.telegram.messenger.R.string.SentCallOnly, new Object[]{LocaleController.addNbsp(r84)}));
            goto L57
        L69:
            if (r132 != 15) goto L71;
            r17 = AndroidUtilities.replaceTags(LocaleController.formatString("SentFragmentCode", org.telegram.messenger.R.string.SentFragmentCode, new Object[]{LocaleController.addNbsp(r84)}));
            goto L72
        L71:
            r17 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L72
        L272:
            return;
        L43:
            TextView r133 = this.prevTypeTextView;
            if (r83 != 16) goto L46;
            r133.setVisibility(0);
            this.prevTypeTextView.setText(AndroidUtilities.replaceArrows(LocaleController.getString(org.telegram.messenger.R.string.BackEnteringWord), true, AndroidUtilities.dp(-1.0f), AndroidUtilities.dp(r162)));
            goto L48
        L46:
            r133.setVisibility(8);
            goto L48
        L21:
            if (r12 != 2) goto L23;
            AndroidUtilities.setWaitingForSms(true);
            NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.didReceiveSmsCode);
            goto L27
        L23:
            if (r12 != 3) goto L27;
            AndroidUtilities.setWaitingForCall(true);
            NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.didReceiveCall);
            if (r21 == false) goto L27;
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda6());
            goto L27
        L5:
            if (this.nextCodeParams != null) goto L7;
            return;
        L7:
            if (this.nextCodeAuth == null) goto L269;
            setProblemTextVisible(true);
            this.timeText.setVisibility(8);
            LoadingTextView r134 = this.problemText;
            if (r134 == null) goto L270;
            r134.setVisibility(0);
            this.problemText.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteValueText));
            int r135 = this.nextType;
            if (r135 != 17) goto L13;
            int r136 = org.telegram.messenger.R.string.ReturnEnteringPhrase;
        L16:
            this.problemText.setText(AndroidUtilities.replaceArrows(LocaleController.getString(r136), true, AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f)));
            return;
        L13:
            if (r135 != 16) goto L15;
            r136 = org.telegram.messenger.R.string.ReturnEnteringWord;
            goto L16
        L15:
            r136 = org.telegram.messenger.R.string.ReturnEnteringSMS;
            goto L16
        L270:
            return;
        }

        @Override // org.telegram.ui.Components.SlideView
        public void updateColors() {
            TextView r0 = this.confirmTextView;
            if (LoginActivity.m15825$$Nest$misInCancelAccountDeletionMode(this.this$0) == false) goto L5;
            int r1 = Theme.key_windowBackgroundWhiteBlackText;
        L6:
            r0.setTextColor(Theme.getColor(r1));
            this.confirmTextView.setLinkTextColor(Theme.getColor(Theme.key_chats_actionBackground));
            TextView r02 = this.titleTextView;
            int r12 = Theme.key_windowBackgroundWhiteBlackText;
            r02.setTextColor(Theme.getColor(r12));
            if (this.currentType != 11) goto L9;
            TextView r03 = this.missedCallDescriptionSubtitle;
            int r2 = Theme.key_windowBackgroundWhiteGrayText;
            r03.setTextColor(Theme.getColor(r2));
            this.missedCallDescriptionSubtitle2.setTextColor(Theme.getColor(r2));
            ImageView r04 = this.missedCallArrowIcon;
            int r3 = Theme.getColor(Theme.key_windowBackgroundWhiteInputFieldActivated);
            PorterDuff.Mode r4 = PorterDuff.Mode.SRC_IN;
            r04.setColorFilter(new PorterDuffColorFilter(r3, r4));
            this.missedCallPhoneIcon.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r12), r4));
            this.prefixTextView.setTextColor(Theme.getColor(r12));
        L9:
            applyLottieColors(this.hintDrawable);
            applyLottieColors(this.starsToDotsDrawable);
            applyLottieColors(this.dotsDrawable);
            applyLottieColors(this.dotsToStarsDrawable);
            CodeFieldContainer r05 = this.codeFieldContainer;
            if (r05 == null) goto L12;
            r05.invalidate();
        L12:
            Integer r06 = (Integer) this.timeText.getTag();
            if (r06 != null) goto L15;
            r06 = Integer.valueOf(Theme.key_windowBackgroundWhiteGrayText6);
        L15:
            this.timeText.setTextColor(Theme.getColor(r06.intValue()));
            if (this.currentType == 15) goto L18;
            this.problemText.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText4));
        L18:
            this.wrongCode.setTextColor(Theme.getColor(Theme.key_text_RedBold));
            return;
        L5:
            r1 = Theme.key_windowBackgroundWhiteGrayText6;
            goto L6
        }
    }

    public class LoginPayView extends SlideView {
        private ButtonWithCounterView button;
        private ExplainStarsSheet.FeatureCell[] cells;
        private String lastError;
        private ImageView optionsButton;
        private Bundle params;
        private boolean polling;
        private long pollingFormId;
        private String pollingPhoneCodeHash;
        private String pollingPhoneNumber;
        private int pollingRequestId;
        private StarParticlesView starParticlesView;
        final /* synthetic */ LoginActivity this$0;

        /* renamed from: org.telegram.ui.LoginActivity$LoginPayView$1, reason: invalid class name */
        public class AnonymousClass1 extends StarParticlesView {
            final /* synthetic */ LoginPayView this$1;
            final /* synthetic */ LoginActivity val$this$0;

            public AnonymousClass1(LoginPayView r1, Context r2, LoginActivity r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            @Override // org.telegram.ui.Components.Premium.StarParticlesView
            public void configure() {
                StarParticlesView.Drawable r0 = this.drawable;
                r0.useGradient = true;
                r0.useBlur = false;
                r0.checkBounds = true;
                r0.isCircle = true;
                r0.centerOffsetY = AndroidUtilities.dp(-14.0f);
                StarParticlesView.Drawable r3 = this.drawable;
                r3.minLifeTime = 2000;
                r3.randLifeTime = 3000;
                r3.size1 = 16;
                r3.useRotate = false;
                r3.type = 28;
                r3.colorKey = Theme.key_premiumGradient2;
                r3.init();
            }

            @Override // org.telegram.ui.Components.Premium.StarParticlesView, android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(r2, r3);
                this.drawable.rect2.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight() - AndroidUtilities.dp(52.0f));
            }
        }

        /* renamed from: org.telegram.ui.LoginActivity$LoginPayView$2, reason: invalid class name */
        public class AnonymousClass2 extends GLIconTextureView {
            final /* synthetic */ LoginPayView this$1;
            final /* synthetic */ LoginActivity val$this$0;

            public AnonymousClass2(LoginPayView r1, Context r2, int r3, int r4, LoginActivity r5) {
                this.this$1 = r1;
                this.val$this$0 = r5;
                super(r2, r3, r4);
            }

            @Override // org.telegram.ui.Components.Premium.GLIcon.GLIconTextureView, android.view.TextureView, android.view.View
            public void onAttachedToWindow() {
                super.onAttachedToWindow();
                setPaused(false);
            }

            @Override // org.telegram.ui.Components.Premium.GLIcon.GLIconTextureView, android.view.View
            public void onDetachedFromWindow() {
                super.onDetachedFromWindow();
                setPaused(true);
            }
        }

        /* renamed from: $r8$lambda$-OSZLbxHsZ2dfLJjsT34bWRkyvA, reason: not valid java name */
        public static /* synthetic */ void m15959$r8$lambda$OSZLbxHsZ2dfLJjsT34bWRkyvA(Runnable r0) {
            r0.run();
        }

        public static /* synthetic */ void $r8$lambda$7QeL_YEQuD8_aCJ12AczdSYbQZ8(LoginPayView r0, TLObject r1, TLRPC.TL_error r2, ProductDetails.OneTimePurchaseOfferDetails r3, int r4, ProductDetails r5, TLRPC.TL_inputStorePaymentAuthCode r6, String r7, TLRPC.TL_payments_canPurchaseStore r8) {
            r0.lambda$setParams$26(r1, r2, r3, r4, r5, r6, r7, r8);
        }

        public static /* synthetic */ void $r8$lambda$DVs7mRwM7jtY0AYXACXphxWHTqQ(LoginPayView r0, TLRPC.TL_inputStorePaymentAuthCode r1, TL_update.TL_updateSentPhoneCode r2) {
            r0.lambda$setParams$19(r1, r2);
        }

        /* renamed from: $r8$lambda$DnI5Y6-tQUVF4tQue-v2YVroc8M, reason: not valid java name */
        public static /* synthetic */ void m15960$r8$lambda$DnI5Y6tQUVF4tQuev2YVroc8M(LoginPayView r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$poll$32(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$G1dM2jQkLryr7Jqiu0jtIP7yslk(LoginPayView r0) {
            r0.lambda$setParams$20();
        }

        public static /* synthetic */ void $r8$lambda$KCk3lclMZJE6br4XAXnrnkyBSoQ(LoginPayView r0) {
            r0.lambda$new$0();
        }

        public static /* synthetic */ void $r8$lambda$MMMgGcArebh0TiH2jec17Adf_vY(LoginPayView r0, String r1, TLRPC.TL_inputStorePaymentAuthCode r2, TLRPC.TL_payments_canPurchaseStore r3, Runnable r4, BillingResult r5, List r6) {
            r0.lambda$setParams$24(r1, r2, r3, r4, r5, r6);
        }

        public static /* synthetic */ void $r8$lambda$QMvgEcQAwYHxhlhL0s1cP_47CKI(LoginPayView r0, String r1) {
            r0.lambda$setParams$13(r1);
        }

        public static /* synthetic */ void $r8$lambda$QS97KYWCZDCyFcEic1NGuSuZCnY(LoginPayView r0, ProductDetails r1, TLRPC.TL_inputStorePaymentAuthCode r2, String r3, TLRPC.TL_payments_canPurchaseStore r4, View r5) {
            r0.lambda$setParams$25(r1, r2, r3, r4, r5);
        }

        public static /* synthetic */ void $r8$lambda$Quqyw9begn4vFn3x_kRODI7Rb64(LoginPayView r0, ProductDetails r1, Utilities.Callback r2, TLRPC.TL_inputStorePaymentAuthCode r3) {
            r0.lambda$setParams$18(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$Rs9ilFM2aQhEcpYoiu7WCTqffJ8(LoginPayView r0, String r1, long r2, String r4, String r5, int r6, View r7) {
            r0.lambda$setParams$12(r1, r2, r4, r5, r6, r7);
        }

        /* renamed from: $r8$lambda$SWUKr0m-RXWObZOGASPOAjH5Zz8, reason: not valid java name */
        public static /* synthetic */ void m15961$r8$lambda$SWUKr0mRXWObZOGASPOAjH5Zz8(LoginPayView r0, BillingResult r1, List r2, String r3, TLRPC.TL_inputStorePaymentAuthCode r4, TLRPC.TL_payments_canPurchaseStore r5, Runnable r6) {
            r0.lambda$setParams$23(r1, r2, r3, r4, r5, r6);
        }

        public static /* synthetic */ void $r8$lambda$StIEVNFM5dyWMZkmjxt31zghRZg(LoginPayView r0) {
            r0.lambda$setParams$9();
        }

        public static /* synthetic */ void $r8$lambda$ULXezlBtNDiqJvp4h9L48xPptWA(LoginPayView r0, TLRPC.TL_inputInvoicePremiumAuthCode r1, TLRPC.TL_inputStorePaymentAuthCode r2, TLObject r3, TLRPC.TL_error r4) {
            r0.lambda$setParams$11(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$UbLvUkQ_2IabMVtStaREm2s9deU(LoginPayView r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$poll$31(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$WNwNns9BGY9ywNV1JHuMAxTQ_l0(LoginPayView r0, TLObject r1, TLRPC.TL_inputInvoicePremiumAuthCode r2, TLRPC.TL_inputStorePaymentAuthCode r3, TLRPC.TL_error r4) {
            r0.lambda$setParams$10(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$WwSAoNODL77G0c8CIjBQKdpBBFs(Utilities.Callback r0, String r1) {
            r0.run(r1);
        }

        public static /* synthetic */ Boolean $r8$lambda$_ADveuHHl5R9Z7dViIIn5TFDwSY(LoginPayView r0, TLRPC.TL_error r1) {
            return r0.lambda$setParams$8(r1);
        }

        public static /* synthetic */ void $r8$lambda$chO9LNGqSbqo21NG7cgsaIFutmk(LoginPayView r0, String r1, String r2, String r3, int r4) {
            r0.lambda$setParams$30(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$f6V1g7fWPBHGQHpxk2fgip2r30g(LoginPayView r0, String r1, String r2, String r3, int r4, BillingResult r5, List r6) {
            r0.lambda$setParams$29(r1, r2, r3, r4, r5, r6);
        }

        /* renamed from: $r8$lambda$fAM-1VfcHj8hYxAfUp1E7BVLJiM, reason: not valid java name */
        public static /* synthetic */ void m15962$r8$lambda$fAM1VfcHj8hYxAfUp1E7BVLJiM(LoginPayView r0, ProductDetails.OneTimePurchaseOfferDetails r1, int r2, ProductDetails r3, TLRPC.TL_inputStorePaymentAuthCode r4, String r5, TLRPC.TL_payments_canPurchaseStore r6, TLObject r7, TLRPC.TL_error r8) {
            r0.lambda$setParams$27(r1, r2, r3, r4, r5, r6, r7, r8);
        }

        public static /* synthetic */ void $r8$lambda$fq6uxIQ9wEL2JQd60AEKEoiv0vo(LoginPayView r0) {
            r0.poll();
        }

        /* renamed from: $r8$lambda$hlwyX-Fkb7WtkJSRh1L7vYlFwSE, reason: not valid java name */
        public static /* synthetic */ void m15963$r8$lambda$hlwyXFkb7WtkJSRh1L7vYlFwSE(LoginPayView r0, TLRPC.TL_inputStorePaymentAuthCode r1, TLRPC.PaymentForm r2) {
            r0.lambda$setParams$5(r1, r2);
        }

        /* renamed from: $r8$lambda$jDqJBe0oiju7lupWudY_-kCqOPA, reason: not valid java name */
        public static /* synthetic */ void m15964$r8$lambda$jDqJBe0oiju7lupWudY_kCqOPA(LoginPayView r0, TLRPC.TL_inputStorePaymentAuthCode r1, TLRPC.PaymentForm r2, TLRPC.TL_payments_paymentResult r3) {
            r0.lambda$setParams$4(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$lu6-oYFkj2upFh5OKS_24M0iLck, reason: not valid java name */
        public static /* synthetic */ void m15965$r8$lambda$lu6oYFkj2upFh5OKS_24M0iLck(LoginPayView r0) {
            r0.lambda$setParams$7();
        }

        public static /* synthetic */ void $r8$lambda$nhQpHYovNHcTGlKUlguxig_kThY(LoginPayView r0, TLRPC.TL_inputStorePaymentAuthCode r1, TLRPC.PaymentForm r2) {
            r0.lambda$setParams$3(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$pAGp1uDnxfAJNNKcngfU5B1nZnE(Utilities.Callback r1, BillingResult r2) {
            if (r2.getResponseCode() != 0) goto L5;
            String r22 = null;
        L6:
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginPayView$$ExternalSyntheticLambda1(r1, r22));
            return;
        L5:
            r22 = BillingController.getResponseCodeString(r2.getResponseCode());
            goto L6
        }

        /* renamed from: $r8$lambda$pmJgeieoRL6l-ZZVps7fRMqXZlE, reason: not valid java name */
        public static /* synthetic */ void m15966$r8$lambda$pmJgeieoRL6lZZVps7fRMqXZlE(LoginPayView r0, String r1, String r2, String r3, View r4) {
            r0.lambda$setParams$2(r1, r2, r3, r4);
        }

        /* renamed from: $r8$lambda$qHYVEbEjN4FuFM-p8vQRurNAZd8, reason: not valid java name */
        public static /* synthetic */ void m15967$r8$lambda$qHYVEbEjN4FuFMp8vQRurNAZd8(LoginPayView r0, String r1, BillingResult r2, List r3, String r4, String r5, int r6) {
            r0.lambda$setParams$28(r1, r2, r3, r4, r5, r6);
        }

        public static /* synthetic */ void $r8$lambda$t51F0XiCL7UqeBnAVnNReVfAiiU(LoginPayView r0, TLRPC.TL_inputStorePaymentAuthCode r1, Purchase r2, TLRPC.TL_payments_canPurchaseStore r3, Runnable r4, TLObject r5, TLRPC.TL_error r6) {
            r0.lambda$setParams$22(r1, r2, r3, r4, r5, r6);
        }

        public static /* synthetic */ void $r8$lambda$wDUFaR2M3bHW6YlYsgwyD4FRAaU(LoginPayView r0, TLRPC.TL_inputStorePaymentAuthCode r1, TLRPC.PaymentForm r2, TLRPC.payments_PaymentResult r3) {
            r0.lambda$setParams$6(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$yNahq-zl1g2_5-HSBCh1gaI7J9M, reason: not valid java name */
        public static /* synthetic */ void m15968$r8$lambda$yNahqzl1g2_5HSBCh1gaI7J9M(LoginPayView r0, String r1, String r2, String r3) {
            r0.lambda$setParams$1(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$ydlzc44dHax8F_tQLXz7-8rekr4, reason: not valid java name */
        public static /* synthetic */ void m15969$r8$lambda$ydlzc44dHax8F_tQLXz78rekr4(Utilities.Callback r1) {
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginPayView$$ExternalSyntheticLambda2(r1));
        }

        /* renamed from: $r8$lambda$yrcnawU5Hb7J-Esgd7ncKM6Qe-o, reason: not valid java name */
        public static /* synthetic */ void m15970$r8$lambda$yrcnawU5Hb7JEsgd7ncKM6Qeo(Utilities.Callback r1) {
            r1.run("CANCELLED");
        }

        public LoginPayView(LoginActivity r19, Context r20) {
            this.this$0 = r19;
            super(r20);
            this.cells = new ExplainStarsSheet.FeatureCell[3];
            this.pollingRequestId = -1;
            setOrientation(1);
            setClipChildren(false);
            setClipToPadding(false);
            setPadding(0, 0, 0, AndroidUtilities.dp(16.0f));
            FrameLayout r8 = new FrameLayout(r20);
            r8.setClipChildren(false);
            r8.setClipToPadding(false);
            addView(r8, LayoutHelper.createLinear(-1, 200));
            AnonymousClass1 r4 = new AnonymousClass1(this, r20, r19);
            this.starParticlesView = r4;
            r8.addView(r4, LayoutHelper.createFrame(-1, 200, 119));
            ImageView r0 = new ImageView(r20);
            this.optionsButton = r0;
            r0.setImageResource(org.telegram.messenger.R.drawable.ic_ab_other);
            this.optionsButton.setScaleType(ImageView.ScaleType.CENTER);
            ImageView r02 = this.optionsButton;
            int r10 = Theme.key_windowBackgroundWhiteBlackText;
            r02.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r10, LoginActivity.access$4400(r19)), PorterDuff.Mode.SRC_IN));
            this.optionsButton.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_listSelector)));
            r8.addView(this.optionsButton, LayoutHelper.createFrame(32, 32.0f, 53, 0.0f, 16.0f, -2.0f, 0.0f));
            AnonymousClass2 r03 = new AnonymousClass2(this, r20, 1, 1, r19);
            r03.setStarParticlesView(this.starParticlesView);
            Bitmap r3 = Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888);
            Canvas r42 = new Canvas(r3);
            int r5 = Theme.key_premiumGradient2;
            r42.drawColor(ColorUtils.blendARGB(Theme.getColor(r5), Theme.getColor(Theme.key_dialogBackground), 0.5f));
            r03.setBackgroundBitmap(r3);
            GLIconRenderer r32 = r03.mRenderer;
            r32.colorKey1 = r5;
            r32.colorKey2 = Theme.key_premiumGradient1;
            r32.updateColors();
            r8.addView(r03, LayoutHelper.createFrame(160, 160, 1));
            TextView r04 = new TextView(r20);
            r04.setText(LocaleController.getString(org.telegram.messenger.R.string.SMSFeeTitle));
            r04.setTextColor(Theme.getColor(r10));
            r04.setTextSize(1, 20.0f);
            r04.setTypeface(AndroidUtilities.bold());
            r04.setGravity(17);
            r8.addView(r04, LayoutHelper.createFrame(-1, -2.0f, 49, 16.0f, 152.0f, 16.0f, 0.0f));
            this.cells[0] = new ExplainStarsSheet.FeatureCell(r20, 1, LoginActivity.access$4500(r19));
            this.cells[0].set(org.telegram.messenger.R.drawable.menu_high_price, LocaleController.getString(org.telegram.messenger.R.string.SMSFee1Title), LocaleController.getString(org.telegram.messenger.R.string.SMSFee1Text));
            addView(this.cells[0], LayoutHelper.createLinear(-1, -2, 55, 0, 0, 0, 6));
            this.cells[1] = new ExplainStarsSheet.FeatureCell(r20, 1, LoginActivity.access$4600(r19));
            this.cells[1].set(org.telegram.messenger.R.drawable.menu_feature_code, LocaleController.getString(org.telegram.messenger.R.string.SMSFee2Title), LocaleController.getString(org.telegram.messenger.R.string.SMSFee2Text));
            addView(this.cells[1], LayoutHelper.createLinear(-1, -2, 55, 0, 0, 0, 6));
            this.cells[2] = new ExplainStarsSheet.FeatureCell(r20, 1, LoginActivity.access$4700(r19));
            this.cells[2].set(org.telegram.messenger.R.drawable.menu_feature_hands, AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.getString(org.telegram.messenger.R.string.SMSFee3Title), new LoginActivity$LoginPayView$$ExternalSyntheticLambda9(this)), true, AndroidUtilities.dp(2.6666667f), AndroidUtilities.dp(1.0f)), LocaleController.getString(org.telegram.messenger.R.string.SMSFee3Text));
            addView(this.cells[2], LayoutHelper.createLinear(-1, -2, 55, 0, 0, 0, 6));
            addView(new Space(r20), LayoutHelper.createLinear(0, 0, 1.0f, 119));
            ButtonWithCounterView r05 = new ButtonWithCounterView(r20, null).setRound();
            this.button = r05;
            r05.setLoading(true);
            addView(this.button, LayoutHelper.createLinear(-1, 48, 7, 0, 16, 0, 16));
        }

        private void closeAllPaymentFormActivities() {
            INavigationLayout r7 = this.this$0.getParentLayout();
            if (r7 != null) goto L5;
            return;
        L5:
            if (r7.getFragmentStack() == null) goto L30;
            List<BaseFragment> r0 = r7.getFragmentStack();
            if (r0.isEmpty() == false) goto L10;
            BaseFragment r1 = null;
        L11:
            ArrayList r3 = new ArrayList(r0);
            int r02 = r3.size();
            int r4 = 0;
        L12:
            if (r4 >= r02) goto L18;
            Object r5 = r3.get(r4);
            r4 = r4 + 1;
            BaseFragment r52 = (BaseFragment) r5;
            if ((r52 instanceof PaymentFormActivity) == false) goto L12;
            if (r52 == r1) goto L12;
            r52.removeSelfFromStack();
            goto L12
        L18:
            if ((r1 instanceof PaymentFormActivity) == false) goto L29;
            r7.closeLastFragment(true);
            return;
        L29:
            return;
        L10:
            r1 = r0.get(r0.size() - 1);
            goto L11
        }

        private /* synthetic */ void lambda$new$0() {
            PremiumPreviewFragment r0 = new PremiumPreviewFragment("sms");
            r0.setCurrentAccount(LoginActivity.access$5300(this.this$0));
            this.this$0.presentFragment(r0);
        }

        private /* synthetic */ void lambda$poll$31(TLObject r4, TLRPC.TL_error r5) {
            this.pollingRequestId = -1;
            if ((r4 instanceof TLRPC.auth_SentCode) == false) goto L6;
            this.polling = false;
            this.button.setLoading(false);
            closeAllPaymentFormActivities();
            LoginActivity.m15820$$Nest$mfillNextCodeParams(this.this$0, this.params, (TLRPC.auth_SentCode) r4);
            return;
        L6:
            if (r5 == null) goto L21;
            String r42 = r5.text;
            if (r42 != null) goto L10;
        L13:
            String r43 = r5.text;
            if (r43 != null) goto L16;
        L19:
            this.lastError = r5.text;
            this.polling = false;
            this.button.setLoading(false);
            BulletinFactory.of(LoginActivity.m15803$$Nest$fgetslideViewsContainer(this.this$0), null).createSimpleBulletin(org.telegram.messenger.R.raw.error, LocaleController.formatString(org.telegram.messenger.R.string.UnknownErrorCode, new Object[]{r5.text}));
            return;
        L16:
            if ("PHONE_CODE_EXPIRED".equalsIgnoreCase(r43) == false) goto L19;
            onBackPressed(true);
            this.this$0.setPage(0, true, null, true);
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString(org.telegram.messenger.R.string.CodeExpired));
            return;
        L10:
            if (r42.startsWith("FLOOD_WAIT_") == false) goto L13;
            int r44 = Integer.parseInt(r5.text.substring(11));
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginPayView$$ExternalSyntheticLambda30(this), r44 * 1000);
            return;
        }

        private /* synthetic */ void lambda$poll$32(TLObject r2, TLRPC.TL_error r3) {
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginPayView$$ExternalSyntheticLambda26(this, r2, r3));
        }

        private /* synthetic */ void lambda$setParams$1(String r8, String r9, String r10) {
            PackageInfo r1 = ApplicationLoader.applicationContext.getPackageManager().getPackageInfo(ApplicationLoader.applicationContext.getPackageName(), 0);     // Catch: Exception -> L60
            String r12 = String.format(Locale.US, "%s (%d)", new Object[]{r1.versionName, Integer.valueOf(r1.versionCode)});     // Catch: Exception -> L60
            Intent r2 = new Intent("android.intent.action.SENDTO");     // Catch: Exception -> L60
            r2.setData(Uri.parse("mailto:"));     // Catch: Exception -> L60
            if (TextUtils.isEmpty(r8) == true) goto L7;
            r2.putExtra("android.intent.extra.EMAIL", new String[]{r8});     // Catch: Exception -> L60
        L10:
            if (TextUtils.isEmpty(r9) == true) goto L12;
            r2.putExtra("android.intent.extra.SUBJECT", r9);     // Catch: Exception -> L60
        L13:
            StringBuilder r82 = new StringBuilder();     // Catch: Exception -> L60
            r82.append("Technical Details (PLEASE DO NOT EDIT OR REMOVE)\n");     // Catch: Exception -> L60
            r82.append("Device: ");     // Catch: Exception -> L60
            r82.append(Build.MANUFACTURER);     // Catch: Exception -> L60
            r82.append(" ");     // Catch: Exception -> L60
            r82.append(Build.MODEL);     // Catch: Exception -> L60
            r82.append("\n");     // Catch: Exception -> L60
            r82.append("OS version: SDK ");     // Catch: Exception -> L60
            int r92 = Build.VERSION.SDK_INT;     // Catch: Exception -> L60
            r82.append(r92);     // Catch: Exception -> L60
            r82.append("\n");     // Catch: Exception -> L60
            r82.append("Locale: ");     // Catch: Exception -> L60
            r82.append(Locale.getDefault());     // Catch: Exception -> L60
            r82.append("\n");     // Catch: Exception -> L60
            r82.append("\n");     // Catch: Exception -> L60
            r82.append("Target Phone: +");     // Catch: Exception -> L60
            r82.append(r10);     // Catch: Exception -> L60
            r82.append("\n");     // Catch: Exception -> L60
            r82.append("\n");     // Catch: Exception -> L60
            SubscriptionManager r102 = SubscriptionManager.from(getContext());     // Catch: Exception -> L17
            if (r92 < 30) goto L19;
            List<SubscriptionInfo> r3 = r102.getCompleteActiveSubscriptionInfoList();     // Catch: Exception -> L17
        L20:
            if (r3 == null) goto L24;
            if (r3.isEmpty() == true) goto L24;
        L26:
            if (r3 != null) goto L28;
        L29:
            r3 = r102.getActiveSubscriptionInfoList();     // Catch: Exception -> L17
        L30:
            if (r3 == null) goto L45;
            Iterator<SubscriptionInfo> r93 = r3.iterator();     // Catch: Exception -> L17
        L32:
            if (r93.hasNext() == false) goto L45;
            SubscriptionInfo r103 = r93.next();     // Catch: Exception -> L17
            String r32 = r103.getNumber();     // Catch: Exception -> L17
            if (TextUtils.isEmpty(r32) == true) goto L32;
            String r4 = "SIM" + r103.getSimSlotIndex();     // Catch: Exception -> L17
            r82.append(r4);     // Catch: Exception -> L17
            r82.append(".Phone: ");     // Catch: Exception -> L17
            r82.append(r32);     // Catch: Exception -> L17
            r82.append("\n");     // Catch: Exception -> L17
            r82.append(r4);     // Catch: Exception -> L17
            r82.append(".MCC: ");     // Catch: Exception -> L17
            r82.append(r103.getMcc());     // Catch: Exception -> L17
            r82.append("\n");     // Catch: Exception -> L17
            r82.append(r4);     // Catch: Exception -> L17
            r82.append(".MNC: ");     // Catch: Exception -> L17
            r82.append(r103.getMnc());     // Catch: Exception -> L17
            r82.append("\n");     // Catch: Exception -> L17
            r82.append(r4);     // Catch: Exception -> L17
            r82.append(".Carrier: ");     // Catch: Exception -> L17
            if (TextUtils.isEmpty(r103.getCarrierName()) == false) goto L39;
            CharSequence r104 = "unknown";
        L40:
            r82.append(r104);     // Catch: Exception -> L17
            r82.append("\n\n");     // Catch: Exception -> L17
            goto L32
        L39:
            r104 = r103.getCarrierName();     // Catch: Exception -> L17
            goto L40
        L45:
            if (Build.VERSION.SDK_INT >= 29) goto L65;
            r82.append("Signal: unknown\n");     // Catch: Exception -> L60
        L55:
            r82.append("Wi-Fi: ");     // Catch: Exception -> L60
            r82.append(AndroidUtilities.isWifiEnabled(getContext()));     // Catch: Exception -> L60
            r82.append("\n");     // Catch: Exception -> L60
            r82.append("Airplane Mode: ");     // Catch: Exception -> L60
            r82.append(AndroidUtilities.isInAirplaneMode(getContext()));     // Catch: Exception -> L60
            r82.append("\n");     // Catch: Exception -> L60
            r82.append("\n");     // Catch: Exception -> L60
            r82.append("App: ");     // Catch: Exception -> L60
            r82.append(BuildVars.getExteraAppId());     // Catch: Exception -> L60
            r82.append("\n");     // Catch: Exception -> L60
            r82.append("App version: ");     // Catch: Exception -> L60
            r82.append(r12);     // Catch: Exception -> L60
            r82.append("\n");     // Catch: Exception -> L60
            r82.append("\n");     // Catch: Exception -> L60
            r82.append("Issue: ");     // Catch: Exception -> L60
            r82.append("billing_issue");     // Catch: Exception -> L60
            r82.append("\n");     // Catch: Exception -> L60
            if (TextUtils.isEmpty(this.lastError) == true) goto L58;
            r82.append("Error: ");     // Catch: Exception -> L60
            r82.append(this.lastError);     // Catch: Exception -> L60
            r82.append("\n");     // Catch: Exception -> L60
        L58:
            r82.append("\n\n================================================\n");     // Catch: Exception -> L60
            r82.append("WRITE YOUR COMMENT HERE:\n");     // Catch: Exception -> L60
            r82.append("\n");     // Catch: Exception -> L60
            r82.append("\n");     // Catch: Exception -> L60
            r2.putExtra("android.intent.extra.TEXT", r82.toString());     // Catch: Exception -> L60
            getContext().startActivity(Intent.createChooser(r2, "Send email..."));     // Catch: Exception -> L60
            return;
        L65:
            TelephonyManager r94 = (TelephonyManager) getContext().getSystemService(TelephonyManager.class);     // Catch: Exception -> L49
            ConnectivityManager r105 = (ConnectivityManager) getContext().getSystemService(ConnectivityManager.class);     // Catch: Exception -> L49
            SignalStrength r95 = r94.getSignalStrength();     // Catch: Exception -> L49
            if (r95 == null) goto L51;
            r82.append("Signal: ");     // Catch: Exception -> L49
            r82.append(r95.getLevel());     // Catch: Exception -> L49
            r82.append("/4\n");     // Catch: Exception -> L49
        L64:
        L51:
            r82.append("Signal: unknown\n");     // Catch: Exception -> L49
        L49:
            e = move-exception;
            FileLog.e(e);     // Catch: Exception -> L60
            goto L55
        L28:
            if (r3.isEmpty() == false) goto L30;
        L24:
            if (r92 < 28) goto L26;
            r3 = r102.getAccessibleSubscriptionInfoList();     // Catch: Exception -> L17
            goto L26
        L19:
            r3 = null;
        L17:
            e = move-exception;
            FileLog.e(e);     // Catch: Exception -> L60
            goto L45
        L12:
            r2.putExtra("android.intent.extra.SUBJECT", "Android Registration/Login Billing Issue #billing_issue");     // Catch: Exception -> L60
            goto L13
        L7:
            r2.putExtra("android.intent.extra.EMAIL", new String[]{"sms@telegram.org"});     // Catch: Exception -> L60
        L60:
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.AppName), LocaleController.getString("NoMailInstalled", org.telegram.messenger.R.string.NoMailInstalled));
        }

        private /* synthetic */ void lambda$setParams$10(TLObject r3, TLRPC.TL_inputInvoicePremiumAuthCode r4, TLRPC.TL_inputStorePaymentAuthCode r5, TLRPC.TL_error r6) {
            this.button.setLoading(false);
            if ((r3 instanceof TLRPC.PaymentForm) == false) goto L7;
            TLRPC.PaymentForm r32 = (TLRPC.PaymentForm) r3;
            this.this$0.getMessagesController().putUsers(r32.users, false);
            PaymentFormActivity r62 = new PaymentFormActivity(r32, r4, true, this.this$0);
            r62.setCustomResultReceiver(new LoginActivity$LoginPayView$$ExternalSyntheticLambda16(this, r5, r32));
            r62.setCustomAnyResultReceiver(new LoginActivity$LoginPayView$$ExternalSyntheticLambda17(this, r5, r32));
            r62.setCustomErrorReceiver(new LoginActivity$LoginPayView$$ExternalSyntheticLambda18(this));
            this.this$0.presentFragment(r62);
            return;
        L7:
            if (r6 != null) goto L9;
            BulletinFactory.of(LoginActivity.m15803$$Nest$fgetslideViewsContainer(this.this$0), null).createSimpleBulletin(org.telegram.messenger.R.raw.error, LocaleController.getString(org.telegram.messenger.R.string.UnknownError));
            return;
        L9:
            if ("PHONE_CODE_EXPIRED".equalsIgnoreCase(r6.text) == false) goto L12;
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginPayView$$ExternalSyntheticLambda19(this));
            return;
        L12:
            this.lastError = r6.text;
            BulletinFactory.of(LoginActivity.m15803$$Nest$fgetslideViewsContainer(this.this$0), null).createSimpleBulletin(org.telegram.messenger.R.raw.error, LocaleController.formatString(org.telegram.messenger.R.string.UnknownErrorCode, new Object[]{r6.text}));
        }

        private /* synthetic */ void lambda$setParams$11(TLRPC.TL_inputInvoicePremiumAuthCode r7, TLRPC.TL_inputStorePaymentAuthCode r8, TLObject r9, TLRPC.TL_error r10) {
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginPayView$$ExternalSyntheticLambda14(this, r9, r7, r8, r10));
        }

        private /* synthetic */ void lambda$setParams$12(String r2, long r3, String r5, String r6, int r7, View r8) {
            if (this.button.isLoading() == false) goto L5;
            return;
        L5:
            this.button.setLoading(true);
            TLRPC.TL_inputStorePaymentAuthCode r82 = new TLRPC.TL_inputStorePaymentAuthCode();
            r82.currency = r2;
            r82.amount = r3;
            if (TextUtils.isEmpty(r5) == false) goto L8;
            r5 = _UrlKt.FRAGMENT_ENCODE_SET;
        L8:
            r82.phone_code_hash = r5;
            r82.phone_number = r6;
            r82.premium_days = r7;
            TLRPC.TL_inputInvoicePremiumAuthCode r22 = new TLRPC.TL_inputInvoicePremiumAuthCode();
            r22.purpose = r82;
            TLRPC.TL_payments_getPaymentForm r32 = new TLRPC.TL_payments_getPaymentForm();
            r32.invoice = r22;
            JSONObject r4 = BotWebViewSheet.makeThemeParams(null);
            if (r4 == null) goto L11;
            TLRPC.TL_dataJSON r52 = new TLRPC.TL_dataJSON();
            r32.theme_params = r52;
            r52.data = r4.toString();
            r32.flags |= 1;
        L11:
            this.this$0.getConnectionsManager().sendRequest(r32, new LoginActivity$LoginPayView$$ExternalSyntheticLambda11(this, r22, r82), 74);
        }

        private /* synthetic */ void lambda$setParams$13(String r3) {
            FileLog.d("LoginBilling purchased done " + r3);
            if ("CANCELLED".equalsIgnoreCase(r3) == false) goto L6;
            this.button.setLoading(false);
            return;
        }

        private /* synthetic */ void lambda$setParams$18(ProductDetails r4, Utilities.Callback r5, TLRPC.TL_inputStorePaymentAuthCode r6) {
            LoginActivity.m15816$$Nest$fputpaid(this.this$0, true);
            BillingController.getInstance().addResultListener(r4.getProductId(), new LoginActivity$LoginPayView$$ExternalSyntheticLambda31(r5));
            BillingController.getInstance().setOnCanceled(new LoginActivity$LoginPayView$$ExternalSyntheticLambda32(r5));
            BillingController.getInstance().launchBillingFlow(this.this$0.getParentActivity(), AccountInstance.getInstance(LoginActivity.access$5200(this.this$0)), r6, Collections.singletonList(BillingFlowParams.ProductDetailsParams.newBuilder().setProductDetails(r4).build()));
        }

        private /* synthetic */ void lambda$setParams$19(TLRPC.TL_inputStorePaymentAuthCode r3, TL_update.TL_updateSentPhoneCode r4) {
            LoginActivity.m15816$$Nest$fputpaid(this.this$0, true);
            LoginActivity r0 = (LoginActivity) LaunchActivity.findFragment(LoginActivity.class);
            if (r0 != null) goto L7;
            r0 = new LoginActivity(LoginActivity.access$5100(this.this$0));
            BaseFragment r2 = LaunchActivity.getSafeLastFragment();
            if (r2 == null) goto L7;
            r2.presentFragment(r0);
        L7:
            r0.open(r3.phone_number, r4.sent_code);
        }

        private /* synthetic */ void lambda$setParams$2(String r4, String r5, String r6, View r7) {
            ItemOptions.makeOptions(this.this$0, this.optionsButton).add(org.telegram.messenger.R.drawable.msg_help, LocaleController.getString(org.telegram.messenger.R.string.SettingsHelp), new LoginActivity$LoginPayView$$ExternalSyntheticLambda12(this, r4, r5, r6)).setGravity(5).show();
        }

        private /* synthetic */ void lambda$setParams$20() {
            this.button.setLoading(false);
        }

        private /* synthetic */ void lambda$setParams$22(TLRPC.TL_inputStorePaymentAuthCode r5, Purchase r6, TLRPC.TL_payments_canPurchaseStore r7, Runnable r8, TLObject r9, TLRPC.TL_error r10) {
            if ((r9 instanceof TLRPC.Updates) == false) goto L9;
            TLRPC.Updates r92 = (TLRPC.Updates) r9;
            ArrayList r82 = MessagesController.findUpdatesAndRemove(r92, TL_update.TL_updateSentPhoneCode.class);
            int r102 = r82.size();
            int r1 = 0;
        L5:
            if (r1 >= r102) goto L7;
            Object r2 = r82.get(r1);
            r1 = r1 + 1;
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginPayView$$ExternalSyntheticLambda3(this, r5, (TL_update.TL_updateSentPhoneCode) r2));
            goto L5
        L7:
            this.this$0.getMessagesController().processUpdates(r92, false);
            BillingController.getInstance().consumeGiftPurchase(r6, r7.purpose, null);
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginPayView$$ExternalSyntheticLambda4(this));
            return;
        L9:
            if (r10 == null) goto L13;
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginPayView$$ExternalSyntheticLambda5(r8));
            return;
        }

        private /* synthetic */ void lambda$setParams$23(BillingResult r7, List r8, String r9, TLRPC.TL_inputStorePaymentAuthCode r10, TLRPC.TL_payments_canPurchaseStore r11, Runnable r12) {
            if (r7.getResponseCode() != 0) goto L14;
            if (r8 == null) goto L14;
            if (r8.isEmpty() == true) goto L14;
            Iterator r72 = r8.iterator();
        L9:
            if (r72.hasNext() == false) goto L14;
            Purchase r3 = (Purchase) r72.next();
            if (r3.getProducts().contains(r9) == false) goto L9;
            TLRPC.TL_payments_assignPlayMarketTransaction r73 = new TLRPC.TL_payments_assignPlayMarketTransaction();
            TLRPC.TL_dataJSON r82 = new TLRPC.TL_dataJSON();
            r73.receipt = r82;
            r82.data = r3.getOriginalJson();
            r10.restore = true;
            r73.purpose = r10;
            this.this$0.getConnectionsManager().sendRequest(r73, new LoginActivity$LoginPayView$$ExternalSyntheticLambda0(this, r10, r3, r11, r12), 74);
            return;
        L14:
            r12.run();
        }

        private /* synthetic */ void lambda$setParams$24(String r9, TLRPC.TL_inputStorePaymentAuthCode r10, TLRPC.TL_payments_canPurchaseStore r11, Runnable r12, BillingResult r13, List r14) {
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginPayView$$ExternalSyntheticLambda33(this, r13, r14, r9, r10, r11, r12));
        }

        private /* synthetic */ void lambda$setParams$25(ProductDetails r8, TLRPC.TL_inputStorePaymentAuthCode r9, String r10, TLRPC.TL_payments_canPurchaseStore r11, View r12) {
            if (this.button.isLoading() == false) goto L5;
            return;
        L5:
            this.button.setLoading(true);
            LoginActivity$LoginPayView$$ExternalSyntheticLambda27 r122 = new LoginActivity$LoginPayView$$ExternalSyntheticLambda27(this);
            FileLog.d("LoginBilling, querying done purchases...");
            LoginActivity$LoginPayView$$ExternalSyntheticLambda28 r6 = new LoginActivity$LoginPayView$$ExternalSyntheticLambda28(this, r8, r122, r9);
            BillingController.getInstance().queryPurchases("inapp", new LoginActivity$LoginPayView$$ExternalSyntheticLambda29(this, r10, r9, r11, r6));
        }

        private /* synthetic */ void lambda$setParams$26(TLObject r3, TLRPC.TL_error r4, ProductDetails.OneTimePurchaseOfferDetails r5, int r6, ProductDetails r7, TLRPC.TL_inputStorePaymentAuthCode r8, String r9, TLRPC.TL_payments_canPurchaseStore r10) {
            FileLog.d("LoginBilling canPurchaseStore returned " + r3 + " " + r4);
            if ((r3 instanceof TLRPC.TL_boolTrue) == false) goto L11;
            this.button.setText(LocaleController.formatString(org.telegram.messenger.R.string.SMSFeePurchaseTitle, new Object[]{r5.getFormattedPrice()}), false);
            ButtonWithCounterView r32 = this.button;
            if (r6 != 7) goto L7;
            String r42 = LocaleController.getString(org.telegram.messenger.R.string.SMSFeePurchaseText);
        L8:
            r32.setSubText(r42, false);
            this.button.setLoading(false);
            this.button.setOnClickListener(new LoginActivity$LoginPayView$$ExternalSyntheticLambda25(this, r7, r8, r9, r10));
            return;
        L7:
            r42 = LocaleController.formatPluralStringComma("SMSFeePurchaseTextDays", r6);
            goto L8
        L11:
            if ((r3 instanceof TLRPC.TL_boolFalse) == false) goto L14;
            this.lastError = "RESPONSE_FALSE";
            BulletinFactory.of(LoginActivity.m15803$$Nest$fgetslideViewsContainer(this.this$0), null).createSimpleBulletin(org.telegram.messenger.R.raw.error, LocaleController.formatString(org.telegram.messenger.R.string.UnknownErrorCode, new Object[]{"RESPONSE_FALSE"}));
            return;
        L14:
            if (r4 == null) goto L17;
            this.lastError = r4.text;
            BulletinFactory.of(LoginActivity.m15803$$Nest$fgetslideViewsContainer(this.this$0), null).showForError(r4);
            return;
        }

        private /* synthetic */ void lambda$setParams$27(ProductDetails.OneTimePurchaseOfferDetails r11, int r12, ProductDetails r13, TLRPC.TL_inputStorePaymentAuthCode r14, String r15, TLRPC.TL_payments_canPurchaseStore r16, TLObject r17, TLRPC.TL_error r18) {
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginPayView$$ExternalSyntheticLambda22(this, r17, r18, r11, r12, r13, r14, r15, r16));
        }

        private /* synthetic */ void lambda$setParams$28(String r9, BillingResult r10, List r11, String r12, String r13, int r14) {
            FileLog.d("LoginBilling queried \"" + r9 + "\" product: " + BillingController.getResponseCodeString(r10.getResponseCode()));
            if (r10.getResponseCode() == 0) goto L6;
            this.lastError = "BILLING_" + BillingController.getResponseCodeString(r10.getResponseCode());
            BulletinFactory.of(LoginActivity.m15803$$Nest$fgetslideViewsContainer(this.this$0), null).createSimpleBulletin(org.telegram.messenger.R.raw.error, LocaleController.formatString(org.telegram.messenger.R.string.UnknownErrorCode, new Object[]{BillingController.getResponseCodeString(r10.getResponseCode())}));
            return;
        L6:
            if (r11 != null) goto L8;
        L15:
            this.lastError = "PRODUCT_NOT_FOUND";
            BulletinFactory.of(LoginActivity.m15803$$Nest$fgetslideViewsContainer(this.this$0), null).createSimpleBulletin(org.telegram.messenger.R.raw.error, LocaleController.formatString(org.telegram.messenger.R.string.UnknownErrorCode, new Object[]{"PRODUCT_NOT_FOUND"}));
            return;
        L8:
            if (r11.isEmpty() == true) goto L15;
            ProductDetails r4 = (ProductDetails) r11.get(0);
            ProductDetails.OneTimePurchaseOfferDetails r2 = r4.getOneTimePurchaseOfferDetails();
            TLRPC.TL_inputStorePaymentAuthCode r5 = new TLRPC.TL_inputStorePaymentAuthCode();
            r5.currency = r2.getPriceCurrencyCode();
            r5.amount = (long) ((r2.getPriceAmountMicros() / Math.pow(10.0d, 6.0d)) * Math.pow(10.0d, BillingController.getInstance().getCurrencyExp(r5.currency)));
            if (TextUtils.isEmpty(r12) == false) goto L12;
            String r102 = _UrlKt.FRAGMENT_ENCODE_SET;
        L13:
            r5.phone_code_hash = r102;
            r5.phone_number = r13;
            r5.premium_days = r14;
            FileLog.d("LoginBilling found \"" + r9 + "\" product, with currency=" + r5.currency + " amount=" + r5.amount + "; phone=" + r13 + ", phone_code_hash=" + r12);
            TLRPC.TL_payments_canPurchaseStore r7 = new TLRPC.TL_payments_canPurchaseStore();
            r7.purpose = r5;
            ConnectionsManager.getInstance(LoginActivity.access$5000(this.this$0)).sendRequest(r7, new LoginActivity$LoginPayView$$ExternalSyntheticLambda15(this, r2, r14, r4, r5, r9, r7), 10);
            return;
        L12:
            r102 = r12;
            goto L13
        }

        private /* synthetic */ void lambda$setParams$29(String r9, String r10, String r11, int r12, BillingResult r13, List r14) {
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginPayView$$ExternalSyntheticLambda13(this, r9, r13, r14, r10, r11, r12));
        }

        private /* synthetic */ void lambda$setParams$3(TLRPC.TL_inputStorePaymentAuthCode r4, TLRPC.PaymentForm r5) {
            startPoll(r4.phone_number, r4.phone_code_hash, r5.form_id);
        }

        private /* synthetic */ void lambda$setParams$30(String r9, String r10, String r11, int r12) {
            ArrayList r0 = new ArrayList();
            r0.add(QueryProductDetailsParams.Product.newBuilder().setProductType("inapp").setProductId(r9).build());
            FileLog.d("LoginBilling querying \"" + r9 + "\" product");
            BillingController.getInstance().queryProductDetails(r0, new LoginActivity$LoginPayView$$ExternalSyntheticLambda10(this, r9, r10, r11, r12));
        }

        private /* synthetic */ void lambda$setParams$4(TLRPC.TL_inputStorePaymentAuthCode r1, TLRPC.PaymentForm r2, TLRPC.TL_payments_paymentResult r3) {
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginPayView$$ExternalSyntheticLambda20(this, r1, r2));
        }

        private /* synthetic */ void lambda$setParams$5(TLRPC.TL_inputStorePaymentAuthCode r4, TLRPC.PaymentForm r5) {
            startPoll(r4.phone_number, r4.phone_code_hash, r5.form_id);
        }

        private /* synthetic */ void lambda$setParams$6(TLRPC.TL_inputStorePaymentAuthCode r1, TLRPC.PaymentForm r2, TLRPC.payments_PaymentResult r3) {
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginPayView$$ExternalSyntheticLambda23(this, r1, r2));
        }

        private /* synthetic */ void lambda$setParams$7() {
            onBackPressed(true);
            this.this$0.setPage(0, true, null, true);
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString(org.telegram.messenger.R.string.CodeExpired));
        }

        private /* synthetic */ Boolean lambda$setParams$8(TLRPC.TL_error r2) {
            if (r2 == null) goto L8;
            if ("PHONE_CODE_EXPIRED".equalsIgnoreCase(r2.text) == false) goto L8;
            AndroidUtilities.runOnUIThread(new LoginActivity$LoginPayView$$ExternalSyntheticLambda21(this));
            return Boolean.TRUE;
        L8:
            return Boolean.FALSE;
        }

        private /* synthetic */ void lambda$setParams$9() {
            onBackPressed(true);
            this.this$0.setPage(0, true, null, true);
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString(org.telegram.messenger.R.string.CodeExpired));
        }

        private void poll() {
            if (this.polling == true) goto L5;
            return;
        L5:
            TLRPC.TL_checkPaidAuth r0 = new TLRPC.TL_checkPaidAuth();
            r0.form_id = this.pollingFormId;
            r0.phone_number = this.pollingPhoneNumber;
            r0.phone_code_hash = this.pollingPhoneCodeHash;
            this.pollingRequestId = ConnectionsManager.getInstance(LoginActivity.access$4800(this.this$0)).sendRequest(r0, new LoginActivity$LoginPayView$$ExternalSyntheticLambda24(this), 1096);
        }

        private void startPoll(String r2, String r3, long r4) {
            if (this.polling == false) goto L5;
            return;
        L5:
            this.polling = true;
            this.pollingPhoneNumber = r2;
            this.pollingPhoneCodeHash = r3;
            this.pollingFormId = r4;
            this.button.setLoading(true);
            poll();
        }

        private void stopPoll() {
            if (this.pollingRequestId < 0) goto L5;
            ConnectionsManager.getInstance(LoginActivity.access$4900(this.this$0)).cancelRequest(this.pollingRequestId, true);
            this.pollingRequestId = -1;
        L5:
            this.polling = false;
            this.button.setLoading(false);
        }

        @Override // org.telegram.ui.Components.SlideView
        public void onHide() {
            super.onHide();
            stopPoll();
        }

        @Override // org.telegram.ui.Components.SlideView
        public void setParams(Bundle r19, boolean r20) {
            super.setParams(r19, r20);
            this.params = r19;
            if (r19 != null) goto L5;
            String r3 = null;
        L6:
            String r32 = LocaleController.getCountryName(r3);
            if (r19 != null) goto L9;
            String r4 = null;
        L10:
            if (r19 != null) goto L12;
            String r6 = null;
        L13:
            if (r19 != null) goto L15;
            String r5 = null;
        L16:
            if (r19 != null) goto L18;
            String r7 = null;
        L19:
            if (r19 != null) goto L21;
            String r8 = null;
        L22:
            if (r19 != null) goto L24;
            String r9 = null;
        L25:
            if (r19 != null) goto L27;
            long r12 = 0;
        L29:
            if (r19 != null) goto L31;
            int r0 = 0;
        L32:
            boolean r15 = TextUtils.isEmpty(r32);
            ExplainStarsSheet.FeatureCell[] r10 = this.cells;
            if (r15 == false) goto L35;
            r10[0].subtitleView.setText(LocaleController.getString(org.telegram.messenger.R.string.SMSFee1Text));
        L36:
            ExplainStarsSheet.FeatureCell r33 = this.cells[2];
            if (r0 != 7) goto L39;
            String r11 = LocaleController.getString(org.telegram.messenger.R.string.SMSFee3Text);
        L40:
            r33.setSubtitle(r11);
            this.optionsButton.setOnClickListener(new LoginActivity$LoginPayView$$ExternalSyntheticLambda6(this, r7, r8, r6));
            this.button.setEnabled(true);
            this.button.setOnClickListener(null);
            if (BuildVars.useInvoiceBilling() == true) goto L43;
            String r34 = r5;
            int r52 = r0;
            boolean r02 = TextUtils.isEmpty(r4);
            ButtonWithCounterView r2 = this.button;
            if (r02 == false) goto L58;
            r2.setVisibility(8);
            return;
        L58:
            r2.setVisibility(0);
            this.button.setLoading(true);
            LoginActivity$LoginPayView$$ExternalSyntheticLambda8 r03 = new LoginActivity$LoginPayView$$ExternalSyntheticLambda8(this, r4, r34, r6, r52);
            if (BillingController.getInstance().isReady() == true) goto L62;
            BillingController.getInstance().whenSetuped(r03);
            return;
        L62:
            r03.run();
            return;
        L43:
            if (TextUtils.isEmpty(r9) == false) goto L45;
        L52:
            this.button.setVisibility(0);
            this.button.setLoading(false);
            this.button.setEnabled(false);
            this.button.setText(LocaleController.getString(org.telegram.messenger.R.string.Unavailable), false);
            return;
        L45:
            if (r12 <= 0) goto L52;
            this.button.setVisibility(0);
            this.button.setLoading(false);
            this.button.setText(LocaleController.formatString(org.telegram.messenger.R.string.SMSFeePurchaseTitle, new Object[]{BillingController.getInstance().formatCurrency(r12, r9)}), false);
            ButtonWithCounterView r22 = this.button;
            if (r0 != 7) goto L49;
            String r35 = LocaleController.getString(org.telegram.messenger.R.string.SMSFeePurchaseText);
        L50:
            r22.setSubText(r35, false);
            this.button.setOnClickListener(new LoginActivity$LoginPayView$$ExternalSyntheticLambda7(this, r9, r12, r5, r6, r0));
            return;
        L49:
            r35 = LocaleController.formatPluralStringComma("SMSFeePurchaseTextDays", r0);
            goto L50
        L39:
            r11 = LocaleController.formatPluralStringComma("SMSFee3TextDays", r0);
            goto L40
        L35:
            r10[0].subtitleView.setText(LocaleController.formatString(org.telegram.messenger.R.string.SMSFee1TextCountry, new Object[]{r32}));
            goto L36
        L31:
            r0 = r19.getInt("premium_days");
            goto L32
        L27:
            r12 = r19.getLong("amount");
            goto L29
        L24:
            r9 = r19.getString("currency");
            goto L25
        L21:
            r8 = r19.getString("support_email_subject");
            goto L22
        L18:
            r7 = r19.getString("support_email_email");
            goto L19
        L15:
            r5 = r19.getString("phoneHash");
            goto L16
        L12:
            r6 = r19.getString("phoneFormated");
            goto L13
        L9:
            r4 = r19.getString("product");
            goto L10
        L5:
            r3 = r19.getString("country");
            goto L6
        }
    }

    public static final class PhoneInputData {
        private CountrySelectActivity.Country country;
        private List<String> patterns;
        private String phoneNumber;

        /* renamed from: -$$Nest$fgetcountry, reason: not valid java name */
        public static /* bridge */ /* synthetic */ CountrySelectActivity.Country m15971$$Nest$fgetcountry(PhoneInputData r0) {
            return r0.country;
        }

        /* renamed from: -$$Nest$fgetpatterns, reason: not valid java name */
        public static /* bridge */ /* synthetic */ List m15972$$Nest$fgetpatterns(PhoneInputData r0) {
            return r0.patterns;
        }

        /* renamed from: -$$Nest$fgetphoneNumber, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m15973$$Nest$fgetphoneNumber(PhoneInputData r0) {
            return r0.phoneNumber;
        }

        /* renamed from: -$$Nest$fputcountry, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15974$$Nest$fputcountry(PhoneInputData r0, CountrySelectActivity.Country r1) {
            r0.country = r1;
        }

        /* renamed from: -$$Nest$fputpatterns, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15975$$Nest$fputpatterns(PhoneInputData r0, List r1) {
            r0.patterns = r1;
        }

        /* renamed from: -$$Nest$fputphoneNumber, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15976$$Nest$fputphoneNumber(PhoneInputData r0, String r1) {
            r0.phoneNumber = r1;
        }

        private PhoneInputData() {
        }

        public /* synthetic */ PhoneInputData(LoginActivityIA r1) {
            this();
        }
    }

    public static final class PhoneNumberConfirmView extends FrameLayout {
        private View blurredView;
        private IConfirmDialogCallback callback;
        private TextView confirmMessageView;
        private TextView confirmTextView;
        private View dimmView;
        private boolean dismissed;
        private TextView editTextView;
        private FragmentFloatingButton fabButton;
        private View fabContainer;
        private TransformableLoginButtonView fabTransform;
        private ViewGroup fragmentView;
        private TextView numberView;
        private final PointF pointF;
        private FrameLayout popupLayout;

        /* renamed from: org.telegram.ui.LoginActivity$PhoneNumberConfirmView$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ PhoneNumberConfirmView this$0;

            public AnonymousClass1(PhoneNumberConfirmView r1) {
                this.this$0 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                if (AndroidUtilities.isAccessibilityTouchExplorationEnabled() == false) goto L6;
                PhoneNumberConfirmView.m15980$$Nest$fgetfabButton(this.this$0).requestFocus();
                return;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator r6) {
                PhoneNumberConfirmView.m15981$$Nest$fgetfabContainer(this.this$0).setVisibility(8);
                int r62 = (int) (PhoneNumberConfirmView.m15982$$Nest$fgetfragmentView(this.this$0).getMeasuredWidth() / 10.0f);
                int r1 = (int) (PhoneNumberConfirmView.m15982$$Nest$fgetfragmentView(this.this$0).getMeasuredHeight() / 10.0f);
                Bitmap r2 = Bitmap.createBitmap(r62, r1, Bitmap.Config.ARGB_8888);
                Canvas r3 = new Canvas(r2);
                r3.scale(0.1f, 0.1f);
                r3.drawColor(Theme.getColor(Theme.key_windowBackgroundWhite));
                PhoneNumberConfirmView.m15982$$Nest$fgetfragmentView(this.this$0).draw(r3);
                Utilities.stackBlurBitmap(r2, Math.max(8, Math.max(r62, r1) / 150));
                PhoneNumberConfirmView.m15979$$Nest$fgetblurredView(this.this$0).setBackground(new BitmapDrawable(this.this$0.getContext().getResources(), r2));
                PhoneNumberConfirmView.m15979$$Nest$fgetblurredView(this.this$0).setAlpha(0.0f);
                PhoneNumberConfirmView.m15979$$Nest$fgetblurredView(this.this$0).setVisibility(0);
                PhoneNumberConfirmView.m15982$$Nest$fgetfragmentView(this.this$0).addView(this.this$0);
            }
        }

        /* renamed from: org.telegram.ui.LoginActivity$PhoneNumberConfirmView$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ PhoneNumberConfirmView this$0;

            public AnonymousClass2(PhoneNumberConfirmView r1) {
                this.this$0 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                if ((this.this$0.getParent() instanceof ViewGroup) == false) goto L5;
                ((ViewGroup) this.this$0.getParent()).removeView(this.this$0);
            L5:
                PhoneNumberConfirmView.m15981$$Nest$fgetfabContainer(this.this$0).setVisibility(0);
            }
        }

        public interface IConfirmDialogCallback {
            void onConfirmPressed(PhoneNumberConfirmView r1, TextView r2);

            void onDismiss(PhoneNumberConfirmView r1);

            void onEditPressed(PhoneNumberConfirmView r1, TextView r2);

            void onFabPressed(PhoneNumberConfirmView r1, TransformableLoginButtonView r2);
        }

        public static /* synthetic */ void $r8$lambda$5rb8l_z8G9uNXknYXFpKq8PotKg(PhoneNumberConfirmView r0, ValueAnimator r1) {
            r0.lambda$show$4(r1);
        }

        /* renamed from: $r8$lambda$QKkri-IawBOCvOcgVwuWkCv56MI, reason: not valid java name */
        public static /* synthetic */ void m15977$r8$lambda$QKkriIawBOCvOcgVwuWkCv56MI(PhoneNumberConfirmView r0, ValueAnimator r1) {
            r0.lambda$dismiss$5(r1);
        }

        public static /* synthetic */ void $r8$lambda$RbKgo67VelYaYXWa4VXMlCn8VBM(PhoneNumberConfirmView r0, View r1) {
            r0.lambda$new$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$X6vtwy8PSiKm8o2YPWq5pR3ZTLc(PhoneNumberConfirmView r0, IConfirmDialogCallback r1, View r2) {
            r0.lambda$new$1(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$lO6d_kF4SChZHuJlZUtABOzAqO8(PhoneNumberConfirmView r0, IConfirmDialogCallback r1, View r2) {
            r0.lambda$new$2(r1, r2);
        }

        /* renamed from: $r8$lambda$qcE672WeKzr4H1E8WHcpLidvP-E, reason: not valid java name */
        public static /* synthetic */ void m15978$r8$lambda$qcE672WeKzr4H1E8WHcpLidvPE(PhoneNumberConfirmView r0, IConfirmDialogCallback r1, View r2) {
            r0.lambda$new$3(r1, r2);
        }

        /* renamed from: -$$Nest$fgetblurredView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ View m15979$$Nest$fgetblurredView(PhoneNumberConfirmView r0) {
            return r0.blurredView;
        }

        /* renamed from: -$$Nest$fgetfabButton, reason: not valid java name */
        public static /* bridge */ /* synthetic */ FragmentFloatingButton m15980$$Nest$fgetfabButton(PhoneNumberConfirmView r0) {
            return r0.fabButton;
        }

        /* renamed from: -$$Nest$fgetfabContainer, reason: not valid java name */
        public static /* bridge */ /* synthetic */ View m15981$$Nest$fgetfabContainer(PhoneNumberConfirmView r0) {
            return r0.fabContainer;
        }

        /* renamed from: -$$Nest$fgetfragmentView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ViewGroup m15982$$Nest$fgetfragmentView(PhoneNumberConfirmView r0) {
            return r0.fragmentView;
        }

        /* renamed from: -$$Nest$manimateProgress, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15983$$Nest$manimateProgress(PhoneNumberConfirmView r0, Runnable r1) {
            r0.animateProgress(r1);
        }

        /* renamed from: -$$Nest$mdismiss, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15984$$Nest$mdismiss(PhoneNumberConfirmView r0) {
            r0.dismiss();
        }

        /* renamed from: -$$Nest$mshow, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15985$$Nest$mshow(PhoneNumberConfirmView r0) {
            r0.show();
        }

        /* renamed from: -$$Nest$mupdateColors, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15986$$Nest$mupdateColors(PhoneNumberConfirmView r0) {
            r0.updateColors();
        }

        /* renamed from: -$$Nest$mupdateFabPosition, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15987$$Nest$mupdateFabPosition(PhoneNumberConfirmView r0) {
            r0.updateFabPosition();
        }

        private PhoneNumberConfirmView(Context r26, ViewGroup r27, View r28, String r29, IConfirmDialogCallback r30) {
            super(r26);
            this.pointF = new PointF();
            this.fragmentView = r27;
            this.fabContainer = r28;
            this.callback = r30;
            View r3 = new View(getContext());
            this.blurredView = r3;
            r3.setOnClickListener(new LoginActivity$PhoneNumberConfirmView$$ExternalSyntheticLambda2(this));
            addView(this.blurredView, LayoutHelper.createFrame(-1, -1.0f));
            View r32 = new View(getContext());
            this.dimmView = r32;
            r32.setBackgroundColor(TLObject.FLAG_30);
            this.dimmView.setAlpha(0.0f);
            addView(this.dimmView, LayoutHelper.createFrame(-1, -1.0f));
            TransformableLoginButtonView r33 = new TransformableLoginButtonView(getContext());
            this.fabTransform = r33;
            r33.setTransformType(1);
            this.fabTransform.setDrawBackground(false);
            FragmentFloatingButton r34 = new FragmentFloatingButton(r26, null);
            this.fabButton = r34;
            r34.addView(this.fabTransform, LayoutHelper.createFrame(56, 56, 17));
            this.fabButton.addAdditionalView(this.fabTransform);
            this.fabButton.setOnClickListener(new LoginActivity$PhoneNumberConfirmView$$ExternalSyntheticLambda3(this, r30));
            this.fabButton.setContentDescription(LocaleController.getString(org.telegram.messenger.R.string.Done));
            addView(this.fabButton, LayoutHelper.createFrame(56, 56, 51));
            FrameLayout r35 = new FrameLayout(r26);
            this.popupLayout = r35;
            addView(r35, LayoutHelper.createFrame(-1, 140.0f, 49, 24.0f, 0.0f, 24.0f, 0.0f));
            TextView r36 = new TextView(r26);
            this.confirmMessageView = r36;
            r36.setText(LocaleController.getString(org.telegram.messenger.R.string.ConfirmCorrectNumber));
            this.confirmMessageView.setTextSize(1, 14.0f);
            this.confirmMessageView.setSingleLine();
            FrameLayout r37 = this.popupLayout;
            TextView r5 = this.confirmMessageView;
            int r7 = 3;
            if (LocaleController.isRTL == false) goto L5;
            int r11 = 5;
        L6:
            r37.addView(r5, LayoutHelper.createFrame(-1, -2.0f, r11, 24.0f, 20.0f, 24.0f, 0.0f));
            TextView r38 = new TextView(r26);
            this.numberView = r38;
            r38.setText(r29);
            this.numberView.setTextSize(1, 18.0f);
            this.numberView.setTypeface(AndroidUtilities.bold());
            this.numberView.setSingleLine();
            FrameLayout r39 = this.popupLayout;
            TextView r52 = this.numberView;
            if (LocaleController.isRTL == false) goto L9;
            int r112 = 5;
        L10:
            r39.addView(r52, LayoutHelper.createFrame(-1, -2.0f, r112, 24.0f, 48.0f, 24.0f, 0.0f));
            int r53 = AndroidUtilities.dp(16.0f);
            TextView r6 = new TextView(r26);
            this.editTextView = r6;
            r6.setText(LocaleController.getString(org.telegram.messenger.R.string.Edit));
            this.editTextView.setSingleLine();
            this.editTextView.setTextSize(1, 16.0f);
            TextView r62 = this.editTextView;
            int r10 = AndroidUtilities.dp(6.0f);
            int r113 = Theme.key_changephoneinfo_image2;
            r62.setBackground(Theme.getRoundRectSelectorDrawable(r10, Theme.getColor(r113)));
            this.editTextView.setOnClickListener(new LoginActivity$PhoneNumberConfirmView$$ExternalSyntheticLambda4(this, r30));
            TextView r63 = this.editTextView;
            Typeface r102 = Typeface.DEFAULT_BOLD;
            r63.setTypeface(r102);
            int r12 = r53 / 2;
            this.editTextView.setPadding(r53, r12, r53, r12);
            FrameLayout r64 = this.popupLayout;
            TextView r13 = this.editTextView;
            if (LocaleController.isRTL == false) goto L13;
            int r14 = 5;
        L14:
            r64.addView(r13, LayoutHelper.createFrame(-2, -2.0f, r14 | 80, 8.0f, 8.0f, 8.0f, 8.0f));
            TextView r65 = new TextView(r26);
            this.confirmTextView = r65;
            r65.setText(LocaleController.getString(org.telegram.messenger.R.string.CheckPhoneNumberYes));
            this.confirmTextView.setSingleLine();
            this.confirmTextView.setTextSize(1, 16.0f);
            this.confirmTextView.setBackground(Theme.getRoundRectSelectorDrawable(AndroidUtilities.dp(6.0f), Theme.getColor(r113)));
            this.confirmTextView.setOnClickListener(new LoginActivity$PhoneNumberConfirmView$$ExternalSyntheticLambda5(this, r30));
            this.confirmTextView.setTypeface(r102);
            this.confirmTextView.setPadding(r53, r12, r53, r12);
            FrameLayout r1 = this.popupLayout;
            TextView r2 = this.confirmTextView;
            if (LocaleController.isRTL == true) goto L18;
            r7 = 5;
        L18:
            r1.addView(r2, LayoutHelper.createFrame(-2, -2.0f, r7 | 80, 8.0f, 8.0f, 8.0f, 8.0f));
            updateFabPosition();
            updateColors();
            return;
        L13:
            r14 = 3;
            goto L14
        L9:
            r112 = 3;
            goto L10
        L5:
            r11 = 3;
            goto L6
        }

        public /* synthetic */ PhoneNumberConfirmView(Context r1, ViewGroup r2, View r3, String r4, IConfirmDialogCallback r5, LoginActivityIA r6) {
            this(r1, r2, r3, r4, r5);
        }

        private void animateProgress(Runnable r3) {
            this.fabButton.setProgressVisible(true, true);
            AndroidUtilities.runOnUIThread(r3, 400);
        }

        private void dismiss() {
            if (this.dismissed == false) goto L5;
            return;
        L5:
            this.dismissed = true;
            this.callback.onDismiss(this);
            ValueAnimator r0 = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f}).setDuration(250);
            r0.addListener(new AnonymousClass2(this));
            r0.addUpdateListener(new LoginActivity$PhoneNumberConfirmView$$ExternalSyntheticLambda0(this));
            r0.setInterpolator(CubicBezierInterpolator.DEFAULT);
            r0.start();
        }

        private /* synthetic */ void lambda$dismiss$5(ValueAnimator r2) {
            float r22 = ((Float) r2.getAnimatedValue()).floatValue();
            this.blurredView.setAlpha(r22);
            this.dimmView.setAlpha(r22);
            this.fabTransform.setProgress(r22);
            this.popupLayout.setAlpha(r22);
            float r23 = (r22 * 0.5f) + 0.5f;
            this.popupLayout.setScaleX(r23);
            this.popupLayout.setScaleY(r23);
        }

        private /* synthetic */ void lambda$new$0(View r1) {
            dismiss();
        }

        private /* synthetic */ void lambda$new$1(IConfirmDialogCallback r1, View r2) {
            r1.onFabPressed(this, this.fabTransform);
        }

        private /* synthetic */ void lambda$new$2(IConfirmDialogCallback r1, View r2) {
            r1.onEditPressed(this, this.editTextView);
        }

        private /* synthetic */ void lambda$new$3(IConfirmDialogCallback r1, View r2) {
            r1.onConfirmPressed(this, this.confirmTextView);
        }

        private /* synthetic */ void lambda$show$4(ValueAnimator r2) {
            float r22 = ((Float) r2.getAnimatedValue()).floatValue();
            this.fabTransform.setProgress(r22);
            this.blurredView.setAlpha(r22);
            this.dimmView.setAlpha(r22);
            this.popupLayout.setAlpha(r22);
            float r23 = (r22 * 0.5f) + 0.5f;
            this.popupLayout.setScaleX(r23);
            this.popupLayout.setScaleY(r23);
        }

        private void show() {
            ValueAnimator r0 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}).setDuration(250);
            r0.addListener(new AnonymousClass1(this));
            r0.addUpdateListener(new LoginActivity$PhoneNumberConfirmView$$ExternalSyntheticLambda1(this));
            r0.setInterpolator(CubicBezierInterpolator.DEFAULT);
            r0.start();
        }

        private void updateColors() {
            this.fabTransform.setColor(Theme.getColor(Theme.key_chats_actionIcon));
            this.fabTransform.setBackgroundColor(Theme.getColor(Theme.key_chats_actionBackground));
            this.popupLayout.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(12.0f), Theme.getColor(Theme.key_dialogBackground)));
            this.confirmMessageView.setTextColor(Theme.getColor(Theme.key_dialogTextGray2));
            this.numberView.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
            TextView r0 = this.editTextView;
            int r1 = Theme.key_changephoneinfo_image2;
            r0.setTextColor(Theme.getColor(r1));
            this.confirmTextView.setTextColor(Theme.getColor(r1));
            this.fabButton.updateColors();
        }

        private void updateFabPosition() {
            ViewPositionWatcher.computeCoordinatesInParent(this.fabContainer, this.fragmentView, this.pointF);
            this.fabButton.setTranslationX(this.pointF.x);
            this.fabButton.setTranslationY(this.pointF.y);
            requestLayout();
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            int r12 = this.popupLayout.getMeasuredHeight();
            int r22 = (int) (this.fabButton.getTranslationY() - AndroidUtilities.dp(32.0f));
            FrameLayout r32 = this.popupLayout;
            r32.layout(r32.getLeft(), r22 - r12, this.popupLayout.getRight(), r22);
        }
    }

    public class PhoneView extends SlideView implements AdapterView.OnItemSelectedListener, NotificationCenter.NotificationCenterDelegate {
        private Runnable cancelRequestingPasskey;
        private ImageView chevronRight;
        private View codeDividerView;
        private AnimatedPhoneNumberEditText codeField;
        private HashMap<String, List<CountrySelectActivity.Country>> codesMap;
        private boolean confirmedNumber;
        private ArrayList<CountrySelectActivity.Country> countriesArray;
        private TextViewSwitcher countryButton;
        private String countryCodeForHint;
        private OutlineTextContainerView countryOutlineView;
        private int countryState;
        private CountrySelectActivity.Country currentCountry;
        private boolean ignoreOnPhoneChange;
        private boolean ignoreOnPhoneChangePaste;
        private boolean ignoreOnTextChange;
        private boolean ignoreSelection;
        private long lastTitleClick;
        private Toast lastTitleToast;
        private boolean nextPressed;
        private boolean numberFilled;
        private AnimatedPhoneNumberEditText phoneField;
        private HashMap<String, List<String>> phoneFormatMap;
        private final ImageView phoneNumberHintButton;
        private OutlineTextContainerView phoneOutlineView;
        private TextView plusTextView;
        private boolean requestedPasskey;
        private boolean requestingPasskey;
        private boolean requestingPhoneNumberHint;
        private LinkSpanDrawable.LinksTextView subtitleView;
        private CheckBoxCell syncContactsBox;
        private CheckBoxCell testBackendCheckBox;
        final /* synthetic */ LoginActivity this$0;
        private int titleClickCount;
        private TextView titleView;
        private int wasCountryHintIndex;

        /* renamed from: org.telegram.ui.LoginActivity$PhoneView$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatedPhoneNumberEditText {
            final /* synthetic */ PhoneView this$1;
            final /* synthetic */ LoginActivity val$this$0;

            public AnonymousClass1(PhoneView r1, Context r2, LoginActivity r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            @Override // org.telegram.ui.Components.EditTextBoldCursor, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
            public void onFocusChanged(boolean r1, int r2, Rect r3) {
                super.onFocusChanged(r1, r2, r3);
                OutlineTextContainerView r22 = PhoneView.m16002$$Nest$fgetphoneOutlineView(this.this$1);
                if (r1 == false) goto L5;
            L8:
                float r32 = 1.0f;
            L9:
                r22.animateSelection(r32);
                if (r1 == false) goto L13;
                LoginActivity.m15794$$Nest$fgetkeyboardView(this.this$1.this$0).setEditText(this);
                return;
            L13:
                return;
            L5:
                if (PhoneView.m16001$$Nest$fgetphoneField(this.this$1).isFocused() == true) goto L8;
                r32 = 0.0f;
                goto L9
            }
        }

        /* renamed from: org.telegram.ui.LoginActivity$PhoneView$2, reason: invalid class name */
        public class AnonymousClass2 implements TextWatcher {
            final /* synthetic */ PhoneView this$1;
            final /* synthetic */ LoginActivity val$this$0;

            public AnonymousClass2(PhoneView r1, LoginActivity r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable r15) {
                if (PhoneView.m16000$$Nest$fgetignoreOnTextChange(this.this$1) == false) goto L5;
                return;
            L5:
                PhoneView.m16007$$Nest$fputignoreOnTextChange(this.this$1, true);
                String r152 = PhoneFormat.stripExceptNumbers(PhoneView.m15995$$Nest$fgetcodeField(this.this$1).getText().toString());
                PhoneView.m15995$$Nest$fgetcodeField(this.this$1).setText(r152);
                if (r152.length() != 0) goto L8;
                PhoneView.m16010$$Nest$msetCountryButtonText(this.this$1, null);
                PhoneView.m16001$$Nest$fgetphoneField(this.this$1).setHintText(null);
                PhoneView.m16004$$Nest$fputcountryState(this.this$1, 1);
            L69:
                PhoneView.m16007$$Nest$fputignoreOnTextChange(this.this$1, false);
                return;
            L8:
                int r5 = 4;
                if (r152.length() <= 4) goto L30;
            L10:
                if (r5 < 1) goto L27;
                String r1 = r152.substring(0, r5);
                List r6 = (List) PhoneView.m15996$$Nest$fgetcodesMap(this.this$1).get(r1);
                if (r6 != null) goto L15;
                CountrySelectActivity.Country r62 = null;
            L24:
                if (r62 != null) goto L25;
                r5 = r5 - 1;
                goto L10
            L25:
                String r153 = r152.substring(r5) + PhoneView.m16001$$Nest$fgetphoneField(this.this$1).getText().toString();
                PhoneView.m15995$$Nest$fgetcodeField(this.this$1).setText(r1);
                String r12 = r153;
                r152 = r1;
                boolean r52 = true;
            L28:
                if (r52 == true) goto L31;
                r12 = r152.substring(1) + PhoneView.m16001$$Nest$fgetphoneField(this.this$1).getText().toString();
                AnimatedPhoneNumberEditText r63 = PhoneView.m15995$$Nest$fgetcodeField(this.this$1);
                r152 = r152.substring(0, 1);
                r63.setText(r152);
            L31:
                ArrayList r64 = PhoneView.m15997$$Nest$fgetcountriesArray(this.this$1);
                int r7 = r64.size();
                CountrySelectActivity.Country r9 = null;
                int r8 = 0;
                int r10 = 0;
            L32:
                if (r10 >= r7) goto L44;
                Object r11 = r64.get(r10);
                r10 = r10 + 1;
                CountrySelectActivity.Country r112 = (CountrySelectActivity.Country) r11;
                if (r112.code.startsWith(r152) == false) goto L32;
                int r122 = r8 + 1;
                if (r112.code.equals(r152) == true) goto L37;
                r8 = r122;
                goto L32
            L37:
                if (r9 != null) goto L39;
            L41:
                r8 = r122;
            L42:
                r9 = r112;
                goto L32
            L39:
                if (r9.code.equals(r112.code) == false) goto L41;
            L44:
                if (r8 != 1) goto L48;
                if (r9 == null) goto L48;
                if (r12 != null) goto L48;
                r12 = r152.substring(r9.code.length()) + PhoneView.m16001$$Nest$fgetphoneField(this.this$1).getText().toString();
                AnimatedPhoneNumberEditText r154 = PhoneView.m15995$$Nest$fgetcodeField(this.this$1);
                String r65 = r9.code;
                r154.setText(r65);
                r152 = r65;
            L48:
                List r66 = (List) PhoneView.m15996$$Nest$fgetcodesMap(this.this$1).get(r152);
                if (r66 != null) goto L52;
                CountrySelectActivity.Country r67 = null;
            L61:
                PhoneView r4 = this.this$1;
                if (r67 == null) goto L64;
                PhoneView.m16008$$Nest$fputignoreSelection(r4, true);
                PhoneView.m16005$$Nest$fputcurrentCountry(this.this$1, r67);
                PhoneView.m16011$$Nest$msetCountryHint(this.this$1, r152, r67);
                PhoneView.m16004$$Nest$fputcountryState(this.this$1, 0);
            L65:
                if (r52 == true) goto L67;
                PhoneView.m15995$$Nest$fgetcodeField(this.this$1).setSelection(PhoneView.m15995$$Nest$fgetcodeField(this.this$1).getText().length());
            L67:
                if (r12 == null) goto L69;
                PhoneView.m16001$$Nest$fgetphoneField(this.this$1).requestFocus();
                PhoneView.m16001$$Nest$fgetphoneField(this.this$1).setText(r12);
                PhoneView.m16001$$Nest$fgetphoneField(this.this$1).setSelection(PhoneView.m16001$$Nest$fgetphoneField(this.this$1).length());
                goto L69
            L64:
                PhoneView.m16010$$Nest$msetCountryButtonText(r4, null);
                PhoneView.m16001$$Nest$fgetphoneField(this.this$1).setHintText(null);
                PhoneView.m16004$$Nest$fputcountryState(this.this$1, 2);
                goto L65
            L52:
                if (r66.size() <= 1) goto L60;
                String r42 = MessagesController.getGlobalMainSettings().getString("phone_code_last_matched_" + r152, null);
                r67 = (CountrySelectActivity.Country) r66.get(r66.size() - 1);
                if (r42 == null) goto L61;
                ArrayList r72 = PhoneView.m15997$$Nest$fgetcountriesArray(this.this$1);
                int r82 = r72.size();
                int r92 = 0;
            L56:
                if (r92 >= r82) goto L61;
                Object r102 = r72.get(r92);
                r92 = r92 + 1;
                CountrySelectActivity.Country r103 = (CountrySelectActivity.Country) r102;
                if (Objects.equals(r103.shortname, r42) == false) goto L56;
                r67 = r103;
                goto L61
            L60:
                r67 = (CountrySelectActivity.Country) r66.get(0);
                goto L61
            L15:
                if (r6.size() <= 1) goto L23;
                String r73 = MessagesController.getGlobalMainSettings().getString("phone_code_last_matched_".concat(r1), null);
                r62 = (CountrySelectActivity.Country) r6.get(r6.size() - 1);
                if (r73 == null) goto L24;
                ArrayList r83 = PhoneView.m15997$$Nest$fgetcountriesArray(this.this$1);
                int r93 = r83.size();
                int r104 = 0;
            L19:
                if (r104 >= r93) goto L24;
                Object r113 = r83.get(r104);
                r104 = r104 + 1;
                CountrySelectActivity.Country r114 = (CountrySelectActivity.Country) r113;
                if (Objects.equals(r114.shortname, r73) == false) goto L19;
                r62 = r114;
                goto L24
            L23:
                r62 = (CountrySelectActivity.Country) r6.get(0);
                goto L24
            L27:
                r12 = null;
                r52 = false;
                goto L28
            L30:
                r12 = null;
                r52 = false;
                goto L31
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }
        }

        /* renamed from: org.telegram.ui.LoginActivity$PhoneView$3, reason: invalid class name */
        public class AnonymousClass3 extends AnimatedPhoneNumberEditText {
            final /* synthetic */ PhoneView this$1;
            final /* synthetic */ LoginActivity val$this$0;

            public AnonymousClass3(PhoneView r1, Context r2, LoginActivity r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            @Override // org.telegram.ui.Components.EditTextBoldCursor, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
            public void onFocusChanged(boolean r1, int r2, Rect r3) {
                super.onFocusChanged(r1, r2, r3);
                OutlineTextContainerView r22 = PhoneView.m16002$$Nest$fgetphoneOutlineView(this.this$1);
                if (r1 == false) goto L5;
            L8:
                float r32 = 1.0f;
            L9:
                r22.animateSelection(r32);
                PhoneView r23 = this.this$1;
                if (r1 == false) goto L16;
                LoginActivity.m15794$$Nest$fgetkeyboardView(r23.this$0).setEditText(this);
                LoginActivity.m15794$$Nest$fgetkeyboardView(this.this$1.this$0).setDispatchBackWhenEmpty(true);
                if (PhoneView.m15998$$Nest$fgetcountryState(this.this$1) != 2) goto L19;
                PhoneView.m16010$$Nest$msetCountryButtonText(this.this$1, LocaleController.getString(org.telegram.messenger.R.string.WrongCountry));
                return;
            L19:
                return;
            L16:
                if (PhoneView.m15998$$Nest$fgetcountryState(r23) != 2) goto L20;
                PhoneView.m16010$$Nest$msetCountryButtonText(this.this$1, null);
                return;
            L20:
                return;
            L5:
                if (PhoneView.m15995$$Nest$fgetcodeField(this.this$1).isFocused() == true) goto L8;
                r32 = 0.0f;
                goto L9
            }

            @Override // android.widget.TextView, android.view.View, android.view.KeyEvent.Callback
            public boolean onKeyDown(int r3, KeyEvent r4) {
                if (r3 != 67) goto L8;
                if (PhoneView.m16001$$Nest$fgetphoneField(this.this$1).length() != 0) goto L8;
                PhoneView.m15995$$Nest$fgetcodeField(this.this$1).requestFocus();
                PhoneView.m15995$$Nest$fgetcodeField(this.this$1).setSelection(PhoneView.m15995$$Nest$fgetcodeField(this.this$1).length());
                PhoneView.m15995$$Nest$fgetcodeField(this.this$1).dispatchKeyEvent(r4);
            L8:
                return super.onKeyDown(r3, r4);
            }

            @Override // org.telegram.ui.Components.EditTextBoldCursor, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
            public boolean onTouchEvent(MotionEvent r2) {
                if (r2.getAction() != 0) goto L8;
                if (LoginActivity.m15836$$Nest$mshowKeyboard(this.this$1.this$0, this) == true) goto L8;
                clearFocus();
                requestFocus();
            L8:
                return super.onTouchEvent(r2);
            }
        }

        /* renamed from: org.telegram.ui.LoginActivity$PhoneView$4, reason: invalid class name */
        public class AnonymousClass4 implements TextWatcher {
            private int actionPosition;
            private int characterAction;
            final /* synthetic */ PhoneView this$1;
            final /* synthetic */ LoginActivity val$this$0;

            public AnonymousClass4(PhoneView r1, LoginActivity r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
                this.characterAction = -1;
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable r11) {
                if (PhoneView.m15999$$Nest$fgetignoreOnPhoneChange(this.this$1) == false) goto L5;
                return;
            L5:
                int r0 = PhoneView.m16001$$Nest$fgetphoneField(this.this$1).getSelectionStart();
                String r1 = PhoneView.m16001$$Nest$fgetphoneField(this.this$1).getText().toString();
                if (this.characterAction != 3) goto L8;
                r1 = r1.substring(0, this.actionPosition).concat(r1.substring(this.actionPosition + 1));
                r0 = r0 - 1;
            L8:
                StringBuilder r2 = new StringBuilder(r1.length());
                int r6 = 0;
            L10:
                if (r6 >= r1.length()) goto L15;
                int r7 = r6 + 1;
                String r62 = r1.substring(r6, r7);
                if ("0123456789".contains(r62) == false) goto L14;
                r2.append(r62);
            L14:
                r6 = r7;
                goto L10
            L15:
                PhoneView.m16006$$Nest$fputignoreOnPhoneChange(this.this$1, true);
                String r12 = PhoneView.m16001$$Nest$fgetphoneField(this.this$1).getHintText();
                if (r12 == null) goto L37;
                int r63 = 0;
            L19:
                if (r63 >= r2.length()) goto L37;
                if (r63 >= r12.length()) goto L31;
                if (r12.charAt(r63) != ' ') goto L30;
                r2.insert(r63, ' ');
                r63 = r63 + 1;
                if (r0 != r63) goto L30;
                int r72 = this.characterAction;
                if (r72 == 2) goto L30;
                if (r72 == 3) goto L30;
                r0 = r0 + 1;
            L30:
                r63 = r63 + 1;
                goto L19
            L31:
                r2.insert(r63, ' ');
                if (r0 != (r63 + 1)) goto L37;
                int r13 = this.characterAction;
                if (r13 == 2) goto L37;
                if (r13 == 3) goto L37;
                r0 = r0 + 1;
            L37:
                r11.replace(0, r11.length(), r2);
                if (r0 < 0) goto L40;
                PhoneView.m16001$$Nest$fgetphoneField(this.this$1).setSelection(Math.min(r0, PhoneView.m16001$$Nest$fgetphoneField(this.this$1).length()));
            L40:
                PhoneView.m16001$$Nest$fgetphoneField(this.this$1).onTextChange();
                PhoneView.m16009$$Nest$minvalidateCountryHint(this.this$1);
                PhoneView.m16006$$Nest$fputignoreOnPhoneChange(this.this$1, false);
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence r2, int r3, int r4, int r5) {
                if (r4 != 0) goto L7;
                if (r5 != 1) goto L7;
                this.characterAction = 1;
                return;
            L7:
                if (r4 != 1) goto L16;
                if (r5 != 0) goto L16;
                if (r2.charAt(r3) != ' ') goto L14;
                if (r3 <= 0) goto L14;
                this.characterAction = 3;
                this.actionPosition = r3 - 1;
                return;
            L14:
                this.characterAction = 2;
                return;
            L16:
                this.characterAction = -1;
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }
        }

        /* renamed from: org.telegram.ui.LoginActivity$PhoneView$5, reason: invalid class name */
        public class AnonymousClass5 extends ReplacementSpan {
            final /* synthetic */ PhoneView this$1;

            public AnonymousClass5(PhoneView r1) {
                this.this$1 = r1;
            }

            @Override // android.text.style.ReplacementSpan
            public void draw(Canvas r1, CharSequence r2, int r3, int r4, float r5, int r6, int r7, int r8, Paint r9) {
            }

            @Override // android.text.style.ReplacementSpan
            public int getSize(Paint r1, CharSequence r2, int r3, int r4, Paint.FontMetricsInt r5) {
                return AndroidUtilities.dp(16.0f);
            }
        }

        /* renamed from: org.telegram.ui.LoginActivity$PhoneView$6, reason: invalid class name */
        public class AnonymousClass6 implements PhoneNumberConfirmView.IConfirmDialogCallback {
            final /* synthetic */ PhoneView this$1;
            final /* synthetic */ String val$code;

            public static /* synthetic */ void $r8$lambda$48Y7BYBysJ636JGsc3FYn273hK8(AnonymousClass6 r0, PhoneNumberConfirmView r1, String r2) {
                r0.lambda$onConfirm$1(r1, r2);
            }

            public static /* synthetic */ void $r8$lambda$OHiDTIPwP37foPm4Hk5uqMpPRMc(AnonymousClass6 r0, String r1, PhoneNumberConfirmView r2) {
                r0.lambda$onConfirm$0(r1, r2);
            }

            public AnonymousClass6(PhoneView r1, String r2) {
                this.this$1 = r1;
                this.val$code = r2;
            }

            private /* synthetic */ void lambda$onConfirm$0(String r2, PhoneNumberConfirmView r3) {
                this.this$1.onNextPressed(r2);
                LoginActivity.m15788$$Nest$fgetfloatingButton(this.this$1.this$0).progressView.sync(PhoneNumberConfirmView.m15980$$Nest$fgetfabButton(r3).progressView);
            }

            private /* synthetic */ void lambda$onConfirm$1(PhoneNumberConfirmView r2, String r3) {
                PhoneNumberConfirmView.m15984$$Nest$mdismiss(r2);
                AndroidUtilities.runOnUIThread(new LoginActivity$PhoneView$6$$ExternalSyntheticLambda1(this, r3, r2), 150);
            }

            private void onConfirm(PhoneNumberConfirmView r3) {
                PhoneView.m16003$$Nest$fputconfirmedNumber(this.this$1, true);
                LoginActivity.m15809$$Nest$fputcurrentDoneType(this.this$1.this$0, 0);
                LoginActivity.m15830$$Nest$mneedShowProgress(this.this$1.this$0, 0, false);
                PhoneNumberConfirmView.m15983$$Nest$manimateProgress(r3, new LoginActivity$PhoneView$6$$ExternalSyntheticLambda0(this, r3, this.val$code));
            }

            @Override // org.telegram.ui.LoginActivity.PhoneNumberConfirmView.IConfirmDialogCallback
            public void onConfirmPressed(PhoneNumberConfirmView r1, TextView r2) {
                onConfirm(r1);
            }

            @Override // org.telegram.ui.LoginActivity.PhoneNumberConfirmView.IConfirmDialogCallback
            public void onDismiss(PhoneNumberConfirmView r1) {
                LoginActivity.m15817$$Nest$fputphoneNumberConfirmView(this.this$1.this$0, null);
            }

            @Override // org.telegram.ui.LoginActivity.PhoneNumberConfirmView.IConfirmDialogCallback
            public void onEditPressed(PhoneNumberConfirmView r1, TextView r2) {
                PhoneNumberConfirmView.m15984$$Nest$mdismiss(r1);
            }

            @Override // org.telegram.ui.LoginActivity.PhoneNumberConfirmView.IConfirmDialogCallback
            public void onFabPressed(PhoneNumberConfirmView r1, TransformableLoginButtonView r2) {
                onConfirm(r1);
            }
        }

        /* renamed from: $r8$lambda$00mOsk-yq2GPdBpM7Ki_JKxAogw, reason: not valid java name */
        public static /* synthetic */ void m15988$r8$lambda$00mOskyq2GPdBpM7Ki_JKxAogw(PhoneView r0, HashMap r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$new$14(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$20AKXKAQsUZ-SeL8CFaznuf2D6U, reason: not valid java name */
        public static /* synthetic */ void m15989$r8$lambda$20AKXKAQsUZSeL8CFaznuf2D6U(PhoneView r0, Bundle r1, String r2, PhoneInputData r3, TLObject r4, TLObject r5, TLRPC.TL_error r6) {
            r0.lambda$onNextPressed$24(r1, r2, r3, r4, r5, r6);
        }

        public static /* synthetic */ void $r8$lambda$47Xp5jL1EmbgwtfP4D7eFek6jJY(PhoneView r0, View r1) {
            r0.lambda$new$9(r1);
        }

        public static /* synthetic */ void $r8$lambda$4PBL40ib9glMW5X_lMyVnuDM__I(PhoneView r0, View r1) {
            r0.lambda$new$6(r1);
        }

        public static /* synthetic */ boolean $r8$lambda$Bg22c220wv4Cm51eoKfUmPnfIj8(PhoneView r0, TextView r1, int r2, KeyEvent r3) {
            return r0.lambda$new$7(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$DXFtf44wD2X3aEsVUh8_3fTniK8(PhoneView r0, Long r1, TLRPC.auth_Authorization r2, String r3) {
            r0.lambda$requestPasskey$31(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$FBnHCYn7R6RNvHXsg6rIIc7JTNw(PhoneView r0, TLObject r1, HashMap r2) {
            r0.lambda$new$13(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$GLnUak53IDGxqiTn5lK4ElurBhY(PhoneView r0) {
            r0.lambda$new$4();
        }

        public static /* synthetic */ void $r8$lambda$GXzGq46we6yWR4Al6XZNykiZUuw(PhoneView r0, String r1) {
            r0.lambda$onNextPressed$18(r1);
        }

        public static /* synthetic */ View $r8$lambda$H3QknSvHpPSXjvO9NWPeRB8o_IM(Context r5) {
            TextView r0 = new TextView(r5);
            r0.setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(12.0f));
            r0.setTextSize(1, 16.0f);
            r0.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
            r0.setHintTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteHintText));
            r0.setMaxLines(1);
            r0.setSingleLine(true);
            r0.setEllipsize(TextUtils.TruncateAt.END);
            if (LocaleController.isRTL == false) goto L5;
            int r52 = 5;
        L6:
            r0.setGravity(r52 | 1);
            return r0;
        L5:
            r52 = 3;
            goto L6
        }

        public static /* synthetic */ void $r8$lambda$Jo8gKZOHQLIUrB_31rnEJWwmYBg(PhoneView r0, TLRPC.TL_error r1, TLObject r2) {
            r0.lambda$loadCountries$15(r1, r2);
        }

        /* renamed from: $r8$lambda$Lyvl2xKemGDL6XrSKSSrViPiX-0, reason: not valid java name */
        public static /* synthetic */ void m15990$r8$lambda$Lyvl2xKemGDL6XrSKSSrViPiX0(PhoneView r0) {
            r0.lambda$requestPasskey$28();
        }

        public static /* synthetic */ void $r8$lambda$PrfLy8oWOqlE66mEjeDwILJJz0M(PhoneView r0, int r1, AlertDialog r2, int r3) {
            r0.lambda$onNextPressed$19(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$TdpJmE9PamE7IkKrqnmcxyp3mN8(PhoneView r0, DialogInterface r1, int r2) {
            r0.lambda$showDebugMenu$0(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$WT3LFoZp1t0EX6V7tjhf29L0z6Y(PhoneView r0, View r1, boolean r2) {
            r0.lambda$new$3(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$aGrAtG_Sa99pHgCUhqZzhM_HuPM(PhoneView r0, TLRPC.TL_error r1, TLObject r2) {
            r0.lambda$requestPasskey$29(r1, r2);
        }

        /* renamed from: $r8$lambda$c-PZ39FUejj9RWbjU0xbg4SpTxE, reason: not valid java name */
        public static /* synthetic */ String m15991$r8$lambda$cPZ39FUejj9RWbjU0xbg4SpTxE(CountrySelectActivity.Country r0) {
            return r0.name;
        }

        public static /* synthetic */ void $r8$lambda$dSpuUAevP4IEEJIedS5ygq_C0gI(PhoneView r0, PendingIntent r1) {
            r0.lambda$requestPhoneNumberHint$25(r1);
        }

        public static /* synthetic */ void $r8$lambda$fnwtZZeYBkwTOb3XbFHwvoTrVC0(PhoneView r0, TLRPC.TL_error r1, TLObject r2, Bundle r3, String r4, PhoneInputData r5, TLObject r6) {
            r0.lambda$onNextPressed$23(r1, r2, r3, r4, r5, r6);
        }

        public static /* synthetic */ boolean $r8$lambda$gXyUbPNwGwd51a_dlVOlt5Vm06Y(PhoneView r0, TextView r1, int r2, KeyEvent r3) {
            return r0.lambda$new$8(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$gifPuoJX9UdUwMw_knLjyCzVDUw(PhoneView r0, TLRPC.TL_error r1, TLObject r2, String r3) {
            r0.lambda$onNextPressed$21(r1, r2, r3);
        }

        public static /* synthetic */ boolean $r8$lambda$iFKrPfIirl2hUcY5i_dHXKYgrug(String r0, String r1) {
            return PhoneNumberUtils.compare(r0, r1);
        }

        public static /* synthetic */ void $r8$lambda$jyEjiVLzy5ZGLBRbUQ2Z60oK5_0(PhoneView r0, CountrySelectActivity.Country r1) {
            r0.lambda$new$5(r1);
        }

        /* renamed from: $r8$lambda$kW5jI7HNGXLtol-hZcfNNi07IeM, reason: not valid java name */
        public static /* synthetic */ void m15992$r8$lambda$kW5jI7HNGXLtolhZcfNNi07IeM(PhoneView r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$requestPasskey$30(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$lyxwRQo6_j_vkq0qaIFK4SGrp1o(PhoneView r0, String r1) {
            r0.lambda$onNextPressed$17(r1);
        }

        public static /* synthetic */ void $r8$lambda$o21picy1OMraLC05MpCWformreE(PhoneView r0, String r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$onNextPressed$22(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$op8tdcyhoJdjb-n6TAnylPHyFMA, reason: not valid java name */
        public static /* synthetic */ void m15993$r8$lambda$op8tdcyhoJdjbn6TAnylPHyFMA(PhoneView r0, Context r1, View r2) {
            r0.lambda$new$1(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$pU3kC7y0vOoWEFrTLMRPCS_1n14(PhoneView r0, View r1) {
            r0.lambda$new$10(r1);
        }

        public static /* synthetic */ void $r8$lambda$ppl7nFPhQODxcWFveUsJGx048hw(PhoneView r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$loadCountries$16(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$qSEcFu8ZC1elxiCS2t9Rkqb77_s(PhoneView r0) {
            r0.lambda$onShow$27();
        }

        public static /* synthetic */ void $r8$lambda$uCFwx9kCULVeoZIIlqr59sgF_DI(PhoneView r0, Exception r1) {
            r0.lambda$requestPhoneNumberHint$26(r1);
        }

        /* renamed from: $r8$lambda$xWUkfDWDd-D3__8JqavAW9_FWmM, reason: not valid java name */
        public static /* synthetic */ void m15994$r8$lambda$xWUkfDWDdD3__8JqavAW9_FWmM(PhoneView r0, View r1) {
            r0.lambda$new$11(r1);
        }

        /* renamed from: -$$Nest$fgetcodeField, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AnimatedPhoneNumberEditText m15995$$Nest$fgetcodeField(PhoneView r0) {
            return r0.codeField;
        }

        /* renamed from: -$$Nest$fgetcodesMap, reason: not valid java name */
        public static /* bridge */ /* synthetic */ HashMap m15996$$Nest$fgetcodesMap(PhoneView r0) {
            return r0.codesMap;
        }

        /* renamed from: -$$Nest$fgetcountriesArray, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m15997$$Nest$fgetcountriesArray(PhoneView r0) {
            return r0.countriesArray;
        }

        /* renamed from: -$$Nest$fgetcountryState, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m15998$$Nest$fgetcountryState(PhoneView r0) {
            return r0.countryState;
        }

        /* renamed from: -$$Nest$fgetignoreOnPhoneChange, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m15999$$Nest$fgetignoreOnPhoneChange(PhoneView r0) {
            return r0.ignoreOnPhoneChange;
        }

        /* renamed from: -$$Nest$fgetignoreOnTextChange, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m16000$$Nest$fgetignoreOnTextChange(PhoneView r0) {
            return r0.ignoreOnTextChange;
        }

        /* renamed from: -$$Nest$fgetphoneField, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AnimatedPhoneNumberEditText m16001$$Nest$fgetphoneField(PhoneView r0) {
            return r0.phoneField;
        }

        /* renamed from: -$$Nest$fgetphoneOutlineView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ OutlineTextContainerView m16002$$Nest$fgetphoneOutlineView(PhoneView r0) {
            return r0.phoneOutlineView;
        }

        /* renamed from: -$$Nest$fputconfirmedNumber, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16003$$Nest$fputconfirmedNumber(PhoneView r0, boolean r1) {
            r0.confirmedNumber = r1;
        }

        /* renamed from: -$$Nest$fputcountryState, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16004$$Nest$fputcountryState(PhoneView r0, int r1) {
            r0.countryState = r1;
        }

        /* renamed from: -$$Nest$fputcurrentCountry, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16005$$Nest$fputcurrentCountry(PhoneView r0, CountrySelectActivity.Country r1) {
            r0.currentCountry = r1;
        }

        /* renamed from: -$$Nest$fputignoreOnPhoneChange, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16006$$Nest$fputignoreOnPhoneChange(PhoneView r0, boolean r1) {
            r0.ignoreOnPhoneChange = r1;
        }

        /* renamed from: -$$Nest$fputignoreOnTextChange, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16007$$Nest$fputignoreOnTextChange(PhoneView r0, boolean r1) {
            r0.ignoreOnTextChange = r1;
        }

        /* renamed from: -$$Nest$fputignoreSelection, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16008$$Nest$fputignoreSelection(PhoneView r0, boolean r1) {
            r0.ignoreSelection = r1;
        }

        /* renamed from: -$$Nest$minvalidateCountryHint, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16009$$Nest$minvalidateCountryHint(PhoneView r0) {
            r0.invalidateCountryHint();
        }

        /* renamed from: -$$Nest$msetCountryButtonText, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16010$$Nest$msetCountryButtonText(PhoneView r0, CharSequence r1) {
            r0.setCountryButtonText(r1);
        }

        /* renamed from: -$$Nest$msetCountryHint, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16011$$Nest$msetCountryHint(PhoneView r0, String r1, CountrySelectActivity.Country r2) {
            r0.setCountryHint(r1, r2);
        }

        public PhoneView(LoginActivity r27, Context r28) {
            this.this$0 = r27;
            super(r28);
            this.countryState = 0;
            this.countriesArray = new ArrayList();
            this.codesMap = new HashMap();
            this.phoneFormatMap = new HashMap();
            this.ignoreSelection = false;
            this.ignoreOnTextChange = false;
            this.ignoreOnPhoneChange = false;
            this.ignoreOnPhoneChangePaste = false;
            this.nextPressed = false;
            this.confirmedNumber = false;
            this.titleClickCount = 0;
            this.lastTitleClick = 0;
            this.wasCountryHintIndex = -1;
            this.requestedPasskey = false;
            this.requestingPasskey = false;
            setOrientation(1);
            setGravity(17);
            TextView r6 = new TextView(r28);
            this.titleView = r6;
            r6.setTextSize(1, 18.0f);
            this.titleView.setTypeface(AndroidUtilities.bold());
            TextView r62 = this.titleView;
            if (LoginActivity.m15776$$Nest$fgetactivityMode(r27) != 2) goto L5;
            int r7 = org.telegram.messenger.R.string.ChangePhoneNewNumber;
        L6:
            r62.setText(LocaleController.getString(r7));
            this.titleView.setGravity(17);
            this.titleView.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            addView(this.titleView, LayoutHelper.createFrame(-1, -2.0f, 1, 32.0f, 0.0f, 32.0f, 0.0f));
            this.titleView.setOnClickListener(new LoginActivity$PhoneView$$ExternalSyntheticLambda5(this, r28));
            LinkSpanDrawable.LinksTextView r63 = new LinkSpanDrawable.LinksTextView(r28);
            this.subtitleView = r63;
            if (LoginActivity.m15776$$Nest$fgetactivityMode(r27) != 2) goto L9;
            int r9 = org.telegram.messenger.R.string.ChangePhoneHelp;
        L10:
            r63.setText(LocaleController.getString(r9));
            this.subtitleView.setTextSize(1, 14.0f);
            this.subtitleView.setGravity(17);
            this.subtitleView.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
            addView(this.subtitleView, LayoutHelper.createLinear(-1, -2, 1, 32, 8, 32, 0));
            TextViewSwitcher r5 = new TextViewSwitcher(r28);
            this.countryButton = r5;
            r5.setFactory(new LoginActivity$PhoneView$$ExternalSyntheticLambda7(r28));
            Animation r52 = AnimationUtils.loadAnimation(r28, org.telegram.messenger.R.anim.text_in);
            r52.setInterpolator(Easings.easeInOutQuad);
            this.countryButton.setInAnimation(r52);
            ImageView r53 = new ImageView(r28);
            this.chevronRight = r53;
            r53.setImageResource(org.telegram.messenger.R.drawable.msg_inputarrow);
            LinearLayout r54 = new LinearLayout(r28);
            r54.setOrientation(0);
            r54.setGravity(16);
            r54.addView(this.countryButton, LayoutHelper.createLinear(0, -2, 1.0f, 0, 0, 0, 0));
            r54.addView(this.chevronRight, LayoutHelper.createLinearRelatively(24.0f, 24.0f, 0, 0.0f, 0.0f, 14.0f, 0.0f));
            OutlineTextContainerView r72 = new OutlineTextContainerView(r28);
            this.countryOutlineView = r72;
            r72.setText(LocaleController.getString(org.telegram.messenger.R.string.Country));
            this.countryOutlineView.addView(r54, LayoutHelper.createFrame(-1, -1.0f, 48, 0.0f, 0.0f, 0.0f, 0.0f));
            this.countryOutlineView.setForceUseCenter(true);
            this.countryOutlineView.setFocusable(true);
            this.countryOutlineView.setContentDescription(LocaleController.getString(org.telegram.messenger.R.string.Country));
            this.countryOutlineView.setOnFocusChangeListener(new LoginActivity$PhoneView$$ExternalSyntheticLambda8(this));
            addView(this.countryOutlineView, LayoutHelper.createLinear(-1, 58, 16.0f, 24.0f, 16.0f, 14.0f));
            this.countryOutlineView.setOnClickListener(new LoginActivity$PhoneView$$ExternalSyntheticLambda9(this));
            LinearLayout r55 = new LinearLayout(r28);
            r55.setOrientation(0);
            OutlineTextContainerView r73 = new OutlineTextContainerView(r28);
            this.phoneOutlineView = r73;
            r73.setClipChildren(false);
            this.phoneOutlineView.setClipToPadding(false);
            this.phoneOutlineView.addView(r55, LayoutHelper.createFrame(-1, 36.0f, 16, 16.0f, 8.0f, 16.0f, 8.0f));
            this.phoneOutlineView.setText(LocaleController.getString(org.telegram.messenger.R.string.PhoneNumber));
            addView(this.phoneOutlineView, LayoutHelper.createLinear(-1, 58, 16.0f, 8.0f, 16.0f, 8.0f));
            TextView r74 = new TextView(r28);
            this.plusTextView = r74;
            r74.setText("+");
            this.plusTextView.setTextSize(1, 16.0f);
            this.plusTextView.setFocusable(false);
            r55.addView(this.plusTextView, LayoutHelper.createLinear(-2, -2));
            AnonymousClass1 r75 = new AnonymousClass1(this, r28, r27);
            this.codeField = r75;
            r75.setInputType(3);
            this.codeField.setCursorSize(AndroidUtilities.dp(20.0f));
            this.codeField.setCursorWidth(1.5f);
            this.codeField.setPadding(AndroidUtilities.dp(10.0f), 0, 0, 0);
            this.codeField.setTextSize(1, 16.0f);
            this.codeField.setMaxLines(1);
            this.codeField.setGravity(19);
            this.codeField.setImeOptions(268435461);
            this.codeField.setBackground(null);
            AnimatedPhoneNumberEditText r76 = this.codeField;
            if (hasCustomKeyboard() == true) goto L13;
        L16:
            boolean r11 = true;
        L17:
            r76.setShowSoftInputOnFocus(r11);
            this.codeField.setContentDescription(LocaleController.getString(org.telegram.messenger.R.string.LoginAccessibilityCountryCode));
            r55.addView(this.codeField, LayoutHelper.createLinear(55, 36, -9.0f, 0.0f, 0.0f, 0.0f));
            this.codeField.addTextChangedListener(new AnonymousClass2(this, r27));
            this.codeField.setOnEditorActionListener(new LoginActivity$PhoneView$$ExternalSyntheticLambda10(this));
            this.codeDividerView = new View(r28);
            LinearLayout.LayoutParams r77 = LayoutHelper.createLinear(0, -1, 4.0f, 8.0f, 12.0f, 8.0f);
            r77.width = Math.max(2, AndroidUtilities.dp(0.5f));
            r55.addView(this.codeDividerView, r77);
            AnonymousClass3 r78 = new AnonymousClass3(this, r28, r27);
            this.phoneField = r78;
            r78.setInputType(3);
            this.phoneField.setPadding(0, 0, 0, 0);
            this.phoneField.setCursorSize(AndroidUtilities.dp(20.0f));
            this.phoneField.setCursorWidth(1.5f);
            this.phoneField.setTextSize(1, 16.0f);
            this.phoneField.setMaxLines(1);
            this.phoneField.setGravity(19);
            this.phoneField.setImeOptions(268435461);
            this.phoneField.setBackground(null);
            AnimatedPhoneNumberEditText r64 = this.phoneField;
            if (hasCustomKeyboard() == true) goto L20;
        L23:
            boolean r79 = true;
        L24:
            r64.setShowSoftInputOnFocus(r79);
            this.phoneField.setContentDescription(LocaleController.getString(org.telegram.messenger.R.string.PhoneNumber));
            r55.addView(this.phoneField, LayoutHelper.createLinear(0, 36, 1.0f));
            this.phoneField.addTextChangedListener(new AnonymousClass4(this, r27));
            this.phoneField.setOnEditorActionListener(new LoginActivity$PhoneView$$ExternalSyntheticLambda11(this));
            ImageView r65 = new ImageView(r28);
            this.phoneNumberHintButton = r65;
            r65.setImageResource(org.telegram.messenger.R.drawable.menu_phone);
            r65.setScaleType(ImageView.ScaleType.CENTER);
            r65.setContentDescription(LocaleController.getString(org.telegram.messenger.R.string.PhoneNumber));
            if (LoginActivity.m15776$$Nest$fgetactivityMode(r27) == 0) goto L27;
        L29:
            int r710 = 8;
        L30:
            r65.setVisibility(r710);
            r65.setOnClickListener(new LoginActivity$PhoneView$$ExternalSyntheticLambda12(this));
            r55.addView(r65, LayoutHelper.createLinear(36, 36, 8.0f, 0.0f, -8.0f, 0.0f));
            int r66 = 56;
            if (LoginActivity.m15795$$Nest$fgetnewAccount(r27) == true) goto L33;
        L41:
            int r56 = 72;
        L43:
            if (LoginActivity.m15776$$Nest$fgetactivityMode(r27) == 0) goto L45;
        L53:
            if (r56 > 0) goto L55;
        L57:
            HashMap r57 = new HashMap();
            BufferedReader r0 = new BufferedReader(new InputStreamReader(getResources().getAssets().open("countries.txt")));     // Catch: Exception -> L68
        L59:
            String r67 = r0.readLine();     // Catch: Exception -> L68
            if (r67 == null) goto L77;
            String[] r68 = r67.split(";");     // Catch: Exception -> L68
            CountrySelectActivity.Country r711 = new CountrySelectActivity.Country();     // Catch: Exception -> L68
            r711.name = r68[2];     // Catch: Exception -> L68
            String r92 = r68[0];     // Catch: Exception -> L68
            r711.code = r92;     // Catch: Exception -> L68
            r711.shortname = r68[1];     // Catch: Exception -> L68
            if (TextUtils.equals(r92, "FT") == true) goto L70;
            String r93 = LocaleController.getCountryName(r711.shortname);     // Catch: Exception -> L68
            if (TextUtils.isEmpty(r93) == true) goto L70;
            if (TextUtils.equals(r711.shortname, r93) == true) goto L70;
            r711.defaultName = r711.name;     // Catch: Exception -> L68
            r711.name = r93;     // Catch: Exception -> L68
        L70:
            this.countriesArray.add(0, r711);     // Catch: Exception -> L68
            List<CountrySelectActivity.Country> r94 = this.codesMap.get(r68[0]);     // Catch: Exception -> L68
            if (r94 != null) goto L73;
            HashMap<String, List<CountrySelectActivity.Country>> r95 = this.codesMap;     // Catch: Exception -> L68
            String r10 = r68[0];     // Catch: Exception -> L68
            ArrayList r112 = new ArrayList();     // Catch: Exception -> L68
            r95.put(r10, r112);     // Catch: Exception -> L68
            r94 = r112;
        L73:
            r94.add(r711);     // Catch: Exception -> L68
            if (r68.length <= 3) goto L76;
            this.phoneFormatMap.put(r68[0], Collections.singletonList(r68[3]));     // Catch: Exception -> L68
        L76:
            r57.put(r68[1], r68[2]);     // Catch: Exception -> L68
            goto L59
        L77:
            r0.close();     // Catch: Exception -> L68
        L80:
            Collections.sort(this.countriesArray, Comparator.comparing(new LoginActivity$PhoneView$$ExternalSyntheticLambda15()));
            TelephonyManager r02 = (TelephonyManager) ApplicationLoader.applicationContext.getSystemService("phone");     // Catch: Exception -> L83
        L85:
            r27.getAccountInstance().getConnectionsManager().sendRequest(new TLRPC.TL_help_getNearestDc(), new LoginActivity$PhoneView$$ExternalSyntheticLambda6(this, r57), 10);
            if (this.codeField.length() != 0) goto L89;
            setCountryButtonText(null);
            this.phoneField.setHintText(null);
            this.countryState = 1;
        L89:
            if (this.codeField.length() == 0) goto L91;
            this.phoneField.requestFocus();
            AnimatedPhoneNumberEditText r03 = this.phoneField;
            r03.setSelection(r03.length());
        L92:
            loadCountries();
            return;
        L91:
            this.codeField.requestFocus();
        L83:
            e = move-exception;
            FileLog.e(e);
        L68:
            e = move-exception;
            FileLog.e(e);
            goto L80
        L55:
            if (AndroidUtilities.isSmallScreen() == true) goto L57;
            Space r69 = new Space(r28);
            r69.setMinimumHeight(AndroidUtilities.dp(r56));
            addView(r69, LayoutHelper.createLinear(-2, -2));
            goto L57
        L45:
            if (UserConfig.getActivatedAccountsCount() <= 0) goto L53;
            CheckBoxCell r96 = new CheckBoxCell(r28, 2);
            this.testBackendCheckBox = r96;
            String r102 = LocaleController.getString(org.telegram.messenger.R.string.TestBackend);
            boolean r113 = r27.getConnectionsManager().isTestBackend();
            LoginActivity.m15819$$Nest$fputtestBackend(r27, r113);
            r96.setText(r102, _UrlKt.FRAGMENT_ENCODE_SET, r113, false);
            CheckBoxCell r712 = this.testBackendCheckBox;
            if (LocaleController.isRTL == true) goto L49;
        L51:
            r66 = 0;
        L52:
            addView(r712, LayoutHelper.createLinear(-2, -1, 51, 16, 0, 16 + r66, 0));
            r56 = r56 - 24;
            this.testBackendCheckBox.setOnClickListener(new LoginActivity$PhoneView$$ExternalSyntheticLambda14(this));
            goto L53
        L49:
            if (AndroidUtilities.isSmallScreen() == false) goto L51;
        L33:
            if (LoginActivity.m15776$$Nest$fgetactivityMode(r27) != 0) goto L41;
            CheckBoxCell r58 = new CheckBoxCell(r28, 2);
            this.syncContactsBox = r58;
            r58.setText(LocaleController.getString("SyncContacts", org.telegram.messenger.R.string.SyncContacts), _UrlKt.FRAGMENT_ENCODE_SET, LoginActivity.m15805$$Nest$fgetsyncContacts(r27), false);
            CheckBoxCell r59 = this.syncContactsBox;
            if (LocaleController.isRTL == true) goto L37;
        L39:
            int r97 = 0;
        L40:
            addView(r59, LayoutHelper.createLinear(-2, -1, 51, 16, 0, 16 + r97, 0));
            this.syncContactsBox.setOnClickListener(new LoginActivity$PhoneView$$ExternalSyntheticLambda13(this));
            r56 = 48;
            goto L43
        L37:
            if (AndroidUtilities.isSmallScreen() == false) goto L39;
            r97 = 56;
            goto L40
        L27:
            if (PushListenerController.GooglePushListenerServiceProvider.INSTANCE.hasServices() == false) goto L29;
            r710 = 0;
            goto L30
        L20:
            if (LoginActivity.m15823$$Nest$misCustomKeyboardForceDisabled(r27) == true) goto L23;
            r79 = false;
            goto L24
        L13:
            if (LoginActivity.m15823$$Nest$misCustomKeyboardForceDisabled(r27) == true) goto L16;
            r11 = false;
            goto L17
        L9:
            r9 = org.telegram.messenger.R.string.StartText;
            goto L10
        L5:
            r7 = org.telegram.messenger.R.string.YourNumber;
            goto L6
        }

        private void fillNumberInternal(String r13) {
            this.codeField.setAlpha(0.0f);
            this.phoneField.setAlpha(0.0f);
            if (TextUtils.isEmpty(r13) == true) goto L29;
            int r3 = 4;
            String r4 = null;
            if (r13.length() <= 4) goto L26;
        L6:
            if (r3 < 1) goto L23;
            String r0 = r13.substring(0, r3);
            List<CountrySelectActivity.Country> r5 = this.codesMap.get(r0);
            if (r5 != null) goto L11;
            CountrySelectActivity.Country r52 = null;
        L20:
            if (r52 != null) goto L21;
            r3 = r3 - 1;
            goto L6
        L21:
            r4 = r13.substring(r3);
            this.codeField.setText(r0);
            boolean r02 = true;
        L24:
            if (r02 == true) goto L26;
            r4 = r13.substring(1);
            this.codeField.setText(r13.substring(0, 1));
            goto L26
        L11:
            if (r5.size() <= 1) goto L19;
            String r6 = MessagesController.getGlobalMainSettings().getString("phone_code_last_matched_".concat(r0), null);
            r52 = r5.get(r5.size() - 1);
            if (r6 == null) goto L20;
            ArrayList<CountrySelectActivity.Country> r7 = this.countriesArray;
            int r8 = r7.size();
            int r9 = 0;
        L15:
            if (r9 >= r8) goto L20;
            CountrySelectActivity.Country r10 = r7.get(r9);
            r9 = r9 + 1;
            CountrySelectActivity.Country r102 = r10;
            if (Objects.equals(r102.shortname, r6) == false) goto L15;
            r52 = r102;
            goto L20
        L19:
            r52 = r5.get(0);
            goto L20
        L23:
            r02 = false;
        L26:
            if (r4 == null) goto L29;
            this.phoneField.requestFocus();
            this.phoneField.setText(r4);
            AnimatedPhoneNumberEditText r132 = this.phoneField;
            r132.setSelection(r132.length());
        L29:
            if (this.phoneField.length() <= 0) goto L32;
            AnimatorSet r133 = new AnimatorSet().setDuration(300);
            Property r53 = View.ALPHA;
            r133.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.codeField, r53, new float[]{1.0f}), ObjectAnimator.ofFloat(this.phoneField, r53, new float[]{1.0f})});
            r133.start();
            this.numberFilled = true;
            this.confirmedNumber = true;
            return;
        L32:
            this.codeField.setAlpha(1.0f);
            this.phoneField.setAlpha(1.0f);
        }

        private void invalidateCountryHint() {
            String r0 = this.countryCodeForHint;
            if (this.phoneField.getText() == null) goto L5;
            String r1 = this.phoneField.getText().toString().replace(" ", _UrlKt.FRAGMENT_ENCODE_SET);
        L6:
            String r5 = null;
            if (this.phoneFormatMap.get(r0) == null) goto L41;
            if (this.phoneFormatMap.get(r0).isEmpty() == true) goto L41;
            List<String> r4 = this.phoneFormatMap.get(r0);
            if (r1.isEmpty() == true) goto L19;
            int r7 = 0;
        L14:
            if (r7 >= r4.size()) goto L19;
            if (r1.startsWith(r4.get(r7).replace(" ", _UrlKt.FRAGMENT_ENCODE_SET).replace("X", _UrlKt.FRAGMENT_ENCODE_SET).replace(MVEL.VERSION_SUB, _UrlKt.FRAGMENT_ENCODE_SET)) == true) goto L20;
            r7 = r7 + 1;
        L20:
            if (r7 != (-1)) goto L34;
            int r12 = 0;
        L23:
            if (r12 >= r4.size()) goto L31;
            String r2 = r4.get(r12);
            if (r2.startsWith("X") == true) goto L30;
            if (r2.startsWith(MVEL.VERSION_SUB) == true) goto L30;
            r12 = r12 + 1;
        L30:
            r7 = r12;
        L31:
            if (r7 != (-1)) goto L34;
            r7 = 0;
        L34:
            if (this.wasCountryHintIndex == r7) goto L49;
            String r02 = this.phoneFormatMap.get(r0).get(r7);
            int r13 = this.phoneField.getSelectionStart();
            int r22 = this.phoneField.getSelectionEnd();
            AnimatedPhoneNumberEditText r3 = this.phoneField;
            if (r02 == null) goto L38;
            r5 = r02.replace('X', '0');
        L38:
            r3.setHintText(r5);
            AnimatedPhoneNumberEditText r03 = this.phoneField;
            r03.setSelection(Math.max(0, Math.min(r03.length(), r13)), Math.max(0, Math.min(this.phoneField.length(), r22)));
            this.wasCountryHintIndex = r7;
            return;
        L49:
            return;
        L19:
            r7 = -1;
        L41:
            if (this.wasCountryHintIndex == (-1)) goto L50;
            int r04 = this.phoneField.getSelectionStart();
            int r14 = this.phoneField.getSelectionEnd();
            this.phoneField.setHintText(null);
            this.phoneField.setSelection(r04, r14);
            this.wasCountryHintIndex = -1;
            return;
        L50:
            return;
        L5:
            r1 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L6
        }

        private /* synthetic */ void lambda$loadCountries$15(TLRPC.TL_error r10, TLObject r11) {
            if (r10 != null) goto L65;
            this.countriesArray.clear();
            this.codesMap.clear();
            this.phoneFormatMap.clear();
            TLRPC.TL_help_countriesList r112 = (TLRPC.TL_help_countriesList) r11;
            int r0 = 0;
        L5:
            if (r0 >= r112.countries.size()) goto L24;
            TLRPC.TL_help_country r1 = r112.countries.get(r0);
            int r2 = 0;
        L8:
            if (r2 >= r1.country_codes.size()) goto L22;
            TLRPC.TL_help_countryCode r3 = r1.country_codes.get(r2);
            if (r3 == null) goto L21;
            CountrySelectActivity.Country r4 = new CountrySelectActivity.Country();
            String r5 = r1.name;
            r4.name = r5;
            String r6 = r1.default_name;
            r4.defaultName = r6;
            if (r5 != null) goto L15;
            if (r6 == null) goto L15;
            r4.name = r6;
        L15:
            r4.code = r3.country_code;
            r4.shortname = r1.iso2;
            this.countriesArray.add(r4);
            List<CountrySelectActivity.Country> r52 = this.codesMap.get(r3.country_code);
            if (r52 != null) goto L18;
            HashMap<String, List<CountrySelectActivity.Country>> r53 = this.codesMap;
            String r62 = r3.country_code;
            ArrayList r7 = new ArrayList();
            r53.put(r62, r7);
            r52 = r7;
        L18:
            r52.add(r4);
            if (r3.patterns.size() <= 0) goto L21;
            this.phoneFormatMap.put(r3.country_code, r3.patterns);
        L21:
            r2 = r2 + 1;
            goto L8
        L22:
            r0 = r0 + 1;
            goto L5
        L24:
            if (LoginActivity.m15776$$Nest$fgetactivityMode(this.this$0) != 2) goto L50;
            String r113 = PhoneFormat.stripExceptNumbers(UserConfig.getInstance(LoginActivity.access$600(this.this$0)).getClientPhone());
            if (TextUtils.isEmpty(r113) == true) goto L50;
            int r12 = 4;
            if (r113.length() <= 4) goto L50;
        L30:
            if (r12 < 1) goto L49;
            String r22 = r113.substring(0, r12);
            List<CountrySelectActivity.Country> r32 = this.codesMap.get(r22);
            CountrySelectActivity.Country r42 = null;
            if (r32 != null) goto L35;
        L46:
            if (r42 != null) goto L47;
            r12 = r12 - 1;
            goto L30
        L47:
            this.codeField.setText(r22);
            goto L50
        L35:
            if (r32.size() <= 1) goto L45;
            String r43 = MessagesController.getGlobalMainSettings().getString("phone_code_last_matched_".concat(r22), null);
            if (r43 == null) goto L44;
            CountrySelectActivity.Country r02 = r32.get(r32.size() - 1);
            ArrayList<CountrySelectActivity.Country> r33 = this.countriesArray;
            int r54 = r33.size();
            int r63 = 0;
        L39:
            if (r63 >= r54) goto L43;
            CountrySelectActivity.Country r72 = r33.get(r63);
            r63 = r63 + 1;
            CountrySelectActivity.Country r73 = r72;
            if (Objects.equals(r73.shortname, r43) == false) goto L39;
            r02 = r73;
        L43:
            r42 = r02;
            goto L46
        L44:
            r02 = r32.get(r32.size() - 1);
            goto L43
        L45:
            r42 = r32.get(0);
            goto L46
        L49:
            this.codeField.setText(r113.substring(0, 1));
        L50:
            CountrySelectActivity.Country r102 = new CountrySelectActivity.Country();
            r102.name = "Test Backend";
            r102.code = "999";
            r102.shortname = "EX";
            this.countriesArray.add(r102);
            List<CountrySelectActivity.Country> r114 = this.codesMap.get(r102.code);
            if (r114 != null) goto L53;
            HashMap<String, List<CountrySelectActivity.Country>> r115 = this.codesMap;
            String r03 = r102.code;
            ArrayList r13 = new ArrayList();
            r115.put(r03, r13);
            r114 = r13;
        L53:
            r114.add(r102);
            this.phoneFormatMap.put(r102.code, Collections.singletonList("66 X XXXX"));
            return;
        }

        private /* synthetic */ void lambda$loadCountries$16(TLObject r2, TLRPC.TL_error r3) {
            AndroidUtilities.runOnUIThread(new LoginActivity$PhoneView$$ExternalSyntheticLambda27(this, r3, r2));
        }

        private /* synthetic */ void lambda$new$1(Context r8, View r9) {
            Toast r92 = this.lastTitleToast;
            if (r92 == null) goto L5;
            r92.cancel();
            this.lastTitleToast = null;
        L5:
            long r0 = System.currentTimeMillis();
            if (this.titleClickCount > 0) goto L8;
        L10:
            int r93 = this.titleClickCount + 1;
            this.titleClickCount = r93;
            this.lastTitleClick = r0;
            if (r93 < 5) goto L14;
            this.titleClickCount = 0;
            this.lastTitleClick = 0;
            showDebugMenu();
            return;
        L14:
            if (r93 <= 1) goto L17;
            Toast r82 = Toast.makeText(r8, LocaleController.formatPluralString("DebugMenuLoginToast", 5 - r93, new Object[0]), 0);
            this.lastTitleToast = r82;
            r82.show();
            return;
        L17:
            return;
        L8:
            if ((r0 - this.lastTitleClick) <= 1500) goto L10;
            this.titleClickCount = 0;
            goto L10
        }

        private /* synthetic */ void lambda$new$10(View r4) {
            if (this.this$0.getParentActivity() != null) goto L5;
            return;
        L5:
            LoginActivity.m15818$$Nest$fputsyncContacts(this.this$0, !LoginActivity.m15805$$Nest$fgetsyncContacts(r0));
            ((CheckBoxCell) r4).setChecked(LoginActivity.m15805$$Nest$fgetsyncContacts(this.this$0), true);
            boolean r42 = LoginActivity.m15805$$Nest$fgetsyncContacts(this.this$0);
            LoginActivity r3 = this.this$0;
            if (r42 == false) goto L9;
            BulletinFactory.of(LoginActivity.m15803$$Nest$fgetslideViewsContainer(r3), null).createSimpleBulletin(org.telegram.messenger.R.raw.contacts_sync_on, LocaleController.getString("SyncContactsOn", org.telegram.messenger.R.string.SyncContactsOn)).show();
            return;
        L9:
            BulletinFactory.of(LoginActivity.m15803$$Nest$fgetslideViewsContainer(r3), null).createSimpleBulletin(org.telegram.messenger.R.raw.contacts_sync_off, LocaleController.getString("SyncContactsOff", org.telegram.messenger.R.string.SyncContactsOff)).show();
        }

        private /* synthetic */ void lambda$new$11(View r4) {
            if (this.this$0.getParentActivity() != null) goto L5;
            return;
        L5:
            LoginActivity.m15819$$Nest$fputtestBackend(this.this$0, !LoginActivity.m15806$$Nest$fgettestBackend(r0));
            ((CheckBoxCell) r4).setChecked(LoginActivity.m15806$$Nest$fgettestBackend(this.this$0), true);
            if (this.this$0.getConnectionsManager().isTestBackend() == LoginActivity.m15806$$Nest$fgettestBackend(this.this$0)) goto L8;
            this.this$0.getConnectionsManager().switchBackend(false);
        L8:
            loadCountries();
        }

        private /* synthetic */ void lambda$new$13(TLObject r2, HashMap r3) {
            if (r2 == null) goto L9;
            TLRPC.TL_nearestDc r22 = (TLRPC.TL_nearestDc) r2;
            if (this.codeField.length() != 0) goto L8;
            setCountry(r3, r22.country.toUpperCase());
            return;
        L8:
            return;
        }

        private /* synthetic */ void lambda$new$14(HashMap r1, TLObject r2, TLRPC.TL_error r3) {
            AndroidUtilities.runOnUIThread(new LoginActivity$PhoneView$$ExternalSyntheticLambda22(this, r2, r1));
        }

        private /* synthetic */ void lambda$new$3(View r1, boolean r2) {
            OutlineTextContainerView r0 = this.countryOutlineView;
            if (r2 == false) goto L5;
            float r12 = 1.0f;
        L6:
            r0.animateSelection(r12);
            return;
        L5:
            r12 = 0.0f;
            goto L6
        }

        private /* synthetic */ void lambda$new$4() {
            LoginActivity.m15836$$Nest$mshowKeyboard(this.this$0, this.phoneField);
        }

        private /* synthetic */ void lambda$new$5(CountrySelectActivity.Country r3) {
            selectCountry(r3);
            AndroidUtilities.runOnUIThread(new LoginActivity$PhoneView$$ExternalSyntheticLambda24(this), 300);
            this.phoneField.requestFocus();
            AnimatedPhoneNumberEditText r2 = this.phoneField;
            r2.setSelection(r2.length());
        }

        private /* synthetic */ void lambda$new$6(View r3) {
            CountrySelectActivity r32 = new CountrySelectActivity(true, this.countriesArray);
            r32.setCountrySelectActivityDelegate(new LoginActivity$PhoneView$$ExternalSyntheticLambda21(this));
            this.this$0.presentFragment(r32);
        }

        private /* synthetic */ boolean lambda$new$7(TextView r1, int r2, KeyEvent r3) {
            if (r2 != 5) goto L6;
            this.phoneField.requestFocus();
            AnimatedPhoneNumberEditText r0 = this.phoneField;
            r0.setSelection(r0.length());
            return true;
        L6:
            return false;
        }

        private /* synthetic */ boolean lambda$new$8(TextView r1, int r2, KeyEvent r3) {
            if (r2 == 5) goto L5;
            return false;
        L5:
            if (LoginActivity.m15797$$Nest$fgetphoneNumberConfirmView(this.this$0) == null) goto L8;
            PhoneNumberConfirmView.m15980$$Nest$fgetfabButton(LoginActivity.m15797$$Nest$fgetphoneNumberConfirmView(this.this$0)).callOnClick();
            return true;
        L8:
            onNextPressed(null);
            return true;
        }

        private /* synthetic */ void lambda$new$9(View r1) {
            requestPhoneNumberHint();
        }

        private /* synthetic */ void lambda$onNextPressed$17(String r1) {
            onNextPressed(r1);
        }

        private /* synthetic */ void lambda$onNextPressed$18(String r4) {
            postDelayed(new LoginActivity$PhoneView$$ExternalSyntheticLambda18(this, r4), 200);
        }

        private /* synthetic */ void lambda$onNextPressed$19(int r1, AlertDialog r2, int r3) {
            if (UserConfig.selectedAccount == r1) goto L5;
            ((LaunchActivity) this.this$0.getParentActivity()).switchToAccount(r1, true);
        L5:
            this.this$0.finishFragment();
        }

        private /* synthetic */ void lambda$onNextPressed$21(TLRPC.TL_error r5, TLObject r6, String r7) {
            this.nextPressed = false;
            LoginActivity.m15835$$Nest$mshowDoneButton(this.this$0, false, true);
            if (r5 != null) goto L10;
            TL_account.Password r62 = (TL_account.Password) r6;
            if (TwoStepVerificationActivity.canHandleCurrentPassword(r62, true) == true) goto L8;
            AlertsCreator.showUpdateAppAlert(this.this$0.getParentActivity(), LocaleController.getString("UpdateAppAlert", org.telegram.messenger.R.string.UpdateAppAlert), true);
            return;
        L8:
            Bundle r52 = new Bundle();
            SerializedData r1 = new SerializedData(r62.getObjectSize());
            r62.serializeToStream(r1);
            r52.putString("password", Utilities.bytesToHex(r1.toByteArray()));
            r52.putString("phoneFormated", r7);
            this.this$0.setPage(6, true, r52, false);
            return;
        L10:
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), r5.text);
        }

        private /* synthetic */ void lambda$onNextPressed$22(String r2, TLObject r3, TLRPC.TL_error r4) {
            AndroidUtilities.runOnUIThread(new LoginActivity$PhoneView$$ExternalSyntheticLambda30(this, r4, r3, r2));
        }

        private /* synthetic */ void lambda$onNextPressed$23(TLRPC.TL_error r4, TLObject r5, Bundle r6, String r7, PhoneInputData r8, TLObject r9) {
            this.nextPressed = false;
            if (r4 == null) goto L5;
            String r52 = r4.text;
            if (r52 != null) goto L17;
        L47:
            if (LoginActivity.m15791$$Nest$fgetisRequestingFirebaseSms(this.this$0) == true) goto L50;
            LoginActivity.m15826$$Nest$mneedHideProgress(this.this$0, false);
            return;
        L50:
            return;
        L17:
            if (r52.contains("SESSION_PASSWORD_NEEDED") == false) goto L20;
            TL_account.getPassword r42 = new TL_account.getPassword();
            ConnectionsManager.getInstance(LoginActivity.access$400(this.this$0)).sendRequest(r42, new LoginActivity$PhoneView$$ExternalSyntheticLambda25(this, r7), 10);
            goto L47
        L20:
            if (r4.text.contains("PHONE_NUMBER_INVALID") == false) goto L23;
            LoginActivity.needShowInvalidAlert(this.this$0, r7, r8, false);
            goto L47
        L23:
            if (r4.text.contains("PHONE_PASSWORD_FLOOD") == false) goto L26;
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString("FloodWait", org.telegram.messenger.R.string.FloodWait));
            goto L47
        L26:
            if (r4.text.contains("PHONE_NUMBER_FLOOD") == false) goto L29;
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString("PhoneNumberFlood", org.telegram.messenger.R.string.PhoneNumberFlood));
            goto L47
        L29:
            if (r4.text.contains("PHONE_NUMBER_BANNED") == false) goto L32;
            LoginActivity.needShowInvalidAlert(this.this$0, r7, r8, true);
            goto L47
        L32:
            if (r4.text.contains("PHONE_CODE_EMPTY") == false) goto L34;
        L45:
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString(org.telegram.messenger.R.string.InvalidCode));
            goto L47
        L34:
            if (r4.text.contains("PHONE_CODE_INVALID") == true) goto L45;
            if (r4.text.contains("PHONE_CODE_EXPIRED") == false) goto L40;
            onBackPressed(true);
            this.this$0.setPage(0, true, null, true);
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString("CodeExpired", org.telegram.messenger.R.string.CodeExpired));
            goto L47
        L40:
            if (r4.text.startsWith("FLOOD_WAIT") == false) goto L43;
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString("FloodWait", org.telegram.messenger.R.string.FloodWait));
            goto L47
        L43:
            if (r4.code == (-1000)) goto L47;
            AlertsCreator.processError(LoginActivity.access$500(this.this$0), r4, this.this$0, r9, new Object[]{PhoneInputData.m15973$$Nest$fgetphoneNumber(r8)});
            goto L47
        L5:
            if ((r5 instanceof TLRPC.TL_auth_sentCodeSuccess) == false) goto L13;
            TLRPC.auth_Authorization r43 = ((TLRPC.TL_auth_sentCodeSuccess) r5).authorization;
            if ((r43 instanceof TLRPC.TL_auth_authorizationSignUpRequired) == false) goto L12;
            TLRPC.TL_help_termsOfService r44 = ((TLRPC.TL_auth_authorizationSignUpRequired) r43).terms_of_service;
            if (r44 == null) goto L11;
            LoginActivity.m15810$$Nest$fputcurrentTermsOfService(this.this$0, r44);
        L11:
            this.this$0.setPage(5, true, r6, false);
            goto L47
        L12:
            LoginActivity.m15831$$Nest$monAuthSuccess(this.this$0, (TLRPC.TL_auth_authorization) r43);
            goto L47
        L13:
            LoginActivity.m15820$$Nest$mfillNextCodeParams(this.this$0, r6, (TLRPC.auth_SentCode) r5);
            goto L47
        }

        private /* synthetic */ void lambda$onNextPressed$24(Bundle r9, String r10, PhoneInputData r11, TLObject r12, TLObject r13, TLRPC.TL_error r14) {
            AndroidUtilities.runOnUIThread(new LoginActivity$PhoneView$$ExternalSyntheticLambda19(this, r14, r13, r9, r10, r11, r12));
        }

        private /* synthetic */ void lambda$onShow$27() {
            if (this.phoneField == null) goto L12;
            if (this.codeField.length() == 0) goto L10;
            this.phoneField.requestFocus();
            if (this.numberFilled == true) goto L9;
            AnimatedPhoneNumberEditText r0 = this.phoneField;
            r0.setSelection(r0.length());
        L9:
            LoginActivity.m15836$$Nest$mshowKeyboard(this.this$0, this.phoneField);
            goto L12
        L10:
            this.codeField.requestFocus();
            LoginActivity.m15836$$Nest$mshowKeyboard(this.this$0, this.codeField);
        L12:
            if (LoginActivity.m15776$$Nest$fgetactivityMode(this.this$0) != 0) goto L15;
            requestPasskey(false);
            return;
        }

        private /* synthetic */ void lambda$requestPasskey$28() {
            requestPasskey(true);
        }

        private /* synthetic */ void lambda$requestPasskey$29(TLRPC.TL_error r5, TLObject r6) {
            this.nextPressed = false;
            LoginActivity.m15835$$Nest$mshowDoneButton(this.this$0, false, true);
            if (r5 != null) goto L10;
            TL_account.Password r62 = (TL_account.Password) r6;
            if (TwoStepVerificationActivity.canHandleCurrentPassword(r62, true) == true) goto L8;
            AlertsCreator.showUpdateAppAlert(this.this$0.getParentActivity(), LocaleController.getString("UpdateAppAlert", org.telegram.messenger.R.string.UpdateAppAlert), true);
            return;
        L8:
            Bundle r52 = new Bundle();
            SerializedData r1 = new SerializedData(r62.getObjectSize());
            r62.serializeToStream(r1);
            r52.putString("password", Utilities.bytesToHex(r1.toByteArray()));
            this.this$0.setPage(6, true, r52, false);
            return;
        L10:
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), r5.text);
        }

        private /* synthetic */ void lambda$requestPasskey$30(TLObject r2, TLRPC.TL_error r3) {
            AndroidUtilities.runOnUIThread(new LoginActivity$PhoneView$$ExternalSyntheticLambda31(this, r3, r2));
        }

        private /* synthetic */ void lambda$requestPasskey$31(Long r8, TLRPC.auth_Authorization r9, String r10) {
            this.cancelRequestingPasskey = null;
            this.requestingPasskey = false;
            if (r10 == null) goto L15;
            if ("EMPTY".equals(r10) == true) goto L9;
            if ("CANCELLED".equals(r10) == false) goto L15;
        L9:
            if (this.subtitleView != null) goto L11;
            return;
        L11:
            if ("CANCELLED".equals(r10) == false) goto L53;
            this.subtitleView.setText(AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.getString(org.telegram.messenger.R.string.StartTextPasskey), new LoginActivity$PhoneView$$ExternalSyntheticLambda28(this)), true));
            return;
        L53:
            return;
        L15:
            if (r8.longValue() != 0) goto L17;
        L34:
            if (r10 != null) goto L36;
        L38:
            if (r10 != null) goto L40;
        L44:
            if ((r9 instanceof TLRPC.TL_auth_authorization) == false) goto L55;
            LoginActivity.m15831$$Nest$monAuthSuccess(this.this$0, (TLRPC.TL_auth_authorization) r9);
            return;
        L55:
            return;
        L40:
            if (BuildVars.DEBUG_VERSION == false) goto L54;
            BulletinFactory.of(this.this$0).showForError(r10);
            return;
        L54:
            return;
        L36:
            if (r10.contains("SESSION_PASSWORD_NEEDED") == false) goto L38;
            ConnectionsManager.getInstance(LoginActivity.access$300(this.this$0)).sendRequest(new TL_account.getPassword(), new LoginActivity$PhoneView$$ExternalSyntheticLambda29(this), 10);
            goto L44
        L17:
            if ((this.this$0.getParentActivity() instanceof LaunchActivity) == false) goto L34;
            int r2 = 0;
        L20:
            if (r2 >= 16) goto L34;
            UserConfig r3 = UserConfig.getInstance(r2);
            if (r3.isClientActivated() == false) goto L33;
            if (r3.getClientUserId() != r8.longValue()) goto L33;
            if (ConnectionsManager.getInstance(r2).isTestBackend() != LoginActivity.m15806$$Nest$fgettestBackend(this.this$0)) goto L33;
            if (UserConfig.selectedAccount == r2) goto L31;
            ((LaunchActivity) this.this$0.getParentActivity()).switchToAccount(r2, true);
        L31:
            this.this$0.finishFragment();
            LoginActivity.m15826$$Nest$mneedHideProgress(this.this$0, false);
            return;
        L33:
            r2 = r2 + 1;
            goto L20
        }

        private /* synthetic */ void lambda$requestPhoneNumberHint$25(PendingIntent r10) {
            if (this.this$0.getParentActivity() != null) goto L5;
        L12:
            this.requestingPhoneNumberHint = false;
            return;
        L5:
            if (LoginActivity.m15782$$Nest$fgetcurrentViewNum(this.this$0) != 0) goto L12;
            AndroidUtilities.hideKeyboard(this.this$0.fragmentView);     // Catch: IntentSender.SendIntentException -> L9
            this.this$0.getParentActivity().startIntentSenderForResult(r10.getIntentSender(), 201, null, 0, 0, 0);     // Catch: IntentSender.SendIntentException -> L9
            return;
        L9:
            e = move-exception;
            this.requestingPhoneNumberHint = false;
            FileLog.e(e);
        }

        private /* synthetic */ void lambda$requestPhoneNumberHint$26(Exception r2) {
            this.requestingPhoneNumberHint = false;
            FileLog.e(r2);
        }

        private /* synthetic */ void lambda$showDebugMenu$0(DialogInterface r4, int r5) {
            if (r5 != 0) goto L16;
            BuildVars.LOGS_ENABLED = !BuildVars.LOGS_ENABLED;
            ApplicationLoader.applicationContext.getSharedPreferences("systemConfig", 0).edit().putBoolean("logsEnabled", BuildVars.LOGS_ENABLED).commit();
            BulletinFactory r3 = BulletinFactory.of(this.this$0);
            int r52 = org.telegram.messenger.R.raw.chats_infotip;
            if (BuildVars.LOGS_ENABLED == false) goto L7;
            String r1 = "Logs enabled.";
        L8:
            r3.createSimpleBulletin(r52, r1).show();
            if (BuildVars.LOGS_ENABLED == false) goto L20;
            FileLog.d("app start time = " + ApplicationLoader.startTime);
            FileLog.d("buildVersion = " + ApplicationLoader.applicationContext.getPackageManager().getPackageInfo(ApplicationLoader.applicationContext.getPackageName(), 0).versionCode);     // Catch: Exception -> L13
            return;
        L13:
            e = move-exception;
            FileLog.e(e);
            return;
        L20:
            return;
        L7:
            r1 = "Logs disabled.";
            goto L8
        L16:
            ProfileActivity.sendLogs(this.this$0.getParentActivity(), false);
        }

        private void loadCountries() {
            TLRPC.TL_help_getCountriesList r0 = new TLRPC.TL_help_getCountriesList();
            if (LocaleController.getInstance().getCurrentLocaleInfo() == null) goto L5;
            String r1 = LocaleController.getInstance().getCurrentLocaleInfo().getLangCode();
        L6:
            r0.lang_code = r1;
            this.this$0.getConnectionsManager().sendRequest(r0, new LoginActivity$PhoneView$$ExternalSyntheticLambda20(this), 10);
            return;
        L5:
            r1 = Locale.getDefault().getCountry();
            goto L6
        }

        private void requestPasskey(boolean r4) {
            if (LoginActivity.m15776$$Nest$fgetactivityMode(this.this$0) == 0) goto L6;
            return;
        L6:
            if (Build.VERSION.SDK_INT >= 28) goto L8;
            return;
        L8:
            if (BuildVars.SUPPORTS_PASSKEYS == true) goto L11;
            return;
        L11:
            if (this.requestingPasskey == true) goto L19;
            if (r4 == false) goto L14;
        L16:
            this.requestingPasskey = true;
            this.requestedPasskey = true;
            this.cancelRequestingPasskey = PasskeysController.login(getContext(), LoginActivity.access$200(this.this$0), r4, new LoginActivity$PhoneView$$ExternalSyntheticLambda26(this));
            return;
        L14:
            if (this.requestedPasskey == false) goto L16;
            return;
        }

        private void setCountry(HashMap<String, String> r3, String r4) {
            if (r3.get(r4) != null) goto L5;
            return;
        L5:
            if (this.countriesArray == null) goto L24;
            int r0 = 0;
        L8:
            if (r0 >= this.countriesArray.size()) goto L15;
            if (this.countriesArray.get(r0) == null) goto L14;
            if (this.countriesArray.get(r0).name.equals(r4) == false) goto L14;
            CountrySelectActivity.Country r42 = this.countriesArray.get(r0);
        L16:
            if (r42 == null) goto L25;
            this.codeField.setText(r42.code);
            this.countryState = 0;
            return;
        L25:
            return;
        L14:
            r0 = r0 + 1;
            goto L8
        L15:
            r42 = null;
            goto L16
        }

        private void setCountryButtonText(CharSequence r4) {
            Context r0 = ApplicationLoader.applicationContext;
            if (this.countryButton.getCurrentView().getText() == null) goto L6;
            if (r4 != null) goto L6;
            int r1 = org.telegram.messenger.R.anim.text_out_down;
        L7:
            Animation r02 = AnimationUtils.loadAnimation(r0, r1);
            r02.setInterpolator(Easings.easeInOutQuad);
            this.countryButton.setOutAnimation(r02);
            CharSequence r03 = this.countryButton.getCurrentView().getText();
            TextViewSwitcher r12 = this.countryButton;
            if (TextUtils.isEmpty(r4) == false) goto L12;
            if (TextUtils.isEmpty(r03) == false) goto L12;
        L14:
            boolean r04 = false;
        L15:
            r12.setText(r4, r04);
            OutlineTextContainerView r3 = this.countryOutlineView;
            if (r4 == null) goto L18;
            float r42 = 1.0f;
        L19:
            r3.animateSelection(r42);
            return;
        L18:
            r42 = 0.0f;
        L12:
            if (Objects.equals(r03, r4) == true) goto L14;
            r04 = true;
        L6:
            r1 = org.telegram.messenger.R.anim.text_out;
            goto L7
        }

        private void setCountryHint(String r6, CountrySelectActivity.Country r7) {
            SpannableStringBuilder r0 = new SpannableStringBuilder();
            String r1 = LocaleController.getLanguageFlag(r7.shortname);
            if (r1 == null) goto L5;
            r0.append(r1).append(" ");
            r0.setSpan(new AnonymousClass5(this), r1.length(), r1.length() + 1, 0);
        L5:
            r0.append(r7.name);
            setCountryButtonText(Emoji.replaceEmoji(r0, this.countryButton.getCurrentView().getPaint().getFontMetricsInt(), false));
            this.countryCodeForHint = r6;
            this.wasCountryHintIndex = -1;
            invalidateCountryHint();
        }

        private void showDebugMenu() {
            AlertDialog.Builder r0 = new AlertDialog.Builder(getContext()).setTitle(LocaleController.getString(org.telegram.messenger.R.string.SettingsDebug));
            if (BuildVars.LOGS_ENABLED == false) goto L6;
            int r1 = org.telegram.messenger.R.string.DebugMenuDisableLogs;
        L5:
            String r12 = LocaleController.getString(r1);
            r0.setItems(new String[]{r12, LocaleController.getString(org.telegram.messenger.R.string.DebugSendLogs)}, new LoginActivity$PhoneView$$ExternalSyntheticLambda23(this)).show();
            return;
        L6:
            r1 = org.telegram.messenger.R.string.DebugMenuEnableLogs;
            goto L5
        }

        @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
        public void didReceivedNotification(int r1, int r2, Object... r3) {
            if (r1 != NotificationCenter.emojiLoaded) goto L6;
            this.countryButton.getCurrentView().invalidate();
            return;
        }

        @Override // org.telegram.ui.Components.SlideView
        public String getHeaderName() {
            return LocaleController.getString("YourPhone", org.telegram.messenger.R.string.YourPhone);
        }

        public void handlePhoneNumberHintResult(int r2, Intent r3) {
            this.requestingPhoneNumberHint = false;
            if (r2 != (-1)) goto L17;
            if (r3 != null) goto L6;
            return;
        L6:
            if (this.this$0.getParentActivity() == null) goto L20;
            String r22 = PhoneFormat.stripExceptNumbers(Identity.getSignInClient(this.this$0.getParentActivity()).getPhoneNumberFromIntent(r3));     // Catch: Exception -> L12
            if (TextUtils.isEmpty(r22) == true) goto L19;
            fillNumberInternal(r22);     // Catch: Exception -> L12
            return;
        L19:
            return;
        L12:
            e = move-exception;
            FileLog.e(e);
            return;
        L20:
            return;
        }

        @Override // org.telegram.ui.Components.SlideView
        public boolean hasCustomKeyboard() {
            return true;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.emojiLoaded);
        }

        @Override // org.telegram.ui.Components.SlideView
        public void onCancelPressed() {
            this.nextPressed = false;
        }

        @Override // org.telegram.ui.Components.SlideView
        public void onDestroyActivity() {
            super.onDestroyActivity();
            Runnable r0 = this.cancelRequestingPasskey;
            if (r0 == null) goto L6;
            r0.run();
            this.cancelRequestingPasskey = null;
            return;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.emojiLoaded);
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> r1, View r2, int r3, long r4) {
            if (this.ignoreSelection == false) goto L6;
            this.ignoreSelection = false;
            return;
        L6:
            this.ignoreOnTextChange = true;
            CountrySelectActivity.Country r12 = this.countriesArray.get(r3);
            this.codeField.setText(r12.code);
            this.ignoreOnTextChange = false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.telegram.ui.Components.SlideView
        public void onNextPressed(String r17) {
            if (this.this$0.getParentActivity() != null) goto L5;
            return;
        L5:
            if (this.nextPressed == false) goto L7;
            return;
        L7:
            if (LoginActivity.m15791$$Nest$fgetisRequestingFirebaseSms(this.this$0) == true) goto L160;
            TelephonyManager r3 = (TelephonyManager) ApplicationLoader.applicationContext.getSystemService("phone");
            if (BuildVars.DEBUG_VERSION == false) goto L13;
            FileLog.d("sim status = " + r3.getSimState());
        L13:
            if (this.codeField.length() != 0) goto L15;
        L140:
            LoginActivity.m15834$$Nest$monFieldError(this.this$0, this.phoneOutlineView, false);
            return;
        L15:
            if (this.phoneField.length() == 0) goto L140;
            String r13 = "+" + this.codeField.getText() + " " + this.phoneField.getText();
            if (this.confirmedNumber == true) goto L29;
            Point r2 = AndroidUtilities.displaySize;
            if (r2.x > r2.y) goto L22;
        L27:
            LoginActivity r22 = this.this$0;
            Context r10 = this.this$0.fragmentView.getContext();
            LoginActivity r32 = this.this$0;
            LoginActivity.m15817$$Nest$fputphoneNumberConfirmView(r22, new PhoneNumberConfirmView(r10, (ViewGroup) r32.fragmentView, LoginActivity.m15788$$Nest$fgetfloatingButton(r32), r13, new AnonymousClass6(this, r17), null));
            PhoneNumberConfirmView.m15985$$Nest$mshow(LoginActivity.m15797$$Nest$fgetphoneNumberConfirmView(this.this$0));
            return;
        L22:
            if (LoginActivity.m15824$$Nest$misCustomKeyboardVisible(this.this$0) == true) goto L27;
            if (LoginActivity.m15802$$Nest$fgetsizeNotifierFrameLayout(this.this$0).measureKeyboardHeight() <= AndroidUtilities.dp(20.0f)) goto L27;
            LoginActivity.m15815$$Nest$fputkeyboardHideCallback(this.this$0, new LoginActivity$PhoneView$$ExternalSyntheticLambda1(this, r17));
            AndroidUtilities.hideKeyboard(this.this$0.fragmentView);
            return;
        L29:
            this.confirmedNumber = false;
            if (LoginActivity.m15797$$Nest$fgetphoneNumberConfirmView(this.this$0) == null) goto L32;
            PhoneNumberConfirmView.m15984$$Nest$mdismiss(LoginActivity.m15797$$Nest$fgetphoneNumberConfirmView(this.this$0));
        L32:
            boolean r0 = AndroidUtilities.isSimAvailable();
            int r33 = this.countryState;
            if (r33 != 1) goto L37;
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString("ChooseCountry", org.telegram.messenger.R.string.ChooseCountry));
            LoginActivity.m15826$$Nest$mneedHideProgress(this.this$0, false);
            return;
        L37:
            if (r33 == 2) goto L39;
        L42:
            String r34 = PhoneFormat.stripExceptNumbers(_UrlKt.FRAGMENT_ENCODE_SET + this.codeField.getText() + this.phoneField.getText());
            if (LoginActivity.m15776$$Nest$fgetactivityMode(this.this$0) == 0) goto L45;
        L69:
            TLRPC.TL_codeSettings r102 = new TLRPC.TL_codeSettings();
            r102.allow_flashcall = r0;
            r102.allow_missed_call = r0;
            boolean r02 = PushListenerController.GooglePushListenerServiceProvider.INSTANCE.hasServices();
            r102.allow_firebase = r02;
            r102.allow_app_hash = r02;
            if (LoginActivity.m15790$$Nest$fgetforceDisableSafetyNet(this.this$0) == false) goto L72;
        L73:
            r102.allow_firebase = false;
        L74:
            ArrayList<TLRPC.TL_auth_authorization> r03 = AuthTokensHelper.getSavedLogInTokens();
            if (r03 == null) goto L92;
            int r132 = 0;
        L78:
            if (r132 >= r03.size()) goto L92;
            if (r03.get(r132).future_auth_token == null) goto L91;
            if (r102.logout_tokens != null) goto L86;
            r102.logout_tokens = new ArrayList();
        L86:
            if (BuildVars.DEBUG_VERSION == false) goto L88;
            FileLog.d("login token to check ".concat(new String(r03.get(r132).future_auth_token, StandardCharsets.UTF_8)));
        L88:
            r102.logout_tokens.add(r03.get(r132).future_auth_token);
            if (r102.logout_tokens.size() >= 20) goto L92;
        L91:
            r132 = r132 + 1;
        L92:
            ArrayList<TLRPC.TL_auth_loggedOut> r04 = AuthTokensHelper.getSavedLogOutTokens();
            if (r04 == null) goto L106;
            int r11 = 0;
        L96:
            if (r11 >= r04.size()) goto L104;
            if (r102.logout_tokens != null) goto L100;
            r102.logout_tokens = new ArrayList();
        L100:
            r102.logout_tokens.add(r04.get(r11).future_auth_token);
            if (r102.logout_tokens.size() >= 20) goto L104;
            r11 = r11 + 1;
        L104:
            AuthTokensHelper.saveLogOutTokens(r04);
        L106:
            if (r102.logout_tokens == null) goto L108;
            r102.flags |= 64;
        L108:
            SharedPreferences r05 = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0);
            r05.edit().remove("sms_hash_code").apply();
            if (r102.allow_app_hash == false) goto L111;
            r05.edit().putString("sms_hash", BuildVars.getSmsHash()).apply();
        L113:
            if (r102.allow_flashcall == false) goto L126;
            HashSet r06 = LoginActivity.m15822$$Nest$mgetUserPhoneNumbers(this.this$0, false);     // Catch: Exception -> L117
            if (r06.isEmpty() == true) goto L119;
            r102.unknown_number = false;     // Catch: Exception -> L117
            r102.current_number = r06.stream().anyMatch(new LoginActivity$PhoneView$$ExternalSyntheticLambda3(r34));     // Catch: Exception -> L117
        L144:
            goto L126
        L119:
            r102.unknown_number = true;     // Catch: Exception -> L117
            if (UserConfig.getActivatedAccountsCount() <= 0) goto L122;
            r102.allow_flashcall = false;     // Catch: Exception -> L117
            goto L144
        L122:
            r102.current_number = false;     // Catch: Exception -> L117
        L117:
            e = move-exception;
            r102.unknown_number = true;
            FileLog.e(e);
        L126:
            if (LoginActivity.m15776$$Nest$fgetactivityMode(this.this$0) != 2) goto L129;
            TL_account.sendChangePhoneCode r07 = new TL_account.sendChangePhoneCode();
            r07.phone_number = r34;
            r07.settings = r102;
            TLRPC.TL_auth_sendCode r08 = r07;
        L128:
            TLRPC.TL_auth_sendCode r5 = r08;
            Bundle r9 = new Bundle();
            r9.putString("phone", "+" + this.codeField.getText() + " " + this.phoneField.getText());
            r9.putString("ephone", "+" + PhoneFormat.stripExceptNumbers(this.codeField.getText().toString()) + " " + PhoneFormat.stripExceptNumbers(this.phoneField.getText().toString()));     // Catch: Exception -> L133
        L135:
            r9.putString("phoneFormated", r34);
            CountrySelectActivity.Country r09 = this.currentCountry;
            if (r09 == null) goto L138;
            r9.putString("country", r09.code);
        L138:
            this.nextPressed = true;
            PhoneInputData r4 = new PhoneInputData(null);
            PhoneInputData.m15976$$Nest$fputphoneNumber(r4, "+" + this.codeField.getText() + " " + this.phoneField.getText());
            PhoneInputData.m15974$$Nest$fputcountry(r4, this.currentCountry);
            PhoneInputData.m15975$$Nest$fputpatterns(r4, this.phoneFormatMap.get(this.codeField.getText().toString()));
            LoginActivity.m15829$$Nest$mneedShowProgress(this.this$0, ConnectionsManager.getInstance(LoginActivity.access$100(this.this$0)).sendRequest(r5, new LoginActivity$PhoneView$$ExternalSyntheticLambda4(this, r9, r34, r4, r5), 27));
            return;
        L133:
            e = move-exception;
            FileLog.e(e);
            r9.putString("ephone", "+" + r34);
            goto L135
        L129:
            ConnectionsManager.getInstance(LoginActivity.access$000(this.this$0)).cleanup(false);
            TLRPC.TL_auth_sendCode r010 = new TLRPC.TL_auth_sendCode();
            r010.api_hash = BuildVars.getExteraAppHash();
            r010.api_id = BuildVars.getExteraAppId();
            r010.phone_number = r34;
            r010.settings = r102;
            r08 = r010;
            goto L128
        L111:
            r05.edit().remove("sms_hash").apply();
            goto L113
        L72:
            if (TextUtils.isEmpty(BuildVars.SAFETYNET_KEY) == false) goto L74;
        L45:
            if (LoginActivity.m15806$$Nest$fgettestBackend(this.this$0) == false) goto L47;
        L51:
            boolean r103 = this.this$0.getConnectionsManager().isTestBackend();
            if (r103 == LoginActivity.m15806$$Nest$fgettestBackend(this.this$0)) goto L55;
            this.this$0.getConnectionsManager().switchBackend(false);
            r103 = LoginActivity.m15806$$Nest$fgettestBackend(this.this$0);
        L55:
            if ((this.this$0.getParentActivity() instanceof LaunchActivity) == false) goto L69;
            int r12 = 0;
        L58:
            if (r12 >= 16) goto L69;
            UserConfig r133 = UserConfig.getInstance(r12);
            if (r133.isClientActivated() == false) goto L68;
            if (PhoneNumberUtils.compare(r34, r133.getCurrentUser().phone) == false) goto L68;
            if (ConnectionsManager.getInstance(r12).isTestBackend() != r103) goto L68;
            AlertDialog.Builder r011 = new AlertDialog.Builder(this.this$0.getParentActivity());
            r011.setTitle(LocaleController.getString(org.telegram.messenger.R.string.AppName));
            r011.setMessage(LocaleController.getString("AccountAlreadyLoggedIn", org.telegram.messenger.R.string.AccountAlreadyLoggedIn));
            r011.setPositiveButton(LocaleController.getString("AccountSwitch", org.telegram.messenger.R.string.AccountSwitch), new LoginActivity$PhoneView$$ExternalSyntheticLambda2(this, r12));
            r011.setNegativeButton(LocaleController.getString("OK", org.telegram.messenger.R.string.OK), null);
            this.this$0.showDialog(r011.create());
            LoginActivity.m15826$$Nest$mneedHideProgress(this.this$0, false);
            return;
        L68:
            r12 = r12 + 1;
            goto L58
        L47:
            if ("999".equals(this.codeField.getText().toString()) == false) goto L51;
            LoginActivity.m15819$$Nest$fputtestBackend(this.this$0, true);
            CheckBoxCell r104 = this.testBackendCheckBox;
            if (r104 == null) goto L51;
            r104.setChecked(true, true);
            goto L51
        L39:
            if (LoginActivity.m15806$$Nest$fgettestBackend(this.this$0) == true) goto L42;
            LoginActivity.m15828$$Nest$mneedShowAlert(this.this$0, LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString("WrongCountry", org.telegram.messenger.R.string.WrongCountry));
            LoginActivity.m15826$$Nest$mneedHideProgress(this.this$0, false);
            return;
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> r1) {
        }

        @Override // org.telegram.ui.Components.SlideView
        public void onShow() {
            super.onShow();
            CheckBoxCell r0 = this.syncContactsBox;
            if (r0 == null) goto L5;
            r0.setChecked(LoginActivity.m15805$$Nest$fgetsyncContacts(this.this$0), false);
        L5:
            AndroidUtilities.runOnUIThread(new LoginActivity$PhoneView$$ExternalSyntheticLambda0(this), LoginActivity.m15838$$Nest$sfgetSHOW_DELAY());
        }

        public void requestPhoneNumberHint() {
            if (this.requestingPhoneNumberHint == false) goto L5;
            return;
        L5:
            if (LoginActivity.m15776$$Nest$fgetactivityMode(this.this$0) == 0) goto L7;
            return;
        L7:
            if (this.this$0.getParentActivity() == null) goto L22;
        L14:
            e = move-exception;
            this.requestingPhoneNumberHint = false;
            FileLog.e(e);
            return;
        L10:
            if (PushListenerController.GooglePushListenerServiceProvider.INSTANCE.hasServices() == false) goto L21;
            this.requestingPhoneNumberHint = true;     // Catch: Exception -> L14
            GetPhoneNumberHintIntentRequest r0 = GetPhoneNumberHintIntentRequest.builder().build();     // Catch: Exception -> L14
            Identity.getSignInClient(this.this$0.getParentActivity()).getPhoneNumberHintIntent(r0).addOnSuccessListener(new LoginActivity$PhoneView$$ExternalSyntheticLambda16(this)).addOnFailureListener(new LoginActivity$PhoneView$$ExternalSyntheticLambda17(this));     // Catch: Exception -> L14
            return;
        L21:
            return;
        }

        @Override // org.telegram.ui.Components.SlideView
        public void restoreStateParams(Bundle r3) {
            String r0 = r3.getString("phoneview_code");
            if (r0 == null) goto L5;
            this.codeField.setText(r0);
        L5:
            String r32 = r3.getString("phoneview_phone");
            if (r32 == null) goto L9;
            this.phoneField.setText(r32);
            return;
        }

        @Override // org.telegram.ui.Components.SlideView
        public void saveStateParams(Bundle r3) {
            String r0 = this.codeField.getText().toString();
            if (r0.length() == 0) goto L5;
            r3.putString("phoneview_code", r0);
        L5:
            String r2 = this.phoneField.getText().toString();
            if (r2.length() == 0) goto L9;
            r3.putString("phoneview_phone", r2);
            return;
        }

        public void selectCountry(CountrySelectActivity.Country r3) {
            this.ignoreOnTextChange = true;
            String r0 = r3.code;
            this.codeField.setText(r0);
            setCountryHint(r0, r3);
            this.currentCountry = r3;
            this.countryState = 0;
            this.ignoreOnTextChange = false;
            MessagesController.getGlobalMainSettings().edit().putString("phone_code_last_matched_" + r3.code, r3.shortname).apply();
        }

        @Override // org.telegram.ui.Components.SlideView
        public void updateColors() {
            this.titleView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
            this.subtitleView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText6));
            this.subtitleView.setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkIn));
            int r0 = 0;
        L4:
            if (r0 >= this.countryButton.getChildCount()) goto L6;
            TextView r1 = (TextView) this.countryButton.getChildAt(r0);
            r1.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
            r1.setHintTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteHintText));
            r0 = r0 + 1;
            goto L4
        L6:
            ImageView r02 = this.chevronRight;
            int r12 = Theme.key_windowBackgroundWhiteHintText;
            r02.setColorFilter(Theme.getColor(r12));
            ImageView r03 = this.chevronRight;
            LoginActivity r2 = this.this$0;
            int r3 = Theme.key_listSelector;
            r03.setBackground(Theme.createSelectorDrawable(r2.getThemedColor(r3), 1));
            TextView r04 = this.plusTextView;
            int r22 = Theme.key_windowBackgroundWhiteBlackText;
            r04.setTextColor(Theme.getColor(r22));
            this.codeField.setTextColor(Theme.getColor(r22));
            AnimatedPhoneNumberEditText r05 = this.codeField;
            int r5 = Theme.key_windowBackgroundWhiteInputFieldActivated;
            r05.setCursorColor(Theme.getColor(r5));
            this.codeDividerView.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhiteInputField));
            this.phoneField.setTextColor(Theme.getColor(r22));
            this.phoneField.setHintTextColor(Theme.getColor(r12));
            this.phoneField.setCursorColor(Theme.getColor(r5));
            ImageView r06 = this.phoneNumberHintButton;
            if (r06 == null) goto L9;
            r06.setColorFilter(Theme.getColor(r12));
            this.phoneNumberHintButton.setBackground(Theme.createSelectorDrawable(this.this$0.getThemedColor(r3), 1));
        L9:
            CheckBoxCell r07 = this.syncContactsBox;
            if (r07 == null) goto L12;
            r07.setSquareCheckBoxColor(Theme.key_checkboxSquareUnchecked, Theme.key_checkboxSquareBackground, Theme.key_checkboxSquareCheck);
            this.syncContactsBox.updateTextColor();
        L12:
            CheckBoxCell r08 = this.testBackendCheckBox;
            if (r08 == null) goto L15;
            r08.setSquareCheckBoxColor(Theme.key_checkboxSquareUnchecked, Theme.key_checkboxSquareBackground, Theme.key_checkboxSquareCheck);
            this.testBackendCheckBox.updateTextColor();
        L15:
            this.phoneOutlineView.updateColor();
            this.countryOutlineView.updateColor();
        }
    }

    public static class ProgressView extends View {
    }

    /* renamed from: $r8$lambda$06M9NW-_zzoEyOzINErXB09IQ58, reason: not valid java name */
    public static /* synthetic */ void m15770$r8$lambda$06M9NW_zzoEyOzINErXB09IQ58(LoginActivity r0, ItemOptions r1, View r2) {
        r0.lambda$showLoginOptionsMenu$24(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$1aXHA9EZTROJ8MihQemxxMAFQiI(LoginActivity r0, String r1, String r2, String r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$tryResetAccount$21(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$1m7w7qiTA74j9l1KfoKCAMLwx_o(LoginActivity r0, int r1, int r2, ViewGroup.MarginLayoutParams r3, int r4, int r5, int r6, TransformableLoginButtonView r7, float r8, int r9, float r10, int r11, ValueAnimator r12) {
        r0.lambda$onCustomTransitionAnimation$19(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12);
    }

    public static /* synthetic */ void $r8$lambda$3GtgAXHOxDbgnlt91HwNpQQD9ps(LoginActivity r0, TLRPC.TL_error r1, String r2, String r3, String r4) {
        r0.lambda$tryResetAccount$20(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$4FtMg6Kkc8YkYVYZJKIAaKffwmU(LoginActivity r0, AlertDialog r1, int r2) {
        r0.lambda$onDoneButtonPressed$14(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ABLOAs5plMRwkKlcDNefQQENBXs(LoginActivity r0) {
        r0.lambda$showProxyButtonDelayed$25();
    }

    public static /* synthetic */ void $r8$lambda$Ge_v0EoomkfnWgp3Gh_ciRg0gOg(LoginActivity r0, ItemOptions r1, View r2) {
        r0.lambda$showLoginOptionsMenu$23(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$HT5pfptc5Zl6pJfQ_iDZQe2pXAE(LoginActivity r0, ValueAnimator r1) {
        r0.lambda$showDoneButton$12(r1);
    }

    public static /* synthetic */ void $r8$lambda$J3QsjpvLP9Gw5tKtwbqVKeRbayg(LoginActivity r0, View r1) {
        r0.lambda$createView$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$R2yhBlqZCtlt_AN9IAaAV6oV4EQ(boolean r8, String r9, BaseFragment r10, AlertDialog r11, int r12) {
        PackageInfo r0 = ApplicationLoader.applicationContext.getPackageManager().getPackageInfo(ApplicationLoader.applicationContext.getPackageName(), 0);     // Catch: Exception -> L14
        String r02 = String.format(Locale.US, "%s (%d)", new Object[]{r0.versionName, Integer.valueOf(r0.versionCode)});     // Catch: Exception -> L14
        Intent r1 = new Intent("android.intent.action.SENDTO");     // Catch: Exception -> L14
        r1.setData(Uri.parse("mailto:"));     // Catch: Exception -> L14
        String[] r4 = new String[1];     // Catch: Exception -> L14
        if (r8 == false) goto L6;
        String r5 = "recover@telegram.org";
    L7:
        r4[0] = r5;     // Catch: Exception -> L14
        r1.putExtra("android.intent.extra.EMAIL", r4);     // Catch: Exception -> L14
        if (r8 == false) goto L11;
        r1.putExtra("android.intent.extra.SUBJECT", "Banned phone number: " + r9);     // Catch: Exception -> L14
        r1.putExtra("android.intent.extra.TEXT", "I'm trying to use my mobile phone number: " + r9 + "\nBut Telegram says it's banned. Please help.\n\nApp version: " + r02 + "\nOS version: SDK " + Build.VERSION.SDK_INT + "\nDevice Name: " + Build.MANUFACTURER + Build.MODEL + "\nLocale: " + Locale.getDefault());     // Catch: Exception -> L14
    L12:
        r10.getParentActivity().startActivity(Intent.createChooser(r1, "Send email..."));     // Catch: Exception -> L14
        return;
    L11:
        r1.putExtra("android.intent.extra.SUBJECT", "Invalid phone number: " + r9);     // Catch: Exception -> L14
        r1.putExtra("android.intent.extra.TEXT", "I'm trying to use my mobile phone number: " + r9 + "\nBut Telegram says it's invalid. Please help.\n\nApp version: " + r02 + "\nOS version: SDK " + Build.VERSION.SDK_INT + "\nDevice Name: " + Build.MANUFACTURER + Build.MODEL + "\nLocale: " + Locale.getDefault());     // Catch: Exception -> L14
        goto L12
    L6:
        r5 = "login@stel.com";
    L14:
        AlertDialog.Builder r82 = new AlertDialog.Builder(r10.getParentActivity());
        r82.setTitle(LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle));
        r82.setMessage(LocaleController.getString("NoMailInstalled", org.telegram.messenger.R.string.NoMailInstalled));
        r82.setPositiveButton(LocaleController.getString("OK", org.telegram.messenger.R.string.OK), null);
        r10.showDialog(r82.create());
    }

    /* renamed from: $r8$lambda$Suiwul-0E58aAo6T2Aj7EmdYmSM, reason: not valid java name */
    public static /* synthetic */ void m15771$r8$lambda$Suiwul0E58aAo6T2Aj7EmdYmSM(LoginActivity r0, View r1) {
        r0.lambda$createView$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$TCtIRHRyGwuYn5NrCkvFe3Fhego(LoginActivity r0, ValueAnimator r1) {
        r0.lambda$setCustomKeyboardVisible$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$UFJu8Eao4GnWTL9o8W65eWFlPXw(LoginActivity r0, View r1) {
        r0.lambda$createView$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$WJUDWuxWfXQG6HgyfTJ6UzXlkeE(LoginActivity r0, boolean r1) {
        r0.lambda$showProxyButton$26(r1);
    }

    public static /* synthetic */ void $r8$lambda$WnWaI2Cppv3rJhp126C7IHFRo6o(LoginActivity r0, int r1, boolean r2) {
        r0.lambda$createView$0(r1, r2);
    }

    /* renamed from: $r8$lambda$WqLDQ107gzHe6-dAGI4-FMh7DvQ, reason: not valid java name */
    public static /* synthetic */ void m15772$r8$lambda$WqLDQ107gzHe6dAGI4FMh7DvQ(LoginActivity r0) {
        r0.clearLoginOptionsMenuReferences();
    }

    /* renamed from: $r8$lambda$X-YGZoXDd1791xMOakjPJh4uZrA, reason: not valid java name */
    public static /* synthetic */ void m15773$r8$lambda$XYGZoXDd1791xMOakjPJh4uZrA(EditText r0, TextWatcher r1) {
        r0.removeTextChangedListener(r1);
    }

    public static /* synthetic */ void $r8$lambda$XEDXx5IV4Tzlq6ZrcXy8Je9fnFA(LoginActivity r0, boolean r1, boolean r2, boolean r3) {
        r0.lambda$showEditDoneProgress$15(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$XNhZ82P3vvrdRDmpnk9o1vCjndQ(LoginActivity r0, DynamicAnimation r1, float r2, float r3) {
        r0.lambda$createView$2(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$ZtyBaYUo1MoKNmE55H12v8ZKNis(LoginActivity r0, String r1, String r2, String r3, AlertDialog r4, int r5) {
        r0.lambda$tryResetAccount$22(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$cQkMCWgAqzo6ZDsWrEi6MDVV9mY(OutlineTextContainerView r1, View r2, EditText r3, TextWatcher r4) {
        r1.animateError(0.0f);
        r2.setTag(org.telegram.messenger.R.id.timeout_callback, null);
        if (r3 == null) goto L6;
        r3.post(new LoginActivity$$ExternalSyntheticLambda24(r3, r4));
        return;
    }

    public static /* synthetic */ void $r8$lambda$e5AqjMHidq0bRU3igVtVGEBUh3k(LoginActivity r0, View r1) {
        r0.lambda$createView$4(r1);
    }

    public static /* synthetic */ MainTabsActivity $r8$lambda$fQjuG1p1jelJBTD3_0GqFA5uieM(boolean r1, Void r2) {
        Bundle r22 = new Bundle();
        r22.putBoolean("afterSignup", r1);
        MainTabsActivity r12 = new MainTabsActivity();
        r12.prepareDialogsActivity(r22);
        return r12;
    }

    public static /* synthetic */ void $r8$lambda$g0Gd9s9b4bh4gCD3LXfbjnj5Q64(LoginActivityRegisterView r0) {
        LoginActivityRegisterView.m15915$$Nest$fgetimageUpdater(r0).openGallery();
    }

    public static /* synthetic */ void $r8$lambda$rt6BpR_TgAECIIAr6dc40Kicoaw(LoginActivity r0, ValueAnimator r1) {
        r0.lambda$setCustomKeyboardVisible$7(r1);
    }

    /* renamed from: $r8$lambda$t34XbskbvkTYImJWe6S5Q5-2abc, reason: not valid java name */
    public static /* synthetic */ void m15774$r8$lambda$t34XbskbvkTYImJWe6S5Q52abc(LoginActivity r0) {
        r0.updateColors();
    }

    public static /* synthetic */ void $r8$lambda$tNczW9JiyyPm9Vjf6r8apOUypro(LoginActivity r0, ValueAnimator r1) {
        r0.lambda$showDoneButton$13(r1);
    }

    public static /* synthetic */ void $r8$lambda$w4D7mItpd_K_ewz_CxL2nusxF_s(LoginActivity r0, int r1, boolean r2, boolean r3) {
        r0.lambda$showEditDoneProgress$16(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$z5wA2snDCPFWRafo-9kjOV4MPks, reason: not valid java name */
    public static /* synthetic */ void m15775$r8$lambda$z5wA2snDCPFWRafo9kjOV4MPks(LoginActivity r0, ValueAnimator r1) {
        r0.lambda$showEditDoneProgress$17(r1);
    }

    /* renamed from: -$$Nest$fgetactivityMode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15776$$Nest$fgetactivityMode(LoginActivity r0) {
        return r0.activityMode;
    }

    /* renamed from: -$$Nest$fgetanimationFinishCallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m15777$$Nest$fgetanimationFinishCallback(LoginActivity r0) {
        return r0.animationFinishCallback;
    }

    /* renamed from: -$$Nest$fgetbackButtonView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageView m15778$$Nest$fgetbackButtonView(LoginActivity r0) {
        return r0.backButtonView;
    }

    /* renamed from: -$$Nest$fgetcancelDeletionPhone, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m15779$$Nest$fgetcancelDeletionPhone(LoginActivity r0) {
        return r0.cancelDeletionPhone;
    }

    /* renamed from: -$$Nest$fgetcurrentDoneType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15780$$Nest$fgetcurrentDoneType(LoginActivity r0) {
        return r0.currentDoneType;
    }

    /* renamed from: -$$Nest$fgetcurrentTermsOfService, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.TL_help_termsOfService m15781$$Nest$fgetcurrentTermsOfService(LoginActivity r0) {
        return r0.currentTermsOfService;
    }

    /* renamed from: -$$Nest$fgetcurrentViewNum, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15782$$Nest$fgetcurrentViewNum(LoginActivity r0) {
        return r0.currentViewNum;
    }

    /* renamed from: -$$Nest$fgetcustomKeyboardWasVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15783$$Nest$fgetcustomKeyboardWasVisible(LoginActivity r0) {
        return r0.customKeyboardWasVisible;
    }

    /* renamed from: -$$Nest$fgetdoneItemAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m15784$$Nest$fgetdoneItemAnimation(LoginActivity r0) {
        return r0.doneItemAnimation;
    }

    /* renamed from: -$$Nest$fgetemailChangeFinishCallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m15785$$Nest$fgetemailChangeFinishCallback(LoginActivity r0) {
        return r0.emailChangeFinishCallback;
    }

    /* renamed from: -$$Nest$fgetemailChangeIsSuggestion, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15786$$Nest$fgetemailChangeIsSuggestion(LoginActivity r0) {
        return r0.emailChangeIsSuggestion;
    }

    /* renamed from: -$$Nest$fgetemailChangeSkipButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m15787$$Nest$fgetemailChangeSkipButton(LoginActivity r0) {
        return r0.emailChangeSkipButton;
    }

    /* renamed from: -$$Nest$fgetfloatingButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FragmentFloatingButton m15788$$Nest$fgetfloatingButton(LoginActivity r0) {
        return r0.floatingButton;
    }

    /* renamed from: -$$Nest$fgetfloatingButtonIcon, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TransformableLoginButtonView m15789$$Nest$fgetfloatingButtonIcon(LoginActivity r0) {
        return r0.floatingButtonIcon;
    }

    /* renamed from: -$$Nest$fgetforceDisableSafetyNet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15790$$Nest$fgetforceDisableSafetyNet(LoginActivity r0) {
        return r0.forceDisableSafetyNet;
    }

    /* renamed from: -$$Nest$fgetisRequestingFirebaseSms, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15791$$Nest$fgetisRequestingFirebaseSms(LoginActivity r0) {
        return r0.isRequestingFirebaseSms;
    }

    /* renamed from: -$$Nest$fgetkeyboardAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m15792$$Nest$fgetkeyboardAnimator(LoginActivity r0) {
        return r0.keyboardAnimator;
    }

    /* renamed from: -$$Nest$fgetkeyboardLinearLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m15793$$Nest$fgetkeyboardLinearLayout(LoginActivity r0) {
        return r0.keyboardLinearLayout;
    }

    /* renamed from: -$$Nest$fgetkeyboardView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CustomPhoneKeyboardView m15794$$Nest$fgetkeyboardView(LoginActivity r0) {
        return r0.keyboardView;
    }

    /* renamed from: -$$Nest$fgetnewAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15795$$Nest$fgetnewAccount(LoginActivity r0) {
        return r0.newAccount;
    }

    /* renamed from: -$$Nest$fgetpaid, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15796$$Nest$fgetpaid(LoginActivity r0) {
        return r0.paid;
    }

    /* renamed from: -$$Nest$fgetphoneNumberConfirmView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PhoneNumberConfirmView m15797$$Nest$fgetphoneNumberConfirmView(LoginActivity r0) {
        return r0.phoneNumberConfirmView;
    }

    /* renamed from: -$$Nest$fgetproxyButtonView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageView m15798$$Nest$fgetproxyButtonView(LoginActivity r0) {
        return r0.proxyButtonView;
    }

    /* renamed from: -$$Nest$fgetradialProgressView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RadialProgressView m15799$$Nest$fgetradialProgressView(LoginActivity r0) {
        return r0.radialProgressView;
    }

    /* renamed from: -$$Nest$fgetrestoringState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15800$$Nest$fgetrestoringState(LoginActivity r0) {
        return r0.restoringState;
    }

    /* renamed from: -$$Nest$fgetshowDoneAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet[] m15801$$Nest$fgetshowDoneAnimation(LoginActivity r0) {
        return r0.showDoneAnimation;
    }

    /* renamed from: -$$Nest$fgetsizeNotifierFrameLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SizeNotifierFrameLayout m15802$$Nest$fgetsizeNotifierFrameLayout(LoginActivity r0) {
        return r0.sizeNotifierFrameLayout;
    }

    /* renamed from: -$$Nest$fgetslideViewsContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m15803$$Nest$fgetslideViewsContainer(LoginActivity r0) {
        return r0.slideViewsContainer;
    }

    /* renamed from: -$$Nest$fgetstartMessagingButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m15804$$Nest$fgetstartMessagingButton(LoginActivity r0) {
        return r0.startMessagingButton;
    }

    /* renamed from: -$$Nest$fgetsyncContacts, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15805$$Nest$fgetsyncContacts(LoginActivity r0) {
        return r0.syncContacts;
    }

    /* renamed from: -$$Nest$fgettestBackend, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15806$$Nest$fgettestBackend(LoginActivity r0) {
        return r0.testBackend;
    }

    /* renamed from: -$$Nest$fgetviews, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SlideView[] m15807$$Nest$fgetviews(LoginActivity r0) {
        return r0.views;
    }

    /* renamed from: -$$Nest$fputanimationFinishCallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15808$$Nest$fputanimationFinishCallback(LoginActivity r0, Runnable r1) {
        r0.animationFinishCallback = r1;
    }

    /* renamed from: -$$Nest$fputcurrentDoneType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15809$$Nest$fputcurrentDoneType(LoginActivity r0, int r1) {
        r0.currentDoneType = r1;
    }

    /* renamed from: -$$Nest$fputcurrentTermsOfService, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15810$$Nest$fputcurrentTermsOfService(LoginActivity r0, TLRPC.TL_help_termsOfService r1) {
        r0.currentTermsOfService = r1;
    }

    /* renamed from: -$$Nest$fputdoneItemAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15811$$Nest$fputdoneItemAnimation(LoginActivity r0, AnimatorSet r1) {
        r0.doneItemAnimation = r1;
    }

    /* renamed from: -$$Nest$fputisAnimatingIntro, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15812$$Nest$fputisAnimatingIntro(LoginActivity r0, boolean r1) {
        r0.isAnimatingIntro = r1;
    }

    /* renamed from: -$$Nest$fputisRequestingFirebaseSms, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15813$$Nest$fputisRequestingFirebaseSms(LoginActivity r0, boolean r1) {
        r0.isRequestingFirebaseSms = r1;
    }

    /* renamed from: -$$Nest$fputkeyboardAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15814$$Nest$fputkeyboardAnimator(LoginActivity r0, ValueAnimator r1) {
        r0.keyboardAnimator = r1;
    }

    /* renamed from: -$$Nest$fputkeyboardHideCallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15815$$Nest$fputkeyboardHideCallback(LoginActivity r0, Runnable r1) {
        r0.keyboardHideCallback = r1;
    }

    /* renamed from: -$$Nest$fputpaid, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15816$$Nest$fputpaid(LoginActivity r0, boolean r1) {
        r0.paid = r1;
    }

    /* renamed from: -$$Nest$fputphoneNumberConfirmView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15817$$Nest$fputphoneNumberConfirmView(LoginActivity r0, PhoneNumberConfirmView r1) {
        r0.phoneNumberConfirmView = r1;
    }

    /* renamed from: -$$Nest$fputsyncContacts, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15818$$Nest$fputsyncContacts(LoginActivity r0, boolean r1) {
        r0.syncContacts = r1;
    }

    /* renamed from: -$$Nest$fputtestBackend, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15819$$Nest$fputtestBackend(LoginActivity r0, boolean r1) {
        r0.testBackend = r1;
    }

    /* renamed from: -$$Nest$mfillNextCodeParams, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15820$$Nest$mfillNextCodeParams(LoginActivity r0, Bundle r1, TLRPC.auth_SentCode r2) {
        r0.fillNextCodeParams(r1, r2);
    }

    /* renamed from: -$$Nest$mfillNextCodeParams, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15821$$Nest$mfillNextCodeParams(LoginActivity r0, Bundle r1, TL_account.sentEmailCode r2) {
        r0.fillNextCodeParams(r1, r2);
    }

    /* renamed from: -$$Nest$mgetUserPhoneNumbers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashSet m15822$$Nest$mgetUserPhoneNumbers(LoginActivity r0, boolean r1) {
        return r0.getUserPhoneNumbers(r1);
    }

    /* renamed from: -$$Nest$misCustomKeyboardForceDisabled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15823$$Nest$misCustomKeyboardForceDisabled(LoginActivity r0) {
        return r0.isCustomKeyboardForceDisabled();
    }

    /* renamed from: -$$Nest$misCustomKeyboardVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15824$$Nest$misCustomKeyboardVisible(LoginActivity r0) {
        return r0.isCustomKeyboardVisible();
    }

    /* renamed from: -$$Nest$misInCancelAccountDeletionMode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15825$$Nest$misInCancelAccountDeletionMode(LoginActivity r0) {
        return r0.isInCancelAccountDeletionMode();
    }

    /* renamed from: -$$Nest$mneedHideProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15826$$Nest$mneedHideProgress(LoginActivity r0, boolean r1) {
        r0.needHideProgress(r1);
    }

    /* renamed from: -$$Nest$mneedHideProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15827$$Nest$mneedHideProgress(LoginActivity r0, boolean r1, boolean r2) {
        r0.needHideProgress(r1, r2);
    }

    /* renamed from: -$$Nest$mneedShowAlert, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15828$$Nest$mneedShowAlert(LoginActivity r0, String r1, String r2) {
        r0.needShowAlert(r1, r2);
    }

    /* renamed from: -$$Nest$mneedShowProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15829$$Nest$mneedShowProgress(LoginActivity r0, int r1) {
        r0.needShowProgress(r1);
    }

    /* renamed from: -$$Nest$mneedShowProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15830$$Nest$mneedShowProgress(LoginActivity r0, int r1, boolean r2) {
        r0.needShowProgress(r1, r2);
    }

    /* renamed from: -$$Nest$monAuthSuccess, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15831$$Nest$monAuthSuccess(LoginActivity r0, TLRPC.TL_auth_authorization r1) {
        r0.onAuthSuccess(r1);
    }

    /* renamed from: -$$Nest$monAuthSuccess, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15832$$Nest$monAuthSuccess(LoginActivity r0, TLRPC.TL_auth_authorization r1, boolean r2) {
        r0.onAuthSuccess(r1, r2);
    }

    /* renamed from: -$$Nest$monDoneButtonPressed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15833$$Nest$monDoneButtonPressed(LoginActivity r0) {
        r0.onDoneButtonPressed();
    }

    /* renamed from: -$$Nest$monFieldError, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15834$$Nest$monFieldError(LoginActivity r0, View r1, boolean r2) {
        r0.onFieldError(r1, r2);
    }

    /* renamed from: -$$Nest$mshowDoneButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15835$$Nest$mshowDoneButton(LoginActivity r0, boolean r1, boolean r2) {
        r0.showDoneButton(r1, r2);
    }

    /* renamed from: -$$Nest$mshowKeyboard, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15836$$Nest$mshowKeyboard(LoginActivity r0, View r1) {
        return r0.showKeyboard(r1);
    }

    /* renamed from: -$$Nest$mtryResetAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15837$$Nest$mtryResetAccount(LoginActivity r0, String r1, String r2, String r3) {
        r0.tryResetAccount(r1, r2, r3);
    }

    /* renamed from: -$$Nest$sfgetSHOW_DELAY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15838$$Nest$sfgetSHOW_DELAY() {
        return SHOW_DELAY;
    }

    static {
        if (SharedConfig.getDevicePerformanceClass() > 1) goto L5;
        int r0 = 150;
    L6:
        SHOW_DELAY = r0;
        return;
    L5:
        r0 = 100;
        goto L6
    }

    public LoginActivity() {
        this.views = new SlideView[20];
        this.permissionsItems = new ArrayList();
        this.checkPermissions = true;
        this.syncContacts = false;
        this.testBackend = false;
        this.activityMode = 0;
        this.showDoneAnimation = new AnimatorSet[2];
        this.doneButtonVisible = new boolean[]{true, false};
        this.customKeyboardWasVisible = false;
        this.doneProgressVisible = new boolean[2];
        this.editDoneCallback = new Runnable[2];
        this.postedEditDoneCallback = new boolean[2];
    }

    public LoginActivity(int r5) {
        this.views = new SlideView[20];
        this.permissionsItems = new ArrayList();
        this.checkPermissions = true;
        this.syncContacts = false;
        this.testBackend = false;
        this.activityMode = 0;
        this.showDoneAnimation = new AnimatorSet[2];
        this.doneButtonVisible = new boolean[]{true, false};
        this.customKeyboardWasVisible = false;
        this.doneProgressVisible = new boolean[2];
        this.editDoneCallback = new Runnable[2];
        this.postedEditDoneCallback = new boolean[2];
        this.currentAccount = r5;
        this.newAccount = true;
    }

    public static /* synthetic */ int access$000(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$100(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1000(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1100(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1200(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1300(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1400(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1500(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1600(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1700(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1800(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1900(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$200(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2000(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2100(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2200(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2300(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2400(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2500(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2600(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2700(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2800(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2900(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$300(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3000(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3100(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3200(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3300(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3400(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3500(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3600(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3700(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3800(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3900(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$400(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$4000(LoginActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ int access$4100(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$4200(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$4300(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$4400(LoginActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$4500(LoginActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$4600(LoginActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$4700(LoginActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ int access$4800(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$4900(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$500(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$5000(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$5100(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$5200(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$5300(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$600(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$700(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$800(LoginActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$900(LoginActivity r0) {
        return r0.currentAccount;
    }

    private void clearCurrentState() {
        Context r0 = ApplicationLoader.applicationContext;
        if (this.newAccount == false) goto L5;
        String r3 = "_" + this.currentAccount;
    L6:
        SharedPreferences.Editor r32 = r0.getSharedPreferences("logininfo2".concat(r3), 0).edit();
        r32.clear();
        r32.apply();
        return;
    L5:
        r3 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L6
    }

    private void clearLoginOptionsMenuReferences() {
        this.loginOptionsMenu = null;
        this.loginOptionsProxyItem = null;
        this.loginOptionsProxyDrawable = null;
    }

    private void fillNextCodeParams(Bundle r2, TLRPC.auth_SentCode r3) {
        fillNextCodeParams(r2, r3, true);
    }

    private void fillNextCodeParams(Bundle r11, TLRPC.auth_SentCode r12, boolean r13) {
        if ((r12 instanceof TLRPC.TL_auth_sentCodePaymentRequired) == false) goto L6;
        TLRPC.TL_auth_sentCodePaymentRequired r122 = (TLRPC.TL_auth_sentCodePaymentRequired) r12;
        r11.putString("product", r122.store_product);
        r11.putString("phoneHash", r122.phone_code_hash);
        r11.putString("support_email_address", r122.support_email_address);
        r11.putString("support_email_subject", r122.support_email_subject);
        r11.putString("currency", r122.currency);
        r11.putInt("premium_days", r122.premium_days);
        r11.putLong("amount", r122.amount);
        setPage(19, true, r11, true);
        return;
    L6:
        r11.putString("phoneHash", r12.phone_code_hash);
        TLRPC.auth_CodeType r0 = r12.next_type;
        if ((r0 instanceof TLRPC.TL_auth_codeTypeCall) == false) goto L10;
        r11.putInt("nextType", 4);
    L22:
        if ((r12.type instanceof TLRPC.TL_auth_sentCodeTypeApp) == false) goto L26;
        r11.putInt("type", 1);
        r11.putInt("length", r12.type.length);
        setPage(1, r13, r11, false);
        return;
    L26:
        if (r12.timeout == 0) goto L28;
    L32:
        r11.putInt("timeout", r12.timeout * 1000);
        TLRPC.auth_SentCodeType r02 = r12.type;
        if ((r02 instanceof TLRPC.TL_auth_sentCodeTypeCall) == false) goto L37;
        r11.putInt("type", 4);
        r11.putInt("length", r12.type.length);
        setPage(4, r13, r11, false);
        return;
    L37:
        if ((r02 instanceof TLRPC.TL_auth_sentCodeTypeFlashCall) == false) goto L41;
        r11.putInt("type", 3);
        r11.putString("pattern", r12.type.pattern);
        setPage(3, r13, r11, false);
        return;
    L41:
        if ((r02 instanceof TLRPC.TL_auth_sentCodeTypeSms) == false) goto L43;
    L75:
        r11.putInt("type", 2);
        r11.putInt("length", r12.type.length);
        r11.putBoolean("firebase", r12.type instanceof TLRPC.TL_auth_sentCodeTypeFirebaseSms);
        setPage(2, r13, r11, false);
        return;
    L43:
        if ((r02 instanceof TLRPC.TL_auth_sentCodeTypeFirebaseSms) == true) goto L75;
        if ((r02 instanceof TLRPC.TL_auth_sentCodeTypeFragmentSms) == false) goto L50;
        r11.putInt("type", 15);
        r11.putString("url", r12.type.url);
        r11.putInt("length", r12.type.length);
        setPage(15, r13, r11, false);
        return;
    L50:
        if ((r02 instanceof TLRPC.TL_auth_sentCodeTypeMissedCall) == false) goto L54;
        r11.putInt("type", 11);
        r11.putInt("length", r12.type.length);
        r11.putString("prefix", r12.type.prefix);
        setPage(11, r13, r11, false);
        return;
    L54:
        if ((r02 instanceof TLRPC.TL_auth_sentCodeTypeSetUpEmailRequired) == false) goto L58;
        r11.putBoolean("googleSignInAllowed", r02.google_signin_allowed);
        setPage(12, r13, r11, false);
        return;
    L58:
        if ((r02 instanceof TLRPC.TL_auth_sentCodeTypeEmailCode) == false) goto L62;
        r11.putBoolean("googleSignInAllowed", r02.google_signin_allowed);
        r11.putString("emailPattern", r12.type.email_pattern);
        r11.putInt("length", r12.type.length);
        r11.putInt("nextPhoneLoginDate", r12.type.next_phone_login_date);
        r11.putInt("resetAvailablePeriod", r12.type.reset_available_period);
        r11.putInt("resetPendingDate", r12.type.reset_pending_date);
        setPage(14, r13, r11, false);
        return;
    L62:
        if ((r02 instanceof TLRPC.TL_auth_sentCodeTypeSmsWord) == false) goto L69;
        String r123 = r02.beginning;
        if (r123 == null) goto L66;
        r11.putString("beginning", r123);
    L66:
        setPage(16, r13, r11, false);
        return;
    L69:
        if ((r02 instanceof TLRPC.TL_auth_sentCodeTypeSmsPhrase) == false) goto L77;
        String r124 = r02.beginning;
        if (r124 == null) goto L73;
        r11.putString("beginning", r124);
    L73:
        setPage(17, r13, r11, false);
        return;
    L77:
        return;
    L28:
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L30;
        int r03 = 5;
    L31:
        r12.timeout = r03;
        goto L32
    L30:
        r03 = 60;
        goto L31
    L10:
        if ((r0 instanceof TLRPC.TL_auth_codeTypeFlashCall) == false) goto L13;
        r11.putInt("nextType", 3);
        goto L22
    L13:
        if ((r0 instanceof TLRPC.TL_auth_codeTypeSms) == false) goto L16;
        r11.putInt("nextType", 2);
        goto L22
    L16:
        if ((r0 instanceof TLRPC.TL_auth_codeTypeMissedCall) == false) goto L19;
        r11.putInt("nextType", 11);
        goto L22
    L19:
        if ((r0 instanceof TLRPC.TL_auth_codeTypeFragmentSms) == false) goto L22;
        r11.putInt("nextType", 15);
        goto L22
    }

    private void fillNextCodeParams(Bundle r3, TL_account.sentEmailCode r4) {
        r3.putString("emailPattern", r4.email_pattern);
        r3.putInt("length", r4.length);
        setPage(13, true, r3, false);
    }

    private HashSet<String> getUserPhoneNumbers(boolean r5) {
        HashSet<String> r0 = new HashSet();
        String r1 = ((TelephonyManager) ApplicationLoader.applicationContext.getSystemService("phone")).getLine1Number();     // Catch: Exception -> L7
        if (TextUtils.isEmpty(r1) == true) goto L42;
        if (r5 == false) goto L9;
        r1 = PhoneFormat.stripExceptNumbers(r1);     // Catch: Exception -> L7
    L9:
        r0.add(r1);     // Catch: Exception -> L7
    L42:
        SubscriptionManager r4 = SubscriptionManager.from(getContext());     // Catch: Exception -> L15
        int r12 = Build.VERSION.SDK_INT;     // Catch: Exception -> L15
        if (r12 < 30) goto L17;
        List<SubscriptionInfo> r2 = r4.getCompleteActiveSubscriptionInfoList();     // Catch: Exception -> L15
    L18:
        if (r2 == null) goto L22;
        if (r2.isEmpty() == true) goto L22;
    L24:
        if (r2 != null) goto L26;
    L27:
        r2 = r4.getActiveSubscriptionInfoList();     // Catch: Exception -> L15
    L28:
        if (r2 == null) goto L39;
        int r42 = 0;
    L30:
        if (r42 >= r2.size()) goto L39;
        String r13 = r2.get(r42).getNumber();     // Catch: Exception -> L15
        if (TextUtils.isEmpty(r13) == true) goto L37;
        if (r5 == false) goto L36;
        r13 = PhoneFormat.stripExceptNumbers(r13);     // Catch: Exception -> L15
    L36:
        r0.add(r13);     // Catch: Exception -> L15
    L37:
        r42 = r42 + 1;
    L39:
        return r0;
    L26:
        if (r2.isEmpty() == false) goto L28;
    L22:
        if (r12 < 28) goto L24;
        r2 = r4.getAccessibleSubscriptionInfoList();     // Catch: Exception -> L15
        goto L24
    L17:
        r2 = null;
    L15:
        e = move-exception;
        FileLog.e(e);
    L7:
        e = move-exception;
        FileLog.e(e);
        goto L42
    }

    private boolean isCustomKeyboardForceDisabled() {
        return AndroidUtilities.isAccessibilityTouchExplorationEnabled();
    }

    private boolean isCustomKeyboardVisible() {
        if (this.views[this.currentViewNum].hasCustomKeyboard() == true) goto L5;
        return false;
    L5:
        if (isCustomKeyboardForceDisabled() == true) goto L10;
        return true;
    L10:
        return false;
    }

    private boolean isInCancelAccountDeletionMode() {
        if (this.activityMode != 1) goto L5;
        return true;
    L5:
        return false;
    }

    private /* synthetic */ void lambda$createView$0(int r2, boolean r3) {
        if (r2 <= AndroidUtilities.dp(20.0f)) goto L8;
        if (isCustomKeyboardVisible() == false) goto L8;
        AndroidUtilities.hideKeyboard(this.fragmentView);
    L8:
        if (r2 > AndroidUtilities.dp(20.0f)) goto L13;
        Runnable r22 = this.keyboardHideCallback;
        if (r22 == null) goto L14;
        r22.run();
        this.keyboardHideCallback = null;
        return;
    L14:
        return;
    }

    private /* synthetic */ void lambda$createView$1(View r1) {
        onDoneButtonPressed();
    }

    private /* synthetic */ void lambda$createView$2(DynamicAnimation r1, float r2, float r3) {
        PhoneNumberConfirmView r0 = this.phoneNumberConfirmView;
        if (r0 == null) goto L6;
        PhoneNumberConfirmView.m15987$$Nest$mupdateFabPosition(r0);
        return;
    }

    private /* synthetic */ void lambda$createView$3(View r1) {
        if (onBackPressed(true) == false) goto L6;
        finishFragment();
        return;
    }

    private /* synthetic */ void lambda$createView$4(View r1) {
        Runnable r12 = this.emailChangeSkipCallback;
        if (r12 == null) goto L5;
        r12.run();
    L5:
        finishFragment();
    }

    private /* synthetic */ void lambda$createView$5(View r1) {
        showLoginOptionsMenu();
    }

    private /* synthetic */ void lambda$onCustomTransitionAnimation$19(int r3, int r4, ViewGroup.MarginLayoutParams r5, int r6, int r7, int r8, TransformableLoginButtonView r9, float r10, int r11, float r12, int r13, ValueAnimator r14) {
        float r142 = ((Float) r14.getAnimatedValue()).floatValue();
        this.keyboardLinearLayout.setAlpha(r142);
        this.fragmentView.setBackgroundColor(ColorUtils.setAlphaComponent(r3, (int) (r4 * r142)));
        float r32 = 1.0f - r142;
        this.slideViewsContainer.setTranslationY(AndroidUtilities.dp(20.0f) * r32);
        if (isCustomKeyboardForceDisabled() == true) goto L5;
        this.keyboardView.setTranslationY(r4.getLayoutParams().height * r32);
        this.floatingButton.setTranslationY(this.keyboardView.getLayoutParams().height * r32);
    L5:
        this.introView.setTranslationY((-AndroidUtilities.dp(20.0f)) * r142);
        float r33 = (r32 * 0.05f) + 0.95f;
        this.introView.setScaleX(r33);
        this.introView.setScaleY(r33);
        r5.width = (int) (r6 + ((r7 - r6) * r142));
        r5.height = (int) (r8 + ((r7 - r8) * r142));
        r9.requestLayout();
        r9.setProgress(r142);
        r9.setTranslationX(r10 + ((r11 - r10) * r142));
        r9.setTranslationY(r12 + ((r13 - r12) * r142));
    }

    private /* synthetic */ void lambda$onDoneButtonPressed$14(AlertDialog r1, int r2) {
        this.views[this.currentViewNum].onCancelPressed();
        needHideProgress(true);
    }

    private /* synthetic */ void lambda$setCustomKeyboardVisible$6(ValueAnimator r2) {
        float r22 = ((Float) r2.getAnimatedValue()).floatValue();
        this.keyboardView.setAlpha(r22);
        this.keyboardView.setTranslationY((1.0f - r22) * AndroidUtilities.dp(230.0f));
    }

    private /* synthetic */ void lambda$setCustomKeyboardVisible$7(ValueAnimator r2) {
        float r22 = ((Float) r2.getAnimatedValue()).floatValue();
        this.keyboardView.setAlpha(r22);
        this.keyboardView.setTranslationY((1.0f - r22) * AndroidUtilities.dp(230.0f));
    }

    private /* synthetic */ void lambda$showDoneButton$12(ValueAnimator r2) {
        float r22 = ((Float) r2.getAnimatedValue()).floatValue();
        this.floatingAutoAnimator.setOffsetY(r22);
        this.floatingButton.setAlpha(1.0f - (r22 / AndroidUtilities.dpf2(70.0f)));
    }

    private /* synthetic */ void lambda$showDoneButton$13(ValueAnimator r2) {
        float r22 = ((Float) r2.getAnimatedValue()).floatValue();
        this.floatingAutoAnimator.setOffsetY(r22);
        this.floatingButton.setAlpha(1.0f - (r22 / AndroidUtilities.dpf2(70.0f)));
    }

    private /* synthetic */ void lambda$showEditDoneProgress$15(boolean r1, boolean r2, boolean r3) {
        showEditDoneProgress(r1, r2, r3);
    }

    private /* synthetic */ void lambda$showEditDoneProgress$16(int r2, boolean r3, boolean r4) {
        int r0 = this.currentDoneType;
        this.currentDoneType = r2;
        showEditDoneProgress(r3, r4, true);
        this.currentDoneType = r0;
    }

    private /* synthetic */ void lambda$showEditDoneProgress$17(ValueAnimator r3) {
        float r32 = ((Float) r3.getAnimatedValue()).floatValue();
        float r0 = (0.9f * r32) + 0.1f;
        this.radialProgressView.setScaleX(r0);
        this.radialProgressView.setScaleY(r0);
        this.radialProgressView.setAlpha(r32);
    }

    private /* synthetic */ void lambda$showLoginOptionsMenu$23(ItemOptions r1, View r2) {
        r1.dismiss();
        presentFragment(new ProxyListActivity());
    }

    private /* synthetic */ void lambda$showLoginOptionsMenu$24(ItemOptions r3, View r4) {
        this.proxyButtonView.clearAnimation();
        this.proxyButtonView.animate().cancel();
        this.proxyButtonView.setVisibility(8);
        r3.dismiss();
        setPage(18, true, null, false);
    }

    private /* synthetic */ void lambda$showProxyButton$26(boolean r1) {
        if (r1 == true) goto L5;
        this.proxyButtonView.setVisibility(8);
        return;
    }

    private /* synthetic */ void lambda$showProxyButtonDelayed$25() {
        this.proxyButtonVisible = false;
        showProxyButton(true, true);
    }

    private /* synthetic */ void lambda$tryResetAccount$20(TLRPC.TL_error r8, String r9, String r10, String r11) {
        needHideProgress(false);
        if (r8 != null) goto L13;
        if (r9 == null) goto L10;
        if (r10 == null) goto L10;
        if (r11 == null) goto L10;
        Bundle r82 = new Bundle();
        r82.putString("phoneFormated", r9);
        r82.putString("phoneHash", r10);
        r82.putString("code", r11);
        setPage(5, true, r82, false);
        return;
    L10:
        setPage(0, true, null, true);
        return;
    L13:
        if (r8.text.equals("2FA_RECENT_CONFIRM") == false) goto L17;
        needShowAlert(LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), LocaleController.getString("ResetAccountCancelledAlert", org.telegram.messenger.R.string.ResetAccountCancelledAlert));
        return;
    L17:
        if (r8.text.startsWith("2FA_CONFIRM_WAIT_") == false) goto L20;
        Bundle r5 = new Bundle();
        r5.putString("phoneFormated", r9);
        r5.putString("phoneHash", r10);
        r5.putString("code", r11);
        r5.putInt("startTime", ConnectionsManager.getInstance(this.currentAccount).getCurrentTime());
        r5.putInt("waitTime", Utilities.parseInt(r8.text.replace("2FA_CONFIRM_WAIT_", _UrlKt.FRAGMENT_ENCODE_SET)));
        setPage(8, true, r5, false);
        return;
    L20:
        needShowAlert(LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle), r8.text);
    }

    private /* synthetic */ void lambda$tryResetAccount$21(String r1, String r2, String r3, TLObject r4, TLRPC.TL_error r5) {
        AndroidUtilities.runOnUIThread(new LoginActivity$$ExternalSyntheticLambda28(this, r5, r1, r2, r3));
    }

    private /* synthetic */ void lambda$tryResetAccount$22(String r2, String r3, String r4, AlertDialog r5, int r6) {
        needShowProgress(0);
        TL_account.deleteAccount r52 = new TL_account.deleteAccount();
        r52.reason = "Forgot password";
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r52, new LoginActivity$$ExternalSyntheticLambda27(this, r2, r3, r4), 10);
    }

    public static Bundle loadCurrentState(boolean r6, int r7) {
        Bundle r2 = new Bundle();     // Catch: Exception -> L37
        Context r3 = ApplicationLoader.applicationContext;     // Catch: Exception -> L37
        if (r6 == false) goto L6;
        String r62 = "_" + r7;     // Catch: Exception -> L37
    L7:
        Iterator<Map.Entry<String, ?>> r63 = r3.getSharedPreferences("logininfo2".concat(r62), 0).getAll().entrySet().iterator();     // Catch: Exception -> L37
    L8:
        if (r63.hasNext() == false) goto L36;
        Map.Entry<String, ?> r0 = r63.next();     // Catch: Exception -> L37
        String r1 = r0.getKey();     // Catch: Exception -> L37
        Object r02 = r0.getValue();     // Catch: Exception -> L37
        String[] r32 = r1.split("_\\|_");     // Catch: Exception -> L37
        if (r32.length == 1) goto L13;
        if (r32.length != 2) goto L8;
        Bundle r12 = r2.getBundle(r32[0]);     // Catch: Exception -> L37
        if (r12 != null) goto L27;
        r12 = new Bundle();     // Catch: Exception -> L37
        r2.putBundle(r32[0], r12);     // Catch: Exception -> L37
    L27:
        if ((r02 instanceof String) == true) goto L28;
        if ((r02 instanceof Integer) == true) goto L31;
        if ((r02 instanceof Boolean) == false) goto L8;
        r12.putBoolean(r32[1], ((Boolean) r02).booleanValue());     // Catch: Exception -> L37
        goto L8
    L31:
        r12.putInt(r32[1], ((Integer) r02).intValue());     // Catch: Exception -> L37
        goto L8
    L28:
        r12.putString(r32[1], (String) r02);     // Catch: Exception -> L37
        goto L8
    L13:
        if ((r02 instanceof String) == true) goto L14;
        if ((r02 instanceof Integer) == true) goto L17;
        if ((r02 instanceof Boolean) == false) goto L8;
        r2.putBoolean(r1, ((Boolean) r02).booleanValue());     // Catch: Exception -> L37
        goto L8
    L17:
        r2.putInt(r1, ((Integer) r02).intValue());     // Catch: Exception -> L37
        goto L8
    L14:
        r2.putString(r1, (String) r02);     // Catch: Exception -> L37
        goto L8
    L36:
        return r2;
    L6:
        r62 = _UrlKt.FRAGMENT_ENCODE_SET;
    L37:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    private void needFinishActivity(boolean r4, boolean r5, int r6) {
        if (getParentActivity() == null) goto L5;
        AndroidUtilities.setLightStatusBar(getParentActivity(), false);
    L5:
        clearCurrentState();
        if ((getParentActivity() instanceof LaunchActivity) == false) goto L18;
        if (this.newAccount == false) goto L11;
        this.newAccount = false;
        this.pendingSwitchingAccount = true;
        ((LaunchActivity) getParentActivity()).switchToAccount(this.currentAccount, true, new LoginActivity$$ExternalSyntheticLambda25(r4));
        this.pendingSwitchingAccount = false;
        finishFragment();
        return;
    L11:
        if (r4 == false) goto L14;
        if (r5 == false) goto L14;
        TwoStepVerificationSetupActivity r42 = new TwoStepVerificationSetupActivity(6, null);
        r42.setBlockingAlert(r6);
        r42.setFromRegistration(true);
        presentFragment(r42, true);
    L15:
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.mainUserInfoChanged, new Object[0]);
        LocaleController.getInstance().loadRemoteLanguages(this.currentAccount);
        RestrictedLanguagesSelectActivity.checkRestrictedLanguages(true);
        return;
    L14:
        Bundle r52 = new Bundle();
        r52.putBoolean("afterSignup", r4);
        MainTabsActivity r43 = new MainTabsActivity();
        r43.prepareDialogsActivity(r52);
        presentFragment(r43, true);
        goto L15
    L18:
        if ((getParentActivity() instanceof ExternalActionActivity) == false) goto L21;
        ((ExternalActionActivity) getParentActivity()).onFinishLogin();
        return;
    }

    private void needHideProgress(boolean r2) {
        needHideProgress(r2, true);
    }

    private void needHideProgress(boolean r4, boolean r5) {
        if (this.progressRequestId == 0) goto L8;
        if (r4 == false) goto L6;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.progressRequestId, true);
    L6:
        this.progressRequestId = 0;
    L8:
        if (isInCancelAccountDeletionMode() == false) goto L12;
        AlertDialog r42 = this.cancelDeleteProgressDialog;
        if (r42 == null) goto L12;
        r42.dismiss();
        this.cancelDeleteProgressDialog = null;
    L12:
        showEditDoneProgress(false, r5);
    }

    private void needShowAlert(String r3, String r4) {
        if (r4 != null) goto L4;
        return;
    L4:
        if (getParentActivity() == null) goto L9;
        AlertDialog.Builder r0 = new AlertDialog.Builder(getParentActivity());
        r0.setTitle(r3);
        r0.setMessage(r4);
        r0.setPositiveButton(LocaleController.getString("OK", org.telegram.messenger.R.string.OK), null);
        showDialog(r0.create());
        return;
    }

    public static void needShowInvalidAlert(BaseFragment r6, String r7, PhoneInputData r8, boolean r9) {
        if (r6 != null) goto L4;
        return;
    L4:
        if (r6.getParentActivity() == null) goto L35;
        AlertDialog.Builder r0 = new AlertDialog.Builder(r6.getParentActivity());
        if (r9 == false) goto L9;
        r0.setTitle(LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle));
        r0.setMessage(LocaleController.getString("BannedPhoneNumber", org.telegram.messenger.R.string.BannedPhoneNumber));
    L27:
        r0.setNeutralButton(LocaleController.getString("BotHelp", org.telegram.messenger.R.string.BotHelp), new LoginActivity$$ExternalSyntheticLambda21(r9, r7, r6));
        r0.setPositiveButton(LocaleController.getString("OK", org.telegram.messenger.R.string.OK), null);
        r6.showDialog(r0.create());
        return;
    L9:
        if (r8 != null) goto L11;
    L26:
        r0.setTitle(LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle));
        r0.setMessage(LocaleController.getString(org.telegram.messenger.R.string.InvalidPhoneNumber));
        goto L27
    L11:
        if (PhoneInputData.m15972$$Nest$fgetpatterns(r8) == null) goto L26;
        if (PhoneInputData.m15972$$Nest$fgetpatterns(r8).isEmpty() == true) goto L26;
        if (PhoneInputData.m15971$$Nest$fgetcountry(r8) == null) goto L26;
        Iterator r1 = PhoneInputData.m15972$$Nest$fgetpatterns(r8).iterator();
        int r2 = Integer.MAX_VALUE;
    L18:
        if (r1.hasNext() == false) goto L23;
        int r3 = ((String) r1.next()).replace(" ", _UrlKt.FRAGMENT_ENCODE_SET).length();
        if (r3 >= r2) goto L18;
        r2 = r3;
        goto L18
    L23:
        if ((PhoneFormat.stripExceptNumbers(r7).length() - PhoneInputData.m15971$$Nest$fgetcountry(r8).code.length()) >= r2) goto L25;
        r0.setTitle(LocaleController.getString(org.telegram.messenger.R.string.WrongNumberFormat));
        r0.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString("ShortNumberInfo", org.telegram.messenger.R.string.ShortNumberInfo, new Object[]{PhoneInputData.m15971$$Nest$fgetcountry(r8).name, PhoneInputData.m15973$$Nest$fgetphoneNumber(r8)})));
        goto L27
    L25:
        r0.setTitle(LocaleController.getString(org.telegram.messenger.R.string.RestorePasswordNoEmailTitle));
        r0.setMessage(LocaleController.getString(org.telegram.messenger.R.string.InvalidPhoneNumber));
        goto L27
    }

    public static void needShowInvalidAlert(BaseFragment r1, String r2, boolean r3) {
        needShowInvalidAlert(r1, r2, null, r3);
    }

    private void needShowProgress(int r2) {
        needShowProgress(r2, true);
    }

    private void needShowProgress(int r2, boolean r3) {
        if (isInCancelAccountDeletionMode() == false) goto L14;
        if (r2 != 0) goto L14;
        if (this.cancelDeleteProgressDialog == null) goto L8;
        return;
    L8:
        if (getParentActivity() != null) goto L10;
        return;
    L10:
        if (getParentActivity().isFinishing() == true) goto L18;
        AlertDialog r22 = new AlertDialog(getParentActivity(), 3);
        this.cancelDeleteProgressDialog = r22;
        r22.setCanCancel(false);
        this.cancelDeleteProgressDialog.show();
        return;
    L18:
        return;
    L14:
        this.progressRequestId = r2;
        showEditDoneProgress(true, r3);
    }

    private void onAuthSuccess(TLRPC.TL_auth_authorization r2) {
        onAuthSuccess(r2, false);
    }

    private void onAuthSuccess(TLRPC.TL_auth_authorization r5, boolean r6) {
        MessagesController.getInstance(this.currentAccount).cleanup();
        ConnectionsManager.getInstance(this.currentAccount).setUserId(r5.user.id);
        UserConfig.getInstance(this.currentAccount).clearConfig();
        MessagesController.getInstance(this.currentAccount).cleanup();
        UserConfig r0 = UserConfig.getInstance(this.currentAccount);
        r0.syncContacts = this.syncContacts;
        UserConfig.getInstance(this.currentAccount).setCurrentUser(r5.user);
        UserConfig.getInstance(this.currentAccount).saveConfig(true);
        MessagesStorage.getInstance(this.currentAccount).cleanup(true);
        ArrayList r02 = new ArrayList();
        r02.add(r5.user);
        MessagesStorage.getInstance(this.currentAccount).putUsersAndChats(r02, null, true, true);
        MessagesController.getInstance(this.currentAccount).putUser(r5.user, false);
        ContactsController.getInstance(this.currentAccount).checkAppAccount();
        MessagesController.getInstance(this.currentAccount).checkPromoInfo(true);
        ConnectionsManager.getInstance(this.currentAccount).updateDcSettings();
        MessagesController.getInstance(this.currentAccount).loadAppConfig();
        MessagesController.getInstance(this.currentAccount).loadWebBrowserConfig();
        MessagesController.getInstance(this.currentAccount).checkPeerColors(false);
        if (r5.future_auth_token == null) goto L5;
        AuthTokensHelper.saveLogInToken(r5);
    L6:
        if (r6 == false) goto L8;
        MessagesController.getInstance(this.currentAccount).putDialogsEndReachedAfterRegistration();
    L8:
        MediaDataController.getInstance(this.currentAccount).loadStickersByEmojiOrName(AndroidUtilities.STICKERS_PLACEHOLDER_PACK_NAME, false, true);
        needFinishActivity(r6, r5.setup_password_required, r5.otherwise_relogin_days);
        return;
    L5:
        FileLog.d("onAuthSuccess future_auth_token is empty");
        goto L6
    }

    private void onDoneButtonPressed() {
        if (this.doneButtonVisible[this.currentDoneType] == true) goto L6;
        return;
    L6:
        if (this.radialProgressView.getTag() != null) goto L8;
        this.views[this.currentViewNum].onNextPressed(null);
        return;
    L8:
        if (getParentActivity() != null) goto L10;
        return;
    L10:
        AlertDialog.Builder r0 = new AlertDialog.Builder(getParentActivity());
        r0.setTitle(LocaleController.getString("StopLoadingTitle", org.telegram.messenger.R.string.StopLoadingTitle));
        r0.setMessage(LocaleController.getString("StopLoading", org.telegram.messenger.R.string.StopLoading));
        r0.setPositiveButton(LocaleController.getString("WaitMore", org.telegram.messenger.R.string.WaitMore), null);
        r0.setNegativeButton(LocaleController.getString("Stop", org.telegram.messenger.R.string.Stop), new LoginActivity$$ExternalSyntheticLambda22(this));
        showDialog(r0.create());
    }

    private void onFieldError(View r6, boolean r7) {
        r6.performHapticFeedback(3, 2);     // Catch: Exception -> L15
    L4:
        AndroidUtilities.shakeViewSpring(r6, 3.5f);
        if (r7 == true) goto L7;
        return;
    L7:
        if ((r6 instanceof OutlineTextContainerView) == false) goto L19;
        Runnable r72 = (Runnable) r6.getTag(org.telegram.messenger.R.id.timeout_callback);
        if (r72 == null) goto L11;
        r6.removeCallbacks(r72);
    L11:
        OutlineTextContainerView r73 = (OutlineTextContainerView) r6;
        AtomicReference r0 = new AtomicReference();
        EditText r1 = r73.getAttachedEditText();
        AnonymousClass7 r2 = new AnonymousClass7(this, r1, r0);
        r73.animateError(1.0f);
        LoginActivity$$ExternalSyntheticLambda23 r5 = new LoginActivity$$ExternalSyntheticLambda23(r73, r6, r1, r2);
        r0.set(r5);
        r6.postDelayed(r5, 2000);
        r6.setTag(org.telegram.messenger.R.id.timeout_callback, r5);
        if (r1 == null) goto L20;
        r1.addTextChangedListener(r2);
        return;
    L20:
        return;
    }

    private void putBundleToEditor(Bundle r6, SharedPreferences.Editor r7, String r8) {
        Iterator<String> r0 = r6.keySet().iterator();
    L4:
        if (r0.hasNext() == false) goto L23;
        String r1 = r0.next();
        Object r2 = r6.get(r1);
        if ((r2 instanceof String) == true) goto L7;
        if ((r2 instanceof Integer) == true) goto L12;
        if ((r2 instanceof Boolean) == true) goto L17;
        if ((r2 instanceof Bundle) == false) goto L4;
        putBundleToEditor((Bundle) r2, r7, r1);
        goto L4
    L17:
        if (r8 != null) goto L18;
        r7.putBoolean(r1, ((Boolean) r2).booleanValue());
        goto L4
    L18:
        r7.putBoolean(r8 + "_|_" + r1, ((Boolean) r2).booleanValue());
        goto L4
    L12:
        if (r8 != null) goto L13;
        r7.putInt(r1, ((Integer) r2).intValue());
        goto L4
    L13:
        r7.putInt(r8 + "_|_" + r1, ((Integer) r2).intValue());
        goto L4
    L7:
        if (r8 != null) goto L8;
        r7.putString(r1, (String) r2);
        goto L4
    L8:
        r7.putString(r8 + "_|_" + r1, (String) r2);
        goto L4
    }

    private void setCustomKeyboardVisible(boolean r6, boolean r7) {
        if (this.customKeyboardWasVisible != r6) goto L6;
        if (r7 == false) goto L6;
        return;
    L6:
        this.customKeyboardWasVisible = r6;
        if (isCustomKeyboardForceDisabled() == false) goto L10;
        r6 = false;
    L10:
        if (r6 == false) goto L17;
        AndroidUtilities.hideKeyboard(this.fragmentView);
        AndroidUtilities.requestAltFocusable(getParentActivity(), this.classGuid);
        if (r7 == false) goto L15;
        ValueAnimator r62 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}).setDuration(300);
        this.keyboardAnimator = r62;
        r62.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.keyboardAnimator.addUpdateListener(new LoginActivity$$ExternalSyntheticLambda8(this));
        this.keyboardAnimator.addListener(new AnonymousClass5(this));
        this.keyboardAnimator.start();
        return;
    L15:
        this.keyboardView.setVisibility(0);
        return;
    L17:
        AndroidUtilities.removeAltFocusable(getParentActivity(), this.classGuid);
        if (r7 == false) goto L21;
        ValueAnimator r63 = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f}).setDuration(300);
        this.keyboardAnimator = r63;
        r63.setInterpolator(Easings.easeInOutQuad);
        this.keyboardAnimator.addUpdateListener(new LoginActivity$$ExternalSyntheticLambda9(this));
        this.keyboardAnimator.addListener(new AnonymousClass6(this));
        this.keyboardAnimator.start();
        return;
    L21:
        this.keyboardView.setVisibility(8);
    }

    private void showDoneButton(boolean r8, boolean r9) {
        int r0 = this.currentDoneType;
        if (r0 != 0) goto L5;
        boolean r3 = true;
    L7:
        if (this.doneButtonVisible[r0] != r8) goto L9;
        return;
    L9:
        AnimatorSet r02 = this.showDoneAnimation[r0];
        if (r02 == null) goto L14;
        if (r9 == false) goto L13;
        r02.removeAllListeners();
    L13:
        this.showDoneAnimation[this.currentDoneType].cancel();
    L14:
        boolean[] r03 = this.doneButtonVisible;
        int r4 = this.currentDoneType;
        r03[r4] = r8;
        if (r9 == false) goto L33;
        this.showDoneAnimation[r4] = new AnimatorSet();
        if (r8 == false) goto L23;
        if (r3 == true) goto L20;
    L25:
        this.showDoneAnimation[this.currentDoneType].addListener(new AnonymousClass8(this, r3, r8));
        int r92 = 150;
        if (r3 == false) goto L30;
        if (r8 == false) goto L29;
        TimeInterpolator r82 = AndroidUtilities.decelerateInterpolator;
        r92 = 200;
    L31:
        this.showDoneAnimation[this.currentDoneType].setDuration(r92);
        this.showDoneAnimation[this.currentDoneType].setInterpolator(r82);
        this.showDoneAnimation[this.currentDoneType].start();
        return;
    L29:
        r82 = AndroidUtilities.accelerateInterpolator;
        goto L31
    L30:
        r82 = null;
        goto L31
    L20:
        if (this.floatingButton.getVisibility() == 0) goto L22;
        this.floatingAutoAnimator.setOffsetY(AndroidUtilities.dpf2(70.0f));
        this.floatingButton.setVisibility(0);
    L22:
        ValueAnimator r93 = ValueAnimator.ofFloat(new float[]{this.floatingAutoAnimator.getOffsetY(), 0.0f});
        r93.addUpdateListener(new LoginActivity$$ExternalSyntheticLambda13(this));
        this.showDoneAnimation[this.currentDoneType].play(r93);
        goto L25
    L23:
        if (r3 == false) goto L25;
        ValueAnimator r94 = ValueAnimator.ofFloat(new float[]{this.floatingAutoAnimator.getOffsetY(), AndroidUtilities.dpf2(70.0f)});
        r94.addUpdateListener(new LoginActivity$$ExternalSyntheticLambda14(this));
        this.showDoneAnimation[this.currentDoneType].play(r94);
        goto L25
    L33:
        VerticalPositionAutoAnimator r95 = this.floatingAutoAnimator;
        if (r8 == false) goto L37;
        r95.setOffsetY(0.0f);
        this.floatingButton.setAlpha(1.0f);
        this.floatingButton.setVisibility(0);
        return;
    L37:
        r95.setOffsetY(AndroidUtilities.dpf2(70.0f));
        this.floatingButton.setAlpha(0.0f);
        this.floatingButton.setVisibility(8);
        return;
    L5:
        r3 = false;
        goto L7
    }

    private void showEditDoneProgress(boolean r2, boolean r3) {
        showEditDoneProgress(r2, r3, false);
    }

    private void showEditDoneProgress(boolean r5, boolean r6, boolean r7) {
        if (r6 == false) goto L8;
        if (this.doneProgressVisible[this.currentDoneType] != r5) goto L8;
        if (r7 == true) goto L8;
        return;
    L8:
        if (Looper.myLooper() == Looper.getMainLooper()) goto L11;
        AndroidUtilities.runOnUIThread(new LoginActivity$$ExternalSyntheticLambda15(this, r5, r6, r7));
        return;
    L11:
        int r0 = this.currentDoneType;
        if (r0 != 0) goto L14;
        boolean r3 = true;
    L15:
        if (r7 == true) goto L26;
        if (r3 == true) goto L26;
        this.doneProgressVisible[r0] = r5;
        if (r6 == true) goto L20;
    L27:
        AnimatorSet r72 = this.doneItemAnimation;
        if (r72 == null) goto L30;
        r72.cancel();
    L30:
        if (r3 == false) goto L33;
        this.floatingButton.setProgressVisible(r5, r6);
        return;
    L33:
        float r73 = 0.0f;
        if (r6 == false) goto L43;
        this.doneItemAnimation = new AnimatorSet();
        if (r5 == false) goto L38;
        float r62 = 0.0f;
    L39:
        if (r5 == false) goto L41;
        r73 = 1.0f;
    L41:
        ValueAnimator r63 = ValueAnimator.ofFloat(new float[]{r62, r73});
        r63.addListener(new AnonymousClass9(this, r5));
        r63.addUpdateListener(new LoginActivity$$ExternalSyntheticLambda17(this));
        this.doneItemAnimation.playTogether(new Animator[]{r63});
        this.doneItemAnimation.setDuration(150);
        this.doneItemAnimation.start();
        return;
    L38:
        r62 = 1.0f;
        goto L39
    L43:
        RadialProgressView r64 = this.radialProgressView;
        if (r5 == false) goto L47;
        r64.setVisibility(0);
        this.radialProgressView.setScaleX(1.0f);
        this.radialProgressView.setScaleY(1.0f);
        this.radialProgressView.setAlpha(1.0f);
        return;
    L47:
        r64.setTag(null);
        this.radialProgressView.setVisibility(4);
        this.radialProgressView.setScaleX(0.1f);
        this.radialProgressView.setScaleY(0.1f);
        this.radialProgressView.setAlpha(0.0f);
        return;
    L20:
        if (this.postedEditDoneCallback[r0] == false) goto L23;
        AndroidUtilities.cancelRunOnUIThread(this.editDoneCallback[r0]);
        this.postedEditDoneCallback[this.currentDoneType] = false;
        return;
    L23:
        if (r5 == false) goto L27;
        Runnable[] r74 = this.editDoneCallback;
        LoginActivity$$ExternalSyntheticLambda16 r2 = new LoginActivity$$ExternalSyntheticLambda16(this, r0, r5, r6);
        r74[r0] = r2;
        AndroidUtilities.runOnUIThread(r2, 2000);
        this.postedEditDoneCallback[this.currentDoneType] = true;
        return;
    L26:
        this.postedEditDoneCallback[r0] = false;
        this.doneProgressVisible[r0] = r5;
        goto L27
    L14:
        r3 = false;
        goto L15
    }

    private boolean showKeyboard(View r1) {
        if (isCustomKeyboardVisible() == false) goto L5;
        return true;
    L5:
        return AndroidUtilities.showKeyboard(r1);
    }

    private void showLoginOptionsMenu() {
        if (this.proxyButtonView != null) goto L5;
        return;
    L5:
        if (getContext() == null) goto L53;
        ItemOptions r0 = this.loginOptionsMenu;
        if (r0 != null) goto L10;
    L12:
        SharedPreferences r02 = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0);
        String r1 = r02.getString("proxy_ip", _UrlKt.FRAGMENT_ENCODE_SET);
        boolean r3 = true;
        if (r02.getBoolean("proxy_enabled", false) == true) goto L15;
    L17:
        boolean r03 = false;
    L18:
        if (r03 == false) goto L20;
    L25:
        boolean r12 = true;
    L26:
        int r4 = getConnectionsManager().getConnectionState();
        if (r4 == 3) goto L31;
        if (r4 == 5) goto L31;
        boolean r5 = false;
    L32:
        if (r4 != 1) goto L34;
    L39:
        ProxyDrawable r42 = new ProxyDrawable(getContext());
        int r7 = Theme.key_actionBarDefaultSubmenuItemIcon;
        int r8 = getThemedColor(r7);
        PorterDuff.Mode r10 = PorterDuff.Mode.SRC_IN;
        r42.setColorFilter(new PorterDuffColorFilter(r8, r10));
        r42.setConnected(r03, r5, false);
        ItemOptions r04 = ItemOptions.makeOptions(this, this.proxyButtonView).setGravity(5).setOnTopOfScrim().translate(AndroidUtilities.dp(6.0f), -AndroidUtilities.dp(12.0f));
        int r6 = Theme.key_actionBarDefaultSubmenuItem;
        r04.setColors(getThemedColor(r6), getThemedColor(r7));
        ActionBarMenuSubItem r9 = r04.add();
        r9.setTextAndIcon(LocaleController.getString(org.telegram.messenger.R.string.ProxySettings), 0, r42);
        r9.setTextColor(getThemedColor(r6));
        r9.setIconColor(r8, r10);
        String r82 = null;
        if (r12 == false) goto L46;
        if (r5 == false) goto L44;
        int r32 = org.telegram.messenger.R.string.MenuProxyConnected;
    L43:
        r82 = LocaleController.getString(r32);
        goto L46
    L44:
        if (r3 == false) goto L46;
        r32 = org.telegram.messenger.R.string.MenuProxyConnecting;
    L46:
        r9.setSubtext(r82);
        if (r12 == false) goto L49;
        int r13 = 56;
    L50:
        r9.setItemHeight(r13);
        r9.setOnClickListener(new LoginActivity$$ExternalSyntheticLambda18(this, r04));
        ActionBarMenuSubItem r14 = r04.add();
        r14.setTextAndIcon(LocaleController.getString(org.telegram.messenger.R.string.LoginQrTitle), org.telegram.messenger.R.drawable.msg_qrcode);
        r14.setTextColor(getThemedColor(r6));
        r14.setIconColor(getThemedColor(r7), r10);
        r14.setOnClickListener(new LoginActivity$$ExternalSyntheticLambda19(this, r04));
        this.loginOptionsMenu = r04;
        this.loginOptionsProxyItem = r9;
        this.loginOptionsProxyDrawable = r42;
        r04.setOnDismiss(new LoginActivity$$ExternalSyntheticLambda20(this));
        updateLoginOptionsProxyItem(false);
        r04.show();
        return;
    L49:
        r13 = 48;
        goto L50
    L34:
        if (r4 == 2) goto L39;
        if (r4 == 4) goto L39;
        r3 = false;
    L31:
        r5 = true;
        goto L32
    L20:
        if (getMessagesController().blockedCountry == true) goto L22;
    L24:
        r12 = false;
        goto L26
    L22:
        if (SharedConfig.proxyList.isEmpty() == true) goto L24;
    L15:
        if (TextUtils.isEmpty(r1) == true) goto L17;
        r03 = true;
        goto L18
    L10:
        if (r0.isShown() == false) goto L12;
        this.loginOptionsMenu.dismiss();
        goto L12
    }

    private void showProxyButton(boolean r5, boolean r6) {
        if (r5 != this.proxyButtonVisible) goto L5;
        return;
    L5:
        Runnable r0 = this.showProxyButtonDelayed;
        if (r0 == null) goto L8;
        AndroidUtilities.cancelRunOnUIThread(r0);
        this.showProxyButtonDelayed = null;
    L8:
        this.proxyButtonVisible = r5;
        this.proxyButtonView.clearAnimation();
        ImageView r02 = this.proxyButtonView;
        float r1 = 0.0f;
        int r3 = 0;
        if (r6 == false) goto L15;
        r02.setVisibility(0);
        ViewPropertyAnimator r62 = this.proxyButtonView.animate();
        if (r5 == false) goto L13;
        r1 = 1.0f;
    L13:
        r62.alpha(r1).withEndAction(new LoginActivity$$ExternalSyntheticLambda10(this, r5)).start();
        return;
    L15:
        if (r5 == true) goto L18;
        r3 = 8;
    L18:
        r02.setVisibility(r3);
        ImageView r4 = this.proxyButtonView;
        if (r5 == false) goto L21;
        r1 = 1.0f;
    L21:
        r4.setAlpha(r1);
    }

    private void showProxyButtonDelayed() {
        if (this.proxyButtonVisible == false) goto L5;
        return;
    L5:
        Runnable r0 = this.showProxyButtonDelayed;
        if (r0 == null) goto L8;
        AndroidUtilities.cancelRunOnUIThread(r0);
    L8:
        this.proxyButtonVisible = true;
        LoginActivity$$ExternalSyntheticLambda12 r02 = new LoginActivity$$ExternalSyntheticLambda12(this);
        this.showProxyButtonDelayed = r02;
        AndroidUtilities.runOnUIThread(r02, 5000);
    }

    private void tryResetAccount(String r4, String r5, String r6) {
        if (this.radialProgressView.getTag() == null) goto L5;
        return;
    L5:
        AlertDialog.Builder r0 = new AlertDialog.Builder(getParentActivity());
        r0.setMessage(LocaleController.getString("ResetMyAccountWarningText", org.telegram.messenger.R.string.ResetMyAccountWarningText));
        r0.setTitle(LocaleController.getString("ResetMyAccountWarning", org.telegram.messenger.R.string.ResetMyAccountWarning));
        r0.setPositiveButton(LocaleController.getString("ResetMyAccountWarningReset", org.telegram.messenger.R.string.ResetMyAccountWarningReset), new LoginActivity$$ExternalSyntheticLambda26(this, r4, r5, r6));
        r0.setNegativeButton(LocaleController.getString("Cancel", org.telegram.messenger.R.string.Cancel), null);
        showDialog(r0.create());
    }

    private void updateColors() {
        this.fragmentView.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        this.backButtonView.setColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        ImageView r0 = this.backButtonView;
        int r1 = Theme.key_listSelector;
        r0.setBackground(Theme.createSelectorDrawable(Theme.getColor(r1)));
        this.proxyButtonView.setColorFilter(Theme.getColor(Theme.key_actionBarDefaultIcon));
        this.proxyButtonView.setBackground(Theme.createSelectorDrawable(Theme.getColor(r1)));
        RadialProgressView r02 = this.radialProgressView;
        int r12 = Theme.key_chats_actionBackground;
        r02.setProgressColor(Theme.getColor(r12));
        this.floatingButton.updateColors();
        this.floatingButton.setBackground(UIUtil.createFabBackground(56, Theme.getColor(Theme.key_featuredStickers_addButton), Theme.getColor(Theme.key_featuredStickers_addButtonPressed)));
        FragmentFloatingButton r03 = this.floatingButton;
        int r3 = 0;
        if (ExteraConfig.getSquareFab() == false) goto L5;
        ViewOutlineProvider r2 = ViewOutlineProviderImpl.boundsWithPaddingRoundRect(0, AndroidUtilities.dp(16.0f));
    L6:
        r03.setOutlineProvider(r2);
        this.floatingButtonIcon.setColor(Theme.getColor(Theme.key_chats_actionIcon));
        this.floatingButtonIcon.setBackgroundColor(Theme.getColor(r12));
        SlideView[] r04 = this.views;
        int r13 = r04.length;
    L7:
        if (r3 >= r13) goto L9;
        r04[r3].updateColors();
        r3 = r3 + 1;
        goto L7
    L9:
        this.keyboardView.updateColors();
        PhoneNumberConfirmView r5 = this.phoneNumberConfirmView;
        if (r5 == null) goto L14;
        PhoneNumberConfirmView.m15986$$Nest$mupdateColors(r5);
        return;
    L14:
        return;
    L5:
        r2 = ViewOutlineProviderImpl.BOUNDS_OVAL;
        goto L6
    }

    private void updateLoginOptionsProxyItem(boolean r9) {
        ItemOptions r0 = this.loginOptionsMenu;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.isShown() == true) goto L7;
        return;
    L7:
        if (this.loginOptionsProxyItem != null) goto L9;
        return;
    L9:
        if (this.loginOptionsProxyDrawable == null) goto L54;
        boolean r2 = false;
        SharedPreferences r02 = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0);
        String r1 = r02.getString("proxy_ip", _UrlKt.FRAGMENT_ENCODE_SET);
        if (r02.getBoolean("proxy_enabled", false) == true) goto L14;
    L16:
        boolean r03 = false;
    L17:
        if (r03 == false) goto L19;
    L24:
        boolean r12 = true;
    L25:
        int r4 = getConnectionsManager().getConnectionState();
        if (r4 != 3) goto L28;
    L31:
        boolean r5 = true;
    L32:
        if (r4 != 1) goto L34;
    L37:
        r2 = true;
    L38:
        int r3 = getThemedColor(Theme.key_actionBarDefaultSubmenuItemIcon);
        ProxyDrawable r42 = this.loginOptionsProxyDrawable;
        PorterDuff.Mode r7 = PorterDuff.Mode.SRC_IN;
        r42.setColorFilter(new PorterDuffColorFilter(r3, r7));
        this.loginOptionsProxyDrawable.setConnected(r03, r5, r9);
        this.loginOptionsProxyItem.setTextColor(getThemedColor(Theme.key_actionBarDefaultSubmenuItem));
        this.loginOptionsProxyItem.setIconColor(r3, r7);
        ActionBarMenuSubItem r92 = this.loginOptionsProxyItem;
        String r04 = null;
        if (r12 == false) goto L45;
        if (r5 == false) goto L43;
        int r05 = org.telegram.messenger.R.string.MenuProxyConnected;
    L42:
        r04 = LocaleController.getString(r05);
        goto L45
    L43:
        if (r2 == false) goto L45;
        r05 = org.telegram.messenger.R.string.MenuProxyConnecting;
    L45:
        r92.setSubtext(r04);
        ActionBarMenuSubItem r93 = this.loginOptionsProxyItem;
        if (r12 == false) goto L48;
        int r06 = 56;
    L49:
        r93.setItemHeight(r06);
        this.loginOptionsProxyItem.requestLayout();
        this.loginOptionsProxyItem.invalidate();
        return;
    L48:
        r06 = 48;
        goto L49
    L34:
        if (r4 == 2) goto L37;
        if (r4 != 4) goto L38;
    L28:
        if (r4 == 5) goto L31;
        r5 = false;
        goto L32
    L19:
        if (getMessagesController().blockedCountry == true) goto L21;
    L23:
        r12 = false;
        goto L25
    L21:
        if (SharedConfig.proxyList.isEmpty() == true) goto L23;
    L14:
        if (TextUtils.isEmpty(r1) == true) goto L16;
        r03 = true;
        goto L17
    }

    private void updateProxyButton(boolean r6, boolean r7) {
        if (this.proxyDrawable == null) goto L42;
        int r0 = getConnectionsManager().getConnectionState();
        if (this.currentConnectionState != r0) goto L9;
        if (r7 == true) goto L9;
        return;
    L9:
        this.currentConnectionState = r0;
        SharedPreferences r72 = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0);
        String r02 = r72.getString("proxy_ip", _UrlKt.FRAGMENT_ENCODE_SET);
        if (r72.getBoolean("proxy_enabled", false) == true) goto L12;
    L14:
        boolean r73 = false;
    L15:
        int r03 = this.currentConnectionState;
        if (r03 != 3) goto L18;
    L21:
        boolean r3 = true;
    L22:
        if (r03 != 1) goto L24;
    L29:
        boolean r04 = true;
    L30:
        if (r73 == false) goto L34;
        this.proxyDrawable.setConnected(true, r3, r6);
        showProxyButton(true, r6);
        return;
    L34:
        if (getMessagesController().blockedCountry == true) goto L36;
    L37:
        if (r04 == false) goto L40;
    L38:
        this.proxyDrawable.setConnected(true, r3, r6);
        showProxyButtonDelayed();
        return;
    L40:
        showProxyButton(false, r6);
        return;
    L36:
        if (SharedConfig.proxyList.isEmpty() == false) goto L38;
    L24:
        if (r03 == 2) goto L29;
        if (r03 == 4) goto L29;
        r04 = false;
        goto L30
    L18:
        if (r03 == 5) goto L21;
        r3 = false;
        goto L22
    L12:
        if (TextUtils.isEmpty(r02) == true) goto L14;
        r73 = true;
        goto L15
    }

    public LoginActivity cancelAccountDeletion(String r1, Bundle r2, TLRPC.TL_auth_sentCode r3) {
        this.cancelDeletionPhone = r1;
        this.cancelDeletionParams = r2;
        this.cancelDeletionCode = r3;
        this.activityMode = 1;
        return this;
    }

    public LoginActivity changeEmail(Runnable r2) {
        this.activityMode = 3;
        this.currentViewNum = 12;
        this.emailChangeFinishCallback = r2;
        return this;
    }

    public LoginActivity changeEmail(Runnable r2, Runnable r3, boolean r4) {
        this.activityMode = 3;
        this.currentViewNum = 12;
        this.emailChangeFinishCallback = r2;
        this.emailChangeSkipCallback = r3;
        this.emailChangeNonSkippable = r4;
        this.emailChangeIsSuggestion = true;
        return this;
    }

    public LoginActivity changePhoneNumber() {
        this.activityMode = 2;
        return this;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void clearViews() {
        View r0 = this.fragmentView;
        if (r0 == null) goto L14;
        ViewGroup r02 = (ViewGroup) r0.getParent();
        if (r02 == null) goto L11;
        onRemoveFromParent();     // Catch: Exception -> L8
        BaseFragment.removeViewFromParent(r02, this.fragmentView);     // Catch: Exception -> L8
    L8:
        e = move-exception;
        FileLog.e(e);
    L11:
        if (this.pendingSwitchingAccount == false) goto L13;
        this.cachedFragmentView = this.fragmentView;
    L13:
        this.fragmentView = null;
    L14:
        ActionBar r03 = this.actionBar;
        if (r03 != null) goto L17;
    L25:
        clearSheets();
        this.parentLayout = null;
        return;
    L17:
        if (this.pendingSwitchingAccount == true) goto L25;
        ViewGroup r04 = (ViewGroup) r03.getParent();
        if (r04 != null) goto L27;
    L24:
        this.actionBar = null;
        goto L25
    L27:
        BaseFragment.removeViewFromParent(r04, this.actionBar);     // Catch: Exception -> L22
    L22:
        e = move-exception;
        FileLog.e(e);
        goto L24
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r29) {
        View r2 = this.cachedFragmentView;
        if (r2 == null) goto L6;
        this.fragmentView = r2;
        this.cachedFragmentView = null;
        return r2;
    L6:
        this.actionBar.setAddToContainer(false);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        this.currentDoneType = 0;
        boolean[] r22 = this.doneButtonVisible;
        r22[0] = true;
        r22[1] = false;
        AnonymousClass2 r23 = new AnonymousClass2(this, r29);
        this.sizeNotifierFrameLayout = r23;
        r23.setDelegate(new LoginActivity$$ExternalSyntheticLambda1(this));
        this.fragmentView = this.sizeNotifierFrameLayout;
        AnonymousClass3 r24 = new AnonymousClass3(this, r29);
        r24.setFillViewport(true);
        this.sizeNotifierFrameLayout.addView(r24, LayoutHelper.createFrame(-1, -1.0f));
        LinearLayout r6 = new LinearLayout(r29);
        this.keyboardLinearLayout = r6;
        r6.setOrientation(1);
        r24.addView(this.keyboardLinearLayout, LayoutHelper.createScroll(-1, -2, 51));
        Space r25 = new Space(r29);
        if (AndroidUtilities.isTablet() == false) goto L9;
        int r62 = 0;
    L10:
        r25.setMinimumHeight(r62);
        this.keyboardLinearLayout.addView(r25);
        AnonymousClass4 r26 = new AnonymousClass4(this, r29);
        this.slideViewsContainer = r26;
        this.keyboardLinearLayout.addView(r26, LayoutHelper.createLinear(-1, 0, 1.0f));
        CustomPhoneKeyboardView r27 = new CustomPhoneKeyboardView(r29);
        this.keyboardView = r27;
        r27.setViewToFindFocus(this.slideViewsContainer);
        this.keyboardLinearLayout.addView(this.keyboardView, LayoutHelper.createLinear(-1, 230));
        this.views[0] = new PhoneView(this, r29);
        this.views[1] = new LoginActivitySmsView(this, r29, 1);
        this.views[2] = new LoginActivitySmsView(this, r29, 2);
        this.views[3] = new LoginActivitySmsView(this, r29, 3);
        this.views[4] = new LoginActivitySmsView(this, r29, 4);
        this.views[5] = new LoginActivityRegisterView(this, r29);
        this.views[6] = new LoginActivityPasswordView(this, r29);
        this.views[7] = new LoginActivityRecoverView(this, r29);
        this.views[8] = new LoginActivityResetWaitView(this, r29);
        this.views[9] = new LoginActivityNewPasswordView(this, r29, 0);
        this.views[10] = new LoginActivityNewPasswordView(this, r29, 1);
        this.views[11] = new LoginActivitySmsView(this, r29, 11);
        this.views[12] = new LoginActivitySetupEmail(this, r29);
        this.views[13] = new LoginActivityEmailCodeView(this, r29, true);
        this.views[14] = new LoginActivityEmailCodeView(this, r29, false);
        this.views[15] = new LoginActivitySmsView(this, r29, 15);
        this.views[16] = new LoginActivityPhraseView(this, r29, 16);
        this.views[17] = new LoginActivityPhraseView(this, r29, 17);
        this.views[18] = new LoginActivityQrView(this, r29);
        this.views[19] = new LoginPayView(this, r29);
        int r28 = 0;
    L11:
        SlideView[] r63 = this.views;
        float r11 = 0.0f;
        if (r28 >= r63.length) goto L33;
        SlideView r64 = r63[r28];
        if (r28 != 0) goto L16;
        int r15 = 0;
    L17:
        r64.setVisibility(r15);
        if (r28 == 19) goto L20;
        boolean r65 = true;
    L21:
        FrameLayout r152 = this.slideViewsContainer;
        SlideView r8 = this.views[r28];
        if (AndroidUtilities.isTablet() == false) goto L24;
        float r242 = 26.0f;
    L25:
        if (r65 == false) goto L27;
        r11 = 30.0f;
    L27:
        float r252 = r11;
        if (AndroidUtilities.isTablet() == false) goto L30;
        float r262 = 26.0f;
    L31:
        r152.addView(r8, LayoutHelper.createFrame(-1, -1.0f, 17, r242, r252, r262, 0.0f));
        r28 = r28 + 1;
        goto L11
    L30:
        r262 = 18.0f;
        goto L31
    L24:
        r242 = 18.0f;
        goto L25
    L20:
        r65 = false;
        goto L21
    L16:
        r15 = 8;
        goto L17
    L33:
        if (this.activityMode != 0) goto L35;
        Bundle r210 = loadCurrentState(this.newAccount, this.currentAccount);
    L37:
        if (r210 == null) goto L43;
        int r82 = r210.getInt("currentViewNum", 0);
        if (r82 >= 0) goto L41;
    L42:
        r210 = null;
        goto L43
    L41:
        if (r82 >= this.views.length) goto L42;
    L43:
        if (r210 == null) goto L69;
        this.currentViewNum = r210.getInt("currentViewNum", 0);
        if (r210.getInt("syncContacts", 0) != 1) goto L47;
        boolean r66 = true;
    L48:
        this.syncContacts = r66;
        int r67 = this.currentViewNum;
        if (r67 < 1) goto L59;
        if (r67 > 4) goto L59;
        int r68 = r210.getInt("open");
        if (r68 != 0) goto L54;
    L57:
        Bundle r16 = r210;
    L58:
        r210 = r16;
        goto L69
    L54:
        if (Math.abs((System.currentTimeMillis() / 1000) - r68) < 86400) goto L57;
        this.currentViewNum = 0;
        clearCurrentState();
    L56:
        r16 = null;
    L59:
        if (r67 == 6) goto L61;
        if (r67 == 7) goto L65;
        if (r67 != 18) goto L69;
        this.currentViewNum = 0;
        clearCurrentState();
        r210 = null;
        goto L69
    L65:
        if (LoginActivityRecoverView.m15894$$Nest$fgetpasswordString((LoginActivityRecoverView) this.views[7]) != null) goto L57;
        this.currentViewNum = 0;
        clearCurrentState();
        goto L56
    L61:
        if (LoginActivityPasswordView.m15863$$Nest$fgetcurrentPassword((LoginActivityPasswordView) this.views[6]) != null) goto L57;
        this.currentViewNum = 0;
        clearCurrentState();
        goto L56
    L47:
        r66 = false;
    L69:
        FragmentFloatingButton r69 = new FragmentFloatingButton(r29, this.resourceProvider);
        this.floatingButton = r69;
        r69.setButtonVisible(this.doneButtonVisible[0], false);
        this.floatingAutoAnimator = VerticalPositionAutoAnimator.attach(this.floatingButton);
        this.sizeNotifierFrameLayout.addView(this.floatingButton, FragmentFloatingButton.createDefaultLayoutParamsBig());
        this.floatingButton.setOnClickListener(new LoginActivity$$ExternalSyntheticLambda2(this));
        this.floatingAutoAnimator.addUpdateListener(new LoginActivity$$ExternalSyntheticLambda3(this));
        ImageView r610 = new ImageView(r29);
        this.backButtonView = r610;
        r610.setImageResource(org.telegram.messenger.R.drawable.ic_ab_back);
        this.backButtonView.setOnClickListener(new LoginActivity$$ExternalSyntheticLambda4(this));
        this.backButtonView.setContentDescription(LocaleController.getString(org.telegram.messenger.R.string.AccDescrGoBack));
        int r611 = AndroidUtilities.dp(4.0f);
        this.backButtonView.setPadding(r611, r611, r611, r611);
        this.sizeNotifierFrameLayout.addView(this.backButtonView, LayoutHelper.createFrame(32, 32.0f, 51, 16.0f, 16.0f, 0.0f, 0.0f));
        if (this.emailChangeSkipCallback != null) goto L72;
    L76:
        ImageView r3 = new ImageView(r29);
        this.proxyButtonView = r3;
        r3.setImageResource(org.telegram.messenger.R.drawable.ic_ab_other);
        this.proxyButtonView.setOnClickListener(new LoginActivity$$ExternalSyntheticLambda6(this));
        this.proxyButtonView.setContentDescription(LocaleController.getString(org.telegram.messenger.R.string.AccDescrMoreOptions));
        this.proxyButtonView.setPadding(r611, r611, r611, r611);
        this.proxyButtonView.setAlpha(1.0f);
        this.proxyButtonView.setVisibility(0);
        this.sizeNotifierFrameLayout.addView(this.proxyButtonView, LayoutHelper.createFrame(32, 32.0f, 53, 16.0f, 16.0f, 16.0f, 16.0f));
        RadialProgressView r32 = new RadialProgressView(r29);
        this.radialProgressView = r32;
        r32.setSize(AndroidUtilities.dp(20.0f));
        this.radialProgressView.setAlpha(0.0f);
        this.radialProgressView.setScaleX(0.1f);
        this.radialProgressView.setScaleY(0.1f);
        this.sizeNotifierFrameLayout.addView(this.radialProgressView, LayoutHelper.createFrame(32, 32.0f, 53, 0.0f, 16.0f, 16.0f, 0.0f));
        TransformableLoginButtonView r33 = new TransformableLoginButtonView(r29);
        this.floatingButtonIcon = r33;
        r33.setTransformType(0);
        this.floatingButtonIcon.setProgress(1.0f);
        this.floatingButtonIcon.setDrawBackground(false);
        this.floatingButton.setContentDescription(LocaleController.getString(org.telegram.messenger.R.string.Done));
        this.floatingButton.addView(this.floatingButtonIcon, LayoutHelper.createFrame(56, 56, 17));
        this.floatingButton.addAdditionalView(this.floatingButtonIcon);
        if (r210 == null) goto L79;
        this.restoringState = true;
    L79:
        int r1 = 0;
    L80:
        SlideView[] r34 = this.views;
        if (r1 >= r34.length) goto L133;
        SlideView r35 = r34[r1];
        if (r210 == null) goto L92;
        if (r1 >= 1) goto L86;
    L90:
        r35.restoreStateParams(r210);
        goto L92
    L86:
        if (r1 > 4) goto L90;
        if (r1 != this.currentViewNum) goto L92;
        r35.restoreStateParams(r210);
    L92:
        if (this.currentViewNum != r1) goto L130;
        ImageView r612 = this.backButtonView;
        if (r35.needBackButton() == false) goto L96;
    L101:
        int r7 = 0;
    L102:
        r612.setVisibility(r7);
        r35.setVisibility(0);
        r35.onShow();
        setCustomKeyboardVisible(r35.hasCustomKeyboard(), false);
        this.currentDoneType = 0;
        if (r1 == 0) goto L115;
        if (r1 == 5) goto L115;
        if (r1 == 6) goto L115;
        if (r1 == 9) goto L115;
        if (r1 == 10) goto L115;
        if (r1 == 12) goto L115;
        boolean r36 = false;
    L116:
        showDoneButton(r36, false);
        if (r1 == 1) goto L127;
        if (r1 == 2) goto L127;
        if (r1 == 3) goto L127;
        if (r1 == 4) goto L127;
    L132:
        r1 = r1 + 1;
    L127:
        this.currentDoneType = 1;
    L115:
        r36 = true;
        goto L116
    L96:
        if (this.newAccount == true) goto L101;
        if (this.activityMode == 2) goto L101;
        r7 = 8;
        goto L102
    L130:
        if (r35.getVisibility() == 8) goto L132;
        r35.setVisibility(8);
        r35.onHide();
        goto L132
    L133:
        this.restoringState = false;
        updateColors();
        if (isInCancelAccountDeletionMode() == false) goto L137;
        fillNextCodeParams(this.cancelDeletionParams, this.cancelDeletionCode, false);
    L137:
        return this.fragmentView;
    L72:
        if (this.emailChangeNonSkippable == true) goto L76;
        if (this.emailChangeIsSuggestion == false) goto L76;
        TextView r9 = new TextView(r29);
        this.emailChangeSkipButton = r9;
        r9.setGravity(19);
        this.emailChangeSkipButton.setTextSize(1, 15.0f);
        this.emailChangeSkipButton.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
        this.emailChangeSkipButton.setPadding(AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(16.0f), 0);
        this.emailChangeSkipButton.setText(LocaleController.getString(org.telegram.messenger.R.string.YourEmailSkip));
        this.emailChangeSkipButton.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText4));
        this.sizeNotifierFrameLayout.addView(this.emailChangeSkipButton, LayoutHelper.createFrame(-2, 30.0f, 53, 0.0f, 16.0f, 16.0f, 0.0f));
        this.emailChangeSkipButton.setOnClickListener(new LoginActivity$$ExternalSyntheticLambda5(this));
        goto L76
    L35:
        r210 = null;
        goto L37
    L9:
        r62 = AndroidUtilities.statusBarHeight;
        goto L10
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r2, int r3, Object... r4) {
        if (r2 != NotificationCenter.didUpdateConnectionState) goto L7;
        updateProxyButton(true, false);
        updateLoginOptionsProxyItem(true);
        return;
    L7:
        if (r2 != NotificationCenter.proxySettingsChanged) goto L11;
        updateProxyButton(false, true);
        updateLoginOptionsProxyItem(false);
        return;
    L11:
        if (r2 == NotificationCenter.newSuggestionsAvailable) goto L13;
        return;
    L13:
        if (this.emailChangeIsSuggestion == true) goto L15;
        return;
    L15:
        if (getMessagesController().hasSetupEmailSuggestion() == true) goto L20;
        finishFragment();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public int getNavigationBarColor() {
        return getThemedColor(Theme.key_windowBackgroundWhite);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        return SimpleThemeDescription.createThemeDescriptions(new LoginActivity$$ExternalSyntheticLambda0(this), new int[]{Theme.key_windowBackgroundWhiteBlackText, Theme.key_windowBackgroundWhiteGrayText6, Theme.key_windowBackgroundWhiteHintText, Theme.key_listSelector, Theme.key_chats_actionBackground, Theme.key_chats_actionIcon, Theme.key_windowBackgroundWhiteInputField, Theme.key_windowBackgroundWhiteInputFieldActivated, Theme.key_windowBackgroundWhiteValueText, Theme.key_text_RedBold, Theme.key_windowBackgroundWhiteGrayText, Theme.key_checkbox, Theme.key_windowBackgroundWhiteBlueText4, Theme.key_changephoneinfo_image2, Theme.key_chats_actionPressedBackground, Theme.key_text_RedRegular, Theme.key_windowBackgroundWhiteLinkText, Theme.key_checkboxSquareUnchecked, Theme.key_checkboxSquareBackground, Theme.key_checkboxSquareCheck, Theme.key_dialogBackground, Theme.key_dialogTextGray2, Theme.key_dialogTextBlack, Theme.key_actionBarDefaultIcon, Theme.key_featuredStickers_addButton, Theme.key_featuredStickers_addButtonPressed});
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isLightStatusBar() {
        if (ColorUtils.calculateLuminance(Theme.getColor(Theme.key_windowBackgroundWhite, null, true)) <= 0.699999988079071d) goto L5;
        return true;
    L5:
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSwipeBackEnabled(MotionEvent r1) {
        return !this.emailChangeIsSuggestion;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onActivityResultFragment(int r3, int r4, Intent r5) {
        super.onActivityResultFragment(r3, r4, r5);
        if (r3 != 201) goto L10;
        int r0 = this.currentViewNum;
        if (r0 != 0) goto L10;
        SlideView r02 = this.views[r0];
        if ((r02 instanceof PhoneView) == false) goto L10;
        ((PhoneView) r02).handlePhoneNumberHintResult(r4, r5);
        return;
    L10:
        LoginActivityRegisterView r2 = (LoginActivityRegisterView) this.views[5];
        if (r2 == null) goto L14;
        LoginActivityRegisterView.m15915$$Nest$fgetimageUpdater(r2).onActivityResult(r3, r4, r5);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r9) {
        int r2 = 0;
        if (this.emailChangeIsSuggestion == true) goto L5;
    L7:
        int r0 = this.currentViewNum;
        if (r0 != 0) goto L10;
    L57:
        if (r9 == false) goto L65;
    L58:
        SlideView[] r92 = this.views;
        if (r2 >= r92.length) goto L64;
        SlideView r93 = r92[r2];
        if (r93 == null) goto L63;
        r93.onDestroyActivity();
    L63:
        r2 = r2 + 1;
        goto L58
    L64:
        clearCurrentState();
    L65:
        return true;
    L10:
        if (this.activityMode != 3) goto L14;
        if (r0 == 12) goto L57;
    L14:
        if (r0 != 6) goto L18;
        if (r9 == false) goto L56;
        this.views[r0].onBackPressed(true);
        setPage(0, true, null, true);
    L56:
        return false;
    L18:
        if (r0 != 7) goto L20;
    L54:
        if (r9 == false) goto L56;
        this.views[r0].onBackPressed(true);
        setPage(6, true, null, true);
        goto L56
    L20:
        if (r0 == 8) goto L54;
        if (r0 < 1) goto L26;
        if (r0 > 4) goto L26;
    L50:
        if (r9 == false) goto L56;
        if (this.views[r0].onBackPressed(false) == false) goto L56;
        setPage(0, true, null, true);
    L26:
        if (r0 == 11) goto L50;
        if (r0 == 15) goto L50;
        if (r0 != 5) goto L35;
        if (r9 == false) goto L56;
        LoginActivityRegisterView.m15917$$Nest$fgetwrongNumber((LoginActivityRegisterView) this.views[r0]).callOnClick();
        goto L56
    L35:
        if (r0 != 9) goto L39;
        if (r9 == false) goto L56;
        this.views[r0].onBackPressed(true);
        setPage(7, true, null, true);
        goto L56
    L39:
        if (r0 != 10) goto L43;
        if (r9 == false) goto L56;
        this.views[r0].onBackPressed(true);
        setPage(9, true, null, true);
        goto L56
    L43:
        if (r0 != 13) goto L46;
        if (r9 == false) goto L56;
        this.views[r0].onBackPressed(true);
        setPage(12, true, null, true);
        goto L56
    L46:
        if (r9 == false) goto L56;
        if (this.views[r0].onBackPressed(true) == false) goto L56;
        setPage(0, true, null, true);
        goto L56
    L5:
        if (this.currentViewNum != 12) goto L7;
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onConfigurationChanged(Configuration r2) {
        setCustomKeyboardVisible(this.views[this.currentViewNum].hasCustomKeyboard(), false);
        PhoneNumberConfirmView r1 = this.phoneNumberConfirmView;
        if (r1 == null) goto L6;
        PhoneNumberConfirmView.m15984$$Nest$mdismiss(r1);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public AnimatorSet onCustomTransitionAnimation(boolean r17, Runnable r18) {
        if (r17 == true) goto L5;
        return null;
    L5:
        if (this.introView != null) goto L7;
        return null;
    L7:
        if ((this.fragmentView.getParent() instanceof View) == false) goto L9;
        ((View) this.fragmentView.getParent()).setTranslationX(0.0f);
    L9:
        TransformableLoginButtonView r8 = new TransformableLoginButtonView(this.fragmentView.getContext());
        r8.setButtonText(this.startMessagingButton.getPaint(), this.startMessagingButton.getText().toString());
        int r5 = this.startMessagingButton.getWidth();
        int r7 = this.startMessagingButton.getHeight();
        int r6 = this.floatingButtonIcon.getLayoutParams().width;
        FrameLayout.LayoutParams r4 = new FrameLayout.LayoutParams(r5, r7);
        r8.setLayoutParams(r4);
        int[] r2 = new int[2];
        this.fragmentView.getLocationInWindow(r2);
        int r3 = r2[0];
        int r9 = r2[1];
        this.startMessagingButton.getLocationInWindow(r2);
        float r32 = r2[0] - r3;
        float r11 = r2[1] - r9;
        r8.setTranslationX(r32);
        r8.setTranslationY(r11);
        int r10 = (((getParentLayout().getView().getWidth() - this.floatingButtonIcon.getLayoutParams().width) - AndroidUtilities.dp(20.0f)) - getParentLayout().getView().getPaddingLeft()) - getParentLayout().getView().getPaddingRight();
        int r22 = (getParentLayout().getView().getHeight() - this.floatingButtonIcon.getLayoutParams().height) - AndroidUtilities.dp(14.0f);
        if (isCustomKeyboardVisible() == false) goto L12;
        int r92 = AndroidUtilities.dp(230.0f);
    L13:
        int r12 = ((r22 - r92) - getParentLayout().getView().getPaddingTop()) - getParentLayout().getView().getPaddingBottom();
        ValueAnimator r15 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r15.addListener(new AnonymousClass11(this, r8, r18));
        int r23 = Theme.getColor(Theme.key_windowBackgroundWhite);
        r15.addUpdateListener(new LoginActivity$$ExternalSyntheticLambda11(this, r23, Color.alpha(r23), r4, r5, r6, r7, r8, r32, r10, r11, r12));
        r15.setInterpolator(CubicBezierInterpolator.DEFAULT);
        AnimatorSet r0 = new AnimatorSet();
        r0.setDuration(300);
        r0.playTogether(new Animator[]{r15});
        r0.start();
        return r0;
    L12:
        r92 = 0;
        goto L13
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onDialogDismiss(Dialog r2) {
        if (r2 == this.permissionsDialog) goto L5;
        return;
    L5:
        if (this.permissionsItems.isEmpty() == false) goto L7;
        return;
    L7:
        if (getParentActivity() == null) goto L15;
        getParentActivity().requestPermissions((String[]) this.permissionsItems.toArray(new String[0]), 6);     // Catch: Exception -> L10
        return;
    L16:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        getNotificationCenter().addObserver(this, NotificationCenter.didUpdateConnectionState);
        getNotificationCenter().addObserver(this, NotificationCenter.newSuggestionsAvailable);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.proxySettingsChanged);
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        int r0 = 0;
        int r1 = 0;
    L3:
        SlideView[] r2 = this.views;
        if (r1 >= r2.length) goto L9;
        SlideView r22 = r2[r1];
        if (r22 == null) goto L8;
        r22.onDestroyActivity();
    L8:
        r1 = r1 + 1;
        goto L3
    L9:
        AlertDialog r12 = this.cancelDeleteProgressDialog;
        if (r12 == null) goto L12;
        r12.dismiss();
        this.cancelDeleteProgressDialog = null;
    L12:
        Runnable[] r13 = this.editDoneCallback;
        int r23 = r13.length;
    L13:
        if (r0 >= r23) goto L18;
        Runnable r3 = r13[r0];
        if (r3 == null) goto L17;
        AndroidUtilities.cancelRunOnUIThread(r3);
    L17:
        r0 = r0 + 1;
        goto L13
    L18:
        ItemOptions r02 = this.loginOptionsMenu;
        if (r02 == null) goto L21;
        r02.dismiss();
    L21:
        clearLoginOptionsMenuReferences();
        getNotificationCenter().removeObserver(this, NotificationCenter.didUpdateConnectionState);
        getNotificationCenter().removeObserver(this, NotificationCenter.newSuggestionsAvailable);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.proxySettingsChanged);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onPause() {
        super.onPause();
        if (this.newAccount == false) goto L5;
        ConnectionsManager.getInstance(this.currentAccount).setAppPaused(true, false);
    L5:
        AndroidUtilities.removeAltFocusable(getParentActivity(), this.classGuid);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onRequestPermissionsResultFragment(int r3, String[] r4, int[] r5) {
        if (r4.length != 0) goto L5;
        return;
    L5:
        if (r5.length != 0) goto L8;
        return;
    L8:
        if (r5[0] != 0) goto L10;
        boolean r52 = true;
    L12:
        if (r3 != 6) goto L18;
        this.checkPermissions = false;
        int r32 = this.currentViewNum;
        if (r32 != 0) goto L28;
        PhoneView.m16003$$Nest$fputconfirmedNumber((PhoneView) this.views[r32], true);
        this.views[this.currentViewNum].onNextPressed(null);
        return;
    L28:
        return;
    L18:
        if (r3 != 20) goto L23;
        if (r52 == false) goto L29;
        LoginActivityRegisterView.m15915$$Nest$fgetimageUpdater((LoginActivityRegisterView) this.views[5]).openCamera();
        return;
    L29:
        return;
    L23:
        if (r3 != 151) goto L30;
        if (r52 == false) goto L31;
        LoginActivityRegisterView r2 = (LoginActivityRegisterView) this.views[5];
        r2.post(new LoginActivity$$ExternalSyntheticLambda7(r2));
        return;
    L31:
        return;
    L30:
        return;
    L10:
        r52 = false;
        goto L12
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        if (this.newAccount == false) goto L5;
        ConnectionsManager.getInstance(this.currentAccount).setAppPaused(false, false);
    L5:
        AndroidUtilities.requestAdjustResize(getParentActivity(), this.classGuid);
        View r0 = this.fragmentView;
        if (r0 == null) goto L36;
        r0.requestLayout();
    L36:
        int r02 = this.currentViewNum;     // Catch: Exception -> L20
        if (r02 >= 1) goto L11;
    L22:
        int r03 = this.currentViewNum;
        if (r03 != 0) goto L28;
        SlideView r04 = this.views[r03];
        if (r04 == null) goto L28;
        r04.onShow();
    L28:
        if (isCustomKeyboardVisible() == false) goto L30;
        AndroidUtilities.hideKeyboard(this.fragmentView);
        AndroidUtilities.requestAltFocusable(getParentActivity(), this.classGuid);
    L30:
        int r05 = this.currentViewNum;
        if (r05 < 0) goto L38;
        SlideView[] r7 = this.views;
        if (r05 >= r7.length) goto L39;
        r7[r05].onResume();
        return;
    L39:
        return;
    L38:
        return;
    L11:
        if (r02 > 4) goto L22;
        SlideView r06 = this.views[r02];     // Catch: Exception -> L20
        if ((r06 instanceof LoginActivitySmsView) == false) goto L22;
        int r07 = LoginActivitySmsView.m15944$$Nest$fgetopenTime((LoginActivitySmsView) r06);     // Catch: Exception -> L20
        if (r07 == 0) goto L22;
        if (Math.abs((System.currentTimeMillis() / 1000) - r07) < 86400) goto L22;
        this.views[this.currentViewNum].onBackPressed(true);     // Catch: Exception -> L20
        setPage(0, false, null, true);     // Catch: Exception -> L20
    L20:
        e = move-exception;
        FileLog.e(e);
        goto L22
    }

    public void open(String r6, TLRPC.auth_SentCode r7) {
        this.paid = true;
        Bundle r1 = new Bundle();
        r1.putString("phone", "+" + r6);
        r1.putString("ephone", "+" + r6);
        r1.putString("phoneFormated", r6);
        fillNextCodeParams(r1, r7, true);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void saveSelfArgs(Bundle r6) {
        Bundle r62 = new Bundle();     // Catch: Exception -> L15
        r62.putInt("currentViewNum", this.currentViewNum);     // Catch: Exception -> L15
        r62.putInt("syncContacts", this.syncContacts ? 1 : 0);     // Catch: Exception -> L15
        int r1 = 0;
    L4:
        if (r1 > this.currentViewNum) goto L9;
        SlideView r2 = this.views[r1];     // Catch: Exception -> L15
        if (r2 == null) goto L8;
        r2.saveStateParams(r62);     // Catch: Exception -> L15
    L8:
        r1 = r1 + 1;     // Catch: Exception -> L15
        goto L4
    L9:
        Context r12 = ApplicationLoader.applicationContext;     // Catch: Exception -> L15
        StringBuilder r22 = new StringBuilder();     // Catch: Exception -> L15
        r22.append("logininfo2");     // Catch: Exception -> L15
        if (this.newAccount == false) goto L12;
        String r3 = "_" + this.currentAccount;     // Catch: Exception -> L15
    L13:
        r22.append(r3);     // Catch: Exception -> L15
        SharedPreferences.Editor r0 = r12.getSharedPreferences(r22.toString(), 0).edit();     // Catch: Exception -> L15
        r0.clear();     // Catch: Exception -> L15
        putBundleToEditor(r62, r0, null);     // Catch: Exception -> L15
        r0.apply();     // Catch: Exception -> L15
        return;
    L12:
        r3 = _UrlKt.FRAGMENT_ENCODE_SET;
    L15:
        e = move-exception;
        FileLog.e(e);
    }

    public LoginActivity setIntroView(View r1, TextView r2) {
        return this;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void setNavigationBarColor(int r1) {
        super.setNavigationBarColor(getNavigationBarColor());
    }

    public void setPage(int r7, boolean r8, Bundle r9, boolean r10) {
        if (r7 != 0) goto L5;
    L20:
        boolean r2 = true;
    L22:
        if (r7 != this.currentViewNum) goto L24;
        r8 = false;
    L24:
        int r3 = 8;
        if (r2 == false) goto L31;
        if (r7 != 0) goto L28;
        this.checkPermissions = true;
    L28:
        this.currentDoneType = 1;
        showDoneButton(false, r8);
        showEditDoneProgress(false, r8);
        this.currentDoneType = 0;
        showEditDoneProgress(false, r8);
        if (r8 == true) goto L34;
        showDoneButton(true, false);
    L34:
        if (r8 == false) goto L59;
        SlideView[] r82 = this.views;
        SlideView r4 = r82[this.currentViewNum];
        SlideView r83 = r82[r7];
        this.currentViewNum = r7;
        ImageView r72 = this.backButtonView;
        if (r83.needBackButton() == false) goto L38;
    L41:
        int r5 = 0;
    L42:
        r72.setVisibility(r5);
        ImageView r73 = this.proxyButtonView;
        if (this.currentViewNum != 0) goto L45;
        r3 = 0;
    L45:
        r73.setVisibility(r3);
        r83.setParams(r9, false);
        setParentActivityTitle(r83.getHeaderName());
        r83.onShow();
        int r74 = AndroidUtilities.displaySize.x;
        if (r10 == false) goto L48;
        r74 = -r74;
    L48:
        r83.setX(r74);
        r83.setVisibility(0);
        if (this.currentDoneType != 0) goto L52;
        if (r2 == false) goto L52;
        showDoneButton(true, true);
    L52:
        AnimatorSet r75 = new AnimatorSet();
        r75.addListener(new AnonymousClass10(this, r2, r4));
        Property r92 = View.TRANSLATION_X;
        if (r10 == false) goto L56;
        int r102 = AndroidUtilities.displaySize.x;
    L57:
        r75.playTogether(new Animator[]{ObjectAnimator.ofFloat(r4, r92, new float[]{r102}), ObjectAnimator.ofFloat(r83, r92, new float[]{0.0f})});
        r75.setDuration(300);
        r75.setInterpolator(new AccelerateDecelerateInterpolator());
        r75.start();
        setCustomKeyboardVisible(r83.hasCustomKeyboard(), true);
        return;
    L56:
        r102 = -AndroidUtilities.displaySize.x;
        goto L57
    L38:
        if (this.newAccount == true) goto L41;
        r5 = 8;
        goto L42
    L59:
        ImageView r84 = this.backButtonView;
        if (this.views[r7].needBackButton() == false) goto L62;
    L65:
        int r103 = 0;
    L66:
        r84.setVisibility(r103);
        ImageView r85 = this.proxyButtonView;
        if (r7 != 0) goto L69;
        int r104 = 0;
    L70:
        r85.setVisibility(r104);
        this.views[this.currentViewNum].setVisibility(8);
        this.views[this.currentViewNum].onHide();
        this.currentViewNum = r7;
        this.views[r7].setParams(r9, false);
        this.views[r7].setVisibility(0);
        setParentActivityTitle(this.views[r7].getHeaderName());
        this.views[r7].onShow();
        setCustomKeyboardVisible(this.views[r7].hasCustomKeyboard(), false);
        return;
    L69:
        r104 = 8;
        goto L70
    L62:
        if (this.newAccount == true) goto L65;
        r103 = 8;
        goto L66
    L31:
        this.currentDoneType = 0;
        showDoneButton(false, r8);
        showEditDoneProgress(false, r8);
        if (r7 == 8) goto L34;
        this.currentDoneType = 1;
        goto L34
    L5:
        if (r7 == 5) goto L20;
        if (r7 == 6) goto L20;
        if (r7 == 9) goto L20;
        if (r7 == 10) goto L20;
        if (r7 == 12) goto L20;
        if (r7 == 17) goto L20;
        if (r7 == 16) goto L20;
        r2 = false;
        goto L22
    }
}
