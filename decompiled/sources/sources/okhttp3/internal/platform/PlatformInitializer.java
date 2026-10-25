package okhttp3.internal.platform;

import android.content.Context;
import androidx.startup.Initializer;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00010\n0\tH\u0016¨\u0006\u000b"}, d2 = {"Lokhttp3/internal/platform/PlatformInitializer;", "Landroidx/startup/Initializer;", "Lokhttp3/internal/platform/Platform;", "<init>", "()V", "create", "context", "Landroid/content/Context;", "dependencies", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/lang/Class;", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class PlatformInitializer implements Initializer<Platform> {
    public PlatformInitializer() {
    }

    @Override // androidx.startup.Initializer
    public /* bridge */ /* synthetic */ Platform create(Context r1) {
        return create2(r1);
    }

    @Override // androidx.startup.Initializer
    /* renamed from: create, reason: avoid collision after fix types in other method */
    public Platform create2(Context r1) {
        PlatformRegistry.INSTANCE.setApplicationContext(r1);
        return Platform.Companion.get();
    }

    @Override // androidx.startup.Initializer
    public List<Class<Initializer<?>>> dependencies() {
        return CollectionsKt.emptyList();
    }
}
