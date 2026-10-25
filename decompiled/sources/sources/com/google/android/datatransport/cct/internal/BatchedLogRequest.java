package com.google.android.datatransport.cct.internal;

import com.google.firebase.encoders.DataEncoder;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import java.util.List;

/* loaded from: classes4.dex */
public abstract class BatchedLogRequest {
    public BatchedLogRequest() {
    }

    public static BatchedLogRequest create(List<LogRequest> r1) {
        return new AutoValue_BatchedLogRequest(r1);
    }

    public static DataEncoder createDataEncoder() {
        return new JsonDataEncoderBuilder().configureWith(AutoBatchedLogRequestEncoder.CONFIG).ignoreNullValues(true).build();
    }

    public abstract List<LogRequest> getLogRequests();
}
