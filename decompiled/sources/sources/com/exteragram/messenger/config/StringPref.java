package com.exteragram.messenger.config;

import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0018\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u0002H\u0016J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0002H\u0016R\u0018\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\bX\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0011"}, d2 = {"Lcom/exteragram/messenger/config/StringPref;", "Lcom/exteragram/messenger/config/BasePref;", _UrlKt.FRAGMENT_ENCODE_SET, "def", "backupKey", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "backupClass", "Ljava/lang/Class;", "getBackupClass", "()Ljava/lang/Class;", "fetch", PluginsConstants.Settings.KEY, PluginsConstants.Settings.DEFAULT, "save", _UrlKt.FRAGMENT_ENCODE_SET, "value", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class StringPref extends BasePref<String> {
    private final Class<?> backupClass;

    public StringPref(String r1, String r2) {
        super(r1, r2);
        this.backupClass = String.class;
    }

    public /* synthetic */ StringPref(String r1, String r2, int r3, DefaultConstructorMarker r4) {
        if ((r3 & 2) == 0) goto L5;
        r2 = null;
    L5:
        this(r1, r2);
    }

    @Override // com.exteragram.messenger.config.BasePref
    public /* bridge */ /* synthetic */ String fetch(String r1, String r2) {
        return fetch2(r1, r2);
    }

    /* renamed from: fetch, reason: avoid collision after fix types in other method */
    public String fetch2(String r1, String r2) {
        String r0 = ExteraConfig.getPreferences().getString(r1, r2);
        if (r0 != null) goto L5;
        return r2;
    L5:
        return r0;
    }

    @Override // com.exteragram.messenger.config.BasePref
    public Class<?> getBackupClass() {
        return this.backupClass;
    }

    @Override // com.exteragram.messenger.config.BasePref
    public /* bridge */ /* synthetic */ void save(String r1, String r2) {
        save2(r1, r2);
    }

    /* renamed from: save, reason: avoid collision after fix types in other method */
    public void save2(String r1, String r2) {
        ExteraConfig.getEditor().putString(r1, r2).apply();
    }
}
