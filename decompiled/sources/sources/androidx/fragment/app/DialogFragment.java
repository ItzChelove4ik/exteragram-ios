package androidx.fragment.app;

import android.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.activity.ComponentDialog;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData$$ExternalSyntheticBUOutline0;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.lifecycle.ViewTreeViewModelStoreOwner;
import androidx.savedstate.ViewTreeSavedStateRegistryOwner;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
public class DialogFragment extends Fragment implements DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {
    private static final String SAVED_BACK_STACK_ID = "android:backStackId";
    private static final String SAVED_CANCELABLE = "android:cancelable";
    private static final String SAVED_DIALOG_STATE_TAG = "android:savedDialogState";
    private static final String SAVED_INTERNAL_DIALOG_SHOWING = "android:dialogShowing";
    private static final String SAVED_SHOWS_DIALOG = "android:showsDialog";
    private static final String SAVED_STYLE = "android:style";
    private static final String SAVED_THEME = "android:theme";
    public static final int STYLE_NORMAL = 0;
    public static final int STYLE_NO_FRAME = 2;
    public static final int STYLE_NO_INPUT = 3;
    public static final int STYLE_NO_TITLE = 1;
    private int mBackStackId;
    private boolean mCancelable;
    private boolean mCreatingDialog;
    private Dialog mDialog;
    private boolean mDialogCreated;
    private Runnable mDismissRunnable;
    private boolean mDismissed;
    private Handler mHandler;
    private Observer<LifecycleOwner> mObserver;
    private DialogInterface.OnCancelListener mOnCancelListener;
    private DialogInterface.OnDismissListener mOnDismissListener;
    private boolean mShownByMe;
    private boolean mShowsDialog;
    private int mStyle;
    private int mTheme;
    private boolean mViewDestroyed;

    /* renamed from: androidx.fragment.app.DialogFragment$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ DialogFragment this$0;

        public AnonymousClass1(DialogFragment r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        @SuppressLint({"SyntheticAccessor"})
        public void run() {
            DialogFragment.access$100(this.this$0).onDismiss(DialogFragment.access$000(this.this$0));
        }
    }

    /* renamed from: androidx.fragment.app.DialogFragment$2, reason: invalid class name */
    public class AnonymousClass2 implements DialogInterface.OnCancelListener {
        final /* synthetic */ DialogFragment this$0;

        public AnonymousClass2(DialogFragment r1) {
            this.this$0 = r1;
        }

        @Override // android.content.DialogInterface.OnCancelListener
        @SuppressLint({"SyntheticAccessor"})
        public void onCancel(DialogInterface r1) {
            if (DialogFragment.access$000(this.this$0) == null) goto L6;
            DialogFragment r0 = this.this$0;
            r0.onCancel(DialogFragment.access$000(r0));
            return;
        }
    }

    /* renamed from: androidx.fragment.app.DialogFragment$3, reason: invalid class name */
    public class AnonymousClass3 implements DialogInterface.OnDismissListener {
        final /* synthetic */ DialogFragment this$0;

        public AnonymousClass3(DialogFragment r1) {
            this.this$0 = r1;
        }

        @Override // android.content.DialogInterface.OnDismissListener
        @SuppressLint({"SyntheticAccessor"})
        public void onDismiss(DialogInterface r1) {
            if (DialogFragment.access$000(this.this$0) == null) goto L6;
            DialogFragment r0 = this.this$0;
            r0.onDismiss(DialogFragment.access$000(r0));
            return;
        }
    }

    /* renamed from: androidx.fragment.app.DialogFragment$4, reason: invalid class name */
    public class AnonymousClass4 implements Observer<LifecycleOwner> {
        final /* synthetic */ DialogFragment this$0;

        public AnonymousClass4(DialogFragment r1) {
            this.this$0 = r1;
        }

