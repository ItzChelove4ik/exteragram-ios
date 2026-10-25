.class public abstract Lcom/google/android/recaptcha/internal/zzhv;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public static final zza(Ljava/lang/String;Ljava/lang/Integer;)Ljava/util/concurrent/ThreadFactory;
    .locals 1

    .line 1
    new-instance p0, Lcom/google/android/recaptcha/internal/zzhw;

    const-string p1, "rC"

    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lcom/google/android/recaptcha/internal/zzhw;-><init>(Ljava/lang/String;Ljava/lang/Integer;)V

    return-object p0
.end method
