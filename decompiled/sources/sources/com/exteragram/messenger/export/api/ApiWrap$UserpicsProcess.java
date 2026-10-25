package com.exteragram.messenger.export.api;

import com.exteragram.messenger.export.output.html.HtmlWriter;
import java.util.ArrayList;
import org.telegram.messenger.Utilities;

/* loaded from: classes4.dex */
public class ApiWrap$UserpicsProcess {
    public int fileIndex;
    public Utilities.CallbackReturn<ApiWrap$DownloadProgress, Boolean> fileProgress;
    public Runnable finish;
    public Utilities.CallbackReturn<ArrayList<HtmlWriter.Photo>, Boolean> handleSlice;
    public boolean lastSlice;
    public long maxId;
    public int processed;
    public ArrayList<HtmlWriter.Photo> slice;
    public Utilities.CallbackReturn<ApiWrap$UserpicsInfo, Boolean> start;

    public ApiWrap$UserpicsProcess() {
        this.processed = 0;
        this.maxId = 0;
        this.lastSlice = false;
        this.fileIndex = 0;
    }
}
