package com.exteragram.messenger.export.api;

import com.exteragram.messenger.export.output.OutputFile;
import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.NativeByteBuffer;

/* loaded from: classes4.dex */
public class ApiWrap$FileProcess {
    public Utilities.Callback<String> done;
    public OutputFile file;
    public ApiWrap$FileLocation location;
    public long offset;
    public ApiWrap$FileOrigin origin;
    public Utilities.CallbackReturn<ApiWrap$FileProgress, Boolean> progress;
    public long randomId;
    public String relativePath;
    public long requestId;
    public Deque<Request> requests;
    public long size;

    public static class Request {
        public NativeByteBuffer bytes;
        public long offset;

        public Request() {
            this.offset = 0;
        }
    }

    public ApiWrap$FileProcess(String r3, OutputFile.Stats r4) {
        this.randomId = 0;
        this.offset = 0;
        this.size = 0;
        this.requestId = 0;
        this.file = new OutputFile(r3, r4);
        this.requests = new ConcurrentLinkedDeque();
    }
}