        @SuppressLint({"SyntheticAccessor"})
        /* renamed from: onChanged, reason: avoid collision after fix types in other method */
        public void onChanged2(LifecycleOwner r3) {
            if (r3 != null) goto L4;
            return;
        L4:
            if (DialogFragment.access$200(this.this$0) == false) goto L17;
            View r32 = this.this$0.requireView();
            if (r32.getParent() == null) goto L8;
            Segment$$ExternalSyntheticBUOutline1.m("DialogFragment can not be attached to a container view");
            return;
        L8:
            if (DialogFragment.access$000(this.this$0) != null) goto L10;
            return;
        L10:
            if (FragmentManager.isLoggingEnabled(3) == false) goto L12;
            Log.d("FragmentManager", "DialogFragment " + this + " setting the content view on " + DialogFragment.access$000(this.this$0));
        L12:
            DialogFragment.access$000(this.this$0).setContentView(r32);
            return;
        }

        @Override // androidx.lifecycle.Observer
        @SuppressLint({"SyntheticAccessor"})
        public /* bridge */ /* synthetic */ void onChanged(LifecycleOwner r1) {
            onChanged2(r1);
        }
    }

    /* renamed from: androidx.fragment.app.DialogFragment$5, reason: invalid class name */
    public class AnonymousClass5 extends FragmentContainer {
        final /* synthetic */ DialogFragment this$0;
        final /* synthetic */ FragmentContainer val$fragmentContainer;

        public AnonymousClass5(DialogFragment r1, FragmentContainer r2) {
            this.this$0 = r1;
            this.val$fragmentContainer = r2;
        }

        @Override // androidx.fragment.app.FragmentContainer
        public View onFindViewById(int r2) {
            if (this.val$fragmentContainer.onHasView() == false) goto L7;
            return this.val$fragmentContainer.onFindViewById(r2);
        L7:
            return this.this$0.onFindViewById(r2);
        }

        @Override // androidx.fragment.app.FragmentContainer
        public boolean onHasView() {
            if (this.val$fragmentContainer.onHasView() == false) goto L5;
            return true;
        L5:
            if (this.this$0.onHasView() == true) goto L11;
            return false;
        L11:
            return true;
        }
    }

    public DialogFragment() {
        this.mDismissRunnable = new AnonymousClass1(this);
        this.mOnCancelListener = new AnonymousClass2(this);
        this.mOnDismissListener = new AnonymousClass3(this);
        this.mStyle = 0;
        this.mTheme = 0;
        this.mCancelable = true;
        this.mShowsDialog = true;
        this.mBackStackId = -1;
        this.mObserver = new AnonymousClass4(this);
        this.mDialogCreated = false;
    }

    public DialogFragment(int r2) {
        super(r2);
        this.mDismissRunnable = new AnonymousClass1(this);
        this.mOnCancelListener = new AnonymousClass2(this);
        this.mOnDismissListener = new AnonymousClass3(this);
        this.mStyle = 0;
        this.mTheme = 0;
        this.mCancelable = true;
        this.mShowsDialog = true;
        this.mBackStackId = -1;
        this.mObserver = new AnonymousClass4(this);
        this.mDialogCreated = false;
    }

    public static /* synthetic */ Dialog access$000(DialogFragment r0) {
        return r0.mDialog;
    }

    public static /* synthetic */ DialogInterface.OnDismissListener access$100(DialogFragment r0) {
        return r0.mOnDismissListener;
    }

    public static /* synthetic */ boolean access$200(DialogFragment r0) {
        return r0.mShowsDialog;
    }

