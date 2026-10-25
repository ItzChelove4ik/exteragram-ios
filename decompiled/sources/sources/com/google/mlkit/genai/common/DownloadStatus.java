package com.google.mlkit.genai.common;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0004\u0005\u0006\u0007\u0082\u0001\u0004\b\t\n\u000b¨\u0006\f"}, d2 = {"Lcom/google/mlkit/genai/common/DownloadStatus;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "DownloadStarted", "DownloadFailed", "DownloadProgress", "DownloadCompleted", "Lcom/google/mlkit/genai/common/DownloadStatus$DownloadCompleted;", "Lcom/google/mlkit/genai/common/DownloadStatus$DownloadFailed;", "Lcom/google/mlkit/genai/common/DownloadStatus$DownloadProgress;", "Lcom/google/mlkit/genai/common/DownloadStatus$DownloadStarted;", "java.com.google.android.libraries.mlkit.granules.genai.common_mlkit_genai_common"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public abstract class DownloadStatus {

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001¨\u0006\u0004"}, d2 = {"Lcom/google/mlkit/genai/common/DownloadStatus$DownloadCompleted;", "Lcom/google/mlkit/genai/common/DownloadStatus;", "<init>", "()V", "java.com.google.android.libraries.mlkit.granules.genai.common_mlkit_genai_common"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class DownloadCompleted extends DownloadStatus {
        public static final DownloadCompleted INSTANCE = null;

        static {
            INSTANCE = new DownloadCompleted();
        }

        private DownloadCompleted() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0007\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\n\u001a\u00020\tHÖ\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u001a\u0010\u000f\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\fHÖ\u0003¢\u0006\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lcom/google/mlkit/genai/common/DownloadStatus$DownloadFailed;", "Lcom/google/mlkit/genai/common/DownloadStatus;", "Lcom/google/mlkit/genai/common/GenAiException;", "e", "<init>", "(Lcom/google/mlkit/genai/common/GenAiException;)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", _UrlKt.FRAGMENT_ENCODE_SET, "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Lcom/google/mlkit/genai/common/GenAiException;", "getE", "()Lcom/google/mlkit/genai/common/GenAiException;", "java.com.google.android.libraries.mlkit.granules.genai.common_mlkit_genai_common"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class DownloadFailed extends DownloadStatus {
        private final GenAiException zza;

        public DownloadFailed(GenAiException r2) {
            super(null);
            this.zza = r2;
        }

        public boolean equals(Object r4) {
            if (this != r4) goto L6;
            return true;
        L6:
            if ((r4 instanceof DownloadFailed) == true) goto L9;
            return false;
        L9:
            if (Intrinsics.areEqual(this.zza, ((DownloadFailed) r4).zza) == true) goto L11;
            return false;
        L11:
            return true;
        }

        public final GenAiException getE() {
            return this.zza;
        }

        public int hashCode() {
            return this.zza.hashCode();
        }

        public String toString() {
            GenAiException r2 = this.zza;
            StringBuilder r1 = new StringBuilder(String.valueOf(r2).length() + 18);
            r1.append("DownloadFailed(e=");
            r1.append(r2);
            r1.append(")");
            return r1.toString();
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0007\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\n\u001a\u00020\tHÖ\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u001a\u0010\u000f\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\fHÖ\u0003¢\u0006\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lcom/google/mlkit/genai/common/DownloadStatus$DownloadProgress;", "Lcom/google/mlkit/genai/common/DownloadStatus;", _UrlKt.FRAGMENT_ENCODE_SET, "totalBytesDownloaded", "<init>", "(J)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", _UrlKt.FRAGMENT_ENCODE_SET, "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "J", "getTotalBytesDownloaded", "()J", "java.com.google.android.libraries.mlkit.granules.genai.common_mlkit_genai_common"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class DownloadProgress extends DownloadStatus {
        private final long zza;

        public DownloadProgress(long r2) {
            super(null);
            this.zza = r2;
        }

        public boolean equals(Object r6) {
            if (this != r6) goto L6;
            return true;
        L6:
            if ((r6 instanceof DownloadProgress) == true) goto L9;
            return false;
        L9:
            if (this.zza == ((DownloadProgress) r6).zza) goto L11;
            return false;
        L11:
            return true;
        }

        public final long getTotalBytesDownloaded() {
            return this.zza;
        }

        public int hashCode() {
            return Long.hashCode(this.zza);
        }

        public String toString() {
            long r0 = this.zza;
            StringBuilder r2 = new StringBuilder(String.valueOf(r0).length() + 39);
            r2.append("DownloadProgress(totalBytesDownloaded=");
            r2.append(r0);
            r2.append(")");
            return r2.toString();
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0007\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\n\u001a\u00020\tHÖ\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u001a\u0010\u000f\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\fHÖ\u0003¢\u0006\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lcom/google/mlkit/genai/common/DownloadStatus$DownloadStarted;", "Lcom/google/mlkit/genai/common/DownloadStatus;", _UrlKt.FRAGMENT_ENCODE_SET, "bytesToDownload", "<init>", "(J)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", _UrlKt.FRAGMENT_ENCODE_SET, "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "J", "getBytesToDownload", "()J", "java.com.google.android.libraries.mlkit.granules.genai.common_mlkit_genai_common"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class DownloadStarted extends DownloadStatus {
        private final long zza;

        public DownloadStarted(long r2) {
            super(null);
            this.zza = r2;
        }

        public boolean equals(Object r6) {
            if (this != r6) goto L6;
            return true;
        L6:
            if ((r6 instanceof DownloadStarted) == true) goto L9;
            return false;
        L9:
            if (this.zza == ((DownloadStarted) r6).zza) goto L11;
            return false;
        L11:
            return true;
        }

        public final long getBytesToDownload() {
            return this.zza;
        }

        public int hashCode() {
            return Long.hashCode(this.zza);
        }

        public String toString() {
            long r0 = this.zza;
            StringBuilder r2 = new StringBuilder(String.valueOf(r0).length() + 33);
            r2.append("DownloadStarted(bytesToDownload=");
            r2.append(r0);
            r2.append(")");
            return r2.toString();
        }
    }

    public /* synthetic */ DownloadStatus(DefaultConstructorMarker r1) {
    }
}
