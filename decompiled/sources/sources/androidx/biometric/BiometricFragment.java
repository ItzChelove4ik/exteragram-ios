package androidx.biometric;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import androidx.biometric.BiometricPrompt;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.gms.cast.CastStatusCodes;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes3.dex */
public class BiometricFragment extends Fragment {
    Handler mHandler;
    BiometricViewModel mViewModel;

    /* renamed from: androidx.biometric.BiometricFragment$1, reason: invalid class name */
    public class AnonymousClass1 implements Observer<BiometricPrompt.AuthenticationResult> {
        final /* synthetic */ BiometricFragment this$0;

        public AnonymousClass1(BiometricFragment r1) {
            this.this$0 = r1;
        }

        /* renamed from: onChanged, reason: avoid collision after fix types in other method */
        public void onChanged2(BiometricPrompt.AuthenticationResult r2) {
            if (r2 == null) goto L5;
            this.this$0.onAuthenticationSucceeded(r2);
            this.this$0.mViewModel.setAuthenticationResult(null);
            return;
        }

        @Override // androidx.lifecycle.Observer
        public /* bridge */ /* synthetic */ void onChanged(BiometricPrompt.AuthenticationResult r1) {
            onChanged2(r1);
        }
    }

    /* renamed from: androidx.biometric.BiometricFragment$10, reason: invalid class name */
    public class AnonymousClass10 implements Runnable {
        final /* synthetic */ BiometricFragment this$0;
        final /* synthetic */ int val$errorCode;
        final /* synthetic */ CharSequence val$errorString;

        public AnonymousClass10(BiometricFragment r1, int r2, CharSequence r3) {
            this.this$0 = r1;
            this.val$errorCode = r2;
            this.val$errorString = r3;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.this$0.mViewModel.getClientCallback().onAuthenticationError(this.val$errorCode, this.val$errorString);
        }
    }

    /* renamed from: androidx.biometric.BiometricFragment$11, reason: invalid class name */
    public class AnonymousClass11 implements Runnable {
        final /* synthetic */ BiometricFragment this$0;

        public AnonymousClass11(BiometricFragment r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.this$0.mViewModel.getClientCallback().onAuthenticationFailed();
        }
    }

    /* renamed from: androidx.biometric.BiometricFragment$2, reason: invalid class name */
    public class AnonymousClass2 implements Observer<BiometricErrorData> {
        final /* synthetic */ BiometricFragment this$0;

        public AnonymousClass2(BiometricFragment r1) {
            this.this$0 = r1;
        }

        /* renamed from: onChanged, reason: avoid collision after fix types in other method */
        public void onChanged2(BiometricErrorData r3) {
            if (r3 == null) goto L5;
            this.this$0.onAuthenticationError(r3.getErrorCode(), r3.getErrorMessage());
            this.this$0.mViewModel.setAuthenticationError(null);
            return;
        }

        @Override // androidx.lifecycle.Observer
        public /* bridge */ /* synthetic */ void onChanged(BiometricErrorData r1) {
            onChanged2(r1);
        }
    }

    /* renamed from: androidx.biometric.BiometricFragment$3, reason: invalid class name */
    public class AnonymousClass3 implements Observer<CharSequence> {
        final /* synthetic */ BiometricFragment this$0;

        public AnonymousClass3(BiometricFragment r1) {
            this.this$0 = r1;
        }

        /* renamed from: onChanged, reason: avoid collision after fix types in other method */
        public void onChanged2(CharSequence r2) {
            if (r2 == null) goto L5;
            this.this$0.onAuthenticationHelp(r2);
            this.this$0.mViewModel.setAuthenticationError(null);
            return;
        }

        @Override // androidx.lifecycle.Observer
        public /* bridge */ /* synthetic */ void onChanged(CharSequence r1) {
            onChanged2(r1);
        }
    }

