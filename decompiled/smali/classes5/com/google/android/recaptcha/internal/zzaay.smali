.class public final Lcom/google/android/recaptcha/internal/zzaay;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field public static final zza:Lcom/google/android/recaptcha/internal/zzaay;


# instance fields
.field private final zzb:Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/google/android/recaptcha/internal/zzaay;

    const-string v1, "SHA2"

    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzaay;-><init>(Ljava/lang/String;)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzaay;->zza:Lcom/google/android/recaptcha/internal/zzaay;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const-string p1, "SHA2"

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzaay;->zzb:Ljava/lang/String;

    return-void
.end method


# virtual methods
.method public final toString()Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzaay;->zzb:Ljava/lang/String;

    return-object p0
.end method
