package org.chromium.net.impl;

import android.util.Log;
import java.nio.ByteBuffer;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
import okhttp3.internal.url._UrlKt;
import org.chromium.net.ExperimentalUrlRequest;
import org.chromium.net.RequestFinishedInfo;
import org.chromium.net.UploadDataProvider;
import org.chromium.net.UrlRequest;

/* loaded from: classes5.dex */
public class UrlRequestBuilderImpl extends ExperimentalUrlRequest.Builder {
    private static final String TAG = "UrlRequestBuilderImpl";
    private boolean mAllowDirectExecutor;
    private final UrlRequest.Callback mCallback;
    private final CronetEngineBase mCronetEngine;
    private ByteBuffer mDictionary;
    private String mDictionaryId;
    private byte[] mDictionarySha256Hash;
    private boolean mDisableCache;
    private boolean mDisableConnectionMigration;
    private final Executor mExecutor;
    private int mIdempotency;
    private String mMethod;
    private long mNetworkHandle;
    private int mPriority;
    private Collection<Object> mRequestAnnotations;
    private RequestFinishedInfo.Listener mRequestFinishedListener;
    private final ArrayList<Map.Entry<String, String>> mRequestHeaders;
    private int mTrafficStatsTag;
    private boolean mTrafficStatsTagSet;
    private int mTrafficStatsUid;
    private boolean mTrafficStatsUidSet;
    private UploadDataProvider mUploadDataProvider;
    private Executor mUploadDataProviderExecutor;
    private final String mUrl;

    static {
    }

    public UrlRequestBuilderImpl(String r3, UrlRequest.Callback r4, Executor r5, CronetEngineBase r6) {
        this.mRequestHeaders = new ArrayList();
        this.mPriority = 3;
        this.mDictionaryId = _UrlKt.FRAGMENT_ENCODE_SET;
        this.mNetworkHandle = -1;
        this.mIdempotency = 0;
        Objects.requireNonNull(r3, "URL is required.");
        this.mUrl = r3;
        Objects.requireNonNull(r4, "Callback is required.");
        this.mCallback = r4;
        Objects.requireNonNull(r5, "Executor is required.");
        this.mExecutor = r5;
        Objects.requireNonNull(r6, "CronetEngine is required.");
        this.mCronetEngine = r6;
    }

    @Override // org.chromium.net.UrlRequest.Builder
    public /* bridge */ /* synthetic */ UrlRequest.Builder addHeader(String r1, String r2) {
        return addHeader(r1, r2);
    }

    @Override // org.chromium.net.UrlRequest.Builder
    public UrlRequestBuilderImpl addHeader(String r3, String r4) {
        Objects.requireNonNull(r3, "Invalid header name.");
        Objects.requireNonNull(r4, "Invalid header value.");
        if ("Accept-Encoding".equalsIgnoreCase(r3) == false) goto L8;
        String r32 = TAG;
        if (Log.isLoggable(r32, 3) == false) goto L7;
        Log.d(r32, "It's not necessary to set Accept-Encoding on requests - cronet will do this automatically for you, and setting it yourself has no effect. See https://crbug.com/581399 for details.", new Exception());
    L7:
        return this;
    L8:
        this.mRequestHeaders.add(new AbstractMap.SimpleEntry(r3, r4));
        return this;
    }

    @Override // org.chromium.net.UrlRequest.Builder
    public ExperimentalUrlRequest build() {
        CronetEngineBase r1 = this.mCronetEngine;
        String r2 = this.mUrl;
        UrlRequest.Callback r3 = this.mCallback;
        Executor r4 = this.mExecutor;
        int r5 = this.mPriority;
        Collection<Object> r6 = this.mRequestAnnotations;
        boolean r7 = this.mDisableCache;
        boolean r8 = this.mDisableConnectionMigration;
        boolean r9 = this.mAllowDirectExecutor;
        boolean r10 = this.mTrafficStatsTagSet;
        int r11 = this.mTrafficStatsTag;
        boolean r12 = this.mTrafficStatsUidSet;
        int r13 = this.mTrafficStatsUid;
        RequestFinishedInfo.Listener r14 = this.mRequestFinishedListener;
        int r15 = this.mIdempotency;
        long r16 = this.mNetworkHandle;
        String r17 = this.mMethod;
        if (r17 != null) goto L5;
        r17 = "GET";
    L5:
        String r20 = r17;
        return r1.createRequest(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r20, this.mRequestHeaders, this.mUploadDataProvider, this.mUploadDataProviderExecutor, this.mDictionarySha256Hash, this.mDictionary, this.mDictionaryId);
    }

    @Override // org.chromium.net.UrlRequest.Builder
    public /* bridge */ /* synthetic */ UrlRequest build() {
        return build();
    }

    @Override // org.chromium.net.UrlRequest.Builder
    public ExperimentalUrlRequest.Builder setHttpMethod(String r2) {
        Objects.requireNonNull(r2, "Method is required.");
        this.mMethod = r2;
        return this;
    }

    @Override // org.chromium.net.UrlRequest.Builder
    public /* bridge */ /* synthetic */ UrlRequest.Builder setHttpMethod(String r1) {
        return setHttpMethod(r1);
    }

    @Override // org.chromium.net.UrlRequest.Builder
    public /* bridge */ /* synthetic */ UrlRequest.Builder setTrafficStatsTag(int r1) {
        return setTrafficStatsTag(r1);
    }

    @Override // org.chromium.net.UrlRequest.Builder
    public UrlRequestBuilderImpl setTrafficStatsTag(int r2) {
        this.mTrafficStatsTagSet = true;
        this.mTrafficStatsTag = r2;
        return this;
    }

    @Override // org.chromium.net.UrlRequest.Builder
    public /* bridge */ /* synthetic */ UrlRequest.Builder setUploadDataProvider(UploadDataProvider r1, Executor r2) {
        return setUploadDataProvider(r1, r2);
    }

    @Override // org.chromium.net.UrlRequest.Builder
    public UrlRequestBuilderImpl setUploadDataProvider(UploadDataProvider r2, Executor r3) {
        Objects.requireNonNull(r2, "Invalid UploadDataProvider.");
        this.mUploadDataProvider = r2;
        Objects.requireNonNull(r3, "Invalid UploadDataProvider Executor.");
        this.mUploadDataProviderExecutor = r3;
        if (this.mMethod != null) goto L5;
        this.mMethod = "POST";
    L5:
        return this;
    }
}
