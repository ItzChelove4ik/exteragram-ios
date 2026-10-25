package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.Keep;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Locale;
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
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.EditTextSettingsCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.TextSettingsCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.EmptyTextProgressView;
import org.telegram.ui.Components.FragmentFloatingButton;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.OutlineTextContainerView;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Components.RadialProgressView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.TransformableLoginButtonView;
import org.telegram.ui.Components.VerticalPositionAutoAnimator;

/* loaded from: classes3.dex */
public class TwoStepVerificationActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private SimpleTextView bottomButton;
    private TextView bottomTextView;
    private TextView cancelResetButton;

    @Keep
    private int changePasswordRow;
    private int changeRecoveryEmailRow;
    private TL_account.Password currentPassword;
    private byte[] currentPasswordHash;
    private byte[] currentSecret;
    private long currentSecretId;
    private TwoStepVerificationActivityDelegate delegate;
    private int delegateType;
    private String delegateTypeString;
    private boolean destroyed;
    private int emailRow;
    private EmptyTextProgressView emptyView;
    private Runnable errorColorTimeout;
    private FragmentFloatingButton floatingButton;
    private TransformableLoginButtonView floatingButtonIcon;
    private boolean forgotPasswordOnShow;
    private ListAdapter listAdapter;
    private RecyclerListView listView;
    private boolean loading;
    private RLottieImageView lockImageView;
    int otherwiseReloginDays;
    private EditTextBoldCursor passwordEditText;
    private int passwordEnabledDetailRow;
    private boolean passwordEntered;
    private OutlineTextContainerView passwordOutlineView;
    private boolean paused;
    private boolean postedErrorColorTimeout;
    public boolean preloaded;
    private AlertDialog progressDialog;
    private RadialProgressView radialProgressView;
    private boolean resetPasswordOnShow;
    private TextView resetWaitView;
    private int rowCount;
    private ScrollView scrollView;
    private int setPasswordDetailRow;
    private int setPasswordRow;
    private int setRecoveryEmailRow;
    private TextView subtitleTextView;
    private TextView titleTextView;

    @Keep
    private int turnPasswordOffRow;
    private Runnable updateTimeRunnable;

    /* renamed from: org.telegram.ui.TwoStepVerificationActivity$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ TwoStepVerificationActivity this$0;

        public AnonymousClass1(TwoStepVerificationActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L10;
            TwoStepVerificationActivity r1 = this.this$0;
            if (r1.otherwiseReloginDays < 0) goto L8;
            TwoStepVerificationActivity.m21043$$Nest$mshowSetForcePasswordAlert(r1);
            return;
        L8:
            r1.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.TwoStepVerificationActivity$2, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass2 implements TextWatcher {
        final /* synthetic */ TwoStepVerificationActivity this$0;

        public AnonymousClass2(TwoStepVerificationActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r1) {
            if (TwoStepVerificationActivity.m21036$$Nest$fgetpostedErrorColorTimeout(this.this$0) == false) goto L6;
            AndroidUtilities.cancelRunOnUIThread(TwoStepVerificationActivity.m21033$$Nest$fgeterrorColorTimeout(this.this$0));
            TwoStepVerificationActivity.m21033$$Nest$fgeterrorColorTimeout(this.this$0).run();
            return;
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.TwoStepVerificationActivity$3, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass3 extends RadialProgressView {
        final /* synthetic */ TwoStepVerificationActivity this$0;

        public AnonymousClass3(TwoStepVerificationActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, r2);
            ViewGroup.MarginLayoutParams r0 = (ViewGroup.MarginLayoutParams) getLayoutParams();
            r0.topMargin = AndroidUtilities.statusBarHeight / 2;
        }
    }

    /* renamed from: org.telegram.ui.TwoStepVerificationActivity$4, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass4 extends TwoStepVerificationSetupActivity {
        final /* synthetic */ TwoStepVerificationActivity this$0;

        public AnonymousClass4(TwoStepVerificationActivity r1, int r2, int r3, TL_account.Password r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.TwoStepVerificationSetupActivity
        public void onReset() {
            TwoStepVerificationActivity.m21042$$Nest$fputresetPasswordOnShow(this.this$0, true);
        }
    }

    /* loaded from: classes6.dex */
    public class ListAdapter extends RecyclerListView.SelectionAdapter {
        private Context mContext;
        final /* synthetic */ TwoStepVerificationActivity this$0;

        public ListAdapter(TwoStepVerificationActivity r1, Context r2) {
            this.this$0 = r1;
            this.mContext = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            if (TwoStepVerificationActivity.m21034$$Nest$fgetloading(this.this$0) == false) goto L5;
            return 0;
        L5:
            if (TwoStepVerificationActivity.m21032$$Nest$fgetcurrentPassword(this.this$0) != null) goto L8;
            return 0;
        L8:
            return TwoStepVerificationActivity.m21037$$Nest$fgetrowCount(this.this$0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r2) {
            if (r2 != TwoStepVerificationActivity.m21038$$Nest$fgetsetPasswordDetailRow(this.this$0)) goto L5;
            return 1;
        L5:
            if (r2 == TwoStepVerificationActivity.m21035$$Nest$fgetpasswordEnabledDetailRow(this.this$0)) goto L11;
            return 0;
        L11:
            return 1;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            if (r1.getItemViewType() != 0) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r4, int r5) {
            int r0 = r4.getItemViewType();
            if (r0 == 0) goto L14;
            if (r0 != 1) goto L36;
            TextInfoPrivacyCell r42 = (TextInfoPrivacyCell) r4.itemView;
            if (r5 != TwoStepVerificationActivity.m21038$$Nest$fgetsetPasswordDetailRow(this.this$0)) goto L11;
            r42.setText(LocaleController.getString(R.string.SetAdditionalPasswordInfo));
            return;
        L11:
            if (r5 != TwoStepVerificationActivity.m21035$$Nest$fgetpasswordEnabledDetailRow(this.this$0)) goto L34;
            r42.setText(LocaleController.getString(R.string.EnabledPasswordText));
            return;
        L34:
            return;
        L36:
            return;
        L14:
            TextSettingsCell r43 = (TextSettingsCell) r4.itemView;
            int r02 = Theme.key_windowBackgroundWhiteBlackText;
            r43.setTag(Integer.valueOf(r02));
            r43.setTextColor(Theme.getColor(r02));
            if (r5 != TwoStepVerificationActivity.m21030$$Nest$fgetchangePasswordRow(this.this$0)) goto L19;
            r43.setText(LocaleController.getString(R.string.ChangePassword), true);
            return;
        L19:
            if (r5 != TwoStepVerificationActivity.m21039$$Nest$fgetsetPasswordRow(this.this$0)) goto L23;
            r43.setText(LocaleController.getString(R.string.SetAdditionalPassword), true);
            return;
        L23:
            if (r5 != TwoStepVerificationActivity.m21041$$Nest$fgetturnPasswordOffRow(this.this$0)) goto L27;
            r43.setText(LocaleController.getString(R.string.TurnPasswordOff), true);
            return;
        L27:
            if (r5 != TwoStepVerificationActivity.m21031$$Nest$fgetchangeRecoveryEmailRow(this.this$0)) goto L31;
            r43.setText(LocaleController.getString(R.string.ChangeRecoveryEmail), false);
            return;
        L31:
            if (r5 != TwoStepVerificationActivity.m21040$$Nest$fgetsetRecoveryEmailRow(this.this$0)) goto L35;
            r43.setText(LocaleController.getString(R.string.SetRecoveryEmail), false);
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r1, int r2) {
            if (r2 == 0) goto L4;
            View r12 = new TextInfoPrivacyCell(this.mContext);
        L6:
            return new RecyclerListView.Holder(r12);
        L4:
            r12 = new TextSettingsCell(this.mContext);
            r12.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            goto L6
        }
    }

    /* loaded from: classes6.dex */
    public interface TwoStepVerificationActivityDelegate {
        void didEnterPassword(TLRPC.InputCheckPasswordSRP r1);
    }

    /* renamed from: $r8$lambda$05M-cWM_mTbhTxgUkM8FwkXfpEA, reason: not valid java name */
    public static /* synthetic */ void m21013$r8$lambda$05McWM_mTbhTxgUkM8FwkXfpEA(TwoStepVerificationActivity r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$clearPassword$21(r1, r2);
    }

    /* renamed from: $r8$lambda$29QntmsMOxh5Ly-wt-Nx7IIzG9w, reason: not valid java name */
    public static /* synthetic */ void m21014$r8$lambda$29QntmsMOxh5LywtNx7IIzG9w(TwoStepVerificationActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$clearPassword$26(r1, r2);
    }

    /* renamed from: $r8$lambda$2wS-rIaNpzQ5KseWc0zu3kPDSrY, reason: not valid java name */
    public static /* synthetic */ void m21015$r8$lambda$2wSrIaNpzQ5KseWc0zu3kPDSrY(TwoStepVerificationActivity r0, AlertDialog r1, int r2) {
        r0.lambda$onPasswordForgot$17(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$56wC7vP43ye7GoTefyhD6FvFm3E(TwoStepVerificationActivity r0, byte[] r1, TLObject r2, byte[] r3) {
        r0.lambda$processDone$30(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$5jFR34AMJOSiuqr9tUNaqDqTUSU(TwoStepVerificationActivity r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$onPasswordForgot$14(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$61AgCsqz7u2vlfJNBm2x90KNFCU(TwoStepVerificationActivity r0, AlertDialog r1, int r2) {
        r0.lambda$cancelPasswordReset$10(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$BPHiAeLZUZecGCSMBnzxokcYWPs(TwoStepVerificationActivity r0, TLObject r1) {
        r0.lambda$cancelPasswordReset$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$CawvajneF80TGmE3cvNo9lU9i9Y(TwoStepVerificationActivity r0, View r1) {
        r0.lambda$createView$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$EyZs2Vj77WIiBXIn2wQ6SmDpO2I(TLObject r0, TLRPC.TL_error r1) {
    }

    /* renamed from: $r8$lambda$F4nded9bVxcvjBj4ZS_6-hFA2E0, reason: not valid java name */
    public static /* synthetic */ void m21016$r8$lambda$F4nded9bVxcvjBj4ZS_6hFA2E0(TwoStepVerificationActivity r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$processDone$31(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$FP3hb4vT_eEm8us6R6FV_pzmEWk(TwoStepVerificationActivity r0) {
        r0.updateBottomButton();
    }

    /* renamed from: $r8$lambda$Hpm0RW_P4I9-2o2NPnwjG3GtyKI, reason: not valid java name */
    public static /* synthetic */ void m21017$r8$lambda$Hpm0RW_P4I92o2NPnwjG3GtyKI(TwoStepVerificationActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$resetPassword$13(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$NpdaAxDw6tXZjeTsCNqyRzqbcSs(TwoStepVerificationActivity r0, AlertDialog r1, int r2) {
        r0.lambda$showSetForcePasswordAlert$37(r1, r2);
    }

    /* renamed from: $r8$lambda$OOPyyiEH9tIA6GjpIHHU-4QWBlI, reason: not valid java name */
    public static /* synthetic */ void m21018$r8$lambda$OOPyyiEH9tIA6GjpIHHU4QWBlI(TwoStepVerificationActivity r0, byte[] r1, byte[] r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$processDone$34(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$QUW7UJKfONaZnQm4AwycD6YoJO4(TwoStepVerificationActivity r0, TL_account.updatePasswordSettings r1) {
        r0.lambda$clearPassword$27(r1);
    }

    /* renamed from: $r8$lambda$UP0Jj-zmZGjeHUbuj5af-UHaJ2k, reason: not valid java name */
    public static /* synthetic */ void m21019$r8$lambda$UP0JjzmZGjeHUbuj5afUHaJ2k(TwoStepVerificationActivity r0) {
        r0.lambda$updateRows$20();
    }

    public static /* synthetic */ void $r8$lambda$VNyafuXFnkw7jrABE3GMOjtBBsc(TwoStepVerificationActivity r0, View r1) {
        r0.lambda$createView$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$WOxaS6jFPvPXt4Bju4kl3ag654g(TwoStepVerificationActivity r0, DialogInterface r1) {
        r0.lambda$resetPassword$11(r1);
    }

    public static /* synthetic */ void $r8$lambda$aBlxd8Pys1LaH6CLMxMM8JQoLtc(TwoStepVerificationActivity r0, AlertDialog r1, int r2) {
        r0.lambda$onPasswordForgot$16(r1, r2);
    }

    /* renamed from: $r8$lambda$b_ZfzRuI-BG2w0nuPRzu0aqKVjY, reason: not valid java name */
    public static /* synthetic */ void m21020$r8$lambda$b_ZfzRuIBG2w0nuPRzu0aqKVjY(TwoStepVerificationActivity r0, View r1, int r2) {
        r0.lambda$createView$7(r1, r2);
    }

    /* renamed from: $r8$lambda$gYjt_F-t3iO1aW8QA26-sUNmEOQ, reason: not valid java name */
    public static /* synthetic */ void m21021$r8$lambda$gYjt_Ft3iO1aW8QA26sUNmEOQ(TwoStepVerificationActivity r0) {
        r0.lambda$onFieldError$36();
    }

    public static /* synthetic */ void $r8$lambda$hulGCv5hjq0vna9tdn3fcUnI9zo(TwoStepVerificationActivity r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$clearPassword$23(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$iJuqkRH0wA9kgfzdReHlMNTlN0M(TwoStepVerificationActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$clearPassword$24(r1, r2);
    }

    /* renamed from: $r8$lambda$kKOguxE0rG_xjTXb2l-PPbfx3TQ, reason: not valid java name */
    public static /* synthetic */ void m21022$r8$lambda$kKOguxE0rG_xjTXb2lPPbfx3TQ(TwoStepVerificationActivity r0, View r1, boolean r2) {
        r0.lambda$createView$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$kfCUPfjwyEcq7ybbwPL_UtGY4EM(TwoStepVerificationActivity r0, TLObject r1) {
        r0.lambda$resetPassword$12(r1);
    }

    public static /* synthetic */ void $r8$lambda$lBDTXUqfBbtoZwsmE4WGAuUh0fA(TwoStepVerificationActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$cancelPasswordReset$9(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$lIbTYgbVnxRRXGs_FKcmQpJgBCI(TwoStepVerificationActivity r0, TLRPC.TL_error r1) {
        r0.lambda$processDone$33(r1);
    }

    /* renamed from: $r8$lambda$olkYoJIeepEgC6XXVIh1q0-rpl4, reason: not valid java name */
    public static /* synthetic */ void m21023$r8$lambda$olkYoJIeepEgC6XXVIh1q0rpl4(TwoStepVerificationActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$clearPassword$22(r1, r2);
    }

    /* renamed from: $r8$lambda$qo31uosFv-3sRMWMA7xVDuqqGWA, reason: not valid java name */
    public static /* synthetic */ void m21024$r8$lambda$qo31uosFv3sRMWMA7xVDuqqGWA(TwoStepVerificationActivity r0, View r1) {
        r0.lambda$createView$5(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$reFLk8AtyGztayai5IVgNYtc7iw(TwoStepVerificationActivity r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$createView$2(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$sEqI_AnUGp-Zcikq_d3MsVLvFNI, reason: not valid java name */
    public static /* synthetic */ void m21025$r8$lambda$sEqI_AnUGpZcikq_d3MsVLvFNI(TwoStepVerificationActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$processDone$32(r1, r2);
    }

    /* renamed from: $r8$lambda$ui5HZs5-dBqbEfz9qo418R2qv20, reason: not valid java name */
    public static /* synthetic */ void m21026$r8$lambda$ui5HZs5dBqbEfz9qo418R2qv20(TwoStepVerificationActivity r0) {
        r0.lambda$new$0();
    }

    public static /* synthetic */ void $r8$lambda$vSQ96k2XwO42rYL9oTCzF33AaSI(TwoStepVerificationActivity r0, AlertDialog r1, int r2) {
        r0.lambda$createView$6(r1, r2);
    }

    /* renamed from: $r8$lambda$vkz0iUv3SOBGdYuBu-TPN6_XXns, reason: not valid java name */
    public static /* synthetic */ void m21027$r8$lambda$vkz0iUv3SOBGdYuBuTPN6_XXns(TwoStepVerificationActivity r0, TLRPC.TL_error r1, TLObject r2, boolean r3, boolean r4, Runnable r5) {
        r0.lambda$loadPasswordInfo$18(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$w8rhMKVNmpkhhcVgcSjPS8RLSOM(TwoStepVerificationActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$onPasswordForgot$15(r1, r2);
    }

    /* renamed from: $r8$lambda$wg-2fx5EeaUNrvo3jXzCQFElDog, reason: not valid java name */
    public static /* synthetic */ void m21028$r8$lambda$wg2fx5EeaUNrvo3jXzCQFElDog(TwoStepVerificationActivity r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$clearPassword$25(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$woEy9Phiv2cHLxSLnx5NXRW9YmA(TwoStepVerificationActivity r0, boolean r1, byte[] r2) {
        r0.lambda$processDone$29(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$xzyZGQaKGeQRSDtHz0ARNWw4EmE(TwoStepVerificationActivity r0, byte[] r1) {
        r0.lambda$processDone$35(r1);
    }

    /* renamed from: $r8$lambda$z4Yi0zTrpcb-j-wdpWdAbzq88f0, reason: not valid java name */
    public static /* synthetic */ void m21029$r8$lambda$z4Yi0zTrpcbjwdpWdAbzq88f0(TwoStepVerificationActivity r0, boolean r1, boolean r2, Runnable r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$loadPasswordInfo$19(r1, r2, r3, r4, r5);
    }

    /* renamed from: -$$Nest$fgetchangePasswordRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21030$$Nest$fgetchangePasswordRow(TwoStepVerificationActivity r0) {
        return r0.changePasswordRow;
    }

    /* renamed from: -$$Nest$fgetchangeRecoveryEmailRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21031$$Nest$fgetchangeRecoveryEmailRow(TwoStepVerificationActivity r0) {
        return r0.changeRecoveryEmailRow;
    }

    /* renamed from: -$$Nest$fgetcurrentPassword, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TL_account.Password m21032$$Nest$fgetcurrentPassword(TwoStepVerificationActivity r0) {
        return r0.currentPassword;
    }

    /* renamed from: -$$Nest$fgeterrorColorTimeout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m21033$$Nest$fgeterrorColorTimeout(TwoStepVerificationActivity r0) {
        return r0.errorColorTimeout;
    }

    /* renamed from: -$$Nest$fgetloading, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21034$$Nest$fgetloading(TwoStepVerificationActivity r0) {
        return r0.loading;
    }

    /* renamed from: -$$Nest$fgetpasswordEnabledDetailRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21035$$Nest$fgetpasswordEnabledDetailRow(TwoStepVerificationActivity r0) {
        return r0.passwordEnabledDetailRow;
    }

    /* renamed from: -$$Nest$fgetpostedErrorColorTimeout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21036$$Nest$fgetpostedErrorColorTimeout(TwoStepVerificationActivity r0) {
        return r0.postedErrorColorTimeout;
    }

    /* renamed from: -$$Nest$fgetrowCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21037$$Nest$fgetrowCount(TwoStepVerificationActivity r0) {
        return r0.rowCount;
    }

    /* renamed from: -$$Nest$fgetsetPasswordDetailRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21038$$Nest$fgetsetPasswordDetailRow(TwoStepVerificationActivity r0) {
        return r0.setPasswordDetailRow;
    }

    /* renamed from: -$$Nest$fgetsetPasswordRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21039$$Nest$fgetsetPasswordRow(TwoStepVerificationActivity r0) {
        return r0.setPasswordRow;
    }

    /* renamed from: -$$Nest$fgetsetRecoveryEmailRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21040$$Nest$fgetsetRecoveryEmailRow(TwoStepVerificationActivity r0) {
        return r0.setRecoveryEmailRow;
    }

    /* renamed from: -$$Nest$fgetturnPasswordOffRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21041$$Nest$fgetturnPasswordOffRow(TwoStepVerificationActivity r0) {
        return r0.turnPasswordOffRow;
    }

    /* renamed from: -$$Nest$fputresetPasswordOnShow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21042$$Nest$fputresetPasswordOnShow(TwoStepVerificationActivity r0, boolean r1) {
        r0.resetPasswordOnShow = r1;
    }

    /* renamed from: -$$Nest$mshowSetForcePasswordAlert, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21043$$Nest$mshowSetForcePasswordAlert(TwoStepVerificationActivity r0) {
        r0.showSetForcePasswordAlert();
    }

    public TwoStepVerificationActivity() {
        this.passwordEntered = true;
        this.currentPasswordHash = new byte[0];
        this.errorColorTimeout = new TwoStepVerificationActivity$$ExternalSyntheticLambda17(this);
        this.otherwiseReloginDays = -1;
        this.updateTimeRunnable = new TwoStepVerificationActivity$$ExternalSyntheticLambda18(this);
    }

    public static boolean canHandleCurrentPassword(TL_account.Password r1, boolean r2) {
        if (r2 == false) goto L8;
        if ((r1.current_algo instanceof TLRPC.TL_passwordKdfAlgoUnknown) == false) goto L14;
        return false;
    L14:
        return true;
    L8:
        if ((r1.new_algo instanceof TLRPC.TL_passwordKdfAlgoUnknown) == false) goto L10;
    L16:
        return false;
    L10:
        if ((r1.current_algo instanceof TLRPC.TL_passwordKdfAlgoUnknown) == true) goto L16;
        if ((r1.new_secure_algo instanceof TLRPC.TL_securePasswordKdfAlgoUnknown) == true) goto L16;
        return true;
    }

    private void cancelPasswordReset() {
        if (getParentActivity() != null) goto L5;
        return;
    L5:
        AlertDialog.Builder r0 = new AlertDialog.Builder(getParentActivity());
        r0.setPositiveButton(LocaleController.getString(R.string.CancelPasswordResetYes), new TwoStepVerificationActivity$$ExternalSyntheticLambda15(this));
        r0.setNegativeButton(LocaleController.getString(R.string.CancelPasswordResetNo), null);
        r0.setTitle(LocaleController.getString(R.string.CancelReset));
        r0.setMessage(LocaleController.getString(R.string.CancelPasswordReset));
        showDialog(r0.create());
    }

    private boolean checkSecretValues(byte[] r14, TL_account.passwordSettings r15) {
        TLRPC.TL_secureSecretSettings r0 = r15.secure_settings;
        if (r0 == null) goto L14;
        this.currentSecret = r0.secure_secret;
        TLRPC.SecurePasswordKdfAlgo r02 = r0.secure_algo;
        if ((r02 instanceof TLRPC.TL_securePasswordKdfAlgoPBKDF2HMACSHA512iter100000) == false) goto L8;
        byte[] r142 = Utilities.computePBKDF2(r14, ((TLRPC.TL_securePasswordKdfAlgoPBKDF2HMACSHA512iter100000) r02).salt);
    L10:
        this.currentSecretId = r15.secure_settings.secure_secret_id;
        byte[] r7 = new byte[32];
        System.arraycopy(r142, 0, r7, 0, 32);
        byte[] r8 = new byte[16];
        System.arraycopy(r142, 32, r8, 0, 16);
        byte[] r6 = this.currentSecret;
        Utilities.aesCbcEncryptionByteArraySafe(r6, r7, r8, 0, r6.length, 0, 0);
        TLRPC.TL_secureSecretSettings r143 = r15.secure_settings;
        if (PassportActivity.checkSecret(r143.secure_secret, Long.valueOf(r143.secure_secret_id)) == true) goto L17;
        TL_account.updatePasswordSettings r144 = new TL_account.updatePasswordSettings();
        r144.password = getNewSrpPassword();
        TL_account.passwordInputSettings r152 = new TL_account.passwordInputSettings();
        r144.new_settings = r152;
        r152.new_secure_settings = new TLRPC.TL_secureSecretSettings();
        TLRPC.TL_secureSecretSettings r153 = r144.new_settings.new_secure_settings;
        r153.secure_secret = new byte[0];
        r153.secure_algo = new TLRPC.TL_securePasswordKdfAlgoUnknown();
        TL_account.passwordInputSettings r154 = r144.new_settings;
        r154.new_secure_settings.secure_secret_id = 0;
        r154.flags |= 4;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r144, new TwoStepVerificationActivity$$ExternalSyntheticLambda37());
        this.currentSecret = null;
        this.currentSecretId = 0;
        return true;
    L17:
        return true;
    L8:
        if ((r02 instanceof TLRPC.TL_securePasswordKdfAlgoSHA512) == false) goto L13;
        byte[] r03 = ((TLRPC.TL_securePasswordKdfAlgoSHA512) r02).salt;
        r142 = Utilities.computeSHA512(r03, r14, r03);
        goto L10
    L13:
        return false;
    L14:
        this.currentSecret = null;
        this.currentSecretId = 0;
        return true;
    }

    private void clearPassword() {
        TL_account.updatePasswordSettings r0 = new TL_account.updatePasswordSettings();
        byte[] r1 = this.currentPasswordHash;
        if (r1 != null) goto L5;
    L6:
        r0.password = new TLRPC.TL_inputCheckPasswordEmpty();
    L7:
        r0.new_settings = new TL_account.passwordInputSettings();
        UserConfig.getInstance(this.currentAccount).resetSavedPassword();
        this.currentSecret = null;
        TL_account.passwordInputSettings r12 = r0.new_settings;
        r12.flags = 3;
        r12.hint = _UrlKt.FRAGMENT_ENCODE_SET;
        r12.new_password_hash = new byte[0];
        r12.new_algo = new TLRPC.TL_passwordKdfAlgoUnknown();
        r0.new_settings.email = _UrlKt.FRAGMENT_ENCODE_SET;
        needShowProgress();
        Utilities.globalQueue.postRunnable(new TwoStepVerificationActivity$$ExternalSyntheticLambda22(this, r0));
        return;
    L5:
        if (r1.length != 0) goto L7;
        goto L6
    }

    public static void initPasswordNewAlgo(TL_account.Password r5) {
        TLRPC.PasswordKdfAlgo r0 = r5.new_algo;
        if ((r0 instanceof TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow) == false) goto L5;
        TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow r02 = (TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow) r0;
        byte[] r1 = new byte[r02.salt1.length + 32];
        Utilities.random.nextBytes(r1);
        byte[] r3 = r02.salt1;
        System.arraycopy(r3, 0, r1, 0, r3.length);
        r02.salt1 = r1;
    L5:
        TLRPC.SecurePasswordKdfAlgo r52 = r5.new_secure_algo;
        if ((r52 instanceof TLRPC.TL_securePasswordKdfAlgoPBKDF2HMACSHA512iter100000) == false) goto L9;
        TLRPC.TL_securePasswordKdfAlgoPBKDF2HMACSHA512iter100000 r53 = (TLRPC.TL_securePasswordKdfAlgoPBKDF2HMACSHA512iter100000) r52;
        byte[] r03 = new byte[r53.salt.length + 32];
        Utilities.random.nextBytes(r03);
        byte[] r12 = r53.salt;
        System.arraycopy(r12, 0, r03, 0, r12.length);
        r53.salt = r03;
        return;
    }

    private /* synthetic */ void lambda$cancelPasswordReset$10(AlertDialog r2, int r3) {
        TL_account.declinePasswordReset r22 = new TL_account.declinePasswordReset();
        getConnectionsManager().sendRequest(r22, new TwoStepVerificationActivity$$ExternalSyntheticLambda25(this));
    }

    private /* synthetic */ void lambda$cancelPasswordReset$8(TLObject r2) {
        if ((r2 instanceof TLRPC.TL_boolTrue) == false) goto L6;
        this.currentPassword.pending_reset_date = 0;
        updateBottomButton();
        return;
    }

    private /* synthetic */ void lambda$cancelPasswordReset$9(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new TwoStepVerificationActivity$$ExternalSyntheticLambda30(this, r1));
    }

    private /* synthetic */ void lambda$clearPassword$21(TLRPC.TL_error r2, TLObject r3) {
        if (r2 != null) goto L5;
        TL_account.Password r32 = (TL_account.Password) r3;
        this.currentPassword = r32;
        initPasswordNewAlgo(r32);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.didSetOrRemoveTwoStepPassword, new Object[]{this.currentPassword});
        clearPassword();
        return;
    }

    private /* synthetic */ void lambda$clearPassword$22(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new TwoStepVerificationActivity$$ExternalSyntheticLambda31(this, r3, r2));
    }

    private /* synthetic */ void lambda$clearPassword$23(TLRPC.TL_error r2, TLObject r3) {
        if (r2 != null) goto L5;
        TL_account.Password r32 = (TL_account.Password) r3;
        this.currentPassword = r32;
        initPasswordNewAlgo(r32);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.didSetOrRemoveTwoStepPassword, new Object[]{this.currentPassword});
        clearPassword();
        return;
    }

    private /* synthetic */ void lambda$clearPassword$24(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new TwoStepVerificationActivity$$ExternalSyntheticLambda38(this, r3, r2));
    }

    private /* synthetic */ void lambda$clearPassword$25(TLRPC.TL_error r3, TLObject r4) {
        if (r3 != null) goto L4;
    L7:
        needHideProgress();
        if (r3 == null) goto L10;
    L13:
        if (r3 != null) goto L15;
        return;
    L15:
        if (r3.text.startsWith("FLOOD_WAIT") == false) goto L22;
        int r32 = Utilities.parseInt(r3.text);
        if (r32 >= 60) goto L19;
        String r33 = LocaleController.formatPluralString("Seconds", r32, new Object[0]);
    L20:
        showAlertWithText(LocaleController.getString(R.string.AppName), LocaleController.formatString("FloodWaitTime", R.string.FloodWaitTime, new Object[]{r33}));
        return;
    L19:
        r33 = LocaleController.formatPluralString("Minutes", r32 / 60, new Object[0]);
        goto L20
    L22:
        showAlertWithText(LocaleController.getString(R.string.AppName), r3.text);
        return;
    L10:
        if ((r4 instanceof TLRPC.TL_boolTrue) == false) goto L13;
        this.currentPassword = null;
        this.currentPasswordHash = new byte[0];
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.didRemoveTwoStepPassword, new Object[0]);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.didSetOrRemoveTwoStepPassword, new Object[0]);
        finishFragment();
        return;
    L4:
        if ("SRP_ID_INVALID".equals(r3.text) == false) goto L7;
        TL_account.getPassword r34 = new TL_account.getPassword();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r34, new TwoStepVerificationActivity$$ExternalSyntheticLambda35(this), 8);
    }

    private /* synthetic */ void lambda$clearPassword$26(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new TwoStepVerificationActivity$$ExternalSyntheticLambda34(this, r3, r2));
    }

    private /* synthetic */ void lambda$clearPassword$27(TL_account.updatePasswordSettings r3) {
        if (r3.password == null) goto L5;
    L9:
        TwoStepVerificationActivity$$ExternalSyntheticLambda29 r0 = new TwoStepVerificationActivity$$ExternalSyntheticLambda29(this);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r3, r0, 10);
        return;
    L5:
        if (this.currentPassword.current_algo != null) goto L8;
        TL_account.getPassword r32 = new TL_account.getPassword();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r32, new TwoStepVerificationActivity$$ExternalSyntheticLambda28(this), 8);
        return;
    L8:
        r3.password = getNewSrpPassword();
        goto L9
    }

    private /* synthetic */ void lambda$createView$1(View r1, boolean r2) {
        OutlineTextContainerView r0 = this.passwordOutlineView;
        if (r2 == false) goto L5;
        float r12 = 1.0f;
    L6:
        r0.animateSelection(r12);
        return;
    L5:
        r12 = 0.0f;
        goto L6
    }

    private /* synthetic */ boolean lambda$createView$2(TextView r1, int r2, KeyEvent r3) {
        if (r2 != 5) goto L5;
    L9:
        processDone();
        return true;
    L5:
        if (r2 == 6) goto L9;
        return false;
    }

    private /* synthetic */ void lambda$createView$3(View r1) {
        onPasswordForgot();
    }

    private /* synthetic */ void lambda$createView$4(View r1) {
        cancelPasswordReset();
    }

    private /* synthetic */ void lambda$createView$5(View r1) {
        processDone();
    }

    private /* synthetic */ void lambda$createView$6(AlertDialog r1, int r2) {
        clearPassword();
    }

    private /* synthetic */ void lambda$createView$7(View r8, int r9) {
        if (r9 != this.setPasswordRow) goto L5;
    L23:
        TwoStepVerificationSetupActivity r1 = new TwoStepVerificationSetupActivity(this.currentAccount, 0, this.currentPassword);
        r1.addFragmentToClose(this);
        r1.setCurrentPasswordParams(this.currentPasswordHash, this.currentSecretId, this.currentSecret, false);
        presentFragment(r1);
        return;
    L5:
        if (r9 == this.changePasswordRow) goto L23;
        if (r9 != this.setRecoveryEmailRow) goto L10;
    L21:
        TwoStepVerificationSetupActivity r0 = new TwoStepVerificationSetupActivity(this.currentAccount, 3, this.currentPassword);
        r0.addFragmentToClose(this);
        r0.setCurrentPasswordParams(this.currentPasswordHash, this.currentSecretId, this.currentSecret, true);
        presentFragment(r0);
        return;
    L10:
        if (r9 == this.changeRecoveryEmailRow) goto L21;
        if (r9 != this.turnPasswordOffRow) goto L25;
        AlertDialog.Builder r82 = new AlertDialog.Builder(getParentActivity());
        String r92 = LocaleController.getString(R.string.TurnPasswordOffQuestion);
        if (this.currentPassword.has_secure_values == false) goto L17;
        r92 = r92 + "\n\n" + LocaleController.getString(R.string.TurnPasswordOffPassport);
    L17:
        String r02 = LocaleController.getString(R.string.TurnPasswordOffQuestionTitle);
        String r12 = LocaleController.getString(R.string.Disable);
        r82.setMessage(r92);
        r82.setTitle(r02);
        r82.setPositiveButton(r12, new TwoStepVerificationActivity$$ExternalSyntheticLambda16(this));
        r82.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        AlertDialog r83 = r82.create();
        showDialog(r83);
        TextView r7 = (TextView) r83.getButton(-1);
        if (r7 == null) goto L26;
        r7.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    L26:
        return;
    }

    private /* synthetic */ void lambda$loadPasswordInfo$18(TLRPC.TL_error r2, TLObject r3, boolean r4, boolean r5, Runnable r6) {
        if (r2 != null) goto L18;
        boolean r22 = false;
        this.loading = false;
        TL_account.Password r32 = (TL_account.Password) r3;
        this.currentPassword = r32;
        if (canHandleCurrentPassword(r32, false) == true) goto L7;
        AlertsCreator.showUpdateAppAlert(getParentActivity(), LocaleController.getString(R.string.UpdateAppAlert), true);
        return;
    L7:
        if (r4 == false) goto L9;
        if (r5 == true) goto L9;
    L17:
        initPasswordNewAlgo(this.currentPassword);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.didSetOrRemoveTwoStepPassword, new Object[]{this.currentPassword});
    L9:
        byte[] r33 = this.currentPasswordHash;
        if (r33 == null) goto L14;
        if (r33.length <= 0) goto L14;
    L15:
        r22 = true;
    L16:
        this.passwordEntered = r22;
    L14:
        if (this.currentPassword.has_password == true) goto L16;
    L18:
        if (r6 == null) goto L20;
        r6.run();
    L20:
        updateRows();
    }

    private /* synthetic */ void lambda$loadPasswordInfo$19(boolean r8, boolean r9, Runnable r10, TLObject r11, TLRPC.TL_error r12) {
        AndroidUtilities.runOnUIThread(new TwoStepVerificationActivity$$ExternalSyntheticLambda20(this, r12, r11, r8, r9, r10));
    }

    private /* synthetic */ void lambda$new$0() {
        this.postedErrorColorTimeout = false;
        this.passwordOutlineView.animateError(0.0f);
    }

    private /* synthetic */ void lambda$onFieldError$36() {
        AndroidUtilities.cancelRunOnUIThread(this.errorColorTimeout);
        AndroidUtilities.runOnUIThread(this.errorColorTimeout, 1500);
        this.postedErrorColorTimeout = true;
    }

    private /* synthetic */ void lambda$onPasswordForgot$14(TLRPC.TL_error r7, TLObject r8) {
        needHideProgress();
        if (r7 != null) goto L7;
        TL_account.Password r72 = this.currentPassword;
        r72.email_unconfirmed_pattern = ((TLRPC.TL_auth_passwordRecovery) r8).email_pattern;
        AnonymousClass4 r0 = new AnonymousClass4(this, this.currentAccount, 4, r72);
        r0.addFragmentToClose(this);
        r0.setCurrentPasswordParams(this.currentPasswordHash, this.currentSecretId, this.currentSecret, false);
        presentFragment(r0);
        return;
    L7:
        if (r7.text.startsWith("FLOOD_WAIT") == false) goto L14;
        int r73 = Utilities.parseInt(r7.text);
        if (r73 >= 60) goto L11;
        String r74 = LocaleController.formatPluralString("Seconds", r73, new Object[0]);
    L12:
        showAlertWithText(LocaleController.getString(R.string.AppName), LocaleController.formatString("FloodWaitTime", R.string.FloodWaitTime, new Object[]{r74}));
        return;
    L11:
        r74 = LocaleController.formatPluralString("Minutes", r73 / 60, new Object[0]);
        goto L12
    L14:
        showAlertWithText(LocaleController.getString(R.string.AppName), r7.text);
    }

    private /* synthetic */ void lambda$onPasswordForgot$15(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new TwoStepVerificationActivity$$ExternalSyntheticLambda19(this, r3, r2));
    }

    private /* synthetic */ void lambda$onPasswordForgot$16(AlertDialog r1, int r2) {
        resetPassword();
    }

    private /* synthetic */ void lambda$onPasswordForgot$17(AlertDialog r1, int r2) {
        resetPassword();
    }

    private /* synthetic */ void lambda$processDone$29(boolean r8, byte[] r9) {
        if (this.delegate == null) goto L5;
        if (r8 == false) goto L5;
    L7:
        if (r8 == false) goto L18;
        this.currentPasswordHash = r9;
        this.passwordEntered = true;
        if (this.delegate == null) goto L13;
        AndroidUtilities.hideKeyboard(this.passwordEditText);
        this.delegate.didEnterPassword(getNewSrpPassword());
        return;
    L13:
        if (TextUtils.isEmpty(this.currentPassword.email_unconfirmed_pattern) == true) goto L16;
        TwoStepVerificationSetupActivity r1 = new TwoStepVerificationSetupActivity(this.currentAccount, 5, this.currentPassword);
        r1.setCurrentPasswordParams(this.currentPasswordHash, this.currentSecretId, this.currentSecret, true);
        presentFragment(r1, true);
        return;
    L16:
        AndroidUtilities.hideKeyboard(this.passwordEditText);
        TwoStepVerificationActivity r82 = new TwoStepVerificationActivity();
        r82.passwordEntered = true;
        r82.currentPasswordHash = this.currentPasswordHash;
        r82.currentPassword = this.currentPassword;
        r82.currentSecret = this.currentSecret;
        r82.currentSecretId = this.currentSecretId;
        presentFragment(r82, true);
        return;
    L18:
        AlertsCreator.showUpdateAppAlert(getParentActivity(), LocaleController.getString(R.string.UpdateAppAlert), true);
        return;
    L5:
        needHideProgress();
        goto L7
    }

    private /* synthetic */ void lambda$processDone$30(byte[] r1, TLObject r2, byte[] r3) {
        AndroidUtilities.runOnUIThread(new TwoStepVerificationActivity$$ExternalSyntheticLambda32(this, checkSecretValues(r1, (TL_account.passwordSettings) r2), r3));
    }

    private /* synthetic */ void lambda$processDone$31(TLRPC.TL_error r2, TLObject r3) {
        if (r2 != null) goto L5;
        TL_account.Password r32 = (TL_account.Password) r3;
        this.currentPassword = r32;
        initPasswordNewAlgo(r32);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.didSetOrRemoveTwoStepPassword, new Object[]{this.currentPassword});
        processDone();
        return;
    }

    private /* synthetic */ void lambda$processDone$32(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new TwoStepVerificationActivity$$ExternalSyntheticLambda36(this, r3, r2));
    }

    private /* synthetic */ void lambda$processDone$33(TLRPC.TL_error r4) {
        if ("SRP_ID_INVALID".equals(r4.text) == false) goto L6;
        TL_account.getPassword r42 = new TL_account.getPassword();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r42, new TwoStepVerificationActivity$$ExternalSyntheticLambda33(this), 8);
        return;
    L6:
        needHideProgress();
        if ("PASSWORD_HASH_INVALID".equals(r4.text) == false) goto L11;
        onFieldError(this.passwordOutlineView, this.passwordEditText, true);
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

    private /* synthetic */ void lambda$processDone$34(byte[] r2, byte[] r3, TLObject r4, TLRPC.TL_error r5) {
        if (r5 != null) goto L5;
        Utilities.globalQueue.postRunnable(new TwoStepVerificationActivity$$ExternalSyntheticLambda26(this, r2, r4, r3));
        return;
    L5:
        AndroidUtilities.runOnUIThread(new TwoStepVerificationActivity$$ExternalSyntheticLambda27(this, r5));
    }

    private /* synthetic */ void lambda$processDone$35(byte[] r8) {
        TL_account.getPasswordSettings r0 = new TL_account.getPasswordSettings();
        TLRPC.PasswordKdfAlgo r1 = this.currentPassword.current_algo;
        if ((r1 instanceof TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow) == false) goto L5;
        byte[] r12 = SRPHelper.getX(r8, (TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow) r1);
    L6:
        TwoStepVerificationActivity$$ExternalSyntheticLambda21 r2 = new TwoStepVerificationActivity$$ExternalSyntheticLambda21(this, r8, r12);
        TL_account.Password r82 = this.currentPassword;
        TLRPC.PasswordKdfAlgo r4 = r82.current_algo;
        if ((r4 instanceof TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow) == false) goto L14;
        TLRPC.TL_inputCheckPasswordSRP r83 = SRPHelper.startCheck(r12, r82.srp_id, r82.srp_B, (TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow) r4);
        r0.password = r83;
        if (r83 != null) goto L12;
        TLRPC.TL_error r7 = new TLRPC.TL_error();
        r7.text = "ALGO_INVALID";
        r2.run(null, r7);
        return;
    L12:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, r2, 10);
        return;
    L14:
        TLRPC.TL_error r72 = new TLRPC.TL_error();
        r72.text = "PASSWORD_HASH_INVALID";
        r2.run(null, r72);
        return;
    L5:
        r12 = null;
        goto L6
    }

    private /* synthetic */ void lambda$resetPassword$11(DialogInterface r3) {
        getNotificationCenter().postNotificationName(NotificationCenter.didSetOrRemoveTwoStepPassword, new Object[0]);
        finishFragment();
    }

    private /* synthetic */ void lambda$resetPassword$12(TLObject r4) {
        needHideProgress();
        if ((r4 instanceof TL_account.resetPasswordOk) == false) goto L7;
        AlertDialog.Builder r42 = new AlertDialog.Builder(getParentActivity());
        r42.setNegativeButton(LocaleController.getString(R.string.OK), null);
        r42.setTitle(LocaleController.getString(R.string.ResetPassword));
        r42.setMessage(LocaleController.getString(R.string.RestorePasswordResetPasswordOk));
        showDialog(r42.create(), new TwoStepVerificationActivity$$ExternalSyntheticLambda24(this));
        return;
    L7:
        if ((r4 instanceof TL_account.resetPasswordRequestedWait) == false) goto L11;
        TL_account.Password r0 = this.currentPassword;
        r0.pending_reset_date = ((TL_account.resetPasswordRequestedWait) r4).until_date;
        updateBottomButton();
        return;
    L11:
        if ((r4 instanceof TL_account.resetPasswordFailedWait) == false) goto L24;
        int r43 = ((TL_account.resetPasswordFailedWait) r4).retry_date - getConnectionsManager().getCurrentTime();
        if (r43 <= 86400) goto L16;
        String r44 = LocaleController.formatPluralString("Days", r43 / 86400, new Object[0]);
    L22:
        showAlertWithText(LocaleController.getString(R.string.ResetPassword), LocaleController.formatString("ResetPasswordWait", R.string.ResetPasswordWait, new Object[]{r44}));
        return;
    L16:
        if (r43 <= 3600) goto L19;
        r44 = LocaleController.formatPluralString("Hours", r43 / 86400, new Object[0]);
        goto L22
    L19:
        if (r43 <= 60) goto L21;
        r44 = LocaleController.formatPluralString("Minutes", r43 / 60, new Object[0]);
        goto L22
    L21:
        r44 = LocaleController.formatPluralString("Seconds", Math.max(1, r43), new Object[0]);
        goto L22
    }

    private /* synthetic */ void lambda$resetPassword$13(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new TwoStepVerificationActivity$$ExternalSyntheticLambda14(this, r1));
    }

    private /* synthetic */ void lambda$showSetForcePasswordAlert$37(AlertDialog r1, int r2) {
        finishFragment();
    }

    private /* synthetic */ void lambda$updateRows$20() {
        if (isFinishing() == false) goto L5;
        return;
    L5:
        if (this.destroyed == true) goto L11;
        EditTextBoldCursor r0 = this.passwordEditText;
        if (r0 == null) goto L12;
        r0.requestFocus();
        AndroidUtilities.showKeyboard(this.passwordEditText);
        return;
    L12:
        return;
    }

    private void loadPasswordInfo(boolean r4, boolean r5, Runnable r6) {
        if (r5 == true) goto L6;
        this.loading = true;
        ListAdapter r0 = this.listAdapter;
        if (r0 == null) goto L6;
        r0.notifyDataSetChanged();
    L6:
        TL_account.getPassword r02 = new TL_account.getPassword();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r02, new TwoStepVerificationActivity$$ExternalSyntheticLambda12(this, r5, r4, r6), 10);
    }

    private void needShowProgress() {
        needShowProgress(false);
    }

    private void needShowProgress(boolean r9) {
        if (getParentActivity() != null) goto L5;
        return;
    L5:
        if (getParentActivity().isFinishing() == false) goto L7;
        return;
    L7:
        if (this.progressDialog == null) goto L10;
        return;
    L10:
        if (this.passwordEntered == true) goto L13;
        AnimatorSet r92 = new AnimatorSet();
        r92.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.radialProgressView, View.ALPHA, new float[]{1.0f}), ObjectAnimator.ofFloat(this.radialProgressView, View.SCALE_X, new float[]{1.0f}), ObjectAnimator.ofFloat(this.radialProgressView, View.SCALE_Y, new float[]{1.0f})});
        r92.setInterpolator(CubicBezierInterpolator.DEFAULT);
        r92.start();
        return;
    L13:
        AlertDialog r0 = new AlertDialog(getParentActivity(), 3);
        this.progressDialog = r0;
        r0.setCanCancel(false);
        AlertDialog r8 = this.progressDialog;
        if (r9 == false) goto L17;
        r8.showDelayed(300);
        return;
    L17:
        r8.show();
    }

    private void onFieldError(OutlineTextContainerView r3, TextView r4, boolean r5) {
        if (getParentActivity() != null) goto L5;
        return;
    L5:
        r3.performHapticFeedback(3, 2);
        if (r5 == false) goto L8;
        r4.setText(_UrlKt.FRAGMENT_ENCODE_SET);
    L8:
        r3.animateError(1.0f);
        AndroidUtilities.shakeViewSpring(r3, 5.0f, new TwoStepVerificationActivity$$ExternalSyntheticLambda23(this));
    }

    private void onPasswordForgot() {
        TL_account.Password r0 = this.currentPassword;
        if (r0.pending_reset_date != 0) goto L9;
        if (r0.has_recovery == false) goto L9;
        needShowProgress(true);
        TLRPC.TL_auth_requestPasswordRecovery r02 = new TLRPC.TL_auth_requestPasswordRecovery();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r02, new TwoStepVerificationActivity$$ExternalSyntheticLambda1(this), 10);
        return;
    L9:
        if (getParentActivity() != null) goto L12;
        return;
    L12:
        if (this.currentPassword.pending_reset_date != 0) goto L14;
        AlertDialog.Builder r03 = new AlertDialog.Builder(getParentActivity());
        r03.setPositiveButton(LocaleController.getString(R.string.Reset), new TwoStepVerificationActivity$$ExternalSyntheticLambda3(this));
        r03.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r03.setTitle(LocaleController.getString(R.string.ResetPassword));
        r03.setMessage(LocaleController.getString(R.string.RestorePasswordNoEmailText2));
        showDialog(r03.create());
        return;
    L14:
        if (getConnectionsManager().getCurrentTime() <= this.currentPassword.pending_reset_date) goto L19;
        AlertDialog.Builder r04 = new AlertDialog.Builder(getParentActivity());
        r04.setPositiveButton(LocaleController.getString(R.string.Reset), new TwoStepVerificationActivity$$ExternalSyntheticLambda2(this));
        r04.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r04.setTitle(LocaleController.getString(R.string.ResetPassword));
        r04.setMessage(LocaleController.getString(R.string.RestorePasswordResetPasswordText));
        AlertDialog r05 = r04.create();
        showDialog(r05);
        TextView r4 = (TextView) r05.getButton(-1);
        if (r4 == null) goto L23;
        r4.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    L23:
        return;
    L19:
        cancelPasswordReset();
    }

    private void processDone() {
        if (this.passwordEntered == true) goto L10;
        String r0 = this.passwordEditText.getText().toString();
        if (r0.length() != 0) goto L8;
        onFieldError(this.passwordOutlineView, this.passwordEditText, false);
        return;
    L8:
        byte[] r02 = AndroidUtilities.getStringBytes(r0);
        needShowProgress();
        Utilities.globalQueue.postRunnable(new TwoStepVerificationActivity$$ExternalSyntheticLambda13(this, r02));
        return;
    }

    private void resetPassword() {
        needShowProgress(true);
        TL_account.resetPassword r0 = new TL_account.resetPassword();
        getConnectionsManager().sendRequest(r0, new TwoStepVerificationActivity$$ExternalSyntheticLambda10(this));
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

    private void showSetForcePasswordAlert() {
        AlertDialog.Builder r0 = new AlertDialog.Builder(getParentActivity());
        r0.setTitle(LocaleController.getString(R.string.Warning));
        r0.setMessage(LocaleController.formatPluralString("ForceSetPasswordAlertMessageShort", this.otherwiseReloginDays, new Object[0]));
        r0.setPositiveButton(LocaleController.getString(R.string.TwoStepVerificationSetPassword), null);
        r0.setNegativeButton(LocaleController.getString(R.string.ForceSetPasswordCancel), new TwoStepVerificationActivity$$ExternalSyntheticLambda0(this));
        ((TextView) r0.show().getButton(-2)).setTextColor(Theme.getColor(Theme.key_text_RedBold));
    }

    private void updateBottomButton() {
        if (this.passwordEntered == false) goto L6;
        return;
    L6:
        if (this.currentPassword.pending_reset_date == 0) goto L25;
        int r0 = getConnectionsManager().getCurrentTime();
        int r3 = this.currentPassword.pending_reset_date;
        if (r0 > r3) goto L25;
        int r02 = Math.max(1, r3 - getConnectionsManager().getCurrentTime());
        if (r02 <= 86400) goto L14;
        String r03 = LocaleController.formatPluralString("Days", r02 / 86400, new Object[0]);
    L17:
        this.resetWaitView.setText(LocaleController.formatString("RestorePasswordResetIn", R.string.RestorePasswordResetIn, new Object[]{r03}));
        this.resetWaitView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText6));
        if (this.bottomButton.getVisibility() == 8) goto L21;
        this.bottomButton.setVisibility(8);
    L21:
        if (this.resetWaitView.getVisibility() == 0) goto L23;
        this.resetWaitView.setVisibility(0);
    L23:
        this.cancelResetButton.setVisibility(0);
        AndroidUtilities.cancelRunOnUIThread(this.updateTimeRunnable);
        AndroidUtilities.runOnUIThread(this.updateTimeRunnable, 1000);
    L33:
        if (this.currentPassword != null) goto L35;
    L38:
        AndroidUtilities.cancelRunOnUIThread(this.updateTimeRunnable);
        TextView r6 = this.cancelResetButton;
        if (r6 == null) goto L43;
        r6.setVisibility(8);
        return;
    L43:
        return;
    L35:
        if (this.bottomButton == null) goto L38;
        if (this.resetWaitView.getVisibility() != 0) goto L38;
        return;
    L14:
        if (r02 < 3600) goto L16;
        r03 = LocaleController.formatPluralString("Hours", r02 / 3600, new Object[0]);
        goto L17
    L16:
        r03 = String.format(Locale.US, "%02d:%02d", new Object[]{Integer.valueOf(r02 / 60), Integer.valueOf(r02 % 60)});
    L25:
        if (this.resetWaitView.getVisibility() == 8) goto L27;
        this.resetWaitView.setVisibility(8);
    L27:
        int r04 = this.currentPassword.pending_reset_date;
        SimpleTextView r32 = this.bottomButton;
        if (r04 != 0) goto L30;
        r32.setText(LocaleController.getString(R.string.ForgotPassword));
        this.cancelResetButton.setVisibility(8);
        this.bottomButton.setVisibility(0);
    L31:
        this.bottomButton.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText4));
        AndroidUtilities.cancelRunOnUIThread(this.updateTimeRunnable);
        goto L33
    L30:
        r32.setText(LocaleController.getString(R.string.ResetPassword));
        this.cancelResetButton.setVisibility(0);
        this.bottomButton.setVisibility(0);
        goto L31
    }

    private void updateRows() {
        StringBuilder r0 = new StringBuilder();
        r0.append(this.setPasswordRow);
        r0.append(this.setPasswordDetailRow);
        r0.append(this.changePasswordRow);
        r0.append(this.turnPasswordOffRow);
        r0.append(this.setRecoveryEmailRow);
        r0.append(this.changeRecoveryEmailRow);
        r0.append(this.passwordEnabledDetailRow);
        r0.append(this.rowCount);
        this.rowCount = 0;
        this.setPasswordRow = -1;
        this.setPasswordDetailRow = -1;
        this.changePasswordRow = -1;
        this.turnPasswordOffRow = -1;
        this.emailRow = -1;
        this.setRecoveryEmailRow = -1;
        this.changeRecoveryEmailRow = -1;
        this.passwordEnabledDetailRow = -1;
        if (this.loading == true) goto L16;
        TL_account.Password r2 = this.currentPassword;
        if (r2 == null) goto L16;
        if (this.passwordEntered == false) goto L16;
        if (r2.has_password == false) goto L15;
        this.changePasswordRow = 0;
        int r4 = 1 + 1;
        this.rowCount = r4;
        this.turnPasswordOffRow = 1;
        this.emailRow = r4;
        if (r2.has_recovery == false) goto L13;
        this.rowCount = r4 + 1;
        this.changeRecoveryEmailRow = r4;
    L14:
        int r22 = this.rowCount;
        this.rowCount = r22 + 1;
        this.passwordEnabledDetailRow = r22;
        goto L16
    L13:
        this.rowCount = r4 + 1;
        this.setRecoveryEmailRow = r4;
        goto L14
    L15:
        this.setPasswordRow = 0;
        this.rowCount = 1 + 1;
        this.setPasswordDetailRow = 1;
    L16:
        StringBuilder r23 = new StringBuilder();
        r23.append(this.setPasswordRow);
        r23.append(this.setPasswordDetailRow);
        r23.append(this.changePasswordRow);
        r23.append(this.turnPasswordOffRow);
        r23.append(this.setRecoveryEmailRow);
        r23.append(this.changeRecoveryEmailRow);
        r23.append(this.passwordEnabledDetailRow);
        r23.append(this.rowCount);
        if (this.listAdapter == null) goto L22;
        if (r0.toString().equals(r23.toString()) == true) goto L22;
        this.listAdapter.notifyDataSetChanged();
    L22:
        if (this.fragmentView != null) goto L24;
        return;
    L24:
        if (this.loading == false) goto L26;
    L39:
        RecyclerListView r02 = this.listView;
        if (r02 == null) goto L43;
        r02.setVisibility(0);
        this.scrollView.setVisibility(4);
        this.listView.setEmptyView(this.emptyView);
    L43:
        if (this.passwordEditText == null) goto L45;
        this.floatingButton.setButtonVisible(false, false);
        this.passwordEditText.setVisibility(4);
        this.titleTextView.setVisibility(4);
        this.bottomTextView.setVisibility(8);
        this.bottomButton.setVisibility(4);
        updateBottomButton();
    L45:
        View r03 = this.fragmentView;
        int r1 = Theme.key_windowBackgroundGray;
        r03.setBackgroundColor(Theme.getColor(r1));
        this.fragmentView.setTag(Integer.valueOf(r1));
        return;
    L26:
        if (this.passwordEntered == true) goto L39;
        RecyclerListView r04 = this.listView;
        if (r04 == null) goto L32;
        r04.setEmptyView(null);
        this.listView.setVisibility(4);
        this.scrollView.setVisibility(0);
        this.emptyView.setVisibility(4);
    L32:
        if (this.passwordEditText == null) goto L48;
        this.floatingButton.setButtonVisible(true, false);
        this.passwordEditText.setVisibility(0);
        View r05 = this.fragmentView;
        int r3 = Theme.key_windowBackgroundWhite;
        r05.setBackgroundColor(Theme.getColor(r3));
        this.fragmentView.setTag(Integer.valueOf(r3));
        this.titleTextView.setVisibility(0);
        this.bottomButton.setVisibility(0);
        updateBottomButton();
        this.bottomTextView.setVisibility(8);
        boolean r06 = TextUtils.isEmpty(this.currentPassword.hint);
        EditTextBoldCursor r12 = this.passwordEditText;
        if (r06 == true) goto L36;
        r12.setHint(this.currentPassword.hint);
    L37:
        AndroidUtilities.runOnUIThread(new TwoStepVerificationActivity$$ExternalSyntheticLambda11(this), 200);
        return;
    L36:
        r12.setHint(null);
        goto L37
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r31) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(false);
        if (this.passwordEntered == true) goto L5;
    L6:
        this.actionBar.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        ActionBar r2 = this.actionBar;
        int r4 = Theme.key_windowBackgroundWhiteBlackText;
        r2.setTitleColor(Theme.getColor(r4));
        this.actionBar.setItemsColor(Theme.getColor(r4), false);
        this.actionBar.setItemsBackgroundColor(Theme.getColor(Theme.key_actionBarWhiteSelector), false);
        this.actionBar.setCastShadows(false);
    L7:
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        FrameLayout r22 = new FrameLayout(r31);
        this.fragmentView = r22;
        int r42 = Theme.key_windowBackgroundWhite;
        r22.setBackgroundColor(Theme.getColor(r42));
        ScrollView r5 = new ScrollView(r31);
        this.scrollView = r5;
        r5.setFillViewport(true);
        r22.addView(this.scrollView, LayoutHelper.createFrame(-1, -1.0f));
        LinearLayout r52 = new LinearLayout(r31);
        r52.setOrientation(1);
        r52.setGravity(1);
        this.scrollView.addView(r52, LayoutHelper.createScroll(-1, -2, 51));
        RLottieImageView r9 = new RLottieImageView(r31);
        this.lockImageView = r9;
        r9.setAnimation(R.raw.tsv_setup_intro, 100, 100);
        this.lockImageView.playAnimation();
        RLottieImageView r92 = this.lockImageView;
        if (AndroidUtilities.isSmallScreen() == true) goto L15;
        Point r10 = AndroidUtilities.displaySize;
        if (r10.x > r10.y) goto L12;
    L14:
        int r102 = 0;
    L16:
        r92.setVisibility(r102);
        r52.addView(this.lockImageView, LayoutHelper.createLinear(100, 100, 1));
        TextView r93 = new TextView(r31);
        this.titleTextView = r93;
        int r103 = Theme.key_windowBackgroundWhiteBlackText;
        r93.setTextColor(Theme.getColor(r103));
        this.titleTextView.setTextSize(1, 18.0f);
        this.titleTextView.setGravity(1);
        this.titleTextView.setTypeface(AndroidUtilities.bold());
        r52.addView(this.titleTextView, LayoutHelper.createLinear(-2, -2, 1, 24, 8, 24, 0));
        TextView r94 = new TextView(r31);
        this.subtitleTextView = r94;
        int r13 = Theme.key_windowBackgroundWhiteGrayText6;
        r94.setTextColor(Theme.getColor(r13));
        this.subtitleTextView.setTextSize(1, 15.0f);
        this.subtitleTextView.setGravity(1);
        this.subtitleTextView.setVisibility(8);
        r52.addView(this.subtitleTextView, LayoutHelper.createLinear(-2, -2, 1, 42, 8, 42, 0));
        OutlineTextContainerView r95 = new OutlineTextContainerView(r31);
        this.passwordOutlineView = r95;
        r95.setText(LocaleController.getString(R.string.EnterPassword));
        this.passwordOutlineView.animateSelection(1.0f, false);
        r52.addView(this.passwordOutlineView, LayoutHelper.createLinear(-1, -2, 1, 24, 24, 24, 0));
        EditTextBoldCursor r8 = new EditTextBoldCursor(r31);
        this.passwordEditText = r8;
        r8.setTextSize(1, 18.0f);
        this.passwordEditText.setTextColor(Theme.getColor(r103));
        this.passwordEditText.setHintTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteHintText));
        this.passwordEditText.setBackground(null);
        this.passwordEditText.setSingleLine(true);
        this.passwordEditText.setInputType(129);
        this.passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        this.passwordEditText.setTypeface(Typeface.DEFAULT);
        EditTextBoldCursor r82 = this.passwordEditText;
        int r104 = Theme.key_windowBackgroundWhiteInputFieldActivated;
        r82.setCursorColor(Theme.getColor(r104));
        this.passwordEditText.setCursorWidth(1.5f);
        this.passwordEditText.setContentDescription(LocaleController.getString(R.string.EnterPassword));
        int r83 = AndroidUtilities.dp(16.0f);
        this.passwordEditText.setPadding(r83, r83, r83, r83);
        this.passwordOutlineView.addView(this.passwordEditText, LayoutHelper.createFrame(-1, -2.0f));
        this.passwordOutlineView.attachEditText(this.passwordEditText);
        this.passwordEditText.setOnFocusChangeListener(new TwoStepVerificationActivity$$ExternalSyntheticLambda4(this));
        this.passwordEditText.setOnEditorActionListener(new TwoStepVerificationActivity$$ExternalSyntheticLambda5(this));
        this.passwordEditText.addTextChangedListener(new AnonymousClass2(this));
        TextView r84 = new TextView(r31);
        this.bottomTextView = r84;
        r84.setTextColor(Theme.getColor(r13));
        this.bottomTextView.setTextSize(1, 14.0f);
        TextView r85 = this.bottomTextView;
        int r11 = 3;
        if (LocaleController.isRTL == false) goto L19;
        int r96 = 5;
    L20:
        r85.setGravity(r96 | 48);
        this.bottomTextView.setText(LocaleController.getString(R.string.YourEmailInfo));
        TextView r86 = this.bottomTextView;
        if (LocaleController.isRTL == false) goto L23;
        int r97 = 5;
    L24:
        r52.addView(r86, LayoutHelper.createLinear(-2, -2, r97 | 48, 40, 30, 40, 0));
        TextView r87 = new TextView(r31);
        this.resetWaitView = r87;
        r87.setTextColor(Theme.getColor(r13));
        this.resetWaitView.setTextSize(1, 12.0f);
        TextView r88 = this.resetWaitView;
        if (LocaleController.isRTL == false) goto L27;
        r11 = 5;
    L27:
        r88.setGravity(r11 | 48);
        r52.addView(this.resetWaitView, LayoutHelper.createLinear(-1, -2, 40.0f, 8.0f, 40.0f, 0.0f));
        LinearLayout r89 = new LinearLayout(r31);
        r89.setOrientation(1);
        r89.setGravity(80);
        r89.setClipChildren(false);
        r52.addView(r89, LayoutHelper.createLinear(-1, 0, 1.0f));
        SimpleTextView r53 = new SimpleTextView(r31);
        this.bottomButton = r53;
        r53.setTextSize(15);
        this.bottomButton.setGravity(19);
        this.bottomButton.setPadding(AndroidUtilities.dp(32.0f), 0, AndroidUtilities.dp(32.0f), 0);
        r22.addView(this.bottomButton, LayoutHelper.createFrame(-1, 56.0f, 80, 0.0f, 0.0f, 0.0f, 16.0f));
        this.bottomButton.setOnClickListener(new TwoStepVerificationActivity$$ExternalSyntheticLambda6(this));
        VerticalPositionAutoAnimator.attach(this.bottomButton);
        TextView r54 = new TextView(r31);
        this.cancelResetButton = r54;
        r54.setTextSize(1, 15.0f);
        this.cancelResetButton.setGravity(19);
        this.cancelResetButton.setPadding(AndroidUtilities.dp(32.0f), 0, AndroidUtilities.dp(32.0f), 0);
        this.cancelResetButton.setText(LocaleController.getString(R.string.CancelReset));
        this.cancelResetButton.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText4));
        this.cancelResetButton.setVisibility(8);
        r22.addView(this.cancelResetButton, LayoutHelper.createFrame(-1, 56.0f, 80, 0.0f, 0.0f, 0.0f, 16.0f));
        this.cancelResetButton.setOnClickListener(new TwoStepVerificationActivity$$ExternalSyntheticLambda7(this));
        VerticalPositionAutoAnimator.attach(this.cancelResetButton);
        FragmentFloatingButton r55 = new FragmentFloatingButton(r31, this.resourceProvider);
        this.floatingButton = r55;
        VerticalPositionAutoAnimator.attach(r55);
        this.floatingButton.setOnClickListener(new TwoStepVerificationActivity$$ExternalSyntheticLambda8(this));
        TransformableLoginButtonView r56 = new TransformableLoginButtonView(r31);
        this.floatingButtonIcon = r56;
        r56.setTransformType(1);
        this.floatingButtonIcon.setProgress(0.0f);
        this.floatingButtonIcon.setColor(Theme.getColor(Theme.key_chats_actionIcon));
        this.floatingButtonIcon.setDrawBackground(false);
        this.floatingButton.setContentDescription(LocaleController.getString(R.string.Next));
        this.floatingButton.addView(this.floatingButtonIcon, LayoutHelper.createFrame(56, 56, 17));
        this.floatingButton.addAdditionalView(this.floatingButtonIcon);
        r22.addView(this.floatingButton, FragmentFloatingButton.createDefaultLayoutParams());
        EmptyTextProgressView r57 = new EmptyTextProgressView(r31);
        this.emptyView = r57;
        r57.showProgress();
        r22.addView(this.emptyView, LayoutHelper.createFrame(-1, -1.0f));
        RecyclerListView r58 = new RecyclerListView(r31);
        this.listView = r58;
        r58.setSections(true);
        this.listView.setLayoutManager(new LinearLayoutManager(r31, 1, false));
        this.listView.setEmptyView(this.emptyView);
        this.listView.setVerticalScrollBarEnabled(false);
        r22.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        RecyclerListView r23 = this.listView;
        ListAdapter r59 = new ListAdapter(this, r31);
        this.listAdapter = r59;
        r23.setAdapter(r59);
        this.listView.setOnItemClickListener(new TwoStepVerificationActivity$$ExternalSyntheticLambda9(this));
        AnonymousClass3 r24 = new AnonymousClass3(this, r31);
        this.radialProgressView = r24;
        r24.setSize(AndroidUtilities.dp(20.0f));
        this.radialProgressView.setAlpha(0.0f);
        this.radialProgressView.setScaleX(0.1f);
        this.radialProgressView.setScaleY(0.1f);
        this.radialProgressView.setProgressColor(Theme.getColor(r104));
        this.actionBar.addView(this.radialProgressView, LayoutHelper.createFrame(32, 32.0f, 21, 0.0f, 0.0f, 12.0f, 0.0f));
        updateRows();
        if (this.passwordEntered == true) goto L30;
    L32:
        this.actionBar.setTitle(null);
    L33:
        TwoStepVerificationActivityDelegate r1 = this.delegate;
        TextView r25 = this.titleTextView;
        if (r1 == null) goto L43;
        r25.setText(LocaleController.getString(R.string.YourPassword));
        int r12 = this.delegateType;
        if (r12 != 1) goto L38;
        this.subtitleTextView.setText(LocaleController.getString(R.string.PleaseEnterCurrentPasswordWithdraw));
    L42:
        this.subtitleTextView.setVisibility(0);
    L44:
        boolean r14 = this.passwordEntered;
        View r26 = this.fragmentView;
        if (r14 == false) goto L47;
        int r15 = Theme.key_windowBackgroundGray;
        r26.setBackgroundColor(Theme.getColor(r15));
        this.fragmentView.setTag(Integer.valueOf(r15));
    L49:
        return this.fragmentView;
    L47:
        r26.setBackgroundColor(Theme.getColor(r42));
        this.fragmentView.setTag(Integer.valueOf(r42));
        goto L49
    L38:
        TextView r27 = this.subtitleTextView;
        if (r12 != 2) goto L41;
        r27.setText(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.PleaseEnterCurrentPasswordGiftTransfer, new Object[]{this.delegateTypeString})));
        goto L42
    L41:
        r27.setText(LocaleController.getString(R.string.PleaseEnterCurrentPasswordTransfer));
        goto L42
    L43:
        r25.setText(LocaleController.getString(R.string.YourPassword));
        this.subtitleTextView.setVisibility(0);
        this.subtitleTextView.setText(LocaleController.getString(R.string.LoginPasswordTextShort));
        goto L44
    L30:
        if (this.delegate != null) goto L32;
        this.actionBar.setTitle(LocaleController.getString(R.string.TwoStepVerificationTitle));
        goto L33
    L23:
        r97 = 3;
        goto L24
    L19:
        r96 = 3;
        goto L20
    L12:
        if (AndroidUtilities.isTablet() == true) goto L14;
    L15:
        r102 = 8;
        goto L16
    L5:
        if (this.delegate == null) goto L7;
        goto L6
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 == NotificationCenter.twoStepPasswordChanged) goto L5;
        return;
    L5:
        if (r3 != null) goto L7;
    L11:
        loadPasswordInfo(false, false, null);
        updateRows();
        return;
    L7:
        if (r3.length <= 0) goto L11;
        Object r22 = r3[0];
        if (r22 == null) goto L11;
        this.currentPasswordHash = (byte[]) r22;
        goto L11
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void finishFragment() {
        if (this.otherwiseReloginDays < 0) goto L6;
        Bundle r0 = new Bundle();
        r0.putBoolean("afterSignup", true);
        MainTabsActivity r1 = new MainTabsActivity();
        r1.prepareDialogsActivity(r0);
        presentFragment(r1, true);
        return;
    L6:
        super.finishFragment();
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
        int r19 = Theme.key_windowBackgroundWhite;
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{TextSettingsCell.class, EditTextSettingsCell.class}, null, null, null, r19));
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND | ThemeDescription.FLAG_CHECKTAG, null, null, null, null, r19));
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND | ThemeDescription.FLAG_CHECKTAG, null, null, null, null, Theme.key_windowBackgroundGray));
        ActionBar r13 = this.actionBar;
        int r14 = ThemeDescription.FLAG_BACKGROUND;
        int r192 = Theme.key_actionBarDefault;
        r1.add(new ThemeDescription(r13, r14, null, null, null, null, r192));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, r192));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        r1.add(new ThemeDescription(this.emptyView, ThemeDescription.FLAG_PROGRESSBAR, null, null, null, null, Theme.key_progressCircle));
        int r20 = Theme.key_windowBackgroundWhiteBlackText;
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKTAG, new Class[]{TextSettingsCell.class}, new String[]{"textView"}, null, null, null, r20));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKTAG, new Class[]{TextSettingsCell.class}, new String[]{"textView"}, null, null, null, Theme.key_text_RedRegular));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{EditTextSettingsCell.class}, new String[]{"textView"}, null, null, null, r20));
        int r11 = Theme.key_windowBackgroundWhiteHintText;
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_HINTTEXTCOLOR, new Class[]{EditTextSettingsCell.class}, new String[]{"textView"}, null, null, null, r11));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText4));
        TextView r4 = this.titleTextView;
        int r5 = ThemeDescription.FLAG_TEXTCOLOR;
        int r193 = Theme.key_windowBackgroundWhiteGrayText6;
        r1.add(new ThemeDescription(r4, r5, null, null, null, null, r193));
        r1.add(new ThemeDescription(this.bottomTextView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r193));
        r1.add(new ThemeDescription(this.bottomButton, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_windowBackgroundWhiteBlueText4));
        r1.add(new ThemeDescription(this.passwordEditText, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r20));
        r1.add(new ThemeDescription(this.passwordEditText, ThemeDescription.FLAG_HINTTEXTCOLOR, null, null, null, null, r11));
        r1.add(new ThemeDescription(this.passwordEditText, ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, Theme.key_windowBackgroundWhiteInputField));
        r1.add(new ThemeDescription(this.passwordEditText, ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_DRAWABLESELECTEDSTATE, null, null, null, null, Theme.key_windowBackgroundWhiteInputFieldActivated));
        return r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isLightStatusBar() {
        if (ColorUtils.calculateLuminance(Theme.getColor(Theme.key_windowBackgroundWhite, null, true)) <= 0.699999988079071d) goto L5;
        return true;
    L5:
        return false;
    }

    public void needHideProgress() {
        if (this.passwordEntered == true) goto L6;
        AnimatorSet r0 = new AnimatorSet();
        r0.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.radialProgressView, View.ALPHA, new float[]{0.0f}), ObjectAnimator.ofFloat(this.radialProgressView, View.SCALE_X, new float[]{0.1f}), ObjectAnimator.ofFloat(this.radialProgressView, View.SCALE_Y, new float[]{0.1f})});
        r0.setInterpolator(CubicBezierInterpolator.DEFAULT);
        r0.start();
        return;
    L6:
        AlertDialog r02 = this.progressDialog;
        if (r02 != null) goto L15;
        return;
    L15:
        r02.dismiss();     // Catch: Exception -> L11
    L13:
        this.progressDialog = null;
        return;
    L11:
        e = move-exception;
        FileLog.e(e);
        goto L13
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r2) {
        if (this.otherwiseReloginDays < 0) goto L9;
        if (r2 == false) goto L10;
        showSetForcePasswordAlert();
        return false;
    L10:
        return false;
    L9:
        return super.onBackPressed(r2);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onConfigurationChanged(Configuration r2) {
        super.onConfigurationChanged(r2);
        RLottieImageView r1 = this.lockImageView;
        if (AndroidUtilities.isSmallScreen() == true) goto L10;
        Point r22 = AndroidUtilities.displaySize;
        if (r22.x > r22.y) goto L7;
    L9:
        int r23 = 0;
    L11:
        r1.setVisibility(r23);
        return;
    L7:
        if (AndroidUtilities.isTablet() == true) goto L9;
    L10:
        r23 = 8;
        goto L11
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        super.onFragmentCreate();
        if (this.preloaded == true) goto L5;
        preload(null);
    L5:
        updateRows();
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.twoStepPasswordChanged);
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        AndroidUtilities.cancelRunOnUIThread(this.updateTimeRunnable);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.twoStepPasswordChanged);
        this.destroyed = true;
        AlertDialog r0 = this.progressDialog;
        if (r0 != null) goto L11;
    L9:
        AndroidUtilities.removeAdjustResize(getParentActivity(), this.classGuid);
        return;
    L11:
        r0.dismiss();     // Catch: Exception -> L6
    L8:
        this.progressDialog = null;
    L6:
        e = move-exception;
        FileLog.e(e);
        goto L8
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onPause() {
        super.onPause();
        this.paused = true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        this.paused = false;
        AndroidUtilities.requestAdjustResize(getParentActivity(), this.classGuid);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationEnd(boolean r1, boolean r2) {
        super.onTransitionAnimationEnd(r1, r2);
        if (r1 == true) goto L5;
        return;
    L5:
        if (this.forgotPasswordOnShow == false) goto L9;
        onPasswordForgot();
        this.forgotPasswordOnShow = false;
        return;
    L9:
        if (this.resetPasswordOnShow == false) goto L13;
        resetPassword();
        this.resetPasswordOnShow = false;
        return;
    }

    public void preload(Runnable r4) {
        boolean r0 = false;
        this.preloaded = false;
        TL_account.Password r1 = this.currentPassword;
        if (r1 == null) goto L15;
        if (r1.current_algo == null) goto L15;
        byte[] r2 = this.currentPasswordHash;
        if (r2 == null) goto L15;
        if (r2.length <= 0) goto L15;
        if (r4 == null) goto L19;
        r4.run();
        return;
    L19:
        return;
    L15:
        if (r1 == null) goto L17;
        r0 = true;
    L17:
        loadPasswordInfo(true, r0, r4);
    }

    public void setBlockingAlert(int r1) {
        this.otherwiseReloginDays = r1;
    }

    public void setCurrentPasswordInfo(byte[] r1, TL_account.Password r2) {
        if (r1 == null) goto L4;
        this.currentPasswordHash = r1;
    L4:
        this.currentPassword = r2;
    }

    public void setCurrentPasswordParams(TL_account.Password r1, byte[] r2, long r3, byte[] r5) {
        this.currentPassword = r1;
        this.currentPasswordHash = r2;
        this.currentSecret = r5;
        this.currentSecretId = r3;
        if (r2 == null) goto L7;
        if (r2.length <= 0) goto L7;
    L8:
        boolean r12 = true;
    L10:
        this.passwordEntered = r12;
        return;
    L7:
        if (r1.has_password == false) goto L8;
        r12 = false;
        goto L10
    }

    public void setDelegate(int r1, TwoStepVerificationActivityDelegate r2) {
        this.delegateType = r1;
        this.delegate = r2;
    }

    public void setDelegateString(String r1) {
        this.delegateTypeString = r1;
    }

    public void setForgotPasswordOnShow() {
        this.forgotPasswordOnShow = true;
    }

    public void setPassword(TL_account.Password r1) {
        this.currentPassword = r1;
        this.passwordEntered = false;
    }
}
