package com.googlecode.mp4parser.util;

import java.util.Date;

/* loaded from: classes5.dex */
public abstract class DateHelper {
    public static long convert(Date r4) {
        return (r4.getTime() / 1000) + 2082844800;
    }

    public static Date convert(long r3) {
        return new Date((r3 - 2082844800) * 1000);
    }
}
