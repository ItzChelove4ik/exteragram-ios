package kotlin.text;

import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.Metadata;
import kotlin.OverloadResolutionByLambdaReturnType;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CharIterator;
import kotlin.collections.CollectionsKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.IntProgression;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import okhttp3.internal.url._UrlKt;
import okio.Utf8$$ExternalSyntheticBUOutline1;
import org.mvel2.MVEL;

@Metadata(d1 = {"\u0000\u0080\u0001\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\u0010\u000b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0019\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u000b\u001a%\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003H\u0086\u0088\u0004ø\u0001\u0000\u001a%\u0010\u0000\u001a\u00020\u0006*\u00020\u00062\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003H\u0086\u0088\u0004ø\u0001\u0000\u001a%\u0010\u0007\u001a\u00020\u0001*\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003H\u0086\u0088\u0004ø\u0001\u0000\u001a%\u0010\u0007\u001a\u00020\u0006*\u00020\u00062\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003H\u0086\u0088\u0004ø\u0001\u0000\u001a%\u0010\b\u001a\u00020\u0001*\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003H\u0086\u0088\u0004ø\u0001\u0000\u001a%\u0010\b\u001a\u00020\u0006*\u00020\u00062\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003H\u0086\u0088\u0004ø\u0001\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\n\u0010\t\u001a\u00020\n\"\u00020\u0004H\u0086\u0080\u0004\u001a\u001a\u0010\u0000\u001a\u00020\u0006*\u00020\u00062\n\u0010\t\u001a\u00020\n\"\u00020\u0004H\u0086\u0080\u0004\u001a\u001a\u0010\u0007\u001a\u00020\u0001*\u00020\u00012\n\u0010\t\u001a\u00020\n\"\u00020\u0004H\u0086\u0080\u0004\u001a\u001a\u0010\u0007\u001a\u00020\u0006*\u00020\u00062\n\u0010\t\u001a\u00020\n\"\u00020\u0004H\u0086\u0080\u0004\u001a\u001a\u0010\b\u001a\u00020\u0001*\u00020\u00012\n\u0010\t\u001a\u00020\n\"\u00020\u0004H\u0086\u0080\u0004\u001a\u001a\u0010\b\u001a\u00020\u0006*\u00020\u00062\n\u0010\t\u001a\u00020\n\"\u00020\u0004H\u0086\u0080\u0004\u001a\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u0086\u0080\u0004\u001a\u000e\u0010\u0000\u001a\u00020\u0006*\u00020\u0006H\u0087\u0088\u0004\u001a\u000e\u0010\u0007\u001a\u00020\u0001*\u00020\u0001H\u0086\u0080\u0004\u001a\u000e\u0010\u0007\u001a\u00020\u0006*\u00020\u0006H\u0087\u0088\u0004\u001a\u000e\u0010\b\u001a\u00020\u0001*\u00020\u0001H\u0086\u0080\u0004\u001a\u000e\u0010\b\u001a\u00020\u0006*\u00020\u0006H\u0087\u0088\u0004\u001a \u0010\u000b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u0004H\u0086\u0080\u0004\u001a \u0010\u000b\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u0004H\u0086\u0080\u0004\u001a \u0010\u000f\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u0004H\u0086\u0080\u0004\u001a \u0010\u000f\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u0004H\u0086\u0080\u0004\u001a!\u0010\u0010\u001a\u00020\u0005*\u0004\u0018\u00010\u0001H\u0087\u0088\u0004\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0001\u001a\u0004\b\u0003\u0010\u0000\u001a\u000e\u0010\u0011\u001a\u00020\u0005*\u00020\u0001H\u0087\u0088\u0004\u001a\u000e\u0010\u0012\u001a\u00020\u0005*\u00020\u0001H\u0087\u0088\u0004\u001a\u000e\u0010\u0013\u001a\u00020\u0005*\u00020\u0001H\u0086\u0080\u0004\u001a\u000e\u0010\u0014\u001a\u00020\u0005*\u00020\u0001H\u0087\u0088\u0004\u001a!\u0010\u0015\u001a\u00020\u0005*\u0004\u0018\u00010\u0001H\u0087\u0088\u0004\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0001\u001a\u0004\b\u0003\u0010\u0000\u001a\u000e\u0010\u0016\u001a\u00020\u0017*\u00020\u0001H\u0086\u0082\u0004\u001a\u0010\u0010\u0018\u001a\u00020\u0006*\u0004\u0018\u00010\u0006H\u0087\u0088\u0004\u001aE\u0010\u0019\u001a\u0002H\u001a\"\f\b\u0000\u0010\u001b*\u00020\u0001*\u0002H\u001a\"\u0004\b\u0001\u0010\u001a*\u0002H\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u001a0\u001dH\u0087\u0088\u0004ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000¢\u0006\u0002\u0010\u001e\u001aE\u0010\u001f\u001a\u0002H\u001a\"\f\b\u0000\u0010\u001b*\u00020\u0001*\u0002H\u001a\"\u0004\b\u0001\u0010\u001a*\u0002H\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u001a0\u001dH\u0087\u0088\u0004ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000¢\u0006\u0002\u0010\u001e\u001a\u0016\u0010'\u001a\u00020\u0005*\u00020\u00012\u0006\u0010(\u001a\u00020\rH\u0086\u0080\u0004\u001a\u0016\u0010)\u001a\u00020\u0006*\u00020\u00062\u0006\u0010*\u001a\u00020!H\u0086\u0080\u0004\u001a\u0016\u0010+\u001a\u00020\u0001*\u00020\u00012\u0006\u0010*\u001a\u00020!H\u0086\u0080\u0004\u001a\u001e\u0010+\u001a\u00020\u0001*\u00020\u00062\u0006\u0010,\u001a\u00020\r2\u0006\u0010-\u001a\u00020\rH\u0087\u0088\u0004\u001a \u0010)\u001a\u00020\u0006*\u00020\u00012\u0006\u0010.\u001a\u00020\r2\b\b\u0002\u0010/\u001a\u00020\rH\u0087\u0088\u0004\u001a\u0016\u0010)\u001a\u00020\u0006*\u00020\u00012\u0006\u0010*\u001a\u00020!H\u0086\u0080\u0004\u001a \u00100\u001a\u00020\u0006*\u00020\u00062\u0006\u00101\u001a\u00020\u00042\b\b\u0002\u00102\u001a\u00020\u0006H\u0086\u0080\u0004\u001a \u00100\u001a\u00020\u0006*\u00020\u00062\u0006\u00101\u001a\u00020\u00062\b\b\u0002\u00102\u001a\u00020\u0006H\u0086\u0080\u0004\u001a \u00103\u001a\u00020\u0006*\u00020\u00062\u0006\u00101\u001a\u00020\u00042\b\b\u0002\u00102\u001a\u00020\u0006H\u0086\u0080\u0004\u001a \u00103\u001a\u00020\u0006*\u00020\u00062\u0006\u00101\u001a\u00020\u00062\b\b\u0002\u00102\u001a\u00020\u0006H\u0086\u0080\u0004\u001a \u00104\u001a\u00020\u0006*\u00020\u00062\u0006\u00101\u001a\u00020\u00042\b\b\u0002\u00102\u001a\u00020\u0006H\u0086\u0080\u0004\u001a \u00104\u001a\u00020\u0006*\u00020\u00062\u0006\u00101\u001a\u00020\u00062\b\b\u0002\u00102\u001a\u00020\u0006H\u0086\u0080\u0004\u001a \u00105\u001a\u00020\u0006*\u00020\u00062\u0006\u00101\u001a\u00020\u00042\b\b\u0002\u00102\u001a\u00020\u0006H\u0086\u0080\u0004\u001a \u00105\u001a\u00020\u0006*\u00020\u00062\u0006\u00101\u001a\u00020\u00062\b\b\u0002\u00102\u001a\u00020\u0006H\u0086\u0080\u0004\u001a&\u00106\u001a\u00020\u0001*\u00020\u00012\u0006\u0010.\u001a\u00020\r2\u0006\u0010/\u001a\u00020\r2\u0006\u00107\u001a\u00020\u0001H\u0086\u0080\u0004\u001a&\u00106\u001a\u00020\u0006*\u00020\u00062\u0006\u0010.\u001a\u00020\r2\u0006\u0010/\u001a\u00020\r2\u0006\u00107\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u001e\u00106\u001a\u00020\u0001*\u00020\u00012\u0006\u0010*\u001a\u00020!2\u0006\u00107\u001a\u00020\u0001H\u0086\u0080\u0004\u001a\u001e\u00106\u001a\u00020\u0006*\u00020\u00062\u0006\u0010*\u001a\u00020!2\u0006\u00107\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u001e\u00108\u001a\u00020\u0001*\u00020\u00012\u0006\u0010.\u001a\u00020\r2\u0006\u0010/\u001a\u00020\rH\u0086\u0080\u0004\u001a\u001e\u00108\u001a\u00020\u0006*\u00020\u00062\u0006\u0010.\u001a\u00020\r2\u0006\u0010/\u001a\u00020\rH\u0087\u0088\u0004\u001a\u0016\u00108\u001a\u00020\u0001*\u00020\u00012\u0006\u0010*\u001a\u00020!H\u0086\u0080\u0004\u001a\u0016\u00108\u001a\u00020\u0006*\u00020\u00062\u0006\u0010*\u001a\u00020!H\u0087\u0088\u0004\u001a\u0016\u00109\u001a\u00020\u0001*\u00020\u00012\u0006\u0010:\u001a\u00020\u0001H\u0086\u0080\u0004\u001a\u0016\u00109\u001a\u00020\u0006*\u00020\u00062\u0006\u0010:\u001a\u00020\u0001H\u0086\u0080\u0004\u001a\u0016\u0010;\u001a\u00020\u0001*\u00020\u00012\u0006\u0010<\u001a\u00020\u0001H\u0086\u0080\u0004\u001a\u0016\u0010;\u001a\u00020\u0006*\u00020\u00062\u0006\u0010<\u001a\u00020\u0001H\u0086\u0080\u0004\u001a\u001e\u0010=\u001a\u00020\u0001*\u00020\u00012\u0006\u0010:\u001a\u00020\u00012\u0006\u0010<\u001a\u00020\u0001H\u0086\u0080\u0004\u001a\u001e\u0010=\u001a\u00020\u0006*\u00020\u00062\u0006\u0010:\u001a\u00020\u00012\u0006\u0010<\u001a\u00020\u0001H\u0086\u0080\u0004\u001a\u0016\u0010=\u001a\u00020\u0001*\u00020\u00012\u0006\u00101\u001a\u00020\u0001H\u0086\u0080\u0004\u001a\u0016\u0010=\u001a\u00020\u0006*\u00020\u00062\u0006\u00101\u001a\u00020\u0001H\u0086\u0080\u0004\u001a(\u0010>\u001a\u00020\u0006*\u00020\u00062\u0006\u00101\u001a\u00020\u00042\u0006\u00107\u001a\u00020\u00062\b\b\u0002\u00102\u001a\u00020\u0006H\u0086\u0080\u0004\u001a(\u0010>\u001a\u00020\u0006*\u00020\u00062\u0006\u00101\u001a\u00020\u00062\u0006\u00107\u001a\u00020\u00062\b\b\u0002\u00102\u001a\u00020\u0006H\u0086\u0080\u0004\u001a(\u0010?\u001a\u00020\u0006*\u00020\u00062\u0006\u00101\u001a\u00020\u00042\u0006\u00107\u001a\u00020\u00062\b\b\u0002\u00102\u001a\u00020\u0006H\u0086\u0080\u0004\u001a(\u0010?\u001a\u00020\u0006*\u00020\u00062\u0006\u00101\u001a\u00020\u00062\u0006\u00107\u001a\u00020\u00062\b\b\u0002\u00102\u001a\u00020\u0006H\u0086\u0080\u0004\u001a(\u0010@\u001a\u00020\u0006*\u00020\u00062\u0006\u00101\u001a\u00020\u00062\u0006\u00107\u001a\u00020\u00062\b\b\u0002\u00102\u001a\u00020\u0006H\u0086\u0080\u0004\u001a(\u0010@\u001a\u00020\u0006*\u00020\u00062\u0006\u00101\u001a\u00020\u00042\u0006\u00107\u001a\u00020\u00062\b\b\u0002\u00102\u001a\u00020\u0006H\u0086\u0080\u0004\u001a(\u0010A\u001a\u00020\u0006*\u00020\u00062\u0006\u00101\u001a\u00020\u00042\u0006\u00107\u001a\u00020\u00062\b\b\u0002\u00102\u001a\u00020\u0006H\u0086\u0080\u0004\u001a(\u0010A\u001a\u00020\u0006*\u00020\u00062\u0006\u00101\u001a\u00020\u00062\u0006\u00107\u001a\u00020\u00062\b\b\u0002\u00102\u001a\u00020\u0006H\u0086\u0080\u0004\u001a\u001e\u0010B\u001a\u00020\u0006*\u00020\u00012\u0006\u0010C\u001a\u00020D2\u0006\u00107\u001a\u00020\u0006H\u0087\u0088\u0004\u001a/\u0010B\u001a\u00020\u0006*\u00020\u00012\u0006\u0010C\u001a\u00020D2\u0014\b\b\u0010E\u001a\u000e\u0012\u0004\u0012\u00020F\u0012\u0004\u0012\u00020\u00010\u0003H\u0087\u0088\u0004ø\u0001\u0000\u001a\u001e\u0010G\u001a\u00020\u0006*\u00020\u00012\u0006\u0010C\u001a\u00020D2\u0006\u00107\u001a\u00020\u0006H\u0087\u0088\u0004\u001a*\u0010H\u001a\u00020\u0006*\u00020\u00062\u0012\u0010E\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\bI\u001a*\u0010H\u001a\u00020\u0006*\u00020\u00062\u0012\u0010E\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\bJ\u001a\u0016\u0010K\u001a\u00020\u0005*\u00020\u00012\u0006\u0010C\u001a\u00020DH\u0087\u008c\u0004\u001a6\u0010L\u001a\u00020\u0005*\u00020\u00012\u0006\u0010M\u001a\u00020\r2\u0006\u0010N\u001a\u00020\u00012\u0006\u0010O\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010P\u001a\u00020\u0005H\u0080\u0080\u0004\u001a \u0010Q\u001a\u00020\u0005*\u00020\u00012\u0006\u0010R\u001a\u00020\u00042\b\b\u0002\u0010P\u001a\u00020\u0005H\u0086\u0080\u0004\u001a \u0010S\u001a\u00020\u0005*\u00020\u00012\u0006\u0010R\u001a\u00020\u00042\b\b\u0002\u0010P\u001a\u00020\u0005H\u0086\u0080\u0004\u001a \u0010Q\u001a\u00020\u0005*\u00020\u00012\u0006\u0010:\u001a\u00020\u00012\b\b\u0002\u0010P\u001a\u00020\u0005H\u0086\u0080\u0004\u001a(\u0010Q\u001a\u00020\u0005*\u00020\u00012\u0006\u0010:\u001a\u00020\u00012\u0006\u0010.\u001a\u00020\r2\b\b\u0002\u0010P\u001a\u00020\u0005H\u0086\u0080\u0004\u001a \u0010S\u001a\u00020\u0005*\u00020\u00012\u0006\u0010<\u001a\u00020\u00012\b\b\u0002\u0010P\u001a\u00020\u0005H\u0086\u0080\u0004\u001a \u0010T\u001a\u00020\u0006*\u00020\u00012\u0006\u0010N\u001a\u00020\u00012\b\b\u0002\u0010P\u001a\u00020\u0005H\u0086\u0080\u0004\u001a \u0010U\u001a\u00020\u0006*\u00020\u00012\u0006\u0010N\u001a\u00020\u00012\b\b\u0002\u0010P\u001a\u00020\u0005H\u0086\u0080\u0004\u001a*\u0010V\u001a\u00020\r*\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010.\u001a\u00020\r2\b\b\u0002\u0010P\u001a\u00020\u0005H\u0086\u0080\u0004\u001a*\u0010W\u001a\u00020\r*\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010.\u001a\u00020\r2\b\b\u0002\u0010P\u001a\u00020\u0005H\u0086\u0080\u0004\u001a=\u0010X\u001a\u00020\r*\u00020\u00012\u0006\u0010N\u001a\u00020\u00012\u0006\u0010.\u001a\u00020\r2\u0006\u0010/\u001a\u00020\r2\u0006\u0010P\u001a\u00020\u00052\b\b\u0002\u0010Y\u001a\u00020\u0005H\u0082\u0080\u0004¢\u0006\u0002\bZ\u001aG\u0010[\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u0006\u0018\u00010\\*\u00020\u00012\f\u0010]\u001a\b\u0012\u0004\u0012\u00020\u00060^2\u0006\u0010.\u001a\u00020\r2\u0006\u0010P\u001a\u00020\u00052\u0006\u0010Y\u001a\u00020\u0005H\u0082\u0080\u0004¢\u0006\u0002\b_\u001a>\u0010[\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u0006\u0018\u00010\\*\u00020\u00012\f\u0010]\u001a\b\u0012\u0004\u0012\u00020\u00060^2\b\b\u0002\u0010.\u001a\u00020\r2\b\b\u0002\u0010P\u001a\u00020\u0005H\u0086\u0080\u0004\u001a>\u0010`\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u0006\u0018\u00010\\*\u00020\u00012\f\u0010]\u001a\b\u0012\u0004\u0012\u00020\u00060^2\b\b\u0002\u0010.\u001a\u00020\r2\b\b\u0002\u0010P\u001a\u00020\u0005H\u0086\u0080\u0004\u001a0\u0010V\u001a\u00020\r*\u00020\u00012\f\u0010]\u001a\b\u0012\u0004\u0012\u00020\u00060^2\b\b\u0002\u0010.\u001a\u00020\r2\b\b\u0002\u0010P\u001a\u00020\u0005H\u0086\u0080\u0004\u001a0\u0010W\u001a\u00020\r*\u00020\u00012\f\u0010]\u001a\b\u0012\u0004\u0012\u00020\u00060^2\b\b\u0002\u0010.\u001a\u00020\r2\b\b\u0002\u0010P\u001a\u00020\u0005H\u0086\u0080\u0004\u001a*\u0010X\u001a\u00020\r*\u00020\u00012\u0006\u0010R\u001a\u00020\u00042\b\b\u0002\u0010.\u001a\u00020\r2\b\b\u0002\u0010P\u001a\u00020\u0005H\u0086\u0080\u0004\u001a*\u0010X\u001a\u00020\r*\u00020\u00012\u0006\u0010a\u001a\u00020\u00062\b\b\u0002\u0010.\u001a\u00020\r2\b\b\u0002\u0010P\u001a\u00020\u0005H\u0086\u0080\u0004\u001a*\u0010b\u001a\u00020\r*\u00020\u00012\u0006\u0010R\u001a\u00020\u00042\b\b\u0002\u0010.\u001a\u00020\r2\b\b\u0002\u0010P\u001a\u00020\u0005H\u0086\u0080\u0004\u001a*\u0010b\u001a\u00020\r*\u00020\u00012\u0006\u0010a\u001a\u00020\u00062\b\b\u0002\u0010.\u001a\u00020\r2\b\b\u0002\u0010P\u001a\u00020\u0005H\u0086\u0080\u0004\u001a \u0010c\u001a\u00020\u0005*\u00020\u00012\u0006\u0010N\u001a\u00020\u00012\b\b\u0002\u0010P\u001a\u00020\u0005H\u0086\u0082\u0004\u001a \u0010c\u001a\u00020\u0005*\u00020\u00012\u0006\u0010R\u001a\u00020\u00042\b\b\u0002\u0010P\u001a\u00020\u0005H\u0086\u0082\u0004\u001a\u0016\u0010c\u001a\u00020\u0005*\u00020\u00012\u0006\u0010C\u001a\u00020DH\u0087\u008a\u0004\u001a?\u0010d\u001a\b\u0012\u0004\u0012\u00020!0e*\u00020\u00012\u0006\u0010f\u001a\u00020\n2\b\b\u0002\u0010.\u001a\u00020\r2\b\b\u0002\u0010P\u001a\u00020\u00052\b\b\u0002\u0010g\u001a\u00020\rH\u0082\u0080\u0004¢\u0006\u0002\bh\u001aI\u0010d\u001a\b\u0012\u0004\u0012\u00020!0e*\u00020\u00012\u000e\u0010f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060i2\b\b\u0002\u0010.\u001a\u00020\r2\b\b\u0002\u0010P\u001a\u00020\u00052\b\b\u0002\u0010g\u001a\u00020\rH\u0082\u0080\u0004¢\u0006\u0004\bh\u0010j\u001a\u0012\u0010k\u001a\u00020l2\u0006\u0010g\u001a\u00020\rH\u0080\u0080\u0004\u001aA\u0010m\u001a\b\u0012\u0004\u0012\u00020\u00060e*\u00020\u00012\u0012\u0010f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060i\"\u00020\u00062\b\b\u0002\u0010P\u001a\u00020\u00052\b\b\u0002\u0010g\u001a\u00020\rH\u0086\u0080\u0004¢\u0006\u0002\u0010n\u001aA\u0010o\u001a\b\u0012\u0004\u0012\u00020\u00060p*\u00020\u00012\u0012\u0010f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060i\"\u00020\u00062\b\b\u0002\u0010P\u001a\u00020\u00052\b\b\u0002\u0010g\u001a\u00020\rH\u0086\u0080\u0004¢\u0006\u0002\u0010q\u001a4\u0010m\u001a\b\u0012\u0004\u0012\u00020\u00060e*\u00020\u00012\n\u0010f\u001a\u00020\n\"\u00020\u00042\b\b\u0002\u0010P\u001a\u00020\u00052\b\b\u0002\u0010g\u001a\u00020\rH\u0086\u0080\u0004\u001a4\u0010o\u001a\b\u0012\u0004\u0012\u00020\u00060p*\u00020\u00012\n\u0010f\u001a\u00020\n\"\u00020\u00042\b\b\u0002\u0010P\u001a\u00020\u00052\b\b\u0002\u0010g\u001a\u00020\rH\u0086\u0080\u0004\u001a1\u0010o\u001a\b\u0012\u0004\u0012\u00020\u00060p*\u00020\u00012\u0006\u00101\u001a\u00020\u00062\u0006\u0010P\u001a\u00020\u00052\u0006\u0010g\u001a\u00020\rH\u0082\u0080\u0004¢\u0006\u0002\br\u001a&\u0010o\u001a\b\u0012\u0004\u0012\u00020\u00060p*\u00020\u00012\u0006\u0010C\u001a\u00020D2\b\b\u0002\u0010g\u001a\u00020\rH\u0087\u0088\u0004\u001a&\u0010m\u001a\b\u0012\u0004\u0012\u00020\u00060e*\u00020\u00012\u0006\u0010C\u001a\u00020D2\b\b\u0002\u0010g\u001a\u00020\rH\u0087\u0088\u0004\u001a\u0014\u0010s\u001a\b\u0012\u0004\u0012\u00020\u00060e*\u00020\u0001H\u0086\u0080\u0004\u001a\u0014\u0010t\u001a\b\u0012\u0004\u0012\u00020\u00060p*\u00020\u0001H\u0086\u0080\u0004\u001a\u001a\u0010u\u001a\u00020\u0005*\u0004\u0018\u00010\u00012\b\u0010N\u001a\u0004\u0018\u00010\u0001H\u0080\u0080\u0004\u001a\u001a\u0010v\u001a\u00020\u0005*\u0004\u0018\u00010\u00012\b\u0010N\u001a\u0004\u0018\u00010\u0001H\u0080\u0080\u0004\u001a\u000e\u0010w\u001a\u00020\u0005*\u00020\u0006H\u0087\u0080\u0004\u001a\u0015\u0010x\u001a\u0004\u0018\u00010\u0005*\u00020\u0006H\u0087\u0080\u0004¢\u0006\u0002\u0010y\u001a-\u0010z\u001a\u00020\r*\u00020\u00062\u0006\u0010.\u001a\u00020\r2\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003H\u0080\u0088\u0004ø\u0001\u0000\"\u0019\u0010 \u001a\u00020!*\u00020\u00018FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\"\u0010#\"\u0019\u0010$\u001a\u00020\r*\u00020\u00018FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b%\u0010&\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006{"}, d2 = {"trim", _UrlKt.FRAGMENT_ENCODE_SET, "predicate", "Lkotlin/Function1;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "trimStart", "trimEnd", "chars", _UrlKt.FRAGMENT_ENCODE_SET, "padStart", "length", _UrlKt.FRAGMENT_ENCODE_SET, "padChar", "padEnd", "isNullOrEmpty", "isEmpty", "isNotEmpty", "isBlank", "isNotBlank", "isNullOrBlank", "iterator", "Lkotlin/collections/CharIterator;", "orEmpty", "ifEmpty", "R", "C", "defaultValue", "Lkotlin/Function0;", "(Ljava/lang/CharSequence;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "ifBlank", "indices", "Lkotlin/ranges/IntRange;", "getIndices", "(Ljava/lang/CharSequence;)Lkotlin/ranges/IntRange;", "lastIndex", "getLastIndex", "(Ljava/lang/CharSequence;)I", "hasSurrogatePairAt", "index", "substring", "range", "subSequence", "start", "end", "startIndex", "endIndex", "substringBefore", "delimiter", "missingDelimiterValue", "substringAfter", "substringBeforeLast", "substringAfterLast", "replaceRange", "replacement", "removeRange", "removePrefix", "prefix", "removeSuffix", "suffix", "removeSurrounding", "replaceBefore", "replaceAfter", "replaceAfterLast", "replaceBeforeLast", "replace", "regex", "Lkotlin/text/Regex;", "transform", "Lkotlin/text/MatchResult;", "replaceFirst", "replaceFirstChar", "replaceFirstCharWithChar", "replaceFirstCharWithCharSequence", "matches", "regionMatchesImpl", "thisOffset", "other", "otherOffset", "ignoreCase", "startsWith", "char", "endsWith", "commonPrefixWith", "commonSuffixWith", "indexOfAny", "lastIndexOfAny", "indexOf", "last", "indexOf$StringsKt__StringsKt", "findAnyOf", "Lkotlin/Pair;", "strings", _UrlKt.FRAGMENT_ENCODE_SET, "findAnyOf$StringsKt__StringsKt", "findLastAnyOf", "string", "lastIndexOf", "contains", "rangesDelimitedBy", "Lkotlin/sequences/Sequence;", "delimiters", "limit", "rangesDelimitedBy$StringsKt__StringsKt", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/lang/CharSequence;[Ljava/lang/String;IZI)Lkotlin/sequences/Sequence;", "requireNonNegativeLimit", _UrlKt.FRAGMENT_ENCODE_SET, "splitToSequence", "(Ljava/lang/CharSequence;[Ljava/lang/String;ZI)Lkotlin/sequences/Sequence;", "split", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/lang/CharSequence;[Ljava/lang/String;ZI)Ljava/util/List;", "split$StringsKt__StringsKt", "lineSequence", "lines", "contentEqualsIgnoreCaseImpl", "contentEqualsImpl", "toBooleanStrict", "toBooleanStrictOrNull", "(Ljava/lang/String;)Ljava/lang/Boolean;", "skipWhile", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/text/StringsKt")
@SourceDebugExtension({"SMAP\nStrings.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Strings.kt\nkotlin/text/StringsKt__StringsKt\n+ 2 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 5 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1660:1\n78#1,22:1661\n112#1,5:1683\n129#1,5:1688\n78#1,22:1693\n106#1:1715\n78#1,22:1716\n112#1,5:1738\n123#1:1743\n112#1,5:1744\n129#1,5:1749\n140#1:1754\n129#1,5:1755\n78#1,22:1760\n112#1,5:1782\n129#1,5:1787\n1088#2,2:1792\n13293#3,2:1794\n13293#3,2:1796\n296#4,2:1798\n296#4,2:1800\n1586#4:1803\n1661#4,3:1804\n1586#4:1807\n1661#4,3:1808\n1#5:1802\n*S KotlinDebug\n*F\n+ 1 Strings.kt\nkotlin/text/StringsKt__StringsKt\n*L\n106#1:1661,22\n123#1:1683,5\n140#1:1688,5\n145#1:1693,22\n150#1:1715\n150#1:1716,22\n155#1:1738,5\n160#1:1743\n160#1:1744,5\n165#1:1749,5\n170#1:1754\n170#1:1755,5\n175#1:1760,22\n186#1:1782,5\n197#1:1787,5\n310#1:1792,2\n976#1:1794,2\n1000#1:1796,2\n1039#1:1798,2\n1045#1:1800,2\n1425#1:1803\n1425#1:1804,3\n1467#1:1807\n1467#1:1808,3\n*E\n"})
/* loaded from: classes.dex */
class StringsKt__StringsKt extends StringsKt__StringsJVMKt {

    @Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\n\u0010\u0004\u001a\u00020\u0005H\u0096\u0080\u0004J\n\u0010\u0006\u001a\u00020\u0007H\u0096\u0082\u0004R\u000f\u0010\u0002\u001a\u00020\u0003X\u0082\u008e\b¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"kotlin/text/StringsKt__StringsKt$iterator$1", "Lkotlin/collections/CharIterator;", "index", _UrlKt.FRAGMENT_ENCODE_SET, "nextChar", _UrlKt.FRAGMENT_ENCODE_SET, "hasNext", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    /* renamed from: kotlin.text.StringsKt__StringsKt$iterator$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static final class AnonymousClass1 extends CharIterator {
        final /* synthetic */ CharSequence $this_iterator;
        private int index;

        public AnonymousClass1(CharSequence r1) {
            this.$this_iterator = r1;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.index >= this.$this_iterator.length()) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // kotlin.collections.CharIterator
        public char nextChar() {
            CharSequence r0 = this.$this_iterator;
            int r1 = this.index;
            this.index = r1 + 1;
            return r0.charAt(r1);
        }
    }

    public static /* synthetic */ Pair $r8$lambda$4JwvsE_x9Qd4xJfLltMpDIP3RoA(char[] r0, boolean r1, CharSequence r2, int r3) {
        return rangesDelimitedBy$lambda$0$StringsKt__StringsKt(r0, r1, r2, r3);
    }

    public static /* synthetic */ String $r8$lambda$ELi_fx0JGmTRPjaALgYW_moJCr4(CharSequence r0, IntRange r1) {
        return splitToSequence$lambda$0$StringsKt__StringsKt(r0, r1);
    }

    public static /* synthetic */ Pair $r8$lambda$gKUnDVwn2f7eC7TWcWbv0SrbwQg(List r0, boolean r1, CharSequence r2, int r3) {
        return rangesDelimitedBy$lambda$1$StringsKt__StringsKt(r0, r1, r2, r3);
    }

    public static /* synthetic */ String $r8$lambda$iTftvDdL_lzGjVlJQTmElLXBbYs(CharSequence r0, IntRange r1) {
        return splitToSequence$lambda$1$StringsKt__StringsKt(r0, r1);
    }

    public StringsKt__StringsKt() {
    }

    public static final String commonPrefixWith(CharSequence r5, CharSequence r6, boolean r7) {
        int r0 = Math.min(r5.length(), r6.length());
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L7;
        if (CharsKt__CharKt.equals(r5.charAt(r2), r6.charAt(r2), r7) == false) goto L7;
        r2 = r2 + 1;
    L7:
        int r72 = r2 - 1;
        if (hasSurrogatePairAt(r5, r72) == false) goto L10;
    L11:
        r2 = r2 - 1;
    L13:
        return r5.subSequence(0, r2).toString();
    L10:
        if (hasSurrogatePairAt(r6, r72) == false) goto L13;
        goto L11
    }

    public static /* synthetic */ String commonPrefixWith$default(CharSequence r0, CharSequence r1, boolean r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = false;
    L6:
        return commonPrefixWith(r0, r1, r2);
    }

    public static final String commonSuffixWith(CharSequence r6, CharSequence r7, boolean r8) {
        int r0 = r6.length();
        int r2 = Math.min(r0, r7.length());
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L8;
        if (CharsKt__CharKt.equals(r6.charAt((r0 - r3) - 1), r7.charAt((r1 - r3) - 1), r8) == false) goto L8;
        r3 = r3 + 1;
    L8:
        if (hasSurrogatePairAt(r6, (r0 - r3) - 1) == false) goto L10;
    L11:
        r3 = r3 - 1;
    L13:
        return r6.subSequence(r0 - r3, r0).toString();
    L10:
        if (hasSurrogatePairAt(r7, (r1 - r3) - 1) == false) goto L13;
        goto L11
    }

    public static /* synthetic */ String commonSuffixWith$default(CharSequence r0, CharSequence r1, boolean r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = false;
    L6:
        return commonSuffixWith(r0, r1, r2);
    }

    public static final boolean contains(CharSequence r6, char r7, boolean r8) {
        if (indexOf$default(r6, r7, 0, r8, 2, null) < 0) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean contains(CharSequence r11, CharSequence r12, boolean r13) {
        if ((r12 instanceof String) == false) goto L9;
        if (indexOf$default(r11, (String) r12, 0, r13, 2, null) < 0) goto L7;
        return true;
    L7:
        return false;
    L9:
        if (indexOf$StringsKt__StringsKt$default(r11, r12, 0, r11.length(), r13, false, 16, null) < 0) goto L11;
        return true;
    L11:
        return false;
    }

    @InlineOnly
    private static final boolean contains(CharSequence r0, Regex r1) {
        return r1.containsMatchIn(r0);
    }

    public static /* synthetic */ boolean contains$default(CharSequence r0, char r1, boolean r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = false;
    L6:
        return contains(r0, r1, r2);
    }

    public static /* synthetic */ boolean contains$default(CharSequence r0, CharSequence r1, boolean r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = false;
    L6:
        return contains(r0, r1, r2);
    }

    public static final boolean contentEqualsIgnoreCaseImpl(CharSequence r6, CharSequence r7) {
        if ((r6 instanceof String) == true) goto L5;
    L8:
        if (r6 != r7) goto L11;
        return true;
    L11:
        if (r6 == null) goto L23;
        if (r7 == null) goto L23;
        if (r6.length() != r7.length()) goto L23;
        int r2 = r6.length();
        int r3 = 0;
    L17:
        if (r3 >= r2) goto L22;
        if (CharsKt__CharKt.equals(r6.charAt(r3), r7.charAt(r3), true) == false) goto L20;
        r3 = r3 + 1;
        goto L17
    L20:
        return false;
    L22:
        return true;
    L23:
        return false;
    L5:
        if ((r7 instanceof String) == false) goto L8;
        return StringsKt__StringsJVMKt.equals((String) r6, (String) r7, true);
    }

    public static final boolean contentEqualsImpl(CharSequence r6, CharSequence r7) {
        if ((r6 instanceof String) == false) goto L9;
        if ((r7 instanceof String) == false) goto L9;
        return Intrinsics.areEqual(r6, r7);
    L9:
        if (r6 != r7) goto L12;
        return true;
    L12:
        if (r6 == null) goto L24;
        if (r7 == null) goto L24;
        if (r6.length() != r7.length()) goto L24;
        int r2 = r6.length();
        int r3 = 0;
    L18:
        if (r3 >= r2) goto L23;
        if (r6.charAt(r3) != r7.charAt(r3)) goto L21;
        r3 = r3 + 1;
        goto L18
    L21:
        return false;
    L23:
        return true;
    L24:
        return false;
    }

    public static final boolean endsWith(CharSequence r1, char r2, boolean r3) {
        if (r1.length() > 0) goto L5;
        return false;
    L5:
        if (CharsKt__CharKt.equals(r1.charAt(getLastIndex(r1)), r2, r3) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public static final boolean endsWith(CharSequence r8, CharSequence r9, boolean r10) {
        if (r10 == true) goto L10;
        if ((r8 instanceof String) == false) goto L10;
        if ((r9 instanceof String) == false) goto L10;
        return StringsKt__StringsJVMKt.endsWith$default((String) r8, (String) r9, false, 2, null);
    L10:
        return regionMatchesImpl(r8, r8.length() - r9.length(), r9, 0, r9.length(), r10);
    }

    public static /* synthetic */ boolean endsWith$default(CharSequence r0, char r1, boolean r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = false;
    L6:
        return endsWith(r0, r1, r2);
    }

    public static /* synthetic */ boolean endsWith$default(CharSequence r0, CharSequence r1, boolean r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = false;
    L6:
        return endsWith(r0, r1, r2);
    }

    public static final Pair<Integer, String> findAnyOf(CharSequence r1, Collection<String> r2, int r3, boolean r4) {
        return findAnyOf$StringsKt__StringsKt(r1, r2, r3, r4, false);
    }

    private static final Pair<Integer, String> findAnyOf$StringsKt__StringsKt(CharSequence r9, Collection<String> r10, int r11, boolean r12, boolean r13) {
        if (r12 == false) goto L5;
    L14:
        CharSequence r1 = r9;
        if (r13 == true) goto L17;
        IntProgression r112 = new IntRange(RangesKt.coerceAtLeast(r11, 0), r1.length());
    L19:
        if ((r1 instanceof String) == false) goto L40;
        int r92 = r112.getFirst();
        int r132 = r112.getLast();
        int r113 = r112.getStep();
        if (r113 <= 0) goto L23;
        if (r92 > r132) goto L23;
    L25:
        int r5 = r92;
    L26:
        Iterator<T> r93 = r10.iterator();
    L28:
        if (r93.hasNext() == false) goto L33;
        Object r8 = r93.next();
        String r2 = (String) r8;
        boolean r7 = r12;
        if (StringsKt__StringsJVMKt.regionMatches(r2, 0, (String) r1, r5, r2.length(), r7) == true) goto L34;
        r12 = r7;
    L34:
        String r82 = (String) r8;
        if (r82 != null) goto L37;
        if (r5 == r132) goto L60;
        r5 = r5 + r113;
        r12 = r7;
    L60:
        return null;
    L37:
        return TuplesKt.to(Integer.valueOf(r5), r82);
    L33:
        r7 = r12;
        r8 = null;
    L23:
        if (r113 >= 0) goto L60;
        if (r132 > r92) goto L60;
    L40:
        boolean r72 = r12;
        int r94 = r112.getFirst();
        int r122 = r112.getLast();
        int r114 = r112.getStep();
        if (r114 <= 0) goto L43;
        if (r94 > r122) goto L43;
    L45:
        int r6 = r94;
    L46:
        Iterator<T> r95 = r10.iterator();
    L48:
        if (r95.hasNext() == false) goto L53;
        Object r133 = r95.next();
        String r3 = (String) r133;
        boolean r83 = r72;
        CharSequence r52 = r1;
        r72 = r83;
        if (regionMatchesImpl(r3, 0, r52, r6, r3.length(), r83) == true) goto L54;
        r1 = r52;
    L54:
        String r134 = (String) r133;
        if (r134 != null) goto L57;
        if (r6 == r122) goto L60;
        r6 = r6 + r114;
        r1 = r52;
        goto L46
    L57:
        return TuplesKt.to(Integer.valueOf(r6), r134);
    L53:
        r52 = r1;
        r133 = null;
    L43:
        if (r114 >= 0) goto L60;
        if (r122 > r94) goto L60;
    L17:
        r112 = RangesKt.downTo(RangesKt.coerceAtMost(r11, getLastIndex(r1)), 0);
        goto L19
    L5:
        if (r10.size() != 1) goto L14;
        String r22 = (String) CollectionsKt.single(r10);
        if (r13 == true) goto L9;
        int r96 = indexOf$default(r9, r22, r11, false, 4, null);
    L10:
        if (r96 >= 0) goto L13;
        return null;
    L13:
        return TuplesKt.to(Integer.valueOf(r96), r22);
    L9:
        r96 = lastIndexOf$default(r9, r22, r11, false, 4, null);
        goto L10
    }

    public static /* synthetic */ Pair findAnyOf$default(CharSequence r1, Collection r2, int r3, boolean r4, int r5, Object r6) {
        if ((r5 & 2) == 0) goto L6;
        r3 = 0;
    L6:
        if ((r5 & 4) == 0) goto L9;
        r4 = false;
    L9:
        return findAnyOf(r1, r2, r3, r4);
    }

    public static final Pair<Integer, String> findLastAnyOf(CharSequence r1, Collection<String> r2, int r3, boolean r4) {
        return findAnyOf$StringsKt__StringsKt(r1, r2, r3, r4, true);
    }

    public static /* synthetic */ Pair findLastAnyOf$default(CharSequence r0, Collection r1, int r2, boolean r3, int r4, Object r5) {
        if ((r4 & 2) == 0) goto L6;
        r2 = getLastIndex(r0);
    L6:
        if ((r4 & 4) == 0) goto L9;
        r3 = false;
    L9:
        return findLastAnyOf(r0, r1, r2, r3);
    }

    public static final IntRange getIndices(CharSequence r2) {
        return new IntRange(0, r2.length() - 1);
    }

    public static int getLastIndex(CharSequence r0) {
        return r0.length() - 1;
    }

    public static final boolean hasSurrogatePairAt(CharSequence r1, int r2) {
        if (r2 >= 0) goto L4;
        return false;
    L4:
        if (r2 <= (r1.length() - 2)) goto L6;
        return false;
    L6:
        if (Character.isHighSurrogate(r1.charAt(r2)) == true) goto L8;
        return false;
    L8:
        if (Character.isLowSurrogate(r1.charAt(r2 + 1)) == false) goto L14;
        return true;
    L14:
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <C extends CharSequence & R, R> R ifBlank(C r1, Function0<? extends R> r2) {
        if (isBlank(r1) == true) goto L5;
        return r1;
    L5:
        return r2.invoke();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <C extends CharSequence & R, R> R ifEmpty(C r1, Function0<? extends R> r2) {
        if (r1.length() == 0) goto L5;
        return r1;
    L5:
        return r2.invoke();
    }

    public static final int indexOf(CharSequence r2, char r3, int r4, boolean r5) {
        if (r5 == true) goto L9;
        if ((r2 instanceof String) == false) goto L9;
        return ((String) r2).indexOf(r3, r4);
    L9:
        return indexOfAny(r2, new char[]{r3}, r4, r5);
    }

    public static final int indexOf(CharSequence r8, String r9, int r10, boolean r11) {
        if (r11 == true) goto L9;
        if ((r8 instanceof String) == false) goto L9;
        return ((String) r8).indexOf(r9, r10);
    L9:
        return indexOf$StringsKt__StringsKt$default(r8, r9, r10, r8.length(), r11, false, 16, null);
    }

    private static final int indexOf$StringsKt__StringsKt(CharSequence r10, CharSequence r11, int r12, int r13, boolean r14, boolean r15) {
        if (r15 == true) goto L5;
        IntProgression r152 = new IntRange(RangesKt.coerceAtLeast(r12, 0), RangesKt.coerceAtMost(r13, r10.length()));
    L7:
        if ((r10 instanceof String) == true) goto L9;
    L21:
        boolean r5 = r14;
        int r122 = r152.getFirst();
        int r132 = r152.getLast();
        int r142 = r152.getStep();
        if (r142 <= 0) goto L24;
        if (r122 > r132) goto L24;
    L26:
        int r7 = r122;
    L27:
        boolean r9 = r5;
        CharSequence r6 = r10;
        CharSequence r4 = r11;
        r5 = r9;
        if (regionMatchesImpl(r4, 0, r6, r7, r11.length(), r9) == true) goto L29;
        if (r7 == r132) goto L42;
        r7 = r7 + r142;
        r11 = r4;
        r10 = r6;
        goto L27
    L42:
        return -1;
    L29:
        return r7;
    L24:
        if (r142 >= 0) goto L39;
        if (r132 <= r122) goto L26;
        return -1;
    L39:
        return -1;
    L9:
        if ((r11 instanceof String) == false) goto L21;
        int r123 = r152.getFirst();
        int r133 = r152.getLast();
        int r153 = r152.getStep();
        if (r153 <= 0) goto L13;
        if (r123 > r133) goto L13;
    L15:
        int r3 = r123;
    L16:
        String r0 = (String) r11;
        boolean r52 = r14;
        if (StringsKt__StringsJVMKt.regionMatches(r0, 0, (String) r10, r3, r0.length(), r52) == true) goto L18;
        if (r3 == r133) goto L41;
        r3 = r3 + r153;
        r14 = r52;
        goto L16
    L41:
        return -1;
    L18:
        return r3;
    L13:
        if (r153 >= 0) goto L32;
        if (r133 <= r123) goto L15;
        return -1;
    L32:
        return -1;
    L5:
        r152 = RangesKt.downTo(RangesKt.coerceAtMost(r12, getLastIndex(r10)), RangesKt.coerceAtLeast(r13, 0));
        goto L7
    }

    public static /* synthetic */ int indexOf$StringsKt__StringsKt$default(CharSequence r6, CharSequence r7, int r8, int r9, boolean r10, boolean r11, int r12, Object r13) {
        if ((r12 & 16) == 0) goto L6;
        r11 = false;
    L6:
        return indexOf$StringsKt__StringsKt(r6, r7, r8, r9, r10, r11);
    }

    public static /* synthetic */ int indexOf$default(CharSequence r1, char r2, int r3, boolean r4, int r5, Object r6) {
        if ((r5 & 2) == 0) goto L6;
        r3 = 0;
    L6:
        if ((r5 & 4) == 0) goto L9;
        r4 = false;
    L9:
        return indexOf(r1, r2, r3, r4);
    }

    public static /* synthetic */ int indexOf$default(CharSequence r1, String r2, int r3, boolean r4, int r5, Object r6) {
        if ((r5 & 2) == 0) goto L6;
        r3 = 0;
    L6:
        if ((r5 & 4) == 0) goto L9;
        r4 = false;
    L9:
        return indexOf(r1, r2, r3, r4);
    }

    public static final int indexOfAny(CharSequence r1, Collection<String> r2, int r3, boolean r4) {
        Pair<Integer, String> r12 = findAnyOf$StringsKt__StringsKt(r1, r2, r3, r4, false);
        if (r12 != null) goto L5;
        return -1;
    L5:
        return r12.getFirst().intValue();
    }

    public static final int indexOfAny(CharSequence r6, char[] r7, int r8, boolean r9) {
        if (r9 == false) goto L4;
    L9:
        int r82 = RangesKt.coerceAtLeast(r8, 0);
        int r1 = getLastIndex(r6);
        if (r82 > r1) goto L19;
    L11:
        char r2 = r6.charAt(r82);
        int r3 = r7.length;
        int r4 = 0;
    L12:
        if (r4 >= r3) goto L17;
        if (CharsKt__CharKt.equals(r7[r4], r2, r9) == true) goto L15;
        r4 = r4 + 1;
        goto L12
    L15:
        return r82;
    L17:
        if (r82 == r1) goto L24;
        r82 = r82 + 1;
        goto L11
    L24:
        return -1;
    L19:
        return -1;
    L4:
        if (r7.length != 1) goto L9;
        if ((r6 instanceof String) == false) goto L9;
        return ((String) r6).indexOf(ArraysKt.single(r7), r8);
    }

    public static /* synthetic */ int indexOfAny$default(CharSequence r1, Collection r2, int r3, boolean r4, int r5, Object r6) {
        if ((r5 & 2) == 0) goto L6;
        r3 = 0;
    L6:
        if ((r5 & 4) == 0) goto L9;
        r4 = false;
    L9:
        return indexOfAny(r1, r2, r3, r4);
    }

    public static /* synthetic */ int indexOfAny$default(CharSequence r1, char[] r2, int r3, boolean r4, int r5, Object r6) {
        if ((r5 & 2) == 0) goto L6;
        r3 = 0;
    L6:
        if ((r5 & 4) == 0) goto L9;
        r4 = false;
    L9:
        return indexOfAny(r1, r2, r3, r4);
    }

    public static boolean isBlank(CharSequence r3) {
        int r1 = 0;
    L4:
        if (r1 >= r3.length()) goto L9;
        if (CharsKt__CharJVMKt.isWhitespace(r3.charAt(r1)) == false) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        return false;
    L9:
        return true;
    }

    @InlineOnly
    private static final boolean isEmpty(CharSequence r0) {
        if (r0.length() != 0) goto L6;
        return true;
    L6:
        return false;
    }

    @InlineOnly
    private static final boolean isNotBlank(CharSequence r0) {
        return !isBlank(r0);
    }

    @InlineOnly
    private static final boolean isNotEmpty(CharSequence r0) {
        if (r0.length() <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    @InlineOnly
    private static final boolean isNullOrBlank(CharSequence r0) {
        if (r0 != null) goto L4;
        return true;
    L4:
        if (isBlank(r0) == true) goto L10;
        return false;
    L10:
        return true;
    }

    @InlineOnly
    private static final boolean isNullOrEmpty(CharSequence r0) {
        if (r0 != null) goto L4;
        return true;
    L4:
        if (r0.length() == 0) goto L10;
        return false;
    L10:
        return true;
    }

    public static final CharIterator iterator(CharSequence r1) {
        return new AnonymousClass1(r1);
    }

    public static final int lastIndexOf(CharSequence r2, char r3, int r4, boolean r5) {
        if (r5 == true) goto L9;
        if ((r2 instanceof String) == false) goto L9;
        return ((String) r2).lastIndexOf(r3, r4);
    L9:
        return lastIndexOfAny(r2, new char[]{r3}, r4, r5);
    }

    public static final int lastIndexOf(CharSequence r6, String r7, int r8, boolean r9) {
        if (r9 == true) goto L9;
        if ((r6 instanceof String) == false) goto L9;
        return ((String) r6).lastIndexOf(r7, r8);
    L9:
        return indexOf$StringsKt__StringsKt(r6, r7, r8, 0, r9, true);
    }

    public static /* synthetic */ int lastIndexOf$default(CharSequence r0, char r1, int r2, boolean r3, int r4, Object r5) {
        if ((r4 & 2) == 0) goto L6;
        r2 = getLastIndex(r0);
    L6:
        if ((r4 & 4) == 0) goto L9;
        r3 = false;
    L9:
        return lastIndexOf(r0, r1, r2, r3);
    }

    public static /* synthetic */ int lastIndexOf$default(CharSequence r0, String r1, int r2, boolean r3, int r4, Object r5) {
        if ((r4 & 2) == 0) goto L6;
        r2 = getLastIndex(r0);
    L6:
        if ((r4 & 4) == 0) goto L9;
        r3 = false;
    L9:
        return lastIndexOf(r0, r1, r2, r3);
    }

    public static final int lastIndexOfAny(CharSequence r1, Collection<String> r2, int r3, boolean r4) {
        Pair<Integer, String> r12 = findAnyOf$StringsKt__StringsKt(r1, r2, r3, r4, true);
        if (r12 != null) goto L5;
        return -1;
    L5:
        return r12.getFirst().intValue();
    }

    public static final int lastIndexOfAny(CharSequence r4, char[] r5, int r6, boolean r7) {
        if (r7 == false) goto L4;
    L9:
        int r62 = RangesKt.coerceAtMost(r6, getLastIndex(r4));
    L11:
        if ((-1) >= r62) goto L19;
        char r0 = r4.charAt(r62);
        int r1 = r5.length;
        int r2 = 0;
    L13:
        if (r2 >= r1) goto L18;
        if (CharsKt__CharKt.equals(r5[r2], r0, r7) == true) goto L16;
        r2 = r2 + 1;
        goto L13
    L16:
        return r62;
    L18:
        r62 = r62 - 1;
        goto L11
    L19:
        return -1;
    L4:
        if (r5.length != 1) goto L9;
        if ((r4 instanceof String) == false) goto L9;
        return ((String) r4).lastIndexOf(ArraysKt.single(r5), r6);
    }

    public static /* synthetic */ int lastIndexOfAny$default(CharSequence r0, Collection r1, int r2, boolean r3, int r4, Object r5) {
        if ((r4 & 2) == 0) goto L6;
        r2 = getLastIndex(r0);
    L6:
        if ((r4 & 4) == 0) goto L9;
        r3 = false;
    L9:
        return lastIndexOfAny(r0, r1, r2, r3);
    }

    public static /* synthetic */ int lastIndexOfAny$default(CharSequence r0, char[] r1, int r2, boolean r3, int r4, Object r5) {
        if ((r4 & 2) == 0) goto L6;
        r2 = getLastIndex(r0);
    L6:
        if ((r4 & 4) == 0) goto L9;
        r3 = false;
    L9:
        return lastIndexOfAny(r0, r1, r2, r3);
    }

    public static final Sequence<String> lineSequence(CharSequence r1) {
        return new StringsKt__StringsKt$lineSequence$$inlined$Sequence$1(r1);
    }

    public static List<String> lines(CharSequence r0) {
        return SequencesKt.toList(lineSequence(r0));
    }

    @InlineOnly
    private static final boolean matches(CharSequence r0, Regex r1) {
        return r1.matches(r0);
    }

    @InlineOnly
    private static final String orEmpty(String r0) {
        if (r0 != null) goto L5;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        return r0;
    }

    public static final CharSequence padEnd(CharSequence r1, int r2, char r3) {
        if (r2 >= 0) goto L4;
        CharsKt__CharKt$$ExternalSyntheticBUOutline0.m("Desired length ", r2, " is less than zero.");
        return null;
    L4:
        if (r2 <= r1.length()) goto L6;
        StringBuilder r0 = new StringBuilder(r2);
        r0.append(r1);
        int r22 = r2 - r1.length();
        int r12 = 1;
        if (1 > r22) goto L12;
    L9:
        r0.append(r3);
        if (r12 == r22) goto L12;
        r12 = r12 + 1;
    L12:
        return r0;
    L6:
        return r1.subSequence(0, r1.length());
    }

    public static String padEnd(String r0, int r1, char r2) {
        return padEnd(r0, r1, r2).toString();
    }

    public static /* synthetic */ CharSequence padEnd$default(CharSequence r0, int r1, char r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = ' ';
    L6:
        return padEnd(r0, r1, r2);
    }

    public static /* synthetic */ String padEnd$default(String r0, int r1, char r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = ' ';
    L6:
        return padEnd(r0, r1, r2);
    }

    public static final CharSequence padStart(CharSequence r2, int r3, char r4) {
        if (r3 >= 0) goto L4;
        CharsKt__CharKt$$ExternalSyntheticBUOutline0.m("Desired length ", r3, " is less than zero.");
        return null;
    L4:
        if (r3 <= r2.length()) goto L6;
        StringBuilder r0 = new StringBuilder(r3);
        int r32 = r3 - r2.length();
        int r1 = 1;
        if (1 > r32) goto L12;
    L9:
        r0.append(r4);
        if (r1 == r32) goto L12;
        r1 = r1 + 1;
    L12:
        r0.append(r2);
        return r0;
    L6:
        return r2.subSequence(0, r2.length());
    }

    public static String padStart(String r0, int r1, char r2) {
        return padStart(r0, r1, r2).toString();
    }

    public static /* synthetic */ CharSequence padStart$default(CharSequence r0, int r1, char r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = ' ';
    L6:
        return padStart(r0, r1, r2);
    }

    public static /* synthetic */ String padStart$default(String r0, int r1, char r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = ' ';
    L6:
        return padStart(r0, r1, r2);
    }

    private static final Sequence<IntRange> rangesDelimitedBy$StringsKt__StringsKt(CharSequence r2, char[] r3, int r4, boolean r5, int r6) {
        requireNonNegativeLimit(r6);
        return new DelimitedRangesSequence(r2, r4, r6, new StringsKt__StringsKt$$ExternalSyntheticLambda2(r3, r5));
    }

    private static final Sequence<IntRange> rangesDelimitedBy$StringsKt__StringsKt(CharSequence r2, String[] r3, int r4, boolean r5, int r6) {
        requireNonNegativeLimit(r6);
        return new DelimitedRangesSequence(r2, r4, r6, new StringsKt__StringsKt$$ExternalSyntheticLambda3(ArraysKt.asList(r3), r5));
    }

    public static /* synthetic */ Sequence rangesDelimitedBy$StringsKt__StringsKt$default(CharSequence r1, char[] r2, int r3, boolean r4, int r5, int r6, Object r7) {
        if ((r6 & 2) == 0) goto L6;
        r3 = 0;
    L6:
        if ((r6 & 4) == 0) goto L9;
        r4 = false;
    L9:
        if ((r6 & 8) == 0) goto L12;
        r5 = 0;
    L12:
        return rangesDelimitedBy$StringsKt__StringsKt(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ Sequence rangesDelimitedBy$StringsKt__StringsKt$default(CharSequence r1, String[] r2, int r3, boolean r4, int r5, int r6, Object r7) {
        if ((r6 & 2) == 0) goto L6;
        r3 = 0;
    L6:
        if ((r6 & 4) == 0) goto L9;
        r4 = false;
    L9:
        if ((r6 & 8) == 0) goto L12;
        r5 = 0;
    L12:
        return rangesDelimitedBy$StringsKt__StringsKt(r1, r2, r3, r4, r5);
    }

    private static final Pair rangesDelimitedBy$lambda$0$StringsKt__StringsKt(char[] r0, boolean r1, CharSequence r2, int r3) {
        int r02 = indexOfAny(r2, r0, r3, r1);
        if (r02 >= 0) goto L7;
        return null;
    L7:
        return TuplesKt.to(Integer.valueOf(r02), 1);
    }

    private static final Pair rangesDelimitedBy$lambda$1$StringsKt__StringsKt(List r1, boolean r2, CharSequence r3, int r4) {
        Pair<Integer, String> r12 = findAnyOf$StringsKt__StringsKt(r3, r1, r4, r2, false);
        if (r12 != null) goto L5;
        return null;
    L5:
        return TuplesKt.to(r12.getFirst(), Integer.valueOf(r12.getSecond().length()));
    }

    public static final boolean regionMatchesImpl(CharSequence r4, int r5, CharSequence r6, int r7, int r8, boolean r9) {
        if (r7 < 0) goto L18;
        if (r5 < 0) goto L18;
        if (r5 > (r4.length() - r8)) goto L18;
        if (r7 > (r6.length() - r8)) goto L18;
        int r1 = 0;
    L11:
        if (r1 >= r8) goto L16;
        if (CharsKt__CharKt.equals(r4.charAt(r5 + r1), r6.charAt(r7 + r1), r9) == false) goto L14;
        r1 = r1 + 1;
        goto L11
    L14:
        return false;
    L16:
        return true;
    L18:
        return false;
    }

    public static final CharSequence removePrefix(CharSequence r3, CharSequence r4) {
        if (startsWith$default(r3, r4, false, 2, null) == false) goto L7;
        return r3.subSequence(r4.length(), r3.length());
    L7:
        return r3.subSequence(0, r3.length());
    }

    public static String removePrefix(String r3, CharSequence r4) {
        if (startsWith$default(r3, r4, false, 2, null) == true) goto L5;
        return r3;
    L5:
        return r3.substring(r4.length());
    }

    public static final CharSequence removeRange(CharSequence r4, int r5, int r6) {
        if (r6 >= r5) goto L4;
        StringsKt__StringsKt$$ExternalSyntheticBUOutline0.m("End index (", r6, ") is less than start index (", r5);
        return null;
    L4:
        if (r6 == r5) goto L6;
        StringBuilder r1 = new StringBuilder(r4.length() - (r6 - r5));
        r1.append(r4, 0, r5);
        r1.append(r4, r6, r4.length());
        return r1;
    L6:
        return r4.subSequence(0, r4.length());
    }

    public static final CharSequence removeRange(CharSequence r1, IntRange r2) {
        return removeRange(r1, r2.getStart().intValue(), r2.getEndInclusive().intValue() + 1);
    }

    @InlineOnly
    private static final String removeRange(String r0, int r1, int r2) {
        return removeRange(r0, r1, r2).toString();
    }

    @InlineOnly
    private static final String removeRange(String r0, IntRange r1) {
        return removeRange(r0, r1).toString();
    }

    public static final CharSequence removeSuffix(CharSequence r3, CharSequence r4) {
        if (endsWith$default(r3, r4, false, 2, null) == false) goto L7;
        return r3.subSequence(0, r3.length() - r4.length());
    L7:
        return r3.subSequence(0, r3.length());
    }

    public static String removeSuffix(String r3, CharSequence r4) {
        if (endsWith$default(r3, r4, false, 2, null) == true) goto L5;
        return r3;
    L5:
        return r3.substring(0, r3.length() - r4.length());
    }

    public static final CharSequence removeSurrounding(CharSequence r0, CharSequence r1) {
        return removeSurrounding(r0, r1, r1);
    }

    public static final CharSequence removeSurrounding(CharSequence r4, CharSequence r5, CharSequence r6) {
        if (r4.length() < (r5.length() + r6.length())) goto L11;
        if (startsWith$default(r4, r5, false, 2, null) == false) goto L11;
        if (endsWith$default(r4, r6, false, 2, null) == false) goto L11;
        return r4.subSequence(r5.length(), r4.length() - r6.length());
    L11:
        return r4.subSequence(0, r4.length());
    }

    public static String removeSurrounding(String r0, CharSequence r1) {
        return removeSurrounding(r0, r1, r1);
    }

    public static final String removeSurrounding(String r4, CharSequence r5, CharSequence r6) {
        if (r4.length() >= (r5.length() + r6.length())) goto L5;
        return r4;
    L5:
        if (startsWith$default(r4, r5, false, 2, null) == true) goto L7;
        return r4;
    L7:
        if (endsWith$default(r4, r6, false, 2, null) == true) goto L9;
        return r4;
    L9:
        return r4.substring(r5.length(), r4.length() - r6.length());
    }

    @InlineOnly
    private static final String replace(CharSequence r0, Regex r1, String r2) {
        return r1.replace(r0, r2);
    }

    @InlineOnly
    private static final String replace(CharSequence r0, Regex r1, Function1<? super MatchResult, ? extends CharSequence> r2) {
        return r1.replace(r0, r2);
    }

    public static final String replaceAfter(String r6, char r7, String r8, String r9) {
        int r62 = indexOf$default(r6, r7, 0, false, 6, null);
        if (r62 != (-1)) goto L6;
        return r9;
    L6:
        return replaceRange(r6, r62 + 1, r6.length(), r8).toString();
    }

    public static final String replaceAfter(String r6, String r7, String r8, String r9) {
        int r62 = indexOf$default(r6, r7, 0, false, 6, null);
        if (r62 != (-1)) goto L6;
        return r9;
    L6:
        return replaceRange(r6, r62 + r7.length(), r6.length(), r8).toString();
    }

    public static /* synthetic */ String replaceAfter$default(String r0, char r1, String r2, String r3, int r4, Object r5) {
        if ((r4 & 4) == 0) goto L6;
        r3 = r0;
    L6:
        return replaceAfter(r0, r1, r2, r3);
    }

    public static /* synthetic */ String replaceAfter$default(String r0, String r1, String r2, String r3, int r4, Object r5) {
        if ((r4 & 4) == 0) goto L6;
        r3 = r0;
    L6:
        return replaceAfter(r0, r1, r2, r3);
    }

    public static final String replaceAfterLast(String r6, char r7, String r8, String r9) {
        int r62 = lastIndexOf$default(r6, r7, 0, false, 6, null);
        if (r62 != (-1)) goto L6;
        return r9;
    L6:
        return replaceRange(r6, r62 + 1, r6.length(), r8).toString();
    }

    public static final String replaceAfterLast(String r6, String r7, String r8, String r9) {
        int r62 = lastIndexOf$default(r6, r7, 0, false, 6, null);
        if (r62 != (-1)) goto L6;
        return r9;
    L6:
        return replaceRange(r6, r62 + r7.length(), r6.length(), r8).toString();
    }

    public static /* synthetic */ String replaceAfterLast$default(String r0, char r1, String r2, String r3, int r4, Object r5) {
        if ((r4 & 4) == 0) goto L6;
        r3 = r0;
    L6:
        return replaceAfterLast(r0, r1, r2, r3);
    }

    public static /* synthetic */ String replaceAfterLast$default(String r0, String r1, String r2, String r3, int r4, Object r5) {
        if ((r4 & 4) == 0) goto L6;
        r3 = r0;
    L6:
        return replaceAfterLast(r0, r1, r2, r3);
    }

    public static final String replaceBefore(String r6, char r7, String r8, String r9) {
        int r62 = indexOf$default(r6, r7, 0, false, 6, null);
        if (r62 != (-1)) goto L6;
        return r9;
    L6:
        return replaceRange(r6, 0, r62, r8).toString();
    }

    public static final String replaceBefore(String r6, String r7, String r8, String r9) {
        int r62 = indexOf$default(r6, r7, 0, false, 6, null);
        if (r62 != (-1)) goto L6;
        return r9;
    L6:
        return replaceRange(r6, 0, r62, r8).toString();
    }

    public static /* synthetic */ String replaceBefore$default(String r0, char r1, String r2, String r3, int r4, Object r5) {
        if ((r4 & 4) == 0) goto L6;
        r3 = r0;
    L6:
        return replaceBefore(r0, r1, r2, r3);
    }

    public static /* synthetic */ String replaceBefore$default(String r0, String r1, String r2, String r3, int r4, Object r5) {
        if ((r4 & 4) == 0) goto L6;
        r3 = r0;
    L6:
        return replaceBefore(r0, r1, r2, r3);
    }

    public static final String replaceBeforeLast(String r6, char r7, String r8, String r9) {
        int r62 = lastIndexOf$default(r6, r7, 0, false, 6, null);
        if (r62 != (-1)) goto L6;
        return r9;
    L6:
        return replaceRange(r6, 0, r62, r8).toString();
    }

    public static final String replaceBeforeLast(String r6, String r7, String r8, String r9) {
        int r62 = lastIndexOf$default(r6, r7, 0, false, 6, null);
        if (r62 != (-1)) goto L6;
        return r9;
    L6:
        return replaceRange(r6, 0, r62, r8).toString();
    }

    public static /* synthetic */ String replaceBeforeLast$default(String r0, char r1, String r2, String r3, int r4, Object r5) {
        if ((r4 & 4) == 0) goto L6;
        r3 = r0;
    L6:
        return replaceBeforeLast(r0, r1, r2, r3);
    }

    public static /* synthetic */ String replaceBeforeLast$default(String r0, String r1, String r2, String r3, int r4, Object r5) {
        if ((r4 & 4) == 0) goto L6;
        r3 = r0;
    L6:
        return replaceBeforeLast(r0, r1, r2, r3);
    }

    @InlineOnly
    private static final String replaceFirst(CharSequence r0, Regex r1, String r2) {
        return r1.replaceFirst(r0, r2);
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "replaceFirstCharWithChar")
    @OverloadResolutionByLambdaReturnType
    private static final String replaceFirstCharWithChar(String r1, Function1<? super Character, Character> r2) {
        if (r1.length() > 0) goto L5;
        return r1;
    L5:
        return r2.invoke(Character.valueOf(r1.charAt(0))).charValue() + r1.substring(1);
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    @JvmName(name = "replaceFirstCharWithCharSequence")
    @OverloadResolutionByLambdaReturnType
    private static final String replaceFirstCharWithCharSequence(String r2, Function1<? super Character, ? extends CharSequence> r3) {
        if (r2.length() > 0) goto L5;
        return r2;
    L5:
        return r3.invoke(Character.valueOf(r2.charAt(0))) + r2.substring(1);
    }

    public static final CharSequence replaceRange(CharSequence r2, int r3, int r4, CharSequence r5) {
        if (r4 < r3) goto L5;
        StringBuilder r0 = new StringBuilder();
        r0.append(r2, 0, r3);
        r0.append(r5);
        r0.append(r2, r4, r2.length());
        return r0;
    L5:
        StringsKt__StringsKt$$ExternalSyntheticBUOutline0.m("End index (", r4, ") is less than start index (", r3);
        return null;
    }

    public static final CharSequence replaceRange(CharSequence r1, IntRange r2, CharSequence r3) {
        return replaceRange(r1, r2.getStart().intValue(), r2.getEndInclusive().intValue() + 1, r3);
    }

    @InlineOnly
    private static final String replaceRange(String r0, int r1, int r2, CharSequence r3) {
        return replaceRange(r0, r1, r2, r3).toString();
    }

    @InlineOnly
    private static final String replaceRange(String r0, IntRange r1, CharSequence r2) {
        return replaceRange(r0, r1, r2).toString();
    }

    public static final void requireNonNegativeLimit(int r1) {
        if (r1 < 0) goto L4;
        return;
    L4:
        Utf8$$ExternalSyntheticBUOutline1.m("Limit must be non-negative, but was ", r1);
    }

    public static final int skipWhile(String r1, int r2, Function1<? super Character, Boolean> r3) {
    L3:
        if (r2 >= r1.length()) goto L7;
        if (r3.invoke(Character.valueOf(r1.charAt(r2))).booleanValue() == false) goto L7;
        r2 = r2 + 1;
    L7:
        return r2;
    }

    @InlineOnly
    private static final List<String> split(CharSequence r0, Regex r1, int r2) {
        return r1.split(r0, r2);
    }

    public static final List<String> split(CharSequence r7, char[] r8, boolean r9, int r10) {
        if (r8.length == 1) goto L5;
        Iterable r72 = SequencesKt.asIterable(rangesDelimitedBy$StringsKt__StringsKt$default(r7, r8, 0, r9, r10, 2, null));
        ArrayList r82 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r72, 10));
        Iterator r73 = r72.iterator();
    L8:
        if (r73.hasNext() == false) goto L10;
        r82.add(substring(r7, (IntRange) r73.next()));
        goto L8
    L10:
        return r82;
    L5:
        return split$StringsKt__StringsKt(r7, String.valueOf(r8[0]), r9, r10);
    }

    public static final List<String> split(CharSequence r7, String[] r8, boolean r9, int r10) {
        if (r8.length != 1) goto L9;
        String r0 = r8[0];
        if (r0.length() == 0) goto L9;
        return split$StringsKt__StringsKt(r7, r0, r9, r10);
    L9:
        Iterable r72 = SequencesKt.asIterable(rangesDelimitedBy$StringsKt__StringsKt$default(r7, r8, 0, r9, r10, 2, null));
        ArrayList r82 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r72, 10));
        Iterator r73 = r72.iterator();
    L11:
        if (r73.hasNext() == false) goto L13;
        r82.add(substring(r7, (IntRange) r73.next()));
        goto L11
    L13:
        return r82;
    }

    private static final List<String> split$StringsKt__StringsKt(CharSequence r7, String r8, boolean r9, int r10) {
        requireNonNegativeLimit(r10);
        int r0 = 0;
        int r1 = indexOf(r7, r8, 0, r9);
        if (r1 == (-1)) goto L23;
        if (r10 == 1) goto L23;
        if (r10 <= 0) goto L9;
        boolean r4 = true;
    L10:
        int r6 = 10;
        if (r4 == false) goto L13;
        r6 = RangesKt.coerceAtMost(r10, 10);
    L13:
        ArrayList r5 = new ArrayList(r6);
    L14:
        r5.add(r7.subSequence(r0, r1).toString());
        r0 = r8.length() + r1;
        if (r4 == true) goto L17;
    L18:
        r1 = indexOf(r7, r8, r0, r9);
        if (r1 != (-1)) goto L14;
    L20:
        r5.add(r7.subSequence(r0, r7.length()).toString());
        return r5;
    L17:
        if (r5.size() == (r10 - 1)) goto L20;
    L9:
        r4 = false;
    L23:
        return CollectionsKt.listOf(r7.toString());
    }

    public static /* synthetic */ List split$default(CharSequence r0, Regex r1, int r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = 0;
    L6:
        return r1.split(r0, r2);
    }

    public static /* synthetic */ List split$default(CharSequence r1, char[] r2, boolean r3, int r4, int r5, Object r6) {
        if ((r5 & 2) == 0) goto L6;
        r3 = false;
    L6:
        if ((r5 & 4) == 0) goto L9;
        r4 = 0;
    L9:
        return split(r1, r2, r3, r4);
    }

    public static /* synthetic */ List split$default(CharSequence r1, String[] r2, boolean r3, int r4, int r5, Object r6) {
        if ((r5 & 2) == 0) goto L6;
        r3 = false;
    L6:
        if ((r5 & 4) == 0) goto L9;
        r4 = 0;
    L9:
        return split(r1, r2, r3, r4);
    }

    @SinceKotlin(version = "1.6")
    @InlineOnly
    private static final Sequence<String> splitToSequence(CharSequence r0, Regex r1, int r2) {
        return r1.splitToSequence(r0, r2);
    }

    public static final Sequence<String> splitToSequence(CharSequence r7, char[] r8, boolean r9, int r10) {
        return SequencesKt.map(rangesDelimitedBy$StringsKt__StringsKt$default(r7, r8, 0, r9, r10, 2, null), new StringsKt__StringsKt$$ExternalSyntheticLambda1(r7));
    }

    public static final Sequence<String> splitToSequence(CharSequence r7, String[] r8, boolean r9, int r10) {
        return SequencesKt.map(rangesDelimitedBy$StringsKt__StringsKt$default(r7, r8, 0, r9, r10, 2, null), new StringsKt__StringsKt$$ExternalSyntheticLambda4(r7));
    }

    public static /* synthetic */ Sequence splitToSequence$default(CharSequence r0, Regex r1, int r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = 0;
    L6:
        return r1.splitToSequence(r0, r2);
    }

    public static /* synthetic */ Sequence splitToSequence$default(CharSequence r1, char[] r2, boolean r3, int r4, int r5, Object r6) {
        if ((r5 & 2) == 0) goto L6;
        r3 = false;
    L6:
        if ((r5 & 4) == 0) goto L9;
        r4 = 0;
    L9:
        return splitToSequence(r1, r2, r3, r4);
    }

    public static /* synthetic */ Sequence splitToSequence$default(CharSequence r1, String[] r2, boolean r3, int r4, int r5, Object r6) {
        if ((r5 & 2) == 0) goto L6;
        r3 = false;
    L6:
        if ((r5 & 4) == 0) goto L9;
        r4 = 0;
    L9:
        return splitToSequence(r1, r2, r3, r4);
    }

    private static final String splitToSequence$lambda$0$StringsKt__StringsKt(CharSequence r0, IntRange r1) {
        return substring(r0, r1);
    }

    private static final String splitToSequence$lambda$1$StringsKt__StringsKt(CharSequence r0, IntRange r1) {
        return substring(r0, r1);
    }

    public static final boolean startsWith(CharSequence r2, char r3, boolean r4) {
        if (r2.length() > 0) goto L5;
    L8:
        return false;
    L5:
        if (CharsKt__CharKt.equals(r2.charAt(0), r3, r4) == false) goto L8;
        return true;
    }

    public static final boolean startsWith(CharSequence r7, CharSequence r8, int r9, boolean r10) {
        if (r10 == true) goto L10;
        if ((r7 instanceof String) == false) goto L10;
        if ((r8 instanceof String) == false) goto L10;
        return StringsKt__StringsJVMKt.startsWith$default((String) r7, (String) r8, r9, false, 4, null);
    L10:
        return regionMatchesImpl(r7, r9, r8, 0, r8.length(), r10);
    }

    public static final boolean startsWith(CharSequence r6, CharSequence r7, boolean r8) {
        if (r8 == true) goto L10;
        if ((r6 instanceof String) == false) goto L10;
        if ((r7 instanceof String) == false) goto L10;
        return StringsKt__StringsJVMKt.startsWith$default((String) r6, (String) r7, false, 2, null);
    L10:
        return regionMatchesImpl(r6, 0, r7, 0, r7.length(), r8);
    }

    public static /* synthetic */ boolean startsWith$default(CharSequence r0, char r1, boolean r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = false;
    L6:
        return startsWith(r0, r1, r2);
    }

    public static /* synthetic */ boolean startsWith$default(CharSequence r0, CharSequence r1, int r2, boolean r3, int r4, Object r5) {
        if ((r4 & 4) == 0) goto L6;
        r3 = false;
    L6:
        return startsWith(r0, r1, r2, r3);
    }

    public static /* synthetic */ boolean startsWith$default(CharSequence r0, CharSequence r1, boolean r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = false;
    L6:
        return startsWith(r0, r1, r2);
    }

    public static final CharSequence subSequence(CharSequence r1, IntRange r2) {
        return r1.subSequence(r2.getStart().intValue(), r2.getEndInclusive().intValue() + 1);
    }

    @Deprecated(message = "Use parameters named startIndex and endIndex.", replaceWith = @ReplaceWith(expression = "subSequence(startIndex = start, endIndex = end)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = MVEL.VERSION, warningSince = "1.0")
    @InlineOnly
    private static final CharSequence subSequence(String r0, int r1, int r2) {
        return r0.subSequence(r1, r2);
    }

    @InlineOnly
    private static final String substring(CharSequence r0, int r1, int r2) {
        return r0.subSequence(r1, r2).toString();
    }

    public static final String substring(CharSequence r1, IntRange r2) {
        return r1.subSequence(r2.getStart().intValue(), r2.getEndInclusive().intValue() + 1).toString();
    }

    public static final String substring(String r1, IntRange r2) {
        return r1.substring(r2.getStart().intValue(), r2.getEndInclusive().intValue() + 1);
    }

    public static /* synthetic */ String substring$default(CharSequence r0, int r1, int r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = r0.length();
    L6:
        return r0.subSequence(r1, r2).toString();
    }

    public static String substringAfter(String r6, char r7, String r8) {
        int r62 = indexOf$default(r6, r7, 0, false, 6, null);
        if (r62 != (-1)) goto L6;
        return r8;
    L6:
        return r6.substring(r62 + 1, r6.length());
    }

    public static String substringAfter(String r6, String r7, String r8) {
        int r62 = indexOf$default(r6, r7, 0, false, 6, null);
        if (r62 != (-1)) goto L6;
        return r8;
    L6:
        return r6.substring(r62 + r7.length(), r6.length());
    }

    public static /* synthetic */ String substringAfter$default(String r0, char r1, String r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = r0;
    L6:
        return substringAfter(r0, r1, r2);
    }

    public static /* synthetic */ String substringAfter$default(String r0, String r1, String r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = r0;
    L6:
        return substringAfter(r0, r1, r2);
    }

    public static String substringAfterLast(String r6, char r7, String r8) {
        int r62 = lastIndexOf$default(r6, r7, 0, false, 6, null);
        if (r62 != (-1)) goto L6;
        return r8;
    L6:
        return r6.substring(r62 + 1, r6.length());
    }

    public static final String substringAfterLast(String r6, String r7, String r8) {
        int r62 = lastIndexOf$default(r6, r7, 0, false, 6, null);
        if (r62 != (-1)) goto L6;
        return r8;
    L6:
        return r6.substring(r62 + r7.length(), r6.length());
    }

    public static /* synthetic */ String substringAfterLast$default(String r0, char r1, String r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = r0;
    L6:
        return substringAfterLast(r0, r1, r2);
    }

    public static /* synthetic */ String substringAfterLast$default(String r0, String r1, String r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = r0;
    L6:
        return substringAfterLast(r0, r1, r2);
    }

    public static final String substringBefore(String r6, char r7, String r8) {
        int r62 = indexOf$default(r6, r7, 0, false, 6, null);
        if (r62 != (-1)) goto L6;
        return r8;
    L6:
        return r6.substring(0, r62);
    }

    public static final String substringBefore(String r6, String r7, String r8) {
        int r62 = indexOf$default(r6, r7, 0, false, 6, null);
        if (r62 != (-1)) goto L6;
        return r8;
    L6:
        return r6.substring(0, r62);
    }

    public static /* synthetic */ String substringBefore$default(String r0, char r1, String r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = r0;
    L6:
        return substringBefore(r0, r1, r2);
    }

    public static /* synthetic */ String substringBefore$default(String r0, String r1, String r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = r0;
    L6:
        return substringBefore(r0, r1, r2);
    }

    public static String substringBeforeLast(String r6, char r7, String r8) {
        int r62 = lastIndexOf$default(r6, r7, 0, false, 6, null);
        if (r62 != (-1)) goto L6;
        return r8;
    L6:
        return r6.substring(0, r62);
    }

    public static final String substringBeforeLast(String r6, String r7, String r8) {
        int r62 = lastIndexOf$default(r6, r7, 0, false, 6, null);
        if (r62 != (-1)) goto L6;
        return r8;
    L6:
        return r6.substring(0, r62);
    }

    public static /* synthetic */ String substringBeforeLast$default(String r0, char r1, String r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = r0;
    L6:
        return substringBeforeLast(r0, r1, r2);
    }

    public static /* synthetic */ String substringBeforeLast$default(String r0, String r1, String r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = r0;
    L6:
        return substringBeforeLast(r0, r1, r2);
    }

    @SinceKotlin(version = "1.5")
    public static final boolean toBooleanStrict(String r1) {
        if (Intrinsics.areEqual(r1, "true") == false) goto L7;
        return true;
    L7:
        if (Intrinsics.areEqual(r1, "false") == false) goto L10;
        return false;
    L10:
        Native$$ExternalSyntheticBUOutline5.m("The string doesn't represent a boolean value: ", r1);
        return false;
    }

    @SinceKotlin(version = "1.5")
    public static Boolean toBooleanStrictOrNull(String r1) {
        if (Intrinsics.areEqual(r1, "true") == false) goto L7;
        return Boolean.TRUE;
    L7:
        if (Intrinsics.areEqual(r1, "false") == true) goto L9;
        return null;
    L9:
        return Boolean.FALSE;
    }

    public static CharSequence trim(CharSequence r5) {
        int r0 = r5.length() - 1;
        int r2 = 0;
        boolean r3 = false;
    L3:
        if (r2 > r0) goto L16;
        if (r3 == true) goto L6;
        int r4 = r2;
    L7:
        boolean r42 = CharsKt__CharJVMKt.isWhitespace(r5.charAt(r4));
        if (r3 == false) goto L9;
        if (r42 == false) goto L16;
        r0 = r0 - 1;
        goto L3
    L9:
        if (r42 == false) goto L10;
        r2 = r2 + 1;
        goto L3
    L10:
        r3 = true;
        goto L3
    L6:
        r4 = r0;
    L16:
        return r5.subSequence(r2, r0 + 1);
    }

    public static final CharSequence trim(CharSequence r5, Function1<? super Character, Boolean> r6) {
        int r0 = r5.length() - 1;
        int r2 = 0;
        boolean r3 = false;
    L3:
        if (r2 > r0) goto L16;
        if (r3 == true) goto L6;
        int r4 = r2;
    L7:
        boolean r42 = r6.invoke(Character.valueOf(r5.charAt(r4))).booleanValue();
        if (r3 == false) goto L9;
        if (r42 == false) goto L16;
        r0 = r0 - 1;
        goto L3
    L9:
        if (r42 == false) goto L10;
        r2 = r2 + 1;
        goto L3
    L10:
        r3 = true;
        goto L3
    L6:
        r4 = r0;
    L16:
        return r5.subSequence(r2, r0 + 1);
    }

    public static final CharSequence trim(CharSequence r5, char... r6) {
        int r0 = r5.length() - 1;
        int r2 = 0;
        boolean r3 = false;
    L3:
        if (r2 > r0) goto L16;
        if (r3 == true) goto L6;
        int r4 = r2;
    L7:
        boolean r42 = ArraysKt.contains(r6, r5.charAt(r4));
        if (r3 == false) goto L9;
        if (r42 == false) goto L16;
        r0 = r0 - 1;
        goto L3
    L9:
        if (r42 == false) goto L10;
        r2 = r2 + 1;
        goto L3
    L10:
        r3 = true;
        goto L3
    L6:
        r4 = r0;
    L16:
        return r5.subSequence(r2, r0 + 1);
    }

    @InlineOnly
    private static final String trim(String r0) {
        return trim(r0).toString();
    }

    public static final String trim(String r5, Function1<? super Character, Boolean> r6) {
        int r0 = r5.length() - 1;
        int r2 = 0;
        boolean r3 = false;
    L3:
        if (r2 > r0) goto L16;
        if (r3 == true) goto L6;
        int r4 = r2;
    L7:
        boolean r42 = r6.invoke(Character.valueOf(r5.charAt(r4))).booleanValue();
        if (r3 == false) goto L9;
        if (r42 == false) goto L16;
        r0 = r0 - 1;
        goto L3
    L9:
        if (r42 == false) goto L10;
        r2 = r2 + 1;
        goto L3
    L10:
        r3 = true;
        goto L3
    L6:
        r4 = r0;
    L16:
        return r5.subSequence(r2, r0 + 1).toString();
    }

    public static final String trim(String r5, char... r6) {
        int r0 = r5.length() - 1;
        int r2 = 0;
        boolean r3 = false;
    L3:
        if (r2 > r0) goto L16;
        if (r3 == true) goto L6;
        int r4 = r2;
    L7:
        boolean r42 = ArraysKt.contains(r6, r5.charAt(r4));
        if (r3 == false) goto L9;
        if (r42 == false) goto L16;
        r0 = r0 - 1;
        goto L3
    L9:
        if (r42 == false) goto L10;
        r2 = r2 + 1;
        goto L3
    L10:
        r3 = true;
        goto L3
    L6:
        r4 = r0;
    L16:
        return r5.subSequence(r2, r0 + 1).toString();
    }

    public static final CharSequence trimEnd(CharSequence r3) {
        int r0 = r3.length() - 1;
        if (r0 < 0) goto L11;
    L4:
        int r1 = r0 - 1;
        if (CharsKt__CharJVMKt.isWhitespace(r3.charAt(r0)) == false) goto L7;
        if (r1 < 0) goto L15;
        r0 = r1;
        goto L4
    L15:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L7:
        return r3.subSequence(0, r0 + 1);
    L11:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static final CharSequence trimEnd(CharSequence r3, Function1<? super Character, Boolean> r4) {
        int r0 = r3.length() - 1;
        if (r0 < 0) goto L11;
    L4:
        int r1 = r0 - 1;
        if (r4.invoke(Character.valueOf(r3.charAt(r0))).booleanValue() == false) goto L7;
        if (r1 < 0) goto L15;
        r0 = r1;
        goto L4
    L15:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L7:
        return r3.subSequence(0, r0 + 1);
    L11:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static final CharSequence trimEnd(CharSequence r3, char... r4) {
        int r0 = r3.length() - 1;
        if (r0 < 0) goto L11;
    L4:
        int r1 = r0 - 1;
        if (ArraysKt.contains(r4, r3.charAt(r0)) == false) goto L7;
        if (r1 < 0) goto L15;
        r0 = r1;
        goto L4
    L15:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L7:
        return r3.subSequence(0, r0 + 1);
    L11:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    @InlineOnly
    private static final String trimEnd(String r0) {
        return trimEnd(r0).toString();
    }

    public static final String trimEnd(String r3, Function1<? super Character, Boolean> r4) {
        int r0 = r3.length() - 1;
        if (r0 < 0) goto L10;
    L4:
        int r1 = r0 - 1;
        if (r4.invoke(Character.valueOf(r3.charAt(r0))).booleanValue() == false) goto L6;
        if (r1 < 0) goto L10;
        r0 = r1;
        goto L4
    L6:
        CharSequence r32 = r3.subSequence(0, r0 + 1);
    L12:
        return r32.toString();
    L10:
        r32 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L12
    }

    public static String trimEnd(String r3, char... r4) {
        int r0 = r3.length() - 1;
        if (r0 < 0) goto L10;
    L4:
        int r1 = r0 - 1;
        if (ArraysKt.contains(r4, r3.charAt(r0)) == false) goto L6;
        if (r1 < 0) goto L10;
        r0 = r1;
        goto L4
    L6:
        CharSequence r32 = r3.subSequence(0, r0 + 1);
    L12:
        return r32.toString();
    L10:
        r32 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L12
    }

    public static final CharSequence trimStart(CharSequence r3) {
        int r0 = r3.length();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L9;
        if (CharsKt__CharJVMKt.isWhitespace(r3.charAt(r1)) == false) goto L7;
        r1 = r1 + 1;
        goto L3
    L7:
        return r3.subSequence(r1, r3.length());
    L9:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static final CharSequence trimStart(CharSequence r3, Function1<? super Character, Boolean> r4) {
        int r0 = r3.length();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L9;
        if (r4.invoke(Character.valueOf(r3.charAt(r1))).booleanValue() == false) goto L7;
        r1 = r1 + 1;
        goto L3
    L7:
        return r3.subSequence(r1, r3.length());
    L9:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static final CharSequence trimStart(CharSequence r3, char... r4) {
        int r0 = r3.length();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L9;
        if (ArraysKt.contains(r4, r3.charAt(r1)) == false) goto L7;
        r1 = r1 + 1;
        goto L3
    L7:
        return r3.subSequence(r1, r3.length());
    L9:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    @InlineOnly
    private static final String trimStart(String r0) {
        return trimStart(r0).toString();
    }

    public static final String trimStart(String r3, Function1<? super Character, Boolean> r4) {
        int r0 = r3.length();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        if (r4.invoke(Character.valueOf(r3.charAt(r1))).booleanValue() == false) goto L6;
        r1 = r1 + 1;
        goto L3
    L6:
        CharSequence r32 = r3.subSequence(r1, r3.length());
    L10:
        return r32.toString();
    L8:
        r32 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L10
    }

    public static final String trimStart(String r3, char... r4) {
        int r0 = r3.length();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        if (ArraysKt.contains(r4, r3.charAt(r1)) == false) goto L6;
        r1 = r1 + 1;
        goto L3
    L6:
        CharSequence r32 = r3.subSequence(r1, r3.length());
    L10:
        return r32.toString();
    L8:
        r32 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L10
    }
}
