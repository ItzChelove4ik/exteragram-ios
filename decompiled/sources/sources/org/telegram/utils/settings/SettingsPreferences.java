package org.telegram.utils.settings;

import android.content.SharedPreferences;
import org.telegram.messenger.ApplicationLoader;

/* loaded from: classes6.dex */
abstract class SettingsPreferences {

    public static final class Holder {
        private static final SharedPreferences INSTANCE = null;

        /* renamed from: -$$Nest$sfgetINSTANCE, reason: not valid java name */
        public static /* bridge */ /* synthetic */ SharedPreferences m22264$$Nest$sfgetINSTANCE() {
            return INSTANCE;
        }

        static {
            INSTANCE = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0);
        }
    }

    public static SharedPreferences get() {
        return Holder.m22264$$Nest$sfgetINSTANCE();
    }
}
