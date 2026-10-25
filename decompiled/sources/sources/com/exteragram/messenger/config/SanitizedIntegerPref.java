package com.exteragram.messenger.config;

import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.utils.chats.DoubleTapUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001d\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\u000fJ\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0002H\u0016R\u0018\u0010\b\u001a\u0006\u0012\u0002\b\u00030\tX\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0013"}, d2 = {"Lcom/exteragram/messenger/config/SanitizedIntegerPref;", "Lcom/exteragram/messenger/config/BasePref;", _UrlKt.FRAGMENT_ENCODE_SET, "def", "backupKey", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(ILjava/lang/String;)V", "backupClass", "Ljava/lang/Class;", "getBackupClass", "()Ljava/lang/Class;", "fetch", PluginsConstants.Settings.KEY, PluginsConstants.Settings.DEFAULT, "(Ljava/lang/String;I)Ljava/lang/Integer;", "save", _UrlKt.FRAGMENT_ENCODE_SET, "value", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SanitizedIntegerPref extends BasePref<Integer> {
    private final Class<?> backupClass;

    public SanitizedIntegerPref(int r1, String r2) {
        super(Integer.valueOf(r1), r2);
        this.backupClass = Integer.class;
    }

    public /* synthetic */ SanitizedIntegerPref(int r1, String r2, int r3, DefaultConstructorMarker r4) {
        if ((r3 & 2) == 0) goto L5;
        r2 = null;
    L5:
        this(r1, r2);
    }

    public Integer fetch(String r1, int r2) {
        return Integer.valueOf(DoubleTapUtils.sanitizeSetting(ExteraConfig.getPreferences().getInt(r1, r2)));
    }

    @Override // com.exteragram.messenger.config.BasePref
    public /* bridge */ /* synthetic */ Integer fetch(String r1, Integer r2) {
        return fetch(r1, r2.intValue());
    }

    @Override // com.exteragram.messenger.config.BasePref
    public Class<?> getBackupClass() {
        return this.backupClass;
    }

    public void save(String r1, int r2) {
        ExteraConfig.getEditor().putInt(r1, DoubleTapUtils.sanitizeSetting(r2)).apply();
    }

    @Override // com.exteragram.messenger.config.BasePref
    public /* bridge */ /* synthetic */ void save(String r1, Integer r2) {
        save(r1, r2.intValue());
    }
}
