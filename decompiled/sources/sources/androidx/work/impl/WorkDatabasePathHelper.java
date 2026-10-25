package androidx.work.impl;

import android.content.Context;
import androidx.work.Logger;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\f\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\r"}, d2 = {"Landroidx/work/impl/WorkDatabasePathHelper;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "migrateDatabase", _UrlKt.FRAGMENT_ENCODE_SET, "context", "Landroid/content/Context;", "migrationPaths", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/io/File;", "getDefaultDatabasePath", "getDatabasePath", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nWorkDatabasePathHelper.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WorkDatabasePathHelper.kt\nandroidx/work/impl/WorkDatabasePathHelper\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,94:1\n216#2,2:95\n8569#3,2:97\n9251#3,4:99\n*S KotlinDebug\n*F\n+ 1 WorkDatabasePathHelper.kt\nandroidx/work/impl/WorkDatabasePathHelper\n*L\n44#1:95,2\n72#1:97,2\n72#1:99,4\n*E\n"})
/* loaded from: classes.dex */
public final class WorkDatabasePathHelper {
    public static final WorkDatabasePathHelper INSTANCE = null;

    static {
        INSTANCE = new WorkDatabasePathHelper();
    }

    private WorkDatabasePathHelper() {
    }

    @JvmStatic
    public static final void migrateDatabase(Context r6) {
        WorkDatabasePathHelper r0 = INSTANCE;
        if (r0.getDefaultDatabasePath(r6).exists() == false) goto L17;
        Logger.get().debug(WorkDatabasePathHelperKt.access$getTAG$p(), "Migrating WorkDatabase to the no-backup directory");
        Iterator<Map.Entry<File, File>> r62 = r0.migrationPaths(r6).entrySet().iterator();
    L6:
        if (r62.hasNext() == false) goto L23;
        Map.Entry<File, File> r02 = r62.next();
        File r1 = r02.getKey();
        File r03 = r02.getValue();
        if (r1.exists() == false) goto L6;
        if (r03.exists() == false) goto L13;
        Logger.get().warning(WorkDatabasePathHelperKt.access$getTAG$p(), "Over-writing contents of " + r03);
    L13:
        if (r1.renameTo(r03) == false) goto L15;
        String r04 = "Migrated " + r1 + "to " + r03;
    L16:
        Logger.get().debug(WorkDatabasePathHelperKt.access$getTAG$p(), r04);
        goto L6
    L15:
        r04 = "Renaming " + r1 + " to " + r03 + " failed";
        goto L16
    L23:
        return;
    }

    public final File getDatabasePath(Context r1) {
        return r1.getNoBackupFilesDir();
    }

    public final File getDefaultDatabasePath(Context r1) {
        return r1.getDatabasePath("androidx.work.workdb");
    }

    public final Map<File, File> migrationPaths(Context r10) {
        File r0 = getDefaultDatabasePath(r10);
        File r9 = getDatabasePath(r10);
        String[] r102 = WorkDatabasePathHelperKt.access$getDATABASE_EXTRA_FILES$p();
        LinkedHashMap r2 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(r102.length), 16));
        int r1 = r102.length;
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L6;
        String r4 = r102[r3];
        Pair r42 = TuplesKt.to(new File(r0.getPath() + r4), new File(r9.getPath() + r4));
        r2.put(r42.getFirst(), r42.getSecond());
        r3 = r3 + 1;
        goto L3
    L6:
        return MapsKt.plus(r2, TuplesKt.to(r0, r9));
    }
}
