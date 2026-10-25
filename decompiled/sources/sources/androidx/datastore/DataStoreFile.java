package androidx.datastore;

import android.content.Context;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"dataStoreFile", "Ljava/io/File;", "Landroid/content/Context;", "fileName", _UrlKt.FRAGMENT_ENCODE_SET, "datastore_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
@JvmName(name = "DataStoreFile")
/* loaded from: classes.dex */
public abstract class DataStoreFile {
    public static final File dataStoreFile(Context r3, String r4) {
        return new File(r3.getApplicationContext().getFilesDir(), "datastore/" + r4);
    }
}
