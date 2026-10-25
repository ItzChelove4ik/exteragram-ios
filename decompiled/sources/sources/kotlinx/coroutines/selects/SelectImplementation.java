package kotlinx.coroutines.selects;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.time.DurationKt$$ExternalSyntheticBUOutline0;
import kotlin.time.DurationKt$$ExternalSyntheticBUOutline1;
import kotlinx.coroutines.CancelHandler;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.Waiter;
import kotlinx.coroutines.internal.Segment;
import okhttp3.OkHttpClient$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;
import okio.ByteString$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0011\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u00022\b\u0012\u0004\u0012\u00028\u00000\u00032\b\u0012\u0004\u0012\u00028\u00000\u0004:\u0001LB\u000f\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00028\u0000H\u0082@¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\fH\u0082@¢\u0006\u0004\b\u000f\u0010\nJ\u0017\u0010\u0010\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0010\u0010\u000eJ!\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\u00042\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004H\u0002¢\u0006\u0004\b\u0013\u0010\u0014J#\u0010\u0016\u001a\u000e\u0018\u00010\u0015R\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u000b\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0018\u001a\u00028\u0000H\u0082@¢\u0006\u0004\b\u0018\u0010\nJ!\u0010\u001a\u001a\u00020\f2\u0010\u0010\u0019\u001a\f0\u0015R\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u001c\u001a\u00028\u0000H\u0091@¢\u0006\u0004\b\u001c\u0010\nJ2\u0010!\u001a\u00020\f*\u00020\u001d2\u001c\u0010 \u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001f\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u001eH\u0096\u0002¢\u0006\u0004\b!\u0010\"JD\u0010!\u001a\u00020\f\"\u0004\b\u0001\u0010#*\b\u0012\u0004\u0012\u00028\u00010$2\"\u0010 \u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001f\u0012\u0006\u0012\u0004\u0018\u00010\u00040%H\u0096\u0002¢\u0006\u0004\b!\u0010&J'\u0010)\u001a\u00020\f*\f0\u0015R\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010(\u001a\u00020'H\u0001¢\u0006\u0004\b)\u0010*J\u0017\u0010-\u001a\u00020\f2\u0006\u0010,\u001a\u00020+H\u0016¢\u0006\u0004\b-\u0010.J#\u00102\u001a\u00020\f2\n\u00100\u001a\u0006\u0012\u0002\b\u00030/2\u0006\u00101\u001a\u00020\u0012H\u0016¢\u0006\u0004\b2\u00103J\u0019\u00104\u001a\u00020\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004H\u0016¢\u0006\u0004\b4\u0010\u000eJ!\u00106\u001a\u00020'2\u0006\u0010\u000b\u001a\u00020\u00042\b\u00105\u001a\u0004\u0018\u00010\u0004H\u0016¢\u0006\u0004\b6\u00107J\u001f\u00109\u001a\u0002082\u0006\u0010\u000b\u001a\u00020\u00042\b\u00105\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b9\u0010:J\u0019\u0010!\u001a\u00020\f2\b\u0010<\u001a\u0004\u0018\u00010;H\u0016¢\u0006\u0004\b!\u0010=R\u001a\u0010\u0006\u001a\u00020\u00058\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0006\u0010>\u001a\u0004\b?\u0010@R(\u0010B\u001a\u0014\u0012\u000e\u0012\f0\u0015R\b\u0012\u0004\u0012\u00028\u00000\u0000\u0018\u00010A8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bB\u0010CR\u0018\u0010D\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bD\u0010ER\u0016\u0010F\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bF\u0010GR\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0011\u0010ER\u0014\u0010H\u001a\u00020'8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bH\u0010IR\u0011\u0010K\u001a\b\u0012\u0004\u0012\u00020\u00040J8\u0002X\u0082\u0004¨\u0006M"}, d2 = {"Lkotlinx/coroutines/selects/SelectImplementation;", "R", "Lkotlinx/coroutines/CancelHandler;", "Lkotlinx/coroutines/selects/SelectBuilder;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/coroutines/CoroutineContext;", "context", "<init>", "(Lkotlin/coroutines/CoroutineContext;)V", "doSelectSuspend", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clauseObject", _UrlKt.FRAGMENT_ENCODE_SET, "checkClauseObject", "(Ljava/lang/Object;)V", "waitUntilSelected", "reregisterClause", "internalResult", _UrlKt.FRAGMENT_ENCODE_SET, "trySelectInternal", "(Ljava/lang/Object;Ljava/lang/Object;)I", "Lkotlinx/coroutines/selects/SelectImplementation$ClauseData;", "findClause", "(Ljava/lang/Object;)Lkotlinx/coroutines/selects/SelectImplementation$ClauseData;", "complete", "selectedClause", "cleanup", "(Lkotlinx/coroutines/selects/SelectImplementation$ClauseData;)V", "doSelect", "Lkotlinx/coroutines/selects/SelectClause0;", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "block", "invoke", "(Lkotlinx/coroutines/selects/SelectClause0;Lkotlin/jvm/functions/Function1;)V", "Q", "Lkotlinx/coroutines/selects/SelectClause1;", "Lkotlin/Function2;", "(Lkotlinx/coroutines/selects/SelectClause1;Lkotlin/jvm/functions/Function2;)V", _UrlKt.FRAGMENT_ENCODE_SET, "reregister", "register", "(Lkotlinx/coroutines/selects/SelectImplementation$ClauseData;Z)V", "Lkotlinx/coroutines/DisposableHandle;", "disposableHandle", "disposeOnCompletion", "(Lkotlinx/coroutines/DisposableHandle;)V", "Lkotlinx/coroutines/internal/Segment;", "segment", "index", "invokeOnCancellation", "(Lkotlinx/coroutines/internal/Segment;I)V", "selectInRegistrationPhase", "result", "trySelect", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "Lkotlinx/coroutines/selects/TrySelectDetailedResult;", "trySelectDetailed", "(Ljava/lang/Object;Ljava/lang/Object;)Lkotlinx/coroutines/selects/TrySelectDetailedResult;", _UrlKt.FRAGMENT_ENCODE_SET, "cause", "(Ljava/lang/Throwable;)V", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", _UrlKt.FRAGMENT_ENCODE_SET, "clauses", "Ljava/util/List;", "disposableHandleOrSegment", "Ljava/lang/Object;", "indexInSegment", "I", "isSelected", "()Z", "Lkotlinx/atomicfu/AtomicRef;", "state", "ClauseData", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
@PublishedApi
@SourceDebugExtension({"SMAP\nSelect.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Select.kt\nkotlinx/coroutines/selects/SelectImplementation\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 CancellableContinuation.kt\nkotlinx/coroutines/CancellableContinuationKt\n+ 5 StackTraceRecovery.kt\nkotlinx/coroutines/internal/StackTraceRecoveryKt\n*L\n1#1,901:1\n1#2:902\n2746#3,3:903\n1869#3,2:915\n1869#3,2:922\n1869#3,2:924\n433#4,9:906\n442#4:917\n149#5,4:918\n*S KotlinDebug\n*F\n+ 1 Select.kt\nkotlinx/coroutines/selects/SelectImplementation\n*L\n526#1:903,3\n590#1:915,2\n746#1:922,2\n771#1:924,2\n566#1:906,9\n566#1:917\n731#1:918,4\n*E\n"})
/* loaded from: classes5.dex */
public class SelectImplementation<R> implements CancelHandler, SelectBuilder<R>, SelectInstance, Waiter {
    private static final /* synthetic */ AtomicReferenceFieldUpdater state$volatile$FU = null;
    private List<SelectImplementation<R>.ClauseData> clauses;
    private final CoroutineContext context;
    private Object disposableHandleOrSegment;
    private int indexInSegment;
    private Object internalResult;
    private volatile /* synthetic */ Object state$volatile;

