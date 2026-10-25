package org.telegram.messenger.car;

import androidx.car.app.CarAppService;
import androidx.car.app.R$array;
import androidx.car.app.Session;
import androidx.car.app.validation.HostValidator;
import org.telegram.messenger.BuildVars;

/* loaded from: classes5.dex */
public class TelegramCarAppService extends CarAppService {
    public TelegramCarAppService() {
    }

    @Override // androidx.car.app.CarAppService
    public HostValidator createHostValidator() {
        if (BuildVars.DEBUG_VERSION == false) goto L7;
        return HostValidator.ALLOW_ALL_HOSTS_VALIDATOR;
    L7:
        return new HostValidator.Builder(getApplicationContext()).addAllowedHosts(R$array.hosts_allowlist_sample).build();
    }

    @Override // androidx.car.app.CarAppService
    public Session onCreateSession() {
        return new TelegramCarSession();
    }
}
