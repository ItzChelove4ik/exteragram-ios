package androidx.profileinstaller;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.Choreographer;
import androidx.startup.Initializer;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class ProfileInstallerInitializer implements Initializer<Result> {

    public static class Handler28Impl {
        public static Handler createAsync(Looper r0) {
            return Handler.createAsync(r0);
        }
    }

    public static class Result {
        public Result() {
        }
    }

    public static /* synthetic */ void $r8$lambda$NsMAloYpbbNbo7OHskmPQsgaOhM(Context r0) {
        ProfileInstaller.writeProfile(r0);
    }

    public static /* synthetic */ void $r8$lambda$VUbECJrladThvzfJ9dnTNG05Na4(Context r0) {
        writeInBackground(r0);
    }

    /* renamed from: $r8$lambda$Wf706qC-QjmOyoCCyLdfVmHaey4, reason: not valid java name */
    public static /* synthetic */ void m932$r8$lambda$Wf706qCQjmOyoCCyLdfVmHaey4(ProfileInstallerInitializer r0, Context r1, long r2) {
        r0.installAfterDelay(r1);
    }

    public ProfileInstallerInitializer() {
    }

    private static void writeInBackground(Context r7) {
        LinkedBlockingQueue r6 = new LinkedBlockingQueue();
        new ThreadPoolExecutor(0, 1, 0, TimeUnit.MILLISECONDS, r6).execute(new ProfileInstallerInitializer$$ExternalSyntheticLambda2(r7));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.startup.Initializer
    public Result create(Context r3) {
        Context r32 = r3.getApplicationContext();
        Choreographer.getInstance().postFrameCallback(new ProfileInstallerInitializer$$ExternalSyntheticLambda0(this, r32));
        return new Result();
    }

    @Override // androidx.startup.Initializer
    public /* bridge */ /* synthetic */ Result create(Context r1) {
        return create(r1);
    }

    @Override // androidx.startup.Initializer
    public List<Class<? extends Initializer<?>>> dependencies() {
        return Collections.EMPTY_LIST;
    }

    public void installAfterDelay(Context r5) {
        if (Build.VERSION.SDK_INT < 28) goto L5;
        Handler r4 = Handler28Impl.createAsync(Looper.getMainLooper());
    L6:
        int r0 = new Random().nextInt(Math.max(1000, 1));
        r4.postDelayed(new ProfileInstallerInitializer$$ExternalSyntheticLambda1(r5), r0 + 5000);
        return;
    L5:
        r4 = new Handler(Looper.getMainLooper());
        goto L6
    }
}
