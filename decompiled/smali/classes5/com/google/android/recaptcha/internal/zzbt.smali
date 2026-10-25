.class final Lcom/google/android/recaptcha/internal/zzbt;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/content/ComponentCallbacks2;


# instance fields
.field final synthetic zza:Lcom/google/android/recaptcha/internal/zzby;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzby;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzbt;->zza:Lcom/google/android/recaptcha/internal/zzby;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final onConfigurationChanged(Landroid/content/res/Configuration;)V
    .locals 0

    return-void
.end method

.method public final onLowMemory()V
    .locals 1

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzbt;->zza:Lcom/google/android/recaptcha/internal/zzby;

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzby;->zzg(Lcom/google/android/recaptcha/internal/zzby;)Lcom/google/android/recaptcha/internal/zzbi;

    move-result-object v0

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzbi;->zzc()Z

    move-result v0

    if-nez v0, :cond_0

    .line 2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzby;->zzd()V

    :cond_0
    return-void
.end method

.method public final onTrimMemory(I)V
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzbt;->zza:Lcom/google/android/recaptcha/internal/zzby;

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzby;->zzg(Lcom/google/android/recaptcha/internal/zzby;)Lcom/google/android/recaptcha/internal/zzbi;

    move-result-object p1

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzbi;->zzc()Z

    move-result p1

    if-nez p1, :cond_0

    .line 2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzby;->zzd()V

    :cond_0
    return-void
.end method