    private void dismissInternal(boolean r4, boolean r5, boolean r6) {
        if (this.mDismissed == false) goto L5;
        return;
    L5:
        this.mDismissed = true;
        this.mShownByMe = false;
        Dialog r1 = this.mDialog;
        if (r1 == null) goto L13;
        r1.setOnDismissListener(null);
        this.mDialog.dismiss();
        if (r5 == true) goto L13;
        if (Looper.myLooper() != this.mHandler.getLooper()) goto L12;
        onDismiss(this.mDialog);
        goto L13
    L12:
        this.mHandler.post(this.mDismissRunnable);
    L13:
        this.mViewDestroyed = true;
        if (this.mBackStackId < 0) goto L20;
        if (r6 == false) goto L17;
        getParentFragmentManager().popBackStackImmediate(this.mBackStackId, 1);
    L18:
        this.mBackStackId = -1;
        return;
    L17:
        getParentFragmentManager().popBackStack(this.mBackStackId, 1, r4);
        goto L18
    L20:
        FragmentTransaction r52 = getParentFragmentManager().beginTransaction();
        r52.setReorderingAllowed(true);
        r52.remove(this);
        if (r6 == false) goto L24;
        r52.commitNow();
        return;
    L24:
        if (r4 == false) goto L27;
        r52.commitAllowingStateLoss();
        return;
    L27:
        r52.commit();
    }

    private void prepareDialog(Bundle r4) {
        if (this.mShowsDialog == true) goto L6;
        return;
    L6:
        if (this.mDialogCreated == false) goto L22;
        return;
    L22:
        this.mCreatingDialog = true;     // Catch: Throwable -> L13
        Dialog r42 = onCreateDialog(r4);     // Catch: Throwable -> L13
        this.mDialog = r42;     // Catch: Throwable -> L13
        if (this.mShowsDialog == false) goto L16;
        setupDialog(r42, this.mStyle);     // Catch: Throwable -> L13
        Context r43 = getContext();     // Catch: Throwable -> L13
        if ((r43 instanceof Activity) == false) goto L15;
        this.mDialog.setOwnerActivity((Activity) r43);     // Catch: Throwable -> L13
    L15:
        this.mDialog.setCancelable(this.mCancelable);     // Catch: Throwable -> L13
        this.mDialog.setOnCancelListener(this.mOnCancelListener);     // Catch: Throwable -> L13
        this.mDialog.setOnDismissListener(this.mOnDismissListener);     // Catch: Throwable -> L13
        this.mDialogCreated = true;     // Catch: Throwable -> L13
    L17:
        this.mCreatingDialog = false;
        return;
    L16:
        this.mDialog = null;     // Catch: Throwable -> L13
    L13:
        th = move-exception;
        this.mCreatingDialog = false;
        throw th;
    }

    @Override // androidx.fragment.app.Fragment
    public FragmentContainer createFragmentContainer() {
        return new AnonymousClass5(this, super.createFragmentContainer());
    }

    public void dismiss() {
        dismissInternal(false, false, false);
    }

    public void dismissAllowingStateLoss() {
        dismissInternal(true, false, false);
    }

    public void dismissNow() {
        dismissInternal(false, false, true);
    }

    public Dialog getDialog() {
        return this.mDialog;
    }

    public boolean getShowsDialog() {
        return this.mShowsDialog;
    }

    public int getTheme() {
        return this.mTheme;
    }

    public boolean isCancelable() {
        return this.mCancelable;
    }

    @Override // androidx.fragment.app.Fragment
    @Deprecated
    public void onActivityCreated(Bundle r1) {
        super.onActivityCreated(r1);
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context r2) {
        super.onAttach(r2);
        getViewLifecycleOwnerLiveData().observeForever(this.mObserver);
        if (this.mShownByMe == true) goto L6;
        this.mDismissed = false;
        return;
    }

    public void onCancel(DialogInterface r1) {
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle r4) {
        super.onCreate(r4);
        this.mHandler = new Handler();
        if (this.mContainerId != 0) goto L5;
        boolean r0 = true;
    L6:
        this.mShowsDialog = r0;
        if (r4 == null) goto L10;
        this.mStyle = r4.getInt(SAVED_STYLE, 0);
        this.mTheme = r4.getInt(SAVED_THEME, 0);
        this.mCancelable = r4.getBoolean(SAVED_CANCELABLE, true);
        this.mShowsDialog = r4.getBoolean(SAVED_SHOWS_DIALOG, this.mShowsDialog);
        this.mBackStackId = r4.getInt(SAVED_BACK_STACK_ID, -1);
        return;
    L10:
        return;
    L5:
        r0 = false;
        goto L6
    }

