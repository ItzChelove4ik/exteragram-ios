package com.google.android.recaptcha.internal;

import j$.util.DesugarTimeZone;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/* loaded from: classes5.dex */
final class zzaip extends ThreadLocal {
    public zzaip() {
    }

    @Override // java.lang.ThreadLocal
    public final /* synthetic */ Object initialValue() {
        int r4 = zzaiq.$r8$clinit;
        SimpleDateFormat r42 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
        GregorianCalendar r0 = new GregorianCalendar(DesugarTimeZone.getTimeZone("UTC"));
        r0.setGregorianChange(new Date(Long.MIN_VALUE));
        r42.setCalendar(r0);
        return r42;
    }
}
