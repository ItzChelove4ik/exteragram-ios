package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Keep;
import androidx.biometric.BiometricManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.TextSettingsCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.CustomPhoneKeyboardView;
import org.telegram.ui.Components.Easings;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.FragmentFloatingButton;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.NumberPicker;
import org.telegram.ui.Components.OutlineTextContainerView;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.TextViewSwitcher;
import org.telegram.ui.Components.TransformableLoginButtonView;
import org.telegram.ui.Components.VerticalPositionAutoAnimator;

/* loaded from: classes6.dex */
public class PasscodeActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private int autoLockDetailRow;

    @Keep
    private int autoLockRow;
    private int captureDetailRow;
    private int captureHeaderRow;
    private int captureRow;

    @Keep
    private int changePasscodeRow;
    private CodeFieldContainer codeFieldContainer;
    private int currentPasswordType;
    private TextViewSwitcher descriptionTextSwitcher;

    @Keep
    private int disablePasscodeRow;

    @Keep
    private int fingerprintRow;
    private String firstPassword;
    private FragmentFloatingButton floatingButton;
    private final Runnable hidePasscodesDoNotMatch;
    private int hintRow;
    private CustomPhoneKeyboardView keyboardView;
    private ListAdapter listAdapter;
    private RecyclerListView listView;
    private RLottieImageView lockImageView;
    private Runnable onShowKeyboardCallback;
    private Runnable openedSettings;
    private ActionBarMenuItem otherItem;
    private OutlineTextContainerView outlinePasswordView;
    private int passcodeSetStep;
    private TextView passcodesDoNotMatchTextView;
    private ImageView passwordButton;
    private EditTextBoldCursor passwordEditText;
    private boolean postedHidePasscodesDoNotMatch;
    private int rowCount;
    private TextView titleTextView;
    private int type;
    private int utyanRow;

    /* renamed from: org.telegram.ui.PasscodeActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ PasscodeActivity this$0;

        public AnonymousClass1(PasscodeActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.PasscodeActivity$10, reason: invalid class name */
    public class AnonymousClass10 extends AnimatorListenerAdapter {
        final /* synthetic */ PasscodeActivity this$0;
        final /* synthetic */ boolean val$visible;

        public AnonymousClass10(PasscodeActivity r1, boolean r2) {
            this.this$0 = r1;
            this.val$visible = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            if (this.val$visible == true) goto L6;
            PasscodeActivity.m16372$$Nest$fgetkeyboardView(this.this$0).setVisibility(8);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r1) {
            if (this.val$visible == false) goto L6;
            PasscodeActivity.m16372$$Nest$fgetkeyboardView(this.this$0).setVisibility(0);
            return;
        }
    }

    /* renamed from: org.telegram.ui.PasscodeActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends SizeNotifierFrameLayout {
        final /* synthetic */ PasscodeActivity this$0;
        final /* synthetic */ View val$fragmentContentView;

        public AnonymousClass2(PasscodeActivity r1, Context r2, View r3) {
            this.this$0 = r1;
            this.val$fragmentContentView = r3;
            super(r2);
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r2, int r3, int r4, int r5, int r6) {
            if (PasscodeActivity.m16372$$Nest$fgetkeyboardView(this.this$0).getVisibility() != 8) goto L5;
        L10:
            int r22 = PasscodeActivity.m16372$$Nest$fgetkeyboardView(this.this$0).getVisibility();
            View r62 = this.val$fragmentContentView;
            if (r22 == 8) goto L14;
            int r23 = getMeasuredWidth();
            int r52 = getMeasuredHeight() - AndroidUtilities.dp(230.0f);
            r62.layout(0, 0, r23, r52);
        L13:
            int r63 = r52;
        L15:
            PasscodeActivity.m16372$$Nest$fgetkeyboardView(this.this$0).layout(0, r63, getMeasuredWidth(), AndroidUtilities.dp(230.0f) + r63);
            notifyHeightChanged();
            return;
        L14:
            int r24 = getMeasuredWidth();
            r52 = getMeasuredHeight();
            r62.layout(0, 0, r24, r52);
            goto L13
        L5:
            if (measureKeyboardHeight() < AndroidUtilities.dp(20.0f)) goto L10;
            boolean r25 = PasscodeActivity.m16381$$Nest$misCustomKeyboardVisible(this.this$0);
            View r53 = this.val$fragmentContentView;
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
            if (PasscodeActivity.m16372$$Nest$fgetkeyboardView(this.this$0).getVisibility() != 8) goto L5;
        L7:
            this.val$fragmentContentView.measure(View.MeasureSpec.makeMeasureSpec(r52, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r62, TLObject.FLAG_30));
            PasscodeActivity.m16372$$Nest$fgetkeyboardView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r52, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(230.0f), TLObject.FLAG_30));
            return;
        L5:
            if (measureKeyboardHeight() >= AndroidUtilities.dp(20.0f)) goto L7;
            r62 = r62 - AndroidUtilities.dp(230.0f);
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.PasscodeActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends LinearLayoutManager {
        final /* synthetic */ PasscodeActivity this$0;

        public AnonymousClass3(PasscodeActivity r1, Context r2, int r3, boolean r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean supportsPredictiveItemAnimations() {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.PasscodeActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ PasscodeActivity this$0;
        final /* synthetic */ ActionBarMenuSubItem val$switchItem;

        public static /* synthetic */ void $r8$lambda$cx5sNAy4hQ5AIYLU9YeUh6SxEmg(AnonymousClass4 r0, ActionBarMenuSubItem r1) {
            r0.lambda$onItemClick$0(r1);
        }

        public AnonymousClass4(PasscodeActivity r1, ActionBarMenuSubItem r2) {
            this.this$0 = r1;
            this.val$switchItem = r2;
        }

        private /* synthetic */ void lambda$onItemClick$0(ActionBarMenuSubItem r3) {
            if (PasscodeActivity.m16367$$Nest$fgetcurrentPasswordType(this.this$0) != 0) goto L5;
            int r0 = R.string.PasscodeSwitchToPassword;
        L6:
            r3.setText(LocaleController.getString(r0));
            if (PasscodeActivity.m16367$$Nest$fgetcurrentPasswordType(this.this$0) != 0) goto L9;
            int r02 = R.drawable.msg_permissions;
        L10:
            r3.setIcon(r02);
            PasscodeActivity.m16385$$Nest$mshowKeyboard(this.this$0);
            if (PasscodeActivity.m16382$$Nest$misPinCode(this.this$0) == false) goto L14;
            PasscodeActivity.m16375$$Nest$fgetpasswordEditText(this.this$0).setInputType(524417);
            AndroidUtilities.updateViewVisibilityAnimated(PasscodeActivity.m16374$$Nest$fgetpasswordButton(this.this$0), true, 0.1f, false);
            return;
        L14:
            return;
        L9:
            r02 = R.drawable.msg_pin_code;
            goto L10
        L5:
            r0 = R.string.PasscodeSwitchToPIN;
            goto L6
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r6) {
            if (r6 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        L6:
            int r0 = 1;
            if (r6 != 1) goto L18;
            PasscodeActivity r62 = this.this$0;
            int r2 = 0;
            if (PasscodeActivity.m16367$$Nest$fgetcurrentPasswordType(r62) == 0) goto L12;
            r0 = 0;
        L12:
            PasscodeActivity.m16380$$Nest$fputcurrentPasswordType(r62, r0);
            AndroidUtilities.runOnUIThread(new PasscodeActivity$4$$ExternalSyntheticLambda0(this, this.val$switchItem), 150);
            PasscodeActivity.m16375$$Nest$fgetpasswordEditText(this.this$0).setText(_UrlKt.FRAGMENT_ENCODE_SET);
            CodeNumberField[] r63 = PasscodeActivity.m16366$$Nest$fgetcodeFieldContainer(this.this$0).codeField;
            int r1 = r63.length;
        L13:
            if (r2 >= r1) goto L15;
            r63[r2].setText(_UrlKt.FRAGMENT_ENCODE_SET);
            r2 = r2 + 1;
            goto L13
        L15:
            PasscodeActivity.m16386$$Nest$mupdateFields(this.this$0);
            return;
        }
    }

    /* renamed from: org.telegram.ui.PasscodeActivity$5, reason: invalid class name */
    public class AnonymousClass5 implements TextWatcher {
        final /* synthetic */ PasscodeActivity this$0;
        final /* synthetic */ AtomicBoolean val$isPasswordShown;

        public AnonymousClass5(PasscodeActivity r1, AtomicBoolean r2) {
            this.this$0 = r1;
            this.val$isPasswordShown = r2;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r5) {
            if (PasscodeActivity.m16378$$Nest$fgettype(this.this$0) == 1) goto L5;
            return;
        L5:
            if (PasscodeActivity.m16373$$Nest$fgetpasscodeSetStep(this.this$0) == 0) goto L7;
            return;
        L7:
            if (TextUtils.isEmpty(r5) == false) goto L16;
            if (PasscodeActivity.m16374$$Nest$fgetpasswordButton(this.this$0).getVisibility() == 8) goto L16;
            if (this.val$isPasswordShown.get() == false) goto L13;
            PasscodeActivity.m16374$$Nest$fgetpasswordButton(this.this$0).callOnClick();
        L13:
            AndroidUtilities.updateViewVisibilityAnimated(PasscodeActivity.m16374$$Nest$fgetpasswordButton(this.this$0), false, 0.1f, true);
            return;
        L16:
            if (TextUtils.isEmpty(r5) == false) goto L18;
            return;
        L18:
            if (PasscodeActivity.m16374$$Nest$fgetpasswordButton(this.this$0).getVisibility() == 0) goto L24;
            AndroidUtilities.updateViewVisibilityAnimated(PasscodeActivity.m16374$$Nest$fgetpasswordButton(this.this$0), true, 0.1f, true);
            return;
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.PasscodeActivity$6, reason: invalid class name */
    public class AnonymousClass6 implements TextWatcher {
        final /* synthetic */ PasscodeActivity this$0;

        public AnonymousClass6(PasscodeActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r1) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
            if (PasscodeActivity.m16376$$Nest$fgetpostedHidePasscodesDoNotMatch(this.this$0) == false) goto L6;
            PasscodeActivity.m16366$$Nest$fgetcodeFieldContainer(this.this$0).removeCallbacks(PasscodeActivity.m16370$$Nest$fgethidePasscodesDoNotMatch(this.this$0));
            PasscodeActivity.m16370$$Nest$fgethidePasscodesDoNotMatch(this.this$0).run();
            return;
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.PasscodeActivity$7, reason: invalid class name */
    public class AnonymousClass7 implements ActionMode.Callback {
        final /* synthetic */ PasscodeActivity this$0;

        public AnonymousClass7(PasscodeActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ActionMode.Callback
        public boolean onActionItemClicked(ActionMode r1, MenuItem r2) {
            return false;
        }

        @Override // android.view.ActionMode.Callback
        public boolean onCreateActionMode(ActionMode r1, Menu r2) {
            return false;
        }

        @Override // android.view.ActionMode.Callback
        public void onDestroyActionMode(ActionMode r1) {
        }

        @Override // android.view.ActionMode.Callback
        public boolean onPrepareActionMode(ActionMode r1, Menu r2) {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.PasscodeActivity$8, reason: invalid class name */
    public class AnonymousClass8 extends CodeFieldContainer {
        final /* synthetic */ PasscodeActivity this$0;

        /* renamed from: $r8$lambda$qClJ90SDa5NUw-ljyEztNZrPstY, reason: not valid java name */
        public static /* synthetic */ void m16387$r8$lambda$qClJ90SDa5NUwljyEztNZrPstY(AnonymousClass8 r0) {
            r0.lambda$processNextPressed$0();
        }

        public AnonymousClass8(PasscodeActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        private /* synthetic */ void lambda$processNextPressed$0() {
            PasscodeActivity.m16384$$Nest$mprocessNext(this.this$0);
        }

        @Override // org.telegram.ui.CodeFieldContainer
        public void processNextPressed() {
            if (PasscodeActivity.m16373$$Nest$fgetpasscodeSetStep(this.this$0) != 0) goto L6;
            postDelayed(new PasscodeActivity$8$$ExternalSyntheticLambda0(this), 260);
            return;
        L6:
            PasscodeActivity.m16383$$Nest$mprocessDone(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.PasscodeActivity$9, reason: invalid class name */
    public class AnonymousClass9 implements TextWatcher {
        final /* synthetic */ PasscodeActivity this$0;

        public AnonymousClass9(PasscodeActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r1) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
            if (PasscodeActivity.m16376$$Nest$fgetpostedHidePasscodesDoNotMatch(this.this$0) == false) goto L6;
            PasscodeActivity.m16366$$Nest$fgetcodeFieldContainer(this.this$0).removeCallbacks(PasscodeActivity.m16370$$Nest$fgethidePasscodesDoNotMatch(this.this$0));
            PasscodeActivity.m16370$$Nest$fgethidePasscodesDoNotMatch(this.this$0).run();
            return;
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    public class ListAdapter extends RecyclerListView.SelectionAdapter {
        private final Context mContext;
        final /* synthetic */ PasscodeActivity this$0;

        public ListAdapter(PasscodeActivity r1, Context r2) {
            this.this$0 = r1;
            this.mContext = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return PasscodeActivity.m16377$$Nest$fgetrowCount(this.this$0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r3) {
            if (r3 != PasscodeActivity.m16369$$Nest$fgetfingerprintRow(this.this$0)) goto L5;
        L34:
            return 0;
        L5:
            if (r3 == PasscodeActivity.m16364$$Nest$fgetcaptureRow(this.this$0)) goto L34;
            if (r3 != PasscodeActivity.m16365$$Nest$fgetchangePasscodeRow(this.this$0)) goto L10;
            return 1;
        L10:
            if (r3 != PasscodeActivity.m16361$$Nest$fgetautoLockRow(this.this$0)) goto L12;
            return 1;
        L12:
            if (r3 != PasscodeActivity.m16368$$Nest$fgetdisablePasscodeRow(this.this$0)) goto L15;
            return 1;
        L15:
            if (r3 != PasscodeActivity.m16360$$Nest$fgetautoLockDetailRow(this.this$0)) goto L17;
            return 2;
        L17:
            if (r3 != PasscodeActivity.m16362$$Nest$fgetcaptureDetailRow(this.this$0)) goto L19;
            return 2;
        L19:
            if (r3 != PasscodeActivity.m16371$$Nest$fgethintRow(this.this$0)) goto L22;
            return 2;
        L22:
            if (r3 != PasscodeActivity.m16363$$Nest$fgetcaptureHeaderRow(this.this$0)) goto L26;
            return 3;
        L26:
            if (r3 != PasscodeActivity.m16379$$Nest$fgetutyanRow(this.this$0)) goto L29;
            return 4;
        L29:
            return 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r2) {
            int r22 = r2.getAdapterPosition();
            if (r22 != PasscodeActivity.m16369$$Nest$fgetfingerprintRow(this.this$0)) goto L5;
            return true;
        L5:
            if (r22 != PasscodeActivity.m16361$$Nest$fgetautoLockRow(this.this$0)) goto L7;
            return true;
        L7:
            if (r22 != PasscodeActivity.m16364$$Nest$fgetcaptureRow(this.this$0)) goto L9;
            return true;
        L9:
            if (r22 != PasscodeActivity.m16365$$Nest$fgetchangePasscodeRow(this.this$0)) goto L11;
            return true;
        L11:
            if (r22 == PasscodeActivity.m16368$$Nest$fgetdisablePasscodeRow(this.this$0)) goto L20;
            return false;
        L20:
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r6, int r7) {
            int r0 = r6.getItemViewType();
            if (r0 != 0) goto L5;
            TextCheckCell r62 = (TextCheckCell) r6.itemView;
            if (r7 != PasscodeActivity.m16369$$Nest$fgetfingerprintRow(this.this$0)) goto L69;
            r62.setTextAndCheck(LocaleController.getString(R.string.UnlockFingerprint), SharedConfig.useFingerprintLock, false);
            return;
        L69:
            if (r7 != PasscodeActivity.m16364$$Nest$fgetcaptureRow(this.this$0)) goto L75;
            r62.setTextAndCheck(LocaleController.getString(R.string.ScreenCaptureShowContent), SharedConfig.allowScreenCapture, false);
            return;
        L75:
            return;
        L5:
            if (r0 == 1) goto L36;
            int r3 = 3;
            if (r0 == 2) goto L18;
            if (r0 != 3) goto L10;
            HeaderCell r63 = (HeaderCell) r6.itemView;
            r63.setHeight(46);
            if (r7 != PasscodeActivity.m16363$$Nest$fgetcaptureHeaderRow(this.this$0)) goto L72;
            r63.setText(LocaleController.getString(R.string.ScreenCaptureHeader));
            return;
        L72:
            return;
        L10:
            if (r0 != 4) goto L76;
            RLottieImageHolderView r5 = (RLottieImageHolderView) r6.itemView;
            RLottieImageHolderView.m16388$$Nest$fgetimageView(r5).setAnimation(R.raw.utyan_passcode, 100, 100);
            RLottieImageHolderView.m16388$$Nest$fgetimageView(r5).playAnimation();
            return;
        L76:
            return;
        L18:
            TextInfoPrivacyCell r64 = (TextInfoPrivacyCell) r6.itemView;
            if (r7 != PasscodeActivity.m16371$$Nest$fgethintRow(this.this$0)) goto L23;
            r64.setText(LocaleController.getString(R.string.PasscodeScreenHint));
            r64.setBackground(null);
            r64.getTextView().setGravity(1);
            return;
        L23:
            if (r7 != PasscodeActivity.m16360$$Nest$fgetautoLockDetailRow(this.this$0)) goto L30;
            r64.setText(LocaleController.getString(R.string.AutoLockInfo));
            LinkSpanDrawable.LinksTextView r52 = r64.getTextView();
            if (LocaleController.isRTL == false) goto L27;
            r3 = 5;
        L27:
            r52.setGravity(r3);
            return;
        L30:
            if (r7 != PasscodeActivity.m16362$$Nest$fgetcaptureDetailRow(this.this$0)) goto L73;
            r64.setText(LocaleController.getString(R.string.ScreenCaptureInfo));
            LinkSpanDrawable.LinksTextView r53 = r64.getTextView();
            if (LocaleController.isRTL == false) goto L34;
            r3 = 5;
        L34:
            r53.setGravity(r3);
            return;
        L73:
            return;
        L36:
            TextSettingsCell r65 = (TextSettingsCell) r6.itemView;
            if (r7 != PasscodeActivity.m16365$$Nest$fgetchangePasscodeRow(this.this$0)) goto L45;
            r65.setText(LocaleController.getString(R.string.ChangePasscode), true);
            if (SharedConfig.passcodeHash.isEmpty() == false) goto L42;
            int r54 = Theme.key_windowBackgroundWhiteGrayText7;
            r65.setTag(Integer.valueOf(r54));
            r65.setTextColor(Theme.getColor(r54));
            return;
        L42:
            int r55 = Theme.key_windowBackgroundWhiteBlackText;
            r65.setTag(Integer.valueOf(r55));
            r65.setTextColor(Theme.getColor(r55));
            return;
        L45:
            if (r7 != PasscodeActivity.m16361$$Nest$fgetautoLockRow(this.this$0)) goto L61;
            int r56 = SharedConfig.autoLockIn;
            if (r56 != Integer.MAX_VALUE) goto L49;
            String r57 = LocaleController.getString(R.string.AutoLockInstant);
        L58:
            r65.setTextAndValue(LocaleController.getString(R.string.AutoLock), r57, true);
            int r58 = Theme.key_windowBackgroundWhiteBlackText;
            r65.setTag(Integer.valueOf(r58));
            r65.setTextColor(Theme.getColor(r58));
            return;
        L49:
            if (r56 != 0) goto L52;
            r57 = LocaleController.formatString("AutoLockDisabled", R.string.AutoLockDisabled, new Object[0]);
            goto L58
        L52:
            if (r56 >= 3600) goto L55;
            r57 = LocaleController.formatString("AutoLockInTime", R.string.AutoLockInTime, new Object[]{LocaleController.formatPluralString("Minutes", r56 / 60, new Object[0])});
            goto L58
        L55:
            if (r56 >= 86400) goto L57;
            r57 = LocaleController.formatString("AutoLockInTime", R.string.AutoLockInTime, new Object[]{LocaleController.formatPluralString("Hours", (int) Math.ceil((r56 / 60.0f) / 60.0f), new Object[0])});
            goto L58
        L57:
            r57 = LocaleController.formatString("AutoLockInTime", R.string.AutoLockInTime, new Object[]{LocaleController.formatPluralString("Days", (int) Math.ceil(((r56 / 60.0f) / 60.0f) / 24.0f), new Object[0])});
            goto L58
        L61:
            if (r7 != PasscodeActivity.m16368$$Nest$fgetdisablePasscodeRow(this.this$0)) goto L74;
            r65.setText(LocaleController.getString(R.string.DisablePasscode), false);
            int r59 = Theme.key_text_RedBold;
            r65.setTag(Integer.valueOf(r59));
            r65.setTextColor(Theme.getColor(r59));
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r1, int r2) {
            if (r2 != 0) goto L4;
            View r12 = new TextCheckCell(this.mContext);
        L15:
            return new RecyclerListView.Holder(r12);
        L4:
            if (r2 != 1) goto L6;
            r12 = new TextSettingsCell(this.mContext);
            goto L15
        L6:
            if (r2 != 3) goto L8;
            r12 = new HeaderCell(this.mContext);
            goto L15
        L8:
            if (r2 == 4) goto L10;
            r12 = new TextInfoPrivacyCell(this.mContext);
            goto L15
        L10:
            r12 = new RLottieImageHolderView(this.mContext, null);
            r12.setTag(-33024);
            goto L15
        }
    }

    public static final class RLottieImageHolderView extends FrameLayout {
        private final RLottieImageView imageView;

        public static /* synthetic */ void $r8$lambda$3V5fQO4xif6wZSFaebSL3bmyrb8(RLottieImageHolderView r0, View r1) {
            r0.lambda$new$0(r1);
        }

        /* renamed from: -$$Nest$fgetimageView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RLottieImageView m16388$$Nest$fgetimageView(RLottieImageHolderView r0) {
            return r0.imageView;
        }

        private RLottieImageHolderView(Context r3) {
            super(r3);
            RLottieImageView r0 = new RLottieImageView(r3);
            this.imageView = r0;
            r0.setOnClickListener(new PasscodeActivity$RLottieImageHolderView$$ExternalSyntheticLambda0(this));
            int r32 = AndroidUtilities.dp(120.0f);
            FrameLayout.LayoutParams r1 = new FrameLayout.LayoutParams(r32, r32);
            r1.gravity = 1;
            addView(r0, r1);
            setPadding(0, AndroidUtilities.dp(32.0f), 0, 0);
            setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
        }

        public /* synthetic */ RLottieImageHolderView(Context r1, PasscodeActivityIA r2) {
            this(r1);
        }

        private /* synthetic */ void lambda$new$0(View r2) {
            if (this.imageView.getAnimatedDrawable().isRunning() == true) goto L6;
            this.imageView.getAnimatedDrawable().setCurrentFrame(0, false);
            this.imageView.playAnimation();
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$8ScX0FAZMm1YBmLuiPKqpankZGI(PasscodeActivity r0, ValueAnimator r1) {
        r0.lambda$setCustomKeyboardVisible$13(r1);
    }

    public static /* synthetic */ void $r8$lambda$APUe19LG_OpP0o0wcRsBQ3Kq4pU(PasscodeActivity r0, AlertDialog r1, int r2) {
        r0.lambda$createView$2(r1, r2);
    }

    /* renamed from: $r8$lambda$Ak9ELcW3etIC-zKyetXayBzpbfg, reason: not valid java name */
    public static /* synthetic */ void m16355$r8$lambda$Ak9ELcW3etICzKyetXayBzpbfg(PasscodeActivity r0, boolean r1) {
        r0.lambda$processDone$18(r1);
    }

    public static /* synthetic */ void $r8$lambda$CV0cA8aD0zO62UKsK0sj7Gf7fjI(PasscodeActivity r0, NumberPicker r1, int r2, AlertDialog r3, int r4) {
        r0.lambda$createView$4(r1, r2, r3, r4);
    }

    public static /* synthetic */ String $r8$lambda$GkHM8SCihEibxZlPxpaEUpqlgj4(int r6) {
        if (r6 != 0) goto L6;
        return LocaleController.getString(R.string.AutoLockDisabled);
    L6:
        if (r6 != 1) goto L10;
        return LocaleController.formatString("AutoLockInTime", R.string.AutoLockInTime, new Object[]{LocaleController.formatPluralString("Minutes", 1, new Object[0])});
    L10:
        if (r6 != 2) goto L14;
        return LocaleController.formatString("AutoLockInTime", R.string.AutoLockInTime, new Object[]{LocaleController.formatPluralString("Minutes", 5, new Object[0])});
    L14:
        if (r6 != 3) goto L18;
        return LocaleController.formatString("AutoLockInTime", R.string.AutoLockInTime, new Object[]{LocaleController.formatPluralString("Hours", 1, new Object[0])});
    L18:
        if (r6 == 4) goto L20;
        if (r6 == 5) goto L23;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L23:
        return LocaleController.getString(R.string.AutoLockInstant);
    L20:
        return LocaleController.formatString("AutoLockInTime", R.string.AutoLockInTime, new Object[]{LocaleController.formatPluralString("Hours", 5, new Object[0])});
    }

    public static /* synthetic */ void $r8$lambda$IPzXRh0H6LtVDhUD0uEE4jDEMxM(PasscodeActivity r0, CodeNumberField r1, View r2, boolean r3) {
        r0.lambda$createView$11(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$L5MjXFFe7Cozve4Q7myLDGNxPdA(PasscodeActivity r0, View r1) {
        r0.lambda$createView$12(r1);
    }

    public static /* synthetic */ void $r8$lambda$Py884Vry9VAwuW0Vb4vhhB_HSfA(PasscodeActivity r0, View r1, boolean r2) {
        r0.lambda$createView$8(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Tp3JxLTTPF8dHOCcilFImd6_fFI(PasscodeActivity r0, Runnable r1) {
        r0.lambda$animateSuccessAnimation$15(r1);
    }

    public static /* synthetic */ void $r8$lambda$TxN4dt6j1vwDEMmOuMBJd5GMv78(PasscodeActivity r0) {
        r0.lambda$onPasscodeError$21();
    }

    public static /* synthetic */ void $r8$lambda$WAZfDLrGPnWWuLoMjqXfV6AwKdk(PasscodeActivity r0, int r1, boolean r2) {
        r0.lambda$createView$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$X9Zhllenqr1PeFPdnXX1_UjlZ68(PasscodeActivity r0) {
        r0.lambda$onPasscodeError$20();
    }

    /* renamed from: $r8$lambda$XKIorhx-l5YN9_hJ17rJOrTs7pI, reason: not valid java name */
    public static /* synthetic */ void m16356$r8$lambda$XKIorhxl5YN9_hJ17rJOrTs7pI(Context r0, View r1) {
        AlertsCreator.createForgotPasscodeDialog(r0).show();
    }

    public static /* synthetic */ void $r8$lambda$Z8RPzsNqmBy5B4KuaOCE4DIECNY(PasscodeActivity r0) {
        r0.lambda$new$0();
    }

    public static /* synthetic */ void $r8$lambda$g5axsS5QjmjKpSidutQy5E66wTY(PasscodeActivity r0) {
        r0.lambda$processDone$17();
    }

    /* renamed from: $r8$lambda$jZVeXl-OcvYnpwL7scZ9QVsNmL4, reason: not valid java name */
    public static /* synthetic */ boolean m16357$r8$lambda$jZVeXlOcvYnpwL7scZ9QVsNmL4(PasscodeActivity r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$createView$10(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$n6_WMZYde_DBAL8MqMBgU4wNvDY(CodeNumberField r1) {
        r1.animateSuccessProgress(1.0f);
    }

    public static /* synthetic */ View $r8$lambda$np0C7gHhM9gMV8nqRbwcHpAgkOk(Context r3) {
        TextView r0 = new TextView(r3);
        r0.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText6));
        r0.setGravity(1);
        r0.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
        r0.setTextSize(1, 15.0f);
        return r0;
    }

    public static /* synthetic */ void $r8$lambda$oBJUwC4Heux4nJl6Jiz5rBL3cLo(PasscodeActivity r0, boolean r1) {
        r0.lambda$updateFields$16(r1);
    }

    public static /* synthetic */ void $r8$lambda$pp5HAaeTYqEyARB40RdqUobvNOY(PasscodeActivity r0, AtomicBoolean r1, View r2) {
        r0.lambda$createView$9(r1, r2);
    }

    /* renamed from: $r8$lambda$tDxHHXa-NIHpguENqTPkvKacb1g, reason: not valid java name */
    public static /* synthetic */ void m16358$r8$lambda$tDxHHXaNIHpguENqTPkvKacb1g(PasscodeActivity r0) {
        r0.showKeyboard();
    }

    public static /* synthetic */ void $r8$lambda$tTXerf4JWzHCO0kKadUtIRWiVmk(PasscodeActivity r0) {
        r0.lambda$processDone$19();
    }

    /* renamed from: $r8$lambda$wGq5kahwtJ0HOlEiWk-SJFNQ5cU, reason: not valid java name */
    public static /* synthetic */ void m16359$r8$lambda$wGq5kahwtJ0HOlEiWkSJFNQ5cU(PasscodeActivity r0, View r1, int r2) {
        r0.lambda$createView$5(r1, r2);
    }

    /* renamed from: -$$Nest$fgetautoLockDetailRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16360$$Nest$fgetautoLockDetailRow(PasscodeActivity r0) {
        return r0.autoLockDetailRow;
    }

    /* renamed from: -$$Nest$fgetautoLockRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16361$$Nest$fgetautoLockRow(PasscodeActivity r0) {
        return r0.autoLockRow;
    }

    /* renamed from: -$$Nest$fgetcaptureDetailRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16362$$Nest$fgetcaptureDetailRow(PasscodeActivity r0) {
        return r0.captureDetailRow;
    }

    /* renamed from: -$$Nest$fgetcaptureHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16363$$Nest$fgetcaptureHeaderRow(PasscodeActivity r0) {
        return r0.captureHeaderRow;
    }

    /* renamed from: -$$Nest$fgetcaptureRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16364$$Nest$fgetcaptureRow(PasscodeActivity r0) {
        return r0.captureRow;
    }

    /* renamed from: -$$Nest$fgetchangePasscodeRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16365$$Nest$fgetchangePasscodeRow(PasscodeActivity r0) {
        return r0.changePasscodeRow;
    }

    /* renamed from: -$$Nest$fgetcodeFieldContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CodeFieldContainer m16366$$Nest$fgetcodeFieldContainer(PasscodeActivity r0) {
        return r0.codeFieldContainer;
    }

    /* renamed from: -$$Nest$fgetcurrentPasswordType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16367$$Nest$fgetcurrentPasswordType(PasscodeActivity r0) {
        return r0.currentPasswordType;
    }

    /* renamed from: -$$Nest$fgetdisablePasscodeRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16368$$Nest$fgetdisablePasscodeRow(PasscodeActivity r0) {
        return r0.disablePasscodeRow;
    }

    /* renamed from: -$$Nest$fgetfingerprintRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16369$$Nest$fgetfingerprintRow(PasscodeActivity r0) {
        return r0.fingerprintRow;
    }

    /* renamed from: -$$Nest$fgethidePasscodesDoNotMatch, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m16370$$Nest$fgethidePasscodesDoNotMatch(PasscodeActivity r0) {
        return r0.hidePasscodesDoNotMatch;
    }

    /* renamed from: -$$Nest$fgethintRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16371$$Nest$fgethintRow(PasscodeActivity r0) {
        return r0.hintRow;
    }

    /* renamed from: -$$Nest$fgetkeyboardView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CustomPhoneKeyboardView m16372$$Nest$fgetkeyboardView(PasscodeActivity r0) {
        return r0.keyboardView;
    }

    /* renamed from: -$$Nest$fgetpasscodeSetStep, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16373$$Nest$fgetpasscodeSetStep(PasscodeActivity r0) {
        return r0.passcodeSetStep;
    }

    /* renamed from: -$$Nest$fgetpasswordButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageView m16374$$Nest$fgetpasswordButton(PasscodeActivity r0) {
        return r0.passwordButton;
    }

    /* renamed from: -$$Nest$fgetpasswordEditText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditTextBoldCursor m16375$$Nest$fgetpasswordEditText(PasscodeActivity r0) {
        return r0.passwordEditText;
    }

    /* renamed from: -$$Nest$fgetpostedHidePasscodesDoNotMatch, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16376$$Nest$fgetpostedHidePasscodesDoNotMatch(PasscodeActivity r0) {
        return r0.postedHidePasscodesDoNotMatch;
    }

    /* renamed from: -$$Nest$fgetrowCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16377$$Nest$fgetrowCount(PasscodeActivity r0) {
        return r0.rowCount;
    }

    /* renamed from: -$$Nest$fgettype, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16378$$Nest$fgettype(PasscodeActivity r0) {
        return r0.type;
    }

    /* renamed from: -$$Nest$fgetutyanRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16379$$Nest$fgetutyanRow(PasscodeActivity r0) {
        return r0.utyanRow;
    }

    /* renamed from: -$$Nest$fputcurrentPasswordType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16380$$Nest$fputcurrentPasswordType(PasscodeActivity r0, int r1) {
        r0.currentPasswordType = r1;
    }

    /* renamed from: -$$Nest$misCustomKeyboardVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16381$$Nest$misCustomKeyboardVisible(PasscodeActivity r0) {
        return r0.isCustomKeyboardVisible();
    }

    /* renamed from: -$$Nest$misPinCode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16382$$Nest$misPinCode(PasscodeActivity r0) {
        return r0.isPinCode();
    }

    /* renamed from: -$$Nest$mprocessDone, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16383$$Nest$mprocessDone(PasscodeActivity r0) {
        r0.processDone();
    }

    /* renamed from: -$$Nest$mprocessNext, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16384$$Nest$mprocessNext(PasscodeActivity r0) {
        r0.processNext();
    }

    /* renamed from: -$$Nest$mshowKeyboard, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16385$$Nest$mshowKeyboard(PasscodeActivity r0) {
        r0.showKeyboard();
    }

    /* renamed from: -$$Nest$mupdateFields, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16386$$Nest$mupdateFields(PasscodeActivity r0) {
        r0.updateFields();
    }

    public PasscodeActivity(int r2) {
        this.currentPasswordType = 0;
        this.passcodeSetStep = 0;
        this.hidePasscodesDoNotMatch = new PasscodeActivity$$ExternalSyntheticLambda22(this);
        this.type = r2;
    }

    private void animateSuccessAnimation(Runnable r9) {
        if (isPinCode() == true) goto L6;
        r9.run();
        return;
    L6:
        int r0 = 0;
    L7:
        CodeFieldContainer r1 = this.codeFieldContainer;
        CodeNumberField[] r2 = r1.codeField;
        if (r0 >= r2.length) goto L10;
        CodeNumberField r12 = r2[r0];
        r12.postDelayed(new PasscodeActivity$$ExternalSyntheticLambda19(r12), r0 * 75);
        r0 = r0 + 1;
        goto L7
    L10:
        r1.postDelayed(new PasscodeActivity$$ExternalSyntheticLambda20(this, r9), (this.codeFieldContainer.codeField.length * 75) + 350);
    }

    public static BaseFragment determineOpenFragment() {
        if (SharedConfig.passcodeHash.isEmpty() == true) goto L7;
        return new PasscodeActivity(2);
    L7:
        return new ActionIntroActivity(6);
    }

    private boolean isCustomKeyboardVisible() {
        if (isPinCode() == true) goto L5;
        return false;
    L5:
        if (this.type != 0) goto L7;
        return false;
    L7:
        if (AndroidUtilities.isTablet() == true) goto L17;
        Point r1 = AndroidUtilities.displaySize;
        if (r1.x < r1.y) goto L11;
        return false;
    L11:
        if (AndroidUtilities.isAccessibilityTouchExplorationEnabled() == true) goto L19;
        return true;
    L19:
        return false;
    L17:
        return false;
    }

    private boolean isPassword() {
        int r0 = this.type;
        if (r0 != 1) goto L7;
        if (this.currentPasswordType != 1) goto L7;
    L10:
        return true;
    L7:
        if (r0 == 2) goto L9;
        return false;
    L9:
        if (SharedConfig.passcodeType == 1) goto L10;
        return false;
    }

    private boolean isPinCode() {
        int r0 = this.type;
        if (r0 != 1) goto L7;
        if (this.currentPasswordType != 0) goto L7;
    L10:
        return true;
    L7:
        if (r0 == 2) goto L9;
        return false;
    L9:
        if (SharedConfig.passcodeType == 0) goto L10;
        return false;
    }

    private /* synthetic */ void lambda$animateSuccessAnimation$15(Runnable r5) {
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

    private /* synthetic */ void lambda$createView$1(int r1, boolean r2) {
        if (r1 < AndroidUtilities.dp(20.0f)) goto L8;
        Runnable r12 = this.onShowKeyboardCallback;
        if (r12 == null) goto L9;
        r12.run();
        this.onShowKeyboardCallback = null;
        return;
    L9:
        return;
    }

    private /* synthetic */ boolean lambda$createView$10(TextView r1, int r2, KeyEvent r3) {
        int r12 = this.passcodeSetStep;
        if (r12 != 0) goto L6;
        processNext();
        return true;
    L6:
        if (r12 != 1) goto L9;
        processDone();
        return true;
    L9:
        return false;
    }

    private /* synthetic */ void lambda$createView$11(CodeNumberField r1, View r2, boolean r3) {
        this.keyboardView.setEditText(r1);
        this.keyboardView.setDispatchBackWhenEmpty(true);
    }

    private /* synthetic */ void lambda$createView$12(View r2) {
        int r22 = this.type;
        if (r22 != 1) goto L11;
        if (this.passcodeSetStep != 0) goto L8;
        processNext();
        return;
    L8:
        processDone();
        return;
    L11:
        if (r22 != 2) goto L14;
        processDone();
        return;
    }

    private /* synthetic */ void lambda$createView$2(AlertDialog r4, int r5) {
        SharedConfig.passcodeHash = _UrlKt.FRAGMENT_ENCODE_SET;
        SharedConfig.appLocked = false;
        SharedConfig.saveConfig();
        getMediaDataController().buildShortcuts();
        int r52 = this.listView.getChildCount();
        int r0 = 0;
    L3:
        if (r0 >= r52) goto L8;
        View r1 = this.listView.getChildAt(r0);
        if ((r1 instanceof TextSettingsCell) == true) goto L6;
        r0 = r0 + 1;
        goto L3
    L6:
        ((TextSettingsCell) r1).setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText7));
    L8:
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.didSetPasscode, new Object[0]);
        finishFragment();
    }

    private /* synthetic */ void lambda$createView$4(NumberPicker r1, int r2, AlertDialog r3, int r4) {
        int r12 = r1.getValue();
        if (r12 != 0) goto L6;
        SharedConfig.autoLockIn = 0;
    L20:
        this.listAdapter.notifyItemChanged(r2);
        UserConfig.getInstance(this.currentAccount).saveConfig(false);
        return;
    L6:
        if (r12 != 1) goto L9;
        SharedConfig.autoLockIn = 60;
        goto L20
    L9:
        if (r12 != 2) goto L12;
        SharedConfig.autoLockIn = 300;
        goto L20
    L12:
        if (r12 != 3) goto L15;
        SharedConfig.autoLockIn = 3600;
        goto L20
    L15:
        if (r12 != 4) goto L18;
        SharedConfig.autoLockIn = 18000;
        goto L20
    L18:
        if (r12 != 5) goto L20;
        SharedConfig.autoLockIn = Integer.MAX_VALUE;
        goto L20
    }

    private /* synthetic */ void lambda$createView$5(View r6, int r7) {
        if (r6.isEnabled() == true) goto L6;
        return;
    L6:
        if (r7 != this.disablePasscodeRow) goto L10;
        AlertDialog r5 = new AlertDialog.Builder(getParentActivity()).setTitle(LocaleController.getString(R.string.DisablePasscode)).setMessage(LocaleController.getString(R.string.DisablePasscodeConfirmMessage)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).setPositiveButton(LocaleController.getString(R.string.DisablePasscodeTurnOff), new PasscodeActivity$$ExternalSyntheticLambda15(this)).create();
        r5.show();
        ((TextView) r5.getButton(-1)).setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    L10:
        if (r7 != this.changePasscodeRow) goto L14;
        presentFragment(new PasscodeActivity(1));
        return;
    L14:
        if (r7 != this.autoLockRow) goto L39;
        if (getParentActivity() == null) goto L51;
        AlertDialog.Builder r62 = new AlertDialog.Builder(getParentActivity());
        r62.setTitle(LocaleController.getString(R.string.AutoLock));
        NumberPicker r0 = new NumberPicker(getParentActivity());
        r0.setMinValue(0);
        r0.setMaxValue(5);
        int r4 = SharedConfig.autoLockIn;
        if (r4 != 0) goto L22;
        r0.setValue(0);
    L36:
        r0.setFormatter(new PasscodeActivity$$ExternalSyntheticLambda16());
        r62.setView(r0);
        r62.setNegativeButton(LocaleController.getString(R.string.Done), new PasscodeActivity$$ExternalSyntheticLambda17(this, r0, r7));
        showDialog(r62.create());
        return;
    L22:
        if (r4 != 60) goto L25;
        r0.setValue(1);
        goto L36
    L25:
        if (r4 != 300) goto L28;
        r0.setValue(2);
        goto L36
    L28:
        if (r4 != 3600) goto L31;
        r0.setValue(3);
        goto L36
    L31:
        if (r4 != 18000) goto L34;
        r0.setValue(4);
        goto L36
    L34:
        if (r4 != Integer.MAX_VALUE) goto L36;
        r0.setValue(5);
        goto L36
    L51:
        return;
    L39:
        if (r7 != this.fingerprintRow) goto L43;
        SharedConfig.useFingerprintLock = !SharedConfig.useFingerprintLock;
        UserConfig.getInstance(this.currentAccount).saveConfig(false);
        ((TextCheckCell) r6).setChecked(SharedConfig.useFingerprintLock);
        return;
    L43:
        if (r7 != this.captureRow) goto L48;
        SharedConfig.allowScreenCapture = !SharedConfig.allowScreenCapture;
        UserConfig.getInstance(this.currentAccount).saveConfig(false);
        ((TextCheckCell) r6).setChecked(SharedConfig.allowScreenCapture);
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.didSetPasscode, new Object[]{Boolean.FALSE});
        if (SharedConfig.allowScreenCapture == true) goto L49;
        AlertsCreator.showSimpleAlert(this, LocaleController.getString(R.string.ScreenCaptureAlert));
        return;
    L49:
        return;
    }

    private /* synthetic */ void lambda$createView$8(View r1, boolean r2) {
        OutlineTextContainerView r0 = this.outlinePasswordView;
        if (r2 == false) goto L5;
        float r12 = 1.0f;
    L6:
        r0.animateSelection(r12);
        return;
    L5:
        r12 = 0.0f;
        goto L6
    }

    private /* synthetic */ void lambda$createView$9(AtomicBoolean r4, View r5) {
        r4.set(!r4.get());
        int r52 = this.passwordEditText.getSelectionStart();
        int r0 = this.passwordEditText.getSelectionEnd();
        EditTextBoldCursor r1 = this.passwordEditText;
        if (r4.get() == false) goto L5;
        int r2 = 144;
    L6:
        r1.setInputType(r2 | 1);
        this.passwordEditText.setSelection(r52, r0);
        ImageView r3 = this.passwordButton;
        if (r4.get() == false) goto L9;
        int r42 = Theme.key_windowBackgroundWhiteInputFieldActivated;
    L10:
        r3.setColorFilter(Theme.getColor(r42));
        return;
    L9:
        r42 = Theme.key_windowBackgroundWhiteHintText;
        goto L10
    L5:
        r2 = 128;
        goto L6
    }

    private /* synthetic */ void lambda$new$0() {
        this.postedHidePasscodesDoNotMatch = false;
        AndroidUtilities.updateViewVisibilityAnimated(this.passcodesDoNotMatchTextView, false);
    }

    private /* synthetic */ void lambda$onPasscodeError$20() {
        if (isPinCode() == false) goto L8;
        CodeNumberField[] r4 = this.codeFieldContainer.codeField;
        int r0 = r4.length;
        int r2 = 0;
    L5:
        if (r2 >= r0) goto L7;
        r4[r2].animateErrorProgress(0.0f);
        r2 = r2 + 1;
        goto L5
    L7:
        return;
    L8:
        this.outlinePasswordView.animateError(0.0f);
    }

    private /* synthetic */ void lambda$onPasscodeError$21() {
        PasscodeActivity$$ExternalSyntheticLambda21 r0 = new PasscodeActivity$$ExternalSyntheticLambda21(this);
        if (isPinCode() == false) goto L5;
        long r1 = 150;
    L6:
        AndroidUtilities.runOnUIThread(r0, r1);
        return;
    L5:
        r1 = 1000;
        goto L6
    }

    private /* synthetic */ void lambda$processDone$17() {
        this.codeFieldContainer.postDelayed(this.hidePasscodesDoNotMatch, 3000);
        this.postedHidePasscodesDoNotMatch = true;
    }

    private /* synthetic */ void lambda$processDone$18(boolean r3) {
        getMediaDataController().buildShortcuts();
        if (r3 == false) goto L7;
        presentFragment(new PasscodeActivity(0), true);
        Runnable r32 = this.openedSettings;
        if (r32 == null) goto L8;
        AndroidUtilities.runOnUIThread(r32);
        this.openedSettings = null;
    L8:
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.didSetPasscode, new Object[0]);
        return;
    L7:
        finishFragment();
        goto L8
    }

    private /* synthetic */ void lambda$processDone$19() {
        presentFragment(new PasscodeActivity(0), true);
        Runnable r0 = this.openedSettings;
        if (r0 == null) goto L6;
        AndroidUtilities.runOnUIThread(r0);
        this.openedSettings = null;
        return;
    }

    private /* synthetic */ void lambda$setCustomKeyboardVisible$13(ValueAnimator r3) {
        float r32 = ((Float) r3.getAnimatedValue()).floatValue();
        this.keyboardView.setAlpha(r32);
        this.keyboardView.setTranslationY(((1.0f - r32) * AndroidUtilities.dp(230.0f)) * 0.75f);
        this.fragmentView.requestLayout();
    }

    private /* synthetic */ void lambda$updateFields$16(boolean r3) {
        this.floatingButton.setButtonVisible(true, r3);
        AndroidUtilities.cancelRunOnUIThread(this.onShowKeyboardCallback);
    }

    private void onPasscodeError() {
        if (getParentActivity() != null) goto L23;
        return;
    L23:
        this.fragmentView.performHapticFeedback(3, 2);     // Catch: Exception -> L22
    L7:
        if (isPinCode() == false) goto L11;
        CodeNumberField[] r0 = this.codeFieldContainer.codeField;
        int r2 = r0.length;
        int r3 = 0;
    L9:
        if (r3 >= r2) goto L13;
        r0[r3].animateErrorProgress(1.0f);
        r3 = r3 + 1;
    L13:
        if (isPinCode() == false) goto L15;
        View r02 = this.codeFieldContainer;
    L17:
        if (isPinCode() == false) goto L19;
        float r1 = 10.0f;
    L20:
        AndroidUtilities.shakeViewSpring(r02, r1, new PasscodeActivity$$ExternalSyntheticLambda14(this));
        return;
    L19:
        r1 = 4.0f;
        goto L20
    L15:
        r02 = this.outlinePasswordView;
        goto L17
    L11:
        this.outlinePasswordView.animateError(1.0f);
        goto L13
    }

    private void processDone() {
        if (isPassword() == false) goto L9;
        if (this.passwordEditText.getText().length() != 0) goto L9;
        onPasscodeError();
        return;
    L9:
        if (isPinCode() == false) goto L11;
        String r0 = this.codeFieldContainer.getCode();
    L12:
        int r1 = this.type;
        int r5 = 0;
        if (r1 != 1) goto L35;
        if (this.firstPassword.equals(r0) == true) goto L24;
        AndroidUtilities.updateViewVisibilityAnimated(this.passcodesDoNotMatchTextView, true);
        CodeNumberField[] r02 = this.codeFieldContainer.codeField;
        int r12 = r02.length;
        int r2 = 0;
    L17:
        if (r2 >= r12) goto L20;
        r02[r2].setText(_UrlKt.FRAGMENT_ENCODE_SET);
        r2 = r2 + 1;
        goto L17
    L20:
        if (isPinCode() == false) goto L22;
        this.codeFieldContainer.codeField[0].requestFocus();
    L22:
        this.passwordEditText.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        onPasscodeError();
        this.codeFieldContainer.removeCallbacks(this.hidePasscodesDoNotMatch);
        this.codeFieldContainer.post(new PasscodeActivity$$ExternalSyntheticLambda11(this));
        return;
    L24:
        boolean r03 = SharedConfig.passcodeHash.isEmpty();
        SharedConfig.passcodeSalt = new byte[16];     // Catch: Exception -> L27
        Utilities.random.nextBytes(SharedConfig.passcodeSalt);     // Catch: Exception -> L27
        byte[] r3 = this.firstPassword.getBytes(StandardCharsets.UTF_8);     // Catch: Exception -> L27
        int r6 = r3.length + 32;     // Catch: Exception -> L27
        byte[] r7 = new byte[r6];     // Catch: Exception -> L27
        System.arraycopy(SharedConfig.passcodeSalt, 0, r7, 0, 16);     // Catch: Exception -> L27
        System.arraycopy(r3, 0, r7, 16, r3.length);     // Catch: Exception -> L27
        System.arraycopy(SharedConfig.passcodeSalt, 0, r7, r3.length + 16, 16);     // Catch: Exception -> L27
        SharedConfig.passcodeHash = Utilities.bytesToHex(Utilities.computeSHA256(r7, 0, r6));     // Catch: Exception -> L27
    L29:
        SharedConfig.allowScreenCapture = true;
        SharedConfig.passcodeType = this.currentPasswordType;
        SharedConfig.saveConfig();
        this.passwordEditText.clearFocus();
        AndroidUtilities.hideKeyboard(this.passwordEditText);
        CodeNumberField[] r13 = this.codeFieldContainer.codeField;
        int r32 = r13.length;
    L30:
        if (r5 >= r32) goto L32;
        CodeNumberField r4 = r13[r5];
        r4.clearFocus();
        AndroidUtilities.hideKeyboard(r4);
        r5 = r5 + 1;
        goto L30
    L32:
        this.keyboardView.setEditText(null);
        animateSuccessAnimation(new PasscodeActivity$$ExternalSyntheticLambda12(this, r03));
        return;
    L27:
        e = move-exception;
        FileLog.e(e);
        goto L29
    L35:
        if (r1 != 2) goto L68;
        long r62 = SharedConfig.passcodeRetryInMs;
        if (r62 <= 0) goto L47;
        Toast.makeText(getParentActivity(), LocaleController.formatString("TooManyTries", R.string.TooManyTries, new Object[]{LocaleController.formatPluralString("Seconds", Math.max(1, (int) Math.ceil(r62 / 1000.0d)), new Object[0])}), 0).show();
        CodeNumberField[] r04 = this.codeFieldContainer.codeField;
        int r14 = r04.length;
        int r22 = 0;
    L39:
        if (r22 >= r14) goto L41;
        r04[r22].setText(_UrlKt.FRAGMENT_ENCODE_SET);
        r22 = r22 + 1;
        goto L39
    L41:
        this.passwordEditText.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        if (isPinCode() == false) goto L44;
        this.codeFieldContainer.codeField[0].requestFocus();
    L44:
        onPasscodeError();
        return;
    L47:
        if (SharedConfig.checkPasscode(r0) == true) goto L56;
        SharedConfig.increaseBadPasscodeTries();
        this.passwordEditText.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        CodeNumberField[] r05 = this.codeFieldContainer.codeField;
        int r15 = r05.length;
        int r23 = 0;
    L49:
        if (r23 >= r15) goto L52;
        r05[r23].setText(_UrlKt.FRAGMENT_ENCODE_SET);
        r23 = r23 + 1;
        goto L49
    L52:
        if (isPinCode() == false) goto L54;
        this.codeFieldContainer.codeField[0].requestFocus();
    L54:
        onPasscodeError();
        return;
    L56:
        SharedConfig.badPasscodeTries = 0;
        SharedConfig.saveConfig();
        this.passwordEditText.clearFocus();
        AndroidUtilities.hideKeyboard(this.passwordEditText);
        CodeNumberField[] r06 = this.codeFieldContainer.codeField;
        int r16 = r06.length;
    L57:
        if (r5 >= r16) goto L59;
        CodeNumberField r33 = r06[r5];
        r33.clearFocus();
        AndroidUtilities.hideKeyboard(r33);
        r5 = r5 + 1;
        goto L57
    L59:
        this.keyboardView.setEditText(null);
        animateSuccessAnimation(new PasscodeActivity$$ExternalSyntheticLambda13(this));
        return;
    L68:
        return;
    L11:
        r0 = this.passwordEditText.getText().toString();
        goto L12
    }

    private void processNext() {
        if (this.currentPasswordType != 1) goto L7;
        if (this.passwordEditText.getText().length() != 0) goto L7;
    L10:
        onPasscodeError();
        return;
    L7:
        if (this.currentPasswordType == 0) goto L9;
    L12:
        ActionBarMenuItem r0 = this.otherItem;
        if (r0 == null) goto L15;
        r0.setVisibility(8);
    L15:
        this.titleTextView.setText(LocaleController.getString(R.string.ConfirmCreatePasscode));
        this.descriptionTextSwitcher.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.PasscodeReinstallNotice)));
        if (isPinCode() == false) goto L18;
        String r02 = this.codeFieldContainer.getCode();
    L19:
        this.firstPassword = r02;
        this.passwordEditText.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        this.passwordEditText.setInputType(524417);
        CodeNumberField[] r03 = this.codeFieldContainer.codeField;
        int r3 = r03.length;
        int r4 = 0;
    L20:
        if (r4 >= r3) goto L22;
        r03[r4].setText(_UrlKt.FRAGMENT_ENCODE_SET);
        r4 = r4 + 1;
        goto L20
    L22:
        showKeyboard();
        this.passcodeSetStep = 1;
        return;
    L18:
        r02 = this.passwordEditText.getText().toString();
        goto L19
    L9:
        if (this.codeFieldContainer.getCode().length() == 4) goto L12;
        goto L10
    }

    private void setCustomKeyboardVisible(boolean r4, boolean r5) {
        if (r4 == false) goto L4;
        AndroidUtilities.hideKeyboard(this.fragmentView);
        AndroidUtilities.requestAltFocusable(getParentActivity(), this.classGuid);
    L5:
        int r0 = 0;
        float r1 = 1.0f;
        float r2 = 0.0f;
        if (r5 == true) goto L21;
        CustomPhoneKeyboardView r52 = this.keyboardView;
        if (r4 == true) goto L11;
        r0 = 8;
    L11:
        r52.setVisibility(r0);
        CustomPhoneKeyboardView r53 = this.keyboardView;
        if (r4 == true) goto L15;
        r1 = 0.0f;
    L15:
        r53.setAlpha(r1);
        CustomPhoneKeyboardView r54 = this.keyboardView;
        if (r4 == true) goto L19;
        r2 = AndroidUtilities.dp(230.0f);
    L19:
        r54.setTranslationY(r2);
        this.fragmentView.requestLayout();
        return;
    L21:
        if (r4 == false) goto L23;
        float r55 = 0.0f;
    L24:
        if (r4 == true) goto L27;
        r1 = 0.0f;
    L27:
        ValueAnimator r56 = ValueAnimator.ofFloat(new float[]{r55, r1}).setDuration(150);
        if (r4 == false) goto L30;
        TimeInterpolator r02 = CubicBezierInterpolator.DEFAULT;
    L31:
        r56.setInterpolator(r02);
        r56.addUpdateListener(new PasscodeActivity$$ExternalSyntheticLambda10(this));
        r56.addListener(new AnonymousClass10(this, r4));
        r56.start();
        return;
    L30:
        r02 = Easings.easeInOutQuad;
        goto L31
    L23:
        r55 = 1.0f;
        goto L24
    L4:
        AndroidUtilities.removeAltFocusable(getParentActivity(), this.classGuid);
        goto L5
    }

    private void showKeyboard() {
        if (isPinCode() == false) goto L9;
        this.codeFieldContainer.codeField[0].requestFocus();
        if (isCustomKeyboardVisible() == true) goto L12;
        AndroidUtilities.showKeyboard(this.codeFieldContainer.codeField[0]);
        return;
    L12:
        return;
    L9:
        if (isPassword() == false) goto L13;
        this.passwordEditText.requestFocus();
        AndroidUtilities.showKeyboard(this.passwordEditText);
        return;
    }

    private void updateFields() {
        if (this.type != 2) goto L6;
        String r0 = LocaleController.getString(R.string.EnterYourPasscodeInfo);
    L14:
        if (this.descriptionTextSwitcher.getCurrentView().getText().equals(r0) == false) goto L16;
    L18:
        boolean r02 = false;
    L20:
        if (this.type != 2) goto L23;
        this.descriptionTextSwitcher.setText(LocaleController.getString(R.string.EnterYourPasscodeInfo), r02);
    L30:
        if (isPinCode() == false) goto L33;
        AndroidUtilities.updateViewVisibilityAnimated(this.codeFieldContainer, true, 1.0f, r02);
        AndroidUtilities.updateViewVisibilityAnimated(this.outlinePasswordView, false, 1.0f, r02);
    L36:
        if (isPassword() == false) goto L38;
        PasscodeActivity$$ExternalSyntheticLambda18 r1 = new PasscodeActivity$$ExternalSyntheticLambda18(this, r02);
        this.onShowKeyboardCallback = r1;
        AndroidUtilities.runOnUIThread(r1, 3000);
    L39:
        setCustomKeyboardVisible(isCustomKeyboardVisible(), r02);
        showKeyboard();
        return;
    L38:
        this.floatingButton.setButtonVisible(false, r02);
        goto L39
    L33:
        if (isPassword() == false) goto L36;
        AndroidUtilities.updateViewVisibilityAnimated(this.codeFieldContainer, false, 1.0f, r02);
        AndroidUtilities.updateViewVisibilityAnimated(this.outlinePasswordView, true, 1.0f, r02);
        goto L36
    L23:
        if (this.passcodeSetStep != 0) goto L30;
        TextViewSwitcher r12 = this.descriptionTextSwitcher;
        if (this.currentPasswordType != 0) goto L27;
        int r4 = R.string.CreatePasscodeInfoPIN;
    L28:
        r12.setText(LocaleController.getString(r4), r02);
        goto L30
    L27:
        r4 = R.string.CreatePasscodeInfoPassword;
        goto L28
    L16:
        if (TextUtils.isEmpty(this.descriptionTextSwitcher.getCurrentView().getText()) == true) goto L18;
        r02 = true;
        goto L20
    L6:
        if (this.passcodeSetStep == 0) goto L8;
        r0 = this.descriptionTextSwitcher.getCurrentView().getText().toString();
        goto L14
    L8:
        if (this.currentPasswordType != 0) goto L10;
        int r03 = R.string.CreatePasscodeInfoPIN;
    L11:
        r0 = LocaleController.getString(r03);
        goto L14
    L10:
        r03 = R.string.CreatePasscodeInfoPassword;
        goto L11
    }

    private void updateRows() {
        this.fingerprintRow = -1;
        this.utyanRow = 0;
        int r1 = 1 + 1;
        this.hintRow = 1;
        this.rowCount = r1 + 1;
        this.changePasscodeRow = r1;
    L9:
        th = move-exception;
        FileLog.e(th);
    L11:
        int r0 = this.rowCount;
        this.autoLockRow = r0;
        this.autoLockDetailRow = r0 + 1;
        this.captureHeaderRow = r0 + 2;
        this.captureRow = r0 + 3;
        int r12 = r0 + 5;
        this.captureDetailRow = r0 + 4;
        this.rowCount = r0 + 6;
        this.disablePasscodeRow = r12;
        return;
    L4:
        if (BiometricManager.from(ApplicationLoader.applicationContext).canAuthenticate(15) != 0) goto L11;
        if (AndroidUtilities.isKeyguardSecure() == false) goto L11;
        int r02 = this.rowCount;     // Catch: Throwable -> L9
        this.rowCount = r02 + 1;     // Catch: Throwable -> L9
        this.fingerprintRow = r02;     // Catch: Throwable -> L9
        goto L11
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r28) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(false);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        FrameLayout r2 = new FrameLayout(r28);
        if (this.type != 0) goto L5;
        FrameLayout r4 = r2;
    L6:
        AnonymousClass2 r8 = new AnonymousClass2(this, r28, r4);
        r8.setDelegate(new PasscodeActivity$$ExternalSyntheticLambda0(this));
        this.fragmentView = r8;
        r8.addView(r4, LayoutHelper.createLinear(-1, 0, 1.0f));
        CustomPhoneKeyboardView r42 = new CustomPhoneKeyboardView(r28);
        this.keyboardView = r42;
        if (isCustomKeyboardVisible() == false) goto L9;
        int r10 = 0;
    L10:
        r42.setVisibility(r10);
        r8.addView(this.keyboardView, LayoutHelper.createLinear(-1, 230));
        int r43 = this.type;
        if (r43 != 0) goto L13;
        this.actionBar.setTitle(LocaleController.getString(R.string.Passcode));
        int r44 = Theme.key_windowBackgroundGray;
        r2.setTag(Integer.valueOf(r44));
        r2.setBackgroundColor(Theme.getColor(r44));
        RecyclerListView r45 = new RecyclerListView(r28);
        this.listView = r45;
        r45.setSections();
        this.actionBar.setAdaptiveBackground(this.listView);
        this.listView.setLayoutManager(new AnonymousClass3(this, r28, 1, false));
        this.listView.setVerticalScrollBarEnabled(false);
        this.listView.setItemAnimator(null);
        this.listView.setLayoutAnimation(null);
        r2.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        RecyclerListView r22 = this.listView;
        ListAdapter r3 = new ListAdapter(this, r28);
        this.listAdapter = r3;
        r22.setAdapter(r3);
        this.listView.setOnItemClickListener(new PasscodeActivity$$ExternalSyntheticLambda1(this));
    L67:
        return this.fragmentView;
    L13:
        if (r43 == 1) goto L16;
        if (r43 != 2) goto L67;
    L16:
        ActionBar r46 = this.actionBar;
        if (r46 == null) goto L23;
        r46.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setItemsColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText), false);
        this.actionBar.setItemsBackgroundColor(Theme.getColor(Theme.key_actionBarWhiteSelector), false);
        this.actionBar.setCastShadows(false);
        ActionBarMenu r47 = this.actionBar.createMenu();
        if (this.type != 1) goto L21;
        ActionBarMenuItem r48 = r47.addItem(0, R.drawable.ic_ab_other);
        this.otherItem = r48;
        ActionBarMenuSubItem r49 = r48.addSubItem(1, R.drawable.msg_permissions, LocaleController.getString(R.string.PasscodeSwitchToPassword));
    L22:
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass4(this, r49));
        goto L23
    L21:
        r49 = null;
    L23:
        FrameLayout r410 = new FrameLayout(r28);
        LinearLayout r13 = new LinearLayout(r28);
        r13.setOrientation(1);
        r13.setGravity(1);
        r2.addView(r13, LayoutHelper.createFrame(-1, -1.0f));
        RLottieImageView r82 = new RLottieImageView(r28);
        this.lockImageView = r82;
        r82.setFocusable(false);
        this.lockImageView.setAnimation(R.raw.tsv_setup_intro, 120, 120);
        this.lockImageView.setAutoRepeat(false);
        this.lockImageView.playAnimation();
        RLottieImageView r83 = this.lockImageView;
        if (AndroidUtilities.isSmallScreen() == true) goto L28;
        Point r14 = AndroidUtilities.displaySize;
        if (r14.x >= r14.y) goto L28;
        int r11 = 0;
    L29:
        r83.setVisibility(r11);
        r13.addView(this.lockImageView, LayoutHelper.createLinear(120, 120, 1));
        TextView r84 = new TextView(r28);
        this.titleTextView = r84;
        int r112 = Theme.key_windowBackgroundWhiteBlackText;
        r84.setTextColor(Theme.getColor(r112));
        this.titleTextView.setTypeface(AndroidUtilities.bold());
        if (this.type != 1) goto L35;
        boolean r85 = SharedConfig.passcodeHash.isEmpty();
        TextView r142 = this.titleTextView;
        if (r85 == true) goto L34;
        r142.setText(LocaleController.getString(R.string.EnterNewPasscode));
    L36:
        this.titleTextView.setTextSize(1, 18.0f);
        this.titleTextView.setGravity(1);
        r13.addView(this.titleTextView, LayoutHelper.createLinear(-2, -2, 1, 0, 16, 0, 0));
        TextViewSwitcher r86 = new TextViewSwitcher(r28);
        this.descriptionTextSwitcher = r86;
        r86.setFactory(new PasscodeActivity$$ExternalSyntheticLambda2(r28));
        this.descriptionTextSwitcher.setInAnimation(r28, R.anim.alpha_in);
        this.descriptionTextSwitcher.setOutAnimation(r28, R.anim.alpha_out);
        r13.addView(this.descriptionTextSwitcher, LayoutHelper.createLinear(-2, -2, 1, 20, 8, 20, 0));
        TextView r87 = new TextView(r28);
        r87.setTextSize(1, 14.0f);
        r87.setTextColor(Theme.getColor(Theme.key_featuredStickers_addButton));
        r87.setPadding(AndroidUtilities.dp(32.0f), 0, AndroidUtilities.dp(32.0f), 0);
        if (isPassword() == false) goto L39;
        int r5 = 3;
    L40:
        r87.setGravity(r5 | 16);
        r87.setOnClickListener(new PasscodeActivity$$ExternalSyntheticLambda3(r28));
        if (this.type != 2) goto L43;
        int r52 = 0;
    L44:
        r87.setVisibility(r52);
        r87.setText(LocaleController.getString(R.string.ForgotPasscode));
        r2.addView(r87, LayoutHelper.createFrame(-1, 56.0f, 81, 0.0f, 0.0f, 0.0f, 16.0f));
        VerticalPositionAutoAnimator.attach(r87);
        TextView r53 = new TextView(r28);
        this.passcodesDoNotMatchTextView = r53;
        r53.setTextSize(1, 14.0f);
        this.passcodesDoNotMatchTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText6));
        this.passcodesDoNotMatchTextView.setText(LocaleController.getString(R.string.PasscodesDoNotMatchTryAgain));
        this.passcodesDoNotMatchTextView.setPadding(0, AndroidUtilities.dp(12.0f), 0, AndroidUtilities.dp(12.0f));
        AndroidUtilities.updateViewVisibilityAnimated(this.passcodesDoNotMatchTextView, false, 1.0f, false);
        r2.addView(this.passcodesDoNotMatchTextView, LayoutHelper.createFrame(-2, -2.0f, 81, 0.0f, 0.0f, 0.0f, 16.0f));
        OutlineTextContainerView r54 = new OutlineTextContainerView(r28);
        this.outlinePasswordView = r54;
        r54.setText(LocaleController.getString(R.string.EnterPassword));
        EditTextBoldCursor r55 = new EditTextBoldCursor(r28);
        this.passwordEditText = r55;
        r55.setInputType(524417);
        this.passwordEditText.setTextSize(1, 18.0f);
        this.passwordEditText.setTextColor(Theme.getColor(r112));
        this.passwordEditText.setBackground(null);
        this.passwordEditText.setMaxLines(1);
        this.passwordEditText.setLines(1);
        EditTextBoldCursor r56 = this.passwordEditText;
        if (LocaleController.isRTL == false) goto L47;
        int r88 = 5;
    L48:
        r56.setGravity(r88);
        this.passwordEditText.setSingleLine(true);
        if (this.type != 1) goto L51;
        this.passcodeSetStep = 0;
        this.passwordEditText.setImeOptions(5);
    L52:
        this.passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        this.passwordEditText.setTypeface(Typeface.DEFAULT);
        this.passwordEditText.setCursorColor(Theme.getColor(Theme.key_windowBackgroundWhiteInputFieldActivated));
        this.passwordEditText.setCursorSize(AndroidUtilities.dp(20.0f));
        this.passwordEditText.setCursorWidth(1.5f);
        int r57 = AndroidUtilities.dp(16.0f);
        this.passwordEditText.setPadding(r57, r57, r57, r57);
        this.passwordEditText.setOnFocusChangeListener(new PasscodeActivity$$ExternalSyntheticLambda4(this));
        LinearLayout r58 = new LinearLayout(r28);
        r58.setOrientation(0);
        r58.setGravity(16);
        r58.addView(this.passwordEditText, LayoutHelper.createLinear(0, -2, 1.0f));
        ImageView r6 = new ImageView(r28);
        this.passwordButton = r6;
        r6.setImageResource(R.drawable.msg_message);
        this.passwordButton.setColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteHintText));
        this.passwordButton.setBackground(Theme.createSelectorDrawable(getThemedColor(Theme.key_listSelector), 1));
        ImageView r62 = this.passwordButton;
        if (this.type == 1) goto L55;
    L57:
        boolean r89 = false;
    L58:
        AndroidUtilities.updateViewVisibilityAnimated(r62, r89, 0.1f, false);
        AtomicBoolean r63 = new AtomicBoolean(false);
        this.passwordEditText.addTextChangedListener(new AnonymousClass5(this, r63));
        this.passwordButton.setOnClickListener(new PasscodeActivity$$ExternalSyntheticLambda5(this, r63));
        r58.addView(this.passwordButton, LayoutHelper.createLinearRelatively(24.0f, 24.0f, 0, 0.0f, 0.0f, 14.0f, 0.0f));
        this.outlinePasswordView.addView(r58, LayoutHelper.createFrame(-1, -2.0f));
        r410.addView(this.outlinePasswordView, LayoutHelper.createLinear(-1, -2, 1, 32, 0, 32, 0));
        this.passwordEditText.setOnEditorActionListener(new PasscodeActivity$$ExternalSyntheticLambda6(this));
        this.passwordEditText.addTextChangedListener(new AnonymousClass6(this));
        this.passwordEditText.setCustomSelectionActionModeCallback(new AnonymousClass7(this));
        AnonymousClass8 r59 = new AnonymousClass8(this, r28);
        this.codeFieldContainer = r59;
        r59.setNumbersCount(4, 10);
        CodeNumberField[] r510 = this.codeFieldContainer.codeField;
        int r64 = r510.length;
        int r810 = 0;
    L59:
        if (r810 >= r64) goto L61;
        CodeNumberField r9 = r510[r810];
        r9.setShowSoftInputOnFocusCompat(!isCustomKeyboardVisible());
        r9.setTransformationMethod(PasswordTransformationMethod.getInstance());
        r9.setTextSize(1, 24.0f);
        r9.addTextChangedListener(new AnonymousClass9(this));
        r9.setOnFocusChangeListener(new PasscodeActivity$$ExternalSyntheticLambda7(this, r9));
        r810 = r810 + 1;
        goto L59
    L61:
        r410.addView(this.codeFieldContainer, LayoutHelper.createFrame(-2, -2.0f, 1, 40.0f, 10.0f, 40.0f, 0.0f));
        r13.addView(r410, LayoutHelper.createLinear(-1, -2, 1, 0, 32, 0, 72));
        if (this.type != 1) goto L64;
        r2.setTag(Integer.valueOf(Theme.key_windowBackgroundWhite));
    L64:
        FragmentFloatingButton r411 = new FragmentFloatingButton(r28, this.resourceProvider);
        this.floatingButton = r411;
        VerticalPositionAutoAnimator.attach(r411);
        r2.addView(this.floatingButton, FragmentFloatingButton.createDefaultLayoutParamsBig());
        this.floatingButton.setOnClickListener(new PasscodeActivity$$ExternalSyntheticLambda8(this));
        TransformableLoginButtonView r23 = new TransformableLoginButtonView(r28);
        r23.setTransformType(1);
        r23.setProgress(0.0f);
        r23.setColor(Theme.getColor(Theme.key_chats_actionIcon));
        r23.setDrawBackground(false);
        this.floatingButton.setContentDescription(LocaleController.getString(R.string.Next));
        this.floatingButton.addView(r23, LayoutHelper.createFrame(56, 56, 17));
        FragmentFloatingButton r1 = this.floatingButton;
        r1.addAdditionalView(r1);
        updateFields();
        goto L67
    L55:
        if (this.passcodeSetStep != 0) goto L57;
        r89 = true;
        goto L58
    L51:
        this.passcodeSetStep = 1;
        this.passwordEditText.setImeOptions(6);
        goto L52
    L47:
        r88 = 3;
        goto L48
    L43:
        r52 = 8;
        goto L44
    L39:
        r5 = 1;
        goto L40
    L34:
        r142.setText(LocaleController.getString(R.string.CreatePasscode));
        goto L36
    L35:
        this.titleTextView.setText(LocaleController.getString(R.string.EnterYourPasscode));
    L28:
        r11 = 8;
        goto L29
    L9:
        r10 = 8;
        goto L10
    L5:
        ScrollView r412 = new ScrollView(r28);
        r412.addView(r2, LayoutHelper.createFrame(-1, -2.0f));
        r412.setFillViewport(true);
        r4 = r412;
        goto L6
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 == NotificationCenter.didSetPasscode) goto L5;
        return;
    L5:
        if (r3.length == 0) goto L9;
        if (((Boolean) r3[0]).booleanValue() == true) goto L9;
        return;
    L9:
        if (this.type != 0) goto L16;
        updateRows();
        ListAdapter r0 = this.listAdapter;
        if (r0 == null) goto L17;
        r0.notifyDataSetChanged();
        return;
    L17:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        int r19 = Theme.key_windowBackgroundWhite;
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{TextCheckCell.class, TextSettingsCell.class}, null, null, null, r19));
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND | ThemeDescription.FLAG_CHECKTAG, null, null, null, null, r19));
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND | ThemeDescription.FLAG_CHECKTAG, null, null, null, null, Theme.key_windowBackgroundGray));
        if (this.type == 0) goto L5;
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_actionBarDefault));
    L5:
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, Theme.key_actionBarDefault));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SUBMENUBACKGROUND, null, null, null, null, Theme.key_actionBarDefaultSubmenuBackground));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SUBMENUITEM, null, null, null, null, Theme.key_actionBarDefaultSubmenuItem));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SUBMENUITEM | ThemeDescription.FLAG_IMAGECOLOR, null, null, null, null, Theme.key_actionBarDefaultSubmenuItemIcon));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        r1.add(new ThemeDescription(this.titleTextView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_windowBackgroundWhiteGrayText6));
        EditTextBoldCursor r3 = this.passwordEditText;
        int r4 = ThemeDescription.FLAG_TEXTCOLOR;
        int r20 = Theme.key_windowBackgroundWhiteBlackText;
        r1.add(new ThemeDescription(r3, r4, null, null, null, null, r20));
        r1.add(new ThemeDescription(this.passwordEditText, ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, Theme.key_windowBackgroundWhiteInputField));
        r1.add(new ThemeDescription(this.passwordEditText, ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_DRAWABLESELECTEDSTATE, null, null, null, null, Theme.key_windowBackgroundWhiteInputFieldActivated));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"textView"}, null, null, null, r20));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"checkBox"}, null, null, null, Theme.key_switchTrack));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell.class}, new String[]{"checkBox"}, null, null, null, Theme.key_switchTrackChecked));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKTAG, new Class[]{TextSettingsCell.class}, new String[]{"textView"}, null, null, null, r20));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKTAG, new Class[]{TextSettingsCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText7));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSettingsCell.class}, new String[]{"valueTextView"}, null, null, null, Theme.key_windowBackgroundWhiteValueText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText4));
        return r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean hasForceLightStatusBar() {
        if (this.type == 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onConfigurationChanged(Configuration r5) {
        super.onConfigurationChanged(r5);
        int r0 = 0;
        setCustomKeyboardVisible(isCustomKeyboardVisible(), false);
        RLottieImageView r52 = this.lockImageView;
        if (r52 != null) goto L5;
    L11:
        CodeFieldContainer r53 = this.codeFieldContainer;
        if (r53 == null) goto L18;
        CodeNumberField[] r54 = r53.codeField;
        if (r54 == null) goto L20;
        int r1 = r54.length;
    L16:
        if (r0 >= r1) goto L21;
        r54[r0].setShowSoftInputOnFocusCompat(!isCustomKeyboardVisible());
        r0 = r0 + 1;
        goto L16
    L21:
        return;
    L20:
        return;
    L18:
        return;
    L5:
        if (AndroidUtilities.isSmallScreen() == true) goto L9;
        Point r12 = AndroidUtilities.displaySize;
        if (r12.x >= r12.y) goto L9;
        int r13 = 0;
    L10:
        r52.setVisibility(r13);
    L9:
        r13 = 8;
        goto L10
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        super.onFragmentCreate();
        updateRows();
        if (this.type != 0) goto L7;
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.didSetPasscode);
        return true;
    L7:
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        if (this.type != 0) goto L5;
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.didSetPasscode);
    L5:
        AndroidUtilities.removeAdjustResize(getParentActivity(), this.classGuid);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onPause() {
        super.onPause();
        AndroidUtilities.removeAltFocusable(getParentActivity(), this.classGuid);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        ListAdapter r0 = this.listAdapter;
        if (r0 == null) goto L6;
        r0.notifyDataSetChanged();
    L6:
        if (this.type != 0) goto L8;
    L10:
        AndroidUtilities.requestAdjustResize(getParentActivity(), this.classGuid);
        if (isCustomKeyboardVisible() == false) goto L14;
        AndroidUtilities.hideKeyboard(this.fragmentView);
        AndroidUtilities.requestAltFocusable(getParentActivity(), this.classGuid);
        return;
    L14:
        return;
    L8:
        if (isCustomKeyboardVisible() == true) goto L10;
        AndroidUtilities.runOnUIThread(new PasscodeActivity$$ExternalSyntheticLambda9(this), 200);
        goto L10
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationEnd(boolean r1, boolean r2) {
        if (r1 == true) goto L4;
        return;
    L4:
        if (this.type == 0) goto L8;
        showKeyboard();
        return;
    }

    public void setOnOpenedSettings(Runnable r1) {
        this.openedSettings = r1;
    }
}
