.class public final Lcom/google/android/recaptcha/internal/zzm;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private final synthetic zza:I


# direct methods
.method public constructor <init>(I)V
    .locals 0

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzm;->zza:I

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final zza(I[I)Lcom/google/android/recaptcha/internal/zzj;
    .locals 0

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzm;->zza:I

    if-eqz p0, :cond_0

    new-instance p0, Lcom/google/android/recaptcha/internal/zzk;

    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzk;-><init>()V

    return-object p0

    :cond_0
    new-instance p0, Lcom/google/android/recaptcha/internal/zzn;

    invoke-direct {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzn;-><init>(I[I)V

    new-instance p1, Lcom/google/android/recaptcha/internal/zzl;

    invoke-direct {p1, p0}, Lcom/google/android/recaptcha/internal/zzl;-><init>(Lcom/google/android/recaptcha/internal/zzn;)V

    return-object p1
.end method
