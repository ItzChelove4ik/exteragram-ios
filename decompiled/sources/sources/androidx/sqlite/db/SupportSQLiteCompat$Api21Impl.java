package androidx.sqlite.db;

import android.content.Context;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"androidx/sqlite/db/SupportSQLiteCompat$Api21Impl", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroid/content/Context;", "context", "Ljava/io/File;", "getNoBackupFilesDir", "(Landroid/content/Context;)Ljava/io/File;", "sqlite"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SupportSQLiteCompat$Api21Impl {
    public static final SupportSQLiteCompat$Api21Impl INSTANCE = null;

    static {
        INSTANCE = new SupportSQLiteCompat$Api21Impl();
    }

    private SupportSQLiteCompat$Api21Impl() {
    }

    @JvmStatic
    public static final File getNoBackupFilesDir(Context r0) {
        return r0.getNoBackupFilesDir();
    }
}