    @Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\b\b\u0080\u0004\u0018\u00002\u00020\u0001BÆ\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012U\u0010\u0003\u001aQ\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0002\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030\u0007¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\b\u0012\u0015\u0012\u0013\u0018\u00010\u0001¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0004j\u0002`\u000b\u0012U\u0010\f\u001aQ\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0002\u0012\u0015\u0012\u0013\u0018\u00010\u0001¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0015\u0012\u0013\u0018\u00010\u0001¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\r\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004j\u0002`\u000e\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0001\u0012\u0006\u0010\u000f\u001a\u00020\u0001\u0012u\u0010\u0010\u001aq\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030\u0007¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\b\u0012\u0015\u0012\u0013\u0018\u00010\u0001¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0015\u0012\u0013\u0018\u00010\u0001¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0011\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\n0\u0004\u0018\u00010\u0004j\u0004\u0018\u0001`\u0014¢\u0006\u0004\b\u0015\u0010\u0016J\u0014\u0010\u001a\u001a\u00020\u001b2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u001cJ\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u00012\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001J\u0018\u0010\u001f\u001a\u00028\u00002\b\u0010 \u001a\u0004\u0018\u00010\u0001H\u0086@¢\u0006\u0002\u0010!J\u0006\u0010\"\u001a\u00020\nJ8\u0010#\u001a\u001e\u0012\u0004\u0012\u00020\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\n\u0018\u00010\u00042\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u00072\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001R\u0010\u0010\u0002\u001a\u00020\u00018\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R]\u0010\u0003\u001aQ\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0002\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030\u0007¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\b\u0012\u0015\u0012\u0013\u0018\u00010\u0001¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0004j\u0002`\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R]\u0010\f\u001aQ\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0002\u0012\u0015\u0012\u0013\u0018\u00010\u0001¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0015\u0012\u0013\u0018\u00010\u0001¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\r\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004j\u0002`\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u007f\u0010\u0010\u001aq\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030\u0007¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\b\u0012\u0015\u0012\u0013\u0018\u00010\u0001¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0015\u0012\u0013\u0018\u00010\u0001¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0011\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\n0\u0004\u0018\u00010\u0004j\u0004\u0018\u0001`\u00148\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\u0004\u0018\u00010\u00018\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0018\u001a\u00020\u00198\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lkotlinx/coroutines/selects/SelectImplementation$ClauseData;", _UrlKt.FRAGMENT_ENCODE_SET, "clauseObject", "regFunc", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "param", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/selects/RegistrationFunction;", "processResFunc", "clauseResult", "Lkotlinx/coroutines/selects/ProcessResultFunction;", "block", "onCancellationConstructor", "internalResult", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/coroutines/CoroutineContext;", "Lkotlinx/coroutines/selects/OnCancellationConstructor;", "<init>", "(Lkotlinx/coroutines/selects/SelectImplementation;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)V", "disposableHandleOrSegment", "indexInSegment", _UrlKt.FRAGMENT_ENCODE_SET, "tryRegisterAsWaiter", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/selects/SelectImplementation;", "processResult", "result", "invokeBlock", "argument", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "dispose", "createOnCancellationAction", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nSelect.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Select.kt\nkotlinx/coroutines/selects/SelectImplementation$ClauseData\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,901:1\n1#2:902\n*E\n"})
    public final class ClauseData {
        private final Object block;

        @JvmField
        public final Object clauseObject;

        @JvmField
        public Object disposableHandleOrSegment;

        @JvmField
        public int indexInSegment;

        @JvmField
        public final Function3<SelectInstance<?>, Object, Object, Function3<Throwable, Object, CoroutineContext, Unit>> onCancellationConstructor;
        private final Object param;
        private final Function3<Object, Object, Object, Object> processResFunc;
        private final Function3<Object, SelectInstance<?>, Object, Unit> regFunc;
        final /* synthetic */ SelectImplementation<R> this$0;

        /* JADX WARN: Multi-variable type inference failed */
        public ClauseData(SelectImplementation r1, Object r2, Function3<Object, ? super SelectInstance<?>, Object, Unit> r3, Function3<Object, Object, Object, ? extends Object> r4, Object r5, Object r6, Function3<? super SelectInstance<?>, Object, Object, ? extends Function3<? super Throwable, Object, ? super CoroutineContext, Unit>> r7) {
            this.this$0 = r1;
            this.clauseObject = r2;
            this.regFunc = r3;
            this.processResFunc = r4;
            this.param = r5;
            this.block = r6;
            this.onCancellationConstructor = r7;
            this.indexInSegment = -1;
        }

        public final Function3<Throwable, Object, CoroutineContext, Unit> createOnCancellationAction(SelectInstance<?> r2, Object r3) {
            Function3<SelectInstance<?>, Object, Object, Function3<Throwable, Object, CoroutineContext, Unit>> r0 = this.onCancellationConstructor;
            if (r0 != null) goto L5;
            return null;
        L5:
            return r0.invoke(r2, this.param, r3);
        }

        public final void dispose() {
            Object r0 = this.disposableHandleOrSegment;
            SelectImplementation<R> r1 = this.this$0;
            DisposableHandle r3 = null;
            if ((r0 instanceof Segment) == false) goto L7;
            ((Segment) r0).onCancellation(this.indexInSegment, null, r1.getContext());
            return;
        L7:
            if ((r0 instanceof DisposableHandle) == false) goto L9;
            r3 = (DisposableHandle) r0;
        L9:
            if (r3 == null) goto L12;
            r3.dispose();
            return;
        }

        public final Object invokeBlock(Object r3, Continuation<? super R> r4) {
            Object r0 = this.block;
            if (this.param != SelectKt.getPARAM_CLAUSE_0()) goto L7;
            return ((Function1) r0).invoke(r4);
        L7:
            return ((Function2) r0).invoke(r3, r4);
        }

        public final Object processResult(Object r3) {
            return this.processResFunc.invoke(this.clauseObject, this.param, r3);
        }

        public final boolean tryRegisterAsWaiter(SelectImplementation<R> r3) {
            this.regFunc.invoke(this.clauseObject, r3, this.param);
            if (SelectImplementation.access$getInternalResult$p(r3) != SelectKt.access$getNO_RESULT$p()) goto L6;
            return true;
        L6:
            return false;
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.selects.SelectImplementation", f = "Select.kt", i = {}, l = {450, 453}, m = "doSelectSuspend", n = {}, s = {}, v = 1)
    /* renamed from: kotlinx.coroutines.selects.SelectImplementation$doSelectSuspend$1, reason: invalid class name */
    public static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ SelectImplementation<R> this$0;

        public AnonymousClass1(SelectImplementation<R> r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return SelectImplementation.access$doSelectSuspend(this.this$0, this);
        }
    }

    static {
        state$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(SelectImplementation.class, Object.class, "state$volatile");
    }

    public SelectImplementation(CoroutineContext r2) {
        this.context = r2;
        this.state$volatile = SelectKt.access$getSTATE_REG$p();
        this.clauses = new ArrayList(2);
        this.indexInSegment = -1;
        this.internalResult = SelectKt.access$getNO_RESULT$p();
    }

    public static final /* synthetic */ Object access$doSelectSuspend(SelectImplementation r0, Continuation r1) {
        return r0.doSelectSuspend(r1);
    }

    public static final /* synthetic */ Object access$getInternalResult$p(SelectImplementation r0) {
        return r0.internalResult;
    }

    public static final /* synthetic */ AtomicReferenceFieldUpdater access$getState$volatile$FU() {
        return getState$volatile$FU();
    }

    public static final /* synthetic */ void access$reregisterClause(SelectImplementation r0, Object r1) {
        r0.reregisterClause(r1);
    }

    private final void checkClauseObject(Object r2) {
        List<SelectImplementation<R>.ClauseData> r1 = this.clauses;
        if ((r1 instanceof Collection) == true) goto L5;
    L7:
        Iterator<T> r12 = r1.iterator();
    L9:
        if (r12.hasNext() == false) goto L18;
        if (((ClauseData) r12.next()).clauseObject != r2) goto L9;
        OkHttpClient$$ExternalSyntheticBUOutline0.m("Cannot use select clauses on the same object: ", r2);
        return;
    L18:
        return;
    L5:
        if (r1.isEmpty() == false) goto L7;
    }

    private final void cleanup(SelectImplementation<R>.ClauseData r3) {
        List<SelectImplementation<R>.ClauseData> r0 = this.clauses;
        if (r0 != null) goto L5;
        return;
    L5:
        Iterator<T> r02 = r0.iterator();
    L7:
        if (r02.hasNext() == false) goto L11;
        SelectImplementation<R>.ClauseData r1 = (ClauseData) r02.next();
        if (r1 == r3) goto L7;
        r1.dispose();
        goto L7
    L11:
        getState$volatile$FU().set(this, SelectKt.access$getSTATE_COMPLETED$p());
        this.internalResult = SelectKt.access$getNO_RESULT$p();
        this.clauses = null;
    }

    private final Object complete(Continuation<? super R> r3) {
        SelectImplementation<R>.ClauseData r0 = (ClauseData) getState$volatile$FU().get(this);
        Object r1 = this.internalResult;
        cleanup(r0);
        return r0.invokeBlock(r0.processResult(r1), r3);
    }

    @PublishedApi
    public static /* synthetic */ <R> Object doSelect$suspendImpl(SelectImplementation<R> r1, Continuation<? super R> r2) {
        if (r1.isSelected() == false) goto L7;
        return r1.complete(r2);
    L7:
        return r1.doSelectSuspend(r2);
    }

    private final Object doSelectSuspend(Continuation<? super R> r6) {
        if ((r6 instanceof AnonymousClass1) == false) goto L7;
        AnonymousClass1 r0 = (AnonymousClass1) r6;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r62 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L17;
        if (r2 == 1) goto L16;
        if (r2 != 2) goto L14;
        ResultKt.throwOnFailure(r62);
        return r62;
    L14:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L16:
        ResultKt.throwOnFailure(r62);
    L20:
        r0.label = 2;
        Object r5 = complete(r0);
        if (r5 != r12) goto L23;
    L22:
        return r12;
    L23:
        return r5;
    L17:
        ResultKt.throwOnFailure(r62);
        r0.label = 1;
        if (waitUntilSelected(r0) != r12) goto L20;
    L7:
        r0 = new AnonymousClass1(this, r6);
        goto L8
    }

    private final SelectImplementation<R>.ClauseData findClause(Object r4) {
        List<SelectImplementation<R>.ClauseData> r3 = this.clauses;
        if (r3 != null) goto L5;
        return null;
    L5:
        Iterator<T> r32 = r3.iterator();
    L7:
        if (r32.hasNext() == false) goto L11;
        Object r1 = r32.next();
        if (((ClauseData) r1).clauseObject != r4) goto L7;
    L12:
        SelectImplementation<R>.ClauseData r12 = (ClauseData) r1;
        if (r12 == null) goto L15;
        return r12;
    L15:
        DurationKt$$ExternalSyntheticBUOutline1.m("Clause with object ", r4, " is not found");
        return null;
    L11:
        r1 = null;
        goto L12
    }

    private static final /* synthetic */ AtomicReferenceFieldUpdater getState$volatile$FU() {
        return state$volatile$FU;
    }

    private final boolean isSelected() {
        return getState$volatile$FU().get(this) instanceof ClauseData;
    }

    public static /* synthetic */ void register$default(SelectImplementation r0, ClauseData r1, boolean r2, int r3, Object r4) {
        if (r4 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: register");
        return;
    L4:
        if ((r3 & 1) == 0) goto L6;
        r2 = false;
    L6:
        r0.register(r1, r2);
    }

    private final void reregisterClause(Object r2) {
        SelectImplementation<R>.ClauseData r22 = findClause(r2);
        r22.disposableHandleOrSegment = null;
        r22.indexInSegment = -1;
        register(r22, true);
    }

    private final int trySelectInternal(Object r7, Object r8) {
    L2:
        Object r0 = getState$volatile$FU().get(this);
        if ((r0 instanceof CancellableContinuation) == true) goto L4;
        if (Intrinsics.areEqual(r0, SelectKt.access$getSTATE_COMPLETED$p()) == true) goto L34;
        if ((r0 instanceof ClauseData) == true) goto L53;
        if (Intrinsics.areEqual(r0, SelectKt.access$getSTATE_CANCELLED$p()) == true) goto L21;
        if (Intrinsics.areEqual(r0, SelectKt.access$getSTATE_REG$p()) == true) goto L25;
        if ((r0 instanceof List) == false) goto L32;
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(getState$volatile$FU(), this, r0, CollectionsKt.plus((Collection) r0, r7)) == false) goto L2;
        return 1;
    L32:
        DurationKt$$ExternalSyntheticBUOutline0.m("Unexpected state: ", r0);
        return 0;
    L25:
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(getState$volatile$FU(), this, r0, CollectionsKt.listOf(r7)) == false) goto L2;
        return 1;
    L21:
        return 2;
    L53:
        return 3;
    L34:
        return 3;
    L4:
        SelectImplementation<R>.ClauseData r1 = findClause(r7);
        if (r1 == null) goto L2;
        Function3<Throwable, Object, CoroutineContext, Unit> r4 = r1.createOnCancellationAction(this, r8);
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(getState$volatile$FU(), this, r0, r1) == false) goto L2;
        this.internalResult = r8;
        if (SelectKt.access$tryResume((CancellableContinuation) r0, r4) == false) goto L12;
        return 0;
    L12:
        this.internalResult = SelectKt.access$getNO_RESULT$p();
        return 2;
    }

    private final Object waitUntilSelected(Continuation<? super Unit> r6) {
        CancellableContinuationImpl r0 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(r6), 1);
        r0.initCancellability();
        AtomicReferenceFieldUpdater r1 = access$getState$volatile$FU();
    L3:
        Object r2 = r1.get(this);
        if (r2 == SelectKt.access$getSTATE_REG$p()) goto L6;
        if ((r2 instanceof List) == false) goto L17;
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(access$getState$volatile$FU(), this, r2, SelectKt.access$getSTATE_REG$p()) == false) goto L3;
        Iterator r22 = ((Iterable) r2).iterator();
    L14:
        if (r22.hasNext() == false) goto L3;
        access$reregisterClause(this, r22.next());
        goto L14
    L17:
        if ((r2 instanceof ClauseData) == false) goto L27;
        r0.resume(Unit.INSTANCE, ((ClauseData) r2).createOnCancellationAction(this, access$getInternalResult$p(this)));
    L19:
        Object r5 = r0.getResult();
        if (r5 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L23;
        DebugProbesKt.probeCoroutineSuspended(r6);
    L23:
        if (r5 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L26;
        return r5;
    L26:
        return Unit.INSTANCE;
    L27:
        DurationKt$$ExternalSyntheticBUOutline0.m("unexpected state: ", r2);
        return null;
    L6:
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(access$getState$volatile$FU(), this, r2, r0) == false) goto L3;
        CancellableContinuationKt.invokeOnCancellation(r0, this);
        goto L19
    }

    @Override // kotlinx.coroutines.selects.SelectInstance
    public void disposeOnCompletion(DisposableHandle r1) {
        this.disposableHandleOrSegment = r1;
    }

    @PublishedApi
    public Object doSelect(Continuation<? super R> r1) {
        return doSelect$suspendImpl(this, r1);
    }

    @Override // kotlinx.coroutines.selects.SelectInstance
    public CoroutineContext getContext() {
        return this.context;
    }

    @Override // kotlinx.coroutines.CancelHandler
    public void invoke(Throwable r3) {
        AtomicReferenceFieldUpdater r32 = getState$volatile$FU();
    L3:
        Object r0 = r32.get(this);
        if (r0 == SelectKt.access$getSTATE_COMPLETED$p()) goto L21;
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(r32, this, r0, SelectKt.access$getSTATE_CANCELLED$p()) == false) goto L3;
        List<SelectImplementation<R>.ClauseData> r33 = this.clauses;
        if (r33 != null) goto L11;
        return;
    L11:
        Iterator<T> r34 = r33.iterator();
    L13:
        if (r34.hasNext() == false) goto L15;
        ((ClauseData) r34.next()).dispose();
        goto L13
    L15:
        this.internalResult = SelectKt.access$getNO_RESULT$p();
        this.clauses = null;
        return;
    }

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public void invoke(SelectClause0 r9, Function1<? super Continuation<? super R>, ? extends Object> r10) {
        register$default(this, new ClauseData(this, r9.getClauseObject(), r9.getRegFunc(), r9.getProcessResFunc(), SelectKt.getPARAM_CLAUSE_0(), r10, r9.getOnCancellationConstructor()), false, 1, null);
    }

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public <Q> void invoke(SelectClause1<? extends Q> r9, Function2<? super Q, ? super Continuation<? super R>, ? extends Object> r10) {
        register$default(this, new ClauseData(this, r9.getClauseObject(), r9.getRegFunc(), r9.getProcessResFunc(), null, r10, r9.getOnCancellationConstructor()), false, 1, null);
    }

    @Override // kotlinx.coroutines.Waiter
    public void invokeOnCancellation(Segment<?> r1, int r2) {
        this.disposableHandleOrSegment = r1;
        this.indexInSegment = r2;
    }

    @JvmName(name = "register")
    public final void register(SelectImplementation<R>.ClauseData r2, boolean r3) {
        if ((getState$volatile$FU().get(this) instanceof ClauseData) == false) goto L5;
        return;
    L5:
        if (r3 == true) goto L8;
        checkClauseObject(r2.clauseObject);
    L8:
        if (r2.tryRegisterAsWaiter(this) == false) goto L13;
        if (r3 == true) goto L11;
        this.clauses.add(r2);
    L11:
        r2.disposableHandleOrSegment = this.disposableHandleOrSegment;
        r2.indexInSegment = this.indexInSegment;
        this.disposableHandleOrSegment = null;
        this.indexInSegment = -1;
        return;
    L13:
        getState$volatile$FU().set(this, r2);
    }

    @Override // kotlinx.coroutines.selects.SelectInstance
    public void selectInRegistrationPhase(Object r1) {
        this.internalResult = r1;
    }

    @Override // kotlinx.coroutines.selects.SelectInstance
    public boolean trySelect(Object r1, Object r2) {
        if (trySelectInternal(r1, r2) != 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final TrySelectDetailedResult trySelectDetailed(Object r1, Object r2) {
        return SelectKt.access$TrySelectDetailedResult(trySelectInternal(r1, r2));
    }
}
