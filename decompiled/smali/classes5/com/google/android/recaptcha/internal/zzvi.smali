.class public abstract Lcom/google/android/recaptcha/internal/zzvi;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private final zza:Ljava/lang/Class;


# direct methods
.method public synthetic constructor <init>(Ljava/lang/Class;Ljava/lang/Class;[B)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzvi;->zza:Ljava/lang/Class;

    return-void
.end method

.method public static zzb(Lcom/google/android/recaptcha/internal/zzvh;Ljava/lang/Class;)Lcom/google/android/recaptcha/internal/zzvi;
    .locals 2

    new-instance v0, Lcom/google/android/recaptcha/internal/zzvg;

    const-class v1, Lcom/google/android/recaptcha/internal/zzsr;

    invoke-direct {v0, p1, v1, p0}, Lcom/google/android/recaptcha/internal/zzvg;-><init>(Ljava/lang/Class;Ljava/lang/Class;Lcom/google/android/recaptcha/internal/zzvh;)V

    return-object v0
.end method


# virtual methods
.method public final zza()Ljava/lang/Class;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzvi;->zza:Ljava/lang/Class;

    return-object p0
.end method
