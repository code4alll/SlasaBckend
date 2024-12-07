package com.ecommerce.slasa.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecommerce.slasa.Enums.Roles;
import com.ecommerce.slasa.Enums.Status;
import com.ecommerce.slasa.Model.UserModel;

public interface UserRepo extends JpaRepository<UserModel,Long> {

	Optional<UserModel> findByusername(String username);

	Optional<UserModel> findByUsernameAndRoleAndIsVerified(String userName, Roles role,Status isVerified);

	@Query("SELECT COUNT(u) > 0 FROM UserModel u WHERE u.email = :email AND u.role = :role AND u.isVerified = :isVerified")
	boolean existsByEmailRoleAndIsVerified(@Param("email") String email, @Param("role") Roles role, @Param("isVerified") Status isVerified);

	@Query("SELECT COUNT(u) > 0 FROM UserModel u WHERE u.username = :username AND u.role = :role AND u.isVerified = :isVerified")
	boolean existsByUsernameRoleAndIsVerified(@Param("username") String username, @Param("role") Roles role, @Param("isVerified") Status isVerified);
	

}
