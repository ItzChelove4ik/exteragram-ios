package com.exteragram.messenger.export.api;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public class ApiWrap$ChatProcess {
    public ApiWrap$ParseMediaContext context;
    public Runnable done;
    public int fileIndex;
    public Utilities.CallbackReturn<ApiWrap$DownloadProgress, Boolean> fileProgress;
    public Utilities.CallbackReturn<ApiWrap$MessagesSlice, Boolean> handleSlice;
    public ApiWrap$DialogInfo info;
    public int largestIdPlusOne;
    public boolean lastSlice;
    public int localSplitIndex;
    public Utilities.Callback<TLRPC.messages_Messages> requestDone;
    public ApiWrap$MessagesSlice slice;
    public Utilities.CallbackReturn<ApiWrap$DialogInfo, Boolean> start;

    public ApiWrap$ChatProcess() {
        this.localSplitIndex = 0;
        this.largestIdPlusOne = 1;
        this.context = new ApiWrap$ParseMediaContext();
        this.lastSlice = false;
        this.fileIndex = 0;
    }
}
