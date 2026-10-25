package com.exteragram.messenger.nowplaying;

import androidx.collection.LruCache;
import com.android.dx.io.Opcodes;
import com.exteragram.messenger.api.db.DatabaseHelper;
import com.exteragram.messenger.api.dto.NowPlayingDTO;
import com.exteragram.messenger.api.dto.NowPlayingInfoDTO;
import com.exteragram.messenger.api.dto.ProfileDTO;
import com.exteragram.messenger.api.model.NowPlayingServiceType;
import com.exteragram.messenger.api.network.ApiClient;
import com.exteragram.messenger.api.network.ApiService;
import com.exteragram.messenger.debug.DebugConfig;
import com.exteragram.messenger.nowplaying.ui.components.NowPlayingCardData;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.utils.chats.ChatUtils;
import com.exteragram.messenger.utils.network.ExteraHttpClient;
import com.exteragram.messenger.utils.network.RemoteUtils;
import com.sun.jna.Callback;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.MainCoroutineDispatcher;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonBuilder;
import kotlinx.serialization.json.JsonKt;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.ProfileMusicView;
import retrofit2.Response;

@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002@AB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0007J\b\u0010\u0011\u001a\u00020\u000eH\u0007J8\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u000e2\u0014\u0010\u0019\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u001b\u0012\u0004\u0012\u00020\u00150\u001aH\u0007J\u001a\u0010\u001c\u001a\u0004\u0018\u00010\u001b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0082@¢\u0006\u0002\u0010\u001dJ6\u0010\u001e\u001a\u0016\u0012\u0004\u0012\u00020\u000e\u0012\f\u0012\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u000b0\u001f2\u000e\u0010 \u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u000b2\b\u0010!\u001a\u0004\u0018\u00010\nH\u0002J\u0018\u0010\"\u001a\u00020#2\u000e\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010%0$H\u0007J*\u0010&\u001a\u00020#2\b\u0010'\u001a\u0004\u0018\u00010%2\b\b\u0002\u0010(\u001a\u00020\u000e2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000e0$H\u0007J\u0010\u0010)\u001a\u0004\u0018\u00010%H\u0082@¢\u0006\u0002\u0010*J\u0018\u0010+\u001a\u0004\u0018\u00010%2\u0006\u0010\u0014\u001a\u00020\u0015H\u0082@¢\u0006\u0002\u0010,J \u0010-\u001a\u00020\u000e2\b\u0010'\u001a\u0004\u0018\u00010%2\u0006\u0010(\u001a\u00020\u000eH\u0082@¢\u0006\u0002\u0010.J\u0018\u0010/\u001a\u0004\u0018\u00010%2\u0006\u0010\u0014\u001a\u00020\u0015H\u0082@¢\u0006\u0002\u0010,J \u00100\u001a\u0002012\u0006\u0010\u0014\u001a\u00020\u00152\b\u00102\u001a\u0004\u0018\u00010%H\u0082@¢\u0006\u0002\u00103J&\u00108\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b2\u0006\u00109\u001a\u00020\n2\u0006\u0010:\u001a\u00020\nH\u0082@¢\u0006\u0002\u0010;J\u0012\u0010<\u001a\u00020=*\u00020>H\u0086@¢\u0006\u0002\u0010?R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u000205X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006B"}, d2 = {"Lcom/exteragram/messenger/nowplaying/NowPlayingController;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "ARTISTS_SPLITTER", "Lkotlin/text/Regex;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "itunesCache", "Landroidx/collection/LruCache;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/nowplaying/NowPlayingController$ItunesTrack;", "shouldShowCard", _UrlKt.FRAGMENT_ENCODE_SET, "nowPlayingCardData", "Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCardData;", "isSeparateStylesSupported", "getCurrentPlayingTrack", "Lkotlinx/coroutines/Job;", "userId", _UrlKt.FRAGMENT_ENCODE_SET, "savedMusic", "Lorg/telegram/tgnet/TLRPC$Document;", "checkApi", Callback.METHOD_NAME, "Ljava/util/function/BiConsumer;", "Lcom/exteragram/messenger/api/dto/NowPlayingDTO;", "processSavedMusic", "(Lorg/telegram/tgnet/TLRPC$Document;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "hasCommonArtist", "Lkotlin/Pair;", "baseArtists", "itunesArtists", "getNowPlayingInfo", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/util/function/Consumer;", "Lcom/exteragram/messenger/api/dto/NowPlayingInfoDTO;", "updateNowPlayingInfo", "newNowPlaying", "cache", "getNowPlayingInfoInternal", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchProfileNowPlayingInfo", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateNowPlayingInfoInternal", "(Lcom/exteragram/messenger/api/dto/NowPlayingInfoDTO;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "dbGetNowPlaying", "dbUpdateNowPlaying", _UrlKt.FRAGMENT_ENCODE_SET, "data", "(JLcom/exteragram/messenger/api/dto/NowPlayingInfoDTO;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "httpClient", "Lokhttp3/OkHttpClient;", "jsonParser", "Lkotlinx/serialization/json/Json;", "fetchItunesTrack", "performer", "title", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "await", "Lokhttp3/Response;", "Lokhttp3/Call;", "(Lokhttp3/Call;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ItunesSearchResponse", "ItunesTrack", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nNowPlayingController.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NowPlayingController.kt\ncom/exteragram/messenger/nowplaying/NowPlayingController\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 CancellableContinuation.kt\nkotlinx/coroutines/CancellableContinuationKt\n+ 4 Json.kt\nkotlinx/serialization/json/Json\n+ 5 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,381:1\n1586#2:382\n1661#2,3:383\n777#2:386\n873#2,2:387\n1586#2:389\n1661#2,3:390\n777#2:393\n873#2,2:394\n1807#2,2:396\n1807#2,3:398\n1809#2:401\n433#3,10:402\n433#3,10:412\n433#3,10:424\n222#4:422\n1#5:423\n*S KotlinDebug\n*F\n+ 1 NowPlayingController.kt\ncom/exteragram/messenger/nowplaying/NowPlayingController\n*L\n132#1:382\n132#1:383,3\n132#1:386\n132#1:387,2\n186#1:389\n186#1:390,3\n187#1:393\n187#1:394,2\n191#1:396,2\n195#1:398,3\n191#1:401\n286#1:402,10\n293#1:412,10\n364#1:424,10\n354#1:422\n*E\n"})
/* loaded from: classes4.dex */
public final class NowPlayingController {
    private static final Regex ARTISTS_SPLITTER = null;
    public static final NowPlayingController INSTANCE = null;
    private static final OkHttpClient httpClient = null;
    private static final LruCache<String, List<ItunesTrack>> itunesCache = null;
    private static final Json jsonParser = null;
    private static final CoroutineScope scope = null;

    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\n\b\u0087\b\u0018\u0000 #2\u00020\u0001:\u0002$#B3\b\u0010\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\n\u0010\u000bJ'\u0010\u0014\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fH\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0016\u001a\u00020\u0015HÖ\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0018\u0010\u0019J\u001a\u0010\u001c\u001a\u00020\u001b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u001c\u0010\u001dR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001e\u001a\u0004\b\u001f\u0010\u0019R\u001d\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006¢\u0006\f\n\u0004\b\u0007\u0010 \u001a\u0004\b!\u0010\"¨\u0006%"}, d2 = {"Lcom/exteragram/messenger/nowplaying/NowPlayingController$ItunesSearchResponse;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "seen0", "resultCount", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/nowplaying/NowPlayingController$ItunesTrack;", "results", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "serializationConstructorMarker", "<init>", "(IILjava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "self", "Lkotlinx/serialization/encoding/CompositeEncoder;", "output", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "serialDesc", _UrlKt.FRAGMENT_ENCODE_SET, "write$Self$TMessagesProj", "(Lcom/exteragram/messenger/nowplaying/NowPlayingController$ItunesSearchResponse;Lkotlinx/serialization/encoding/CompositeEncoder;Lkotlinx/serialization/descriptors/SerialDescriptor;)V", "write$Self", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "hashCode", "()I", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "I", "getResultCount", "Ljava/util/List;", "getResults", "()Ljava/util/List;", "Companion", "$serializer", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class ItunesSearchResponse {

        @JvmField
        private static final Lazy<KSerializer<Object>>[] $childSerializers = null;
        public static final Companion Companion = null;
        private final int resultCount;
        private final List<ItunesTrack> results;

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/exteragram/messenger/nowplaying/NowPlayingController$ItunesSearchResponse$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/exteragram/messenger/nowplaying/NowPlayingController$ItunesSearchResponse;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker r1) {
                this();
            }

            public final KSerializer<ItunesSearchResponse> serializer() {
                return NowPlayingController$ItunesSearchResponse$$serializer.INSTANCE;
            }
        }

        public static /* synthetic */ KSerializer $r8$lambda$caHQxLf036BBbASIMNd493wK1zE() {
            return _childSerializers$_anonymous_();
        }

        static {
            Companion = new Companion(null);
            $childSerializers = new Lazy[]{null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new NowPlayingController$ItunesSearchResponse$$ExternalSyntheticLambda0())};
        }

        public /* synthetic */ ItunesSearchResponse(int r2, int r3, List r4, SerializationConstructorMarker r5) {
            if (3 == (r2 & 3)) goto L5;
            PluginExceptionsKt.throwMissingFieldException(r2, 3, NowPlayingController$ItunesSearchResponse$$serializer.INSTANCE.getDescriptor());
        L5:
            this.resultCount = r3;
            this.results = r4;
        }

        private static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(NowPlayingController$ItunesTrack$$serializer.INSTANCE);
        }

        public static final /* synthetic */ Lazy[] access$get$childSerializers$cp() {
            return $childSerializers;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$TMessagesProj(ItunesSearchResponse r3, CompositeEncoder r4, SerialDescriptor r5) {
            Lazy<KSerializer<Object>>[] r0 = $childSerializers;
            r4.encodeIntElement(r5, 0, r3.resultCount);
            r4.encodeSerializableElement(r5, 1, r0[1].getValue(), r3.results);
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof ItunesSearchResponse) == true) goto L8;
            return false;
        L8:
            ItunesSearchResponse r52 = (ItunesSearchResponse) r5;
            if (this.resultCount == r52.resultCount) goto L12;
            return false;
        L12:
            if (Intrinsics.areEqual(this.results, r52.results) == true) goto L14;
            return false;
        L14:
            return true;
        }

        public final List<ItunesTrack> getResults() {
            return this.results;
        }

        public int hashCode() {
            return (Integer.hashCode(this.resultCount) * 31) + this.results.hashCode();
        }

        public String toString() {
            return "ItunesSearchResponse(resultCount=" + this.resultCount + ", results=" + this.results + ")";
        }
    }

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u000e\b\u0087\b\u0018\u0000 *2\u00020\u0001:\u0002+*BW\b\u0010\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\b\u0010\r\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u000e\u0010\u000fJ'\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0013H\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0019\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u001b\u0010\u001cJ\u001a\u0010\u001f\u001a\u00020\u001e2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u001f\u0010 R\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010!\u001a\u0004\b\"\u0010\u001aR\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010!\u001a\u0004\b#\u0010\u001aR\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0007\u0010!\u001a\u0004\b$\u0010\u001aR\u0019\u0010\b\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\b\u0010!\u001a\u0004\b%\u0010\u001aR\u0019\u0010\t\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\t\u0010!\u001a\u0004\b&\u0010\u001aR\u0019\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010'\u001a\u0004\b(\u0010)¨\u0006,"}, d2 = {"Lcom/exteragram/messenger/nowplaying/NowPlayingController$ItunesTrack;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "seen0", _UrlKt.FRAGMENT_ENCODE_SET, "trackName", "artistName", "collectionName", "artworkUrl100", "previewUrl", _UrlKt.FRAGMENT_ENCODE_SET, "trackTimeMillis", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "serializationConstructorMarker", "<init>", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "self", "Lkotlinx/serialization/encoding/CompositeEncoder;", "output", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "serialDesc", _UrlKt.FRAGMENT_ENCODE_SET, "write$Self$TMessagesProj", "(Lcom/exteragram/messenger/nowplaying/NowPlayingController$ItunesTrack;Lkotlinx/serialization/encoding/CompositeEncoder;Lkotlinx/serialization/descriptors/SerialDescriptor;)V", "write$Self", "toString", "()Ljava/lang/String;", "hashCode", "()I", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getTrackName", "getArtistName", "getCollectionName", "getArtworkUrl100", "getPreviewUrl", "Ljava/lang/Long;", "getTrackTimeMillis", "()Ljava/lang/Long;", "Companion", "$serializer", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class ItunesTrack {
        public static final Companion Companion = null;
        private final String artistName;
        private final String artworkUrl100;
        private final String collectionName;
        private final String previewUrl;
        private final String trackName;
        private final Long trackTimeMillis;

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/exteragram/messenger/nowplaying/NowPlayingController$ItunesTrack$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/exteragram/messenger/nowplaying/NowPlayingController$ItunesTrack;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker r1) {
                this();
            }

            public final KSerializer<ItunesTrack> serializer() {
                return NowPlayingController$ItunesTrack$$serializer.INSTANCE;
            }
        }

        static {
            Companion = new Companion(null);
        }

        public /* synthetic */ ItunesTrack(int r2, String r3, String r4, String r5, String r6, String r7, Long r8, SerializationConstructorMarker r9) {
            if ((r2 & 1) != 0) goto L5;
            this.trackName = null;
        L7:
            if ((r2 & 2) != 0) goto L9;
            this.artistName = null;
        L11:
            if ((r2 & 4) != 0) goto L13;
            this.collectionName = null;
        L15:
            if ((r2 & 8) != 0) goto L17;
            this.artworkUrl100 = null;
        L19:
            if ((r2 & 16) != 0) goto L21;
            this.previewUrl = null;
        L23:
            if ((r2 & 32) != 0) goto L26;
            this.trackTimeMillis = null;
            return;
        L26:
            this.trackTimeMillis = r8;
            return;
        L21:
            this.previewUrl = r7;
            goto L23
        L17:
            this.artworkUrl100 = r6;
            goto L19
        L13:
            this.collectionName = r5;
            goto L15
        L9:
            this.artistName = r4;
            goto L11
        L5:
            this.trackName = r3;
            goto L7
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$TMessagesProj(ItunesTrack r3, CompositeEncoder r4, SerialDescriptor r5) {
            if (r4.shouldEncodeElementDefault(r5, 0) == false) goto L6;
        L7:
            r4.encodeNullableSerializableElement(r5, 0, StringSerializer.INSTANCE, r3.trackName);
        L9:
            if (r4.shouldEncodeElementDefault(r5, 1) == false) goto L12;
        L13:
            r4.encodeNullableSerializableElement(r5, 1, StringSerializer.INSTANCE, r3.artistName);
        L15:
            if (r4.shouldEncodeElementDefault(r5, 2) == false) goto L18;
        L19:
            r4.encodeNullableSerializableElement(r5, 2, StringSerializer.INSTANCE, r3.collectionName);
        L21:
            if (r4.shouldEncodeElementDefault(r5, 3) == false) goto L24;
        L25:
            r4.encodeNullableSerializableElement(r5, 3, StringSerializer.INSTANCE, r3.artworkUrl100);
        L27:
            if (r4.shouldEncodeElementDefault(r5, 4) == false) goto L30;
        L31:
            r4.encodeNullableSerializableElement(r5, 4, StringSerializer.INSTANCE, r3.previewUrl);
        L33:
            if (r4.shouldEncodeElementDefault(r5, 5) == false) goto L36;
        L37:
            r4.encodeNullableSerializableElement(r5, 5, LongSerializer.INSTANCE, r3.trackTimeMillis);
            return;
        L36:
            if (r3.trackTimeMillis != null) goto L37;
            return;
        L30:
            if (r3.previewUrl == null) goto L33;
        L24:
            if (r3.artworkUrl100 == null) goto L27;
        L18:
            if (r3.collectionName == null) goto L21;
        L12:
            if (r3.artistName == null) goto L15;
        L6:
            if (r3.trackName == null) goto L9;
            goto L7
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof ItunesTrack) == true) goto L8;
            return false;
        L8:
            ItunesTrack r52 = (ItunesTrack) r5;
            if (Intrinsics.areEqual(this.trackName, r52.trackName) == true) goto L12;
            return false;
        L12:
            if (Intrinsics.areEqual(this.artistName, r52.artistName) == true) goto L15;
            return false;
        L15:
            if (Intrinsics.areEqual(this.collectionName, r52.collectionName) == true) goto L18;
            return false;
        L18:
            if (Intrinsics.areEqual(this.artworkUrl100, r52.artworkUrl100) == true) goto L21;
            return false;
        L21:
            if (Intrinsics.areEqual(this.previewUrl, r52.previewUrl) == true) goto L24;
            return false;
        L24:
            if (Intrinsics.areEqual(this.trackTimeMillis, r52.trackTimeMillis) == true) goto L26;
            return false;
        L26:
            return true;
        }

        public final String getArtistName() {
            return this.artistName;
        }

        public final String getArtworkUrl100() {
            return this.artworkUrl100;
        }

        public final String getCollectionName() {
            return this.collectionName;
        }

        public final String getTrackName() {
            return this.trackName;
        }

        public int hashCode() {
            String r0 = this.trackName;
            int r1 = 0;
            if (r0 != null) goto L5;
            int r02 = 0;
        L6:
            int r03 = r02 * 31;
            String r2 = this.artistName;
            if (r2 != null) goto L9;
            int r22 = 0;
        L10:
            int r04 = (r03 + r22) * 31;
            String r23 = this.collectionName;
            if (r23 != null) goto L13;
            int r24 = 0;
        L14:
            int r05 = (r04 + r24) * 31;
            String r25 = this.artworkUrl100;
            if (r25 != null) goto L17;
            int r26 = 0;
        L18:
            int r06 = (r05 + r26) * 31;
            String r27 = this.previewUrl;
            if (r27 != null) goto L21;
            int r28 = 0;
        L22:
            int r07 = (r06 + r28) * 31;
            Long r3 = this.trackTimeMillis;
            if (r3 == null) goto L27;
            r1 = r3.hashCode();
        L27:
            return r07 + r1;
        L21:
            r28 = r27.hashCode();
            goto L22
        L17:
            r26 = r25.hashCode();
            goto L18
        L13:
            r24 = r23.hashCode();
            goto L14
        L9:
            r22 = r2.hashCode();
            goto L10
        L5:
            r02 = r0.hashCode();
            goto L6
        }

        public String toString() {
            return "ItunesTrack(trackName=" + this.trackName + ", artistName=" + this.artistName + ", collectionName=" + this.collectionName + ", artworkUrl100=" + this.artworkUrl100 + ", previewUrl=" + this.previewUrl + ", trackTimeMillis=" + this.trackTimeMillis + ")";
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.nowplaying.NowPlayingController", f = "NowPlayingController.kt", i = {0, 0, 0, 0, 0}, l = {350}, m = "fetchItunesTrack", n = {"performer", "title", "cacheKey", "httpUrl", PluginsConstants.REQUEST}, s = {"L$0", "L$1", "L$2", "L$3", "L$4"})
    /* renamed from: com.exteragram.messenger.nowplaying.NowPlayingController$fetchItunesTrack$1, reason: invalid class name */
    public static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ NowPlayingController this$0;

        public AnonymousClass1(NowPlayingController r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return NowPlayingController.access$fetchItunesTrack(this.this$0, null, null, this);
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/exteragram/messenger/api/dto/NowPlayingInfoDTO;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.nowplaying.NowPlayingController$fetchProfileNowPlayingInfo$2", f = "NowPlayingController.kt", i = {1, 1}, l = {239, 245}, m = "invokeSuspend", n = {PluginsConstants.RESPONSE, "profile"}, s = {"L$0", "L$1"})
    /* renamed from: com.exteragram.messenger.nowplaying.NowPlayingController$fetchProfileNowPlayingInfo$2, reason: invalid class name */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super NowPlayingInfoDTO>, Object> {
        final /* synthetic */ long $userId;
        Object L$0;
        Object L$1;
        int label;

        public AnonymousClass2(long r1, Continuation<? super AnonymousClass2> r3) {
            this.$userId = r1;
            super(2, r3);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r3, Continuation<?> r4) {
            return new AnonymousClass2(this.$userId, r4);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super NowPlayingInfoDTO> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super NowPlayingInfoDTO> r2) {
            return ((AnonymousClass2) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r8) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L15;
            if (r1 == 1) goto L13;
            if (r1 != 2) goto L11;
            ProfileDTO r02 = (ProfileDTO) this.L$1;
            Response r7 = (Response) this.L$0;
            ResultKt.throwOnFailure(r8);     // Catch: Throwable -> L9
        L31:
            return r02.getNowPlaying();
        L11:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L13:
            ResultKt.throwOnFailure(r8);     // Catch: Throwable -> L9
        L21:
            Response r82 = (Response) r8;     // Catch: Throwable -> L9
            if (r82.isSuccessful() == true) goto L24;
            return null;
        L24:
            ProfileDTO r12 = (ProfileDTO) r82.body();     // Catch: Throwable -> L9
            if (r12 != null) goto L27;
            return null;
        L27:
            DatabaseHelper r3 = DatabaseHelper.INSTANCE;     // Catch: Throwable -> L9
            List<ProfileDTO> r5 = CollectionsKt.listOf(r12);     // Catch: Throwable -> L9
            this.L$0 = SpillingKt.nullOutSpilledVariable(r82);     // Catch: Throwable -> L9
            this.L$1 = r12;     // Catch: Throwable -> L9
            this.label = 2;     // Catch: Throwable -> L9
            if (r3.insertProfiles(r5, this) != r0) goto L30;
        L29:
            return r0;
        L30:
            r02 = r12;
            goto L31
        L15:
            ResultKt.throwOnFailure(r8);
            ApiClient r83 = ApiClient.INSTANCE;
            if (r83.getRequestsEnabled$TMessagesProj() == true) goto L18;
            return null;
        L18:
            ApiService r84 = r83.getApiService();     // Catch: Throwable -> L9
            long r52 = this.$userId;     // Catch: Throwable -> L9
            this.label = 1;     // Catch: Throwable -> L9
            r8 = r84.getProfile(r52, this);     // Catch: Throwable -> L9
            if (r8 != r0) goto L21;
        L9:
            th = move-exception;
            FileLog.e(th);
            return null;
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.nowplaying.NowPlayingController$getCurrentPlayingTrack$1", f = "NowPlayingController.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2}, l = {106, 112, 115}, m = "invokeSuspend", n = {"$this$launch", "liveTrackDeferred", "savedTrackDeferred", "startTime", "$this$launch", "liveTrackDeferred", "savedTrackDeferred", "liveTrack", "startTime", "$this$launch", "liveTrackDeferred", "savedTrackDeferred", "liveTrack", "finalTrack", "startTime"}, s = {"L$0", "L$1", "L$2", "J$0", "L$0", "L$1", "L$2", "L$3", "J$0", "L$0", "L$1", "L$2", "L$3", "L$4", "J$0"})
    /* renamed from: com.exteragram.messenger.nowplaying.NowPlayingController$getCurrentPlayingTrack$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01511 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ BiConsumer<NowPlayingDTO, Long> $callback;
        final /* synthetic */ boolean $checkApi;
        final /* synthetic */ TLRPC.Document $savedMusic;
        final /* synthetic */ long $userId;
        long J$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "com.exteragram.messenger.nowplaying.NowPlayingController$getCurrentPlayingTrack$1$1", f = "NowPlayingController.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.exteragram.messenger.nowplaying.NowPlayingController$getCurrentPlayingTrack$1$1, reason: invalid class name and collision with other inner class name */
        public static final class C00211 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ BiConsumer<NowPlayingDTO, Long> $callback;
            final /* synthetic */ NowPlayingDTO $finalTrack;
            final /* synthetic */ long $startTime;
            int label;

            public C00211(BiConsumer<NowPlayingDTO, Long> r1, NowPlayingDTO r2, long r3, Continuation<? super C00211> r5) {
                this.$callback = r1;
                this.$finalTrack = r2;
                this.$startTime = r3;
                super(2, r5);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object r7, Continuation<?> r8) {
                return new C00211(this.$callback, this.$finalTrack, this.$startTime, r8);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
                return invoke2(r1, r2);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
                return ((C00211) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object r6) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) goto L6;
                ResultKt.throwOnFailure(r6);
                this.$callback.accept(this.$finalTrack, Boxing.boxLong(System.currentTimeMillis() - this.$startTime));
                return Unit.INSTANCE;
            L6:
                Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
        }

        public C01511(boolean r1, long r2, TLRPC.Document r4, BiConsumer<NowPlayingDTO, Long> r5, Continuation<? super C01511> r6) {
            this.$checkApi = r1;
            this.$userId = r2;
            this.$savedMusic = r4;
            this.$callback = r5;
            super(2, r6);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r8, Continuation<?> r9) {
            C01511 r0 = new C01511(this.$checkApi, this.$userId, this.$savedMusic, this.$callback, r9);
            r0.L$0 = r8;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((C01511) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r15) {
            CoroutineScope r1 = (CoroutineScope) this.L$0;
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r2 = this.label;
            if (r2 == 0) goto L12;
            if (r2 == 1) goto L11;
            if (r2 == 2) goto L10;
            if (r2 != 3) goto L8;
            NowPlayingDTO r02 = (NowPlayingDTO) this.L$4;
            NowPlayingDTO r03 = (NowPlayingDTO) this.L$3;
            Deferred r04 = (Deferred) this.L$2;
            Deferred r14 = (Deferred) this.L$1;
            ResultKt.throwOnFailure(r15);
        L30:
            return Unit.INSTANCE;
        L8:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L10:
            long r22 = this.J$0;
            NowPlayingDTO r4 = (NowPlayingDTO) this.L$3;
            Deferred r5 = (Deferred) this.L$2;
            Deferred r6 = (Deferred) this.L$1;
            ResultKt.throwOnFailure(r15);
        L25:
            NowPlayingDTO r10 = (NowPlayingDTO) r15;
        L21:
            long r11 = r22;
            MainCoroutineDispatcher r152 = Dispatchers.getMain();
            C00211 r8 = new C00211(this.$callback, r10, r11, null);
            this.L$0 = SpillingKt.nullOutSpilledVariable(r1);
            this.L$1 = SpillingKt.nullOutSpilledVariable(r6);
            this.L$2 = SpillingKt.nullOutSpilledVariable(r5);
            this.L$3 = SpillingKt.nullOutSpilledVariable(r4);
            this.L$4 = SpillingKt.nullOutSpilledVariable(r10);
            this.J$0 = r11;
            this.label = 3;
            if (BuildersKt.withContext(r152, r8, this) != r0) goto L30;
        L28:
            return r0;
        L11:
            r22 = this.J$0;
            Deferred r42 = (Deferred) this.L$2;
            Deferred r52 = (Deferred) this.L$1;
            ResultKt.throwOnFailure(r15);
            r6 = r52;
            r5 = r42;
        L16:
            r4 = (NowPlayingDTO) r15;
            if (r4 != null) goto L19;
        L22:
            this.L$0 = SpillingKt.nullOutSpilledVariable(r1);
            this.L$1 = SpillingKt.nullOutSpilledVariable(r6);
            this.L$2 = SpillingKt.nullOutSpilledVariable(r5);
            this.L$3 = SpillingKt.nullOutSpilledVariable(r4);
            this.J$0 = r22;
            this.label = 2;
            r15 = r5.await(this);
            if (r15 != r0) goto L25;
        L19:
            if (r4.isPlaying() == false) goto L22;
            Job.cancel$default(r5, null, 1, null);
            r10 = r4;
            goto L21
        L12:
            ResultKt.throwOnFailure(r15);
            long r112 = System.currentTimeMillis();
            Deferred r153 = BuildersKt.async$default(r1, Dispatchers.getIO(), null, new NowPlayingController$getCurrentPlayingTrack$1$liveTrackDeferred$1(this.$checkApi, this.$userId, null), 2, null);
            Deferred r23 = BuildersKt.async$default(r1, Dispatchers.getIO(), null, new NowPlayingController$getCurrentPlayingTrack$1$savedTrackDeferred$1(this.$savedMusic, null), 2, null);
            this.L$0 = SpillingKt.nullOutSpilledVariable(r1);
            this.L$1 = SpillingKt.nullOutSpilledVariable(r153);
            this.L$2 = r23;
            this.J$0 = r112;
            this.label = 1;
            Object r3 = r153.await(this);
            if (r3 == r0) goto L28;
            r6 = r153;
            r5 = r23;
            r15 = r3;
            r22 = r112;
            goto L16
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.nowplaying.NowPlayingController$getNowPlayingInfo$1", f = "NowPlayingController.kt", i = {}, l = {Opcodes.REM_INT_LIT16}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.exteragram.messenger.nowplaying.NowPlayingController$getNowPlayingInfo$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01521 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Consumer<NowPlayingInfoDTO> $callback;
        int label;

        public C01521(Consumer<NowPlayingInfoDTO> r1, Continuation<? super C01521> r2) {
            this.$callback = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
            return new C01521(this.$callback, r2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((C01521) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r4) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L8;
            if (r1 != 1) goto L6;
            ResultKt.throwOnFailure(r4);
        L11:
            Consumer<NowPlayingInfoDTO> r3 = this.$callback;
            r3.accept((NowPlayingInfoDTO) r4);
            return Unit.INSTANCE;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L8:
            ResultKt.throwOnFailure(r4);
            NowPlayingController r42 = NowPlayingController.INSTANCE;
            this.label = 1;
            r4 = NowPlayingController.access$getNowPlayingInfoInternal(r42, this);
            if (r4 != r0) goto L11;
            return r0;
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.nowplaying.NowPlayingController", f = "NowPlayingController.kt", i = {0, 1}, l = {232, 232}, m = "getNowPlayingInfoInternal", n = {"userId", "userId"}, s = {"J$0", "J$0"})
    /* renamed from: com.exteragram.messenger.nowplaying.NowPlayingController$getNowPlayingInfoInternal$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01531 extends ContinuationImpl {
        long J$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ NowPlayingController this$0;

        public C01531(NowPlayingController r1, Continuation<? super C01531> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return NowPlayingController.access$getNowPlayingInfoInternal(this.this$0, this);
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.nowplaying.NowPlayingController", f = "NowPlayingController.kt", i = {0, 0, 0, 0, 0}, l = {150}, m = "processSavedMusic", n = {"savedMusic", "title", "author", "baseArtistsList", "baseDto"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4"})
    /* renamed from: com.exteragram.messenger.nowplaying.NowPlayingController$processSavedMusic$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01541 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ NowPlayingController this$0;

        public C01541(NowPlayingController r1, Continuation<? super C01541> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return NowPlayingController.access$processSavedMusic(this.this$0, null, this);
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.nowplaying.NowPlayingController$updateNowPlayingInfo$1", f = "NowPlayingController.kt", i = {}, l = {Opcodes.SHR_INT_LIT8}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.exteragram.messenger.nowplaying.NowPlayingController$updateNowPlayingInfo$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01551 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $cache;
        final /* synthetic */ Consumer<Boolean> $callback;
        final /* synthetic */ NowPlayingInfoDTO $newNowPlaying;
        int label;

        public C01551(NowPlayingInfoDTO r1, boolean r2, Consumer<Boolean> r3, Continuation<? super C01551> r4) {
            this.$newNowPlaying = r1;
            this.$cache = r2;
            this.$callback = r3;
            super(2, r4);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r3, Continuation<?> r4) {
            return new C01551(this.$newNowPlaying, this.$cache, this.$callback, r4);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((C01551) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r5) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L8;
            if (r1 != 1) goto L6;
            ResultKt.throwOnFailure(r5);
        L11:
            boolean r52 = ((Boolean) r5).booleanValue();
            this.$callback.accept(Boxing.boxBoolean(r52));
            return Unit.INSTANCE;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L8:
            ResultKt.throwOnFailure(r5);
            NowPlayingController r53 = NowPlayingController.INSTANCE;
            NowPlayingInfoDTO r12 = this.$newNowPlaying;
            boolean r3 = this.$cache;
            this.label = 1;
            r5 = NowPlayingController.access$updateNowPlayingInfoInternal(r53, r12, r3, this);
            if (r5 != r0) goto L11;
            return r0;
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.nowplaying.NowPlayingController$updateNowPlayingInfoInternal$2", f = "NowPlayingController.kt", i = {0, 0, 1, 1}, l = {382, 272}, m = "invokeSuspend", n = {"query", "$i$f$suspendCancellableCoroutine", "query", "message"}, s = {"L$0", "I$0", "L$0", "L$1"})
    @SourceDebugExtension({"SMAP\nNowPlayingController.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NowPlayingController.kt\ncom/exteragram/messenger/nowplaying/NowPlayingController$updateNowPlayingInfoInternal$2\n+ 2 CancellableContinuation.kt\nkotlinx/coroutines/CancellableContinuationKt\n*L\n1#1,381:1\n433#2,10:382\n*S KotlinDebug\n*F\n+ 1 NowPlayingController.kt\ncom/exteragram/messenger/nowplaying/NowPlayingController$updateNowPlayingInfoInternal$2\n*L\n265#1:382,10\n*E\n"})
    /* renamed from: com.exteragram.messenger.nowplaying.NowPlayingController$updateNowPlayingInfoInternal$2, reason: invalid class name and case insensitive filesystem */
    public static final class C01562 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
        final /* synthetic */ boolean $cache;
        final /* synthetic */ NowPlayingInfoDTO $newNowPlaying;
        int I$0;
        Object L$0;
        Object L$1;
        boolean Z$0;
        int label;

        public C01562(NowPlayingInfoDTO r1, boolean r2, Continuation<? super C01562> r3) {
            this.$newNowPlaying = r1;
            this.$cache = r2;
            super(2, r3);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
            return new C01562(this.$newNowPlaying, this.$cache, r3);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Boolean> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Boolean> r2) {
            return ((C01562) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r10) {
            Object r1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r2 = this.label;
            boolean r5 = true;
            if (r2 == 0) goto L14;
            if (r2 == 1) goto L11;
            if (r2 != 2) goto L9;
            String r0 = (String) this.L$1;
            String r9 = (String) this.L$0;
            ResultKt.throwOnFailure(r10);     // Catch: Exception -> L41
        L34:
            if (((Number) r10).intValue() > 0) goto L38;
            r5 = false;
        L38:
            return Boxing.boxBoolean(r5);
        L9:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L11:
            String r02 = (String) this.L$0;
            ResultKt.throwOnFailure(r10);     // Catch: Exception -> L41
        L28:
            String r102 = (String) r10;     // Catch: Exception -> L41
            if (Intrinsics.areEqual(r102, "ok") == false) goto L40;
            NowPlayingController r22 = NowPlayingController.INSTANCE;     // Catch: Exception -> L41
            long r6 = ChatUtils.getInstance().getUserConfig().getClientUserId();     // Catch: Exception -> L41
            NowPlayingInfoDTO r8 = this.$newNowPlaying;     // Catch: Exception -> L41
            this.L$0 = SpillingKt.nullOutSpilledVariable(r02);     // Catch: Exception -> L41
            this.L$1 = SpillingKt.nullOutSpilledVariable(r102);     // Catch: Exception -> L41
            this.label = 2;     // Catch: Exception -> L41
            r10 = NowPlayingController.access$dbUpdateNowPlaying(r22, r6, r8, this);     // Catch: Exception -> L41
            if (r10 != r1) goto L34;
        L32:
            return r1;
        L40:
            return Boxing.boxBoolean(false);
        L14:
            ResultKt.throwOnFailure(r10);
            NowPlayingInfoDTO r103 = this.$newNowPlaying;     // Catch: Exception -> L41
            if (r103 != null) goto L18;
        L22:
            String r104 = "clear_now_playing";
        L21:
            r02 = r104;
            boolean r105 = this.$cache;     // Catch: Exception -> L41
            this.L$0 = r02;     // Catch: Exception -> L41
            this.Z$0 = r105;     // Catch: Exception -> L41
            this.I$0 = 0;     // Catch: Exception -> L41
            this.label = 1;     // Catch: Exception -> L41
            CancellableContinuationImpl r23 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(this), 1);     // Catch: Exception -> L41
            r23.initCancellability();     // Catch: Exception -> L41
            ChatUtils.getInstance().sendBotRequest(r02, r105, new NowPlayingController$updateNowPlayingInfoInternal$2$message$1$1(r23));     // Catch: Exception -> L41
            r10 = r23.getResult();     // Catch: Exception -> L41
            if (r10 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L26;
            DebugProbesKt.probeCoroutineSuspended(this);     // Catch: Exception -> L41
        L26:
            if (r10 != r1) goto L28;
        L18:
            if (r103.getServiceType() == NowPlayingServiceType.NONE) goto L22;
            r104 = "set_now_playing " + this.$newNowPlaying.getServiceType().name() + " " + this.$newNowPlaying.getUsername();     // Catch: Exception -> L41
            goto L21
        L42:
            return Boxing.boxBoolean(false);
        }
    }

    public static Unit $r8$lambda$kuZTxn5AlwC8BJR0OlZH_p3unYA(JsonBuilder r1) {
        r1.setIgnoreUnknownKeys(true);
        return Unit.INSTANCE;
    }

    static {
        INSTANCE = new NowPlayingController();
        ARTISTS_SPLITTER = new Regex("(?i)\\s*(?:,|&|\\bfeat\\b\\.?|\\bft\\b\\.?)\\s*");
        scope = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob$default(null, 1, null).plus(Dispatchers.getMain()));
        itunesCache = new LruCache(50);
        OkHttpClient.Builder r2 = ExteraHttpClient.INSTANCE.getClient().newBuilder();
        TimeUnit r5 = TimeUnit.SECONDS;
        httpClient = r2.connectTimeout(5, r5).readTimeout(5, r5).writeTimeout(5, r5).build();
        jsonParser = JsonKt.Json$default(null, new NowPlayingController$$ExternalSyntheticLambda0(), 1, null);
    }

    private NowPlayingController() {
    }

    public static final /* synthetic */ Object access$dbUpdateNowPlaying(NowPlayingController r0, long r1, NowPlayingInfoDTO r3, Continuation r4) {
        return r0.dbUpdateNowPlaying(r1, r3, r4);
    }

    public static final /* synthetic */ Object access$fetchItunesTrack(NowPlayingController r0, String r1, String r2, Continuation r3) {
        return r0.fetchItunesTrack(r1, r2, r3);
    }

    public static final /* synthetic */ Object access$getNowPlayingInfoInternal(NowPlayingController r0, Continuation r1) {
        return r0.getNowPlayingInfoInternal(r1);
    }

    public static final /* synthetic */ Object access$processSavedMusic(NowPlayingController r0, TLRPC.Document r1, Continuation r2) {
        return r0.processSavedMusic(r1, r2);
    }

    public static final /* synthetic */ Object access$updateNowPlayingInfoInternal(NowPlayingController r0, NowPlayingInfoDTO r1, boolean r2, Continuation r3) {
        return r0.updateNowPlayingInfoInternal(r1, r2, r3);
    }

    private final Object dbGetNowPlaying(long r3, Continuation<? super NowPlayingInfoDTO> r5) {
        CancellableContinuationImpl r2 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(r5), 1);
        r2.initCancellability();
        DatabaseHelper.getNowPlaying(r3, new NowPlayingController$dbGetNowPlaying$2$1(r2));
        Object r22 = r2.getResult();
        if (r22 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L5;
        DebugProbesKt.probeCoroutineSuspended(r5);
    L5:
        return r22;
    }

    private final Object dbUpdateNowPlaying(long r3, NowPlayingInfoDTO r5, Continuation<? super Integer> r6) {
        CancellableContinuationImpl r2 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(r6), 1);
        r2.initCancellability();
        DatabaseHelper.updateNowPlaying(r3, r5, new NowPlayingController$dbUpdateNowPlaying$2$1(r2));
        Object r22 = r2.getResult();
        if (r22 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L5;
        DebugProbesKt.probeCoroutineSuspended(r6);
    L5:
        return r22;
    }

    private final Object fetchItunesTrack(String r9, String r10, Continuation<? super List<ItunesTrack>> r11) {
        if ((r11 instanceof AnonymousClass1) == false) goto L7;
        AnonymousClass1 r0 = (AnonymousClass1) r11;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r112 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L18;
        if (r2 != 1) goto L16;
        Request r8 = (Request) r0.L$4;
        HttpUrl r82 = (HttpUrl) r0.L$3;
        String r83 = (String) r0.L$2;
        String r92 = (String) r0.L$1;
        String r93 = (String) r0.L$0;
        ResultKt.throwOnFailure(r112);     // Catch: Exception -> L14
    L45:
        okhttp3.Response r113 = (okhttp3.Response) r112;     // Catch: Exception -> L14
        if (r113.isSuccessful() == true) goto L48;
        return null;
    L48:
        Json r94 = jsonParser;     // Catch: Exception -> L14
        String r102 = r113.body().string();     // Catch: Exception -> L14
        r94.getSerializersModule();     // Catch: Exception -> L14
        List<ItunesTrack> r95 = ((ItunesSearchResponse) r94.decodeFromString(ItunesSearchResponse.Companion.serializer(), r102)).getResults();     // Catch: Exception -> L14
        List<ItunesTrack> r84 = itunesCache.put(r83, r95);     // Catch: Exception -> L14
        return r95;
    L16:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L18:
        ResultKt.throwOnFailure(r112);
        if (StringsKt.isBlank(r9) == true) goto L21;
    L23:
        String r114 = r9 + " - " + r10;
        List<ItunesTrack> r5 = itunesCache.get(r114);
        if (r5 == null) goto L26;
        return r5;
    L26:
        HttpUrl r52 = HttpUrl.Companion.parse("https://itunes.apple.com/search");
        if (r52 == null) goto L37;
        HttpUrl.Builder r53 = r52.newBuilder();
        if (r53 == null) goto L37;
        HttpUrl.Builder r22 = r53.addQueryParameter("term", r10 + " - " + r9);
        if (r22 == null) goto L37;
        HttpUrl.Builder r23 = r22.addQueryParameter("entity", "song");
        if (r23 == null) goto L37;
        HttpUrl.Builder r24 = r23.addQueryParameter("limit", "5");
        if (r24 == null) goto L37;
        HttpUrl r25 = r24.build();
    L38:
        if (r25 != null) goto L40;
        return null;
    L40:
        Request r54 = new Request.Builder().url(r25).build();
        Call r6 = httpClient.newCall(r54);     // Catch: Exception -> L14
        r0.L$0 = SpillingKt.nullOutSpilledVariable(r9);     // Catch: Exception -> L14
        r0.L$1 = SpillingKt.nullOutSpilledVariable(r10);     // Catch: Exception -> L14
        r0.L$2 = r114;     // Catch: Exception -> L14
        r0.L$3 = SpillingKt.nullOutSpilledVariable(r25);     // Catch: Exception -> L14
        r0.L$4 = SpillingKt.nullOutSpilledVariable(r54);     // Catch: Exception -> L14
        r0.label = 1;     // Catch: Exception -> L14
        Object r85 = await(r6, r0);     // Catch: Exception -> L14
        if (r85 != r12) goto L44;
        return r12;
    L44:
        r112 = r85;
        r83 = r114;
    L37:
        r25 = null;
        goto L38
    L21:
        if (StringsKt.isBlank(r10) == false) goto L23;
        return null;
    L14:
        e = move-exception;
        e.printStackTrace();
        return null;
    L7:
        r0 = new AnonymousClass1(this, r11);
        goto L8
    }

    private final Object fetchProfileNowPlayingInfo(long r3, Continuation<? super NowPlayingInfoDTO> r5) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(r3, null), r5);
    }

    @JvmStatic
    public static final Job getCurrentPlayingTrack(long r8, TLRPC.Document r10, boolean r11, BiConsumer<NowPlayingDTO, Long> r12) {
        return BuildersKt.launch$default(scope, null, null, new C01511(r11, r8, r10, r12, null), 3, null);
    }

    @JvmStatic
    public static final void getNowPlayingInfo(Consumer<NowPlayingInfoDTO> r6) {
        BuildersKt.launch$default(scope, null, null, new C01521(r6, null), 3, null);
    }

    private final Object getNowPlayingInfoInternal(Continuation<? super NowPlayingInfoDTO> r8) {
        if ((r8 instanceof C01531) == false) goto L7;
        C01531 r0 = (C01531) r8;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r82 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L17;
        if (r2 == 1) goto L16;
        if (r2 != 2) goto L14;
        ResultKt.throwOnFailure(r82);
        return r82;
    L14:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L16:
        long r4 = r0.J$0;
        ResultKt.throwOnFailure(r82);
    L21:
        NowPlayingInfoDTO r83 = (NowPlayingInfoDTO) r82;
        if (r83 != null) goto L27;
        r0.J$0 = r4;
        r0.label = 2;
        Object r7 = fetchProfileNowPlayingInfo(r4, r0);
        if (r7 != r12) goto L26;
    L25:
        return r12;
    L26:
        return r7;
    L27:
        return r83;
    L17:
        ResultKt.throwOnFailure(r82);
        long r5 = ChatUtils.getInstance().getUserConfig().getClientUserId();
        r0.J$0 = r5;
        r0.label = 1;
        r82 = dbGetNowPlaying(r5, r0);
        if (r82 == r12) goto L25;
        r4 = r5;
    L7:
        r0 = new C01531(this, r8);
        goto L8
    }

    private final Pair<Boolean, List<String>> hasCommonArtist(List<String> r9, String r10) {
        if (r10 == null) goto L47;
        if (StringsKt.isBlank(r10) == true) goto L47;
        boolean r0 = false;
        List<String> r8 = ARTISTS_SPLITTER.split(r10, 0);
        ArrayList r102 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r8, 10));
        Iterator<T> r82 = r8.iterator();
    L8:
        if (r82.hasNext() == false) goto L10;
        r102.add(StringsKt.trim((String) r82.next()).toString());
        goto L8
    L10:
        ArrayList r83 = new ArrayList();
        int r1 = r102.size();
        int r2 = 0;
    L11:
        if (r2 >= r1) goto L15;
        Object r3 = r102.get(r2);
        r2 = r2 + 1;
        if (((String) r3).length() <= 0) goto L11;
        r83.add(r3);
        goto L11
    L15:
        List<String> r103 = r9;
        if (r103 == null) goto L45;
        if (r103.isEmpty() == true) goto L45;
        List<String> r104 = r9;
        if ((r104 instanceof Collection) == true) goto L23;
    L25:
        Iterator<T> r105 = r104.iterator();
    L27:
        if (r105.hasNext() == false) goto L40;
        String r12 = ((String) r105.next()).toLowerCase(Locale.ROOT);
        String r22 = AndroidUtilities.translitSafe(r12);
        if (r83.isEmpty() == true) goto L27;
        int r32 = r83.size();
        int r4 = 0;
    L32:
        if (r4 >= r32) goto L27;
        Object r5 = r83.get(r4);
        r4 = r4 + 1;
        String r52 = ((String) r5).toLowerCase(Locale.ROOT);
        String r6 = AndroidUtilities.translitSafe(r52);
        if (Intrinsics.areEqual(r52, r12) == true) goto L39;
        if (Intrinsics.areEqual(r52, r22) == true) goto L39;
        if (Intrinsics.areEqual(r6, r12) == false) goto L32;
    L39:
        r0 = true;
    L40:
        if (r0 == false) goto L43;
        r9 = r83;
    L43:
        return TuplesKt.to(Boolean.valueOf(r0), r9);
    L23:
        if (r104.isEmpty() == false) goto L25;
    L45:
        return TuplesKt.to(Boolean.FALSE, r83);
    L47:
        return TuplesKt.to(Boolean.FALSE, r9);
    }

    @JvmStatic
    public static final boolean isSeparateStylesSupported() {
        if (DebugConfig.getForceCompactSavedMusic() == false) goto L5;
        return true;
    L5:
        if (RemoteUtils.getBooleanConfigValue("separate_music_styles", false).booleanValue() == true) goto L10;
        return false;
    L10:
        return true;
    }

    private final Object processSavedMusic(TLRPC.Document r21, Continuation<? super NowPlayingDTO> r22) {
        if ((r22 instanceof C01541) == false) goto L7;
        C01541 r2 = (C01541) r22;
        int r3 = r2.label;
        if ((r3 & Integer.MIN_VALUE) == 0) goto L7;
        r2.label = r3 - Integer.MIN_VALUE;
    L8:
        Object r1 = r2.result;
        Object r32 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r4 = r2.label;
        Object r6 = null;
        if (r4 == 0) goto L14;
        if (r4 != 1) goto L12;
        NowPlayingDTO r33 = (NowPlayingDTO) r2.L$4;
        List r42 = (List) r2.L$3;
        String r43 = (String) r2.L$2;
        String r44 = (String) r2.L$1;
        TLRPC.Document r23 = (TLRPC.Document) r2.L$0;
        ResultKt.throwOnFailure(r1);
        NowPlayingDTO r7 = r33;
    L47:
        List r12 = (List) r1;
        List r24 = r12;
        if (r24 != null) goto L50;
    L75:
        return r7;
    L50:
        if (r24.isEmpty() == true) goto L75;
        Iterator r25 = r12.iterator();
    L54:
        if (r25.hasNext() == false) goto L59;
        Object r34 = r25.next();
        ItunesTrack r45 = (ItunesTrack) r34;
        boolean r8 = INSTANCE.hasCommonArtist(r7.getArtists(), r45.getArtistName()).component1().booleanValue();
        if (StringsKt.equals(r45.getTrackName(), r7.getTrackName(), true) == false) goto L54;
        if (r8 == false) goto L54;
        r6 = r34;
    L59:
        ItunesTrack r62 = (ItunesTrack) r6;
        if (r62 != null) goto L62;
        r62 = (ItunesTrack) CollectionsKt.first(r12);
    L62:
        List<String> r9 = hasCommonArtist(r7.getArtists(), r62.getArtistName()).component2();
        String r0 = r62.getCollectionName();
        if (r0 != null) goto L65;
        r0 = r7.getAlbumName();
    L65:
        String r10 = r0;
        String r02 = r62.getArtworkUrl100();
        if (r02 != null) goto L68;
    L72:
        String r03 = r7.getCoverUrl();
    L74:
        return NowPlayingDTO.copy$default(r7, null, r9, r10, r03, null, null, false, null, r7.getPlatform(), null, 753, null);
    L68:
        if (r02.length() == 0) goto L72;
        r03 = StringsKt.replace$default(r62.getArtworkUrl100(), "100x100", "300x300", false, 4, null);
        goto L74
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r1);
        if (r21 != null) goto L17;
        return null;
    L17:
        CharSequence r13 = ProfileMusicView.getTitle(r21);
        if ((r13 instanceof String) == false) goto L20;
        String r14 = (String) r13;
    L21:
        if (r14 != null) goto L23;
        r14 = LocaleController.getString(R.string.AudioUnknownTitle);
    L23:
        String r82 = r14;
        CharSequence r15 = ProfileMusicView.getAuthor(r21);
        if ((r15 instanceof String) == false) goto L26;
        String r16 = (String) r15;
    L27:
        if (r16 != null) goto L29;
        r16 = LocaleController.getString(R.string.AudioUnknownArtist);
    L29:
        if (r82 == null) goto L76;
        if (r16 == null) goto L76;
        int r72 = 0;
        List<String> r46 = ARTISTS_SPLITTER.split(r16, 0);
        ArrayList r92 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r46, 10));
        Iterator<T> r47 = r46.iterator();
    L34:
        if (r47.hasNext() == false) goto L36;
        r92.add(StringsKt.trim((String) r47.next()).toString());
        goto L34
    L36:
        ArrayList r48 = new ArrayList();
        int r102 = r92.size();
    L37:
        if (r72 >= r102) goto L41;
        Object r11 = r92.get(r72);
        r72 = r72 + 1;
        if (((String) r11).length() <= 0) goto L37;
        r48.add(r11);
        goto L37
    L41:
        r7 = new NowPlayingDTO(r82, r48, null, null, null, null, true, null, "TELEGRAM", null);
        if (isSeparateStylesSupported() == false) goto L44;
        return r7;
    L44:
        r2.L$0 = SpillingKt.nullOutSpilledVariable(r21);
        r2.L$1 = SpillingKt.nullOutSpilledVariable(r82);
        r2.L$2 = SpillingKt.nullOutSpilledVariable(r16);
        r2.L$3 = SpillingKt.nullOutSpilledVariable(r48);
        r2.L$4 = r7;
        r2.label = 1;
        r1 = fetchItunesTrack(r16, r82, r2);
        if (r1 != r32) goto L47;
        return r32;
    L76:
        return null;
    L26:
        r16 = null;
        goto L27
    L20:
        r14 = null;
    L7:
        r2 = new C01541(this, r22);
        goto L8
    }

    @JvmStatic
    public static final boolean shouldShowCard(NowPlayingCardData r2) {
        if (r2 != null) goto L6;
        return false;
    L6:
        if (Intrinsics.areEqual(r2.getNowPlayingDTO().getPlatform(), "TELEGRAM") == true) goto L8;
        return true;
    L8:
        if (isSeparateStylesSupported() == true) goto L10;
        return true;
    L10:
        return false;
    }

    @JvmStatic
    @JvmOverloads
    public static final void updateNowPlayingInfo(NowPlayingInfoDTO r6, boolean r7, Consumer<Boolean> r8) {
        BuildersKt.launch$default(scope, null, null, new C01551(r6, r7, r8, null), 3, null);
    }

    public static /* synthetic */ void updateNowPlayingInfo$default(NowPlayingInfoDTO r0, boolean r1, Consumer r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L5;
        r1 = true;
    L5:
        updateNowPlayingInfo(r0, r1, r2);
    }

    private final Object updateNowPlayingInfoInternal(NowPlayingInfoDTO r3, boolean r4, Continuation<? super Boolean> r5) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C01562(r3, r4, null), r5);
    }

    public final Object await(Call r3, Continuation<? super okhttp3.Response> r4) {
        CancellableContinuationImpl r2 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(r4), 1);
        r2.initCancellability();
        r2.invokeOnCancellation(new NowPlayingController$await$2$1(r3));
        r3.enqueue(new NowPlayingController$await$2$2(r2));
        Object r22 = r2.getResult();
        if (r22 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L5;
        DebugProbesKt.probeCoroutineSuspended(r4);
    L5:
        return r22;
    }
}
