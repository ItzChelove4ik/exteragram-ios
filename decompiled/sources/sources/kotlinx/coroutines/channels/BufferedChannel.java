package kotlinx.coroutines.channels;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.reflect.KFunction;
import kotlin.text.StringsKt;
import kotlin.time.DurationKt;
import kotlin.time.DurationKt$$ExternalSyntheticBUOutline0;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.Waiter;
import kotlinx.coroutines.internal.ConcurrentLinkedListKt;
import kotlinx.coroutines.internal.ConcurrentLinkedListNode;
import kotlinx.coroutines.internal.InlineList;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.SegmentOrClosed;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.UndeliveredElementException;
import kotlinx.coroutines.selects.SelectClause1;
import kotlinx.coroutines.selects.SelectClause1Impl;
import kotlinx.coroutines.selects.SelectImplementation;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.TrySelectDetailedResult;
import okhttp3.Response$Builder$$ExternalSyntheticBUOutline1;
import okhttp3.internal.url._UrlKt;
import okio.ByteString$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline1;
import okio.SegmentedByteString$$ExternalSyntheticBUOutline0;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000º\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b5\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u0002:\u0002Ü\u0001B3\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\"\b\u0002\u0010\b\u001a\u001c\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\u0007¢\u0006\u0004\b\t\u0010\nJ\u0018\u0010\f\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00028\u0000H\u0082@¢\u0006\u0004\b\f\u0010\rJ6\u0010\u0013\u001a\u00020\u00062\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e2\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00028\u00002\u0006\u0010\u0012\u001a\u00020\u0011H\u0082@¢\u0006\u0004\b\u0013\u0010\u0014J)\u0010\u0016\u001a\u00020\u0006*\u00020\u00152\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e2\u0006\u0010\u0010\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u0016\u0010\u0017J%\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00028\u00002\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u0018H\u0002¢\u0006\u0004\b\u001a\u0010\u001bJG\u0010 \u001a\u00020\u00032\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e2\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00028\u00002\u0006\u0010\u0012\u001a\u00020\u00112\b\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001f\u001a\u00020\u001eH\u0002¢\u0006\u0004\b \u0010!JG\u0010\"\u001a\u00020\u00032\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e2\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00028\u00002\u0006\u0010\u0012\u001a\u00020\u00112\b\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001f\u001a\u00020\u001eH\u0002¢\u0006\u0004\b\"\u0010!J\u0017\u0010$\u001a\u00020\u001e2\u0006\u0010#\u001a\u00020\u0011H\u0003¢\u0006\u0004\b$\u0010%J\u0017\u0010'\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020\u0011H\u0002¢\u0006\u0004\b'\u0010%J\u001b\u0010(\u001a\u00020\u001e*\u00020\u001c2\u0006\u0010\u000b\u001a\u00028\u0000H\u0002¢\u0006\u0004\b(\u0010)J.\u0010+\u001a\u00028\u00002\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e2\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010*\u001a\u00020\u0011H\u0082@¢\u0006\u0004\b+\u0010,J)\u0010-\u001a\u00020\u0006*\u00020\u00152\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e2\u0006\u0010\u0010\u001a\u00020\u0003H\u0002¢\u0006\u0004\b-\u0010\u0017J\u001d\u0010.\u001a\u00020\u00062\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u0018H\u0002¢\u0006\u0004\b.\u0010/J4\u00102\u001a\b\u0012\u0004\u0012\u00028\u0000002\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e2\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010*\u001a\u00020\u0011H\u0082@¢\u0006\u0004\b1\u0010,J#\u00103\u001a\u00020\u00062\u0012\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u0000000\u0018H\u0002¢\u0006\u0004\b3\u0010/J9\u00104\u001a\u0004\u0018\u00010\u001c2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e2\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010*\u001a\u00020\u00112\b\u0010\u001d\u001a\u0004\u0018\u00010\u001cH\u0002¢\u0006\u0004\b4\u00105J9\u00106\u001a\u0004\u0018\u00010\u001c2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e2\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010*\u001a\u00020\u00112\b\u0010\u001d\u001a\u0004\u0018\u00010\u001cH\u0002¢\u0006\u0004\b6\u00105J)\u00107\u001a\u00020\u001e*\u00020\u001c2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e2\u0006\u0010\u0010\u001a\u00020\u0003H\u0002¢\u0006\u0004\b7\u00108J\u000f\u00109\u001a\u00020\u0006H\u0002¢\u0006\u0004\b9\u0010:J-\u0010<\u001a\u00020\u001e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e2\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010;\u001a\u00020\u0011H\u0002¢\u0006\u0004\b<\u0010=J-\u0010>\u001a\u00020\u001e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e2\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010;\u001a\u00020\u0011H\u0002¢\u0006\u0004\b>\u0010=J\u0019\u0010@\u001a\u00020\u00062\b\b\u0002\u0010?\u001a\u00020\u0011H\u0002¢\u0006\u0004\b@\u0010AJ%\u0010E\u001a\u00020\u00062\n\u0010C\u001a\u0006\u0012\u0002\b\u00030B2\b\u0010D\u001a\u0004\u0018\u00010\u001cH\u0002¢\u0006\u0004\bE\u0010FJ\u001b\u0010G\u001a\u00020\u00062\n\u0010C\u001a\u0006\u0012\u0002\b\u00030BH\u0002¢\u0006\u0004\bG\u0010HJ%\u0010J\u001a\u0004\u0018\u00010\u001c2\b\u0010D\u001a\u0004\u0018\u00010\u001c2\b\u0010I\u001a\u0004\u0018\u00010\u001cH\u0002¢\u0006\u0004\bJ\u0010KJ\u000f\u0010L\u001a\u00020\u0006H\u0002¢\u0006\u0004\bL\u0010:J\u000f\u0010M\u001a\u00020\u0006H\u0002¢\u0006\u0004\bM\u0010:J\u000f\u0010N\u001a\u00020\u0006H\u0002¢\u0006\u0004\bN\u0010:J\u000f\u0010O\u001a\u00020\u0006H\u0002¢\u0006\u0004\bO\u0010:J\u000f\u0010P\u001a\u00020\u0006H\u0002¢\u0006\u0004\bP\u0010:J\u001d\u0010R\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e2\u0006\u0010Q\u001a\u00020\u0011H\u0002¢\u0006\u0004\bR\u0010SJ\u0017\u0010T\u001a\u00020\u00062\u0006\u0010Q\u001a\u00020\u0011H\u0002¢\u0006\u0004\bT\u0010AJ\u0015\u0010U\u001a\b\u0012\u0004\u0012\u00028\u00000\u000eH\u0002¢\u0006\u0004\bU\u0010VJ\u001d\u0010X\u001a\u00020\u00112\f\u0010W\u001a\b\u0012\u0004\u0012\u00028\u00000\u000eH\u0002¢\u0006\u0004\bX\u0010YJ\u001d\u0010Z\u001a\u00020\u00062\f\u0010W\u001a\b\u0012\u0004\u0012\u00028\u00000\u000eH\u0002¢\u0006\u0004\bZ\u0010[J%\u0010]\u001a\u00020\u00062\f\u0010W\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e2\u0006\u0010\\\u001a\u00020\u0011H\u0002¢\u0006\u0004\b]\u0010^J\u0013\u0010_\u001a\u00020\u0006*\u00020\u0015H\u0002¢\u0006\u0004\b_\u0010`J\u0013\u0010a\u001a\u00020\u0006*\u00020\u0015H\u0002¢\u0006\u0004\ba\u0010`J\u001b\u0010c\u001a\u00020\u0006*\u00020\u00152\u0006\u0010b\u001a\u00020\u001eH\u0002¢\u0006\u0004\bc\u0010dJ\u001f\u0010g\u001a\u00020\u001e2\u0006\u0010e\u001a\u00020\u00112\u0006\u0010f\u001a\u00020\u001eH\u0002¢\u0006\u0004\bg\u0010hJ-\u0010j\u001a\u00020\u001e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e2\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010i\u001a\u00020\u0011H\u0002¢\u0006\u0004\bj\u0010=J-\u0010m\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u000e2\u0006\u0010k\u001a\u00020\u00112\f\u0010l\u001a\b\u0012\u0004\u0012\u00028\u00000\u000eH\u0002¢\u0006\u0004\bm\u0010nJ-\u0010o\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u000e2\u0006\u0010k\u001a\u00020\u00112\f\u0010l\u001a\b\u0012\u0004\u0012\u00028\u00000\u000eH\u0002¢\u0006\u0004\bo\u0010nJ5\u0010q\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u000e2\u0006\u0010k\u001a\u00020\u00112\f\u0010l\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e2\u0006\u0010p\u001a\u00020\u0011H\u0002¢\u0006\u0004\bq\u0010rJ%\u0010s\u001a\u00020\u00062\u0006\u0010k\u001a\u00020\u00112\f\u0010l\u001a\b\u0012\u0004\u0012\u00028\u00000\u000eH\u0002¢\u0006\u0004\bs\u0010tJ\u0017\u0010v\u001a\u00020\u00062\u0006\u0010u\u001a\u00020\u0011H\u0002¢\u0006\u0004\bv\u0010AJ\u0017\u0010w\u001a\u00020\u00062\u0006\u0010u\u001a\u00020\u0011H\u0002¢\u0006\u0004\bw\u0010AJu\u0010\u007f\u001aM\u0012\u0013\u0012\u00110y¢\u0006\f\bz\u0012\b\b{\u0012\u0004\b\b(|\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u000000¢\u0006\f\bz\u0012\b\b{\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110}¢\u0006\f\bz\u0012\b\b{\u0012\u0004\b\b(~\u0012\u0004\u0012\u00020\u00060x*\u0018\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00028\u0000`\u0007H\u0002¢\u0006\u0005\b\u007f\u0010\u0080\u0001J0\u0010\u0083\u0001\u001a\u00020\u00062\u0006\u0010|\u001a\u00020y2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u0000002\u0006\u0010~\u001a\u00020}H\u0002¢\u0006\u0006\b\u0081\u0001\u0010\u0082\u0001JO\u0010\u0085\u0001\u001a\u001d\u0012\u0004\u0012\u00020y\u0012\u0006\u0012\u0004\u0018\u00010\u001c\u0012\u0004\u0012\u00020}\u0012\u0004\u0012\u00020\u00060\u0084\u0001*\u0018\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00028\u0000`\u00072\u0006\u0010\u000b\u001a\u00028\u0000H\u0002¢\u0006\u0006\b\u0085\u0001\u0010\u0086\u0001Jq\u0010\u0085\u0001\u001aG\u0012\u0013\u0012\u00110y¢\u0006\f\bz\u0012\b\b{\u0012\u0004\b\b(|\u0012\u0013\u0012\u00118\u0000¢\u0006\f\bz\u0012\b\b{\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110}¢\u0006\f\bz\u0012\b\b{\u0012\u0004\b\b(~\u0012\u0004\u0012\u00020\u00060x*\u0018\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00028\u0000`\u0007H\u0002¢\u0006\u0006\b\u0085\u0001\u0010\u0080\u0001J*\u0010\u0087\u0001\u001a\u00020\u00062\u0006\u0010|\u001a\u00020y2\u0006\u0010\u000b\u001a\u00028\u00002\u0006\u0010~\u001a\u00020}H\u0002¢\u0006\u0006\b\u0087\u0001\u0010\u0082\u0001J\u001a\u0010\u0088\u0001\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00028\u0000H\u0096@¢\u0006\u0005\b\u0088\u0001\u0010\rJ \u0010\u008b\u0001\u001a\b\u0012\u0004\u0012\u00020\u0006002\u0006\u0010\u000b\u001a\u00028\u0000H\u0016¢\u0006\u0006\b\u0089\u0001\u0010\u008a\u0001J\u0011\u0010\u008c\u0001\u001a\u00020\u0006H\u0014¢\u0006\u0005\b\u008c\u0001\u0010:J\u0011\u0010\u008d\u0001\u001a\u00020\u0006H\u0014¢\u0006\u0005\b\u008d\u0001\u0010:J\u0013\u0010\u008e\u0001\u001a\u00028\u0000H\u0096@¢\u0006\u0006\b\u008e\u0001\u0010\u008f\u0001J\u0019\u0010\u0091\u0001\u001a\b\u0012\u0004\u0012\u00028\u000000H\u0096@¢\u0006\u0006\b\u0090\u0001\u0010\u008f\u0001J\u0018\u0010\u0094\u0001\u001a\b\u0012\u0004\u0012\u00028\u000000H\u0016¢\u0006\u0006\b\u0092\u0001\u0010\u0093\u0001J\u001a\u0010\u0096\u0001\u001a\u00020\u00062\u0007\u0010\u0095\u0001\u001a\u00020\u0011H\u0004¢\u0006\u0005\b\u0096\u0001\u0010AJ\u0019\u0010\u0098\u0001\u001a\u00020\u00062\u0006\u0010i\u001a\u00020\u0011H\u0000¢\u0006\u0005\b\u0097\u0001\u0010AJ\u001a\u0010\u009a\u0001\u001a\t\u0012\u0004\u0012\u00028\u00000\u0099\u0001H\u0096\u0002¢\u0006\u0006\b\u009a\u0001\u0010\u009b\u0001J\u0011\u0010\u009c\u0001\u001a\u00020\u0006H\u0014¢\u0006\u0005\b\u009c\u0001\u0010:J\u001c\u0010\u009d\u0001\u001a\u00020\u001e2\b\u0010|\u001a\u0004\u0018\u00010yH\u0016¢\u0006\u0006\b\u009d\u0001\u0010\u009e\u0001J\"\u0010¡\u0001\u001a\u00020\u00062\u0010\u0010|\u001a\f\u0018\u00010\u009f\u0001j\u0005\u0018\u0001` \u0001¢\u0006\u0006\b¡\u0001\u0010¢\u0001J\u001c\u0010¤\u0001\u001a\u00020\u001e2\b\u0010|\u001a\u0004\u0018\u00010yH\u0010¢\u0006\u0006\b£\u0001\u0010\u009e\u0001J%\u0010¥\u0001\u001a\u00020\u001e2\b\u0010|\u001a\u0004\u0018\u00010y2\u0007\u0010¡\u0001\u001a\u00020\u001eH\u0014¢\u0006\u0006\b¥\u0001\u0010¦\u0001J8\u0010¨\u0001\u001a\u00020\u00062$\u0010§\u0001\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010y¢\u0006\f\bz\u0012\b\b{\u0012\u0004\b\b(|\u0012\u0004\u0012\u00020\u00060\u0005H\u0016¢\u0006\u0006\b¨\u0001\u0010©\u0001J\u0012\u0010¬\u0001\u001a\u00020\u001eH\u0000¢\u0006\u0006\bª\u0001\u0010«\u0001J\u0013\u0010®\u0001\u001a\u00030\u00ad\u0001H\u0016¢\u0006\u0006\b®\u0001\u0010¯\u0001R\u0015\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0004\u0010°\u0001R/\u0010\b\u001a\u001c\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\u00078\u0000X\u0081\u0004¢\u0006\u0007\n\u0005\b\b\u0010±\u0001R\u0092\u0001\u0010µ\u0001\u001av\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030B¢\u0006\f\bz\u0012\b\b{\u0012\u0004\b\b(C\u0012\u0016\u0012\u0014\u0018\u00010\u001c¢\u0006\r\bz\u0012\t\b{\u0012\u0005\b\b(²\u0001\u0012\u0016\u0012\u0014\u0018\u00010\u001c¢\u0006\r\bz\u0012\t\b{\u0012\u0005\b\b(³\u0001\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020y\u0012\u0006\u0012\u0004\u0018\u00010\u001c\u0012\u0004\u0012\u00020}\u0012\u0004\u0012\u00020\u00060\u0084\u0001\u0018\u00010\u0084\u0001j\u0005\u0018\u0001`´\u00018\u0002X\u0082\u0004¢\u0006\u000f\n\u0006\bµ\u0001\u0010¶\u0001\u0012\u0005\b·\u0001\u0010:R\u0017\u0010º\u0001\u001a\u00020\u00118BX\u0082\u0004¢\u0006\b\u001a\u0006\b¸\u0001\u0010¹\u0001R\u0017\u0010»\u0001\u001a\u00020\u001e8BX\u0082\u0004¢\u0006\b\u001a\u0006\b»\u0001\u0010«\u0001R\u0017\u0010¾\u0001\u001a\u00020y8BX\u0082\u0004¢\u0006\b\u001a\u0006\b¼\u0001\u0010½\u0001R\u001a\u0010¿\u0001\u001a\u00020\u001e*\u00020\u00118BX\u0082\u0004¢\u0006\u0007\u001a\u0005\b¿\u0001\u0010%R\u001a\u0010À\u0001\u001a\u00020\u001e*\u00020\u00118BX\u0082\u0004¢\u0006\u0007\u001a\u0005\bÀ\u0001\u0010%R\u0016\u0010\\\u001a\u00020\u00118@X\u0080\u0004¢\u0006\b\u001a\u0006\bÁ\u0001\u0010¹\u0001R\u0017\u0010Ã\u0001\u001a\u00020\u00118@X\u0080\u0004¢\u0006\b\u001a\u0006\bÂ\u0001\u0010¹\u0001R%\u0010È\u0001\u001a\t\u0012\u0004\u0012\u00028\u00000Ä\u00018VX\u0096\u0004¢\u0006\u000f\u0012\u0005\bÇ\u0001\u0010:\u001a\u0006\bÅ\u0001\u0010Æ\u0001R\u0019\u0010Ê\u0001\u001a\u0004\u0018\u00010y8DX\u0084\u0004¢\u0006\b\u001a\u0006\bÉ\u0001\u0010½\u0001R\u0017\u0010Ì\u0001\u001a\u00020y8DX\u0084\u0004¢\u0006\b\u001a\u0006\bË\u0001\u0010½\u0001R\u0017\u0010Í\u0001\u001a\u00020\u001e8TX\u0094\u0004¢\u0006\b\u001a\u0006\bÍ\u0001\u0010«\u0001R\u001e\u0010Î\u0001\u001a\u00020\u001e8VX\u0097\u0004¢\u0006\u000f\u0012\u0005\bÏ\u0001\u0010:\u001a\u0006\bÎ\u0001\u0010«\u0001R\u001c\u0010f\u001a\u00020\u001e8VX\u0097\u0004¢\u0006\u000e\u0012\u0005\bÐ\u0001\u0010:\u001a\u0005\bf\u0010«\u0001R\r\u0010Ò\u0001\u001a\u00030Ñ\u00018\u0002X\u0082\u0004R\r\u0010Ó\u0001\u001a\u00030Ñ\u00018\u0002X\u0082\u0004R\r\u0010Ô\u0001\u001a\u00030Ñ\u00018\u0002X\u0082\u0004R\r\u0010Õ\u0001\u001a\u00030Ñ\u00018\u0002X\u0082\u0004R\u0019\u0010×\u0001\u001a\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000e0Ö\u00018\u0002X\u0082\u0004R\u0019\u0010Ø\u0001\u001a\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000e0Ö\u00018\u0002X\u0082\u0004R\u0019\u0010Ù\u0001\u001a\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000e0Ö\u00018\u0002X\u0082\u0004R\u0015\u0010Ú\u0001\u001a\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u001c0Ö\u00018\u0002X\u0082\u0004R\u0015\u0010Û\u0001\u001a\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u001c0Ö\u00018\u0002X\u0082\u0004¨\u0006Ý\u0001"}, d2 = {"Lkotlinx/coroutines/channels/BufferedChannel;", "E", "Lkotlinx/coroutines/channels/Channel;", _UrlKt.FRAGMENT_ENCODE_SET, "capacity", "Lkotlin/Function1;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/internal/OnUndeliveredElement;", "onUndeliveredElement", "<init>", "(ILkotlin/jvm/functions/Function1;)V", "element", "onClosedSend", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/coroutines/channels/ChannelSegment;", "segment", "index", _UrlKt.FRAGMENT_ENCODE_SET, "s", "sendOnNoWaiterSuspend", "(Lkotlinx/coroutines/channels/ChannelSegment;ILjava/lang/Object;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/coroutines/Waiter;", "prepareSenderForSuspension", "(Lkotlinx/coroutines/Waiter;Lkotlinx/coroutines/channels/ChannelSegment;I)V", "Lkotlinx/coroutines/CancellableContinuation;", "cont", "onClosedSendOnNoWaiterSuspend", "(Ljava/lang/Object;Lkotlinx/coroutines/CancellableContinuation;)V", _UrlKt.FRAGMENT_ENCODE_SET, "waiter", _UrlKt.FRAGMENT_ENCODE_SET, "closed", "updateCellSend", "(Lkotlinx/coroutines/channels/ChannelSegment;ILjava/lang/Object;JLjava/lang/Object;Z)I", "updateCellSendSlow", "curSendersAndCloseStatus", "shouldSendSuspend", "(J)Z", "curSenders", "bufferOrRendezvousSend", "tryResumeReceiver", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "r", "receiveOnNoWaiterSuspend", "(Lkotlinx/coroutines/channels/ChannelSegment;IJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "prepareReceiverForSuspension", "onClosedReceiveOnNoWaiterSuspend", "(Lkotlinx/coroutines/CancellableContinuation;)V", "Lkotlinx/coroutines/channels/ChannelResult;", "receiveCatchingOnNoWaiterSuspend-GKJJFZk", "receiveCatchingOnNoWaiterSuspend", "onClosedReceiveCatchingOnNoWaiterSuspend", "updateCellReceive", "(Lkotlinx/coroutines/channels/ChannelSegment;IJLjava/lang/Object;)Ljava/lang/Object;", "updateCellReceiveSlow", "tryResumeSender", "(Ljava/lang/Object;Lkotlinx/coroutines/channels/ChannelSegment;I)Z", "expandBuffer", "()V", "b", "updateCellExpandBuffer", "(Lkotlinx/coroutines/channels/ChannelSegment;IJ)Z", "updateCellExpandBufferSlow", "nAttempts", "incCompletedExpandBufferAttempts", "(J)V", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "ignoredParam", "registerSelectForReceive", "(Lkotlinx/coroutines/selects/SelectInstance;Ljava/lang/Object;)V", "onClosedSelectOnReceive", "(Lkotlinx/coroutines/selects/SelectInstance;)V", "selectResult", "processResultSelectReceive", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "invokeCloseHandler", "markClosed", "markCancelled", "markCancellationStarted", "completeCloseOrCancel", "sendersCur", "completeClose", "(J)Lkotlinx/coroutines/channels/ChannelSegment;", "completeCancel", "closeLinkedList", "()Lkotlinx/coroutines/channels/ChannelSegment;", "lastSegment", "markAllEmptyCellsAsClosed", "(Lkotlinx/coroutines/channels/ChannelSegment;)J", "removeUnprocessedElements", "(Lkotlinx/coroutines/channels/ChannelSegment;)V", "sendersCounter", "cancelSuspendedReceiveRequests", "(Lkotlinx/coroutines/channels/ChannelSegment;J)V", "resumeReceiverOnClosedChannel", "(Lkotlinx/coroutines/Waiter;)V", "resumeSenderOnCancelledChannel", "receiver", "resumeWaiterOnClosedChannel", "(Lkotlinx/coroutines/Waiter;Z)V", "sendersAndCloseStatusCur", "isClosedForReceive", "isClosed", "(JZ)Z", "globalIndex", "isCellNonEmpty", "id", "startFrom", "findSegmentSend", "(JLkotlinx/coroutines/channels/ChannelSegment;)Lkotlinx/coroutines/channels/ChannelSegment;", "findSegmentReceive", "currentBufferEndCounter", "findSegmentBufferEnd", "(JLkotlinx/coroutines/channels/ChannelSegment;J)Lkotlinx/coroutines/channels/ChannelSegment;", "moveSegmentBufferEndToSpecifiedOrLast", "(JLkotlinx/coroutines/channels/ChannelSegment;)V", "value", "updateSendersCounterIfLower", "updateReceiversCounterIfLower", "Lkotlin/reflect/KFunction3;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ParameterName;", "name", "cause", "Lkotlin/coroutines/CoroutineContext;", "context", "bindCancellationFunResult", "(Lkotlin/jvm/functions/Function1;)Lkotlin/reflect/KFunction;", "onCancellationChannelResultImplDoNotCall-5_sEAP8", "(Ljava/lang/Throwable;Ljava/lang/Object;Lkotlin/coroutines/CoroutineContext;)V", "onCancellationChannelResultImplDoNotCall", "Lkotlin/Function3;", "bindCancellationFun", "(Lkotlin/jvm/functions/Function1;Ljava/lang/Object;)Lkotlin/jvm/functions/Function3;", "onCancellationImplDoNotCall", "send", "trySend-JP2dKIU", "(Ljava/lang/Object;)Ljava/lang/Object;", "trySend", "onReceiveEnqueued", "onReceiveDequeued", "receive", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "receiveCatching-JP2dKIU", "receiveCatching", "tryReceive-PtdJZtk", "()Ljava/lang/Object;", "tryReceive", "globalCellIndex", "dropFirstElementUntilTheSpecifiedCellIsInTheBuffer", "waitExpandBufferCompletion$kotlinx_coroutines_core", "waitExpandBufferCompletion", "Lkotlinx/coroutines/channels/ChannelIterator;", "iterator", "()Lkotlinx/coroutines/channels/ChannelIterator;", "onClosedIdempotent", "close", "(Ljava/lang/Throwable;)Z", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "cancel", "(Ljava/util/concurrent/CancellationException;)V", "cancelImpl$kotlinx_coroutines_core", "cancelImpl", "closeOrCancelImpl", "(Ljava/lang/Throwable;Z)Z", "handler", "invokeOnClose", "(Lkotlin/jvm/functions/Function1;)V", "hasElements$kotlinx_coroutines_core", "()Z", "hasElements", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "I", "Lkotlin/jvm/functions/Function1;", "param", "internalResult", "Lkotlinx/coroutines/selects/OnCancellationConstructor;", "onUndeliveredElementReceiveCancellationConstructor", "Lkotlin/jvm/functions/Function3;", "getOnUndeliveredElementReceiveCancellationConstructor$annotations", "getBufferEndCounter", "()J", "bufferEndCounter", "isRendezvousOrUnlimited", "getReceiveException", "()Ljava/lang/Throwable;", "receiveException", "isClosedForSend0", "isClosedForReceive0", "getSendersCounter$kotlinx_coroutines_core", "getReceiversCounter$kotlinx_coroutines_core", "receiversCounter", "Lkotlinx/coroutines/selects/SelectClause1;", "getOnReceive", "()Lkotlinx/coroutines/selects/SelectClause1;", "getOnReceive$annotations", "onReceive", "getCloseCause", "closeCause", "getSendException", "sendException", "isConflatedDropOldest", "isClosedForSend", "isClosedForSend$annotations", "isClosedForReceive$annotations", "Lkotlinx/atomicfu/AtomicLong;", "sendersAndCloseStatus", "receivers", "bufferEnd", "completedExpandBuffersAndPauseFlag", "Lkotlinx/atomicfu/AtomicRef;", "sendSegment", "receiveSegment", "bufferEndSegment", "_closeCause", "closeHandler", "BufferedChannelIterator", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nBufferedChannel.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BufferedChannel.kt\nkotlinx/coroutines/channels/BufferedChannel\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 BufferedChannel.kt\nkotlinx/coroutines/channels/BufferedChannelKt\n+ 4 CancellableContinuation.kt\nkotlinx/coroutines/CancellableContinuationKt\n+ 5 DispatchedTask.kt\nkotlinx/coroutines/DispatchedTaskKt\n+ 6 StackTraceRecovery.kt\nkotlinx/coroutines/internal/StackTraceRecoveryKt\n+ 7 BufferedChannel.kt\nkotlinx/coroutines/channels/BufferedChannel$sendImpl$1\n+ 8 BufferedChannel.kt\nkotlinx/coroutines/channels/BufferedChannel$receiveImpl$1\n+ 9 InlineList.kt\nkotlinx/coroutines/internal/InlineList\n+ 10 ConcurrentLinkedList.kt\nkotlinx/coroutines/internal/ConcurrentLinkedListKt\n+ 11 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,3092:1\n270#1,6:3095\n277#1,68:3102\n371#1,20:3192\n241#1:3212\n266#1,10:3213\n277#1,48:3224\n392#1:3272\n331#1,14:3273\n396#1,3:3288\n241#1:3301\n266#1,10:3302\n277#1,68:3313\n241#1:3391\n266#1,10:3392\n277#1,68:3403\n241#1:3474\n266#1,10:3475\n277#1,68:3486\n883#1,52:3556\n961#1,9:3612\n855#1:3621\n879#1,33:3622\n971#1:3655\n913#1,14:3656\n932#1,3:3671\n976#1,6:3674\n883#1,52:3688\n961#1,9:3744\n855#1:3753\n879#1,33:3754\n971#1:3787\n913#1,14:3788\n932#1,3:3803\n976#1,6:3806\n855#1:3821\n879#1,48:3822\n932#1,3:3871\n855#1:3874\n879#1,48:3875\n932#1,3:3924\n241#1:3936\n266#1,10:3937\n277#1,68:3948\n855#1:4017\n879#1,48:4018\n932#1,3:4067\n1#2:3093\n3075#3:3094\n3075#3:3101\n3075#3:3223\n3075#3:3312\n3075#3:3402\n3075#3:3473\n3075#3:3485\n3075#3:3555\n3075#3:3820\n3075#3:3927\n3075#3:3928\n3089#3:3929\n3089#3:3930\n3088#3:3931\n3088#3:3932\n3088#3:3933\n3089#3:3934\n3088#3:3935\n3075#3:3947\n3076#3:4070\n3075#3:4071\n3075#3:4072\n3075#3:4073\n3076#3:4074\n3075#3:4075\n3076#3:4098\n3075#3:4099\n3075#3:4100\n3076#3:4101\n3075#3:4156\n3076#3:4157\n3076#3:4158\n3076#3:4176\n3076#3:4177\n433#4,9:3170\n442#4:3187\n452#4,4:3188\n456#4,8:3291\n433#4,9:3382\n442#4:3472\n452#4,4:3608\n456#4,8:3680\n452#4,4:3740\n456#4,8:3812\n204#5:3179\n205#5:3182\n204#5:3183\n205#5:3186\n57#6,2:3180\n57#6,2:3184\n57#6,2:3299\n266#7:3287\n266#7:3381\n266#7:3471\n266#7:3554\n266#7:4016\n879#8:3670\n879#8:3802\n879#8:3870\n879#8:3923\n879#8:4066\n33#9,11:4076\n33#9,11:4087\n68#10,3:4102\n41#10,9:4105\n68#10,3:4114\n41#10,9:4117\n41#10,9:4126\n68#10,3:4135\n41#10,9:4138\n41#10,9:4147\n774#11:4159\n865#11,2:4160\n2393#11,14:4162\n774#11:4178\n865#11,2:4179\n2393#11,14:4181\n774#11:4195\n865#11,2:4196\n2393#11,14:4198\n*S KotlinDebug\n*F\n+ 1 BufferedChannel.kt\nkotlinx/coroutines/channels/BufferedChannel\n*L\n110#1:3095,6\n110#1:3102,68\n151#1:3192,20\n151#1:3212\n151#1:3213,10\n151#1:3224,48\n151#1:3272\n151#1:3273,14\n151#1:3288,3\n191#1:3301\n191#1:3302,10\n191#1:3313,68\n222#1:3391\n222#1:3392,10\n222#1:3403,68\n388#1:3474\n388#1:3475,10\n388#1:3486,68\n664#1:3556,52\n693#1:3612,9\n693#1:3621\n693#1:3622,33\n693#1:3655\n693#1:3656,14\n693#1:3671,3\n693#1:3674,6\n729#1:3688,52\n745#1:3744,9\n745#1:3753\n745#1:3754,33\n745#1:3787\n745#1:3788,14\n745#1:3803,3\n745#1:3806,6\n778#1:3821\n778#1:3822,48\n778#1:3871,3\n968#1:3874\n968#1:3875,48\n968#1:3924,3\n1461#1:3936\n1461#1:3937,10\n1461#1:3948,68\n1509#1:4017\n1509#1:4018,48\n1509#1:4067,3\n67#1:3094\n110#1:3101\n151#1:3223\n191#1:3312\n222#1:3402\n275#1:3473\n388#1:3485\n603#1:3555\n768#1:3820\n1004#1:3927\n1053#1:3928\n1371#1:3929\n1373#1:3930\n1403#1:3931\n1413#1:3932\n1422#1:3933\n1423#1:3934\n1430#1:3935\n1461#1:3947\n1875#1:4070\n1877#1:4071\n1879#1:4072\n1892#1:4073\n1903#1:4074\n1904#1:4075\n2206#1:4098\n2219#1:4099\n2229#1:4100\n2232#1:4101\n2549#1:4156\n2551#1:4157\n2575#1:4158\n2637#1:4176\n2638#1:4177\n131#1:3170,9\n131#1:3187\n150#1:3188,4\n150#1:3291,8\n218#1:3382,9\n218#1:3472\n692#1:3608,4\n692#1:3680,8\n743#1:3740,4\n743#1:3812,8\n135#1:3179\n135#1:3182\n138#1:3183\n138#1:3186\n135#1:3180,2\n138#1:3184,2\n180#1:3299,2\n151#1:3287\n191#1:3381\n222#1:3471\n388#1:3554\n1461#1:4016\n693#1:3670\n745#1:3802\n778#1:3870\n968#1:3923\n1509#1:4066\n2108#1:4076,11\n2163#1:4087,11\n2371#1:4102,3\n2371#1:4105,9\n2426#1:4114,3\n2426#1:4117,9\n2446#1:4126,9\n2475#1:4135,3\n2475#1:4138,9\n2536#1:4147,9\n2584#1:4159\n2584#1:4160,2\n2585#1:4162,14\n2649#1:4178\n2649#1:4179,2\n2650#1:4181,14\n2690#1:4195\n2690#1:4196,2\n2691#1:4198,14\n*E\n"})
/* loaded from: classes.dex */
public class BufferedChannel<E> implements Channel<E> {
    private static final /* synthetic */ AtomicReferenceFieldUpdater _closeCause$volatile$FU = null;
    private static final /* synthetic */ AtomicLongFieldUpdater bufferEnd$volatile$FU = null;
    private static final /* synthetic */ AtomicReferenceFieldUpdater bufferEndSegment$volatile$FU = null;
    private static final /* synthetic */ AtomicReferenceFieldUpdater closeHandler$volatile$FU = null;
    private static final /* synthetic */ AtomicLongFieldUpdater completedExpandBuffersAndPauseFlag$volatile$FU = null;
    private static final /* synthetic */ AtomicReferenceFieldUpdater receiveSegment$volatile$FU = null;
    private static final /* synthetic */ AtomicLongFieldUpdater receivers$volatile$FU = null;
    private static final /* synthetic */ AtomicReferenceFieldUpdater sendSegment$volatile$FU = null;
    private static final /* synthetic */ AtomicLongFieldUpdater sendersAndCloseStatus$volatile$FU = null;
    private volatile /* synthetic */ Object _closeCause$volatile;
    private volatile /* synthetic */ long bufferEnd$volatile;
    private volatile /* synthetic */ Object bufferEndSegment$volatile;
    private final int capacity;
    private volatile /* synthetic */ Object closeHandler$volatile;
    private volatile /* synthetic */ long completedExpandBuffersAndPauseFlag$volatile;

    @JvmField
    public final Function1<E, Unit> onUndeliveredElement;
    private final Function3<SelectInstance<?>, Object, Object, Function3<Throwable, Object, CoroutineContext, Unit>> onUndeliveredElementReceiveCancellationConstructor;
    private volatile /* synthetic */ Object receiveSegment$volatile;
    private volatile /* synthetic */ long receivers$volatile;
    private volatile /* synthetic */ Object sendSegment$volatile;
    private volatile /* synthetic */ long sendersAndCloseStatus$volatile;

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u000e\u0010\n\u001a\u00020\tH\u0096B¢\u0006\u0002\u0010\u000bJ\b\u0010\f\u001a\u00020\tH\u0002J,\u0010\r\u001a\u00020\t2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0082@¢\u0006\u0002\u0010\u0014J\u001c\u0010\u0015\u001a\u00020\u00162\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u00172\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0018\u001a\u00020\u0016H\u0002J\u000e\u0010\u0019\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u001aJ\u0013\u0010\u001b\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00028\u0000¢\u0006\u0002\u0010\u001dJ\u0006\u0010\u001e\u001a\u00020\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lkotlinx/coroutines/channels/BufferedChannel$BufferedChannelIterator;", "Lkotlinx/coroutines/channels/ChannelIterator;", "Lkotlinx/coroutines/Waiter;", "<init>", "(Lkotlinx/coroutines/channels/BufferedChannel;)V", "receiveResult", _UrlKt.FRAGMENT_ENCODE_SET, "continuation", "Lkotlinx/coroutines/CancellableContinuationImpl;", _UrlKt.FRAGMENT_ENCODE_SET, "hasNext", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onClosedHasNext", "hasNextOnNoWaiterSuspend", "segment", "Lkotlinx/coroutines/channels/ChannelSegment;", "index", _UrlKt.FRAGMENT_ENCODE_SET, "r", _UrlKt.FRAGMENT_ENCODE_SET, "(Lkotlinx/coroutines/channels/ChannelSegment;IJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "invokeOnCancellation", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/internal/Segment;", "onClosedHasNextNoWaiterSuspend", "next", "()Ljava/lang/Object;", "tryResumeHasNext", "element", "(Ljava/lang/Object;)Z", "tryResumeHasNextOnClosedChannel", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nBufferedChannel.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BufferedChannel.kt\nkotlinx/coroutines/channels/BufferedChannel$BufferedChannelIterator\n+ 2 BufferedChannel.kt\nkotlinx/coroutines/channels/BufferedChannel\n+ 3 CancellableContinuation.kt\nkotlinx/coroutines/CancellableContinuationKt\n+ 4 BufferedChannel.kt\nkotlinx/coroutines/channels/BufferedChannel$receiveImpl$1\n+ 5 StackTraceRecovery.kt\nkotlinx/coroutines/internal/StackTraceRecoveryKt\n+ 6 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,3092:1\n883#2,52:3093\n961#2,9:3149\n855#2:3158\n879#2,33:3159\n971#2:3192\n913#2,14:3193\n932#2,3:3208\n976#2,6:3211\n452#3,4:3145\n456#3,8:3217\n879#4:3207\n57#5,2:3225\n57#5,2:3228\n1#6:3227\n*S KotlinDebug\n*F\n+ 1 BufferedChannel.kt\nkotlinx/coroutines/channels/BufferedChannel$BufferedChannelIterator\n*L\n1596#1:3093,52\n1634#1:3149,9\n1634#1:3158\n1634#1:3159,33\n1634#1:3192\n1634#1:3193,14\n1634#1:3208,3\n1634#1:3211,6\n1632#1:3145,4\n1632#1:3217,8\n1634#1:3207\n1670#1:3225,2\n1718#1:3228,2\n*E\n"})
    public final class BufferedChannelIterator implements ChannelIterator<E>, Waiter {
        private CancellableContinuationImpl<? super Boolean> continuation;
        private Object receiveResult;
        final /* synthetic */ BufferedChannel<E> this$0;

        public BufferedChannelIterator(BufferedChannel r1) {
            this.this$0 = r1;
            this.receiveResult = BufferedChannelKt.access$getNO_RECEIVE_RESULT$p();
        }

        public static final /* synthetic */ void access$onClosedHasNextNoWaiterSuspend(BufferedChannelIterator r0) {
            r0.onClosedHasNextNoWaiterSuspend();
        }

        public static final /* synthetic */ void access$setContinuation$p(BufferedChannelIterator r0, CancellableContinuationImpl r1) {
            r0.continuation = r1;
        }

        public static final /* synthetic */ void access$setReceiveResult$p(BufferedChannelIterator r0, Object r1) {
            r0.receiveResult = r1;
        }

        private final Object hasNextOnNoWaiterSuspend(ChannelSegment<E> r10, int r11, long r12, Continuation<? super Boolean> r14) {
            BufferedChannel<E> r0 = this.this$0;
            CancellableContinuationImpl r6 = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(r14));
            access$setContinuation$p(this, r6);     // Catch: Throwable -> L6
            Object r9 = BufferedChannel.access$updateCellReceive(r0, r10, r11, r12, this);     // Catch: Throwable -> L6
            if (r9 != BufferedChannelKt.access$getSUSPEND$p()) goto L8;
            BufferedChannel.access$prepareReceiverForSuspension(r0, this, r10, r11);     // Catch: Throwable -> L6
        L45:
            Object r92 = r6.getResult();
            if (r92 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L48;
            DebugProbesKt.probeCoroutineSuspended(r14);
        L48:
            return r92;
        L8:
            Function3 r122 = null;
            if (r9 == BufferedChannelKt.access$getFAILED$p()) goto L11;
            r10.cleanPrev();     // Catch: Throwable -> L6
            access$setReceiveResult$p(this, r9);     // Catch: Throwable -> L6
            access$setContinuation$p(this, null);     // Catch: Throwable -> L6
            Boolean r102 = Boxing.boxBoolean(true);     // Catch: Throwable -> L6
            Function1<E, Unit> r112 = r0.onUndeliveredElement;     // Catch: Throwable -> L6
            if (r112 != null) goto L38;
        L44:
            r6.resume(r102, r122);     // Catch: Throwable -> L6
        L38:
            r122 = BufferedChannel.access$bindCancellationFun(r0, r112, r9);     // Catch: Throwable -> L6
            goto L44
        L11:
            if (r12 >= r0.getSendersCounter$kotlinx_coroutines_core()) goto L13;
            r10.cleanPrev();     // Catch: Throwable -> L6
        L13:
            ChannelSegment r93 = (ChannelSegment) BufferedChannel.access$getReceiveSegment$volatile$FU().get(r0);     // Catch: Throwable -> L6
        L15:
            if (r0.isClosedForReceive() == true) goto L16;
            long r3 = BufferedChannel.access$getReceivers$volatile$FU().getAndIncrement(r0);     // Catch: Throwable -> L6
            int r103 = BufferedChannelKt.SEGMENT_SIZE;     // Catch: Throwable -> L6
            long r1 = r3 / r103;     // Catch: Throwable -> L6
            int r104 = (int) (r3 % r103);     // Catch: Throwable -> L6
            if (r93.id == r1) goto L24;
            ChannelSegment r13 = BufferedChannel.access$findSegmentReceive(r0, r1, r93);     // Catch: Throwable -> L6
            if (r13 == null) goto L15;
            ChannelSegment r15 = r13;
        L25:
            r9 = BufferedChannel.access$updateCellReceive(r0, r15, r104, r3, this);     // Catch: Throwable -> L6
            ChannelSegment r132 = r15;
            if (r9 == BufferedChannelKt.access$getSUSPEND$p()) goto L27;
            if (r9 != BufferedChannelKt.access$getFAILED$p()) goto L35;
            if (r3 >= r0.getSendersCounter$kotlinx_coroutines_core()) goto L33;
            r132.cleanPrev();     // Catch: Throwable -> L6
        L33:
            r93 = r132;
            goto L15
        L35:
            if (r9 == BufferedChannelKt.access$getSUSPEND_NO_WAITER$p()) goto L40;
            r132.cleanPrev();     // Catch: Throwable -> L6
            access$setReceiveResult$p(this, r9);     // Catch: Throwable -> L6
            access$setContinuation$p(this, null);     // Catch: Throwable -> L6
            r102 = Boxing.boxBoolean(true);     // Catch: Throwable -> L6
            r112 = r0.onUndeliveredElement;     // Catch: Throwable -> L6
            if (r112 == null) goto L44;
        L40:
            throw new IllegalStateException("unexpected");     // Catch: Throwable -> L6
        L27:
            BufferedChannel.access$prepareReceiverForSuspension(r0, this, r132, r104);     // Catch: Throwable -> L6
            goto L45
        L24:
            r15 = r93;
            goto L25
        L16:
            access$onClosedHasNextNoWaiterSuspend(this);     // Catch: Throwable -> L6
        L6:
            th = move-exception;
            r6.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
            throw th;
        }

        private final boolean onClosedHasNext() {
            this.receiveResult = BufferedChannelKt.getCHANNEL_CLOSED();
            Throwable r1 = this.this$0.getCloseCause();
            if (r1 != null) goto L7;
            return false;
        L7:
            throw StackTraceRecoveryKt.recoverStackTrace(r1);
        }

        private final void onClosedHasNextNoWaiterSuspend() {
            CancellableContinuationImpl<? super Boolean> r0 = this.continuation;
            this.continuation = null;
            this.receiveResult = BufferedChannelKt.getCHANNEL_CLOSED();
            Throwable r2 = this.this$0.getCloseCause();
            if (r2 != null) goto L6;
            Result.Companion r22 = Result.Companion;
            r0.resumeWith(Result.m2018constructorimpl(Boolean.FALSE));
            return;
        L6:
            Result.Companion r1 = Result.Companion;
            r0.resumeWith(Result.m2018constructorimpl(ResultKt.createFailure(r2)));
        }

        @Override // kotlinx.coroutines.channels.ChannelIterator
        public Object hasNext(Continuation<? super Boolean> r11) {
            boolean r2 = true;
            if (this.receiveResult != BufferedChannelKt.access$getNO_RECEIVE_RESULT$p()) goto L5;
        L7:
            BufferedChannel<E> r3 = this.this$0;
            ChannelSegment<E> r0 = (ChannelSegment) BufferedChannel.access$getReceiveSegment$volatile$FU().get(r3);
        L9:
            if (r3.isClosedForReceive() == true) goto L10;
            long r6 = BufferedChannel.access$getReceivers$volatile$FU().getAndIncrement(r3);
            int r1 = BufferedChannelKt.SEGMENT_SIZE;
            long r4 = r6 / r1;
            int r12 = (int) (r6 % r1);
            if (r0.id == r4) goto L16;
            ChannelSegment<E> r42 = BufferedChannel.access$findSegmentReceive(r3, r4, r0);
            if (r42 == null) goto L9;
        L17:
            Object r02 = BufferedChannel.access$updateCellReceive(r3, r42, r12, r6, null);
            if (r02 == BufferedChannelKt.access$getSUSPEND$p()) goto L32;
            if (r02 != BufferedChannelKt.access$getFAILED$p()) goto L26;
            if (r6 >= r3.getSendersCounter$kotlinx_coroutines_core()) goto L24;
            r42.cleanPrev();
        L24:
            r0 = r42;
            goto L9
        L26:
            if (r02 == BufferedChannelKt.access$getSUSPEND_NO_WAITER$p()) goto L28;
            r42.cleanPrev();
            this.receiveResult = r02;
        L31:
            return Boxing.boxBoolean(r2);
        L28:
            return hasNextOnNoWaiterSuspend(r42, r12, r6, r11);
        L32:
            Segment$$ExternalSyntheticBUOutline1.m("unreachable");
            return null;
        L16:
            r42 = r0;
            goto L17
        L10:
            r2 = onClosedHasNext();
            goto L31
        L5:
            if (this.receiveResult == BufferedChannelKt.getCHANNEL_CLOSED()) goto L7;
            goto L7
        }

        @Override // kotlinx.coroutines.Waiter
        public void invokeOnCancellation(Segment<?> r1, int r2) {
            CancellableContinuationImpl<? super Boolean> r0 = this.continuation;
            if (r0 == null) goto L6;
            r0.invokeOnCancellation(r1, r2);
            return;
        }

        @Override // kotlinx.coroutines.channels.ChannelIterator
        public E next() {
            E r0 = (E) this.receiveResult;
            if (r0 == BufferedChannelKt.access$getNO_RECEIVE_RESULT$p()) goto L9;
            this.receiveResult = BufferedChannelKt.access$getNO_RECEIVE_RESULT$p();
            if (r0 == BufferedChannelKt.getCHANNEL_CLOSED()) goto L8;
            return r0;
        L8:
            throw StackTraceRecoveryKt.recoverStackTrace(BufferedChannel.access$getReceiveException(this.this$0));
        L9:
            Segment$$ExternalSyntheticBUOutline1.m("`hasNext()` has not been invoked");
            return null;
        }

        public final boolean tryResumeHasNext(E r5) {
            CancellableContinuationImpl<? super Boolean> r0 = this.continuation;
            Function3 r1 = null;
            this.continuation = null;
            this.receiveResult = r5;
            Boolean r2 = Boolean.TRUE;
            BufferedChannel<E> r4 = this.this$0;
            Function1<E, Unit> r3 = r4.onUndeliveredElement;
            if (r3 == null) goto L6;
            r1 = BufferedChannel.access$bindCancellationFun(r4, r3, r5);
        L6:
            return BufferedChannelKt.access$tryResume0(r0, r2, r1);
        }

        public final void tryResumeHasNextOnClosedChannel() {
            CancellableContinuationImpl<? super Boolean> r0 = this.continuation;
            this.continuation = null;
            this.receiveResult = BufferedChannelKt.getCHANNEL_CLOSED();
            Throwable r2 = this.this$0.getCloseCause();
            if (r2 != null) goto L6;
            Result.Companion r22 = Result.Companion;
            r0.resumeWith(Result.m2018constructorimpl(Boolean.FALSE));
            return;
        L6:
            Result.Companion r1 = Result.Companion;
            r0.resumeWith(Result.m2018constructorimpl(ResultKt.createFailure(r2)));
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    /* renamed from: kotlinx.coroutines.channels.BufferedChannel$bindCancellationFun$2, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static final /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function3<Throwable, E, CoroutineContext, Unit> {
        public AnonymousClass2(Object r8) {
            super(3, r8, BufferedChannel.class, "onCancellationImplDoNotCall", "onCancellationImplDoNotCall(Ljava/lang/Throwable;Ljava/lang/Object;Lkotlin/coroutines/CoroutineContext;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(Throwable r1, Object r2, CoroutineContext r3) {
            invoke2(r1, r2, r3);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Throwable r1, E r2, CoroutineContext r3) {
            BufferedChannel.access$onCancellationImplDoNotCall((BufferedChannel) this.receiver, r1, r2, r3);
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    /* renamed from: kotlinx.coroutines.channels.BufferedChannel$bindCancellationFunResult$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function3<Throwable, ChannelResult<? extends E>, CoroutineContext, Unit> {
        public AnonymousClass1(Object r8) {
            super(3, r8, BufferedChannel.class, "onCancellationChannelResultImplDoNotCall", "onCancellationChannelResultImplDoNotCall-5_sEAP8(Ljava/lang/Throwable;Ljava/lang/Object;Lkotlin/coroutines/CoroutineContext;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(Throwable r1, Object r2, CoroutineContext r3) {
            m3533invoke5_sEAP8(r1, ((ChannelResult) r2).m3544unboximpl(), r3);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke-5_sEAP8, reason: not valid java name */
        public final void m3533invoke5_sEAP8(Throwable r1, Object r2, CoroutineContext r3) {
            BufferedChannel.m3525access$onCancellationChannelResultImplDoNotCall5_sEAP8((BufferedChannel) this.receiver, r1, r2, r3);
        }
    }

    public static Unit $r8$lambda$3LIDflb3aV6rxzAC9EUPHtqhyps(Function1 r0, Object r1, Throwable r2, Object r3, CoroutineContext r4) {
        OnUndeliveredElementKt.callUndeliveredElement(r0, r1, r4);
        return Unit.INSTANCE;
    }

    public static /* synthetic */ Function3 $r8$lambda$_Cj5yiGGpUJwtUmTsC7NBJ84egQ(BufferedChannel r0, SelectInstance r1, Object r2, Object r3) {
        return onUndeliveredElementReceiveCancellationConstructor$lambda$0$0(r0, r1, r2, r3);
    }

    /* renamed from: $r8$lambda$yCDUJIks9rZw5_-YDKmbWJBUDh4, reason: not valid java name */
    public static /* synthetic */ Unit m3524$r8$lambda$yCDUJIks9rZw5_YDKmbWJBUDh4(Object r0, BufferedChannel r1, SelectInstance r2, Throwable r3, Object r4, CoroutineContext r5) {
        return onUndeliveredElementReceiveCancellationConstructor$lambda$0$0$0(r0, r1, r2, r3, r4, r5);
    }

    static {
        sendersAndCloseStatus$volatile$FU = AtomicLongFieldUpdater.newUpdater(BufferedChannel.class, "sendersAndCloseStatus$volatile");
        receivers$volatile$FU = AtomicLongFieldUpdater.newUpdater(BufferedChannel.class, "receivers$volatile");
        bufferEnd$volatile$FU = AtomicLongFieldUpdater.newUpdater(BufferedChannel.class, "bufferEnd$volatile");
        completedExpandBuffersAndPauseFlag$volatile$FU = AtomicLongFieldUpdater.newUpdater(BufferedChannel.class, "completedExpandBuffersAndPauseFlag$volatile");
        sendSegment$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(BufferedChannel.class, Object.class, "sendSegment$volatile");
        receiveSegment$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(BufferedChannel.class, Object.class, "receiveSegment$volatile");
        bufferEndSegment$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(BufferedChannel.class, Object.class, "bufferEndSegment$volatile");
        _closeCause$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(BufferedChannel.class, Object.class, "_closeCause$volatile");
        closeHandler$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(BufferedChannel.class, Object.class, "closeHandler$volatile");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public BufferedChannel(int r9, Function1<? super E, Unit> r10) {
        this.capacity = r9;
        this.onUndeliveredElement = r10;
        if (r9 < 0) goto L13;
        this.bufferEnd$volatile = BufferedChannelKt.access$initialBufferEnd(r9);
        this.completedExpandBuffersAndPauseFlag$volatile = getBufferEndCounter();
        ChannelSegment r2 = new ChannelSegment(0, null, this, 3);
        this.sendSegment$volatile = r2;
        this.receiveSegment$volatile = r2;
        if (isRendezvousOrUnlimited() == false) goto L7;
        r2 = BufferedChannelKt.access$getNULL_SEGMENT$p();
    L7:
        this.bufferEndSegment$volatile = r2;
        if (r10 == 0) goto L10;
        BufferedChannel$$ExternalSyntheticLambda0 r8 = new BufferedChannel$$ExternalSyntheticLambda0(this);
    L11:
        this.onUndeliveredElementReceiveCancellationConstructor = r8;
        this._closeCause$volatile = BufferedChannelKt.access$getNO_CLOSE_CAUSE$p();
        return;
    L10:
        r8 = null;
        goto L11
    L13:
        SegmentedByteString$$ExternalSyntheticBUOutline0.m("Invalid channel capacity: ", r9, ", should be >=0");
        throw null;
    }

    public static final /* synthetic */ Function3 access$bindCancellationFun(BufferedChannel r0, Function1 r1, Object r2) {
        return r0.bindCancellationFun(r1, r2);
    }

    public static final /* synthetic */ KFunction access$bindCancellationFun(BufferedChannel r0, Function1 r1) {
        return r0.bindCancellationFun(r1);
    }

    public static final /* synthetic */ KFunction access$bindCancellationFunResult(BufferedChannel r0, Function1 r1) {
        return r0.bindCancellationFunResult(r1);
    }

    public static final /* synthetic */ ChannelSegment access$findSegmentReceive(BufferedChannel r0, long r1, ChannelSegment r3) {
        return r0.findSegmentReceive(r1, r3);
    }

    public static final /* synthetic */ ChannelSegment access$findSegmentSend(BufferedChannel r0, long r1, ChannelSegment r3) {
        return r0.findSegmentSend(r1, r3);
    }

    public static final /* synthetic */ Throwable access$getReceiveException(BufferedChannel r0) {
        return r0.getReceiveException();
    }

    public static final /* synthetic */ AtomicReferenceFieldUpdater access$getReceiveSegment$volatile$FU() {
        return getReceiveSegment$volatile$FU();
    }

    public static final /* synthetic */ AtomicLongFieldUpdater access$getReceivers$volatile$FU() {
        return getReceivers$volatile$FU();
    }

    public static final /* synthetic */ AtomicReferenceFieldUpdater access$getSendSegment$volatile$FU() {
        return getSendSegment$volatile$FU();
    }

    public static final /* synthetic */ AtomicLongFieldUpdater access$getSendersAndCloseStatus$volatile$FU() {
        return getSendersAndCloseStatus$volatile$FU();
    }

    public static final /* synthetic */ boolean access$isClosedForSend0(BufferedChannel r0, long r1) {
        return r0.isClosedForSend0(r1);
    }

    /* renamed from: access$onCancellationChannelResultImplDoNotCall-5_sEAP8, reason: not valid java name */
    public static final /* synthetic */ void m3525access$onCancellationChannelResultImplDoNotCall5_sEAP8(BufferedChannel r0, Throwable r1, Object r2, CoroutineContext r3) {
        r0.m3527onCancellationChannelResultImplDoNotCall5_sEAP8(r1, r2, r3);
    }

    public static final /* synthetic */ void access$onCancellationImplDoNotCall(BufferedChannel r0, Throwable r1, Object r2, CoroutineContext r3) {
        r0.onCancellationImplDoNotCall(r1, r2, r3);
    }

    public static final /* synthetic */ void access$onClosedReceiveCatchingOnNoWaiterSuspend(BufferedChannel r0, CancellableContinuation r1) {
        r0.onClosedReceiveCatchingOnNoWaiterSuspend(r1);
    }

    public static final /* synthetic */ void access$onClosedReceiveOnNoWaiterSuspend(BufferedChannel r0, CancellableContinuation r1) {
        r0.onClosedReceiveOnNoWaiterSuspend(r1);
    }

    public static final /* synthetic */ void access$onClosedSendOnNoWaiterSuspend(BufferedChannel r0, Object r1, CancellableContinuation r2) {
        r0.onClosedSendOnNoWaiterSuspend(r1, r2);
    }

    public static final /* synthetic */ void access$prepareReceiverForSuspension(BufferedChannel r0, Waiter r1, ChannelSegment r2, int r3) {
        r0.prepareReceiverForSuspension(r1, r2, r3);
    }

    public static final /* synthetic */ void access$prepareSenderForSuspension(BufferedChannel r0, Waiter r1, ChannelSegment r2, int r3) {
        r0.prepareSenderForSuspension(r1, r2, r3);
    }

    public static final /* synthetic */ Object access$processResultSelectReceive(BufferedChannel r0, Object r1, Object r2) {
        return r0.processResultSelectReceive(r1, r2);
    }

    /* renamed from: access$receiveCatchingOnNoWaiterSuspend-GKJJFZk, reason: not valid java name */
    public static final /* synthetic */ Object m3526access$receiveCatchingOnNoWaiterSuspendGKJJFZk(BufferedChannel r0, ChannelSegment r1, int r2, long r3, Continuation r5) {
        return r0.m3529receiveCatchingOnNoWaiterSuspendGKJJFZk(r1, r2, r3, r5);
    }

    public static final /* synthetic */ void access$registerSelectForReceive(BufferedChannel r0, SelectInstance r1, Object r2) {
        r0.registerSelectForReceive(r1, r2);
    }

    public static final /* synthetic */ Object access$updateCellReceive(BufferedChannel r0, ChannelSegment r1, int r2, long r3, Object r5) {
        return r0.updateCellReceive(r1, r2, r3, r5);
    }

    public static final /* synthetic */ int access$updateCellSend(BufferedChannel r0, ChannelSegment r1, int r2, Object r3, long r4, Object r6, boolean r7) {
        return r0.updateCellSend(r1, r2, r3, r4, r6, r7);
    }

    private final Function3<Throwable, Object, CoroutineContext, Unit> bindCancellationFun(Function1<? super E, Unit> r1, E r2) {
        return new BufferedChannel$$ExternalSyntheticLambda1(r1, r2);
    }

    private final KFunction<Unit> bindCancellationFun(Function1<? super E, Unit> r1) {
        return new AnonymousClass2(this);
    }

    private final KFunction<Unit> bindCancellationFunResult(Function1<? super E, Unit> r1) {
        return new AnonymousClass1(this);
    }

    private final boolean bufferOrRendezvousSend(long r5) {
        if (r5 >= getBufferEndCounter()) goto L5;
        return true;
    L5:
        if (r5 < (getReceiversCounter$kotlinx_coroutines_core() + this.capacity)) goto L11;
        return false;
    L11:
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void cancelSuspendedReceiveRequests(ChannelSegment<E> r9, long r10) {
        Object r0 = InlineList.m3555constructorimpl$default(null, 1, null);
    L4:
        if (r9 == null) goto L29;
        int r3 = BufferedChannelKt.SEGMENT_SIZE - 1;
    L6:
        if ((-1) >= r3) goto L28;
        if (((r9.id * BufferedChannelKt.SEGMENT_SIZE) + r3) < r10) goto L29;
    L9:
        Object r4 = r9.getState$kotlinx_coroutines_core(r3);
        if (r4 == null) goto L25;
        if (r4 == BufferedChannelKt.access$getIN_BUFFER$p()) goto L25;
        if ((r4 instanceof WaiterEB) == true) goto L17;
        if ((r4 instanceof Waiter) == false) goto L27;
        if (r9.casState$kotlinx_coroutines_core(r3, r4, BufferedChannelKt.getCHANNEL_CLOSED()) == false) goto L9;
        r0 = InlineList.m3556plusFjFbRPM(r0, r4);
        r9.onCancelledRequest(r3, true);
    L27:
        r3 = r3 - 1;
        goto L6
    L17:
        if (r9.casState$kotlinx_coroutines_core(r3, r4, BufferedChannelKt.getCHANNEL_CLOSED()) == false) goto L9;
        r0 = InlineList.m3556plusFjFbRPM(r0, ((WaiterEB) r4).waiter);
        r9.onCancelledRequest(r3, true);
    L25:
        if (r9.casState$kotlinx_coroutines_core(r3, r4, BufferedChannelKt.getCHANNEL_CLOSED()) == false) goto L9;
        r9.onSlotCleaned();
        goto L27
    L28:
        r9 = (ChannelSegment) r9.getPrev();
    L29:
        if (r0 != null) goto L31;
        return;
    L31:
        if ((r0 instanceof ArrayList) == true) goto L34;
        resumeReceiverOnClosedChannel((Waiter) r0);
        return;
    L34:
        ArrayList r02 = (ArrayList) r0;
        int r92 = r02.size() - 1;
    L35:
        if ((-1) >= r92) goto L58;
        resumeReceiverOnClosedChannel((Waiter) r02.get(r92));
        r92 = r92 - 1;
        goto L35
    }

    private final ChannelSegment<E> closeLinkedList() {
        Object r0 = getBufferEndSegment$volatile$FU().get(this);
        ChannelSegment r1 = (ChannelSegment) getSendSegment$volatile$FU().get(this);
        if (r1.id <= ((ChannelSegment) r0).id) goto L5;
        r0 = r1;
    L5:
        ChannelSegment r6 = (ChannelSegment) getReceiveSegment$volatile$FU().get(this);
        if (r6.id <= ((ChannelSegment) r0).id) goto L9;
        r0 = r6;
    L9:
        return (ChannelSegment) ConcurrentLinkedListKt.close((ConcurrentLinkedListNode) r0);
    }

    private final void completeCancel(long r1) {
        removeUnprocessedElements(completeClose(r1));
    }

    private final ChannelSegment<E> completeClose(long r6) {
        ChannelSegment<E> r0 = closeLinkedList();
        if (isConflatedDropOldest() == false) goto L7;
        long r1 = markAllEmptyCellsAsClosed(r0);
        if (r1 == (-1)) goto L7;
        dropFirstElementUntilTheSpecifiedCellIsInTheBuffer(r1);
    L7:
        cancelSuspendedReceiveRequests(r0, r6);
        return r0;
    }

    private final void completeCloseOrCancel() {
        isClosedForSend();
    }

    private final void expandBuffer() {
        if (isRendezvousOrUnlimited() == false) goto L5;
        return;
    L5:
        ChannelSegment<E> r4 = (ChannelSegment) getBufferEndSegment$volatile$FU().get(this);
    L6:
        long r5 = getBufferEnd$volatile$FU().getAndIncrement(this);
        int r0 = BufferedChannelKt.SEGMENT_SIZE;
        long r2 = r5 / r0;
        if (this.getSendersCounter$kotlinx_coroutines_core() <= r5) goto L9;
        if (r4.id == r2) goto L21;
        BufferedChannel<E> r1 = this;
        ChannelSegment<E> r13 = r1.findSegmentBufferEnd(r2, r4, r5);
        if (r13 == null) goto L19;
        r4 = r13;
    L23:
        if (r1.updateCellExpandBuffer(r4, (int) (r5 % r0), r5) == true) goto L24;
        incCompletedExpandBufferAttempts$default(r1, 0, 1, null);
        goto L19
    L24:
        incCompletedExpandBufferAttempts$default(r1, 0, 1, null);
        return;
    L19:
        BufferedChannel<E> this = r1;
        goto L6
    L21:
        r1 = this;
        goto L23
    L9:
        if (r4.id < r2) goto L11;
    L13:
        incCompletedExpandBufferAttempts$default(this, 0, 1, null);
        return;
    L11:
        if (r4.getNext() == 0) goto L13;
        this.moveSegmentBufferEndToSpecifiedOrLast(r2, r4);
        goto L13
    }

    private final ChannelSegment<E> findSegmentBufferEnd(long r15, ChannelSegment<E> r17, long r18) {
        AtomicReferenceFieldUpdater r0 = getBufferEndSegment$volatile$FU();
        Function2 r4 = (Function2) BufferedChannelKt.createSegmentFunction();
    L3:
        Object r6 = ConcurrentLinkedListKt.findSegmentInternal(r17, r15, r4);
        if (SegmentOrClosed.m3559isClosedimpl(r6) == true) goto L21;
        Segment r7 = SegmentOrClosed.m3558getSegmentimpl(r6);
    L6:
        Segment r8 = (Segment) r0.get(this);
        if (r8.id >= r7.id) goto L21;
        if (r7.tryIncPointers$kotlinx_coroutines_core() == false) goto L3;
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(r0, this, r8, r7) == true) goto L15;
        if (r7.decPointers$kotlinx_coroutines_core() == false) goto L6;
        r7.remove();
        goto L6
    L15:
        if (r8.decPointers$kotlinx_coroutines_core() == false) goto L21;
        r8.remove();
    L21:
        if (SegmentOrClosed.m3559isClosedimpl(r6) == false) goto L24;
        completeCloseOrCancel();
        moveSegmentBufferEndToSpecifiedOrLast(r15, r17);
        incCompletedExpandBufferAttempts$default(this, 0, 1, null);
        return null;
    L24:
        ChannelSegment<E> r62 = (ChannelSegment) SegmentOrClosed.m3558getSegmentimpl(r6);
        if (r62.id <= r15) goto L31;
        long r42 = r62.id;
        int r11 = BufferedChannelKt.SEGMENT_SIZE;
        if (getBufferEnd$volatile$FU().compareAndSet(this, r18 + 1, r42 * r11) == false) goto L29;
        incCompletedExpandBufferAttempts((r62.id * r11) - r18);
    L30:
        return null;
    L29:
        incCompletedExpandBufferAttempts$default(this, 0, 1, null);
        goto L30
    L31:
        return r62;
    }

    private final ChannelSegment<E> findSegmentReceive(long r10, ChannelSegment<E> r12) {
        AtomicReferenceFieldUpdater r0 = getReceiveSegment$volatile$FU();
        Function2 r1 = (Function2) BufferedChannelKt.createSegmentFunction();
    L3:
        Object r2 = ConcurrentLinkedListKt.findSegmentInternal(r12, r10, r1);
        if (SegmentOrClosed.m3559isClosedimpl(r2) == true) goto L21;
        Segment r3 = SegmentOrClosed.m3558getSegmentimpl(r2);
    L6:
        Segment r4 = (Segment) r0.get(this);
        if (r4.id >= r3.id) goto L21;
        if (r3.tryIncPointers$kotlinx_coroutines_core() == false) goto L3;
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(r0, this, r4, r3) == true) goto L15;
        if (r3.decPointers$kotlinx_coroutines_core() == false) goto L6;
        r3.remove();
        goto L6
    L15:
        if (r4.decPointers$kotlinx_coroutines_core() == false) goto L21;
        r4.remove();
    L21:
        if (SegmentOrClosed.m3559isClosedimpl(r2) == false) goto L26;
        completeCloseOrCancel();
        if ((r12.id * BufferedChannelKt.SEGMENT_SIZE) >= getSendersCounter$kotlinx_coroutines_core()) goto L25;
        r12.cleanPrev();
    L25:
        return null;
    L26:
        ChannelSegment<E> r122 = (ChannelSegment) SegmentOrClosed.m3558getSegmentimpl(r2);
        if (isRendezvousOrUnlimited() == false) goto L29;
    L43:
        long r22 = r122.id;
        if (r22 <= r10) goto L49;
        int r102 = BufferedChannelKt.SEGMENT_SIZE;
        updateReceiversCounterIfLower(r22 * r102);
        if ((r122.id * r102) >= getSendersCounter$kotlinx_coroutines_core()) goto L48;
        r122.cleanPrev();
    L48:
        return null;
    L49:
        return r122;
    L29:
        if (r10 > (getBufferEndCounter() / BufferedChannelKt.SEGMENT_SIZE)) goto L43;
        AtomicReferenceFieldUpdater r02 = getBufferEndSegment$volatile$FU();
    L31:
        Segment r23 = (Segment) r02.get(this);
        if (r23.id >= r122.id) goto L43;
        if (r122.tryIncPointers$kotlinx_coroutines_core() == false) goto L43;
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(r02, this, r23, r122) == true) goto L38;
        if (r122.decPointers$kotlinx_coroutines_core() == false) goto L31;
        r122.remove();
        goto L31
    L38:
        if (r23.decPointers$kotlinx_coroutines_core() == false) goto L43;
        r23.remove();
        goto L43
    }

    private final ChannelSegment<E> findSegmentSend(long r10, ChannelSegment<E> r12) {
        AtomicReferenceFieldUpdater r0 = getSendSegment$volatile$FU();
        Function2 r1 = (Function2) BufferedChannelKt.createSegmentFunction();
    L3:
        Object r2 = ConcurrentLinkedListKt.findSegmentInternal(r12, r10, r1);
        if (SegmentOrClosed.m3559isClosedimpl(r2) == true) goto L21;
        Segment r3 = SegmentOrClosed.m3558getSegmentimpl(r2);
    L6:
        Segment r4 = (Segment) r0.get(this);
        if (r4.id >= r3.id) goto L21;
        if (r3.tryIncPointers$kotlinx_coroutines_core() == false) goto L3;
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(r0, this, r4, r3) == true) goto L15;
        if (r3.decPointers$kotlinx_coroutines_core() == false) goto L6;
        r3.remove();
        goto L6
    L15:
        if (r4.decPointers$kotlinx_coroutines_core() == false) goto L21;
        r4.remove();
    L21:
        if (SegmentOrClosed.m3559isClosedimpl(r2) == false) goto L26;
        completeCloseOrCancel();
        if ((r12.id * BufferedChannelKt.SEGMENT_SIZE) >= getReceiversCounter$kotlinx_coroutines_core()) goto L25;
        r12.cleanPrev();
    L25:
        return null;
    L26:
        ChannelSegment<E> r122 = (ChannelSegment) SegmentOrClosed.m3558getSegmentimpl(r2);
        long r22 = r122.id;
        if (r22 <= r10) goto L32;
        int r102 = BufferedChannelKt.SEGMENT_SIZE;
        updateSendersCounterIfLower(r22 * r102);
        if ((r122.id * r102) >= getReceiversCounter$kotlinx_coroutines_core()) goto L31;
        r122.cleanPrev();
    L31:
        return null;
    L32:
        return r122;
    }

    private static final /* synthetic */ AtomicLongFieldUpdater getBufferEnd$volatile$FU() {
        return bufferEnd$volatile$FU;
    }

    private final long getBufferEndCounter() {
        return getBufferEnd$volatile$FU().get(this);
    }

    private static final /* synthetic */ AtomicReferenceFieldUpdater getBufferEndSegment$volatile$FU() {
        return bufferEndSegment$volatile$FU;
    }

    private static final /* synthetic */ AtomicReferenceFieldUpdater getCloseHandler$volatile$FU() {
        return closeHandler$volatile$FU;
    }

    private static final /* synthetic */ AtomicLongFieldUpdater getCompletedExpandBuffersAndPauseFlag$volatile$FU() {
        return completedExpandBuffersAndPauseFlag$volatile$FU;
    }

    private final Throwable getReceiveException() {
        Throwable r1 = getCloseCause();
        if (r1 == null) goto L5;
        return r1;
    L5:
        return new ClosedReceiveChannelException("Channel was closed");
    }

    private static final /* synthetic */ AtomicReferenceFieldUpdater getReceiveSegment$volatile$FU() {
        return receiveSegment$volatile$FU;
    }

    private static final /* synthetic */ AtomicLongFieldUpdater getReceivers$volatile$FU() {
        return receivers$volatile$FU;
    }

    private static final /* synthetic */ AtomicReferenceFieldUpdater getSendSegment$volatile$FU() {
        return sendSegment$volatile$FU;
    }

    private static final /* synthetic */ AtomicLongFieldUpdater getSendersAndCloseStatus$volatile$FU() {
        return sendersAndCloseStatus$volatile$FU;
    }

    private static final /* synthetic */ AtomicReferenceFieldUpdater get_closeCause$volatile$FU() {
        return _closeCause$volatile$FU;
    }

    private final void incCompletedExpandBufferAttempts(long r5) {
        if ((getCompletedExpandBuffersAndPauseFlag$volatile$FU().addAndGet(this, r5) & 4611686018427387904L) != 0) goto L5;
        return;
    L5:
        if ((getCompletedExpandBuffersAndPauseFlag$volatile$FU().get(this) & 4611686018427387904L) != 0) goto L5;
    }

    public static /* synthetic */ void incCompletedExpandBufferAttempts$default(BufferedChannel r0, long r1, int r3, Object r4) {
        if (r4 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: incCompletedExpandBufferAttempts");
        return;
    L4:
        if ((r3 & 1) == 0) goto L6;
        r1 = 1;
    L6:
        r0.incCompletedExpandBufferAttempts(r1);
    }

    private final void invokeCloseHandler() {
        AtomicReferenceFieldUpdater r0 = getCloseHandler$volatile$FU();
    L3:
        Object r1 = r0.get(this);
        if (r1 != null) goto L6;
        Symbol r2 = BufferedChannelKt.access$getCLOSE_HANDLER_CLOSED$p();
    L8:
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(r0, this, r1, r2) == false) goto L3;
        if (r1 != null) goto L11;
        return;
    L11:
        Function1 r02 = (Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(r1, 1);
        ((Function1) r1).invoke(getCloseCause());
        return;
    L6:
        r2 = BufferedChannelKt.access$getCLOSE_HANDLER_INVOKED$p();
        goto L8
    }

    private final boolean isCellNonEmpty(ChannelSegment<E> r4, int r5, long r6) {
    L2:
        Object r0 = r4.getState$kotlinx_coroutines_core(r5);
        if (r0 == null) goto L33;
        if (r0 == BufferedChannelKt.access$getIN_BUFFER$p()) goto L33;
        if (r0 != BufferedChannelKt.BUFFERED) goto L11;
        return true;
    L11:
        if (r0 != BufferedChannelKt.access$getINTERRUPTED_SEND$p()) goto L14;
        return false;
    L14:
        if (r0 != BufferedChannelKt.getCHANNEL_CLOSED()) goto L17;
        return false;
    L17:
        if (r0 != BufferedChannelKt.access$getDONE_RCV$p()) goto L20;
        return false;
    L20:
        if (r0 != BufferedChannelKt.access$getPOISONED$p()) goto L23;
        return false;
    L23:
        if (r0 != BufferedChannelKt.access$getRESUMING_BY_EB$p()) goto L26;
        return true;
    L26:
        if (r0 != BufferedChannelKt.access$getRESUMING_BY_RCV$p()) goto L29;
        return false;
    L29:
        if (r6 != getReceiversCounter$kotlinx_coroutines_core()) goto L31;
        return true;
    L31:
        return false;
    L33:
        if (r4.casState$kotlinx_coroutines_core(r5, r0, BufferedChannelKt.access$getPOISONED$p()) == false) goto L2;
        expandBuffer();
        return false;
    }

    private final boolean isClosed(long r7, boolean r9) {
        int r0 = (int) (r7 >> 60);
        if (r0 != 0) goto L5;
    L21:
        return false;
    L5:
        if (r0 == 1) goto L21;
        if (r0 != 2) goto L9;
        completeClose(r7 & 1152921504606846975L);
        if (r9 == true) goto L17;
        return true;
    L17:
        if (hasElements$kotlinx_coroutines_core() == true) goto L19;
        return true;
    L19:
        return false;
    L9:
        if (r0 != 3) goto L12;
        completeCancel(r7 & 1152921504606846975L);
        return true;
    L12:
        Response$Builder$$ExternalSyntheticBUOutline1.m("unexpected close status: ", r0);
        return false;
    }

    private final boolean isClosedForReceive0(long r2) {
        return isClosed(r2, true);
    }

    private final boolean isClosedForSend0(long r2) {
        return isClosed(r2, false);
    }

    private final boolean isRendezvousOrUnlimited() {
        long r0 = getBufferEndCounter();
        if (r0 != 0) goto L5;
        return true;
    L5:
        if (r0 == LongCompanionObject.MAX_VALUE) goto L11;
        return false;
    L11:
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final long markAllEmptyCellsAsClosed(ChannelSegment<E> r8) {
    L2:
        int r0 = BufferedChannelKt.SEGMENT_SIZE;
    L3:
        r0 = r0 - 1;
        if ((-1) >= r0) goto L20;
        long r3 = (r8.id * BufferedChannelKt.SEGMENT_SIZE) + r0;
        if (r3 < getReceiversCounter$kotlinx_coroutines_core()) goto L7;
    L8:
        Object r1 = r8.getState$kotlinx_coroutines_core(r0);
        if (r1 == null) goto L17;
        if (r1 == BufferedChannelKt.access$getIN_BUFFER$p()) goto L17;
        if (r1 != BufferedChannelKt.BUFFERED) goto L3;
        return r3;
    L17:
        if (r8.casState$kotlinx_coroutines_core(r0, r1, BufferedChannelKt.getCHANNEL_CLOSED()) == false) goto L8;
        r8.onSlotCleaned();
        goto L3
    L7:
        return -1;
    L20:
        r8 = (ChannelSegment) r8.getPrev();
        if (r8 != null) goto L2;
        return -1;
    }

    private final void markCancellationStarted() {
        AtomicLongFieldUpdater r0 = getSendersAndCloseStatus$volatile$FU();
    L3:
        long r2 = r0.get(this);
        if (((int) (r2 >> 60)) != 0) goto L9;
        BufferedChannel<E> r1 = this;
        if (r0.compareAndSet(r1, r2, BufferedChannelKt.access$constructSendersAndCloseStatus(1152921504606846975L & r2, 1)) == true) goto L12;
        BufferedChannel<E> this = r1;
        goto L3
    L12:
        return;
    }

    private final void markCancelled() {
        AtomicLongFieldUpdater r0 = getSendersAndCloseStatus$volatile$FU();
    L3:
        long r2 = r0.get(this);
        BufferedChannel<E> r1 = this;
        if (r0.compareAndSet(r1, r2, BufferedChannelKt.access$constructSendersAndCloseStatus(1152921504606846975L & r2, 3)) == true) goto L5;
        BufferedChannel<E> this = r1;
        goto L3
    }

    private final void markClosed() {
        AtomicLongFieldUpdater r0 = getSendersAndCloseStatus$volatile$FU();
    L3:
        long r2 = r0.get(this);
        int r1 = (int) (r2 >> 60);
        if (r1 != 0) goto L6;
        long r4 = BufferedChannelKt.access$constructSendersAndCloseStatus(1152921504606846975L & r2, 2);
    L9:
        BufferedChannel<E> r12 = this;
        if (r0.compareAndSet(r12, r2, r4) == true) goto L17;
        BufferedChannel<E> this = r12;
        goto L3
    L17:
        return;
    L6:
        if (r1 != 1) goto L13;
        r4 = BufferedChannelKt.access$constructSendersAndCloseStatus(1152921504606846975L & r2, 3);
        goto L9
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void moveSegmentBufferEndToSpecifiedOrLast(long r5, ChannelSegment<E> r7) {
    L3:
        if (r7.id >= r5) goto L9;
        ChannelSegment<E> r0 = (ChannelSegment) r7.getNext();
        if (r0 == null) goto L9;
        r7 = r0;
    L9:
        if (r7.isRemoved() == false) goto L14;
        ChannelSegment<E> r52 = (ChannelSegment) r7.getNext();
        if (r52 == null) goto L14;
        r7 = r52;
    L14:
        AtomicReferenceFieldUpdater r53 = getBufferEndSegment$volatile$FU();
    L15:
        Segment r6 = (Segment) r53.get(this);
        if (r6.id >= r7.id) goto L17;
        if (r7.tryIncPointers$kotlinx_coroutines_core() == false) goto L9;
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(r53, this, r6, r7) == true) goto L24;
        if (r7.decPointers$kotlinx_coroutines_core() == false) goto L15;
        r7.remove();
        goto L15
    L24:
        if (r6.decPointers$kotlinx_coroutines_core() == false) goto L45;
        r6.remove();
        return;
    L45:
        return;
    }

    /* renamed from: onCancellationChannelResultImplDoNotCall-5_sEAP8, reason: not valid java name */
    private final void m3527onCancellationChannelResultImplDoNotCall5_sEAP8(Throwable r1, Object r2, CoroutineContext r3) {
        OnUndeliveredElementKt.callUndeliveredElement(this.onUndeliveredElement, ChannelResult.m3538getOrNullimpl(r2), r3);
    }

    private final void onCancellationImplDoNotCall(Throwable r1, E r2, CoroutineContext r3) {
        OnUndeliveredElementKt.callUndeliveredElement(this.onUndeliveredElement, r2, r3);
    }

    private final void onClosedReceiveCatchingOnNoWaiterSuspend(CancellableContinuation<? super ChannelResult<? extends E>> r2) {
        Result.Companion r0 = Result.Companion;
        r2.resumeWith(Result.m2018constructorimpl(ChannelResult.m3534boximpl(ChannelResult.Companion.m3545closedJP2dKIU(getCloseCause()))));
    }

    private final void onClosedReceiveOnNoWaiterSuspend(CancellableContinuation<? super E> r2) {
        Result.Companion r0 = Result.Companion;
        r2.resumeWith(Result.m2018constructorimpl(ResultKt.createFailure(getReceiveException())));
    }

    private final void onClosedSelectOnReceive(SelectInstance<?> r1) {
        r1.selectInRegistrationPhase(BufferedChannelKt.getCHANNEL_CLOSED());
    }

    private final Object onClosedSend(E r5, Continuation<? super Unit> r6) {
        CancellableContinuationImpl r0 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(r6), 1);
        r0.initCancellability();
        Function1<E, Unit> r1 = this.onUndeliveredElement;
        if (r1 == null) goto L7;
        UndeliveredElementException r52 = OnUndeliveredElementKt.callUndeliveredElementCatchingException$default(r1, r5, null, 2, null);
        if (r52 == null) goto L7;
        ExceptionsKt.addSuppressed(r52, getSendException());
        Result.Companion r4 = Result.Companion;
        r0.resumeWith(Result.m2018constructorimpl(ResultKt.createFailure(r52)));
    L8:
        Object r42 = r0.getResult();
        if (r42 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L12;
        DebugProbesKt.probeCoroutineSuspended(r6);
    L12:
        if (r42 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L15;
        return r42;
    L15:
        return Unit.INSTANCE;
    L7:
        Throwable r43 = getSendException();
        Result.Companion r53 = Result.Companion;
        r0.resumeWith(Result.m2018constructorimpl(ResultKt.createFailure(r43)));
        goto L8
    }

    private final void onClosedSendOnNoWaiterSuspend(E r3, CancellableContinuation<? super Unit> r4) {
        Function1<E, Unit> r0 = this.onUndeliveredElement;
        if (r0 == null) goto L5;
        OnUndeliveredElementKt.callUndeliveredElement(r0, r3, r4.getContext());
    L5:
        Throwable r2 = getSendException();
        Result.Companion r32 = Result.Companion;
        r4.resumeWith(Result.m2018constructorimpl(ResultKt.createFailure(r2)));
    }

    private static final Function3 onUndeliveredElementReceiveCancellationConstructor$lambda$0$0(BufferedChannel r0, SelectInstance r1, Object r2, Object r3) {
        return new BufferedChannel$$ExternalSyntheticLambda2(r3, r0, r1);
    }

    private static final Unit onUndeliveredElementReceiveCancellationConstructor$lambda$0$0$0(Object r0, BufferedChannel r1, SelectInstance r2, Throwable r3, Object r4, CoroutineContext r5) {
        if (r0 == BufferedChannelKt.getCHANNEL_CLOSED()) goto L6;
        OnUndeliveredElementKt.callUndeliveredElement(r1.onUndeliveredElement, r0, r2.getContext());
    L6:
        return Unit.INSTANCE;
    }

    private final void prepareReceiverForSuspension(Waiter r1, ChannelSegment<E> r2, int r3) {
        onReceiveEnqueued();
        r1.invokeOnCancellation(r2, r3);
    }

    private final void prepareSenderForSuspension(Waiter r1, ChannelSegment<E> r2, int r3) {
        r1.invokeOnCancellation(r2, r3 + BufferedChannelKt.SEGMENT_SIZE);
    }

    private final Object processResultSelectReceive(Object r1, Object r2) {
        if (r2 == BufferedChannelKt.getCHANNEL_CLOSED()) goto L6;
        return r2;
    L6:
        throw getReceiveException();
    }

    public static /* synthetic */ <E> Object receive$suspendImpl(BufferedChannel<E> r9, Continuation<? super E> r10) {
        ChannelSegment<E> r0 = (ChannelSegment) access$getReceiveSegment$volatile$FU().get(r9);
    L4:
        if (r9.isClosedForReceive() == true) goto L29;
        long r5 = access$getReceivers$volatile$FU().getAndIncrement(r9);
        int r1 = BufferedChannelKt.SEGMENT_SIZE;
        long r2 = r5 / r1;
        int r4 = (int) (r5 % r1);
        if (r0.id == r2) goto L11;
        ChannelSegment<E> r12 = access$findSegmentReceive(r9, r2, r0);
        if (r12 == null) goto L4;
        ChannelSegment<E> r3 = r12;
    L12:
        BufferedChannel<E> r22 = r9;
        Object r92 = access$updateCellReceive(r22, r3, r4, r5, null);
        if (r92 == BufferedChannelKt.access$getSUSPEND$p()) goto L26;
        if (r92 != BufferedChannelKt.access$getFAILED$p()) goto L21;
        if (r5 >= r22.getSendersCounter$kotlinx_coroutines_core()) goto L19;
        r3.cleanPrev();
    L19:
        r9 = r22;
        r0 = r3;
        goto L4
    L21:
        if (r92 == BufferedChannelKt.access$getSUSPEND_NO_WAITER$p()) goto L23;
        r3.cleanPrev();
        return r92;
    L23:
        return r22.receiveOnNoWaiterSuspend(r3, r4, r5, r10);
    L26:
        Segment$$ExternalSyntheticBUOutline1.m("unexpected");
        return null;
    L11:
        r3 = r0;
        goto L12
    L29:
        throw StackTraceRecoveryKt.recoverStackTrace(r9.getReceiveException());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: receiveCatching-JP2dKIU$suspendImpl, reason: not valid java name */
    public static /* synthetic */ <E> Object m3528receiveCatchingJP2dKIU$suspendImpl(BufferedChannel<E> r14, Continuation<? super ChannelResult<? extends E>> r15) {
        if ((r15 instanceof BufferedChannel$receiveCatching$1) == false) goto L8;
        BufferedChannel$receiveCatching$1 r2 = (BufferedChannel$receiveCatching$1) r15;
        int r3 = r2.label;
        if ((r3 & Integer.MIN_VALUE) == 0) goto L8;
        r2.label = r3 - Integer.MIN_VALUE;
    L7:
        BufferedChannel$receiveCatching$1 r6 = r2;
        Object r1 = r6.result;
        Object r7 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r22 = r6.label;
        if (r22 == 0) goto L16;
        if (r22 != 1) goto L14;
        ChannelSegment r0 = (ChannelSegment) r6.L$4;
        ChannelSegment r02 = (ChannelSegment) r6.L$2;
        BufferedChannel r03 = (BufferedChannel) r6.L$1;
        BufferedChannel r04 = (BufferedChannel) r6.L$0;
        ResultKt.throwOnFailure(r1);
        return ((ChannelResult) r1).m3544unboximpl();
    L14:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L16:
        ResultKt.throwOnFailure(r1);
        ChannelSegment<E> r12 = (ChannelSegment) access$getReceiveSegment$volatile$FU().get(r14);
    L18:
        if (r14.isClosedForReceive() == true) goto L20;
        long r32 = access$getReceivers$volatile$FU().getAndIncrement(r14);
        int r23 = BufferedChannelKt.SEGMENT_SIZE;
        long r10 = r32 / r23;
        int r24 = (int) (r32 % r23);
        if (r12.id == r10) goto L27;
        ChannelSegment<E> r5 = access$findSegmentReceive(r14, r10, r12);
        if (r5 == null) goto L18;
        r12 = r5;
    L27:
        Object r52 = access$updateCellReceive(r14, r12, r24, r32, null);
        if (r52 == BufferedChannelKt.access$getSUSPEND$p()) goto L42;
        if (r52 != BufferedChannelKt.access$getFAILED$p()) goto L35;
        if (r32 >= r14.getSendersCounter$kotlinx_coroutines_core()) goto L18;
        r12.cleanPrev();
        goto L18
    L35:
        if (r52 != BufferedChannelKt.access$getSUSPEND_NO_WAITER$p()) goto L40;
        r6.L$0 = SpillingKt.nullOutSpilledVariable(r14);
        r6.L$1 = SpillingKt.nullOutSpilledVariable(r14);
        r6.L$2 = SpillingKt.nullOutSpilledVariable(r12);
        r6.L$3 = SpillingKt.nullOutSpilledVariable(r52);
        r6.L$4 = SpillingKt.nullOutSpilledVariable(r12);
        r6.I$0 = 0;
        r6.J$0 = r32;
        r6.J$1 = r10;
        r6.I$1 = r24;
        r6.J$2 = r32;
        r6.I$2 = r24;
        r6.I$3 = 0;
        r6.label = 1;
        Object r05 = r14.m3529receiveCatchingOnNoWaiterSuspendGKJJFZk(r12, r24, r32, r6);
        if (r05 != r7) goto L39;
        return r7;
    L39:
        return r05;
    L40:
        r12.cleanPrev();
        return ChannelResult.Companion.m3547successJP2dKIU(r52);
    L42:
        Segment$$ExternalSyntheticBUOutline1.m("unexpected");
        return null;
    L20:
        return ChannelResult.Companion.m3545closedJP2dKIU(r14.getCloseCause());
    L8:
        r2 = new BufferedChannel$receiveCatching$1(r14, r15);
        goto L7
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: receiveCatchingOnNoWaiterSuspend-GKJJFZk, reason: not valid java name */
    private final Object m3529receiveCatchingOnNoWaiterSuspendGKJJFZk(ChannelSegment<E> r15, int r16, long r17, Continuation<? super ChannelResult<? extends E>> r19) {
        if ((r19 instanceof BufferedChannel$receiveCatchingOnNoWaiterSuspend$1) == false) goto L8;
        BufferedChannel$receiveCatchingOnNoWaiterSuspend$1 r1 = (BufferedChannel$receiveCatchingOnNoWaiterSuspend$1) r19;
        int r5 = r1.label;
        if ((r5 & Integer.MIN_VALUE) == 0) goto L8;
        r1.label = r5 - Integer.MIN_VALUE;
    L7:
        BufferedChannel$receiveCatchingOnNoWaiterSuspend$1 r6 = r1;
        Object r0 = r6.result;
        Object r7 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r12 = r6.label;
        KFunction r8 = null;
        if (r12 == 0) goto L15;
        if (r12 != 1) goto L13;
        ChannelSegment r14 = (ChannelSegment) r6.L$0;
        ResultKt.throwOnFailure(r0);
    L64:
        return ((ChannelResult) r0).m3544unboximpl();
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L15:
        ResultKt.throwOnFailure(r0);
        r6.L$0 = r15;
        r6.I$0 = r16;
        r6.J$0 = r17;
        r6.I$1 = 0;
        r6.label = 1;
        CancellableContinuationImpl r9 = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(r6));
        ReceiveCatching r52 = new ReceiveCatching(r9);     // Catch: Throwable -> L19
        Object r10 = access$updateCellReceive(this, r15, r16, r17, r52);     // Catch: Throwable -> L19
        if (r10 != BufferedChannelKt.access$getSUSPEND$p()) goto L22;
        access$prepareReceiverForSuspension(this, r52, r15, r16);     // Catch: Throwable -> L19
    L58:
        r0 = r9.getResult();
        if (r0 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L61;
        DebugProbesKt.probeCoroutineSuspended(r6);
    L61:
        if (r0 != r7) goto L64;
        return r7;
    L22:
        if (r10 == BufferedChannelKt.access$getFAILED$p()) goto L24;
        r15.cleanPrev();     // Catch: Throwable -> L19
        ChannelResult r152 = ChannelResult.m3534boximpl(ChannelResult.Companion.m3547successJP2dKIU(r10));     // Catch: Throwable -> L19
        Function1<E, Unit> r13 = this.onUndeliveredElement;     // Catch: Throwable -> L19
        if (r13 == null) goto L57;
        r8 = access$bindCancellationFunResult(this, r13);     // Catch: Throwable -> L19
    L57:
        r9.resume(r152, (Function3) r8);     // Catch: Throwable -> L19
        goto L58
    L24:
        if (r17 >= getSendersCounter$kotlinx_coroutines_core()) goto L26;
        r15.cleanPrev();     // Catch: Throwable -> L19
    L26:
        ChannelSegment r153 = (ChannelSegment) access$getReceiveSegment$volatile$FU().get(this);     // Catch: Throwable -> L19
    L28:
        if (isClosedForReceive() == true) goto L29;
        long r3 = access$getReceivers$volatile$FU().getAndIncrement(this);     // Catch: Throwable -> L19
        int r18 = BufferedChannelKt.SEGMENT_SIZE;     // Catch: Throwable -> L19
        long r102 = r3 / r18;     // Catch: Throwable -> L19
        int r2 = (int) (r3 % r18);     // Catch: Throwable -> L19
        if (r153.id == r102) goto L36;
        ChannelSegment r110 = access$findSegmentReceive(this, r102, r153);     // Catch: Throwable -> L19
        if (r110 == null) goto L28;
    L37:
        Object r154 = access$updateCellReceive(this, r110, r2, r3, r52);     // Catch: Throwable -> L19
        if (r154 == BufferedChannelKt.access$getSUSPEND$p()) goto L39;
        if (r154 != BufferedChannelKt.access$getFAILED$p()) goto L47;
        if (r3 >= getSendersCounter$kotlinx_coroutines_core()) goto L45;
        r110.cleanPrev();     // Catch: Throwable -> L19
    L45:
        r153 = r110;
        goto L28
    L47:
        if (r154 == BufferedChannelKt.access$getSUSPEND_NO_WAITER$p()) goto L53;
        r110.cleanPrev();     // Catch: Throwable -> L19
        r152 = ChannelResult.m3534boximpl(ChannelResult.Companion.m3547successJP2dKIU(r154));     // Catch: Throwable -> L19
        Function1<E, Unit> r111 = this.onUndeliveredElement;     // Catch: Throwable -> L19
        if (r111 == null) goto L57;
        r8 = access$bindCancellationFunResult(this, r111);     // Catch: Throwable -> L19
        goto L57
    L53:
        throw new IllegalStateException("unexpected");     // Catch: Throwable -> L19
    L39:
        access$prepareReceiverForSuspension(this, r52, r110, r2);     // Catch: Throwable -> L19
        goto L58
    L36:
        r110 = r153;
        goto L37
    L29:
        access$onClosedReceiveCatchingOnNoWaiterSuspend(this, r9);     // Catch: Throwable -> L19
    L19:
        th = move-exception;
        r9.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
        throw th;
    L8:
        r1 = new BufferedChannel$receiveCatchingOnNoWaiterSuspend$1(this, r19);
        goto L7
    }

    private final Object receiveOnNoWaiterSuspend(ChannelSegment<E> r10, int r11, long r12, Continuation<? super E> r14) {
        CancellableContinuationImpl r6 = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(r14));
        Object r9 = access$updateCellReceive(this, r10, r11, r12, r6);     // Catch: Throwable -> L6
        if (r9 != BufferedChannelKt.access$getSUSPEND$p()) goto L8;
        access$prepareReceiverForSuspension(this, r6, r10, r11);     // Catch: Throwable -> L6
    L48:
        Object r92 = r6.getResult();
        if (r92 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L51;
        DebugProbesKt.probeCoroutineSuspended(r14);
    L51:
        return r92;
    L8:
        KFunction r112 = null;
        r112 = null;
        CancellableContinuationImpl r113 = null;
        if (r9 == BufferedChannelKt.access$getFAILED$p()) goto L11;
        r10.cleanPrev();     // Catch: Throwable -> L6
        Function1<E, Unit> r102 = this.onUndeliveredElement;     // Catch: Throwable -> L6
        if (r102 != null) goto L46;
    L41:
        r6.resume(r9, (Function3) r112);     // Catch: Throwable -> L6
        goto L48
    L46:
        r112 = access$bindCancellationFun(this, r102);     // Catch: Throwable -> L6
        goto L41
    L11:
        if (r12 >= getSendersCounter$kotlinx_coroutines_core()) goto L13;
        r10.cleanPrev();     // Catch: Throwable -> L6
    L13:
        ChannelSegment r93 = (ChannelSegment) access$getReceiveSegment$volatile$FU().get(this);     // Catch: Throwable -> L6
    L15:
        if (isClosedForReceive() == true) goto L16;
        long r4 = access$getReceivers$volatile$FU().getAndIncrement(this);     // Catch: Throwable -> L6
        int r103 = BufferedChannelKt.SEGMENT_SIZE;     // Catch: Throwable -> L6
        long r122 = r4 / r103;     // Catch: Throwable -> L6
        int r3 = (int) (r4 % r103);     // Catch: Throwable -> L6
        if (r93.id == r122) goto L23;
        ChannelSegment r104 = access$findSegmentReceive(this, r122, r93);     // Catch: Throwable -> L6
        if (r104 == null) goto L15;
        ChannelSegment r2 = r104;
    L24:
        r9 = access$updateCellReceive(this, r2, r3, r4, r6);     // Catch: Throwable -> L6
        ChannelSegment r105 = r2;
        if (r9 == BufferedChannelKt.access$getSUSPEND$p()) goto L26;
        if (r9 != BufferedChannelKt.access$getFAILED$p()) goto L37;
        if (r4 >= getSendersCounter$kotlinx_coroutines_core()) goto L35;
        r105.cleanPrev();     // Catch: Throwable -> L6
    L35:
        r93 = r105;
        goto L15
    L37:
        if (r9 == BufferedChannelKt.access$getSUSPEND_NO_WAITER$p()) goto L43;
        r105.cleanPrev();     // Catch: Throwable -> L6
        Function1<E, Unit> r106 = this.onUndeliveredElement;     // Catch: Throwable -> L6
        if (r106 == null) goto L41;
        r112 = access$bindCancellationFun(this, r106);     // Catch: Throwable -> L6
        goto L41
    L43:
        throw new IllegalStateException("unexpected");     // Catch: Throwable -> L6
    L26:
        if (r6 == null) goto L28;
        r113 = r6;
    L28:
        if (r113 == null) goto L48;
        access$prepareReceiverForSuspension(this, r113, r105, r3);     // Catch: Throwable -> L6
        goto L48
    L23:
        r2 = r93;
        goto L24
    L16:
        access$onClosedReceiveOnNoWaiterSuspend(this, r6);     // Catch: Throwable -> L6
    L6:
        th = move-exception;
        r6.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
        throw th;
    }

    private final void registerSelectForReceive(SelectInstance<?> r9, Object r10) {
        ChannelSegment r102 = (ChannelSegment) access$getReceiveSegment$volatile$FU().get(this);
    L4:
        if (this.isClosedForReceive() == true) goto L5;
        long r4 = access$getReceivers$volatile$FU().getAndIncrement(this);
        int r0 = BufferedChannelKt.SEGMENT_SIZE;
        long r1 = r4 / r0;
        int r3 = (int) (r4 % r0);
        if (r102.id == r1) goto L14;
        ChannelSegment r02 = access$findSegmentReceive(this, r1, r102);
        if (r02 == null) goto L4;
        ChannelSegment r2 = r02;
    L13:
        BufferedChannel<E> r12 = this;
        SelectInstance<?> r6 = r9;
        Object r8 = access$updateCellReceive(r12, r2, r3, r4, r6);
        r102 = r2;
        if (r8 == BufferedChannelKt.access$getSUSPEND$p()) goto L18;
        if (r8 != BufferedChannelKt.access$getFAILED$p()) goto L31;
        if (r4 >= r12.getSendersCounter$kotlinx_coroutines_core()) goto L29;
        r102.cleanPrev();
    L29:
        BufferedChannel<E> this = r12;
        r9 = r6;
        goto L4
    L31:
        if (r8 == BufferedChannelKt.access$getSUSPEND_NO_WAITER$p()) goto L34;
        r102.cleanPrev();
        r6.selectInRegistrationPhase(r8);
        return;
    L34:
        Segment$$ExternalSyntheticBUOutline1.m("unexpected");
        return;
    L18:
        if ((r6 instanceof Waiter) == false) goto L20;
        Waiter r92 = (Waiter) r6;
    L21:
        if (r92 == null) goto L44;
        access$prepareReceiverForSuspension(r12, r92, r102, r3);
        return;
    L44:
        return;
    L20:
        r92 = null;
        goto L21
    L14:
        r2 = r102;
        goto L13
    L5:
        this.onClosedSelectOnReceive(r9);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void removeUnprocessedElements(ChannelSegment<E> r12) {
        Function1<E, Unit> r0 = this.onUndeliveredElement;
        UndeliveredElementException r1 = null;
        Object r3 = InlineList.m3555constructorimpl$default(null, 1, null);
    L3:
        int r4 = BufferedChannelKt.SEGMENT_SIZE - 1;
    L5:
        if ((-1) >= r4) goto L50;
        long r6 = (r12.id * BufferedChannelKt.SEGMENT_SIZE) + r4;
    L7:
        Object r8 = r12.getState$kotlinx_coroutines_core(r4);
        if (r8 == BufferedChannelKt.access$getDONE_RCV$p()) goto L52;
        if (r8 == BufferedChannelKt.BUFFERED) goto L12;
        if (r8 == BufferedChannelKt.access$getIN_BUFFER$p()) goto L47;
        if (r8 == null) goto L47;
        if ((r8 instanceof Waiter) == true) goto L36;
        if ((r8 instanceof WaiterEB) == true) goto L36;
        if (r8 == BufferedChannelKt.access$getRESUMING_BY_EB$p()) goto L52;
        if (r8 == BufferedChannelKt.access$getRESUMING_BY_RCV$p()) goto L52;
        if (r8 == BufferedChannelKt.access$getRESUMING_BY_EB$p()) goto L7;
    L49:
        r4 = r4 - 1;
    L36:
        if (r6 < getReceiversCounter$kotlinx_coroutines_core()) goto L52;
        if ((r8 instanceof WaiterEB) == false) goto L40;
        Waiter r9 = ((WaiterEB) r8).waiter;
    L42:
        if (r12.casState$kotlinx_coroutines_core(r4, r8, BufferedChannelKt.getCHANNEL_CLOSED()) == false) goto L7;
        if (r0 == null) goto L45;
        r1 = OnUndeliveredElementKt.callUndeliveredElementCatchingException(r0, r12.getElement$kotlinx_coroutines_core(r4), r1);
    L45:
        r3 = InlineList.m3556plusFjFbRPM(r3, r9);
        r12.cleanElement$kotlinx_coroutines_core(r4);
        r12.onSlotCleaned();
        goto L49
    L40:
        r9 = (Waiter) r8;
    L47:
        if (r12.casState$kotlinx_coroutines_core(r4, r8, BufferedChannelKt.getCHANNEL_CLOSED()) == false) goto L7;
        r12.onSlotCleaned();
        goto L49
    L12:
        if (r6 < getReceiversCounter$kotlinx_coroutines_core()) goto L52;
        if (r12.casState$kotlinx_coroutines_core(r4, r8, BufferedChannelKt.getCHANNEL_CLOSED()) == false) goto L7;
        if (r0 == null) goto L17;
        r1 = OnUndeliveredElementKt.callUndeliveredElementCatchingException(r0, r12.getElement$kotlinx_coroutines_core(r4), r1);
    L17:
        r12.cleanElement$kotlinx_coroutines_core(r4);
        r12.onSlotCleaned();
    L52:
        if (r3 != null) goto L54;
    L59:
        if (r1 != null) goto L61;
        return;
    L61:
        throw r1;
    L54:
        if ((r3 instanceof ArrayList) == true) goto L56;
        resumeSenderOnCancelledChannel((Waiter) r3);
        goto L59
    L56:
        ArrayList r32 = (ArrayList) r3;
        int r122 = r32.size() - 1;
    L57:
        if ((-1) >= r122) goto L59;
        resumeSenderOnCancelledChannel((Waiter) r32.get(r122));
        r122 = r122 - 1;
        goto L57
    L50:
        r12 = (ChannelSegment) r12.getPrev();
        if (r12 != null) goto L3;
        goto L52
    }

    private final void resumeReceiverOnClosedChannel(Waiter r2) {
        resumeWaiterOnClosedChannel(r2, true);
    }

    private final void resumeSenderOnCancelledChannel(Waiter r2) {
        resumeWaiterOnClosedChannel(r2, false);
    }

    private final void resumeWaiterOnClosedChannel(Waiter r2, boolean r3) {
        if ((r2 instanceof CancellableContinuation) == false) goto L11;
        Continuation r22 = (Continuation) r2;
        Result.Companion r0 = Result.Companion;
        if (r3 == false) goto L7;
        Throwable r1 = getReceiveException();
    L8:
        r22.resumeWith(Result.m2018constructorimpl(ResultKt.createFailure(r1)));
        return;
    L7:
        r1 = getSendException();
        goto L8
    L11:
        if ((r2 instanceof ReceiveCatching) == false) goto L15;
        CancellableContinuationImpl<ChannelResult<? extends E>> r23 = ((ReceiveCatching) r2).cont;
        Result.Companion r32 = Result.Companion;
        r23.resumeWith(Result.m2018constructorimpl(ChannelResult.m3534boximpl(ChannelResult.Companion.m3545closedJP2dKIU(getCloseCause()))));
        return;
    L15:
        if ((r2 instanceof BufferedChannelIterator) == false) goto L19;
        ((BufferedChannelIterator) r2).tryResumeHasNextOnClosedChannel();
        return;
    L19:
        if ((r2 instanceof SelectInstance) == false) goto L22;
        ((SelectInstance) r2).trySelect(this, BufferedChannelKt.getCHANNEL_CLOSED());
        return;
    L22:
        DurationKt$$ExternalSyntheticBUOutline0.m("Unexpected waiter: ", r2);
    }

    public static /* synthetic */ <E> Object send$suspendImpl(BufferedChannel<E> r13, E r14, Continuation<? super Unit> r15) {
        ChannelSegment<E> r0 = (ChannelSegment) access$getSendSegment$volatile$FU().get(r13);
    L3:
        long r1 = access$getSendersAndCloseStatus$volatile$FU().getAndIncrement(r13);
        long r9 = r1 & 1152921504606846975L;
        boolean r12 = access$isClosedForSend0(r13, r1);
        int r16 = BufferedChannelKt.SEGMENT_SIZE;
        long r2 = r9 / r16;
        int r7 = (int) (r9 % r16);
        if (r0.id == r2) goto L12;
        ChannelSegment<E> r17 = access$findSegmentSend(r13, r2, r0);
        if (r17 == null) goto L7;
        ChannelSegment<E> r6 = r17;
    L13:
        BufferedChannel<E> r5 = r13;
        E r8 = r14;
        int r132 = access$updateCellSend(r5, r6, r7, r8, r9, null, r12);
        if (r132 == 0) goto L42;
        if (r132 == 1) goto L44;
        if (r132 == 2) goto L38;
        if (r132 == 3) goto L34;
        if (r132 == 4) goto L29;
        if (r132 != 5) goto L27;
        r6.cleanPrev();
    L27:
        r13 = r5;
        r0 = r6;
        r14 = r8;
        goto L3
    L29:
        if (r9 >= r5.getReceiversCounter$kotlinx_coroutines_core()) goto L31;
        r6.cleanPrev();
    L31:
        Object r133 = r5.onClosedSend(r8, r15);
        if (r133 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L44;
        return r133;
    L34:
        Object r134 = r5.sendOnNoWaiterSuspend(r6, r7, r8, r9, r15);
        if (r134 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L44;
        return r134;
    L38:
        if (r12 == false) goto L44;
        r6.onSlotCleaned();
        Object r135 = r5.onClosedSend(r8, r15);
        if (r135 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L44;
        return r135;
    L44:
        return Unit.INSTANCE;
    L42:
        r6.cleanPrev();
        goto L44
    L7:
        if (r12 == false) goto L3;
        Object r136 = r13.onClosedSend(r14, r15);
        if (r136 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L44;
        return r136;
    L12:
        r6 = r0;
        goto L13
    }

    private final Object sendOnNoWaiterSuspend(ChannelSegment<E> r16, int r17, E r18, long r19, Continuation<? super Unit> r21) {
        CancellableContinuationImpl r6 = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(r21));
        int r7 = access$updateCellSend(this, r16, r17, r18, r19, r6, false);     // Catch: Throwable -> L20
        if (r7 != 0) goto L6;
        r16.cleanPrev();     // Catch: Throwable -> L20
        Result.Companion r15 = Result.Companion;     // Catch: Throwable -> L20
        Unit r152 = Unit.INSTANCE;     // Catch: Throwable -> L20
    L49:
        r6.resumeWith(Result.m2018constructorimpl(r152));     // Catch: Throwable -> L20
    L60:
        Object r153 = r6.getResult();
        if (r153 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L64;
        DebugProbesKt.probeCoroutineSuspended(r21);
    L64:
        if (r153 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L67;
        return r153;
    L67:
        return Unit.INSTANCE;
    L6:
        if (r7 != 1) goto L8;
        Result.Companion r154 = Result.Companion;     // Catch: Throwable -> L20
        r152 = Unit.INSTANCE;     // Catch: Throwable -> L20
        goto L49
    L8:
        if (r7 != 2) goto L10;
        access$prepareSenderForSuspension(this, r6, r16, r17);     // Catch: Throwable -> L20
        goto L60
    L10:
        if (r7 == 4) goto L54;
        if (r7 != 5) goto L52;
        r16.cleanPrev();     // Catch: Throwable -> L20
        ChannelSegment r1 = (ChannelSegment) access$getSendSegment$volatile$FU().get(this);     // Catch: Throwable -> L20
    L14:
        long r4 = access$getSendersAndCloseStatus$volatile$FU().getAndIncrement(this);     // Catch: Throwable -> L20
        long r13 = 1152921504606846975L & r4;     // Catch: Throwable -> L20
        boolean r72 = access$isClosedForSend0(this, r4);     // Catch: Throwable -> L20
        int r2 = BufferedChannelKt.SEGMENT_SIZE;     // Catch: Throwable -> L20
        long r42 = r13 / r2;     // Catch: Throwable -> L20
        int r22 = (int) (r13 % r2);     // Catch: Throwable -> L20
        if (r1.id == r42) goto L24;
        ChannelSegment r43 = access$findSegmentSend(this, r42, r1);     // Catch: Throwable -> L20
        if (r43 == null) goto L18;
        r1 = r43;
        goto L24
    L18:
        if (r72 == false) goto L14;
    L19:
        access$onClosedSendOnNoWaiterSuspend(this, r18, r6);     // Catch: Throwable -> L20
    L24:
        int r12 = access$updateCellSend(this, r1, r22, r18, r13, r6, r72);     // Catch: Throwable -> L20
        if (r12 == 0) goto L50;
        if (r12 == 1) goto L48;
        if (r12 == 2) goto L41;
        if (r12 == 3) goto L40;
        if (r12 == 4) goto L37;
        if (r12 != 5) goto L14;
        r1.cleanPrev();     // Catch: Throwable -> L20
        goto L14
    L37:
        if (r13 >= getReceiversCounter$kotlinx_coroutines_core()) goto L19;
        r1.cleanPrev();     // Catch: Throwable -> L20
        goto L19
    L40:
        throw new IllegalStateException("unexpected");     // Catch: Throwable -> L20
    L41:
        if (r72 == false) goto L43;
        r1.onSlotCleaned();     // Catch: Throwable -> L20
        goto L19
    L43:
        if (r6 == null) goto L45;
        CancellableContinuationImpl r3 = r6;
    L46:
        if (r3 == null) goto L60;
        access$prepareSenderForSuspension(this, r3, r1, r22);     // Catch: Throwable -> L20
        goto L60
    L45:
        r3 = null;
        goto L46
    L48:
        Result.Companion r155 = Result.Companion;     // Catch: Throwable -> L20
        r152 = Unit.INSTANCE;     // Catch: Throwable -> L20
        goto L49
    L50:
        r1.cleanPrev();     // Catch: Throwable -> L20
        Result.Companion r156 = Result.Companion;     // Catch: Throwable -> L20
        r152 = Unit.INSTANCE;     // Catch: Throwable -> L20
        goto L49
    L52:
        throw new IllegalStateException("unexpected");     // Catch: Throwable -> L20
    L54:
        if (r19 >= getReceiversCounter$kotlinx_coroutines_core()) goto L19;
        r16.cleanPrev();     // Catch: Throwable -> L20
    L20:
        th = move-exception;
        r6.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
        throw th;
    }

    private final boolean shouldSendSuspend(long r3) {
        if (isClosedForSend0(r3) == false) goto L7;
        return false;
    L7:
        return !bufferOrRendezvousSend(r3 & 1152921504606846975L);
    }

    private final boolean tryResumeReceiver(Object r3, E r4) {
        if ((r3 instanceof SelectInstance) == true) goto L5;
        KFunction<Unit> r1 = null;
        if ((r3 instanceof ReceiveCatching) == false) goto L14;
        CancellableContinuationImpl<ChannelResult<? extends E>> r32 = ((ReceiveCatching) r3).cont;
        ChannelResult r42 = ChannelResult.m3534boximpl(ChannelResult.Companion.m3547successJP2dKIU(r4));
        Function1<E, Unit> r0 = this.onUndeliveredElement;
        if (r0 == null) goto L12;
        r1 = bindCancellationFunResult(r0);
    L12:
        return BufferedChannelKt.access$tryResume0(r32, r42, (Function3) r1);
    L14:
        if ((r3 instanceof BufferedChannelIterator) == false) goto L18;
        return ((BufferedChannelIterator) r3).tryResumeHasNext(r4);
    L18:
        if ((r3 instanceof CancellableContinuation) == false) goto L24;
        CancellableContinuation r33 = (CancellableContinuation) r3;
        Function1<E, Unit> r02 = this.onUndeliveredElement;
        if (r02 == null) goto L23;
        r1 = bindCancellationFun(r02);
    L23:
        return BufferedChannelKt.access$tryResume0(r33, r4, (Function3) r1);
    L24:
        DurationKt$$ExternalSyntheticBUOutline0.m("Unexpected receiver type: ", r3);
        return false;
    L5:
        return ((SelectInstance) r3).trySelect(this, r4);
    }

    private final boolean tryResumeSender(Object r3, ChannelSegment<E> r4, int r5) {
        if ((r3 instanceof CancellableContinuation) == false) goto L7;
        return BufferedChannelKt.tryResume0$default((CancellableContinuation) r3, Unit.INSTANCE, null, 2, null);
    L7:
        if ((r3 instanceof SelectInstance) == false) goto L16;
        TrySelectDetailedResult r2 = ((SelectImplementation) r3).trySelectDetailed(this, Unit.INSTANCE);
        if (r2 != TrySelectDetailedResult.REREGISTER) goto L12;
        r4.cleanElement$kotlinx_coroutines_core(r5);
    L12:
        if (r2 != TrySelectDetailedResult.SUCCESSFUL) goto L15;
        return true;
    L15:
        return false;
    L16:
        DurationKt$$ExternalSyntheticBUOutline0.m("Unexpected waiter: ", r3);
        return false;
    }

    private final boolean updateCellExpandBuffer(ChannelSegment<E> r4, int r5, long r6) {
        Object r0 = r4.getState$kotlinx_coroutines_core(r5);
        if ((r0 instanceof Waiter) == false) goto L15;
        if (r6 < getReceivers$volatile$FU().get(this)) goto L15;
        if (r4.casState$kotlinx_coroutines_core(r5, r0, BufferedChannelKt.access$getRESUMING_BY_EB$p()) == false) goto L15;
        if (tryResumeSender(r0, r4, r5) == false) goto L12;
        r4.setState$kotlinx_coroutines_core(r5, BufferedChannelKt.BUFFERED);
        return true;
    L12:
        r4.setState$kotlinx_coroutines_core(r5, BufferedChannelKt.access$getINTERRUPTED_SEND$p());
        r4.onCancelledRequest(r5, false);
        return false;
    L15:
        return updateCellExpandBufferSlow(r4, r5, r6);
    }

    private final boolean updateCellExpandBufferSlow(ChannelSegment<E> r7, int r8, long r9) {
    L2:
        Object r0 = r7.getState$kotlinx_coroutines_core(r8);
        if ((r0 instanceof Waiter) == true) goto L5;
        if (r0 == BufferedChannelKt.access$getINTERRUPTED_SEND$p()) goto L19;
        if (r0 == null) goto L22;
        if (r0 == BufferedChannelKt.BUFFERED) goto L26;
        if (r0 == BufferedChannelKt.access$getPOISONED$p()) goto L42;
        if (r0 == BufferedChannelKt.access$getDONE_RCV$p()) goto L42;
        if (r0 == BufferedChannelKt.access$getINTERRUPTED_RCV$p()) goto L42;
        if (r0 == BufferedChannelKt.getCHANNEL_CLOSED()) goto L36;
        if (r0 == BufferedChannelKt.access$getRESUMING_BY_RCV$p()) goto L2;
        DurationKt$$ExternalSyntheticBUOutline0.m("Unexpected cell state: ", r0);
        return false;
    L36:
        return true;
    L42:
        return true;
    L26:
        return true;
    L22:
        if (r7.casState$kotlinx_coroutines_core(r8, r0, BufferedChannelKt.access$getIN_BUFFER$p()) == false) goto L2;
        return true;
    L19:
        return false;
    L5:
        if (r9 < getReceivers$volatile$FU().get(this)) goto L7;
        if (r7.casState$kotlinx_coroutines_core(r8, r0, BufferedChannelKt.access$getRESUMING_BY_EB$p()) == false) goto L2;
        if (tryResumeSender(r0, r7, r8) == false) goto L15;
        r7.setState$kotlinx_coroutines_core(r8, BufferedChannelKt.BUFFERED);
        return true;
    L15:
        r7.setState$kotlinx_coroutines_core(r8, BufferedChannelKt.access$getINTERRUPTED_SEND$p());
        r7.onCancelledRequest(r8, false);
        return false;
    L7:
        if (r7.casState$kotlinx_coroutines_core(r8, r0, new WaiterEB((Waiter) r0)) == false) goto L2;
        return true;
    }

    private final Object updateCellReceive(ChannelSegment<E> r7, int r8, long r9, Object r11) {
        Object r0 = r7.getState$kotlinx_coroutines_core(r8);
        if (r0 != null) goto L15;
        if (r9 < (getSendersAndCloseStatus$volatile$FU().get(this) & 1152921504606846975L)) goto L21;
        if (r11 != null) goto L10;
        return BufferedChannelKt.access$getSUSPEND_NO_WAITER$p();
    L10:
        if (r7.casState$kotlinx_coroutines_core(r8, r0, r11) == false) goto L21;
        expandBuffer();
        return BufferedChannelKt.access$getSUSPEND$p();
    L21:
        return updateCellReceiveSlow(r7, r8, r9, r11);
    L15:
        if (r0 != BufferedChannelKt.BUFFERED) goto L21;
        if (r7.casState$kotlinx_coroutines_core(r8, r0, BufferedChannelKt.access$getDONE_RCV$p()) == false) goto L21;
        expandBuffer();
        return r7.retrieveElement$kotlinx_coroutines_core(r8);
    }

    private final Object updateCellReceiveSlow(ChannelSegment<E> r6, int r7, long r8, Object r10) {
    L2:
        Object r0 = r6.getState$kotlinx_coroutines_core(r7);
        if (r0 == null) goto L42;
        if (r0 == BufferedChannelKt.access$getIN_BUFFER$p()) goto L42;
        if (r0 == BufferedChannelKt.BUFFERED) goto L10;
        if (r0 == BufferedChannelKt.access$getINTERRUPTED_SEND$p()) goto L16;
        if (r0 == BufferedChannelKt.access$getPOISONED$p()) goto L20;
        if (r0 == BufferedChannelKt.getCHANNEL_CLOSED()) goto L23;
        if (r0 == BufferedChannelKt.access$getRESUMING_BY_EB$p()) goto L2;
        if (r6.casState$kotlinx_coroutines_core(r7, r0, BufferedChannelKt.access$getRESUMING_BY_RCV$p()) == false) goto L2;
        boolean r82 = r0 instanceof WaiterEB;
        if (r82 == false) goto L33;
        r0 = ((WaiterEB) r0).waiter;
    L33:
        if (tryResumeSender(r0, r6, r7) == false) goto L36;
        r6.setState$kotlinx_coroutines_core(r7, BufferedChannelKt.access$getDONE_RCV$p());
        expandBuffer();
        return r6.retrieveElement$kotlinx_coroutines_core(r7);
    L36:
        r6.setState$kotlinx_coroutines_core(r7, BufferedChannelKt.access$getINTERRUPTED_SEND$p());
        r6.onCancelledRequest(r7, false);
        if (r82 == false) goto L40;
        expandBuffer();
    L40:
        return BufferedChannelKt.access$getFAILED$p();
    L23:
        expandBuffer();
        return BufferedChannelKt.access$getFAILED$p();
    L20:
        return BufferedChannelKt.access$getFAILED$p();
    L16:
        return BufferedChannelKt.access$getFAILED$p();
    L10:
        if (r6.casState$kotlinx_coroutines_core(r7, r0, BufferedChannelKt.access$getDONE_RCV$p()) == false) goto L2;
        expandBuffer();
        return r6.retrieveElement$kotlinx_coroutines_core(r7);
    L42:
        if (r8 < (getSendersAndCloseStatus$volatile$FU().get(this) & 1152921504606846975L)) goto L44;
        if (r10 == null) goto L49;
        if (r6.casState$kotlinx_coroutines_core(r7, r0, r10) == false) goto L2;
        expandBuffer();
        return BufferedChannelKt.access$getSUSPEND$p();
    L49:
        return BufferedChannelKt.access$getSUSPEND_NO_WAITER$p();
    L44:
        if (r6.casState$kotlinx_coroutines_core(r7, r0, BufferedChannelKt.access$getPOISONED$p()) == false) goto L2;
        expandBuffer();
        return BufferedChannelKt.access$getFAILED$p();
    }

    private final int updateCellSend(ChannelSegment<E> r4, int r5, E r6, long r7, Object r9, boolean r10) {
        r4.storeElement$kotlinx_coroutines_core(r5, r6);
        if (r10 == true) goto L5;
        Object r0 = r4.getState$kotlinx_coroutines_core(r5);
        if (r0 != null) goto L21;
        if (bufferOrRendezvousSend(r7) == true) goto L11;
        if (r9 != null) goto L17;
        return 3;
    L17:
        if (r4.casState$kotlinx_coroutines_core(r5, null, r9) == false) goto L32;
        return 2;
    L32:
        return updateCellSendSlow(r4, r5, r6, r7, r9, r10);
    L11:
        if (r4.casState$kotlinx_coroutines_core(r5, null, BufferedChannelKt.BUFFERED) == false) goto L32;
        return 1;
    L21:
        if ((r0 instanceof Waiter) == false) goto L32;
        r4.cleanElement$kotlinx_coroutines_core(r5);
        if (tryResumeReceiver(r0, r6) == false) goto L27;
        r4.setState$kotlinx_coroutines_core(r5, BufferedChannelKt.access$getDONE_RCV$p());
        onReceiveDequeued();
        return 0;
    L27:
        if (r4.getAndSetState$kotlinx_coroutines_core(r5, BufferedChannelKt.access$getINTERRUPTED_RCV$p()) == BufferedChannelKt.access$getINTERRUPTED_RCV$p()) goto L33;
        r4.onCancelledRequest(r5, true);
        return 5;
    L33:
        return 5;
    L5:
        return updateCellSendSlow(r4, r5, r6, r7, r9, r10);
    }

    private final int updateCellSendSlow(ChannelSegment<E> r6, int r7, E r8, long r9, Object r11, boolean r12) {
    L2:
        Object r0 = r6.getState$kotlinx_coroutines_core(r7);
        if (r0 == null) goto L5;
        if (r0 != BufferedChannelKt.access$getIN_BUFFER$p()) goto L28;
        if (r6.casState$kotlinx_coroutines_core(r7, r0, BufferedChannelKt.BUFFERED) == false) goto L2;
        return 1;
    L28:
        if (r0 != BufferedChannelKt.access$getINTERRUPTED_RCV$p()) goto L32;
        r6.cleanElement$kotlinx_coroutines_core(r7);
        return 5;
    L32:
        if (r0 != BufferedChannelKt.access$getPOISONED$p()) goto L36;
        r6.cleanElement$kotlinx_coroutines_core(r7);
        return 5;
    L36:
        if (r0 != BufferedChannelKt.getCHANNEL_CLOSED()) goto L39;
        r6.cleanElement$kotlinx_coroutines_core(r7);
        completeCloseOrCancel();
        return 4;
    L39:
        r6.cleanElement$kotlinx_coroutines_core(r7);
        if ((r0 instanceof WaiterEB) == false) goto L43;
        r0 = ((WaiterEB) r0).waiter;
    L43:
        if (tryResumeReceiver(r0, r8) == false) goto L47;
        r6.setState$kotlinx_coroutines_core(r7, BufferedChannelKt.access$getDONE_RCV$p());
        onReceiveDequeued();
        return 0;
    L47:
        if (r6.getAndSetState$kotlinx_coroutines_core(r7, BufferedChannelKt.access$getINTERRUPTED_RCV$p()) == BufferedChannelKt.access$getINTERRUPTED_RCV$p()) goto L49;
        r6.onCancelledRequest(r7, true);
    L49:
        return 5;
    L5:
        if (bufferOrRendezvousSend(r9) == false) goto L10;
        if (r12 == true) goto L10;
        if (r6.casState$kotlinx_coroutines_core(r7, null, BufferedChannelKt.BUFFERED) == false) goto L2;
        return 1;
    L10:
        if (r12 == true) goto L12;
        if (r11 == null) goto L16;
        if (r6.casState$kotlinx_coroutines_core(r7, null, r11) == false) goto L2;
        return 2;
    L16:
        return 3;
    L12:
        if (r6.casState$kotlinx_coroutines_core(r7, null, BufferedChannelKt.access$getINTERRUPTED_SEND$p()) == false) goto L2;
        r6.onCancelledRequest(r7, false);
        return 4;
    }

    private final void updateReceiversCounterIfLower(long r8) {
        AtomicLongFieldUpdater r0 = getReceivers$volatile$FU();
    L3:
        long r3 = r0.get(this);
        if (r3 >= r8) goto L8;
        BufferedChannel<E> r2 = this;
        long r5 = r8;
        if (getReceivers$volatile$FU().compareAndSet(r2, r3, r5) == true) goto L12;
        BufferedChannel<E> this = r2;
        r8 = r5;
        goto L3
    L12:
        return;
    }

    private final void updateSendersCounterIfLower(long r8) {
        AtomicLongFieldUpdater r0 = getSendersAndCloseStatus$volatile$FU();
    L3:
        long r3 = r0.get(this);
        long r1 = 1152921504606846975L & r3;
        if (r1 >= r8) goto L8;
        long r5 = BufferedChannelKt.access$constructSendersAndCloseStatus(r1, (int) (r3 >> 60));
        BufferedChannel<E> r2 = this;
        if (getSendersAndCloseStatus$volatile$FU().compareAndSet(r2, r3, r5) == true) goto L12;
        BufferedChannel<E> this = r2;
        goto L3
    L12:
        return;
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final void cancel(CancellationException r1) {
        cancelImpl$kotlinx_coroutines_core(r1);
    }

    public boolean cancelImpl$kotlinx_coroutines_core(Throwable r2) {
        if (r2 != null) goto L5;
        r2 = new CancellationException("Channel was cancelled");
    L5:
        return closeOrCancelImpl(r2, true);
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public boolean close(Throwable r2) {
        return closeOrCancelImpl(r2, false);
    }

    public boolean closeOrCancelImpl(Throwable r3, boolean r4) {
        if (r4 == false) goto L4;
        markCancellationStarted();
    L4:
        boolean r32 = AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_closeCause$volatile$FU(), this, BufferedChannelKt.access$getNO_CLOSE_CAUSE$p(), r3);
        if (r4 == false) goto L7;
        markCancelled();
    L8:
        completeCloseOrCancel();
        onClosedIdempotent();
        if (r32 == false) goto L11;
        invokeCloseHandler();
    L11:
        return r32;
    L7:
        markClosed();
        goto L8
    }

    public final void dropFirstElementUntilTheSpecifiedCellIsInTheBuffer(long r11) {
        ChannelSegment<E> r0 = (ChannelSegment) getReceiveSegment$volatile$FU().get(this);
    L3:
        long r4 = getReceivers$volatile$FU().get(this);
        if (r11 < Math.max(this.capacity + r4, this.getBufferEndCounter())) goto L5;
        BufferedChannel<E> r3 = this;
        if (getReceivers$volatile$FU().compareAndSet(r3, r4, 1 + r4) == false) goto L27;
        int r10 = BufferedChannelKt.SEGMENT_SIZE;
        long r6 = r4 / r10;
        int r102 = (int) (r4 % r10);
        if (r0.id == r6) goto L14;
        ChannelSegment<E> r1 = r3.findSegmentReceive(r6, r0);
        if (r1 == null) goto L27;
        ChannelSegment<E> r32 = r1;
    L15:
        Object r103 = r3.updateCellReceive(r32, r102, r4, null);
        if (r103 == BufferedChannelKt.access$getFAILED$p()) goto L18;
        r32.cleanPrev();
        Function1<E, Unit> r02 = r3.onUndeliveredElement;
        if (r02 == null) goto L26;
        UndeliveredElementException r104 = OnUndeliveredElementKt.callUndeliveredElementCatchingException$default(r02, r103, null, 2, null);
        if (r104 == null) goto L26;
        throw r104;
    L26:
        BufferedChannel<E> this = r3;
        r0 = r32;
        goto L3
    L18:
        if (r4 >= r3.getSendersCounter$kotlinx_coroutines_core()) goto L26;
        r32.cleanPrev();
        goto L26
    L14:
        r32 = r0;
    L27:
        this = r3;
        goto L3
    }

    public final Throwable getCloseCause() {
        return (Throwable) get_closeCause$volatile$FU().get(this);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public SelectClause1<E> getOnReceive() {
        return new SelectClause1Impl(this, (Function3) TypeIntrinsics.beforeCheckcastToFunctionOfArity(BufferedChannel$onReceive$1.INSTANCE, 3), (Function3) TypeIntrinsics.beforeCheckcastToFunctionOfArity(BufferedChannel$onReceive$2.INSTANCE, 3), this.onUndeliveredElementReceiveCancellationConstructor);
    }

    public final long getReceiversCounter$kotlinx_coroutines_core() {
        return getReceivers$volatile$FU().get(this);
    }

    public final Throwable getSendException() {
        Throwable r1 = getCloseCause();
        if (r1 == null) goto L5;
        return r1;
    L5:
        return new ClosedSendChannelException("Channel was closed");
    }

    public final long getSendersCounter$kotlinx_coroutines_core() {
        return getSendersAndCloseStatus$volatile$FU().get(this) & 1152921504606846975L;
    }

    public final boolean hasElements$kotlinx_coroutines_core() {
    L2:
        ChannelSegment<E> r0 = (ChannelSegment) getReceiveSegment$volatile$FU().get(this);
        long r3 = getReceiversCounter$kotlinx_coroutines_core();
        if (getSendersCounter$kotlinx_coroutines_core() <= r3) goto L4;
        int r1 = BufferedChannelKt.SEGMENT_SIZE;
        long r5 = r3 / r1;
        if (r0.id == r5) goto L12;
        r0 = findSegmentReceive(r5, r0);
        if (r0 != null) goto L12;
        if (((ChannelSegment) getReceiveSegment$volatile$FU().get(this)).id >= r5) goto L2;
        return false;
    L12:
        r0.cleanPrev();
        if (isCellNonEmpty(r0, (int) (r3 % r1), r3) == true) goto L14;
        getReceivers$volatile$FU().compareAndSet(this, r3, 1 + r3);
        goto L2
    L14:
        return true;
    L4:
        return false;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public void invokeOnClose(Function1<? super Throwable, Unit> r5) {
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(getCloseHandler$volatile$FU(), this, null, r5) == false) goto L5;
        return;
    L5:
        AtomicReferenceFieldUpdater r0 = getCloseHandler$volatile$FU();
    L6:
        Object r1 = r0.get(this);
        if (r1 != BufferedChannelKt.access$getCLOSE_HANDLER_CLOSED$p()) goto L13;
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(getCloseHandler$volatile$FU(), this, BufferedChannelKt.access$getCLOSE_HANDLER_CLOSED$p(), BufferedChannelKt.access$getCLOSE_HANDLER_INVOKED$p()) == false) goto L6;
        r5.invoke(getCloseCause());
        return;
    L13:
        if (r1 != BufferedChannelKt.access$getCLOSE_HANDLER_INVOKED$p()) goto L16;
        Segment$$ExternalSyntheticBUOutline1.m("Another handler was already registered and successfully invoked");
        return;
    L16:
        DurationKt$$ExternalSyntheticBUOutline0.m("Another handler is already registered: ", r1);
    }

    public boolean isClosedForReceive() {
        return isClosedForReceive0(getSendersAndCloseStatus$volatile$FU().get(this));
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public boolean isClosedForSend() {
        return isClosedForSend0(getSendersAndCloseStatus$volatile$FU().get(this));
    }

    public boolean isConflatedDropOldest() {
        return false;
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public ChannelIterator<E> iterator() {
        return new BufferedChannelIterator(this);
    }

    public void onClosedIdempotent() {
    }

    public void onReceiveDequeued() {
    }

    public void onReceiveEnqueued() {
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public Object receive(Continuation<? super E> r1) {
        return receive$suspendImpl(this, r1);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    /* renamed from: receiveCatching-JP2dKIU, reason: not valid java name */
    public Object mo3530receiveCatchingJP2dKIU(Continuation<? super ChannelResult<? extends E>> r1) {
        return m3528receiveCatchingJP2dKIU$suspendImpl(this, r1);
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public Object send(E r1, Continuation<? super Unit> r2) {
        return send$suspendImpl(this, r1, r2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public String toString() {
        StringBuilder r1 = new StringBuilder();
        int r2 = (int) (getSendersAndCloseStatus$volatile$FU().get(this) >> 60);
        if (r2 == 2) goto L7;
        if (r2 != 3) goto L8;
        r1.append("cancelled,");
    L8:
        r1.append("capacity=" + this.capacity + ',');
        r1.append("data=[");
        int r6 = 0;
        List r22 = CollectionsKt.listOf(new ChannelSegment[]{getReceiveSegment$volatile$FU().get(this), getSendSegment$volatile$FU().get(this), getBufferEndSegment$volatile$FU().get(this)});
        ArrayList r3 = new ArrayList();
        Iterator r23 = r22.iterator();
    L10:
        if (r23.hasNext() == false) goto L14;
        Object r4 = r23.next();
        if (((ChannelSegment) r4) == BufferedChannelKt.access$getNULL_SEGMENT$p()) goto L10;
        r3.add(r4);
        goto L10
    L14:
        Iterator r24 = r3.iterator();
        if (r24.hasNext() == false) goto L95;
        Object r32 = r24.next();
        if (r24.hasNext() == false) goto L25;
        long r8 = ((ChannelSegment) r32).id;
    L20:
        Object r42 = r24.next();
        long r10 = ((ChannelSegment) r42).id;
        if (r8 <= r10) goto L24;
        r32 = r42;
        r8 = r10;
    L24:
        if (r24.hasNext() == true) goto L20;
    L25:
        ChannelSegment r33 = (ChannelSegment) r32;
        long r102 = getReceiversCounter$kotlinx_coroutines_core();
        long r12 = getSendersCounter$kotlinx_coroutines_core();
    L26:
        int r0 = BufferedChannelKt.SEGMENT_SIZE;
        int r25 = r6;
    L27:
        if (r25 >= r0) goto L87;
        long r82 = (r33.id * BufferedChannelKt.SEGMENT_SIZE) + r25;
        if (r82 >= r12) goto L31;
    L32:
        Object r14 = r33.getState$kotlinx_coroutines_core(r25);
        Object r15 = r33.getElement$kotlinx_coroutines_core(r25);
        if ((r14 instanceof CancellableContinuation) == false) goto L45;
        if (r12 > r82) goto L40;
        if (r82 >= r102) goto L40;
        String r43 = "receive";
    L83:
        if (r15 == null) goto L85;
        r1.append("(" + r43 + ',' + r15 + "),");
    L86:
        r25 = r25 + 1;
        goto L27
    L85:
        r1.append(r43 + ',');
    L40:
        if (r102 > r82) goto L43;
        if (r82 >= r12) goto L43;
        r43 = "send";
    L43:
        r43 = "cont";
        goto L83
    L45:
        if ((r14 instanceof SelectInstance) == false) goto L57;
        if (r12 > r82) goto L52;
        if (r82 >= r102) goto L52;
        r43 = "onReceive";
    L52:
        if (r102 > r82) goto L55;
        if (r82 >= r12) goto L55;
        r43 = "onSend";
    L55:
        r43 = "select";
        goto L83
    L57:
        if ((r14 instanceof ReceiveCatching) == false) goto L60;
        r43 = "receiveCatching";
        goto L83
    L60:
        if ((r14 instanceof WaiterEB) == false) goto L63;
        r43 = "EB(" + r14 + ')';
        goto L83
    L63:
        if (Intrinsics.areEqual(r14, BufferedChannelKt.access$getRESUMING_BY_RCV$p()) == false) goto L65;
    L82:
        r43 = "resuming_sender";
        goto L83
    L65:
        if (Intrinsics.areEqual(r14, BufferedChannelKt.access$getRESUMING_BY_EB$p()) == true) goto L82;
        if (r14 == null) goto L86;
        if (Intrinsics.areEqual(r14, BufferedChannelKt.access$getIN_BUFFER$p()) == true) goto L86;
        if (Intrinsics.areEqual(r14, BufferedChannelKt.access$getDONE_RCV$p()) == true) goto L86;
        if (Intrinsics.areEqual(r14, BufferedChannelKt.access$getPOISONED$p()) == true) goto L86;
        if (Intrinsics.areEqual(r14, BufferedChannelKt.access$getINTERRUPTED_RCV$p()) == true) goto L86;
        if (Intrinsics.areEqual(r14, BufferedChannelKt.access$getINTERRUPTED_SEND$p()) == true) goto L86;
        if (Intrinsics.areEqual(r14, BufferedChannelKt.getCHANNEL_CLOSED()) == true) goto L86;
        r43 = r14.toString();
        goto L83
    L31:
        if (r82 < r102) goto L32;
    L90:
        if (StringsKt.last(r1) != ',') goto L92;
        r1.deleteCharAt(r1.length() - 1);
    L92:
        r1.append("]");
        return r1.toString();
    L87:
        r33 = (ChannelSegment) r33.getNext();
        if (r33 == null) goto L90;
        r6 = 0;
        goto L26
    L95:
        Utils$$ExternalSyntheticBUOutline0.m();
        return null;
    L7:
        r1.append("closed,");
        goto L8
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.channels.ReceiveChannel
    /* renamed from: tryReceive-PtdJZtk, reason: not valid java name */
    public Object mo3531tryReceivePtdJZtk() {
        long r0 = getReceivers$volatile$FU().get(this);
        long r2 = getSendersAndCloseStatus$volatile$FU().get(this);
        if (isClosedForReceive0(r2) == false) goto L7;
        return ChannelResult.Companion.m3545closedJP2dKIU(getCloseCause());
    L7:
        if (r0 >= (r2 & 1152921504606846975L)) goto L9;
        Object r5 = BufferedChannelKt.access$getINTERRUPTED_RCV$p();
        ChannelSegment r02 = (ChannelSegment) access$getReceiveSegment$volatile$FU().get(this);
    L12:
        if (this.isClosedForReceive() == true) goto L14;
        long r3 = access$getReceivers$volatile$FU().getAndIncrement(this);
        int r1 = BufferedChannelKt.SEGMENT_SIZE;
        long r6 = r3 / r1;
        int r22 = (int) (r3 % r1);
        if (r02.id == r6) goto L21;
        ChannelSegment r12 = access$findSegmentReceive(this, r6, r02);
        if (r12 == null) goto L12;
    L20:
        BufferedChannel<E> r03 = this;
        Object r11 = access$updateCellReceive(r03, r12, r22, r3, r5);
        r02 = r12;
        Waiter r7 = null;
        if (r11 == BufferedChannelKt.access$getSUSPEND$p()) goto L25;
        if (r11 != BufferedChannelKt.access$getFAILED$p()) goto L38;
        if (r3 >= r03.getSendersCounter$kotlinx_coroutines_core()) goto L36;
        r02.cleanPrev();
    L36:
        BufferedChannel<E> this = r03;
        goto L12
    L38:
        if (r11 == BufferedChannelKt.access$getSUSPEND_NO_WAITER$p()) goto L41;
        r02.cleanPrev();
        return ChannelResult.Companion.m3547successJP2dKIU(r11);
    L41:
        Segment$$ExternalSyntheticBUOutline1.m("unexpected");
        return null;
    L25:
        if ((r5 instanceof Waiter) == false) goto L27;
        r7 = (Waiter) r5;
    L27:
        if (r7 == null) goto L29;
        access$prepareReceiverForSuspension(r03, r7, r02, r22);
    L29:
        r03.waitExpandBufferCompletion$kotlinx_coroutines_core(r3);
        r02.onSlotCleaned();
        return ChannelResult.Companion.m3546failurePtdJZtk();
    L21:
        r12 = r02;
        goto L20
    L14:
        return ChannelResult.Companion.m3545closedJP2dKIU(this.getCloseCause());
    L9:
        return ChannelResult.Companion.m3546failurePtdJZtk();
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    /* renamed from: trySend-JP2dKIU, reason: not valid java name */
    public Object mo3532trySendJP2dKIU(E r11) {
        if (shouldSendSuspend(getSendersAndCloseStatus$volatile$FU().get(this)) == true) goto L5;
        Object r6 = BufferedChannelKt.access$getINTERRUPTED_SEND$p();
        ChannelSegment r0 = (ChannelSegment) access$getSendSegment$volatile$FU().get(this);
    L7:
        long r1 = access$getSendersAndCloseStatus$volatile$FU().getAndIncrement(this);
        long r4 = r1 & 1152921504606846975L;
        boolean r7 = access$isClosedForSend0(this, r1);
        int r12 = BufferedChannelKt.SEGMENT_SIZE;
        long r2 = r4 / r12;
        int r13 = (int) (r4 % r12);
        if (r0.id == r2) goto L15;
        ChannelSegment r22 = access$findSegmentSend(this, r2, r0);
        if (r22 == null) goto L11;
        int r23 = r13;
        ChannelSegment r14 = r22;
        BufferedChannel<E> r02 = this;
        E r3 = r11;
    L16:
        int r10 = access$updateCellSend(r02, r14, r23, r3, r4, r6, r7);
        BufferedChannel<E> r112 = r02;
        r0 = r14;
        if (r10 == 0) goto L50;
        if (r10 == 1) goto L49;
        Waiter r8 = null;
        if (r10 == 2) goto L38;
        if (r10 == 3) goto L36;
        if (r10 == 4) goto L32;
        if (r10 != 5) goto L30;
        r0.cleanPrev();
    L30:
        BufferedChannel<E> this = r112;
        r11 = r3;
        goto L7
    L32:
        if (r4 >= r112.getReceiversCounter$kotlinx_coroutines_core()) goto L35;
        r0.cleanPrev();
    L35:
        return ChannelResult.Companion.m3545closedJP2dKIU(r112.getSendException());
    L36:
        Segment$$ExternalSyntheticBUOutline1.m("unexpected");
        return null;
    L38:
        if (r7 == false) goto L42;
        r0.onSlotCleaned();
        return ChannelResult.Companion.m3545closedJP2dKIU(r112.getSendException());
    L42:
        if ((r6 instanceof Waiter) == false) goto L44;
        r8 = (Waiter) r6;
    L44:
        if (r8 == null) goto L46;
        access$prepareSenderForSuspension(r112, r8, r0, r23);
    L46:
        r0.onSlotCleaned();
        return ChannelResult.Companion.m3546failurePtdJZtk();
    L49:
        return ChannelResult.Companion.m3547successJP2dKIU(Unit.INSTANCE);
    L50:
        r0.cleanPrev();
        return ChannelResult.Companion.m3547successJP2dKIU(Unit.INSTANCE);
    L11:
        if (r7 == false) goto L7;
        return ChannelResult.Companion.m3545closedJP2dKIU(this.getSendException());
    L15:
        r3 = r11;
        r23 = r13;
        r14 = r0;
        r02 = this;
        goto L16
    L5:
        return ChannelResult.Companion.m3546failurePtdJZtk();
    }

    public final void waitExpandBufferCompletion$kotlinx_coroutines_core(long r19) {
        BufferedChannel<E> r1 = this;
        if (r1.isRendezvousOrUnlimited() == false) goto L6;
        return;
    L6:
        if (r1.getBufferEndCounter() > r19) goto L7;
        r1 = this;
        goto L6
    L7:
        int r0 = BufferedChannelKt.access$getEXPAND_BUFFER_COMPLETION_WAIT_ITERATIONS$p();
        int r2 = 0;
    L9:
        if (r2 >= r0) goto L16;
        long r3 = r1.getBufferEndCounter();
        if (r3 != (DurationKt.MAX_MILLIS & getCompletedExpandBuffersAndPauseFlag$volatile$FU().get(r1))) goto L15;
        if (r3 != r1.getBufferEndCounter()) goto L15;
        return;
    L15:
        r2 = r2 + 1;
        goto L9
    L16:
        AtomicLongFieldUpdater r02 = getCompletedExpandBuffersAndPauseFlag$volatile$FU();
    L17:
        long r22 = r02.get(r1);
        if (r02.compareAndSet(r1, r22, BufferedChannelKt.access$constructEBCompletedAndPauseFlag(r22 & DurationKt.MAX_MILLIS, true)) == true) goto L19;
        r1 = this;
    L19:
        long r23 = r1.getBufferEndCounter();
        long r4 = getCompletedExpandBuffersAndPauseFlag$volatile$FU().get(r1);
        long r10 = r4 & DurationKt.MAX_MILLIS;
        if ((4611686018427387904L & r4) == 0) goto L22;
        boolean r03 = true;
    L24:
        if (r23 == r10) goto L26;
    L32:
        if (r03 == false) goto L33;
        r1 = this;
        goto L19
    L33:
        r1 = this;
        getCompletedExpandBuffersAndPauseFlag$volatile$FU().compareAndSet(r1, r4, BufferedChannelKt.access$constructEBCompletedAndPauseFlag(r10, true));
        goto L19
    L26:
        if (r23 != r1.getBufferEndCounter()) goto L32;
        AtomicLongFieldUpdater r04 = getCompletedExpandBuffersAndPauseFlag$volatile$FU();
    L28:
        long r24 = r04.get(r1);
        boolean r25 = r04.compareAndSet(r1, r24, BufferedChannelKt.access$constructEBCompletedAndPauseFlag(r24 & DurationKt.MAX_MILLIS, false));
        AtomicLongFieldUpdater r12 = r04;
        if (r25 == true) goto L51;
        r04 = r12;
        r1 = this;
        goto L28
    L51:
        return;
    L22:
        r03 = false;
        goto L24
    }
}
