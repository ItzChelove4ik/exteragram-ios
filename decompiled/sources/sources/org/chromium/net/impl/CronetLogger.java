package org.chromium.net.impl;

import com.google.android.gms.cast.MediaError;
import j$.time.Duration;

/* loaded from: classes5.dex */
public abstract class CronetLogger {

    public static class CronetEngineBuilderInfo {
        private final boolean mBrotiEnabled;
        private final long mCronetInitializationRef;
        private final String mExperimentalOptions;
        private final boolean mHttp2Enabled;
        private final int mHttpCacheMode;
        private final boolean mNetworkQualityEstimatorEnabled;
        private final boolean mPublicKeyPinningBypassForLocalTrustAnchorsEnabled;
        private final boolean mQuicEnabled;
        private final String mStoragePath;
        private final int mThreadPriority;
        private final String mUserAgent;

        public CronetEngineBuilderInfo(boolean r1, String r2, String r3, boolean r4, boolean r5, boolean r6, int r7, String r8, boolean r9, int r10, long r11) {
            this.mPublicKeyPinningBypassForLocalTrustAnchorsEnabled = r1;
            this.mUserAgent = r2;
            this.mStoragePath = r3;
            this.mQuicEnabled = r4;
            this.mHttp2Enabled = r5;
            this.mBrotiEnabled = r6;
            this.mHttpCacheMode = r7;
            this.mExperimentalOptions = r8;
            this.mNetworkQualityEstimatorEnabled = r9;
            this.mThreadPriority = r10;
            this.mCronetInitializationRef = r11;
        }

        public long getCronetInitializationRef() {
            return this.mCronetInitializationRef;
        }

        public String getExperimentalOptions() {
            return this.mExperimentalOptions;
        }

        public int getHttpCacheMode() {
            return this.mHttpCacheMode;
        }

        public int getThreadPriority() {
            return this.mThreadPriority;
        }

        public boolean isBrotliEnabled() {
            return this.mBrotiEnabled;
        }

        public boolean isHttp2Enabled() {
            return this.mHttp2Enabled;
        }

        public boolean isNetworkQualityEstimatorEnabled() {
            return this.mNetworkQualityEstimatorEnabled;
        }

        public boolean isPublicKeyPinningBypassForLocalTrustAnchorsEnabled() {
            return this.mPublicKeyPinningBypassForLocalTrustAnchorsEnabled;
        }

        public boolean isQuicEnabled() {
            return this.mQuicEnabled;
        }
    }

    public static final class CronetEngineBuilderInitializedInfo {
        public CronetVersion apiVersion;
        public Author author;
        public Boolean creationSuccessful;
        public long cronetInitializationRef;
        public int engineBuilderCreatedLatencyMillis;
        public CronetVersion implVersion;
        public CronetSource source;
        public int uid;

        public enum Author extends Enum<Author> {
            private static final /* synthetic */ Author[] $VALUES = null;
            public static final Author API = null;
            public static final Author IMPL = null;

            private static /* synthetic */ Author[] $values() {
                return new Author[]{API, IMPL};
            }

            static {
                API = new Author("API", 0);
                IMPL = new Author("IMPL", 1);
                $VALUES = $values();
            }

            Author(String r1, int r2) {
            }

            public static Author valueOf(String r1) {
                return (Author) Enum.valueOf(Author.class, r1);
            }

            public static Author[] values() {
                return (Author[]) $VALUES.clone();
            }
        }

        public CronetEngineBuilderInitializedInfo() {
            this.engineBuilderCreatedLatencyMillis = -1;
            this.source = CronetSource.CRONET_SOURCE_UNSPECIFIED;
        }
    }

    public enum CronetSource extends Enum<CronetSource> {
        private static final /* synthetic */ CronetSource[] $VALUES = null;
        public static final CronetSource CRONET_SOURCE_FAKE = null;
        public static final CronetSource CRONET_SOURCE_FALLBACK = null;
        public static final CronetSource CRONET_SOURCE_PLATFORM = null;
        public static final CronetSource CRONET_SOURCE_PLAY_SERVICES = null;
        public static final CronetSource CRONET_SOURCE_STATICALLY_LINKED = null;
        public static final CronetSource CRONET_SOURCE_UNSPECIFIED = null;

        private static /* synthetic */ CronetSource[] $values() {
            return new CronetSource[]{CRONET_SOURCE_UNSPECIFIED, CRONET_SOURCE_STATICALLY_LINKED, CRONET_SOURCE_PLAY_SERVICES, CRONET_SOURCE_FALLBACK, CRONET_SOURCE_PLATFORM, CRONET_SOURCE_FAKE};
        }

