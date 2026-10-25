package kotlin.collections;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.BuilderInference;
import kotlin.ExperimentalStdlibApi;
import kotlin.IgnorableReturnValue;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.sequences.Sequence;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;

@Metadata(d1 = {"\u0000\u0084\u0001\n\u0000\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010&\n\u0002\b\f\n\u0002\u0010(\n\u0002\u0010)\n\u0002\u0010'\n\u0002\b\b\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u001d\u001a\"\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003H\u0086\u0080\u0004\u001aS\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032*\u0010\u0005\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070\u0006\"\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007H\u0086\u0080\u0004¢\u0006\u0002\u0010\b\u001a\"\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003H\u0087\u0088\u0004\u001a\"\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\n\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003H\u0087\u0088\u0004\u001aS\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\n\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032*\u0010\u0005\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070\u0006\"\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007H\u0086\u0080\u0004¢\u0006\u0002\u0010\b\u001a7\u0010\u000b\u001a\u001e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\fj\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003`\r\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003H\u0087\u0088\u0004¢\u0006\u0002\u0010\u000e\u001ac\u0010\u000b\u001a\u001e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\fj\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003`\r\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032*\u0010\u0005\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070\u0006\"\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007H\u0086\u0080\u0004¢\u0006\u0002\u0010\u000f\u001a7\u0010\u0010\u001a\u001e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011j\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003`\u0012\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003H\u0087\u0088\u0004¢\u0006\u0002\u0010\u0013\u001ac\u0010\u0010\u001a\u001e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011j\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003`\u0012\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032*\u0010\u0005\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070\u0006\"\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007H\u0086\u0080\u0004¢\u0006\u0002\u0010\u0014\u001aY\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032%\b\u0001\u0010\u0016\u001a\u001f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\n\u0012\u0004\u0012\u00020\u00180\u0017¢\u0006\u0002\b\u0019H\u0087\u0088\u0004ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u001aa\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u001a\u001a\u00020\u001b2%\b\u0001\u0010\u0016\u001a\u001f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\n\u0012\u0004\u0012\u00020\u00180\u0017¢\u0006\u0002\b\u0019H\u0087\u0088\u0004ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u001a(\u0010\u001c\u001a\u00020\u001d\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001H\u0087\u0088\u0004\u001a;\u0010\u001e\u001a\u00020\u001d\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0012\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0001H\u0087\u0088\u0004\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0001\u001a\u0004\b\u0003\u0010\u0000\u001a4\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0001H\u0087\u0088\u0004\u001aM\u0010 \u001a\u0002H!\"\u0014\b\u0000\u0010\"*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0001*\u0002H!\"\u0004\b\u0001\u0010!*\u0002H\"2\f\u0010#\u001a\b\u0012\u0004\u0012\u0002H!0$H\u0087\u0088\u0004ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000¢\u0006\u0002\u0010%\u001a:\u0010&\u001a\u00020\u001d\"\t\b\u0000\u0010\u0002¢\u0006\u0002\b'\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010(\u001a\u0002H\u0002H\u0087\u008a\u0004¢\u0006\u0002\u0010)\u001a<\u0010*\u001a\u0004\u0018\u0001H\u0003\"\t\b\u0000\u0010\u0002¢\u0006\u0002\b'\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010(\u001a\u0002H\u0002H\u0087\u008a\u0004¢\u0006\u0002\u0010+\u001a;\u0010,\u001a\u00020\u0018\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\n2\u0006\u0010(\u001a\u0002H\u00022\u0006\u0010-\u001a\u0002H\u0003H\u0087\u008a\u0004¢\u0006\u0002\u0010.\u001a2\u0010/\u001a\u00020\u001d\"\t\b\u0000\u0010\u0002¢\u0006\u0002\b'*\u000e\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0002\b\u00030\u00012\u0006\u0010(\u001a\u0002H\u0002H\u0087\u0088\u0004¢\u0006\u0002\u0010)\u001a8\u00100\u001a\u00020\u001d\"\u0004\b\u0000\u0010\u0002\"\t\b\u0001\u0010\u0003¢\u0006\u0002\b'*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010-\u001a\u0002H\u0003H\u0087\u0088\u0004¢\u0006\u0002\u0010)\u001a<\u00101\u001a\u0004\u0018\u0001H\u0003\"\t\b\u0000\u0010\u0002¢\u0006\u0002\b'\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\n2\u0006\u0010(\u001a\u0002H\u0002H\u0087\u0088\b¢\u0006\u0002\u0010+\u001a+\u00102\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u000303H\u0087\u008a\u0004¢\u0006\u0002\u00104\u001a+\u00105\u001a\u0002H\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u000303H\u0087\u008a\u0004¢\u0006\u0002\u00104\u001a2\u00106\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u000303H\u0087\u0088\u0004\u001a2\u00107\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u000303\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u000303H\u0087\u0080\u0004\u001a7\u00108\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u000303\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010(\u001a\u0002H\u00022\u0006\u0010-\u001a\u0002H\u0003H\u0080\u0080\u0004¢\u0006\u0002\u00109\u001aQ\u0010:\u001a\u0002H\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010(\u001a\u0002H\u00022\f\u0010#\u001a\b\u0012\u0004\u0012\u0002H\u00030$H\u0087\u0088\u0004ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0000¢\u0006\u0002\u0010;\u001aD\u0010<\u001a\u0002H\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010(\u001a\u0002H\u00022\f\u0010#\u001a\b\u0012\u0004\u0012\u0002H\u00030$H\u0080\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u0010;\u001a3\u0010=\u001a\u0002H\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010(\u001a\u0002H\u0002H\u0087\u0080\u0004¢\u0006\u0002\u0010+\u001aD\u0010>\u001a\u0002H\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\n2\u0006\u0010(\u001a\u0002H\u00022\f\u0010#\u001a\b\u0012\u0004\u0012\u0002H\u00030$H\u0086\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u0010;\u001a:\u0010?\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003030@\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001H\u0087\u008a\u0004\u001a=\u0010?\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030B0A\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\nH\u0087\u008a\u0004¢\u0006\u0002\bC\u001ax\u0010D\u001a\u0002H\"\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010!\"\u0018\b\u0003\u0010\"*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H!0\n*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010E\u001a\u0002H\"2\u001e\u0010F\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u000303\u0012\u0004\u0012\u0002H!0\u0017H\u0087\u0088\bø\u0001\u0000¢\u0006\u0002\u0010G\u001ax\u0010H\u001a\u0002H\"\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010!\"\u0018\b\u0003\u0010\"*\u0012\u0012\u0006\b\u0000\u0012\u0002H!\u0012\u0006\b\u0000\u0012\u0002H\u00030\n*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010E\u001a\u0002H\"2\u001e\u0010F\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u000303\u0012\u0004\u0012\u0002H!0\u0017H\u0087\u0088\bø\u0001\u0000¢\u0006\u0002\u0010G\u001aK\u0010I\u001a\u00020\u0018\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\n2\u001a\u0010\u0005\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070\u0006H\u0086\u0080\u0004¢\u0006\u0002\u0010J\u001aD\u0010I\u001a\u00020\u0018\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\n2\u0018\u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070KH\u0086\u0080\u0004\u001aD\u0010I\u001a\u00020\u0018\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\n2\u0018\u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070LH\u0086\u0080\u0004\u001a]\u0010M\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H!0\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010!*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u001e\u0010F\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u000303\u0012\u0004\u0012\u0002H!0\u0017H\u0086\u0088\u0004ø\u0001\u0000\u001a]\u0010N\u001a\u000e\u0012\u0004\u0012\u0002H!\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010!*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u001e\u0010F\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u000303\u0012\u0004\u0012\u0002H!0\u0017H\u0086\u0088\u0004ø\u0001\u0000\u001aK\u0010O\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0012\u0010P\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u001d0\u0017H\u0086\u0088\u0004ø\u0001\u0000\u001aK\u0010Q\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0012\u0010P\u001a\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\u001d0\u0017H\u0086\u0088\u0004ø\u0001\u0000\u001ar\u0010R\u001a\u0002H\"\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0018\b\u0002\u0010\"*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\n*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010E\u001a\u0002H\"2\u001e\u0010P\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u000303\u0012\u0004\u0012\u00020\u001d0\u0017H\u0087\u0088\bø\u0001\u0000¢\u0006\u0002\u0010G\u001aW\u0010S\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u001e\u0010P\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u000303\u0012\u0004\u0012\u00020\u001d0\u0017H\u0086\u0088\u0004ø\u0001\u0000\u001ar\u0010T\u001a\u0002H\"\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0018\b\u0002\u0010\"*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\n*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010E\u001a\u0002H\"2\u001e\u0010P\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u000303\u0012\u0004\u0012\u00020\u001d0\u0017H\u0087\u0088\bø\u0001\u0000¢\u0006\u0002\u0010G\u001aW\u0010U\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u001e\u0010P\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u000303\u0012\u0004\u0012\u00020\u001d0\u0017H\u0086\u0088\u0004ø\u0001\u0000\u001a8\u0010V\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070KH\u0086\u0080\u0004\u001aS\u0010V\u001a\u0002H\"\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0018\b\u0002\u0010\"*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\n*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070K2\u0006\u0010E\u001a\u0002H\"H\u0087\u0080\b¢\u0006\u0002\u0010W\u001a?\u0010V\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070\u0006H\u0086\u0080\u0004¢\u0006\u0002\u0010\b\u001aU\u0010V\u001a\u0002H\"\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0018\b\u0002\u0010\"*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\n*\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070\u00062\u0006\u0010E\u001a\u0002H\"H\u0087\u0080\b¢\u0006\u0002\u0010X\u001a8\u0010V\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070LH\u0086\u0080\u0004\u001aS\u0010V\u001a\u0002H\"\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0018\b\u0002\u0010\"*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\n*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070L2\u0006\u0010E\u001a\u0002H\"H\u0087\u0080\b¢\u0006\u0002\u0010Y\u001a4\u0010V\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001H\u0087\u0080\u0004\u001a4\u0010Z\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\n\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001H\u0087\u0080\u0004\u001aO\u0010V\u001a\u0002H\"\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0018\b\u0002\u0010\"*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\n*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010E\u001a\u0002H\"H\u0087\u0080\b¢\u0006\u0002\u0010[\u001aH\u0010\\\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0012\u0010]\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007H\u0086\u0082\u0004\u001aN\u0010\\\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0018\u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070KH\u0086\u0082\u0004\u001aU\u0010\\\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u001a\u0010\u0005\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070\u0006H\u0086\u0082\u0004¢\u0006\u0002\u0010^\u001aN\u0010\\\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0018\u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070LH\u0086\u0082\u0004\u001aJ\u0010\\\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0014\u0010_\u001a\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001H\u0086\u0082\u0004\u001a>\u0010`\u001a\u00020\u0018\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\n2\u0012\u0010]\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007H\u0087\u008a\u0004\u001aD\u0010`\u001a\u00020\u0018\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\n2\u0018\u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070KH\u0087\u008a\u0004\u001aK\u0010`\u001a\u00020\u0018\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\n2\u001a\u0010\u0005\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070\u0006H\u0087\u008a\u0004¢\u0006\u0002\u0010J\u001aD\u0010`\u001a\u00020\u0018\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\n2\u0018\u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070LH\u0087\u008a\u0004\u001a>\u0010`\u001a\u00020\u0018\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0006\b\u0000\u0012\u0002H\u00030\n2\u0012\u0010_\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001H\u0087\u008a\u0004\u001aA\u0010a\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u0006\u0010(\u001a\u0002H\u0002H\u0087\u0082\u0004¢\u0006\u0002\u0010b\u001aB\u0010a\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\f\u0010c\u001a\b\u0012\u0004\u0012\u0002H\u00020KH\u0087\u0082\u0004\u001aI\u0010a\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\u000e\u0010c\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0006H\u0087\u0082\u0004¢\u0006\u0002\u0010d\u001aB\u0010a\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00012\f\u0010c\u001a\b\u0012\u0004\u0012\u0002H\u00020LH\u0087\u0082\u0004\u001a3\u0010e\u001a\u00020\u0018\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\n2\u0006\u0010(\u001a\u0002H\u0002H\u0087\u008a\u0004¢\u0006\u0002\u0010f\u001a4\u0010e\u001a\u00020\u0018\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\n2\f\u0010c\u001a\b\u0012\u0004\u0012\u0002H\u00020KH\u0087\u008a\u0004\u001a;\u0010e\u001a\u00020\u0018\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\n2\u000e\u0010c\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0006H\u0087\u008a\u0004¢\u0006\u0002\u0010g\u001a4\u0010e\u001a\u00020\u0018\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\n2\f\u0010c\u001a\b\u0012\u0004\u0012\u0002H\u00020LH\u0087\u008a\u0004\u001a2\u0010h\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001H\u0080\u0080\u0004\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006i"}, d2 = {"emptyMap", _UrlKt.FRAGMENT_ENCODE_SET, "K", "V", "mapOf", "pairs", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/Pair;", "([Lkotlin/Pair;)Ljava/util/Map;", "mutableMapOf", _UrlKt.FRAGMENT_ENCODE_SET, "hashMapOf", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "()Ljava/util/HashMap;", "([Lkotlin/Pair;)Ljava/util/HashMap;", "linkedMapOf", "Ljava/util/LinkedHashMap;", "Lkotlin/collections/LinkedHashMap;", "()Ljava/util/LinkedHashMap;", "([Lkotlin/Pair;)Ljava/util/LinkedHashMap;", "buildMap", "builderAction", "Lkotlin/Function1;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ExtensionFunctionType;", "capacity", _UrlKt.FRAGMENT_ENCODE_SET, "isNotEmpty", _UrlKt.FRAGMENT_ENCODE_SET, "isNullOrEmpty", "orEmpty", "ifEmpty", "R", "M", "defaultValue", "Lkotlin/Function0;", "(Ljava/util/Map;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "contains", "Lkotlin/internal/OnlyInputTypes;", PluginsConstants.Settings.KEY, "(Ljava/util/Map;Ljava/lang/Object;)Z", "get", "(Ljava/util/Map;Ljava/lang/Object;)Ljava/lang/Object;", "set", "value", "(Ljava/util/Map;Ljava/lang/Object;Ljava/lang/Object;)V", "containsKey", "containsValue", "remove", "component1", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/util/Map$Entry;)Ljava/lang/Object;", "component2", "toPair", "copy", "mapEntryOf", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/Map$Entry;", "getOrElse", "(Ljava/util/Map;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "getOrElseNullable", "getValue", "getOrPut", "iterator", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "mutableIterator", "mapValuesTo", "destination", "transform", "(Ljava/util/Map;Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/util/Map;", "mapKeysTo", "putAll", "(Ljava/util/Map;[Lkotlin/Pair;)V", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/sequences/Sequence;", "mapValues", "mapKeys", "filterKeys", "predicate", "filterValues", "filterTo", "filter", "filterNotTo", "filterNot", "toMap", "(Ljava/lang/Iterable;Ljava/util/Map;)Ljava/util/Map;", "([Lkotlin/Pair;Ljava/util/Map;)Ljava/util/Map;", "(Lkotlin/sequences/Sequence;Ljava/util/Map;)Ljava/util/Map;", "toMutableMap", "(Ljava/util/Map;Ljava/util/Map;)Ljava/util/Map;", "plus", "pair", "(Ljava/util/Map;[Lkotlin/Pair;)Ljava/util/Map;", "map", "plusAssign", "minus", "(Ljava/util/Map;Ljava/lang/Object;)Ljava/util/Map;", "keys", "(Ljava/util/Map;[Ljava/lang/Object;)Ljava/util/Map;", "minusAssign", "(Ljava/util/Map;Ljava/lang/Object;)V", "(Ljava/util/Map;[Ljava/lang/Object;)V", "optimizeReadOnlyMap", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/collections/MapsKt")
@SourceDebugExtension({"SMAP\nMaps.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,850:1\n442#1:860\n454#1:865\n552#1,6:870\n578#1,6:876\n1#2:851\n1266#3,4:852\n1266#3,4:856\n1266#3,4:861\n1266#3,4:866\n*S KotlinDebug\n*F\n+ 1 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n493#1:860\n508#1:865\n567#1:870,6\n593#1:876,6\n442#1:852,4\n454#1:856,4\n493#1:861,4\n508#1:866,4\n*E\n"})
/* loaded from: classes.dex */
class MapsKt__MapsKt extends MapsKt__MapsJVMKt {
    public MapsKt__MapsKt() {
    }

    @SinceKotlin(version = "1.6")
    @InlineOnly
    private static final <K, V> Map<K, V> buildMap(int r0, @BuilderInference Function1<? super Map<K, V>, Unit> r1) {
        Map r02 = MapsKt__MapsJVMKt.createMapBuilder(r0);
        r1.invoke(r02);
        return MapsKt__MapsJVMKt.build(r02);
    }

    @SinceKotlin(version = "1.6")
    @InlineOnly
    private static final <K, V> Map<K, V> buildMap(@BuilderInference Function1<? super Map<K, V>, Unit> r1) {
        Map r0 = MapsKt__MapsJVMKt.createMapBuilder();
        r1.invoke(r0);
        return MapsKt__MapsJVMKt.build(r0);
    }

    @InlineOnly
    private static final <K, V> K component1(Map.Entry<? extends K, ? extends V> r0) {
        return r0.getKey();
    }

    @InlineOnly
    private static final <K, V> V component2(Map.Entry<? extends K, ? extends V> r0) {
        return r0.getValue();
    }

    @InlineOnly
    private static final <K, V> boolean contains(Map<? extends K, ? extends V> r0, K r1) {
        return r0.containsKey(r1);
    }

    @InlineOnly
    private static final <K> boolean containsKey(Map<? extends K, ?> r0, K r1) {
        return r0.containsKey(r1);
    }

    @InlineOnly
    private static final <K, V> boolean containsValue(Map<K, ? extends V> r0, V r1) {
        return r0.containsValue(r1);
    }

    @SinceKotlin(version = MVEL.VERSION)
    @ExperimentalStdlibApi
    public static final <K, V> Map.Entry<K, V> copy(Map.Entry<? extends K, ? extends V> r1) {
        if ((r1 instanceof DetachedMapEntry) == false) goto L5;
        DetachedMapEntry r0 = (DetachedMapEntry) r1;
    L6:
        if (r0 == null) goto L9;
        return r0;
    L9:
        return mapEntryOf(r1.getKey(), r1.getValue());
    L5:
        r0 = null;
        goto L6
    }

    public static <K, V> Map<K, V> emptyMap() {
        return EmptyMap.INSTANCE;
    }

    public static final <K, V> Map<K, V> filter(Map<? extends K, ? extends V> r3, Function1<? super Map.Entry<? extends K, ? extends V>, Boolean> r4) {
        LinkedHashMap r0 = new LinkedHashMap();
        Iterator<Map.Entry<? extends K, ? extends V>> r32 = r3.entrySet().iterator();
    L4:
        if (r32.hasNext() == false) goto L8;
        Map.Entry<? extends K, ? extends V> r1 = r32.next();
        if (r4.invoke(r1).booleanValue() == false) goto L4;
        r0.put(r1.getKey(), r1.getValue());
        goto L4
    L8:
        return r0;
    }

    public static final <K, V> Map<K, V> filterKeys(Map<? extends K, ? extends V> r3, Function1<? super K, Boolean> r4) {
        LinkedHashMap r0 = new LinkedHashMap();
        Iterator<Map.Entry<? extends K, ? extends V>> r32 = r3.entrySet().iterator();
    L4:
        if (r32.hasNext() == false) goto L8;
        Map.Entry<? extends K, ? extends V> r1 = r32.next();
        if (r4.invoke(r1.getKey()).booleanValue() == false) goto L4;
        r0.put(r1.getKey(), r1.getValue());
        goto L4
    L8:
        return r0;
    }

    public static final <K, V> Map<K, V> filterNot(Map<? extends K, ? extends V> r3, Function1<? super Map.Entry<? extends K, ? extends V>, Boolean> r4) {
        LinkedHashMap r0 = new LinkedHashMap();
        Iterator<Map.Entry<? extends K, ? extends V>> r32 = r3.entrySet().iterator();
    L4:
        if (r32.hasNext() == false) goto L8;
        Map.Entry<? extends K, ? extends V> r1 = r32.next();
        if (r4.invoke(r1).booleanValue() == true) goto L4;
        r0.put(r1.getKey(), r1.getValue());
        goto L4
    L8:
        return r0;
    }

    @IgnorableReturnValue
    public static final <K, V, M extends Map<? super K, ? super V>> M filterNotTo(Map<? extends K, ? extends V> r2, M r3, Function1<? super Map.Entry<? extends K, ? extends V>, Boolean> r4) {
        Iterator<Map.Entry<? extends K, ? extends V>> r22 = r2.entrySet().iterator();
    L4:
        if (r22.hasNext() == false) goto L8;
        Map.Entry<? extends K, ? extends V> r0 = r22.next();
        if (r4.invoke(r0).booleanValue() == true) goto L4;
        r3.put(r0.getKey(), r0.getValue());
        goto L4
    L8:
        return r3;
    }

    @IgnorableReturnValue
    public static final <K, V, M extends Map<? super K, ? super V>> M filterTo(Map<? extends K, ? extends V> r2, M r3, Function1<? super Map.Entry<? extends K, ? extends V>, Boolean> r4) {
        Iterator<Map.Entry<? extends K, ? extends V>> r22 = r2.entrySet().iterator();
    L4:
        if (r22.hasNext() == false) goto L8;
        Map.Entry<? extends K, ? extends V> r0 = r22.next();
        if (r4.invoke(r0).booleanValue() == false) goto L4;
        r3.put(r0.getKey(), r0.getValue());
        goto L4
    L8:
        return r3;
    }

    public static final <K, V> Map<K, V> filterValues(Map<? extends K, ? extends V> r3, Function1<? super V, Boolean> r4) {
        LinkedHashMap r0 = new LinkedHashMap();
        Iterator<Map.Entry<? extends K, ? extends V>> r32 = r3.entrySet().iterator();
    L4:
        if (r32.hasNext() == false) goto L8;
        Map.Entry<? extends K, ? extends V> r1 = r32.next();
        if (r4.invoke(r1.getValue()).booleanValue() == false) goto L4;
        r0.put(r1.getKey(), r1.getValue());
        goto L4
    L8:
        return r0;
    }

    @InlineOnly
    private static final <K, V> V get(Map<? extends K, ? extends V> r0, K r1) {
        return r0.get(r1);
    }

    @InlineOnly
    private static final <K, V> V getOrElse(Map<K, ? extends V> r0, K r1, Function0<? extends V> r2) {
        V r02 = r0.get(r1);
        if (r02 == null) goto L5;
        return r02;
    L5:
        return r2.invoke();
    }

    public static final <K, V> V getOrElseNullable(Map<K, ? extends V> r1, K r2, Function0<? extends V> r3) {
        V r0 = r1.get(r2);
        if (r0 == null) goto L5;
    L8:
        return r0;
    L5:
        if (r1.containsKey(r2) == true) goto L8;
        return r3.invoke();
    }

    public static final <K, V> V getOrPut(Map<K, V> r1, K r2, Function0<? extends V> r3) {
        V r0 = r1.get(r2);
        if (r0 != null) goto L6;
        V r32 = r3.invoke();
        r1.put(r2, r32);
        return r32;
    L6:
        return r0;
    }

    @SinceKotlin(version = "1.1")
    public static <K, V> V getValue(Map<K, ? extends V> r0, K r1) {
        return (V) MapsKt__MapWithDefaultKt.getOrImplicitDefaultNullable(r0, r1);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <K, V> HashMap<K, V> hashMapOf() {
        return new HashMap();
    }

    public static final <K, V> HashMap<K, V> hashMapOf(Pair<? extends K, ? extends V>... r2) {
        HashMap<K, V> r0 = new HashMap(MapsKt__MapsJVMKt.mapCapacity(r2.length));
        putAll(r0, r2);
        return r0;
    }

    /* JADX WARN: Incorrect types in method signature: <M::Ljava/util/Map<**>;:TR;R:Ljava/lang/Object;>(TM;Lkotlin/jvm/functions/Function0<+TR;>;)TR; */
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final Object ifEmpty(Map r1, Function0 r2) {
        if (r1.isEmpty() == true) goto L5;
        return r1;
    L5:
        return r2.invoke();
    }

    @InlineOnly
    private static final <K, V> boolean isNotEmpty(Map<? extends K, ? extends V> r0) {
        return !r0.isEmpty();
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <K, V> boolean isNullOrEmpty(Map<? extends K, ? extends V> r0) {
        if (r0 != null) goto L4;
        return true;
    L4:
        if (r0.isEmpty() == true) goto L10;
        return false;
    L10:
        return true;
    }

    @InlineOnly
    private static final <K, V> Iterator<Map.Entry<K, V>> iterator(Map<? extends K, ? extends V> r0) {
        return r0.entrySet().iterator();
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <K, V> LinkedHashMap<K, V> linkedMapOf() {
        return new LinkedHashMap();
    }

    public static <K, V> LinkedHashMap<K, V> linkedMapOf(Pair<? extends K, ? extends V>... r2) {
        return (LinkedHashMap) toMap(r2, new LinkedHashMap(MapsKt__MapsJVMKt.mapCapacity(r2.length)));
    }

    public static final <K, V> Map.Entry<K, V> mapEntryOf(K r1, V r2) {
        return new DetachedMapEntry(r1, r2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <K, V, R> Map<R, V> mapKeys(Map<? extends K, ? extends V> r3, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> r4) {
        LinkedHashMap r0 = new LinkedHashMap(MapsKt__MapsJVMKt.mapCapacity(r3.size()));
        Iterator<T> r32 = r3.entrySet().iterator();
    L4:
        if (r32.hasNext() == false) goto L6;
        Map.Entry r1 = (Object) r32.next();
        r0.put(r4.invoke(r1), r1.getValue());
        goto L4
    L6:
        return r0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @IgnorableReturnValue
    public static final <K, V, R, M extends Map<? super R, ? super V>> M mapKeysTo(Map<? extends K, ? extends V> r2, M r3, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> r4) {
        Iterator<T> r22 = r2.entrySet().iterator();
    L4:
        if (r22.hasNext() == false) goto L6;
        Map.Entry r0 = (Object) r22.next();
        r3.put(r4.invoke(r0), r0.getValue());
        goto L4
    L6:
        return r3;
    }

    @InlineOnly
    private static final <K, V> Map<K, V> mapOf() {
        return emptyMap();
    }

    public static <K, V> Map<K, V> mapOf(Pair<? extends K, ? extends V>... r2) {
        if (r2.length <= 0) goto L7;
        return toMap(r2, new LinkedHashMap(MapsKt__MapsJVMKt.mapCapacity(r2.length)));
    L7:
        return emptyMap();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <K, V, R> Map<K, R> mapValues(Map<? extends K, ? extends V> r3, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> r4) {
        LinkedHashMap r0 = new LinkedHashMap(MapsKt__MapsJVMKt.mapCapacity(r3.size()));
        Iterator<T> r32 = r3.entrySet().iterator();
    L4:
        if (r32.hasNext() == false) goto L6;
        Map.Entry r1 = (Object) r32.next();
        r0.put(r1.getKey(), r4.invoke(r1));
        goto L4
    L6:
        return r0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @IgnorableReturnValue
    public static final <K, V, R, M extends Map<? super K, ? super R>> M mapValuesTo(Map<? extends K, ? extends V> r2, M r3, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> r4) {
        Iterator<T> r22 = r2.entrySet().iterator();
    L4:
        if (r22.hasNext() == false) goto L6;
        Map.Entry r0 = (Object) r22.next();
        r3.put(r0.getKey(), r4.invoke(r0));
        goto L4
    L6:
        return r3;
    }

    @SinceKotlin(version = "1.1")
    public static final <K, V> Map<K, V> minus(Map<? extends K, ? extends V> r1, Iterable<? extends K> r2) {
        Map r12 = toMutableMap(r1);
        CollectionsKt__MutableCollectionsKt.removeAll(r12.keySet(), r2);
        return optimizeReadOnlyMap(r12);
    }

    @SinceKotlin(version = "1.1")
    public static final <K, V> Map<K, V> minus(Map<? extends K, ? extends V> r0, K r1) {
        Map r02 = toMutableMap(r0);
        r02.remove(r1);
        return optimizeReadOnlyMap(r02);
    }

    @SinceKotlin(version = "1.1")
    public static final <K, V> Map<K, V> minus(Map<? extends K, ? extends V> r1, Sequence<? extends K> r2) {
        Map r12 = toMutableMap(r1);
        CollectionsKt__MutableCollectionsKt.removeAll(r12.keySet(), r2);
        return optimizeReadOnlyMap(r12);
    }

    @SinceKotlin(version = "1.1")
    public static final <K, V> Map<K, V> minus(Map<? extends K, ? extends V> r1, K[] r2) {
        Map r12 = toMutableMap(r1);
        CollectionsKt__MutableCollectionsKt.removeAll(r12.keySet(), r2);
        return optimizeReadOnlyMap(r12);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <K, V> void minusAssign(Map<K, V> r0, Iterable<? extends K> r1) {
        CollectionsKt__MutableCollectionsKt.removeAll(r0.keySet(), r1);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <K, V> void minusAssign(Map<K, V> r0, K r1) {
        r0.remove(r1);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <K, V> void minusAssign(Map<K, V> r0, Sequence<? extends K> r1) {
        CollectionsKt__MutableCollectionsKt.removeAll(r0.keySet(), r1);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <K, V> void minusAssign(Map<K, V> r0, K[] r1) {
        CollectionsKt__MutableCollectionsKt.removeAll(r0.keySet(), r1);
    }

    @InlineOnly
    @JvmName(name = "mutableIterator")
    private static final <K, V> Iterator<Map.Entry<K, V>> mutableIterator(Map<K, V> r0) {
        return r0.entrySet().iterator();
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <K, V> Map<K, V> mutableMapOf() {
        return new LinkedHashMap();
    }

    public static <K, V> Map<K, V> mutableMapOf(Pair<? extends K, ? extends V>... r2) {
        LinkedHashMap r0 = new LinkedHashMap(MapsKt__MapsJVMKt.mapCapacity(r2.length));
        putAll(r0, r2);
        return r0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <K, V> Map<K, V> optimizeReadOnlyMap(Map<K, ? extends V> r2) {
        int r0 = r2.size();
        if (r0 == 0) goto L10;
        if (r0 == 1) goto L8;
        return r2;
    L8:
        return MapsKt__MapsJVMKt.toSingletonMap(r2);
    L10:
        return emptyMap();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @InlineOnly
    private static final <K, V> Map<K, V> orEmpty(Map<K, ? extends V> r0) {
        if (r0 == 0) goto L4;
        return r0;
    L4:
        return emptyMap();
    }

    public static final <K, V> Map<K, V> plus(Map<? extends K, ? extends V> r1, Iterable<? extends Pair<? extends K, ? extends V>> r2) {
        if (r1.isEmpty() == true) goto L5;
        LinkedHashMap r0 = new LinkedHashMap(r1);
        putAll(r0, r2);
        return r0;
    L5:
        return toMap(r2);
    }

    public static <K, V> Map<K, V> plus(Map<? extends K, ? extends V> r1, Map<? extends K, ? extends V> r2) {
        LinkedHashMap r0 = new LinkedHashMap(r1);
        r0.putAll(r2);
        return r0;
    }

    public static <K, V> Map<K, V> plus(Map<? extends K, ? extends V> r1, Pair<? extends K, ? extends V> r2) {
        if (r1.isEmpty() == true) goto L5;
        LinkedHashMap r0 = new LinkedHashMap(r1);
        r0.put(r2.getFirst(), r2.getSecond());
        return r0;
    L5:
        return MapsKt__MapsJVMKt.mapOf(r2);
    }

    public static final <K, V> Map<K, V> plus(Map<? extends K, ? extends V> r1, Sequence<? extends Pair<? extends K, ? extends V>> r2) {
        LinkedHashMap r0 = new LinkedHashMap(r1);
        putAll(r0, r2);
        return optimizeReadOnlyMap(r0);
    }

    public static final <K, V> Map<K, V> plus(Map<? extends K, ? extends V> r1, Pair<? extends K, ? extends V>[] r2) {
        if (r1.isEmpty() == true) goto L5;
        LinkedHashMap r0 = new LinkedHashMap(r1);
        putAll(r0, r2);
        return r0;
    L5:
        return toMap(r2);
    }

    @InlineOnly
    private static final <K, V> void plusAssign(Map<? super K, ? super V> r0, Iterable<? extends Pair<? extends K, ? extends V>> r1) {
        putAll(r0, r1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @InlineOnly
    private static final <K, V> void plusAssign(Map<? super K, ? super V> r0, Map<K, ? extends V> r1) {
        r0.putAll(r1);
    }

    @InlineOnly
    private static final <K, V> void plusAssign(Map<? super K, ? super V> r1, Pair<? extends K, ? extends V> r2) {
        r1.put(r2.getFirst(), r2.getSecond());
    }

    @InlineOnly
    private static final <K, V> void plusAssign(Map<? super K, ? super V> r0, Sequence<? extends Pair<? extends K, ? extends V>> r1) {
        putAll(r0, r1);
    }

    @InlineOnly
    private static final <K, V> void plusAssign(Map<? super K, ? super V> r0, Pair<? extends K, ? extends V>[] r1) {
        putAll(r0, r1);
    }

    public static final <K, V> void putAll(Map<? super K, ? super V> r2, Iterable<? extends Pair<? extends K, ? extends V>> r3) {
        Iterator<? extends Pair<? extends K, ? extends V>> r32 = r3.iterator();
    L4:
        if (r32.hasNext() == false) goto L6;
        Pair<? extends K, ? extends V> r0 = r32.next();
        r2.put(r0.component1(), r0.component2());
        goto L4
    }

    public static final <K, V> void putAll(Map<? super K, ? super V> r2, Sequence<? extends Pair<? extends K, ? extends V>> r3) {
        Iterator<? extends Pair<? extends K, ? extends V>> r32 = r3.iterator();
    L4:
        if (r32.hasNext() == false) goto L6;
        Pair<? extends K, ? extends V> r0 = r32.next();
        r2.put(r0.component1(), r0.component2());
        goto L4
    }

    public static final <K, V> void putAll(Map<? super K, ? super V> r4, Pair<? extends K, ? extends V>[] r5) {
        int r0 = r5.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        Pair<? extends K, ? extends V> r2 = r5[r1];
        r4.put(r2.component1(), r2.component2());
        r1 = r1 + 1;
        goto L3
    }

    @IgnorableReturnValue
    @InlineOnly
    private static final <K, V> V remove(Map<? extends K, V> r0, K r1) {
        return (V) TypeIntrinsics.asMutableMap(r0).remove(r1);
    }

    @InlineOnly
    private static final <K, V> void set(Map<K, V> r0, K r1, V r2) {
        r0.put(r1, r2);
    }

    public static <K, V> Map<K, V> toMap(Iterable<? extends Pair<? extends K, ? extends V>> r3) {
        if ((r3 instanceof Collection) == false) goto L20;
        Collection r0 = (Collection) r3;
        int r1 = r0.size();
        if (r1 == 0) goto L18;
        if (r1 == 1) goto L11;
        return toMap(r3, new LinkedHashMap(MapsKt__MapsJVMKt.mapCapacity(r0.size())));
    L11:
        if ((r3 instanceof List) == false) goto L14;
        Object r32 = ((List) r3).get(0);
    L16:
        return MapsKt__MapsJVMKt.mapOf((Pair) r32);
    L14:
        r32 = r0.iterator().next();
        goto L16
    L18:
        return emptyMap();
    L20:
        return optimizeReadOnlyMap(toMap(r3, new LinkedHashMap()));
    }

    @IgnorableReturnValue
    public static final <K, V, M extends Map<? super K, ? super V>> M toMap(Iterable<? extends Pair<? extends K, ? extends V>> r0, M r1) {
        putAll(r1, r0);
        return r1;
    }

    @SinceKotlin(version = "1.1")
    public static <K, V> Map<K, V> toMap(Map<? extends K, ? extends V> r2) {
        int r0 = r2.size();
        if (r0 == 0) goto L11;
        if (r0 == 1) goto L9;
        return toMutableMap(r2);
    L9:
        return MapsKt__MapsJVMKt.toSingletonMap(r2);
    L11:
        return emptyMap();
    }

    @SinceKotlin(version = "1.1")
    @IgnorableReturnValue
    public static final <K, V, M extends Map<? super K, ? super V>> M toMap(Map<? extends K, ? extends V> r0, M r1) {
        r1.putAll(r0);
        return r1;
    }

    public static final <K, V> Map<K, V> toMap(Sequence<? extends Pair<? extends K, ? extends V>> r1) {
        return optimizeReadOnlyMap(toMap(r1, new LinkedHashMap()));
    }

    @IgnorableReturnValue
    public static final <K, V, M extends Map<? super K, ? super V>> M toMap(Sequence<? extends Pair<? extends K, ? extends V>> r0, M r1) {
        putAll(r1, r0);
        return r1;
    }

    public static final <K, V> Map<K, V> toMap(Pair<? extends K, ? extends V>[] r2) {
        int r0 = r2.length;
        if (r0 == 0) goto L11;
        if (r0 == 1) goto L9;
        return toMap(r2, new LinkedHashMap(MapsKt__MapsJVMKt.mapCapacity(r2.length)));
    L9:
        return MapsKt__MapsJVMKt.mapOf(r2[0]);
    L11:
        return emptyMap();
    }

    @IgnorableReturnValue
    public static final <K, V, M extends Map<? super K, ? super V>> M toMap(Pair<? extends K, ? extends V>[] r0, M r1) {
        putAll(r1, r0);
        return r1;
    }

    @SinceKotlin(version = "1.1")
    public static <K, V> Map<K, V> toMutableMap(Map<? extends K, ? extends V> r1) {
        return new LinkedHashMap(r1);
    }

    @InlineOnly
    private static final <K, V> Pair<K, V> toPair(Map.Entry<? extends K, ? extends V> r2) {
        return new Pair(r2.getKey(), r2.getValue());
    }
}
