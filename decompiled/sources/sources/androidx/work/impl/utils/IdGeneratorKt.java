package androidx.work.impl.utils;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.model.Preference;
import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u001b\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u0004\u0010\u0005\u001a#\u0010\b\u001a\u00020\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\b\u0010\t\u001a\u001f\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\fH\u0000¢\u0006\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Landroidx/work/impl/WorkDatabase;", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.KEY, _UrlKt.FRAGMENT_ENCODE_SET, "nextId", "(Landroidx/work/impl/WorkDatabase;Ljava/lang/String;)I", "value", _UrlKt.FRAGMENT_ENCODE_SET, "updatePreference", "(Landroidx/work/impl/WorkDatabase;Ljava/lang/String;I)V", "Landroid/content/Context;", "context", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "sqLiteDatabase", "migrateLegacyIdGenerator", "(Landroid/content/Context;Landroidx/sqlite/db/SupportSQLiteDatabase;)V", "work-runtime_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class IdGeneratorKt {
    public static final /* synthetic */ int access$nextId(WorkDatabase r0, String r1) {
        return nextId(r0, r1);
    }

    public static final /* synthetic */ void access$updatePreference(WorkDatabase r0, String r1, int r2) {
        updatePreference(r0, r1, r2);
    }

    public static final void migrateLegacyIdGenerator(Context r5, SupportSQLiteDatabase r6) {
        SharedPreferences r52 = r5.getSharedPreferences("androidx.work.util.id", 0);
        if (r52.contains("next_job_scheduler_id") == false) goto L5;
    L8:
        int r3 = r52.getInt("next_job_scheduler_id", 0);
        int r2 = r52.getInt("next_alarm_manager_id", 0);
        r6.beginTransaction();
        r6.execSQL("INSERT OR REPLACE INTO `Preference` (`key`, `long_value`) VALUES (@key, @long_value)", new Object[]{"next_job_scheduler_id", Integer.valueOf(r3)});     // Catch: Throwable -> L12
        r6.execSQL("INSERT OR REPLACE INTO `Preference` (`key`, `long_value`) VALUES (@key, @long_value)", new Object[]{"next_alarm_manager_id", Integer.valueOf(r2)});     // Catch: Throwable -> L12
        r52.edit().clear().apply();     // Catch: Throwable -> L12
        r6.setTransactionSuccessful();     // Catch: Throwable -> L12
        r6.endTransaction();
        return;
    L12:
        th = move-exception;
        r6.endTransaction();
        throw th;
    L5:
        if (r52.contains("next_job_scheduler_id") == true) goto L8;
    }

    private static final int nextId(WorkDatabase r4, String r5) {
        Long r0 = r4.preferenceDao().getLongValue(r5);
        int r1 = 0;
        if (r0 == null) goto L5;
        int r02 = (int) r0.longValue();
    L7:
        if (r02 == Integer.MAX_VALUE) goto L10;
        r1 = r02 + 1;
    L10:
        updatePreference(r4, r5, r1);
        return r02;
    L5:
        r02 = 0;
        goto L7
    }

    private static final void updatePreference(WorkDatabase r3, String r4, int r5) {
        r3.preferenceDao().insertPreference(new Preference(r4, Long.valueOf(r5)));
    }
}
