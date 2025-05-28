package com.example.demo.users.services;

import com.example.demo.users.grpc.generated.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.stereotype.Service;

@Service
@GrpcService
public class UserRpcService extends UserServiceGrpc.UserServiceImplBase {

    @Override
    public void getUser(GetUserRequest request, StreamObserver<UserResponse> responseObserver) {
        // Mock implementation
        UserResponse response = UserResponse.newBuilder()
                .setUserId(request.getUserId())
                .setName("John Doe")
                .setEmail("john.doe@example.com")
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void createUser(CreateUserRequest request, StreamObserver<UserResponse> responseObserver) {
        // Mock implementation
        UserResponse response = UserResponse.newBuilder()
                .setUserId("user-" + System.currentTimeMillis())
                .setName(request.getName())
                .setEmail(request.getEmail())
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
} 