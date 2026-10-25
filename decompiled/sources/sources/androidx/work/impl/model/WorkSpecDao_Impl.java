package androidx.work.impl.model;

import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteConnectionUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OutOfQuotaPolicy;
import androidx.work.WorkInfo$State;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.NetworkRequestCompat;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KClass;
import kotlinx.coroutines.flow.Flow;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u001f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 O2\u00020\u0001:\u0001OB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u000b\u0010\nJ\u0019\u0010\u000e\u001a\u0004\u0018\u00010\u00062\u0006\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u001d\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0010\u001a\u00020\fH\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u0019\u0010\u0016\u001a\u0004\u0018\u00010\u00152\u0006\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u001d\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u00112\u0006\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\u0019\u0010\u0014J\u001d\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\f0\u00112\u0006\u0010\u0010\u001a\u00020\fH\u0016¢\u0006\u0004\b\u001a\u0010\u0014J\u0015\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ\u001d\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00060\u00112\u0006\u0010 \u001a\u00020\u001fH\u0016¢\u0006\u0004\b!\u0010\"J\u0015\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00060\u0011H\u0016¢\u0006\u0004\b#\u0010$J\u001d\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00060\u00112\u0006\u0010%\u001a\u00020\u001fH\u0016¢\u0006\u0004\b&\u0010\"J\u0015\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00060\u0011H\u0016¢\u0006\u0004\b'\u0010$J\u0015\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00060\u0011H\u0016¢\u0006\u0004\b(\u0010$J\u001d\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00060\u00112\u0006\u0010*\u001a\u00020)H\u0016¢\u0006\u0004\b+\u0010,J\u000f\u0010-\u001a\u00020\u001fH\u0016¢\u0006\u0004\b-\u0010.J\u0017\u0010/\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b/\u00100J\u001f\u00102\u001a\u00020\u001f2\u0006\u00101\u001a\u00020\u00152\u0006\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b2\u00103J\u0017\u00104\u001a\u00020\u001f2\u0006\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b4\u00105J\u0017\u00106\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b6\u00100J\u001f\u00108\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\f2\u0006\u00107\u001a\u00020\u0018H\u0016¢\u0006\u0004\b8\u00109J\u001f\u0010;\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010:\u001a\u00020)H\u0016¢\u0006\u0004\b;\u0010<J\u0017\u0010=\u001a\u00020\u001f2\u0006\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b=\u00105J\u0017\u0010>\u001a\u00020\u001f2\u0006\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b>\u00105J\u001f\u0010@\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010?\u001a\u00020\u001fH\u0016¢\u0006\u0004\b@\u0010AJ\u001f\u0010C\u001a\u00020\u001f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010B\u001a\u00020)H\u0016¢\u0006\u0004\bC\u0010DJ\u000f\u0010E\u001a\u00020\u001fH\u0016¢\u0006\u0004\bE\u0010.J\u001f\u0010G\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010F\u001a\u00020\u001fH\u0016¢\u0006\u0004\bG\u0010AR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010HR\u001a\u0010J\u001a\b\u0012\u0004\u0012\u00020\u00060I8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bJ\u0010KR\u001a\u0010M\u001a\b\u0012\u0004\u0012\u00020\u00060L8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bM\u0010N¨\u0006P"}, d2 = {"Landroidx/work/impl/model/WorkSpecDao_Impl;", "Landroidx/work/impl/model/WorkSpecDao;", "Landroidx/room/RoomDatabase;", "__db", "<init>", "(Landroidx/room/RoomDatabase;)V", "Landroidx/work/impl/model/WorkSpec;", "workSpec", _UrlKt.FRAGMENT_ENCODE_SET, "insertWorkSpec", "(Landroidx/work/impl/model/WorkSpec;)V", "updateWorkSpec", _UrlKt.FRAGMENT_ENCODE_SET, "id", "getWorkSpec", "(Ljava/lang/String;)Landroidx/work/impl/model/WorkSpec;", "name", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/work/impl/model/WorkSpec$IdAndState;", "getWorkSpecIdAndStatesForName", "(Ljava/lang/String;)Ljava/util/List;", "Landroidx/work/WorkInfo$State;", "getState", "(Ljava/lang/String;)Landroidx/work/WorkInfo$State;", "Landroidx/work/Data;", "getInputsFromPrerequisites", "getUnfinishedWorkWithName", "Lkotlinx/coroutines/flow/Flow;", _UrlKt.FRAGMENT_ENCODE_SET, "hasUnfinishedWorkFlow", "()Lkotlinx/coroutines/flow/Flow;", _UrlKt.FRAGMENT_ENCODE_SET, "schedulerLimit", "getEligibleWorkForScheduling", "(I)Ljava/util/List;", "getEligibleWorkForSchedulingWithContentUris", "()Ljava/util/List;", "maxLimit", "getAllEligibleWorkSpecsForScheduling", "getScheduledWork", "getRunningWork", _UrlKt.FRAGMENT_ENCODE_SET, "startingAt", "getRecentlyCompletedWork", "(J)Ljava/util/List;", "countNonFinishedContentUriTriggerWorkers", "()I", "delete", "(Ljava/lang/String;)V", "state", "setState", "(Landroidx/work/WorkInfo$State;Ljava/lang/String;)I", "setCancelledState", "(Ljava/lang/String;)I", "incrementPeriodCount", "output", "setOutput", "(Ljava/lang/String;Landroidx/work/Data;)V", "enqueueTime", "setLastEnqueueTime", "(Ljava/lang/String;J)V", "incrementWorkSpecRunAttemptCount", "resetWorkSpecRunAttemptCount", "overrideGeneration", "resetWorkSpecNextScheduleTimeOverride", "(Ljava/lang/String;I)V", "startTime", "markWorkSpecScheduled", "(Ljava/lang/String;J)I", "resetScheduledState", "stopReason", "setStopReason", "Landroidx/room/RoomDatabase;", "Landroidx/room/EntityInsertAdapter;", "__insertAdapterOfWorkSpec", "Landroidx/room/EntityInsertAdapter;", "Landroidx/room/EntityDeleteOrUpdateAdapter;", "__updateAdapterOfWorkSpec", "Landroidx/room/EntityDeleteOrUpdateAdapter;", "Companion", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nWorkSpecDao_Impl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WorkSpecDao_Impl.kt\nandroidx/work/impl/model/WorkSpecDao_Impl\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,3476:1\n1#2:3477\n*E\n"})
/* loaded from: classes.dex */
public final class WorkSpecDao_Impl implements WorkSpecDao {
    public static final Companion Companion = null;
    private final RoomDatabase __db;
    private final EntityInsertAdapter<WorkSpec> __insertAdapterOfWorkSpec;
    private final EntityDeleteOrUpdateAdapter<WorkSpec> __updateAdapterOfWorkSpec;

