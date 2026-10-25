package androidx.lifecycle;

import androidx.lifecycle.viewmodel.internal.ViewModelImpl;
import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\t\b\u0016¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0015¢\u0006\u0004\b\u0005\u0010\u0003J\u000f\u0010\u0007\u001a\u00020\u0004H\u0001¢\u0006\u0004\b\u0006\u0010\u0003J!\u0010\r\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\b2\n\u0010\f\u001a\u00060\nj\u0002`\u000b¢\u0006\u0004\b\r\u0010\u000eJ%\u0010\u0010\u001a\u0004\u0018\u00018\u0000\"\f\b\u0000\u0010\u000f*\u00060\nj\u0002`\u000b2\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Landroidx/lifecycle/ViewModel;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "onCleared", "clear$lifecycle_viewmodel", "clear", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.KEY, "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "closeable", "addCloseable", "(Ljava/lang/String;Ljava/lang/AutoCloseable;)V", "T", "getCloseable", "(Ljava/lang/String;)Ljava/lang/AutoCloseable;", "Landroidx/lifecycle/viewmodel/internal/ViewModelImpl;", "impl", "Landroidx/lifecycle/viewmodel/internal/ViewModelImpl;", "lifecycle-viewmodel"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class ViewModel {
    private final ViewModelImpl impl;

    public ViewModel() {
        this.impl = new ViewModelImpl();
    }

    public final void addCloseable(String r1, AutoCloseable r2) {
        ViewModelImpl r0 = this.impl;
        if (r0 == null) goto L6;
        r0.addCloseable(r1, r2);
        return;
    }

    public final void clear$lifecycle_viewmodel() {
        ViewModelImpl r0 = this.impl;
        if (r0 == null) goto L5;
        r0.clear();
    L5:
        onCleared();
    }

    public final <T extends AutoCloseable> T getCloseable(String r1) {
        ViewModelImpl r0 = this.impl;
        if (r0 != null) goto L5;
        return null;
    L5:
        return (T) r0.getCloseable(r1);
    }

    public void onCleared() {
    }
}
