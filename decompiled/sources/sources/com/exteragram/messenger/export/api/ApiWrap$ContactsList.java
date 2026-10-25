package com.exteragram.messenger.export.api;

import java.util.ArrayList;

/* loaded from: classes4.dex */
public class ApiWrap$ContactsList {
    public ArrayList<ApiWrap$TopPeer> correspondents;
    public ArrayList<ApiWrap$TopPeer> inlineBots;
    public ArrayList<ApiWrap$ContactInfo> list;
    public ArrayList<ApiWrap$TopPeer> phoneCalls;

    public ApiWrap$ContactsList() {
        this.list = new ArrayList();
        this.correspondents = new ArrayList();
        this.inlineBots = new ArrayList();
        this.phoneCalls = new ArrayList();
    }
}
