.class public abstract Lcom/google/android/recaptcha/internal/zzvf;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private final zza:Ljava/lang/String;


# direct methods
.method public synthetic constructor <init>(Ljava/lang/String;[B)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzvf;->zza:Ljava/lang/String;

    return-void
.end method

.method public static zzb(Lcom/google/android/recaptcha/internal/zzve;Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzvf;
    .locals 1

    new-instance v0, Lcom/google/android/recaptcha/internal/zzvd;

    invoke-direct {v0, p1, p0}, Lcom/google/android/recaptcha/internal/zzvd;-><init>(Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzve;)V

    return-object v0
.end method


# virtual methods
.method public final zza()Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzvf;->zza:Ljava/lang/String;

    return-object p0
.end method