        static {
            CRONET_SOURCE_UNSPECIFIED = new CronetSource("CRONET_SOURCE_UNSPECIFIED", 0);
            CRONET_SOURCE_STATICALLY_LINKED = new CronetSource("CRONET_SOURCE_STATICALLY_LINKED", 1);
            CRONET_SOURCE_PLAY_SERVICES = new CronetSource("CRONET_SOURCE_PLAY_SERVICES", 2);
            CRONET_SOURCE_FALLBACK = new CronetSource("CRONET_SOURCE_FALLBACK", 3);
            CRONET_SOURCE_PLATFORM = new CronetSource("CRONET_SOURCE_PLATFORM", 4);
            CRONET_SOURCE_FAKE = new CronetSource("CRONET_SOURCE_FAKE", 5);
            $VALUES = $values();
        }

        CronetSource(String r1, int r2) {
        }

        public static CronetSource valueOf(String r1) {
            return (CronetSource) Enum.valueOf(CronetSource.class, r1);
        }

        public static CronetSource[] values() {
            return (CronetSource[]) $VALUES.clone();
        }
    }

    public static class CronetTrafficInfo {
        private final CronetSource mCronetSource;
        private final String mCronetVersion;
        private final boolean mDidConnectionMigrationSucceed;
        private final RequestFailureReason mFailureReason;
        private final boolean mFinalUserCallbackThrew;
        private final Duration mHeadersLatency;
        private final boolean mIsBidiStream;
        private final String mNegotiatedProtocol;
        private final int mNetworkInternalErrorCode;
        private final int mNonfinalUserCallbackExceptionCount;
        private final int mOnUploadReadCount;
        private final int mQuicErrorCode;
        private final int mReadCount;
        private final long mRequestBodySizeInBytes;
        private final long mRequestHeaderSizeInBytes;
        private final long mResponseBodySizeInBytes;
        private final long mResponseHeaderSizeInBytes;
        private final int mResponseStatusCode;
        private final boolean mSocketReused;
        private final int mSource;
        private final RequestTerminalState mTerminalState;
        private final long mTimeToConnectMillis;
        private final long mTimeToEstablishDnsMillis;
        private final long mTimeToEstablishSSLMillis;
        private final long mTimeToSendFirstByteMillis;
        private final Duration mTotalLatency;
        private final int mUid;
        private final boolean mWasConnectionMigrationAttempted;

        public enum RequestFailureReason extends Enum<RequestFailureReason> {
            private static final /* synthetic */ RequestFailureReason[] $VALUES = null;
            public static final RequestFailureReason NETWORK = null;
            public static final RequestFailureReason OTHER = null;
            public static final RequestFailureReason UNKNOWN = null;

            private static /* synthetic */ RequestFailureReason[] $values() {
                return new RequestFailureReason[]{UNKNOWN, NETWORK, OTHER};
            }

            static {
                UNKNOWN = new RequestFailureReason("UNKNOWN", 0);
                NETWORK = new RequestFailureReason("NETWORK", 1);
                OTHER = new RequestFailureReason("OTHER", 2);
                $VALUES = $values();
            }

            RequestFailureReason(String r1, int r2) {
            }

            public static RequestFailureReason valueOf(String r1) {
                return (RequestFailureReason) Enum.valueOf(RequestFailureReason.class, r1);
            }

            public static RequestFailureReason[] values() {
                return (RequestFailureReason[]) $VALUES.clone();
            }
        }

        public enum RequestTerminalState extends Enum<RequestTerminalState> {
            private static final /* synthetic */ RequestTerminalState[] $VALUES = null;
            public static final RequestTerminalState CANCELLED = null;
            public static final RequestTerminalState ERROR = null;
            public static final RequestTerminalState SUCCEEDED = null;

            private static /* synthetic */ RequestTerminalState[] $values() {
                return new RequestTerminalState[]{SUCCEEDED, ERROR, CANCELLED};
            }

            static {
                SUCCEEDED = new RequestTerminalState("SUCCEEDED", 0);
                ERROR = new RequestTerminalState(MediaError.ERROR_TYPE_ERROR, 1);
                CANCELLED = new RequestTerminalState("CANCELLED", 2);
                $VALUES = $values();
            }

            RequestTerminalState(String r1, int r2) {
            }

            public static RequestTerminalState valueOf(String r1) {
                return (RequestTerminalState) Enum.valueOf(RequestTerminalState.class, r1);
            }

            public static RequestTerminalState[] values() {
                return (RequestTerminalState[]) $VALUES.clone();
            }
        }

