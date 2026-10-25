package org.telegram.ui.Components;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import androidx.mediarouter.app.MediaRouteButton;
import java.lang.reflect.Field;
import org.telegram.messenger.ApplicationLoader;

/* loaded from: classes7.dex */
public abstract class CastMediaRouteButton extends MediaRouteButton {
    private boolean lastConnected;

    /* renamed from: org.telegram.ui.Components.CastMediaRouteButton$1, reason: invalid class name */
    public class AnonymousClass1 extends ContextWrapper {
        public AnonymousClass1(Context r1) {
            super(r1);
        }

        @Override // android.content.ContextWrapper, android.content.Context
        public Resources getResources() {
            return ApplicationLoader.applicationContext.getResources();
        }
    }

    public CastMediaRouteButton(Context r2) {
        super(new AnonymousClass1(r2));
    }

    private void checkConnected() {
        boolean r0 = isConnected();
        if (this.lastConnected == r0) goto L6;
        this.lastConnected = r0;
        stateUpdated(r0);
        return;
    }

    @Override // android.view.View
    public void dispatchDraw(Canvas r1) {
        checkConnected();
    }

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
        checkConnected();
    }

    public boolean isConnected() {
        Field r1 = MediaRouteButton.class.getDeclaredField("mConnectionState");     // Catch: Exception -> L7
        r1.setAccessible(true);     // Catch: Exception -> L7
        if (((Integer) r1.get(this)).intValue() <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // androidx.mediarouter.app.MediaRouteButton, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        checkConnected();
    }

    @Override // androidx.mediarouter.app.MediaRouteButton, android.view.View
    public void onDraw(Canvas r1) {
        checkConnected();
    }

    @Override // android.view.View
    public void setBackground(Drawable r1) {
    }

    public abstract void stateUpdated(boolean r1);
}
