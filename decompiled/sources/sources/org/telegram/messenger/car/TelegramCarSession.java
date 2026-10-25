package org.telegram.messenger.car;

import android.content.Intent;
import androidx.car.app.Screen;
import androidx.car.app.Session;

/* loaded from: classes5.dex */
public class TelegramCarSession extends Session {
    public TelegramCarSession() {
    }

    @Override // androidx.car.app.Session
    public Screen onCreateScreen(Intent r1) {
        return new HomeScreen(getCarContext());
    }
}
