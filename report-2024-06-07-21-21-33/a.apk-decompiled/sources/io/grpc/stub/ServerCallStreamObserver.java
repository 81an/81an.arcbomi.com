package io.grpc.stub;

/* loaded from: classes2.dex */
public abstract class ServerCallStreamObserver<RespT> extends CallStreamObserver<RespT> {
    public abstract boolean isCancelled();

    @Override // io.grpc.stub.CallStreamObserver
    public abstract boolean isReady();

    @Override // io.grpc.stub.CallStreamObserver
    public abstract void request(int i);

    public abstract void setCompression(String str);

    @Override // io.grpc.stub.CallStreamObserver
    public abstract void setMessageCompression(boolean z);

    public abstract void setOnCancelHandler(Runnable runnable);

    @Override // io.grpc.stub.CallStreamObserver
    public abstract void setOnReadyHandler(Runnable runnable);

    public void disableAutoRequest() {
        throw new UnsupportedOperationException();
    }

    public void setOnCloseHandler(Runnable runnable) {
        throw new UnsupportedOperationException();
    }
}
