package com.exteragram.messenger.api.network;

import com.exteragram.messenger.api.dto.BoostySubscriberDTO;
import com.exteragram.messenger.api.dto.NowPlayingDTO;
import com.exteragram.messenger.api.dto.ProfileDTO;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import okhttp3.internal.url._UrlKt;
import okio.ByteString$$ExternalSyntheticBUOutline0;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007H§@¢\u0006\u0002\u0010\bJ0\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\n\u001a\u00020\u00072\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007H§@¢\u0006\u0002\u0010\u000bJ\u001e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\u00032\b\b\u0001\u0010\r\u001a\u00020\u000eH§@¢\u0006\u0002\u0010\u000fJ\u001e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u00032\b\b\u0001\u0010\r\u001a\u00020\u000eH§@¢\u0006\u0002\u0010\u000fJ\u001a\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00040\u0003H§@¢\u0006\u0002\u0010\u0014¨\u0006\u0015À\u0006\u0003"}, d2 = {"Lcom/exteragram/messenger/api/network/ApiService;", _UrlKt.FRAGMENT_ENCODE_SET, "getAllProfiles", "Lretrofit2/Response;", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/api/dto/ProfileDTO;", "etag", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUpdates", "since", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getProfile", "userId", _UrlKt.FRAGMENT_ENCODE_SET, "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCurrentPlayingTrack", "Lcom/exteragram/messenger/api/dto/NowPlayingDTO;", "getBoostySubscribers", "Lcom/exteragram/messenger/api/dto/BoostySubscriberDTO;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public interface ApiService {

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ Object getAllProfiles$default(ApiService r0, String r1, Continuation r2, int r3, Object r4) {
            return ApiService.getAllProfiles$default(r0, r1, r2, r3, r4);
        }

        public static /* synthetic */ Object getUpdates$default(ApiService r0, String r1, String r2, Continuation r3, int r4, Object r5) {
            return ApiService.getUpdates$default(r0, r1, r2, r3, r4, r5);
        }
    }

    static /* synthetic */ Object getAllProfiles$default(ApiService r0, String r1, Continuation r2, int r3, Object r4) {
        if (r4 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: getAllProfiles");
        return null;
    L4:
        if ((r3 & 1) == 0) goto L7;
        r1 = null;
    L7:
        return r0.getAllProfiles(r1, r2);
    }

    static /* synthetic */ Object getUpdates$default(ApiService r0, String r1, String r2, Continuation r3, int r4, Object r5) {
        if (r5 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: getUpdates");
        return null;
    L4:
        if ((r4 & 2) == 0) goto L7;
        r2 = null;
    L7:
        return r0.getUpdates(r1, r2, r3);
    }

    @GET("profiles")
    Object getAllProfiles(@Header("If-None-Match") String r1, Continuation<? super Response<List<ProfileDTO>>> r2);

    @GET("boosty-subscribers")
    Object getBoostySubscribers(Continuation<? super Response<List<BoostySubscriberDTO>>> r1);

    @GET("profiles/{userId}/now-playing")
    Object getCurrentPlayingTrack(@Path("userId") long r1, Continuation<? super Response<NowPlayingDTO>> r3);

    @GET("profiles/{userId}")
    Object getProfile(@Path("userId") long r1, Continuation<? super Response<ProfileDTO>> r3);

    @GET("profiles/updates")
    Object getUpdates(@Query("since") String r1, @Header("If-None-Match") String r2, Continuation<? super Response<List<ProfileDTO>>> r3);
}