        public CronetTrafficInfo(long r1, long r3, long r5, long r7, int r9, Duration r10, Duration r11, String r12, boolean r13, boolean r14, RequestTerminalState r15, int r16, int r17, int r18, boolean r19, boolean r20, int r21, int r22, int r23, int r24, RequestFailureReason r25, boolean r26, String r27, CronetSource r28, long r29, long r31, long r33, long r35) {
            this.mRequestHeaderSizeInBytes = r1;
            this.mRequestBodySizeInBytes = r3;
            this.mResponseHeaderSizeInBytes = r5;
            this.mResponseBodySizeInBytes = r7;
            this.mResponseStatusCode = r9;
            this.mHeadersLatency = r10;
            this.mTotalLatency = r11;
            this.mNegotiatedProtocol = r12;
            this.mWasConnectionMigrationAttempted = r13;
            this.mDidConnectionMigrationSucceed = r14;
            this.mTerminalState = r15;
            this.mNonfinalUserCallbackExceptionCount = r16;
            this.mReadCount = r17;
            this.mOnUploadReadCount = r18;
            this.mIsBidiStream = r19;
            this.mFinalUserCallbackThrew = r20;
            this.mUid = r21;
            this.mNetworkInternalErrorCode = r22;
            this.mQuicErrorCode = r23;
            this.mSource = r24;
            this.mFailureReason = r25;
            this.mSocketReused = r26;
            this.mCronetVersion = r27;
            this.mCronetSource = r28;
            this.mTimeToEstablishDnsMillis = r29;
            this.mTimeToEstablishSSLMillis = r31;
            this.mTimeToConnectMillis = r33;
            this.mTimeToSendFirstByteMillis = r35;
        }

        public boolean didConnectionMigrationSucceed() {
            return this.mDidConnectionMigrationSucceed;
        }

        public int getConnectionCloseSource() {
            return this.mSource;
        }

        public CronetSource getCronetSource() {
            return this.mCronetSource;
        }

        public String getCronetVersion() {
            return this.mCronetVersion;
        }

        public RequestFailureReason getFailureReason() {
            return this.mFailureReason;
        }

        public boolean getFinalUserCallbackThrew() {
            return this.mFinalUserCallbackThrew;
        }

        public Duration getHeadersLatency() {
            return this.mHeadersLatency;
        }

        public boolean getIsBidiStream() {
            return this.mIsBidiStream;
        }

        public boolean getIsSocketReused() {
            return this.mSocketReused;
        }

        public String getNegotiatedProtocol() {
            return this.mNegotiatedProtocol;
        }

        public int getNetworkInternalErrorCode() {
            return this.mNetworkInternalErrorCode;
        }

        public int getNonfinalUserCallbackExceptionCount() {
            return this.mNonfinalUserCallbackExceptionCount;
        }

        public int getOnUploadReadCount() {
            return this.mOnUploadReadCount;
        }

        public int getQuicErrorCode() {
            return this.mQuicErrorCode;
        }

        public int getReadCount() {
            return this.mReadCount;
        }

        public long getRequestBodySizeInBytes() {
            return this.mRequestBodySizeInBytes;
        }

        public long getRequestHeaderSizeInBytes() {
            return this.mRequestHeaderSizeInBytes;
        }

        public long getResponseBodySizeInBytes() {
            return this.mResponseBodySizeInBytes;
        }

        public long getResponseHeaderSizeInBytes() {
            return this.mResponseHeaderSizeInBytes;
        }

        public int getResponseStatusCode() {
            return this.mResponseStatusCode;
        }

        public RequestTerminalState getTerminalState() {
            return this.mTerminalState;
        }

        public long getTimeToConnectMillis() {
            return this.mTimeToConnectMillis;
        }

        public long getTimeToEstablishDNSMillis() {
            return this.mTimeToEstablishDnsMillis;
        }

        public long getTimeToEstablishSSLMillis() {
            return this.mTimeToEstablishSSLMillis;
        }

        public long getTimeToSendFirstByteMillis() {
            return this.mTimeToSendFirstByteMillis;
        }

        public Duration getTotalLatency() {
            return this.mTotalLatency;
        }

        public int getUid() {
            return this.mUid;
        }

        public boolean wasConnectionMigrationAttempted() {
            return this.mWasConnectionMigrationAttempted;
        }
    }

    public static class CronetVersion {
        private final int mBuildVersion;
        private final int mMajorVersion;
        private final int mMinorVersion;
        private final int mPatchVersion;

        public CronetVersion(String r2) {
            String[] r22 = r2.split("\\.");
            this.mMajorVersion = Integer.parseInt(r22[0]);
            this.mMinorVersion = Integer.parseInt(r22[1]);
            this.mBuildVersion = Integer.parseInt(r22[2]);
            this.mPatchVersion = Integer.parseInt(r22[3]);
        }

        public int getBuildVersion() {
            return this.mBuildVersion;
        }

        public int getMajorVersion() {
            return this.mMajorVersion;
        }

        public int getMinorVersion() {
            return this.mMinorVersion;
        }

        public int getPatchVersion() {
            return this.mPatchVersion;
        }

        public String toString() {
            return this.mMajorVersion + "." + this.mMinorVersion + "." + this.mBuildVersion + "." + this.mPatchVersion;
        }
    }

    public CronetLogger() {
    }

    public abstract long generateId();

    public abstract void logCronetEngineBuilderInitializedInfo(CronetEngineBuilderInitializedInfo r1);

    public abstract void logCronetEngineCreation(long r1, CronetEngineBuilderInfo r3, CronetVersion r4, CronetSource r5);

    public abstract void logCronetTrafficInfo(long r1, CronetTrafficInfo r3);
}
