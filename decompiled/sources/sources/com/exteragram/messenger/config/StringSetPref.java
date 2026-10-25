package com.exteragram.messenger.config;

import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B!\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007J,\u0010\f\u001a\u0010\u0012\f\u0012\n \r*\u0004\u0018\u00010\u00030\u00030\u00022\u0006\u0010\u000e\u001a\u00020\u00032\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016J\u001e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u00032\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016R\u0018\u0010\b\u001a\u0006\u0012\u0002\b\u00030\tX\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0013"}, d2 = {"Lcom/exteragram/messenger/config/StringSetPref;", "Lcom/exteragram/messenger/config/BasePref;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "def", "backupKey", "<init>", "(Ljava/util/Set;Ljava/lang/String;)V", "backupClass", "Ljava/lang/Class;", "getBackupClass", "()Ljava/lang/Class;", "fetch", "kotlin.jvm.PlatformType", PluginsConstants.Settings.KEY, PluginsConstants.Settings.DEFAULT, "save", _UrlKt.FRAGMENT_ENCODE_SET, "value", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class StringSetPref extends BasePref<Set<? extends String>> {
    private final Class<?> backupClass;

    public StringSetPref(Set<String> r1, String r2) {
        super(r1, r2);
        this.backupClass = Set.class;
    }

    public /* synthetic */ StringSetPref(Set r1, String r2, int r3, DefaultConstructorMarker r4) {
        if ((r3 & 2) == 0) goto L5;
        r2 = null;
    L5:
        this(r1, r2);
    }

    @Override // com.exteragram.messenger.config.BasePref
    public /* bridge */ /* synthetic */ Set<? extends String> fetch(String r1, Set<? extends String> r2) {
        return fetch2(r1, r2);
    }

    /* renamed from: fetch, reason: avoid collision after fix types in other method */
    public Set<String> fetch2(String r1, Set<String> r2) {
        Set<String> r0 = ExteraConfig.getPreferences().getStringSet(r1, r2);
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
    public /* bridge */ /* synthetic */ void save(String r1, Set<? extends String> r2) {
        save2(r1, r2);
    }

    /* renamed from: save, reason: avoid collision after fix types in other method */
    public void save2(String r1, Set<String> r2) {
        ExteraConfig.getEditor().putStringSet(r1, r2).apply();
    }
}