    /* renamed from: androidx.biometric.BiometricFragment$4, reason: invalid class name */
    public class AnonymousClass4 implements Observer<Boolean> {
        final /* synthetic */ BiometricFragment this$0;

        public AnonymousClass4(BiometricFragment r1) {
            this.this$0 = r1;
        }

        /* renamed from: onChanged, reason: avoid collision after fix types in other method */
        public void onChanged2(Boolean r1) {
            if (r1.booleanValue() == false) goto L6;
            this.this$0.onAuthenticationFailed();
            this.this$0.mViewModel.setAuthenticationFailurePending(false);
            return;
        }

        @Override // androidx.lifecycle.Observer
        public /* bridge */ /* synthetic */ void onChanged(Boolean r1) {
            onChanged2(r1);
        }
    }

    /* renamed from: androidx.biometric.BiometricFragment$5, reason: invalid class name */
    public class AnonymousClass5 implements Observer<Boolean> {
        final /* synthetic */ BiometricFragment this$0;

        public AnonymousClass5(BiometricFragment r1) {
            this.this$0 = r1;
        }

        /* renamed from: onChanged, reason: avoid collision after fix types in other method */
        public void onChanged2(Boolean r2) {
            if (r2.booleanValue() == false) goto L10;
            boolean r22 = this.this$0.isManagingDeviceCredentialButton();
            BiometricFragment r0 = this.this$0;
            if (r22 == false) goto L7;
            r0.onDeviceCredentialButtonPressed();
        L8:
            this.this$0.mViewModel.setNegativeButtonPressPending(false);
            return;
        L7:
            r0.onCancelButtonPressed();
            goto L8
        }

        @Override // androidx.lifecycle.Observer
        public /* bridge */ /* synthetic */ void onChanged(Boolean r1) {
            onChanged2(r1);
        }
    }

    /* renamed from: androidx.biometric.BiometricFragment$6, reason: invalid class name */
    public class AnonymousClass6 implements Observer<Boolean> {
        final /* synthetic */ BiometricFragment this$0;

        public AnonymousClass6(BiometricFragment r1) {
            this.this$0 = r1;
        }

        /* renamed from: onChanged, reason: avoid collision after fix types in other method */
        public void onChanged2(Boolean r2) {
            if (r2.booleanValue() == false) goto L6;
            this.this$0.cancelAuthentication(1);
            this.this$0.dismiss();
            this.this$0.mViewModel.setFingerprintDialogCancelPending(false);
            return;
        }

        @Override // androidx.lifecycle.Observer
        public /* bridge */ /* synthetic */ void onChanged(Boolean r1) {
            onChanged2(r1);
        }
    }

    /* renamed from: androidx.biometric.BiometricFragment$7, reason: invalid class name */
    public class AnonymousClass7 implements Runnable {
        final /* synthetic */ BiometricFragment this$0;

        public AnonymousClass7(BiometricFragment r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.this$0.mViewModel.setFingerprintDialogDismissedInstantly(false);
        }
    }

    /* renamed from: androidx.biometric.BiometricFragment$8, reason: invalid class name */
    public class AnonymousClass8 implements Runnable {
        final /* synthetic */ BiometricFragment this$0;
        final /* synthetic */ CharSequence val$errorString;
        final /* synthetic */ int val$knownErrorCode;

        public AnonymousClass8(BiometricFragment r1, int r2, CharSequence r3) {
            this.this$0 = r1;
            this.val$knownErrorCode = r2;
            this.val$errorString = r3;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.this$0.sendErrorAndDismiss(this.val$knownErrorCode, this.val$errorString);
        }
    }

    /* renamed from: androidx.biometric.BiometricFragment$9, reason: invalid class name */
    public class AnonymousClass9 implements Runnable {
        final /* synthetic */ BiometricFragment this$0;
        final /* synthetic */ BiometricPrompt.AuthenticationResult val$result;

