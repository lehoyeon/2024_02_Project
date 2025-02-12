package com.monmi.repository;

import com.monmi.domain.Signin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SigninRepository extends JpaRepository<Signin, String> {

}
