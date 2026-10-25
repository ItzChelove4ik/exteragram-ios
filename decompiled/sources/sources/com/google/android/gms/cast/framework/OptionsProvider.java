package com.google.android.gms.cast.framework;

import android.content.Context;
import java.util.List;

/* loaded from: classes4.dex */
public interface OptionsProvider {
    List<SessionProvider> getAdditionalSessionProviders(Context r1);

    CastOptions getCastOptions(Context r1);
}
