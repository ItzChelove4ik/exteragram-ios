package androidx.lifecycle;

import android.view.View;
import androidx.lifecycle.runtime.R$id;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u001a\u001d\u0010\u0006\u001a\u00020\u0003*\u00020\u00002\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\u0007¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0007"}, d2 = {"Landroid/view/View;", "Landroidx/lifecycle/LifecycleOwner;", "lifecycleOwner", _UrlKt.FRAGMENT_ENCODE_SET, "set", "(Landroid/view/View;Landroidx/lifecycle/LifecycleOwner;)V", "setViewTreeLifecycleOwner", "lifecycle-runtime"}, k = 2, mv = {2, 1, 0}, xi = 48)
@JvmName(name = "ViewTreeLifecycleOwner")
/* loaded from: classes.dex */
public abstract class ViewTreeLifecycleOwner {
    @JvmName(name = "set")
    public static final void set(View r1, LifecycleOwner r2) {
        r1.setTag(R$id.view_tree_lifecycle_owner, r2);
    }
}
