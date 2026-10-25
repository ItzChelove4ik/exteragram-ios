.class public abstract Lcom/google/android/recaptcha/internal/zzaed;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzahe;


# static fields
.field private static final zza:Lcom/google/android/recaptcha/internal/zzafh;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzafh;->zza:Lcom/google/android/recaptcha/internal/zzafh;

    .line 2
    sget v0, Lcom/google/android/recaptcha/internal/zzaef;->$r8$clinit:I

    sget-object v0, Lcom/google/android/recaptcha/internal/zzafh;->zza:Lcom/google/android/recaptcha/internal/zzafh;

    sput-object v0, Lcom/google/android/recaptcha/internal/zzaed;->zza:Lcom/google/android/recaptcha/internal/zzafh;

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public abstract zza([BIILcom/google/android/recaptcha/internal/zzafh;)Lcom/google/android/recaptcha/internal/zzagx;
.end method

.method public final synthetic zzb([B)Ljava/lang/Object;
    .locals 3

    .line 1
    array-length v0, p1

    sget-object v1, Lcom/google/android/recaptcha/internal/zzaed;->zza:Lcom/google/android/recaptcha/internal/zzafh;

    const/4 v2, 0x0

    invoke-virtual {p0, p1, v2, v0, v1}, Lcom/google/android/recaptcha/internal/zzaed;->zza([BIILcom/google/android/recaptcha/internal/zzafh;)Lcom/google/android/recaptcha/internal/zzagx;

    move-result-object p0

    if-eqz p0, :cond_1

    .line 2
    invoke-interface {p0}, Lcom/google/android/recaptcha/internal/zzagy;->zzab()Z

    move-result p1

    if-eqz p1, :cond_0

    goto :goto_0

    .line 3
    :cond_0
    check-cast p0, Lcom/google/android/recaptcha/internal/zzaec;

    new-instance p1, Lcom/google/android/recaptcha/internal/zzahy;

    .line 4
    invoke-direct {p1, p0}, Lcom/google/android/recaptcha/internal/zzahy;-><init>(Lcom/google/android/recaptcha/internal/zzagx;)V

    .line 5
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzahy;->zza()Lcom/google/android/recaptcha/internal/zzagk;

    move-result-object p0

    .line 6
    throw p0

    :cond_1
    :goto_0
    return-object p0
.end method
