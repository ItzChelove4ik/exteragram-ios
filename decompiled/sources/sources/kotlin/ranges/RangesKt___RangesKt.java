package kotlin.ranges;

import androidx.core.os.BundleKt$$ExternalSyntheticBUOutline0;
import com.google.android.gms.cast.MediaStatus;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UIntArray$Iterator$$ExternalSyntheticBUOutline0;
import kotlin.WasExperimental;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.random.Random;
import kotlin.random.RandomKt;
import kotlin.ranges.CharProgression;
import kotlin.ranges.IntProgression;
import kotlin.ranges.LongProgression;
import okhttp3.MediaType$Companion$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000r\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\n\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\u000f\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\u0080\u0004\u001a\u000e\u0010\u0000\u001a\u00020\u0003*\u00020\u0004H\u0087\u0080\u0004\u001a\u000e\u0010\u0000\u001a\u00020\u0005*\u00020\u0006H\u0087\u0080\u0004\u001a\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u0087\u0080\u0004¢\u0006\u0002\u0010\b\u001a\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0003*\u00020\u0004H\u0087\u0080\u0004¢\u0006\u0002\u0010\t\u001a\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0005*\u00020\u0006H\u0087\u0080\u0004¢\u0006\u0002\u0010\n\u001a\u000e\u0010\u000b\u001a\u00020\u0001*\u00020\u0002H\u0087\u0080\u0004\u001a\u000e\u0010\u000b\u001a\u00020\u0003*\u00020\u0004H\u0087\u0080\u0004\u001a\u000e\u0010\u000b\u001a\u00020\u0005*\u00020\u0006H\u0087\u0080\u0004\u001a\u0015\u0010\f\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u0087\u0080\u0004¢\u0006\u0002\u0010\b\u001a\u0015\u0010\f\u001a\u0004\u0018\u00010\u0003*\u00020\u0004H\u0087\u0080\u0004¢\u0006\u0002\u0010\t\u001a\u0015\u0010\f\u001a\u0004\u0018\u00010\u0005*\u00020\u0006H\u0087\u0080\u0004¢\u0006\u0002\u0010\n\u001a\u000e\u0010\r\u001a\u00020\u0001*\u00020\u000eH\u0087\u0088\u0004\u001a\u000e\u0010\r\u001a\u00020\u0003*\u00020\u000fH\u0087\u0088\u0004\u001a\u000e\u0010\r\u001a\u00020\u0005*\u00020\u0010H\u0087\u0088\u0004\u001a\u0016\u0010\r\u001a\u00020\u0001*\u00020\u000e2\u0006\u0010\r\u001a\u00020\u0011H\u0087\u0080\u0004\u001a\u0016\u0010\r\u001a\u00020\u0003*\u00020\u000f2\u0006\u0010\r\u001a\u00020\u0011H\u0087\u0080\u0004\u001a\u0016\u0010\r\u001a\u00020\u0005*\u00020\u00102\u0006\u0010\r\u001a\u00020\u0011H\u0087\u0080\u0004\u001a\u0015\u0010\u0012\u001a\u0004\u0018\u00010\u0001*\u00020\u000eH\u0087\u0088\u0004¢\u0006\u0002\u0010\u0013\u001a\u0015\u0010\u0012\u001a\u0004\u0018\u00010\u0003*\u00020\u000fH\u0087\u0088\u0004¢\u0006\u0002\u0010\u0014\u001a\u0015\u0010\u0012\u001a\u0004\u0018\u00010\u0005*\u00020\u0010H\u0087\u0088\u0004¢\u0006\u0002\u0010\u0015\u001a\u001d\u0010\u0012\u001a\u0004\u0018\u00010\u0001*\u00020\u000e2\u0006\u0010\r\u001a\u00020\u0011H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0016\u001a\u001d\u0010\u0012\u001a\u0004\u0018\u00010\u0003*\u00020\u000f2\u0006\u0010\r\u001a\u00020\u0011H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0017\u001a\u001d\u0010\u0012\u001a\u0004\u0018\u00010\u0005*\u00020\u00102\u0006\u0010\r\u001a\u00020\u0011H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0018\u001a\u001d\u0010\u0019\u001a\u00020\u001a*\u00020\u000e2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001H\u0087\u008a\u0004¢\u0006\u0002\u0010\u001c\u001a\u001d\u0010\u0019\u001a\u00020\u001a*\u00020\u000f2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0003H\u0087\u008a\u0004¢\u0006\u0002\u0010\u001d\u001a\u001d\u0010\u0019\u001a\u00020\u001a*\u00020\u00102\b\u0010\u001b\u001a\u0004\u0018\u00010\u0005H\u0087\u008a\u0004¢\u0006\u0002\u0010\u001e\u001a!\u0010\u0019\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020\u00010\u001f2\u0006\u0010 \u001a\u00020!H\u0087\u0082\u0004¢\u0006\u0002\b\"\u001a!\u0010\u0019\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020\u00030\u001f2\u0006\u0010 \u001a\u00020!H\u0087\u0082\u0004¢\u0006\u0002\b#\u001a!\u0010\u0019\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020$0\u001f2\u0006\u0010 \u001a\u00020!H\u0087\u0082\u0004¢\u0006\u0002\b%\u001a!\u0010\u0019\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020&0\u001f2\u0006\u0010 \u001a\u00020!H\u0087\u0082\u0004¢\u0006\u0002\b'\u001a!\u0010\u0019\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020(0\u001f2\u0006\u0010 \u001a\u00020!H\u0087\u0082\u0004¢\u0006\u0002\b)\u001a!\u0010\u0019\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020\u00010*2\u0006\u0010 \u001a\u00020!H\u0087\u0082\u0004¢\u0006\u0002\b\"\u001a!\u0010\u0019\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020\u00030*2\u0006\u0010 \u001a\u00020!H\u0087\u0082\u0004¢\u0006\u0002\b#\u001a!\u0010\u0019\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020$0*2\u0006\u0010 \u001a\u00020!H\u0087\u0082\u0004¢\u0006\u0002\b%\u001a\u0016\u0010\u0019\u001a\u00020\u001a*\u00020\u000e2\u0006\u0010 \u001a\u00020!H\u0087\u008a\u0004\u001a\u0016\u0010\u0019\u001a\u00020\u001a*\u00020\u000f2\u0006\u0010 \u001a\u00020!H\u0087\u008a\u0004\u001a!\u0010\u0019\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020\u00010\u001f2\u0006\u0010 \u001a\u00020&H\u0087\u0082\u0004¢\u0006\u0002\b\"\u001a!\u0010\u0019\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020\u00030\u001f2\u0006\u0010 \u001a\u00020&H\u0087\u0082\u0004¢\u0006\u0002\b#\u001a!\u0010\u0019\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020!0\u001f2\u0006\u0010 \u001a\u00020&H\u0087\u0082\u0004¢\u0006\u0002\b+\u001a!\u0010\u0019\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020$0\u001f2\u0006\u0010 \u001a\u00020&H\u0087\u0082\u0004¢\u0006\u0002\b%\u001a!\u0010\u0019\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020(0\u001f2\u0006\u0010 \u001a\u00020&H\u0087\u0082\u0004¢\u0006\u0002\b)\u001a!\u0010\u0019\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020\u00010\u001f2\u0006\u0010 \u001a\u00020(H\u0087\u0082\u0004¢\u0006\u0002\b\"\u001a!\u0010\u0019\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020\u00030\u001f2\u0006\u0010 \u001a\u00020(H\u0087\u0082\u0004¢\u0006\u0002\b#\u001a!\u0010\u0019\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020!0\u001f2\u0006\u0010 \u001a\u00020(H\u0087\u0082\u0004¢\u0006\u0002\b+\u001a!\u0010\u0019\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020$0\u001f2\u0006\u0010 \u001a\u00020(H\u0087\u0082\u0004¢\u0006\u0002\b%\u001a!\u0010\u0019\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020&0\u001f2\u0006\u0010 \u001a\u00020(H\u0087\u0082\u0004¢\u0006\u0002\b'\u001a!\u0010\u0019\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020&0*2\u0006\u0010 \u001a\u00020(H\u0087\u0082\u0004¢\u0006\u0002\b'\u001a!\u0010\u0019\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020\u00030\u001f2\u0006\u0010 \u001a\u00020\u0001H\u0087\u0082\u0004¢\u0006\u0002\b#\u001a!\u0010\u0019\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020!0\u001f2\u0006\u0010 \u001a\u00020\u0001H\u0087\u0082\u0004¢\u0006\u0002\b+\u001a!\u0010\u0019\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020$0\u001f2\u0006\u0010 \u001a\u00020\u0001H\u0087\u0082\u0004¢\u0006\u0002\b%\u001a!\u0010\u0019\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020&0\u001f2\u0006\u0010 \u001a\u00020\u0001H\u0087\u0082\u0004¢\u0006\u0002\b'\u001a!\u0010\u0019\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020(0\u001f2\u0006\u0010 \u001a\u00020\u0001H\u0087\u0082\u0004¢\u0006\u0002\b)\u001a!\u0010\u0019\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020\u00030*2\u0006\u0010 \u001a\u00020\u0001H\u0087\u0082\u0004¢\u0006\u0002\b#\u001a!\u0010\u0019\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020!0*2\u0006\u0010 \u001a\u00020\u0001H\u0087\u0082\u0004¢\u0006\u0002\b+\u001a!\u0010\u0019\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020$0*2\u0006\u0010 \u001a\u00020\u0001H\u0087\u0082\u0004¢\u0006\u0002\b%\u001a\u0016\u0010\u0019\u001a\u00020\u001a*\u00020\u000f2\u0006\u0010 \u001a\u00020\u0001H\u0087\u008a\u0004\u001a!\u0010\u0019\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020\u00010\u001f2\u0006\u0010 \u001a\u00020\u0003H\u0087\u0082\u0004¢\u0006\u0002\b\"\u001a!\u0010\u0019\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020!0\u001f2\u0006\u0010 \u001a\u00020\u0003H\u0087\u0082\u0004¢\u0006\u0002\b+\u001a!\u0010\u0019\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020$0\u001f2\u0006\u0010 \u001a\u00020\u0003H\u0087\u0082\u0004¢\u0006\u0002\b%\u001a!\u0010\u0019\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020&0\u001f2\u0006\u0010 \u001a\u00020\u0003H\u0087\u0082\u0004¢\u0006\u0002\b'\u001a!\u0010\u0019\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020(0\u001f2\u0006\u0010 \u001a\u00020\u0003H\u0087\u0082\u0004¢\u0006\u0002\b)\u001a!\u0010\u0019\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020\u00010*2\u0006\u0010 \u001a\u00020\u0003H\u0087\u0082\u0004¢\u0006\u0002\b\"\u001a!\u0010\u0019\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020!0*2\u0006\u0010 \u001a\u00020\u0003H\u0087\u0082\u0004¢\u0006\u0002\b+\u001a!\u0010\u0019\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020$0*2\u0006\u0010 \u001a\u00020\u0003H\u0087\u0082\u0004¢\u0006\u0002\b%\u001a\u0016\u0010\u0019\u001a\u00020\u001a*\u00020\u000e2\u0006\u0010 \u001a\u00020\u0003H\u0087\u008a\u0004\u001a!\u0010\u0019\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020\u00010\u001f2\u0006\u0010 \u001a\u00020$H\u0087\u0082\u0004¢\u0006\u0002\b\"\u001a!\u0010\u0019\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020\u00030\u001f2\u0006\u0010 \u001a\u00020$H\u0087\u0082\u0004¢\u0006\u0002\b#\u001a!\u0010\u0019\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020!0\u001f2\u0006\u0010 \u001a\u00020$H\u0087\u0082\u0004¢\u0006\u0002\b+\u001a!\u0010\u0019\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020&0\u001f2\u0006\u0010 \u001a\u00020$H\u0087\u0082\u0004¢\u0006\u0002\b'\u001a!\u0010\u0019\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020(0\u001f2\u0006\u0010 \u001a\u00020$H\u0087\u0082\u0004¢\u0006\u0002\b)\u001a!\u0010\u0019\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020\u00010*2\u0006\u0010 \u001a\u00020$H\u0087\u0082\u0004¢\u0006\u0002\b\"\u001a!\u0010\u0019\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020\u00030*2\u0006\u0010 \u001a\u00020$H\u0087\u0082\u0004¢\u0006\u0002\b#\u001a!\u0010\u0019\u001a\u00020\u001a*\b\u0012\u0004\u0012\u00020!0*2\u0006\u0010 \u001a\u00020$H\u0087\u0082\u0004¢\u0006\u0002\b+\u001a\u0016\u0010\u0019\u001a\u00020\u001a*\u00020\u000e2\u0006\u0010 \u001a\u00020$H\u0087\u008a\u0004\u001a\u0016\u0010\u0019\u001a\u00020\u001a*\u00020\u000f2\u0006\u0010 \u001a\u00020$H\u0087\u008a\u0004\u001a\u0016\u0010,\u001a\u00020\u0002*\u00020\u00012\u0006\u0010-\u001a\u00020!H\u0086\u0084\u0004\u001a\u0016\u0010,\u001a\u00020\u0004*\u00020\u00032\u0006\u0010-\u001a\u00020!H\u0086\u0084\u0004\u001a\u0016\u0010,\u001a\u00020\u0002*\u00020!2\u0006\u0010-\u001a\u00020!H\u0086\u0084\u0004\u001a\u0016\u0010,\u001a\u00020\u0002*\u00020$2\u0006\u0010-\u001a\u00020!H\u0086\u0084\u0004\u001a\u0016\u0010,\u001a\u00020\u0006*\u00020\u00052\u0006\u0010-\u001a\u00020\u0005H\u0086\u0084\u0004\u001a\u0016\u0010,\u001a\u00020\u0002*\u00020\u00012\u0006\u0010-\u001a\u00020\u0001H\u0086\u0084\u0004\u001a\u0016\u0010,\u001a\u00020\u0004*\u00020\u00032\u0006\u0010-\u001a\u00020\u0001H\u0086\u0084\u0004\u001a\u0016\u0010,\u001a\u00020\u0002*\u00020!2\u0006\u0010-\u001a\u00020\u0001H\u0086\u0084\u0004\u001a\u0016\u0010,\u001a\u00020\u0002*\u00020$2\u0006\u0010-\u001a\u00020\u0001H\u0086\u0084\u0004\u001a\u0016\u0010,\u001a\u00020\u0004*\u00020\u00012\u0006\u0010-\u001a\u00020\u0003H\u0086\u0084\u0004\u001a\u0016\u0010,\u001a\u00020\u0004*\u00020\u00032\u0006\u0010-\u001a\u00020\u0003H\u0086\u0084\u0004\u001a\u0016\u0010,\u001a\u00020\u0004*\u00020!2\u0006\u0010-\u001a\u00020\u0003H\u0086\u0084\u0004\u001a\u0016\u0010,\u001a\u00020\u0004*\u00020$2\u0006\u0010-\u001a\u00020\u0003H\u0086\u0084\u0004\u001a\u0016\u0010,\u001a\u00020\u0002*\u00020\u00012\u0006\u0010-\u001a\u00020$H\u0086\u0084\u0004\u001a\u0016\u0010,\u001a\u00020\u0004*\u00020\u00032\u0006\u0010-\u001a\u00020$H\u0086\u0084\u0004\u001a\u0016\u0010,\u001a\u00020\u0002*\u00020!2\u0006\u0010-\u001a\u00020$H\u0086\u0084\u0004\u001a\u0016\u0010,\u001a\u00020\u0002*\u00020$2\u0006\u0010-\u001a\u00020$H\u0086\u0084\u0004\u001a\u000e\u0010.\u001a\u00020\u0002*\u00020\u0002H\u0086\u0080\u0004\u001a\u000e\u0010.\u001a\u00020\u0004*\u00020\u0004H\u0086\u0080\u0004\u001a\u000e\u0010.\u001a\u00020\u0006*\u00020\u0006H\u0086\u0080\u0004\u001a\u0016\u0010/\u001a\u00020\u0002*\u00020\u00022\u0006\u0010/\u001a\u00020\u0001H\u0086\u0084\u0004\u001a\u0016\u0010/\u001a\u00020\u0004*\u00020\u00042\u0006\u0010/\u001a\u00020\u0003H\u0086\u0084\u0004\u001a\u0016\u0010/\u001a\u00020\u0006*\u00020\u00062\u0006\u0010/\u001a\u00020\u0001H\u0086\u0084\u0004\u001a\u0015\u00100\u001a\u0004\u0018\u00010!*\u00020\u0001H\u0080\u0080\u0004¢\u0006\u0002\u00101\u001a\u0015\u00100\u001a\u0004\u0018\u00010!*\u00020\u0003H\u0080\u0080\u0004¢\u0006\u0002\u00102\u001a\u0015\u00100\u001a\u0004\u0018\u00010!*\u00020$H\u0080\u0080\u0004¢\u0006\u0002\u00103\u001a\u0015\u00100\u001a\u0004\u0018\u00010!*\u00020&H\u0080\u0080\u0004¢\u0006\u0002\u00104\u001a\u0015\u00100\u001a\u0004\u0018\u00010!*\u00020(H\u0080\u0080\u0004¢\u0006\u0002\u00105\u001a\u0015\u00106\u001a\u0004\u0018\u00010\u0001*\u00020\u0003H\u0080\u0080\u0004¢\u0006\u0002\u00107\u001a\u0015\u00106\u001a\u0004\u0018\u00010\u0001*\u00020&H\u0080\u0080\u0004¢\u0006\u0002\u00108\u001a\u0015\u00106\u001a\u0004\u0018\u00010\u0001*\u00020(H\u0080\u0080\u0004¢\u0006\u0002\u00109\u001a\u0015\u0010:\u001a\u0004\u0018\u00010\u0003*\u00020&H\u0080\u0080\u0004¢\u0006\u0002\u0010;\u001a\u0015\u0010:\u001a\u0004\u0018\u00010\u0003*\u00020(H\u0080\u0080\u0004¢\u0006\u0002\u0010<\u001a\u0015\u0010=\u001a\u0004\u0018\u00010$*\u00020\u0001H\u0080\u0080\u0004¢\u0006\u0002\u0010>\u001a\u0015\u0010=\u001a\u0004\u0018\u00010$*\u00020\u0003H\u0080\u0080\u0004¢\u0006\u0002\u0010?\u001a\u0015\u0010=\u001a\u0004\u0018\u00010$*\u00020&H\u0080\u0080\u0004¢\u0006\u0002\u0010@\u001a\u0015\u0010=\u001a\u0004\u0018\u00010$*\u00020(H\u0080\u0080\u0004¢\u0006\u0002\u0010A\u001a\u0016\u0010B\u001a\u00020\u000e*\u00020\u00012\u0006\u0010-\u001a\u00020!H\u0086\u0084\u0004\u001a\u0016\u0010B\u001a\u00020\u000f*\u00020\u00032\u0006\u0010-\u001a\u00020!H\u0086\u0084\u0004\u001a\u0016\u0010B\u001a\u00020\u000e*\u00020!2\u0006\u0010-\u001a\u00020!H\u0086\u0084\u0004\u001a\u0016\u0010B\u001a\u00020\u000e*\u00020$2\u0006\u0010-\u001a\u00020!H\u0086\u0084\u0004\u001a\u0016\u0010B\u001a\u00020\u0010*\u00020\u00052\u0006\u0010-\u001a\u00020\u0005H\u0086\u0084\u0004\u001a\u0016\u0010B\u001a\u00020\u000e*\u00020\u00012\u0006\u0010-\u001a\u00020\u0001H\u0086\u0084\u0004\u001a\u0016\u0010B\u001a\u00020\u000f*\u00020\u00032\u0006\u0010-\u001a\u00020\u0001H\u0086\u0084\u0004\u001a\u0016\u0010B\u001a\u00020\u000e*\u00020!2\u0006\u0010-\u001a\u00020\u0001H\u0086\u0084\u0004\u001a\u0016\u0010B\u001a\u00020\u000e*\u00020$2\u0006\u0010-\u001a\u00020\u0001H\u0086\u0084\u0004\u001a\u0016\u0010B\u001a\u00020\u000f*\u00020\u00012\u0006\u0010-\u001a\u00020\u0003H\u0086\u0084\u0004\u001a\u0016\u0010B\u001a\u00020\u000f*\u00020\u00032\u0006\u0010-\u001a\u00020\u0003H\u0086\u0084\u0004\u001a\u0016\u0010B\u001a\u00020\u000f*\u00020!2\u0006\u0010-\u001a\u00020\u0003H\u0086\u0084\u0004\u001a\u0016\u0010B\u001a\u00020\u000f*\u00020$2\u0006\u0010-\u001a\u00020\u0003H\u0086\u0084\u0004\u001a\u0016\u0010B\u001a\u00020\u000e*\u00020\u00012\u0006\u0010-\u001a\u00020$H\u0086\u0084\u0004\u001a\u0016\u0010B\u001a\u00020\u000f*\u00020\u00032\u0006\u0010-\u001a\u00020$H\u0086\u0084\u0004\u001a\u0016\u0010B\u001a\u00020\u000e*\u00020!2\u0006\u0010-\u001a\u00020$H\u0086\u0084\u0004\u001a\u0016\u0010B\u001a\u00020\u000e*\u00020$2\u0006\u0010-\u001a\u00020$H\u0086\u0084\u0004\u001a+\u0010C\u001a\u0002HD\"\u000e\b\u0000\u0010D*\b\u0012\u0004\u0012\u0002HD0E*\u0002HD2\u0006\u0010F\u001a\u0002HDH\u0086\u0080\u0004¢\u0006\u0002\u0010G\u001a\u0016\u0010C\u001a\u00020!*\u00020!2\u0006\u0010F\u001a\u00020!H\u0086\u0080\u0004\u001a\u0016\u0010C\u001a\u00020$*\u00020$2\u0006\u0010F\u001a\u00020$H\u0086\u0080\u0004\u001a\u0016\u0010C\u001a\u00020\u0001*\u00020\u00012\u0006\u0010F\u001a\u00020\u0001H\u0086\u0080\u0004\u001a\u0016\u0010C\u001a\u00020\u0003*\u00020\u00032\u0006\u0010F\u001a\u00020\u0003H\u0086\u0080\u0004\u001a\u0016\u0010C\u001a\u00020(*\u00020(2\u0006\u0010F\u001a\u00020(H\u0086\u0080\u0004\u001a\u0016\u0010C\u001a\u00020&*\u00020&2\u0006\u0010F\u001a\u00020&H\u0086\u0080\u0004\u001a+\u0010H\u001a\u0002HD\"\u000e\b\u0000\u0010D*\b\u0012\u0004\u0012\u0002HD0E*\u0002HD2\u0006\u0010I\u001a\u0002HDH\u0086\u0080\u0004¢\u0006\u0002\u0010G\u001a\u0016\u0010H\u001a\u00020!*\u00020!2\u0006\u0010I\u001a\u00020!H\u0086\u0080\u0004\u001a\u0016\u0010H\u001a\u00020$*\u00020$2\u0006\u0010I\u001a\u00020$H\u0086\u0080\u0004\u001a\u0016\u0010H\u001a\u00020\u0001*\u00020\u00012\u0006\u0010I\u001a\u00020\u0001H\u0086\u0080\u0004\u001a\u0016\u0010H\u001a\u00020\u0003*\u00020\u00032\u0006\u0010I\u001a\u00020\u0003H\u0086\u0080\u0004\u001a\u0016\u0010H\u001a\u00020(*\u00020(2\u0006\u0010I\u001a\u00020(H\u0086\u0080\u0004\u001a\u0016\u0010H\u001a\u00020&*\u00020&2\u0006\u0010I\u001a\u00020&H\u0086\u0080\u0004\u001a7\u0010J\u001a\u0002HD\"\u000e\b\u0000\u0010D*\b\u0012\u0004\u0012\u0002HD0E*\u0002HD2\b\u0010F\u001a\u0004\u0018\u0001HD2\b\u0010I\u001a\u0004\u0018\u0001HDH\u0086\u0080\u0004¢\u0006\u0002\u0010K\u001a\u001e\u0010J\u001a\u00020!*\u00020!2\u0006\u0010F\u001a\u00020!2\u0006\u0010I\u001a\u00020!H\u0086\u0080\u0004\u001a\u001e\u0010J\u001a\u00020$*\u00020$2\u0006\u0010F\u001a\u00020$2\u0006\u0010I\u001a\u00020$H\u0086\u0080\u0004\u001a\u001e\u0010J\u001a\u00020\u0001*\u00020\u00012\u0006\u0010F\u001a\u00020\u00012\u0006\u0010I\u001a\u00020\u0001H\u0086\u0080\u0004\u001a\u001e\u0010J\u001a\u00020\u0003*\u00020\u00032\u0006\u0010F\u001a\u00020\u00032\u0006\u0010I\u001a\u00020\u0003H\u0086\u0080\u0004\u001a\u001e\u0010J\u001a\u00020(*\u00020(2\u0006\u0010F\u001a\u00020(2\u0006\u0010I\u001a\u00020(H\u0086\u0080\u0004\u001a\u001e\u0010J\u001a\u00020&*\u00020&2\u0006\u0010F\u001a\u00020&2\u0006\u0010I\u001a\u00020&H\u0086\u0080\u0004\u001a1\u0010J\u001a\u0002HD\"\u000e\b\u0000\u0010D*\b\u0012\u0004\u0012\u0002HD0E*\u0002HD2\f\u0010L\u001a\b\u0012\u0004\u0012\u0002HD0MH\u0087\u0080\u0004¢\u0006\u0002\u0010N\u001a1\u0010J\u001a\u0002HD\"\u000e\b\u0000\u0010D*\b\u0012\u0004\u0012\u0002HD0E*\u0002HD2\f\u0010L\u001a\b\u0012\u0004\u0012\u0002HD0\u001fH\u0086\u0080\u0004¢\u0006\u0002\u0010O\u001a\u001c\u0010J\u001a\u00020\u0001*\u00020\u00012\f\u0010L\u001a\b\u0012\u0004\u0012\u00020\u00010\u001fH\u0086\u0080\u0004\u001a\u001c\u0010J\u001a\u00020\u0003*\u00020\u00032\f\u0010L\u001a\b\u0012\u0004\u0012\u00020\u00030\u001fH\u0086\u0080\u0004¨\u0006P"}, d2 = {"first", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ranges/IntProgression;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ranges/LongProgression;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ranges/CharProgression;", "firstOrNull", "(Lkotlin/ranges/IntProgression;)Ljava/lang/Integer;", "(Lkotlin/ranges/LongProgression;)Ljava/lang/Long;", "(Lkotlin/ranges/CharProgression;)Ljava/lang/Character;", "last", "lastOrNull", "random", "Lkotlin/ranges/IntRange;", "Lkotlin/ranges/LongRange;", "Lkotlin/ranges/CharRange;", "Lkotlin/random/Random;", "randomOrNull", "(Lkotlin/ranges/IntRange;)Ljava/lang/Integer;", "(Lkotlin/ranges/LongRange;)Ljava/lang/Long;", "(Lkotlin/ranges/CharRange;)Ljava/lang/Character;", "(Lkotlin/ranges/IntRange;Lkotlin/random/Random;)Ljava/lang/Integer;", "(Lkotlin/ranges/LongRange;Lkotlin/random/Random;)Ljava/lang/Long;", "(Lkotlin/ranges/CharRange;Lkotlin/random/Random;)Ljava/lang/Character;", "contains", _UrlKt.FRAGMENT_ENCODE_SET, "element", "(Lkotlin/ranges/IntRange;Ljava/lang/Integer;)Z", "(Lkotlin/ranges/LongRange;Ljava/lang/Long;)Z", "(Lkotlin/ranges/CharRange;Ljava/lang/Character;)Z", "Lkotlin/ranges/ClosedRange;", "value", _UrlKt.FRAGMENT_ENCODE_SET, "intRangeContains", "longRangeContains", _UrlKt.FRAGMENT_ENCODE_SET, "shortRangeContains", _UrlKt.FRAGMENT_ENCODE_SET, "doubleRangeContains", _UrlKt.FRAGMENT_ENCODE_SET, "floatRangeContains", "Lkotlin/ranges/OpenEndRange;", "byteRangeContains", "downTo", "to", "reversed", "step", "toByteExactOrNull", "(I)Ljava/lang/Byte;", "(J)Ljava/lang/Byte;", "(S)Ljava/lang/Byte;", "(D)Ljava/lang/Byte;", "(F)Ljava/lang/Byte;", "toIntExactOrNull", "(J)Ljava/lang/Integer;", "(D)Ljava/lang/Integer;", "(F)Ljava/lang/Integer;", "toLongExactOrNull", "(D)Ljava/lang/Long;", "(F)Ljava/lang/Long;", "toShortExactOrNull", "(I)Ljava/lang/Short;", "(J)Ljava/lang/Short;", "(D)Ljava/lang/Short;", "(F)Ljava/lang/Short;", "until", "coerceAtLeast", "T", _UrlKt.FRAGMENT_ENCODE_SET, "minimumValue", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)Ljava/lang/Comparable;", "coerceAtMost", "maximumValue", "coerceIn", "(Ljava/lang/Comparable;Ljava/lang/Comparable;Ljava/lang/Comparable;)Ljava/lang/Comparable;", "range", "Lkotlin/ranges/ClosedFloatingPointRange;", "(Ljava/lang/Comparable;Lkotlin/ranges/ClosedFloatingPointRange;)Ljava/lang/Comparable;", "(Ljava/lang/Comparable;Lkotlin/ranges/ClosedRange;)Ljava/lang/Comparable;", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/ranges/RangesKt")
@SourceDebugExtension({"SMAP\n_Ranges.kt\nKotlin\n*S Kotlin\n*F\n+ 1 _Ranges.kt\nkotlin/ranges/RangesKt___RangesKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1573:1\n1#2:1574\n*E\n"})
/* loaded from: classes.dex */
class RangesKt___RangesKt extends RangesKt__RangesKt {
    public RangesKt___RangesKt() {
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @DeprecatedSinceKotlin(errorSince = "1.4", hiddenSince = "1.5", warningSince = "1.3")
    @JvmName(name = "byteRangeContains")
    public static final /* synthetic */ boolean byteRangeContains(ClosedRange r0, double r1) {
        Byte r12 = toByteExactOrNull(r1);
        if (r12 != null) goto L5;
        return false;
    L5:
        return r0.contains(r12);
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @DeprecatedSinceKotlin(errorSince = "1.4", hiddenSince = "1.5", warningSince = "1.3")
    @JvmName(name = "byteRangeContains")
    public static final /* synthetic */ boolean byteRangeContains(ClosedRange r0, float r1) {
        Byte r12 = toByteExactOrNull(r1);
        if (r12 != null) goto L5;
        return false;
    L5:
        return r0.contains(r12);
    }

    @JvmName(name = "byteRangeContains")
    public static final boolean byteRangeContains(ClosedRange<Byte> r0, int r1) {
        Byte r12 = toByteExactOrNull(r1);
        if (r12 != null) goto L5;
        return false;
    L5:
        return r0.contains(r12);
    }

    @JvmName(name = "byteRangeContains")
    public static final boolean byteRangeContains(ClosedRange<Byte> r0, long r1) {
        Byte r12 = toByteExactOrNull(r1);
        if (r12 != null) goto L5;
        return false;
    L5:
        return r0.contains(r12);
    }

    @JvmName(name = "byteRangeContains")
    public static final boolean byteRangeContains(ClosedRange<Byte> r0, short r1) {
        Byte r12 = toByteExactOrNull(r1);
        if (r12 != null) goto L5;
        return false;
    L5:
        return r0.contains(r12);
    }

    @SinceKotlin(version = "1.9")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @JvmName(name = "byteRangeContains")
    public static final boolean byteRangeContains(OpenEndRange<Byte> r0, int r1) {
        Byte r12 = toByteExactOrNull(r1);
        if (r12 != null) goto L5;
        return false;
    L5:
        return r0.contains(r12);
    }

    @SinceKotlin(version = "1.9")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @JvmName(name = "byteRangeContains")
    public static final boolean byteRangeContains(OpenEndRange<Byte> r0, long r1) {
        Byte r12 = toByteExactOrNull(r1);
        if (r12 != null) goto L5;
        return false;
    L5:
        return r0.contains(r12);
    }

    @SinceKotlin(version = "1.9")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @JvmName(name = "byteRangeContains")
    public static final boolean byteRangeContains(OpenEndRange<Byte> r0, short r1) {
        Byte r12 = toByteExactOrNull(r1);
        if (r12 != null) goto L5;
        return false;
    L5:
        return r0.contains(r12);
    }

    public static final byte coerceAtLeast(byte r0, byte r1) {
        if (r0 >= r1) goto L4;
        return r1;
    L4:
        return r0;
    }

    public static final double coerceAtLeast(double r1, double r3) {
        if (r1 >= r3) goto L5;
        return r3;
    L5:
        return r1;
    }

    public static float coerceAtLeast(float r1, float r2) {
        if (r1 >= r2) goto L5;
        return r2;
    L5:
        return r1;
    }

    public static int coerceAtLeast(int r0, int r1) {
        if (r0 >= r1) goto L4;
        return r1;
    L4:
        return r0;
    }

    public static long coerceAtLeast(long r1, long r3) {
        if (r1 >= r3) goto L5;
        return r3;
    L5:
        return r1;
    }

    public static final <T extends Comparable<? super T>> T coerceAtLeast(T r1, T r2) {
        if (r1.compareTo(r2) >= 0) goto L5;
        return r2;
    L5:
        return r1;
    }

    public static final short coerceAtLeast(short r0, short r1) {
        if (r0 >= r1) goto L4;
        return r1;
    L4:
        return r0;
    }

    public static final byte coerceAtMost(byte r0, byte r1) {
        if (r0 <= r1) goto L4;
        return r1;
    L4:
        return r0;
    }

    public static final double coerceAtMost(double r1, double r3) {
        if (r1 <= r3) goto L5;
        return r3;
    L5:
        return r1;
    }

    public static float coerceAtMost(float r1, float r2) {
        if (r1 <= r2) goto L5;
        return r2;
    L5:
        return r1;
    }

    public static int coerceAtMost(int r0, int r1) {
        if (r0 <= r1) goto L4;
        return r1;
    L4:
        return r0;
    }

    public static long coerceAtMost(long r1, long r3) {
        if (r1 <= r3) goto L5;
        return r3;
    L5:
        return r1;
    }

    public static final <T extends Comparable<? super T>> T coerceAtMost(T r1, T r2) {
        if (r1.compareTo(r2) <= 0) goto L5;
        return r2;
    L5:
        return r1;
    }

    public static final short coerceAtMost(short r0, short r1) {
        if (r0 <= r1) goto L4;
        return r1;
    L4:
        return r0;
    }

    public static final byte coerceIn(byte r0, byte r1, byte r2) {
        if (r1 > r2) goto L8;
        if (r0 >= r1) goto L5;
        return r1;
    L5:
        if (r0 <= r2) goto L7;
        return r2;
    L7:
        return r0;
    L8:
        RangesKt___RangesKt$$ExternalSyntheticBUOutline1.m(r2, r1);
        return 0;
    }

    public static final double coerceIn(double r1, double r3, double r5) {
        if (r3 > r5) goto L12;
        if (r1 >= r3) goto L8;
        return r3;
    L8:
        if (r1 <= r5) goto L10;
        return r5;
    L10:
        return r1;
    L12:
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + r5 + " is less than minimum " + r3 + '.');
    }

    public static float coerceIn(float r2, float r3, float r4) {
        if (r3 > r4) goto L12;
        if (r2 >= r3) goto L8;
        return r3;
    L8:
        if (r2 <= r4) goto L10;
        return r4;
    L10:
        return r2;
    L12:
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + r4 + " is less than minimum " + r3 + '.');
    }

    public static int coerceIn(int r0, int r1, int r2) {
        if (r1 > r2) goto L8;
        if (r0 >= r1) goto L5;
        return r1;
    L5:
        if (r0 <= r2) goto L7;
        return r2;
    L7:
        return r0;
    L8:
        RangesKt___RangesKt$$ExternalSyntheticBUOutline1.m(r2, r1);
        return 0;
    }

    public static int coerceIn(int r1, ClosedRange<Integer> r2) {
        if ((r2 instanceof ClosedFloatingPointRange) == false) goto L7;
        return ((Number) coerceIn(Integer.valueOf(r1), (ClosedFloatingPointRange) r2)).intValue();
    L7:
        if (r2.isEmpty() == false) goto L9;
        MediaType$Companion$$ExternalSyntheticBUOutline0.m("Cannot coerce value to an empty range: ", r2, 46);
        return 0;
    L9:
        if (r1 >= ((Number) r2.getStart()).intValue()) goto L13;
        return ((Number) r2.getStart()).intValue();
    L13:
        if (r1 > ((Number) r2.getEndInclusive()).intValue()) goto L15;
        return r1;
    L15:
        return ((Number) r2.getEndInclusive()).intValue();
    }

    public static long coerceIn(long r1, long r3, long r5) {
        if (r3 > r5) goto L12;
        if (r1 >= r3) goto L8;
        return r3;
    L8:
        if (r1 <= r5) goto L10;
        return r5;
    L10:
        return r1;
    L12:
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + r5 + " is less than minimum " + r3 + '.');
    }

    public static long coerceIn(long r2, ClosedRange<Long> r4) {
        if ((r4 instanceof ClosedFloatingPointRange) == false) goto L7;
        return ((Number) coerceIn(Long.valueOf(r2), (ClosedFloatingPointRange) r4)).longValue();
    L7:
        if (r4.isEmpty() == false) goto L9;
        MediaType$Companion$$ExternalSyntheticBUOutline0.m("Cannot coerce value to an empty range: ", r4, 46);
        return 0;
    L9:
        if (r2 >= ((Number) r4.getStart()).longValue()) goto L13;
        return ((Number) r4.getStart()).longValue();
    L13:
        if (r2 > ((Number) r4.getEndInclusive()).longValue()) goto L15;
        return r2;
    L15:
        return ((Number) r4.getEndInclusive()).longValue();
    }

    public static final <T extends Comparable<? super T>> T coerceIn(T r2, T r3, T r4) {
        if (r3 == null) goto L14;
        if (r4 == null) goto L14;
        if (r3.compareTo(r4) <= 0) goto L7;
        BundleKt$$ExternalSyntheticBUOutline0.m("Cannot coerce value to an empty range: maximum ", r4, " is less than minimum ", r3, 46);
        return null;
    L7:
        if (r2.compareTo(r3) >= 0) goto L10;
        return r3;
    L10:
        if (r2.compareTo(r4) <= 0) goto L22;
        return r4;
    L22:
        return r2;
    L14:
        if (r3 != null) goto L16;
    L18:
        if (r4 == null) goto L22;
        if (r2.compareTo(r4) <= 0) goto L22;
        return r4;
    L16:
        if (r2.compareTo(r3) >= 0) goto L18;
        return r3;
    }

    @SinceKotlin(version = "1.1")
    public static final <T extends Comparable<? super T>> T coerceIn(T r1, ClosedFloatingPointRange<T> r2) {
        if (r2.isEmpty() == false) goto L5;
        MediaType$Companion$$ExternalSyntheticBUOutline0.m("Cannot coerce value to an empty range: ", r2, 46);
        return null;
    L5:
        if (r2.lessThanOrEquals(r1, r2.getStart()) == false) goto L11;
        if (r2.lessThanOrEquals(r2.getStart(), r1) == true) goto L11;
        return r2.getStart();
    L11:
        if (r2.lessThanOrEquals(r2.getEndInclusive(), r1) == true) goto L13;
        return r1;
    L13:
        if (r2.lessThanOrEquals(r1, r2.getEndInclusive()) == false) goto L15;
        return r1;
    L15:
        return r2.getEndInclusive();
    }

    public static final <T extends Comparable<? super T>> T coerceIn(T r1, ClosedRange<T> r2) {
        if ((r2 instanceof ClosedFloatingPointRange) == false) goto L7;
        return (T) coerceIn(r1, (ClosedFloatingPointRange) r2);
    L7:
        if (r2.isEmpty() == false) goto L9;
        MediaType$Companion$$ExternalSyntheticBUOutline0.m("Cannot coerce value to an empty range: ", r2, 46);
        return null;
    L9:
        if (r1.compareTo(r2.getStart()) >= 0) goto L13;
        return (T) r2.getStart();
    L13:
        if (r1.compareTo(r2.getEndInclusive()) > 0) goto L15;
        return r1;
    L15:
        return (T) r2.getEndInclusive();
    }

    public static final short coerceIn(short r0, short r1, short r2) {
        if (r1 > r2) goto L8;
        if (r0 >= r1) goto L5;
        return r1;
    L5:
        if (r0 <= r2) goto L7;
        return r2;
    L7:
        return r0;
    L8:
        RangesKt___RangesKt$$ExternalSyntheticBUOutline1.m(r2, r1);
        return 0;
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final boolean contains(CharRange r0, Character r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (r0.contains(r1.charValue()) == false) goto L9;
        return true;
    L9:
        return false;
    }

    @InlineOnly
    private static final boolean contains(IntRange r0, byte r1) {
        return intRangeContains(r0, r1);
    }

    @InlineOnly
    private static final boolean contains(IntRange r0, long r1) {
        return intRangeContains(r0, r1);
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final boolean contains(IntRange r0, Integer r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (r0.contains(r1.intValue()) == false) goto L9;
        return true;
    L9:
        return false;
    }

    @InlineOnly
    private static final boolean contains(IntRange r0, short r1) {
        return intRangeContains(r0, r1);
    }

    @InlineOnly
    private static final boolean contains(LongRange r0, byte r1) {
        return longRangeContains(r0, r1);
    }

    @InlineOnly
    private static final boolean contains(LongRange r0, int r1) {
        return longRangeContains(r0, r1);
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final boolean contains(LongRange r2, Long r3) {
        if (r3 != null) goto L4;
        return false;
    L4:
        if (r2.contains(r3.longValue()) == false) goto L9;
        return true;
    L9:
        return false;
    }

    @InlineOnly
    private static final boolean contains(LongRange r0, short r1) {
        return longRangeContains(r0, r1);
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @DeprecatedSinceKotlin(errorSince = "1.4", hiddenSince = "1.5", warningSince = "1.3")
    @JvmName(name = "doubleRangeContains")
    public static final /* synthetic */ boolean doubleRangeContains(ClosedRange r2, byte r3) {
        return r2.contains(Double.valueOf(r3));
    }

    @JvmName(name = "doubleRangeContains")
    public static final boolean doubleRangeContains(ClosedRange<Double> r2, float r3) {
        return r2.contains(Double.valueOf(r3));
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @DeprecatedSinceKotlin(errorSince = "1.4", hiddenSince = "1.5", warningSince = "1.3")
    @JvmName(name = "doubleRangeContains")
    public static final /* synthetic */ boolean doubleRangeContains(ClosedRange r2, int r3) {
        return r2.contains(Double.valueOf(r3));
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @DeprecatedSinceKotlin(errorSince = "1.4", hiddenSince = "1.5", warningSince = "1.3")
    @JvmName(name = "doubleRangeContains")
    public static final /* synthetic */ boolean doubleRangeContains(ClosedRange r0, long r1) {
        return r0.contains(Double.valueOf(r1));
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @DeprecatedSinceKotlin(errorSince = "1.4", hiddenSince = "1.5", warningSince = "1.3")
    @JvmName(name = "doubleRangeContains")
    public static final /* synthetic */ boolean doubleRangeContains(ClosedRange r2, short r3) {
        return r2.contains(Double.valueOf(r3));
    }

    @SinceKotlin(version = "1.9")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @JvmName(name = "doubleRangeContains")
    public static final boolean doubleRangeContains(OpenEndRange<Double> r2, float r3) {
        return r2.contains(Double.valueOf(r3));
    }

    public static final CharProgression downTo(char r2, char r3) {
        return CharProgression.Companion.fromClosedRange(r2, r3, -1);
    }

    public static final IntProgression downTo(byte r2, byte r3) {
        return IntProgression.Companion.fromClosedRange(r2, r3, -1);
    }

    public static final IntProgression downTo(byte r2, int r3) {
        return IntProgression.Companion.fromClosedRange(r2, r3, -1);
    }

    public static final IntProgression downTo(byte r2, short r3) {
        return IntProgression.Companion.fromClosedRange(r2, r3, -1);
    }

    public static final IntProgression downTo(int r2, byte r3) {
        return IntProgression.Companion.fromClosedRange(r2, r3, -1);
    }

    public static IntProgression downTo(int r2, int r3) {
        return IntProgression.Companion.fromClosedRange(r2, r3, -1);
    }

    public static final IntProgression downTo(int r2, short r3) {
        return IntProgression.Companion.fromClosedRange(r2, r3, -1);
    }

    public static final IntProgression downTo(short r2, byte r3) {
        return IntProgression.Companion.fromClosedRange(r2, r3, -1);
    }

    public static final IntProgression downTo(short r2, int r3) {
        return IntProgression.Companion.fromClosedRange(r2, r3, -1);
    }

    public static final IntProgression downTo(short r2, short r3) {
        return IntProgression.Companion.fromClosedRange(r2, r3, -1);
    }

    public static final LongProgression downTo(byte r7, long r8) {
        return LongProgression.Companion.fromClosedRange(r7, r8, -1);
    }

    public static final LongProgression downTo(int r7, long r8) {
        return LongProgression.Companion.fromClosedRange(r7, r8, -1);
    }

    public static final LongProgression downTo(long r7, byte r9) {
        return LongProgression.Companion.fromClosedRange(r7, r9, -1);
    }

    public static final LongProgression downTo(long r7, int r9) {
        return LongProgression.Companion.fromClosedRange(r7, r9, -1);
    }

    public static final LongProgression downTo(long r7, long r9) {
        return LongProgression.Companion.fromClosedRange(r7, r9, -1);
    }

    public static final LongProgression downTo(long r7, short r9) {
        return LongProgression.Companion.fromClosedRange(r7, r9, -1);
    }

    public static final LongProgression downTo(short r7, long r8) {
        return LongProgression.Companion.fromClosedRange(r7, r8, -1);
    }

    @SinceKotlin(version = "1.7")
    public static final char first(CharProgression r2) {
        if (r2.isEmpty() == false) goto L5;
        RangesKt___RangesKt$$ExternalSyntheticBUOutline0.m("Progression ", r2, " is empty.");
        return 0;
    L5:
        return r2.getFirst();
    }

    @SinceKotlin(version = "1.7")
    public static final int first(IntProgression r2) {
        if (r2.isEmpty() == false) goto L5;
        RangesKt___RangesKt$$ExternalSyntheticBUOutline0.m("Progression ", r2, " is empty.");
        return 0;
    L5:
        return r2.getFirst();
    }

    @SinceKotlin(version = "1.7")
    public static final long first(LongProgression r2) {
        if (r2.isEmpty() == false) goto L5;
        RangesKt___RangesKt$$ExternalSyntheticBUOutline0.m("Progression ", r2, " is empty.");
        return 0;
    L5:
        return r2.getFirst();
    }

    @SinceKotlin(version = "1.7")
    public static final Character firstOrNull(CharProgression r1) {
        if (r1.isEmpty() == false) goto L7;
        return null;
    L7:
        return Character.valueOf(r1.getFirst());
    }

    @SinceKotlin(version = "1.7")
    public static final Integer firstOrNull(IntProgression r1) {
        if (r1.isEmpty() == false) goto L7;
        return null;
    L7:
        return Integer.valueOf(r1.getFirst());
    }

    @SinceKotlin(version = "1.7")
    public static final Long firstOrNull(LongProgression r2) {
        if (r2.isEmpty() == false) goto L7;
        return null;
    L7:
        return Long.valueOf(r2.getFirst());
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @DeprecatedSinceKotlin(errorSince = "1.4", hiddenSince = "1.5", warningSince = "1.3")
    @JvmName(name = "floatRangeContains")
    public static final /* synthetic */ boolean floatRangeContains(ClosedRange r0, byte r1) {
        return r0.contains(Float.valueOf(r1));
    }

    @JvmName(name = "floatRangeContains")
    public static final boolean floatRangeContains(ClosedRange<Float> r0, double r1) {
        return r0.contains(Float.valueOf((float) r1));
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @DeprecatedSinceKotlin(errorSince = "1.4", hiddenSince = "1.5", warningSince = "1.3")
    @JvmName(name = "floatRangeContains")
    public static final /* synthetic */ boolean floatRangeContains(ClosedRange r0, int r1) {
        return r0.contains(Float.valueOf(r1));
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @DeprecatedSinceKotlin(errorSince = "1.4", hiddenSince = "1.5", warningSince = "1.3")
    @JvmName(name = "floatRangeContains")
    public static final /* synthetic */ boolean floatRangeContains(ClosedRange r0, long r1) {
        return r0.contains(Float.valueOf(r1));
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @DeprecatedSinceKotlin(errorSince = "1.4", hiddenSince = "1.5", warningSince = "1.3")
    @JvmName(name = "floatRangeContains")
    public static final /* synthetic */ boolean floatRangeContains(ClosedRange r0, short r1) {
        return r0.contains(Float.valueOf(r1));
    }

    @JvmName(name = "intRangeContains")
    public static final boolean intRangeContains(ClosedRange<Integer> r0, byte r1) {
        return r0.contains(Integer.valueOf(r1));
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @DeprecatedSinceKotlin(errorSince = "1.4", hiddenSince = "1.5", warningSince = "1.3")
    @JvmName(name = "intRangeContains")
    public static final /* synthetic */ boolean intRangeContains(ClosedRange r0, double r1) {
        Integer r12 = toIntExactOrNull(r1);
        if (r12 != null) goto L5;
        return false;
    L5:
        return r0.contains(r12);
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @DeprecatedSinceKotlin(errorSince = "1.4", hiddenSince = "1.5", warningSince = "1.3")
    @JvmName(name = "intRangeContains")
    public static final /* synthetic */ boolean intRangeContains(ClosedRange r0, float r1) {
        Integer r12 = toIntExactOrNull(r1);
        if (r12 != null) goto L5;
        return false;
    L5:
        return r0.contains(r12);
    }

    @JvmName(name = "intRangeContains")
    public static final boolean intRangeContains(ClosedRange<Integer> r0, long r1) {
        Integer r12 = toIntExactOrNull(r1);
        if (r12 != null) goto L5;
        return false;
    L5:
        return r0.contains(r12);
    }

    @JvmName(name = "intRangeContains")
    public static final boolean intRangeContains(ClosedRange<Integer> r0, short r1) {
        return r0.contains(Integer.valueOf(r1));
    }

    @SinceKotlin(version = "1.9")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @JvmName(name = "intRangeContains")
    public static final boolean intRangeContains(OpenEndRange<Integer> r0, byte r1) {
        return r0.contains(Integer.valueOf(r1));
    }

    @SinceKotlin(version = "1.9")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @JvmName(name = "intRangeContains")
    public static final boolean intRangeContains(OpenEndRange<Integer> r0, long r1) {
        Integer r12 = toIntExactOrNull(r1);
        if (r12 != null) goto L5;
        return false;
    L5:
        return r0.contains(r12);
    }

    @SinceKotlin(version = "1.9")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @JvmName(name = "intRangeContains")
    public static final boolean intRangeContains(OpenEndRange<Integer> r0, short r1) {
        return r0.contains(Integer.valueOf(r1));
    }

    @SinceKotlin(version = "1.7")
    public static final char last(CharProgression r2) {
        if (r2.isEmpty() == false) goto L5;
        RangesKt___RangesKt$$ExternalSyntheticBUOutline0.m("Progression ", r2, " is empty.");
        return 0;
    L5:
        return r2.getLast();
    }

    @SinceKotlin(version = "1.7")
    public static final int last(IntProgression r2) {
        if (r2.isEmpty() == false) goto L5;
        RangesKt___RangesKt$$ExternalSyntheticBUOutline0.m("Progression ", r2, " is empty.");
        return 0;
    L5:
        return r2.getLast();
    }

    @SinceKotlin(version = "1.7")
    public static final long last(LongProgression r2) {
        if (r2.isEmpty() == false) goto L5;
        RangesKt___RangesKt$$ExternalSyntheticBUOutline0.m("Progression ", r2, " is empty.");
        return 0;
    L5:
        return r2.getLast();
    }

    @SinceKotlin(version = "1.7")
    public static final Character lastOrNull(CharProgression r1) {
        if (r1.isEmpty() == false) goto L7;
        return null;
    L7:
        return Character.valueOf(r1.getLast());
    }

    @SinceKotlin(version = "1.7")
    public static final Integer lastOrNull(IntProgression r1) {
        if (r1.isEmpty() == false) goto L7;
        return null;
    L7:
        return Integer.valueOf(r1.getLast());
    }

    @SinceKotlin(version = "1.7")
    public static final Long lastOrNull(LongProgression r2) {
        if (r2.isEmpty() == false) goto L7;
        return null;
    L7:
        return Long.valueOf(r2.getLast());
    }

    @JvmName(name = "longRangeContains")
    public static final boolean longRangeContains(ClosedRange<Long> r2, byte r3) {
        return r2.contains(Long.valueOf(r3));
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @DeprecatedSinceKotlin(errorSince = "1.4", hiddenSince = "1.5", warningSince = "1.3")
    @JvmName(name = "longRangeContains")
    public static final /* synthetic */ boolean longRangeContains(ClosedRange r0, double r1) {
        Long r12 = toLongExactOrNull(r1);
        if (r12 != null) goto L5;
        return false;
    L5:
        return r0.contains(r12);
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @DeprecatedSinceKotlin(errorSince = "1.4", hiddenSince = "1.5", warningSince = "1.3")
    @JvmName(name = "longRangeContains")
    public static final /* synthetic */ boolean longRangeContains(ClosedRange r0, float r1) {
        Long r12 = toLongExactOrNull(r1);
        if (r12 != null) goto L5;
        return false;
    L5:
        return r0.contains(r12);
    }

    @JvmName(name = "longRangeContains")
    public static final boolean longRangeContains(ClosedRange<Long> r2, int r3) {
        return r2.contains(Long.valueOf(r3));
    }

    @JvmName(name = "longRangeContains")
    public static final boolean longRangeContains(ClosedRange<Long> r2, short r3) {
        return r2.contains(Long.valueOf(r3));
    }

    @SinceKotlin(version = "1.9")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @JvmName(name = "longRangeContains")
    public static final boolean longRangeContains(OpenEndRange<Long> r2, byte r3) {
        return r2.contains(Long.valueOf(r3));
    }

    @SinceKotlin(version = "1.9")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @JvmName(name = "longRangeContains")
    public static final boolean longRangeContains(OpenEndRange<Long> r2, int r3) {
        return r2.contains(Long.valueOf(r3));
    }

    @SinceKotlin(version = "1.9")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @JvmName(name = "longRangeContains")
    public static final boolean longRangeContains(OpenEndRange<Long> r2, short r3) {
        return r2.contains(Long.valueOf(r3));
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final char random(CharRange r1) {
        return random(r1, Random.Default);
    }

    @SinceKotlin(version = "1.3")
    public static final char random(CharRange r1, Random r2) {
        return (char) r2.nextInt(r1.getFirst(), r1.getLast() + 1);
    L5:
        e = move-exception;
        UIntArray$Iterator$$ExternalSyntheticBUOutline0.m(e.getMessage());
        return 0;
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final int random(IntRange r1) {
        return random(r1, Random.Default);
    }

    @SinceKotlin(version = "1.3")
    public static final int random(IntRange r0, Random r1) {
        return RandomKt.nextInt(r1, r0);
    L4:
        e = move-exception;
        UIntArray$Iterator$$ExternalSyntheticBUOutline0.m(e.getMessage());
        return 0;
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final long random(LongRange r2) {
        return random(r2, Random.Default);
    }

    @SinceKotlin(version = "1.3")
    public static final long random(LongRange r0, Random r1) {
        return RandomKt.nextLong(r1, r0);
    L4:
        e = move-exception;
        UIntArray$Iterator$$ExternalSyntheticBUOutline0.m(e.getMessage());
        return 0;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final Character randomOrNull(CharRange r1) {
        return randomOrNull(r1, Random.Default);
    }

    @SinceKotlin(version = "1.4")
    public static final Character randomOrNull(CharRange r1, Random r2) {
        if (r1.isEmpty() == false) goto L7;
        return null;
    L7:
        return Character.valueOf((char) r2.nextInt(r1.getFirst(), r1.getLast() + 1));
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final Integer randomOrNull(IntRange r1) {
        return randomOrNull(r1, Random.Default);
    }

    @SinceKotlin(version = "1.4")
    public static final Integer randomOrNull(IntRange r1, Random r2) {
        if (r1.isEmpty() == false) goto L7;
        return null;
    L7:
        return Integer.valueOf(RandomKt.nextInt(r2, r1));
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final Long randomOrNull(LongRange r1) {
        return randomOrNull(r1, Random.Default);
    }

    @SinceKotlin(version = "1.4")
    public static final Long randomOrNull(LongRange r1, Random r2) {
        if (r1.isEmpty() == false) goto L7;
        return null;
    L7:
        return Long.valueOf(RandomKt.nextLong(r2, r1));
    }

    public static final CharProgression reversed(CharProgression r3) {
        return CharProgression.Companion.fromClosedRange(r3.getLast(), r3.getFirst(), -r3.getStep());
    }

    public static final IntProgression reversed(IntProgression r3) {
        return IntProgression.Companion.fromClosedRange(r3.getLast(), r3.getFirst(), -r3.getStep());
    }

    public static final LongProgression reversed(LongProgression r7) {
        return LongProgression.Companion.fromClosedRange(r7.getLast(), r7.getFirst(), -r7.getStep());
    }

    @JvmName(name = "shortRangeContains")
    public static final boolean shortRangeContains(ClosedRange<Short> r0, byte r1) {
        return r0.contains(Short.valueOf(r1));
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @DeprecatedSinceKotlin(errorSince = "1.4", hiddenSince = "1.5", warningSince = "1.3")
    @JvmName(name = "shortRangeContains")
    public static final /* synthetic */ boolean shortRangeContains(ClosedRange r0, double r1) {
        Short r12 = toShortExactOrNull(r1);
        if (r12 != null) goto L5;
        return false;
    L5:
        return r0.contains(r12);
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @DeprecatedSinceKotlin(errorSince = "1.4", hiddenSince = "1.5", warningSince = "1.3")
    @JvmName(name = "shortRangeContains")
    public static final /* synthetic */ boolean shortRangeContains(ClosedRange r0, float r1) {
        Short r12 = toShortExactOrNull(r1);
        if (r12 != null) goto L5;
        return false;
    L5:
        return r0.contains(r12);
    }

    @JvmName(name = "shortRangeContains")
    public static final boolean shortRangeContains(ClosedRange<Short> r0, int r1) {
        Short r12 = toShortExactOrNull(r1);
        if (r12 != null) goto L5;
        return false;
    L5:
        return r0.contains(r12);
    }

    @JvmName(name = "shortRangeContains")
    public static final boolean shortRangeContains(ClosedRange<Short> r0, long r1) {
        Short r12 = toShortExactOrNull(r1);
        if (r12 != null) goto L5;
        return false;
    L5:
        return r0.contains(r12);
    }

    @SinceKotlin(version = "1.9")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @JvmName(name = "shortRangeContains")
    public static final boolean shortRangeContains(OpenEndRange<Short> r0, byte r1) {
        return r0.contains(Short.valueOf(r1));
    }

    @SinceKotlin(version = "1.9")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @JvmName(name = "shortRangeContains")
    public static final boolean shortRangeContains(OpenEndRange<Short> r0, int r1) {
        Short r12 = toShortExactOrNull(r1);
        if (r12 != null) goto L5;
        return false;
    L5:
        return r0.contains(r12);
    }

    @SinceKotlin(version = "1.9")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @JvmName(name = "shortRangeContains")
    public static final boolean shortRangeContains(OpenEndRange<Short> r0, long r1) {
        Short r12 = toShortExactOrNull(r1);
        if (r12 != null) goto L5;
        return false;
    L5:
        return r0.contains(r12);
    }

    public static final CharProgression step(CharProgression r3, int r4) {
        if (r4 <= 0) goto L4;
        boolean r0 = true;
    L5:
        RangesKt__RangesKt.checkStepIsPositive(r0, Integer.valueOf(r4));
        CharProgression.Companion r02 = CharProgression.Companion;
        char r1 = r3.getFirst();
        char r2 = r3.getLast();
        if (r3.getStep() > 0) goto L10;
        r4 = -r4;
    L10:
        return r02.fromClosedRange(r1, r2, r4);
    L4:
        r0 = false;
        goto L5
    }

    public static IntProgression step(IntProgression r3, int r4) {
        if (r4 <= 0) goto L4;
        boolean r0 = true;
    L5:
        RangesKt__RangesKt.checkStepIsPositive(r0, Integer.valueOf(r4));
        IntProgression.Companion r02 = IntProgression.Companion;
        int r1 = r3.getFirst();
        int r2 = r3.getLast();
        if (r3.getStep() > 0) goto L10;
        r4 = -r4;
    L10:
        return r02.fromClosedRange(r1, r2, r4);
    L4:
        r0 = false;
        goto L5
    }

    public static final LongProgression step(LongProgression r11, long r12) {
        if (r12 <= 0) goto L5;
        boolean r2 = true;
    L6:
        RangesKt__RangesKt.checkStepIsPositive(r2, Long.valueOf(r12));
        LongProgression.Companion r4 = LongProgression.Companion;
        long r5 = r11.getFirst();
        long r7 = r11.getLast();
        if (r11.getStep() > 0) goto L11;
        r12 = -r12;
    L11:
        return r4.fromClosedRange(r5, r7, r12);
    L5:
        r2 = false;
        goto L6
    }

    public static final Byte toByteExactOrNull(double r2) {
        if ((-128.0d) <= r2) goto L5;
        return null;
    L5:
        if (r2 <= 127.0d) goto L7;
        return null;
    L7:
        return Byte.valueOf((byte) r2);
    }

    public static final Byte toByteExactOrNull(float r1) {
        if ((-128.0f) <= r1) goto L5;
        return null;
    L5:
        if (r1 <= 127.0f) goto L7;
        return null;
    L7:
        return Byte.valueOf((byte) r1);
    }

    public static final Byte toByteExactOrNull(int r1) {
        if ((-128) <= r1) goto L5;
        return null;
    L5:
        if (r1 < 128) goto L7;
        return null;
    L7:
        return Byte.valueOf((byte) r1);
    }

    public static final Byte toByteExactOrNull(long r2) {
        if ((-128) <= r2) goto L5;
        return null;
    L5:
        if (r2 < 128) goto L7;
        return null;
    L7:
        return Byte.valueOf((byte) r2);
    }

    public static final Byte toByteExactOrNull(short r1) {
        if ((-128) <= r1) goto L5;
        return null;
    L5:
        if (r1 < 128) goto L7;
        return null;
    L7:
        return Byte.valueOf((byte) r1);
    }

    public static final Integer toIntExactOrNull(double r2) {
        if ((-2.147483648E9d) <= r2) goto L5;
        return null;
    L5:
        if (r2 <= 2.147483647E9d) goto L7;
        return null;
    L7:
        return Integer.valueOf((int) r2);
    }

    public static final Integer toIntExactOrNull(float r1) {
        if ((-2.1474836E9f) <= r1) goto L5;
        return null;
    L5:
        if (r1 <= 2.1474836E9f) goto L7;
        return null;
    L7:
        return Integer.valueOf((int) r1);
    }

    public static final Integer toIntExactOrNull(long r2) {
        if ((-2147483648L) <= r2) goto L5;
        return null;
    L5:
        if (r2 < 2147483648L) goto L7;
        return null;
    L7:
        return Integer.valueOf((int) r2);
    }

    public static final Long toLongExactOrNull(double r2) {
        if ((-9.223372036854776E18d) <= r2) goto L5;
        return null;
    L5:
        if (r2 <= 9.223372036854776E18d) goto L7;
        return null;
    L7:
        return Long.valueOf((long) r2);
    }

    public static final Long toLongExactOrNull(float r2) {
        if ((-9.223372E18f) <= r2) goto L5;
        return null;
    L5:
        if (r2 <= 9.223372E18f) goto L7;
        return null;
    L7:
        return Long.valueOf((long) r2);
    }

    public static final Short toShortExactOrNull(double r2) {
        if ((-32768.0d) <= r2) goto L5;
        return null;
    L5:
        if (r2 <= 32767.0d) goto L7;
        return null;
    L7:
        return Short.valueOf((short) r2);
    }

    public static final Short toShortExactOrNull(float r1) {
        if ((-32768.0f) <= r1) goto L5;
        return null;
    L5:
        if (r1 <= 32767.0f) goto L7;
        return null;
    L7:
        return Short.valueOf((short) r1);
    }

    public static final Short toShortExactOrNull(int r1) {
        if ((-32768) <= r1) goto L5;
        return null;
    L5:
        if (r1 < 32768) goto L7;
        return null;
    L7:
        return Short.valueOf((short) r1);
    }

    public static final Short toShortExactOrNull(long r2) {
        if ((-32768) <= r2) goto L5;
        return null;
    L5:
        if (r2 < MediaStatus.COMMAND_DISLIKE) goto L7;
        return null;
    L7:
        return Short.valueOf((short) r2);
    }

    public static final CharRange until(char r1, char r2) {
        if (Intrinsics.compare(r2, 0) > 0) goto L7;
        return CharRange.Companion.getEMPTY();
    L7:
        return new CharRange(r1, (char) (r2 - 1));
    }

    public static final IntRange until(byte r1, byte r2) {
        return new IntRange(r1, r2 - 1);
    }

    public static final IntRange until(byte r1, int r2) {
        if (r2 > Integer.MIN_VALUE) goto L7;
        return IntRange.Companion.getEMPTY();
    L7:
        return new IntRange(r1, r2 - 1);
    }

    public static final IntRange until(byte r1, short r2) {
        return new IntRange(r1, r2 - 1);
    }

    public static final IntRange until(int r1, byte r2) {
        return new IntRange(r1, r2 - 1);
    }

    public static IntRange until(int r1, int r2) {
        if (r2 > Integer.MIN_VALUE) goto L7;
        return IntRange.Companion.getEMPTY();
    L7:
        return new IntRange(r1, r2 - 1);
    }

    public static final IntRange until(int r1, short r2) {
        return new IntRange(r1, r2 - 1);
    }

    public static final IntRange until(short r1, byte r2) {
        return new IntRange(r1, r2 - 1);
    }

    public static final IntRange until(short r1, int r2) {
        if (r2 > Integer.MIN_VALUE) goto L7;
        return IntRange.Companion.getEMPTY();
    L7:
        return new IntRange(r1, r2 - 1);
    }

    public static final IntRange until(short r1, short r2) {
        return new IntRange(r1, r2 - 1);
    }

    public static final LongRange until(byte r5, long r6) {
        if (r6 > Long.MIN_VALUE) goto L7;
        return LongRange.Companion.getEMPTY();
    L7:
        return new LongRange(r5, r6 - 1);
    }

    public static final LongRange until(int r5, long r6) {
        if (r6 > Long.MIN_VALUE) goto L7;
        return LongRange.Companion.getEMPTY();
    L7:
        return new LongRange(r5, r6 - 1);
    }

    public static final LongRange until(long r5, byte r7) {
        return new LongRange(r5, r7 - 1);
    }

    public static final LongRange until(long r5, int r7) {
        return new LongRange(r5, r7 - 1);
    }

    public static final LongRange until(long r3, long r5) {
        if (r5 > Long.MIN_VALUE) goto L7;
        return LongRange.Companion.getEMPTY();
    L7:
        return new LongRange(r3, r5 - 1);
    }

    public static final LongRange until(long r5, short r7) {
        return new LongRange(r5, r7 - 1);
    }

    public static final LongRange until(short r5, long r6) {
        if (r6 > Long.MIN_VALUE) goto L7;
        return LongRange.Companion.getEMPTY();
    L7:
        return new LongRange(r5, r6 - 1);
    }
}
