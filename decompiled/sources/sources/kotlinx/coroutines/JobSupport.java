package kotlinx.coroutines;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.time.DurationKt$$ExternalSyntheticBUOutline0;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.selects.SelectClause0;
import kotlinx.coroutines.selects.SelectClause0Impl;
import kotlinx.coroutines.selects.SelectClause1;
import kotlinx.coroutines.selects.SelectClause1Impl;
import kotlinx.coroutines.selects.SelectInstance;
import okhttp3.internal.url._UrlKt;
import okio.ByteString$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.mvel2.asm.Constants$$ExternalSyntheticBUOutline0;

@Deprecated(level = DeprecationLevel.ERROR, message = "This is internal API and may be removed in the future releases")
@Metadata(d1 = {"\u0000Ö\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\b\u0017\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003:\nÃ\u0001Ä\u0001Å\u0001Æ\u0001Ç\u0001B\u000f\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J#\u0010\f\u001a\u0004\u0018\u00010\n2\u0006\u0010\t\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0002¢\u0006\u0004\b\f\u0010\rJ'\u0010\u0011\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\t\u001a\u00020\b2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0002¢\u0006\u0004\b\u0011\u0010\u0012J%\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0002¢\u0006\u0004\b\u0015\u0010\u0016J!\u0010\u0019\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\nH\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ!\u0010\u001b\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\nH\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ\u001f\u0010 \u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u000fH\u0002¢\u0006\u0004\b \u0010!J\u0017\u0010\"\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\"\u0010#J\u001d\u0010$\u001a\u00020\u0014*\u00020\u001d2\b\u0010\u001f\u001a\u0004\u0018\u00010\u000fH\u0002¢\u0006\u0004\b$\u0010!J\u0019\u0010&\u001a\u00020%2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002¢\u0006\u0004\b&\u0010'J\u0017\u0010)\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020(H\u0002¢\u0006\u0004\b)\u0010*J\u0017\u0010,\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020+H\u0002¢\u0006\u0004\b,\u0010-J\u000f\u0010.\u001a\u00020\u0004H\u0002¢\u0006\u0004\b.\u0010/J\u0010\u00100\u001a\u00020\u0014H\u0082@¢\u0006\u0004\b0\u00101J%\u00105\u001a\u00020\u00142\n\u00103\u001a\u0006\u0012\u0002\b\u0003022\b\u00104\u001a\u0004\u0018\u00010\nH\u0002¢\u0006\u0004\b5\u00106J\u001b\u00107\u001a\u0004\u0018\u00010\n2\b\u0010\u001f\u001a\u0004\u0018\u00010\nH\u0002¢\u0006\u0004\b7\u00108J\u0019\u00109\u001a\u00020\u000f2\b\u0010\u001f\u001a\u0004\u0018\u00010\nH\u0002¢\u0006\u0004\b9\u0010:J\u001b\u0010;\u001a\u0004\u0018\u00010\n2\b\u0010\u001f\u001a\u0004\u0018\u00010\nH\u0002¢\u0006\u0004\b;\u00108J\u0019\u0010<\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\t\u001a\u00020\u0017H\u0002¢\u0006\u0004\b<\u0010=J\u001f\u0010>\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00172\u0006\u0010\u0013\u001a\u00020\u000fH\u0002¢\u0006\u0004\b>\u0010?J%\u0010@\u001a\u0004\u0018\u00010\n2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0002¢\u0006\u0004\b@\u0010AJ#\u0010B\u001a\u0004\u0018\u00010\n2\u0006\u0010\t\u001a\u00020\u00172\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0002¢\u0006\u0004\bB\u0010CJ*\u0010F\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\b2\u0006\u0010E\u001a\u00020D2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0082\u0010¢\u0006\u0004\bF\u0010GJ)\u0010I\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\b2\u0006\u0010H\u001a\u00020D2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0002¢\u0006\u0004\bI\u0010JJ\u0015\u0010L\u001a\u0004\u0018\u00010D*\u00020KH\u0002¢\u0006\u0004\bL\u0010MJ\u0019\u0010O\u001a\u00020N2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002¢\u0006\u0004\bO\u0010PJ\u0012\u0010Q\u001a\u0004\u0018\u00010\nH\u0082@¢\u0006\u0004\bQ\u00101J%\u0010R\u001a\u00020\u00142\n\u00103\u001a\u0006\u0012\u0002\b\u0003022\b\u00104\u001a\u0004\u0018\u00010\nH\u0002¢\u0006\u0004\bR\u00106J%\u0010T\u001a\u0004\u0018\u00010\n2\b\u00104\u001a\u0004\u0018\u00010\n2\b\u0010S\u001a\u0004\u0018\u00010\nH\u0002¢\u0006\u0004\bT\u0010AJ\u0019\u0010V\u001a\u00020\u00142\b\u0010U\u001a\u0004\u0018\u00010\u0001H\u0004¢\u0006\u0004\bV\u0010WJ\r\u0010X\u001a\u00020\u0004¢\u0006\u0004\bX\u0010/J\u000f\u0010Y\u001a\u00020\u0014H\u0014¢\u0006\u0004\bY\u0010ZJ\u0011\u0010]\u001a\u00060[j\u0002`\\¢\u0006\u0004\b]\u0010^J#\u0010`\u001a\u00060[j\u0002`\\*\u00020\u000f2\n\b\u0002\u0010_\u001a\u0004\u0018\u00010NH\u0004¢\u0006\u0004\b`\u0010aJ6\u0010h\u001a\u00020g2'\u0010f\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u000f¢\u0006\f\bc\u0012\b\bd\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020\u00140bj\u0002`e¢\u0006\u0004\bh\u0010iJF\u0010h\u001a\u00020g2\u0006\u0010j\u001a\u00020\u00042\u0006\u0010k\u001a\u00020\u00042'\u0010f\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u000f¢\u0006\f\bc\u0012\b\bd\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020\u00140bj\u0002`e¢\u0006\u0004\bh\u0010lJ\u001f\u0010p\u001a\u00020g2\u0006\u0010k\u001a\u00020\u00042\u0006\u0010m\u001a\u00020+H\u0000¢\u0006\u0004\bn\u0010oJ\u0010\u0010q\u001a\u00020\u0014H\u0086@¢\u0006\u0004\bq\u00101J\u0017\u0010s\u001a\u00020\u00142\u0006\u0010m\u001a\u00020+H\u0000¢\u0006\u0004\br\u0010-J\u001f\u0010t\u001a\u00020\u00142\u000e\u0010\u001f\u001a\n\u0018\u00010[j\u0004\u0018\u0001`\\H\u0016¢\u0006\u0004\bt\u0010uJ\u000f\u0010v\u001a\u00020NH\u0014¢\u0006\u0004\bv\u0010wJ\u0017\u0010x\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u000fH\u0016¢\u0006\u0004\bx\u0010yJ\u0015\u0010{\u001a\u00020\u00142\u0006\u0010z\u001a\u00020\u0003¢\u0006\u0004\b{\u0010|J\u0017\u0010}\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u000fH\u0016¢\u0006\u0004\b}\u0010#J\u0017\u0010~\u001a\u00020\u00042\b\u0010\u001f\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b~\u0010#J\u001b\u0010\u0081\u0001\u001a\u00020\u00042\b\u0010\u001f\u001a\u0004\u0018\u00010\nH\u0000¢\u0006\u0005\b\u007f\u0010\u0080\u0001J\u0015\u0010\u0082\u0001\u001a\u00060[j\u0002`\\H\u0016¢\u0006\u0005\b\u0082\u0001\u0010^J\u001c\u0010\u0084\u0001\u001a\u00020\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0000¢\u0006\u0006\b\u0083\u0001\u0010\u0080\u0001J\u001d\u0010\u0086\u0001\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0000¢\u0006\u0005\b\u0085\u0001\u00108J\u0019\u0010\u0088\u0001\u001a\u00030\u0087\u00012\u0006\u0010E\u001a\u00020\u0002¢\u0006\u0006\b\u0088\u0001\u0010\u0089\u0001J\u001a\u0010\u008c\u0001\u001a\u00020\u00142\u0007\u0010\u008a\u0001\u001a\u00020\u000fH\u0010¢\u0006\u0005\b\u008b\u0001\u0010yJ\u0019\u0010j\u001a\u00020\u00142\b\u0010\u001f\u001a\u0004\u0018\u00010\u000fH\u0014¢\u0006\u0004\bj\u0010yJ\u001a\u0010\u008d\u0001\u001a\u00020\u00042\u0007\u0010\u008a\u0001\u001a\u00020\u000fH\u0014¢\u0006\u0005\b\u008d\u0001\u0010#J\u001c\u0010\u008e\u0001\u001a\u00020\u00142\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0014¢\u0006\u0006\b\u008e\u0001\u0010\u008f\u0001J\u001c\u0010\u0090\u0001\u001a\u00020\u00142\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0014¢\u0006\u0006\b\u0090\u0001\u0010\u008f\u0001J\u0011\u0010\u0091\u0001\u001a\u00020NH\u0016¢\u0006\u0005\b\u0091\u0001\u0010wJ\u0011\u0010\u0092\u0001\u001a\u00020NH\u0007¢\u0006\u0005\b\u0092\u0001\u0010wJ\u0011\u0010\u0094\u0001\u001a\u00020NH\u0010¢\u0006\u0005\b\u0093\u0001\u0010wJ\u0012\u0010\u0095\u0001\u001a\u0004\u0018\u00010\u000f¢\u0006\u0006\b\u0095\u0001\u0010\u0096\u0001J\u0014\u0010\u0099\u0001\u001a\u0004\u0018\u00010\nH\u0000¢\u0006\u0006\b\u0097\u0001\u0010\u0098\u0001J\u0014\u0010\u009a\u0001\u001a\u0004\u0018\u00010\nH\u0084@¢\u0006\u0005\b\u009a\u0001\u00101R\u001e\u0010\u009c\u0001\u001a\u0004\u0018\u00010\u000f*\u0004\u0018\u00010\n8BX\u0082\u0004¢\u0006\u0007\u001a\u0005\b\u009b\u0001\u0010:R\u0019\u0010 \u0001\u001a\u0007\u0012\u0002\b\u00030\u009d\u00018F¢\u0006\b\u001a\u0006\b\u009e\u0001\u0010\u009f\u0001R0\u0010¦\u0001\u001a\u0005\u0018\u00010\u0087\u00012\n\u0010¡\u0001\u001a\u0005\u0018\u00010\u0087\u00018@@@X\u0080\u000e¢\u0006\u0010\u001a\u0006\b¢\u0001\u0010£\u0001\"\u0006\b¤\u0001\u0010¥\u0001R\u0018\u0010U\u001a\u0004\u0018\u00010\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\b§\u0001\u0010¨\u0001R\u0018\u0010\t\u001a\u0004\u0018\u00010\n8@X\u0080\u0004¢\u0006\b\u001a\u0006\b©\u0001\u0010\u0098\u0001R\u0016\u0010ª\u0001\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bª\u0001\u0010/R\u0013\u0010«\u0001\u001a\u00020\u00048F¢\u0006\u0007\u001a\u0005\b«\u0001\u0010/R\u0013\u0010¬\u0001\u001a\u00020\u00048F¢\u0006\u0007\u001a\u0005\b¬\u0001\u0010/R\u001c\u0010±\u0001\u001a\u00030\u00ad\u00018F¢\u0006\u000f\u0012\u0005\b°\u0001\u0010Z\u001a\u0006\b®\u0001\u0010¯\u0001R\u0016\u0010³\u0001\u001a\u00020\u00048PX\u0090\u0004¢\u0006\u0007\u001a\u0005\b²\u0001\u0010/R\u001b\u0010·\u0001\u001a\t\u0012\u0004\u0012\u00020\u00010´\u00018F¢\u0006\b\u001a\u0006\bµ\u0001\u0010¶\u0001R\u0016\u0010¸\u0001\u001a\u00020\u00048TX\u0094\u0004¢\u0006\u0007\u001a\u0005\b¸\u0001\u0010/R\u0016\u0010º\u0001\u001a\u00020\u00048PX\u0090\u0004¢\u0006\u0007\u001a\u0005\b¹\u0001\u0010/R#\u0010¿\u0001\u001a\u0007\u0012\u0002\b\u00030»\u00018DX\u0084\u0004¢\u0006\u000f\u0012\u0005\b¾\u0001\u0010Z\u001a\u0006\b¼\u0001\u0010½\u0001R\u0015\u0010Á\u0001\u001a\u000b\u0012\u0006\u0012\u0004\u0018\u00010\n0À\u00018\u0002X\u0082\u0004R\u0016\u0010Â\u0001\u001a\f\u0012\u0007\u0012\u0005\u0018\u00010\u0087\u00010À\u00018\u0002X\u0082\u0004¨\u0006È\u0001"}, d2 = {"Lkotlinx/coroutines/JobSupport;", "Lkotlinx/coroutines/Job;", "Lkotlinx/coroutines/ChildJob;", "Lkotlinx/coroutines/ParentJob;", _UrlKt.FRAGMENT_ENCODE_SET, "active", "<init>", "(Z)V", "Lkotlinx/coroutines/JobSupport$Finishing;", "state", _UrlKt.FRAGMENT_ENCODE_SET, "proposedUpdate", "finalizeFinishingState", "(Lkotlinx/coroutines/JobSupport$Finishing;Ljava/lang/Object;)Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "exceptions", "getFinalRootCause", "(Lkotlinx/coroutines/JobSupport$Finishing;Ljava/util/List;)Ljava/lang/Throwable;", "rootCause", _UrlKt.FRAGMENT_ENCODE_SET, "addSuppressedExceptions", "(Ljava/lang/Throwable;Ljava/util/List;)V", "Lkotlinx/coroutines/Incomplete;", PluginsConstants.UPDATE, "tryFinalizeSimpleState", "(Lkotlinx/coroutines/Incomplete;Ljava/lang/Object;)Z", "completeStateFinalization", "(Lkotlinx/coroutines/Incomplete;Ljava/lang/Object;)V", "Lkotlinx/coroutines/NodeList;", "list", "cause", "notifyCancelling", "(Lkotlinx/coroutines/NodeList;Ljava/lang/Throwable;)V", "cancelParent", "(Ljava/lang/Throwable;)Z", "notifyCompletion", _UrlKt.FRAGMENT_ENCODE_SET, "startInternal", "(Ljava/lang/Object;)I", "Lkotlinx/coroutines/Empty;", "promoteEmptyToNodeList", "(Lkotlinx/coroutines/Empty;)V", "Lkotlinx/coroutines/JobNode;", "promoteSingleToNodeList", "(Lkotlinx/coroutines/JobNode;)V", "joinInternal", "()Z", "joinSuspend", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "ignoredParam", "registerSelectForOnJoin", "(Lkotlinx/coroutines/selects/SelectInstance;Ljava/lang/Object;)V", "cancelMakeCompleting", "(Ljava/lang/Object;)Ljava/lang/Object;", "createCauseException", "(Ljava/lang/Object;)Ljava/lang/Throwable;", "makeCancelling", "getOrPromoteCancellingList", "(Lkotlinx/coroutines/Incomplete;)Lkotlinx/coroutines/NodeList;", "tryMakeCancelling", "(Lkotlinx/coroutines/Incomplete;Ljava/lang/Throwable;)Z", "tryMakeCompleting", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "tryMakeCompletingSlowPath", "(Lkotlinx/coroutines/Incomplete;Ljava/lang/Object;)Ljava/lang/Object;", "Lkotlinx/coroutines/ChildHandleNode;", "child", "tryWaitForChild", "(Lkotlinx/coroutines/JobSupport$Finishing;Lkotlinx/coroutines/ChildHandleNode;Ljava/lang/Object;)Z", "lastChild", "continueCompleting", "(Lkotlinx/coroutines/JobSupport$Finishing;Lkotlinx/coroutines/ChildHandleNode;Ljava/lang/Object;)V", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "nextChild", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)Lkotlinx/coroutines/ChildHandleNode;", _UrlKt.FRAGMENT_ENCODE_SET, "stateString", "(Ljava/lang/Object;)Ljava/lang/String;", "awaitSuspend", "onAwaitInternalRegFunc", "result", "onAwaitInternalProcessResFunc", "parent", "initParentJob", "(Lkotlinx/coroutines/Job;)V", "start", "onStart", "()V", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "getCancellationException", "()Ljava/util/concurrent/CancellationException;", "message", "toCancellationException", "(Ljava/lang/Throwable;Ljava/lang/String;)Ljava/util/concurrent/CancellationException;", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "Lkotlinx/coroutines/CompletionHandler;", "handler", "Lkotlinx/coroutines/DisposableHandle;", "invokeOnCompletion", "(Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/DisposableHandle;", "onCancelling", "invokeImmediately", "(ZZLkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/DisposableHandle;", "node", "invokeOnCompletionInternal$kotlinx_coroutines_core", "(ZLkotlinx/coroutines/JobNode;)Lkotlinx/coroutines/DisposableHandle;", "invokeOnCompletionInternal", "join", "removeNode$kotlinx_coroutines_core", "removeNode", "cancel", "(Ljava/util/concurrent/CancellationException;)V", "cancellationExceptionMessage", "()Ljava/lang/String;", "cancelInternal", "(Ljava/lang/Throwable;)V", "parentJob", "parentCancelled", "(Lkotlinx/coroutines/ParentJob;)V", "childCancelled", "cancelCoroutine", "cancelImpl$kotlinx_coroutines_core", "(Ljava/lang/Object;)Z", "cancelImpl", "getChildJobCancellationCause", "makeCompleting$kotlinx_coroutines_core", "makeCompleting", "makeCompletingOnce$kotlinx_coroutines_core", "makeCompletingOnce", "Lkotlinx/coroutines/ChildHandle;", "attachChild", "(Lkotlinx/coroutines/ChildJob;)Lkotlinx/coroutines/ChildHandle;", "exception", "handleOnCompletionException$kotlinx_coroutines_core", "handleOnCompletionException", "handleJobException", "onCompletionInternal", "(Ljava/lang/Object;)V", "afterCompletion", "toString", "toDebugString", "nameString$kotlinx_coroutines_core", "nameString", "getCompletionExceptionOrNull", "()Ljava/lang/Throwable;", "getCompletedInternal$kotlinx_coroutines_core", "()Ljava/lang/Object;", "getCompletedInternal", "awaitInternal", "getExceptionOrNull", "exceptionOrNull", "Lkotlin/coroutines/CoroutineContext$Key;", "getKey", "()Lkotlin/coroutines/CoroutineContext$Key;", PluginsConstants.Settings.KEY, "value", "getParentHandle$kotlinx_coroutines_core", "()Lkotlinx/coroutines/ChildHandle;", "setParentHandle$kotlinx_coroutines_core", "(Lkotlinx/coroutines/ChildHandle;)V", "parentHandle", "getParent", "()Lkotlinx/coroutines/Job;", "getState$kotlinx_coroutines_core", "isActive", "isCompleted", "isCancelled", "Lkotlinx/coroutines/selects/SelectClause0;", "getOnJoin", "()Lkotlinx/coroutines/selects/SelectClause0;", "getOnJoin$annotations", "onJoin", "getOnCancelComplete$kotlinx_coroutines_core", "onCancelComplete", "Lkotlin/sequences/Sequence;", "getChildren", "()Lkotlin/sequences/Sequence;", "children", "isScopedCoroutine", "getHandlesException$kotlinx_coroutines_core", "handlesException", "Lkotlinx/coroutines/selects/SelectClause1;", "getOnAwaitInternal", "()Lkotlinx/coroutines/selects/SelectClause1;", "getOnAwaitInternal$annotations", "onAwaitInternal", "Lkotlinx/atomicfu/AtomicRef;", "_state", "_parentHandle", "SelectOnJoinCompletionHandler", "Finishing", "ChildCompletion", "AwaitContinuation", "SelectOnAwaitCompletionHandler", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nJobSupport.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JobSupport.kt\nkotlinx/coroutines/JobSupport\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Synchronized.common.kt\nkotlinx/coroutines/internal/Synchronized_commonKt\n+ 4 Synchronized.kt\nkotlinx/coroutines/internal/SynchronizedKt\n+ 5 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 6 Concurrent.kt\nkotlinx/coroutines/internal/ConcurrentKt\n+ 7 StackTraceRecovery.kt\nkotlinx/coroutines/internal/StackTraceRecoveryKt\n+ 8 LockFreeLinkedList.kt\nkotlinx/coroutines/internal/LockFreeLinkedListHead\n+ 9 CancellableContinuation.kt\nkotlinx/coroutines/CancellableContinuationKt\n*L\n1#1,1583:1\n733#1,2:1587\n361#1,2:1596\n363#1,5:1601\n368#1,5:1607\n373#1,2:1615\n361#1,2:1617\n363#1,5:1622\n368#1,5:1628\n373#1,2:1636\n169#1,2:1644\n734#1:1646\n536#1:1647\n169#1,2:1648\n537#1,15:1650\n169#1,2:1665\n169#1,2:1667\n169#1,2:1679\n733#1,2:1681\n733#1,2:1683\n169#1,2:1685\n733#1,2:1687\n169#1,2:1689\n169#1,2:1693\n169#1,2:1695\n536#1:1699\n169#1,2:1700\n537#1,15:1702\n1#2:1584\n1#2:1606\n1#2:1627\n26#3:1585\n26#3:1691\n26#3:1697\n16#4:1586\n16#4:1692\n16#4:1698\n295#5,2:1589\n295#5,2:1591\n23#6:1593\n159#7:1594\n159#7:1595\n149#7,4:1717\n273#8,3:1598\n276#8,3:1612\n273#8,3:1619\n276#8,3:1633\n273#8,6:1638\n433#9,10:1669\n*S KotlinDebug\n*F\n+ 1 JobSupport.kt\nkotlinx/coroutines/JobSupport\n*L\n241#1:1587,2\n324#1:1596,2\n324#1:1601,5\n324#1:1607,5\n324#1:1615,2\n357#1:1617,2\n357#1:1622,5\n357#1:1628,5\n357#1:1636,2\n377#1:1644,2\n422#1:1646\n468#1:1647\n468#1:1648,2\n468#1:1650,15\n536#1:1665,2\n579#1:1667,2\n621#1:1679,2\n648#1:1681,2\n657#1:1683,2\n721#1:1685,2\n750#1:1687,2\n763#1:1689,2\n836#1:1693,2\n858#1:1695,2\n1023#1:1699\n1023#1:1700,2\n1023#1:1702,15\n324#1:1606\n357#1:1627\n204#1:1585\n766#1:1691\n911#1:1697\n204#1:1586\n766#1:1692\n911#1:1698\n252#1:1589,2\n256#1:1591,2\n264#1:1593\n270#1:1594\n272#1:1595\n1327#1:1717,4\n324#1:1598,3\n324#1:1612,3\n357#1:1619,3\n357#1:1633,3\n362#1:1638,6\n585#1:1669,10\n*E\n"})
/* loaded from: classes.dex */
public class JobSupport implements Job, ChildJob, ParentJob {
    private static final /* synthetic */ AtomicReferenceFieldUpdater _parentHandle$volatile$FU = null;
    private static final /* synthetic */ AtomicReferenceFieldUpdater _state$volatile$FU = null;
    private volatile /* synthetic */ Object _parentHandle$volatile;
    private volatile /* synthetic */ Object _state$volatile;

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001d\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lkotlinx/coroutines/JobSupport$AwaitContinuation;", "T", "Lkotlinx/coroutines/CancellableContinuationImpl;", "delegate", "Lkotlin/coroutines/Continuation;", "job", "Lkotlinx/coroutines/JobSupport;", "<init>", "(Lkotlin/coroutines/Continuation;Lkotlinx/coroutines/JobSupport;)V", "getContinuationCancellationCause", _UrlKt.FRAGMENT_ENCODE_SET, "parent", "Lkotlinx/coroutines/Job;", "nameString", _UrlKt.FRAGMENT_ENCODE_SET, "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nJobSupport.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JobSupport.kt\nkotlinx/coroutines/JobSupport$AwaitContinuation\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1583:1\n1#2:1584\n*E\n"})
    public static final class AwaitContinuation<T> extends CancellableContinuationImpl<T> {
        private final JobSupport job;

        public AwaitContinuation(Continuation<? super T> r2, JobSupport r3) {
            super(r2, 1);
            this.job = r3;
        }

        @Override // kotlinx.coroutines.CancellableContinuationImpl
        public Throwable getContinuationCancellationCause(Job r2) {
            Object r1 = this.job.getState$kotlinx_coroutines_core();
            if ((r1 instanceof Finishing) == false) goto L8;
            Throwable r0 = ((Finishing) r1).getRootCause();
            if (r0 == null) goto L8;
            return r0;
        L8:
            if ((r1 instanceof CompletedExceptionally) == false) goto L12;
            return ((CompletedExceptionally) r1).cause;
        L12:
            return r2.getCancellationException();
        }

        @Override // kotlinx.coroutines.CancellableContinuationImpl
        public String nameString() {
            return "AwaitContinuation";
        }
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\b\u0002\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bJ\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0014"}, d2 = {"Lkotlinx/coroutines/JobSupport$ChildCompletion;", "Lkotlinx/coroutines/JobNode;", "parent", "Lkotlinx/coroutines/JobSupport;", "state", "Lkotlinx/coroutines/JobSupport$Finishing;", "child", "Lkotlinx/coroutines/ChildHandleNode;", "proposedUpdate", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lkotlinx/coroutines/JobSupport;Lkotlinx/coroutines/JobSupport$Finishing;Lkotlinx/coroutines/ChildHandleNode;Ljava/lang/Object;)V", "onCancelling", _UrlKt.FRAGMENT_ENCODE_SET, "getOnCancelling", "()Z", "invoke", _UrlKt.FRAGMENT_ENCODE_SET, "cause", _UrlKt.FRAGMENT_ENCODE_SET, "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes5.dex */
    public static final class ChildCompletion extends JobNode {
        private final ChildHandleNode child;
        private final JobSupport parent;
        private final Object proposedUpdate;
        private final Finishing state;

        public ChildCompletion(JobSupport r1, Finishing r2, ChildHandleNode r3, Object r4) {
            this.parent = r1;
            this.state = r2;
            this.child = r3;
            this.proposedUpdate = r4;
        }

        @Override // kotlinx.coroutines.JobNode
        public boolean getOnCancelling() {
            return false;
        }

        @Override // kotlinx.coroutines.JobNode
        public void invoke(Throwable r3) {
            JobSupport.access$continueCompleting(this.parent, this.state, this.child, this.proposedUpdate);
        }
    }

    @Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u00022\u00020\u0003B!\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bJ\u0016\u0010#\u001a\b\u0012\u0004\u0012\u00020\t0$2\b\u0010%\u001a\u0004\u0018\u00010\tJ\u000e\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\tJ\u0018\u0010)\u001a\u0012\u0012\u0004\u0012\u00020\t0*j\b\u0012\u0004\u0012\u00020\t`+H\u0002J\b\u0010,\u001a\u00020-H\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\t\u0010\u000e\u001a\u00020\u000fX\u0082\u0004R$\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00078F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0006\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0015X\u0082\u0004R(\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\u0010\u001a\u0004\u0018\u00010\t8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0011\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0015X\u0082\u0004R(\u0010\u001b\u001a\u0004\u0018\u00010\u00012\b\u0010\u0010\u001a\u0004\u0018\u00010\u00018B@BX\u0082\u000e¢\u0006\f\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u0011\u0010 \u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b \u0010\u0011R\u0011\u0010!\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b!\u0010\u0011R\u0014\u0010\"\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010\u0011¨\u0006."}, d2 = {"Lkotlinx/coroutines/JobSupport$Finishing;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/internal/SynchronizedObject;", "Lkotlinx/coroutines/Incomplete;", "list", "Lkotlinx/coroutines/NodeList;", "isCompleting", _UrlKt.FRAGMENT_ENCODE_SET, "rootCause", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lkotlinx/coroutines/NodeList;ZLjava/lang/Throwable;)V", "getList", "()Lkotlinx/coroutines/NodeList;", "_isCompleting", "Lkotlinx/atomicfu/AtomicBoolean;", "value", "()Z", "setCompleting", "(Z)V", "_rootCause", "Lkotlinx/atomicfu/AtomicRef;", "getRootCause", "()Ljava/lang/Throwable;", "setRootCause", "(Ljava/lang/Throwable;)V", "_exceptionsHolder", "exceptionsHolder", "getExceptionsHolder", "()Ljava/lang/Object;", "setExceptionsHolder", "(Ljava/lang/Object;)V", "isSealed", "isCancelling", "isActive", "sealLocked", _UrlKt.FRAGMENT_ENCODE_SET, "proposedException", "addExceptionLocked", _UrlKt.FRAGMENT_ENCODE_SET, "exception", "allocateList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nJobSupport.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JobSupport.kt\nkotlinx/coroutines/JobSupport$Finishing\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1583:1\n1#2:1584\n*E\n"})
    public static final class Finishing implements Incomplete {
        private static final /* synthetic */ AtomicReferenceFieldUpdater _exceptionsHolder$volatile$FU = null;
        private static final /* synthetic */ AtomicIntegerFieldUpdater _isCompleting$volatile$FU = null;
        private static final /* synthetic */ AtomicReferenceFieldUpdater _rootCause$volatile$FU = null;
        private volatile /* synthetic */ Object _exceptionsHolder$volatile;
        private volatile /* synthetic */ int _isCompleting$volatile;
        private volatile /* synthetic */ Object _rootCause$volatile;
        private final NodeList list;

        static {
            _isCompleting$volatile$FU = AtomicIntegerFieldUpdater.newUpdater(Finishing.class, "_isCompleting$volatile");
            _rootCause$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(Finishing.class, Object.class, "_rootCause$volatile");
            _exceptionsHolder$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(Finishing.class, Object.class, "_exceptionsHolder$volatile");
        }

        public Finishing(NodeList r1, boolean r2, Throwable r3) {
            this.list = r1;
            this._isCompleting$volatile = r2 ? 1 : 0;
            this._rootCause$volatile = r3;
        }

        private final ArrayList<Throwable> allocateList() {
            return new ArrayList(4);
        }

        private final Object getExceptionsHolder() {
            return get_exceptionsHolder$volatile$FU().get(this);
        }

        private static final /* synthetic */ AtomicReferenceFieldUpdater get_exceptionsHolder$volatile$FU() {
            return _exceptionsHolder$volatile$FU;
        }

        private static final /* synthetic */ AtomicIntegerFieldUpdater get_isCompleting$volatile$FU() {
            return _isCompleting$volatile$FU;
        }

        private static final /* synthetic */ AtomicReferenceFieldUpdater get_rootCause$volatile$FU() {
            return _rootCause$volatile$FU;
        }

        private final void setExceptionsHolder(Object r2) {
            get_exceptionsHolder$volatile$FU().set(this, r2);
        }

        public final void addExceptionLocked(Throwable r3) {
            Throwable r0 = getRootCause();
            if (r0 != null) goto L6;
            setRootCause(r3);
            return;
        L6:
            if (r3 == r0) goto L24;
            Object r02 = getExceptionsHolder();
            if (r02 != null) goto L13;
            setExceptionsHolder(r3);
            return;
        L13:
            if ((r02 instanceof Throwable) == false) goto L19;
            if (r3 != r02) goto L16;
            return;
        L16:
            ArrayList<Throwable> r1 = allocateList();
            r1.add(r02);
            r1.add(r3);
            setExceptionsHolder(r1);
            return;
        L19:
            if ((r02 instanceof ArrayList) == false) goto L22;
            ((ArrayList) r02).add(r3);
            return;
        L22:
            DurationKt$$ExternalSyntheticBUOutline0.m("State is ", r02);
            return;
        }

        @Override // kotlinx.coroutines.Incomplete
        public NodeList getList() {
            return this.list;
        }

        public final Throwable getRootCause() {
            return (Throwable) get_rootCause$volatile$FU().get(this);
        }

        @Override // kotlinx.coroutines.Incomplete
        public boolean isActive() {
            if (getRootCause() != null) goto L6;
            return true;
        L6:
            return false;
        }

        public final boolean isCancelling() {
            if (getRootCause() == null) goto L6;
            return true;
        L6:
            return false;
        }

        public final boolean isCompleting() {
            if (get_isCompleting$volatile$FU().get(this) != 1) goto L5;
            return true;
        L5:
            return false;
        }

        public final boolean isSealed() {
            if (getExceptionsHolder() != JobSupportKt.access$getSEALED$p()) goto L6;
            return true;
        L6:
            return false;
        }

        public final List<Throwable> sealLocked(Throwable r4) {
            Object r0 = getExceptionsHolder();
            if (r0 != null) goto L6;
            ArrayList<Throwable> r02 = allocateList();
        L11:
            Throwable r1 = getRootCause();
            if (r1 == null) goto L14;
            r02.add(0, r1);
        L14:
            if (r4 != null) goto L16;
        L18:
            setExceptionsHolder(JobSupportKt.access$getSEALED$p());
            return r02;
        L16:
            if (Intrinsics.areEqual(r4, r1) == true) goto L18;
            r02.add(r4);
            goto L18
        L6:
            if ((r0 instanceof Throwable) == false) goto L9;
            ArrayList<Throwable> r12 = allocateList();
            r12.add(r0);
            r02 = r12;
            goto L11
        L9:
            if ((r0 instanceof ArrayList) == false) goto L20;
            r02 = (ArrayList) r0;
            goto L11
        L20:
            DurationKt$$ExternalSyntheticBUOutline0.m("State is ", r0);
            return null;
        }

        public final void setCompleting(boolean r2) {
            get_isCompleting$volatile$FU().set(this, r2 ? 1 : 0);
        }

        public final void setRootCause(Throwable r2) {
            get_rootCause$volatile$FU().set(this, r2);
        }

        public String toString() {
            return "Finishing[cancelling=" + isCancelling() + ", completing=" + isCompleting() + ", rootCause=" + getRootCause() + ", exceptions=" + getExceptionsHolder() + ", list=" + getList() + ']';
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0013\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016R\u0012\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/JobSupport$SelectOnAwaitCompletionHandler;", "Lkotlinx/coroutines/JobNode;", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "<init>", "(Lkotlinx/coroutines/JobSupport;Lkotlinx/coroutines/selects/SelectInstance;)V", "onCancelling", _UrlKt.FRAGMENT_ENCODE_SET, "getOnCancelling", "()Z", "invoke", _UrlKt.FRAGMENT_ENCODE_SET, "cause", _UrlKt.FRAGMENT_ENCODE_SET, "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes5.dex */
    public final class SelectOnAwaitCompletionHandler extends JobNode {
        private final SelectInstance<?> select;
        final /* synthetic */ JobSupport this$0;

        public SelectOnAwaitCompletionHandler(JobSupport r1, SelectInstance<?> r2) {
            this.this$0 = r1;
            this.select = r2;
        }

        @Override // kotlinx.coroutines.JobNode
        public boolean getOnCancelling() {
            return false;
        }

        @Override // kotlinx.coroutines.JobNode
        public void invoke(Throwable r2) {
            Object r22 = this.this$0.getState$kotlinx_coroutines_core();
            if ((r22 instanceof CompletedExceptionally) == true) goto L6;
            r22 = JobSupportKt.unboxState(r22);
        L6:
            this.select.trySelect(this.this$0, r22);
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0013\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016R\u0012\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/JobSupport$SelectOnJoinCompletionHandler;", "Lkotlinx/coroutines/JobNode;", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "<init>", "(Lkotlinx/coroutines/JobSupport;Lkotlinx/coroutines/selects/SelectInstance;)V", "onCancelling", _UrlKt.FRAGMENT_ENCODE_SET, "getOnCancelling", "()Z", "invoke", _UrlKt.FRAGMENT_ENCODE_SET, "cause", _UrlKt.FRAGMENT_ENCODE_SET, "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes5.dex */
    public final class SelectOnJoinCompletionHandler extends JobNode {
        private final SelectInstance<?> select;
        final /* synthetic */ JobSupport this$0;

        public SelectOnJoinCompletionHandler(JobSupport r1, SelectInstance<?> r2) {
            this.this$0 = r1;
            this.select = r2;
        }

        @Override // kotlinx.coroutines.JobNode
        public boolean getOnCancelling() {
            return false;
        }

        @Override // kotlinx.coroutines.JobNode
        public void invoke(Throwable r2) {
            this.select.trySelect(this.this$0, Unit.INSTANCE);
        }
    }

    static {
        _state$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(JobSupport.class, Object.class, "_state$volatile");
        _parentHandle$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(JobSupport.class, Object.class, "_parentHandle$volatile");
    }

    public JobSupport(boolean r1) {
        if (r1 == false) goto L5;
        Empty r12 = JobSupportKt.access$getEMPTY_ACTIVE$p();
    L6:
        this._state$volatile = r12;
        return;
    L5:
        r12 = JobSupportKt.access$getEMPTY_NEW$p();
        goto L6
    }

    public static final /* synthetic */ String access$cancellationExceptionMessage(JobSupport r0) {
        return r0.cancellationExceptionMessage();
    }

    public static final /* synthetic */ void access$continueCompleting(JobSupport r0, Finishing r1, ChildHandleNode r2, Object r3) {
        r0.continueCompleting(r1, r2, r3);
    }

    public static final /* synthetic */ Object access$onAwaitInternalProcessResFunc(JobSupport r0, Object r1, Object r2) {
        return r0.onAwaitInternalProcessResFunc(r1, r2);
    }

    public static final /* synthetic */ void access$onAwaitInternalRegFunc(JobSupport r0, SelectInstance r1, Object r2) {
        r0.onAwaitInternalRegFunc(r1, r2);
    }

    public static final /* synthetic */ void access$registerSelectForOnJoin(JobSupport r0, SelectInstance r1, Object r2) {
        r0.registerSelectForOnJoin(r1, r2);
    }

    private final void addSuppressedExceptions(Throwable r3, List<? extends Throwable> r4) {
        if (r4.size() <= 1) goto L16;
        Set r2 = Collections.newSetFromMap(new IdentityHashMap(r4.size()));
        Iterator<? extends Throwable> r42 = r4.iterator();
    L7:
        if (r42.hasNext() == false) goto L28;
        Throwable r0 = r42.next();
        if (r0 == r3) goto L7;
        if (r0 == r3) goto L7;
        if ((r0 instanceof CancellationException) == true) goto L7;
        if (r2.add(r0) == false) goto L7;
        kotlin.ExceptionsKt.addSuppressed(r3, r0);
        goto L7
    L28:
        return;
    }

    private final Object awaitSuspend(Continuation<Object> r6) {
        AwaitContinuation r0 = new AwaitContinuation(IntrinsicsKt.intercepted(r6), this);
        r0.initCancellability();
        CancellableContinuationKt.disposeOnCancellation(r0, JobKt.invokeOnCompletion$default(this, false, new ResumeAwaitOnCompletion(r0), 1, null));
        Object r5 = r0.getResult();
        if (r5 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L5;
        DebugProbesKt.probeCoroutineSuspended(r6);
    L5:
        return r5;
    }

    private final Object cancelMakeCompleting(Object r7) {
    L2:
        Object r0 = getState$kotlinx_coroutines_core();
        if ((r0 instanceof Incomplete) == false) goto L13;
        if ((r0 instanceof Finishing) == true) goto L7;
    L9:
        Object r02 = tryMakeCompleting(r0, new CompletedExceptionally(createCauseException(r7), false, 2, null));
        if (r02 == JobSupportKt.access$getCOMPLETING_RETRY$p()) goto L2;
        return r02;
    L7:
        if (((Finishing) r0).isCompleting() == false) goto L9;
    L13:
        return JobSupportKt.access$getCOMPLETING_ALREADY$p();
    }

    private final boolean cancelParent(Throwable r4) {
        if (isScopedCoroutine() == false) goto L5;
        return true;
    L5:
        boolean r0 = r4 instanceof CancellationException;
        ChildHandle r3 = getParentHandle$kotlinx_coroutines_core();
        if (r3 != null) goto L8;
    L17:
        return r0;
    L8:
        if (r3 == NonDisposableHandle.INSTANCE) goto L17;
        if (r3.childCancelled(r4) == true) goto L16;
        if (r0 == true) goto L16;
        return false;
    L16:
        return true;
    }

    private final void completeStateFinalization(Incomplete r4, Object r5) {
        ChildHandle r0 = getParentHandle$kotlinx_coroutines_core();
        if (r0 == null) goto L5;
        r0.dispose();
        setParentHandle$kotlinx_coroutines_core(NonDisposableHandle.INSTANCE);
    L5:
        Throwable r1 = null;
        if ((r5 instanceof CompletedExceptionally) == false) goto L8;
        CompletedExceptionally r52 = (CompletedExceptionally) r5;
    L9:
        if (r52 == null) goto L12;
        r1 = r52.cause;
    L12:
        if ((r4 instanceof JobNode) == true) goto L22;
        NodeList r42 = r4.getList();
        if (r42 == null) goto L24;
        notifyCompletion(r42, r1);
        return;
    L24:
        return;
    L22:
        ((JobNode) r4).invoke(r1);     // Catch: Throwable -> L15
        return;
    L15:
        th = move-exception;
        handleOnCompletionException$kotlinx_coroutines_core(new CompletionHandlerException("Exception in completion handler " + r4 + " for " + this, th));
        return;
    L8:
        r52 = null;
        goto L9
    }

    private final void continueCompleting(Finishing r3, ChildHandleNode r4, Object r5) {
        ChildHandleNode r0 = nextChild(r4);
        if (r0 != null) goto L5;
    L7:
        r3.getList().close(2);
        ChildHandleNode r42 = nextChild(r4);
        if (r42 != null) goto L10;
    L12:
        afterCompletion(finalizeFinishingState(r3, r5));
        return;
    L10:
        if (tryWaitForChild(r3, r42, r5) == false) goto L12;
        return;
    L5:
        if (tryWaitForChild(r3, r0, r5) == false) goto L7;
    }

    private final Throwable createCauseException(Object r3) {
        if (r3 != null) goto L4;
        boolean r0 = true;
    L5:
        if (r0 == false) goto L11;
        Throwable r32 = (Throwable) r3;
        if (r32 == null) goto L9;
        return r32;
    L9:
        return new JobCancellationException(access$cancellationExceptionMessage(this), null, this);
    L11:
        return ((ParentJob) r3).getChildJobCancellationCause();
    L4:
        r0 = r3 instanceof Throwable;
        goto L5
    }

    private final Object finalizeFinishingState(Finishing r6, Object r7) {
        if ((r7 instanceof CompletedExceptionally) == false) goto L5;
        CompletedExceptionally r0 = (CompletedExceptionally) r7;
    L6:
        if (r0 == null) goto L8;
        Throwable r02 = r0.cause;
    L9:
        monitor-enter(r6);
        boolean r2 = r6.isCancelling();     // Catch: Throwable -> L14
        List<Throwable> r3 = r6.sealLocked(r02);     // Catch: Throwable -> L14
        Throwable r4 = getFinalRootCause(r6, r3);     // Catch: Throwable -> L14
        if (r4 == null) goto L16;
        addSuppressedExceptions(r4, r3);     // Catch: Throwable -> L14
    L16:
        monitor-exit(r6);
        if (r4 == null) goto L22;
        if (r4 == r02) goto L22;
        r7 = new CompletedExceptionally(r4, false, 2, null);
    L22:
        if (r4 != null) goto L24;
    L28:
        if (r2 == true) goto L30;
        onCancelling(r4);
    L30:
        onCompletionInternal(r7);
        AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_state$volatile$FU(), this, r6, JobSupportKt.boxIncomplete(r7));
        completeStateFinalization(r6, r7);
        return r7;
    L24:
        if (cancelParent(r4) == false) goto L26;
    L27:
        ((CompletedExceptionally) r7).makeHandled();
        goto L28
    L26:
        if (handleJobException(r4) == false) goto L28;
    L14:
        th = move-exception;
        throw th;
    L8:
        r02 = null;
        goto L9
    L5:
        r0 = null;
        goto L6
    }

    private final Throwable getExceptionOrNull(Object r2) {
        if ((r2 instanceof CompletedExceptionally) == false) goto L5;
        CompletedExceptionally r22 = (CompletedExceptionally) r2;
    L6:
        if (r22 != null) goto L8;
        return null;
    L8:
        return r22.cause;
    L5:
        r22 = null;
        goto L6
    }

    private final Throwable getFinalRootCause(Finishing r4, List<? extends Throwable> r5) {
        Object r1 = null;
        if (r5.isEmpty() == true) goto L5;
        List<? extends Throwable> r3 = r5;
        Iterator<T> r42 = r3.iterator();
    L11:
        if (r42.hasNext() == false) goto L15;
        Object r0 = r42.next();
        if ((((Throwable) r0) instanceof CancellationException) == true) goto L11;
    L16:
        Throwable r02 = (Throwable) r0;
        if (r02 == null) goto L19;
        return r02;
    L19:
        Throwable r43 = r5.get(0);
        if ((r43 instanceof TimeoutCancellationException) == false) goto L32;
        Iterator<T> r32 = r3.iterator();
    L23:
        if (r32.hasNext() == false) goto L29;
        Object r52 = r32.next();
        Throwable r03 = (Throwable) r52;
        if (r03 == r43) goto L23;
        if ((r03 instanceof TimeoutCancellationException) == false) goto L23;
        r1 = r52;
    L29:
        Throwable r12 = (Throwable) r1;
        if (r12 == null) goto L32;
        return r12;
    L32:
        return r43;
    L15:
        r0 = null;
        goto L16
    L5:
        if (r4.isCancelling() == true) goto L7;
        return null;
    L7:
        return new JobCancellationException(access$cancellationExceptionMessage(this), null, this);
    }

    private final NodeList getOrPromoteCancellingList(Incomplete r3) {
        NodeList r0 = r3.getList();
        if (r0 == null) goto L5;
        return r0;
    L5:
        if ((r3 instanceof Empty) == false) goto L9;
        return new NodeList();
    L9:
        if ((r3 instanceof JobNode) == false) goto L12;
        promoteSingleToNodeList((JobNode) r3);
        return null;
    L12:
        DurationKt$$ExternalSyntheticBUOutline0.m("State should have list: ", r3);
        return null;
    }

    private static final /* synthetic */ AtomicReferenceFieldUpdater get_parentHandle$volatile$FU() {
        return _parentHandle$volatile$FU;
    }

    private static final /* synthetic */ AtomicReferenceFieldUpdater get_state$volatile$FU() {
        return _state$volatile$FU;
    }

    private final boolean joinInternal() {
    L2:
        Object r0 = getState$kotlinx_coroutines_core();
        if ((r0 instanceof Incomplete) == false) goto L4;
        if (startInternal(r0) < 0) goto L2;
        return true;
    L4:
        return false;
    }

    private final Object joinSuspend(Continuation<? super Unit> r6) {
        CancellableContinuationImpl r0 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(r6), 1);
        r0.initCancellability();
        CancellableContinuationKt.disposeOnCancellation(r0, JobKt.invokeOnCompletion$default(this, false, new ResumeOnCompletion(r0), 1, null));
        Object r5 = r0.getResult();
        if (r5 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L6;
        DebugProbesKt.probeCoroutineSuspended(r6);
    L6:
        if (r5 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L9;
        return r5;
    L9:
        return Unit.INSTANCE;
    }

    private final Object makeCancelling(Object r7) {
        Throwable r0 = null;
        Throwable r1 = null;
    L3:
        Object r2 = getState$kotlinx_coroutines_core();
        if ((r2 instanceof Finishing) == true) goto L5;
        if ((r2 instanceof Incomplete) == false) goto L47;
        if (r1 != null) goto L33;
        r1 = createCauseException(r7);
    L33:
        Incomplete r3 = (Incomplete) r2;
        if (r3.isActive() == true) goto L36;
        Object r32 = tryMakeCompleting(r2, new CompletedExceptionally(r1, false, 2, null));
        if (r32 == JobSupportKt.access$getCOMPLETING_ALREADY$p()) goto L44;
        if (r32 == JobSupportKt.access$getCOMPLETING_RETRY$p()) goto L3;
        return r32;
    L44:
        DurationKt$$ExternalSyntheticBUOutline0.m("Cannot happen in ", r2);
        return null;
    L36:
        if (tryMakeCancelling(r3, r1) == false) goto L3;
        return JobSupportKt.access$getCOMPLETING_ALREADY$p();
    L47:
        return JobSupportKt.access$getTOO_LATE_TO_CANCEL$p();
    L5:
        monitor-enter(r2);
    L11:
        th = move-exception;
        throw th;
    L7:
        if (((Finishing) r2).isSealed() == false) goto L13;
        Symbol r6 = JobSupportKt.access$getTOO_LATE_TO_CANCEL$p();     // Catch: Throwable -> L11
        monitor-exit(r2);
        return r6;
    L13:
        boolean r33 = ((Finishing) r2).isCancelling();     // Catch: Throwable -> L11
        if (r7 != null) goto L16;
        if (r33 == false) goto L16;
    L19:
        Throwable r72 = ((Finishing) r2).getRootCause();     // Catch: Throwable -> L11
        if (r33 == true) goto L22;
        r0 = r72;
    L22:
        monitor-exit(r2);
        if (r0 == null) goto L26;
        notifyCancelling(((Finishing) r2).getList(), r0);
    L26:
        return JobSupportKt.access$getCOMPLETING_ALREADY$p();
    L16:
        if (r1 != null) goto L18;
        r1 = createCauseException(r7);     // Catch: Throwable -> L11
    L18:
        ((Finishing) r2).addExceptionLocked(r1);     // Catch: Throwable -> L11
        goto L19
    }

    private final ChildHandleNode nextChild(LockFreeLinkedListNode r1) {
    L3:
        if (r1.isRemoved() == false) goto L5;
        r1 = r1.getPrevNode();
    L5:
        r1 = r1.getNextNode();
        if (r1.isRemoved() == true) goto L5;
        if ((r1 instanceof ChildHandleNode) == true) goto L10;
        if ((r1 instanceof NodeList) == false) goto L5;
        return null;
    L10:
        return (ChildHandleNode) r1;
    }

    private final void notifyCancelling(NodeList r6, Throwable r7) {
        onCancelling(r7);
        r6.close(4);
        LockFreeLinkedListNode r0 = (LockFreeLinkedListNode) r6.getNext();
        CompletionHandlerException r1 = null;
    L4:
        if (Intrinsics.areEqual(r0, r6) == true) goto L16;
        if ((r0 instanceof JobNode) == false) goto L15;
        if (((JobNode) r0).getOnCancelling() == false) goto L15;
        ((JobNode) r0).invoke(r7);     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        if (r1 == null) goto L14;
        kotlin.ExceptionsKt.addSuppressed(r1, th);
        goto L15
    L14:
        r1 = new CompletionHandlerException("Exception in completion handler " + r0 + " for " + this, th);
        Unit r2 = Unit.INSTANCE;
    L15:
        r0 = r0.getNextNode();
        goto L4
    L16:
        if (r1 == null) goto L18;
        handleOnCompletionException$kotlinx_coroutines_core(r1);
    L18:
        cancelParent(r7);
    }

    private final void notifyCompletion(NodeList r6, Throwable r7) {
        r6.close(1);
        LockFreeLinkedListNode r0 = (LockFreeLinkedListNode) r6.getNext();
        CompletionHandlerException r1 = null;
    L4:
        if (Intrinsics.areEqual(r0, r6) == true) goto L14;
        if ((r0 instanceof JobNode) == false) goto L13;
        ((JobNode) r0).invoke(r7);     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        if (r1 == null) goto L12;
        kotlin.ExceptionsKt.addSuppressed(r1, th);
        goto L13
    L12:
        r1 = new CompletionHandlerException("Exception in completion handler " + r0 + " for " + this, th);
        Unit r2 = Unit.INSTANCE;
    L13:
        r0 = r0.getNextNode();
        goto L4
    L14:
        if (r1 == null) goto L24;
        handleOnCompletionException$kotlinx_coroutines_core(r1);
        return;
    }

    private final Object onAwaitInternalProcessResFunc(Object r1, Object r2) {
        if ((r2 instanceof CompletedExceptionally) == true) goto L6;
        return r2;
    L6:
        throw ((CompletedExceptionally) r2).cause;
    }

    private final void onAwaitInternalRegFunc(SelectInstance<?> r4, Object r5) {
    L2:
        Object r52 = getState$kotlinx_coroutines_core();
        if ((r52 instanceof Incomplete) == false) goto L5;
        if (startInternal(r52) < 0) goto L2;
        r4.disposeOnCompletion(JobKt.invokeOnCompletion$default(this, false, new SelectOnAwaitCompletionHandler(this, r4), 1, null));
        return;
    L5:
        if ((r52 instanceof CompletedExceptionally) == true) goto L8;
        r52 = JobSupportKt.unboxState(r52);
    L8:
        r4.selectInRegistrationPhase(r52);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [kotlinx.coroutines.InactiveNodeList] */
    private final void promoteEmptyToNodeList(Empty r3) {
        NodeList r0 = new NodeList();
        if (r3.isActive() == true) goto L6;
        r0 = new InactiveNodeList(r0);
    L6:
        AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_state$volatile$FU(), this, r3, r0);
    }

    private final void promoteSingleToNodeList(JobNode r3) {
        r3.addOneIfEmpty(new NodeList());
        LockFreeLinkedListNode r0 = r3.getNextNode();
        AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_state$volatile$FU(), this, r3, r0);
    }

    private final void registerSelectForOnJoin(SelectInstance<?> r4, Object r5) {
        if (joinInternal() == true) goto L6;
        r4.selectInRegistrationPhase(Unit.INSTANCE);
        return;
    L6:
        r4.disposeOnCompletion(JobKt.invokeOnCompletion$default(this, false, new SelectOnJoinCompletionHandler(this, r4), 1, null));
    }

    private final int startInternal(Object r5) {
        if ((r5 instanceof Empty) == false) goto L13;
        if (((Empty) r5).isActive() == false) goto L8;
        return 0;
    L8:
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_state$volatile$FU(), this, r5, JobSupportKt.access$getEMPTY_ACTIVE$p()) == true) goto L10;
        return -1;
    L10:
        onStart();
        return 1;
    L13:
        if ((r5 instanceof InactiveNodeList) == true) goto L15;
        return 0;
    L15:
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_state$volatile$FU(), this, r5, ((InactiveNodeList) r5).getList()) == true) goto L17;
        return -1;
    L17:
        onStart();
        return 1;
    }

    private final String stateString(Object r2) {
        if ((r2 instanceof Finishing) == false) goto L14;
        Finishing r22 = (Finishing) r2;
        if (r22.isCancelling() == false) goto L9;
        return "Cancelling";
    L9:
        if (r22.isCompleting() == false) goto L12;
        return "Completing";
    L12:
        return "Active";
    L14:
        if ((r2 instanceof Incomplete) == false) goto L21;
        if (((Incomplete) r2).isActive() == false) goto L18;
        return "Active";
    L18:
        return "New";
    L21:
        if ((r2 instanceof CompletedExceptionally) == false) goto L24;
        return "Cancelled";
    L24:
        return "Completed";
    }

    public static /* synthetic */ CancellationException toCancellationException$default(JobSupport r0, Throwable r1, String r2, int r3, Object r4) {
        if (r4 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: toCancellationException");
        return null;
    L4:
        if ((r3 & 1) == 0) goto L7;
        r2 = null;
    L7:
        return r0.toCancellationException(r1, r2);
    }

    private final boolean tryFinalizeSimpleState(Incomplete r3, Object r4) {
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_state$volatile$FU(), this, r3, JobSupportKt.boxIncomplete(r4)) == true) goto L6;
        return false;
    L6:
        onCancelling(null);
        onCompletionInternal(r4);
        completeStateFinalization(r3, r4);
        return true;
    }

    private final boolean tryMakeCancelling(Incomplete r5, Throwable r6) {
        NodeList r0 = getOrPromoteCancellingList(r5);
        if (r0 != null) goto L5;
        return false;
    L5:
        Finishing r2 = new Finishing(r0, false, r6);
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_state$volatile$FU(), this, r5, r2) == true) goto L8;
        return false;
    L8:
        notifyCancelling(r0, r6);
        return true;
    }

    private final Object tryMakeCompleting(Object r2, Object r3) {
        if ((r2 instanceof Incomplete) == true) goto L7;
        return JobSupportKt.access$getCOMPLETING_ALREADY$p();
    L7:
        if ((r2 instanceof Empty) == true) goto L11;
        if ((r2 instanceof JobNode) == true) goto L11;
    L20:
        return tryMakeCompletingSlowPath((Incomplete) r2, r3);
    L11:
        if ((r2 instanceof ChildHandleNode) == true) goto L20;
        if ((r3 instanceof CompletedExceptionally) == true) goto L20;
        if (tryFinalizeSimpleState((Incomplete) r2, r3) == false) goto L18;
        return r3;
    L18:
        return JobSupportKt.access$getCOMPLETING_RETRY$p();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [T, java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r2v2 */
    private final Object tryMakeCompletingSlowPath(Incomplete r6, Object r7) {
        NodeList r0 = getOrPromoteCancellingList(r6);
        if (r0 == null) goto L5;
        ?? r2 = 0;
        if ((r6 instanceof Finishing) == false) goto L9;
        Finishing r1 = (Finishing) r6;
    L10:
        if (r1 != null) goto L12;
        r1 = new Finishing(r0, false, null);
    L12:
        Ref.ObjectRef r3 = new Ref.ObjectRef();
        monitor-enter(r1);
    L19:
        th = move-exception;
        throw th;
    L15:
        if (r1.isCompleting() == false) goto L22;
        Symbol r5 = JobSupportKt.access$getCOMPLETING_ALREADY$p();     // Catch: Throwable -> L19
        monitor-exit(r1);
        return r5;
    L22:
        r1.setCompleting(true);     // Catch: Throwable -> L19
        if (r1 != r6) goto L25;
    L29:
        boolean r62 = r1.isCancelling();     // Catch: Throwable -> L19
        if ((r7 instanceof CompletedExceptionally) == false) goto L32;
        CompletedExceptionally r4 = (CompletedExceptionally) r7;     // Catch: Throwable -> L19
    L33:
        if (r4 == null) goto L35;
        r1.addExceptionLocked(r4.cause);     // Catch: Throwable -> L19
    L35:
        Throwable r42 = r1.getRootCause();     // Catch: Throwable -> L19
        if (r62 == true) goto L38;
        r2 = r42;
    L38:
        r3.element = r2;     // Catch: Throwable -> L19
        Unit r63 = Unit.INSTANCE;     // Catch: Throwable -> L19
        monitor-exit(r1);
        if (r2 == 0) goto L42;
        notifyCancelling(r0, r2);
    L42:
        ChildHandleNode r64 = nextChild(r0);
        if (r64 != null) goto L45;
    L48:
        r0.close(2);
        ChildHandleNode r65 = nextChild(r0);
        if (r65 == null) goto L55;
        if (tryWaitForChild(r1, r65, r7) == false) goto L55;
        return JobSupportKt.COMPLETING_WAITING_CHILDREN;
    L55:
        return finalizeFinishingState(r1, r7);
    L45:
        if (tryWaitForChild(r1, r64, r7) == false) goto L48;
        return JobSupportKt.COMPLETING_WAITING_CHILDREN;
    L32:
        r4 = null;
        goto L33
    L25:
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_state$volatile$FU(), this, r6, r1) == true) goto L29;
        Symbol r52 = JobSupportKt.access$getCOMPLETING_RETRY$p();     // Catch: Throwable -> L19
        monitor-exit(r1);
        return r52;
    L9:
        r1 = null;
        goto L10
    L5:
        return JobSupportKt.access$getCOMPLETING_RETRY$p();
    }

    private final boolean tryWaitForChild(Finishing r4, ChildHandleNode r5, Object r6) {
    L3:
        if (JobKt.invokeOnCompletion(r5.childJob, false, new ChildCompletion(this, r4, r5, r6)) != NonDisposableHandle.INSTANCE) goto L4;
        r5 = nextChild(r5);
        if (r5 != null) goto L3;
        return false;
    L4:
        return true;
    }

    public void afterCompletion(Object r1) {
    }

    @Override // kotlinx.coroutines.Job
    public final ChildHandle attachChild(ChildJob r4) {
        ChildHandleNode r0 = new ChildHandleNode(r4);
        r0.setJob(this);
    L3:
        Object r42 = getState$kotlinx_coroutines_core();
        if ((r42 instanceof Empty) == true) goto L5;
        Throwable r2 = null;
        if ((r42 instanceof Incomplete) == false) goto L33;
        NodeList r1 = ((Incomplete) r42).getList();
        if (r1 != null) goto L17;
        promoteSingleToNodeList((JobNode) r42);
        goto L3
    L17:
        if (r1.addLast(r0, 7) == true) goto L30;
        boolean r43 = r1.addLast(r0, 3);
        Object r3 = getState$kotlinx_coroutines_core();
        if ((r3 instanceof Finishing) == false) goto L23;
        r2 = ((Finishing) r3).getRootCause();
    L28:
        r0.invoke(r2);
        if (r43 == true) goto L30;
        return NonDisposableHandle.INSTANCE;
    L23:
        if ((r3 instanceof CompletedExceptionally) == false) goto L25;
        CompletedExceptionally r32 = (CompletedExceptionally) r3;
    L26:
        if (r32 == null) goto L28;
        r2 = r32.cause;
        goto L28
    L25:
        r32 = null;
    L30:
        return r0;
    L33:
        Object r33 = getState$kotlinx_coroutines_core();
        if ((r33 instanceof CompletedExceptionally) == false) goto L36;
        CompletedExceptionally r34 = (CompletedExceptionally) r33;
    L37:
        if (r34 == null) goto L39;
        r2 = r34.cause;
    L39:
        r0.invoke(r2);
        return NonDisposableHandle.INSTANCE;
    L36:
        r34 = null;
        goto L37
    L5:
        Empty r12 = (Empty) r42;
        if (r12.isActive() == true) goto L8;
        promoteEmptyToNodeList(r12);
        goto L3
    L8:
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_state$volatile$FU(), this, r42, r0) == false) goto L3;
        return r0;
    }

    public final Object awaitInternal(Continuation<Object> r3) {
    L2:
        Object r0 = getState$kotlinx_coroutines_core();
        if ((r0 instanceof Incomplete) == false) goto L5;
        if (startInternal(r0) < 0) goto L2;
        return awaitSuspend(r3);
    L5:
        if ((r0 instanceof CompletedExceptionally) == true) goto L9;
        return JobSupportKt.unboxState(r0);
    L9:
        throw ((CompletedExceptionally) r0).cause;
    }

    @Override // kotlinx.coroutines.Job
    public void cancel(CancellationException r3) {
        if (r3 != null) goto L4;
        r3 = new JobCancellationException(access$cancellationExceptionMessage(this), null, this);
    L4:
        cancelInternal(r3);
    }

    public final boolean cancelCoroutine(Throwable r1) {
        return cancelImpl$kotlinx_coroutines_core(r1);
    }

    public final boolean cancelImpl$kotlinx_coroutines_core(Object r4) {
        Object r0 = JobSupportKt.access$getCOMPLETING_ALREADY$p();
        if (getOnCancelComplete$kotlinx_coroutines_core() == false) goto L8;
        r0 = cancelMakeCompleting(r4);
        if (r0 != JobSupportKt.COMPLETING_WAITING_CHILDREN) goto L8;
        return true;
    L8:
        if (r0 != JobSupportKt.access$getCOMPLETING_ALREADY$p()) goto L11;
        r0 = makeCancelling(r4);
    L11:
        if (r0 != JobSupportKt.access$getCOMPLETING_ALREADY$p()) goto L14;
        return true;
    L14:
        if (r0 != JobSupportKt.COMPLETING_WAITING_CHILDREN) goto L17;
        return true;
    L17:
        if (r0 != JobSupportKt.access$getTOO_LATE_TO_CANCEL$p()) goto L20;
        return false;
    L20:
        afterCompletion(r0);
        return true;
    }

    public void cancelInternal(Throwable r1) {
        cancelImpl$kotlinx_coroutines_core(r1);
    }

    public String cancellationExceptionMessage() {
        return "Job was cancelled";
    }

    public boolean childCancelled(Throwable r3) {
        if ((r3 instanceof CancellationException) == false) goto L6;
        return true;
    L6:
        if (cancelImpl$kotlinx_coroutines_core(r3) == true) goto L8;
        return false;
    L8:
        if (getHandlesException$kotlinx_coroutines_core() == false) goto L12;
        return true;
    L12:
        return false;
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public /* bridge */ <R> R fold(R r1, Function2<? super R, ? super CoroutineContext.Element, ? extends R> r2) {
        return (R) Job.DefaultImpls.fold(this, r1, r2);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public /* bridge */ <E extends CoroutineContext.Element> E get(CoroutineContext.Key<E> r1) {
        return (E) Job.DefaultImpls.get(this, r1);
    }

    @Override // kotlinx.coroutines.Job
    public final CancellationException getCancellationException() {
        Object r0 = getState$kotlinx_coroutines_core();
        if ((r0 instanceof Finishing) == false) goto L12;
        Throwable r02 = ((Finishing) r0).getRootCause();
        if (r02 == null) goto L9;
        CancellationException r03 = toCancellationException(r02, DebugStringsKt.getClassSimpleName(this) + " is cancelling");
        if (r03 == null) goto L9;
        return r03;
    L9:
        DurationKt$$ExternalSyntheticBUOutline0.m("Job is still new or active: ", this);
        return null;
    L12:
        if ((r0 instanceof Incomplete) == false) goto L14;
        DurationKt$$ExternalSyntheticBUOutline0.m("Job is still new or active: ", this);
        return null;
    L14:
        if ((r0 instanceof CompletedExceptionally) == false) goto L18;
        return toCancellationException$default(this, ((CompletedExceptionally) r0).cause, null, 1, null);
    L18:
        return new JobCancellationException(DebugStringsKt.getClassSimpleName(this) + " has completed normally", null, this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v10, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.lang.Throwable] */
    @Override // kotlinx.coroutines.ParentJob
    public CancellationException getChildJobCancellationCause() {
        Object r0 = getState$kotlinx_coroutines_core();
        CancellationException r2 = null;
        if ((r0 instanceof Finishing) == false) goto L6;
        CancellationException r1 = ((Finishing) r0).getRootCause();
    L12:
        if ((r1 instanceof CancellationException) == false) goto L14;
        r2 = r1;
    L14:
        if (r2 == null) goto L16;
        return r2;
    L16:
        return new JobCancellationException("Parent job is " + stateString(r0), r1, this);
    L6:
        if ((r0 instanceof CompletedExceptionally) == false) goto L9;
        r1 = ((CompletedExceptionally) r0).cause;
        goto L12
    L9:
        if ((r0 instanceof Incomplete) == true) goto L17;
        r1 = null;
        goto L12
    L17:
        DurationKt$$ExternalSyntheticBUOutline0.m("Cannot be cancelling child in this state: ", r0);
        return null;
    }

    @Override // kotlinx.coroutines.Job
    public final Sequence<Job> getChildren() {
        return SequencesKt.sequence(new JobSupport$children$1(this, null));
    }

    public final Object getCompletedInternal$kotlinx_coroutines_core() {
        Object r1 = getState$kotlinx_coroutines_core();
        if ((r1 instanceof Incomplete) == false) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("This job has not completed yet");
        return null;
    L5:
        if ((r1 instanceof CompletedExceptionally) == true) goto L9;
        return JobSupportKt.unboxState(r1);
    L9:
        throw ((CompletedExceptionally) r1).cause;
    }

    public final Throwable getCompletionExceptionOrNull() {
        Object r0 = getState$kotlinx_coroutines_core();
        if ((r0 instanceof Incomplete) == false) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("This job has not completed yet");
        return null;
    L5:
        return getExceptionOrNull(r0);
    }

    public boolean getHandlesException$kotlinx_coroutines_core() {
        return true;
    }

    @Override // kotlin.coroutines.CoroutineContext.Element
    public final CoroutineContext.Key<?> getKey() {
        return Job.Key;
    }

    public final SelectClause1<?> getOnAwaitInternal() {
        return new SelectClause1Impl(this, (Function3) TypeIntrinsics.beforeCheckcastToFunctionOfArity(JobSupport$onAwaitInternal$1.INSTANCE, 3), (Function3) TypeIntrinsics.beforeCheckcastToFunctionOfArity(JobSupport$onAwaitInternal$2.INSTANCE, 3), null, 8, null);
    }

    public boolean getOnCancelComplete$kotlinx_coroutines_core() {
        return false;
    }

    @Override // kotlinx.coroutines.Job
    public final SelectClause0 getOnJoin() {
        return new SelectClause0Impl(this, (Function3) TypeIntrinsics.beforeCheckcastToFunctionOfArity(JobSupport$onJoin$1.INSTANCE, 3), null, 4, null);
    }

    public Job getParent() {
        ChildHandle r0 = getParentHandle$kotlinx_coroutines_core();
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getParent();
    }

    public final ChildHandle getParentHandle$kotlinx_coroutines_core() {
        return (ChildHandle) get_parentHandle$volatile$FU().get(this);
    }

    public final Object getState$kotlinx_coroutines_core() {
        return get_state$volatile$FU().get(this);
    }

    public boolean handleJobException(Throwable r1) {
        return false;
    }

    public void handleOnCompletionException$kotlinx_coroutines_core(Throwable r1) {
        throw r1;
    }

    public final void initParentJob(Job r2) {
        if (r2 != null) goto L5;
        setParentHandle$kotlinx_coroutines_core(NonDisposableHandle.INSTANCE);
        return;
    L5:
        r2.start();
        ChildHandle r22 = r2.attachChild(this);
        setParentHandle$kotlinx_coroutines_core(r22);
        if (isCompleted() == false) goto L9;
        r22.dispose();
        setParentHandle$kotlinx_coroutines_core(NonDisposableHandle.INSTANCE);
        return;
    }

    @Override // kotlinx.coroutines.Job
    public final DisposableHandle invokeOnCompletion(Function1<? super Throwable, Unit> r2) {
        return invokeOnCompletionInternal$kotlinx_coroutines_core(true, new InvokeOnCompletion(r2));
    }

    @Override // kotlinx.coroutines.Job
    public final DisposableHandle invokeOnCompletion(boolean r1, boolean r2, Function1<? super Throwable, Unit> r3) {
        if (r1 == false) goto L4;
        JobNode r12 = new InvokeOnCancelling(r3);
    L6:
        return invokeOnCompletionInternal$kotlinx_coroutines_core(r2, r12);
    L4:
        r12 = new InvokeOnCompletion(r3);
        goto L6
    }

    public final DisposableHandle invokeOnCompletionInternal$kotlinx_coroutines_core(boolean r6, JobNode r7) {
        r7.setJob(this);
    L3:
        Object r0 = getState$kotlinx_coroutines_core();
        boolean r2 = true;
        Throwable r3 = null;
        if ((r0 instanceof Empty) == true) goto L5;
        if ((r0 instanceof Incomplete) == false) goto L34;
        Incomplete r1 = (Incomplete) r0;
        NodeList r4 = r1.getList();
        if (r4 == null) goto L15;
        if (r7.getOnCancelling() == true) goto L19;
        boolean r02 = r4.addLast(r7, 1);
    L32:
        if (r02 == false) goto L3;
    L35:
        if (r2 == false) goto L37;
        return r7;
    L37:
        if (r6 == false) goto L46;
        Object r5 = getState$kotlinx_coroutines_core();
        if ((r5 instanceof CompletedExceptionally) == false) goto L41;
        CompletedExceptionally r52 = (CompletedExceptionally) r5;
    L42:
        if (r52 == null) goto L44;
        r3 = r52.cause;
    L44:
        r7.invoke(r3);
        goto L46
    L41:
        r52 = null;
    L46:
        return NonDisposableHandle.INSTANCE;
    L19:
        if ((r1 instanceof Finishing) == false) goto L21;
        Finishing r12 = (Finishing) r1;
    L22:
        if (r12 == null) goto L24;
        Throwable r03 = r12.getRootCause();
    L25:
        if (r03 != null) goto L27;
        r02 = r4.addLast(r7, 5);
        goto L32
    L27:
        if (r6 == false) goto L30;
        r7.invoke(r03);
    L30:
        return NonDisposableHandle.INSTANCE;
    L24:
        r03 = null;
        goto L25
    L21:
        r12 = null;
        goto L22
    L15:
        promoteSingleToNodeList((JobNode) r0);
        goto L3
    L34:
        r2 = false;
        goto L35
    L5:
        Empty r13 = (Empty) r0;
        if (r13.isActive() == true) goto L8;
        promoteEmptyToNodeList(r13);
        goto L3
    L8:
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_state$volatile$FU(), this, r0, r7) == false) goto L3;
        goto L35
    }

    @Override // kotlinx.coroutines.Job
    public boolean isActive() {
        Object r1 = getState$kotlinx_coroutines_core();
        if ((r1 instanceof Incomplete) == true) goto L5;
        return false;
    L5:
        if (((Incomplete) r1).isActive() == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // kotlinx.coroutines.Job
    public final boolean isCancelled() {
        Object r1 = getState$kotlinx_coroutines_core();
        if ((r1 instanceof CompletedExceptionally) == false) goto L5;
        return true;
    L5:
        if ((r1 instanceof Finishing) == true) goto L7;
        return false;
    L7:
        if (((Finishing) r1).isCancelling() == true) goto L14;
        return false;
    L14:
        return true;
    }

    @Override // kotlinx.coroutines.Job
    public final boolean isCompleted() {
        return !(getState$kotlinx_coroutines_core() instanceof Incomplete);
    }

    public boolean isScopedCoroutine() {
        return false;
    }

    @Override // kotlinx.coroutines.Job
    public final Object join(Continuation<? super Unit> r2) {
        if (joinInternal() == true) goto L6;
        JobKt.ensureActive(r2.getContext());
        return Unit.INSTANCE;
    L6:
        Object r1 = joinSuspend(r2);
        if (r1 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L10;
        return r1;
    L10:
        return Unit.INSTANCE;
    }

    public final boolean makeCompleting$kotlinx_coroutines_core(Object r4) {
    L2:
        Object r0 = tryMakeCompleting(getState$kotlinx_coroutines_core(), r4);
        if (r0 == JobSupportKt.access$getCOMPLETING_ALREADY$p()) goto L4;
        if (r0 == JobSupportKt.COMPLETING_WAITING_CHILDREN) goto L8;
        if (r0 == JobSupportKt.access$getCOMPLETING_RETRY$p()) goto L2;
        afterCompletion(r0);
        return true;
    L8:
        return true;
    L4:
        return false;
    }

    public final Object makeCompletingOnce$kotlinx_coroutines_core(Object r3) {
    L2:
        Object r0 = tryMakeCompleting(getState$kotlinx_coroutines_core(), r3);
        if (r0 == JobSupportKt.access$getCOMPLETING_ALREADY$p()) goto L7;
        if (r0 == JobSupportKt.access$getCOMPLETING_RETRY$p()) goto L2;
        return r0;
    L7:
        Constants$$ExternalSyntheticBUOutline0.m("Job " + this + " is already complete or completing, but is being completed with " + r3, getExceptionOrNull(r3));
        return null;
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public /* bridge */ CoroutineContext minusKey(CoroutineContext.Key<?> r1) {
        return Job.DefaultImpls.minusKey(this, r1);
    }

    public String nameString$kotlinx_coroutines_core() {
        return DebugStringsKt.getClassSimpleName(this);
    }

    public void onCancelling(Throwable r1) {
    }

    public void onCompletionInternal(Object r1) {
    }

    public void onStart() {
    }

    @Override // kotlinx.coroutines.ChildJob
    public final void parentCancelled(ParentJob r1) {
        cancelImpl$kotlinx_coroutines_core(r1);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public /* bridge */ CoroutineContext plus(CoroutineContext r1) {
        return Job.DefaultImpls.plus(this, r1);
    }

    public final void removeNode$kotlinx_coroutines_core(JobNode r4) {
    L2:
        Object r0 = getState$kotlinx_coroutines_core();
        if ((r0 instanceof JobNode) == false) goto L10;
        if (r0 != r4) goto L21;
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(get_state$volatile$FU(), this, r0, JobSupportKt.access$getEMPTY_ACTIVE$p()) == false) goto L2;
        return;
    L21:
        return;
    L10:
        if ((r0 instanceof Incomplete) == true) goto L12;
        return;
    L12:
        if (((Incomplete) r0).getList() == null) goto L20;
        r4.remove();
        return;
    }

    public final void setParentHandle$kotlinx_coroutines_core(ChildHandle r2) {
        get_parentHandle$volatile$FU().set(this, r2);
    }

    @Override // kotlinx.coroutines.Job
    public final boolean start() {
    L2:
        int r0 = startInternal(getState$kotlinx_coroutines_core());
        if (r0 == 0) goto L8;
        if (r0 != 1) goto L2;
        return true;
    L8:
        return false;
    }

    public final CancellationException toCancellationException(Throwable r2, String r3) {
        if ((r2 instanceof CancellationException) == false) goto L5;
        CancellationException r0 = (CancellationException) r2;
    L6:
        if (r0 == null) goto L8;
    L11:
        return r0;
    L8:
        if (r3 != null) goto L10;
        r3 = access$cancellationExceptionMessage(this);
    L10:
        r0 = new JobCancellationException(r3, r2, this);
        goto L11
    L5:
        r0 = null;
        goto L6
    }

    public final String toDebugString() {
        return nameString$kotlinx_coroutines_core() + '{' + stateString(getState$kotlinx_coroutines_core()) + '}';
    }

    public String toString() {
        return toDebugString() + '@' + DebugStringsKt.getHexAddress(this);
    }
}
