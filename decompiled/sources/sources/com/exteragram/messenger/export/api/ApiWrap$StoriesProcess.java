package com.exteragram.messenger.export.api;

import org.telegram.messenger.Utilities;

/* loaded from: classes4.dex */
public class ApiWrap$StoriesProcess {
    public int fileIndex;
    public Utilities.CallbackReturn<ApiWrap$DownloadProgress, Boolean> fileProgress;
    public Runnable finish;
    public Utilities.CallbackReturn<ApiWrap$StoriesSlice, Boolean> handleSlice;
    public boolean lastSlice;
    public int offsetId;
    public int processed;
    public ApiWrap$StoriesSlice slice;
    public Utilities.CallbackReturn<Integer, Boolean> start;

    public ApiWrap$StoriesProcess() {
        this.processed = 0;
        this.offsetId = 0;
        this.lastSlice = false;
        this.fileIndex = 0;
    }
}
