package androidx.car.app.managers;

import androidx.car.app.managers.Manager;

/* loaded from: classes3.dex */
public interface ManagerFactory<T extends Manager> {
    T create();
}
