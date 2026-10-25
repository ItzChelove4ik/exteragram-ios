package androidx.work.impl.model;

import android.annotation.SuppressLint;
import androidx.work.Data;
import androidx.work.WorkInfo$State;
import androidx.work.impl.model.WorkSpec;
import java.util.List;
import kotlin.Metadata;
import kotlinx.coroutines.flow.Flow;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0018\bg\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H'¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007H'¢\u0006\u0004\b\t\u0010\nJ\u0019\u0010\u000b\u001a\u0004\u0018\u00010\u00022\u0006\u0010\b\u001a\u00020\u0007H'¢\u0006\u0004\b\u000b\u0010\fJ\u001d\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\r\u001a\u00020\u0007H'¢\u0006\u0004\b\u0010\u0010\u0011J\u001f\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\u0007H'¢\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0017\u001a\u00020\u00142\u0006\u0010\b\u001a\u00020\u0007H'¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u0019\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007H'¢\u0006\u0004\b\u0019\u0010\nJ\u001f\u0010\u001c\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u001aH'¢\u0006\u0004\b\u001c\u0010\u001dJ\u001f\u0010 \u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u001eH'¢\u0006\u0004\b \u0010!J\u0017\u0010\"\u001a\u00020\u00142\u0006\u0010\b\u001a\u00020\u0007H'¢\u0006\u0004\b\"\u0010\u0018J\u0017\u0010#\u001a\u00020\u00142\u0006\u0010\b\u001a\u00020\u0007H'¢\u0006\u0004\b#\u0010\u0018J\u001f\u0010%\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010$\u001a\u00020\u0014H'¢\u0006\u0004\b%\u0010&J\u0019\u0010'\u001a\u0004\u0018\u00010\u00122\u0006\u0010\b\u001a\u00020\u0007H'¢\u0006\u0004\b'\u0010(J\u001d\u0010)\u001a\b\u0012\u0004\u0012\u00020\u001a0\u000e2\u0006\u0010\b\u001a\u00020\u0007H'¢\u0006\u0004\b)\u0010\u0011J\u001d\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00070\u000e2\u0006\u0010\r\u001a\u00020\u0007H'¢\u0006\u0004\b*\u0010\u0011J\u0015\u0010-\u001a\b\u0012\u0004\u0012\u00020,0+H'¢\u0006\u0004\b-\u0010.J\u001f\u00100\u001a\u00020\u00142\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010/\u001a\u00020\u001eH'¢\u0006\u0004\b0\u00101J\u000f\u00102\u001a\u00020\u0014H'¢\u0006\u0004\b2\u00103J\u001d\u00105\u001a\b\u0012\u0004\u0012\u00020\u00020\u000e2\u0006\u00104\u001a\u00020\u0014H'¢\u0006\u0004\b5\u00106J\u0015\u00107\u001a\b\u0012\u0004\u0012\u00020\u00020\u000eH'¢\u0006\u0004\b7\u00108J\u001d\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00020\u000e2\u0006\u00109\u001a\u00020\u0014H'¢\u0006\u0004\b:\u00106J\u0015\u0010;\u001a\b\u0012\u0004\u0012\u00020\u00020\u000eH'¢\u0006\u0004\b;\u00108J\u0015\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00020\u000eH'¢\u0006\u0004\b<\u00108J\u001d\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00020\u000e2\u0006\u0010=\u001a\u00020\u001eH'¢\u0006\u0004\b>\u0010?J\u0017\u0010@\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H'¢\u0006\u0004\b@\u0010\u0006J\u000f\u0010A\u001a\u00020\u0014H'¢\u0006\u0004\bA\u00103J\u001f\u0010C\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010B\u001a\u00020\u0014H'¢\u0006\u0004\bC\u0010&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006DÀ\u0006\u0001"}, d2 = {"Landroidx/work/impl/model/WorkSpecDao;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/work/impl/model/WorkSpec;", "workSpec", _UrlKt.FRAGMENT_ENCODE_SET, "insertWorkSpec", "(Landroidx/work/impl/model/WorkSpec;)V", _UrlKt.FRAGMENT_ENCODE_SET, "id", "delete", "(Ljava/lang/String;)V", "getWorkSpec", "(Ljava/lang/String;)Landroidx/work/impl/model/WorkSpec;", "name", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/work/impl/model/WorkSpec$IdAndState;", "getWorkSpecIdAndStatesForName", "(Ljava/lang/String;)Ljava/util/List;", "Landroidx/work/WorkInfo$State;", "state", _UrlKt.FRAGMENT_ENCODE_SET, "setState", "(Landroidx/work/WorkInfo$State;Ljava/lang/String;)I", "setCancelledState", "(Ljava/lang/String;)I", "incrementPeriodCount", "Landroidx/work/Data;", "output", "setOutput", "(Ljava/lang/String;Landroidx/work/Data;)V", _UrlKt.FRAGMENT_ENCODE_SET, "enqueueTime", "setLastEnqueueTime", "(Ljava/lang/String;J)V", "incrementWorkSpecRunAttemptCount", "resetWorkSpecRunAttemptCount", "overrideGeneration", "resetWorkSpecNextScheduleTimeOverride", "(Ljava/lang/String;I)V", "getState", "(Ljava/lang/String;)Landroidx/work/WorkInfo$State;", "getInputsFromPrerequisites", "getUnfinishedWorkWithName", "Lkotlinx/coroutines/flow/Flow;", _UrlKt.FRAGMENT_ENCODE_SET, "hasUnfinishedWorkFlow", "()Lkotlinx/coroutines/flow/Flow;", "startTime", "markWorkSpecScheduled", "(Ljava/lang/String;J)I", "resetScheduledState", "()I", "schedulerLimit", "getEligibleWorkForScheduling", "(I)Ljava/util/List;", "getEligibleWorkForSchedulingWithContentUris", "()Ljava/util/List;", "maxLimit", "getAllEligibleWorkSpecsForScheduling", "getScheduledWork", "getRunningWork", "startingAt", "getRecentlyCompletedWork", "(J)Ljava/util/List;", "updateWorkSpec", "countNonFinishedContentUriTriggerWorkers", "stopReason", "setStopReason", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SuppressLint({"UnknownNullness"})
/* loaded from: classes.dex */
public interface WorkSpecDao {
    int countNonFinishedContentUriTriggerWorkers();

    void delete(String r1);

    List<WorkSpec> getAllEligibleWorkSpecsForScheduling(int r1);

    List<WorkSpec> getEligibleWorkForScheduling(int r1);

    List<WorkSpec> getEligibleWorkForSchedulingWithContentUris();

    List<Data> getInputsFromPrerequisites(String r1);

    List<WorkSpec> getRecentlyCompletedWork(long r1);

    List<WorkSpec> getRunningWork();

    List<WorkSpec> getScheduledWork();

    WorkInfo$State getState(String r1);

    List<String> getUnfinishedWorkWithName(String r1);

    WorkSpec getWorkSpec(String r1);

    List<WorkSpec.IdAndState> getWorkSpecIdAndStatesForName(String r1);

    Flow<Boolean> hasUnfinishedWorkFlow();

    void incrementPeriodCount(String r1);

    int incrementWorkSpecRunAttemptCount(String r1);

    void insertWorkSpec(WorkSpec r1);

    int markWorkSpecScheduled(String r1, long r2);

    int resetScheduledState();

    void resetWorkSpecNextScheduleTimeOverride(String r1, int r2);

    int resetWorkSpecRunAttemptCount(String r1);

    int setCancelledState(String r1);

    void setLastEnqueueTime(String r1, long r2);

    void setOutput(String r1, Data r2);

    int setState(WorkInfo$State r1, String r2);

    void setStopReason(String r1, int r2);

    void updateWorkSpec(WorkSpec r1);
}
