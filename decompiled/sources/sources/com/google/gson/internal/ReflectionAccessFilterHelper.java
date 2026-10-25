package com.google.gson.internal;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import com.google.gson.ReflectionAccessFilter$FilterResult;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public abstract class ReflectionAccessFilterHelper {

    /* renamed from: com.google.gson.internal.ReflectionAccessFilterHelper$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    /* loaded from: classes5.dex */
    public static abstract class AccessChecker {
        static final AccessChecker INSTANCE = null;

        /* renamed from: com.google.gson.internal.ReflectionAccessFilterHelper$AccessChecker$1, reason: invalid class name */
        public class AnonymousClass1 extends AccessChecker {
            final /* synthetic */ Method val$canAccessMethod;

            public AnonymousClass1(Method r1) {
                this.val$canAccessMethod = r1;
                super(null);
            }

            @Override // com.google.gson.internal.ReflectionAccessFilterHelper.AccessChecker
            public boolean canAccess(AccessibleObject r1, Object r2) {
                return ((Boolean) this.val$canAccessMethod.invoke(r1, new Object[]{r2})).booleanValue();
            L4:
                e = move-exception;
                Make$Map$$ExternalSyntheticBUOutline0.m("Failed invoking canAccess", e);
                return false;
            }
        }

        /* renamed from: com.google.gson.internal.ReflectionAccessFilterHelper$AccessChecker$2, reason: invalid class name */
        public class AnonymousClass2 extends AccessChecker {
            public AnonymousClass2() {
                super(null);
            }

            @Override // com.google.gson.internal.ReflectionAccessFilterHelper.AccessChecker
            public boolean canAccess(AccessibleObject r1, Object r2) {
                return true;
            }
        }

        static {
            if (JavaVersion.isJava9OrLater() == true) goto L12;
        L6:
            AccessChecker r1 = null;
        L7:
            if (r1 != null) goto L9;
            r1 = new AnonymousClass2();
        L9:
            INSTANCE = r1;
            return;
        L12:
            r1 = new AnonymousClass1(AccessibleObject.class.getDeclaredMethod("canAccess", new Class[]{Object.class}));     // Catch: NoSuchMethodException -> L11
            goto L7
        }

        private AccessChecker() {
        }

        public /* synthetic */ AccessChecker(AnonymousClass1 r1) {
            this();
        }

        public abstract boolean canAccess(AccessibleObject r1, Object r2);
    }

    public static boolean canAccess(AccessibleObject r1, Object r2) {
        return AccessChecker.INSTANCE.canAccess(r1, r2);
    }

    public static ReflectionAccessFilter$FilterResult getFilterResult(List<Object> r0, Class<?> r1) {
        Iterator<Object> r02 = r0.iterator();
        if (r02.hasNext() == false) goto L5;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r02.next());
        throw null;
    L5:
        return ReflectionAccessFilter$FilterResult.ALLOW;
    }
}
