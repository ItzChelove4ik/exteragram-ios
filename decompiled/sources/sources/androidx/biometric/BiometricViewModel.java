package androidx.biometric;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import androidx.biometric.AuthenticationCallbackProvider;
import androidx.biometric.BiometricPrompt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;

/* loaded from: classes3.dex */
public class BiometricViewModel extends ViewModel {
    private AuthenticationCallbackProvider mAuthenticationCallbackProvider;
    private MutableLiveData<BiometricErrorData> mAuthenticationError;
    private MutableLiveData<CharSequence> mAuthenticationHelpMessage;
    private MutableLiveData<BiometricPrompt.AuthenticationResult> mAuthenticationResult;
    private int mCanceledFrom;
    private CancellationSignalProvider mCancellationSignalProvider;
    private BiometricPrompt.AuthenticationCallback mClientCallback;
    private Executor mClientExecutor;
    private BiometricPrompt.CryptoObject mCryptoObject;
    private MutableLiveData<CharSequence> mFingerprintDialogHelpMessage;
    private int mFingerprintDialogPreviousState;
    private MutableLiveData<Integer> mFingerprintDialogState;
    private MutableLiveData<Boolean> mIsAuthenticationFailurePending;
    private boolean mIsAwaitingResult;
    private boolean mIsConfirmingDeviceCredential;
    private boolean mIsDelayingPrompt;
    private MutableLiveData<Boolean> mIsFingerprintDialogCancelPending;
    private boolean mIsFingerprintDialogDismissedInstantly;
    private boolean mIsIgnoringCancel;
    private MutableLiveData<Boolean> mIsNegativeButtonPressPending;
    private boolean mIsPromptShowing;
    private DialogInterface.OnClickListener mNegativeButtonListener;
    private CharSequence mNegativeButtonTextOverride;
    private BiometricPrompt.PromptInfo mPromptInfo;

    /* renamed from: androidx.biometric.BiometricViewModel$1, reason: invalid class name */
    public class AnonymousClass1 extends BiometricPrompt.AuthenticationCallback {
        final /* synthetic */ BiometricViewModel this$0;

        public AnonymousClass1(BiometricViewModel r1) {
            this.this$0 = r1;
        }
    }

    public static final class CallbackListener extends AuthenticationCallbackProvider.Listener {
        private final WeakReference<BiometricViewModel> mViewModelRef;

        public CallbackListener(BiometricViewModel r2) {
            this.mViewModelRef = new WeakReference(r2);
        }

        @Override // androidx.biometric.AuthenticationCallbackProvider.Listener
        public void onError(int r2, CharSequence r3) {
            if (this.mViewModelRef.get() != null) goto L5;
            return;
        L5:
            if (this.mViewModelRef.get().isConfirmingDeviceCredential() == false) goto L7;
            return;
        L7:
            if (this.mViewModelRef.get().isAwaitingResult() == false) goto L12;
            this.mViewModelRef.get().setAuthenticationError(new BiometricErrorData(r2, r3));
            return;
        }

        @Override // androidx.biometric.AuthenticationCallbackProvider.Listener
        public void onFailure() {
            if (this.mViewModelRef.get() != null) goto L5;
            return;
        L5:
            if (this.mViewModelRef.get().isAwaitingResult() == false) goto L9;
            this.mViewModelRef.get().setAuthenticationFailurePending(true);
            return;
        }

        @Override // androidx.biometric.AuthenticationCallbackProvider.Listener
        public void onSuccess(BiometricPrompt.AuthenticationResult r3) {
            if (this.mViewModelRef.get() != null) goto L5;
            return;
        L5:
            if (this.mViewModelRef.get().isAwaitingResult() == true) goto L7;
            return;
        L7:
            if (r3.getAuthenticationType() != (-1)) goto L9;
            r3 = new BiometricPrompt.AuthenticationResult(r3.getCryptoObject(), this.mViewModelRef.get().getInferredAuthenticationResultType());
        L9:
            this.mViewModelRef.get().setAuthenticationResult(r3);
        }
    }

    public static class DefaultExecutor implements Executor {
        private final Handler mHandler;

        public DefaultExecutor() {
            this.mHandler = new Handler(Looper.getMainLooper());
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable r1) {
            this.mHandler.post(r1);
        }
    }

    public static class NegativeButtonListener implements DialogInterface.OnClickListener {
        private final WeakReference<BiometricViewModel> mViewModelRef;

        public NegativeButtonListener(BiometricViewModel r2) {
            this.mViewModelRef = new WeakReference(r2);
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface r1, int r2) {
            if (this.mViewModelRef.get() == null) goto L6;
            this.mViewModelRef.get().setNegativeButtonPressPending(true);
            return;
        }
    }

    public BiometricViewModel() {
        this.mCanceledFrom = 0;
        this.mIsFingerprintDialogDismissedInstantly = true;
        this.mFingerprintDialogPreviousState = 0;
    }

