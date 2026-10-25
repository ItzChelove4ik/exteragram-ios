package androidx.datastore.preferences;

import android.content.Context;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljava/io/File;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class PreferenceDataStoreSingletonDelegate$getValue$1$1 extends Lambda implements Function0<File> {
    final /* synthetic */ Context $applicationContext;
    final /* synthetic */ PreferenceDataStoreSingletonDelegate this$0;

    public PreferenceDataStoreSingletonDelegate$getValue$1$1(Context r1, PreferenceDataStoreSingletonDelegate r2) {
        this.$applicationContext = r1;
        this.this$0 = r2;
        super(0);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final File invoke() {
        return PreferenceDataStoreFile.preferencesDataStoreFile(this.$applicationContext, PreferenceDataStoreSingletonDelegate.access$getName$p(this.this$0));
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ File invoke() {
        return invoke();
    }
}
