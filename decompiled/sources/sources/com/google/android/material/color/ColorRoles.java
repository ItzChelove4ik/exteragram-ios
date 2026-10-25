package com.google.android.material.color;

/* loaded from: classes5.dex */
public final class ColorRoles {
    private final int accent;
    private final int accentContainer;
    private final int onAccent;
    private final int onAccentContainer;

    public ColorRoles(int r1, int r2, int r3, int r4) {
        this.accent = r1;
        this.onAccent = r2;
        this.accentContainer = r3;
        this.onAccentContainer = r4;
    }

    public int getAccent() {
        return this.accent;
    }

    public int getAccentContainer() {
        return this.accentContainer;
    }

    public int getOnAccent() {
        return this.onAccent;
    }

    public int getOnAccentContainer() {
        return this.onAccentContainer;
    }
}