    public Dialog onCreateDialog(Bundle r2) {
        if (FragmentManager.isLoggingEnabled(3) == false) goto L6;
        Log.d("FragmentManager", "onCreateDialog called for DialogFragment " + this);
    L6:
        return new ComponentDialog(requireContext(), getTheme());
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        Dialog r0 = this.mDialog;
        if (r0 == null) goto L9;
        this.mViewDestroyed = true;
        r0.setOnDismissListener(null);
        this.mDialog.dismiss();
        if (this.mDismissed == true) goto L7;
        onDismiss(this.mDialog);
    L7:
        this.mDialog = null;
        this.mDialogCreated = false;
        return;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        if (this.mShownByMe == false) goto L5;
    L7:
        getViewLifecycleOwnerLiveData().removeObserver(this.mObserver);
        return;
    L5:
        if (this.mDismissed == true) goto L7;
        this.mDismissed = true;
        goto L7
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface r2) {
        if (this.mViewDestroyed == false) goto L5;
        return;
    L5:
        if (FragmentManager.isLoggingEnabled(3) == false) goto L7;
        Log.d("FragmentManager", "onDismiss called for DialogFragment " + this);
    L7:
        dismissInternal(true, true, false);
    }

    public View onFindViewById(int r1) {
        Dialog r0 = this.mDialog;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.findViewById(r1);
    }

    @Override // androidx.fragment.app.Fragment
    public LayoutInflater onGetLayoutInflater(Bundle r5) {
        LayoutInflater r0 = super.onGetLayoutInflater(r5);
        if (this.mShowsDialog == false) goto L15;
        if (this.mCreatingDialog == true) goto L15;
        prepareDialog(r5);
        if (FragmentManager.isLoggingEnabled(2) == false) goto L10;
        Log.d("FragmentManager", "get layout inflater for DialogFragment " + this + " from dialog context");
    L10:
        Dialog r4 = this.mDialog;
        if (r4 != null) goto L13;
    L21:
        return r0;
    L13:
        return r0.cloneInContext(r4.getContext());
    L15:
        if (FragmentManager.isLoggingEnabled(2) == false) goto L21;
        String r52 = "getting layout inflater for DialogFragment " + this;
        if (this.mShowsDialog == true) goto L20;
        Log.d("FragmentManager", "mShowsDialog = false: ".concat(r52));
        return r0;
    L20:
        Log.d("FragmentManager", "mCreatingDialog = true: ".concat(r52));
        goto L21
    }

