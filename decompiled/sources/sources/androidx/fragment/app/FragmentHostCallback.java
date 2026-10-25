package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.util.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public abstract class FragmentHostCallback<E> extends FragmentContainer {
    private final Activity mActivity;
    private final Context mContext;
    final FragmentManager mFragmentManager;
    private final Handler mHandler;
    private final int mWindowAnimations;

    public FragmentHostCallback(Activity r2, Context r3, Handler r4, int r5) {
        this.mFragmentManager = new FragmentManagerImpl();
        this.mActivity = r2;
        this.mContext = (Context) Preconditions.checkNotNull(r3, "context == null");
        this.mHandler = (Handler) Preconditions.checkNotNull(r4, "handler == null");
        this.mWindowAnimations = r5;
    }

    public FragmentHostCallback(FragmentActivity r3) {
        this(r3, r3, new Handler(), 0);
    }

    public Activity getActivity() {
        return this.mActivity;
    }

    public Context getContext() {
        return this.mContext;
    }

    public Handler getHandler() {
        return this.mHandler;
    }

    public abstract void onDump(String r1, FileDescriptor r2, PrintWriter r3, String[] r4);

    public abstract E onGetHost();

    public abstract LayoutInflater onGetLayoutInflater();

    @Deprecated
    public void onRequestPermissionsFromFragment(Fragment r1, String[] r2, int r3) {
    }

    public abstract boolean onShouldShowRequestPermissionRationale(String r1);

    public void onStartActivityFromFragment(Fragment r1, @SuppressLint({"UnknownNullness"}) Intent r2, int r3, Bundle r4) {
        if (r3 != (-1)) goto L6;
        ContextCompat.startActivity(this.mContext, r2, r4);
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("Starting activity with a requestCode requires a FragmentActivity host");
    }

    @Deprecated
    public void onStartIntentSenderFromFragment(Fragment r9, @SuppressLint({"UnknownNullness"}) IntentSender r10, int r11, Intent r12, int r13, int r14, int r15, Bundle r16) {
        if (r11 != (-1)) goto L6;
        ActivityCompat.startIntentSenderForResult(this.mActivity, r10, r11, r12, r13, r14, r15, r16);
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("Starting intent sender with a requestCode requires a FragmentActivity host");
    }

    public abstract void onSupportInvalidateOptionsMenu();
}
