package com.exteragram.messenger.config;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\u001a\r\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0001\u0010\u0002\u001a\u0015\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a\r\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\t\u001a\r\u0010\n\u001a\u00020\u0007¢\u0006\u0004\b\n\u0010\t\u001a\r\u0010\u000b\u001a\u00020\u0007¢\u0006\u0004\b\u000b\u0010\t\"\u0016\u0010\f\u001a\u00020\u00008\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {_UrlKt.FRAGMENT_ENCODE_SET, "getMode", "()I", "value", _UrlKt.FRAGMENT_ENCODE_SET, "setMode", "(I)V", _UrlKt.FRAGMENT_ENCODE_SET, "hidden", "()Z", "visible", "floating", "mode", "I", "TMessagesProj"}, k = 2, mv = {2, 2, 0}, xi = 48)
@JvmName(name = "BottomNavigationBar")
@SourceDebugExtension({"SMAP\nBottomNavigationBar.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BottomNavigationBar.kt\ncom/exteragram/messenger/config/BottomNavigationBar\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,27:1\n1#2:28\n*E\n"})
/* loaded from: classes.dex */
public abstract class BottomNavigationBar {
    private static int mode;

    static {
    }

    public static final boolean floating() {
        if (getMode() != 2) goto L6;
        return true;
    L6:
        return false;
    }

    public static final int getMode() {
        int r0 = RangesKt.coerceIn(mode, 0, 2);
        mode = r0;
        return r0;
    }

    public static final boolean hidden() {
        if (getMode() != 1) goto L5;
        return true;
    L5:
        return false;
    }

    public static final void setMode(int r0) {
        mode = r0;
        getMode();
    }

    public static final boolean visible() {
        if (getMode() == 1) goto L5;
        return true;
    L5:
        return false;
    }
}