    private static <T> void updateValue(MutableLiveData<T> r2, T r3) {
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) goto L6;
        r2.setValue(r3);
        return;
    L6:
        r2.postValue(r3);
    }

    public int getAllowedAuthenticators() {
        BiometricPrompt.PromptInfo r0 = this.mPromptInfo;
        if (r0 != null) goto L5;
        return 0;
    L5:
        return AuthenticatorUtils.getConsolidatedAuthenticators(r0, this.mCryptoObject);
    }

    public AuthenticationCallbackProvider getAuthenticationCallbackProvider() {
        if (this.mAuthenticationCallbackProvider != null) goto L6;
        this.mAuthenticationCallbackProvider = new AuthenticationCallbackProvider(new CallbackListener(this));
    L6:
        return this.mAuthenticationCallbackProvider;
    }

    public MutableLiveData<BiometricErrorData> getAuthenticationError() {
        if (this.mAuthenticationError != null) goto L6;
        this.mAuthenticationError = new MutableLiveData();
    L6:
        return this.mAuthenticationError;
    }

    public LiveData<CharSequence> getAuthenticationHelpMessage() {
        if (this.mAuthenticationHelpMessage != null) goto L6;
        this.mAuthenticationHelpMessage = new MutableLiveData();
    L6:
        return this.mAuthenticationHelpMessage;
    }

    public LiveData<BiometricPrompt.AuthenticationResult> getAuthenticationResult() {
        if (this.mAuthenticationResult != null) goto L6;
        this.mAuthenticationResult = new MutableLiveData();
    L6:
        return this.mAuthenticationResult;
    }

    public int getCanceledFrom() {
        return this.mCanceledFrom;
    }

    public CancellationSignalProvider getCancellationSignalProvider() {
        if (this.mCancellationSignalProvider != null) goto L6;
        this.mCancellationSignalProvider = new CancellationSignalProvider();
    L6:
        return this.mCancellationSignalProvider;
    }

    public BiometricPrompt.AuthenticationCallback getClientCallback() {
        if (this.mClientCallback != null) goto L6;
        this.mClientCallback = new AnonymousClass1(this);
    L6:
        return this.mClientCallback;
    }

    public Executor getClientExecutor() {
        Executor r0 = this.mClientExecutor;
        if (r0 == null) goto L6;
        return r0;
    L6:
        return new DefaultExecutor();
    }

    public BiometricPrompt.CryptoObject getCryptoObject() {
        return this.mCryptoObject;
    }

    public CharSequence getDescription() {
        BiometricPrompt.PromptInfo r0 = this.mPromptInfo;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getDescription();
    }

    public LiveData<CharSequence> getFingerprintDialogHelpMessage() {
        if (this.mFingerprintDialogHelpMessage != null) goto L6;
        this.mFingerprintDialogHelpMessage = new MutableLiveData();
    L6:
        return this.mFingerprintDialogHelpMessage;
    }

    public int getFingerprintDialogPreviousState() {
        return this.mFingerprintDialogPreviousState;
    }

    public LiveData<Integer> getFingerprintDialogState() {
        if (this.mFingerprintDialogState != null) goto L6;
        this.mFingerprintDialogState = new MutableLiveData();
    L6:
        return this.mFingerprintDialogState;
    }

    public int getInferredAuthenticationResultType() {
        int r1 = getAllowedAuthenticators();
        if (AuthenticatorUtils.isSomeBiometricAllowed(r1) == true) goto L5;
        return -1;
    L5:
        if (AuthenticatorUtils.isDeviceCredentialAllowed(r1) == true) goto L10;
        return 2;
    L10:
        return -1;
    }

    public DialogInterface.OnClickListener getNegativeButtonListener() {
        if (this.mNegativeButtonListener != null) goto L6;
        this.mNegativeButtonListener = new NegativeButtonListener(this);
    L6:
        return this.mNegativeButtonListener;
    }

    public CharSequence getNegativeButtonText() {
        CharSequence r0 = this.mNegativeButtonTextOverride;
        if (r0 == null) goto L5;
        return r0;
    L5:
        BiometricPrompt.PromptInfo r1 = this.mPromptInfo;
        if (r1 != null) goto L8;
        return null;
    L8:
        return r1.getNegativeButtonText();
    }

    public CharSequence getSubtitle() {
        BiometricPrompt.PromptInfo r0 = this.mPromptInfo;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getSubtitle();
    }

    public CharSequence getTitle() {
        BiometricPrompt.PromptInfo r0 = this.mPromptInfo;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getTitle();
    }

    public LiveData<Boolean> isAuthenticationFailurePending() {
        if (this.mIsAuthenticationFailurePending != null) goto L6;
        this.mIsAuthenticationFailurePending = new MutableLiveData();
    L6:
        return this.mIsAuthenticationFailurePending;
    }

    public boolean isAwaitingResult() {
        return this.mIsAwaitingResult;
    }

    public boolean isConfirmationRequired() {
        BiometricPrompt.PromptInfo r0 = this.mPromptInfo;
        if (r0 != null) goto L5;
        return true;
    L5:
        if (r0.isConfirmationRequired() == true) goto L11;
        return false;
    L11:
        return true;
    }

    public boolean isConfirmingDeviceCredential() {
        return this.mIsConfirmingDeviceCredential;
    }

    public boolean isDelayingPrompt() {
        return this.mIsDelayingPrompt;
    }

    public LiveData<Boolean> isFingerprintDialogCancelPending() {
        if (this.mIsFingerprintDialogCancelPending != null) goto L6;
        this.mIsFingerprintDialogCancelPending = new MutableLiveData();
    L6:
        return this.mIsFingerprintDialogCancelPending;
    }

    public boolean isFingerprintDialogDismissedInstantly() {
        return this.mIsFingerprintDialogDismissedInstantly;
    }

    public boolean isIgnoringCancel() {
        return this.mIsIgnoringCancel;
    }

    public LiveData<Boolean> isNegativeButtonPressPending() {
        if (this.mIsNegativeButtonPressPending != null) goto L6;
        this.mIsNegativeButtonPressPending = new MutableLiveData();
    L6:
        return this.mIsNegativeButtonPressPending;
    }

    public boolean isPromptShowing() {
        return this.mIsPromptShowing;
    }

    public void setAuthenticationError(BiometricErrorData r2) {
        if (this.mAuthenticationError != null) goto L5;
        this.mAuthenticationError = new MutableLiveData();
    L5:
        updateValue(this.mAuthenticationError, r2);
    }

    public void setAuthenticationFailurePending(boolean r2) {
        if (this.mIsAuthenticationFailurePending != null) goto L5;
        this.mIsAuthenticationFailurePending = new MutableLiveData();
    L5:
        updateValue(this.mIsAuthenticationFailurePending, Boolean.valueOf(r2));
    }

    public void setAuthenticationResult(BiometricPrompt.AuthenticationResult r2) {
        if (this.mAuthenticationResult != null) goto L5;
        this.mAuthenticationResult = new MutableLiveData();
    L5:
        updateValue(this.mAuthenticationResult, r2);
    }

    public void setAwaitingResult(boolean r1) {
        this.mIsAwaitingResult = r1;
    }

    public void setCanceledFrom(int r1) {
        this.mCanceledFrom = r1;
    }

    public void setClientCallback(BiometricPrompt.AuthenticationCallback r1) {
        this.mClientCallback = r1;
    }

    public void setClientExecutor(Executor r1) {
        this.mClientExecutor = r1;
    }

    public void setConfirmingDeviceCredential(boolean r1) {
        this.mIsConfirmingDeviceCredential = r1;
    }

    public void setCryptoObject(BiometricPrompt.CryptoObject r1) {
        this.mCryptoObject = r1;
    }

    public void setDelayingPrompt(boolean r1) {
        this.mIsDelayingPrompt = r1;
    }

    public void setFingerprintDialogCancelPending(boolean r2) {
        if (this.mIsFingerprintDialogCancelPending != null) goto L5;
        this.mIsFingerprintDialogCancelPending = new MutableLiveData();
    L5:
        updateValue(this.mIsFingerprintDialogCancelPending, Boolean.valueOf(r2));
    }

    public void setFingerprintDialogDismissedInstantly(boolean r1) {
        this.mIsFingerprintDialogDismissedInstantly = r1;
    }

    public void setFingerprintDialogHelpMessage(CharSequence r2) {
        if (this.mFingerprintDialogHelpMessage != null) goto L5;
        this.mFingerprintDialogHelpMessage = new MutableLiveData();
    L5:
        updateValue(this.mFingerprintDialogHelpMessage, r2);
    }

    public void setFingerprintDialogPreviousState(int r1) {
        this.mFingerprintDialogPreviousState = r1;
    }

    public void setFingerprintDialogState(int r2) {
        if (this.mFingerprintDialogState != null) goto L5;
        this.mFingerprintDialogState = new MutableLiveData();
    L5:
        updateValue(this.mFingerprintDialogState, Integer.valueOf(r2));
    }

    public void setIgnoringCancel(boolean r1) {
        this.mIsIgnoringCancel = r1;
    }

    public void setNegativeButtonPressPending(boolean r2) {
        if (this.mIsNegativeButtonPressPending != null) goto L5;
        this.mIsNegativeButtonPressPending = new MutableLiveData();
    L5:
        updateValue(this.mIsNegativeButtonPressPending, Boolean.valueOf(r2));
    }

    public void setNegativeButtonTextOverride(CharSequence r1) {
        this.mNegativeButtonTextOverride = r1;
    }

    public void setPromptInfo(BiometricPrompt.PromptInfo r1) {
        this.mPromptInfo = r1;
    }

    public void setPromptShowing(boolean r1) {
        this.mIsPromptShowing = r1;
    }
}
