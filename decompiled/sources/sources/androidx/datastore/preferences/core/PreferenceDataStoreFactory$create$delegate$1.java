package androidx.datastore.preferences.core;

import androidx.lifecycle.LifecycleRegistry$$ExternalSyntheticBUOutline0;
import java.io.File;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljava/io/File;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class PreferenceDataStoreFactory$create$delegate$1 extends Lambda implements Function0<File> {
    final /* synthetic */ Function0<File> $produceFile;

    /* JADX WARN: Multi-variable type inference failed */
    public PreferenceDataStoreFactory$create$delegate$1(Function0<? extends File> r1) {
        this.$produceFile = r1;
        super(0);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final File invoke() {
        File r2 = this.$produceFile.invoke();
        if (Intrinsics.areEqual(FilesKt.getExtension(r2), "preferences_pb") == true) goto L5;
        LifecycleRegistry$$ExternalSyntheticBUOutline0.m("File extension for file: ", r2, " does not match required extension for Preferences file: preferences_pb");
        return null;
    L5:
        return r2.getAbsoluteFile();
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ File invoke() {
        return invoke();
    }
}