    @Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0004H\u0014J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0002H\u0014¨\u0006\n"}, d2 = {"androidx/work/impl/model/WorkSpecDao_Impl$1", "Landroidx/room/EntityInsertAdapter;", "Landroidx/work/impl/model/WorkSpec;", "createQuery", _UrlKt.FRAGMENT_ENCODE_SET, "bind", _UrlKt.FRAGMENT_ENCODE_SET, "statement", "Landroidx/sqlite/SQLiteStatement;", "entity", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nWorkSpecDao_Impl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WorkSpecDao_Impl.kt\nandroidx/work/impl/model/WorkSpecDao_Impl$1\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,3476:1\n1#2:3477\n*E\n"})
    /* renamed from: androidx.work.impl.model.WorkSpecDao_Impl$1, reason: invalid class name */
    public static final class AnonymousClass1 extends EntityInsertAdapter<WorkSpec> {
        public AnonymousClass1() {
        }

        /* renamed from: bind, reason: avoid collision after fix types in other method */
        public void bind2(SQLiteStatement r4, WorkSpec r5) {
            r4.bindText(1, r5.id);
            r4.bindLong(2, WorkTypeConverters.stateToInt(r5.state));
            r4.bindText(3, r5.workerClassName);
            r4.bindText(4, r5.inputMergerClassName);
            Data.Companion r3 = Data.Companion;
            r4.bindBlob(5, r3.toByteArrayInternalV1(r5.input));
            r4.bindBlob(6, r3.toByteArrayInternalV1(r5.output));
            r4.bindLong(7, r5.initialDelay);
            r4.bindLong(8, r5.intervalDuration);
            r4.bindLong(9, r5.flexDuration);
            r4.bindLong(10, r5.runAttemptCount);
            r4.bindLong(11, WorkTypeConverters.backoffPolicyToInt(r5.backoffPolicy));
            r4.bindLong(12, r5.backoffDelayDuration);
            r4.bindLong(13, r5.lastEnqueueTime);
            r4.bindLong(14, r5.minimumRetentionDuration);
            r4.bindLong(15, r5.scheduleRequestedAt);
            r4.bindLong(16, r5.expedited ? 1 : 0);
            r4.bindLong(17, WorkTypeConverters.outOfQuotaPolicyToInt(r5.outOfQuotaPolicy));
            r4.bindLong(18, r5.getPeriodCount());
            r4.bindLong(19, r5.getGeneration());
            r4.bindLong(20, r5.getNextScheduleTimeOverride());
            r4.bindLong(21, r5.getNextScheduleTimeOverrideGeneration());
            r4.bindLong(22, r5.getStopReason());
            String r32 = r5.getTraceTag();
            if (r32 != null) goto L5;
            r4.bindNull(23);
        L6:
            Boolean r33 = r5.getBackOffOnSystemInterruptions();
            if (r33 == null) goto L9;
            Integer r34 = Integer.valueOf(r33.booleanValue() ? 1 : 0);
        L11:
            if (r34 != null) goto L13;
            r4.bindNull(24);
        L14:
            Constraints r35 = r5.constraints;
            r4.bindLong(25, WorkTypeConverters.networkTypeToInt(r35.getRequiredNetworkType()));
            r4.bindBlob(26, WorkTypeConverters.fromNetworkRequest$work_runtime_release(r35.getRequiredNetworkRequestCompat$work_runtime_release()));
            r4.bindLong(27, r35.requiresCharging() ? 1 : 0);
            r4.bindLong(28, r35.requiresDeviceIdle() ? 1 : 0);
            r4.bindLong(29, r35.requiresBatteryNotLow() ? 1 : 0);
            r4.bindLong(30, r35.requiresStorageNotLow() ? 1 : 0);
            r4.bindLong(31, r35.getContentTriggerUpdateDelayMillis());
            r4.bindLong(32, r35.getContentTriggerMaxDelayMillis());
            r4.bindBlob(33, WorkTypeConverters.setOfTriggersToByteArray(r35.getContentUriTriggers()));
            return;
        L13:
            r4.bindLong(24, r34.intValue());
            goto L14
        L9:
            r34 = null;
            goto L11
        L5:
            r4.bindText(23, r32);
            goto L6
        }

        @Override // androidx.room.EntityInsertAdapter
        public /* bridge */ /* synthetic */ void bind(SQLiteStatement r1, WorkSpec r2) {
            bind2(r1, r2);
        }

        @Override // androidx.room.EntityInsertAdapter
        public String createQuery() {
            return "INSERT OR IGNORE INTO `WorkSpec` (`id`,`state`,`worker_class_name`,`input_merger_class_name`,`input`,`output`,`initial_delay`,`interval_duration`,`flex_duration`,`run_attempt_count`,`backoff_policy`,`backoff_delay_duration`,`last_enqueue_time`,`minimum_retention_duration`,`schedule_requested_at`,`run_in_foreground`,`out_of_quota_policy`,`period_count`,`generation`,`next_schedule_time_override`,`next_schedule_time_override_generation`,`stop_reason`,`trace_tag`,`backoff_on_system_interruptions`,`required_network_type`,`required_network_request`,`requires_charging`,`requires_device_idle`,`requires_battery_not_low`,`requires_storage_not_low`,`trigger_content_update_delay`,`trigger_max_content_delay`,`content_uri_triggers`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    @Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0004H\u0014J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0002H\u0014¨\u0006\n"}, d2 = {"androidx/work/impl/model/WorkSpecDao_Impl$2", "Landroidx/room/EntityDeleteOrUpdateAdapter;", "Landroidx/work/impl/model/WorkSpec;", "createQuery", _UrlKt.FRAGMENT_ENCODE_SET, "bind", _UrlKt.FRAGMENT_ENCODE_SET, "statement", "Landroidx/sqlite/SQLiteStatement;", "entity", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nWorkSpecDao_Impl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WorkSpecDao_Impl.kt\nandroidx/work/impl/model/WorkSpecDao_Impl$2\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,3476:1\n1#2:3477\n*E\n"})
    /* renamed from: androidx.work.impl.model.WorkSpecDao_Impl$2, reason: invalid class name */
    public static final class AnonymousClass2 extends EntityDeleteOrUpdateAdapter<WorkSpec> {
        public AnonymousClass2() {
        }

        /* renamed from: bind, reason: avoid collision after fix types in other method */
        public void bind2(SQLiteStatement r5, WorkSpec r6) {
            r5.bindText(1, r6.id);
            r5.bindLong(2, WorkTypeConverters.stateToInt(r6.state));
            r5.bindText(3, r6.workerClassName);
            r5.bindText(4, r6.inputMergerClassName);
            Data.Companion r4 = Data.Companion;
            r5.bindBlob(5, r4.toByteArrayInternalV1(r6.input));
            r5.bindBlob(6, r4.toByteArrayInternalV1(r6.output));
            r5.bindLong(7, r6.initialDelay);
            r5.bindLong(8, r6.intervalDuration);
            r5.bindLong(9, r6.flexDuration);
            r5.bindLong(10, r6.runAttemptCount);
            r5.bindLong(11, WorkTypeConverters.backoffPolicyToInt(r6.backoffPolicy));
            r5.bindLong(12, r6.backoffDelayDuration);
            r5.bindLong(13, r6.lastEnqueueTime);
            r5.bindLong(14, r6.minimumRetentionDuration);
            r5.bindLong(15, r6.scheduleRequestedAt);
            r5.bindLong(16, r6.expedited ? 1 : 0);
            r5.bindLong(17, WorkTypeConverters.outOfQuotaPolicyToInt(r6.outOfQuotaPolicy));
            r5.bindLong(18, r6.getPeriodCount());
            r5.bindLong(19, r6.getGeneration());
            r5.bindLong(20, r6.getNextScheduleTimeOverride());
            r5.bindLong(21, r6.getNextScheduleTimeOverrideGeneration());
            r5.bindLong(22, r6.getStopReason());
            String r42 = r6.getTraceTag();
            if (r42 != null) goto L5;
            r5.bindNull(23);
        L6:
            Boolean r43 = r6.getBackOffOnSystemInterruptions();
            if (r43 == null) goto L9;
            Integer r44 = Integer.valueOf(r43.booleanValue() ? 1 : 0);
        L11:
            if (r44 != null) goto L13;
            r5.bindNull(24);
        L14:
            Constraints r45 = r6.constraints;
            r5.bindLong(25, WorkTypeConverters.networkTypeToInt(r45.getRequiredNetworkType()));
            r5.bindBlob(26, WorkTypeConverters.fromNetworkRequest$work_runtime_release(r45.getRequiredNetworkRequestCompat$work_runtime_release()));
            r5.bindLong(27, r45.requiresCharging() ? 1 : 0);
            r5.bindLong(28, r45.requiresDeviceIdle() ? 1 : 0);
            r5.bindLong(29, r45.requiresBatteryNotLow() ? 1 : 0);
            r5.bindLong(30, r45.requiresStorageNotLow() ? 1 : 0);
            r5.bindLong(31, r45.getContentTriggerUpdateDelayMillis());
            r5.bindLong(32, r45.getContentTriggerMaxDelayMillis());
            r5.bindBlob(33, WorkTypeConverters.setOfTriggersToByteArray(r45.getContentUriTriggers()));
            r5.bindText(34, r6.id);
            return;
        L13:
            r5.bindLong(24, r44.intValue());
            goto L14
        L9:
            r44 = null;
            goto L11
        L5:
            r5.bindText(23, r42);
            goto L6
        }

        @Override // androidx.room.EntityDeleteOrUpdateAdapter
        public /* bridge */ /* synthetic */ void bind(SQLiteStatement r1, WorkSpec r2) {
            bind2(r1, r2);
        }

        @Override // androidx.room.EntityDeleteOrUpdateAdapter
        public String createQuery() {
            return "UPDATE OR ABORT `WorkSpec` SET `id` = ?,`state` = ?,`worker_class_name` = ?,`input_merger_class_name` = ?,`input` = ?,`output` = ?,`initial_delay` = ?,`interval_duration` = ?,`flex_duration` = ?,`run_attempt_count` = ?,`backoff_policy` = ?,`backoff_delay_duration` = ?,`last_enqueue_time` = ?,`minimum_retention_duration` = ?,`schedule_requested_at` = ?,`run_in_foreground` = ?,`out_of_quota_policy` = ?,`period_count` = ?,`generation` = ?,`next_schedule_time_override` = ?,`next_schedule_time_override_generation` = ?,`stop_reason` = ?,`trace_tag` = ?,`backoff_on_system_interruptions` = ?,`required_network_type` = ?,`required_network_request` = ?,`requires_charging` = ?,`requires_device_idle` = ?,`requires_battery_not_low` = ?,`requires_storage_not_low` = ?,`trigger_content_update_delay` = ?,`trigger_max_content_delay` = ?,`content_uri_triggers` = ? WHERE `id` = ?";
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¨\u0006\u0007"}, d2 = {"Landroidx/work/impl/model/WorkSpecDao_Impl$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "getRequiredConverters", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/reflect/KClass;", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final List<KClass<?>> getRequiredConverters() {
            return CollectionsKt.emptyList();
        }
    }

    public static int $r8$lambda$1X8u9KRHqPGLidWnCmxbXa2nlSk(String r0, SQLiteConnection r1) {
        SQLiteStatement r02 = r1.prepare(r0);
        r02.step();     // Catch: Throwable -> L6
        int r12 = SQLiteConnectionUtil.getTotalChangedRows(r1);     // Catch: Throwable -> L6
        r02.close();
        return r12;
    L6:
        th = move-exception;
        r02.close();
        throw th;
    }

    public static Unit $r8$lambda$1dPPQOz9wdzZqLrC9ZC41PXLr2A(WorkSpecDao_Impl r0, WorkSpec r1, SQLiteConnection r2) {
        r0.__updateAdapterOfWorkSpec.handle(r2, r1);
        return Unit.INSTANCE;
    }

    /* renamed from: $r8$lambda$3IxUHjjrtvVME73wUnQx-Tzg1n4, reason: not valid java name */
    public static int m991$r8$lambda$3IxUHjjrtvVME73wUnQxTzg1n4(String r2, SQLiteConnection r3) {
        SQLiteStatement r22 = r3.prepare(r2);
        int r0 = 0;
        if (r22.step() == false) goto L9;
        r0 = (int) r22.getLong(0);
    L9:
        r22.close();
        return r0;
    L7:
        th = move-exception;
        r22.close();
        throw th;
    }

    public static int $r8$lambda$3YgOuD96vxpWzCW8tqVMWPtOMeA(String r2, WorkInfo$State r3, String r4, SQLiteConnection r5) {
        SQLiteStatement r22 = r5.prepare(r2);
        r22.bindLong(1, WorkTypeConverters.stateToInt(r3));     // Catch: Throwable -> L6
        r22.bindText(2, r4);     // Catch: Throwable -> L6
        r22.step();     // Catch: Throwable -> L6
        int r32 = SQLiteConnectionUtil.getTotalChangedRows(r5);     // Catch: Throwable -> L6
        r22.close();
        return r32;
    L6:
        th = move-exception;
        r22.close();
        throw th;
    }

    public static List $r8$lambda$5E9Q3WxcAEUU2AazRaO6GyVPULI(String r0, String r1, SQLiteConnection r2) {
        SQLiteStatement r02 = r2.prepare(r0);
        r02.bindText(1, r1);     // Catch: Throwable -> L8
        ArrayList r12 = new ArrayList();     // Catch: Throwable -> L8
    L4:
        if (r02.step() == false) goto L10;
        r12.add(r02.getText(0));     // Catch: Throwable -> L8
        goto L4
    L10:
        r02.close();
        return r12;
    L8:
        th = move-exception;
        r02.close();
        throw th;
    }

    public static Unit $r8$lambda$7qvmZJxUUMCwMeWTK5YGPSYgwI0(String r2, int r3, String r4, SQLiteConnection r5) {
        SQLiteStatement r22 = r5.prepare(r2);
        r22.bindLong(1, r3);     // Catch: Throwable -> L6
        r22.bindText(2, r4);     // Catch: Throwable -> L6
        r22.step();     // Catch: Throwable -> L6
        r22.close();
        return Unit.INSTANCE;
    L6:
        th = move-exception;
        r22.close();
        throw th;
    }

    public static List $r8$lambda$7xU4rcngjQY9wTrzM2HFSRzKlTE(String r83, int r84, SQLiteConnection r85) {
        SQLiteStatement r1 = r85.prepare(r83);
        r1.bindLong(1, r84);     // Catch: Throwable -> L27
        int r2 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "id");     // Catch: Throwable -> L27
        int r3 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "state");     // Catch: Throwable -> L27
        int r4 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "worker_class_name");     // Catch: Throwable -> L27
        int r5 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "input_merger_class_name");     // Catch: Throwable -> L27
        int r6 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, PluginsConstants.Settings.TYPE_INPUT);     // Catch: Throwable -> L27
        int r7 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "output");     // Catch: Throwable -> L27
        int r8 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "initial_delay");     // Catch: Throwable -> L27
        int r9 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "interval_duration");     // Catch: Throwable -> L27
        int r10 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "flex_duration");     // Catch: Throwable -> L27
        int r11 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "run_attempt_count");     // Catch: Throwable -> L27
        int r12 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "backoff_policy");     // Catch: Throwable -> L27
        int r13 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "backoff_delay_duration");     // Catch: Throwable -> L27
        int r14 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "last_enqueue_time");     // Catch: Throwable -> L27
        int r15 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "minimum_retention_duration");     // Catch: Throwable -> L27
        int r842 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "schedule_requested_at");     // Catch: Throwable -> L27
        int r852 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "run_in_foreground");     // Catch: Throwable -> L27
        int r16 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "out_of_quota_policy");     // Catch: Throwable -> L27
        int r17 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "period_count");     // Catch: Throwable -> L27
        int r18 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "generation");     // Catch: Throwable -> L27
        int r19 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "next_schedule_time_override");     // Catch: Throwable -> L27
        int r20 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "next_schedule_time_override_generation");     // Catch: Throwable -> L27
        int r21 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "stop_reason");     // Catch: Throwable -> L27
        int r22 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "trace_tag");     // Catch: Throwable -> L27
        int r23 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "backoff_on_system_interruptions");     // Catch: Throwable -> L27
        int r24 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "required_network_type");     // Catch: Throwable -> L27
        int r25 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "required_network_request");     // Catch: Throwable -> L27
        int r26 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "requires_charging");     // Catch: Throwable -> L27
        int r27 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "requires_device_idle");     // Catch: Throwable -> L27
        int r28 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "requires_battery_not_low");     // Catch: Throwable -> L27
        int r29 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "requires_storage_not_low");     // Catch: Throwable -> L27
        int r30 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "trigger_content_update_delay");     // Catch: Throwable -> L27
        int r31 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "trigger_max_content_delay");     // Catch: Throwable -> L27
        int r32 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "content_uri_triggers");     // Catch: Throwable -> L27
        ArrayList r0 = new ArrayList();     // Catch: Throwable -> L27
    L4:
        if (r1.step() == false) goto L51;
        String r35 = r1.getText(r2);     // Catch: Throwable -> L27
        int r33 = r14;
        int r68 = r15;
        WorkInfo$State r36 = WorkTypeConverters.intToState((int) r1.getLong(r3));     // Catch: Throwable -> L27
        String r37 = r1.getText(r4);     // Catch: Throwable -> L27
        String r38 = r1.getText(r5);     // Catch: Throwable -> L27
        byte[] r142 = r1.getBlob(r6);     // Catch: Throwable -> L27
        Data.Companion r152 = Data.Companion;     // Catch: Throwable -> L27
        Data r39 = r152.fromByteArray(r142);     // Catch: Throwable -> L27
        Data r40 = r152.fromByteArray(r1.getBlob(r7));     // Catch: Throwable -> L27
        long r41 = r1.getLong(r8);     // Catch: Throwable -> L27
        long r43 = r1.getLong(r9);     // Catch: Throwable -> L27
        long r45 = r1.getLong(r10);     // Catch: Throwable -> L27
        int r143 = (int) r1.getLong(r11);     // Catch: Throwable -> L27
        int r153 = r2;
        int r69 = r3;
        BackoffPolicy r49 = WorkTypeConverters.intToBackoffPolicy((int) r1.getLong(r12));     // Catch: Throwable -> L27
        long r50 = r1.getLong(r13);     // Catch: Throwable -> L27
        long r52 = r1.getLong(r33);     // Catch: Throwable -> L27
        long r54 = r1.getLong(r68);     // Catch: Throwable -> L27
        int r210 = r842;
        long r56 = r1.getLong(r210);     // Catch: Throwable -> L27
        r842 = r210;
        int r211 = r852;
        int r853 = r4;
        if (((int) r1.getLong(r211)) == 0) goto L10;
        boolean r58 = true;
    L9:
        int r34 = r16;
        int r162 = r5;
        OutOfQuotaPolicy r59 = WorkTypeConverters.intToOutOfQuotaPolicy((int) r1.getLong(r34));     // Catch: Throwable -> L27
        int r42 = r17;
        int r212 = (int) r1.getLong(r42);     // Catch: Throwable -> L27
        int r310 = r18;
        int r44 = (int) r1.getLong(r310);     // Catch: Throwable -> L27
        int r53 = r19;
        long r62 = r1.getLong(r53);     // Catch: Throwable -> L27
        int r213 = r20;
        int r311 = (int) r1.getLong(r213);     // Catch: Throwable -> L27
        r20 = r213;
        int r46 = r21;
        int r214 = (int) r1.getLong(r46);     // Catch: Throwable -> L27
        int r312 = r22;
        Boolean r222 = null;
        if (r1.isNull(r312) == false) goto L15;
        String r66 = null;
    L14:
        int r215 = r23;
        if (r1.isNull(r215) == false) goto L19;
        int r232 = r312;
        r21 = r46;
        Integer r313 = null;
    L20:
        if (r313 != null) goto L22;
    L26:
        Boolean r67 = r222;
        int r314 = r24;
        NetworkType r73 = WorkTypeConverters.intToNetworkType((int) r1.getLong(r314));     // Catch: Throwable -> L27
        int r47 = r25;
        NetworkRequestCompat r72 = WorkTypeConverters.toNetworkRequest$work_runtime_release(r1.getBlob(r47));     // Catch: Throwable -> L27
        int r55 = r26;
        if (((int) r1.getLong(r55)) == 0) goto L33;
        boolean r74 = true;
    L32:
        int r216 = r27;
        if (((int) r1.getLong(r216)) == 0) goto L38;
        boolean r75 = true;
    L37:
        int r315 = r28;
        if (((int) r1.getLong(r315)) == 0) goto L43;
        boolean r76 = true;
    L42:
        r28 = r315;
        int r48 = r29;
        if (((int) r1.getLong(r48)) == 0) goto L48;
        boolean r77 = true;
    L47:
        int r217 = r30;
        int r316 = r31;
        r30 = r217;
        int r218 = r32;
        r0.add(new WorkSpec(r35, r36, r37, r38, r39, r40, r41, r43, r45, new Constraints(r72, r73, r74, r75, r76, r77, r1.getLong(r217), r1.getLong(r316), WorkTypeConverters.byteArrayToSetOfTriggers(r1.getBlob(r218))), r143, r49, r50, r52, r54, r56, r58, r59, r212, r44, r62, r311, r214, r66, r67));     // Catch: Throwable -> L27
        r27 = r216;
        r5 = r162;
        r16 = r34;
        r17 = r42;
        r18 = r310;
        r19 = r53;
        r22 = r232;
        r23 = r215;
        r24 = r314;
        r25 = r47;
        r26 = r55;
        r32 = r218;
        r31 = r316;
        r29 = r48;
        r2 = r153;
        r14 = r33;
        r15 = r68;
        r3 = r69;
        r4 = r853;
        r852 = r211;
        goto L4
    L48:
        r77 = false;
        goto L47
    L43:
        r76 = false;
        goto L42
    L38:
        r75 = false;
        goto L37
    L33:
        r74 = false;
        goto L32
    L22:
        if (r313.intValue() == 0) goto L24;
        boolean r317 = true;
    L25:
        r222 = Boolean.valueOf(r317);     // Catch: Throwable -> L27
        goto L26
    L24:
        r317 = false;
        goto L25
    L19:
        r232 = r312;
        r21 = r46;
        r313 = Integer.valueOf((int) r1.getLong(r215));     // Catch: Throwable -> L27
        goto L20
    L15:
        r66 = r1.getText(r312);     // Catch: Throwable -> L27
        goto L14
    L10:
        r58 = false;
        goto L9
    L51:
        r1.close();
        return r0;
    L27:
        th = move-exception;
        r1.close();
        throw th;
    }

    public static int $r8$lambda$9icPC8zW71nSfq9GS1Jqyvbbd9I(String r1, String r2, SQLiteConnection r3) {
        SQLiteStatement r12 = r3.prepare(r1);
        r12.bindText(1, r2);     // Catch: Throwable -> L6
        r12.step();     // Catch: Throwable -> L6
        int r22 = SQLiteConnectionUtil.getTotalChangedRows(r3);     // Catch: Throwable -> L6
        r12.close();
        return r22;
    L6:
        th = move-exception;
        r12.close();
        throw th;
    }

    public static List $r8$lambda$Bmk0Wb4PqGceYkMRz22vPjZLj7s(String r83, SQLiteConnection r84) {
        SQLiteStatement r1 = r84.prepare(r83);
        int r0 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "id");     // Catch: Throwable -> L27
        int r2 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "state");     // Catch: Throwable -> L27
        int r3 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "worker_class_name");     // Catch: Throwable -> L27
        int r4 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "input_merger_class_name");     // Catch: Throwable -> L27
        int r5 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, PluginsConstants.Settings.TYPE_INPUT);     // Catch: Throwable -> L27
        int r6 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "output");     // Catch: Throwable -> L27
        int r7 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "initial_delay");     // Catch: Throwable -> L27
        int r8 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "interval_duration");     // Catch: Throwable -> L27
        int r9 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "flex_duration");     // Catch: Throwable -> L27
        int r10 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "run_attempt_count");     // Catch: Throwable -> L27
        int r11 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "backoff_policy");     // Catch: Throwable -> L27
        int r12 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "backoff_delay_duration");     // Catch: Throwable -> L27
        int r13 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "last_enqueue_time");     // Catch: Throwable -> L27
        int r14 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "minimum_retention_duration");     // Catch: Throwable -> L27
        int r832 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "schedule_requested_at");     // Catch: Throwable -> L27
        int r842 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "run_in_foreground");     // Catch: Throwable -> L27
        int r16 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "out_of_quota_policy");     // Catch: Throwable -> L27
        int r17 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "period_count");     // Catch: Throwable -> L27
        int r18 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "generation");     // Catch: Throwable -> L27
        int r19 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "next_schedule_time_override");     // Catch: Throwable -> L27
        int r20 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "next_schedule_time_override_generation");     // Catch: Throwable -> L27
        int r21 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "stop_reason");     // Catch: Throwable -> L27
        int r22 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "trace_tag");     // Catch: Throwable -> L27
        int r23 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "backoff_on_system_interruptions");     // Catch: Throwable -> L27
        int r24 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "required_network_type");     // Catch: Throwable -> L27
        int r25 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "required_network_request");     // Catch: Throwable -> L27
        int r26 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "requires_charging");     // Catch: Throwable -> L27
        int r27 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "requires_device_idle");     // Catch: Throwable -> L27
        int r28 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "requires_battery_not_low");     // Catch: Throwable -> L27
        int r29 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "requires_storage_not_low");     // Catch: Throwable -> L27
        int r30 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "trigger_content_update_delay");     // Catch: Throwable -> L27
        int r31 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "trigger_max_content_delay");     // Catch: Throwable -> L27
        int r32 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "content_uri_triggers");     // Catch: Throwable -> L27
        ArrayList r15 = new ArrayList();     // Catch: Throwable -> L27
    L4:
        if (r1.step() == false) goto L51;
        String r35 = r1.getText(r0);     // Catch: Throwable -> L27
        int r33 = r14;
        ArrayList r68 = r15;
        WorkInfo$State r36 = WorkTypeConverters.intToState((int) r1.getLong(r2));     // Catch: Throwable -> L27
        String r37 = r1.getText(r3);     // Catch: Throwable -> L27
        String r38 = r1.getText(r4);     // Catch: Throwable -> L27
        byte[] r142 = r1.getBlob(r5);     // Catch: Throwable -> L27
        Data.Companion r152 = Data.Companion;     // Catch: Throwable -> L27
        Data r39 = r152.fromByteArray(r142);     // Catch: Throwable -> L27
        Data r40 = r152.fromByteArray(r1.getBlob(r6));     // Catch: Throwable -> L27
        long r41 = r1.getLong(r7);     // Catch: Throwable -> L27
        long r43 = r1.getLong(r8);     // Catch: Throwable -> L27
        long r45 = r1.getLong(r9);     // Catch: Throwable -> L27
        int r143 = (int) r1.getLong(r10);     // Catch: Throwable -> L27
        int r153 = r2;
        int r69 = r3;
        BackoffPolicy r49 = WorkTypeConverters.intToBackoffPolicy((int) r1.getLong(r11));     // Catch: Throwable -> L27
        long r50 = r1.getLong(r12);     // Catch: Throwable -> L27
        long r52 = r1.getLong(r13);     // Catch: Throwable -> L27
        long r54 = r1.getLong(r33);     // Catch: Throwable -> L27
        int r34 = r832;
        long r56 = r1.getLong(r34);     // Catch: Throwable -> L27
        int r833 = r0;
        int r02 = r842;
        if (((int) r1.getLong(r02)) == 0) goto L10;
        boolean r58 = true;
    L9:
        int r210 = r16;
        int r162 = r4;
        OutOfQuotaPolicy r59 = WorkTypeConverters.intToOutOfQuotaPolicy((int) r1.getLong(r210));     // Catch: Throwable -> L27
        int r310 = r17;
        int r172 = r5;
        int r42 = (int) r1.getLong(r310);     // Catch: Throwable -> L27
        int r53 = r18;
        int r211 = (int) r1.getLong(r53);     // Catch: Throwable -> L27
        int r311 = r19;
        long r62 = r1.getLong(r311);     // Catch: Throwable -> L27
        int r03 = r20;
        int r212 = (int) r1.getLong(r03);     // Catch: Throwable -> L27
        int r312 = r21;
        int r44 = (int) r1.getLong(r312);     // Catch: Throwable -> L27
        int r55 = r22;
        Boolean r48 = null;
        if (r1.isNull(r55) == false) goto L15;
        String r66 = null;
    L14:
        int r04 = r23;
        if (r1.isNull(r04) == false) goto L19;
        int r64 = r212;
        int r232 = r312;
        Integer r213 = null;
    L20:
        if (r213 != null) goto L22;
    L26:
        int r214 = r24;
        Boolean r67 = r48;
        NetworkType r73 = WorkTypeConverters.intToNetworkType((int) r1.getLong(r214));     // Catch: Throwable -> L27
        int r313 = r25;
        NetworkRequestCompat r72 = WorkTypeConverters.toNetworkRequest$work_runtime_release(r1.getBlob(r313));     // Catch: Throwable -> L27
        r24 = r214;
        r25 = r313;
        int r46 = r26;
        if (((int) r1.getLong(r46)) == 0) goto L33;
        boolean r74 = true;
    L32:
        r26 = r46;
        int r215 = r27;
        if (((int) r1.getLong(r215)) == 0) goto L38;
        boolean r75 = true;
    L37:
        int r314 = r28;
        if (((int) r1.getLong(r314)) == 0) goto L43;
        boolean r76 = true;
    L42:
        r28 = r314;
        int r47 = r29;
        if (((int) r1.getLong(r47)) == 0) goto L48;
        boolean r77 = true;
    L47:
        int r216 = r30;
        int r315 = r31;
        int r05 = r32;
        r32 = r05;
        r68.add(new WorkSpec(r35, r36, r37, r38, r39, r40, r41, r43, r45, new Constraints(r72, r73, r74, r75, r76, r77, r1.getLong(r216), r1.getLong(r315), WorkTypeConverters.byteArrayToSetOfTriggers(r1.getBlob(r05))), r143, r49, r50, r52, r54, r56, r58, r59, r42, r211, r62, r64, r44, r66, r67));     // Catch: Throwable -> L27
        r29 = r47;
        r4 = r162;
        r16 = r210;
        r18 = r53;
        r21 = r232;
        r23 = r04;
        r30 = r216;
        r31 = r315;
        r2 = r153;
        r14 = r33;
        r3 = r69;
        r15 = r68;
        r0 = r833;
        r832 = r34;
        r842 = r02;
        r19 = r311;
        r20 = r03;
        r22 = r55;
        r27 = r215;
        r5 = r172;
        r17 = r310;
        goto L4
    L48:
        r77 = false;
        goto L47
    L43:
        r76 = false;
        goto L42
    L38:
        r75 = false;
        goto L37
    L33:
        r74 = false;
        goto L32
    L22:
        if (r213.intValue() == 0) goto L24;
        boolean r217 = true;
    L25:
        r48 = Boolean.valueOf(r217);     // Catch: Throwable -> L27
        goto L26
    L24:
        r217 = false;
        goto L25
    L19:
        r64 = r212;
        r232 = r312;
        r213 = Integer.valueOf((int) r1.getLong(r04));     // Catch: Throwable -> L27
        goto L20
    L15:
        r66 = r1.getText(r55);     // Catch: Throwable -> L27
        goto L14
    L10:
        r58 = false;
        goto L9
    L51:
        ArrayList r06 = r15;
        r1.close();
        return r06;
    L27:
        th = move-exception;
        r1.close();
        throw th;
    }

    public static Unit $r8$lambda$D_j3iVlrhs4qIubkJxP9Pf64v1w(String r0, String r1, SQLiteConnection r2) {
        SQLiteStatement r02 = r2.prepare(r0);
        r02.bindText(1, r1);     // Catch: Throwable -> L6
        r02.step();     // Catch: Throwable -> L6
        r02.close();
        return Unit.INSTANCE;
    L6:
        th = move-exception;
        r02.close();
        throw th;
    }

    /* renamed from: $r8$lambda$KFb3nzXfzaUaq1v3ooSxnQ-hpBs, reason: not valid java name */
    public static int m992$r8$lambda$KFb3nzXfzaUaq1v3ooSxnQhpBs(String r1, String r2, SQLiteConnection r3) {
        SQLiteStatement r12 = r3.prepare(r1);
        r12.bindText(1, r2);     // Catch: Throwable -> L6
        r12.step();     // Catch: Throwable -> L6
        int r22 = SQLiteConnectionUtil.getTotalChangedRows(r3);     // Catch: Throwable -> L6
        r12.close();
        return r22;
    L6:
        th = move-exception;
        r12.close();
        throw th;
    }

    public static int $r8$lambda$KUcGUYGmOUPT2odFOUwBnvgk5Q0(String r1, String r2, SQLiteConnection r3) {
        SQLiteStatement r12 = r3.prepare(r1);
        r12.bindText(1, r2);     // Catch: Throwable -> L6
        r12.step();     // Catch: Throwable -> L6
        int r22 = SQLiteConnectionUtil.getTotalChangedRows(r3);     // Catch: Throwable -> L6
        r12.close();
        return r22;
    L6:
        th = move-exception;
        r12.close();
        throw th;
    }

    public static Unit $r8$lambda$TcStHcTAZZVbzvOHHrrDz_mxpiM(WorkSpecDao_Impl r0, WorkSpec r1, SQLiteConnection r2) {
        r0.__insertAdapterOfWorkSpec.insert(r2, r1);
        return Unit.INSTANCE;
    }

    public static WorkInfo$State $r8$lambda$V8dm0CzATZY_g4wbyyGkVh7xf2c(String r2, String r3, SQLiteConnection r4) {
        SQLiteStatement r22 = r4.prepare(r2);
        r22.bindText(1, r3);     // Catch: Throwable -> L13
        WorkInfo$State r42 = null;
        if (r22.step() == true) goto L6;
    L15:
        r22.close();
        return r42;
    L6:
        if (r22.isNull(0) == false) goto L8;
        Integer r32 = null;
    L9:
        if (r32 == null) goto L15;
        r42 = WorkTypeConverters.intToState(r32.intValue());     // Catch: Throwable -> L13
        goto L15
    L8:
        r32 = Integer.valueOf((int) r22.getLong(0));     // Catch: Throwable -> L13
    L13:
        th = move-exception;
        r22.close();
        throw th;
    }

    /* renamed from: $r8$lambda$WmdU6OWFKOewjux-UcOkDAId0fg, reason: not valid java name */
    public static List m993$r8$lambda$WmdU6OWFKOewjuxUcOkDAId0fg(String r83, SQLiteConnection r84) {
        SQLiteStatement r1 = r84.prepare(r83);
        int r0 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "id");     // Catch: Throwable -> L27
        int r2 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "state");     // Catch: Throwable -> L27
        int r3 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "worker_class_name");     // Catch: Throwable -> L27
        int r4 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "input_merger_class_name");     // Catch: Throwable -> L27
        int r5 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, PluginsConstants.Settings.TYPE_INPUT);     // Catch: Throwable -> L27
        int r6 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "output");     // Catch: Throwable -> L27
        int r7 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "initial_delay");     // Catch: Throwable -> L27
        int r8 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "interval_duration");     // Catch: Throwable -> L27
        int r9 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "flex_duration");     // Catch: Throwable -> L27
        int r10 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "run_attempt_count");     // Catch: Throwable -> L27
        int r11 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "backoff_policy");     // Catch: Throwable -> L27
        int r12 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "backoff_delay_duration");     // Catch: Throwable -> L27
        int r13 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "last_enqueue_time");     // Catch: Throwable -> L27
        int r14 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "minimum_retention_duration");     // Catch: Throwable -> L27
        int r832 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "schedule_requested_at");     // Catch: Throwable -> L27
        int r842 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "run_in_foreground");     // Catch: Throwable -> L27
        int r16 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "out_of_quota_policy");     // Catch: Throwable -> L27
        int r17 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "period_count");     // Catch: Throwable -> L27
        int r18 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "generation");     // Catch: Throwable -> L27
        int r19 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "next_schedule_time_override");     // Catch: Throwable -> L27
        int r20 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "next_schedule_time_override_generation");     // Catch: Throwable -> L27
        int r21 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "stop_reason");     // Catch: Throwable -> L27
        int r22 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "trace_tag");     // Catch: Throwable -> L27
        int r23 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "backoff_on_system_interruptions");     // Catch: Throwable -> L27
        int r24 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "required_network_type");     // Catch: Throwable -> L27
        int r25 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "required_network_request");     // Catch: Throwable -> L27
        int r26 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "requires_charging");     // Catch: Throwable -> L27
        int r27 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "requires_device_idle");     // Catch: Throwable -> L27
        int r28 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "requires_battery_not_low");     // Catch: Throwable -> L27
        int r29 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "requires_storage_not_low");     // Catch: Throwable -> L27
        int r30 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "trigger_content_update_delay");     // Catch: Throwable -> L27
        int r31 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "trigger_max_content_delay");     // Catch: Throwable -> L27
        int r32 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "content_uri_triggers");     // Catch: Throwable -> L27
        ArrayList r15 = new ArrayList();     // Catch: Throwable -> L27
    L4:
        if (r1.step() == false) goto L51;
        String r35 = r1.getText(r0);     // Catch: Throwable -> L27
        int r33 = r14;
        ArrayList r68 = r15;
        WorkInfo$State r36 = WorkTypeConverters.intToState((int) r1.getLong(r2));     // Catch: Throwable -> L27
        String r37 = r1.getText(r3);     // Catch: Throwable -> L27
        String r38 = r1.getText(r4);     // Catch: Throwable -> L27
        byte[] r142 = r1.getBlob(r5);     // Catch: Throwable -> L27
        Data.Companion r152 = Data.Companion;     // Catch: Throwable -> L27
        Data r39 = r152.fromByteArray(r142);     // Catch: Throwable -> L27
        Data r40 = r152.fromByteArray(r1.getBlob(r6));     // Catch: Throwable -> L27
        long r41 = r1.getLong(r7);     // Catch: Throwable -> L27
        long r43 = r1.getLong(r8);     // Catch: Throwable -> L27
        long r45 = r1.getLong(r9);     // Catch: Throwable -> L27
        int r143 = (int) r1.getLong(r10);     // Catch: Throwable -> L27
        int r153 = r2;
        int r69 = r3;
        BackoffPolicy r49 = WorkTypeConverters.intToBackoffPolicy((int) r1.getLong(r11));     // Catch: Throwable -> L27
        long r50 = r1.getLong(r12);     // Catch: Throwable -> L27
        long r52 = r1.getLong(r13);     // Catch: Throwable -> L27
        long r54 = r1.getLong(r33);     // Catch: Throwable -> L27
        int r34 = r832;
        long r56 = r1.getLong(r34);     // Catch: Throwable -> L27
        int r833 = r0;
        int r02 = r842;
        if (((int) r1.getLong(r02)) == 0) goto L10;
        boolean r58 = true;
    L9:
        int r210 = r16;
        int r162 = r4;
        OutOfQuotaPolicy r59 = WorkTypeConverters.intToOutOfQuotaPolicy((int) r1.getLong(r210));     // Catch: Throwable -> L27
        int r310 = r17;
        int r172 = r5;
        int r42 = (int) r1.getLong(r310);     // Catch: Throwable -> L27
        int r53 = r18;
        int r211 = (int) r1.getLong(r53);     // Catch: Throwable -> L27
        int r311 = r19;
        long r62 = r1.getLong(r311);     // Catch: Throwable -> L27
        int r03 = r20;
        int r212 = (int) r1.getLong(r03);     // Catch: Throwable -> L27
        int r312 = r21;
        int r44 = (int) r1.getLong(r312);     // Catch: Throwable -> L27
        int r55 = r22;
        Boolean r48 = null;
        if (r1.isNull(r55) == false) goto L15;
        String r66 = null;
    L14:
        int r04 = r23;
        if (r1.isNull(r04) == false) goto L19;
        int r64 = r212;
        int r232 = r312;
        Integer r213 = null;
    L20:
        if (r213 != null) goto L22;
    L26:
        int r214 = r24;
        Boolean r67 = r48;
        NetworkType r73 = WorkTypeConverters.intToNetworkType((int) r1.getLong(r214));     // Catch: Throwable -> L27
        int r313 = r25;
        NetworkRequestCompat r72 = WorkTypeConverters.toNetworkRequest$work_runtime_release(r1.getBlob(r313));     // Catch: Throwable -> L27
        r24 = r214;
        r25 = r313;
        int r46 = r26;
        if (((int) r1.getLong(r46)) == 0) goto L33;
        boolean r74 = true;
    L32:
        r26 = r46;
        int r215 = r27;
        if (((int) r1.getLong(r215)) == 0) goto L38;
        boolean r75 = true;
    L37:
        int r314 = r28;
        if (((int) r1.getLong(r314)) == 0) goto L43;
        boolean r76 = true;
    L42:
        r28 = r314;
        int r47 = r29;
        if (((int) r1.getLong(r47)) == 0) goto L48;
        boolean r77 = true;
    L47:
        int r216 = r30;
        int r315 = r31;
        int r05 = r32;
        r32 = r05;
        r68.add(new WorkSpec(r35, r36, r37, r38, r39, r40, r41, r43, r45, new Constraints(r72, r73, r74, r75, r76, r77, r1.getLong(r216), r1.getLong(r315), WorkTypeConverters.byteArrayToSetOfTriggers(r1.getBlob(r05))), r143, r49, r50, r52, r54, r56, r58, r59, r42, r211, r62, r64, r44, r66, r67));     // Catch: Throwable -> L27
        r29 = r47;
        r4 = r162;
        r16 = r210;
        r18 = r53;
        r21 = r232;
        r23 = r04;
        r30 = r216;
        r31 = r315;
        r2 = r153;
        r14 = r33;
        r3 = r69;
        r15 = r68;
        r0 = r833;
        r832 = r34;
        r842 = r02;
        r19 = r311;
        r20 = r03;
        r22 = r55;
        r27 = r215;
        r5 = r172;
        r17 = r310;
        goto L4
    L48:
        r77 = false;
        goto L47
    L43:
        r76 = false;
        goto L42
    L38:
        r75 = false;
        goto L37
    L33:
        r74 = false;
        goto L32
    L22:
        if (r213.intValue() == 0) goto L24;
        boolean r217 = true;
    L25:
        r48 = Boolean.valueOf(r217);     // Catch: Throwable -> L27
        goto L26
    L24:
        r217 = false;
        goto L25
    L19:
        r64 = r212;
        r232 = r312;
        r213 = Integer.valueOf((int) r1.getLong(r04));     // Catch: Throwable -> L27
        goto L20
    L15:
        r66 = r1.getText(r55);     // Catch: Throwable -> L27
        goto L14
    L10:
        r58 = false;
        goto L9
    L51:
        ArrayList r06 = r15;
        r1.close();
        return r06;
    L27:
        th = move-exception;
        r1.close();
        throw th;
    }

    /* renamed from: $r8$lambda$_VUlGlVo31AuD-ORktivu9yoNtM, reason: not valid java name */
    public static Unit m994$r8$lambda$_VUlGlVo31AuDORktivu9yoNtM(String r0, long r1, String r3, SQLiteConnection r4) {
        SQLiteStatement r02 = r4.prepare(r0);
        r02.bindLong(1, r1);     // Catch: Throwable -> L6
        r02.bindText(2, r3);     // Catch: Throwable -> L6
        r02.step();     // Catch: Throwable -> L6
        r02.close();
        return Unit.INSTANCE;
    L6:
        th = move-exception;
        r02.close();
        throw th;
    }

    public static int $r8$lambda$c3IlkoMXTk79d9ht9bnL8LIKQs0(String r1, long r2, String r4, SQLiteConnection r5) {
        SQLiteStatement r12 = r5.prepare(r1);
        r12.bindLong(1, r2);     // Catch: Throwable -> L6
        r12.bindText(2, r4);     // Catch: Throwable -> L6
        r12.step();     // Catch: Throwable -> L6
        int r22 = SQLiteConnectionUtil.getTotalChangedRows(r5);     // Catch: Throwable -> L6
        r12.close();
        return r22;
    L6:
        th = move-exception;
        r12.close();
        throw th;
    }

    public static List $r8$lambda$chvsbAAJjg4lV4x5vJMY03fl4pI(String r83, SQLiteConnection r84) {
        SQLiteStatement r1 = r84.prepare(r83);
        int r0 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "id");     // Catch: Throwable -> L27
        int r2 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "state");     // Catch: Throwable -> L27
        int r3 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "worker_class_name");     // Catch: Throwable -> L27
        int r4 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "input_merger_class_name");     // Catch: Throwable -> L27
        int r5 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, PluginsConstants.Settings.TYPE_INPUT);     // Catch: Throwable -> L27
        int r6 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "output");     // Catch: Throwable -> L27
        int r7 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "initial_delay");     // Catch: Throwable -> L27
        int r8 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "interval_duration");     // Catch: Throwable -> L27
        int r9 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "flex_duration");     // Catch: Throwable -> L27
        int r10 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "run_attempt_count");     // Catch: Throwable -> L27
        int r11 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "backoff_policy");     // Catch: Throwable -> L27
        int r12 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "backoff_delay_duration");     // Catch: Throwable -> L27
        int r13 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "last_enqueue_time");     // Catch: Throwable -> L27
        int r14 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "minimum_retention_duration");     // Catch: Throwable -> L27
        int r832 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "schedule_requested_at");     // Catch: Throwable -> L27
        int r842 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "run_in_foreground");     // Catch: Throwable -> L27
        int r16 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "out_of_quota_policy");     // Catch: Throwable -> L27
        int r17 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "period_count");     // Catch: Throwable -> L27
        int r18 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "generation");     // Catch: Throwable -> L27
        int r19 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "next_schedule_time_override");     // Catch: Throwable -> L27
        int r20 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "next_schedule_time_override_generation");     // Catch: Throwable -> L27
        int r21 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "stop_reason");     // Catch: Throwable -> L27
        int r22 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "trace_tag");     // Catch: Throwable -> L27
        int r23 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "backoff_on_system_interruptions");     // Catch: Throwable -> L27
        int r24 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "required_network_type");     // Catch: Throwable -> L27
        int r25 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "required_network_request");     // Catch: Throwable -> L27
        int r26 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "requires_charging");     // Catch: Throwable -> L27
        int r27 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "requires_device_idle");     // Catch: Throwable -> L27
        int r28 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "requires_battery_not_low");     // Catch: Throwable -> L27
        int r29 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "requires_storage_not_low");     // Catch: Throwable -> L27
        int r30 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "trigger_content_update_delay");     // Catch: Throwable -> L27
        int r31 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "trigger_max_content_delay");     // Catch: Throwable -> L27
        int r32 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "content_uri_triggers");     // Catch: Throwable -> L27
        ArrayList r15 = new ArrayList();     // Catch: Throwable -> L27
    L4:
        if (r1.step() == false) goto L51;
        String r35 = r1.getText(r0);     // Catch: Throwable -> L27
        int r33 = r14;
        ArrayList r68 = r15;
        WorkInfo$State r36 = WorkTypeConverters.intToState((int) r1.getLong(r2));     // Catch: Throwable -> L27
        String r37 = r1.getText(r3);     // Catch: Throwable -> L27
        String r38 = r1.getText(r4);     // Catch: Throwable -> L27
        byte[] r142 = r1.getBlob(r5);     // Catch: Throwable -> L27
        Data.Companion r152 = Data.Companion;     // Catch: Throwable -> L27
        Data r39 = r152.fromByteArray(r142);     // Catch: Throwable -> L27
        Data r40 = r152.fromByteArray(r1.getBlob(r6));     // Catch: Throwable -> L27
        long r41 = r1.getLong(r7);     // Catch: Throwable -> L27
        long r43 = r1.getLong(r8);     // Catch: Throwable -> L27
        long r45 = r1.getLong(r9);     // Catch: Throwable -> L27
        int r143 = (int) r1.getLong(r10);     // Catch: Throwable -> L27
        int r153 = r2;
        int r69 = r3;
        BackoffPolicy r49 = WorkTypeConverters.intToBackoffPolicy((int) r1.getLong(r11));     // Catch: Throwable -> L27
        long r50 = r1.getLong(r12);     // Catch: Throwable -> L27
        long r52 = r1.getLong(r13);     // Catch: Throwable -> L27
        long r54 = r1.getLong(r33);     // Catch: Throwable -> L27
        int r34 = r832;
        long r56 = r1.getLong(r34);     // Catch: Throwable -> L27
        int r833 = r0;
        int r02 = r842;
        if (((int) r1.getLong(r02)) == 0) goto L10;
        boolean r58 = true;
    L9:
        int r210 = r16;
        int r162 = r4;
        OutOfQuotaPolicy r59 = WorkTypeConverters.intToOutOfQuotaPolicy((int) r1.getLong(r210));     // Catch: Throwable -> L27
        int r310 = r17;
        int r172 = r5;
        int r42 = (int) r1.getLong(r310);     // Catch: Throwable -> L27
        int r53 = r18;
        int r211 = (int) r1.getLong(r53);     // Catch: Throwable -> L27
        int r311 = r19;
        long r62 = r1.getLong(r311);     // Catch: Throwable -> L27
        int r03 = r20;
        int r212 = (int) r1.getLong(r03);     // Catch: Throwable -> L27
        int r312 = r21;
        int r44 = (int) r1.getLong(r312);     // Catch: Throwable -> L27
        int r55 = r22;
        Boolean r48 = null;
        if (r1.isNull(r55) == false) goto L15;
        String r66 = null;
    L14:
        int r04 = r23;
        if (r1.isNull(r04) == false) goto L19;
        int r64 = r212;
        int r232 = r312;
        Integer r213 = null;
    L20:
        if (r213 != null) goto L22;
    L26:
        int r214 = r24;
        Boolean r67 = r48;
        NetworkType r73 = WorkTypeConverters.intToNetworkType((int) r1.getLong(r214));     // Catch: Throwable -> L27
        int r313 = r25;
        NetworkRequestCompat r72 = WorkTypeConverters.toNetworkRequest$work_runtime_release(r1.getBlob(r313));     // Catch: Throwable -> L27
        r24 = r214;
        r25 = r313;
        int r46 = r26;
        if (((int) r1.getLong(r46)) == 0) goto L33;
        boolean r74 = true;
    L32:
        r26 = r46;
        int r215 = r27;
        if (((int) r1.getLong(r215)) == 0) goto L38;
        boolean r75 = true;
    L37:
        int r314 = r28;
        if (((int) r1.getLong(r314)) == 0) goto L43;
        boolean r76 = true;
    L42:
        r28 = r314;
        int r47 = r29;
        if (((int) r1.getLong(r47)) == 0) goto L48;
        boolean r77 = true;
    L47:
        int r216 = r30;
        int r315 = r31;
        int r05 = r32;
        r32 = r05;
        r68.add(new WorkSpec(r35, r36, r37, r38, r39, r40, r41, r43, r45, new Constraints(r72, r73, r74, r75, r76, r77, r1.getLong(r216), r1.getLong(r315), WorkTypeConverters.byteArrayToSetOfTriggers(r1.getBlob(r05))), r143, r49, r50, r52, r54, r56, r58, r59, r42, r211, r62, r64, r44, r66, r67));     // Catch: Throwable -> L27
        r29 = r47;
        r4 = r162;
        r16 = r210;
        r18 = r53;
        r21 = r232;
        r23 = r04;
        r30 = r216;
        r31 = r315;
        r2 = r153;
        r14 = r33;
        r3 = r69;
        r15 = r68;
        r0 = r833;
        r832 = r34;
        r842 = r02;
        r19 = r311;
        r20 = r03;
        r22 = r55;
        r27 = r215;
        r5 = r172;
        r17 = r310;
        goto L4
    L48:
        r77 = false;
        goto L47
    L43:
        r76 = false;
        goto L42
    L38:
        r75 = false;
        goto L37
    L33:
        r74 = false;
        goto L32
    L22:
        if (r213.intValue() == 0) goto L24;
        boolean r217 = true;
    L25:
        r48 = Boolean.valueOf(r217);     // Catch: Throwable -> L27
        goto L26
    L24:
        r217 = false;
        goto L25
    L19:
        r64 = r212;
        r232 = r312;
        r213 = Integer.valueOf((int) r1.getLong(r04));     // Catch: Throwable -> L27
        goto L20
    L15:
        r66 = r1.getText(r55);     // Catch: Throwable -> L27
        goto L14
    L10:
        r58 = false;
        goto L9
    L51:
        ArrayList r06 = r15;
        r1.close();
        return r06;
    L27:
        th = move-exception;
        r1.close();
        throw th;
    }

    public static Unit $r8$lambda$dcBKbVNLGBPeNR9pZ6Kmf5Rcnk4(String r0, String r1, int r2, SQLiteConnection r3) {
        SQLiteStatement r02 = r3.prepare(r0);
        r02.bindText(1, r1);     // Catch: Throwable -> L6
        r02.bindLong(2, r2);     // Catch: Throwable -> L6
        r02.step();     // Catch: Throwable -> L6
        r02.close();
        return Unit.INSTANCE;
    L6:
        th = move-exception;
        r02.close();
        throw th;
    }

    public static List $r8$lambda$fnITpoR1PO1p7_VkaDg0ZRzSZ9U(String r83, int r84, SQLiteConnection r85) {
        SQLiteStatement r1 = r85.prepare(r83);
        r1.bindLong(1, r84);     // Catch: Throwable -> L27
        int r2 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "id");     // Catch: Throwable -> L27
        int r3 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "state");     // Catch: Throwable -> L27
        int r4 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "worker_class_name");     // Catch: Throwable -> L27
        int r5 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "input_merger_class_name");     // Catch: Throwable -> L27
        int r6 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, PluginsConstants.Settings.TYPE_INPUT);     // Catch: Throwable -> L27
        int r7 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "output");     // Catch: Throwable -> L27
        int r8 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "initial_delay");     // Catch: Throwable -> L27
        int r9 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "interval_duration");     // Catch: Throwable -> L27
        int r10 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "flex_duration");     // Catch: Throwable -> L27
        int r11 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "run_attempt_count");     // Catch: Throwable -> L27
        int r12 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "backoff_policy");     // Catch: Throwable -> L27
        int r13 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "backoff_delay_duration");     // Catch: Throwable -> L27
        int r14 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "last_enqueue_time");     // Catch: Throwable -> L27
        int r15 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "minimum_retention_duration");     // Catch: Throwable -> L27
        int r842 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "schedule_requested_at");     // Catch: Throwable -> L27
        int r852 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "run_in_foreground");     // Catch: Throwable -> L27
        int r16 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "out_of_quota_policy");     // Catch: Throwable -> L27
        int r17 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "period_count");     // Catch: Throwable -> L27
        int r18 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "generation");     // Catch: Throwable -> L27
        int r19 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "next_schedule_time_override");     // Catch: Throwable -> L27
        int r20 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "next_schedule_time_override_generation");     // Catch: Throwable -> L27
        int r21 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "stop_reason");     // Catch: Throwable -> L27
        int r22 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "trace_tag");     // Catch: Throwable -> L27
        int r23 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "backoff_on_system_interruptions");     // Catch: Throwable -> L27
        int r24 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "required_network_type");     // Catch: Throwable -> L27
        int r25 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "required_network_request");     // Catch: Throwable -> L27
        int r26 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "requires_charging");     // Catch: Throwable -> L27
        int r27 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "requires_device_idle");     // Catch: Throwable -> L27
        int r28 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "requires_battery_not_low");     // Catch: Throwable -> L27
        int r29 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "requires_storage_not_low");     // Catch: Throwable -> L27
        int r30 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "trigger_content_update_delay");     // Catch: Throwable -> L27
        int r31 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "trigger_max_content_delay");     // Catch: Throwable -> L27
        int r32 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "content_uri_triggers");     // Catch: Throwable -> L27
        ArrayList r0 = new ArrayList();     // Catch: Throwable -> L27
    L4:
        if (r1.step() == false) goto L51;
        String r35 = r1.getText(r2);     // Catch: Throwable -> L27
        int r33 = r14;
        int r68 = r15;
        WorkInfo$State r36 = WorkTypeConverters.intToState((int) r1.getLong(r3));     // Catch: Throwable -> L27
        String r37 = r1.getText(r4);     // Catch: Throwable -> L27
        String r38 = r1.getText(r5);     // Catch: Throwable -> L27
        byte[] r142 = r1.getBlob(r6);     // Catch: Throwable -> L27
        Data.Companion r152 = Data.Companion;     // Catch: Throwable -> L27
        Data r39 = r152.fromByteArray(r142);     // Catch: Throwable -> L27
        Data r40 = r152.fromByteArray(r1.getBlob(r7));     // Catch: Throwable -> L27
        long r41 = r1.getLong(r8);     // Catch: Throwable -> L27
        long r43 = r1.getLong(r9);     // Catch: Throwable -> L27
        long r45 = r1.getLong(r10);     // Catch: Throwable -> L27
        int r143 = (int) r1.getLong(r11);     // Catch: Throwable -> L27
        int r153 = r2;
        int r69 = r3;
        BackoffPolicy r49 = WorkTypeConverters.intToBackoffPolicy((int) r1.getLong(r12));     // Catch: Throwable -> L27
        long r50 = r1.getLong(r13);     // Catch: Throwable -> L27
        long r52 = r1.getLong(r33);     // Catch: Throwable -> L27
        long r54 = r1.getLong(r68);     // Catch: Throwable -> L27
        int r210 = r842;
        long r56 = r1.getLong(r210);     // Catch: Throwable -> L27
        r842 = r210;
        int r211 = r852;
        int r853 = r4;
        if (((int) r1.getLong(r211)) == 0) goto L10;
        boolean r58 = true;
    L9:
        int r34 = r16;
        int r162 = r5;
        OutOfQuotaPolicy r59 = WorkTypeConverters.intToOutOfQuotaPolicy((int) r1.getLong(r34));     // Catch: Throwable -> L27
        int r42 = r17;
        int r212 = (int) r1.getLong(r42);     // Catch: Throwable -> L27
        int r310 = r18;
        int r44 = (int) r1.getLong(r310);     // Catch: Throwable -> L27
        int r53 = r19;
        long r62 = r1.getLong(r53);     // Catch: Throwable -> L27
        int r213 = r20;
        int r311 = (int) r1.getLong(r213);     // Catch: Throwable -> L27
        r20 = r213;
        int r46 = r21;
        int r214 = (int) r1.getLong(r46);     // Catch: Throwable -> L27
        int r312 = r22;
        Boolean r222 = null;
        if (r1.isNull(r312) == false) goto L15;
        String r66 = null;
    L14:
        int r215 = r23;
        if (r1.isNull(r215) == false) goto L19;
        int r232 = r312;
        r21 = r46;
        Integer r313 = null;
    L20:
        if (r313 != null) goto L22;
    L26:
        Boolean r67 = r222;
        int r314 = r24;
        NetworkType r73 = WorkTypeConverters.intToNetworkType((int) r1.getLong(r314));     // Catch: Throwable -> L27
        int r47 = r25;
        NetworkRequestCompat r72 = WorkTypeConverters.toNetworkRequest$work_runtime_release(r1.getBlob(r47));     // Catch: Throwable -> L27
        int r55 = r26;
        if (((int) r1.getLong(r55)) == 0) goto L33;
        boolean r74 = true;
    L32:
        int r216 = r27;
        if (((int) r1.getLong(r216)) == 0) goto L38;
        boolean r75 = true;
    L37:
        int r315 = r28;
        if (((int) r1.getLong(r315)) == 0) goto L43;
        boolean r76 = true;
    L42:
        r28 = r315;
        int r48 = r29;
        if (((int) r1.getLong(r48)) == 0) goto L48;
        boolean r77 = true;
    L47:
        int r217 = r30;
        int r316 = r31;
        r30 = r217;
        int r218 = r32;
        r0.add(new WorkSpec(r35, r36, r37, r38, r39, r40, r41, r43, r45, new Constraints(r72, r73, r74, r75, r76, r77, r1.getLong(r217), r1.getLong(r316), WorkTypeConverters.byteArrayToSetOfTriggers(r1.getBlob(r218))), r143, r49, r50, r52, r54, r56, r58, r59, r212, r44, r62, r311, r214, r66, r67));     // Catch: Throwable -> L27
        r27 = r216;
        r5 = r162;
        r16 = r34;
        r17 = r42;
        r18 = r310;
        r19 = r53;
        r22 = r232;
        r23 = r215;
        r24 = r314;
        r25 = r47;
        r26 = r55;
        r32 = r218;
        r31 = r316;
        r29 = r48;
        r2 = r153;
        r14 = r33;
        r15 = r68;
        r3 = r69;
        r4 = r853;
        r852 = r211;
        goto L4
    L48:
        r77 = false;
        goto L47
    L43:
        r76 = false;
        goto L42
    L38:
        r75 = false;
        goto L37
    L33:
        r74 = false;
        goto L32
    L22:
        if (r313.intValue() == 0) goto L24;
        boolean r317 = true;
    L25:
        r222 = Boolean.valueOf(r317);     // Catch: Throwable -> L27
        goto L26
    L24:
        r317 = false;
        goto L25
    L19:
        r232 = r312;
        r21 = r46;
        r313 = Integer.valueOf((int) r1.getLong(r215));     // Catch: Throwable -> L27
        goto L20
    L15:
        r66 = r1.getText(r312);     // Catch: Throwable -> L27
        goto L14
    L10:
        r58 = false;
        goto L9
    L51:
        r1.close();
        return r0;
    L27:
        th = move-exception;
        r1.close();
        throw th;
    }

    public static boolean $r8$lambda$hFo_x_SZZBoNGDTckdtCQRnSGyo(String r3, SQLiteConnection r4) {
        SQLiteStatement r32 = r4.prepare(r3);
        boolean r0 = false;
        if (r32.step() == false) goto L11;
        if (((int) r32.getLong(0)) == 0) goto L11;
        r0 = true;
    L11:
        r32.close();
        return r0;
    L9:
        th = move-exception;
        r32.close();
        throw th;
    }

    public static List $r8$lambda$hdVPv_oKlCDT6boNQXfAlJpqbeE(String r3, String r4, SQLiteConnection r5) {
        SQLiteStatement r32 = r5.prepare(r3);
        r32.bindText(1, r4);     // Catch: Throwable -> L8
        ArrayList r42 = new ArrayList();     // Catch: Throwable -> L8
    L4:
        if (r32.step() == false) goto L10;
        r42.add(new WorkSpec.IdAndState(r32.getText(0), WorkTypeConverters.intToState((int) r32.getLong(1))));     // Catch: Throwable -> L8
        goto L4
    L10:
        r32.close();
        return r42;
    L8:
        th = move-exception;
        r32.close();
        throw th;
    }

    public static List $r8$lambda$l02HuTLPNONsWznK7CLIrAjqzl4(String r82, long r83, SQLiteConnection r85) {
        SQLiteStatement r1 = r85.prepare(r82);
        r1.bindLong(1, r83);     // Catch: Throwable -> L27
        int r2 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "id");     // Catch: Throwable -> L27
        int r3 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "state");     // Catch: Throwable -> L27
        int r4 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "worker_class_name");     // Catch: Throwable -> L27
        int r5 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "input_merger_class_name");     // Catch: Throwable -> L27
        int r6 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, PluginsConstants.Settings.TYPE_INPUT);     // Catch: Throwable -> L27
        int r7 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "output");     // Catch: Throwable -> L27
        int r8 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "initial_delay");     // Catch: Throwable -> L27
        int r9 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "interval_duration");     // Catch: Throwable -> L27
        int r10 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "flex_duration");     // Catch: Throwable -> L27
        int r11 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "run_attempt_count");     // Catch: Throwable -> L27
        int r12 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "backoff_policy");     // Catch: Throwable -> L27
        int r13 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "backoff_delay_duration");     // Catch: Throwable -> L27
        int r14 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "last_enqueue_time");     // Catch: Throwable -> L27
        int r15 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "minimum_retention_duration");     // Catch: Throwable -> L27
        int r832 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "schedule_requested_at");     // Catch: Throwable -> L27
        int r84 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "run_in_foreground");     // Catch: Throwable -> L27
        int r852 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "out_of_quota_policy");     // Catch: Throwable -> L27
        int r16 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "period_count");     // Catch: Throwable -> L27
        int r17 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "generation");     // Catch: Throwable -> L27
        int r18 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "next_schedule_time_override");     // Catch: Throwable -> L27
        int r19 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "next_schedule_time_override_generation");     // Catch: Throwable -> L27
        int r20 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "stop_reason");     // Catch: Throwable -> L27
        int r21 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "trace_tag");     // Catch: Throwable -> L27
        int r22 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "backoff_on_system_interruptions");     // Catch: Throwable -> L27
        int r23 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "required_network_type");     // Catch: Throwable -> L27
        int r24 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "required_network_request");     // Catch: Throwable -> L27
        int r25 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "requires_charging");     // Catch: Throwable -> L27
        int r26 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "requires_device_idle");     // Catch: Throwable -> L27
        int r27 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "requires_battery_not_low");     // Catch: Throwable -> L27
        int r28 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "requires_storage_not_low");     // Catch: Throwable -> L27
        int r29 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "trigger_content_update_delay");     // Catch: Throwable -> L27
        int r30 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "trigger_max_content_delay");     // Catch: Throwable -> L27
        int r31 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "content_uri_triggers");     // Catch: Throwable -> L27
        ArrayList r0 = new ArrayList();     // Catch: Throwable -> L27
    L4:
        if (r1.step() == false) goto L51;
        String r34 = r1.getText(r2);     // Catch: Throwable -> L27
        int r32 = r14;
        int r67 = r15;
        WorkInfo$State r35 = WorkTypeConverters.intToState((int) r1.getLong(r3));     // Catch: Throwable -> L27
        String r36 = r1.getText(r4);     // Catch: Throwable -> L27
        String r37 = r1.getText(r5);     // Catch: Throwable -> L27
        byte[] r142 = r1.getBlob(r6);     // Catch: Throwable -> L27
        Data.Companion r152 = Data.Companion;     // Catch: Throwable -> L27
        Data r38 = r152.fromByteArray(r142);     // Catch: Throwable -> L27
        Data r39 = r152.fromByteArray(r1.getBlob(r7));     // Catch: Throwable -> L27
        long r40 = r1.getLong(r8);     // Catch: Throwable -> L27
        long r42 = r1.getLong(r9);     // Catch: Throwable -> L27
        long r44 = r1.getLong(r10);     // Catch: Throwable -> L27
        int r143 = (int) r1.getLong(r11);     // Catch: Throwable -> L27
        int r153 = r2;
        int r68 = r3;
        BackoffPolicy r48 = WorkTypeConverters.intToBackoffPolicy((int) r1.getLong(r12));     // Catch: Throwable -> L27
        long r49 = r1.getLong(r13);     // Catch: Throwable -> L27
        long r51 = r1.getLong(r32);     // Catch: Throwable -> L27
        long r53 = r1.getLong(r67);     // Catch: Throwable -> L27
        int r210 = r832;
        long r55 = r1.getLong(r210);     // Catch: Throwable -> L27
        r832 = r210;
        int r211 = r84;
        int r842 = r4;
        if (((int) r1.getLong(r211)) == 0) goto L10;
        boolean r57 = true;
    L9:
        int r33 = r852;
        int r853 = r5;
        OutOfQuotaPolicy r58 = WorkTypeConverters.intToOutOfQuotaPolicy((int) r1.getLong(r33));     // Catch: Throwable -> L27
        int r43 = r16;
        int r212 = (int) r1.getLong(r43);     // Catch: Throwable -> L27
        int r310 = r17;
        int r45 = (int) r1.getLong(r310);     // Catch: Throwable -> L27
        int r52 = r18;
        long r61 = r1.getLong(r52);     // Catch: Throwable -> L27
        int r213 = r19;
        int r311 = (int) r1.getLong(r213);     // Catch: Throwable -> L27
        r19 = r213;
        int r46 = r20;
        int r214 = (int) r1.getLong(r46);     // Catch: Throwable -> L27
        int r312 = r21;
        Boolean r215 = null;
        if (r1.isNull(r312) == false) goto L15;
        String r65 = null;
    L14:
        int r216 = r22;
        if (r1.isNull(r216) == false) goto L19;
        int r222 = r312;
        r20 = r46;
        Integer r313 = null;
    L20:
        if (r313 != null) goto L22;
    L26:
        Boolean r66 = r215;
        int r314 = r23;
        NetworkType r72 = WorkTypeConverters.intToNetworkType((int) r1.getLong(r314));     // Catch: Throwable -> L27
        int r47 = r24;
        NetworkRequestCompat r71 = WorkTypeConverters.toNetworkRequest$work_runtime_release(r1.getBlob(r47));     // Catch: Throwable -> L27
        int r54 = r25;
        if (((int) r1.getLong(r54)) == 0) goto L33;
        boolean r73 = true;
    L32:
        int r217 = r26;
        if (((int) r1.getLong(r217)) == 0) goto L38;
        boolean r74 = true;
    L37:
        int r315 = r27;
        if (((int) r1.getLong(r315)) == 0) goto L43;
        boolean r75 = true;
    L42:
        r27 = r315;
        int r410 = r28;
        if (((int) r1.getLong(r410)) == 0) goto L48;
        boolean r76 = true;
    L47:
        int r218 = r29;
        int r316 = r30;
        r29 = r218;
        int r219 = r31;
        r0.add(new WorkSpec(r34, r35, r36, r37, r38, r39, r40, r42, r44, new Constraints(r71, r72, r73, r74, r75, r76, r1.getLong(r218), r1.getLong(r316), WorkTypeConverters.byteArrayToSetOfTriggers(r1.getBlob(r219))), r143, r48, r49, r51, r53, r55, r57, r58, r212, r45, r61, r311, r214, r65, r66));     // Catch: Throwable -> L27
        r5 = r853;
        r852 = r33;
        r16 = r43;
        r17 = r310;
        r18 = r52;
        r21 = r222;
        r22 = r216;
        r23 = r314;
        r24 = r47;
        r25 = r54;
        r26 = r217;
        r31 = r219;
        r30 = r316;
        r28 = r410;
        r2 = r153;
        r14 = r32;
        r15 = r67;
        r3 = r68;
        r4 = r842;
        r84 = r211;
        goto L4
    L48:
        r76 = false;
        goto L47
    L43:
        r75 = false;
        goto L42
    L38:
        r74 = false;
        goto L37
    L33:
        r73 = false;
        goto L32
    L22:
        if (r313.intValue() == 0) goto L24;
        boolean r317 = true;
    L25:
        r215 = Boolean.valueOf(r317);     // Catch: Throwable -> L27
        goto L26
    L24:
        r317 = false;
        goto L25
    L19:
        r222 = r312;
        r20 = r46;
        r313 = Integer.valueOf((int) r1.getLong(r216));     // Catch: Throwable -> L27
        goto L20
    L15:
        r65 = r1.getText(r312);     // Catch: Throwable -> L27
        goto L14
    L10:
        r57 = false;
        goto L9
    L51:
        r1.close();
        return r0;
    L27:
        th = move-exception;
        r1.close();
        throw th;
    }

    public static Unit $r8$lambda$rC9mXMIs_zeIDEgWlKpketls7QU(String r0, String r1, SQLiteConnection r2) {
        SQLiteStatement r02 = r2.prepare(r0);
        r02.bindText(1, r1);     // Catch: Throwable -> L6
        r02.step();     // Catch: Throwable -> L6
        r02.close();
        return Unit.INSTANCE;
    L6:
        th = move-exception;
        r02.close();
        throw th;
    }

    /* renamed from: $r8$lambda$uBy-csnqVEM52eglkebt_v2RNeQ, reason: not valid java name */
    public static Unit m995$r8$lambda$uBycsnqVEM52eglkebt_v2RNeQ(String r0, Data r1, String r2, SQLiteConnection r3) {
        SQLiteStatement r02 = r3.prepare(r0);
        r02.bindBlob(1, Data.Companion.toByteArrayInternalV1(r1));     // Catch: Throwable -> L6
        r02.bindText(2, r2);     // Catch: Throwable -> L6
        r02.step();     // Catch: Throwable -> L6
        r02.close();
        return Unit.INSTANCE;
    L6:
        th = move-exception;
        r02.close();
        throw th;
    }

    /* renamed from: $r8$lambda$ypwWC_-IQ7uVVpMML6lkqasFSrc, reason: not valid java name */
    public static List m996$r8$lambda$ypwWC_IQ7uVVpMML6lkqasFSrc(String r1, String r2, SQLiteConnection r3) {
        SQLiteStatement r12 = r3.prepare(r1);
        r12.bindText(1, r2);     // Catch: Throwable -> L8
        ArrayList r22 = new ArrayList();     // Catch: Throwable -> L8
    L4:
        if (r12.step() == false) goto L10;
        byte[] r32 = r12.getBlob(0);     // Catch: Throwable -> L8
        r22.add(Data.Companion.fromByteArray(r32));     // Catch: Throwable -> L8
        goto L4
    L10:
        r12.close();
        return r22;
    L8:
        th = move-exception;
        r12.close();
        throw th;
    }

    /* renamed from: $r8$lambda$zaC8f9OroooJCR2QClopG-XnzS4, reason: not valid java name */
    public static WorkSpec m997$r8$lambda$zaC8f9OroooJCR2QClopGXnzS4(String r68, String r69, SQLiteConnection r70) {
        SQLiteStatement r1 = r70.prepare(r68);
        r1.bindText(1, r69);     // Catch: Throwable -> L26
        int r2 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "id");     // Catch: Throwable -> L26
        int r3 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "state");     // Catch: Throwable -> L26
        int r4 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "worker_class_name");     // Catch: Throwable -> L26
        int r5 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "input_merger_class_name");     // Catch: Throwable -> L26
        int r6 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, PluginsConstants.Settings.TYPE_INPUT);     // Catch: Throwable -> L26
        int r7 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "output");     // Catch: Throwable -> L26
        int r8 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "initial_delay");     // Catch: Throwable -> L26
        int r9 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "interval_duration");     // Catch: Throwable -> L26
        int r10 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "flex_duration");     // Catch: Throwable -> L26
        int r11 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "run_attempt_count");     // Catch: Throwable -> L26
        int r12 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "backoff_policy");     // Catch: Throwable -> L26
        int r13 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "backoff_delay_duration");     // Catch: Throwable -> L26
        int r14 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "last_enqueue_time");     // Catch: Throwable -> L26
        int r15 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "minimum_retention_duration");     // Catch: Throwable -> L26
        int r0 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "schedule_requested_at");     // Catch: Throwable -> L26
        int r02 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "run_in_foreground");     // Catch: Throwable -> L26
        int r03 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "out_of_quota_policy");     // Catch: Throwable -> L26
        int r04 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "period_count");     // Catch: Throwable -> L26
        int r05 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "generation");     // Catch: Throwable -> L26
        int r06 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "next_schedule_time_override");     // Catch: Throwable -> L26
        int r07 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "next_schedule_time_override_generation");     // Catch: Throwable -> L26
        int r08 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "stop_reason");     // Catch: Throwable -> L26
        int r09 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "trace_tag");     // Catch: Throwable -> L26
        int r010 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "backoff_on_system_interruptions");     // Catch: Throwable -> L26
        int r011 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "required_network_type");     // Catch: Throwable -> L26
        int r012 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "required_network_request");     // Catch: Throwable -> L26
        int r013 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "requires_charging");     // Catch: Throwable -> L26
        int r014 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "requires_device_idle");     // Catch: Throwable -> L26
        int r015 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "requires_battery_not_low");     // Catch: Throwable -> L26
        int r016 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "requires_storage_not_low");     // Catch: Throwable -> L26
        int r017 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "trigger_content_update_delay");     // Catch: Throwable -> L26
        int r018 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "trigger_max_content_delay");     // Catch: Throwable -> L26
        int r019 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "content_uri_triggers");     // Catch: Throwable -> L26
        WorkSpec r33 = null;
        Boolean r332 = null;
        if (r1.step() == false) goto L50;
        String r35 = r1.getText(r2);     // Catch: Throwable -> L26
        WorkInfo$State r36 = WorkTypeConverters.intToState((int) r1.getLong(r3));     // Catch: Throwable -> L26
        String r37 = r1.getText(r4);     // Catch: Throwable -> L26
        String r38 = r1.getText(r5);     // Catch: Throwable -> L26
        byte[] r22 = r1.getBlob(r6);     // Catch: Throwable -> L26
        Data.Companion r32 = Data.Companion;     // Catch: Throwable -> L26
        Data r39 = r32.fromByteArray(r22);     // Catch: Throwable -> L26
        Data r40 = r32.fromByteArray(r1.getBlob(r7));     // Catch: Throwable -> L26
        long r41 = r1.getLong(r8);     // Catch: Throwable -> L26
        long r43 = r1.getLong(r9);     // Catch: Throwable -> L26
        long r45 = r1.getLong(r10);     // Catch: Throwable -> L26
        int r23 = (int) r1.getLong(r11);     // Catch: Throwable -> L26
        BackoffPolicy r49 = WorkTypeConverters.intToBackoffPolicy((int) r1.getLong(r12));     // Catch: Throwable -> L26
        long r50 = r1.getLong(r13);     // Catch: Throwable -> L26
        long r52 = r1.getLong(r14);     // Catch: Throwable -> L26
        long r54 = r1.getLong(r15);     // Catch: Throwable -> L26
        long r56 = r1.getLong(r0);     // Catch: Throwable -> L26
        if (((int) r1.getLong(r02)) == 0) goto L9;
        boolean r58 = true;
    L10:
        OutOfQuotaPolicy r59 = WorkTypeConverters.intToOutOfQuotaPolicy((int) r1.getLong(r03));     // Catch: Throwable -> L26
        int r34 = (int) r1.getLong(r04);     // Catch: Throwable -> L26
        int r53 = (int) r1.getLong(r05);     // Catch: Throwable -> L26
        long r62 = r1.getLong(r06);     // Catch: Throwable -> L26
        int r63 = (int) r1.getLong(r07);     // Catch: Throwable -> L26
        int r72 = (int) r1.getLong(r08);     // Catch: Throwable -> L26
        if (r1.isNull(r09) == false) goto L14;
        String r66 = null;
    L16:
        if (r1.isNull(r010) == false) goto L18;
        Integer r82 = null;
    L19:
        if (r82 != null) goto L21;
    L25:
        Boolean r67 = r332;
        NetworkType r112 = WorkTypeConverters.intToNetworkType((int) r1.getLong(r011));     // Catch: Throwable -> L26
        NetworkRequestCompat r102 = WorkTypeConverters.toNetworkRequest$work_runtime_release(r1.getBlob(r012));     // Catch: Throwable -> L26
        if (((int) r1.getLong(r013)) == 0) goto L32;
        boolean r122 = true;
    L34:
        if (((int) r1.getLong(r014)) == 0) goto L37;
        boolean r132 = true;
    L39:
        if (((int) r1.getLong(r015)) == 0) goto L42;
        boolean r142 = true;
    L44:
        if (((int) r1.getLong(r016)) == 0) goto L47;
        boolean r152 = true;
    L48:
        r33 = new WorkSpec(r35, r36, r37, r38, r39, r40, r41, r43, r45, new Constraints(r102, r112, r122, r132, r142, r152, r1.getLong(r017), r1.getLong(r018), WorkTypeConverters.byteArrayToSetOfTriggers(r1.getBlob(r019))), r23, r49, r50, r52, r54, r56, r58, r59, r34, r53, r62, r63, r72, r66, r67);     // Catch: Throwable -> L26
        goto L50
    L47:
        r152 = false;
        goto L48
    L42:
        r142 = false;
        goto L44
    L37:
        r132 = false;
        goto L39
    L32:
        r122 = false;
        goto L34
    L21:
        if (r82.intValue() == 0) goto L23;
        boolean r83 = true;
    L24:
        r332 = Boolean.valueOf(r83);     // Catch: Throwable -> L26
        goto L25
    L23:
        r83 = false;
        goto L24
    L18:
        r82 = Integer.valueOf((int) r1.getLong(r010));     // Catch: Throwable -> L26
        goto L19
    L14:
        r66 = r1.getText(r09);     // Catch: Throwable -> L26
        goto L16
    L9:
        r58 = false;
    L50:
        r1.close();
        return r33;
    L26:
        th = move-exception;
        r1.close();
        throw th;
    }

    static {
        Companion = new Companion(null);
    }

    public WorkSpecDao_Impl(RoomDatabase r1) {
        this.__db = r1;
        this.__insertAdapterOfWorkSpec = new AnonymousClass1();
        this.__updateAdapterOfWorkSpec = new AnonymousClass2();
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public int countNonFinishedContentUriTriggerWorkers() {
        return ((Number) DBUtil.performBlocking(this.__db, true, false, new WorkSpecDao_Impl$$ExternalSyntheticLambda4("Select COUNT(*) FROM workspec WHERE LENGTH(content_uri_triggers)<>0 AND state NOT IN (2, 3, 5)"))).intValue();
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public void delete(String r3) {
        DBUtil.performBlocking(this.__db, false, true, new WorkSpecDao_Impl$$ExternalSyntheticLambda5("DELETE FROM workspec WHERE id=?", r3));
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<WorkSpec> getAllEligibleWorkSpecsForScheduling(int r3) {
        return (List) DBUtil.performBlocking(this.__db, true, false, new WorkSpecDao_Impl$$ExternalSyntheticLambda7("SELECT * FROM workspec WHERE state=0 ORDER BY last_enqueue_time LIMIT ?", r3));
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<WorkSpec> getEligibleWorkForScheduling(int r3) {
        return (List) DBUtil.performBlocking(this.__db, true, false, new WorkSpecDao_Impl$$ExternalSyntheticLambda11("SELECT * FROM workspec WHERE state=0 AND schedule_requested_at=-1 ORDER BY last_enqueue_time LIMIT (SELECT MAX(?-COUNT(*), 0) FROM workspec WHERE schedule_requested_at<>-1 AND LENGTH(content_uri_triggers)=0 AND state NOT IN (2, 3, 5))", r3));
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<WorkSpec> getEligibleWorkForSchedulingWithContentUris() {
        return (List) DBUtil.performBlocking(this.__db, true, false, new WorkSpecDao_Impl$$ExternalSyntheticLambda2("SELECT * FROM workspec WHERE state=0 AND schedule_requested_at=-1 AND LENGTH(content_uri_triggers)<>0 ORDER BY last_enqueue_time"));
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<Data> getInputsFromPrerequisites(String r3) {
        return (List) DBUtil.performBlocking(this.__db, true, false, new WorkSpecDao_Impl$$ExternalSyntheticLambda25("SELECT output FROM workspec WHERE id IN\n             (SELECT prerequisite_id FROM dependency WHERE work_spec_id=?)", r3));
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<WorkSpec> getRecentlyCompletedWork(long r3) {
        return (List) DBUtil.performBlocking(this.__db, true, false, new WorkSpecDao_Impl$$ExternalSyntheticLambda17("SELECT * FROM workspec WHERE last_enqueue_time >= ? AND state IN (2, 3, 5) ORDER BY last_enqueue_time DESC", r3));
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<WorkSpec> getRunningWork() {
        return (List) DBUtil.performBlocking(this.__db, true, false, new WorkSpecDao_Impl$$ExternalSyntheticLambda13("SELECT * FROM workspec WHERE state=1"));
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<WorkSpec> getScheduledWork() {
        return (List) DBUtil.performBlocking(this.__db, true, false, new WorkSpecDao_Impl$$ExternalSyntheticLambda10("SELECT * FROM workspec WHERE state=0 AND schedule_requested_at<>-1"));
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public WorkInfo$State getState(String r3) {
        return (WorkInfo$State) DBUtil.performBlocking(this.__db, true, false, new WorkSpecDao_Impl$$ExternalSyntheticLambda15("SELECT state FROM workspec WHERE id=?", r3));
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<String> getUnfinishedWorkWithName(String r3) {
        return (List) DBUtil.performBlocking(this.__db, true, false, new WorkSpecDao_Impl$$ExternalSyntheticLambda16("SELECT id FROM workspec WHERE state NOT IN (2, 3, 5) AND id IN (SELECT work_spec_id FROM workname WHERE name=?)", r3));
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public WorkSpec getWorkSpec(String r3) {
        return (WorkSpec) DBUtil.performBlocking(this.__db, true, false, new WorkSpecDao_Impl$$ExternalSyntheticLambda0("SELECT * FROM workspec WHERE id=?", r3));
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public List<WorkSpec.IdAndState> getWorkSpecIdAndStatesForName(String r3) {
        return (List) DBUtil.performBlocking(this.__db, true, false, new WorkSpecDao_Impl$$ExternalSyntheticLambda9("SELECT id, state FROM workspec WHERE id IN (SELECT work_spec_id FROM workname WHERE name=?)", r3));
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public Flow<Boolean> hasUnfinishedWorkFlow() {
        return FlowUtil.createFlow(this.__db, false, new String[]{"workspec"}, new WorkSpecDao_Impl$$ExternalSyntheticLambda6("SELECT COUNT(*) > 0 FROM workspec WHERE state NOT IN (2, 3, 5) LIMIT 1"));
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public void incrementPeriodCount(String r3) {
        DBUtil.performBlocking(this.__db, false, true, new WorkSpecDao_Impl$$ExternalSyntheticLambda24("UPDATE workspec SET period_count=period_count+1 WHERE id=?", r3));
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public int incrementWorkSpecRunAttemptCount(String r3) {
        return ((Number) DBUtil.performBlocking(this.__db, false, true, new WorkSpecDao_Impl$$ExternalSyntheticLambda26("UPDATE workspec SET run_attempt_count=run_attempt_count+1 WHERE id=?", r3))).intValue();
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public void insertWorkSpec(WorkSpec r3) {
        DBUtil.performBlocking(this.__db, false, true, new WorkSpecDao_Impl$$ExternalSyntheticLambda1(this, r3));
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public int markWorkSpecScheduled(String r3, long r4) {
        return ((Number) DBUtil.performBlocking(this.__db, false, true, new WorkSpecDao_Impl$$ExternalSyntheticLambda12("UPDATE workspec SET schedule_requested_at=? WHERE id=?", r4, r3))).intValue();
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public int resetScheduledState() {
        return ((Number) DBUtil.performBlocking(this.__db, false, true, new WorkSpecDao_Impl$$ExternalSyntheticLambda8("UPDATE workspec SET schedule_requested_at=-1 WHERE state NOT IN (2, 3, 5)"))).intValue();
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public void resetWorkSpecNextScheduleTimeOverride(String r3, int r4) {
        DBUtil.performBlocking(this.__db, false, true, new WorkSpecDao_Impl$$ExternalSyntheticLambda21("UPDATE workspec SET next_schedule_time_override=9223372036854775807 WHERE (id=? AND next_schedule_time_override_generation=?)", r3, r4));
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public int resetWorkSpecRunAttemptCount(String r3) {
        return ((Number) DBUtil.performBlocking(this.__db, false, true, new WorkSpecDao_Impl$$ExternalSyntheticLambda23("UPDATE workspec SET run_attempt_count=0 WHERE id=?", r3))).intValue();
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public int setCancelledState(String r3) {
        return ((Number) DBUtil.performBlocking(this.__db, false, true, new WorkSpecDao_Impl$$ExternalSyntheticLambda18("UPDATE workspec SET stop_reason = CASE WHEN state=1 THEN 1 ELSE -256 END, state=5 WHERE id=?", r3))).intValue();
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public void setLastEnqueueTime(String r3, long r4) {
        DBUtil.performBlocking(this.__db, false, true, new WorkSpecDao_Impl$$ExternalSyntheticLambda20("UPDATE workspec SET last_enqueue_time=? WHERE id=?", r4, r3));
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public void setOutput(String r3, Data r4) {
        DBUtil.performBlocking(this.__db, false, true, new WorkSpecDao_Impl$$ExternalSyntheticLambda19("UPDATE workspec SET output=? WHERE id=?", r4, r3));
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public int setState(WorkInfo$State r3, String r4) {
        return ((Number) DBUtil.performBlocking(this.__db, false, true, new WorkSpecDao_Impl$$ExternalSyntheticLambda14("UPDATE workspec SET state=? WHERE id=?", r3, r4))).intValue();
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public void setStopReason(String r3, int r4) {
        DBUtil.performBlocking(this.__db, false, true, new WorkSpecDao_Impl$$ExternalSyntheticLambda3("UPDATE workspec SET stop_reason=? WHERE id=?", r4, r3));
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public void updateWorkSpec(WorkSpec r3) {
        DBUtil.performBlocking(this.__db, false, true, new WorkSpecDao_Impl$$ExternalSyntheticLambda22(this, r3));
    }
}
