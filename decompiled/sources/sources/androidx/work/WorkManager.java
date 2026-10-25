package androidx.work;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.work.impl.WorkManagerImpl;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b'\u0018\u0000 \u00132\u00020\u0001:\u0002\u0013\u0014B\t\b\u0000¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\u0007\u001a\u00020\u00062\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\tH&¢\u0006\u0004\b\u0007\u0010\u000bJ'\u0010\u0011\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u0010H&¢\u0006\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Landroidx/work/WorkManager;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroidx/work/WorkRequest;", PluginsConstants.REQUEST, "Landroidx/work/Operation;", "enqueue", "(Landroidx/work/WorkRequest;)Landroidx/work/Operation;", _UrlKt.FRAGMENT_ENCODE_SET, "requests", "(Ljava/util/List;)Landroidx/work/Operation;", _UrlKt.FRAGMENT_ENCODE_SET, "uniqueWorkName", "Landroidx/work/ExistingPeriodicWorkPolicy;", "existingPeriodicWorkPolicy", "Landroidx/work/PeriodicWorkRequest;", "enqueueUniquePeriodicWork", "(Ljava/lang/String;Landroidx/work/ExistingPeriodicWorkPolicy;Landroidx/work/PeriodicWorkRequest;)Landroidx/work/Operation;", "Companion", "UpdateResult", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SuppressLint({"AddedAbstractMethod"})
/* loaded from: classes.dex */
public abstract class WorkManager {
    public static final Companion Companion = null;

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0017¢\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\tH\u0017¢\u0006\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Landroidx/work/WorkManager$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroid/content/Context;", "context", "Landroidx/work/WorkManager;", "getInstance", "(Landroid/content/Context;)Landroidx/work/WorkManager;", "Landroidx/work/Configuration;", "configuration", _UrlKt.FRAGMENT_ENCODE_SET, "initialize", "(Landroid/content/Context;Landroidx/work/Configuration;)V", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        public WorkManager getInstance(Context r1) {
            return WorkManagerImpl.getInstance(r1);
        }

        @JvmStatic
        public void initialize(Context r1, Configuration r2) {
            WorkManagerImpl.initialize(r1, r2);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Landroidx/work/WorkManager$UpdateResult;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "NOT_APPLIED", "APPLIED_IMMEDIATELY", "APPLIED_FOR_NEXT_RUN", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public enum UpdateResult extends Enum<UpdateResult> {
        private static final /* synthetic */ EnumEntries $ENTRIES = null;
        private static final /* synthetic */ UpdateResult[] $VALUES = null;
        public static final UpdateResult APPLIED_FOR_NEXT_RUN = null;
        public static final UpdateResult APPLIED_IMMEDIATELY = null;
        public static final UpdateResult NOT_APPLIED = null;

        private static final /* synthetic */ UpdateResult[] $values() {
            return new UpdateResult[]{NOT_APPLIED, APPLIED_IMMEDIATELY, APPLIED_FOR_NEXT_RUN};
        }

        static {
            NOT_APPLIED = new UpdateResult("NOT_APPLIED", 0);
            APPLIED_IMMEDIATELY = new UpdateResult("APPLIED_IMMEDIATELY", 1);
            APPLIED_FOR_NEXT_RUN = new UpdateResult("APPLIED_FOR_NEXT_RUN", 2);
            UpdateResult[] r0 = $values();
            $VALUES = r0;
            $ENTRIES = EnumEntriesKt.enumEntries(r0);
        }

        UpdateResult(String r1, int r2) {
        }

        public static UpdateResult valueOf(String r1) {
            return (UpdateResult) Enum.valueOf(UpdateResult.class, r1);
        }

        public static UpdateResult[] values() {
            return (UpdateResult[]) $VALUES.clone();
        }
    }

    static {
        Companion = new Companion(null);
    }

    public WorkManager() {
    }

    @JvmStatic
    public static WorkManager getInstance(Context r1) {
        return Companion.getInstance(r1);
    }

    @JvmStatic
    public static void initialize(Context r1, Configuration r2) {
        Companion.initialize(r1, r2);
    }

    public final Operation enqueue(WorkRequest r1) {
        return enqueue(CollectionsKt.listOf(r1));
    }

    public abstract Operation enqueue(List<? extends WorkRequest> r1);

    public abstract Operation enqueueUniquePeriodicWork(String r1, ExistingPeriodicWorkPolicy r2, PeriodicWorkRequest r3);
}
