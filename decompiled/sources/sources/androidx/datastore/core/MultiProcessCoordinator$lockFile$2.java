package androidx.datastore.core;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljava/io/File;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class MultiProcessCoordinator$lockFile$2 extends Lambda implements Function0<File> {
    final /* synthetic */ MultiProcessCoordinator this$0;

    public MultiProcessCoordinator$lockFile$2(MultiProcessCoordinator r1) {
        this.this$0 = r1;
        super(0);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final File invoke() {
        MultiProcessCoordinator r0 = this.this$0;
        File r02 = MultiProcessCoordinator.access$fileWithSuffix(r0, MultiProcessCoordinator.access$getLOCK_SUFFIX$p(r0));
        MultiProcessCoordinator.access$createIfNotExists(this.this$0, r02);
        return r02;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ File invoke() {
        return invoke();
    }
}