        public AnonymousClass9(BiometricFragment r1, BiometricPrompt.AuthenticationResult r2) {
            this.this$0 = r1;
            this.val$result = r2;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.this$0.mViewModel.getClientCallback().onAuthenticationSucceeded(this.val$result);
        }
    }

    public static class Api21Impl {
        public static Intent createConfirmDeviceCredentialIntent(KeyguardManager r0, CharSequence r1, CharSequence r2) {
            return r0.createConfirmDeviceCredentialIntent(r1, r2);
        }
    }

    public static class Api28Impl {
        public static void authenticate(android.hardware.biometrics.BiometricPrompt r0, BiometricPrompt.CryptoObject r1, CancellationSignal r2, Executor r3, android.hardware.biometrics.BiometricPrompt$AuthenticationCallback r4) {
            r0.authenticate(r1, r2, r3, r4);
        }

        public static void authenticate(android.hardware.biometrics.BiometricPrompt r0, CancellationSignal r1, Executor r2, android.hardware.biometrics.BiometricPrompt$AuthenticationCallback r3) {
            r0.authenticate(r1, r2, r3);
        }

        public static android.hardware.biometrics.BiometricPrompt buildPrompt(BiometricPrompt.Builder r0) {
            return r0.build();
        }

        public static BiometricPrompt.Builder createPromptBuilder(Context r1) {
            return new BiometricPrompt.Builder(r1);
        }

        public static void setDescription(BiometricPrompt.Builder r0, CharSequence r1) {
            r0.setDescription(r1);
        }

        public static void setNegativeButton(BiometricPrompt.Builder r0, CharSequence r1, Executor r2, DialogInterface.OnClickListener r3) {
            r0.setNegativeButton(r1, r2, r3);
        }

        public static void setSubtitle(BiometricPrompt.Builder r0, CharSequence r1) {
            r0.setSubtitle(r1);
        }

        public static void setTitle(BiometricPrompt.Builder r0, CharSequence r1) {
            r0.setTitle(r1);
        }
    }

    public static class Api29Impl {
        public static void setConfirmationRequired(BiometricPrompt.Builder r0, boolean r1) {
            r0.setConfirmationRequired(r1);
        }

        public static void setDeviceCredentialAllowed(BiometricPrompt.Builder r0, boolean r1) {
            r0.setDeviceCredentialAllowed(r1);
        }
    }

    public static class Api30Impl {
        public static void setAllowedAuthenticators(BiometricPrompt.Builder r0, int r1) {
            r0.setAllowedAuthenticators(r1);
        }
    }

    public static class PromptExecutor implements Executor {
        private final Handler mPromptHandler;

        public PromptExecutor() {
            this.mPromptHandler = new Handler(Looper.getMainLooper());
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable r1) {
            this.mPromptHandler.post(r1);
        }
    }

    public static class ShowPromptForAuthenticationRunnable implements Runnable {
        private final WeakReference<BiometricFragment> mFragmentRef;

