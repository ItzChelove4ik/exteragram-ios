package androidx.work.impl.model;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.arch.core.util.Function;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.Logger;
import androidx.work.OutOfQuotaPolicy;
import androidx.work.OverwritingInputMerger;
import androidx.work.WorkInfo$State;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.tgnet.TLObject;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0002\n\u0002\b/\b\u0087\b\u0018\u0000 X2\u00020\u0001:\u0002YXB\u0081\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0002\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\b\b\u0002\u0010\r\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000f\u0012\b\b\u0003\u0010\u0012\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u0019\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u001b\u0012\b\b\u0002\u0010\u001d\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u001e\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u001f\u001a\u00020\u000b\u0012\b\b\u0002\u0010 \u001a\u00020\u0011\u0012\b\b\u0002\u0010!\u001a\u00020\u0011\u0012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u0019¢\u0006\u0004\b$\u0010%B\u0019\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010&\u001a\u00020\u0002¢\u0006\u0004\b$\u0010'B\u0019\b\u0016\u0012\u0006\u0010(\u001a\u00020\u0002\u0012\u0006\u0010)\u001a\u00020\u0000¢\u0006\u0004\b$\u0010*J\u0015\u0010,\u001a\u00020+2\u0006\u0010\u0015\u001a\u00020\u000b¢\u0006\u0004\b,\u0010-J\u0015\u0010.\u001a\u00020+2\u0006\u0010\r\u001a\u00020\u000b¢\u0006\u0004\b.\u0010-J\u001d\u0010.\u001a\u00020+2\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000b¢\u0006\u0004\b.\u0010/J\r\u00100\u001a\u00020\u000b¢\u0006\u0004\b0\u00101J\r\u00102\u001a\u00020\u0019¢\u0006\u0004\b2\u00103J\u000f\u00104\u001a\u00020\u0002H\u0016¢\u0006\u0004\b4\u00105J\u008e\u0002\u00106\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000b2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0003\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u000b2\b\b\u0002\u0010\u0016\u001a\u00020\u000b2\b\b\u0002\u0010\u0017\u001a\u00020\u000b2\b\b\u0002\u0010\u0018\u001a\u00020\u000b2\b\b\u0002\u0010\u001a\u001a\u00020\u00192\b\b\u0002\u0010\u001c\u001a\u00020\u001b2\b\b\u0002\u0010\u001d\u001a\u00020\u00112\b\b\u0002\u0010\u001e\u001a\u00020\u00112\b\b\u0002\u0010\u001f\u001a\u00020\u000b2\b\b\u0002\u0010 \u001a\u00020\u00112\b\b\u0002\u0010!\u001a\u00020\u00112\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u0019HÆ\u0001¢\u0006\u0004\b6\u00107J\u0010\u00108\u001a\u00020\u0011HÖ\u0001¢\u0006\u0004\b8\u00109J\u001a\u0010:\u001a\u00020\u00192\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b:\u0010;R\u0014\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010<R\u0016\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b\u0005\u0010=R\u0016\u0010\u0006\u001a\u00020\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b\u0006\u0010<R\u0016\u0010\u0007\u001a\u00020\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b\u0007\u0010<R\u0016\u0010\t\u001a\u00020\b8\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b\t\u0010>R\u0016\u0010\n\u001a\u00020\b8\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b\n\u0010>R\u0016\u0010\f\u001a\u00020\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b\f\u0010?R\u0016\u0010\r\u001a\u00020\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b\r\u0010?R\u0016\u0010\u000e\u001a\u00020\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b\u000e\u0010?R\u0016\u0010\u0010\u001a\u00020\u000f8\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b\u0010\u0010@R\u0016\u0010\u0012\u001a\u00020\u00118\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b\u0012\u0010AR\u0016\u0010\u0014\u001a\u00020\u00138\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b\u0014\u0010BR\u0016\u0010\u0015\u001a\u00020\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b\u0015\u0010?R\u0016\u0010\u0016\u001a\u00020\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b\u0016\u0010?R\u0016\u0010\u0017\u001a\u00020\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b\u0017\u0010?R\u0016\u0010\u0018\u001a\u00020\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b\u0018\u0010?R\u0016\u0010\u001a\u001a\u00020\u00198\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b\u001a\u0010CR\u0016\u0010\u001c\u001a\u00020\u001b8\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b\u001c\u0010DR\"\u0010\u001d\u001a\u00020\u00118\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001d\u0010A\u001a\u0004\bE\u00109\"\u0004\bF\u0010GR\u001a\u0010\u001e\u001a\u00020\u00118\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\u001e\u0010A\u001a\u0004\bH\u00109R\"\u0010\u001f\u001a\u00020\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001f\u0010?\u001a\u0004\bI\u00101\"\u0004\bJ\u0010-R\"\u0010 \u001a\u00020\u00118\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b \u0010A\u001a\u0004\bK\u00109\"\u0004\bL\u0010GR\u001a\u0010!\u001a\u00020\u00118\u0006X\u0087\u0004¢\u0006\f\n\u0004\b!\u0010A\u001a\u0004\bM\u00109R$\u0010\"\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\"\u0010<\u001a\u0004\bN\u00105\"\u0004\bO\u0010PR$\u0010#\u001a\u0004\u0018\u00010\u00198\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b#\u0010Q\u001a\u0004\bR\u0010S\"\u0004\bT\u0010UR\u0011\u0010V\u001a\u00020\u00198F¢\u0006\u0006\u001a\u0004\bV\u00103R\u0011\u0010W\u001a\u00020\u00198F¢\u0006\u0006\u001a\u0004\bW\u00103¨\u0006Z"}, d2 = {"Landroidx/work/impl/model/WorkSpec;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "id", "Landroidx/work/WorkInfo$State;", "state", "workerClassName", "inputMergerClassName", "Landroidx/work/Data;", PluginsConstants.Settings.TYPE_INPUT, "output", _UrlKt.FRAGMENT_ENCODE_SET, "initialDelay", "intervalDuration", "flexDuration", "Landroidx/work/Constraints;", "constraints", _UrlKt.FRAGMENT_ENCODE_SET, "runAttemptCount", "Landroidx/work/BackoffPolicy;", "backoffPolicy", "backoffDelayDuration", "lastEnqueueTime", "minimumRetentionDuration", "scheduleRequestedAt", _UrlKt.FRAGMENT_ENCODE_SET, "expedited", "Landroidx/work/OutOfQuotaPolicy;", "outOfQuotaPolicy", "periodCount", "generation", "nextScheduleTimeOverride", "nextScheduleTimeOverrideGeneration", "stopReason", "traceTag", "backOffOnSystemInterruptions", "<init>", "(Ljava/lang/String;Landroidx/work/WorkInfo$State;Ljava/lang/String;Ljava/lang/String;Landroidx/work/Data;Landroidx/work/Data;JJJLandroidx/work/Constraints;ILandroidx/work/BackoffPolicy;JJJJZLandroidx/work/OutOfQuotaPolicy;IIJIILjava/lang/String;Ljava/lang/Boolean;)V", "workerClassName_", "(Ljava/lang/String;Ljava/lang/String;)V", "newId", "other", "(Ljava/lang/String;Landroidx/work/impl/model/WorkSpec;)V", _UrlKt.FRAGMENT_ENCODE_SET, "setBackoffDelayDuration", "(J)V", "setPeriodic", "(JJ)V", "calculateNextRunTime", "()J", "hasConstraints", "()Z", "toString", "()Ljava/lang/String;", "copy", "(Ljava/lang/String;Landroidx/work/WorkInfo$State;Ljava/lang/String;Ljava/lang/String;Landroidx/work/Data;Landroidx/work/Data;JJJLandroidx/work/Constraints;ILandroidx/work/BackoffPolicy;JJJJZLandroidx/work/OutOfQuotaPolicy;IIJIILjava/lang/String;Ljava/lang/Boolean;)Landroidx/work/impl/model/WorkSpec;", "hashCode", "()I", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "Landroidx/work/WorkInfo$State;", "Landroidx/work/Data;", "J", "Landroidx/work/Constraints;", "I", "Landroidx/work/BackoffPolicy;", "Z", "Landroidx/work/OutOfQuotaPolicy;", "getPeriodCount", "setPeriodCount", "(I)V", "getGeneration", "getNextScheduleTimeOverride", "setNextScheduleTimeOverride", "getNextScheduleTimeOverrideGeneration", "setNextScheduleTimeOverrideGeneration", "getStopReason", "getTraceTag", "setTraceTag", "(Ljava/lang/String;)V", "Ljava/lang/Boolean;", "getBackOffOnSystemInterruptions", "()Ljava/lang/Boolean;", "setBackOffOnSystemInterruptions", "(Ljava/lang/Boolean;)V", "isPeriodic", "isBackedOff", "Companion", "IdAndState", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nWorkSpec.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WorkSpec.kt\nandroidx/work/impl/model/WorkSpec\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,467:1\n1563#2:468\n1634#2,3:469\n*S KotlinDebug\n*F\n+ 1 WorkSpec.kt\nandroidx/work/impl/model/WorkSpec\n*L\n406#1:468\n406#1:469,3\n*E\n"})
/* loaded from: classes.dex */
public final class WorkSpec {
    public static final Companion Companion = null;
    private static final String TAG = null;

