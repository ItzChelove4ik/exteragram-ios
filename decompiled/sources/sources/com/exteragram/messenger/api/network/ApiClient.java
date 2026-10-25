package com.exteragram.messenger.api.network;

import com.exteragram.messenger.debug.DebugConfig;
import com.exteragram.messenger.utils.network.ExteraHttpClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.OkHttpClient;
import okhttp3.internal.url._UrlKt;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00078@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u001b\u0010\n\u001a\u00020\u000b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/exteragram/messenger/api/network/ApiClient;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "BASE_URL", _UrlKt.FRAGMENT_ENCODE_SET, "requestsEnabled", _UrlKt.FRAGMENT_ENCODE_SET, "getRequestsEnabled$TMessagesProj", "()Z", "apiService", "Lcom/exteragram/messenger/api/network/ApiService;", "getApiService", "()Lcom/exteragram/messenger/api/network/ApiService;", "apiService$delegate", "Lkotlin/Lazy;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nApiClient.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ApiClient.kt\ncom/exteragram/messenger/api/network/ApiClient\n+ 2 OkHttpClient.kt\nokhttp3/OkHttpClient$Builder\n*L\n1#1,55:1\n689#2:56\n*S KotlinDebug\n*F\n+ 1 ApiClient.kt\ncom/exteragram/messenger/api/network/ApiClient\n*L\n32#1:56\n*E\n"})
/* loaded from: classes.dex */
public final class ApiClient {
    private static final String BASE_URL = "https://api.exteragram.app/api/v1/";
    public static final ApiClient INSTANCE = null;
    private static final Lazy apiService$delegate = null;

    public static ApiService $r8$lambda$eQYFQy4p1yS6c9r7oScFNs3eM4E() {
        OkHttpClient r0 = ExteraHttpClient.INSTANCE.getClient().newBuilder().addInterceptor(new ApiClient$apiService_delegate$lambda$1$$inlined$addInterceptor$1()).build();
        Gson r1 = new GsonBuilder().create();
        return (ApiService) new Retrofit.Builder().baseUrl(BASE_URL).client(r0).addConverterFactory(GsonConverterFactory.create(r1)).build().create(ApiService.class);
    }

    static {
        INSTANCE = new ApiClient();
        apiService$delegate = LazyKt.lazy(new ApiClient$$ExternalSyntheticLambda0());
    }

    private ApiClient() {
    }

    public final ApiService getApiService() {
        return (ApiService) apiService$delegate.getValue();
    }

    public final boolean getRequestsEnabled$TMessagesProj() {
        return !DebugConfig.getDisableApiRequests();
    }
}
