package androidx.datastore.preferences;

import android.content.Context;
import androidx.datastore.DataStoreFile;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"preferencesDataStoreFile", "Ljava/io/File;", "Landroid/content/Context;", "name", _UrlKt.FRAGMENT_ENCODE_SET, "datastore-preferences_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
@JvmName(name = "PreferenceDataStoreFile")
/* loaded from: classes.dex */
public abstract class PreferenceDataStoreFile {
    public static final File preferencesDataStoreFile(Context r1, String r2) {
        return DataStoreFile.dataStoreFile(r1, r2 + ".preferences_pb");
    }
}
