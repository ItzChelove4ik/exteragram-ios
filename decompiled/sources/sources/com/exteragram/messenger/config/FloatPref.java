package com.exteragram.messenger.config;

import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001d\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\u000fJ\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0002H\u0016R\u0018\u0010\b\u001a\u0006\u0012\u0002\b\u00030\tX\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0013"}, d2 = {"Lcom/exteragram/messenger/config/FloatPref;", "Lcom/exteragram/messenger/config/BasePref;", _UrlKt.FRAGMENT_ENCODE_SET, "def", "backupKey", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(FLjava/lang/String;)V", "backupClass", "Ljava/lang/Class;", "getBackupClass", "()Ljava/lang/Class;", "fetch", PluginsConstants.Settings.KEY, PluginsConstants.Settings.DEFAULT, "(Ljava/lang/String;F)Ljava/lang/Float;", "save", _UrlKt.FRAGMENT_ENCODE_SET, "value", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class FloatPref extends BasePref<Float> {
    private final Class<?> backupClass;

    public FloatPref(float r1, String r2) {
        super(Float.valueOf(r1), r2);
        this.backupClass = Float.class;
    }

    public /* synthetic */ FloatPref(float r1, String r2, int r3, DefaultConstructorMarker r4) {
        if ((r3 & 2) == 0) goto L5;
        r2 = null;
    L5:
        this(r1, r2);
    }

    public Float fetch(String r2, float r3) {
        float r1 = ExteraConfig.getPreferences().getFloat(r2, r3);     // Catch: ClassCastException -> L4
    L8:
        return Float.valueOf(r1);
    L4:
        r3 = ExteraConfig.getPreferences().getInt(r2, (int) r3);     // Catch: Exception -> L9
    L6:
        r1 = r3;
        goto L8
    }

    @Override // com.exteragram.messenger.config.BasePref
    public /* bridge */ /* synthetic */ Float fetch(String r1, Float r2) {
        return fetch(r1, r2.floatValue());
    }

    @Override // com.exteragram.messenger.config.BasePref
    public Class<?> getBackupClass() {
        return this.backupClass;
    }

    public void save(String r1, float r2) {
        ExteraConfig.getEditor().putFloat(r1, r2).apply();
    }

    @Override // com.exteragram.messenger.config.BasePref
    public /* bridge */ /* synthetic */ void save(String r1, Float r2) {
        save(r1, r2.floatValue());
    }
}
