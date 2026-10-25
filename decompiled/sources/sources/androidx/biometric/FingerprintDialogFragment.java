package androidx.biometric;

import android.R;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

/* loaded from: classes3.dex */
public class FingerprintDialogFragment extends DialogFragment {
    private int mErrorTextColor;
    private ImageView mFingerprintIcon;
    final Handler mHandler;
    TextView mHelpMessageView;
    private int mNormalTextColor;
    final Runnable mResetDialogRunnable;
    BiometricViewModel mViewModel;

    /* renamed from: androidx.biometric.FingerprintDialogFragment$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ FingerprintDialogFragment this$0;

        public AnonymousClass1(FingerprintDialogFragment r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.this$0.resetDialog();
        }
    }

    /* renamed from: androidx.biometric.FingerprintDialogFragment$2, reason: invalid class name */
    public class AnonymousClass2 implements DialogInterface.OnClickListener {
        final /* synthetic */ FingerprintDialogFragment this$0;

        public AnonymousClass2(FingerprintDialogFragment r1) {
            this.this$0 = r1;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface r1, int r2) {
            this.this$0.mViewModel.setNegativeButtonPressPending(true);
        }
    }

    /* renamed from: androidx.biometric.FingerprintDialogFragment$3, reason: invalid class name */
    public class AnonymousClass3 implements Observer<Integer> {
        final /* synthetic */ FingerprintDialogFragment this$0;

        public AnonymousClass3(FingerprintDialogFragment r1) {
            this.this$0 = r1;
        }

        /* renamed from: onChanged, reason: avoid collision after fix types in other method */
        public void onChanged2(Integer r3) {
            FingerprintDialogFragment r0 = this.this$0;
            r0.mHandler.removeCallbacks(r0.mResetDialogRunnable);
            this.this$0.updateFingerprintIcon(r3.intValue());
            this.this$0.updateHelpMessageColor(r3.intValue());
            FingerprintDialogFragment r2 = this.this$0;
            r2.mHandler.postDelayed(r2.mResetDialogRunnable, 2000);
        }

        @Override // androidx.lifecycle.Observer
        public /* bridge */ /* synthetic */ void onChanged(Integer r1) {
            onChanged2(r1);
        }
    }

    /* renamed from: androidx.biometric.FingerprintDialogFragment$4, reason: invalid class name */
    public class AnonymousClass4 implements Observer<CharSequence> {
        final /* synthetic */ FingerprintDialogFragment this$0;

        public AnonymousClass4(FingerprintDialogFragment r1) {
            this.this$0 = r1;
        }

        /* renamed from: onChanged, reason: avoid collision after fix types in other method */
        public void onChanged2(CharSequence r3) {
            FingerprintDialogFragment r0 = this.this$0;
            r0.mHandler.removeCallbacks(r0.mResetDialogRunnable);
            this.this$0.updateHelpMessageText(r3);
            FingerprintDialogFragment r2 = this.this$0;
            r2.mHandler.postDelayed(r2.mResetDialogRunnable, 2000);
        }

        @Override // androidx.lifecycle.Observer
        public /* bridge */ /* synthetic */ void onChanged(CharSequence r1) {
            onChanged2(r1);
        }
    }

    public static class Api21Impl {
        public static void startAnimation(Drawable r1) {
            if ((r1 instanceof AnimatedVectorDrawable) == false) goto L6;
            ((AnimatedVectorDrawable) r1).start();
            return;
        }
    }

    public static class Api26Impl {
        public static int getColorErrorAttr() {
            return R$attr.colorError;
        }
    }

    public FingerprintDialogFragment() {
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mResetDialogRunnable = new AnonymousClass1(this);
    }

    private void connectViewModel() {
        FragmentActivity r0 = getActivity();
        if (r0 != null) goto L5;
        return;
    L5:
        BiometricViewModel r02 = (BiometricViewModel) new ViewModelProvider(r0).get(BiometricViewModel.class);
        this.mViewModel = r02;
        r02.getFingerprintDialogState().observe(this, new AnonymousClass3(this));
        this.mViewModel.getFingerprintDialogHelpMessage().observe(this, new AnonymousClass4(this));
    }

    private Drawable getAssetForTransition(int r4, int r5) {
        Context r3 = getContext();
        if (r3 != null) goto L7;
        Log.w("FingerprintFragment", "Unable to get asset. Context is null.");
        return null;
    L7:
        if (r4 != 0) goto L11;
        if (r5 != 1) goto L11;
        int r42 = R$drawable.fingerprint_dialog_fp_icon;
    L22:
        return ContextCompat.getDrawable(r3, r42);
    L11:
        if (r4 != 1) goto L14;
        if (r5 != 2) goto L14;
        r42 = R$drawable.fingerprint_dialog_error;
    L14:
        if (r4 != 2) goto L17;
        if (r5 != 1) goto L17;
        r42 = R$drawable.fingerprint_dialog_fp_icon;
    L17:
        if (r4 == 1) goto L19;
    L23:
        return null;
    L19:
        if (r5 != 3) goto L23;
        r42 = R$drawable.fingerprint_dialog_fp_icon;
        goto L22
    }

