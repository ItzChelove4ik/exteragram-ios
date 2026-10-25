package androidx.lifecycle;

import androidx.lifecycle.ReportFragment;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0003H\u0016¨\u0006\u0006"}, d2 = {"androidx/lifecycle/ProcessLifecycleOwner$initializationListener$1", "Landroidx/lifecycle/ReportFragment$ActivityInitializationListener;", "onCreate", _UrlKt.FRAGMENT_ENCODE_SET, "onStart", "onResume", "lifecycle-process"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ProcessLifecycleOwner$initializationListener$1 implements ReportFragment.ActivityInitializationListener {
    final /* synthetic */ ProcessLifecycleOwner this$0;

    public ProcessLifecycleOwner$initializationListener$1(ProcessLifecycleOwner r1) {
        this.this$0 = r1;
    }

    @Override // androidx.lifecycle.ReportFragment.ActivityInitializationListener
    public void onCreate() {
    }

    @Override // androidx.lifecycle.ReportFragment.ActivityInitializationListener
    public void onResume() {
        this.this$0.activityResumed$lifecycle_process();
    }

    @Override // androidx.lifecycle.ReportFragment.ActivityInitializationListener
    public void onStart() {
        this.this$0.activityStarted$lifecycle_process();
    }
}
