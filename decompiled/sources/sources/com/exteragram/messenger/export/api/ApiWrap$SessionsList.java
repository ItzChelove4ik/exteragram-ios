package com.exteragram.messenger.export.api;

import java.util.ArrayList;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public class ApiWrap$SessionsList {
    public ArrayList<TLRPC.TL_authorization> list;
    public ArrayList<ApiWrap$WebSession> webList;

    public ApiWrap$SessionsList() {
        this.list = new ArrayList();
        this.webList = new ArrayList();
    }
}
