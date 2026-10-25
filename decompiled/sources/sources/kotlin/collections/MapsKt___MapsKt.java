package kotlin.collections;

import android.R;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.IgnorableReturnValue;
import kotlin.Metadata;
import kotlin.OverloadResolutionByLambdaReturnType;
import kotlin.Pair;
import kotlin.SinceKotlin;
import kotlin.UIntArray$Iterator$$ExternalSyntheticBUOutline0;
import kotlin.Unit;
import kotlin.internal.HidesMembers;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.NotificationBadge;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000z\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010&\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001f\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000f\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\\\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\b\b\u0002\u0010\u0001*\u00020\u0004*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052 \u0010\u0006\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0006\u0012\u0004\u0018\u0001H\u00010\u0007H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u0010\t\u001a^\u0010\n\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\b\b\u0002\u0010\u0001*\u00020\u0004*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052 \u0010\u0006\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0006\u0012\u0004\u0018\u0001H\u00010\u0007H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u0010\t\u001a:\u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\r0\f\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0005H\u0086\u0080\u0004\u001a]\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u00010\f\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052$\u0010\u0006\u001a \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u000f0\u0007H\u0086\u0088\u0004ø\u0001\u0000\u001ab\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u00010\f\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052$\u0010\u0006\u001a \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00100\u0007H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\b\u0011\u001av\u0010\u0012\u001a\u0002H\u0013\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0001\"\u0010\b\u0003\u0010\u0013*\n\u0012\u0006\b\u0000\u0012\u0002H\u00010\u0014*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u0006\u0010\u0015\u001a\u0002H\u00132$\u0010\u0006\u001a \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u000f0\u0007H\u0087\u0088\bø\u0001\u0000¢\u0006\u0002\u0010\u0016\u001ax\u0010\u0012\u001a\u0002H\u0013\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0001\"\u0010\b\u0003\u0010\u0013*\n\u0012\u0006\b\u0000\u0012\u0002H\u00010\u0014*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u0006\u0010\u0015\u001a\u0002H\u00132$\u0010\u0006\u001a \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00100\u0007H\u0087\u0088\bø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0016\u001aW\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00010\f\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001e\u0010\u0006\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u0002H\u00010\u0007H\u0086\u0088\u0004ø\u0001\u0000\u001a]\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00010\f\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\b\b\u0002\u0010\u0001*\u00020\u0004*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052 \u0010\u0006\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0006\u0012\u0004\u0018\u0001H\u00010\u0007H\u0086\u0088\u0004ø\u0001\u0000\u001av\u0010\u001a\u001a\u0002H\u0013\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\b\b\u0002\u0010\u0001*\u00020\u0004\"\u0010\b\u0003\u0010\u0013*\n\u0012\u0006\b\u0000\u0012\u0002H\u00010\u0014*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u0006\u0010\u0015\u001a\u0002H\u00132 \u0010\u0006\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0006\u0012\u0004\u0018\u0001H\u00010\u0007H\u0087\u0088\bø\u0001\u0000¢\u0006\u0002\u0010\u0016\u001ap\u0010\u001b\u001a\u0002H\u0013\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0001\"\u0010\b\u0003\u0010\u0013*\n\u0012\u0006\b\u0000\u0012\u0002H\u00010\u0014*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u0006\u0010\u0015\u001a\u0002H\u00132\u001e\u0010\u0006\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u0002H\u00010\u0007H\u0087\u0088\bø\u0001\u0000¢\u0006\u0002\u0010\u0016\u001aK\u0010\u001c\u001a\u00020\u001d\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001e\u0010\u001e\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u00020\u001d0\u0007H\u0086\u0088\u0004ø\u0001\u0000\u001a(\u0010\u001f\u001a\u00020\u001d\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0005H\u0086\u0080\u0004\u001aK\u0010\u001f\u001a\u00020\u001d\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001e\u0010\u001e\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u00020\u001d0\u0007H\u0086\u0088\u0004ø\u0001\u0000\u001a(\u0010 \u001a\u00020!\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0005H\u0087\u0088\u0004\u001aK\u0010 \u001a\u00020!\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001e\u0010\u001e\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u00020\u001d0\u0007H\u0086\u0088\u0004ø\u0001\u0000\u001aK\u0010\"\u001a\u00020#\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001e\u0010$\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u00020#0\u0007H\u0087\u0088\u0004ø\u0001\u0000\u001al\u0010%\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010&*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u0002H\u00010\u0007H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\b(\u001ai\u0010)\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\b\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010&*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u0002H\u00010\u0007H\u0087\u0088\u0004ø\u0001\u0000\u001aK\u0010*\u001a\u00020+\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u00020+0\u0007H\u0087\u0088\u0004ø\u0001\u0000\u001aK\u0010*\u001a\u00020,\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u00020,0\u0007H\u0087\u0088\u0004ø\u0001\u0000\u001a`\u0010*\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010&*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u0002H\u00010\u0007H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u0010-\u001aR\u0010.\u001a\u0004\u0018\u00010+\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u00020+0\u0007H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u0010/\u001aR\u0010.\u001a\u0004\u0018\u00010,\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u00020,0\u0007H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u00100\u001ab\u0010.\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010&*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u0002H\u00010\u0007H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u0010-\u001ar\u00101\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001a\u00102\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u000103j\n\u0012\u0006\b\u0000\u0012\u0002H\u0001`42\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u0002H\u00010\u0007H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u00105\u001at\u00106\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001a\u00102\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u000103j\n\u0012\u0006\b\u0000\u0012\u0002H\u0001`42\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u0002H\u00010\u0007H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u00105\u001ao\u00107\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000522\u00102\u001a.\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b03j\u0016\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b`4H\u0087\u0088\u0004¢\u0006\u0004\b8\u00109\u001ao\u0010:\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\b\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000522\u00102\u001a.\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b03j\u0016\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b`4H\u0087\u0088\u0004¢\u0006\u0002\u00109\u001al\u0010;\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010&*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u0002H\u00010\u0007H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\b<\u001ai\u0010=\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\b\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010&*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u0002H\u00010\u0007H\u0087\u0088\u0004ø\u0001\u0000\u001aK\u0010>\u001a\u00020+\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u00020+0\u0007H\u0087\u0088\u0004ø\u0001\u0000\u001aK\u0010>\u001a\u00020,\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u00020,0\u0007H\u0087\u0088\u0004ø\u0001\u0000\u001a`\u0010>\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010&*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u0002H\u00010\u0007H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u0010-\u001aR\u0010?\u001a\u0004\u0018\u00010+\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u00020+0\u0007H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u0010/\u001aR\u0010?\u001a\u0004\u0018\u00010,\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u00020,0\u0007H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u00100\u001ab\u0010?\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010&*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u0002H\u00010\u0007H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u0010-\u001ar\u0010@\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001a\u00102\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u000103j\n\u0012\u0006\b\u0000\u0012\u0002H\u0001`42\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u0002H\u00010\u0007H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u00105\u001at\u0010A\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001a\u00102\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u000103j\n\u0012\u0006\b\u0000\u0012\u0002H\u0001`42\u001e\u0010'\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u0002H\u00010\u0007H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u00105\u001ao\u0010B\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000522\u00102\u001a.\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b03j\u0016\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b`4H\u0087\u0088\u0004¢\u0006\u0004\bC\u00109\u001ao\u0010D\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\b\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000522\u00102\u001a.\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b03j\u0016\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b`4H\u0087\u0088\u0004¢\u0006\u0002\u00109\u001a(\u0010E\u001a\u00020\u001d\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0005H\u0086\u0080\u0004\u001aK\u0010E\u001a\u00020\u001d\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u001e\u0010\u001e\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u00020\u001d0\u0007H\u0086\u0088\u0004ø\u0001\u0000\u001aZ\u0010F\u001a\u0002HG\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0016\b\u0002\u0010G*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0005*\u0002HG2\u001e\u0010$\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u00020#0\u0007H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u0010H\u001ao\u0010I\u001a\u0002HG\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0016\b\u0002\u0010G*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0005*\u0002HG23\u0010$\u001a/\u0012\u0013\u0012\u00110!¢\u0006\f\bK\u0012\b\bL\u0012\u0004\b\b(M\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b\u0012\u0004\u0012\u00020#0JH\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u0010N\u001a:\u0010O\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b0\u000f\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0005H\u0087\u0088\u0004\u001a:\u0010P\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\b0\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0005H\u0086\u0080\u0004\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006Q"}, d2 = {"firstNotNullOf", "R", "K", "V", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "transform", "Lkotlin/Function1;", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "firstNotNullOfOrNull", "toList", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/Pair;", "flatMap", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/sequences/Sequence;", "flatMapSequence", "flatMapTo", "C", _UrlKt.FRAGMENT_ENCODE_SET, "destination", "(Ljava/util/Map;Ljava/util/Collection;Lkotlin/jvm/functions/Function1;)Ljava/util/Collection;", "flatMapSequenceTo", "map", "mapNotNull", "mapNotNullTo", "mapTo", "all", _UrlKt.FRAGMENT_ENCODE_SET, "predicate", "any", NotificationBadge.NewHtcHomeBadger.COUNT, _UrlKt.FRAGMENT_ENCODE_SET, "forEach", _UrlKt.FRAGMENT_ENCODE_SET, "action", "maxBy", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.TYPE_SELECTOR, "maxByOrThrow", "maxByOrNull", "maxOf", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/lang/Comparable;", "maxOfOrNull", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/lang/Double;", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/lang/Float;", "maxOfWith", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Ljava/util/Map;Ljava/util/Comparator;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "maxOfWithOrNull", "maxWith", "maxWithOrThrow", "(Ljava/util/Map;Ljava/util/Comparator;)Ljava/util/Map$Entry;", "maxWithOrNull", "minBy", "minByOrThrow", "minByOrNull", "minOf", "minOfOrNull", "minOfWith", "minOfWithOrNull", "minWith", "minWithOrThrow", "minWithOrNull", "none", "onEach", "M", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/util/Map;", "onEachIndexed", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "index", "(Ljava/util/Map;Lkotlin/jvm/functions/Function2;)Ljava/util/Map;", "asIterable", "asSequence", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/collections/MapsKt")
@SourceDebugExtension({"SMAP\n_Maps.kt\nKotlin\n*S Kotlin\n*F\n+ 1 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,676:1\n99#1,5:677\n115#1,5:682\n158#1,3:687\n148#1:690\n221#1:691\n222#1:693\n149#1:694\n221#1:695\n222#1:697\n1#2:692\n1#2:696\n2015#3,14:698\n2045#3,14:712\n2439#3,14:726\n2469#3,14:740\n1924#3,3:754\n*S KotlinDebug\n*F\n+ 1 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n78#1:677,5\n91#1:682,5\n129#1:687,3\n139#1:690\n139#1:691\n139#1:693\n139#1:694\n148#1:695\n148#1:697\n139#1:692\n243#1:698,14\n261#1:712,14\n441#1:726,14\n459#1:740,14\n656#1:754,3\n*E\n"})
/* loaded from: classes.dex */
class MapsKt___MapsKt extends MapsKt___MapsJvmKt {
    public MapsKt___MapsKt() {
    }

    public static final <K, V> boolean all(Map<? extends K, ? extends V> r2, Function1<? super Map.Entry<? extends K, ? extends V>, Boolean> r3) {
        if (r2.isEmpty() == false) goto L5;
        return true;
    L5:
        Iterator<Map.Entry<? extends K, ? extends V>> r22 = r2.entrySet().iterator();
    L7:
        if (r22.hasNext() == false) goto L12;
        if (r3.invoke(r22.next()).booleanValue() == true) goto L7;
        return false;
    L12:
        return true;
    }

    public static final <K, V> boolean any(Map<? extends K, ? extends V> r0) {
        return !r0.isEmpty();
    }

    public static final <K, V> boolean any(Map<? extends K, ? extends V> r2, Function1<? super Map.Entry<? extends K, ? extends V>, Boolean> r3) {
        if (r2.isEmpty() == false) goto L5;
        return false;
    L5:
        Iterator<Map.Entry<? extends K, ? extends V>> r22 = r2.entrySet().iterator();
    L7:
        if (r22.hasNext() == false) goto L12;
        if (r3.invoke(r22.next()).booleanValue() == false) goto L7;
        return true;
    L12:
        return false;
    }

    @InlineOnly
    private static final <K, V> Iterable<Map.Entry<K, V>> asIterable(Map<? extends K, ? extends V> r0) {
        return r0.entrySet();
    }

    public static final <K, V> Sequence<Map.Entry<K, V>> asSequence(Map<? extends K, ? extends V> r0) {
        return CollectionsKt___CollectionsKt.asSequence(r0.entrySet());
    }

    @InlineOnly
    private static final <K, V> int count(Map<? extends K, ? extends V> r0) {
        return r0.size();
    }

    public static final <K, V> int count(Map<? extends K, ? extends V> r2, Function1<? super Map.Entry<? extends K, ? extends V>, Boolean> r3) {
        int r1 = 0;
        if (r2.isEmpty() == false) goto L5;
        return 0;
    L5:
        Iterator<Map.Entry<? extends K, ? extends V>> r22 = r2.entrySet().iterator();
    L7:
        if (r22.hasNext() == false) goto L11;
        if (r3.invoke(r22.next()).booleanValue() == false) goto L7;
        r1 = r1 + 1;
        goto L7
    L11:
        return r1;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final <K, V, R> R firstNotNullOf(Map<? extends K, ? extends V> r2, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> r3) {
        Iterator<Map.Entry<? extends K, ? extends V>> r22 = r2.entrySet().iterator();
    L4:
        if (r22.hasNext() == false) goto L8;
        R r0 = r3.invoke(r22.next());
        if (r0 == null) goto L4;
    L9:
        if (r0 == null) goto L11;
        return r0;
    L11:
        UIntArray$Iterator$$ExternalSyntheticBUOutline0.m("No element of the map was transformed to a non-null value.");
        return null;
    L8:
        r0 = null;
        goto L9
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final <K, V, R> R firstNotNullOfOrNull(Map<? extends K, ? extends V> r1, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> r2) {
        Iterator<Map.Entry<? extends K, ? extends V>> r12 = r1.entrySet().iterator();
    L4:
        if (r12.hasNext() == false) goto L8;
        R r0 = r2.invoke(r12.next());
        if (r0 == null) goto L4;
        return r0;
    L8:
        return null;
    }

    public static final <K, V, R> List<R> flatMap(Map<? extends K, ? extends V> r2, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends Iterable<? extends R>> r3) {
        ArrayList r0 = new ArrayList();
        Iterator<Map.Entry<? extends K, ? extends V>> r22 = r2.entrySet().iterator();
    L4:
        if (r22.hasNext() == false) goto L6;
        CollectionsKt__MutableCollectionsKt.addAll(r0, r3.invoke(r22.next()));
        goto L4
    L6:
        return r0;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @JvmName(name = "flatMapSequence")
    public static final <K, V, R> List<R> flatMapSequence(Map<? extends K, ? extends V> r2, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends Sequence<? extends R>> r3) {
        ArrayList r0 = new ArrayList();
        Iterator<Map.Entry<? extends K, ? extends V>> r22 = r2.entrySet().iterator();
    L4:
        if (r22.hasNext() == false) goto L6;
        CollectionsKt__MutableCollectionsKt.addAll(r0, r3.invoke(r22.next()));
        goto L4
    L6:
        return r0;
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "flatMapSequenceTo")
    @IgnorableReturnValue
    @OverloadResolutionByLambdaReturnType
    public static final <K, V, R, C extends Collection<? super R>> C flatMapSequenceTo(Map<? extends K, ? extends V> r1, C r2, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends Sequence<? extends R>> r3) {
        Iterator<Map.Entry<? extends K, ? extends V>> r12 = r1.entrySet().iterator();
    L4:
        if (r12.hasNext() == false) goto L6;
        CollectionsKt__MutableCollectionsKt.addAll(r2, r3.invoke(r12.next()));
        goto L4
    L6:
        return r2;
    }

    @IgnorableReturnValue
    public static final <K, V, R, C extends Collection<? super R>> C flatMapTo(Map<? extends K, ? extends V> r1, C r2, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends Iterable<? extends R>> r3) {
        Iterator<Map.Entry<? extends K, ? extends V>> r12 = r1.entrySet().iterator();
    L4:
        if (r12.hasNext() == false) goto L6;
        CollectionsKt__MutableCollectionsKt.addAll(r2, r3.invoke(r12.next()));
        goto L4
    L6:
        return r2;
    }

    @HidesMembers
    public static final <K, V> void forEach(Map<? extends K, ? extends V> r1, Function1<? super Map.Entry<? extends K, ? extends V>, Unit> r2) {
        Iterator<Map.Entry<? extends K, ? extends V>> r12 = r1.entrySet().iterator();
    L4:
        if (r12.hasNext() == false) goto L6;
        r2.invoke(r12.next());
        goto L4
    }

    public static final <K, V, R> List<R> map(Map<? extends K, ? extends V> r2, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> r3) {
        ArrayList r0 = new ArrayList(r2.size());
        Iterator<Map.Entry<? extends K, ? extends V>> r22 = r2.entrySet().iterator();
    L4:
        if (r22.hasNext() == false) goto L6;
        r0.add(r3.invoke(r22.next()));
        goto L4
    L6:
        return r0;
    }

    public static final <K, V, R> List<R> mapNotNull(Map<? extends K, ? extends V> r2, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> r3) {
        ArrayList r0 = new ArrayList();
        Iterator<Map.Entry<? extends K, ? extends V>> r22 = r2.entrySet().iterator();
    L4:
        if (r22.hasNext() == false) goto L8;
        R r1 = r3.invoke(r22.next());
        if (r1 == null) goto L4;
        r0.add(r1);
        goto L4
    L8:
        return r0;
    }

    @IgnorableReturnValue
    public static final <K, V, R, C extends Collection<? super R>> C mapNotNullTo(Map<? extends K, ? extends V> r1, C r2, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> r3) {
        Iterator<Map.Entry<? extends K, ? extends V>> r12 = r1.entrySet().iterator();
    L4:
        if (r12.hasNext() == false) goto L8;
        R r0 = r3.invoke(r12.next());
        if (r0 == null) goto L4;
        r2.add(r0);
        goto L4
    L8:
        return r2;
    }

    @IgnorableReturnValue
    public static final <K, V, R, C extends Collection<? super R>> C mapTo(Map<? extends K, ? extends V> r1, C r2, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> r3) {
        Iterator<Map.Entry<? extends K, ? extends V>> r12 = r1.entrySet().iterator();
    L4:
        if (r12.hasNext() == false) goto L6;
        r2.add(r3.invoke(r12.next()));
        goto L4
    L6:
        return r2;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final <K, V, R extends Comparable<? super R>> Map.Entry<K, V> maxByOrNull(Map<? extends K, ? extends V> r5, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> r6) {
        Iterator<T> r52 = r5.entrySet().iterator();
        if (r52.hasNext() == true) goto L5;
        Map.Entry<K, V> r53 = null;
    L16:
        return r53;
    L5:
        Map.Entry<K, V> r0 = (Object) r52.next();
        if (r52.hasNext() == true) goto L8;
    L7:
        r53 = r0;
        goto L16
    L8:
        R r1 = r6.invoke(r0);
    L9:
        Map.Entry<K, V> r2 = (Object) r52.next();
        R r3 = r6.invoke(r2);
        if (r1.compareTo(r3) >= 0) goto L13;
        r0 = r2;
        r1 = r3;
    L13:
        if (r52.hasNext() == true) goto L9;
        goto L7
    }

    @SinceKotlin(version = "1.7")
    @InlineOnly
    @JvmName(name = "maxByOrThrow")
    private static final <K, V, R extends Comparable<? super R>> Map.Entry<K, V> maxByOrThrow(Map<? extends K, ? extends V> r5, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> r6) {
        Iterator<T> r52 = r5.entrySet().iterator();
        if (r52.hasNext() == false) goto L15;
        Map.Entry<K, V> r0 = (Object) r52.next();
        if (r52.hasNext() == false) goto L14;
        R r1 = r6.invoke(r0);
    L8:
        Map.Entry<K, V> r2 = (Object) r52.next();
        R r3 = r6.invoke(r2);
        if (r1.compareTo(r3) >= 0) goto L12;
        r0 = r2;
        r1 = r3;
    L12:
        if (r52.hasNext() == true) goto L8;
    L14:
        return r0;
    L15:
        Utils$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <K, V> double maxOf(Map<? extends K, ? extends V> r4, Function1<? super Map.Entry<? extends K, ? extends V>, Double> r5) {
        Iterator<T> r42 = r4.entrySet().iterator();
        if (r42.hasNext() == false) goto L9;
        double r0 = r5.invoke((Object) r42.next()).doubleValue();
    L6:
        if (r42.hasNext() == false) goto L8;
        r0 = Math.max(r0, r5.invoke((Object) r42.next()).doubleValue());
        goto L6
    L8:
        return r0;
    L9:
        Utils$$ExternalSyntheticBUOutline0.m();
        return 0.0d;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: maxOf, reason: collision with other method in class */
    private static final <K, V> float m2469maxOf(Map<? extends K, ? extends V> r2, Function1<? super Map.Entry<? extends K, ? extends V>, Float> r3) {
        Iterator<T> r22 = r2.entrySet().iterator();
        if (r22.hasNext() == false) goto L9;
        float r0 = r3.invoke((Object) r22.next()).floatValue();
    L6:
        if (r22.hasNext() == false) goto L8;
        r0 = Math.max(r0, r3.invoke((Object) r22.next()).floatValue());
        goto L6
    L8:
        return r0;
    L9:
        Utils$$ExternalSyntheticBUOutline0.m();
        return 0.0f;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: maxOf, reason: collision with other method in class */
    private static final <K, V, R extends Comparable<? super R>> R m2470maxOf(Map<? extends K, ? extends V> r3, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> r4) {
        Iterator<T> r32 = r3.entrySet().iterator();
        if (r32.hasNext() == false) goto L11;
        R r0 = r4.invoke((Object) r32.next());
    L6:
        if (r32.hasNext() == false) goto L10;
        R r1 = r4.invoke((Object) r32.next());
        if (r0.compareTo(r1) >= 0) goto L6;
        r0 = r1;
        goto L6
    L10:
        return r0;
    L11:
        Utils$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <K, V, R extends Comparable<? super R>> R maxOfOrNull(Map<? extends K, ? extends V> r3, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> r4) {
        Iterator<T> r32 = r3.entrySet().iterator();
        if (r32.hasNext() == true) goto L6;
        return null;
    L6:
        R r0 = r4.invoke((Object) r32.next());
    L8:
        if (r32.hasNext() == false) goto L12;
        R r1 = r4.invoke((Object) r32.next());
        if (r0.compareTo(r1) >= 0) goto L8;
        r0 = r1;
        goto L8
    L12:
        return r0;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: maxOfOrNull, reason: collision with other method in class */
    private static final <K, V> Double m2471maxOfOrNull(Map<? extends K, ? extends V> r4, Function1<? super Map.Entry<? extends K, ? extends V>, Double> r5) {
        Iterator<T> r42 = r4.entrySet().iterator();
        if (r42.hasNext() == true) goto L6;
        return null;
    L6:
        double r0 = r5.invoke((Object) r42.next()).doubleValue();
    L8:
        if (r42.hasNext() == false) goto L11;
        r0 = Math.max(r0, r5.invoke((Object) r42.next()).doubleValue());
        goto L8
    L11:
        return Double.valueOf(r0);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: maxOfOrNull, reason: collision with other method in class */
    private static final <K, V> Float m2472maxOfOrNull(Map<? extends K, ? extends V> r2, Function1<? super Map.Entry<? extends K, ? extends V>, Float> r3) {
        Iterator<T> r22 = r2.entrySet().iterator();
        if (r22.hasNext() == true) goto L6;
        return null;
    L6:
        float r0 = r3.invoke((Object) r22.next()).floatValue();
    L8:
        if (r22.hasNext() == false) goto L11;
        r0 = Math.max(r0, r3.invoke((Object) r22.next()).floatValue());
        goto L8
    L11:
        return Float.valueOf(r0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <K, V, R> R maxOfWith(Map<? extends K, ? extends V> r3, Comparator<? super R> r4, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> r5) {
        Iterator r32 = r3.entrySet().iterator();
        if (r32.hasNext() == false) goto L11;
        Object r0 = (R) r5.invoke((Object) r32.next());
    L6:
        if (r32.hasNext() == false) goto L10;
        Object r1 = (R) r5.invoke((Object) r32.next());
        if (r4.compare(r0, r1) >= 0) goto L6;
        r0 = (R) r1;
        goto L6
    L10:
        return (R) r0;
    L11:
        Utils$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <K, V, R> R maxOfWithOrNull(Map<? extends K, ? extends V> r3, Comparator<? super R> r4, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> r5) {
        Iterator r32 = r3.entrySet().iterator();
        if (r32.hasNext() == true) goto L6;
        return null;
    L6:
        Object r0 = (R) r5.invoke((Object) r32.next());
    L8:
        if (r32.hasNext() == false) goto L12;
        Object r1 = (R) r5.invoke((Object) r32.next());
        if (r4.compare(r0, r1) >= 0) goto L8;
        r0 = (R) r1;
        goto L8
    L12:
        return (R) r0;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final <K, V> Map.Entry<K, V> maxWithOrNull(Map<? extends K, ? extends V> r0, Comparator<? super Map.Entry<? extends K, ? extends V>> r1) {
        return (Map.Entry) CollectionsKt___CollectionsKt.maxWithOrNull(r0.entrySet(), r1);
    }

    @SinceKotlin(version = "1.7")
    @InlineOnly
    @JvmName(name = "maxWithOrThrow")
    private static final <K, V> Map.Entry<K, V> maxWithOrThrow(Map<? extends K, ? extends V> r0, Comparator<? super Map.Entry<? extends K, ? extends V>> r1) {
        return (Map.Entry) CollectionsKt___CollectionsKt.maxWithOrThrow(r0.entrySet(), r1);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final <K, V, R extends Comparable<? super R>> Map.Entry<K, V> minByOrNull(Map<? extends K, ? extends V> r5, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> r6) {
        Iterator<T> r52 = r5.entrySet().iterator();
        if (r52.hasNext() == true) goto L5;
        Map.Entry<K, V> r53 = null;
    L16:
        return r53;
    L5:
        Map.Entry<K, V> r0 = (Object) r52.next();
        if (r52.hasNext() == true) goto L8;
    L7:
        r53 = r0;
        goto L16
    L8:
        R r1 = r6.invoke(r0);
    L9:
        Map.Entry<K, V> r2 = (Object) r52.next();
        R r3 = r6.invoke(r2);
        if (r1.compareTo(r3) <= 0) goto L13;
        r0 = r2;
        r1 = r3;
    L13:
        if (r52.hasNext() == true) goto L9;
        goto L7
    }

    @SinceKotlin(version = "1.7")
    @InlineOnly
    @JvmName(name = "minByOrThrow")
    private static final <K, V, R extends Comparable<? super R>> Map.Entry<K, V> minByOrThrow(Map<? extends K, ? extends V> r5, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> r6) {
        Iterator<T> r52 = r5.entrySet().iterator();
        if (r52.hasNext() == false) goto L15;
        Map.Entry<K, V> r0 = (Object) r52.next();
        if (r52.hasNext() == false) goto L14;
        R r1 = r6.invoke(r0);
    L8:
        Map.Entry<K, V> r2 = (Object) r52.next();
        R r3 = r6.invoke(r2);
        if (r1.compareTo(r3) <= 0) goto L12;
        r0 = r2;
        r1 = r3;
    L12:
        if (r52.hasNext() == true) goto L8;
    L14:
        return r0;
    L15:
        Utils$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <K, V> double minOf(Map<? extends K, ? extends V> r4, Function1<? super Map.Entry<? extends K, ? extends V>, Double> r5) {
        Iterator<T> r42 = r4.entrySet().iterator();
        if (r42.hasNext() == false) goto L9;
        double r0 = r5.invoke((Object) r42.next()).doubleValue();
    L6:
        if (r42.hasNext() == false) goto L8;
        r0 = Math.min(r0, r5.invoke((Object) r42.next()).doubleValue());
        goto L6
    L8:
        return r0;
    L9:
        Utils$$ExternalSyntheticBUOutline0.m();
        return 0.0d;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: minOf, reason: collision with other method in class */
    private static final <K, V> float m2473minOf(Map<? extends K, ? extends V> r2, Function1<? super Map.Entry<? extends K, ? extends V>, Float> r3) {
        Iterator<T> r22 = r2.entrySet().iterator();
        if (r22.hasNext() == false) goto L9;
        float r0 = r3.invoke((Object) r22.next()).floatValue();
    L6:
        if (r22.hasNext() == false) goto L8;
        r0 = Math.min(r0, r3.invoke((Object) r22.next()).floatValue());
        goto L6
    L8:
        return r0;
    L9:
        Utils$$ExternalSyntheticBUOutline0.m();
        return 0.0f;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: minOf, reason: collision with other method in class */
    private static final <K, V, R extends Comparable<? super R>> R m2474minOf(Map<? extends K, ? extends V> r3, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> r4) {
        Iterator<T> r32 = r3.entrySet().iterator();
        if (r32.hasNext() == false) goto L11;
        R r0 = r4.invoke((Object) r32.next());
    L6:
        if (r32.hasNext() == false) goto L10;
        R r1 = r4.invoke((Object) r32.next());
        if (r0.compareTo(r1) <= 0) goto L6;
        r0 = r1;
        goto L6
    L10:
        return r0;
    L11:
        Utils$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <K, V, R extends Comparable<? super R>> R minOfOrNull(Map<? extends K, ? extends V> r3, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> r4) {
        Iterator<T> r32 = r3.entrySet().iterator();
        if (r32.hasNext() == true) goto L6;
        return null;
    L6:
        R r0 = r4.invoke((Object) r32.next());
    L8:
        if (r32.hasNext() == false) goto L12;
        R r1 = r4.invoke((Object) r32.next());
        if (r0.compareTo(r1) <= 0) goto L8;
        r0 = r1;
        goto L8
    L12:
        return r0;
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: minOfOrNull, reason: collision with other method in class */
    private static final <K, V> Double m2475minOfOrNull(Map<? extends K, ? extends V> r4, Function1<? super Map.Entry<? extends K, ? extends V>, Double> r5) {
        Iterator<T> r42 = r4.entrySet().iterator();
        if (r42.hasNext() == true) goto L6;
        return null;
    L6:
        double r0 = r5.invoke((Object) r42.next()).doubleValue();
    L8:
        if (r42.hasNext() == false) goto L11;
        r0 = Math.min(r0, r5.invoke((Object) r42.next()).doubleValue());
        goto L8
    L11:
        return Double.valueOf(r0);
    }

    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    /* renamed from: minOfOrNull, reason: collision with other method in class */
    private static final <K, V> Float m2476minOfOrNull(Map<? extends K, ? extends V> r2, Function1<? super Map.Entry<? extends K, ? extends V>, Float> r3) {
        Iterator<T> r22 = r2.entrySet().iterator();
        if (r22.hasNext() == true) goto L6;
        return null;
    L6:
        float r0 = r3.invoke((Object) r22.next()).floatValue();
    L8:
        if (r22.hasNext() == false) goto L11;
        r0 = Math.min(r0, r3.invoke((Object) r22.next()).floatValue());
        goto L8
    L11:
        return Float.valueOf(r0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <K, V, R> R minOfWith(Map<? extends K, ? extends V> r3, Comparator<? super R> r4, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> r5) {
        Iterator r32 = r3.entrySet().iterator();
        if (r32.hasNext() == false) goto L11;
        Object r0 = (R) r5.invoke((Object) r32.next());
    L6:
        if (r32.hasNext() == false) goto L10;
        Object r1 = (R) r5.invoke((Object) r32.next());
        if (r4.compare(r0, r1) <= 0) goto L6;
        r0 = (R) r1;
        goto L6
    L10:
        return (R) r0;
    L11:
        Utils$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.4")
    @OverloadResolutionByLambdaReturnType
    @InlineOnly
    private static final <K, V, R> R minOfWithOrNull(Map<? extends K, ? extends V> r3, Comparator<? super R> r4, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> r5) {
        Iterator r32 = r3.entrySet().iterator();
        if (r32.hasNext() == true) goto L6;
        return null;
    L6:
        Object r0 = (R) r5.invoke((Object) r32.next());
    L8:
        if (r32.hasNext() == false) goto L12;
        Object r1 = (R) r5.invoke((Object) r32.next());
        if (r4.compare(r0, r1) <= 0) goto L8;
        r0 = (R) r1;
        goto L8
    L12:
        return (R) r0;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final <K, V> Map.Entry<K, V> minWithOrNull(Map<? extends K, ? extends V> r0, Comparator<? super Map.Entry<? extends K, ? extends V>> r1) {
        return (Map.Entry) CollectionsKt___CollectionsKt.minWithOrNull(r0.entrySet(), r1);
    }

    @SinceKotlin(version = "1.7")
    @InlineOnly
    @JvmName(name = "minWithOrThrow")
    private static final <K, V> Map.Entry<K, V> minWithOrThrow(Map<? extends K, ? extends V> r0, Comparator<? super Map.Entry<? extends K, ? extends V>> r1) {
        return (Map.Entry) CollectionsKt___CollectionsKt.minWithOrThrow(r0.entrySet(), r1);
    }

    public static final <K, V> boolean none(Map<? extends K, ? extends V> r0) {
        return r0.isEmpty();
    }

    public static final <K, V> boolean none(Map<? extends K, ? extends V> r2, Function1<? super Map.Entry<? extends K, ? extends V>, Boolean> r3) {
        if (r2.isEmpty() == false) goto L5;
        return true;
    L5:
        Iterator<Map.Entry<? extends K, ? extends V>> r22 = r2.entrySet().iterator();
    L7:
        if (r22.hasNext() == false) goto L12;
        if (r3.invoke(r22.next()).booleanValue() == false) goto L7;
        return false;
    L12:
        return true;
    }

    @SinceKotlin(version = "1.1")
    public static final <K, V, M extends Map<? extends K, ? extends V>> M onEach(M r2, Function1<? super Map.Entry<? extends K, ? extends V>, Unit> r3) {
        Iterator<Map.Entry<K, V>> r0 = r2.entrySet().iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        r3.invoke(r0.next());
        goto L4
    L6:
        return r2;
    }

    @SinceKotlin(version = "1.4")
    public static final <K, V, M extends Map<? extends K, ? extends V>> M onEachIndexed(M r4, Function2<? super Integer, ? super Map.Entry<? extends K, ? extends V>, Unit> r5) {
        Iterator<T> r0 = r4.entrySet().iterator();
        int r1 = 0;
    L4:
        if (r0.hasNext() == false) goto L9;
        R r2 = (Object) r0.next();
        int r3 = r1 + 1;
        if (r1 >= 0) goto L8;
        CollectionsKt__CollectionsKt.throwIndexOverflow();
    L8:
        r5.invoke(Integer.valueOf(r1), r2);
        r1 = r3;
        goto L4
    L9:
        return r4;
    }

    public static final <K, V> List<Pair<K, V>> toList(Map<? extends K, ? extends V> r4) {
        if (r4.size() == 0) goto L5;
        Iterator<Map.Entry<? extends K, ? extends V>> r0 = r4.entrySet().iterator();
        if (r0.hasNext() == false) goto L9;
        Map.Entry<? extends K, ? extends V> r1 = r0.next();
        if (r0.hasNext() == false) goto L13;
        ArrayList r2 = new ArrayList(r4.size());
        r2.add(new Pair(r1.getKey(), r1.getValue()));
    L15:
        Map.Entry<? extends K, ? extends V> r42 = r0.next();
        r2.add(new Pair(r42.getKey(), r42.getValue()));
        if (r0.hasNext() == true) goto L15;
        return r2;
    L13:
        return CollectionsKt__CollectionsJVMKt.listOf(new Pair(r1.getKey(), r1.getValue()));
    L9:
        return CollectionsKt__CollectionsKt.emptyList();
    L5:
        return CollectionsKt__CollectionsKt.emptyList();
    }
}
