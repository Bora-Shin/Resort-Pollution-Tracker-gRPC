package RoomAirController;

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
    comments = "Source: RoomAir.proto")
public final class RoomAirGrpc {

  private RoomAirGrpc() {}

  public static final String SERVICE_NAME = "RoomAirController.RoomAir";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<RoomAirController.roomNum,
      RoomAirController.hourlyAirTracker> getControllRoomAirMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "controllRoomAir",
      requestType = RoomAirController.roomNum.class,
      responseType = RoomAirController.hourlyAirTracker.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<RoomAirController.roomNum,
      RoomAirController.hourlyAirTracker> getControllRoomAirMethod() {
    io.grpc.MethodDescriptor<RoomAirController.roomNum, RoomAirController.hourlyAirTracker> getControllRoomAirMethod;
    if ((getControllRoomAirMethod = RoomAirGrpc.getControllRoomAirMethod) == null) {
      synchronized (RoomAirGrpc.class) {
        if ((getControllRoomAirMethod = RoomAirGrpc.getControllRoomAirMethod) == null) {
          RoomAirGrpc.getControllRoomAirMethod = getControllRoomAirMethod = 
              io.grpc.MethodDescriptor.<RoomAirController.roomNum, RoomAirController.hourlyAirTracker>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "RoomAirController.RoomAir", "controllRoomAir"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  RoomAirController.roomNum.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  RoomAirController.hourlyAirTracker.getDefaultInstance()))
                  .setSchemaDescriptor(new RoomAirMethodDescriptorSupplier("controllRoomAir"))
                  .build();
          }
        }
     }
     return getControllRoomAirMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static RoomAirStub newStub(io.grpc.Channel channel) {
    return new RoomAirStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static RoomAirBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new RoomAirBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static RoomAirFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new RoomAirFutureStub(channel);
  }

  /**
   */
  public static abstract class RoomAirImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * server streaming rpc
     * </pre>
     */
    public void controllRoomAir(RoomAirController.roomNum request,
        io.grpc.stub.StreamObserver<RoomAirController.hourlyAirTracker> responseObserver) {
      asyncUnimplementedUnaryCall(getControllRoomAirMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getControllRoomAirMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                RoomAirController.roomNum,
                RoomAirController.hourlyAirTracker>(
                  this, METHODID_CONTROLL_ROOM_AIR)))
          .build();
    }
  }

  /**
   */
  public static final class RoomAirStub extends io.grpc.stub.AbstractStub<RoomAirStub> {
    private RoomAirStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RoomAirStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RoomAirStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RoomAirStub(channel, callOptions);
    }

    /**
     * <pre>
     * server streaming rpc
     * </pre>
     */
    public void controllRoomAir(RoomAirController.roomNum request,
        io.grpc.stub.StreamObserver<RoomAirController.hourlyAirTracker> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getControllRoomAirMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class RoomAirBlockingStub extends io.grpc.stub.AbstractStub<RoomAirBlockingStub> {
    private RoomAirBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RoomAirBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RoomAirBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RoomAirBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * server streaming rpc
     * </pre>
     */
    public java.util.Iterator<RoomAirController.hourlyAirTracker> controllRoomAir(
        RoomAirController.roomNum request) {
      return blockingServerStreamingCall(
          getChannel(), getControllRoomAirMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class RoomAirFutureStub extends io.grpc.stub.AbstractStub<RoomAirFutureStub> {
    private RoomAirFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RoomAirFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RoomAirFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RoomAirFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_CONTROLL_ROOM_AIR = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final RoomAirImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(RoomAirImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CONTROLL_ROOM_AIR:
          serviceImpl.controllRoomAir((RoomAirController.roomNum) request,
              (io.grpc.stub.StreamObserver<RoomAirController.hourlyAirTracker>) responseObserver);
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

  private static abstract class RoomAirBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    RoomAirBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return RoomAirController.RoomAirImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("RoomAir");
    }
  }

  private static final class RoomAirFileDescriptorSupplier
      extends RoomAirBaseDescriptorSupplier {
    RoomAirFileDescriptorSupplier() {}
  }

  private static final class RoomAirMethodDescriptorSupplier
      extends RoomAirBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    RoomAirMethodDescriptorSupplier(String methodName) {
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
      synchronized (RoomAirGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new RoomAirFileDescriptorSupplier())
              .addMethod(getControllRoomAirMethod())
              .build();
        }
      }
    }
    return result;
  }
}