    private int getThemedColorFor(int r5) {
        Context r0 = getContext();
        FragmentActivity r4 = getActivity();
        if (r0 == null) goto L8;
        if (r4 == null) goto L8;
        TypedValue r2 = new TypedValue();
        r0.getTheme().resolveAttribute(r5, r2, true);
        TypedArray r42 = r4.obtainStyledAttributes(r2.data, new int[]{r5});
        int r52 = r42.getColor(0, 0);
        r42.recycle();
        return r52;
    L8:
        Log.w("FingerprintFragment", "Unable to get themed color. Context or activity is null.");
        return 0;
    }

    public static FingerprintDialogFragment newInstance() {
        return new FingerprintDialogFragment();
    }

    private boolean shouldAnimateForTransition(int r3, int r4) {
        if (r3 != 0) goto L7;
        if (r4 != 1) goto L7;
        return false;
    L7:
        if (r3 != 1) goto L10;
        if (r4 != 2) goto L10;
        return true;
    L10:
        if (r3 != 2) goto L13;
        if (r4 != 1) goto L13;
        return true;
    L13:
        return false;
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface r1) {
        super.onCancel(r1);
        this.mViewModel.setFingerprintDialogCancelPending(true);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle r2) {
        super.onCreate(r2);
        connectViewModel();
        if (Build.VERSION.SDK_INT < 26) goto L5;
        this.mErrorTextColor = getThemedColorFor(Api26Impl.getColorErrorAttr());
    L10:
        this.mNormalTextColor = getThemedColorFor(R.attr.textColorSecondary);
        return;
    L5:
        Context r22 = getContext();
        if (r22 == null) goto L8;
        int r23 = ContextCompat.getColor(r22, R$color.biometric_error_color);
    L9:
        this.mErrorTextColor = r23;
        goto L10
    L8:
        r23 = 0;
        goto L9
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle r7) {
        AlertDialog.Builder r72 = new AlertDialog.Builder(requireContext());
        r72.setTitle(this.mViewModel.getTitle());
        View r0 = LayoutInflater.from(r72.getContext()).inflate(R$layout.fingerprint_dialog_layout, null);
        TextView r1 = (TextView) r0.findViewById(R$id.fingerprint_subtitle);
        if (r1 == null) goto L8;
        CharSequence r4 = this.mViewModel.getSubtitle();
        if (TextUtils.isEmpty(r4) == false) goto L7;
        r1.setVisibility(8);
        goto L8
    L7:
        r1.setVisibility(0);
        r1.setText(r4);
    L8:
        TextView r12 = (TextView) r0.findViewById(R$id.fingerprint_description);
        if (r12 == null) goto L14;
        CharSequence r42 = this.mViewModel.getDescription();
        if (TextUtils.isEmpty(r42) == false) goto L13;
        r12.setVisibility(8);
        goto L14
    L13:
        r12.setVisibility(0);
        r12.setText(r42);
    L14:
        this.mFingerprintIcon = (ImageView) r0.findViewById(R$id.fingerprint_icon);
        this.mHelpMessageView = (TextView) r0.findViewById(R$id.fingerprint_error);
        if (AuthenticatorUtils.isDeviceCredentialAllowed(this.mViewModel.getAllowedAuthenticators()) == false) goto L17;
        CharSequence r13 = getString(R$string.confirm_device_credential_password);
    L18:
        r72.setNegativeButton(r13, new AnonymousClass2(this));
        r72.setView(r0);
        AlertDialog r6 = r72.create();
        r6.setCanceledOnTouchOutside(false);
        return r6;
    L17:
        r13 = this.mViewModel.getNegativeButtonText();
        goto L18
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.mHandler.removeCallbacksAndMessages(null);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.mViewModel.setFingerprintDialogPreviousState(0);
        this.mViewModel.setFingerprintDialogState(1);
        this.mViewModel.setFingerprintDialogHelpMessage(getString(R$string.fingerprint_dialog_touch_sensor));
    }

    public void resetDialog() {
        Context r0 = getContext();
        if (r0 != null) goto L6;
        Log.w("FingerprintFragment", "Not resetting the dialog. Context is null.");
        return;
    L6:
        this.mViewModel.setFingerprintDialogState(1);
        this.mViewModel.setFingerprintDialogHelpMessage(r0.getString(R$string.fingerprint_dialog_touch_sensor));
    }

    public void updateFingerprintIcon(int r4) {
        if (this.mFingerprintIcon == null) goto L13;
        int r0 = this.mViewModel.getFingerprintDialogPreviousState();
        Drawable r1 = getAssetForTransition(r0, r4);
        if (r1 != null) goto L8;
        return;
    L8:
        this.mFingerprintIcon.setImageDrawable(r1);
        if (shouldAnimateForTransition(r0, r4) == false) goto L11;
        Api21Impl.startAnimation(r1);
    L11:
        this.mViewModel.setFingerprintDialogPreviousState(r4);
        return;
    }

    public void updateHelpMessageColor(int r3) {
        TextView r0 = this.mHelpMessageView;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r3 != 2) goto L7;
        boolean r32 = true;
    L8:
        if (r32 == false) goto L10;
        int r2 = this.mErrorTextColor;
    L11:
        r0.setTextColor(r2);
        return;
    L10:
        r2 = this.mNormalTextColor;
        goto L11
    L7:
        r32 = false;
        goto L8
    }

    public void updateHelpMessageText(CharSequence r1) {
        TextView r0 = this.mHelpMessageView;
        if (r0 == null) goto L6;
        r0.setText(r1);
        return;
    }
}
