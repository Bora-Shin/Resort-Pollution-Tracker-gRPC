package RoomWaterDispenser;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: RoomWaterDispenser.proto")
public final class RoomWaterDispenserGrpc {

  private RoomWaterDispenserGrpc() {}

  public static final String SERVICE_NAME = "RoomWaterDispenser.RoomWaterDispenser";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<RoomWaterDispenser.lastReplaced,
      RoomWaterDispenser.expired> getFilterExpiryMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "filterExpiry",
      requestType = RoomWaterDispenser.lastReplaced.class,
      responseType = RoomWaterDispenser.expired.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<RoomWaterDispenser.lastReplaced,
      RoomWaterDispenser.expired> getFilterExpiryMethod() {
    io.grpc.MethodDescriptor<RoomWaterDispenser.lastReplaced, RoomWaterDispenser.expired> getFilterExpiryMethod;
    if ((getFilterExpiryMethod = RoomWaterDispenserGrpc.getFilterExpiryMethod) == null) {
      synchronized (RoomWaterDispenserGrpc.class) {
        if ((getFilterExpiryMethod = RoomWaterDispenserGrpc.getFilterExpiryMethod) == null) {
          RoomWaterDispenserGrpc.getFilterExpiryMethod = getFilterExpiryMethod = 
              io.grpc.MethodDescriptor.<RoomWaterDispenser.lastReplaced, RoomWaterDispenser.expired>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "RoomWaterDispenser.RoomWaterDispenser", "filterExpiry"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  RoomWaterDispenser.lastReplaced.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  RoomWaterDispenser.expired.getDefaultInstance()))
                  .setSchemaDescriptor(new RoomWaterDispenserMethodDescriptorSupplier("filterExpiry"))
                  .build();
          }
        }
     }
     return getFilterExpiryMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static RoomWaterDispenserStub newStub(io.grpc.Channel channel) {
    return new RoomWaterDispenserStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static RoomWaterDispenserBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new RoomWaterDispenserBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static RoomWaterDispenserFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new RoomWaterDispenserFutureStub(channel);
  }

  /**
   */
  public static abstract class RoomWaterDispenserImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * unary rpc
     * </pre>
     */
    public void filterExpiry(RoomWaterDispenser.lastReplaced request,
        io.grpc.stub.StreamObserver<RoomWaterDispenser.expired> responseObserver) {
      asyncUnimplementedUnaryCall(getFilterExpiryMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getFilterExpiryMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                RoomWaterDispenser.lastReplaced,
                RoomWaterDispenser.expired>(
                  this, METHODID_FILTER_EXPIRY)))
          .build();
    }
  }

  /**
   */
  public static final class RoomWaterDispenserStub extends io.grpc.stub.AbstractStub<RoomWaterDispenserStub> {
    private RoomWaterDispenserStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RoomWaterDispenserStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RoomWaterDispenserStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RoomWaterDispenserStub(channel, callOptions);
    }

    /**
     * <pre>
     * unary rpc
     * </pre>
     */
    public void filterExpiry(RoomWaterDispenser.lastReplaced request,
        io.grpc.stub.StreamObserver<RoomWaterDispenser.expired> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getFilterExpiryMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class RoomWaterDispenserBlockingStub extends io.grpc.stub.AbstractStub<RoomWaterDispenserBlockingStub> {
    private RoomWaterDispenserBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RoomWaterDispenserBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RoomWaterDispenserBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RoomWaterDispenserBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * unary rpc
     * </pre>
     */
    public RoomWaterDispenser.expired filterExpiry(RoomWaterDispenser.lastReplaced request) {
      return blockingUnaryCall(
          getChannel(), getFilterExpiryMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class RoomWaterDispenserFutureStub extends io.grpc.stub.AbstractStub<RoomWaterDispenserFutureStub> {
    private RoomWaterDispenserFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RoomWaterDispenserFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RoomWaterDispenserFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RoomWaterDispenserFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * unary rpc
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<RoomWaterDispenser.expired> filterExpiry(
        RoomWaterDispenser.lastReplaced request) {
      return futureUnaryCall(
          getChannel().newCall(getFilterExpiryMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_FILTER_EXPIRY = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final RoomWaterDispenserImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(RoomWaterDispenserImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_FILTER_EXPIRY:
          serviceImpl.filterExpiry((RoomWaterDispenser.lastReplaced) request,
              (io.grpc.stub.StreamObserver<RoomWaterDispenser.expired>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class RoomWaterDispenserBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    RoomWaterDispenserBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return RoomWaterDispenser.RoomWaterDispenserImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("RoomWaterDispenser");
    }
  }

  private static final class RoomWaterDispenserFileDescriptorSupplier
      extends RoomWaterDispenserBaseDescriptorSupplier {
    RoomWaterDispenserFileDescriptorSupplier() {}
  }

  private static final class RoomWaterDispenserMethodDescriptorSupplier
      extends RoomWaterDispenserBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    RoomWaterDispenserMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (RoomWaterDispenserGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new RoomWaterDispenserFileDescriptorSupplier())
              .addMethod(getFilterExpiryMethod())
              .build();
        }
      }
    }
    return result;
  }
}