    public boolean onHasView() {
        return this.mDialogCreated;
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle r4) {
        super.onSaveInstanceState(r4);
        Dialog r0 = this.mDialog;
        if (r0 == null) goto L5;
        Bundle r02 = r0.onSaveInstanceState();
        r02.putBoolean(SAVED_INTERNAL_DIALOG_SHOWING, false);
        r4.putBundle(SAVED_DIALOG_STATE_TAG, r02);
    L5:
        int r03 = this.mStyle;
        if (r03 == 0) goto L8;
        r4.putInt(SAVED_STYLE, r03);
    L8:
        int r04 = this.mTheme;
        if (r04 == 0) goto L11;
        r4.putInt(SAVED_THEME, r04);
    L11:
        boolean r05 = this.mCancelable;
        if (r05 == true) goto L14;
        r4.putBoolean(SAVED_CANCELABLE, r05);
    L14:
        boolean r06 = this.mShowsDialog;
        if (r06 == true) goto L17;
        r4.putBoolean(SAVED_SHOWS_DIALOG, r06);
    L17:
        int r3 = this.mBackStackId;
        if (r3 == (-1)) goto L21;
        r4.putInt(SAVED_BACK_STACK_ID, r3);
        return;
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Dialog r0 = this.mDialog;
        if (r0 == null) goto L6;
        this.mViewDestroyed = false;
        r0.show();
        View r02 = this.mDialog.getWindow().getDecorView();
        ViewTreeLifecycleOwner.set(r02, this);
        ViewTreeViewModelStoreOwner.set(r02, this);
        ViewTreeSavedStateRegistryOwner.set(r02, this);
        return;
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        Dialog r0 = this.mDialog;
        if (r0 == null) goto L6;
        r0.hide();
        return;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewStateRestored(Bundle r2) {
        super.onViewStateRestored(r2);
        if (this.mDialog == null) goto L9;
        if (r2 == null) goto L10;
        Bundle r22 = r2.getBundle(SAVED_DIALOG_STATE_TAG);
        if (r22 == null) goto L11;
        this.mDialog.onRestoreInstanceState(r22);
        return;
    L11:
        return;
    L10:
        return;
    }

    @Override // androidx.fragment.app.Fragment
    public void performCreateView(LayoutInflater r1, ViewGroup r2, Bundle r3) {
        super.performCreateView(r1, r2, r3);
        if (this.mView == null) goto L5;
        return;
    L5:
        if (this.mDialog == null) goto L12;
        if (r3 == null) goto L13;
        Bundle r12 = r3.getBundle(SAVED_DIALOG_STATE_TAG);
        if (r12 == null) goto L14;
        this.mDialog.onRestoreInstanceState(r12);
        return;
    L14:
        return;
    L13:
        return;
    }

    public final Dialog requireDialog() {
        Dialog r0 = getDialog();
        if (r0 == null) goto L5;
        return r0;
    L5:
        LiveData$$ExternalSyntheticBUOutline0.m("DialogFragment ", this, " does not have a Dialog.");
        return null;
    }

    public void setCancelable(boolean r1) {
        this.mCancelable = r1;
        Dialog r0 = this.mDialog;
        if (r0 == null) goto L6;
        r0.setCancelable(r1);
        return;
    }

    public void setShowsDialog(boolean r1) {
        this.mShowsDialog = r1;
    }

    public void setStyle(int r4, int r5) {
        if (FragmentManager.isLoggingEnabled(2) == false) goto L5;
        Log.d("FragmentManager", "Setting style and theme for DialogFragment " + this + " to " + r4 + ", " + r5);
    L5:
        this.mStyle = r4;
        if (r4 != 2) goto L8;
    L9:
        this.mTheme = R.style.Theme.Panel;
    L10:
        if (r5 == 0) goto L13;
        this.mTheme = r5;
        return;
    L13:
        return;
    L8:
        if (r4 != 3) goto L10;
        goto L9
    }

    public void setupDialog(Dialog r2, int r3) {
        if (r3 != 1) goto L5;
    L12:
        r2.requestWindowFeature(1);
        return;
    L5:
        if (r3 == 2) goto L12;
        if (r3 == 3) goto L9;
        return;
    L9:
        Window r32 = r2.getWindow();
        if (r32 == null) goto L12;
        r32.addFlags(24);
        goto L12
    }

    public int show(FragmentTransaction r3, String r4) {
        this.mDismissed = false;
        this.mShownByMe = true;
        r3.add(this, r4);
        this.mViewDestroyed = false;
        int r32 = r3.commit();
        this.mBackStackId = r32;
        return r32;
    }

    public void show(FragmentManager r2, String r3) {
        this.mDismissed = false;
        this.mShownByMe = true;
        FragmentTransaction r22 = r2.beginTransaction();
        r22.setReorderingAllowed(true);
        r22.add(this, r3);
        r22.commit();
    }

    public void showNow(FragmentManager r2, String r3) {
        this.mDismissed = false;
        this.mShownByMe = true;
        FragmentTransaction r22 = r2.beginTransaction();
        r22.setReorderingAllowed(true);
        r22.add(this, r3);
        r22.commitNow();
    }
}