        public ShowPromptForAuthenticationRunnable(BiometricFragment r2) {
            this.mFragmentRef = new WeakReference(r2);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.mFragmentRef.get() == null) goto L6;
            this.mFragmentRef.get().showPromptForAuthentication();
            return;
        }
    }

    public static class StopDelayingPromptRunnable implements Runnable {
        private final WeakReference<BiometricViewModel> mViewModelRef;

        public StopDelayingPromptRunnable(BiometricViewModel r2) {
            this.mViewModelRef = new WeakReference(r2);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.mViewModelRef.get() == null) goto L6;
            this.mViewModelRef.get().setDelayingPrompt(false);
            return;
        }
    }

    public static class StopIgnoringCancelRunnable implements Runnable {
        private final WeakReference<BiometricViewModel> mViewModelRef;

        public StopIgnoringCancelRunnable(BiometricViewModel r2) {
            this.mViewModelRef = new WeakReference(r2);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.mViewModelRef.get() == null) goto L6;
            this.mViewModelRef.get().setIgnoringCancel(false);
            return;
        }
    }

    public BiometricFragment() {
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    private static int checkForFingerprintPreAuthenticationErrors(FingerprintManagerCompat r1) {
        if (r1.isHardwareDetected() == true) goto L7;
        return 12;
    L7:
        if (r1.hasEnrolledFingerprints() == true) goto L10;
        return 11;
    L10:
        return 0;
    }

    private void connectViewModel() {
        if (getActivity() != null) goto L5;
        return;
    L5:
        BiometricViewModel r0 = (BiometricViewModel) new ViewModelProvider(getActivity()).get(BiometricViewModel.class);
        this.mViewModel = r0;
        r0.getAuthenticationResult().observe(this, new AnonymousClass1(this));
        this.mViewModel.getAuthenticationError().observe(this, new AnonymousClass2(this));
        this.mViewModel.getAuthenticationHelpMessage().observe(this, new AnonymousClass3(this));
        this.mViewModel.isAuthenticationFailurePending().observe(this, new AnonymousClass4(this));
        this.mViewModel.isNegativeButtonPressPending().observe(this, new AnonymousClass5(this));
        this.mViewModel.isFingerprintDialogCancelPending().observe(this, new AnonymousClass6(this));
    }

    private void dismissFingerprintDialog() {
        this.mViewModel.setPromptShowing(false);
        if (isAdded() == false) goto L12;
        FragmentManager r2 = getParentFragmentManager();
        FingerprintDialogFragment r0 = (FingerprintDialogFragment) r2.findFragmentByTag("androidx.biometric.FingerprintDialogFragment");
        if (r0 != null) goto L7;
        return;
    L7:
        if (r0.isAdded() == false) goto L10;
        r0.dismissAllowingStateLoss();
        return;
    L10:
        r2.beginTransaction().remove(r0).commitAllowingStateLoss();
        return;
    }

    private int getDismissDialogDelay() {
        Context r1 = getContext();
        if (r1 != null) goto L5;
        return CastStatusCodes.AUTHENTICATION_FAILED;
    L5:
        if (DeviceUtils.shouldHideFingerprintDialog(r1, Build.MODEL) == false) goto L10;
        return 0;
    L10:
        return CastStatusCodes.AUTHENTICATION_FAILED;
    }

    private void handleConfirmCredentialResult(int r3) {
        if (r3 != (-1)) goto L6;
        sendSuccessAndDismiss(new BiometricPrompt.AuthenticationResult(null, 1));
        return;
    L6:
        sendErrorAndDismiss(10, getString(R$string.generic_error_user_canceled));
    }

    private boolean isChangingConfigurations() {
        FragmentActivity r0 = getActivity();
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isChangingConfigurations() == false) goto L10;
        return true;
    L10:
        return false;
    }

    private boolean isFingerprintDialogNeededForCrypto() {
        FragmentActivity r0 = getActivity();
        if (r0 != null) goto L5;
        return false;
    L5:
        if (this.mViewModel.getCryptoObject() != null) goto L7;
        return false;
    L7:
        if (DeviceUtils.shouldUseFingerprintForCrypto(r0, Build.MANUFACTURER, Build.MODEL) == false) goto L13;
        return true;
    L13:
        return false;
    }

    private boolean isFingerprintDialogNeededForErrorHandling() {
        if (Build.VERSION.SDK_INT == 28) goto L5;
        return false;
    L5:
        if (PackageUtils.hasSystemFeatureFingerprint(getContext()) == true) goto L10;
        return true;
    L10:
        return false;
    }

    private boolean isUsingFingerprintDialog() {
        if (Build.VERSION.SDK_INT >= 28) goto L5;
        return true;
    L5:
        if (isFingerprintDialogNeededForCrypto() == false) goto L7;
        return true;
    L7:
        if (isFingerprintDialogNeededForErrorHandling() == true) goto L14;
        return false;
    L14:
        return true;
    }

    private void launchConfirmCredentialActivity() {
        FragmentActivity r0 = getActivity();
        if (r0 != null) goto L6;
        Log.e("BiometricFragment", "Failed to check device credential. Client FragmentActivity not found.");
        return;
    L6:
        KeyguardManager r02 = KeyguardUtils.getKeyguardManager(r0);
        if (r02 != null) goto L10;
        sendErrorAndDismiss(12, getString(R$string.generic_error_no_keyguard));
        return;
    L10:
        CharSequence r1 = this.mViewModel.getTitle();
        CharSequence r2 = this.mViewModel.getSubtitle();
        CharSequence r3 = this.mViewModel.getDescription();
        if (r2 != null) goto L14;
        r2 = r3;
    L14:
        Intent r03 = Api21Impl.createConfirmDeviceCredentialIntent(r02, r1, r2);
        if (r03 != null) goto L18;
        sendErrorAndDismiss(14, getString(R$string.generic_error_no_device_credential));
        return;
    L18:
        this.mViewModel.setConfirmingDeviceCredential(true);
        if (isUsingFingerprintDialog() == false) goto L21;
        dismissFingerprintDialog();
    L21:
        r03.setFlags(134742016);
        startActivityForResult(r03, 1);
    }

    public static BiometricFragment newInstance() {
        return new BiometricFragment();
    }

    private void sendErrorToClient(int r3, CharSequence r4) {
        if (this.mViewModel.isConfirmingDeviceCredential() == false) goto L7;
        Log.v("BiometricFragment", "Error not sent to client. User is confirming their device credential.");
        return;
    L7:
        if (this.mViewModel.isAwaitingResult() == true) goto L10;
        Log.w("BiometricFragment", "Error not sent to client. Client is not awaiting a result.");
        return;
    L10:
        this.mViewModel.setAwaitingResult(false);
        this.mViewModel.getClientExecutor().execute(new AnonymousClass10(this, r3, r4));
    }

    private void sendFailureToClient() {
        if (this.mViewModel.isAwaitingResult() == true) goto L6;
        Log.w("BiometricFragment", "Failure not sent to client. Client is not awaiting a result.");
        return;
    L6:
        this.mViewModel.getClientExecutor().execute(new AnonymousClass11(this));
    }

    private void sendSuccessAndDismiss(BiometricPrompt.AuthenticationResult r1) {
        sendSuccessToClient(r1);
        dismiss();
    }

    private void sendSuccessToClient(BiometricPrompt.AuthenticationResult r3) {
        if (this.mViewModel.isAwaitingResult() == true) goto L6;
        Log.w("BiometricFragment", "Success not sent to client. Client is not awaiting a result.");
        return;
    L6:
        this.mViewModel.setAwaitingResult(false);
        this.mViewModel.getClientExecutor().execute(new AnonymousClass9(this, r3));
    }

    private void showBiometricPromptForAuthentication() {
        BiometricPrompt.Builder r0 = Api28Impl.createPromptBuilder(requireContext().getApplicationContext());
        CharSequence r1 = this.mViewModel.getTitle();
        CharSequence r2 = this.mViewModel.getSubtitle();
        CharSequence r3 = this.mViewModel.getDescription();
        if (r1 == null) goto L5;
        Api28Impl.setTitle(r0, r1);
    L5:
        if (r2 == null) goto L7;
        Api28Impl.setSubtitle(r0, r2);
    L7:
        if (r3 == null) goto L9;
        Api28Impl.setDescription(r0, r3);
    L9:
        CharSequence r12 = this.mViewModel.getNegativeButtonText();
        if (TextUtils.isEmpty(r12) == true) goto L12;
        Api28Impl.setNegativeButton(r0, r12, this.mViewModel.getClientExecutor(), this.mViewModel.getNegativeButtonListener());
    L12:
        int r13 = Build.VERSION.SDK_INT;
        if (r13 < 29) goto L15;
        Api29Impl.setConfirmationRequired(r0, this.mViewModel.isConfirmationRequired());
    L15:
        int r32 = this.mViewModel.getAllowedAuthenticators();
        if (r13 < 30) goto L18;
        Api30Impl.setAllowedAuthenticators(r0, r32);
    L20:
        authenticateWithBiometricPrompt(Api28Impl.buildPrompt(r0), getContext());
        return;
    L18:
        if (r13 < 29) goto L20;
        Api29Impl.setDeviceCredentialAllowed(r0, AuthenticatorUtils.isDeviceCredentialAllowed(r32));
        goto L20
    }

    private void showFingerprintDialogForAuthentication() {
        Context r0 = requireContext().getApplicationContext();
        FingerprintManagerCompat r1 = FingerprintManagerCompat.from(r0);
        int r2 = checkForFingerprintPreAuthenticationErrors(r1);
        if (r2 == 0) goto L7;
        sendErrorAndDismiss(r2, ErrorUtils.getFingerprintErrorString(r0, r2));
        return;
    L7:
        if (isAdded() == false) goto L13;
        this.mViewModel.setFingerprintDialogDismissedInstantly(true);
        if (DeviceUtils.shouldHideFingerprintDialog(r0, Build.MODEL) == true) goto L11;
        this.mHandler.postDelayed(new AnonymousClass7(this), 500);
        FingerprintDialogFragment.newInstance().show(getParentFragmentManager(), "androidx.biometric.FingerprintDialogFragment");
    L11:
        this.mViewModel.setCanceledFrom(0);
        authenticateWithFingerprint(r1, r0);
        return;
    }

    private void showFingerprintErrorMessage(CharSequence r3) {
        if (r3 != null) goto L5;
        r3 = getString(R$string.default_error_msg);
    L5:
        this.mViewModel.setFingerprintDialogState(2);
        this.mViewModel.setFingerprintDialogHelpMessage(r3);
    }

    public void authenticate(BiometricPrompt.PromptInfo r4, BiometricPrompt.CryptoObject r5) {
        FragmentActivity r0 = getActivity();
        if (r0 != null) goto L6;
        Log.e("BiometricFragment", "Not launching prompt. Client activity was null.");
        return;
    L6:
        this.mViewModel.setPromptInfo(r4);
        int r42 = AuthenticatorUtils.getConsolidatedAuthenticators(r4, r5);
        if (Build.VERSION.SDK_INT < 30) goto L9;
    L12:
        this.mViewModel.setCryptoObject(r5);
    L13:
        boolean r43 = isManagingDeviceCredentialButton();
        BiometricViewModel r52 = this.mViewModel;
        if (r43 == false) goto L16;
        r52.setNegativeButtonTextOverride(getString(R$string.confirm_device_credential_password));
    L18:
        if (isManagingDeviceCredentialButton() == false) goto L24;
        if (BiometricManager.from(r0).canAuthenticate(255) == 0) goto L24;
        this.mViewModel.setAwaitingResult(true);
        launchConfirmCredentialActivity();
        return;
    L24:
        if (this.mViewModel.isDelayingPrompt() == false) goto L27;
        this.mHandler.postDelayed(new ShowPromptForAuthenticationRunnable(this), 600);
        return;
    L27:
        showPromptForAuthentication();
        return;
    L16:
        r52.setNegativeButtonTextOverride(null);
        goto L18
    L9:
        if (r42 != 15) goto L12;
        if (r5 != null) goto L12;
        this.mViewModel.setCryptoObject(CryptoObjectUtils.createFakeCryptoObject());
        goto L13
    }

    public void authenticateWithBiometricPrompt(android.hardware.biometrics.BiometricPrompt r5, Context r6) {
        BiometricPrompt.CryptoObject r0 = CryptoObjectUtils.wrapForBiometricPrompt(this.mViewModel.getCryptoObject());
        CancellationSignal r1 = this.mViewModel.getCancellationSignalProvider().getBiometricCancellationSignal();
        PromptExecutor r2 = new PromptExecutor();
        android.hardware.biometrics.BiometricPrompt$AuthenticationCallback r3 = this.mViewModel.getAuthenticationCallbackProvider().getBiometricCallback();
        if (r0 != null) goto L8;
        Api28Impl.authenticate(r5, r1, r2, r3);     // Catch: NullPointerException -> L6
        return;
    L8:
        Api28Impl.authenticate(r5, r0, r1, r2, r3);     // Catch: NullPointerException -> L6
        return;
    L6:
        e = move-exception;
        Log.e("BiometricFragment", "Got NPE while authenticating with biometric prompt.", e);
        if (r6 == null) goto L13;
        String r52 = r6.getString(R$string.default_error_msg);
    L14:
        sendErrorAndDismiss(1, r52);
        return;
    L13:
        r52 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L14
    }

    public void authenticateWithFingerprint(FingerprintManagerCompat r8, Context r9) {
        r8.authenticate(CryptoObjectUtils.wrapForFingerprintManager(this.mViewModel.getCryptoObject()), 0, this.mViewModel.getCancellationSignalProvider().getFingerprintCancellationSignal(), this.mViewModel.getAuthenticationCallbackProvider().getFingerprintCallback(), null);     // Catch: NullPointerException -> L5
        return;
    L5:
        e = move-exception;
        Log.e("BiometricFragment", "Got NPE while authenticating with fingerprint.", e);
        sendErrorAndDismiss(1, ErrorUtils.getFingerprintErrorString(r9, 1));
    }

    public void cancelAuthentication(int r2) {
        if (r2 == 3) goto L8;
        if (this.mViewModel.isIgnoringCancel() == false) goto L8;
        return;
    L8:
        if (isUsingFingerprintDialog() == false) goto L12;
        this.mViewModel.setCanceledFrom(r2);
        if (r2 != 1) goto L12;
        sendErrorToClient(10, ErrorUtils.getFingerprintErrorString(getContext(), 10));
    L12:
        this.mViewModel.getCancellationSignalProvider().cancel();
    }

    public void dismiss() {
        this.mViewModel.setPromptShowing(false);
        dismissFingerprintDialog();
        if (this.mViewModel.isConfirmingDeviceCredential() == false) goto L5;
    L7:
        Context r0 = getContext();
        if (r0 != null) goto L10;
        return;
    L10:
        if (DeviceUtils.shouldDelayShowingPrompt(r0, Build.MODEL) == false) goto L14;
        this.mViewModel.setDelayingPrompt(true);
        this.mHandler.postDelayed(new StopDelayingPromptRunnable(this.mViewModel), 600);
        return;
    L14:
        return;
    L5:
        if (isAdded() == false) goto L7;
        getParentFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss();
        goto L7
    }

    public boolean isManagingDeviceCredentialButton() {
        if (Build.VERSION.SDK_INT <= 28) goto L5;
        return false;
    L5:
        if (AuthenticatorUtils.isDeviceCredentialAllowed(this.mViewModel.getAllowedAuthenticators()) == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int r1, int r2, Intent r3) {
        super.onActivityResult(r1, r2, r3);
        if (r1 != 1) goto L6;
        this.mViewModel.setConfirmingDeviceCredential(false);
        handleConfirmCredentialResult(r2);
        return;
    }

    public void onAuthenticationError(int r4, CharSequence r5) {
        if (ErrorUtils.isKnownError(r4) == true) goto L6;
        r4 = 8;
    L6:
        Context r0 = getContext();
        if (Build.VERSION.SDK_INT >= 29) goto L18;
        if (ErrorUtils.isLockoutError(r4) == false) goto L18;
        if (r0 == null) goto L18;
        if (KeyguardUtils.isDeviceSecuredWithCredential(r0) == false) goto L18;
        if (AuthenticatorUtils.isDeviceCredentialAllowed(this.mViewModel.getAllowedAuthenticators()) == false) goto L18;
        launchConfirmCredentialActivity();
        return;
    L18:
        if (isUsingFingerprintDialog() == false) goto L37;
        if (r5 != null) goto L22;
        r5 = ErrorUtils.getFingerprintErrorString(getContext(), r4);
    L22:
        BiometricViewModel r02 = this.mViewModel;
        if (r4 != 5) goto L32;
        int r03 = r02.getCanceledFrom();
        if (r03 != 0) goto L27;
    L28:
        sendErrorToClient(r4, r5);
    L29:
        dismiss();
        return;
    L27:
        if (r03 != 3) goto L29;
    L32:
        if (r02.isFingerprintDialogDismissedInstantly() == false) goto L34;
        sendErrorAndDismiss(r4, r5);
    L35:
        this.mViewModel.setFingerprintDialogDismissedInstantly(true);
        return;
    L34:
        showFingerprintErrorMessage(r5);
        this.mHandler.postDelayed(new AnonymousClass8(this, r4, r5), getDismissDialogDelay());
        goto L35
    L37:
        if (r5 != null) goto L40;
        r5 = getString(R$string.default_error_msg) + " " + r4;
    L40:
        sendErrorAndDismiss(r4, r5);
    }

    public void onAuthenticationFailed() {
        if (isUsingFingerprintDialog() == false) goto L5;
        showFingerprintErrorMessage(getString(R$string.fingerprint_not_recognized));
    L5:
        sendFailureToClient();
    }

    public void onAuthenticationHelp(CharSequence r2) {
        if (isUsingFingerprintDialog() == false) goto L6;
        showFingerprintErrorMessage(r2);
        return;
    }

    public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult r1) {
        sendSuccessAndDismiss(r1);
    }

    public void onCancelButtonPressed() {
        CharSequence r0 = this.mViewModel.getNegativeButtonText();
        if (r0 != null) goto L6;
        r0 = getString(R$string.default_error_msg);
    L6:
        sendErrorAndDismiss(13, r0);
        cancelAuthentication(2);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle r1) {
        super.onCreate(r1);
        connectViewModel();
    }

    public void onDeviceCredentialButtonPressed() {
        launchConfirmCredentialActivity();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        if (Build.VERSION.SDK_INT == 29) goto L5;
        return;
    L5:
        if (AuthenticatorUtils.isDeviceCredentialAllowed(this.mViewModel.getAllowedAuthenticators()) == false) goto L9;
        this.mViewModel.setIgnoringCancel(true);
        this.mHandler.postDelayed(new StopIgnoringCancelRunnable(this.mViewModel), 250);
        return;
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        if (Build.VERSION.SDK_INT < 29) goto L5;
        return;
    L5:
        if (this.mViewModel.isConfirmingDeviceCredential() == false) goto L7;
        return;
    L7:
        if (isChangingConfigurations() == true) goto L12;
        cancelAuthentication(0);
        return;
    }

    public void sendErrorAndDismiss(int r1, CharSequence r2) {
        sendErrorToClient(r1, r2);
        dismiss();
    }

    public void showPromptForAuthentication() {
        if (this.mViewModel.isPromptShowing() == false) goto L5;
        return;
    L5:
        if (getContext() != null) goto L8;
        Log.w("BiometricFragment", "Not showing biometric prompt. Context is null.");
        return;
    L8:
        this.mViewModel.setPromptShowing(true);
        this.mViewModel.setAwaitingResult(true);
        if (isUsingFingerprintDialog() == false) goto L12;
        showFingerprintDialogForAuthentication();
        return;
    L12:
        showBiometricPromptForAuthentication();
    }
}
