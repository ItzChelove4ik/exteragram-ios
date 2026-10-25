package com.exteragram.messenger.config;

import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.lang.Enum;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001b\u0012\u0006\u0010\u0004\u001a\u00028\u0000\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\r\u001a\u00028\u00002\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0010J\u001d\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0014R\u0018\u0010\t\u001a\u0006\u0012\u0002\b\u00030\nX\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0015"}, d2 = {"Lcom/exteragram/messenger/config/EnumPref;", "E", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/config/BasePref;", "def", "backupKey", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/Enum;Ljava/lang/String;)V", "backupClass", "Ljava/lang/Class;", "getBackupClass", "()Ljava/lang/Class;", "fetch", PluginsConstants.Settings.KEY, PluginsConstants.Settings.DEFAULT, "(Ljava/lang/String;Ljava/lang/Enum;)Ljava/lang/Enum;", "save", _UrlKt.FRAGMENT_ENCODE_SET, "value", "(Ljava/lang/String;Ljava/lang/Enum;)V", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class EnumPref<E extends Enum<E>> extends BasePref<E> {
    private final Class<?> backupClass;

    public EnumPref(E r1, String r2) {
        super(r1, r2);
        this.backupClass = Integer.class;
    }

    public /* synthetic */ EnumPref(Enum r1, String r2, int r3, DefaultConstructorMarker r4) {
        if ((r3 & 2) == 0) goto L5;
        r2 = null;
    L5:
        this(r1, r2);
    }

    public E fetch(String r2, E r3) {
        int r1 = ExteraConfig.getPreferences().getInt(r2, r3.ordinal());
        Enum[] r22 = (Enum[]) r3.getClass().getEnumConstants();     // Catch: Exception -> L10
        if (r22 == null) goto L9;
        E r12 = (E) ArraysKt.getOrNull(r22, r1);     // Catch: Exception -> L10
        if (r12 == null) goto L9;
        return r12;
    L9:
        return r3;
    }

    @Override // com.exteragram.messenger.config.BasePref
    public /* bridge */ /* synthetic */ Object fetch(String r1, Object r2) {
        return fetch(r1, (Enum) r2);
    }

    @Override // com.exteragram.messenger.config.BasePref
    public Class<?> getBackupClass() {
        return this.backupClass;
    }

    public void save(String r1, E r2) {
        ExteraConfig.getEditor().putInt(r1, r2.ordinal()).apply();
    }

    @Override // com.exteragram.messenger.config.BasePref
    public /* bridge */ /* synthetic */ void save(String r1, Object r2) {
        save(r1, (Enum) r2);
    }
}
