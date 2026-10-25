package com.google.common.util.concurrent;

import java.util.concurrent.locks.LockSupport;

/* loaded from: classes5.dex */
abstract class OverflowAvoidingLockSupport {
    public static void parkNanos(Object r2, long r3) {
        LockSupport.parkNanos(r2, Math.min(r3, 2147483647999999999L));
    }
}
