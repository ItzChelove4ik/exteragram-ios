package com.google.firebase.heartbeatinfo;

import com.google.firebase.components.Component;

/* loaded from: classes.dex */
public abstract class HeartBeatConsumerComponent {

    /* renamed from: com.google.firebase.heartbeatinfo.HeartBeatConsumerComponent$1, reason: invalid class name */
    public class AnonymousClass1 implements HeartBeatConsumer {
        public AnonymousClass1() {
        }
    }

    public static Component<?> create() {
        return Component.intoSet(new AnonymousClass1(), HeartBeatConsumer.class);
    }
}