    @JvmField
    public static final Function<List<Object>, List<Object>> WORK_INFO_MAPPER = null;
    private Boolean backOffOnSystemInterruptions;

    @JvmField
    public long backoffDelayDuration;

    @JvmField
    public BackoffPolicy backoffPolicy;

    @JvmField
    public Constraints constraints;

    @JvmField
    public boolean expedited;

    @JvmField
    public long flexDuration;
    private final int generation;

    @JvmField
    public final String id;

    @JvmField
    public long initialDelay;

    @JvmField
    public Data input;

    @JvmField
    public String inputMergerClassName;

    @JvmField
    public long intervalDuration;

    @JvmField
    public long lastEnqueueTime;

    @JvmField
    public long minimumRetentionDuration;
    private long nextScheduleTimeOverride;
    private int nextScheduleTimeOverrideGeneration;

    @JvmField
    public OutOfQuotaPolicy outOfQuotaPolicy;

    @JvmField
    public Data output;
    private int periodCount;

    @JvmField
    public int runAttemptCount;

    @JvmField
    public long scheduleRequestedAt;

    @JvmField
    public WorkInfo$State state;
    private final int stopReason;
    private String traceTag;

    @JvmField
    public String workerClassName;

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003Je\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\n¢\u0006\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\n8\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R,\u0010\u001c\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u001b0\u001a8\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001d¨\u0006\u001e"}, d2 = {"Landroidx/work/impl/model/WorkSpec$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "isBackedOff", _UrlKt.FRAGMENT_ENCODE_SET, "runAttemptCount", "Landroidx/work/BackoffPolicy;", "backoffPolicy", _UrlKt.FRAGMENT_ENCODE_SET, "backoffDelayDuration", "lastEnqueueTime", "periodCount", "isPeriodic", "initialDelay", "flexDuration", "intervalDuration", "nextScheduleTimeOverride", "calculateNextRunTime", "(ZILandroidx/work/BackoffPolicy;JJIZJJJJ)J", _UrlKt.FRAGMENT_ENCODE_SET, "TAG", "Ljava/lang/String;", "SCHEDULE_NOT_REQUESTED_YET", "J", "Landroidx/arch/core/util/Function;", _UrlKt.FRAGMENT_ENCODE_SET, "WORK_INFO_MAPPER", "Landroidx/arch/core/util/Function;", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final long calculateNextRunTime(boolean r5, int r6, BackoffPolicy r7, long r8, long r10, int r12, boolean r13, long r14, long r16, long r18, long r20) {
            if (r20 == LongCompanionObject.MAX_VALUE) goto L9;
            if (r13 == false) goto L9;
            if (r12 != 0) goto L8;
            return r20;
        L8:
            return RangesKt.coerceAtLeast(r20, r10 + 900000);
        L9:
            if (r5 == true) goto L11;
            if (r13 == false) goto L27;
            if (r12 != 0) goto L20;
            r10 = r10 + r14;
        L22:
            if (r16 == r18) goto L25;
            if (r12 != 0) goto L25;
            long r4 = r18 - r16;
        L16:
            return r10 + r4;
        L25:
            return r10;
        L20:
            r10 = r10 + r18;
            goto L22
        L27:
            if (r10 != (-1)) goto L30;
            return LongCompanionObject.MAX_VALUE;
        L30:
            return r10 + r14;
        L11:
            if (r7 != BackoffPolicy.LINEAR) goto L13;
            long r82 = r8 * r6;
        L14:
            r4 = RangesKt.coerceAtMost(r82, 18000000);
            goto L16
        L13:
            r82 = (long) Math.scalb(r8, r6 - 1);
            goto L14
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u000b\u001a\u00020\nHÖ\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u001a\u0010\u000f\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b\u0003\u0010\u0011R\u0016\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b\u0005\u0010\u0012¨\u0006\u0013"}, d2 = {"Landroidx/work/impl/model/WorkSpec$IdAndState;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "id", "Landroidx/work/WorkInfo$State;", "state", "<init>", "(Ljava/lang/String;Landroidx/work/WorkInfo$State;)V", "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "Landroidx/work/WorkInfo$State;", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class IdAndState {

        @JvmField
        public String id;

        @JvmField
        public WorkInfo$State state;

        public IdAndState(String r1, WorkInfo$State r2) {
            this.id = r1;
            this.state = r2;
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof IdAndState) == true) goto L8;
            return false;
        L8:
            IdAndState r52 = (IdAndState) r5;
            if (Intrinsics.areEqual(this.id, r52.id) == true) goto L12;
            return false;
        L12:
            if (this.state == r52.state) goto L14;
            return false;
        L14:
            return true;
        }

        public int hashCode() {
            return (this.id.hashCode() * 31) + this.state.hashCode();
        }

        public String toString() {
            return "IdAndState(id=" + this.id + ", state=" + this.state + ')';
        }
    }

    public static List $r8$lambda$kxU3gfKzoZdfGv8GSpkPecoJFFM(List r3) {
        if (r3 == null) goto L9;
        List r32 = r3;
        ArrayList r1 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r32, 10));
        Iterator r33 = r32.iterator();
        if (r33.hasNext() == true) goto L7;
        return r1;
    L7:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r33.next());
        throw null;
    L9:
        return null;
    }

    static {
        Companion = new Companion(null);
        TAG = Logger.tagWithPrefix("WorkSpec");
        WORK_INFO_MAPPER = new WorkSpec$$ExternalSyntheticLambda0();
    }

    public WorkSpec(String r1, WorkInfo$State r2, String r3, String r4, Data r5, Data r6, long r7, long r9, long r11, Constraints r13, int r14, BackoffPolicy r15, long r16, long r18, long r20, long r22, boolean r24, OutOfQuotaPolicy r25, int r26, int r27, long r28, int r30, int r31, String r32, Boolean r33) {
        this.id = r1;
        this.state = r2;
        this.workerClassName = r3;
        this.inputMergerClassName = r4;
        this.input = r5;
        this.output = r6;
        this.initialDelay = r7;
        this.intervalDuration = r9;
        this.flexDuration = r11;
        this.constraints = r13;
        this.runAttemptCount = r14;
        this.backoffPolicy = r15;
        this.backoffDelayDuration = r16;
        this.lastEnqueueTime = r18;
        this.minimumRetentionDuration = r20;
        this.scheduleRequestedAt = r22;
        this.expedited = r24;
        this.outOfQuotaPolicy = r25;
        this.periodCount = r26;
        this.generation = r27;
        this.nextScheduleTimeOverride = r28;
        this.nextScheduleTimeOverrideGeneration = r30;
        this.stopReason = r31;
        this.traceTag = r32;
        this.backOffOnSystemInterruptions = r33;
    }

    public /* synthetic */ WorkSpec(String r37, WorkInfo$State r38, String r39, String r40, Data r41, Data r42, long r43, long r45, long r47, Constraints r49, int r50, BackoffPolicy r51, long r52, long r54, long r56, long r58, boolean r60, OutOfQuotaPolicy r61, int r62, int r63, long r64, int r66, int r67, String r68, Boolean r69, int r70, DefaultConstructorMarker r71) {
        if ((r70 & 2) == 0) goto L5;
        WorkInfo$State r4 = WorkInfo$State.ENQUEUED;
    L7:
        if ((r70 & 8) == 0) goto L9;
        String r6 = OverwritingInputMerger.class.getName();
    L11:
        if ((r70 & 16) == 0) goto L13;
        Data r7 = Data.EMPTY;
    L15:
        if ((r70 & 32) == 0) goto L17;
        Data r8 = Data.EMPTY;
    L18:
        long r2 = 0;
        if ((r70 & 64) == 0) goto L21;
        long r9 = 0;
    L23:
        if ((r70 & 128) == 0) goto L25;
        long r11 = 0;
    L27:
        if ((r70 & 256) == 0) goto L29;
        long r13 = 0;
    L31:
        if ((r70 & 512) == 0) goto L33;
        Constraints r15 = Constraints.NONE;
    L35:
        if ((r70 & 1024) == 0) goto L37;
        int r16 = 0;
    L39:
        if ((r70 & 2048) == 0) goto L41;
        BackoffPolicy r17 = BackoffPolicy.EXPONENTIAL;
    L43:
        if ((r70 & 4096) == 0) goto L45;
        long r18 = NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS;
    L47:
        if ((r70 & 8192) == 0) goto L49;
        long r22 = -1;
    L51:
        if ((r70 & 16384) != 0) goto L55;
        r2 = r56;
    L55:
        if ((32768 & r70) == 0) goto L57;
        long r24 = -1;
    L59:
        if ((65536 & r70) == 0) goto L61;
        boolean r26 = false;
    L63:
        if ((131072 & r70) == 0) goto L65;
        OutOfQuotaPolicy r27 = OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST;
    L67:
        if ((262144 & r70) == 0) goto L69;
        int r28 = 0;
    L71:
        if ((524288 & r70) == 0) goto L73;
        int r29 = 0;
    L75:
        if ((1048576 & r70) == 0) goto L77;
        long r30 = Long.MAX_VALUE;
    L79:
        if ((2097152 & r70) == 0) goto L81;
        int r32 = 0;
    L83:
        if ((4194304 & r70) == 0) goto L85;
        int r33 = -256;
    L87:
        if ((8388608 & r70) == 0) goto L89;
        String r34 = null;
    L91:
        if ((r70 & 16777216) == 0) goto L94;
        Boolean r35 = Boolean.FALSE;
    L95:
        this(r37, r4, r39, r6, r7, r8, r9, r11, r13, r15, r16, r17, r18, r22, r2, r24, r26, r27, r28, r29, r30, r32, r33, r34, r35);
        return;
    L94:
        r35 = r69;
        goto L95
    L89:
        r34 = r68;
        goto L91
    L85:
        r33 = r67;
        goto L87
    L81:
        r32 = r66;
        goto L83
    L77:
        r30 = r64;
        goto L79
    L73:
        r29 = r63;
        goto L75
    L69:
        r28 = r62;
        goto L71
    L65:
        r27 = r61;
        goto L67
    L61:
        r26 = r60;
        goto L63
    L57:
        r24 = r58;
        goto L59
    L49:
        r22 = r54;
        goto L51
    L45:
        r18 = r52;
        goto L47
    L41:
        r17 = r51;
        goto L43
    L37:
        r16 = r50;
        goto L39
    L33:
        r15 = r49;
        goto L35
    L29:
        r13 = r47;
        goto L31
    L25:
        r11 = r45;
        goto L27
    L21:
        r9 = r43;
        goto L23
    L17:
        r8 = r42;
        goto L18
    L13:
        r7 = r41;
        goto L15
    L9:
        r6 = r40;
        goto L11
    L5:
        r4 = r38;
        goto L7
    }

    public WorkSpec(String r37, WorkSpec r38) {
        String r3 = r38.workerClassName;
        this(r37, r38.state, r3, r38.inputMergerClassName, new Data(r38.input), new Data(r38.output), r38.initialDelay, r38.intervalDuration, r38.flexDuration, new Constraints(r38.constraints), r38.runAttemptCount, r38.backoffPolicy, r38.backoffDelayDuration, r38.lastEnqueueTime, r38.minimumRetentionDuration, r38.scheduleRequestedAt, r38.expedited, r38.outOfQuotaPolicy, r38.periodCount, 0, r38.nextScheduleTimeOverride, r38.nextScheduleTimeOverrideGeneration, r38.stopReason, r38.traceTag, r38.backOffOnSystemInterruptions, 524288, null);
    }

    public WorkSpec(String r37, String r38) {
        this(r37, null, r38, null, null, null, 0, 0, 0, null, 0, null, 0, 0, 0, 0, false, null, 0, 0, 0, 0, 0, null, null, 33554426, null);
    }

    public static /* synthetic */ WorkSpec copy$default(WorkSpec r17, String r18, WorkInfo$State r19, String r20, String r21, Data r22, Data r23, long r24, long r26, long r28, Constraints r30, int r31, BackoffPolicy r32, long r33, long r35, long r37, long r39, boolean r41, OutOfQuotaPolicy r42, int r43, int r44, long r45, int r47, int r48, String r49, Boolean r50, int r51, Object r52) {
        if ((r51 & 1) == 0) goto L5;
        String r2 = r17.id;
    L7:
        if ((r51 & 2) == 0) goto L9;
        WorkInfo$State r3 = r17.state;
    L11:
        if ((r51 & 4) == 0) goto L13;
        String r4 = r17.workerClassName;
    L15:
        if ((r51 & 8) == 0) goto L17;
        String r5 = r17.inputMergerClassName;
    L19:
        if ((r51 & 16) == 0) goto L21;
        Data r6 = r17.input;
    L23:
        if ((r51 & 32) == 0) goto L25;
        Data r7 = r17.output;
    L27:
        if ((r51 & 64) == 0) goto L29;
        long r8 = r17.initialDelay;
    L31:
        if ((r51 & 128) == 0) goto L33;
        long r10 = r17.intervalDuration;
    L35:
        if ((r51 & 256) == 0) goto L37;
        long r12 = r17.flexDuration;
    L39:
        if ((r51 & 512) == 0) goto L41;
        Constraints r14 = r17.constraints;
    L43:
        if ((r51 & 1024) == 0) goto L45;
        int r15 = r17.runAttemptCount;
    L46:
        String r182 = r2;
        if ((r51 & 2048) == 0) goto L49;
        BackoffPolicy r25 = r17.backoffPolicy;
    L50:
        BackoffPolicy r192 = r25;
        WorkInfo$State r522 = r3;
        if ((r51 & 4096) == 0) goto L53;
        long r27 = r17.backoffDelayDuration;
    L54:
        long r202 = r27;
        if ((r51 & 8192) == 0) goto L57;
        long r29 = r17.lastEnqueueTime;
    L58:
        long r222 = r29;
        if ((r51 & 16384) == 0) goto L61;
        long r210 = r17.minimumRetentionDuration;
    L62:
        long r242 = r210;
        if ((r51 & 32768) == 0) goto L65;
        long r1 = r17.scheduleRequestedAt;
    L67:
        if ((r51 & 65536) == 0) goto L69;
        boolean r34 = r17.expedited;
    L70:
        long r262 = r1;
        if ((r51 & 131072) == 0) goto L73;
        OutOfQuotaPolicy r13 = r17.outOfQuotaPolicy;
    L75:
        if ((r51 & 262144) == 0) goto L77;
        int r211 = r17.periodCount;
    L78:
        OutOfQuotaPolicy r282 = r13;
        if ((r51 & 524288) == 0) goto L81;
        int r16 = r17.generation;
    L82:
        int r302 = r16;
        int r292 = r211;
        if ((r51 & 1048576) == 0) goto L85;
        long r110 = r17.nextScheduleTimeOverride;
    L86:
        long r312 = r110;
        if ((r51 & TLObject.FLAG_21) == 0) goto L89;
        int r111 = r17.nextScheduleTimeOverrideGeneration;
    L91:
        if ((r51 & 4194304) == 0) goto L93;
        int r212 = r17.stopReason;
    L94:
        int r332 = r111;
        if ((r51 & 8388608) == 0) goto L97;
        String r112 = r17.traceTag;
    L99:
        if ((r51 & 16777216) == 0) goto L102;
        String r342 = r112;
        String r502 = r342;
        Boolean r512 = r17.backOffOnSystemInterruptions;
        long r36 = r222;
        long r38 = r242;
        long r40 = r262;
        OutOfQuotaPolicy r432 = r282;
        int r442 = r292;
        int r452 = r302;
        long r46 = r312;
        int r482 = r332;
        int r492 = r212;
        boolean r422 = r34;
        String r223 = r5;
        Data r232 = r6;
        Data r243 = r7;
        long r252 = r8;
        long r272 = r10;
        long r293 = r12;
        Constraints r313 = r14;
        int r322 = r15;
        BackoffPolicy r333 = r192;
        long r343 = r202;
        WorkInfo$State r203 = r522;
        String r213 = r4;
    L104:
        return r17.copy(r182, r203, r213, r223, r232, r243, r252, r272, r293, r313, r322, r333, r343, r36, r38, r40, r422, r432, r442, r452, r46, r482, r492, r502, r512);
    L102:
        r512 = r50;
        r502 = r112;
        r343 = r202;
        r36 = r222;
        r38 = r242;
        r40 = r262;
        r432 = r282;
        r442 = r292;
        r452 = r302;
        r46 = r312;
        r482 = r332;
        r203 = r522;
        r492 = r212;
        r422 = r34;
        r213 = r4;
        r223 = r5;
        r232 = r6;
        r243 = r7;
        r252 = r8;
        r272 = r10;
        r293 = r12;
        r313 = r14;
        r322 = r15;
        r333 = r192;
        goto L104
    L97:
        r112 = r49;
        goto L99
    L93:
        r212 = r48;
        goto L94
    L89:
        r111 = r47;
        goto L91
    L85:
        r110 = r45;
        goto L86
    L81:
        r16 = r44;
        goto L82
    L77:
        r211 = r43;
        goto L78
    L73:
        r13 = r42;
        goto L75
    L69:
        r34 = r41;
        goto L70
    L65:
        r1 = r39;
        goto L67
    L61:
        r210 = r37;
        goto L62
    L57:
        r29 = r35;
        goto L58
    L53:
        r27 = r33;
        goto L54
    L49:
        r25 = r32;
        goto L50
    L45:
        r15 = r31;
        goto L46
    L41:
        r14 = r30;
        goto L43
    L37:
        r12 = r28;
        goto L39
    L33:
        r10 = r26;
        goto L35
    L29:
        r8 = r24;
        goto L31
    L25:
        r7 = r23;
        goto L27
    L21:
        r6 = r22;
        goto L23
    L17:
        r5 = r21;
        goto L19
    L13:
        r4 = r20;
        goto L15
    L9:
        r3 = r19;
        goto L11
    L5:
        r2 = r18;
        goto L7
    }

    public final long calculateNextRunTime() {
        return Companion.calculateNextRunTime(isBackedOff(), this.runAttemptCount, this.backoffPolicy, this.backoffDelayDuration, this.lastEnqueueTime, this.periodCount, isPeriodic(), this.initialDelay, this.flexDuration, this.intervalDuration, this.nextScheduleTimeOverride);
    }

    public final WorkSpec copy(String r35, WorkInfo$State r36, String r37, String r38, Data r39, Data r40, long r41, long r43, long r45, Constraints r47, int r48, BackoffPolicy r49, long r50, long r52, long r54, long r56, boolean r58, OutOfQuotaPolicy r59, int r60, int r61, long r62, int r64, int r65, String r66, Boolean r67) {
        return new WorkSpec(r35, r36, r37, r38, r39, r40, r41, r43, r45, r47, r48, r49, r50, r52, r54, r56, r58, r59, r60, r61, r62, r64, r65, r66, r67);
    }

    public boolean equals(Object r8) {
        if (this != r8) goto L6;
        return true;
    L6:
        if ((r8 instanceof WorkSpec) == true) goto L8;
        return false;
    L8:
        WorkSpec r82 = (WorkSpec) r8;
        if (Intrinsics.areEqual(this.id, r82.id) == true) goto L12;
        return false;
    L12:
        if (this.state == r82.state) goto L15;
        return false;
    L15:
        if (Intrinsics.areEqual(this.workerClassName, r82.workerClassName) == true) goto L18;
        return false;
    L18:
        if (Intrinsics.areEqual(this.inputMergerClassName, r82.inputMergerClassName) == true) goto L21;
        return false;
    L21:
        if (Intrinsics.areEqual(this.input, r82.input) == true) goto L24;
        return false;
    L24:
        if (Intrinsics.areEqual(this.output, r82.output) == true) goto L27;
        return false;
    L27:
        if (this.initialDelay == r82.initialDelay) goto L30;
        return false;
    L30:
        if (this.intervalDuration == r82.intervalDuration) goto L33;
        return false;
    L33:
        if (this.flexDuration == r82.flexDuration) goto L36;
        return false;
    L36:
        if (Intrinsics.areEqual(this.constraints, r82.constraints) == true) goto L39;
        return false;
    L39:
        if (this.runAttemptCount == r82.runAttemptCount) goto L42;
        return false;
    L42:
        if (this.backoffPolicy == r82.backoffPolicy) goto L45;
        return false;
    L45:
        if (this.backoffDelayDuration == r82.backoffDelayDuration) goto L48;
        return false;
    L48:
        if (this.lastEnqueueTime == r82.lastEnqueueTime) goto L51;
        return false;
    L51:
        if (this.minimumRetentionDuration == r82.minimumRetentionDuration) goto L54;
        return false;
    L54:
        if (this.scheduleRequestedAt == r82.scheduleRequestedAt) goto L57;
        return false;
    L57:
        if (this.expedited == r82.expedited) goto L60;
        return false;
    L60:
        if (this.outOfQuotaPolicy == r82.outOfQuotaPolicy) goto L63;
        return false;
    L63:
        if (this.periodCount == r82.periodCount) goto L66;
        return false;
    L66:
        if (this.generation == r82.generation) goto L69;
        return false;
    L69:
        if (this.nextScheduleTimeOverride == r82.nextScheduleTimeOverride) goto L72;
        return false;
    L72:
        if (this.nextScheduleTimeOverrideGeneration == r82.nextScheduleTimeOverrideGeneration) goto L75;
        return false;
    L75:
        if (this.stopReason == r82.stopReason) goto L78;
        return false;
    L78:
        if (Intrinsics.areEqual(this.traceTag, r82.traceTag) == true) goto L81;
        return false;
    L81:
        if (Intrinsics.areEqual(this.backOffOnSystemInterruptions, r82.backOffOnSystemInterruptions) == true) goto L83;
        return false;
    L83:
        return true;
    }

    public final Boolean getBackOffOnSystemInterruptions() {
        return this.backOffOnSystemInterruptions;
    }

    public final int getGeneration() {
        return this.generation;
    }

    public final long getNextScheduleTimeOverride() {
        return this.nextScheduleTimeOverride;
    }

    public final int getNextScheduleTimeOverrideGeneration() {
        return this.nextScheduleTimeOverrideGeneration;
    }

    public final int getPeriodCount() {
        return this.periodCount;
    }

    public final int getStopReason() {
        return this.stopReason;
    }

    public final String getTraceTag() {
        return this.traceTag;
    }

    public final boolean hasConstraints() {
        return !Intrinsics.areEqual(Constraints.NONE, this.constraints);
    }

    public int hashCode() {
        int r0 = ((((((((((((((((((((((((((((((((((((((((((((this.id.hashCode() * 31) + this.state.hashCode()) * 31) + this.workerClassName.hashCode()) * 31) + this.inputMergerClassName.hashCode()) * 31) + this.input.hashCode()) * 31) + this.output.hashCode()) * 31) + Long.hashCode(this.initialDelay)) * 31) + Long.hashCode(this.intervalDuration)) * 31) + Long.hashCode(this.flexDuration)) * 31) + this.constraints.hashCode()) * 31) + Integer.hashCode(this.runAttemptCount)) * 31) + this.backoffPolicy.hashCode()) * 31) + Long.hashCode(this.backoffDelayDuration)) * 31) + Long.hashCode(this.lastEnqueueTime)) * 31) + Long.hashCode(this.minimumRetentionDuration)) * 31) + Long.hashCode(this.scheduleRequestedAt)) * 31) + Boolean.hashCode(this.expedited)) * 31) + this.outOfQuotaPolicy.hashCode()) * 31) + Integer.hashCode(this.periodCount)) * 31) + Integer.hashCode(this.generation)) * 31) + Long.hashCode(this.nextScheduleTimeOverride)) * 31) + Integer.hashCode(this.nextScheduleTimeOverrideGeneration)) * 31) + Integer.hashCode(this.stopReason)) * 31;
        String r1 = this.traceTag;
        int r2 = 0;
        if (r1 != null) goto L5;
        int r12 = 0;
    L6:
        int r02 = (r0 + r12) * 31;
        Boolean r3 = this.backOffOnSystemInterruptions;
        if (r3 == null) goto L11;
        r2 = r3.hashCode();
    L11:
        return r02 + r2;
    L5:
        r12 = r1.hashCode();
        goto L6
    }

    public final boolean isBackedOff() {
        if (this.state == WorkInfo$State.ENQUEUED) goto L5;
        return false;
    L5:
        if (this.runAttemptCount <= 0) goto L10;
        return true;
    L10:
        return false;
    }

    public final boolean isPeriodic() {
        if (this.intervalDuration == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final void setBackoffDelayDuration(long r10) {
        if (r10 <= 18000000) goto L6;
        Logger.get().warning(TAG, "Backoff delay duration exceeds maximum value");
    L6:
        if (r10 >= NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS) goto L8;
        Logger.get().warning(TAG, "Backoff delay duration less than minimum value");
    L8:
        this.backoffDelayDuration = RangesKt.coerceIn(r10, NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS, 18000000);
    }

    public final void setNextScheduleTimeOverride(long r1) {
        this.nextScheduleTimeOverride = r1;
    }

    public final void setNextScheduleTimeOverrideGeneration(int r1) {
        this.nextScheduleTimeOverrideGeneration = r1;
    }

    public final void setPeriodic(long r6) {
        if (r6 >= 900000) goto L5;
        Logger.get().warning(TAG, "Interval duration lesser than minimum allowed value; Changed to 900000");
    L5:
        setPeriodic(RangesKt.coerceAtLeast(r6, 900000), RangesKt.coerceAtLeast(r6, 900000));
    }

    public final void setPeriodic(long r9, long r11) {
        if (r9 >= 900000) goto L5;
        Logger.get().warning(TAG, "Interval duration lesser than minimum allowed value; Changed to 900000");
    L5:
        this.intervalDuration = RangesKt.coerceAtLeast(r9, 900000);
        if (r11 >= 300000) goto L9;
        Logger.get().warning(TAG, "Flex duration lesser than minimum allowed value; Changed to 300000");
    L9:
        if (r11 <= this.intervalDuration) goto L11;
        Logger.get().warning(TAG, "Flex duration greater than interval duration; Changed to " + r9);
    L11:
        this.flexDuration = RangesKt.coerceIn(r11, 300000, this.intervalDuration);
    }

    public final void setTraceTag(String r1) {
        this.traceTag = r1;
    }

    public String toString() {
        return "{WorkSpec: " + this.id + '}';
    }
}
